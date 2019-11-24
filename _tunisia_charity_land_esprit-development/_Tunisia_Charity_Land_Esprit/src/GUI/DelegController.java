/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Entity.Produit;
import static GUI.RegistrationGuiFXMLController.user;
import Service.gestion_produit;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DelegController implements Initializable {

    @FXML
    private AnchorPane myPane;
    @FXML
    private Button homeButton;
    @FXML
    private Button blogButton;
    @FXML
    private Button eventButton;
    @FXML
    private Button associationButton;
    @FXML
    private Button connectionButton;
    @FXML
    private Button storeButton;
    @FXML
    private TableView<Produit> tab_prod;
    @FXML
    private TableColumn<Produit, Integer> id_tab;
    @FXML
    private TableColumn<Produit, Integer> delegue_tab;
    @FXML
    private TableColumn<Produit, String> img_tab;
    @FXML
    private TableColumn<Produit, Integer> price_tab;
    @FXML
    private TableColumn<Produit, Integer> quantity_tab;
    @FXML
    private TableColumn<Produit, Date> created_tab;
    @FXML
    private TableColumn<Produit, String> category_tab;
    @FXML
    private TableColumn<Produit, String> description_tab;
    @FXML
    private TableColumn<Produit, String> name_tab;
    @FXML
    private Button Supprimer_prod;
    @FXML
    private Button Modifier_prod;
    @FXML
    private Button Ajouter_prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         gestion_produit gestprod = new gestion_produit();
        ArrayList<Produit> list;
        list = gestprod.read();
        ObservableList OL = FXCollections.observableArrayList(list);
        tab_prod.setItems(OL);
        id_tab.setCellValueFactory(new PropertyValueFactory<>("id"));
        delegue_tab.setCellValueFactory(new PropertyValueFactory<>("delegue_id"));
        img_tab.setCellValueFactory(new PropertyValueFactory<>("img_url"));
        price_tab.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity_tab.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        created_tab.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        category_tab.setCellValueFactory(new PropertyValueFactory<>("category"));
        description_tab.setCellValueFactory(new PropertyValueFactory<>("description"));
        name_tab.setCellValueFactory(new PropertyValueFactory<>("name_prod"));
    }    

    @FXML
    private void navigateToHome(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("Gui/FXML.fxml"));
            homeButton.getScene().setRoot(root);
    }

    @FXML
    private void navigateToBlog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/AffichageArticle.fxml"));
            blogButton.getScene().setRoot(root);
    }
    

    @FXML
    private void navigateToEvents (ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("Gui/browseEvents.fxml"));
            eventButton.getScene().setRoot(root);
    }

    @FXML
    private void navigateToProfile(ActionEvent event) throws IOException {
             if(user == null){
            Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
            connectionButton.getScene().setRoot(root);
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
            connectionButton.getScene().setRoot(root);
            connectionButton.setText("Profil");
    }}

    @FXML
    private void navigateToStore(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Gui/StoreGuiFXML.fxml"));
        
        //Scene scene = new Scene(root);
        storeButton.getScene().setRoot(root);
    }

   @FXML
    private void fenetre_aj(ActionEvent event) throws SQLException, IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Gui/ajoutprod.fxml"));
        
        //Scene scene = new Scene(root);
        Ajouter_prod.getScene().setRoot(root);
    }
    @FXML
    private void fenetre_mod(ActionEvent event) throws SQLException, IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Gui/modifprod.fxml"));
        
        //Scene scene = new Scene(root);
        Modifier_prod.getScene().setRoot(root);}
        
     @FXML
    
     private void fenetre_supp(ActionEvent event) throws SQLException, IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Gui/suppprod.fxml"));
        
        //Scene scene = new Scene(root);
        Supprimer_prod.getScene().setRoot(root);
   
    }
    
}
