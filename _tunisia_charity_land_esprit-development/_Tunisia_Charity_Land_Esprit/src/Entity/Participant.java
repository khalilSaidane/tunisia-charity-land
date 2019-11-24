/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import java.util.*;

/**
 *
 * @author Raed
 */
public class Participant {
    private int eventid;
    private int userid;
    private Date date;

    public Participant(int eventid, int userid, Date date) {
        this.eventid=eventid;
        this.userid = userid;
        this.date = date;
    }

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

   

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
