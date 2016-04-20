package daos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;

import objects.Book;
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
    public List<Book> getWishlist(String userName) {
        List<Book> booksOnWishlist = new ArrayList<Book>();
        
        Connection conToUse = null;
        java.sql.PreparedStatement ps = null;
        try {            
            // Class.forName(driver).newInstance();
            //conn = ConnectionUtil.getConnection(); //Connection) DriverManager.getConnection(dbURL, usr, pass);
            conToUse = getConnection();
            String sql = "select w.bookImageUrl, w.bookName from WISHLISTS w, CUSTOMERS c where w.USERNAME = c.USERNAME";
            
            stmt = (Statement) conToUse.createStatement();
            res = stmt.executeQuery(sql);            
            
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
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        
        return booksOnWishlist;
    }
    
    @Override
    public int removeFromWishlist(String username, String bookName) {
        Connection conToUse = null;
        PreparedStatement preparedStmt = null;
        String sql = "delete from WISHLISTS where bookname = ? and username = ?";
        int status = 0;
        try {
            // stmt = conn.createStatement();
            // res = stmt.executeQuery(sql);            
            preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
            preparedStmt.setString(1, bookName);
            preparedStmt.setString(2, username);            
            status = preparedStmt.executeUpdate();            
            sql = "select * from WISHLISTS where bookname = '"+bookName+"'";            
            stmt = (Statement) conToUse.createStatement();
            res = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(preparedStmt);
        }
        
        return status;
    }
}
