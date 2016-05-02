package com.glb.daos;

import com.glb.objects.Book;
import com.glb.objects.User;
import java.util.List;

public interface UserDao extends JdbcDaoSupport {   
    public int save(User user);
    public User getUser(String username, String password);
    public List<Book> getWishlist(User user);
    public List<Book> getPublisherItems(User publisher);
    public int addToWishlist(User user, String isbn);
    public int removeFromWishlist(String username, String bookName);
}