/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.friend;
import Entity.request;
import Utils.Datasourc;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Entity.account;
import static GUI.RegistrationGuiFXMLController.user;
import Service.acountservice;
import Service.friendservice;
import Service.requestservice;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leanbois
 */
public class PublicRoomController implements Initializable {
static String accountus;
static String friendus;
static String requestus;
static String requestuser;
static String requestmsg;
static String allacc;
    @FXML
    private TextArea mttext1;
    @FXML
    private TextArea mttext2;
    @FXML
    private Button mtbutton;
     @FXML
    private TableView<account> mtonline;
    @FXML
    private TableColumn<account, String> mtonlinec;
    @FXML
    private TableView<friend> mtfriend;
    @FXML
    private TableColumn<friend, String> mtfriendc;
    @FXML
    private TableView<request> mtrequest;
    @FXML
    private TableColumn<request, String> mtrequestc;
    @FXML
    private TableView<account> mtallusers;
    @FXML
    private TableColumn<account, String> mtuserc;
    @FXML
    private TextField mtrecherche;

    
    
    
       private static Statement ste;
    Connection con = Datasourc.getInstance().getConnexion();
    @FXML
    private AnchorPane myPane;
    @FXML
    private Button homeButton;
    @FXML
    private Button blogButton;
    @FXML
    private Button eventButton;

    @FXML
    private Button connectionButton;
    @FXML
    private Button storeButton;
   @FXML
    private Button Refresh;
   
