/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Utils.DataSource;
import java.sql.*;
import Entity.Evenement;
import java.util.ArrayList;
/**
 *
 * @author oflcad
 */


/*this class is for validating event 
 *
 *
 * CRUD here are for Admin validation 
 * For Validation_status :: 0 means event is rejected; 1 means event is approved; 2 means event is under review
 * 
 * 
 * 
*/
public class EventValidation {
    
     private Connection connection;
     private Statement statement;
     
     
    
    public EventValidation() throws SQLException{
        connection = DataSource.getInstance().getConnection();
        
    }
    
    //This is for Getting all the events for adminValidation
    public ArrayList<Evenement> getAllEvents(){
        
       String query = "SELECT * from `evenement` WHERE validation_status=2";
       ArrayList<Evenement> eventsArrayList = new ArrayList<>();
        try {   
            Statement ste = connection.createStatement();
            ResultSet result = ste.executeQuery(query);
            while (result.next()) {
                int id = result.getInt(1);
                int delegue_id = result.getInt(2) ;
                String img_url = result.getString(3);
                String localisation = result.getString(4);
                Date date = result.getDate(5);
                String type = result.getString(6);
                String shortDescription = result.getString(7);
                String longDescription = result.getString(8);
                Date created_at = result.getDate(9);
                Date modified_at = result.getDate(10);
                int validation_status = result.getInt(11);
                //in every etiration we create a new instance 
                Evenement ev = new Evenement(id,delegue_id,img_url,localisation,date,Evenement.Type.valueOf(type),shortDescription,longDescription,validation_status);
                ev.setCreated_at(created_at);
                ev.setModified_at(modified_at);
                eventsArrayList.add(ev);  
            }
        } catch (SQLException e) {
        } finally{
            return eventsArrayList;
        }
    }  
    
    public void approveEvent(Evenement ev){
        System.out.println("this is the event :" + ev.getId());
        String query = "UPDATE `evenement` SET `validation_status`=? WHERE id=?";
        PreparedStatement preparedStatement;
        int validation_status = 1;
        
        try {
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setInt(1, validation_status);
            preparedStatement.setInt(2, ev.getId());
            
            int eventUpdated = preparedStatement.executeUpdate();
                if (eventUpdated > 0) {
                 System.out.println("Event has been approved! Please check database for persistance!");
            }
            
            
        } catch (SQLException e) {
            
            System.out.println("EventServiceApproval not validated");
            System.out.println(e.getMessage());
        }
        
    }
    
    public void rejectEvent(Evenement ev) {
        String query = "UPDATE `evenement` SET `validation_status`=? WHERE id=? ";
        PreparedStatement preparedStatement;
        int validation_status = 0;
        try {
            preparedStatement =  connection.prepareStatement(query);
            
            preparedStatement.setInt(1, validation_status);
            preparedStatement.setInt(2, ev.getId());
            
             int eventUpdated = preparedStatement.executeUpdate();
                if (eventUpdated > 0) {
                 System.out.println("Event has been rejected! Please check database for persistance!");
            }
            
        } catch (SQLException e) {
            
              System.out.println("EventServiceDeletion not validated");
            System.out.println(e.getMessage());
        }
    }
    
    //this is to send an event for approval
    public void sendEventForApproval(Evenement ev){
        int validation_status = 2;
        ServiceEvenement serviceEvenement = new ServiceEvenement();
        serviceEvenement.add(ev, validation_status);
        System.out.println("Event is added succefully! Please wait for Admin approval!");
        
        
    }
    
}
