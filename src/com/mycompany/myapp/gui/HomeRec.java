/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.io.IOException;


/**
 *
 * @author Akrem
 */
public class HomeRec {

    Label controle;
    Form f;
    TextField tnom, tprenom, ttel;
    TextArea tdescription, tsujet, tmail;
    Button btnajout, btnaff, btnstat, btnvalide;
    private String SERVICEURL;
    private final Resources theme;
String RecImg;
    boolean verif = false;

    public HomeRec() {
        theme = UIManager.initFirstTheme("/leather");
        f = new Form("home", BoxLayout.y());
        Toolbar tb = f.getToolbar();
        Image icona = theme.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icona));
        topBar.add(BorderLayout.SOUTH, new Label("HuntKingdom", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        Label ma = new Label("Ajouter Une Reclamation");
        tnom = new TextField("", "Nom");
        tprenom = new TextField("", "Prenom");
        ttel = new TextField("", "Telephone");
        tmail = new TextField("", "Email");
        tsujet = new TextField("", "Sujet");
        tdescription = new TextField("", "Description");
        Button photobutton = new Button("Upload image");

        photobutton.addActionListener(new ActionListener() {
            Form mainForm = new Form();

            @Override
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().openImageGallery(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        ServiceReclamation ser = new ServiceReclamation();

                        String i = (String) ev.getSource();
                        if (i != null) {
                            try {

                                Image img = Image.createImage(i);
                                img = img.scaled(Math.round(Display.getInstance().getDisplayWidth() - 40), Math.round(Display.getInstance().getDisplayHeight() - 40));
                            } catch (IOException ex) {
                            }
                        }

                         RecImg = ser.uploadPhoto(i);
                        System.out.println("hhhh" + RecImg);

                    }
                });
            }

        });

        btnajout = new Button("Ajouter");
        btnaff = new Button("Affichage");
        controle = new Label("");
       

        f.add(ma);
        f.add(tnom);
        f.add(tprenom);
        f.add(ttel);
        f.add(tmail);
        f.add(tsujet);
        f.add(tdescription);
        f.add(controle);
        f.add(photobutton);
        

        f.add(btnajout);
        //f.add(btnaff);

        btnvalide = new Button("valider");
        Validator v = new Validator();

        v.addConstraint(tnom, new LengthConstraint(3));
        v.addConstraint(tnom, new RegexConstraint("[a-zA-Z]+", null));
        v.addConstraint(tmail, RegexConstraint.validEmail());

        v.addSubmitButtons(btnajout);

        btnajout.addActionListener((e) -> {
            ServiceReclamation ser = new ServiceReclamation();
            Reclamation p = new Reclamation(Integer.parseInt(ttel.getText()), tdescription.getText(),
                    tsujet.getText(), tnom.getText(), tprenom.getText(), tmail.getText(),RecImg);
            ser.ajoutReclamation(p);
            AffichageRec a;
            try {
                a = new AffichageRec();
                            a.getF().show();

            } catch (IOException ex) {
            }

        });
        f.getToolbar().addCommandToLeftBar("Back", null, ev -> {
            Acceuil a = new Acceuil();
            a.getF().show();
        });
        // btnaff.addActionListener((e)->{
        // }); 
        tb.addMaterialCommandToSideMenu("Nos Reclamations", FontImage.MATERIAL_HOME, e -> {

            AffichageRec a;
            try {
                a = new AffichageRec();
                            a.getF().show();

            } catch (IOException ex) {
            }

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
