/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Date;


/**
 *
 * @author Kevin_Setayesh
 */
public class AudioBook extends Item{
   
    private double length;
    
    public AudioBook(String title, String description, String isbn, double avgRating, 
            String downloadLink, License license, Date datePublished, Author author, 
            Image image, Publisher publisher, double length){
        
        super(title, description, isbn, avgRating, downloadLink, license, datePublished, 
              author, image, publisher);
        this.length = length;
       
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
    
    public void getPreview(){
        
    }
    
    
}
