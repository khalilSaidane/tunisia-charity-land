/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Users;
import GUI.Gui.AdminDashBordFXMLController;
import Service.AdminDashBoardService;
import Service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author oflcad
 */
public class AdminDashBoardUserFXMLController implements Initializable {

    @FXML
    private AnchorPane myPane;
   
    @FXML
    private Button blogButton;
   
    private Users user;
    @FXML
    private TableView<Users> userTable;
    @FXML
    private Button contactUser;
    @FXML
    private Button deleteSelectedUser;
    @FXML
    private Button refreshButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button usersButton;
    @FXML
    private Button eventButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button apiControllButton;
    @FXML
    private TableColumn<Users, Integer> idColumn;
    @FXML
    private TableColumn<Users, String> usernameColumn;
    @FXML
    private TableColumn<Users, String> emailColumn;
    @FXML
    private TableColumn<Users, Integer> enabledColumn;
    @FXML
    private TableColumn<Users, Date> lastLoginColumn;
    @FXML
    private TableColumn<Users, Integer> lockedColumn;
    @FXML
    private TableColumn<Users, Date> expiredColumn;
    
    private ArrayList<Users> usersList;
    private String selectedUser;
    @FXML
    private TextField messageField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            profileButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            usersButton.setStyle("-fx-text-fill: #F25652;-fx-background-color: transparent;");
            blogButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            eventButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            apiControllButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent; ");
            logoutButton.setStyle("-fx-text-fill: white ;-fx-background-color: transparent;");
            
        try {
            AdminDashBoardService service = new AdminDashBoardService();
            usersList = service.getAllUsersFromDatabase();
            ObservableList observableList = FXCollections.observableList(usersList);
            userTable.setItems(observableList);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            enabledColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
            lastLoginColumn.setCellValueFactory(new PropertyValueFactory<>("last_login"));
            //lockedColumn.setCellFactory(new PropertyValueFactory<>("locked"));
            expiredColumn.setCellValueFactory(new PropertyValueFactory<>("credentials_expires_at"));
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashBoardUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }    
    
    public void initData(Users user){
        this.user = user;
        System.out.println(user.toString());
    }
    
     public void navigateToContactMail(ActionEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBoardMailContactFXML.fxml"));
         Parent root = (Parent) loader.load();
         AdminDashBoardMailContactFXMLController controller = loader.<AdminDashBoardMailContactFXMLController>getController();
         controller.intiEmail(selectedUser);
         contactUser.getScene().setRoot(root);
     }
     
    
    @FXML
    public void navigateToProfile() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBordFXML.fxml"));
        Parent root = (Parent) loader.load();
        AdminDashBordFXMLController controller = loader.<AdminDashBordFXMLController>getController() ;
        controller.initData(this.user);
        
        profileButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToPosts() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBoardBlogFXML.fxml"));
         Parent root = (Parent) loader.load();
        AdminDashBoardBlogFXMLController controller = loader.<AdminDashBoardBlogFXMLController>getController() ;
        controller.initData(this.user);
        blogButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToEvents() throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBoardEventFXML.fxml"));
         Parent root = (Parent) loader.load();
        AdminDashBoardEventFXMLController controller = loader.<AdminDashBoardEventFXMLController>getController() ;
        controller.initData(this.user);
        eventButton.getScene().setRoot(root);
    } 
    
    @FXML
    public void navigateToExit() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
        logoutButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToApiControll(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBoardApiController.fxml"));
         Parent root = (Parent) loader.load();
        AdminDashBoardApiController controller = loader.<AdminDashBoardApiController>getController() ;
        controller.initData(user);
        apiControllButton.getScene().setRoot(root);
    }
    
    @FXML
    public void refreshData(ActionEvent event) throws IOException {
          System.out.println("hello from refresh");
            
        try {
            AdminDashBoardService service = new AdminDashBoardService();
            usersList = service.getAllUsersFromDatabase();
            ObservableList observableList = FXCollections.observableList(usersList);
            userTable.setItems(observableList);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            enabledColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
            lastLoginColumn.setCellValueFactory(new PropertyValueFactory<>("last_login"));
            lockedColumn.setCellValueFactory(new PropertyValueFactory<>("locked"));
            expiredColumn.setCellValueFactory(new PropertyValueFactory<>("credentials_expires_at"));
            messageField.setText("Table has been refreshed");
            messageField.setStyle("-fx-text-fill:green; -fx-background-color:transparent;");
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashBoardUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    @FXML
    public void DeleteSelectedUser(ActionEvent event) throws IOException, SQLException {
        Users selectedUser = userTable.getSelectionModel().getSelectedItem();
        if(selectedUser.equals(null)){
            messageField.setText("Please select a user to contact");
            messageField.setStyle("-fx-text-fill:red;");
        }
        int selectedId = selectedUser.getId();
        ServiceUser userService = new ServiceUser();
        userService.supprimerUser(selectedId);
        populateTabel();
         messageField.setText("user has been deleted");
         messageField.setStyle("-fx-text-fill:green; -fx-background-color:transparent;");
    }
    
    public void populateTabel() {
        try {
            AdminDashBoardService service = new AdminDashBoardService();
            usersList = service.getAllUsersFromDatabase();
            ObservableList observableList = FXCollections.observableList(usersList);
            userTable.setItems(observableList);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            enabledColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
            lastLoginColumn.setCellValueFactory(new PropertyValueFactory<>("last_login"));
            lockedColumn.setCellValueFactory(new PropertyValueFactory<>("locked"));
            expiredColumn.setCellValueFactory(new PropertyValueFactory<>("credentials_expires_at"));
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashBoardUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
      
}
