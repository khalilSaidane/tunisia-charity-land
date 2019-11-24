/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Article {
    
    private int id;

    private int id_auteur;

    private Date date_creation;

    private String titre;

    private String contenu;

    private String image_url;



    public Article() {
    }

    public Article(int id, int id_auteur, String titre, String contenu, String image_url) {
        this.id = id;
        this.id_auteur = id_auteur;
        this.titre = titre;
        this.contenu = contenu;
        this.image_url = image_url;
    }

    public Article(int id, int id_auteur, Date date_creation, String titre, String contenu, String image_url) {
        this.id = id;
        this.id_auteur = id_auteur;
        this.date_creation = date_creation;
        this.titre = titre;
        this.contenu = contenu;
        this.image_url = image_url;
    }

    public Article(int id_auteur, String titre, String contenu, String image_url) {
        this.id_auteur = id_auteur;
        this.titre = titre;
        this.contenu = contenu;
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return  "\n\n\n\n\n" + "id=" + id + ",\n id_auteur=" + id_auteur + ",\n date_creation=" + date_creation + ",\n titre=" + titre + ",\n contenu=" + contenu + ",\n image_url=" + image_url;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public int getId_auteur() {
        return id_auteur;
    }

    public void setId_auteur(int id_auteur) {
        this.id_auteur = id_auteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
        public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

}
