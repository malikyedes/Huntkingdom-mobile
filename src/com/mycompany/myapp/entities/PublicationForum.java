/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.sql.Timestamp;

/**
 *
 * @author malik
 */
public class PublicationForum {
    private int id_pb ;
    private int ID_USERS,nbSignal ;
    private String titre ;
    private String contenu ;
    private String theme ; 
    private Timestamp Posted_in;
    
     private String image ;

    public PublicationForum(int id_pb, int ID_USERS, String titre, String theme) {
        this.id_pb = id_pb;
        this.ID_USERS = ID_USERS;
        this.titre = titre;
        this.theme = theme;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PublicationForum(int id_pb, String contenu) {
        this.id_pb = id_pb;
        this.contenu = contenu;
    }
    
        public PublicationForum(int id_pb, String titre, String theme, String contenu) {
        this.id_pb = id_pb;
        this.ID_USERS = ID_USERS;
        this.titre = titre;
        this.contenu = contenu;
        this.theme = theme;
        this.Posted_in = Posted_in;
        this.image = image;
    }


    public PublicationForum(int id_pb, int ID_USERS, String titre, String contenu, String theme, Timestamp Posted_in, String image) {
        this.id_pb = id_pb;
        this.ID_USERS = ID_USERS;
        this.titre = titre;
        this.contenu = contenu;
        this.theme = theme;
        this.Posted_in = Posted_in;
        this.image = image;
    }


    public PublicationForum(int id_pb, int ID_USERS, String titre, String contenu, String theme,Timestamp Posted_in) {
        this.id_pb = id_pb;
        this.ID_USERS = ID_USERS;
        this.titre = titre;
        this.contenu = contenu;
        this.theme = theme;
        this.Posted_in= Posted_in;
    }
    public PublicationForum(  String titre, String contenu, String theme) {
        
        this.titre = titre;
        this.contenu = contenu;
        this.theme = theme;
        
    }

    public int getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(int nbSignal) {
        this.nbSignal = nbSignal;
    }
    
    public PublicationForum(  String titre, String theme, Timestamp Posted_in) {
        
        this.titre = titre;
        this.theme = theme;
        this.Posted_in = Posted_in;
        
    }

    public PublicationForum() {
    }

    public int getId_pb() {
        return id_pb;
    }

    public int getID_USERS() {
        return ID_USERS;
    }

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public String getTheme() {
        return theme;
    }

    public void setId_pb(int id_pb) {
        this.id_pb = id_pb;
    }

    public void setID_USERS(int ID_USERS) {
        this.ID_USERS = ID_USERS;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Timestamp getPosted_in() {
        return Posted_in;
    }

    public void setPosted_in(Timestamp Posted_in) {
        this.Posted_in = Posted_in;
    }

    @Override
    public String toString() {
        return "PublicationForum{" + "id_pb=" + id_pb + ", ID_USERS=" + ID_USERS + ", titre=" + titre + ", contenu=" + contenu + ", theme=" + theme + ", Posted_in=" + Posted_in + '}';
    }
    

   
    
    
    
}
