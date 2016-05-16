package com.glb.daos;

import com.glb.factories.ServiceFactory;
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
import com.glb.services.BookService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends JdbcDaoSupportImpl implements UserDao {
    
    BookService bookService;
    
    public void init() {
        bookService = ServiceFactory.getBookService();
    }
  
    @Override
    public int save(User user) {
        String sql = "insert into USERS (username, firstname, lastname, email, password, street, city, state, zipcode, phoneNumber, type, company)"
                + "  values(?,?,?,?,?,?,?,?,?,?,?,?)";
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
            ConnectionUtil.closeStatement(ps);
        }
        return status;
    }

    @Override
    public User getUser(String username, String password) {
        String sql = "select U.username, U.password, U.firstname, U.lastname, U.street, U.city, U.state, U.zipcode, U.phoneNumber, U.email, U.type, U.company,"
                + "U.eBookLendPeriod"
                        + "   from USERS U "
                        + "   where U.username = '" + username + "'"
                        + "   and U.password = '" + password + "'";
        Connection conToUse = null;
        ResultSet res = null;
        Statement stmt = null;
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
              int eBookLendPeriod = res.getInt("eBookLendPeriod");
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
              user.setEBookLendPeriod(eBookLendPeriod);
              count++;
            }
            if (count != 1) {
                return null;
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionUtil.closeStatement(stmt);
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
            ConnectionUtil.closeStatement(ps);
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
           ConnectionUtil.closeStatement(preparedStmt);
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
            ConnectionUtil.closeStatement(preparedStmt);
        }
        
        return status;
    }

    @Override
    public List<Book> getPublisherItems(User publisher) {
        List<Book> publisherItems = new ArrayList<Book>();        
        Connection conToUse = null;
        PreparedStatement ps = null;
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
            ConnectionUtil.closeStatement(ps);
       }
        
        return publisherItems;
    }

    @Override
    public List<Book> getCheckedOut(User user) {
        List<Book> checkedOut = new ArrayList<Book>();        
        Connection conToUse = null;
        BookService bookService = ServiceFactory.getBookService();
        java.sql.PreparedStatement ps = null;
        ResultSet res = null;
        try {
            String username = user.getUsername();
            conToUse = getConnection();
            String sql = "select * from checked_out where username = ? and expired = \'n\'";            
            ps = (PreparedStatement) conToUse.prepareStatement(sql);
            ps.setString(1, username);
            res = ps.executeQuery();
            while (res.next()) {
               Book book = new Book();
               book = bookService.getBookByIsbn(res.getString("isbn"));
               checkedOut.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionUtil.closeStatement(ps);
        }
        
        return checkedOut;
    }
    
    @Override
    public int update(User user) {
        String sql = "update USERS U SET U.firstname = ?,"
                + "U.lastname = ?,"
                + "U.email = ?," 
                + "U.street = ?," 
                + "U.city = ?,"
                + "U.state = ?,"
                + "U.zipcode = ?,"
                + "U.phoneNumber = ?,"
                + "U.company = ?,"
                + "U.type = ?"
                 + "   where U.username = ?"
                 + "   and U.password = ?";
        Connection conToUse = null;
        PreparedStatement ps = null;
        int status = 0;
        try {
            conToUse = getConnection();
            ps = conToUse.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getStreet());
            ps.setString(5, user.getCity());
            ps.setString(6, user.getState());
            ps.setString(7, user.getZipcode());
            ps.setString(8, user.getPhoneNumber());
            ps.setString(9, user.getCompany());
            ps.setString(10, user.getType());
            ps.setString(11, user.getUsername());
            ps.setString(12, user.getPassword());
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            ConnectionUtil.closeStatement(ps);
        }
        return status;
    }

    @Override
    public List<Book> getOnHoldItems(User user) {
        List<Book> onHold = new ArrayList<Book>();   
        BookService bookService = ServiceFactory.getBookService();
        Connection conToUse = null;
        java.sql.PreparedStatement ps = null;
        ResultSet res = null;
        try {
            conToUse = getConnection();
            String sql = "SELECT isbn from HOLDS WHERE username = ?";
            ps = (PreparedStatement) conToUse.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            res = ps.executeQuery();
            while (res.next()) { 
                  onHold.add(bookService.getBookByIsbn(res.getString("isbn"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionUtil.closeStatement(ps);
        }
        
        return onHold;
    }
    
   // TODO. Check for extra logic when our lending stuff is sorted out more
   @Override
   public int deleteUser(String username) {
      println("deleteUser.username: " + username);
      Connection conToUse = null;
      PreparedStatement preparedStmt = null;
      String sql = "delete from USERS where username = ?";
      int status = 0;
      try {
         //status = deleteHolds(username);
         status = returnAllCheckedOut(username);
         if (status == -1) {
            throw new SQLException();
         }
         status = deleteWishlist(username);
         if (status == -1) {
            throw new SQLException();
         }
         conToUse = getConnection();
         // Finally now that we've removed traces of the user in other tables we 
         // can delete from the users table
         preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
         preparedStmt.setString(1, username);
         status = preparedStmt.executeUpdate();
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
      } 
      finally {
          ConnectionUtil.closeStatement(preparedStmt);
      }

      return status;
   }
   
   /*
   TODO. Probably needs more logic. 
   This returns all the items of the given user
   */
   private int returnAllCheckedOut(String username) {
      Connection conToUse = null;
      PreparedStatement ps = null;
      String sql = "select * from CHECKED_OUT where username = ?";
      int status = 0;
      ResultSet rs = null;
      BookService bookService = ServiceFactory.getBookService();
      try {
         conToUse = getConnection();
         ps = (PreparedStatement) conToUse.prepareStatement(sql);
         ps.setString(1, username);
         rs = ps.executeQuery();
         while (rs.next()) {
            String isbn = rs.getString("isbn");
            bookService.returnItem(username, isbn);
         }
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
          status = -1;
      } finally {
          ConnectionUtil.closeStatement(ps);
      }

      return status;
   }

   private int deleteHolds(String username) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   
   /**
    * Deletes the wishlist of the user with the given username.
    * @param username
    * @return 
    */
   private int deleteWishlist(String username) {
      Connection conToUse = null;
      PreparedStatement preparedStmt = null;
      String sql = "delete from WISHLISTS where username = ?";
      int status = 0;
      try {
          conToUse = getConnection();
          preparedStmt = (PreparedStatement) conToUse.prepareStatement(sql);
          preparedStmt.setString(1, username);
          status = preparedStmt.executeUpdate();
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
          status = -1;
      }
      finally {
          ConnectionUtil.closeStatement(preparedStmt);
      }

      return status;
   }
   
   @Override
   public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();        
        Connection conToUse = null;
        java.sql.PreparedStatement ps = null;
        ResultSet res = null;
        try {
            conToUse = getConnection();
            String sql = "select * from USERS where type != \'admin\'";
            ps = (PreparedStatement) conToUse.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                User user = getUser(res.getString("username"), res.getString("password"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionUtil.closeStatement(ps);
        }
        
        return users;
    }

   @Override
   public User getUser(String username) {
      String sql = "select password from USERS where username = ?";
      Connection conToUse = null;
      ResultSet res = null;
      PreparedStatement ps = null;
      try {         
          conToUse = getConnection();
          ps = (PreparedStatement) conToUse.prepareStatement(sql);
          ps.setString(1, username);
          User user = new User();
          String password = "";
          res = ps.executeQuery();          
          while (res.next()) {
             password = res.getString("password");
          }
          user = getUser(username, password);
          return user;
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
          ConnectionUtil.closeStatement(ps);
      }

      return null;
   }

   @Override
   public int putOnHold(String username, String isbn, String email, String automaticCheckout) {
      Connection conToUse = null;
      PreparedStatement ps = null;
      String sql = "insert into HOLDS (username, isbn, email, autoCheckout) values (?,?,?,?)";
      int status = 0;
      try {
          conToUse = getConnection();
          ps = (PreparedStatement) conToUse.prepareStatement(sql);
          ps.setString(1, username);
          ps.setString(2, isbn);
          ps.setString(3, email);
          ps.setString(4, automaticCheckout);
          status = ps.executeUpdate();
      } catch (SQLException ex) {
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
          status = -1;
      }
      finally {
          ConnectionUtil.closeStatement(ps);
      }

      return status;
   }
}
