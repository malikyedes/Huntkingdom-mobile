/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.PublicationForum;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTask {

    public ArrayList<Task> tasks;
    
    public static ServiceTask instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceTask() {
         req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }

    
    
               
    
    
    
    
    
        ArrayList<PublicationForum> listpb = new ArrayList<>();

        
        
        
        
        
        
         public ArrayList<PublicationForum> parseListTaskJson(String json) {

        ArrayList<PublicationForum> listpb = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                PublicationForum G = new PublicationForum();
                float id_pb = Float.parseFloat(obj.get("idPb").toString());
                G.setId_pb((int) id_pb);
                G.setTitre(obj.get("titre").toString());
                G.setTheme(obj.get("theme").toString());
                G.setContenu(obj.get("contenu").toString());
                G.setImage(obj.get("image").toString());

                System.out.println(G);
                
                listpb.add(G);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listpb);
        return listpb;

    }
         
         
         
          public ArrayList<PublicationForum> parseListTaskJson2(String json) {

        ArrayList<PublicationForum> listpb1= new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("root");

            //Parcourir la liste des tâches Json
                            System.out.println("hhhh");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                PublicationForum P = new PublicationForum();
              //  float id_pb = Float.parseFloat(obj.get("idPb").toString());
            //    G.setId_pb((int) id_pb);
                P.setContenu(obj.get("contenu").toString());
                P.setImage(obj.get("image").toString());
                
                                System.out.println("erreur");

                System.out.println(P);
                
                listpb1.add(P);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listpb1);
        return listpb1;

    }
        
        
        
            ArrayList<PublicationForum> listPublications = new ArrayList<>();

            public ArrayList<PublicationForum> getListPublication(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/integration.1/web/app_dev.php/PublicationMobile/42");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                listPublications = ser.parseListTaskJson2(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPublications;
    }
        
 public ArrayList<PublicationForum> getListpub(int idpb){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/integration.1/web/app_dev.php/PublicationMobile/"+idpb);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                listpb = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listpb;
    }
      
            
            
            
            
            
            
     public ArrayList<PublicationForum> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/integration.1/web/app_dev.php/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                listpb = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listpb;
    }
      
          public void ajoutPublication(PublicationForum p) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/integration.1/web/app_dev.php/Publicationadd?contenu="+ p.getContenu()+"&theme=" + p.getTheme()+"&titre=" + p.getTitre();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
          
          
          
                public void Modifier(PublicationForum p) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/integration.1/web/app_dev.php/UpdateMobile/"+p.getId_pb()+ p.getContenu()+"&theme=" + p.getTheme()+"&titre=" + p.getTitre();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

                public void Supprimer(int idpb) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/integration.1/web/app_dev.php/delete/"+idpb;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
             

     
      public ArrayList<PublicationForum> getListAdmin(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/integration.1/web/app_dev.php/adminmobile");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                listpb = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listpb;
    }

     
     
     }
