/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kevin_Setayesh
 */
public abstract class Item {
    private String title;
    private String description;
    private String isbn;
    private double avgRating;
    private String downloadLink;
    private License license;
    private Date datePublished;
    private List<Genre> genres;
    private Map<User, Review> reviews;
    private List<User> usersCheckedout;
    private Author author;
    private Image image;
    private Publisher publisher;
    
    public Item(){}

    public Item(String title, String description, String isbn, double avgRating, 
            String downloadLink, License license, Date datePublished, Author author, 
            Image image, Publisher publisher) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.avgRating = avgRating;
        this.downloadLink = downloadLink;
        this.license = license;
        this.datePublished = datePublished;
        this.genres = new ArrayList<>();
        this.reviews = new HashMap<>();
        this.usersCheckedout = new ArrayList<>();
        this.author = author;
        this.image = image;
        this.publisher = publisher;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Map<User, Review> getReviews() {
        return reviews;
    }

    public void setReviews(Map<User, Review> reviews) {
        this.reviews = reviews;
    }

    public List<User> getUsersCheckedout() {
        return usersCheckedout;
    }

    public void setUsersCheckedout(List<User> usersCheckedout) {
        this.usersCheckedout = usersCheckedout;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
    
}
