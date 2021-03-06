package Mobile_App.Entities;

import java.util.Date;

public class Offre_Emploi {
    private int id,category_id;
    private String titre, poste, description, location, file, email,catname;
    private Date date_debut, date_expiration;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    private int max_salary, min_salary;

    public Offre_Emploi() {
    }

    public Offre_Emploi(int id,int category_id, String titre, String poste, String description, String location, String file, String email, Date date_debut, Date date_expiration, int max_salary, int min_salary) {
        this.category_id = category_id;
        this.titre = titre;
        this.poste = poste;
        this.description = description;
        this.location = location;
        this.file = file;
        this.email = email;
        this.date_debut = date_debut;
        this.date_expiration = date_expiration;
        this.max_salary = max_salary;
        this.min_salary = min_salary;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public int getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(int max_salary) {
        this.max_salary = max_salary;
    }

    public int getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(int min_salary) {
        this.min_salary = min_salary;
    }

    @Override
    public String toString() {
        return "Offre_Emploi{" +
                "category_id=" + category_id +
                ", titre='" + titre + '\'' +
                ", poste='" + poste + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", file='" + file + '\'' +
                ", email='" + email + '\'' +
                ", date_debut=" + date_debut +
                ", date_expiration=" + date_expiration +
                ", max_salary=" + max_salary +
                ", min_salary=" + min_salary +
                '}';
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
        Offre_Emploi other = (Offre_Emploi) obj;
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

    public int getCategory() {
        return category_id;
    }

    public void setCategory(int category_id) {
        this.category_id = category_id;
    }
}