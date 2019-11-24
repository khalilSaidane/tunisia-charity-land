/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import Utils.Datasourc;
import Entity.Produit;
import static GUI.RegistrationGuiFXMLController.user;
import Service.gestion_produit;


/**
 * FXML Controller class
 *
 * @author PC
 */
public class StoreController implements Initializable {

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
    private Button prev_page;
    @FXML
    private Button next_page;
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
    private Button view_p6;
    @FXML
    private Button view_p5;
    @FXML
    private Button view_p4;
    @FXML
    private Button view_p3;
    @FXML
    private Button view_p2;
     @FXML
    private Button stat_prod;
    @FXML
    private Button view_p1;
    private final gestion_produit serv = new gestion_produit();
    private ArrayList<Produit> lisOfProduct = serv.read();
    static String ur;
    private final int nbP = lisOfProduct.size() / 6 + fct(lisOfProduct.size() % 6);
    private int CurrP = 1;
    private int i;
    private int nbrOfimgInlastPage;
    int x;
    int indexOfImage;
    private static Statement ste;
        Connection con=Datasourc.getInstance().getConnexion();
    @FXML
    private Button gprod_but;
    
        public StoreController(){
             try{
                ste=con.createStatement();
            }catch(SQLException e){
                System.out.println(e);
            }
        }
    /**
     * Initializes the controller class.
     */
     public void initImages(ArrayList<Produit> l) {

        if (l.size() > 0) {
            loadImage1(l.get(0).getImg_url());
            
       }

        if (l.size() > 1) {
            loadImage2(l.get(1).getImg_url());
                    }

        if (l.size() > 2) {
            loadImage3(l.get(2).getImg_url());
           
        }

        if (l.size() > 3) {
            loadImage4(l.get(3).getImg_url());
            
        }

        if (l.size() > 4) {
            loadImage5(l.get(4).getImg_url());
          
        }

        if (l.size() > 5) {
            loadImage6(l.get(5).getImg_url());
            
        }

    }
public void intImageToEmpty() {
        Image image = new Image("pics/nothing.jpg");
        image1.setImage(image);
        image2.setImage(image);
        image3.setImage(image);
        image4.setImage(image);
        image5.setImage(image);
        image6.setImage(image);
}
 public void loadImage1(String url) {
        Image img1 = new Image(url);
        image1.setImage(img1);

    }
    

    public void loadImage2(String url) {
        Image img = new Image(url);
        image2.setImage(img);
    }

    public void loadImage3(String url) {
        Image img = new Image(url);
        image3.setImage(img);
    }

    public void loadImage4(String url) {
        Image img = new Image(url);
        image4.setImage(img);
    }

    public void loadImage5(String url) {
        Image img = new Image(url);
        image5.setImage(img);
    }
  

    public void loadImage6(String url) {
        Image img = new Image(url);
        image6.setImage(img);
    }   
    
    public void loadNextPage() throws SQLException {
        gestion_produit serv = new gestion_produit();
        ArrayList<Produit> lisOfProduct = serv.read();

        if (CurrP < nbP - 1) {
            indexOfImage = CurrP * 6;

            loadImage1(lisOfProduct.get(indexOfImage).getImg_url());
            indexOfImage++;
            //System.out.println("  i: " + i);
            loadImage2(lisOfProduct.get(indexOfImage).getImg_url()); 
            indexOfImage++;
            loadImage3(lisOfProduct.get(indexOfImage).getImg_url());
            indexOfImage++;
            loadImage4(lisOfProduct.get(indexOfImage).getImg_url());
            indexOfImage++;
            loadImage5(lisOfProduct.get(indexOfImage).getImg_url());
            indexOfImage++;
            loadImage6(lisOfProduct.get(indexOfImage).getImg_url());
            indexOfImage++;
            CurrP++;

        } else if (CurrP < nbP) {
            i = 0;
            intImageToEmpty();
            if (indexOfImage < lisOfProduct.size()) {
                loadImage1(lisOfProduct.get(indexOfImage).getImg_url());
                indexOfImage++;
                nbrOfimgInlastPage++;
            }
            if (indexOfImage < lisOfProduct.size()) {
                loadImage2(lisOfProduct.get(indexOfImage).getImg_url());
                indexOfImage++;

            }
            if (indexOfImage < lisOfProduct.size()) {
                loadImage3(lisOfProduct.get(indexOfImage).getImg_url());
                indexOfImage++;

            }
            if (indexOfImage < lisOfProduct.size()) {
                loadImage4(lisOfProduct.get(indexOfImage).getImg_url());
              
                indexOfImage++;
            }
            if (indexOfImage < lisOfProduct.size()) {
                loadImage5(lisOfProduct.get(indexOfImage).getImg_url());
               

                indexOfImage++;
            }
            if (indexOfImage < lisOfProduct.size()) {
                loadImage6(lisOfProduct.get(indexOfImage).getImg_url());
                indexOfImage++;

            }
            //CurrP++;

//            CurrP = 0;
//            i = 0;
        }

    }

