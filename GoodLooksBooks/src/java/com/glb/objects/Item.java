package com.glb.objects;

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.glb.helpers.Helpers.round;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Kevin_Setayesh
 */
public class Item implements Serializable, Comparable{
    private String isbn;
    private String title;
    private String type;
    private String description;
    private double avgRating;
    private int numberOfRatings;
    private String author;
    private String date;
    private String language;
    private String imageUrl;
    private String sampleUrl;
    private String downloadUrl;
    private List<String> genres;
    private int numOfDownloads;
    private Map<String, Review> reviews;
    private boolean isBanned;
    private int copiesLeft;
    private Map<String, Recommendation> recommendations; 
    
    public Item(){
        this.reviews = new LinkedHashMap<>();
        this.genres = new ArrayList<>(); 
        this.recommendations = new HashMap<>();
    }

    public Item(String isbn, String title, String description, double avgRating, String downloadUrl, 
            String author, String date, String language, String imageUrl, int numOfDownloads) {
        this.reviews = new LinkedHashMap<>();
        this.genres = new ArrayList<>(); 
        this.recommendations = new HashMap<>();
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.avgRating = avgRating;
        this.downloadUrl = downloadUrl;
        this.author = author;
        this.date = date;
        this.language = language;
        this.imageUrl = imageUrl;
        this.genres = new ArrayList<>();
        this.numOfDownloads = numOfDownloads;
       // this.isBanned = isBanned;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getNumOfDownloads() {
        return numOfDownloads;
    }

    public void setNumOfDownloads(String numOfDownloads) {
        this.numOfDownloads = Integer.parseInt(numOfDownloads);
    }

    public Map<String, Review> getReviews() {
        return reviews;
    }
    
   public String getSampleUrl() {
     return sampleUrl;
   }

   public void setSampleUrl(String sampleUrl) {
      this.sampleUrl = sampleUrl;
   }

    public void setReviews(Map<String, Review> reviews) {
        this.numberOfRatings = 0;
        int total = 0;
        for(String username : reviews.keySet()){
            Review review = reviews.get(username);
            total = total + review.getRating();
            this.numberOfRatings++;
        }
        
        this.avgRating = (this.numberOfRatings > 0) ? (round((double)total / this.numberOfRatings)) : 0;
        this.reviews = reviews;
    }
    
    public String getType() {
      return type;
    }

   public void setType(String type) {
      this.type = type;
   }
    
    public void updateOrderOfReviews(String username){
        List<String>usernameList = new ArrayList<>();
        List<Review>reviewsList = new ArrayList<>();
        int counter = 0;
        int index = -1;
        for(String key : reviews.keySet()){
            usernameList.add(key);
            reviewsList.add(reviews.get(key));
            if(key.equals(username)){
                index = counter; 
            }  
            counter++;
        }
        Map<String,Review>reviewsTemp = new LinkedHashMap<>();
        if(index > -1){
            reviewsTemp.put(usernameList.get(index), reviewsList.get(index));
        }
    
        for(int i = 0; i<reviewsList.size(); i++){
            if(i!=index){
        	reviewsTemp.put(usernameList.get(i), reviewsList.get(i));
            }
        }
        this.reviews = reviewsTemp;
         
    }
    
    public boolean addReview(User user, Review review){
        if(this.reviews.get(user.getUsername()) == null){
            this.reviews.put(user.getUsername(), review);
            if(review.getRating()>-1){
                this.updateAvgRating(review.getRating());
            }
            return true;
        } 
        return false;
    }
    
    public void updateAvgRating(int rating){
        double total = ((this.avgRating * this.numberOfRatings) + (rating));
        this.numberOfRatings++; 
        this.avgRating = round(total / this.numberOfRatings); 
    }
     
    public void editReview(User user, Review review){
        this.reviews.remove(user.getEmail());
        this.reviews.put(user.getEmail(), review);
    }  
    
    @Override
    public String toString(){
        return "Title: " + this.title + 
                "\nAuthor: " + this.author + 
                "\nImageUrl: " + this.imageUrl + "\n";
                
    }
    
    public boolean getIsBanned(){
        return isBanned;
    }
    
    public void setIsBanned(boolean isBanned){
        this.isBanned = isBanned;
    }
    
    public String getDownloadUrl(){
        return downloadUrl;
    }
    
    public void setDownloadUrl(String downloadUrl){
        this.downloadUrl = downloadUrl;
    }
    
    public int getCopiesLeft() {
        return this.copiesLeft;
    }
    
    public void setCopiesLeft(int left) {
        this.copiesLeft = left;
    }

    public Map<String, Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Map<String, Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
    
    public void addRecomendation(String key, Recommendation rec){
        this.recommendations.put(key, rec);
    }
    
     
    @Override
    public int compareTo(Object o) {
       Book book = (Book)o;
       switch(ItemSorting.sort){
           case 1 : return this.getTitle().compareTo(book.getTitle());
           case 2 : return this.getAuthor().compareTo(book.getAuthor()); 
           case 5 : if(this.getCopiesLeft() > book.getCopiesLeft()){
                        return 1;
                    }
                    else if(this.getCopiesLeft() < book.getCopiesLeft()){
                        return -1;
                    }
                    return 0;      
       }
       return -1;
    }
    
    public static class ItemSorting{
        public static int sort;
    }
     
    
} 
 