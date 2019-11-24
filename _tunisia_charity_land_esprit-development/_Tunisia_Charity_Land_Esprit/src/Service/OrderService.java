/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Order2;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.DataSource;

/**
 *
 * @author Raed
 */
public class OrderService {
       Connection connection;
     private Statement ste;

    public OrderService() throws SQLException {
        connection = DataSource.getInstance().getConnection();
    }
      public List<Order2> getByUserID(Integer r) {
         List<Order2> part = new ArrayList<>();
        String req = "select * from orders where user_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Order2 pa;
                pa = new Order2(resultSet.getInt("id"), resultSet.getInt("user_id"),resultSet.getInt("quantity"), resultSet.getInt("price"), resultSet.getInt("delegue_id"));
                System.out.println(pa);
                part.add(pa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return part;
    }
      public void ajouterOrder(Order2 p) throws SQLException
    {
         String query = " insert into orders (id, user_id, quantity , price , delegue_id)"
            + " values (?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setInt (1, p.getProductid());
        preparedStmt.setInt (2, p.getUserid());
        preparedStmt.setInt (3, p.getQuantite());
        preparedStmt.setInt (4, p.getPrix());
        preparedStmt.setInt (5, p.getProductowner());

        // execute the preparedstatement
        preparedStmt.execute();
    }
      public boolean suppOrder(int x, int y) throws SQLException
    {   boolean g = false;
        String requete="DELETE FROM `orders` WHERE `id`=? and user_id=?;";
          PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, x);
            preparedStatement.setInt(2, y);
            preparedStatement.executeUpdate();
            g=true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }return g;
    }
    public boolean modOrder (int x, int a,int y) throws SQLException
    {
        boolean g;
        String requete="UPDATE `orders` SET `quantity`=? WHERE `id`=? and user_id=? ;";
          PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, x);
            preparedStatement.setInt(2, a);
            preparedStatement.setInt(3, y);
            preparedStatement.executeUpdate();
            g=true;
        } catch (SQLException ex) {
            System.out.println(ex);
            g = false ;
        }
        return g;
    }
    public boolean rech(int a, int x) throws SQLException{
           Statement stmt = connection.createStatement();
         ResultSet RS = stmt.executeQuery("select * from orders where user_id="+x+" id like "+a);
         if (RS.next())
         {//System.out.println("True");
             return true ;
         
         }
         //System.out.println("False");
         return false ;
       }
      public ArrayList<Order2> rechercherOrder( int x, Integer a) {
         ArrayList<Order2> part = new ArrayList<>();
        String req = "select * from orders where  user_id="+a+" and id like '%"+x+"%'";
        System.out.println(req);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
           // preparedStatement.setInt(1, r);
       
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Order2 pa;
              pa = new Order2(resultSet.getInt("id"), resultSet.getInt("user_id"),resultSet.getInt("quantity"), resultSet.getInt("price"), resultSet.getInt("delegue_id"));
                System.out.println(pa);
                part.add(pa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return part;
    }
}
