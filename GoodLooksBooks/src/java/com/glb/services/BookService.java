package com.glb.services;

import java.util.List;
import com.glb.objects.Book;
import com.glb.objects.Review;
import com.glb.objects.User;
import java.util.Map;

public interface BookService {
    public List<Book> searchBooks(String parameter, String[] categories, int offset, int recordsPerPage);
    public int getNumberOfResults();
    public List<Book> getAllBooks();
    public Book getBookByIsbn(String isbn);
    public int addBookToUserItems(String username, String isbn);
    public int submitEditRequest(String isbn);
    public List<Book> getItemsList(String username);
    public Map<String, Review> getAllReviewsForBook(String isbn);
    public int addReview(Review review, Book book, User user);
}  