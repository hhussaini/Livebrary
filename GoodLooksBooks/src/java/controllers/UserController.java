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
   
    public boolean verifyUser(String username, String password) {
        if (username.equals("customer") && password.equals("password")) {
            return true;
        }
        return false;
    }
    
    public User getUser(String username, String password) {
        // TODO. Add db implementation to grab user from the db
        // This user is just a test
        return new User("kevinCustomer", "password", "kevin", "young", "Stony Rd", "Stony Brook", "NY", 
                "11790", "63199999999", "kevin@kevin.com", "customer", "");
    }
}
