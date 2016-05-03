package com.glb.services;

import com.glb.objects.Book;
import com.glb.objects.User;
import java.util.List;

public interface UserService {
    public int save(User user);
    public User getUser(String username, String password);
    public List<Book> getWishlist(User user);
    public List<Book> getPublisherItems(User publisher);
    public int removeFromWishlist(User user, String isbn);
    public int addToWishlist(User user, String isbn);
    public List<Book> getCheckedOutItems(User user);
    public List<Book> getOnHoldItems(User user);
    public int update(User user);
}
