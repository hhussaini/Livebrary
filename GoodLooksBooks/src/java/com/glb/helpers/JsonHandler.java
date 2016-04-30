/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glb.helpers;

import com.glb.objects.Book;
import com.glb.objects.Review; 
import com.glb.objects.User;
import java.util.List;
import java.util.Map;   
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kevin_Setayesh
 */
public class JsonHandler {
    
    protected JsonHandler(){}
    
    public static JSONObject createJSONObj(Review review) throws JSONException{
        JSONObject jso = new JSONObject();
        jso.put("rating", review.getRating());
        jso.put("reviewText", review.getReviewText());
        return jso;
    }
  
    
    public static JSONObject createJSONObj(User user, Book book) throws JSONException{
        JSONObject jso = new JSONObject();
        jso.put("currentUser", user.getUsername());
        jso.put("isbn", book.getIsbn());
        jso.put("title", book.getTitle());
        jso.put("description", book.getDescription());
        jso.put("avgRating", book.getAvgRating());
        jso.put("numberOfRatings", book.getNumberOfRatings());
        jso.put("downloadLink", book.getDownloadLink());
        jso.put("author", book.getAuthor());
        jso.put("date", book.getDate());
        jso.put("language", book.getLanguage());
        jso.put("imageUrl", book.getImageUrl());
        jso.put("reviews", createJSONArray(book.getReviews()));
        jso.put("genres", createJSONArray(book.getGenres()));
        return jso;
    }
    
    public static JSONArray createJSONArray(Map<String, Review> reviews) throws JSONException{
        JSONArray jsonArray = new JSONArray();
        for (String key : reviews.keySet()) {
            Review review = reviews.get(key); 
            JSONObject jso = new JSONObject();
            jso.put("rating", review.getRating());
            jso.put("reviewText", review.getReviewText());
            jso.put("username", key);
            jsonArray.put(jso);
        }
        return jsonArray;
    }
    
      
    // Have to Edit this later
    public static JSONArray createJSONArray(List<String>genres){
        JSONArray jsonArray = new JSONArray();
        for(String genre : genres){
            jsonArray.put(genre);
        }
        return jsonArray;
    }
    
}
