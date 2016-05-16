package com.glb.daos;

import com.glb.constants.CategoryMap;
import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.getTagFromXmlStr;
import static com.glb.helpers.Helpers.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.glb.objects.Book;
import com.glb.objects.Item;
import com.glb.objects.Review;
import com.glb.objects.Ticket;
import com.glb.objects.User;
import com.glb.services.UserService;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

/**
 * @author mobile-mann
 */
public class BookDaoImpl extends JdbcDaoSupportImpl implements BookDao {
    
    private static CategoryMap categoryMap = new CategoryMap();
    private int numberOfResults;
    private int totalBooks;
    
    @Override
    public List<Book> searchBooks(HashMap<String,String> searchTermMap, String[] categories, int offset, int recordsPerPage) {
        Connection conn = getConnection();
        boolean catSelected = !categories[0].equals("");
        ResultSet rs = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        String finalView = "";
        String limit = " limit " + offset + ", " + recordsPerPage;
        List<Book> results = new ArrayList<Book>();
        this.numberOfResults = 0;
        int pRows = 1;
        String title = searchTermMap.get("title");
        String author = searchTermMap.get("author");
        String publisher = searchTermMap.get("publisher");
        String isbn = searchTermMap.get("isbn");
        try {
            // Search term query
            String query = "create or replace view resultsView as "
                    + "select * from books "
                    + "where "
                    + "(publisher like ? "
                    + "AND author like ? "
                    + "AND title like ? "
                    + "AND isbn like ?) ";
            
            // CategoryMap Query
            if (catSelected) {
                query += "and isbn in ( "
                        + "select b.isbn from categories c, books b "
                        + "where b.isbn = c.isbn and (";
                
                for (int i = 0; i < categories.length; i++) {
                    query += "c.category regexp ?";
                    if (i != categories.length - 1) {
                        query += " or ";
                    }
                }
                
                query += "))";
            }
            
            pstmt = (PreparedStatement) conn.prepareStatement(query);// + limit);
            
            pstmt.setString(pRows++, "%"+publisher+"%");
            pstmt.setString(pRows++, "%"+author+"%");
            pstmt.setString(pRows++, "%"+title+"%");
            pstmt.setString(pRows++, "%"+isbn+"%");
            
            if (catSelected) {
                for (int i = 0; i < categories.length; i++) {
                    pstmt.setString(pRows++, categories[i]);
                }
            }
            
            pstmt.executeUpdate();
            
            String countQuery = "select count(*) from resultsView";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(countQuery);
            while (rs.next()) {
                numberOfResults = rs.getInt(1);
            }
            
            System.out.println(numberOfResults);
            
            rs = stmt.executeQuery("select * from resultsView " + limit);
            
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setImageUrl(rs.getString("imageUrl"));
                book.setAuthor(rs.getString("author"));
                book.setDate(rs.getString("published"));
                book.setCopiesLeft(rs.getInt("copiesLeft"));
                results.add(book);
            }
            rs.close();
            results.size();
            if (numberOfResults == 0) {
                println("No results");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(stmt != null)
                    stmt.close();
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
    
    @Override
    public int getNumberOfResults() {
        return this.numberOfResults;
    }
    
    @Override
    public int getTotalBooks() {
        return this.totalBooks;
    }
    
    @Override
    public List<Book> getAllBooks() {
        List<Book> results = new ArrayList<Book>();
        this.totalBooks = 0;
        Connection conn = getConnection();
        ResultSet rs = null;
        Statement stmt = null;
        String query = "select * from books where title is not null";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setImageUrl(rs.getString("imageUrl"));
                book.setAuthor(rs.getString("author"));
                results.add(book);
                totalBooks++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(rs);
        }
        return results;
    }
    
    @Override
    public Book getBookByIsbn(String isbn) {
        Book book = null;
        Connection conn = getConnection();
        ResultSet rs = null;
        Statement stmt = null;
        String query = "select * from books where isbn = '" + isbn + "'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setCopiesLeft(rs.getInt("copiesLeft"));
                book.setImageUrl(rs.getString("imageUrl"));
                book.setAuthor(rs.getString("author"));
                book.setDescription(rs.getString("description"));
                book.setDate(rs.getString("published"));
                book.setLanguage(rs.getString("language"));
                Map<String, Review> reviews = getAllReviewsForBook(isbn);
                book.setReviews(reviews);
                book.setSampleUrl(rs.getString("sampleUrl"));
                book.setDownloadUrl(rs.getString("downloadUrl"));
                book.setType(rs.getString("type"));
                //book.setIsBanned(rs.getInt("isBanned")==1?true:false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(rs);
        }
        return book;
    }
    
