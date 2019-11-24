/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Users;
import Service.UserAuthenticationService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.util.prefs.Preferences;
/**
 * FXML Controller class
 *
 * @author oflcad
 */
public class AdminDashBoardProfileFXMLController implements Initializable {

    @FXML
    private AnchorPane myPane;
    public Users user;
    @FXML
    private Button ProfileButton;
    @FXML
    private Button ExitButton;
    @FXML
    private Button EventsButton;
    @FXML
    private Button BlogButton;
    @FXML
    private Button UsersButton;
    @FXML
    private TextField IdField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField phoneAdresse;
    @FXML
    private TextField expiresField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        int userId = user.getId();
//        try {
//            UserAuthenticationService service = new UserAuthenticationService();
//            user = service.getAdminInfo(userId);
//            System.out.println("we have fresh data from the database");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        String id = String.valueOf(user.getId());
//        String phone_nbr = String.valueOf(user.getPhone_nbr());
//        String Date = String.valueOf(user.getExpires_at());
//        IdField.setText(id);
//        emailField.setText(user.getEmail());
//        adresseField.setText(user.getAdresse());
//        phoneAdresse.setText(phone_nbr);
//        expiresField.setText(Date);

        System.out.println("this is profile page");
        //System.out.println(user.toString());
        
    }   
    
    public void initDate(Users user){
        this.user = user ;
        System.out.println("from profile admin dash bord");
        System.out.println(user.toString());
    }
    
    
 
    
    
}
