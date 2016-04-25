package com.glb.daos;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            //Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
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
            //Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
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
            //Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(rs);
        }
        return book;
    }
}
