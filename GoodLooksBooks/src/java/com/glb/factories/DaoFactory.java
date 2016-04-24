package com.glb.factories;

import com.glb.daos.BookDao;
import com.glb.daos.BookDaoImpl;
import com.glb.daos.UserDao;
import com.glb.daos.UserDaoImpl;

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
