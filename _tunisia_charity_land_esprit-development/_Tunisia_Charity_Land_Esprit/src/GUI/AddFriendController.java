/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.request;
import Service.requestservice;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leanbois
 */
public class AddFriendController implements Initializable {

    @FXML
    private Label mylabel1;
    @FXML
    private Button mtbutt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PublicRoomController a = new PublicRoomController();
        mylabel1.setText(a.allacc);
    }    

    @FXML
    private void mtbuttaction(ActionEvent event) throws SQLException {
         PublicRoomController a = new PublicRoomController();
         String b = a.allacc;
         RegistrationGuiFXMLController c = new RegistrationGuiFXMLController();
      
        String d = c.user.getUsername();
        String msg = ""+ b +"wants to be your friend";
        request req = new request();
        req.setMsg(msg);
        req.setUser_receive(b);
        req.setUser_send(d);
        requestservice rs = new requestservice();
        rs.ajouterrequest(req);
         Stage stage = (Stage) mtbutt.getScene().getWindow();
      stage.close();
    }
    
}
