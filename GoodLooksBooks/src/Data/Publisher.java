/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin_Setayesh
 */
public class Publisher extends User{ 
    private List<Item>items;
    
    public Publisher(){}
    
    public Publisher(String firstName, String lastName, String userName, String email, long phoneNumber, 
            int userId, Address address){
        
        super(firstName, lastName, userName, email, phoneNumber, 
             userId, address);
        this.items = new ArrayList<>();
        
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public void addBook(Item item){
        
    }
    
    public void editBook(Item item){
        
    }
    
    
    
}
