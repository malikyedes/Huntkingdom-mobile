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
public class Event {
    private int id ; 
    private String titre ; 
    private String lieur ; 
    private int nbreplaces ;
    private int nbreparticipants ;
    private String DateDebut ;
    private String DateFin ;
    private String description ;
    private String image ; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieur() {
        return lieur;
    }

    public void setLieur(String lieur) {
        this.lieur = lieur;
    }

    public int getNbreplaces() {
        return nbreplaces;
    }

    public void setNbreplaces(int nbreplaces) {
        this.nbreplaces = nbreplaces;
    }

    public int getNbreparticipants() {
        return nbreparticipants;
    }

    public void setNbreparticipants(int nbreparticipants) {
        this.nbreparticipants = nbreparticipants;
    }

    public String getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(String DateDebut) {
        this.DateDebut = DateDebut;
    }

    public String getDateFin() {
        return DateFin;
    }

    public void setDateFin(String DateFin) {
        this.DateFin = DateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", titre=" + titre + ", lieur=" + lieur + ", nbreplaces=" + nbreplaces + ", nbreparticipants=" + nbreparticipants + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + ", description=" + description + ", image=" + image + '}';
    }
    
    
    
    
}
