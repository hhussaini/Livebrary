package com.glb.constants;

import java.util.HashMap;

/**
 * @author PaulMan
 */

public class CategoryMap extends HashMap {
    
    private HashMap<String, String> categoryMap;
    private String action= "[[:<:]]action[[:>:]]";
    private String americas = "[[:<:]]americas[[:>:]]";
    private String animals = "[[:<:]]animals[[:>:]]";
    private String administration = "[[:<:]]administration[[:>:]]";
    private String adventure= "[[:<:]]adventures?[[:>:]]";
    private String africa= "[[:<:]]africa[[:>:]]";
    private String artsAndPhotos = "Arts & Photography";
    private String biographies = "Biographies & Memoirs";
    private String cooking = "[[:<:]]cooking[[:>:]]";
    private String health = "Business & Investing";
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
    private final String war = "[[:<:]]wars?[[:>:]]";
    
    public CategoryMap() {
        initCategories();
    }
    
    public void initCategories() {
        
        this.put( "action", action);
        this.put( "americas", americas);
        this.put( "animals", animals);
        this.put( "administration", administration);
        this.put( "adventure", adventure);
        this.put( "africa", africa);
        this.put( "artsAndPhotos",artsAndPhotos );
        this.put( "biographies", biographies);
        this.put( "cooking",cooking );
        this.put( "health", health);
        this.put( "children", children);
        this.put( "entertainment", entertainment);
        this.put( "europe", europe);
        this.put( "foodAndWine", foodAndWine);
        this.put( "history",history );
        this.put( "horror", horror);
        this.put( "law", law);
        this.put( "fiction", fiction);
        this.put( "home",home );
        this.put( "medicine",medicine );
        this.put( "mystery", mystery);
        this.put( "reference",reference );
        this.put( "religion",religion );
        this.put( "science",science );
        this.put( "space",space );
        this.put( "sport",sport );
        this.put( "thriller",thriller );
        this.put( "travel", travel);
        this.put( "usa", usa);
        this.put( "war", war);
    }
    
    
}
