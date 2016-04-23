/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import constants.UserTypes;
import java.io.Serializable;

/**
 *
 * @author Kevin_Setayesh
 */
public class Review implements Serializable{
    private int rating;
    private String description;
    private User user;
    
    public Review(User user){
        if(user.getType().equalsIgnoreCase(UserTypes.CUSTOMER.toString())){
            this.user = user;
        }
    }
    
    public Review(int rating, String description, User user){
        this.rating = rating;
        this.description = description;
        if(user.getType().equalsIgnoreCase(UserTypes.CUSTOMER.toString())){
            this.user = user;
        }
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
