/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Users;
import Service.ServiceUser;
import Service.UserAuthenticationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author oflcad
 */
public class MailConfirmationTokenFXMLController implements Initializable {

    @FXML
    private PasswordField confirmationTokenField;
    private String email ;
    @FXML
    private TextField emailField;
    @FXML
    private Button activateAccount;
    @FXML
    private Text errorMessage;
    private ServiceUser userService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
    }   
    
    public void initData(String email) {
        this.email  = email;
        emailField.setText(email);
        emailField.setStyle("-fx-text-fill: greeen; -fx-background-color: transparent;");
        
    }
    
    /**
     *
     * @param confirmationToken
     */
    @FXML
    public void checkConfirmationToken(ActionEvent event) throws IOException, SQLException{
        UserAuthenticationService service  = new UserAuthenticationService();
        String userConfirmatiomToken = service.getConfirmationToken(email);
           
        if(confirmationTokenField.getText().equals(userConfirmatiomToken)){
            System.out.println("mail is confirmed !");
            Parent root = FXMLLoader.load(getClass().getResource("ProfilUserFXML.fxml"));
            
            
            activateAccount.getScene().setRoot(root);
            
        }else {
            errorMessage.setText("Please check your confirmation token again !");
            errorMessage.setStyle("-fx-fill: red;");
        }
        
        
        
        
    }

}
