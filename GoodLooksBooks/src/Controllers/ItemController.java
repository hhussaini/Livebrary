/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Data.Item;
import java.util.Map; 
import Data.User;
import java.util.HashMap;

/**
 *
 * @author Kevin_Setayesh
 */
public class ItemController {
    private Map<String, Item>itemsMap;
    
    public ItemController(){
        this.itemsMap = new HashMap<>();
    }
    
    public void checkOut(User user, Item item){
        
    }
    
    public void checkIn(User user, Item item){
        
    }
    
    public void removeAllExpiredBooks(){
        
    }
    
}
