/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
 
import Entity.Evenement;
import Entity.Users;

import Service.ServiceEvenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
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

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;



/**
 * FXML Controller class
 *
 * @author machd
 */
public class CrudEventFXMLController implements Initializable {

    
    @FXML
    private TableView<Evenement> evnentsVIew;
    @FXML
    private TableColumn<Evenement, Integer> eventId;
    @FXML
    private TableColumn<Evenement, String> brieveDescription;
    @FXML
    private TableColumn<Evenement, String> eventLocation;
    @FXML
    private TableColumn<Evenement, Date> eventDate;
    @FXML
    private ImageView eventImage;
    @FXML
    private Label createdAt;
    @FXML
    private Label modifiedAt;
    @FXML
    private Button addEvent;
    @FXML
    private TextField smartSearch;
    @FXML
    private Button SearchButton;
    private TextField delegue;
    @FXML
    private TextField img;
    @FXML
    private TextField localisation;
    @FXML
    private TextField Short;
    @FXML
    private TextField Longe;
    @FXML
    private ComboBox<String> type;
    ObservableList<String> listType = FXCollections.observableArrayList("ENVIRONMENTAL", "SANTE", "EDUCATION", "INTERNATIONAL");
    @FXML
    private DatePicker date;
    @FXML
    private TableColumn<Evenement, String> Cimage;
    @FXML
    private TableColumn<Evenement, Integer> Cvalidation;
    @FXML
    private Label noResult;
    ServiceEvenement ser = new ServiceEvenement();
    ArrayList<Evenement> Table = ser.read();
    ArrayList<String> listOfShortDescriptions = new ArrayList();
    String[] possibleWords={};
    Set<String> hashSet = new HashSet();
    RegistrationGuiFXMLController C = new RegistrationGuiFXMLController();
    Users user = C.user;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
        
        
        //initialisation de combobox types
        type.setPromptText("choisir un type");
        type.setItems(listType);

        //initialisation des radio buttons
        

        try {
            //initialisation du tableau
            loadTable();
            evnentsVIew.getSelectionModel().selectFirst();
        Evenement selectedEvent = evnentsVIew.getSelectionModel().getSelectedItem();

        evnentsVIew.setEditable(true);
        brieveDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        Cimage.setCellFactory(TextFieldTableCell.forTableColumn());
//           Cvalidation.setCellFactory(TextFieldTableCell.forTableColumn());

        if (selectedEvent != null) {

            // initialisation des images
            evnentsVIew.getSelectionModel().selectFirst();
            Image image = new Image(selectedEvent.getImg_url());
            eventImage.setImage(image);

            // intialisation des label date de creation et date de modification 
            createdAt.setText("Créé à: " + selectedEvent.getCreated_at().toString());
            modifiedAt.setText("Dernière modification à: " + selectedEvent.getModified_at().toString());
        }

            
        } catch (SQLException ex) {
            Logger.getLogger(CrudEventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @FXML
    private void ajouterEvenement(ActionEvent event) throws SQLException {
        if ( img.getText() != "" && localisation.getText() != ""
                && Longe.getText() != "" && Short.getText() != "" && date.getValue() != null) {
            int dId = user.getId();
            String image = img.getText();
            String loc = localisation.getText();
            String longe = Longe.getText();
            String shor = Short.getText();
            LocalDate _date = date.getValue();
            Date dat = Date.valueOf(_date);

            Evenement.Type _type = Evenement.Type.valueOf(type.getValue().toString());   //type is an enum do not forget
            Evenement ev = new Evenement(3, dId, image, loc, dat, _type, shor, longe, 2);

           
                ServiceEvenement serv = new ServiceEvenement();
                serv.add(ev, 1);
                loadTable();
           
        }
        
        img.setText("");
        localisation.setText("");
        Short.setText("");
        Longe.setText("");

        
        img.setPromptText("URL");
        localisation.setPromptText("localisation");
        Short.setPromptText("breve description");
        Longe.setPromptText("longe description");

    }

    @FXML
    private void supprimerEvenement(ActionEvent event) throws SQLException {
        Evenement selectedEvent = evnentsVIew.getSelectionModel().getSelectedItem();
        ServiceEvenement service;
       
            service = new ServiceEvenement();
            service.deleteById(selectedEvent);
            loadTable();
        
    }

    // the following method unlike its name it synchronise between the selected item in the table and the date appering below the image
    @FXML
    private void UpdatImage(MouseEvent event) {
        Evenement selectedEvent = evnentsVIew.getSelectionModel().getSelectedItem();
        if(selectedEvent != null){
          Image image = new Image(selectedEvent.getImg_url());

        eventImage.setImage(image);

        createdAt.setText("Créé à: " + selectedEvent.getCreated_at().toString());
        modifiedAt.setText("Dernière modification à: " + selectedEvent.getModified_at().toString());
        }
        

    }

    public void loadTable() throws SQLException {
        ArrayList<Evenement> list = new ArrayList<>();
        ServiceEvenement serE = new ServiceEvenement();
            serE = new ServiceEvenement();
        
         if(user != null){
           if(user.getRoles().equals("association")) {
               list = serE.read(user.getId());
               System.out.println(user.toString()+"===========");
           } 
         }
           
//            System.out.println(list.toString());     
            ObservableList Ol = FXCollections.observableArrayList(list);
            evnentsVIew.setItems(Ol);
            eventId.setCellValueFactory(new PropertyValueFactory<>("id"));
            brieveDescription.setCellValueFactory(new PropertyValueFactory<>("shortDescription"));
            eventLocation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
            eventDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            //Cvalidation.setCellValueFactory(new PropertyValueFactory<>("validation_status"));
            Cimage.setCellValueFactory(new PropertyValueFactory<>("img_url"));
      

    }

    //this method will retur a table of nuber if occurence of the 4 fist words fro search
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
//                    System.out.println("score :" + score);
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

            System.out.println("tableau de score");
            System.out.println(tabScore);
            System.out.println("to results are :");
            System.out.println(tamponTab);
            return tamponTab;
        }
        return new ArrayList<Evenement>();

    }

