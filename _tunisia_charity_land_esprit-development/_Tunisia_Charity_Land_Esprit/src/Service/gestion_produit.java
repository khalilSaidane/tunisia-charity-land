/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entity.Produit;
import Utils.Datasourc;
import java.sql.*;

import java.util.ArrayList;
/**
 *
 * @author PC
 */



    public class gestion_produit {

private static Statement ste;
        Connection con=Datasourc.getInstance().getConnexion();
    
        public gestion_produit(){
             try{
                ste=con.createStatement();
            }catch(SQLException e){
                System.out.println(e);
            }
        }
    public void add(Produit pr)  {
        String req = "INSERT INTO products (id, delegue_id, img_url, price, quantity, created_at, category, description,name_prod )"
                + "VALUES (?, ?, ?, ?, ?,?,?,?,?)";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setInt(1, pr.getId());
            preStatement.setInt(2, pr.getDelegue_id());
            preStatement.setString(3, pr.getImg_url());
            preStatement.setInt(4, pr.getPrice());
            preStatement.setInt(5, pr.getQuantity());
            preStatement.setDate(6, pr.getCreated_at());
            preStatement.setString(7, pr.getCategory());
            preStatement.setString(8, pr.getDescription());
            preStatement.setString(9, pr.getName_prod());
            preStatement.execute();
            System.out.println("add method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void deleteById(Produit pr) {
        String req = "DELETE  FROM `products` WHERE `id` = ?";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setInt(1, pr.getId());

            preStatement.executeUpdate();

            System.out.println("deleteById method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
public void updateImage(Produit pr, String newImg_url)  {
        pr.setImg_url(newImg_url);
        String req = "UPDATE `esprit`.`products` SET `img_url` = ?,`modified_at`=CURRENT_DATE() WHERE `id` = ?;";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setString(1, newImg_url);
            preStatement.setInt(2, pr.getId());
            System.out.println(pr.getId());
            preStatement.executeUpdate();

            System.out.println("updateImage method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
      public void updatePrice(Produit pr, int nprice)  {
       // pr.setPrice(nprice);
        String req = "UPDATE `products` SET price=? WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setInt(1, nprice);
            preStatement.setInt(2, pr.getId());

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
        public void updateQuantité(Produit pr, int nquantity)  {
        //pr.setQuantity(nquantity);
        String req = "UPDATE `products` SET quantity=? WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setInt(1, nquantity);
            preStatement.setInt(2, pr.getId());

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
            public ArrayList<Produit> read() {
        ArrayList<Produit> tab = new ArrayList<Produit>();
        String req = "SELECT * FROM `products`" ;
        try {   
            Statement ste = con.createStatement();
            ResultSet result = ste.executeQuery(req);
            while (result.next()) {
                int id = result.getInt(1);
                int delegue_id = result.getInt(2) ;
                String img_url = result.getString(3);
                int price = result.getInt(4);
                int quantity = result.getInt(5);
                Date created_at = result.getDate(6);
                String category = result.getString(7);
                String description = result.getString(8);
                String name_prod = result.getString(9);
                
               
                               
                //in every etiration we create a new instance 
                Produit pr = new Produit(id,delegue_id,img_url,price,quantity,created_at,category,description,name_prod);
                pr.setCreated_at(created_at);
                tab.add(pr);  
            }
        } catch (SQLException e) {
        } finally{
            return tab;
        }
}
    public  Produit rechercheprod(String url) throws SQLException {
        Produit pr = new Produit();
        String req = "SELECT * FROM `products` where img_url= ? ";
       PreparedStatement preStatement = con.prepareStatement(req);
            preStatement.setString(1,url);
            ResultSet result = preStatement.executeQuery();
            
            
            while (result.next()) {
                pr.setId(result.getInt(1));
                pr.setDelegue_id ( result.getInt(2)) ;
                pr.setImg_url ( result.getString(3));
                pr.setPrice ( result.getInt(4));
                pr.setQuantity (result.getInt(5));
                pr.setCreated_at ( result.getDate(6));
                pr.setCategory ( result.getString(7));
                pr.setDescription ( result.getString(8));
                pr.setName_prod ( result.getString(9));
          //in every etiration we create a new instance
            }
       
            return pr;
        }
    }
        
    
