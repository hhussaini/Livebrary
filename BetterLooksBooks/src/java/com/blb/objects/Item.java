package com.blb.objects;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Kevin Young
 */
public class Item implements Serializable{
    private String isbn;
    private String title;
    private String description;
    private double avgRating;
    private int numberOfRatings;
    private String author;
    private String date;
    private String language;
    private String imageUrl;
    private String downloadUrl;
    private List<String> genres;
    private int numOfDownloads;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

   public String getDownloadUrl() {
      return downloadUrl;
   }

   public void setDownloadUrl(String downloadUrl) {
      this.downloadUrl = downloadUrl;
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

    public void setAvgRating(int rating) {
        double total = ((this.avgRating * this.numberOfRatings) + (rating));
        this.numberOfRatings++; 
        this.avgRating = round(total / this.numberOfRatings); 
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
    
    @Override
    public String toString(){
        return "Title: " + this.title + 
                "\nAuthor: " + this.author + 
                "\nImageUrl: " + this.imageUrl + "\n";
                
    }
    
    private double round(double num){
        return (double)Math.round(num * 100) / 100;
    }    
}