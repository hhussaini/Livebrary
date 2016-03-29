/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kevin_Setayesh
 */
public class Customer {
    private Map<String, Integer>reviewedItems;
    private List<Item>currentItems;
    private int libraryId;
    private Library localLibrary;
    private String barcode;
    private List<Item>wishList;
    
    public Customer(){}
    
    public Customer(int libraryId, Library localLibrary, String barcode){
        this.libraryId = libraryId;
        this.localLibrary = localLibrary;
        this.barcode = barcode;
        this.reviewedItems = new HashMap<>();
        this.currentItems = new ArrayList<>();
        this.wishList = new ArrayList<>();
        
    }

    public Map<String, Integer> getReviewedItems() {
        return reviewedItems;
    }

    public void setReviewedItems(Map<String, Integer> reviewedItems) {
        this.reviewedItems = reviewedItems;
    }

    public List<Item> getCurrentItems() {
        return currentItems;
    }

    public void setCurrentItems(List<Item> currentItems) {
        this.currentItems = currentItems;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public Library getLocalLibrary() {
        return localLibrary;
    }

    public void setLocalLibrary(Library localLibrary) {
        this.localLibrary = localLibrary;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public List<Item> getWishList() {
        return wishList;
    }

    public void setWishList(List<Item> wishList) {
        this.wishList = wishList;
    }
    
    public void addToWishList(String isbn, Item item){
        
    }
    
    public void addToCart(String isbn, Item item){
        
    }
    
    public void removeFromWishList(String isbn){
        
    }
    
    public void removeFromCart(String isbn){
        
    }
    
    
    
}
