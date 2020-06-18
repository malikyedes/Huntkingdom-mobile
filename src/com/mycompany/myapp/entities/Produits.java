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
public class Produits {
    private int id ; 
    private String nom ; 
    private String marque; 
    private Double prix ; 
    private int quantite ; 
    private String image ; 
    private Categories c ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Categories getC() {
        return c;
    }

    public void setC(Categories c) {
        this.c = c;
    }
    
    public Produits() {
    }

    @Override
    public String toString() {
        return "Produits{" + "id=" + id + ", nom=" + nom + ", marque=" + marque + ", prix=" + prix + ", quantite=" + quantite + ", image=" + image + ", c=" + c + '}';
    }

    
    
    
    
}
