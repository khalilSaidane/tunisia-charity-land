/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.SendMailSSL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author oflcad
 */
public class AdminDashBoardMailContactFXMLController implements Initializable {

    @FXML
    private Button profileButton;
    @FXML
    private Button usersButton;
    @FXML
    private Button blogButton;
    @FXML
    private Button eventButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button apiControllButton;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField emailField;
    @FXML
    private TextArea bodyField;
    private String email;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        emailField.setText(email);
    }
    
    public void intiEmail(String email){
        this.email = email;
    }

    @FXML
    public void navigateToUsers(ActionEvent event) {
        
    }

    @FXML
    public void navigateToBlog(ActionEvent event) {
    }

    @FXML
    public void navigateToEvents(ActionEvent event) {
    }

    @FXML
    public void logout(ActionEvent event) {
    }

    @FXML
    public void navigateToApiControllButton(ActionEvent event) {
    }

    @FXML
    public void navigateToProfile(ActionEvent event) {
    }
    
    @FXML
    public void sendEmailTo(ActionEvent event) {
        SendMailSSL mailApi = new SendMailSSL();
        
        mailApi.contactMaker(email, subjectField.getText(), bodyField.getText());
    }
}
