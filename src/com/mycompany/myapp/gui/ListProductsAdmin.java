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

import com.mycompany.myapp.gui.ListProduits;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.gui.AddProduct;
import com.mycompany.myapp.gui.EditProduct;
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
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class ListProductsAdmin extends BaseForm{
    
    public ListProductsAdmin(){
        setName("liste des produits");
        setTitle("liste des produits");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           
           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
           AddProduct a = new AddProduct();
                a.show(); 
        });
           
          
         getToolbar().addCommandToRightSideMenu("Lite des produits Admin", icon, (e) -> {
           ListProductsAdmin lpa = new ListProductsAdmin();
           lpa.show();
           
        });
         getToolbar().addCommandToRightSideMenu("Liste des produits client", icon, (e) -> {
           ListProduits lpa = new ListProduits();
           lpa.show();
           
        });
            getToolbar().addCommandToRightSideMenu("Ajouter produit", icons, (e) -> {
                AddProduct a = new AddProduct();
                a.show();             
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
            Button b = new Button("Edit");
            Button g = new Button("Delete");

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
            photos.add(b);
            photos.add(g);
            
            
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                
                
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
            b.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                  EditProduct.p = e;
                  EditProduct pp = new EditProduct();
                  pp.show();
                }
            });
             g.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                  ws.deleteProduct(e.getId());
                  ListProductsAdmin lpa = new ListProductsAdmin();
                  lpa.show();
                }
            });
            
            
        }
    }
    
    
}
