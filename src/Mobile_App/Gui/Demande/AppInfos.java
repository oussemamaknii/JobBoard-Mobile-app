package Mobile_App.Gui.Demande;

import Mobile_App.Entities.Demande_Recrutement;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.HomeForm;
import Mobile_App.Gui.Offre_Emploi.AddOffer;
import Mobile_App.Gui.Offre_Emploi.ListViewOffer;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Main;
import Mobile_App.Service.DemandeService;
import Mobile_App.Service.Offer_Service;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

public class AppInfos extends SideMenu {

    public AppInfos(Demande_Recrutement s, Form previous, Resources res) {
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);
        this.setTitle("Apps Infos");
        this.setLayout(new FlowLayout(CENTER, CENTER));
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> previous.showBack());
        try {
            Container details = new Container(BoxLayout.y());
            Container titleDuree = new Container(BoxLayout.x());

            ImageViewer image = new ImageViewer(Main.theme.getImage("jobapp.png").scaled(250, 350));
            ImageViewer job = new ImageViewer(Main.theme.getImage("job.png").scaled(100, 150));
            ImageViewer user = new ImageViewer(Main.theme.getImage("user.png").scaled(100, 150));

            Container c1 =new Container(BoxLayout.x());
            Container c2 =new Container(BoxLayout.x());

            Label lbTitle = new Label(s.getOfftit());
            Label lDescription = new Label(s.getUsername());
            Label lDuree;
            if (s.getStatus() == "true") {
                lDuree = new Label("Treated !");
                lDuree.getAllStyles().setFgColor(ColorUtil.GREEN);
            } else {
                lDuree = new Label("Loading ..");
                lDuree.getAllStyles().setFgColor(ColorUtil.rgb(139, 0, 0));
            }

            Container buttons = new Container(BoxLayout.x());
            Button delete = new Button("Delete");
            Button Update = new Button("Treat");
            delete.addActionListener(e -> {
                Offer_Service.getInstance().deleteoffer(s.getId());
                Dialog.show("Success", "Deleted Successfully !", new Command("OK"));
                Form f2 = new ListViewOffer(new HomeForm(res), res);
                f2.show();
            });
            Update.addActionListener(e -> {
                if (s.getStatus() == "false"){
                Offer_Service.getInstance().treat(s.getId());
                    Dialog.show("Success", "Apply treated Successfully !", new Command("OK"));
                }
                else{
                    Dialog.show("Success", "Apply treated Already !", new Command("OK"));
                }
                Form f = new ListApps(this, res);
                f.show();
            });

            buttons.addAll(delete, Update);

            c1.addAll(job,lbTitle);
            c2.addAll(user,lDescription);
            details.addAll(c1, c2);

            this.addAll(image, details, buttons);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
