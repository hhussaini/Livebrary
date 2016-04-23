package daos;

import java.util.List;
import objects.Book;

public interface BookDao extends JdbcDaoSupport {
    public List<Book> getWishlist(String userName);
    public Book addToWishlist(String username, String isbn);
    public int removeFromWishlist(String username, String bookName);
}