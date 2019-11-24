/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Article;
import Entity.Users;
import GUI.Gui.AdminDashBordFXMLController;
import Service.AdminDashBoardService;
import Service.ArticleService;
import Service.ServiceArticle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author oflcad
 */
public class AdminDashBoardBlogFXMLController implements Initializable {

    private Users user;
    @FXML
    private AnchorPane myPane;
    @FXML
    private Button blogButton;
    private Button usersBtn;
    @FXML
    private Button apiControllButton;
    private Button ProfileBtn;
    @FXML
    private TableView<Article> BlogTable;
    @FXML
    private Button profileButton;
    @FXML
    private Button usersButton;
    @FXML
    private Button eventButton;
    @FXML
    private Button logoutButton;
    @FXML
    private TableColumn<Article, Integer> idColumn;
    @FXML
    private TableColumn<Article, Integer> authorColumn;
    @FXML
    private TableColumn<Article, String> titleColumn;
    @FXML
    private TableColumn<Article, String> contentColumn;
    @FXML
    private Text messageField;
    private ArrayList<Article> articleList;
    private ServiceArticle service ;
    private AdminDashBoardService adminDashBoardService;
    @FXML
    private Button deleteVutton;
    @FXML
    private Button contactButton;
    @FXML
    private Button refreshButton;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            
            profileButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            usersButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            blogButton.setStyle("-fx-text-fill: #F25652;-fx-background-color: transparent;");
            eventButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            apiControllButton.setStyle("-fx-text-white: black;-fx-background-color: transparent; ");
            logoutButton.setStyle("-fx-text-fill: white ;-fx-background-color: transparent;");
        try {
            ServiceArticle service = new ServiceArticle();
            articleList = service.read();
            ObservableList observableList = FXCollections.observableList(articleList);
            BlogTable.setItems(observableList);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            authorColumn.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
            contentColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashBoardBlogFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
            
            
            
            
    }    
    
     public void initData(Users user) {
        this.user = user;
    }
    
    @FXML
    public void deleteSelectedPost(ActionEvent event) throws SQLException{
        
        Article  selectedArticle = BlogTable.getSelectionModel().getSelectedItem();
        
        System.out.println(selectedArticle.toString());
        if(selectedArticle.equals(null)){
            messageField.setText("Please select a post to delete");
        }else {
            adminDashBoardService.deleteSelectedArticle(selectedArticle);
            //service.deleteById(selectedArticle);
            populateTable();
        }
        
    }
    
    public void populateTable(){
        articleList = service.read();
        try {
            ObservableList observableList = FXCollections.observableList(articleList);
            BlogTable.setItems(observableList);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            authorColumn.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
            contentColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void contactMaker(ActionEvent event){
        
    }
    
    @FXML
    public void navigateToProfile() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBordFXML.fxml"));
        Parent root = (Parent) loader.load();
        AdminDashBordFXMLController controller = loader.<AdminDashBordFXMLController>getController() ;
        controller.initData(this.user);
        
        profileButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToUsers() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBoardUserFXML.fxml"));
        Parent root =  (Parent) loader.load();
        AdminDashBoardUserFXMLController controller = loader.<AdminDashBoardUserFXMLController>getController();
        controller.initData(user);
        
        usersButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToEvents() throws IOException{
       FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBoardEventFXML.fxml"));
         Parent root = (Parent) loader.load();
        AdminDashBoardEventFXMLController controller = loader.<AdminDashBoardEventFXMLController>getController() ;
        controller.initData(this.user);
        
        
        eventButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToExit() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
        usersBtn.getScene().setRoot(root);
    }
    
   
    
   

    @FXML
    public void navigateToPosts(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBoardBlogFXML.fxml"));
         Parent root = (Parent) loader.load();
        AdminDashBoardBlogFXMLController controller = loader.<AdminDashBoardBlogFXMLController>getController() ;
        controller.initData(this.user);
        blogButton.getScene().setRoot(root);
    }

    @FXML
    public void navigateToApiControll(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBoardApiController.fxml"));
         Parent root = (Parent) loader.load();
        AdminDashBoardApiController controller = loader.<AdminDashBoardApiController>getController() ;
        controller.initData(user);
        
        apiControllButton.getScene().setRoot(root);
    }

    

    @FXML
    public void refreshData(ActionEvent event) {
        populateTable();
    }
    
    
}
