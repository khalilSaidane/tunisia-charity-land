/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raed
 */
public class DialogFXMLController implements Initializable {

    @FXML
    private Label labeldialog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void buttok(ActionEvent event) {
       // labeldialog.setText("Evenement Supprimé");
         try{
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("DialogFXML.fxml"));
       Parent root = (Parent) fxmlloader.load();
       Stage stage = new Stage();
       stage.setTitle("Dialog");
       stage.setScene(new Scene(root));
       stage.hide();
               }catch (Exception e){
                   System.out.println(e);
               }
    }
    
}
