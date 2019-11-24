/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.RegistrationGuiFXMLController.user;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Entity.Produit;
import Service.gestion_produit;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ModifprodController implements Initializable {

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
    private TextField id_prod2;
    @FXML
    private TextField prix_prod2;
    @FXML
    private TextField quantité_prod2;
    @FXML
    private Button Modifier_prod1;

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
    private void modification(ActionEvent event) throws IOException {
         Produit p = new Produit();
        int ID=Integer.parseInt(id_prod2.getText());
        p.setId(ID); 
        int price=Integer.parseInt(prix_prod2.getText());
        //p.setPrice(price); 
        int quantity=Integer.parseInt(quantité_prod2.getText());
        //p.setQuantity(quantity); 
         gestion_produit gestprod = new gestion_produit();
         gestprod.updateQuantité(p, quantity);
         gestprod.updatePrice(p,price);
      
        
        
        Parent root = FXMLLoader.load(getClass().getResource("Gui/deleg.fxml"));
        //Scene scene = new Scene(root);
        Modifier_prod1.getScene().setRoot(root);
    }
    
}
