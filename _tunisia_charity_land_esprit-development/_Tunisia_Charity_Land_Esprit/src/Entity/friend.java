/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;



/**
 *
 * @author leanbois
 */
public class friend {
    public String username;
    public String friendusername;
    public friend (String username,String friendusername){
        this.username=username;
        this.friendusername=friendusername;
    }

    public friend() {
     
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendusername() {
        return friendusername;
    }

    public void setFriendusername(String friendusername) {
        this.friendusername = friendusername;
    }
    
}
