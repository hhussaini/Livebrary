/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.glb.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author PaulMan
 */
@Named(value = "bookBean")
@SessionScoped
//@Dependent
public class BookBean implements Serializable {
    
    private String isbn;
    private String bookId;
    private String title;
    private String language;
    private String description;
    private String editionInfo;
    private String authorId;
    private String authorName;
    private String publisherId;
    private String publisherName;
    private String imageUrl;
    private String summary;
    
    public BookBean() {
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getBookId() {
        return bookId;
    }
    
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getEditionInfo() {
        return editionInfo;
    }
    
    public void setEditionInfo(String editionInfo) {
        this.editionInfo = editionInfo;
    }
    
    public String getAuthorId() {
        return authorId;
    }
    
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    
    public String getAuthorName() {
        return authorName;
    }
    
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    public String getPublisherId() {
        return publisherId;
    }
    
    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }
    
    public String getPublisherName() {
        return publisherName;
    }
    
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    
    
}
