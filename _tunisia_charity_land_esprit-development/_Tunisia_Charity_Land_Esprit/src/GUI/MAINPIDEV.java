/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Raed
 */
public class MAINPIDEV extends Application {
    
    @Override
     public void start(Stage primaryStage) throws IOException {
        
       
        Parent root1;
        root1 = FXMLLoader.load(getClass().getResource("ProfilUserFXML.fxml"));
        Scene scene = new Scene(root1);
        
        primaryStage.setTitle("Profil User");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     public static void main(String[] args) {
        launch(args);
    }
    
}
