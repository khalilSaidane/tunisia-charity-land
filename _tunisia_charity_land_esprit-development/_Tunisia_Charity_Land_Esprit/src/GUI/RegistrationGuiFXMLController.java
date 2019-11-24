/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import Service.UserAuthenticationService;
import java.sql.SQLException;
import javafx.scene.control.TextField;
import GUI.Gui.AdminDashBordFXMLController;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author oflcad
 */
public class RegistrationGuiFXMLController implements Initializable {

    
    @FXML
    private Button loginBtn;
    private Button SignUpBtn;
    public static Users user;

    private TextField username;
    private TextField email;
    private TextField password;
    @FXML
    private TextField login_username;
    @FXML
    private TextField login_pass;
    @FXML
    private Button signupBtn;
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
    private Button forgotPassword;
    @FXML
    private TextField errorField;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    @FXML
    public void goSignUp(ActionEvent event) throws IOException, SQLException {
        
            Parent root = FXMLLoader.load(getClass().getResource("Gui/SignupFXML.fxml"));
            signupBtn.getScene().setRoot(root);
    }
    
    
    //this function is called when a user presses the login button
    @FXML
    public void loginUser(ActionEvent event) throws IOException,SQLException {
            
                    //Getting data from GUI 
                String username = login_username.getText();
                String pass = login_pass.getText();
                boolean isValid = false;
                UserAuthenticationService service = new UserAuthenticationService();
                user = service.authenticateUserWithCrendentials(username,pass);
                
                String roles = user.getRoles();
                
                System.out.println(username);
                System.out.println(pass);
                System.out.println(roles);
                
                if(!(username.equals("") || pass.equals(""))){
                // case treatment for each type of users
                if(roles != null){
                switch(roles){
                    case "admin":{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBordFXML.fxml"));
                            Parent root =  (Parent) loader.load();
                            AdminDashBordFXMLController controller = loader.<AdminDashBordFXMLController>getController();
                            System.out.println("This is for ADMIN !!!");
                            controller.initData(user);
                            loginBtn.getScene().setRoot(root);
                        break;
                    }
                    case "user" :{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilUserFXML.fxml")); // this is home
                            Parent root =  (Parent) loader.load();
                            ProfilUserFXMLController controller = loader.<ProfilUserFXMLController>getController();
                            System.out.println("This is for NORMAL USER !!!");
                            controller.initData(user.getEmail());
                            loginBtn.getScene().setRoot(root);
                        break;
                    }
                    case "association":{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/FXML.fxml"));
                            Parent root =  (Parent) loader.load();
                            FXMLController controller = loader.<FXMLController>getController();
                            System.out.println("This is for ASSOCIATION USER !!!");
                            controller.initData(user);
                            loginBtn.getScene().setRoot(root);
                        break;
                        }
                    }
                } else {
                    errorField.setText("Please check you credentials!");
                    errorField.setStyle("-fx-text-fill:red; -fx-background-color:transparent");
                }
               } else {
                    errorField.setText("Please check you credentials!");
                    errorField.setStyle("-fx-text-fill:red; -fx-background-color:transparent");
                }
                
                
                 
                  
    }

    @FXML
    public void navigateToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/FXML.fxml"));
        homeButton.getScene().setRoot(root);
    }

    @FXML
    public void navigateToBlog(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Gui/AffichageArticle.fxml"));
          blogButton.getScene().setRoot(root);
    }

    @FXML
    public void navigateToEvents(ActionEvent event)  throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("Gui/browseEvents.fxml"));
        eventButton.getScene().setRoot(root);
    }

    

    @FXML
    public void navigateToProfile(ActionEvent event)  throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("Gui/RegistrationGuiFXML.fxml"));
         connectionButton.getScene().setRoot(root);
    }

    @FXML
    public void navigateToStore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/StoreGuiFXML.fxml"));
        storeButton.getScene().setRoot(root);
    }

    
    
}
