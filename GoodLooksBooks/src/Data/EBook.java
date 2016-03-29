/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kevin_Setayesh
 */
public class EBook extends Item{
    private int numberOfPages;
    
    public EBook(){}
    
    public EBook(String title, String description, String isbn, double avgRating, 
            String downloadLink, License license, Date datePublished, Author author, 
            Image image, Publisher publisher, int numberOfPages){
        
        super(title, description, isbn, avgRating, downloadLink, license, datePublished, 
                author, image, publisher);
        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    
    
}
