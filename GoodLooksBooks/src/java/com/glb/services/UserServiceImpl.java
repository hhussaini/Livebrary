package com.glb.services;

import com.glb.factories.DaoFactory;
import com.glb.daos.UserDao;
import com.glb.exceptions.ResourceHelperException;
import java.sql.Connection;
import java.sql.SQLException;
import com.glb.objects.User;
import com.glb.daos.ConnectionUtil;
import static com.glb.helpers.Helpers.*;
import com.glb.objects.Book;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;
import com.glb.services.UserService;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    
    @Override
    public int save(User user) {
        System.out.println("Inside UserServiceImpl.save");
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            UserDao userDao = DaoFactory.getUserDao();
            userDao.setConnection(conToUse);
            status = userDao.save(user);
            conToUse.commit();
        } catch (ResourceHelperException e) {
            // TODO Auto-generated catch block
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            System.out.println("SQLException");
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        System.out.println("Status from UserServiceImppl.save() = " + status);
        return status;
    }
    
    public User getUser(String username, String password) {
        System.out.println("Inside UserServiceImpl.getUser");
        Connection conToUse = null;
        User user = null;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            UserDao userDao = DaoFactory.getUserDao();
            userDao.setConnection(conToUse);
            user = userDao.getUser(username, password);
        } catch (ResourceHelperException e) {
            // TODO Auto-generated catch block
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        return user;
    }
    
    @Override
    public List<Book> getWishlist(User user) {
        Connection conToUse = null;
        List<Book> booksOnWishlist = null;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            UserDao userDao = DaoFactory.getUserDao();
            userDao.setConnection(conToUse);
            booksOnWishlist = userDao.getWishlist(user);
        } catch (ResourceHelperException e) {
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            System.out.println("SQLException");
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        return booksOnWishlist;
    }
    
    /**
     *
     * @param username
     * @param bookName
     * @return
     */
    @Override
    public int removeFromWishlist(User user, String isbn) {
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            String username = user.getUsername();
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            UserDao userDao = DaoFactory.getUserDao();
            userDao.setConnection(conToUse);
            status = userDao.removeFromWishlist(username, isbn);
            conToUse.commit();
        } catch (ResourceHelperException e) {
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            System.out.println("SQLException");
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        System.out.println("Status from BookServiceImpl.removeFromWishlist() = " + status);
        return status;
    }
    
    @Override
    public int addToWishlist(User user, String isbn) {
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            UserDao userDao = DaoFactory.getUserDao();
            userDao.setConnection(conToUse);
            status = userDao.addToWishlist(user, isbn);
            conToUse.commit();
            println("Added book : service");
        } catch (ResourceHelperException e) {
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            System.out.println("SQLException");
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        System.out.println("Status from BookServiceImpl.removeFromWishlist() = " + status);
        return status;
    }
    
    @Override
    public List<Book> getPublisherItems(User publisher) {
        Connection conToUse = null;
        List<Book> publisherItems = null;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            UserDao userDao = DaoFactory.getUserDao();
            userDao.setConnection(conToUse);
            publisherItems = userDao.getPublisherItems(publisher);
        } catch (ResourceHelperException e) {
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            System.out.println("SQLException");
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        return publisherItems;
    }
    
    
    @Override
    public List<Book> getCheckedOutItems(User user) {
        List<Book> checkedOut = new ArrayList<Book>();
        Connection conToUse = null;
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            UserDao userDao = DaoFactory.getUserDao();
            userDao.setConnection(conToUse);
            checkedOut = userDao.getCheckedOut(user);
        } catch (ResourceHelperException e) {
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            System.out.println("SQLException");
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        return checkedOut;
    }
    
    
    @Override
    public int update(User user) {
        System.out.println("Inside UserServiceImpl.update");
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            UserDao userDao = DaoFactory.getUserDao();
            userDao.setConnection(conToUse);
            status = userDao.update(user);
            conToUse.commit();
        } catch (ResourceHelperException e) {
            // TODO Auto-generated catch block
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            System.out.println("SQLException");
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        System.out.println("Status from UserServiceImppl.update() = " + status);
        return status;
    }

    @Override
    public List<Book> getOnHoldItems(User user) {
        List<Book> onHold = new ArrayList<Book>();
        Connection conToUse = null;
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            UserDao userDao = DaoFactory.getUserDao();
            userDao.setConnection(conToUse);
            onHold = userDao.getOnHoldItems(user);
        } catch (ResourceHelperException e) {
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            System.out.println("SQLException");
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        return onHold;
    }

   @Override
   public int deleteUser(String username) {
      System.out.println("Inside UserServiceImpl.deleteUser");
      Connection conToUse = null;
      int status = 0;
      // get the connection from util class
      // set the transaction to con & pass con to dao
      try {
          conToUse = ConnectionUtil.getConnection();
          conToUse.setAutoCommit(false);
          UserDao userDao = DaoFactory.getUserDao();
          userDao.setConnection(conToUse);
          status = userDao.deleteUser(username);
          conToUse.commit();
      } catch (ResourceHelperException e) {
          // TODO Auto-generated catch block
          System.out.println("ResourceHelperException");
          DbUtils.rollbackAndCloseQuietly(conToUse);
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
      } catch (SQLException ex) {
          System.out.println("SQLException");
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
          DbUtils.closeQuietly(conToUse);
      }

      System.out.println("Status from UserServiceImpl.deleteUser() = " + status);
      return status;
   }

   @Override
   public List<User> getAllUsers() {
      System.out.println(this.getClass().getName() + " : getAllUsers");
        Connection conToUse = null;
        List<User> users = null;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            UserDao userDao = DaoFactory.getUserDao();
            userDao.setConnection(conToUse);
            users = userDao.getAllUsers();
        } catch (ResourceHelperException e) {
            // TODO Auto-generated catch block
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        return users;
   }

   @Override
   public User getUser(String username) {
      Connection conToUse = null;
      User user = null;
      // get the connection from util class
      // set the transaction to con & pass con to dao
      try {
          conToUse = ConnectionUtil.getConnection();
          UserDao userDao = DaoFactory.getUserDao();
          userDao.setConnection(conToUse);
          user = userDao.getUser(username);
      } catch (ResourceHelperException e) {
          // TODO Auto-generated catch block
          System.out.println("ResourceHelperException");
          DbUtils.rollbackAndCloseQuietly(conToUse);
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
      } finally {
          DbUtils.closeQuietly(conToUse);
      }

      return user;
   }

   @Override
   public int putOnHold(String username, String isbn, String email, String autoCheckout) {
      Connection conToUse = null;
      int status = 0;
      // get the connection from util class
      // set the transaction to con & pass con to dao
      try {
          conToUse = ConnectionUtil.getConnection();
          UserDao userDao = DaoFactory.getUserDao();
          userDao.setConnection(conToUse);
          status = userDao.putOnHold(username, isbn, email, autoCheckout);
      } catch (ResourceHelperException e) {
          // TODO Auto-generated catch block
          System.out.println("ResourceHelperException");
          DbUtils.rollbackAndCloseQuietly(conToUse);
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
      } finally {
          DbUtils.closeQuietly(conToUse);
      }

      return status;
   }
   
   @Override
   public int updateItemSettings(String username, int eBookLendPeriod, int audioBookLendPeriod,
             int videoLendPeriod, String maturityStart, String maturityEnd) {
      Connection conToUse = null;
      int status = 0;
      // get the connection from util class
      // set the transaction to con & pass con to dao
      try {
          conToUse = ConnectionUtil.getConnection();
          UserDao userDao = DaoFactory.getUserDao();
          userDao.setConnection(conToUse);
          status = userDao.updateItemSettings(username, eBookLendPeriod, 
                  audioBookLendPeriod, videoLendPeriod, maturityStart, maturityEnd);
      } catch (ResourceHelperException e) {
          // TODO Auto-generated catch block
          System.out.println("ResourceHelperException");
          DbUtils.rollbackAndCloseQuietly(conToUse);
          Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
      } finally {
          DbUtils.closeQuietly(conToUse);
      }

      return status;
   }
}
