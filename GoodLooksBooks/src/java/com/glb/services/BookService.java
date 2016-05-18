package com.glb.services;

import java.util.List;
import com.glb.objects.Book;
import com.glb.objects.Item;
import com.glb.objects.Review;
import com.glb.objects.Ticket;
import com.glb.objects.User;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface BookService {
    public List<Book> searchBooks(HashMap<String,String> searchTermMap, ArrayList<String> categories, int offset, int recordsPerPage);
    public int getNumberOfResults();
    public List<Book> getAllBooks();
    public List<Ticket> getTickets(String resolved);
    public int acceptTicket(int ticketId);
    public int resolveTicket(int ticketId, String accepted);
    public Book getBookByIsbn(String isbn);
    public int addBookToUserItems(String username, String isbn);
    public int submitEditRequest(String oldIsbn, String newIsbn, String title, 
            String author, String description);
    public int submitAddRequest(String isbn, String isbn10, String title, String author, String description, String binding, 
            String imageUrl, int pages, String language, double listPrice, String currency, String publisher, String category);
    public int submitDeleteRequest(String isbn);
    public Map<String, Review> getAllReviewsForBook(String isbn);
    public int addReview(Review review, String isbn, String username);
    public int deleteReview(String isbn, String username);
    public Book editReview(Review review, String isbn, String username);
    public int banBook(String isbn);
    public String getItemAccess(User user, Item item);
    public void checkExpiredCheckouts();
    public void checkHolds();
    public int returnItem(String username, String isbn);
    public int editHoldEmail(String username, String email, String isbn);
    public int suspendHold(String username, String isbn, int days);
    public int cancelSuspension(String username, String isbn);
    public Timestamp getHoldSuspensionDate(String username, String isbn);
    public int editHoldAutoCheckout(String username, String autoCheckout, String isbn);
    public int removeHold(String username, String isbn);
    public int renewItem(String username, String isbn);
    public int recommendItem(String username, String isbn, String email, String checkOut_or_email);
    // public int updateBook(String oldIsbn, String newIsbn, String title, String author, String description);
}  
