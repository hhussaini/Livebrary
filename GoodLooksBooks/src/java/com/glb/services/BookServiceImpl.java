package com.glb.services;

import com.glb.daos.BookDao;
import com.glb.factories.DaoFactory;
import com.glb.exceptions.ResourceHelperException;
import java.sql.Connection;
import java.sql.SQLException;
import com.glb.daos.ConnectionUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.glb.objects.Book;
import org.apache.commons.dbutils.DbUtils;

public class BookServiceImpl implements BookService {
    
    private int numberOfResults;
    private int totalBooks;
    
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
    public int removeFromWishlist(String username, String isbn) {
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conToUse);
            status = bookDao.removeFromWishlist(username, isbn);
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
    
    @Override
    public List<Book> searchBooks(String term, int offset, int recordsPerPage) {
        List<Book> results = null;
        Connection conn = null;       
       
        try {            
            conn = ConnectionUtil.getConnection();
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conn);
            results = bookDao.searchBooks(term, offset, recordsPerPage);
            this.numberOfResults = bookDao.getNumberOfResults();
        } catch (ResourceHelperException ex) {
            Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
    
    @Override
    public List<Book> getAllBooks() {
        List<Book> results = null;
        Connection conn = null;       
       
        try {            
            conn = ConnectionUtil.getConnection();
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conn);
            results = bookDao.getAllBooks();
            this.totalBooks = bookDao.getTotalBooks();
        } catch (ResourceHelperException ex) {
            Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
    
    @Override
    public Book getBookByIsbn(String isbn) {
        Connection conn = null;
        Book book = null;
        try {            
            conn = ConnectionUtil.getConnection();
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conn);
            book = bookDao.getBookByIsbn(isbn);
        } catch (ResourceHelperException ex) {
            Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return book;
    }
    
    @Override
    public int getNumberOfResults() {
        return this.numberOfResults;
    }
    
    public int getTotalBooks() {
        return totalBooks;
    }
}