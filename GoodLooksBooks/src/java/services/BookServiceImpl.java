package services;

import daos.BookDao;
import factories.DaoFactory;
import exceptions.ResourceHelperException;
import java.sql.Connection;
import java.sql.SQLException;
import daos.ConnectionUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Book;
import org.apache.commons.dbutils.DbUtils;

public class BookServiceImpl implements BookService {
    
    @Override
    public List<Book> getWishlist(String userName) {
        Connection conToUse = null;
        List<Book> booksOnWishlist = null;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conToUse);
            booksOnWishlist = bookDao.getWishlist(userName);
        } catch (ResourceHelperException e) {
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            System.out.println("SQLException");
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
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
    public int removeFromWishlist(String username, String bookName) {
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conToUse);
            status = bookDao.removeFromWishlist(username, bookName);
            conToUse.commit();
        } catch (ResourceHelperException e) {
            System.out.println("ResourceHelperException");
            DbUtils.rollbackAndCloseQuietly(conToUse);
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            System.out.println("SQLException");
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conToUse);
        }
        
        System.out.println("Status from BookServiceImpl.removeFromWishlist() = " + status);
        return status;
    }
}