package Mobile_App.Gui.GestionProduit_Commande;

import Mobile_App.Entities.GestionProduit_Commande.Commande;
import Mobile_App.Entities.GestionProduit_Commande.LignePanier;
import Mobile_App.Entities.GestionProduit_Commande.Panier;
import Mobile_App.Gui.BaseForm;
import Mobile_App.Main;
import Mobile_App.Service.ServiceGPC.CommandeService;
import Mobile_App.Service.ServiceGPC.PanierService;
import com.codename1.components.ImageViewer;
import com.codename1.io.Storage;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PanierForm extends BaseForm {

    public PanierForm() {
        this.setTitle("Cart");
        this.setLayout(BoxLayout.y());
        this.getToolbar().addCommandToLeftBar("BackToShop",null,(evt2)->{
            Form f1= new ShopForm();
            f1.show();
        });

        List<LignePanier> panier = new ArrayList<LignePanier>();
        panier.addAll(((List<LignePanier>) Storage.getInstance().readObject("Panier")));
        Container header = new Container(BoxLayout.x());
        Label img = new Label("Image");
        Label qty = new Label("Quantite");
        Label prix = new Label("Prix");
        Label total1 = new Label("Total");
        header.addAll(img,prix,qty,total1);
        this.add(header);
        double total = 0;
        int myQty = 1;
        int myId = 0;
        for(LignePanier cart : panier )
        {
            try {
                Container holder = new Container(BoxLayout.x());

                ImageViewer image  = new ImageViewer(Main.theme.getImage(cart.getImage()).scaled(250,350));
                Label price = new Label(String.valueOf(cart.getPrix())+" DT");
                price.getAllStyles().setFgColor(0xFE7B37);
                Label quantite = new Label(String.valueOf(cart.getQuantite()));
                myQty = cart.getQuantite();
                myId = cart.getIdProduit();
                Label total2 = new Label(String.valueOf(cart.getQuantite()*cart.getPrix())+" DT");
                total = total + (cart.getPrix()* cart.getQuantite());
                ImageViewer delete = new ImageViewer(Main.theme.getImage("Icon material-delete.png"));
                delete.getAllStyles().setMarginUnit(Style.UNIT_TYPE_DIPS);
                delete.getAllStyles().setMargin(0, 0, 15, 15);
                holder.addAll(image,price,quantite,total2,delete);
                this.add(holder);
            }catch (NullPointerException e){
                System.out.println(e.getMessage());
            }
        }
        Container footer = new Container(BoxLayout.y());
        Label totalPayment = new Label("Total à payer: "+total+" DT");
        Button commander = new Button("Passer la commande");
        totalPayment.getAllStyles().setMargin(15,15,450,5);
        footer.addAll(totalPayment,commander);
        double finalTotal = total;
        int finalMyQty = myQty;
        int finalMyId = myId;
        commander.addActionListener((evt)->{
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dateCommande = dateFormat.format(date);

            if(Dialog.show("Passer la commander","Cliquez sur OK pour valider","OK","Cancel")){
                Commande order =  new Commande(1,dateCommande, finalTotal,false);
                if(new CommandeService().addCommande(order)){
                    Dialog.show("Success","Connection accepted",new Command("OK"));
                    Panier myCart = new Panier(finalMyQty,1, finalMyId);
                    if(new PanierService().addPanier(myCart)){
                        Dialog.show("Success","Connection accepted",new Command("OK"));
                    }else Dialog.show("ERROR", "Server error", new Command("OK"));
                }
                else
                    Dialog.show("ERROR", "Server error", new Command("OK"));
            }
        });
        this.add(footer);

    }



}
