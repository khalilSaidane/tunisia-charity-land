/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entity.Article;
//import entite.Order;
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
public class ArticleService {
     Connection connection;
     private Statement ste;

    public ArticleService() throws SQLException {
        connection = DataSource.getInstance().getConnection();
    }
      public List<Article> getByUserID(Integer r) {
         List<Article> part = new ArrayList<>();
        String req = "select * from article where id_auteur=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Article pa;
                pa = new Article(resultSet.getInt("id"), resultSet.getInt("id_auteur"),resultSet.getDate("date_creation"), resultSet.getString("titre"), resultSet.getString("contenu"), resultSet.getString("image"));
                System.out.println(pa);
                part.add(pa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return part;
    }
      public void ajouterArticle(Article p) throws SQLException
    {
         String query = " insert into article (id, id_auteur, date_creation , titre , contenu,image)"
            + " values (?, ?, ?, ?, ?,?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setInt (1, p.getId());
        preparedStmt.setInt (2, p.getId_auteur());
        preparedStmt.setDate (3, (Date) p.getDate_creation());
        preparedStmt.setString (4, p.getTitre());
        preparedStmt.setString (5, p.getContenu());
        preparedStmt.setString (6, p.getImage_url());
        
        // execute the preparedstatement
        preparedStmt.execute();
    }
      public void suppArticle(int x,int y) throws SQLException
    {
        String requete="DELETE FROM `article` WHERE id_auteur=? and `id`=?;";
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
    public void modArticle (String x, int a) throws SQLException
    {
        String requete="UPDATE `article` SET `titre`=? WHERE `id`=? ;";
          PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, x);
         
            preparedStatement.setInt(2, a);
               preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public ArrayList<Article> rechercherArt( String x, Integer a) {
         ArrayList<Article> part = new ArrayList<>();
        String req = "select * from article where  id_auteur="+a+" and titre like "+x;
        System.out.println(req);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
           // preparedStatement.setInt(1, r);
       
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Article pa;
               pa = new Article(resultSet.getInt("id"), resultSet.getInt("id_auteur"),resultSet.getDate("date_creation"), resultSet.getString("titre"), resultSet.getString("contenu"), resultSet.getString("image"));
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
         ResultSet RS = stmt.executeQuery("select * from article where id_auteur="+x+" and id like "+a);
         if (RS.next())
         {//System.out.println("True");
             return true ;
         
         }
         //System.out.println("False");
         return false ;
       }
}
    