package com.glb.daos;

import java.util.List;
import com.glb.objects.Book;

public interface BookDao extends JdbcDaoSupport {
    
    public List<Book> searchBooks(String term, int offset, int recordsPerPage);
    public int getNumberOfResults();
    public List<Book> getAllBooks();
    public int getTotalBooks();
    public Book getBookByIsbn(String isbn);
}