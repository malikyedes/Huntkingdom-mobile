/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Categories;
import com.mycompany.myapp.entities.Produits;
import com.mycompany.myapp.services.ServiceProduit;
import  com.mycompany.myapp.utils.WebService;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class ServiceProduit {
    
    public  ArrayList<Produits> getListsEvents(Map m){
        ArrayList<Produits> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("produis");
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Produits p = new Produits();
            Double ll = (Double) f.get("id");
            
            Double prix = (Double) f.get("prix");
            Double qte = (Double) f.get("quantite");
            p.setId(ll.intValue());
           
            p.setNom((String) f.get("nom"));
            p.setMarque((String) f.get("marque"));
            p.setImage((String) f.get("webpath"));
            p.setPrix(prix);
            p.setQuantite(qte.intValue());
            Map e =  (Map) f.get("idCategorie");
            Categories c = new Categories();
            Double ld = (Double) e.get("id");
            c.setId(ld.intValue());
            c.setNom((String) e.get("nom"));
            p.setC(c);
            
            /**
            e.setTitre((String) f.get("titre"));
            e.setDescription((String) f.get("description"));
            e.setCategorie((String) f.get("categorie"));
            e.setPhoto((String) f.get("photo"));
            e.setDateDebut((Date)f.get("dateDebut"));
            e.setDateFin((Date)f.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            e.setLieu((String) f.get("lieu"));
            
            e.setNb_max(((Double) f.get("nbMax")).intValue());**/
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
        
    }
    public  ArrayList<Categories> getListscate(Map m){
        ArrayList<Categories> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("categorie");
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Categories p = new Categories();
            
            
            Double ld = (Double) f.get("id");
            p.setId(ld.intValue());
            p.setNom((String) f.get("nom"));
            
            
            /**
            e.setTitre((String) f.get("titre"));
            e.setDescription((String) f.get("description"));
            e.setCategorie((String) f.get("categorie"));
            e.setPhoto((String) f.get("photo"));
            e.setDateDebut((Date)f.get("dateDebut"));
            e.setDateFin((Date)f.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            e.setLieu((String) f.get("lieu"));
            
            e.setNb_max(((Double) f.get("nbMax")).intValue());**/
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
        
    }
    
}
