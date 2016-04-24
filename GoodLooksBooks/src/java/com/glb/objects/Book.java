package com.glb.objects;

import java.io.Serializable;

/**
 *
 * @author mobile-mann
 */
public class Book implements Serializable {
    private String isbn;
    private String title;
    private String imageUrl;
    private String author;
    
    public Book(){}

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
   
}
