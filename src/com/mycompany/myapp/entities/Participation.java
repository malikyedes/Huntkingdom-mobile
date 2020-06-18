/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Jasser
 */
public class Participation {
    
    private Event e ; 
    private int user ; 

    public Event getE() {
        return e;
    }

    public void setE(Event e) {
        this.e = e;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

   

    public Participation() {
    }

    @Override
    public String toString() {
        return "Participation{" + "e=" + e + ", user=" + user + '}';
    }

   
    
}
