/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glb.objects;

/**
 *
 * @author Kevin_Setayesh
 */
public class Review {
    private int rating;
    private String reviewText;
    
    public Review(){
        //this.rating = -1; // means it hasnt been rated yet
    }
    
    public Review(int rating, String reviewText){
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    
    @Override
    public String toString(){
        return this.rating + "";
    }
    
    
}
