/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Users;
import Service.UserAuthenticationService;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author oflcad
 */
public class FXMLController implements Initializable {

    private Button cnxBtn;
    private Button blogBtn;
    public Users loggedInUser;
    UserAuthenticationService service;
    @FXML
    private AnchorPane myPane;
    @FXML
    private Button homeButton;
    @FXML
    private Button blogButton;
    @FXML
    private Button eventButton;
    private Button associationButton;
    @FXML
    private Button connectionButton;
    @FXML
    private Button storeButton;
    @FXML
    private Button startButton;

    public FXMLController() {
        try {
            this.service = new UserAuthenticationService();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    /**
     * Login/Sign-up Button navigation
     * @param event
     * @throws IOException 
     */
   
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }   
    
    
    
    
    // This is how you pass a user object from one class to another  === Same concept of persistancy
    public void initData(Users user) {
        this.loggedInUser = user;
    }
    
    
    
    @FXML 
    public void navigateToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/FXML.fxml"));
        homeButton.getScene().setRoot(root);
    }
    
    
    @FXML 
    public void navigateToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/browseEvents.fxml"));
        eventButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/RegistrationGuiFXML.fxml"));
        connectionButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToStore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/StoreGuiFXML.fxml"));
        storeButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToAssociation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/AssociationGuiFXML.fxml"));
        associationButton.getScene().setRoot(root);
    }

    @FXML
    public void navigateToBlog(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Gui/AffichageArticle.fxml"));
          blogButton.getScene().setRoot(root);
    }
    
    
    
}
