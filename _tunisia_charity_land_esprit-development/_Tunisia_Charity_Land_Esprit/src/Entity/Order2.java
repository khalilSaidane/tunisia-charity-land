/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Raed
 */
public class Order2 {
    private int productid;
    private int userid;
    private int quantite;
    private int prix ;
    private int productowner;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getProductowner() {
        return productowner;
    }

    public void setProductowner(int productowner) {
        this.productowner = productowner;
    }

    public Order2(int productid, int userid, int quantite, int prix, int productowner) {
        this.productid = productid;
        this.userid = userid;
        this.quantite = quantite;
        this.prix = prix;
        this.productowner = productowner;
    }
    
           
}
