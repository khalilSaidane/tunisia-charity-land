/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Article;
import Entity.Users;
import static GUI.RegistrationGuiFXMLController.user;
import Service.ServiceArticle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjouterArticleFXMLController implements Initializable {

   
    @FXML
    private TextField titre_article;
    @FXML
    private TextField image_article;
    @FXML
    private TextArea contenu_article;
    @FXML
    private Button boutton_ajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) throws SQLException, IOException {
        RegistrationGuiFXMLController C = new RegistrationGuiFXMLController();
         Users user = C.user;
         
          Article a = new Article();
        a.setImage_url(image_article.getText());
        a.setTitre(titre_article.getText());
          a.setContenu(contenu_article.getText());
        a.setId_auteur(12);
        
        ServiceArticle sa = new ServiceArticle();
        sa.add(a);
        Parent root = FXMLLoader.load(getClass().getResource("Gui/GestionArticleFXML.fxml"));
        
        //Scene scene = new Scene(root);
        boutton_ajout.getScene().setRoot(root);
        
    
}
}