/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Service.gestion_order;
import Entity.order;
import static GUI.RegistrationGuiFXMLController.user;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CartController implements Initializable {

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
    private TableView<order> cart_view;
    @FXML
    private TableColumn<order, Integer> user_view;
    @FXML
    private TableColumn<order, Integer> prodid_view;
    @FXML
    private TableColumn<order, Integer> quantity_view;
    @FXML
    private TableColumn<order, Integer> price_view;
    @FXML
    private TableColumn<order, Integer> delegue_view;
    @FXML
    private TableColumn<order, String> prodnam_view;
    @FXML
    private TableColumn<order, Integer> total_view;
    @FXML
    private TextArea tot_tot;
    @FXML
    private TextField userid_cart;
    @FXML
    private Button view_c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void view(ActionEvent event) throws SQLException {
        gestion_order gestord = new gestion_order();
        
        ArrayList<order> list;
       int uid=Integer.parseInt(userid_cart.getText());
        
        list = gestord.readcart(uid);
        ObservableList OL = FXCollections.observableArrayList(list);
        cart_view.setItems(OL);
        user_view.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        prodid_view.setCellValueFactory(new PropertyValueFactory<>("id"));
        delegue_view.setCellValueFactory(new PropertyValueFactory<>("delegue_id"));
        price_view.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity_view.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        prodnam_view.setCellValueFactory(new PropertyValueFactory<>("name_prod"));
        total_view.setCellValueFactory(new PropertyValueFactory<>("total"));
        
     tot_tot.setText(""+gestord.total(uid)+"");
    }
    }
    

