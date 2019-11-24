/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leanbois
 */
public class PrivateRoomController implements Initializable {

    @FXML
    private AnchorPane hh;
    @FXML
    private TextArea mttext1;
    @FXML
    private TextArea mttext2;
    @FXML
    private Button mtbut;
    @FXML
 private Label label;
    

       private static Statement ste;
    Connection con = Datasourc.getInstance().getConnexion();

    public PrivateRoomController() {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }}
        public void pmDisplay(){
        mttext1.setText("");
   RegistrationGuiFXMLController a = new RegistrationGuiFXMLController();
      
        String c = a.user.getUsername();
        PublicRoomController b = new PublicRoomController();
        String d;
        d = b.accountus;
        String request = "SELECT * FROM messages WHERE (sender_id = '"+c+"' AND receiver_id = '"+d+"') OR (receiver_id = '"+c+"' AND sender_id ='"+d+"')";
        try{
           ResultSet rs = ste.executeQuery(request);
            while(rs.next()){   
                String sender_id = rs.getString("sender_id");
                String receiver_id = rs.getString("receiver_id");
                String title = rs.getString("title");
                 String content = rs.getString("content");
                  Date date_created = rs.getDate("date_created");
               
                String message = "(" +date_created+")"+ sender_id + " says: " + content+"\n";
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


    }    

    @FXML
    private void sendaction(ActionEvent event) {
              String msg = mttext2.getText();
     RegistrationGuiFXMLController a = new RegistrationGuiFXMLController();
      
        String c = a.user.getUsername();
        PublicRoomController b = new PublicRoomController();
        String d = b.accountus;
        System.out.println(d);
        String sql = "INSERT INTO messages(sender_id,receiver_id,title,content,date_created)VALUES('" + c + "','" + d + "','title','" + msg + "',CURRENT_TIME())";
        try {
            ste.executeUpdate(sql);
//                msg_area.append(NICK + " says: "+msg +"\n");

        } catch (Exception ex) {
            System.out.println(ex);
        }
        mttext2.setText("");
        pmDisplay();
 }
    
}