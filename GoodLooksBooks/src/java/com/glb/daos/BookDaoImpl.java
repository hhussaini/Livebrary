package com.glb.daos;

import com.glb.constants.CategoryMap;
import static com.glb.helpers.Helpers.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.glb.objects.Book;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author mobile-mann
 */
public class BookDaoImpl extends JdbcDaoSupportImpl implements BookDao {
    private Statement stmt = null;
    private ResultSet res = null;
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
}
