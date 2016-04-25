//package com.glb.controllers;
//
//import static com.glb.daos.ConnectionUtil.*;
//import com.glb.exceptions.ResourceHelperException;
//import com.glb.objects.Item;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
///**
// * @author Kevin_Setayesh
// */
//public class FileController {
//    
//    private static FileController instance = null;
//    private final List<Item>listOfAllItems;
//    private static final String BASE_URL = "https://www.gutenberg.org/";
//    private Connection conn;
//    
//    protected FileController() throws IOException {
//       // Exists only to defeat instantiation.
//        Map<String, String> map = getImagePaths();
//        this.listOfAllItems = readTextFile(map);
//        try {
//            conn = getConnection();
//        } catch (ResourceHelperException ex) {
//            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public static FileController getInstance() throws IOException {
//       if(instance == null) {
//          instance = new FileController();
//       }
//       return instance;
//    }
//    
//         
//    private static Map<String, String> getImagePaths() throws FileNotFoundException, IOException{
//        Map<String, String>map = new HashMap<>();
//        String filePath = "/Users/Kevin_Setayesh/Desktop/Spring_2016_Classes/catalog.rdf";
//        File file = new File(filePath);
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        String line;
//        boolean flag = false; 
//        String imagePath = "";
//        String id = "";
//       
//        while ((line = reader.readLine()) != null) {
//            if(line.length() == 0){
//                flag = false;
//            }
//            if(line.indexOf("<pgterms:file rdf:about=\"&f;cache/epub") == 0 && line.contains("cover") && line.contains("medium")){
//                imagePath = line.substring(line.indexOf("cache"), line.length() - 2);
//                flag = true;
//            }
//            if(flag && line.contains("#etext")){ 
//                id = line.substring(line.indexOf("#") + 1, line.lastIndexOf(" />") - 1);
//                map.put(id, BASE_URL + imagePath);
//            } 
//        } 
//        return map;
// 
//    }
//    
//    private static List<Item> readTextFile(Map<String,String> imageMap) throws FileNotFoundException, IOException{
//        List<Item>list = new ArrayList<>();  
//        String filePath = "/Users/Kevin_Setayesh/Desktop/cse308_1/cse3082016/GoodLooksBooks/src/java/files/ListOfBooks.txt";
//        File file = new File(filePath);
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        String line;
//        String id = "";
//        String title = "";
//        String author = "";
//        String friendlyTitle = "";
//        String language = "";
//        String subjects = "";
//        String date = "";
//        String downloads = "";
//        int flag = 0;  
//        while ((line = reader.readLine()) != null) {
//            String info = "";
//            if(line.length()>0){
//                 if(line.indexOf(": ") > 0){
//                    info = line.substring(line.indexOf(": ") + 2);
//                 }
//                 else{
//                     info = line;
//                 } 
//            }
//            if(line.indexOf("Id") == 0){
//                id = info;
//                flag = 0;
//            }
//            else if(line.indexOf("Title")==0){
//                title = info;
//                flag = 1;
//            }
//            else if(line.indexOf("Author")==0){
//                author = info;
//                flag = 2;
//            }
//            else if(line.indexOf("Friendly Title")==0){
//                friendlyTitle = info;
//                flag = 3;
//            }
//            else if(line.indexOf("Language")==0){
//                language = info;
//                flag = 4;
//            } 
//            else if(line.indexOf("Subjects")==0){
//                subjects = info;
//                flag = 5;
//            }
//            else if(line.indexOf("Date Created")==0){
//                date = info;
//                flag = 6;
//            }
//            else if(line.indexOf("Downloads")==0){
//                downloads = info;
//                flag = 7;
//            }
//            else if(line.length()==0){
//                Item item = new Item();
//                item.setIsbn(id);
//                item.setTitle(friendlyTitle);
//                item.setAuthor(author);
//               // item.setFriendlyTitle(friendlyTitle);
//                item.setLanguage(language);
//                String[]subjectsArray = subjects.split(" -- ");
//                List<String>subjectsList = new ArrayList<>();
//                for(int i = 0; i<subjectsArray.length; i++){
//                    subjectsList.add(subjectsArray[i]);
//                }
//                item.setGenres(subjectsList);
//                item.setDate(date);
//                item.setNumOfDownloads(downloads);
//                if(imageMap.get(item.getIsbn()) != null){
//                    item.setImageUrl(imageMap.get(item.getIsbn())); 
//                    list.add(item);
//                } 
//            }
//            else{
//                switch(flag){
//                    case 0 : id = id + info;
//                    case 1 : title = title + info;
//                    case 2 : author = author + info;
//                    case 3 : friendlyTitle = friendlyTitle + info;
//                    case 4 : language = language + info;
//                    case 5 : subjects = subjects + info;
//                    case 6 : date = date + info;
//                    case 7 : downloads = downloads + info;          
//                }
//            } 
//        }
//        return list;
//    }
//    
//    public List<Item> getListOfAllItems(){
//        return listOfAllItems;
//    }
//    
//    public Item searchForItem(String isbn){
//        for(int i = 0; i<listOfAllItems.size(); i++){
//            if(listOfAllItems.get(i).getIsbn().equalsIgnoreCase(isbn)){
//                return listOfAllItems.get(i);
//            }
//        }
//        return null;
//    }
//    
//    public List<Item> searchForAllItems(String searchString){
//        ResultSet rs;
//        searchString = "%" + searchString + "%";
//        String query = "select * from books where publisher like ?"
//                                           + " OR language like ?"
//                                           + " OR author like ?"
//                                           + " OR title like ?";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(query);
//         
//            pstmt.setString(1, searchString);
//            pstmt.setString(2, searchString);
//            pstmt.setString(3, searchString);
//            pstmt.setString(4, searchString);
//            rs = pstmt.executeQuery();
//            
//            while (rs.next()) {
//                
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        searchString = searchString.toUpperCase();
//        List<Item>list = new ArrayList<>();
//        for(int i = 0; i<listOfAllItems.size(); i++){
//            if(listOfAllItems.get(i).getAuthor().toUpperCase().contains(searchString) ||
//               listOfAllItems.get(i).getTitle().toUpperCase().contains(searchString)){
//                   list.add(listOfAllItems.get(i));
//            }
//            else{
//                for(int j = 0; j<listOfAllItems.get(i).getGenres().size(); j++){
//                    if(listOfAllItems.get(i).getGenres().get(j).equalsIgnoreCase(searchString)){
//                         list.add(listOfAllItems.get(i));
//                         break;
//                    }
//                }
//            }
//        }
//          return list;
//    }
//    
//  
//  
//   
//   
//} 
//
