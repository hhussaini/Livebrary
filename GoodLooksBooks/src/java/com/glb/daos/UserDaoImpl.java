package com.glb.daos;

import static com.glb.helpers.Helpers.println;
import com.glb.objects.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.glb.objects.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

public class UserDaoImpl extends JdbcDaoSupportImpl implements UserDao {
    
    private Statement stmt = null;
    
    @Override
    public int save(User user) {
        String sql = "insert into USERS values(?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conToUse = null;
        PreparedStatement ps = null;
        int status = 0;
        try {
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getStreet());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getZipcode());
            ps.setString(10, user.getPhoneNumber());
            ps.setString(11, user.getType());
            ps.setString(12, user.getCompany());
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }

    @Override
    public User getUser(String username, String password) {
        String sql = "select U.username, U.password, U.firstname, U.lastname, U.street, U.city, U.state, U.zipcode, U.phoneNumber, U.email, U.type, U.company"
                        + "   from USERS U "
                        + "   where U.username = '" + username + "'"
                        + "   and U.password = '" + password + "'";
        
        Connection conToUse = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            conToUse = getConnection();
            stmt = (Statement) conToUse.createStatement();
            res = stmt.executeQuery(sql);
            User user = new User();
            int count = 0;
            while (res.next()) {
              String user_name = res.getString("username");
              String pass_word = res.getString("password");  
              String firstname = res.getString("firstname");
              String lastname = res.getString("lastname");
              String street = res.getString("street"); 
              String city = res.getString("city"); 
              String state = res.getString("state"); 
              String zipcode = res.getString("zipcode"); 
              String phoneNumber = res.getString("phoneNumber"); 
              String email = res.getString("email");
              String type = res.getString("type");
              String company = res.getString("company");
              user.setUsername(user_name);
              user.setPassword(pass_word);
              user.setFirstName(firstname);
              user.setLastName(lastname);
              user.setStreet(street);
              user.setCity(city);
              user.setState(state);
              user.setZipcode(zipcode);
              user.setPhoneNumber(phoneNumber);
              user.setEmail(email);
              user.setType(type);
              user.setCompany(company);
              count++;
            }
            if (count != 1) {
                return null;
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        
        return null;
    }
    
    /**
     *
     * @param userName
     * @return
     * @throws ObjectException
     */
    @Override
    public List<Book> getWishlist(User user) {
        List<Book> booksOnWishlist = new ArrayList<Book>();        
        Connection conToUse = null;
        java.sql.PreparedStatement ps = null;
        ResultSet res = null;
        ResultSet res2 = null;
        try {
            String username = user.getUsername();
            conToUse = getConnection();
            String sql = "select * from WISHLISTS where USERNAME = ?";
            ps = (PreparedStatement) conToUse.prepareStatement(sql);
            ps.setString(1, username);
            res = ps.executeQuery();
            
            sql = "select copiesLeft from books where isbn = ?";
             ps = (PreparedStatement) conToUse.prepareStatement(sql);
            while (res.next()) {
                Book book = new Book();
                String isbn = res.getString("isbn");
                book.setIsbn(isbn);
                book.setTitle(res.getString("title"));
                book.setImageUrl(res.getString("imageUrl"));
                
                ps.setString(1, isbn);
                res2 = ps.executeQuery();
                while(res2.next()) {
                    book.setCopiesLeft(res2.getInt("copiesLeft"));
                }
                booksOnWishlist.add(book);
            }
            
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        
        return booksOnWishlist;
    }
    
    @Override
    public int addToWishlist(User user, String isbn) {
        String title, imageUrl;
        title = imageUrl = null;
        Connection conToUse = null;
        PreparedStatement preparedStmt = null;
        String sql;
        ResultSet res = null;
        int status = 0;
        try {
            String username = user.getUsername();
            conToUse = getConnection();
            if (conToUse == null)
                System.out.println("conToUse == null");
            
            sql = "select title, imageUrl from books where isbn = ?";
            preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
            preparedStmt.setString(1, isbn);
            res = preparedStmt.executeQuery();
            while (res.next()) {
                title = res.getString("title");
                imageUrl = res.getString("imageUrl");
            }
            
            sql = "insert into wishlists(username, isbn, title, imageUrl) values(?,?,?,?)";
            preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, isbn);
            preparedStmt.setString(3, title);
            preparedStmt.setString(4, imageUrl);
            status = preparedStmt.executeUpdate();
            println("Added book : dao");
        } catch (MySQLIntegrityConstraintViolationException ex) {
            println("Entry already exists");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(preparedStmt);
        }
        
        return status;
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
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DbUtils.closeQuietly(preparedStmt);
        }
        
        return status;
    }

    @Override
    public List<Book> getPublisherItems(User publisher) {
        List<Book> publisherItems = new ArrayList<Book>();        
        Connection conToUse = null;
        java.sql.PreparedStatement ps = null;
        ResultSet res = null;
        try {
            String company = publisher.getCompany();
            conToUse = getConnection();
            String sql = "select * from BOOKS where publisher = ? and title is not null";
            
            ps = (PreparedStatement) conToUse.prepareStatement(sql);
            ps.setString(1, company);
            res = ps.executeQuery();
            while (res.next()) {
                Book book = new Book();
                book.setIsbn(res.getString("isbn"));
                book.setTitle(res.getString("title"));
                book.setImageUrl(res.getString("imageUrl"));
                publisherItems.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        
        return publisherItems;
    }

    @Override
    public List<Book> getCheckedOut(User user) {
          List<Book> checkedOut = new ArrayList<Book>();        
        Connection conToUse = null;
        java.sql.PreparedStatement ps = null;
        ResultSet res = null;
        try {
            String username = user.getUsername();
            conToUse = getConnection();
            String sql = "select * from checked_out where username = ?";
            
            ps = (PreparedStatement) conToUse.prepareStatement(sql);
            ps.setString(1, username);
            res = ps.executeQuery();
            while (res.next()) {
                Book book = new Book();
                book.setIsbn(res.getString("isbn"));
                book.setTitle(res.getString("title"));
                book.setImageUrl(res.getString("imageUrl"));
                checkedOut.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(ps);
        }
        
        return checkedOut;
    }
    
    @Override
    public int update(User user) {
        String sql = "update USERS U" + " SET U.firstname = '" + user.getFirstName() + "'" + ", " 
                + "U.lastname = '" + user.getLastName() + "'" + ", "
                + "U.email = '" + user.getEmail() + "'" + ", " 
                + "U.street = '" + user.getStreet() + "'" + ", " 
                + "U.city = '" + user.getCity() + "'" + ", "
                + "U.state = '" + user.getState() + "'" + ", "
                + "U.zipcode = '" + user.getZipcode() + "'" + ", "
                + "U.phoneNumber = '" + user.getPhoneNumber() + "'"
                 + "   where U.username = '" + user.getUsername() + "'"
                 + "   and U.password = '" + user.getPassword() + "'";
        Connection conToUse = null;
        PreparedStatement ps = null;
        int status = 0;
        try {
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbUtils.closeQuietly(ps);
        }
        return status;
    }
    
    
    
}
