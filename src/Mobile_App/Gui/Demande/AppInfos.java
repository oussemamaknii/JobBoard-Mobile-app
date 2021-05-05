package Mobile_App.Gui.Demande;

import Mobile_App.Entities.Demande_Recrutement;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.HomeForm;
import Mobile_App.Gui.Offre_Emploi.AddOffer;
import Mobile_App.Gui.Offre_Emploi.ListViewOffer;
import Mobile_App.Main;
import Mobile_App.Service.DemandeService;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

public class AppInfos extends Form {

    public AppInfos(Demande_Recrutement s, Form previous) {
        this.setTitle("Apps Infos");
        this.setLayout(new FlowLayout(CENTER, CENTER));
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> previous.showBack());
        try {
            Container details = new Container(BoxLayout.y());
            Container titleDuree = new Container(BoxLayout.x());

            ImageViewer image = new ImageViewer(Main.theme.getImage("job.png").scaled(250, 350));

            Label lbTitle = new Label(s.getOfftit());
            Label lDescription = new Label(s.getOfftit());
            Label lDuree;
            if (s.getStatus())
                lDuree = new Label("Treated !");
            else
                lDuree = new Label("Loadinf ..");

            Container buttons = new Container(BoxLayout.x());
            Button delete = new Button("Delete");
            Button Update = new Button("Treat");
            delete.addActionListener(e -> {
                Offer_Service.getInstance().deleteoffer(s.getId());
                Dialog.show("Success", "Deleted Successfully !", new Command("OK"));
                Form f2 = new ListViewOffer(new HomeForm());
                f2.show();
            });
            Update.addActionListener(e -> {
                //Offer_Service.getInstance().treat();
                Form f = new ListApps(this);
                f.show();
            });

            buttons.addAll(delete, Update);

            titleDuree.addAll(lbTitle, lDuree);
            details.addAll(titleDuree, lDescription);

            this.addAll(image, details, buttons);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
