/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.io.IOException;

/**
 *
 * @author malik
 */
public class Acceuil {
 
    
      Label controle;
    Form f;
    TextField tnom;
    TextArea tdescription;
    Button btnajout,btnaff,btnstat,btnvalide;
    private String SERVICEURL;
    private final Resources theme;
    
    
    
    
    public Acceuil() {
        
     theme = UIManager.initFirstTheme("/leather"); 
        f = new Form("Acceuil",BoxLayout.y());
        Toolbar tb = f.getToolbar();
        Image icona = theme.getImage("icon.png"); 
       Container topBar = BorderLayout.east(new Label(icona));
       topBar.add(BorderLayout.SOUTH, new Label("HuntKingdom", "SidemenuTagline")); 
       topBar.setUIID("SideCommand");
       tb.addComponentToSideMenu(topBar);



     
        Label ma=new Label("Bienvenue dans notre Application");
            ImageViewer img = null;

          try {
               img = new ImageViewer(Image.createImage("/logoHK.png"));
          } catch (IOException ex) {
          }
        
        f.add(ma);
        f.add(img) ;
  
     
                
 
        
            tb.addMaterialCommandToSideMenu("Evenements", FontImage.MATERIAL_HOME, e -> {
            
            ListEvent s =new ListEvent ();
              s.show();

        
        });
            tb.addMaterialCommandToSideMenu("Login", FontImage.MATERIAL_HOME, e -> {
            
            HomeForm h =new HomeForm();
        h.getF().show();

        
        });
            tb.addMaterialCommandToSideMenu("Boutique", FontImage.MATERIAL_HOME, e -> {
            
            ListProduits h =new ListProduits();
        h.show();

        
        });
           
     
       
        tb.addMaterialCommandToSideMenu("Forum", FontImage.MATERIAL_HOME, e -> {
            
            HomeForm h =new HomeForm();
        h.getF().show();

        
        });
         tb.addMaterialCommandToSideMenu("Reclamations", FontImage.MATERIAL_HOME, e -> {
             
             
                        HomeRec a;
                        a = new HomeRec();
                        a.getF().show();

        
        });

    


    
      
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

}

    
    


