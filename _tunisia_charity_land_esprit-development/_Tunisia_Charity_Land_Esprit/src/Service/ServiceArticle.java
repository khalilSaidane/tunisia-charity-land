/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Article;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class ServiceArticle {
       private Connection connection;
    private Statement statement;

    public ServiceArticle() throws SQLException {
        connection = DataSource.getInstance().getConnection();
    }
    
    public void add(Article a)  {
        /*String req = "INSERT INTO esprit.`evenement` (id, delegue_id, img_url, localisation, date, type, shortDescription,"
                + " longDescription, created_at, modified_at) "
                + "VALUES (NULL, ?, ?, ?, ?, ?,?,?, CURRENT_DATE(),CURRENT_DATE());";*/
        
       String req = "INSERT INTO esprit.`article` (`id`, `id_auteur`, `date_creation`, `titre`, `contenu`, `image`)"
               + " VALUES (NULL, ?, CURRENT_DATE(), ?, ?, ?);";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

//          preStatement.setInt(1, ev.getId());
             preStatement.setInt(1,    a.getId_auteur());
         
            preStatement.setString(2, a.getTitre());
            preStatement.setString(3, a.getContenu());
            preStatement.setString(4, a.getImage_url());

            preStatement.executeUpdate();
            System.out.println("add method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
        public void updateTitre(Article a, String titre)  {
        a.setTitre(titre);
        String req = "UPDATE `article` SET titre=? WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

            preStatement.setString(1, titre);
            preStatement.setInt(2, a.getId());

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
        
        public void updateContenu(Article a, String contenu)  {
        a.setContenu(contenu);
        String req = "UPDATE `article` SET contenu=? WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

            preStatement.setString(1, contenu);
            preStatement.setInt(2, a.getId());

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
        
        public void updateImage(Article a, String image_url)  {
        a.setImage_url(image_url);
        String req = "UPDATE `article` SET image=? WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

            preStatement.setString(1, image_url);
            preStatement.setInt(2, a.getId());

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
        
        public ArrayList<Article> read() {
        ArrayList<Article> tab = new ArrayList<>();
        String req = "SELECT * FROM `article`;";
        try {   
            Statement ste = connection.createStatement();
            ResultSet result = ste.executeQuery(req);
            while (result.next()) {
                int id = result.getInt(1);
                int id_auteur = result.getInt(2) ;
                Date date = result.getDate(3);
                
                String titre = result.getString(4);
                String contenu = result.getString(5);
                String image_url = result.getString(6);
                
                //in every etiration we create a new instance 
                Article a = new Article(id,id_auteur,date,titre,contenu,image_url);
             
                tab.add(a); 
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
             return tab;
        }
        }   
        
        public void deleteById(Article a) {
        String req = "DELETE  FROM `article` WHERE `id` = ? ;";
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement(req);

            preStatement.setInt(1, a.getId());

            preStatement.executeUpdate();

            System.out.println("deleteById method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static class getInstance {

        public getInstance() {
        }
    }
    }

