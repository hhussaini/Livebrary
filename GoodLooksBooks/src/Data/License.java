/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Date;

/**
 *
 * @author Kevin_Setayesh
 */
public class License {
    private String terms;
    private int numOfCopies;
    private int copiesLeft;
    private Date expirationDate;
    
    public License(){}

    public License(String terms, int numOfCopies, int copiesLeft, Date expirationDate) {
        this.terms = terms;
        this.numOfCopies = numOfCopies;
        this.copiesLeft = copiesLeft;
        this.expirationDate = expirationDate;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public int getCopiesLeft() {
        return copiesLeft;
    }

    public void setCopiesLeft(int copiesLeft) {
        this.copiesLeft = copiesLeft;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    
}
