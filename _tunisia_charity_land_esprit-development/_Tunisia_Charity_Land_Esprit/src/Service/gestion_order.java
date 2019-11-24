/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entity.order;
import Utils.Datasourc;
import java.sql.*;
import GUI.CartController;
import java.util.ArrayList;
import Entity.Produit;

/**
 *
 * @author PC
 */
public class gestion_order {
    private static Statement ste;
        Connection con=Datasourc.getInstance().getConnexion();
    
        public gestion_order(){
             try{
                ste=con.createStatement();
            }catch(SQLException e){
                System.out.println(e);
            }
        }
          public void addtocart(order o)  {
        String req = "INSERT INTO orders (id, user_id, quantity, price, delegue_id ,name_prod,total)"
                + "VALUES (?, ?, ?, ?, ?,?,?)";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setInt(1, o.getId());
            preStatement.setInt(2, o.getUser_id());
            preStatement.setInt(3, o.getQuantity());
            preStatement.setInt(4, o.getPrice());
            preStatement.setInt(5, o.getDelegue_id());
            preStatement.setString(6, o.getName_prod());
            preStatement.setInt(7, (o.getQuantity())*(o.getPrice()));
           
            preStatement.execute();
            System.out.println("add method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    
}
            public ArrayList<order> readcart(int uid) throws SQLException {
        ArrayList<order> tab = new ArrayList<order>();
        String req = "SELECT * FROM `orders` where user_id=? " ;
        
        PreparedStatement preStatement = con.prepareStatement(req);
            preStatement.setInt(1, uid);
            ResultSet result = preStatement.executeQuery();
            
          
            while (result.next()) {
                int id = result.getInt(1);
                int user_id = result.getInt(2) ;
                int quantity = result.getInt(3);
                int price = result.getInt(4);
                int delegue_id = result.getInt(5) ;
                String name_prod = result.getString(6);
                int total=result.getInt(7);
               
                               
                //in every etiration we create a new instance 
                order oi = new order(id,user_id,quantity,price,delegue_id,name_prod,total);
                 tab.add(oi);  
       }
            return tab;
        
            }
         public int total (int uid) throws SQLException{
        String req = "SELECT SUM(total) as sum FROM `orders` where user_id= '"+uid+"'" ;
       
        PreparedStatement preStatement;
         
            Statement ste = con.createStatement();
            //preStatement = con.prepareStatement(req);
            ResultSet result = ste.executeQuery(req);
           while(result.next()) {
            int sum= result.getInt("sum");
                   
      
            return sum;
        }
          return 0; 
         }
}
          

    













         
                    
            
 




   
    
