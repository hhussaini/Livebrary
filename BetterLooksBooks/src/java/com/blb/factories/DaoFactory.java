package com.blb.factories;

import com.blb.daos.UserDao;
import com.blb.daos.UserDaoImpl;

public class DaoFactory {
    private static UserDao userDao = new UserDaoImpl();

    public static UserDao getUserDao() {
        return userDao;
    }
}
