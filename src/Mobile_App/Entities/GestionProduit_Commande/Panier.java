package Mobile_App.Entities.GestionProduit_Commande;

public class Panier {

    private int id,quantity,idOrder,idProduct;

    public Panier() {
    }

    public Panier(int quantity, int idOrder, int idProduct) {
        this.quantity = quantity;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
    }

    public Panier(int id, int quantity, int idOrder, int idProduct) {
        this.id = id;
        this.quantity = quantity;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }


    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", idOrder=" + idOrder +
                ", idProduct=" + idProduct +
                '}';
    }
}
