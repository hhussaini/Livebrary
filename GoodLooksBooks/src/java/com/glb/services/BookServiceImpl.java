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

public class BookServiceImpl implements BookService {
    
    private int numberOfResults;
    private int totalBooks;
    
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