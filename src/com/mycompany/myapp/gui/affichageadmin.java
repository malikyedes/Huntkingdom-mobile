/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.facebook.ui.LikeButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.PublicationForum;
import com.mycompany.myapp.services.ServicePublication;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;
import com.mycompany.myapp.gui.Share;

/**
 *
 * @author malik
 */
public class affichageadmin {

    Form f,f1,fmod;
    SpanLabel lb;
    private final Resources theme;
    Button publier,modifier,media;
    TextArea contenu;
    Container pub;
    TextField tnom;
    TextArea tdescription;
    Button btnajout,btnaff,btnstat,btnvalide,supprim;
    boolean verif = false;

     
  
    public affichageadmin() {
        
       theme = UIManager.initFirstTheme("/leather"); 
     
        f = new Form("Mes Publications", new GridLayout(2, 2));
         f1 = new Form("Publication");
         f1.getToolbar().addCommandToLeftBar("Back", null, ev->{
        f.show();
        });
        
      
       // f.add(lb);
        ServiceTask serviceTask=new ServiceTask();
       // lb.setText(serviceTask.getList2().toString());
         ArrayList<PublicationForum> listpb = new ArrayList<>();
         
         listpb=serviceTask.getListAdmin();
         for (PublicationForum g : listpb) {
           
             Container Containe1 = new Container(BoxLayout.y());//new FlowLayout());
      
        Label nom=new Label("Titre");
        Label type=new Label("theme");
        Label description=new Label("description");
        Button consulter=new Button("consulter");
         Button supprimer=new Button("supprimer");
       
            
               nom.setText(g.getTitre());
               type.setText(g.getTheme());
               description.setText(g.getContenu());
               Containe1.add(nom);
               Containe1.add(type);
               Containe1.add(description);
               Containe1.add(supprimer);
               Containe1.add(consulter);

               
        LikeButton b = new LikeButton();
        Containe1.addComponent(b);
        ShareButton s1 = new ShareButton();
        s1.setText("Share");
        s1.setTextToShare("I like this Post!!!");
        Containe1.addComponent(s1);
        b.setUIID("Button");
        
        
                f.add(Containe1);
               
                
                
                
                
                 supprimer.addActionListener((e) -> {
             ArrayList<PublicationForum> listPublications = new ArrayList<>();
                   serviceTask.Supprimer(g.getId_pb());
           
          
           
           

        });
                
                
                
                
                
                
                
                  
                  consulter.addActionListener((e)->{
                       f1 = new Form("Publication",BoxLayout.y());
                       Toolbar.setGlobalToolbar(true);
  f1.getToolbar().addCommandToLeftBar("Back", null, ev->{
        f.show();
        });
        
                    ArrayList<PublicationForum> listPublications = new ArrayList<>();

                       Style sa = UIManager.getInstance().getComponentStyle("TitleCommand");
                       FontImage icona = FontImage.createMaterial(FontImage.MATERIAL_WARNING, sa);         
           
      
   
       
                        
                      listPublications= serviceTask.getListpub(g.getId_pb());
                                                                      System.out.println("for");

                         for (PublicationForum p : listPublications) {
                                                                             System.out.println("55");

                             Container PubContai = new Container(BoxLayout.y());
                             
                             
                         Label contenu1=new Label("");
             
                          contenu1.setText(p.getContenu());
                          PubContai.add(contenu1);

                        
                        
                          
                          f1.add(PubContai);
                      }
                     
                      
                             
                  
                      
                      
                      
                         System.out.println("ahla 3");

            f1.show();
                                    
                        
         });
              
                      
                                         
                  
                      
                      
                      
      
             
           
         }
        
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
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