    @Override
    public int addBookToUserItems(String username, String isbn) {
        Connection conToUse = null;
        PreparedStatement preparedStmt = null;
        String sql = null;
        int status = 0;
        Book book = null;
        User user = null;
        UserService userService = ServiceFactory.getUserService();
        try {
            conToUse = getConnection();
            if (conToUse == null)
                System.out.println("conToUse == null");
            user = userService.getUser(username);
            book = getBookByIsbn(isbn);
            int lendPeriod = 0;
            // TODO. Add other book types
            switch (book.getType()) {
               case "eBook": lendPeriod = user.getEBookLendPeriod();
                             break;
            }
            Timestamp startDate = new Timestamp(new Date().getTime());
            Timestamp endDate = addDays(lendPeriod, startDate);
            sql = "insert into CHECKED_OUT(username, isbn, startDate, endDate) values(?,?,?,?)";
            preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, isbn);
            preparedStmt.setTimestamp(3, startDate);
            preparedStmt.setTimestamp(4, endDate);
            status = preparedStmt.executeUpdate();
            if (status == 1) {
               status = changeItemQuantity(isbn, -1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(preparedStmt);
        }
        return status;
    }
    
    /**
     * Checks if the item checked out by the user has expired. If so, then increment
     * the stock and delete the record from the checked_out table.
     * @param username
     * @param isbn
     * @return 
     */
    @Override
    public void checkExpiredCheckouts() {
      println("Inside checkExpiredCheckout");
      String sql = "select * from CHECKED_OUT";
      Connection conToUse = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
          conToUse = getConnection();
          ps = conToUse.prepareStatement(sql);
          rs = ps.executeQuery();
          Timestamp endDate = null;
          Date date= new Date();
          Timestamp now = new Timestamp(date.getTime());
          String isbn, username;
          while (rs.next()) {
             endDate = rs.getTimestamp("endDate");
             isbn = rs.getString("isbn");
             username = rs.getString("username");
             // If true, the check out is expired
             if (now.after(endDate) || now.equals(endDate)) {
               // TODO. Do email and stuff here for users waiting. PLACE THAT CODE
               // IN RETURNITEM
               returnItem(username, isbn);
            }
          }
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
          DbUtils.closeQuietly(ps);
      }
    }
    