    public PublicRoomController() {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }}
        public void pmDisplay(){
        mttext1.setText("");
   RegistrationGuiFXMLController a = new RegistrationGuiFXMLController();
      
        String c = a.user.getUsername();

        String request = "SELECT * FROM public_chat";
        try{
           ResultSet rs = ste.executeQuery(request);
            while(rs.next()){
                String username = rs.getString("username");
                String content = rs.getString("message");
     
               
                String message = username + " says: " + content+"\n";
                          mttext1.appendText(message);
            mttext1.positionCaret(mttext1.getLength());
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RegistrationGuiFXMLController c = new RegistrationGuiFXMLController();
        ////////////////////////////////////////
             acountservice SP = new acountservice();
        ArrayList<account> list;
        try {
            list = SP.afficheracount(c.user.getUsername());
            ObservableList OL = FXCollections.observableArrayList(list);
            mtonline.setItems(OL);
            mtonlinec.setCellValueFactory(new PropertyValueFactory<>("username"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ///////////////////////////////////////////
                   friendservice FR = new friendservice();
        ArrayList<friend> list1;
        try {
            list1 = FR.afficherfriend(c.user.getUsername());
            ObservableList OL = FXCollections.observableArrayList(list1);
            mtfriend.setItems(OL);
            mtfriendc.setCellValueFactory(new PropertyValueFactory<>("friendusername"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ////////////////////////////
                        requestservice RQ = new requestservice();
        ArrayList<request> list2;
        try {
            list2 = RQ.afficherrequest(c.user.getUsername());
            ObservableList OL = FXCollections.observableArrayList(list2);
            mtrequest.setItems(OL);
            mtrequestc.setCellValueFactory(new PropertyValueFactory<>("msg"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        /////////////////////////////
                   acountservice all = new acountservice();
        ArrayList<account> list3;
        try {
            list3 = all.afficherallacount(c.user.getUsername());
            ObservableList OL = FXCollections.observableArrayList(list3);
            mtallusers.setItems(OL);
            mtuserc.setCellValueFactory(new PropertyValueFactory<>("username"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         pmDisplay();                  
    }    

    @FXML
    private void mtsend(ActionEvent event) {
          String msg = mttext2.getText();
     RegistrationGuiFXMLController a = new RegistrationGuiFXMLController();
      
        String c = a.user.getUsername();

        String sql = "INSERT INTO public_chat(username,message)VALUES('" + c + "','" + msg + "')";
        try {
            ste.executeUpdate(sql);
//                msg_area.append(NICK + " says: "+msg +"\n");

        } catch (Exception ex) {
            System.out.println(ex);
        }
        mttext2.setText("");
        pmDisplay();
 
    }

    @FXML
    private void mtonlineaction(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2){
             account c = (account) mtonline.getSelectionModel().getSelectedItem();
             accountus = c.getUsername();
             Stage stage = new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("Gui/PrivateRoom.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("PrivateRoom");
        stage.show();
        }
    }

    @FXML
    private void mtfriendaction(MouseEvent event) throws IOException {
              if (event.getClickCount() == 2){
             friend c = (friend) mtfriend.getSelectionModel().getSelectedItem();
             friendus = c.getFriendusername();
             Stage stage = new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("Gui/PrivateRoom.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("PrivateRoom");
        stage.show();
        }
    }

    @FXML
    private void mtrequestaction(MouseEvent event) throws IOException {
                    if (event.getClickCount() == 2){
             request c = (request) mtrequest.getSelectionModel().getSelectedItem();
             requestus = c.getUser_send();
             requestuser =c.getUser_receive();
             requestmsg =c.getMsg();
             Stage stage = new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("Gui/RequestAcceptance.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Request");
        stage.show();
        }
    }

    @FXML
    private void allusersaction(MouseEvent event) throws IOException {
                      if (event.getClickCount() == 2){
             account c = (account) mtallusers.getSelectionModel().getSelectedItem();
             allacc = c.getUsername();
             Stage stage = new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("Gui/AddFriend.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Add Friend");
        stage.show();
        }
    }

      @FXML
    void search(KeyEvent event) {
        String alpha = mtrecherche.getText();
                   acountservice one = new acountservice();
        ArrayList<account> list4;
        try {
            list4 = one.afficheroneacount(alpha);
            ObservableList OL = FXCollections.observableArrayList(list4);
            mtallusers.setItems(OL);
            mtuserc.setCellValueFactory(new PropertyValueFactory<>("username"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

   @FXML
    private void navigateToBlog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/AffichageArticle.fxml"));
            blogButton.getScene().setRoot(root);
    }
    @FXML
         private void navigateEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/browseEvents.fxml"));
            eventButton.getScene().setRoot(root);
    }      


        
    
    @FXML
    private void navigateToHome(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("Gui/FXML.fxml"));
            homeButton.getScene().setRoot(root);
    }

   

    @FXML
    private void navigateToStore(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Gui/StoreGuiFXML.fxml"));
            storeButton.getScene().setRoot(root);
    }
     @FXML
    void Refreshaction(ActionEvent event) {
  RegistrationGuiFXMLController c = new RegistrationGuiFXMLController();
        ////////////////////////////////////////
             acountservice SP = new acountservice();
        ArrayList<account> list;
        try {
            list = SP.afficheracount(c.user.getUsername());
            ObservableList OL = FXCollections.observableArrayList(list);
            mtonline.setItems(OL);
            mtonlinec.setCellValueFactory(new PropertyValueFactory<>("username"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ///////////////////////////////////////////
                   friendservice FR = new friendservice();
        ArrayList<friend> list1;
        try {
            list1 = FR.afficherfriend(c.user.getUsername());
            ObservableList OL = FXCollections.observableArrayList(list1);
            mtfriend.setItems(OL);
            mtfriendc.setCellValueFactory(new PropertyValueFactory<>("friendusername"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ////////////////////////////
                        requestservice RQ = new requestservice();
        ArrayList<request> list2;
        try {
            list2 = RQ.afficherrequest(c.user.getUsername());
            ObservableList OL = FXCollections.observableArrayList(list2);
            mtrequest.setItems(OL);
            mtrequestc.setCellValueFactory(new PropertyValueFactory<>("msg"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        /////////////////////////////
                   acountservice all = new acountservice();
        ArrayList<account> list3;
        try {
            list3 = all.afficherallacount(c.user.getUsername());
            ObservableList OL = FXCollections.observableArrayList(list3);
            mtallusers.setItems(OL);
            mtuserc.setCellValueFactory(new PropertyValueFactory<>("username"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         pmDisplay();                  
    }    
    }

