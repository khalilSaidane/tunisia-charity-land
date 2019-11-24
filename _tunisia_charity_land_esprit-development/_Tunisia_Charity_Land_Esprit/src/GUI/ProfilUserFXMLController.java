/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Entity.Article;
import Entity.Order2;
import Entity.Participant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Service.ServiceUser;
//import entite.Profile;
//import service.ProfileService;
import Entity.Users;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Service.ArticleService;
import static java.lang.Double.NaN;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.OrderService;
import service.ParticipantService;

/**
 * FXML Controller class
 *
 * @author Raed
 */
public class ProfilUserFXMLController implements Initializable {

    @FXML
    private TextField textusernameuser;
    @FXML
    private TextField textemailuser;
    private TextField textprenomuser;
    @FXML
    private Button enregistreruser;        
  
    String lienFacebook;
    String telephone;
    String description;
    @FXML
    private AnchorPane toolbar;
    @FXML
    private ImageView buttuser;
    @FXML
    private ImageView buttuser1;
    @FXML
    private ImageView buttevents;
    @FXML
    private AnchorPane profil;
    @FXML
    private AnchorPane events;
     @FXML
    private TableView<Participant> tableviewuser;
    @FXML
    private TableColumn<Participant, Date> cdate;
    @FXML
    private TableColumn<Participant , Integer> ceventid;
    @FXML
    private TableColumn<Participant , Integer> cuserid;
       @FXML
    private Button supprimerpart;
      @FXML
    private TextField textsupp;
    @FXML
    private TextField textrech;
    @FXML
    private Label warning;
    @FXML
    private ImageView buttblog;
    @FXML
    private AnchorPane blog;
    @FXML
    private TableView<Article> tableviewart;
    @FXML
    private TableColumn<Article, Integer> cid;
    @FXML
    private TableColumn<Article, Integer> cauthorid;
    @FXML
    private TableColumn<Article, Date> cdateart;
    @FXML
    private TableColumn<Article , String> ctitle;
    @FXML
    private TableColumn<Article , String> ccontent;
    @FXML
    private TableColumn<Article , String> cimage;
       @FXML
    private Button supprimerart;
      @FXML
    private TextField textsuppart; 
    @FXML
    private TextField textpassuser;
    @FXML
    private TextField textadresseuserr;
    @FXML
    private TextField textmod;
    private TextField rechart;
    @FXML
    private ImageView buttorder;
    @FXML
    private AnchorPane order;
    @FXML
    private TextField textmodorder;
    @FXML
    private TextField textsupporder;
    @FXML
    private Button buttsupporder;
    @FXML
    private Button modorder;
    @FXML
    private TextField textrechorder;
    @FXML
    private TableView<Order2> tableorder;
    @FXML
    private TableColumn<Order2, Integer> cprodi;
    @FXML
    private TableColumn<Order2, Integer> cquantite;
    @FXML
    private TableColumn<Order2, Integer> cprix;
    @FXML
    private TableColumn<Order2, Integer> cproductowner;
    @FXML
    private TableColumn<Order2, Integer> cuseridorder;
    private Label laberr;
    RegistrationGuiFXMLController C = new RegistrationGuiFXMLController();
     Users user = C.user ;
    private String email;
    private ServiceUser service;
    @FXML
    private ImageView facebookapi;
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
    private Button logoutbutt;
    @FXML
    private Label labelerreur;
    @FXML
    private Button buttactpart;
    @FXML
    private Button buttactorder;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
        System.out.println("this is the email :" + email);
        //this.user = service.getUserByEmail(email);  
         textusernameuser.setText("username");
        textpassuser.setText("password");
        textadresseuserr.setText("adresse");
        textemailuser.setText("email@email.com");

/*          FXMLLoader fxmlLoader = new FXMLLoader();
try {
    fxmlLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml").openStream());
    RegistrationGuiFXMLController controller = fxmlLoader.getController();
    // Or use this to find your label
    TextField mytextfield = (TextField) fxmlLoader.getNamespace().get("login_username");
     TextField mytextfield1 = (TextField) fxmlLoader.getNamespace().get("login_pass");
     String a = mytextfield.getText();
     String b = mytextfield1.getText();
} catch (IOException e) {
    e.printStackTrace();
}*/
         textusernameuser.setText(user.getUsername() );
        textpassuser.setText(user.getPassword());
        textadresseuserr.setText(user.getAdresse());
        textemailuser.setText(user.getEmail());

