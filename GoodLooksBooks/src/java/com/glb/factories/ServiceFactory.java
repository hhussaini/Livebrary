package com.glb.factories;

import com.glb.services.BookService;
import com.glb.services.BookServiceImpl;
import com.glb.services.UserServiceImpl;
import com.glb.services.UserService;

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