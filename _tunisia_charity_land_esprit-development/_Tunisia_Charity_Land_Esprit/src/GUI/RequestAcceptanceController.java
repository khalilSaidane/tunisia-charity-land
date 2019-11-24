/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.friend;
import Service.friendservice;
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
public class RequestAcceptanceController implements Initializable {

    @FXML
    private Label mtlabel;
    @FXML
    private Button mtaccept;
    @FXML
    private Button mtdecline;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PublicRoomController b = new PublicRoomController();
mtlabel.setText(b.requestmsg);
    }    

    @FXML
    private void mtacceptaction(ActionEvent event) throws SQLException {
        friend a = new friend();
          friend b = new friend();
           PublicRoomController pub = new PublicRoomController();
          String c = pub.requestus;
          String d = pub.requestuser;
          a.setUsername(c);
          a.setFriendusername(d);
          b.setFriendusername(c);
          b.setUsername(d);
          friendservice fr = new friendservice();
          fr.ajouterfriend(b);
          fr.ajouterfriend(a);
          requestservice rq = new requestservice();
          rq.deleteByuser(c,d);
      Stage stage = (Stage) mtaccept.getScene().getWindow();
      stage.close();
    }

    @FXML
    private void mtdeclineaction(ActionEvent event) {
         friend a = new friend();
          friend b = new friend();
           PublicRoomController pub = new PublicRoomController();
          String c = pub.requestus;
          String d = pub.requestuser;
          a.setUsername(c);
          a.setFriendusername(d);
          b.setFriendusername(c);
          b.setUsername(d);
          requestservice rq = new requestservice();
         rq.deleteByuser(c,d);
      Stage stage = (Stage) mtdecline.getScene().getWindow();
      stage.close();
    }
    
}
