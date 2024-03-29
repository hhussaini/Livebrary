/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glb.objects;

import java.io.Serializable;

/**
 *
 * @author Kevin_Setayesh
 */
public class User implements Serializable{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String phoneNumber;
    private String email;
    private String type;
    private String company;
    private int eBookLendPeriod;
    private String contrast;
    private String dyslexic;
    private int audiobookLendPeriod;
    private int videoLendPeriod;
    private String maturityStart;
    private String maturityEnd;
    
    public User(){}

    public User(String username, String password, String firstName, String lastName, String street, 
            String city, String state, String zipcode, String phoneNumber, String email, String type, String company) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
        this.email = email;    
        this.type = type;
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getType() {
        // TODO. Remove this?
        if (type == null) {
            return "guest";
        } 
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    
    public int getEBookLendPeriod() {
      return eBookLendPeriod;
   }

   public void setEBookLendPeriod(int eBookLendPeriod) {
      this.eBookLendPeriod = eBookLendPeriod;
   }

    public String getContrast() {
        return contrast;
    }

    public void setContrast(String contrast) {
        this.contrast = contrast;
    }

    public String getDyslexic() {
        return dyslexic;
    }

    public void setDyslexic(String dyslexic) {
        this.dyslexic = dyslexic;
    }
   
   
   
   public int geteBookLendPeriod() {
      return eBookLendPeriod;
   }

   public void seteBookLendPeriod(int eBookLendPeriod) {
      this.eBookLendPeriod = eBookLendPeriod;
   }

   public int getAudiobookLendPeriod() {
      return audiobookLendPeriod;
   }

   public void setAudiobookLendPeriod(int audiobookLendPeriod) {
      this.audiobookLendPeriod = audiobookLendPeriod;
   }

   public int getVideoLendPeriod() {
      return videoLendPeriod;
   }

   public void setVideoLendPeriod(int videoLendPeriod) {
      this.videoLendPeriod = videoLendPeriod;
   }

   public String getMaturityStart() {
      return maturityStart;
   }

   public void setMaturityStart(String maturityStart) {
      this.maturityStart = maturityStart;
   }

   public String getMaturityEnd() {
      return maturityEnd;
   }

   public void setMaturityEnd(String maturityEnd) {
      this.maturityEnd = maturityEnd;
   }
}
