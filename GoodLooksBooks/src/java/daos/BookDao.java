package daos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Book;

/**
 *
 * @author mobile-mann
 */
public class BookDao {
    
    String dbURL = "jdbc:mysql://mysql2.cs.stonybrook.edu:3306/pmannarino";
    String usr = "pmannarino";
    String pass = "108060069";
    String driver = "com.mysql.jdbc.Driver";
    String sql;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet res = null;
    
    
    public List<Book> getWishlist(String username) {
        List<Book> booksOnWishlist = new ArrayList<Book>();
        
        try {
            
            Class.forName(driver).newInstance();
            conn = (Connection) DriverManager.getConnection(dbURL, usr, pass);
            if (conn != null) {
                System.out.println("Connected");
            }
            
            sql = "select bookImageUrl, bookName from WISHLISTS where USERNAME = ?";
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(sql);
            preparedStmt.setString(1, username);
            res = preparedStmt.executeQuery();
            
            
//            stmt = (Statement) conn.createStatement();
//            res = stmt.executeQuery(sql);


while (res.next()) {
    System.out.println("In BookDao");
    Book book = new Book();
    String name = res.getString("BOOKNAME");
    String imageUrl = res.getString("BOOKIMAGEURL");
    book.setName(name);
    book.setImageUrl(imageUrl);
    booksOnWishlist.add(book);
    
    System.out.println(name + ":" + imageUrl);
}
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return booksOnWishlist;
    }
    
    public void removeFromWishlist(String username, String bookName) {
        String sql = "delete from WISHLISTS where bookname = ? and username = ?";
        
        try {
//            stmt = conn.createStatement();
//            res = stmt.executeQuery(sql);

            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(sql);
            preparedStmt.setString(1, bookName);
            preparedStmt.setString(2, username);
            
            preparedStmt.executeUpdate();
            
            
            sql = "select * from WISHLISTS where bookname = '"+bookName+"'";
            
            stmt = (Statement) conn.createStatement();
            res = stmt.executeQuery(sql);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
