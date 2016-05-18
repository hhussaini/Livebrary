package com.glb.daos;

import com.glb.objects.Book;
import com.glb.objects.User;
import com.glb.objects.Wishlist;
import java.util.List;

public interface UserDao extends JdbcDaoSupport {   
    public int save(User user);
    public User getUser(String username, String password);
    public User getUser(String username);
    public List<User> getAllUsers();
    public Wishlist getWishlist(User user);
    public List<Book> getPublisherItems(User publisher);
    public int addToWishlist(User user, String isbn);
    public int removeFromWishlist(String username, String bookName);
    public List<Book> getCheckedOut(User user);
    public List<Book> getOnHoldItems(User user);
    public int update(User user);
    public int deleteUser(String username);
    public int putOnHold(String username, String isbn, String email, String automaticCheckout);
    public int updateItemSettings(String username, int eBookLendPeriod, int audioBookLendPeriod,
             int videoLendPeriod, String maturityStart, String maturityEnd);
    public List<Book> getRatedItems(User user);
}
