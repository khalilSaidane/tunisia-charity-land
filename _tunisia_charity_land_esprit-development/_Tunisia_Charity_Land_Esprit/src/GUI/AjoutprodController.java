/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Entity.Produit;
import static GUI.RegistrationGuiFXMLController.user;
import Service.gestion_produit;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AjoutprodController implements Initializable {

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
    private Button Ajouter_prod1;
    @FXML
    private TextField id_prod;
    @FXML
    private TextField category_prod;
    @FXML
    private TextField Description_prod;
    @FXML
    private TextField Delegue_prod;
    @FXML
    private TextField Img_prod;
    @FXML
    private TextField price_prod;
    @FXML
    private TextField quantity_prod;
    @FXML
    private DatePicker created_prod;
    @FXML
    private TextField Name_prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void ajout(ActionEvent event) throws IOException {
            
        Produit p = new Produit();
        
        int ID=Integer.parseInt(id_prod.getText());
        p.setId(ID); 
        
        int delegue_id=Integer.parseInt(Delegue_prod.getText());
        p.setDelegue_id(delegue_id);       
        
        p.setImg_url(Img_prod.getText());
        
        int price=Integer.parseInt(price_prod.getText());
        p.setPrice(price);
        
        int quantity=Integer.parseInt(quantity_prod.getText());
        p.setQuantity(quantity);
        
        Calendar v=Calendar.getInstance();
        int y=created_prod.getValue().getYear();
        int m=created_prod.getValue().getMonthValue();
        int j=created_prod.getValue().getDayOfMonth();
      
        Date date = new Date(j,m,y); 
        p.setCreated_at( date);
        p.setCategory(category_prod.getText());
        p.setDescription(Description_prod.getText());
        p.setName_prod(Name_prod.getText());
        
        gestion_produit gestprod = new gestion_produit();
        gestprod.add(p);
        
        
        Parent root = FXMLLoader.load(getClass().getResource("Gui/deleg.fxml"));
        
        //Scene scene = new Scene(root);
        Ajouter_prod1.getScene().setRoot(root);}
    }
    
