package factories;

import daos.BookDao;
import daos.BookDaoImpl;
import daos.UserDao;
import daos.UserDaoImpl;

public class DaoFactory {
    private static UserDao userDao = new UserDaoImpl();
    private static BookDao bookDao = new BookDaoImpl();

    public static UserDao getUserDao() {
        return userDao;
    }
    
    public static BookDao getBookDao() {
        return bookDao;
    }
}
