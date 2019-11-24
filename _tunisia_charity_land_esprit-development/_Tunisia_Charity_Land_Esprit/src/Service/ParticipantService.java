/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entity.Participant;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import Utils.DataSource;

/**
 *
 * @author Raed
 */
public class ParticipantService {
     Connection connection;
     private Statement ste;

    public ParticipantService() throws SQLException {
        connection = DataSource.getInstance().getConnection();
    }
     public List<Participant> getByUserID(Integer r) {
         List<Participant> part = new ArrayList<>();
        String req = "select * from participant where user_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Participant pa;
                pa = new Participant(resultSet.getInt("event_id"), resultSet.getInt("user_id"), resultSet.getDate("due_date"));
                System.out.println(pa);
                part.add(pa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return part;
    }
      public void ajouterParticipant(Participant p) throws SQLException
    {
         String query = " insert into participant (event_id, user_id, due_date)"
            + " values (?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setInt (1, p.getEventid());
        preparedStmt.setInt (2, p.getUserid());
        preparedStmt.setDate (3, (Date) p.getDate());
        
        // execute the preparedstatement
        preparedStmt.execute();
    }
      public void suppPart(int x, int y) throws SQLException
    {
        String requete="DELETE FROM `participant` WHERE `event_id`=? and user_id =? ;";
          PreparedStatement preparedStatement;
         
        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, x);
            preparedStatement.setInt(2, y);
            preparedStatement.executeUpdate();
             
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
        
    }
       public ArrayList<Participant> rechercherPart( Integer x, Integer a) {
         ArrayList<Participant> part = new ArrayList<>();
        String req = "select * from participant where user_id="+a+" and event_id like '%"+x+"%'";
        System.out.println(req);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
           // preparedStatement.setInt(1, r);
       
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Participant pa;
                pa = new Participant(resultSet.getInt("event_id"), resultSet.getInt("user_id"), resultSet.getDate("due_date"));
                System.out.println(pa);
                part.add(pa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return part;
    }
       public boolean rech(int a, int x) throws SQLException{
           Statement stmt = connection.createStatement();
         ResultSet RS = stmt.executeQuery("select * from participant where user_id="+x+" and event_id like "+a);
         if (RS.next())
         {//System.out.println("True");
             return true ;
         
         }
         //System.out.println("False");
         return false ;
       }
       public void add(int id_user,int id_ev) throws SQLException{
           

        String query = "INSERT INTO esprit.`participant` (event_id, user_id, due_date)"
                   + " VALUES (?, ?, CURRENT_TIME());";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(query);

            preStatement.setInt(1, id_ev);
            preStatement.setInt(2,id_user);
            
            preStatement.executeUpdate();
            System.out.println("add method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
       }
       }
}
