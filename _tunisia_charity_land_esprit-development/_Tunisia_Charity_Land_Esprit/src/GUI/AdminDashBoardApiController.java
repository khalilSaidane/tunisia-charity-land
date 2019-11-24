/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Users;
import GUI.Gui.AdminDashBordFXMLController;
import Service.SendMailSSL;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
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
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author oflcad
 */
public class AdminDashBoardApiController implements Initializable {

    private Users user;
    
    @FXML
    private Button profileButton;
    @FXML
    private Button usersButton;
    @FXML
    private Button blogButton;
    @FXML
    private Button eventButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button apiControllButton;
    @FXML
    private Circle mailingSignal;
    @FXML
    private Button mailApiTestButton;
    @FXML
    private Button mapApiTestButton;
    @FXML
    private Circle MapSignal;
    @FXML
    private GoogleMapView mapView;
    private GoogleMap map;
    private GeocodingService geocodingService;
    @FXML
    private TextField mailFieldTest;
    @FXML
    private TextField mapFieldTest;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         profileButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            usersButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            blogButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            eventButton.setStyle("-fx-text-fill: white;-fx-background-color: transparent;");
            apiControllButton.setStyle("-fx-text-fill: #F25652;-fx-background-color: transparent; ");
            logoutButton.setStyle("-fx-text-fill: white ;-fx-background-color: transparent;");
        // TODO
    }    
    
    
    @FXML 
    public void testMailingAPi() throws SQLException {
        mailFieldTest.setText("Pinging google Mail API waitaing for RESPONSE 200!");
            mailFieldTest.setStyle("-fx-text-fill: green; -fx-background-color: transparent");
            mailingSignal.setStyle("-fx-fill: #fbc531");
        SendMailSSL mailingService = new SendMailSSL();
        boolean feedback = mailingService.adminApiTest();
        if(feedback){
            mailFieldTest.setText("Pinging google Mail API result RESPONSE 200!");
            mailFieldTest.setStyle("-fx-text-fill: green; -fx-background-color: transparent");
            mailingSignal.setStyle("-fx-fill: #27ae60");
        }
    }
    
    @FXML
    public void navigateToProfile(ActionEvent event) throws IOException{
       FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBordFXML.fxml"));
        Parent root = (Parent) loader.load();
        AdminDashBordFXMLController controller = loader.<AdminDashBordFXMLController>getController() ;
        controller.initData(this.user);
        
        profileButton.getScene().setRoot(root);
    }
    @FXML
    public void navigateToUsers(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBoardUserFXML.fxml"));
        Parent root =  (Parent) loader.load();
        AdminDashBoardUserFXMLController controller = loader.<AdminDashBoardUserFXMLController>getController();
        controller.initData(user);
        
        usersButton.getScene().setRoot(root);
    }
    
   
    
    @FXML
    public void navigateToEvent(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBoardEventFXML.fxml"));
         Parent root = (Parent) loader.load();
        AdminDashBoardEventFXMLController controller = loader.<AdminDashBoardEventFXMLController>getController() ;
        controller.initData(this.user);
        
        
        eventButton.getScene().setRoot(root);
    }
    
 
   
    public void navigateToLogout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
        logoutButton.getScene().setRoot(root);
    }
    
    public void initData(Users user){
        this.user = user;
    }

    @FXML
    public void navigateToPosts(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/AdminDashBoardBlogFXML.fxml"));
         Parent root = (Parent) loader.load();
        AdminDashBoardBlogFXMLController controller = loader.<AdminDashBoardBlogFXMLController>getController();
        controller.initData(this.user);
        blogButton.getScene().setRoot(root);
    }

    @FXML
    public void navigateToExit(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
        logoutButton.getScene().setRoot(root);
    }

    @FXML
    public void testMapAPi(ActionEvent event) {
        mapFieldTest.setText("Pinging google API waiting for RESPONSE 200!");
        mapFieldTest.setStyle("-fx-text-filll: yellow; -fx-background-color: transparent");
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(33.8869, 9.5375))
                .mapType(MapTypeIdEnum.HYBRID)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(5);

        mapFieldTest.setText("Pinging google API result RESPONSE 200!");
        mapFieldTest.setStyle("-fx-text-fill: green; -fx-background-color: transparent");
        
        map = mapView.createMap(mapOptions);
    }

   

    
}