    public int changeItemQuantity(String isbn, int quantityChange) {
        String sql = "select copiesLeft from BOOKS where isbn = ?";
        Connection conToUse = null;
        PreparedStatement ps = null;
        int status = 0;
        ResultSet rs = null;
        try {
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setString(1, isbn);
            rs = ps.executeQuery();
            int oldCopiesLeft = 0;
            while (rs.next()) {
               oldCopiesLeft = rs.getInt("copiesLeft");
            }
            rs.close();
            
            int updatedCopiesLeft = oldCopiesLeft + quantityChange;
            sql = "update BOOKS set copiesLeft = ? where isbn = ?";
            ps = conToUse.prepareStatement(sql);
            ps.setInt(1, updatedCopiesLeft);
            ps.setString(2, isbn);
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }
    
    @Override
    public Map<String, Review> getAllReviewsForBook(String isbn){
        Map<String, Review>reviewsMap = new LinkedHashMap<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        Statement stmt = null;
        String query = "SELECT * FROM reviews WHERE isbn = '" + isbn + "'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            Review tempReview = null;
            while (rs.next()) {
                Review review = new Review();
                review.setRating(rs.getInt("rating"));
                review.setReviewText(rs.getString("reviewText"));
                String userName = rs.getString("username");
                reviewsMap.put(userName, review);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(rs);
        }
        return reviewsMap;
    }
    
    @Override
    public int addReview(Review review, String isbn, String username) {
        Connection conToUse = null;
        PreparedStatement preparedStmt = null;
        int status = 0;
        try {
            conToUse = getConnection();
            if (conToUse == null)
                System.out.println("conToUse == null");
            String sql = "INSERT INTO reviews(isbn, username, rating, reviewText) VALUES(?,?,?,?)";
            preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
            preparedStmt.setString(1, isbn);
            preparedStmt.setString(2, username);
            preparedStmt.setInt(3, review.getRating());
            preparedStmt.setString(4, review.getReviewText());
            status = preparedStmt.executeUpdate();
            // book.addReview(user, review);
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(preparedStmt);
//            ConnectionUtil.closeStatement(preparedStmt);
//            ConnectionUtil.closeConnection(conToUse);
        }
        return status;
    }
    
    @Override
    public int submitEditRequest(String oldIsbn, String newIsbn, String title, String author, String description) {
        String sql = "insert into TICKETS (type, xmlStr) values(?,?)";
        Connection conToUse = null;
        PreparedStatement ps = null;
        String type = "edit";
        String xmlStr = createEditXmlString(oldIsbn, newIsbn, title, author, description);
        int status = 0;
        try {
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setString(1, type);
            ps.setString(2, xmlStr);
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }
    
    @Override
    public List<Ticket> getTickets(String resolved) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "select * from TICKETS where resolved = \'" + resolved + "\'";
        Connection conToUse = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conToUse = getConnection();
            stmt = conToUse.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setType(rs.getString("type"));
                ticket.setXmlStr(rs.getString("xmlStr"));
                tickets.add(ticket);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbUtils.closeQuietly(stmt);
        }
        return tickets;
    }
    
