package com.glb.objects;

import java.io.Serializable;

/**
 *
 * @author mobile-mann
 */
public class Book extends Item implements Serializable{
    private int numPages;
    
    public Book(){}

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }
   
}
