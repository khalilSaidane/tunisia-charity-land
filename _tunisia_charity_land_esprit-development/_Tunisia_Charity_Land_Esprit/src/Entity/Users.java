/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author oflcad
 */
public class Users extends FosUser {

    private int id;
    private String username;
    private String email;
    private int enabled;
    private String password;
    private Date last_login;
    private int locked;
    private int expired;
    private Date expires_at;
    private String confirmation_token;
    private String roles;
    private String adresse;
    
    public Users () {
        
    }

    public Users(int id, String username, String email, int enabled, String password, Date last_login, int locked, int expired, Date expires_at, String confirmation_token, String roles, String adresse) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
        this.last_login = last_login;
        this.locked = locked;
        this.expired = expired;
        this.expires_at = expires_at;
        this.confirmation_token = confirmation_token;
        this.roles = roles;
        this.adresse = adresse;
    }
    
    
    public Users(int id, String username, String email, int enabled, Date last_login, int locked, Date expires_at){
        this.id = id;
        this.username= username;
        this.email = email;
        this.enabled = enabled;
        this.last_login = last_login;
        this.locked = locked;
        this.expires_at = expires_at;
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

   

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
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

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", username=" + username + ", email=" + email + ", enabled=" + enabled + ", password=" + password + ", last_login=" + last_login + ", locked=" + locked + ", expired=" + expired + ", expires_at=" + expires_at + ", confirmation_token=" + confirmation_token + ", roles=" + roles + ", adresse=" + adresse + '}';
    }

    
}