    @Override
    public int acceptTicket(int ticketId) {
        String sql = "select type, xmlStr from TICKETS where id = " + "'" + ticketId + "'";
        Connection conToUse = null;
        ResultSet rs = null;
        Statement stmt = null;
        int status = 0;
        try {
            conToUse = getConnection();
            stmt = conToUse.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String type = rs.getString("type");
                String xmlStr = rs.getString("xmlStr");
                if (type.equals("edit")) {
                    status = doEditTicket(xmlStr);
                } else if (type.equals("add")) {
                    status = doAddTicket(xmlStr);
                } else if (type.equals("delete")) {
                    status = doDeleteTicket(xmlStr);
                }
                if (status == 1) {
                    status = resolveTicket(ticketId, "y");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbUtils.closeQuietly(stmt);
        }
        return status;
    }
    
    @Override
    public int addBook(String isbn, String isbn10, String title, String author, String description,
            String binding, String imageUrl, int pages, String language, double listPrice,
            String currency, String publisher, String category) {
        String sql = "insert into BOOKS (isbn, isbn10, title, author, description, "
                + "binding, imageUrl, pages, language, listPrice, currency, publisher, published) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conToUse = null;
        PreparedStatement ps = null;
        int status = 0;
        try {
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setString(1, isbn);
            ps.setString(2, isbn10);
            ps.setString(3, title);
            ps.setString(4, author);
            ps.setString(5, description);
            ps.setString(6, binding);
            ps.setString(7, imageUrl);
            ps.setInt(8, pages);
            ps.setString(9, language);
            ps.setDouble(10, listPrice);
            ps.setString(11, currency);
            ps.setString(12, publisher);
            Date todaysDate = new Date();
            String todaysDateStr = new SimpleDateFormat("MM-dd-yyyy").format(todaysDate);
            ps.setString(13, todaysDateStr);
            status = ps.executeUpdate();
            if (status == 1) {
                sql = "insert into CATEGORIES (isbn, category) "
                        + "values (?,?)";
                ps = conToUse.prepareStatement(sql);
                ps.setString(1, isbn);
                ps.setString(2, category);
                status = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }
    
    @Override
    public int updateBook(String oldIsbn, String newIsbn, String title, String author, String description) {
        String sql = "update BOOKS set isbn = ?"
                + ",title = ?"
                + ",author = ?"
                + ",description = ?"
                + " where isbn = ?";
        Connection conToUse = null;
        PreparedStatement ps = null;
        Book book = null;
        int status = 0;
        try {
            book = getBookByIsbn(oldIsbn);
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setString(1, newIsbn.isEmpty() ? book.getIsbn() : newIsbn);
            ps.setString(2, title.isEmpty() ? book.getTitle() : title);
            ps.setString(3, author.isEmpty() ? book.getAuthor() : author);
            ps.setString(4, description.isEmpty() ? book.getDescription() : description);
            ps.setString(5, oldIsbn);
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }
    
    @Override
    public int deleteBook(String isbn) {
        // TODO. Maybe need to handle if a user owns this book?
        String sql = "delete from BOOKS where isbn = ?";
        Connection conToUse = null;
        PreparedStatement ps = null;
        int status = 0;
        try {
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setString(1, isbn);
            status = ps.executeUpdate();
            if (status == 1) {
                sql = "delete from CATEGORIES where isbn = ?";
                ps = conToUse.prepareStatement(sql);
                ps.setString(1, isbn);
                status = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }
    
    @Override
    public int resolveTicket(int ticketId, String accepted) {
        String sql = "update TICKETS set resolved = ?, accepted = ? "
                + "where id = ?";
        Connection conToUse = null;
        PreparedStatement ps = null;
        int status = 0;
        try {
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setString(1, "y");
            ps.setString(2, accepted);
            ps.setInt(3, ticketId);
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }
    
    @Override
    public int submitAddRequest(String isbn, String isbn10, String title, String author, String description, String binding,
            String imageUrl, int pages, String language, double listPrice, String currency, String publisher, String category) {
        String sql = "insert into TICKETS (type, xmlStr) values(?,?)";
        Connection conToUse = null;
        PreparedStatement ps = null;
        String type = "add";
        String xmlStr = createAddXmlString(isbn, isbn10, title, author, description,
                binding, imageUrl, pages, language, listPrice, currency, publisher, category);
        int status = 0;
        if (!categoryMap.containsKey(category)) {
            return 0;
        }
        try {
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setString(1, type);
            ps.setString(2, xmlStr);
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }
    
    @Override
    public int deleteReview(String isbn, String username) {
        Connection conToUse = null;
        PreparedStatement preparedStmt = null;
        int status = 0;
        try {
            conToUse = getConnection();
            if (conToUse == null)
                System.out.println("conToUse == null");
            String sql = "DELETE FROM reviews WHERE isbn = ? AND username = ?";
            preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
            preparedStmt.setString(1, isbn);
            preparedStmt.setString(2, username);
            status = preparedStmt.executeUpdate();
            // book.addReview(user, review);
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(preparedStmt);
        }
        return status;
    }
    
    private int doEditTicket(String xmlStr) {
        String oldIsbn = getTagFromXmlStr(xmlStr, "oldIsbn");
        String newIsbn = getTagFromXmlStr(xmlStr, "newIsbn");
        String title = getTagFromXmlStr(xmlStr, "title");
        String author = getTagFromXmlStr(xmlStr, "author");
        String description = getTagFromXmlStr(xmlStr, "description");
        return updateBook(oldIsbn, newIsbn, title, author, description);
    }
    
    private int doAddTicket(String xmlStr) {
        String isbn = getTagFromXmlStr(xmlStr, "isbn");
        String title = getTagFromXmlStr(xmlStr, "title");
        String author = getTagFromXmlStr(xmlStr, "author");
        String publisher = getTagFromXmlStr(xmlStr, "publisher");
        String description = getTagFromXmlStr(xmlStr, "description");
        String isbn10 = getTagFromXmlStr(xmlStr, "isbn10");
        String binding = getTagFromXmlStr(xmlStr, "binding");
        String imageUrl = getTagFromXmlStr(xmlStr, "imageUrl");
        int pages = Integer.parseInt(getTagFromXmlStr(xmlStr, "pages"));
        String language = getTagFromXmlStr(xmlStr, "language");
        double listPrice = Double.parseDouble(getTagFromXmlStr(xmlStr, "listPrice"));
        String currency = getTagFromXmlStr(xmlStr, "currency");
        String category = getTagFromXmlStr(xmlStr, "category");
        return addBook(isbn, isbn10, title, author, description, binding,
                imageUrl, pages, language, listPrice, currency, publisher, category);
    }
    
    private int doDeleteTicket(String xmlStr) {
        String isbn = getTagFromXmlStr(xmlStr, "isbn");
        return deleteBook(isbn);
    }
    
    private String createAddXmlString(String isbn, String isbn10, String title, String author, String description, String binding,
            String imageUrl, int pages, String language, double listPrice, String currency, String publisher, String category) {
        String xmlStr = "<type>add</type>" +
                "<isbn>" + isbn + "</isbn>" +
                "<isbn10>" + isbn10 + "</isbn10>" +
                "<title>" + title + "</title>" +
                "<author>" + author + "</author>" +
                "<description>" + description + "</description>" +
                "<binding>" + binding + "</binding>" +
                "<imageUrl>" + imageUrl + "</imageUrl>" +
                "<pages>" + pages + "</pages>" +
                "<language>" + language + "</language>" +
                "<listPrice>" + listPrice + "</listPrice>" +
                "<currency>" + currency + "</currency>" +
                "<category>" + category + "</category>" +
                "<publisher>" + publisher + "</publisher>";
        return xmlStr;
    }
    
    private String createEditXmlString(String oldIsbn, String newIsbn, String title,
            String author, String description) {
        String xmlStr = "<type>edit</type>" +
                "<oldIsbn>" + oldIsbn + "</oldIsbn>" +
                "<newIsbn>" + newIsbn + "</newIsbn>" +
                "<title>" + title + "</title>" +
                "<author>" + author + "</author>" +
                "<description>" + description + "</description>";
        return xmlStr;
    }
    
    private String createDeleteXmlString(String isbn) {
        String xmlStr = "<type>delete</type>" +
                "<isbn>" + isbn + "</isbn>";
        return xmlStr;
    }
    
    @Override
    public Book editReview(Review review, String isbn, String username) {
        String sql = "UPDATE reviews SET"
                + " rating = ?"
                + ",reviewText = ?"
                + " WHERE isbn = ? AND username = ?";
        Connection conToUse = null;
        PreparedStatement ps = null;
        Book book = null;
        int status = 0;
        try {
            book = getBookByIsbn(isbn);
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setInt(1, review.getRating());
            ps.setString(2, review.getReviewText());
            ps.setString(3, isbn);
            ps.setString(4, username);
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        return book;
    }
    
    @Override
    public int submitDeleteRequest(String isbn) {
        String sql = "insert into TICKETS (type, xmlStr) values(?,?)";
        Connection conToUse = null;
        PreparedStatement ps = null;
        String type = "delete";
        String xmlStr = createDeleteXmlString(isbn);
        int status = 0;
        try {
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setString(1, type);
            ps.setString(2, xmlStr);
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }
    
    @Override
    public int banBook(String isbn) {
        Connection conToUse = null;
        PreparedStatement preparedStmt = null;
        String sql = null;
        int status = 0;
        try {
            conToUse = getConnection();
            if (conToUse == null)
                System.out.println("conToUse == null");
            
            Book bookToBan = getBookByIsbn(isbn);
            bookToBan.setIsBanned(!bookToBan.getIsBanned());
            int isbanned = 0;
            if (bookToBan.getIsBanned() == false){isbanned = 0;}
            else if (bookToBan.getIsBanned() == true){isbanned = 1;}
            sql = "update Books B"  + " SET B.isBanned = " + "'" + isbanned + "'" +
                    " where B.isbn = " + "'" + bookToBan.getIsbn() + "'";
            preparedStmt = conToUse.prepareStatement(sql);
            status = preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(preparedStmt);
        }
        return status;
    }
    
    // TODO. This will need more logic when we figure out exactly how we're doing
    // reserves and holds and stuff 
   @Override
   public String getItemAccess(User user, Item item) {
      Connection conToUse = null;
      ResultSet rs = null;
      PreparedStatement ps = null;
      String isbn = item.getIsbn();
      String access = "Borrow";
      // First check if the user already has this item checked out
      String sql = "select username from CHECKED_OUT where isbn = ? and username = ? and expired = \'n\'";
      try {
          conToUse = getConnection();
          ps = conToUse.prepareStatement(sql);
          ps.setString(1, isbn);
          ps.setString(2, user.getUsername());
          rs = ps.executeQuery();
          int count = 0;
          while (rs.next()) {
            count++;
          }
          if (count > 0) {
            access = "isCheckedOut";
            return access;
          }
          rs.close();
          // Second, check if there are any copies of this item left
          int copiesLeft = 0;
          sql = "select copiesLeft from BOOKS where isbn = ?";
          ps = conToUse.prepareStatement(sql);
          ps.setString(1, isbn);
          rs = ps.executeQuery();
          while (rs.next()) {
            copiesLeft = rs.getInt("copiesLeft");
          }
          if (copiesLeft <= 0) {
            access = "Hold";
            return access;
          }
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
      }finally {
          DbUtils.closeQuietly(ps);
      }
      return access;
   }

   @Override
   public int returnItem(String username, String isbn) {
      int status = 0;
      Connection conToUse = null;
      PreparedStatement ps = null;
      String sql = "update CHECKED_OUT set expired = \'y\' where isbn = ? and username = ?";
      try {
          conToUse = getConnection();
          ps = conToUse.prepareStatement(sql);
          ps.setString(1, isbn);
          ps.setString(2, username);
          status = ps.executeUpdate();
          status = changeItemQuantity(isbn, 1);
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
      }finally {
          DbUtils.closeQuietly(ps);
      }
      return status;
   }

   @Override
   public int editHoldEmail(String username, String email, String isbn) {
      String sql = "update HOLDS set email = ? where username = ? and isbn = ?";
      Connection conToUse = null;
      PreparedStatement ps = null;
      int status = 0;
      try {
          conToUse = getConnection();
          ps = conToUse.prepareStatement(sql);
          ps.setString(1, email);
          ps.setString(2, username);
          ps.setString(3, isbn);
          status = ps.executeUpdate();
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
          DbUtils.closeQuietly(ps);
      }
      return status;
   }

   @Override
   public int suspendHold(String username, String isbn, int days) {
      String sql = "update HOLDS set suspended = \'y\', suspendDate = ? where username = ? and isbn = ? and suspended = \'n\'";
      Connection conToUse = null;
      PreparedStatement ps = null;
      int status = 0;
      try {
          conToUse = getConnection();
          ps = conToUse.prepareStatement(sql);
          Timestamp startDate = new Timestamp(new Date().getTime());
          Timestamp endDate = addDays(days, startDate);
          ps.setTimestamp(1, endDate);
          ps.setString(2, username);
          ps.setString(3, isbn);
          status = ps.executeUpdate();
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
          DbUtils.closeQuietly(ps);
      }
      return status;
   }

   @Override
   public int cancelSuspension(String username, String isbn) {
      String sql = "update HOLDS set suspended = \'n\' where username = ? and isbn = ? and suspended = \'y\'";
      Connection conToUse = null;
      PreparedStatement ps = null;
      int status = 0;
      try {
          conToUse = getConnection();
          ps = conToUse.prepareStatement(sql);
          ps.setString(1, username);
          ps.setString(2, isbn);
          status = ps.executeUpdate();
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
          DbUtils.closeQuietly(ps);
      }
      return status;
   }

   @Override
   public Timestamp getHoldSuspensionDate(String username, String isbn) {
      Connection conToUse = null;
      ResultSet rs = null;
      PreparedStatement ps = null;
      Timestamp date = null;
      String sql = "select suspendDate from HOLDS where isbn = ? and username = ? and suspended = \'y\'";
      try {
          conToUse = getConnection();
          ps = conToUse.prepareStatement(sql);
          ps.setString(1, isbn);
          ps.setString(2, username);
          rs = ps.executeQuery();
          while (rs.next()) {
            date = rs.getTimestamp("suspendDate");
          }
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
      }finally {
          DbUtils.closeQuietly(ps);
      }
      return date;
   }
}
