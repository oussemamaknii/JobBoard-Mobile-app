package Mobile_App.Gui.GestionProduit_Commande;

import Mobile_App.Entities.GestionProduit_Commande.LignePanier;
import Mobile_App.Entities.GestionProduit_Commande.Produit;
import Mobile_App.Main;
import com.codename1.components.ImageViewer;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;
import java.util.List;

public class ProductSingleForm extends Form {

    static int nbr_chargement = 0;
    private Resources theme=null;
    Form current = null;

    public ProductSingleForm(Produit book, Form previous, Resources res){
        current = previous;
        theme = res;

        List<LignePanier> ProduitSingle = new ArrayList<LignePanier>();
        List<LignePanier> panier = new ArrayList<LignePanier>();
        ProduitSingle.addAll(((List<LignePanier>) Storage.getInstance().readObject("ProduitSingle")));

        this.setTitle("Book Infos");
        this.setLayout(new FlowLayout(CENTER,CENTER));
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        /*this.getToolbar().addCommandToLeftBar("BackToShop",null,(evt2)->{
            Form f1= new ShopForm();
            f1.show();
        });*/
        try {
            Container holder = new Container(BoxLayout.y());
            Container details1 = new Container(BoxLayout.y());
            Container details2 = new Container(BoxLayout.y());
            Container qty = new Container(BoxLayout.x());

            ImageViewer image  = new ImageViewer(Main.theme.getImage(book.getImage()).scaled(250,350));

            Label price = new Label("Prix:  "+ String.valueOf(book.getPrice())+" DT");
            Label autor_name = new Label("Auteur:  "+book.getName());
            Label ref = new Label("Reference:  "+book.getRef());
            Label desc = new Label(book.getDescription());
            Label lBquantite = new Label("Quantite");
            TextField tFquantite = new TextField("","quantite");

            Button addButton = new Button("Ajouter");

            qty.addAll(lBquantite,tFquantite);
            details1.addAll(image,price,ref);
            details2.addAll(autor_name,desc);
            holder.addAll(details1,details2,qty,addButton);
            addButton.addActionListener((evt)->{
              //  LignePanier ProductCart = new LignePanier(book.getId(), book.getName(), book.getPrice(),Integer.parseInt(tFquantite.getText()),book.getImage(),1);
              //  System.exit(0);
                ProduitSingle.get(0).setQuantite(Integer.parseInt(tFquantite.getText()));
                //ProduitSingle.add(ProductCart);
                if (this.nbr_chargement == 0) {
                    panier.add(ProduitSingle.get(0));
                  //  panier.add(ProductCart);
                    Storage.getInstance().writeObject("Panier", panier);
                    //new PanierForm(resourceObjectInstance).show();
                    Form f = new PanierForm(current,theme);
                    f.show();
                    this.nbr_chargement++;
                } else {
                    panier.clear();
                    panier.addAll(((List<LignePanier>) Storage.getInstance().readObject("Panier")));
                    panier.add(ProduitSingle.get(0));
                    //panier.add(ProductCart);
                    Storage.getInstance().writeObject("Panier", panier);
                    //new PanierForm(resourceObjectInstance).show();
                    Form f = new PanierForm(current,theme);
                    f.show();
                }
            });
            this.add(holder);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}
