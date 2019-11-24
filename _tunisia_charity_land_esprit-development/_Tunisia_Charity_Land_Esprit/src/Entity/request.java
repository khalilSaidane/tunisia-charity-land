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
public class request {
       public String user_send;
    public String user_receive;
    public String msg;
    public request (String username,String friendusername,String msg){
        this.user_send=username;
        this.user_receive=friendusername;
        this.msg=msg;
    }

    public request() {
    
    }

    public String getUser_send() {
        return user_send;
    }

    public void setUser_send(String user_send) {
        this.user_send = user_send;
    }

    public String getUser_receive() {
        return user_receive;
    }

    public void setUser_receive(String user_receive) {
        this.user_receive = user_receive;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
