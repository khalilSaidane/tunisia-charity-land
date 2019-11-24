/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import java.sql.Date;
/**
 *
 * @author PC
 */

public class Produit {
    private int id ; 
    private int delegue_id;
    private String img_url;
    private int price;
    private int quantity;
    private Date created_at;
    private String category;  
    private String description;
    private String name_prod;
    
    
    public Produit(){}
    public Produit(int id , int delegue_id, String img_url,int price , int quantity,Date created_at,String category,String description,String name_prod ) {
        this.id = id;
        this.delegue_id = delegue_id;
        this.img_url = img_url;
        this.price = price;
        this.quantity= quantity;
        this.created_at = created_at;
        this.category = category;
        this.description = description;
        this.name_prod=name_prod;
        
         
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDelegue_id() {
        return delegue_id;
    }

    public void setDelegue_id(int delegue_id) {
        this.delegue_id = delegue_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName_prod() {
        return name_prod;
    }

    public void setName_prod(String name_prod) {
        this.name_prod = name_prod;
    }

   
    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", delegue_id=" + delegue_id +  ", img_url=" + img_url +", price=" + price + ", quantity=" + quantity + ", created_at=" + created_at + ", category=" + category + ", description=" + description +", name_prod=" + name_prod+ '}';
    }
    
    
    
}
