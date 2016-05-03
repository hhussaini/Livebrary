package com.glb.daos;

import com.glb.constants.CategoryMap;
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
import com.glb.objects.Review;
import com.glb.objects.Ticket;
import com.glb.objects.User;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

/**
 * @author mobile-mann
 */
public class BookDaoImpl extends JdbcDaoSupportImpl implements BookDao {
    
    private Statement stmt = null;
    private static CategoryMap categoryMap = new CategoryMap();
    private int numberOfResults;
    private int totalBooks;
    
    @Override
    public List<Book> searchBooks(HashMap<String,String> searchTermMap, String[] categories, int offset, int recordsPerPage) {
        Connection conn = getConnection();
        ResultSet rs;
        String finalView = "";
        String limit = " limit " + offset + ", " + recordsPerPage;
        List<Book> results = new ArrayList<Book>();
        this.numberOfResults = 0;

        String keyword = searchTermMap.get("term");
        String author = searchTermMap.get("author");
        String publisher = searchTermMap.get("publisher");
        String isbn = searchTermMap.get("isbn");
        
        // Search term query
        String query = "create view searchTermView as"
                + " select b.isbn, b.title, b.author, b.imageUrl from books b"
                + " where"
                + " (b.publisher like ?"
                + " AND b.author like ?"
                + " AND b.title like ?"
                + " AND b.isbn like ?)";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("drop view if exists searchTermView");
            PreparedStatement pstmt = conn.prepareStatement(query);
            finalView = "searchTermView";
            
            pstmt.setString(1, "%"+publisher+"%");
            pstmt.setString(2, "%"+author+"%");
            pstmt.setString(3, "%"+keyword+"%");
            pstmt.setString(4, "%"+isbn+"%");
            pstmt.executeUpdate();
            
            // Category Query
            String catQuery = (categories[0].equals("")) ? null : "";
            if (!categories[0].equals("")) {
                for (int i = 0; i < categories.length; i++) {
                    if (i == 0)
                        catQuery += " (";
                    catQuery += "c.category rlike ?";
                    if (i == categories.length - 1) {
                        catQuery += ") ";
                    } else {
                        catQuery += " or ";
                    }
                }
                
                stmt = conn.createStatement();
                stmt.executeUpdate("drop view if exists categoryView");
                query =  "create view categoryView as"
                        + " select b.isbn, b.title, b.author, b.imageUrl from books b, category c, "
                        + " searchTermView r where b.isbn = r.isbn or c.isbn = r.isbn and "
                        + catQuery;
                pstmt = conn.prepareStatement(query);
                for (int i = 0; i < categories.length; i++) {
                    if (categories[i].equals(""))
                        pstmt.setString(i+1, ".*");
                    else {
                        String category = categoryMap.get(categories[i]).toString();
                        pstmt.setString(i+1, category);
                    }
                }
                pstmt.executeUpdate();
                finalView += " categoryView";
            }
            
            if (catQuery != null) {
                stmt = conn.createStatement();
                stmt.executeUpdate("drop view if exists finalView");
                query =  "create view finalView as"
                        + " select isbn, title, author, imageUrl"
                        + " from books inner join categories"
                        + " on books.isbn = categories.isbn";
                finalView = "finalView";
            }
            
            query = "select * from "+ finalView
                    + " group by isbn";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query + limit);
            
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setImageUrl(rs.getString("imageUrl"));
                book.setAuthor(rs.getString("author"));
                results.add(book);
            }
            rs.close();
            
            query = "select count(distinct isbn) from " + finalView;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                numberOfResults = rs.getInt(1);
            }
            
            if (numberOfResults == 0) {
                println("No results");
//                Book book = new Book();
//                book.setIsbn("0");
//                book.setTitle("");
//                book.setImageUrl("assets/no-results.png");
//                book.setAuthor("");
//                results.add(book);
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
        ResultSet rs;
        String query = "select * from books where title is not null";
        try {
            Statement stmt = conn.createStatement();
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
            rs.close();
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
    public Book getBookByIsbn(String isbn) {
        Book book = null;
        Connection conn = getConnection();
        ResultSet rs = null;
        String query = "select * from books where isbn = '" + isbn + "'";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setImageUrl(rs.getString("imageUrl"));
                book.setAuthor(rs.getString("author"));
                book.setDescription(rs.getString("description"));
                book.setDate(rs.getString("published"));
                book.setLanguage(rs.getString("language"));
                Map<String, Review> reviews = getAllReviewsForBook(isbn);
                book.setReviews(reviews);
            }
            rs.close();
            
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
        try {
            conToUse = getConnection();
            if (conToUse == null)
                System.out.println("conToUse == null");
            
            sql = "INSERT into RESERVED(username, isbn) values(?,?)";
            preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, isbn);
            status = preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(preparedStmt);
        }
        return status;
    }
    
    @Override
    public List<Book> getItemsList(String userName) {
        List<Book> itemsList = new ArrayList<>();
        Connection conToUse = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            conToUse = getConnection();
            String sql = "SELECT isbn from RESERVED WHERE username = ?";
            
            ps = (PreparedStatement) conToUse.prepareStatement(sql);
            ps.setString(1, userName);
            res = ps.executeQuery();
            
            while (res.next()) {
                itemsList.add(this.getBookByIsbn(res.getString("isbn")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        
        return itemsList;
    }
    
    @Override
    public Map<String, Review> getAllReviewsForBook(String isbn){
        Map<String, Review>reviewsMap = new HashMap<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        String query = "SELECT * FROM reviews WHERE isbn = '" + isbn + "'";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Review review = new Review();
                review.setRating(rs.getInt("rating"));
                review.setReviewText(rs.getString("reviewText"));
                String userName = rs.getString("username");
                reviewsMap.put(userName, review);
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(rs);
        }
        return reviewsMap;
    }
    
    @Override
    public int addReview(Review review, Book book, User user) {
        Connection conToUse = null;
        PreparedStatement preparedStmt = null;
        int status = 0;
        try {
            conToUse = getConnection();
            if (conToUse == null)
                System.out.println("conToUse == null");
            
            
            String sql = "INSERT INTO reviews(isbn, username, rating, reviewText) VALUES(?,?,?,?)";
            preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
            preparedStmt.setString(1, book.getIsbn());
            preparedStmt.setString(2, user.getUsername());
            preparedStmt.setInt(3, review.getRating());
            preparedStmt.setString(4, review.getReviewText());
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
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conToUse = getConnection();
            Statement stmt = conToUse.createStatement();
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
            DbUtils.closeQuietly(ps);
        }
        return tickets;
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
        return addBook(isbn, isbn10, title, author, description,
                binding, imageUrl, pages, language, listPrice, currency, publisher);
    }
    
    @Override
    public int acceptTicket(int ticketId) {
        String sql = "select type, xmlStr from TICKETS where id = " + "'" + ticketId + "'";
        Connection conToUse = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int status = 0;
        try {
            conToUse = getConnection();
            Statement stmt = conToUse.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String type = rs.getString("type");
                String xmlStr = rs.getString("xmlStr");
                if (type.equals("edit")) {
                    status = doEditTicket(xmlStr);
                } else if (type.equals("add")) {
                    status = doAddTicket(xmlStr);
                }
                if (status == 1) {
                    status = resolveTicket(ticketId, "y");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }
    
    @Override
    public int addBook(String isbn, String isbn10, String title, String author, String description,
            String binding, String imageUrl, int pages, String language, double listPrice,
            String currency, String publisher) {
        String sql = "insert into BOOKS values (isbn, isbn10, title, author, description, "
                + "binding, imageUrl, pages, language, listPrice, currency, publisher "
                + "(?,?,?,?,?,?,?,?,?,?,?,?)";
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
            status = ps.executeUpdate();
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
    public int submitAddRequest(String isbn, String isbn10, String title, String author, String description,
            String binding, String imageUrl, int pages, String language, double listPrice, String currency, String publisher) {
        String sql = "insert into TICKETS (type, xmlStr) values(?,?)";
        Connection conToUse = null;
        PreparedStatement ps = null;
        String type = "add";
        String xmlStr = createAddXmlString(isbn, isbn10, title, author, description,
                binding, imageUrl, pages, language, listPrice, currency, publisher);
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
    
    private String createAddXmlString(String isbn, String isbn10, String title, String author, String description,
            String binding, String imageUrl, int pages, String language, double listPrice, String currency, String publisher) {
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
}

