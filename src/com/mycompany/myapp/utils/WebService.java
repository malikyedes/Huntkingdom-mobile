/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;



import com.mycompany.myapp.entities.Categories;
import com.mycompany.myapp.entities.Produits;
import com.mycompany.myapp.services.ServiceProduit;
import  com.mycompany.myapp.utils.WebService;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.commentaire;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Justpro
 */
public class WebService {
    static Map h;
    static String status ="";
    static int c ;
    static String lg ;
    
    public static Map<String, Object> getResponse(String url){
        url = "http://127.0.0.1:8000/"+url;
        System.out.println("url---------------"+url);
        ConnectionRequest r = new ConnectionRequest();
        System.out.println("url ::::::::: "+url);
        r.setUrl(url);
        r.setPost(false);
        System.out.println("url   :   "+r);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                System.out.println(targetReader);
                h= p.parseJSON(targetReader);
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h; 
    }
   
     
    public void addProduct(Produits e){
        
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/addProd";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("qte", e.getQuantite()+"");
     con.addArgument("nom", e.getNom());
     con.addArgument("prix", e.getPrix()+"");
     con.addArgument("categorie", e.getC().getNom());
     con.addArgument("marque", e.getMarque());
     con.addArgument("image", e.getImage());
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void addEvent(Produits e){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/addP";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("qte", e.getQuantite()+"");
     con.addArgument("nom", e.getNom());
     con.addArgument("prix", e.getPrix()+"");
     con.addArgument("user", "1");
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void editProduct(Produits e){
        
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/editProdu";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("qte", e.getQuantite()+"");
     con.addArgument("nom", e.getNom());
     con.addArgument("prix", e.getPrix()+"");
     con.addArgument("id", e.getId()+"");
     con.addArgument("categorie", e.getC().getNom());
     con.addArgument("marque", e.getMarque());
     con.addArgument("image", e.getImage());
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void deleteProduct(int id){
        
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/deleteProdu/"+id;
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
        public void addCommentaire(commentaire e){
        
        
        String url = "http://127.0.0.1:8000/AddC";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("commentaire", e.getCommentaire());
     con.addArgument("iduser", MyApplication.id+"");
     con.addArgument("idevent", e.getInevent()+"");
    
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public int count( int a){
        
        
        String url = "http://127.0.0.1:8000/countCo/"+a;
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        c = Integer.parseInt(s);
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return c ;
    }
    public void addEvent(Event e){
        
        
        String url = "http://127.0.0.1:8000/AddE";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("titre", e.getTitre());
     con.addArgument("nbp", e.getNbreplaces()+"");
     con.addArgument("lieu", e.getLieur()+"");
    con.addArgument("dateDebut", e.getDateDebut()+"");
     con.addArgument("dateFin", e.getDateFin()+"");
     con.addArgument("description", e.getDescription()+"");
     con.addArgument("image", e.getImage()+"");
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void EditEvent(Event e){
        
        
        String url = "http://127.0.0.1:8000/editE";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("titre", e.getTitre());
     con.addArgument("nbp", e.getNbreplaces()+"");
     con.addArgument("lieu", e.getLieur()+"");
    con.addArgument("dateDebut", e.getDateDebut()+"");
     con.addArgument("dateFin", e.getDateFin()+"");
     con.addArgument("description", e.getDescription()+"");
     con.addArgument("image", e.getImage()+"");
     con.addArgument("id", e.getId()+"");
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void deleteEvent(int e){
        
        
        String url = "http://127.0.0.1:8000/deleteE/"+e;
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   public void partEvent(int e, int u){
        
        
        String url = "http://127.0.0.1:8000/PartE";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("idEvent", e+"");
     con.addArgument("idUser", u+"");
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja participé à cet evenement", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    
}
