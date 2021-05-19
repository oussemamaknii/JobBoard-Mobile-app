package Mobile_App.Entities;

import java.sql.Date;

public class Formation {

    private int id,category_id;
    private String nom, formateur, description,adresse,mail;
    private Date date_debut,date_fin;
    private  float tel,prix;

    public int getId() {
        return id;
    }

    public Formation( int category_id, String nom, String formateur, String description, String adresse, String mail, Date date_debut, Date date_fin, float tel, float prix) {
        this.category_id = category_id;
        this.nom = nom;
        this.formateur = formateur;
        this.description = description;
        this.adresse = adresse;
        this.mail = mail;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tel = tel;
        this.prix = prix;
    }
    public Formation( String nom, String formateur, String description, String adresse, String mail, Date date_debut, Date date_fin, float tel, float prix) {
        this.nom = nom;
        this.formateur = formateur;
        this.description = description;
        this.adresse = adresse;
        this.mail = mail;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tel = tel;
        this.prix = prix;
    }
    public Formation() {
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", nom='" + nom + '\'' +
                ", formateur='" + formateur + '\'' +
                ", description='" + description + '\'' +
                ", adresse='" + adresse + '\'' +
                ", mail='" + mail + '\'' +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", tel=" + tel +
                ", prix=" + prix +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFormateur() {
        return formateur;
    }

    public void setFormateur(String formateur) {
        this.formateur = formateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public float getTel() {
        return tel;
    }

    public void setTel(float tel) {
        this.tel = tel;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }


}