    @FXML
    private void SearchEvents(ActionEvent event) {
        ArrayList<Evenement> list = new ArrayList<>();
        ArrayList<Evenement> topResults = new ArrayList<>();
        ServiceEvenement serE;
        try {
           
            serE = new ServiceEvenement();
            if(user != null){
           if(user != null){
           if(user.getRoles().equals("association")) {
               list = serE.read(user.getId());
               System.out.println(user.toString()+"===========");
           } 
         }
         }
            
            topResults = search(list, smartSearch.getText());
//            System.out.println("-----");
//            System.out.println(topResults);
            //loading the table

//            System.out.println(list.toString());  
            if(topResults.size() !=0){
                 noResult.setText("");
                ObservableList Ol = FXCollections.observableArrayList(topResults);
            evnentsVIew.setItems(Ol);
            eventId.setCellValueFactory(new PropertyValueFactory<>("id"));
            brieveDescription.setCellValueFactory(new PropertyValueFactory<>("shortDescription"));
            eventLocation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
            eventDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            Cvalidation.setCellValueFactory(new PropertyValueFactory<>("validation_status"));
            Cimage.setCellValueFactory(new PropertyValueFactory<>("img_url"));
             evnentsVIew.getSelectionModel().selectFirst();
            }else{
                noResult.setText("No result maches your search");
            }
            
        } catch (Exception e) {

        }
    }
    @FXML
    private void editShortDescription(TableColumn.CellEditEvent<Evenement, String> event) throws SQLException {
        Evenement selectedEvent = evnentsVIew.getSelectionModel().getSelectedItem();
        selectedEvent.setShortDescription(event.getNewValue());
        
            ServiceEvenement s = new ServiceEvenement();

            s.updateShortDescription(selectedEvent, selectedEvent.getShortDescription());
       

    }
    @FXML
    private void editImage(TableColumn.CellEditEvent<Evenement, String> event) throws SQLException {

        Evenement selectedEvent = evnentsVIew.getSelectionModel().getSelectedItem();
        selectedEvent.setImg_url(event.getNewValue());
       
            ServiceEvenement s = new ServiceEvenement();

            s.updateImage(selectedEvent, selectedEvent.getImg_url()); //(selectedEvent,selectedEvent.getShortDescription());
            // setting the new image
            Image image = new Image(selectedEvent.getImg_url());
            eventImage.setImage(image);
       
    }

    @FXML
    private void SearchIsEmpty(KeyEvent event) {
        try {
            if(smartSearch.getText().equals("")) loadTable();
        } catch (SQLException ex) {        
        }
    }

    @FXML 
    public void navigateToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/FXML.fxml"));
        homeButton.getScene().setRoot(root);
    }
    
    
    @FXML 
    public void navigateToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/browseEvents.fxml"));
        eventButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/RegistrationGuiFXML.fxml"));
        connectionButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToStore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/StoreGuiFXML.fxml"));
        storeButton.getScene().setRoot(root);
    }
    
    @FXML
    public void navigateToAssociation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/AssociationGuiFXML.fxml"));
        associationButton.getScene().setRoot(root);
    }

    @FXML
    private void navigateToBlog(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Gui/BlogGuiFXML.fxml"));
          blogButton.getScene().setRoot(root);
    }

    
    
    
}

