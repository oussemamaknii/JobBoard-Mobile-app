package Mobile_App.Gui.GestionProduit_Commande;

import Mobile_App.Entities.GestionProduit_Commande.LignePanier;
import Mobile_App.Entities.GestionProduit_Commande.Produit;
import Mobile_App.Gui.BaseForm;
import Mobile_App.Main;
import Mobile_App.Service.ServiceGPC.ShopService;
import com.codename1.components.ImageViewer;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import static com.codename1.ui.plaf.Style.UNIT_TYPE_DIPS;

public class ShopForm extends BaseForm {

    private Resources theme;

    public ShopForm(){
        //super("Border Layout", new BorderLayout());
        setTitle("Shop");
        this.setLayout(BoxLayout.y());

        List<Produit> products = new ShopService().getAllProducts();

        for(int i = 0; i< products.size(); i++){

            //System.out.println(series.get(i));
            //ajouter le container à la form
            this.add(addSeriesHolder(products.get(i)));
        }
        /*
        theme = UIManager.initNamedTheme("/theme", "Theme");
        List<Button> list_button = new ArrayList<Button>();
        List<Form> list_form = new ArrayList<Form>();
        list_button.add(new Button("Books"));
        //installSidemenu(theme, list_button, list_form);

        List<Produit> products = new ShopService().getAllProducts();
        Container contenant = new Container(BoxLayout.y());
        int i = 1 ;
        List<Produit> products_2 = new ArrayList<Produit>();
        for(Produit book : products){
            products_2.add(book);
            if(i % 2 == 0){
                Container C = BoxLayout.encloseX();
                Label book_name1 = new Label(products_2.get(0).getName());
                Label book_price1 = new Label(Double.toString(products_2.get(0).getPrice())+" dt");
                book_name1.setUIID("book_name");
                book_price1.setUIID("book_price");

                Container book_1 = new Container();
                String book_image1 = StringUtil.replaceAll(products_2.get(0).getImage(),"E:\\Etudes\\ESPRIT\\Esprit_3A28\\Semestre 2\\PiDev\\Mobile\\Mobile-app\\src\\Mobile_App\\Images\\","");
                book_1.add(new Label(theme.getImage(book_image1)));

                Container C1 = BoxLayout.encloseY(
                        book_1,
                        book_name1,
                        book_price1
                );
                C1.getAllStyles().setBgColor(0xFF0000);
                C1.getStyle().setBgTransparency(255);
                C1.getAllStyles().setMarginUnit(Style.UNIT_TYPE_DIPS);
                C1.getAllStyles().setMargin(0,3,0,3);
                C1.getAllStyles().setBgImage(theme.getImage("Mask2.png"));

                Label book_name2 = new Label(products_2.get(1).getName());
                Label book_price2 = new Label(Double.toString(products_2.get(1).getPrice()) + " dt");
                book_name2.setUIID("book_name");
                book_price2.setUIID("book_price");

                Container book_2 = new Container();
                String image_2 = StringUtil.replaceAll(products_2.get(1).getImage(), "E:\\Etudes\\ESPRIT\\Esprit_3A28\\Semestre 2\\PiDev\\Mobile\\Mobile-app\\src\\Mobile_App\\Images\\", "");
                book_2.add(new Label(theme.getImage(image_2)));
                Container C2 = BoxLayout.encloseY(
                        book_2,
                        book_name2,
                        book_price2
                );
                C2.getAllStyles().setBgColor(0xFF0000);
                C2.getStyle().setBgTransparency(255);
                C2.getAllStyles().setMarginUnit(Style.UNIT_TYPE_DIPS);
                C2.getAllStyles().setMargin(0, 3, 3, 0);
                C2.getAllStyles().setBgImage(theme.getImage("Mask2.png"));

                C.getAllStyles().setBgColor(0xfbfcfe);
                C.getAllStyles().setMarginUnit(Style.UNIT_TYPE_DIPS);
                C.getAllStyles().setMargin(1, 1, 3, 3);
                C.addAll(C1, C2);
                contenant.add(C);
                products_2.clear();
            }

            i++;
        }

        Container contenant2 = new Container();
        contenant2.add(contenant);
        this.add(BorderLayout.CENTER, contenant2);
*/

    }

    public Container addSeriesHolder(Produit book){
        //faire un bloc try catch pour eviter une exception genre une image inexistant ie null
        try {
            //creer les container
            Container holder =new Container(BoxLayout.x());
            Container details = new Container(BoxLayout.y());
            Container titleDuree = new Container(BoxLayout.y());

            //les composants de chaq container en recuperant les infos
            ImageViewer image  = new ImageViewer(Main.theme.getImage(book.getImage()).scaled(250,350));
            Label lbTitle = new Label("Auteur: "+ book.getName());
            Label lDescription = new Label("Reference: "+ book.getRef());
            Label lDuree = new Label(String.valueOf(book.getPrice())+" DT");
            //cree l'action qui utilise un lambda expression
            image.addPointerReleasedListener((evnt)->{
               if(Dialog.show(book.getName(),book.getDescription(),"OK","Cancel")){
                   List<LignePanier> ProduitSingle = new ArrayList<LignePanier>();
                   ProduitSingle.add(new LignePanier(book.getId(),book.getName(),book.getPrice(),book.getQuantity(),book.getImage(),1));
                   Storage.getInstance().writeObject("ProduitSingle", ProduitSingle);
                   Form f = new ProductSingleForm(book);
                   f.show();
               }

            });

            //ajouter a chque container les elements respectifs
            titleDuree.addAll(lbTitle,lDuree);
            details.addAll(titleDuree,lDescription);
            holder.addAll(image,details);
            holder.setLeadComponent(image);//pour rendre l'action sur tout le container

            //retourner le container holder
            return holder;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return new Container(BoxLayout.x());
    }

}
