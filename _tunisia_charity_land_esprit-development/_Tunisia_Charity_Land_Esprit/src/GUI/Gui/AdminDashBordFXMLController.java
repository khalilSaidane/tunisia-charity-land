/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Gui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import Entity.Users;
import GUI.AdminDashBoardApiController;
import GUI.AdminDashBoardBlogFXMLController;
import GUI.AdminDashBoardEventFXMLController;
import GUI.AdminDashBoardUserFXMLController;
/**
 * FXML Controller class
 *
 * @author oflcad
 */
public class AdminDashBordFXMLController implements Initializable {

    @FXML
    private AnchorPane myPane;
    
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField role;
    
    private Users selectedUser;
    
    @FXML
    private TextField idField;
    @FXML
    private TextField expiresField;
    @FXML
    private TextField enabledField;
    
    @FXML
    private Button blogButton;
    
    @FXML
    private Button profileButton;
    @FXML
    private Button usersButton;
    @FXML
    private Button eventButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button apiControllButton;
    @FXML
    private TextField lockedTextField;
    @FXML
    private TextField lastLoginTextField;
    
    
    /*
     * This Method gets an Admin to initialize the view 
     */
    public void initData(Users user){
        
        this.selectedUser = user;
        String idValue = String.valueOf(selectedUser.getId());
        String expiresValue = String.valueOf(selectedUser.getExpires_at());
        String enabledValue = String.valueOf(selectedUser.getEnabled());
        String lastLoginValue = String.valueOf(selectedUser.getLast_login());
        email.setText(selectedUser.getEmail());
        password.setText(selectedUser.getPassword());
        role.setText(selectedUser.getRoles());
        idField.setText(idValue);
        expiresField.setText(expiresValue);
        enabledField.setText(enabledValue);
        lockedTextField.setText(lastLoginValue);
        lastLoginTextField.setText(idValue);
        System.out.println(user.toString());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
            profileButton.setStyle("-fx-text-fill: #F25652;-fx-background-color: transparent;");
            usersButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            blogButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            eventButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            apiControllButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent; ");
            logoutButton.setStyle("-fx-text-fill: white; -fx-background-color: transparent;");
    }    
    
    
    
//    public void editProfile() throws SQLException, IOException {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashBoardProfileFXML.fxml"));
//                
//                
//                Parent root = (Parent) loader.load();
//                AdminDashBoardProfileFXMLController controller = loader.<AdminDashBoardProfileFXMLController>getController();
//                controller.initDate(selectedUser);
//                
//                profileButton.getScene().setRoot(root);
//    }
    
    @FXML // this to check users 
    public void navigateToUsers() throws SQLException,IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashBoardUserFXML.fxml"));
        Parent root =  (Parent) loader.load();
        AdminDashBoardUserFXMLController controller = loader.<AdminDashBoardUserFXMLController>getController();
        controller.initData(selectedUser);
        
        usersButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToBlog() throws SQLException,IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashBoardBlogFXML.fxml"));
        Parent root =  (Parent) loader.load();
        AdminDashBoardBlogFXMLController controller = loader.<AdminDashBoardBlogFXMLController>getController();
        controller.initData(selectedUser);
        
        
        blogButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToEvents() throws SQLException,IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashBoardEventFXML.fxml"));
        Parent root =  (Parent) loader.load();
        AdminDashBoardEventFXMLController controller = loader.<AdminDashBoardEventFXMLController>getController();
        
        controller.initData(selectedUser);
        eventButton.getScene().setRoot(root);
    }
    @FXML 
    public void logout() throws SQLException,IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("registrationGuiFXML.fxml"));
        logoutButton.getScene().setRoot(root);
    }
    @FXML
    public void navigateToApiControllButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashBoardApiController.fxml"));
        Parent root =  (Parent) loader.load();
        AdminDashBoardApiController controller = loader.<AdminDashBoardApiController>getController();
        controller.initData(selectedUser);
        apiControllButton.getScene().setRoot(root);
    }
}
