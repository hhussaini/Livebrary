package com.glb.services;

import java.util.List;
import com.glb.objects.Book;
import com.glb.objects.Review;
import com.glb.objects.Ticket;
import com.glb.objects.User;
import java.util.Map;

public interface BookService {
    public List<Book> searchBooks(String parameter, String[] categories, int offset, int recordsPerPage);
    public int getNumberOfResults();
    public List<Book> getAllBooks();
    public List<Ticket> getTickets(String resolved);
    public int acceptTicket(int ticketId);
    public int resolveTicket(int ticketId, String accepted);
    public Book getBookByIsbn(String isbn);
    public int addBookToUserItems(String username, String isbn);
    public int submitEditRequest(String oldIsbn, String newIsbn, String title, 
            String author, String description);
    public int submitAddRequest(String isbn, String isbn10, String title, String author, String description, 
            String binding, String imageUrl, int pages, String language, double listPrice, String currency, String publisher);
    public List<Book> getItemsList(String username);
    public Map<String, Review> getAllReviewsForBook(String isbn);
    public int addReview(Review review, Book book, User user);
    public int deleteReview(String isbn, String username);
    // public int updateBook(String oldIsbn, String newIsbn, String title, String author, String description);
}  
