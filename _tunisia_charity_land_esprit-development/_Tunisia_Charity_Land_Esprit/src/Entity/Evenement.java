/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author machd
 */
public class Evenement {

    private  int id ; //no set method
    private int delegue_id; //no set method
    private String img_url;
    private String localisation;
    private String shortDescription;
    private String longDescription;
    private Date created_at;
    private Date modified_at;
    private Date date;
    private Type type;//no set method
    private int validation_status;
    private String eventType;
    public enum Type{
        ENVIRONMENTAL,
        SANTE,
        EDUCATION,
        INTERNATIONAL;
    }

    public Evenement() {
    }

    public Evenement(int id , int delegue_id, String img_url, String localisation, Date date, Type type,String shortDescription,String longDescription, int validation_status) {
        this.id = id;
        this.delegue_id = delegue_id;
        this.img_url = img_url;
        this.localisation = localisation;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.validation_status = validation_status;
        this.type = type;
        this.date= date; 
    }
    
    public Evenement(int id, String shorDescription, int delegue_id,String longDescription, Date date, Type type,int validation_status){
        this.id=id;
        this.shortDescription=shorDescription;
        this.delegue_id=delegue_id;
        this.longDescription=longDescription;
        this.date= date;
        this.type = type;
        this.validation_status = validation_status;
    }

    public Evenement(int id, int delegue_id, String shortDescription, String longDescription, Date created_at,String eventType, int validation_status) {
        this.id = id;
        this.delegue_id = delegue_id;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.created_at = created_at;
        this.validation_status = validation_status;
        this.eventType = eventType;
    }
    
    
    

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    
   
    public int getId() {
        return id;
    }

    public int getDelegue_id() {
        return delegue_id;
    }


    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public Date getDate() {
        return date;
    }

    public Type getType() {
        return type;
    }

    public void setDate(Date date) {
        this.date = date;
    }
//  we need these two methods in read service 
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public int getValidation_status() {
        return validation_status;
    }

    public void setValidation_status(int validation_status) {
        this.validation_status = validation_status;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", delegue_id=" + delegue_id + ", img_url=" + img_url + ", localisation=" + localisation + ", shortDescription=" + shortDescription + ", longDescription=" + longDescription + ", created_at=" + created_at + ", modified_at=" + modified_at + ", date=" + date + ", type=" + type + ", validation_status=" + validation_status + '}';
    }
    
}
