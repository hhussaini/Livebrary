package com.glb.services;

import com.glb.objects.Book;
import com.glb.objects.User;
import java.util.List;

public interface UserService {
    public int save(User user);
    public User getUser(String username, String password);
    public User getUser(String username);
    public List<User> getAllUsers();
    public List<Book> getWishlist(User user);
    public List<Book> getPublisherItems(User publisher);
    public int removeFromWishlist(User user, String isbn);
    public int addToWishlist(User user, String isbn);
    public List<Book> getCheckedOutItems(User user);
    public List<Book> getOnHoldItems(User user);
    public int putOnHold(String username, String isbn, String email, String autoCheckout);
    public int update(User user);
    public int deleteUser(String username);
    public int updateItemSettings(String username, int eBookLendPeriod, int audioBookLendPeriod,
            int videoLendPeriod, String maturityStart, String maturityEnd);
    public List<Book> getRatedItems(User user);
    
    public List<Book> getInstockWishlist(List<Book> fullWishlist);
    public List<Book> getInstockWishlist(User user);
}
