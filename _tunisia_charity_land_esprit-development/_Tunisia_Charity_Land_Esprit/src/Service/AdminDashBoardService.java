/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Article;
import Entity.Evenement;
import Entity.Users;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author oflcad
 */
public class AdminDashBoardService {
    
    private Connection connection;
    private Statement statement;
    
    public AdminDashBoardService() throws SQLException{
        connection = DataSource.getInstance().getConnection();
    }
    
    
   
    
    
    public ArrayList<Users> getAllUsersFromDatabase(){
        ArrayList<Users> usersList = new ArrayList<>();
        String query = "SELECT * FROM fos_user;";
        
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                int id = result.getInt("id");
                String username = result.getString("username");
                String email = result.getString("email");
                int enabled = result.getInt("enabled");
                Date last_login = result.getDate("last_login");
                int locked = result.getInt("locked");
                Date expires = result.getDate("credentials_expires_at");
                Users user = new Users(id,username,email,enabled,last_login,locked,expires);
                usersList.add(user);
                 for(int i = 0;i<usersList.size();i++){
                   System.out.println(usersList.get(i));
                     }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
        return usersList;
    }
    
    
    
    public void deleteSelectedArticle(Article article) throws SQLException {
        int selectedId = article.getId();
        System.out.println(selectedId);
        String query = "DELETE FROM `article` WHERE `id`="+selectedId+";";
        statement.executeUpdate(query);
        
        
    }
    
    
}
