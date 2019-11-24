/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.mysql.jdbc.Messages.getString;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import Service.gestion_order;
import Entity.order;
import Entity.Produit;
import static GUI.RegistrationGuiFXMLController.user;
import Service.gestion_produit;
import Utils.Datasourc;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class BuyController implements Initializable {

    @FXML
    private AnchorPane myPane;
    @FXML
    private Button homeButton;
    @FXML
    private Button blogButton;
    @FXML
    private Button eventButton;
    @FXML
    private Button associationButton;
    @FXML
    private Button connectionButton;
    @FXML
    private Button storeButton;
    @FXML
    private ImageView product_img;
    @FXML
    private Text product_id;
    @FXML
    private Button add_cart;
    @FXML
    private Button view_cart;
  
    @FXML
    private TextArea description_prod;
    @FXML
    private Text product_name;
    @FXML
    private Text price_prod;
    @FXML
    private TextField quantity_v;
    @FXML
    private TextField user_buy;
    StoreController s= new StoreController();
           String im=s.ur; 
           String j = s.getur();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         gestion_produit gestpr=new gestion_produit();
       
        
        Produit pr=new Produit();
        try {
            pr=gestpr.rechercheprod(im);
        } catch (SQLException ex) {
            Logger.getLogger(BuyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(im);
        Image imag = new Image(im);
        product_img.setImage(imag);
        
        String name= pr.getName_prod();
        System.out.println(name);
        product_name.setText(name);
        
        String descr=pr.getDescription();
        description_prod.setText(descr);
 
        int id=pr.getId();
        product_id.setText(String.valueOf(id));
       System.out.println(id);
        int price=pr.getPrice();
        price_prod.setText(String.valueOf(price)); 
       
    }    

   @FXML
    private void navigateToHome(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("Gui/FXML.fxml"));
            homeButton.getScene().setRoot(root);
    }

    @FXML
    private void navigateToBlog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/AffichageArticle.fxml"));
            blogButton.getScene().setRoot(root);
    }
    

    @FXML
    private void navigateToEvents (ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("Gui/browseEvents.fxml"));
            eventButton.getScene().setRoot(root);
    }

    @FXML
    private void navigateToProfile(ActionEvent event) throws IOException {
             if(user == null){
            Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
            connectionButton.getScene().setRoot(root);
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
            connectionButton.getScene().setRoot(root);
            connectionButton.setText("Profil");
    }}


    @FXML
    private void navigateToStore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/StoreGuiFXML.fxml"));
        
        //Scene scene = new Scene(root);
        storeButton.getScene().setRoot(root);
    }

    @FXML
    private void addcart(ActionEvent event) {
        Produit pr=new Produit();
        gestion_produit gestpr=new gestion_produit();
        try {
            pr=gestpr.rechercheprod(im);
        } catch (SQLException ex) {
            Logger.getLogger(BuyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        gestion_order gestord=new gestion_order();
        String im=getString(s.ur);        
        order o=new order();
       
       
        
        String name= pr.getName_prod();
        o.setName_prod(name);
        
        int id=pr.getId();
        o.setId(id);
        
        int price=pr.getPrice();
        o.setPrice(price);
        
        int deleg=pr.getDelegue_id();
        o.setDelegue_id(deleg);
        
        int quant=Integer.parseInt(quantity_v.getText());
        o.setQuantity(quant);
        
        int user=Integer.parseInt(user_buy.getText());
        o.setUser_id(user);
        
        o.setTotal(0);
        
        gestord.addtocart(o);
        
        
        
    }

    @FXML
    private void tocart(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Gui/cart.fxml"));
        
        //Scene scene = new Scene(root);
        storeButton.getScene().setRoot(root);
    }

    @FXML
    private void backmag(ActionEvent event) {
    }
    
}
