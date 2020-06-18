/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.util.regex.RE;
import com.mycompany.myapp.entities.PublicationForum;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceTask;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;




/**
 *
 * @author bhk
 */
public class HomeForm {
    
    Label controle;
    Form f;
    TextField tnom;
    TextArea tdescription;
    Button btnajout,btnaff,btnstat,btnvalide;
    private String SERVICEURL;
    private final Resources theme;
    
    boolean verif = false;

    public HomeForm() {
        theme = UIManager.initFirstTheme("/leather"); 
        f = new Form("home",BoxLayout.y());
        Toolbar tb = f.getToolbar();
        Image icona = theme.getImage("icon.png"); 
       Container topBar = BorderLayout.east(new Label(icona));
       topBar.add(BorderLayout.SOUTH, new Label("HuntKingdom", "SidemenuTagline")); 
       topBar.setUIID("SideCommand");
       tb.addComponentToSideMenu(topBar);



     
        Label ma=new Label("Ajouter Une Publication");
        tnom = new TextField("","Titre ");
        tdescription = new TextField("","Contenu");
        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage");
        controle=new Label("");
        ComboBox<String> typeC=new ComboBox();
        typeC.addItem("Chasse");
        typeC.addItem("Péche");
        typeC.addItem("Régle Génerale");
        
        f.add(ma);
        f.add(tnom);
        f.add(controle);
        f.add(typeC);
        f.add(tdescription);
        f.add(btnajout);
        //f.add(btnaff);
     
        btnvalide=new Button("valider");
         Validator v = new Validator();
         
             v.addConstraint(tnom, new LengthConstraint(4));
            v.addConstraint(tnom,new RegexConstraint("[a-zA-Z]+", null));
            v.addConstraint(tdescription, new LengthConstraint(7, "non"));
              
                
                
               
                
 
        
           
            v.addSubmitButtons(btnajout);
           
        btnajout.addActionListener((e) -> {
            ServiceTask ser = new ServiceTask();
            
            PublicationForum p = new PublicationForum(tnom.getText(), tdescription.getText(), typeC.getSelectedItem());
            ser.ajoutPublication(p);
          
           
           

        });
         f.getToolbar().addCommandToLeftBar("Back", null, ev->{
                     Acceuil a = new Acceuil();
                     a.getF().show();
                       });
       // btnaff.addActionListener((e)->{
       // }); 
        tb.addMaterialCommandToSideMenu("Tous les Publications", FontImage.MATERIAL_HOME, e -> {
            
            
                Affichage a=new Affichage();
        a.getF().show();

        
        });
         tb.addMaterialCommandToSideMenu("Mes Publications", FontImage.MATERIAL_HOME, e -> {
             
             
                        affichageadmin a=new affichageadmin();
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
