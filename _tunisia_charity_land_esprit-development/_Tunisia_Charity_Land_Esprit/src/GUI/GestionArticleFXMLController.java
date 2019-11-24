/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Article;
import static GUI.RegistrationGuiFXMLController.user;
import Service.CreatePdf;
import Service.ServiceArticle;
import static Utils.DataSource.getInstance;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import static javax.swing.text.html.HTML.Tag.OL;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class GestionArticleFXMLController implements Initializable {

    @FXML
    private AnchorPane ga_rootpane;
    @FXML
    private TableView<Article> ga_tv;
    @FXML
    private TableColumn<Article, Integer> tv_id;
    @FXML
    private TableColumn<Article, Integer> tv_id_auteur;
    @FXML
    private TableColumn<Article, Date> tv_date;
    @FXML
    private TableColumn<Article, String> tv_titre;
    @FXML
    private TableColumn<Article, String> tv_contenu;
    @FXML
    private TableColumn<Article, String> tv_image;
    @FXML
    private Button addArticleBT;
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
    private Button pdfBtn;
    @FXML
    private TextField rech_tf;
    @FXML
    private Button btn_tf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            
            ArrayList<Article> list;
            ServiceArticle SA = new ServiceArticle();
            list = SA.read();
            ObservableList OL = FXCollections.observableArrayList(list);
            ga_tv.setItems(OL);
            tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tv_id_auteur.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
            tv_date.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
            tv_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tv_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            tv_image.setCellValueFactory(new PropertyValueFactory<>("image_url"));
            
           tv_titre.setCellFactory(TextFieldTableCell.forTableColumn());
           tv_contenu.setCellFactory(TextFieldTableCell.forTableColumn());
           tv_image.setCellFactory(TextFieldTableCell.forTableColumn());
           if (user == null){
               connectionButton.setText("Login/Sign up");
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(GestionArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void deleteById(ActionEvent event) throws SQLException {
        Article article_selectionne = ga_tv.getSelectionModel().getSelectedItem();
   int id_article = article_selectionne.getId();
  
   ServiceArticle sa = new ServiceArticle();
   sa.deleteById(article_selectionne);
    ArrayList<Article> list = new ArrayList();
            ServiceArticle SA = new ServiceArticle();
            list.remove(article_selectionne);
            list = SA.read();
            ObservableList OL = FXCollections.observableArrayList(list);
          ga_tv.setItems(OL);
            tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tv_id_auteur.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
            tv_date.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
            tv_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tv_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            tv_image.setCellValueFactory(new PropertyValueFactory<>("image_url"));
        }
     @FXML
    private void modifierTitre(TableColumn.CellEditEvent<Article, String> event) {
          Article  articleSelectionne = ga_tv.getSelectionModel().getSelectedItem();
        articleSelectionne.setTitre(event.getNewValue());
        try {
            ServiceArticle serv =new ServiceArticle();
            serv.updateTitre(articleSelectionne, articleSelectionne.getTitre());
        } catch (SQLException ex) {
            Logger.getLogger(GestionArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private void modifierContenu(TableColumn.CellEditEvent<Article, String> event) {
        Article  articleSelectionne = ga_tv.getSelectionModel().getSelectedItem();
        articleSelectionne.setContenu(event.getNewValue());
        try {
            ServiceArticle serv =new ServiceArticle();
            serv.updateContenu(articleSelectionne, articleSelectionne.getContenu());
        } catch (SQLException ex) {
            Logger.getLogger(GestionArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private void modifierImage(TableColumn.CellEditEvent<Article, String> event) {
        Article  articleSelectionne = ga_tv.getSelectionModel().getSelectedItem();
        articleSelectionne.setImage_url(event.getNewValue());
        try {
            ServiceArticle serv =new ServiceArticle();
            serv.updateImage(articleSelectionne, articleSelectionne.getImage_url());
        } catch (SQLException ex) {
            Logger.getLogger(GestionArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void navigateToHome(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Gui/FXML.fxml"));
        
        //Scene scene = new Scene(root);
        homeButton.getScene().setRoot(root);
    }

    @FXML
    private void navigateToBlog(ActionEvent event) throws IOException {
        
                   Parent root = FXMLLoader.load(getClass().getResource("Gui/AffichageArticle.fxml"));
        
        //Scene scene = new Scene(root);
        blogButton.getScene().setRoot(root);
    }

    @FXML
    private void navigateToEvents(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Gui/browseEvents.fxml"));
        
        //Scene scene = new Scene(root);
        eventButton.getScene().setRoot(root);
    }


    @FXML
    private void navigateToProfile(ActionEvent event) throws IOException {
        if(user == null){
            Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
            connectionButton.getScene().setRoot(root);
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("ProfilUserFXML.fxml"));
           connectionButton.getScene().setRoot(root);
            connectionButton.setText("Profil");
        }
    }

    @FXML
    private void navigateToStore(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Gui/StoreGuiFXML.fxml"));
        
        //Scene scene = new Scene(root);
        storeButton.getScene().setRoot(root);
    }

   
   

    @FXML
    private void convertToPdf(ActionEvent event) throws FileNotFoundException {
        try {
            CreatePdf.convertToPdf();
        } catch (SQLException | DocumentException ex) {
            Logger.getLogger(CreatePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void recherche(ActionEvent event) {
         ArrayList<Article> list = new ArrayList<>();
        ArrayList<Article> topResults = new ArrayList<>();
        ServiceArticle serE;
        try {
            serE = new ServiceArticle();
            list = serE.read();
            
            topResults = search(list, rech_tf.getText());
            
                 
                ObservableList Ol = FXCollections.observableArrayList(topResults);
            ga_tv.setItems(Ol);
            tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tv_id_auteur.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
            tv_date.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
            tv_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tv_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            tv_image.setCellValueFactory(new PropertyValueFactory<>("image_url"));
             ga_tv.getSelectionModel().selectFirst();
            
            
        } catch (Exception e) {

        }
    }

    
    public ArrayList<Integer> SearchInshort(Article e, String Input) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;

        String[] S = Input.split(" ");
        String[] E = e.getTitre().split(" ");
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
    public ArrayList<Article> search(ArrayList<Article> tab, String s) {
        Article tampon = new Article();
        int tampInt = 0;

        ArrayList<Integer> tabScore = new ArrayList<>();
        ArrayList<Article> tamponTab = new ArrayList<>();

        if (!s.equals("")) {

            for (Article ev : tab) {
                ArrayList<Integer> Occurence = SearchInshort(ev, s);

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

                    System.out.println("score :" + score);
                    tabScore.add(score);

                    tamponTab.add(ev);

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
        return new ArrayList<Article>();
    }

   
   

    @FXML
    private void navArticleAdd(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Gui/ArticleAddFXML.fxml"));
        
        //Scene scene = new Scene(root);
        addArticleBT.getScene().setRoot(root);
    }

    
    

    

}
   
    

        
   