         ParticipantService pa = null;
        try {
            pa = new ParticipantService();
        } catch (SQLException ex) {
            Logger.getLogger(ProfilUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Participant> part = new ArrayList();
        // User b = new User (555,"pipo","sarko",1,"raed","djdjd","jbhjbd","admin",null,"999","550cc");
      //  try {
          //  part = (ArrayList<Participant>) pa.getByUserID(ServiceUser.userStatic.getId());
           part = (ArrayList <Participant>) pa.getByUserID(user.getId());
      // } catch (SQLException ex) {
        //    Logger.getLogger(MyeventsUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
     //  }
         ObservableList obs = FXCollections.observableArrayList(part);
            tableviewuser.setItems(obs);
          
            cdate.setCellValueFactory(new PropertyValueFactory<>("date"));
            ceventid.setCellValueFactory(new PropertyValueFactory<>("eventid"));
            cuserid.setCellValueFactory(new PropertyValueFactory<>("userid"));
         
           
       
        // TODO
    }    
    
    public void initData(String email){
        this.email = email;
        
    }
    
    public void Myeventsuser(ActionEvent event) {
       try{
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("MyeventsUserFXML.fxml"));
       Parent root = (Parent) fxmlloader.load();
       Stage stage = new Stage();
       stage.setTitle("Mes Evenements");
       stage.setScene(new Scene(root));
       stage.show();
               }catch (Exception e){
                   System.out.println(e);
               }
    }

