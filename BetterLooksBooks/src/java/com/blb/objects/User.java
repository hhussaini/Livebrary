package com.blb.objects;

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
    private String firstServerUsername;

    public User(){}

    public User(String username, String password, String firstName, String lastName, String street, 
            String city, String state, String zipcode, String phoneNumber, String email, String type) {
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
    }
    
   public String getFirstServerUsername() {
      return firstServerUsername;
   }

   public void setFirstServerUsername(String firstServerUsername) {
      this.firstServerUsername = firstServerUsername;
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
}
