/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Evenement;
import Entity.Users;
import Service.ServiceEvenement;
import Utils.Datasourc;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import service.ParticipantService;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 * FXML Controller class
 *
 * @author machd
 */
public class BrowseEventsController implements Initializable, MapComponentInitializedListener {

    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image6;
    @FXML
    private Button next;
    @FXML
    private Button previous;

    private final ServiceEvenement serv = new ServiceEvenement();
    private ArrayList<Evenement> lisOfEvent = serv.read();

    private final int nbP = lisOfEvent.size() / 6 + fct(lisOfEvent.size() % 6);
    private int CurrP = 1;
    private int i;
    private int nbrOfimgInlastPage;
    int x;
    int indexOfImage;
    
    private static Statement ste;
        Connection con=Datasourc.getInstance().getConnexion();
    
        public BrowseEventsController(){
             try{
                ste=con.createStatement();
            }catch(SQLException e){
                System.out.println(e);
            }
        }

    @FXML
    private Label date1;
    @FXML
    private Label date2;
    @FXML
    private Label date3;
    @FXML
    private Label date4;
    @FXML
    private Label date5;
    @FXML
    private Label date6;
    @FXML
    private Button subscribe1;
    @FXML
    private Button subscribe2;
    @FXML
    private Button subsrcribe3;
    @FXML
    private Button sbscribe4;
    @FXML
    private Button subscribe5;
    @FXML
    private Button subscribr6;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;
    @FXML
    private Label NoResult;
    @FXML
    private Label short1;
    @FXML
    private Label short2;
    @FXML
    private Label short3;
    @FXML
    private Label short4;
    @FXML
    private Label short5;
    @FXML
    private Label short6;

    @FXML
    private GoogleMapView mapView;
    @FXML
    private TextField addressTextField;

