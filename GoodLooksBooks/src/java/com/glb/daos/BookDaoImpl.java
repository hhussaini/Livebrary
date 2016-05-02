package com.glb.daos;
 
import com.glb.constants.CategoryMap;
import static com.glb.helpers.Helpers.getTagFromXmlStr;
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
 *
 * @author mobile-mann
 */
public class BookDaoImpl extends JdbcDaoSupportImpl implements BookDao {
    
    private Statement stmt = null;
    private static CategoryMap categoryMap = new CategoryMap();
    private int numberOfResults;
    private int totalBooks;
    
    @Override
    public List<Book> searchBooks(String term, String[] categories, int offset, int recordsPerPage) {
        String limit = " limit " + offset + ", " + recordsPerPage;
        List<Book> results = new ArrayList<Book>();
        this.numberOfResults = 0;
        Connection conn = getConnection();
        ResultSet rs;
        
        // Category Query
        String catQuery = "";
        for (int i = 0; i < categories.length; i++) {
            if (i == 0)
                catQuery += " (";
            catQuery += "category rlike ?";
            if (i == categories.length - 1) {
                catQuery += ") ";
            } else {
                catQuery += " or ";
            }
        }
        // Search term query
        term = "%" + term + "%";
        String query = "create view results1 as"
                + " select b.isbn, b.title, b.author, b.imageUrl, c.category from books b, categories c"
                + " where b.isbn = c.isbn and"
                + " (b.publisher like ?"
                + " OR b.language like ?"
                + " OR b.author like ?"
                + " OR b.title like ?)";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("drop view if exists results1");
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, term);
            pstmt.setString(2, term);
            pstmt.setString(3, term);
            pstmt.setString(4, term);
            pstmt.executeUpdate();
            
            stmt.executeUpdate("drop view if exists results2");
            query =  "create view results2 as"
                    + " select isbn, title, author, imageUrl from results1 where"
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
            
            query = "select count(distinct isbn) from results2";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                numberOfResults = rs.getInt(1);
            }
            
            query = "select * from results2"
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
        String xmlStr = createXmlString(type, oldIsbn, newIsbn, title, author, description);
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
                String xmlStr = rs.getString("xmlStr");
                String oldIsbn = getTagFromXmlStr(xmlStr, "oldIsbn");
                String newIsbn = getTagFromXmlStr(xmlStr, "newIsbn");
                String title = getTagFromXmlStr(xmlStr, "title");
                String author = getTagFromXmlStr(xmlStr, "author");
                String description = getTagFromXmlStr(xmlStr, "description");
                status = updateBook(oldIsbn, newIsbn, title, author, description);
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
    
    private String createXmlString(String type, String oldIsbn, String newIsbn, String title, String author, String description) {
        String xmlStr = "<type>" + type + "</type>" +
                "<oldIsbn>" + oldIsbn + "</oldIsbn>" +
                "<newIsbn>" + newIsbn + "</newIsbn>" +
                "<title>" + title + "</title>" + 
                "<author>" + author + "</author>" + 
                "<description>" + description + "</description>";
        return xmlStr;
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
}
 
