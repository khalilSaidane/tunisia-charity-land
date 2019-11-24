/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Users;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.UUID;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


/**
 *
 * @author oflcad
 */
public class UserAuthenticationService {
    
    private final Connection connection;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    PreparedStatement preparedStatement;
    public Users user;
    private boolean isUsernameValid =false;
    
    private boolean isPasswordValid = false;
    public int count = 0;
    private String userValidateError;
    private String emailFieldError;
    private String passwordFieldError;
    public UserAuthenticationService() throws SQLException {
        connection = DataSource.getInstance().getConnection();
    }
    
    /*
    * return user object to authenticate user
    */
    public Users authenticateUserWithCrendentials(String username, String password){
            Users user = new Users();
          
            
            String query = "SELECT * from fos_user WHERE username = ? AND password = ?;"; 
            try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setEnabled(rs.getInt("enabled"));
                
                user.setPassword(rs.getString("password"));
                user.setLast_login(rs.getDate("last_login"));
                user.setLocked(rs.getInt("locked"));
                user.setExpired(rs.getInt("expired"));
                user.setExpires_at(rs.getDate("expires_at"));
                user.setConfirmation_token(rs.getString("confirmation_token"));
                user.setRoles(rs.getString("roles"));
                user.setAdresse(rs.getString("adresse"));
                user.setLocked(rs.getInt("locked"));
                user.setLast_login(rs.getDate("last_login"));
                System.out.println("console log from auth Service : "+user.getId());
                System.out.println(user.toString());
               
                return user;
            }

        } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Error! we can't communicate with the database ");
        }
        
        return user;
    }
    
    
    
    /*
    * returns a user object populated with data from database
    */
    public Users getAdminInfo(int id){
        String query = "SELECT * FROM fos_user WHERE id=? and roles='admin'";
        Users user = new Users();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                if(id == user.getId()){
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRoles(rs.getString("roles"));
                    user.setExpires_at(rs.getDate("expires_at"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
       return user;
    }
    
    /*
    *return an new ID from database
    */
    public int getNewIdFromDatabase(){
        int id = 0;
        String query = "SELECT * FROM fos_user ORDER BY id DESC LIMIT 1";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
                
                while(result.next()){
                    id = result.getInt("id");
                    //System.out.println("this is our  new ID :"+ id +1);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id + 1;
    }
    
    
    
    /*
    * This method is used to validate the username field from SignupConroller 
    */
    public boolean validateUserName(String username){
        if(username.equals("")){
            System.out.println("username is empty");
            isUsernameValid = false;   
        } else {
            System.out.println("username is valid : "+ username);
            isUsernameValid = true;
        }
        return isUsernameValid;
    }
    
    
    /*
    * REGEX (regular expression) Email Validation
    */
    public static boolean validateEmail(String email){
        
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null){
            System.out.println("error in email validation !: line 175");
                return false; 
        }
            
        System.out.println("Valid Email !  is : "+pat.matcher(email).matches());
        return pat.matcher(email).matches();
    }
    
    
    /*
    * This method is used to validate the username field from SignupConroller
    */
    public boolean checkPassword(String password, String secondPassword){
        
        if(password.length()>8 && secondPassword.length()>8){
                if(password.equals(secondPassword)){
                    System.out.println("passwords are identical: returning true");
                    return isPasswordValid = true;
            }
        }

        return isPasswordValid;
    }
    
     /*
    * Database check for username existance
    */
    public boolean checkDatabaseUsernames(String username){
        String query = "SELECT username FROM fos_user WHERE username ='"+username+"' ";
        boolean doesExist = false;
        
        try {
            Statement ste = connection.createStatement();
            ResultSet result = ste.executeQuery(query);
            while(result.next()){
                System.out.println("we came back from database with this value : line 304");
                System.out.println(result.getString("username"));
                count++;
            }
            
            if(count == 0){
                System.out.println("No match with database ! check database is true : ");
                doesExist = true;
            }    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return doesExist;
    }
    
    /*
    * retrieve from database confirmation token
    */
    public String getConfirmationToken(String email) {
        String confirmation_token = "";
        String query = "SELECT confirmation_token from fos_user WHERE email = '"+email+"'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
                
                while(result.next()){
                    confirmation_token = result.getString("confirmation_token");
                    System.out.println("this is our confirmation_token retrived from database :"+ confirmation_token);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return confirmation_token;
    }

    
     
     /*
     * Personal user input validation
     */
    public boolean validateUserInputFields(String username, String email, String password, String secondPassword){
            boolean isClear =false;
            if(validateUserName(username)){
                if(checkDatabaseUsernames(username)){
                    if(validateEmail(email)) {
                        if(checkPassword(password, secondPassword)){
                            isClear = true;
                        }
                    }
                }
            }
        return isClear;
    }
   
    
    
    
    /*
    * Insert new user with role set as user into database;
    */
    public boolean insertNewUserIntoDatabase(String username, String email, String password, String secondPassword){
        boolean isInserted = false;
        String confirmation_token = genrateConfirmationToken();
        int newID = getNewIdFromDatabase();
        boolean isChecked = validateUserInputFields(username, email, password, secondPassword);
        System.out.println("database check return :"+isChecked);
        Calendar calendar = Calendar.getInstance();
            java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
        if(isChecked){
            
            String query = "INSERT INTO fos_user (id,username,email,password,enabled,confirmation_token,roles,last_login,expires_at,password_requested_at,credentials_expires_at) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, newID);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setInt(5, 0);
                preparedStatement.setString(6, confirmation_token);
                preparedStatement.setString(7, "user");
                preparedStatement.setDate(8, ourJavaDateObject);
                preparedStatement.setDate(9, ourJavaDateObject);
                preparedStatement.setDate(10, ourJavaDateObject);
                preparedStatement.setDate(11, ourJavaDateObject);
                preparedStatement.executeUpdate();
                System.out.println("please check database new user is created");
                isInserted = true;
              } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("database insertion"+isInserted);
        return isInserted;
    }
    

    /*
    * Insert new user with role set as user into database;
    */
    public boolean inserNewAssociationIntoDatabase(String name,String email, String password, String secondPassword){
            boolean isInserted = false;
            String confirmation_token = genrateConfirmationToken();
            int newId = getNewIdFromDatabase();
            
            Calendar calendar = Calendar.getInstance();
            java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
            
                if(validateUserInputFields(name,email,password,secondPassword)){
                    String query = "INSERT INTO fos_user (id,username,email,password,enabled,confirmation_token,roles,last_login,expires_at,password_requested_at,credentials_expires_at) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, newId);
                        preparedStatement.setString(2, name);
                        preparedStatement.setString(3, email);
                        preparedStatement.setString(4, password);
                        preparedStatement.setInt(5, 0);
                        preparedStatement.setString(6, confirmation_token);
                        preparedStatement.setString(7, "association");
                        preparedStatement.setDate(8, ourJavaDateObject);
                        preparedStatement.setDate(9, ourJavaDateObject);
                        preparedStatement.setDate(10, ourJavaDateObject);
                        preparedStatement.setDate(11, ourJavaDateObject);
                        preparedStatement.executeUpdate();
                        System.out.println("please check database new user is created");
                        isInserted= true;   
                    } catch (SQLException e) {
                        Logger.getLogger(UserAuthenticationService.class.getName()).log(Level.SEVERE, null, e);
                        //TODO: handle exception
                    }
                }
        return isInserted;       
    }
    
   
    
    
    /*
     * This method generate random String used for confirmation Token
    */
    public static String genrateConfirmationToken() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

   

    
}
