/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Produits;
import com.mycompany.myapp.services.ServiceProduit;
import  com.mycompany.myapp.utils.WebService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class ListProduits extends BaseForm{
    public static ArrayList<Produits> p ; 
    public ListProduits(){
        p = new ArrayList<Produits>();
        setName("liste des produits");
        setTitle("liste des produits");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
               
               Acceuil a = new Acceuil();
               a.getF().show();
           
        });
           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_MAP, e -> {
           Panier p = new Panier();
           p.show();
        });
          
         getToolbar().addCommandToRightSideMenu("Lite des produits Admin", icon, (e) -> {
           ListProductsAdmin lpa = new ListProductsAdmin();
           lpa.show();
           
        });
         getToolbar().addCommandToRightSideMenu("Liste des produits client", icon, (e) -> {
           ListProduits lpa = new ListProduits();
           lpa.show();
           
        });
           
             
             
            
             WebService ws = new WebService();
    ServiceProduit ds = new ServiceProduit();
    Map x = ws.getResponse("ListP");
    ArrayList<Produits> listevents = ds.getListsEvents(x);
             for (Produits e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            Label a = new Label("Nom : "+e.getNom());
            Label d = new Label("Categorie : "+e.getC().getNom());
            Label f = new Label("Marque : "+e.getMarque());
            Label q = new Label("Quantité : "+e.getQuantite());
            Label c = new Label("Prix : "+e.getPrix());
            FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
            Button b1 = new Button("Ajouter au panier",icone);
            Button b = new Button("Détails");

            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, e.getImage(), "http://127.0.0.1:8000/Upload/" + e.getImage());
            imv = new ImageViewer(img);
            photos.add(imv);
             
            photos.add(a);
            photos.add(d);
            photos.add(f);
            photos.add(q);
            photos.add(c);
            if(!p.contains(e)){
                photos.add(b1);
            }
            photos.add(b);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                
                
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
            
            b1.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println(e);
                  p.add(e);
                  b1.setVisible(false);
                }
            });
             b.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                   ProductDetails.e = e;
                   ProductDetails ee = new ProductDetails();
                   ee.show();
                }
            });
            
        }
        show();
   }
    }

    

