package com.glb.constants;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author PaulMan
 * @param <String>
 */

public class CategoryMap extends TreeMap<String, String> {
    
    private String action= "[[:<:]]action[[:>:]]";
    private String americas = "[[:<:]]americas[[:>:]]";
    private String animals = "[[:<:]]animals[[:>:]]";
    private String administration = "[[:<:]]administration[[:>:]]";
    private String adventure= "[[:<:]]adventures?[[:>:]]";
    private String africa= "[[:<:]]africa[[:>:]]";
    private String artsAndPhotos = "Arts & Photography";
    private String business = "Business & Investing";
    private String biographies = "Biographies & Memoirs";
    private String cooking = "[[:<:]]cooking[[:>:]]";
    private String health = "health";
    private String children = "[[:<:]]children('s)?[[:>:]]";
    private String entertainment = "[[:<:]]Entertainment[[:>:]]";
    private String europe = "[[:<:]]europe[[:>:]]";
    private String foodAndWine= "[[:<:]]Food & Wine[[:>:]]";
    private String history= "[[:<:]]histor(y|ical)[[:>:]]";
    private String horror = "[[:<:]]horrors?[[:>:]]";
    private String law = "[[:<:]]law[[:>:]]";
    private String fiction = "(literature & fiction|genre fiction)";
    private String home = "[[:<:]]Home & Garden[[:>:]]";
    private String medicine = "[[:<:]]medicine[[:>:]]";
    private String mystery = "[[:<:]]myster(y|ies)[[:>:]]";
    private String reference = "[[:<:]]references?[[:>:]]";
    private String religion = "(Religion & Spirituality|christian(ity)?|bibles?)";
    private String science = "[[:<:]]sciences?[[:>:]]";
    private String space = "[[:<:]]space[[:>:]]";
    private String sport = "[[:<:]](sports?|football|baseball|basketball|soccer|tennis)[[:>:]]";
    private String thriller = "[[:<:]]thrillers[[:>:]]";
    private String travel = "[[:<:]]Travel[[:>:]]";
    private String usa = "[[:<:]]united states[[:>:]]";
    private String war = "[[:<:]]wars?[[:>:]]";
    
    public CategoryMap() {
        initCategories();
    }
    
    public void initCategories() {
        
        this.put( "Action", action);
        this.put( "Americas", americas);
        this.put( "Animals", animals);
        this.put( "Administration", administration);
        this.put( "Adventure", adventure);
        this.put( "Africa", africa);
        this.put( "ArtsAndPhotos",artsAndPhotos );
        this.put( "Biographies", biographies);
        this.put( "Business", business);
        this.put( "Cooking",cooking );
        this.put( "Children", children);
        this.put( "Entertainment", entertainment);
        this.put( "Europe", europe);
        this.put( "FoodAndWine", foodAndWine);
        this.put( "Fiction", fiction);
        this.put( "Home",home );
        this.put( "History",history );
        this.put( "Health", health);
        this.put( "Horror", horror);
        this.put( "Law", law);
        this.put( "Medicine",medicine );
        this.put( "Mystery", mystery);
        this.put( "Reference",reference );
        this.put( "Religion",religion );
        this.put( "Science",science );
        this.put( "Space",space );
        this.put( "Sport",sport );
        this.put( "Thriller",thriller );
        this.put( "Travel", travel);
        this.put( "Usa", usa);
        this.put( "War", war);
    } 
}
