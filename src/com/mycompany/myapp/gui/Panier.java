/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Categories;
import com.mycompany.myapp.entities.Produits;
import com.mycompany.myapp.services.ServiceProduit;
import  com.mycompany.myapp.utils.WebService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
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
public class Panier extends BaseForm{
    public ArrayList<Produits> p ;
    int qte = 1;
    public Panier(){
        p = ListProduits.p ;
        setName("Panier");
        setTitle("Panier");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           ListProduits lp = new ListProduits();
           lp.show();
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
    
             for (Produits e : p) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            Label a = new Label("Nom : "+e.getNom());
            Label c = new Label("Prix : "+e.getPrix());
            e.setQuantite(qte);
            Label qt = new Label("Quantité : "+qte);
            Container ctn = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Button b = new Button("+");
            Button b1 = new Button("-");
            ctn.add(b);
            ctn.add(b1);
            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, e.getImage(), "http://127.0.0.1:8000/Upload/" + e.getImage());
            imv = new ImageViewer(img);
            photos.add(imv);
            photos.add(a);
            photos.add(c);
            photos.add(qt);
            photos.add(ctn);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                
                
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
            b.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    qte = qte+1;
                    int f = qte;
                  qt.setText(f+"");
                  e.setQuantite(qte);
                  p.remove(e);
                  p.add(e);
                }
            });
            b1.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    qte = qte-1;
                    int f = qte;
                  qt.setText(f+"");
                  e.setQuantite(qte);
                  p.remove(e);
                  p.add(e);
                }
            });
            
        }
             Button bou = new Button("Valider la commande");
             add(bou);
             bou.addActionListener(e->{
                  for (Produits ev : p) {
                     ws.addEvent(ev);
                     
                 }
                 
             });
        show();
    }
    
}
