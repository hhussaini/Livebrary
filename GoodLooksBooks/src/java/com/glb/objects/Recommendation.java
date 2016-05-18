/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glb.objects;

/**
 *
 * @author Kevin_Setayesh
 */
public class Recommendation {
    private String email;
    private boolean checkOut_or_email;
    
    public Recommendation(){}

    public Recommendation(String email, boolean checkOut_or_email) {
        this.email = email;
        this.checkOut_or_email = checkOut_or_email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCheckOut_or_email() {
        return checkOut_or_email;
    }

    public void setCheckOut_or_email(boolean checkOut_or_email) {
        this.checkOut_or_email = checkOut_or_email;
    }
    
    
}
