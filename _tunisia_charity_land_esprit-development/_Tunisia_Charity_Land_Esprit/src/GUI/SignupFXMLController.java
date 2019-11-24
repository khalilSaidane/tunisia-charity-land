/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.SendMailSSL;
import Service.UserAuthenticationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author oflcad
 */
public class SignupFXMLController implements Initializable {

    @FXML
    private AnchorPane myPane;
    private Button homrBtn;
    @FXML
    private TextField personalUsername;
    @FXML
    private TextField personalEmail;
    @FXML
    private TextField personalPassword;
    @FXML
    private TextField personalSecondPassword;
    @FXML
    private Button personalSignup;
    @FXML
    private TextField assoName;
    @FXML
    private TextField assoEmail;
    @FXML
    private PasswordField assPassword;
    @FXML
    private PasswordField assSecondPassword;
    @FXML
    private Button associationSignup;
    @FXML
    private Button blogButton;
    @FXML
    private Button eventButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button associationButton;
    @FXML
    private Button connectionButton;
    @FXML
    private Button storeButton;
    final  UserAuthenticationService service;
    final  SendMailSSL mailApi;
    @FXML
    private TextField errorFields;
    
    public SignupFXMLController() throws SQLException {
        this.service = new UserAuthenticationService();
        this.mailApi =  new SendMailSSL();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void goHome() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Gui/FXML.fxml"));
        homrBtn.getScene().setRoot(root);
    }
    
    @FXML
    public void signInUserWithUsernameAndEmailAndPassword(ActionEvent event) throws SQLException, IOException {
        
        
       
        String username = personalUsername.getText();
        String email = personalEmail.getText();
        String password = personalPassword.getText();
        String secondPassword = personalSecondPassword.getText();
        boolean isClear = false;
            isClear = service.insertNewUserIntoDatabase(username, email, password, secondPassword);
            System.out.println("this is Signup FXML Controller line 97 :" +service.insertNewUserIntoDatabase(username, email, password, secondPassword));
            if(isClear){
            //all data are good to go
                System.out.println("we are good to go please send an email to complete sign up !");
                mailApi.sendEmail(email,username);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/mailConfirmationTokenFXML.fxml"));
                Parent root = (Parent) loader.load();
                MailConfirmationTokenFXMLController controller = loader.<MailConfirmationTokenFXMLController>getController();
                controller.initData(email);
                personalSignup.getScene().setRoot(root);
               } else {
                 errorFields.setText("Please check your information !");
                errorFields.setStyle("-fx-text-fill:red; -fx-background-color: transparent;");
                System.out.println(" error From signup user controller ! line 104 ");
             }
        
    }
    
    
    /*
    * Signing association with name email and password
    */
    @FXML
    public void signInAssociationWithNameAndEmailAndPassword(ActionEvent event) throws SQLException,IOException{
        
        String name = assoName.getText();
        String email = assoEmail.getText();
        String password = assPassword.getText();
        String secondPassword = assSecondPassword.getText();
        
        
            if(service.inserNewAssociationIntoDatabase(name,email,password,secondPassword)){
                System.out.println("we are good to go! Please send an email to complete signup !");
                mailApi.sendEmail(email,name);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/mailConfirmationTokenFXML.fxml"));
                
                Parent root = (Parent) loader.load();
                MailConfirmationTokenFXMLController controller = loader.<MailConfirmationTokenFXMLController>getController();
                controller.initData(email);
                
                associationSignup.getScene().setRoot(root); 
                System.out.println("We are going to profile now");
            } else {
                errorFields.setText("Please check your information !");
                errorFields.setStyle("-fx-text-fill:red; -fx-background-color: transparent;");
                System.out.println("error mister piccola");
            }
        
        
    }

    @FXML
    public void navigateToProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
        connectionButton.getScene().setRoot(root);
    }


    @FXML
    public void navigateToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/EventsGuiFXML.fxml"));
        eventButton.getScene().setRoot(root);
    }

    @FXML
    public void navigateToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/FXML.fxml"));
        homeButton.getScene().setRoot(root);
    }

    @FXML
    public void navigateToBlog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/BlogGuiFXML.fxml"));
        blogButton.getScene().setRoot(root);
    }

    @FXML
    public void navigateToAssociation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/AssociationGuiFXML.fxml"));
        associationButton.getScene().setRoot(root);
    }

    @FXML
    public void navigateToStore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/StoreGuiFXML.fxml"));
        storeButton.getScene().setRoot(root);
    }
    
}
