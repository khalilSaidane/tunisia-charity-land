/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author PC
 */
public class order {
    private int id ; 
    private int user_id;
    private int quantity;
    private int price;
    private int delegue_id;
    private String name_prod;
    private int total;
    
    
    public order(){}
    public order(int id ,  int user_id,  int quantity,int price , int deleguet_id,String name_prod , int total) {
        this.id = id;
        this.user_id=user_id;
        this.quantity= quantity;
        this.price = price;
        this.delegue_id = delegue_id;
        this.name_prod=name_prod;
        this.total=total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDelegue_id() {
        return delegue_id;
    }

    public void setDelegue_id(int delegue_id) {
        this.delegue_id = delegue_id;
    
    }

    public String getName_prod() {
        return name_prod;
    }

    public void setName_prod(String name_prod) {
        this.name_prod = name_prod;
        
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return "order{" + "id=" + id + ", user_id=" + user_id +  ", quantity=" + quantity +", price=" + price + ", quantity=" + quantity + ", delegue_id=" + delegue_id  + ", name_prod="+name_prod+ ", total="+total+'}';
    }
   
}