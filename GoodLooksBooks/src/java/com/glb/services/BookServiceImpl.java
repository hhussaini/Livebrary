package com.glb.services;

import com.glb.daos.BookDao;
import com.glb.factories.DaoFactory;
import com.glb.exceptions.ResourceHelperException;
import java.sql.Connection;
import com.glb.daos.ConnectionUtil; 
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.glb.objects.Book;
import com.glb.objects.Review; 
import com.glb.objects.Ticket;
import com.glb.objects.User;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.Map;
import org.apache.commons.dbutils.DbUtils;

public class BookServiceImpl implements BookService {
    
    private int numberOfResults;
    private int totalBooks;
    private ResultSet res = null;
    
    @Override
    public List<Book> searchBooks(String term, String[] categories, int offset, int recordsPerPage) {
        List<Book> results = null;
        Connection conn = null;       
       
        try {            
            conn = ConnectionUtil.getConnection();
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conn);
            results = bookDao.searchBooks(term, categories, offset, recordsPerPage);
            this.numberOfResults = bookDao.getNumberOfResults();
        } catch (ResourceHelperException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
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

      @Override
    public int addBookToUserItems(String username, String isbn) {
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try { 
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conToUse);
            status = bookDao.addBookToUserItems(username, isbn);
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
        
        System.out.println("Status from BookServiceImpl.addBookToUserItems() = " + status);
        return status;
    }

    @Override
     public Map<String, Review> getAllReviewsForBook(String isbn){
        Connection conn = null;
        Map<String, Review>reviewsMap = null;
        try {            
            conn = ConnectionUtil.getConnection();
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conn);
            reviewsMap = bookDao.getAllReviewsForBook(isbn);
        } catch (ResourceHelperException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reviewsMap;
    }

    @Override
    public List<Book> getItemsList(String username) {
        Connection conn = null;
       List<Book>listOfBooks = null;
        try {            
            conn = ConnectionUtil.getConnection();
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conn);
            listOfBooks = bookDao.getItemsList(username);
        } catch (ResourceHelperException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listOfBooks;
    }

    @Override
    public int addReview(Review review, String isbn, String username){
        Connection conn = null;
        int status = 0;
        try {            
            conn = ConnectionUtil.getConnection();
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conn);
            status = bookDao.addReview(review, isbn, username);
        } catch (ResourceHelperException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
    }

    @Override
    public int submitEditRequest(String oldIsbn, String newIsbn, String title, String author, String description) {
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conToUse);;
            status = bookDao.submitEditRequest(oldIsbn, newIsbn, title, author, description);
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
    
        return status;
    }

    @Override
    public List<Ticket> getTickets(String resolved) {
        Connection conToUse = null;
        List<Ticket> tickets = null;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conToUse);;
            tickets = bookDao.getTickets(resolved);
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
    
        return tickets;
    }

    @Override
    public int acceptTicket(int ticketId) {
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conToUse);;
            status = bookDao.acceptTicket(ticketId);
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
    
        return status;
    }
    
    @Override
    public int resolveTicket(int ticketId, String accepted) {
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conToUse);;
            status = bookDao.resolveTicket(ticketId, accepted);
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
    
        return status;
    }
    
    @Override
    public int submitAddRequest(String isbn, String isbn10, String title, String author, String description, String binding, 
            String imageUrl, int pages, String language, double listPrice, String currency, String publisher, String category) {
        Connection conToUse = null;
        int status = 0;
        // get the connection from util class
        // set the transaction to con & pass con to dao
        try {
            conToUse = ConnectionUtil.getConnection();
            conToUse.setAutoCommit(false);
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conToUse);;
            status = bookDao.submitAddRequest(isbn, isbn10, title, author,description, 
                    binding, imageUrl, pages, language, listPrice, currency, publisher, category);
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
    
        return status;
    }

    @Override
    public int deleteReview(String isbn, String username) {
        Connection conn = null;
        int status = 0;
        try {            
            conn = ConnectionUtil.getConnection();
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conn);
            status = bookDao.deleteReview(isbn, username);
        } catch (ResourceHelperException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    @Override
    public Book editReview(Review review, String isbn, String username) {
        Connection conn = null;
        int status = 0;
        Book book = null;
        try {            
            conn = ConnectionUtil.getConnection();
            BookDao bookDao = DaoFactory.getBookDao();
            bookDao.setConnection(conn); 
            book = bookDao.editReview(review, isbn, username);
        } catch (ResourceHelperException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return book;
    }
}
