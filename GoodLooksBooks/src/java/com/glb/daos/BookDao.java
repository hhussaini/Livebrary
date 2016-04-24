package com.glb.daos;

import java.util.List;
import com.glb.objects.Book;

public interface BookDao extends JdbcDaoSupport {
    public List<Book> getWishlist(String userName);
    public Book addToWishlist(String username, String isbn);
    public int removeFromWishlist(String username, String bookName);
    public List<Book> searchBooks(String term, int offset, int recordsPerPage);
    public int getNumberOfResults();
    public List<Book> getAllBooks();
    public int getTotalBooks();
}