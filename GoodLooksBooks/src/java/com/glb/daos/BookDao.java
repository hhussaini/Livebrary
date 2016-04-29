package com.glb.daos;

import java.util.List;
import com.glb.objects.Book;
import com.glb.objects.Review;
import com.glb.objects.User;
import java.util.Map;

public interface BookDao extends JdbcDaoSupport {    
    public List<Book> searchBooks(String term, String[] categories, int offset, int recordsPerPage);
    public int getNumberOfResults();
    public List<Book> getAllBooks();
    public int getTotalBooks();
    public Book getBookByIsbn(String isbn);
    public int addBookToUserItems(String username, String isbn);
    public List<Book> getItemsList(String userName);
    public Map<String, Review> getAllReviewsForBook(String isbn);
    public Book addReview(Review review, Book book, User user);
    
 
}