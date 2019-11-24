/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.public_chat;
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
public class public_chatservice {
            private static Statement ste;
        Connection con=Datasourc.getInstance().getConnexion();
    
        public public_chatservice(){
             try{
                ste=con.createStatement();
            }catch(SQLException e){
                System.out.println(e);
            }
        }
         public void ajouterchat(public_chat p) throws SQLException{
    String query = " insert into public_chat (username, message)"
            + " values (?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString (1, p.getUsername());
preparedStmt.setString (2, p.getMeassage());
        // execute the preparedstatement
        preparedStmt.execute();
    }
         public ArrayList<public_chat> afficherPersonnes() throws SQLException{
            ArrayList<public_chat> room = new ArrayList<public_chat>();
            String request = "SELECT * FROM public_chat";
            try {
                ResultSet rs = ste.executeQuery(request);
                while(rs.next()){
                    String username = rs.getString(1);
                    String message = rs.getString(2);
                    public_chat p = new public_chat(username, message);
                    room.add(p);
                    
                }
                           
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            return room;
        }
}
