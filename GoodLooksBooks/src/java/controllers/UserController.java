package controllers;

import objects.User;

/**
 * Singleton
 * @author Kevin Young
 */
public class UserController {
    
    private static UserController instance = null;

    protected UserController() {
       // Exists only to defeat instantiation.
    }

    public static UserController getInstance() {
       if(instance == null) {
          instance = new UserController();
       }
       return instance;
    }
   
    // TODO: Add db implementation
    public boolean verifyUser(String username, String password) {
         switch (username) {
                 case "admin":
                 case "customer":
                 case "librarian":
                 case "publisher": return true;
         }
         return false;
    }
    
    // TODO: Add db implementation to grab user from the db
    // This user is just a test
    public User getUser(String username, String password) {
        String userType = username;
        return new User("chestbrah", "password", "kevin", "young", "Stony Rd", "Stony Brook", "NY", 
                "11790", "63199999999", "kevin@kevin.com", userType, "");
    }
}