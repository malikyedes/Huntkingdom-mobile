/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.commentaire;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */

public class Services {
    public  ArrayList<Event> getListsProduits(Map m){
        ArrayList<Event> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("event");
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Event p = new Event();
            Double ll = (Double) f.get("id");
            
            Double nbplaces = (Double) f.get("nbreplaces");
            Double nbparticipants = (Double) f.get("nbreparticipants");
            p.setNbreparticipants(nbparticipants.intValue());
            p.setNbreplaces(nbplaces.intValue());
            p.setId(ll.intValue());
           
            p.setLieur((String) f.get("lieu"));
            p.setDescription((String) f.get("description"));
            p.setTitre((String) f.get("titre"));
            p.setImage((String)f.get("image"));
             Map map1 = ((Map) f.get("datedebut"));
             Map map2 = ((Map) f.get("datefin"));
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Date date2 = new Date((((Double)map2.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = formatter.format(date1);
            String s2 = formatter.format(date2);
            p.setDateFin(s2);
            p.setDateDebut(s1);
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
        
    }
    public  ArrayList<commentaire> getListsComments(Map m){
        ArrayList<commentaire> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("comment");
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            commentaire p = new commentaire();
            
            p.setCommentaire((String)f.get("commentaire"));
            
           
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
        
    }
    
}
