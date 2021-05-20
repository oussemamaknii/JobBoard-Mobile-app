package Mobile_App.Entities;

public class Category {

    private int id;
    private String titre, descriptionc, couleur;

    public Category() {

    }




    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public  String getDescriptionc() {
        return descriptionc;
    }

    public void setDescriptionc(String descriptionc) {
        this.descriptionc = descriptionc;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((titre == null) ? 0 : titre.hashCode());
        return result;
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
        Category other = (Category) obj;
        if (id != other.id) {
            return false;
        }
        if (titre == null) {
            if (other.titre != null) {
                return false;
            }
        } else if (!titre.equals(other.titre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return (titre);
    }

    public Category( String titre, String descriptionc, String couleur) {
        this.titre = titre;
        this.descriptionc = descriptionc;
        this.couleur = couleur;
    }

    public Category(String titre,String descriptionc) {
        this.titre = titre;
        this.descriptionc = descriptionc;
    }
}