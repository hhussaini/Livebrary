package com.glb.daos;

import com.mysql.jdbc.PreparedStatement;
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
    //@Resource(name="jdbc/glbdb")
   // private DataSource ds;
//    String dbURL = "jdbc:mysql://mysql2.cs.stonybrook.edu:3306/pmannarino";
//    String usr = "pmannarino";
//    String pass = "108060069";
//    String driver = "com.mysql.jdbc.Driver";
    
    Statement stmt = null;
    ResultSet res = null;
    
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
            // Class.forName(driver).newInstance();
            //conn = ConnectionUtil.getConnection(); //Connection) DriverManager.getConnection(dbURL, usr, pass);
            conToUse = getConnection();
            String sql = "select imageUrl, title from WISHLISTS where USERNAME = ?";
            
            ps = (PreparedStatement) conToUse.prepareStatement(sql);
            ps.setString(1, username);
            res = ps.executeQuery();           
            
            while (res.next()) {
                println("In BookDao");
                Book book = new Book();
                String name = res.getString("title");
                String imageUrl = res.getString("imageUrl");
                book.setName(name);
                book.setImageUrl(imageUrl);
                booksOnWishlist.add(book);
                
                System.out.println(name + ":" + imageUrl);
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
            // stmt = conn.createStatement();
            // res = stmt.executeQuery(sql); 
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
                println(title);
                println(imageUrl);
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
            //DbUtils.closeQuietly(preparedStmt);
        }
        
        return null;
    }
    
    @Override
    public int removeFromWishlist(String username, String bookName) {
        println("Username: " + username);
        Connection conToUse = null;
        PreparedStatement preparedStmt = null;
        String sql = "delete from WISHLISTS where bookname = ? and username = ?";
        int status = 0;
        try {
            // stmt = conn.createStatement();
            // res = stmt.executeQuery(sql); 
            conToUse = getConnection();
            if (conToUse == null)
                System.out.println("conToUse == null");
            preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
            preparedStmt.setString(1, bookName);
            preparedStmt.setString(2, username);            
            status = preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            //DbUtils.closeQuietly(preparedStmt);
        }
        
        return status;
    }
}
