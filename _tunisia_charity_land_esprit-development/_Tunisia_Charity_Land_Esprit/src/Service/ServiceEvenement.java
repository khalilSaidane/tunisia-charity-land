/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Evenement;
import Utils.DataSource;
import java.sql.*;

import java.util.ArrayList;

/**
 *
 * @author machd
 */
public class ServiceEvenement {

    private Connection connection;
    private Statement statement;

    public ServiceEvenement()  {
        try {
              connection = DataSource.getInstance().getConnection();
        } catch (Exception e) {
        }
      
    }

    public void add(Evenement ev, int validation_status)  {
        String req = "INSERT INTO `esprit`.`evenement` (`id`, `delegue_id`, `img_url`, `localisation`, `date`, `type`, `shortDescription`,"
                + " `longDescription`, `created_at`, `modified_at`,`validation_status`) "
                + "VALUES (NULL, ?, ?, ?, ?, ?,?,?, CURRENT_DATE(),CURRENT_DATE(),?);";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

//          preStatement.setInt(1, ev.getId());
            preStatement.setInt(1, ev.getDelegue_id());
            preStatement.setString(2, ev.getImg_url());
            preStatement.setString(3, ev.getLocalisation());
            preStatement.setDate(4, ev.getDate());
            preStatement.setString(5, ev.getType().toString());
            preStatement.setString(6, ev.getShortDescription());
            preStatement.setString(7, ev.getLongDescription());
            preStatement.setInt(8, validation_status);// this for event validationCode;
            preStatement.executeUpdate();
            System.out.println("add method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteById(Evenement ev) {
        String req = "DELETE  FROM `evenement` WHERE `id` = ?";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

            preStatement.setInt(1, ev.getId());

            preStatement.executeUpdate();

            System.out.println("deleteById method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // You can modify in an event : img_url
    //                              description(short and long)
    //                              location
    //                              date
    public void updateImage(Evenement ev, String newImg_url)  {
        ev.setImg_url(newImg_url);
        String req = "UPDATE `esprit`.`evenement` SET `img_url` = ?,`modified_at`=CURRENT_DATE() WHERE `id` = ?;";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

            preStatement.setString(1, newImg_url);
            preStatement.setInt(2, ev.getId());
            System.out.println(ev.getId());
            preStatement.executeUpdate();

            System.out.println("updateImage method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void updateShortDescription(Evenement ev, String newShortDescription) {
        ev.setShortDescription(newShortDescription);
        String req = "UPDATE `evenement` SET shortDescription=?,`modified_at`=CURRENT_DATE() WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

            preStatement.setString(1, newShortDescription);
            preStatement.setInt(2, ev.getId());

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateShortLongDescription(Evenement ev, String longDescription)  {
        ev.setLongDescription(longDescription);
        String req = "UPDATE `evenement` SET longDescription=?,`modified_at`=CURRENT_DATE() WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

            preStatement.setString(1, longDescription);
            preStatement.setInt(2, ev.getId());

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void updateLocalisation(Evenement ev, String localisation)  {
        ev.setLocalisation(localisation);
        String req = "UPDATE `evenement` SET localisation=?,`modified_at`=CURRENT_DATE() WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

            preStatement.setString(1, localisation);
            preStatement.setInt(2, ev.getId());

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void updateDate(Evenement ev, Date date)  {
        ev.setDate(date);
        String req = "UPDATE `evenement` SET date=?,`modified_at`=CURRENT_DATE() WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

            preStatement.setDate(1, date);
            preStatement.setInt(2, ev.getId());

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    //recherche methodes 
    public ArrayList<Evenement> read() {
        ArrayList<Evenement> tab = new ArrayList<>();
        String req = "SELECT * FROM `evenement`";
        try {   
            Statement ste = connection.createStatement();
            ResultSet result = ste.executeQuery(req);
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
                tab.add(ev);  
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally{
            return tab;
        }
        
    }
    public ArrayList<Evenement> read(int d) {
        ArrayList<Evenement> tab = new ArrayList<>();
        String req = "SELECT * FROM `evenement` where evenement.delegue_id="+d+"";
        try {   
            Statement ste = connection.createStatement();
            ResultSet result = ste.executeQuery(req);
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
                tab.add(ev);  
            }
        } catch (SQLException e) {
        } finally{
            return tab;
        }
        
    }

    }
