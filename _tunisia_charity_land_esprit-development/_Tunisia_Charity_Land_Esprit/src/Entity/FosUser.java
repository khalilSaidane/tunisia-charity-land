/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import java.util.Date;
/**
 *
 * @author wissem
 */
public class FosUser {
    private int id;
    private String username;
    private String email;
    private int enabled;
    private String salt;
    private String password;
    private Date last_login;
    private int locked;
    private int expired;
    private Date expires_at;
    private String confirmation_token;
    private Date password_requested_at;
    private String roles;
    private int crendentials_expired;
    private Date crendentials_expires_at;
    private int phone_nbr;
    private String image_url;
    private String adresse;
    public FosUser() {
    }

    //this is for the User part
    
    public FosUser(int user_id, String username, String email, String password, String adresse, int phone_nbr, String roles, String image_url) {
        this.id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.roles = roles;
        this.image_url= image_url;
    }

    public FosUser( String username, String email, String password) {
        
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public Date getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(Date expires_at) {
        this.expires_at = expires_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
        
}
