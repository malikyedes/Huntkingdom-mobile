/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Akrem
 */
public class AffichageRec {

     Form f, f1, fmod;
    SpanLabel lb;
    private final Resources theme;
    Button publier, modifier, media;
    TextArea nom, prenom, email, sujet, description, tel;
    Container pub;
    TextField tnom;
    TextArea tdescription;
    Button btnajout, btnaff, btnstat, btnvalide;
    boolean verif = false;
    EncodedImage enc;
    String RecImg;
    ServiceReclamation servicereclamation = new ServiceReclamation();
    ArrayList<Reclamation> listpb = new ArrayList<Reclamation>();

    public AffichageRec() throws IOException {

        theme = UIManager.initFirstTheme("/leather");

        f = new Form("Tous nos Reclamation", new GridLayout(2, 2));
        f1 = new Form("Publication");
        f1.getToolbar().addCommandToLeftBar("Back", null, ev -> {
            f.show();
        });

        // f.add(lb);
        ServiceReclamation serviceTask = new ServiceReclamation();
        // lb.setText(serviceTask.getList2().toString());

        listpb = serviceTask.getListPublication();

        for (Reclamation g : listpb) {

            Container Containe1 = new Container(BoxLayout.y());//new FlowLayout());
            enc = EncodedImage.create("/giphy.gif");
            Image image = URLImage.createToStorage(enc, g.getScreenshot(), "http://localhost/integration/Uploads/" + g.getScreenshot());
            Label nom = new Label("Nom");
            Label prenom = new Label("Prenom");
            Label email = new Label("Email");
            Label tel = new Label("Telephone");
            Label sujet = new Label("Sujet");
            Label description = new Label("Description");
            Button consulter = new Button("Consulter");
            Button modifier = new Button("Modifier");
            Button supprimer = new Button("Supprimer");
            supprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    servicereclamation.supprimerRec(g.getId());
                    Dialog.show("Attention!", "la reclamtion est supprimée", "ok", null);

                    AffichageRec a;
                  
                         try {
                a = new AffichageRec();
                            a.getF().show();

            } catch (IOException ex) {
            }

                   

                }
            });
            modifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Dialog d1 = new Dialog("Modifier Rec");
                    TextArea popupBody = new TextArea(g.getNom(), 2, 10);
                    popupBody.setUIID("PopupBody");
                    popupBody.setEditable(true);
                    TextArea popupBody1 = new TextArea(g.getPrenom(), 2, 10);
                    popupBody1.setUIID("PopupBody1");
                    popupBody1.setEditable(true);
                    TextArea popupBody2 = new TextArea(g.getDescription(), 2, 10);
                    popupBody2.setUIID("PopupBody2");
                    popupBody2.setEditable(true);
                    TextArea popupBody3 = new TextArea(g.getEmail(), 2, 10);
                    popupBody3.setUIID("PopupBody3");
                    popupBody3.setEditable(true);
                    TextArea popupBody4 = new TextArea(String.valueOf(g.getTel()), 2, 10);
                    popupBody4.setUIID("PopupBody4");
                    popupBody4.setEditable(true);
                    TextArea popupBody5 = new TextArea(g.getSujet(), 2, 10);
                    popupBody5.setUIID("PopupBody5");
                    popupBody5.setEditable(true);
                    d1.setLayout(new BorderLayout());
                    Container butts1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Button upload = new Button("Upload Image");
                    butts1.add(popupBody).add(popupBody1).add(popupBody2).add(popupBody3).add(popupBody4).add(popupBody5).add(upload);
                    d1.add(BorderLayout.NORTH, butts1);

                    Container butts = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Button valider = new Button("Valider");
                    Button annuler = new Button("Annuler");
                    ///////////////////////////////////////////////////////////////////////////
                    upload.addActionListener(new ActionListener() {
                        Form mainForm = new Form();

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Display.getInstance().openImageGallery(new ActionListener() {
                                public void actionPerformed(ActionEvent ev) {
                                    Container imagec = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                    String i = (String) ev.getSource();
                                    if (i != null) {
                                        try {

                                            Image img = Image.createImage(i);
                                            img = img.scaled(Math.round(Display.getInstance().getDisplayWidth() - 40), Math.round(Display.getInstance().getDisplayHeight() - 40));
                                            imagec.add(img);
                                        } catch (IOException ex) {
                                        }
                                    }

                                    RecImg = servicereclamation.uploadPhoto(i);

                                    Container photoButtons = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                                    Container buttsPhoto = new Container(new BoxLayout(BoxLayout.X_AXIS));
                                    photoButtons.add(BorderLayout.CENTER, buttsPhoto);

                                }
                            });
                        }

                    });
                    ////////////////////////////////////////////////////////////////////////////////////////////////////
                    valider.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                            serviceTask.modifierGroup(String.valueOf(g.getId()), popupBody.getText(),
                                    popupBody1.getText(), popupBody2.getText(), popupBody3.getText(), popupBody4.getText(), popupBody5.getText(), RecImg);
                            d1.dispose();
                            AffichageRec a;
                            
                                 try {
                a = new AffichageRec();
                             a.getF().show();

            } catch (IOException ex) {
            }
                            
                            

                        }
                    });
                    annuler.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            d1.dispose();
                        }
                    });
                    butts.add(valider).add(annuler);
                    d1.add(BorderLayout.SOUTH, butts);
                    //d.showPopupDialog(update);
                    d1.show();
                }
            });

            nom.setText(g.getNom());
            prenom.setText(g.getPrenom());
            email.setText(g.getEmail());
            tel.setText(String.valueOf(g.getTel()));
            sujet.setText(g.getSujet());
            description.setText(g.getDescription());
            Containe1.add(image);
            Containe1.add(nom);
            Containe1.add(prenom);
            Containe1.add(tel);
            Containe1.add(email);
            Containe1.add(sujet);

            Containe1.add(description);
            Containe1.add(modifier);
            Containe1.add(supprimer);

            Containe1.add(consulter);

            
            f.add(Containe1);

            consulter.addActionListener((e) -> {
                f1 = new Form("Publication", BoxLayout.y());
                Toolbar.setGlobalToolbar(true);
                f1.getToolbar().addCommandToLeftBar("Back", null, ev -> {
                    f.show();
                });

                Style sa = UIManager.getInstance().getComponentStyle("TitleCommand");
                FontImage icona = FontImage.createMaterial(FontImage.MATERIAL_WARNING, sa);

                Reclamation p = new Reclamation();
                p = serviceTask.getAccessoire(g.getId());

                Container PubContai = new Container(BoxLayout.y());
                try {
                    enc = EncodedImage.create("/giphy.gif");
                } catch (IOException ex) {
                }
                Image image1 = URLImage.createToStorage(enc, g.getScreenshot(), "http://localhost/integration/Uploads/" + g.getScreenshot());
                Label nomm = new Label("Nom");
                Label tprenom = new Label("Prenom");
                Label ttel = new Label("Telephone");
                Label tsujet = new Label("Sujet");
                Label ttdescription = new Label("Description");
                Label temail = new Label("E mail");

                nomm.setText(p.getNom());
                tprenom.setText(p.getPrenom());
                ttel.setText(String.valueOf(p.getTel()));
                tsujet.setText(p.getSujet());
                ttdescription.setText(p.getDescription());
                temail.setText(p.getEmail());
                PubContai.add(image1);
                PubContai.add(nomm);
                PubContai.add(tprenom);
                PubContai.add(temail);
                PubContai.add(ttel);
                PubContai.add(tsujet);
                PubContai.add(ttdescription);

                f1.add(PubContai);

                f1.show();

            });

        }

        f.getToolbar().addCommandToRightBar("Une autre ?", null, (ev) -> {
            HomeRec h = new HomeRec();
            h.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
