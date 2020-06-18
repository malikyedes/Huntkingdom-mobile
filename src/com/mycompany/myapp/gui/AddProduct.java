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
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class AddProduct extends BaseForm{
    private String im ;
    ComboBox<String> c;
    
    public AddProduct(){
        c = new ComboBox();
        setName("Ajouter produit");
        setTitle("Ajouter produit");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListProductsAdmin lpa = new ListProductsAdmin();
            lpa.show();
        });
        getToolbar().addCommandToRightSideMenu("Lite des produits Admin", icon, (e) -> {
           ListProductsAdmin lpa = new ListProductsAdmin();
           lpa.show();
           
        });
         getToolbar().addCommandToRightSideMenu("Liste des produits client", icon, (e) -> {
           ListProduits lpa = new ListProduits();
           lpa.show();
           
        });
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label l = new Label("Catégorie");
            TextField nom = new TextField();
            nom.setHint("Nom");
            TextField marque = new TextField();
            marque.setHint("Marque");
            TextField prix = new TextField();
            prix.setHint("Prix");
            TextField qte = new TextField();
            qte.setHint("Quantité");
           
            Button img = new Button("Ajouter une image",icone);
          
            Button b = new Button("Ajouter");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
            Map x = ws.getResponse("ListC");
            ServiceProduit ds = new ServiceProduit();
           ArrayList<Categories> listevents = ds.getListscate(x);
           for (Categories e : listevents) {
               c.addItem(e.getNom()+"");
           }
           photos.add(nom);
           photos.add(l);
           photos.add(c);
           photos.add(marque);
           photos.add(prix);
           photos.add(qte);
            
            
      
            
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
                Produits p = new Produits();
                p.setImage(im);
                p.setPrix(Double.parseDouble(prix.getText()));
                p.setQuantite(Integer.parseInt(qte.getText()));
                p.setNom(nom.getText());
                p.setMarque(marque.getText());
                Categories c1 = new Categories();
                c1.setNom(c.getSelectedItem());
                p.setC(c1);
                ws.addProduct(p);
                ListProduits lp = new ListProduits();
                lp.show();
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
