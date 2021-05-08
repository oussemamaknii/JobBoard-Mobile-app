package Mobile_App.Entities.GestionProduit_Commande;

public class Commande {

    private int id, id_user;
    private String date;
    private double total_payment;
    private boolean state;

    public Commande() {
    }

    public Commande(int id, int id_user, String date, double total_payment, boolean state) {
        this.id = id;
        this.id_user = id_user;
        this.date = date;
        this.total_payment = total_payment;
        this.state = state;
    }

    public Commande(int id_user, String date, double total_payment, boolean state) {
        this.id_user = id_user;
        this.date = date;
        this.total_payment = total_payment;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(double total_payment) {
        this.total_payment = total_payment;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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
        Commande other = (Commande) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", date='" + date + '\'' +
                ", total_payment=" + total_payment +
                ", state=" + state +
                '}';
    }
}