    public void Mesblogsuser(ActionEvent event) {
           try{
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("MyarticlesFXML.fxml"));
       Parent root = (Parent) fxmlloader.load();
       Stage stage = new Stage();
       stage.setTitle("Mes Blogs");
       stage.setScene(new Scene(root));
       stage.show();
               }catch (Exception e){
                   System.out.println(e);
               }
    }

    public void Mesarticlesuser(ActionEvent event) {
         try{
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("MyorderFXML.fxml"));
       Parent root = (Parent) fxmlloader.load();
       Stage stage = new Stage();
       stage.setTitle("Mes Commandes");
       stage.setScene(new Scene(root));
       stage.show();
               }catch (Exception e){
                   System.out.println(e);
               }
    }

    @FXML
    private void Enregistreruser(ActionEvent event){try{
      ServiceUser serv = new ServiceUser();
         user.setUsername(textusernameuser.getText());
          user.setEmail(textemailuser.getText());
         user.setPassword(textpassuser.getText());
        user.setAdresse(textadresseuserr.getText());
        serv.moduser(user.getUsername(), user.getEmail(), user.getPassword(), user.getAdresse(), user.getId());
        try{
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Gui/Modifie.fxml"));
       Parent root = (Parent) fxmlloader.load();
       Stage stage = new Stage();
       
       stage.setScene(new Scene(root));
       stage.show();
               }catch (Exception e){
                   System.out.println(e);
               }
        System.out.println("ModifiÈ");
    }catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
     public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    @FXML
    private void handlebutton(MouseEvent event) throws SQLException {
          if (event.getTarget()== buttuser){
            profil.setVisible(true);
            events.setVisible(false);
           blog.setVisible(false);
           order.setVisible(false);
            ParticipantService pa = new ParticipantService();
        ArrayList<Participant> part = new ArrayList();
           part =  (ArrayList<Participant>) pa.getByUserID(user.getId());
         ObservableList obs = FXCollections.observableArrayList(part);
            tableviewuser.setItems(obs);
            cdate.setCellValueFactory(new PropertyValueFactory<>("date"));
            ceventid.setCellValueFactory(new PropertyValueFactory<>("eventid"));
            cuserid.setCellValueFactory(new PropertyValueFactory<>("userid"));
           
            
      
        }else if (event.getTarget()== buttevents){
           profil.setVisible(false);
            events.setVisible(true);
            blog.setVisible(false);
            order.setVisible(false);
        }else if(event.getTarget()== buttblog){
           profil.setVisible(false);
            events.setVisible(false);
            blog.setVisible(true);
            order.setVisible(false);
             ArticleService as = new ArticleService();
        ArrayList<Article> asl = new ArrayList();
           asl = (ArrayList <Article>) as.getByUserID(user.getId());
         ObservableList obs = FXCollections.observableArrayList(asl);
            tableviewart.setItems(obs);
            cid.setCellValueFactory(new PropertyValueFactory<>("id"));
            cauthorid.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
            cdateart.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
            ctitle.setCellValueFactory(new PropertyValueFactory<>("titre"));
            ccontent.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            cimage.setCellValueFactory(new PropertyValueFactory<>("image_url"));
        }
        else if (event.getTarget() ==buttorder){
             profil.setVisible(false);
            events.setVisible(false);
            blog.setVisible(false);
            order.setVisible(true);
             OrderService as = new OrderService();
        ArrayList<Order2> asl = new ArrayList();
           asl = (ArrayList <Order2>) as.getByUserID(user.getId());
         ObservableList obs = FXCollections.observableArrayList(asl);
            tableorder.setItems(obs);
            cprodi.setCellValueFactory(new PropertyValueFactory<>("productid"));
            cuseridorder.setCellValueFactory(new PropertyValueFactory<>("userid"));
            cquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            cproductowner.setCellValueFactory(new PropertyValueFactory<>("productowner"));
            
        }
    }
    
     @FXML
    public void supprimerPart(ActionEvent event) throws SQLException, IOException{
            ParticipantService or = new ParticipantService();
            int a = Integer.parseInt(textsupp.getText());
     
       boolean b=or.rech(a,user.getId());
       if (b==true){
           or.suppPart(a,user.getId());
             try{
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Gui/DialogFXML.fxml"));
       Parent root = (Parent) fxmlloader.load();
       Stage stage = new Stage();
       stage.setTitle("Dialog");
       stage.setScene(new Scene(root));
       stage.show();
               }catch (Exception e){
                   System.out.println(e);

               }}
       else 
           warning.setText("ID erronÈ");
        ParticipantService pa = new ParticipantService();
        ArrayList<Participant> part = new ArrayList();
           part =  (ArrayList<Participant>) pa.getByUserID(user.getId());
         ObservableList obs = FXCollections.observableArrayList(part);
            tableviewuser.setItems(obs);
            cdate.setCellValueFactory(new PropertyValueFactory<>("date"));
            ceventid.setCellValueFactory(new PropertyValueFactory<>("eventid"));
            cuserid.setCellValueFactory(new PropertyValueFactory<>("userid"));
         
           
       
               
       
    }    
    @FXML
       public void rechercheavancee(KeyEvent event) throws SQLException {
            
        String x=textrech.getText();
       // String y="'%"+x+"%'";
        int a = Integer.parseInt(x);
        //System.out.println(y);
        ParticipantService pa = new ParticipantService();
        ArrayList<Participant> part = new ArrayList();
        part =  (ArrayList<Participant>) pa.getByUserID(user.getId());
           part = pa.rechercherPart(a,user.getId());
         ObservableList obs = FXCollections.observableArrayList(part);
            tableviewuser.setItems(obs);
            cdate.setCellValueFactory(new PropertyValueFactory<>("date"));
            ceventid.setCellValueFactory(new PropertyValueFactory<>("eventid"));
            cuserid.setCellValueFactory(new PropertyValueFactory<>("userid"));
         
         
    }
         @FXML
    public void supprimerArt(ActionEvent event) throws SQLException, IOException{
            ArticleService or = new ArticleService();
            int a = Integer.parseInt(textsuppart.getText());
            if (or.rech(a, user.getId())==true){
                  try{
                      or.suppArticle(user.getId(),a);
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Gui/DialogFXML.fxml"));
       Parent root = (Parent) fxmlloader.load();
       Stage stage = new Stage();
       stage.setTitle("Dialog");
       stage.setScene(new Scene(root));
       stage.show();
               }catch (Exception e){
                   System.out.println(e);

               }
            }else labelerreur.setText("ID erroné");
       
        ArticleService as = new ArticleService();
        ArrayList<Article> asl = new ArrayList();
           asl = (ArrayList <Article>) as.getByUserID(user.getId());
         ObservableList obs = FXCollections.observableArrayList(asl);
            tableviewart.setItems(obs);
            cid.setCellValueFactory(new PropertyValueFactory<>("id"));
            cauthorid.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
            cdateart.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
            ctitle.setCellValueFactory(new PropertyValueFactory<>("titre"));
            ccontent.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            cimage.setCellValueFactory(new PropertyValueFactory<>("image_url"));
    }    
    @FXML
    public void modtitre(ActionEvent event) throws SQLException, IOException {
        ArticleService or = new ArticleService ();
        String b = textmod.getText();
        int a = Integer.parseInt(textsuppart.getText());
            if (a != NaN)
            {
        or.modArticle(b, a);
           try{
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Gui/Modifie.fxml"));
       Parent root = (Parent) fxmlloader.load();
       Stage stage = new Stage();
       
       stage.setScene(new Scene(root));
       stage.show();
               }catch (Exception e){
                   System.out.println(e);
               }
        
        }
            
            
         ArticleService as = new ArticleService();
        ArrayList<Article> asl = new ArrayList();
           asl = (ArrayList <Article>) as.getByUserID(user.getId());
         ObservableList obs = FXCollections.observableArrayList(asl);
            tableviewart.setItems(obs);
            cid.setCellValueFactory(new PropertyValueFactory<>("id"));
            cauthorid.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
            cdateart.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
            ctitle.setCellValueFactory(new PropertyValueFactory<>("titre"));
            ccontent.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            cimage.setCellValueFactory(new PropertyValueFactory<>("image_url"));
        
    }

    private void rechercheart(KeyEvent event) throws SQLException {
          String x=rechart.getText();
        String y="'%"+x+"%'";
        //System.out.println(y);
        ArticleService as = new ArticleService();
        ArrayList<Article> part = new ArrayList();
        part =  (ArrayList<Article>) as.getByUserID(user.getId());
           part = as.rechercherArt(y,user.getId());
         ObservableList obs = FXCollections.observableArrayList(part);
            tableviewart.setItems(obs);
            cid.setCellValueFactory(new PropertyValueFactory<>("id"));
            cauthorid.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
            cdateart.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
            ctitle.setCellValueFactory(new PropertyValueFactory<>("titre"));
            ccontent.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            cimage.setCellValueFactory(new PropertyValueFactory<>("image_url"));
    }

    @FXML
    private void supporder(ActionEvent event) throws SQLException {
         OrderService or = new OrderService();
            int a = Integer.parseInt(textsupporder.getText());
            
                
       if(or.suppOrder(a,user.getId())){
            try{
                      
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Gui/DialogFXML.fxml"));
       Parent root = (Parent) fxmlloader.load();
       Stage stage = new Stage();
       stage.setTitle("Dialog");
       stage.setScene(new Scene(root));
       stage.show();
               }catch (Exception e){
                   System.out.println(e);

               }
           
       }
       
        OrderService as = new OrderService();
        ArrayList<Order2> asl = new ArrayList();
           asl = (ArrayList <Order2>) as.getByUserID(user.getId());
         ObservableList obs = FXCollections.observableArrayList(asl);
            tableorder.setItems(obs);
            cprodi.setCellValueFactory(new PropertyValueFactory<>("productid"));
            cuseridorder.setCellValueFactory(new PropertyValueFactory<>("userid"));
            cquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            cproductowner.setCellValueFactory(new PropertyValueFactory<>("productowner"));
           
    }

    @FXML
    private void modifierorder(ActionEvent event) throws SQLException {
         OrderService or = new OrderService ();
        int b = Integer.parseInt (textmodorder.getText());
        int a = Integer.parseInt(textsupporder.getText());
        or.modOrder(b, a,user.getId());
         OrderService as = new OrderService();
        ArrayList<Order2> asl = new ArrayList();
           asl = (ArrayList <Order2>) as.getByUserID(user.getId());
         ObservableList obs = FXCollections.observableArrayList(asl);
            tableorder.setItems(obs);
            cprodi.setCellValueFactory(new PropertyValueFactory<>("productid"));
            cuseridorder.setCellValueFactory(new PropertyValueFactory<>("userid"));
            cquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            cproductowner.setCellValueFactory(new PropertyValueFactory<>("productowner"));
        
    }

    @FXML
    private void rechercheorder(KeyEvent event) throws SQLException ,IOException {
     String x=textrechorder.getText();
     
     if (x !=""){
     int a = Integer.parseInt(x);
      
        OrderService as = new OrderService();
        ArrayList<Order2> part = new ArrayList();
        part =  (ArrayList<Order2>) as.getByUserID(user.getId());
           part = as.rechercherOrder(a,user.getId());   
       
        
         ObservableList obs = FXCollections.observableArrayList(part);
            tableorder.setItems(obs);
            cprodi.setCellValueFactory(new PropertyValueFactory<>("productid"));
            cuseridorder.setCellValueFactory(new PropertyValueFactory<>("userid"));
            cquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            cproductowner.setCellValueFactory(new PropertyValueFactory<>("productowner"));
     }else if (x == ""){
         laberr.setText("Inserer Prod ID !!");
     }
    }
      @FXML
    void messanger(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Gui/PublicRoom.fxml"));
                
                
               
                
                 buttuser1.getScene().setRoot(root);
    }


    @FXML
    private void facebook(MouseEvent event) {
           String domain = "https://www.google.com/";
        String appId = "1930575387248668" ;
          String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,"
                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
                + "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
                + "manage_notifications,manage_pages,publish_actions,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";
          
           System.setProperty("webdirver.chrome.driver", "chromedriver.exe");
           
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        while(true){
       
            if(!driver.getCurrentUrl().contains("facebook.com")){
            String url = driver.getCurrentUrl();
            accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
           
            driver.quit();
           
                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                User user = fbClient.fetchObject("me",User.class);
               
               // message.setText(user.getName());
           
          
         
    }
    
} 
    }

    @FXML
    private void navigateToHome(ActionEvent event) throws IOException {
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/FXML.fxml"));
        //Parent root =  (Parent) loader.load();
    }

    @FXML
    private void navigateToBlog(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/GestionArticleFXML.fxml"));
        Parent root =  (Parent) loader.load();
      //  AdminDashBoardBlogFXMLController controller = loader.<AdminDashBoardBlogFXMLController>getController();
        
        
        
        blogButton.getScene().setRoot(root);
    }

    @FXML
    private void navigateToEvents(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/browseEvents.fxml"));
        Parent root =  (Parent) loader.load();
       // AdminDashBoFXMLController controller = loader.<AdminDashBoardEventFXMLController>getController();
        
        
        eventButton.getScene().setRoot(root);
    }

    @FXML
    private void navigateToProfile(ActionEvent event) {
    }

    @FXML
    private void navigateToStore(ActionEvent event)throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui/StoreGuiFXML.fxml"));
        Parent root =  (Parent) loader.load();
        storeButton.getScene().setRoot(root);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Gui/registrationGuiFXML.fxml"));
        logoutbutt.getScene().setRoot(root);
    }

    @FXML
    private void actpart(ActionEvent event) throws SQLException {
        ParticipantService pa = new ParticipantService();
        ArrayList<Participant> part = new ArrayList();
           part =  (ArrayList<Participant>) pa.getByUserID(user.getId());
         ObservableList obs = FXCollections.observableArrayList(part);
            tableviewuser.setItems(obs);
            cdate.setCellValueFactory(new PropertyValueFactory<>("date"));
            ceventid.setCellValueFactory(new PropertyValueFactory<>("eventid"));
            cuserid.setCellValueFactory(new PropertyValueFactory<>("userid"));
    }

    @FXML
    private void actorder(ActionEvent event) throws SQLException {
        OrderService as = new OrderService();
        ArrayList<Order2> part = new ArrayList();
        part =  (ArrayList<Order2>) as.getByUserID(user.getId());
       
        
         ObservableList obs = FXCollections.observableArrayList(part);
            tableorder.setItems(obs);
            cprodi.setCellValueFactory(new PropertyValueFactory<>("productid"));
            cuseridorder.setCellValueFactory(new PropertyValueFactory<>("userid"));
            cquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            cproductowner.setCellValueFactory(new PropertyValueFactory<>("productowner"));
        
    }
}
