/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.commentaire;
import com.mycompany.myapp.utils.WebService;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jasser
 */
public class EditEvent extends BaseForm{
    private String im ;
    public static Event ev ; 
    public EditEvent(){
       
        
        setName("Ajouter Evenement");
        setTitle("Ajouter Evenement");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           EventDetails.e = ev ;
           EventDetails ed = new EventDetails();
           ed.show();
        });
        getToolbar().addCommandToRightSideMenu("Ajouter évenements", icon, (e) -> {
          AddEvent ae = new AddEvent();
           
        });
         getToolbar().addCommandToRightSideMenu("Liste des évenements", icon, (e) -> {
           
           ListEvent le = new ListEvent();
        });
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Picker datePicker = new Picker();
             DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                
               
           
            datePicker.setType(Display.PICKER_TYPE_DATE);
            
            Picker datePicker1 = new Picker();
           
            datePicker1.setType(Display.PICKER_TYPE_DATE);
             
            TextField titre = new TextField();
            titre.setText(ev.getTitre());
            TextField description = new TextField();
            description.setText(ev.getDescription());
            TextField lieu = new TextField();
            lieu.setText(ev.getLieur());
            TextField nbp = new TextField();
            nbp.setText(ev.getNbreplaces()+"");
            Label Date = new Label("Date de début : ");
            Label Date1 = new Label("Date de fin : ");
            Button img = new Button("Ajouter une image",icone);
          
            Button b = new Button("Ajouter");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
           photos.add(titre);
           photos.add(description);
           photos.add(lieu);
           photos.add(Date);
            photos.add(datePicker);
             photos.add(Date1);
            photos.add(datePicker1);
            photos.add(nbp);
            
           
            
            photos.add(img);
            photos.add(b);
            add(photos);
            img.addActionListener(e->{
                
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost:88/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new com.codename1.l10n.SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want

                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    im =fileNameInServer ;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                }
            });
            b.addActionListener(e->{
                
                Date dateobj = datePicker1.getDate();
                Date da = datePicker.getDate();
                String stt = df.format(dateobj);
                String stt1 = df.format(da);
               Date dnnow = new Date(); 
               Date dcc = new Date();              
       try {
           dnnow = df.parse(stt);
           dcc = df.parse(stt1);          
       } catch (ParseException ex) {
           
       }                          
                if(titre.getText().equals("") || description.getText().equals("") || lieu.getText().equals("")  || nbp.getText().equals("") || ((int)(dnnow.getTime()- dcc.getTime()) <= 0)){
                    Dialog.show("Erreur", "Vérifiez vos informations", "Ok", null);
                    
                }else{
                    
                    ev.setDateDebut(stt);
                    ev.setDateFin(stt1);
                    ev.setDescription(description.getText());
                    if(im != null){
                    ev.setImage(im);
                    }
                    ev.setTitre(titre.getText());
                    ev.setLieur(lieu.getText());
                    ev.setNbreplaces(Integer.parseInt(nbp.getText()));
                    ws.EditEvent(ev);
                    ListEvent le = new ListEvent();
                    le.show();
                    
               }
                });
            /**c.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    WebService ws = new WebService();
                    String status = ws.getStatus("check/"+6+"/"+e.getId());
                    if(status.equals("subscribed")){
                        MatiereVideos.ml = e ;
                        System.out.println(e.getId());
                        MatiereVideos m = new MatiereVideos();
                        m.f.show();
                    }else{
                        
                    }

                }
            });**/
           
      
        show();
    }
    
}
