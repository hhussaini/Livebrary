package factories;

import services.BookService;
import services.BookServiceImpl;
import services.UserServiceImpl;
import services.UserService;

public class ServiceFactory {
    private static UserService userService = new UserServiceImpl();
    private static BookService bookService = new BookServiceImpl();

    public static UserService getUserService() {
        return userService;
    }
    
    public static BookService getBookService() {
        return bookService;
    }
}