package Mobile_App.Gui.Demande;

import Mobile_App.Entities.Demande_Recrutement;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.Offre_Emploi.OfferInfos;
import Mobile_App.Main;
import Mobile_App.Service.DemandeService;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

import java.util.List;

public class ListApps extends Form {
    public ListApps(Form previous) {

        this.setTitle("liste des series");
        this.setLayout(BoxLayout.y());

        List<Demande_Recrutement> apps = DemandeService.getInstance().getAllApps();
        for (int i = 0; i < apps.size(); i++) {
            this.add(addSeriesHolder(apps.get(i)));
        }
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public Container addSeriesHolder(Demande_Recrutement s) {
        try {
            Container holder = new Container(BoxLayout.x());
            Container details = new Container(BoxLayout.y());
            Container titleDuree = new Container(BoxLayout.x());
            ;

            ImageViewer image = new ImageViewer(Main.theme.getImage("jobapp.png").scaled(250, 350));

            Label lbTitle = new Label(s.getOfftit());
            Label lDescription = new Label(s.getUsername());
            Label lDuree;
            if (s.getStatus())
                lDuree = new Label("Treated !");
            else
                lDuree = new Label("Loading ..");
            image.addPointerReleasedListener((evnt) -> {
                Form f = new AppInfos(s, this);
                f.show();
            });
            titleDuree.addAll(lbTitle, lDuree);
            details.addAll(titleDuree, lDescription);
            holder.addAll(image, details);
            holder.setLeadComponent(image);
            return holder;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return new Container(BoxLayout.x());
    }
}