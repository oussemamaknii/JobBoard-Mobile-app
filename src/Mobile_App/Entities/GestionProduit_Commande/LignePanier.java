/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_App.Entities.GestionProduit_Commande;

import com.codename1.ui.Image;

/**
 *
 * @author toshiba
 */public class LignePanier {

    private int id;
    private int idProduit;// clé étrangère de la table produit
    private String nomProduit;
    private int idCommande;
    private double prix;
    private int quantite;
    private String image;

    @Override
    public String toString() {
        return "LignePanier{" + "id=" + id + ", idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", idCommande=" + idCommande + ", prix=" + prix + ", quantite=" + quantite + ", image=" + image + '}';
    }

    public LignePanier() {
    }

    public LignePanier(int idProduit, String nomProduit, double prix, int quantite, String image, int idCommande) {
        this.image=image;
        this.quantite = quantite;
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.idCommande=idCommande;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public double getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getImage() {
        return image;
    }


}
