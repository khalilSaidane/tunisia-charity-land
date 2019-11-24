/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.friend;
import Utils.Datasourc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author leanbois
 */
public class friendservice {
                  private static Statement ste;
        Connection con=Datasourc.getInstance().getConnexion();
    
        public friendservice(){
             try{
                ste=con.createStatement();
            }catch(SQLException e){
                System.out.println(e);
            }
    
}
          public ArrayList<friend> afficherfriend(String a) throws SQLException{
            ArrayList<friend> list = new ArrayList<friend>();
            String request = "SELECT * FROM friends WHERE (username = '"+a+"')";
            try {
                ResultSet rs = ste.executeQuery(request);
                while(rs.next()){
                    String username = rs.getString(1);
                    String friendusername = rs.getString(2);
                    friend p = new friend(username, friendusername);
                    list.add(p);
                    
                }
                 } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            return list;
        }
           public void ajouterfriend(friend p) throws SQLException{
    String query = " insert into friends (username, friendusername)"
            + " values (?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString (1, p.getUsername());
preparedStmt.setString (2, p.getFriendusername());
        // execute the preparedstatement
        preparedStmt.execute();
    }
}
          