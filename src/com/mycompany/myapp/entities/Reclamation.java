/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Akrem
 */
public class Reclamation {
    private int id,tel ;
    private String description,sujet,nom,prenom,email ,screenshot; 
    private Date added_in ;

    public Reclamation(int id, int tel, String description, String sujet, String nom, String prenom, String email, Date added_in) {
        this.id = id;
        this.tel = tel;
        this.description = description;
        this.sujet = sujet;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.added_in = added_in;
    }

    public Reclamation() {
    }

    public Reclamation(int tel, String description, String sujet, String nom, String prenom, String email, Date added_in) {
        this.tel = tel;
        this.description = description;
        this.sujet = sujet;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.added_in = added_in;
    }

    public Reclamation(int tel, String description, String sujet, String nom, String prenom, String email) {
        this.tel = tel;
        this.description = description;
        this.sujet = sujet;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Reclamation(int tel, String description, String sujet, String nom, String prenom, String email, String screenshot) {
        this.tel = tel;
        this.description = description;
        this.sujet = sujet;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.screenshot = screenshot;
    }

    public Reclamation(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public Date getAdded_in() {
        return added_in;
    }

    public void setAdded_in(Date added_in) {
        this.added_in = added_in;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", tel=" + tel + ", description=" + description + ", sujet=" + sujet + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", added_in=" + added_in + '}';
    }
    
    
}
