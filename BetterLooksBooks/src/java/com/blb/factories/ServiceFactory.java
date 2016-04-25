package com.blb.factories;

import com.blb.services.UserServiceImpl;
import com.blb.services.UserService;

public class ServiceFactory {
    private static UserService userService = new UserServiceImpl();

    public static UserService getUserService() {
        return userService;
    }
}