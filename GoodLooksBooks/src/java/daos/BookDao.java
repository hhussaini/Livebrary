package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
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
    public List<Book> getWishlist(String userName) {
        List<Book> booksOnWishlist = new ArrayList<Book>();
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        try {
            String dbURL = "jdbc:derby://localhost:1527/elibrary";
            String usr = "root"; 
            String pass = "root";
            String driver = "org.apache.derby.jdbc.ClientDriver";
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(dbURL, usr, pass);
            if (conn != null) {
                System.out.println("Connected");
            }
            String sql = "select w.imageurl, w.book from WISHLIST w, CUSTOMERS c where w.USERNAME = c.USERNAME";
            
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
           

            while (res.next()) {
                System.out.println("In BookDao");
                Book book = new Book();
                String name = res.getString("BOOK");
                String imageUrl = res.getString("IMAGEURL");
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
}