    private GoogleMap map;

    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
    @FXML
    private AnchorPane myPane;
    @FXML
    private Button homrBtn;
    @FXML
    private Button blogBtn;
    @FXML
    private Button eventBtn;
    @FXML
    private Button cnxBtn;
    RegistrationGuiFXMLController C = new RegistrationGuiFXMLController();
    Users user = C.user;
    //tableau des localisation
    public ArrayList<LatLong> tabLatLong = new ArrayList();
    @FXML
    private Button storBttn;
    @FXML
    private Button Gevent;
    @FXML
    private Button stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Init images
        initImages(lisOfEvent);
        System.out.println(lisOfEvent);
        mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());

        if (user == null) {
            Gevent.setVisible(false);
            isInternaute();            
        }else if(user.getRoles().equals("association")){
            cnxBtn.setText("Profile");
            Gevent.setVisible(true);
        }
        else {
            cnxBtn.setText("Profile");
            Gevent.setVisible(false);
        }

    }

    @FXML
    private void addressTextFieldAction(ActionEvent event) {
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                
                return;
            } else if (results.length > 1) {
               
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }

            map.setCenter(latLong);

        });
    }

    @Override
    public void mapInitialized() {
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

        map = mapView.createMap(mapOptions);

        for (Evenement e : lisOfEvent) {
            getLatLong(e.getLocalisation(), e);
        }

    }

    private void getLatLong(String address, Evenement e) {
        geocodingService.geocode(address, (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;
            if (status == GeocoderStatus.ZERO_RESULTS) {
                
                return;
            } else if (results.length > 1) {
               
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(latLong);
                Marker joeSmithMarker = new Marker(markerOptions1);
                map.addMarker(joeSmithMarker);
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(latLong);
                Marker joeSmithMarker = new Marker(markerOptions1);
                map.addMarker(joeSmithMarker);
//                InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
//                infoWindowOptions.content("<h2>"+e.getShortDescription()+"</h2>"
//                        + "Date:"+e.getDate()+"<br>"
//                        );
//                InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
//                fredWilkeInfoWindow.open(map, joeSmithMarker);
            }
        });
    }

    
    
    
    
    
    
    
    
    
    
    @FXML
    private void goToNext(ActionEvent event) throws SQLException {

        System.out.println("indexOfImage:" + indexOfImage);
        if (lisOfEvent.size() > indexOfImage) {
            loadNextPage();
        }
        System.out.println("**************goToNext page called***************** ");

        System.out.println("indexOfImage:" + indexOfImage);

    }

    @FXML
    private void goToPrevious(ActionEvent event) {
        if (CurrP >= 1) {

            System.out.println("indexOfImage :" + indexOfImage);
            loadPreviousPage();
            System.out.println("**************goToPrevious page called***************** ");

            System.out.println("indexOfImage :" + indexOfImage);
        }

    }

    public void initImages(ArrayList<Evenement> l) {
        setVesibilityToFalse();
        if (l.size() > 0) {
            image1.setVisible(true);
            date1.setVisible(true);
            short1.setVisible(true);
            subscribe1.setVisible(true);
            loadImage1(l.get(0).getImg_url());
            loadDate1(l.get(0).getDate());
            loadShort1(l.get(0).getShortDescription());
            indexOfImage++;
        }

        if (l.size() > 1) {
            image2.setVisible(true);
            date2.setVisible(true);
            short2.setVisible(true);
            subscribe2.setVisible(true);
            loadImage2(l.get(1).getImg_url());
            loadDate2(l.get(1).getDate());
            loadShort2(l.get(1).getShortDescription());
            indexOfImage++;
        }

        if (l.size() > 2) {

            image3.setVisible(true);
            date3.setVisible(true);
            short3.setVisible(true);
            subsrcribe3.setVisible(true);
            loadImage3(l.get(2).getImg_url());
            loadDate3(l.get(2).getDate());
            loadShort3(l.get(2).getShortDescription());
            indexOfImage++;
        }

        if (l.size() > 3) {
            image4.setVisible(true);
            date4.setVisible(true);
            short4.setVisible(true);
            sbscribe4.setVisible(true);
            loadImage4(l.get(3).getImg_url());
            loadDate4(l.get(3).getDate());
            loadShort4(l.get(3).getShortDescription());
            indexOfImage++;
        }

        if (l.size() > 4) {
            image5.setVisible(true);
            date5.setVisible(true);
            short5.setVisible(true);
            subscribe5.setVisible(true);
            loadImage5(l.get(4).getImg_url());
            loadDate5(l.get(4).getDate());
            loadShort5(l.get(4).getShortDescription());
            indexOfImage++;
        }

        if (l.size() > 5) {
            image6.setVisible(true);
            date6.setVisible(true);
            short6.setVisible(true);
            subscribr6.setVisible(true);
            loadImage6(l.get(5).getImg_url());
            loadDate6(l.get(5).getDate());
            loadShort6(l.get(5).getShortDescription());
            indexOfImage++;
        }

    }

    public void intImageToEmpty() {
        Image image = new Image("Images/empty-image.jpg");
        image1.setImage(image);
        image2.setImage(image);
        image3.setImage(image);
        image4.setImage(image);
        image5.setImage(image);
        image6.setImage(image);

        date1.setText("");
        date2.setText("");
        date3.setText("");
        date4.setText("");
        date5.setText("");
        date6.setText("");

        short1.setText("");
        short2.setText("");
        short3.setText("");
        short4.setText("");
        short5.setText("");
        short6.setText("");

    }

    public void loadImage1(String url) {
        Image img1 = new Image(url);
        image1.setImage(img1);

    }

    public void loadShort1(String s) {
        short1.setText(s);
    }

    public void loadDate1(Date date) {
        date1.setText("Date :" + date.toString());
    }

    public void loadImage2(String url) {
        Image img = new Image(url);
        image2.setImage(img);
    }

    public void loadDate2(Date date) {
        date2.setText("Date :" + date.toString());
    }

    public void loadShort2(String s) {
        short2.setText(s);
    }

    public void loadImage3(String url) {
        Image img = new Image(url);
        image3.setImage(img);
    }

    public void loadDate3(Date date) {
        date3.setText("Date :" + date.toString());
    }

    public void loadShort3(String s) {
        short3.setText(s);
    }

    public void loadImage4(String url) {
        Image img = new Image(url);
        image4.setImage(img);
    }

    public void loadDate4(Date date) {
        date4.setText("Date :" + date.toString());
    }

    public void loadShort4(String s) {
        short4.setText(s);
    }

    public void loadImage5(String url) {
        Image img = new Image(url);
        image5.setImage(img);
    }

    public void loadDate5(Date date) {
        date5.setText("Date :" + date.toString());
    }

    public void loadShort5(String s) {
        short5.setText(s);
    }

    public void loadImage6(String url) {
        Image img = new Image(url);
        image6.setImage(img);
    }

    public void loadDate6(Date date) {
        date6.setText("Date :" + date.toString());
    }

    public void loadShort6(String s) {
        short6.setText(s);
    }

    public void loadNextPage() throws SQLException {

        setVesibilityToTrue();
        if (CurrP < nbP - 1) {
            indexOfImage = CurrP * 6;

            loadImage1(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate1(lisOfEvent.get(indexOfImage).getDate());
            loadShort1(lisOfEvent.get(indexOfImage).getShortDescription());
            indexOfImage++;
            System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");
            //System.out.println("  i: " + i);
            loadImage2(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate2(lisOfEvent.get(indexOfImage).getDate());
            loadShort2(lisOfEvent.get(indexOfImage).getShortDescription());
            indexOfImage++;
            System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");
            loadImage3(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate3(lisOfEvent.get(indexOfImage).getDate());
            loadShort3(lisOfEvent.get(indexOfImage).getShortDescription());
            indexOfImage++;
            System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");
            loadImage4(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate4(lisOfEvent.get(indexOfImage).getDate());
            loadShort4(lisOfEvent.get(indexOfImage).getShortDescription());
            indexOfImage++;
            System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");
            loadImage5(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate5(lisOfEvent.get(indexOfImage).getDate());
            loadShort5(lisOfEvent.get(indexOfImage).getShortDescription());
            indexOfImage++;
            System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");
            loadImage6(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate6(lisOfEvent.get(indexOfImage).getDate());
            loadShort6(lisOfEvent.get(indexOfImage).getShortDescription());
            indexOfImage++;
            CurrP++;
            System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");
        } else if (CurrP < nbP) {
            i = 0;
            intImageToEmpty();
            if (indexOfImage < lisOfEvent.size()) {
                loadImage1(lisOfEvent.get(indexOfImage).getImg_url());
                loadDate1(lisOfEvent.get(indexOfImage).getDate());
                loadShort1(lisOfEvent.get(indexOfImage).getShortDescription());
                indexOfImage++;
                nbrOfimgInlastPage++;
                System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");
            } else {
                image1.setVisible(false);
                date1.setVisible(false);
                short1.setVisible(false);
                subscribe1.setVisible(false);

            }
            if (indexOfImage < lisOfEvent.size()) {
                loadImage2(lisOfEvent.get(indexOfImage).getImg_url());
                loadDate2(lisOfEvent.get(indexOfImage).getDate());
                loadShort2(lisOfEvent.get(indexOfImage).getShortDescription());
                indexOfImage++;
                System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");

            } else {
                image2.setVisible(false);
                date2.setVisible(false);
                short2.setVisible(false);
                subscribe2.setVisible(false);
            }
            if (indexOfImage < lisOfEvent.size()) {
                loadImage3(lisOfEvent.get(indexOfImage).getImg_url());
                loadDate3(lisOfEvent.get(indexOfImage).getDate());
                loadShort3(lisOfEvent.get(indexOfImage).getShortDescription());
                indexOfImage++;
                System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");

            } else {
                image3.setVisible(false);
                date3.setVisible(false);
                short3.setVisible(false);
                subsrcribe3.setVisible(false);
            }
            if (indexOfImage < lisOfEvent.size()) {
                loadImage4(lisOfEvent.get(indexOfImage).getImg_url());
                loadDate4(lisOfEvent.get(indexOfImage).getDate());
                loadShort4(lisOfEvent.get(indexOfImage).getShortDescription());
                indexOfImage++;
                System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");
            } else {
                image4.setVisible(false);
                date4.setVisible(false);
                short4.setVisible(false);
                sbscribe4.setVisible(false);
            }
            if (indexOfImage < lisOfEvent.size()) {
                loadImage5(lisOfEvent.get(indexOfImage).getImg_url());
                loadDate5(lisOfEvent.get(indexOfImage).getDate());
                loadShort5(lisOfEvent.get(indexOfImage).getShortDescription());

                indexOfImage++;
                System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");
            } else {
                image5.setVisible(false);
                date5.setVisible(false);
                short5.setVisible(false);
                subscribe5.setVisible(false);
            }
            if (indexOfImage < lisOfEvent.size()) {
                loadImage6(lisOfEvent.get(indexOfImage).getImg_url());
                loadDate6(lisOfEvent.get(indexOfImage).getDate());
                loadShort6(lisOfEvent.get(indexOfImage).getShortDescription());
                indexOfImage++;
                System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: ");
            } else {
                image6.setVisible(false);
                date6.setVisible(false);
                short6.setVisible(false);
                subscribr6.setVisible(false);
            }
            //CurrP++;

//            CurrP = 0;
//            i = 0;
        }

    }

    public void setVesibilityToTrue() {
        image1.setVisible(true);
        date1.setVisible(true);
        short1.setVisible(true);
        subscribe1.setVisible(true);

        image2.setVisible(true);
        date2.setVisible(true);
        short2.setVisible(true);
        subscribe2.setVisible(true);

        image3.setVisible(true);
        date3.setVisible(true);
        short3.setVisible(true);
        subsrcribe3.setVisible(true);

        image4.setVisible(true);
        date4.setVisible(true);
        short4.setVisible(true);
        sbscribe4.setVisible(true);

        image5.setVisible(true);
        date5.setVisible(true);
        short5.setVisible(true);
        subscribe5.setVisible(true);

        image6.setVisible(true);
        date6.setVisible(true);
        short6.setVisible(true);
        subscribr6.setVisible(true);

    }

    public void setVesibilityToFalse() {
        image1.setVisible(false);
        date1.setVisible(false);
        short1.setVisible(false);
        subscribe1.setVisible(false);

        image2.setVisible(false);
        date2.setVisible(false);
        short2.setVisible(false);
        subscribe2.setVisible(false);

        image3.setVisible(false);
        date3.setVisible(false);
        short3.setVisible(false);
        subsrcribe3.setVisible(false);

        image4.setVisible(false);
        date4.setVisible(false);
        short4.setVisible(false);
        sbscribe4.setVisible(false);

        image5.setVisible(false);
        date5.setVisible(false);
        short5.setVisible(false);
        subscribe5.setVisible(false);

        image6.setVisible(false);
        date6.setVisible(false);
        short6.setVisible(false);
        subscribr6.setVisible(false);

    }

    public void isInternaute() {
        subscribe1.setDisable(true);
        subscribe2.setDisable(true);
        subsrcribe3.setDisable(true);
        sbscribe4.setDisable(true);
        subscribe5.setDisable(true);
        subscribr6.setDisable(true);

    }

    public void loadPreviousPage() {

//           System.out.println("##########");
//           System.out.println("index :"+i);
//           System.out.println("##########");
        CurrP--;
        setVesibilityToTrue();

        if (CurrP < nbP) {
            i = 0;
            indexOfImage = CurrP * 6 + i;
            loadImage1(lisOfEvent.get(x).getImg_url());
            loadDate1(lisOfEvent.get(indexOfImage + i).getDate());
            loadShort1(lisOfEvent.get(indexOfImage).getShortDescription());
            i++;
            x = CurrP * 6 + i;
            System.out.println("  i: " + indexOfImage + "Curr: " + CurrP + "i in page: " + x);

            loadImage1(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate1(lisOfEvent.get(indexOfImage).getDate());
            loadShort1(lisOfEvent.get(indexOfImage).getShortDescription());
            indexOfImage++;

            loadImage2(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate2(lisOfEvent.get(indexOfImage).getDate());
            loadShort2(lisOfEvent.get(indexOfImage).getShortDescription());
            indexOfImage++;
            loadImage3(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate3(lisOfEvent.get(indexOfImage).getDate());
            loadShort3(lisOfEvent.get(indexOfImage).getShortDescription());
            indexOfImage++;
            loadImage4(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate4(lisOfEvent.get(indexOfImage).getDate());
            loadShort4(lisOfEvent.get(indexOfImage).getShortDescription());
            indexOfImage++;
            loadImage5(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate5(lisOfEvent.get(indexOfImage).getDate());
            loadShort5(lisOfEvent.get(indexOfImage).getShortDescription());

            indexOfImage++;
            loadImage6(lisOfEvent.get(indexOfImage).getImg_url());
            loadDate6(lisOfEvent.get(indexOfImage).getDate());
            loadShort6(lisOfEvent.get(indexOfImage).getShortDescription());
            indexOfImage++;
        }
//        indexOfImage -= 5;
//           IntStream.range(0, 1).forEach(
//                i -> next.fire()
//        );  

//            CurrP = 0;
//            i = 0;
    }

    public int fct(int i) {
        if (i != 0) {
            return 1;
        } else {
            return i;
        }
    }

    @FXML
    private void subscribeButton1(ActionEvent event) {
        try {
            int I = indexOfImage - 6;
            ParticipantService serv = new ParticipantService();
            System.out.println("++++++++++++______++++++++++______");
            System.out.println("eventID: " + lisOfEvent.get(indexOfImage).getId());
            System.out.println("index :" + I);
            System.out.println("++++++++++++______++++++++++______");

            serv.add(user.getId(), lisOfEvent.get(I).getId());

        } catch (SQLException ex) {

        }

    }

    @FXML
    private void subscribeButton2(ActionEvent event) {
        try {

            int I = indexOfImage - 5;
            ParticipantService serv = new ParticipantService();
            serv.add(user.getId(), lisOfEvent.get(I).getId());
//            System.out.println("eventID: "+lisOfEvent.get(indexOfImage).getId());
//            System.out.println("index :"+I);
        } catch (SQLException ex) {

        }

    }

    @FXML
    private void subscribeButton3(ActionEvent event) {
        try {
            int I = indexOfImage - 4;
            ParticipantService serv = new ParticipantService();
            serv.add(user.getId(), lisOfEvent.get(I).getId());
//            System.out.println("eventID: "+lisOfEvent.get(indexOfImage).getId());
//            System.out.println("index :"+I);
        } catch (SQLException ex) {

        }

    }

    @FXML
    private void subscribeButton4(ActionEvent event) {
        try {

            int I = indexOfImage - 3;
            ParticipantService serv = new ParticipantService();
            serv.add(user.getId(), lisOfEvent.get(I).getId());
//             System.out.println("eventID: "+lisOfEvent.get(indexOfImage).getId());
//            System.out.println("index :"+I);
        } catch (SQLException ex) {

        }
    }

    @FXML
    private void subscribeButton5(ActionEvent event) {
        try {

            int I = indexOfImage - 2;
            ParticipantService serv = new ParticipantService();
            serv.add(user.getId(), lisOfEvent.get(I).getId());
//             System.out.println("eventID: "+lisOfEvent.get(indexOfImage).getId());
//            System.out.println("index :"+I);
        } catch (SQLException ex) {

        }
    }

    @FXML
    private void subscribeButton6(ActionEvent event) {
        try {

            int I = indexOfImage - 1;
            ParticipantService serv = new ParticipantService();
            serv.add(user.getId(), lisOfEvent.get(I).getId());
//            System.out.println("eventID: "+lisOfEvent.get(indexOfImage).getId());
//            System.out.println("index :"+I);
        } catch (SQLException ex) {

        }
    }

    @FXML
    private void searchEvents(ActionEvent event) {
        ArrayList<Evenement> list = new ArrayList<>();
        ArrayList<Evenement> topResults = new ArrayList<>();
        ServiceEvenement serE;
        try {
            serE = new ServiceEvenement();
            list = serE.read();
            topResults = search(list, searchField.getText());
            if (topResults.size() != 0) {
                
                CurrP=1;
                NoResult.setText("");
                lisOfEvent.clear();
                lisOfEvent.addAll(topResults);
                System.out.println(lisOfEvent);
                intImageToEmpty();
                initImages(topResults);
                indexOfImage = 6;
                
            } else {
                NoResult.setText("no result maches your search");
            }
        } catch (Exception e) {

        }

    }

    //search methods
    public ArrayList<Integer> SearchInshort(Evenement e, String Input) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;

        String[] S = Input.split(" ");
        String[] E = e.getShortDescription().split(" ");
        for (int i = 0; i < S.length; i++) {
            for (String evSho : E) {
                if (evSho.toUpperCase().equals(S[i].toUpperCase())) {
                    if (i == 0) {
                        count1++;
                    }
                    if (i == 1) {
                        count2++;
                    }
                    if (i == 2) {
                        count3++;
                    }
                    if (i == 3) {
                        count4++;
                    }

                }
            }
        }
        ArrayList<Integer> tab = new ArrayList<>();
        tab.add(count1);
        tab.add(count2);
        tab.add(count3);
        tab.add(count4);

        return tab;
    }

    // this meth call'the last one
    //and appy it to a list of events 
    public ArrayList<Evenement> search(ArrayList<Evenement> tab, String s) {
        Evenement tampon = new Evenement();
        int tampInt = 0;

        ArrayList<Integer> tabScore = new ArrayList<>();
        ArrayList<Evenement> tamponTab = new ArrayList<>();

        if (!s.equals("")) {

            for (Evenement ev : tab) {
                ArrayList<Integer> Occurence = SearchInshort(ev, s);
                //calculate score 
                int score2 = 0;
                int score = 0;
                for (int i = 0; i < 4; i++) {
                    if (Occurence.get(i) != 0) {
                        score++;
                        score2 += Occurence.get(i);
                        System.out.println("in the if :" + score2);
                    }
                }

                if (score != 0) {
                    score = score * 100 + score2;
//                System.out.println("score :" + score);
//                tabScore.add(score);
                    System.out.println("score :" + score);
                    tabScore.add(score);
//                    System.out.println(ev.toString());
                    tamponTab.add(ev);
//                 System.out.println(" size Tompon"+tamponTab.size());
                }
            }

            for (int i = tabScore.size() - 1; i > 0; i--) {
                for (int j = 1; j <= i; j++) {
                    if (tabScore.get(j - 1) < tabScore.get(j)) {
                        tampInt = tabScore.get(j - 1);
                        tabScore.set(j - 1, tabScore.get(j));
                        tabScore.set(j, tampInt);

                        tampon = tamponTab.get(j - 1);
                        tamponTab.set(j - 1, tamponTab.get(j));
                        tamponTab.set(j, tampon);
                    }
                }
            }
            return tamponTab;
        }
        return new ArrayList<Evenement>();

    }

    @FXML
    private void searchIsEmpty(KeyEvent event) {
        if (searchField.getText().equals("")) {
            ServiceEvenement serE = new ServiceEvenement();
            lisOfEvent = serE.read();
            initImages(lisOfEvent);
            CurrP = 1;
            i = 0;
            nbrOfimgInlastPage = 0;
            x = 0;
            indexOfImage = 6;
        }
    }

   

    @FXML
    private void centerMap1(MouseEvent event) {
        
        Evenement e =lisOfEvent.get(indexOfImage -6 );
        geocodingService.geocode(e.getLocalisation(), (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;
            if (status == GeocoderStatus.ZERO_RESULTS) {
                
                return;
            } else if (results.length > 1) {
                
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
               map.setCenter(latLong);
               map.setZoom(12);
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(latLong);
                Marker joeSmithMarker = new Marker(markerOptions1);
                map.addMarker(joeSmithMarker);
               InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                infoWindowOptions.content("<h2>"+e.getShortDescription()+"</h2>"
                        + "Description:"+e.getLongDescription()+"<br>"
                        );
                InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
                fredWilkeInfoWindow.open(map, joeSmithMarker);
            }
        });
        
    }

    @FXML
    private void centerMap4(MouseEvent event) {
        Evenement e =lisOfEvent.get(indexOfImage -3 );
        geocodingService.geocode(e.getLocalisation(), (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;
            if (status == GeocoderStatus.ZERO_RESULTS) {
                
                return;
            } else if (results.length > 1) {
               
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
               map.setCenter(latLong);
               map.setZoom(12);
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(latLong);
                Marker joeSmithMarker = new Marker(markerOptions1);
                map.addMarker(joeSmithMarker);
               InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                infoWindowOptions.content("<h2>"+e.getShortDescription()+"</h2>"
                        + "Description:"+e.getLongDescription()+"<br>"
                        );
                InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
                fredWilkeInfoWindow.open(map, joeSmithMarker);
            }
        });
    }

    @FXML
    private void centerMap2(MouseEvent event) {
         Evenement e =lisOfEvent.get(indexOfImage -5 );
        geocodingService.geocode(e.getLocalisation(), (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;
            if (status == GeocoderStatus.ZERO_RESULTS) {
               
                return;
            } else if (results.length > 1) {
                
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
               map.setCenter(latLong);
               map.setZoom(12);
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(latLong);
                Marker joeSmithMarker = new Marker(markerOptions1);
                map.addMarker(joeSmithMarker);
               InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                infoWindowOptions.content("<h2>"+e.getShortDescription()+"</h2>"
                        + "Description:"+e.getLongDescription()+"<br>"
                        );
                InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
                fredWilkeInfoWindow.open(map, joeSmithMarker);
            }
        });
    }

    @FXML
    private void centerMap5(MouseEvent event) {
        Evenement e =lisOfEvent.get(indexOfImage -2 );
        geocodingService.geocode(e.getLocalisation(), (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;
            if (status == GeocoderStatus.ZERO_RESULTS) {
                
                return;
            } else if (results.length > 1) {
                
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
               map.setCenter(latLong);
               map.setZoom(12);
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(latLong);
                Marker joeSmithMarker = new Marker(markerOptions1);
                map.addMarker(joeSmithMarker);
               InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                infoWindowOptions.content("<h2>"+e.getShortDescription()+"</h2>"
                        + "Description:"+e.getLongDescription()+"<br>"
                        );
                InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
                fredWilkeInfoWindow.open(map, joeSmithMarker);
            }
        });
    }

    @FXML
    private void centerMap3(MouseEvent event) {
         Evenement e =lisOfEvent.get(indexOfImage -4 );
        geocodingService.geocode(e.getLocalisation(), (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;
            if (status == GeocoderStatus.ZERO_RESULTS) {
               
                return;
            } else if (results.length > 1) {
               
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
               map.setCenter(latLong);
               map.setZoom(12);
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(latLong);
                Marker joeSmithMarker = new Marker(markerOptions1);
                map.addMarker(joeSmithMarker);
               InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                infoWindowOptions.content("<h2>"+e.getShortDescription()+"</h2>"
                        + "Description:"+e.getLongDescription()+"<br>"
                        );
                InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
                fredWilkeInfoWindow.open(map, joeSmithMarker);
            }
        });
    }

    @FXML
    private void centerMap6(MouseEvent event) {
       Evenement e =lisOfEvent.get(indexOfImage -1 );
        geocodingService.geocode(e.getLocalisation(), (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;
            if (status == GeocoderStatus.ZERO_RESULTS) {
               
                return;
            } else if (results.length > 1) {
                
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
               map.setCenter(latLong);
               map.setZoom(12);
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(latLong);
                Marker joeSmithMarker = new Marker(markerOptions1);
                map.addMarker(joeSmithMarker);
               InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                infoWindowOptions.content("<h2>"+e.getShortDescription()+"</h2>"
                        + "Description:"+e.getLongDescription()+"<br>"
                        );
                InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
                fredWilkeInfoWindow.open(map, joeSmithMarker);
            }
        });
    }
     @FXML
    private void navigateBlog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/AffichageArticle.fxml"));
            blogBtn.getScene().setRoot(root);
    }

    @FXML
    private void navigateConnexion(ActionEvent event) throws IOException {
        if(user == null){
            Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
            cnxBtn.getScene().setRoot(root);
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("ProfilUserFXML.fxml"));
            cnxBtn.getScene().setRoot(root);
            cnxBtn.setText("   Profil");
        }
        
    }
    @FXML
    private void navigatHome(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("Gui/FXML.fxml"));
            homrBtn.getScene().setRoot(root);
    }

   

    @FXML
    private void navigatsStore(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Gui/StoreGuiFXML.fxml"));
            storBttn.getScene().setRoot(root);
    }

    @FXML
    private void goToGevent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/EventsGuiFXML.fxml"));
            storBttn.getScene().setRoot(root);
    }

    @FXML
    public void goToStat(ActionEvent event) {
        
        try {
            final String SQL = "    SELECT event_id , COUNT(user_id) FROM participant GROUP BY event_id";
            final CategoryDataset dataset = new JDBCCategoryDataset(con, SQL);
            
            
            
            
            JFreeChart chart = ChartFactory.createBarChart3D("Events", "axe x", "axe y", dataset, PlotOrientation.VERTICAL, true, true, true);
            ChartFrame frame = new ChartFrame("Events",chart);
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(BrowseEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
