/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class Customer extends User implements Serializable{
   // private int libraryID;
   // private String barCode;
    private Map<String, Integer> reviewedItems;
    private List<Item> itemsList;
    private List<Item> wishList;
    private List<Item> ownedItems;
    
    public Customer(){
       super();
       this.reviewedItems = new HashMap<>();
       this.itemsList = new ArrayList<>();
       this.wishList = new ArrayList<>();
       this.ownedItems = new ArrayList<>();
    }
    
    public Customer(String username, String password, String firstName, String lastName, String street, 
            String city, String state, String zipcode, String phoneNumber, String email, String type, String accessCode){
        super(username, password, firstName, lastName, street, city, state, zipcode, phoneNumber, email, type, accessCode);
        this.reviewedItems = new HashMap<>();
        this.itemsList = new ArrayList<>();
        this.wishList = new ArrayList<>();
        this.ownedItems = new ArrayList<>();
    }

    public Map<String, Integer> getReviewedItems() {
        return reviewedItems;
    }

    public void setReviewedItems(Map<String, Integer> reviewedItems) {
        this.reviewedItems = reviewedItems;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public List<Item> getWishList() {
        return wishList;
    }

    public void setWishList(List<Item> wishList) {
        this.wishList = wishList;
    }

    public List<Item> getOwnedItems() {
        return ownedItems;
    }

    public void setOwnedItems(List<Item> ownedItems) {
        this.ownedItems = ownedItems;
        
    }
    
    public void putInItemsList(Item item){
        for(int i = 0; i<this.itemsList.size(); i++){
            if(itemsList.get(i).getIsbn().equals(item.getIsbn())){
                return;
            }
        }
        this.itemsList.add(item);
    }
}
    
   
    
    
    