    public void loadPreviousPage() {

//           System.out.println("##########");
//           System.out.println("index :"+i);
//           System.out.println("##########");
        lisOfProduct = serv.read();
        CurrP--;
        if (CurrP < nbP) {
            i = 0;
            indexOfImage = CurrP * 6 + i;
            loadImage1(lisOfProduct.get(x).getImg_url());
            i++;
            x = CurrP * 6 + i;
            System.out.println("  i: " + i + "Curr: " + CurrP + "i in page: " + x);

            loadImage1(lisOfProduct.get(indexOfImage).getImg_url());
            indexOfImage++;
            loadImage2(lisOfProduct.get(indexOfImage).getImg_url());
            indexOfImage++;
            loadImage3(lisOfProduct.get(indexOfImage).getImg_url());
            indexOfImage++;
            loadImage4(lisOfProduct.get(indexOfImage).getImg_url());
            indexOfImage++;
            loadImage5(lisOfProduct.get(indexOfImage).getImg_url());          
            indexOfImage++;
            loadImage6(lisOfProduct.get(indexOfImage).getImg_url());
           
            indexOfImage++;
        }
        indexOfImage -= 5;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initImages(lisOfProduct);
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
    private void go_prev(ActionEvent event) {
        if (CurrP >= 1) {

            System.out.println("indexOfImage :" + indexOfImage);
            loadPreviousPage();
            System.out.println("*************goToPrevious page called**************** ");

            System.out.println("indexOfImage :" + indexOfImage);
        }
    }

    @FXML
    private void go_next(ActionEvent event) throws SQLException {
         System.out.println("indexOfImage:" + indexOfImage);
        loadNextPage();
        System.out.println("*************goToNext page called**************** ");

        System.out.println("indexOfImage:" + indexOfImage);
    }

    @FXML
    private void cart6(ActionEvent event) throws IOException {
   ur=getur(5);       
// String ur= lisOfProduct.get(indexOfImage+4).getImg_url();
     //System.out.println(ur);
      Parent root = FXMLLoader.load(getClass().getResource("Gui/buy.fxml"));   
   //  Scene scene = new Scene(root); 
view_p6.getScene().setRoot(root);
    }
    @FXML
    private void cart5(ActionEvent event) throws IOException {
ur=getur(4);       
// String ur= lisOfProduct.get(indexOfImage+4).getImg_url();
     //System.out.println(ur);
      Parent root = FXMLLoader.load(getClass().getResource("Gui/buy.fxml"));   
   //  Scene scene = new Scene(root); 
view_p5.getScene().setRoot(root);
    }

    @FXML
    private void cart4(ActionEvent event) throws IOException {
ur=getur(3);       
// String ur= lisOfProduct.get(indexOfImage+3).getImg_url();
     //System.out.println(ur);
      Parent root = FXMLLoader.load(getClass().getResource("Gui/buy.fxml"));   
   //  Scene scene = new Scene(root); 
view_p4.getScene().setRoot(root);
    }

    @FXML
    private void cart3(ActionEvent event) throws IOException {
        ur=getur(2);
        i=2;
   //  System.out.println(ur);
   Parent root = FXMLLoader.load(getClass().getResource("Gui/buy.fxml"));   
   //  Scene scene = new Scene(root); 
view_p3.getScene().setRoot(root);
    }

    @FXML
    private void cart2(ActionEvent event) throws IOException {
 ur=getur(1);       
//String ur= lisOfProduct.get(indexOfImage+1).getImg_url();
  //   System.out.println(ur
   Parent root = FXMLLoader.load(getClass().getResource("Gui/buy.fxml"));   
   //  Scene scene = new Scene(root); 
view_p2.getScene().setRoot(root);
    }

    @FXML
    private void cart1(ActionEvent event) throws IOException {
      ur=getur(0);
    
    // System.out.println(ur);
   Parent root = FXMLLoader.load(getClass().getResource("Gui/buy.fxml"));   
   //  Scene scene = new Scene(root); 
view_p1.getScene().setRoot(root);
     
    }
  public String getur(){
           String ur= lisOfProduct.get(indexOfImage ).getImg_url(); 
              return ur;
      
  }
  public String getur(int i){
           String ur= lisOfProduct.get(indexOfImage+i ).getImg_url(); 
              return ur;
      
  }

@FXML
    private void gostat(ActionEvent event) throws SQLException{
final String SQL = "    SELECT name_prod , COUNT(user_id) FROM orders GROUP BY name_prod";
        
final CategoryDataset dataset = new JDBCCategoryDataset(con, SQL);

         JFreeChart chart = ChartFactory.createBarChart3D("products", "axe x", "axe y", dataset, PlotOrientation.VERTICAL, true, true, true);
         ChartFrame frame = new ChartFrame("products",chart);
         frame.pack();
         frame.setVisible(true);

}

    @FXML
    private void nav_gp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/deleg.fxml"));   
   //  Scene scene = new Scene(root); 
gprod_but.getScene().setRoot(root);
    }
}