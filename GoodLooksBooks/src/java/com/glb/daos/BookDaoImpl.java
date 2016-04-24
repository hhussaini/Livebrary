package com.glb.daos;

import com.glb.controllers.FileController;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.glb.helpers.Helpers.*;

import com.glb.objects.Book;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author mobile-mann
 */
public class BookDaoImpl extends JdbcDaoSupportImpl implements BookDao {    
    private Statement stmt = null;
    private ResultSet res = null;
    private int numberOfResults;
    private int totalBooks;
    
    /**
     *
     * @param userName
     * @return
     * @throws ObjectException
     */
    @Override
    public List<Book> getWishlist(String username) {
        List<Book> booksOnWishlist = new ArrayList<Book>();        
        Connection conToUse = null;
        java.sql.PreparedStatement ps = null;
        try {
            conToUse = getConnection();
            String sql = "select * from WISHLISTS where USERNAME = ?";
            
            ps = (PreparedStatement) conToUse.prepareStatement(sql);
            ps.setString(1, username);
            res = ps.executeQuery();
            
            while (res.next()) {
                Book book = new Book();
                book.setIsbn(res.getString("isbn"));
                book.setTitle(res.getString("title"));
                book.setImageUrl(res.getString("imageUrl"));
                booksOnWishlist.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        
        return booksOnWishlist;
    }
    
    @Override
    public Book addToWishlist(String username, String isbn) {
        String title, imageUrl;
        title = imageUrl = null;
        Connection conToUse = null;
        PreparedStatement preparedStmt = null;
        String sql = null;//"insert into wishlist(username, bookname, imageUrl, isbn) values(?,?,?,?)";
        try {
            conToUse = getConnection();
            if (conToUse == null)
                System.out.println("conToUse == null");
            
            sql = "select imageUrl, title from books where isbn = ?";
            preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
            preparedStmt.setString(1, isbn);
            res = preparedStmt.executeQuery();
            while (res.next()) {
                title = res.getString("title");
                imageUrl = res.getString("imageUrl");
            }
            sql = "insert into wishlist(username, isbn, title, imageUrl) values(?,?,?,?)";
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, isbn);
            preparedStmt.setString(3, title);
            preparedStmt.setString(4, imageUrl);
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(preparedStmt);
        }
        
        return null;
    }
    
    @Override
    public int removeFromWishlist(String username, String isbn) {
        println("Username: " + username);
        Connection conToUse = null;
        PreparedStatement preparedStmt = null;
        String sql = "delete from WISHLISTS where isbn = ? and username = ?";
        int status = 0;
        try {
            conToUse = getConnection();
            if (conToUse == null)
                System.out.println("conToUse == null");
            preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
            preparedStmt.setString(1, isbn);
            preparedStmt.setString(2, username);
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
    public List<Book> searchBooks(String term, int offset, int recordsPerPage) {
        List<Book> results = new ArrayList<Book>();
        this.numberOfResults = 0;        
        Connection conn = getConnection();
        ResultSet rs;
        term = "%" + term + "%";
        String query = "select * from books where title is not null and"
                + " (publisher like ?"
                + " OR language like ?"
                + " OR author like ?"
                + " OR title like ?)";
        String limit = " limit " + offset + ", " + recordsPerPage;
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, term);
            pstmt.setString(2, term);
            pstmt.setString(3, term);
            pstmt.setString(4, term);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                numberOfResults++;
            }
            
            rs.close();
            
            pstmt = conn.prepareStatement(query + limit);
            pstmt.setString(1, term);
            pstmt.setString(2, term);
            pstmt.setString(3, term);
            pstmt.setString(4, term);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setImageUrl(rs.getString("imageUrl"));
                book.setAuthor(rs.getString("author"));
                if (book.getImageUrl().toString().length() > 0) {
                    results.add(book);
                    numberOfResults++;
                }
            }
            rs.close();
            
            Statement stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
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
                if (book.getImageUrl().toString().length() > 0) {
                    results.add(book);
                    totalBooks++;
                }
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
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
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(rs);
        }
        return book;
    }
}
