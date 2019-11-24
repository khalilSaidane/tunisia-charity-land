/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


/**
 *
 * @author leanbois
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class Datasourc {
    private static String url="jdbc:mysql://localhost:3306/esprit";
    private static  String user="root";
    private static  String pass="";
    private static Datasourc Connection;
    private static Connection con;
    private static Datasourc data;
    private Datasourc(){
        try{
       con=DriverManager.getConnection(url,user,pass);
       System.out.println("connexion etablit");
       }catch(SQLException e){
           System.out.println(e);
       }
   }
   public Connection getConnexion(){
       return con;
   }
   public static Datasourc getInstance(){
       if(data==null){
           data =new Datasourc();
          
       }
        return data;
      
   }
    
}