/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.request;
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
public class requestservice {
                      private static Statement ste;
        Connection con=Datasourc.getInstance().getConnexion();
    
        public requestservice(){
             try{
                ste=con.createStatement();
            }catch(SQLException e){
                System.out.println(e);
            }
    
}
                  public void ajouterrequest(request p) throws SQLException{
    String query = " insert into friend_request (user_send, user_receive, msg)"
            + " values (?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString (1, p.getUser_send());
preparedStmt.setString (2, p.getUser_receive());
preparedStmt.setString (3, p.getMsg());
        // execute the preparedstatement
        preparedStmt.execute();
    }
                       public ArrayList<request> afficherrequest(String a) throws SQLException{
            ArrayList<request> list = new ArrayList<request>();
            String request = "SELECT * FROM friend_request WHERE (user_receive = '"+a+"')";
            try {
                ResultSet rs = ste.executeQuery(request);
                while(rs.next()){
                    String user_send = rs.getString(1);
                    String user_receive = rs.getString(2);
                    String msg = rs.getString(3);
                    request p = new request(user_send, user_receive, msg);
                    list.add(p);
                    
                }
                 } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            return list;
        }
        public void deleteByuser(String a,String b) {
        String req = "DELETE  FROM friend_request WHERE (user_send = ?) AND (user_receive = ?)";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setString(1, a);
            preStatement.setString(2, b);

            preStatement.executeUpdate();

        
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
