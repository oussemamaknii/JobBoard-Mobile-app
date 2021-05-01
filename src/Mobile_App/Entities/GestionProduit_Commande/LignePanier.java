/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_App.Entities.GestionProduit_Commande;

/**
 *
 * @author toshiba
 */
public class LignePanier {
    private int id;
    private int $quantite;
    private int $idCommande;// clé étrangère de la table commande
    private int $idProduit;// clé étrangère de la table produit 

    public LignePanier(int $quantite, int $idCommande, int $idProduit) {
        this.$quantite = $quantite;
        this.$idCommande = $idCommande;
        this.$idProduit = $idProduit;
    }

    public int getId() {
        return id;
    }

    public int get$quantite() {
        return $quantite;
    }

    public int get$idCommande() {
        return $idCommande;
    }

    public int get$idProduit() {
        return $idProduit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void set$quantite(int $quantite) {
        this.$quantite = $quantite;
    }

    public void set$idProduit(int $idProduit) {
        this.$idProduit = $idProduit;
    }
public void setIdCommande(int idCommande) {
        this.$idCommande = idCommande;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LignePanier other = (LignePanier) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LignePanier{ $quantite=" + $quantite + ", $idCommande=" + $idCommande + ", $idProduit=" + $idProduit + '}';
    }
    
    
}
