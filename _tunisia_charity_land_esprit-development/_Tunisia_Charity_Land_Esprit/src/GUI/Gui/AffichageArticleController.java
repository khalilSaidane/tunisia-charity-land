/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Gui;

import Entity.Article;
import static GUI.RegistrationGuiFXMLController.user;
import Service.ServiceArticle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AffichageArticleController implements Initializable {

    @FXML
    private Label titre1;
    @FXML
    private Label titre2;

    private int CurrP = 1;
    int index;
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
    private TextField rech_tf;
    @FXML
    private Button btn_tf;
    @FXML
    private ImageView img1;
    @FXML
    private Label dateLabel1;
    @FXML
    private TextArea contenuLabel1;
    @FXML
    private ImageView img2;
    @FXML
    private Label dateLabel2;
    @FXML
    private TextArea contenuLabel2;
    @FXML
    private Button previous1;
    @FXML
    private Button next1;
    @FXML
    private Button homeButton1;
    @FXML
    private Button blogButton1;
    @FXML
    private Button eventButton1;
    @FXML
    private Button connectionButton1;
    @FXML
    private Button storeButton1;
    @FXML
    private Button gesstionArticleButton;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceArticle serv = new ServiceArticle();
            ArrayList<Article> listOfArticle = serv.read();
            initTitle(listOfArticle);
            if (user == null){
               connectionButton1.setText("Login/Sign up");
               gesstionArticleButton.setVisible(false);
           }
        } catch (SQLException ex) {
            Logger.getLogger(AffichageArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToNext(ActionEvent event) {
        try {
            ServiceArticle serv = new ServiceArticle();
            ArrayList<Article> listOfArticle = serv.read();
            if(listOfArticle.size()>index) loadNextPage() ;
                } catch (SQLException ex) {
            Logger.getLogger(AffichageArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToPrevious(ActionEvent event) {
        try {
            if(CurrP>0){
                ServiceArticle serv;
            serv = new ServiceArticle();
            ArrayList<Article> listOfArticle = serv.read();
            CurrP--;
            int nbP = listOfArticle.size() / 2 + fct(listOfArticle.size() % 2);
            
            if (CurrP < nbP) {
                
                index = CurrP * 2 ;
                loadTitle1(listOfArticle.get(index).getTitre());
                loadContenu1(listOfArticle.get(index).getContenu());
                loadDate1(listOfArticle.get(index).getDate_creation().toString());
               loadImg1(listOfArticle.get(index).getImage_url());
                index++;
                
                loadTitle2(listOfArticle.get(index).getTitre());
                loadContenu2(listOfArticle.get(index).getContenu());
                loadDate2(listOfArticle.get(index).getDate_creation().toString());
                loadImg2(listOfArticle.get(index).getImage_url());
                index ++;
            }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichageArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public int fct(int i) {
        if (i != 0) {
            return 1;
        } else {
            return i;
        }
    }

    public void loadTitle1(String t) {
        titre1.setText(t);
    }

    public void loadTitle2(String t) {
        titre2.setText(t);
    }
    
    public void loadContenu1(String t) {
        contenuLabel1.setText(t);
    }

    public void loadContenu2(String t) {
        contenuLabel2.setText(t);
    }
    
    public void loadDate1(String t) {
        dateLabel1.setText(t);
    }
    
    public void loadDate2(String t) {
        dateLabel2.setText(t);
    }
    
    /*final String imageURI = "http://www.developpez.com/template/images/logo.png"; 
final Image image = new Image(imageURI);*/
    public void loadImg1(String url) {
        Image image1 = new Image(url);
       img1.setImage(image1);
    }
    
    public void loadImg2(String url) {
        Image image2 = new Image(url);
        img2.setImage(image2);
    }
    public void initTitle(ArrayList<Article> a) {
        if (a.size() > 0) {
            loadTitle1(a.get(0).getTitre());
            loadContenu1(a.get(0).getContenu());
            loadDate1(a.get(0).getDate_creation().toString());
           loadImg1(a.get(0).getImage_url());
            
               
        }
        if (a.size() > 1) {
            loadTitle2(a.get(1).getTitre());
            loadContenu2(a.get(0).getContenu());
            loadDate2(a.get(0).getDate_creation().toString());
            loadImg2(a.get(0).getImage_url());
        }
    }

    public void initTitle() {
        titre1.setText("");
        titre2.setText("");
        contenuLabel1.setText("");
        contenuLabel2.setText("");
        dateLabel1.setText("");
        dateLabel2.setText("");
        Image image = new Image("images/empty-image.jpg");
        Image logoimg =new Image("pics/images.png");
        logo.setImage(logoimg);
        img1.setImage(image);
        img2.setImage(image);
        
    }

    
    
    public void loadNextPage() {
        ServiceArticle serv;
        try {
            serv = new ServiceArticle();
            ArrayList<Article> listOfArticle = serv.read();
            int nbP = listOfArticle.size() / 2 + fct(listOfArticle.size() % 2);
             
            if(CurrP<nbP-1){
                index=CurrP*2;
                loadTitle1(listOfArticle.get(index).getTitre());
                loadContenu1(listOfArticle.get(index).getContenu());
                loadDate1(listOfArticle.get(index).getDate_creation().toString());
                loadImg1(listOfArticle.get(index).getImage_url());
                index++;
                loadTitle2(listOfArticle.get(index).getTitre());
                loadContenu2(listOfArticle.get(index).getContenu());
                loadDate2(listOfArticle.get(index).getDate_creation().toString());
                loadImg2(listOfArticle.get(index).getImage_url());
                index++;
                CurrP++;
        
            }
            else if(CurrP<nbP){
                initTitle();
                if (index<listOfArticle.size()){
                    loadTitle1(listOfArticle.get(index).getTitre());
                    loadContenu1(listOfArticle.get(index).getContenu());
                    loadDate1(listOfArticle.get(index).getDate_creation().toString());
                    loadImg1(listOfArticle.get(index).getImage_url());
                index++;
                }
                if (index<listOfArticle.size()){
                    loadTitle2(listOfArticle.get(index).getTitre());
                    loadContenu2(listOfArticle.get(index).getContenu());
                    loadDate2(listOfArticle.get(index).getDate_creation().toString());
                    loadImg2(listOfArticle.get(index).getImage_url());
                index++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AffichageArticleController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    


    @FXML
    private void navigateToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        
        //Scene scene = new Scene(root);
        homeButton1.getScene().setRoot(root);
    }

    @FXML
    public void navigateToBlog(ActionEvent event) {
    }

    @FXML
    public void navigateToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("browseEvents.fxml"));
        
        //Scene scene = new Scene(root);
        eventButton1.getScene().setRoot(root);
    }

   /* @FXML
    private void navigateToAssociation(ActionEvent event) {
    }*/

    @FXML
    public void navigateToProfile(ActionEvent event) throws IOException {
        if(user == null){
            Parent root = FXMLLoader.load(getClass().getResource("registrationGuiFXML.fxml"));
            connectionButton1.getScene().setRoot(root);
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("ProfilUserFXML.fxml"));
           connectionButton1.getScene().setRoot(root);
            connectionButton1.setText("Profil");
        }
    }

    @FXML
    public void navigateToStore(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("StoreGuiFXML.fxml"));
        
        //Scene scene = new Scene(root);
        storeButton1.getScene().setRoot(root);
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
            initTitle();
            initTitle(topResults);
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
    private void navigateToAssociation(ActionEvent event) {
    }

    @FXML
    private void NavigatetoGestionArticle(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GestionArticleFXML.fxml"));
        
        //Scene scene = new Scene(root);
        gesstionArticleButton.getScene().setRoot(root);
        
    }
   
}
