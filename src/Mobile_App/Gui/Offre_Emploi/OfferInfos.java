package Mobile_App.Gui.Offre_Emploi;

import Mobile_App.Entities.Demande_Recrutement;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.Demande.AppInfos;
import Mobile_App.Gui.HomeForm;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Main;
import Mobile_App.Service.DemandeService;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.codename1.ui.layouts.GroupLayout.CENTER;

public class OfferInfos extends SideMenu {

    public OfferInfos(Offre_Emploi s, Form previous, Resources res) {

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);
        this.setTitle("Offer Infos");
        Container all = new Container(new FlowLayout(CENTER, CENTER));
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> previous.showBack());
        try {
            Container details = new Container(BoxLayout.y());
            Container titleDuree = new Container(BoxLayout.x());

            ImageViewer image = new ImageViewer(Main.theme.getImage("job.png").scaled(250, 350));

            Label lbTitle = new Label(s.getTitre());
            Label lDescription = new Label(s.getDescription());
            Label lDuree = new Label(String.valueOf(s.getEmail()));

            Container buttons = new Container(BoxLayout.x());
            Button delete = new Button("Delete");
            Button Update = new Button("Update");
            Button Apply = new Button("Apply");
            delete.addActionListener(e -> {
                Offer_Service.getInstance().deleteoffer(s.getId());
                Dialog.show("Success", "Deleted Successfully !", new Command("OK"));
                Form f2 = new ListViewOffer(new HomeForm(res), res);
                f2.show();
            });
            Update.addActionListener(e -> {
                Form f = new AddOffer(this, s, res);
                f.show();
            });
            Apply.addActionListener(e -> {
                int iduser = 45;
                DemandeService.getInstance().applytojob(s.getId(), iduser);
                Dialog.show("Success", "Applied Successfully !", new Command("OK"));
                Form f2 = new ListViewOffer(new HomeForm(res), res);
                f2.show();
            });

            buttons.addAll(delete, Update, Apply);

            titleDuree.addAll(lbTitle, lDuree);
            details.addAll(titleDuree, lDescription);

            all.addAll(image, details, buttons);

            Container titre = new Container(new FlowLayout(CENTER, CENTER));
            titre.add(new Label("Job Offer Applications"));
            Container applies = new Container();
            applies.add(titre);
            List<Demande_Recrutement> apps = DemandeService.getInstance().getAllApps();
            for (int i = 0; i < apps.size(); i++) {
                if (apps.get(i).getOffre_id() == s.getId())
                    applies.add(addSeriesHolder(apps.get(i), res));
            }

            Container desc = new Container(new FlowLayout(CENTER, CENTER));
            desc.add(new Label("Job Offer Requirements"));

            ImageViewer image2 = new ImageViewer(res.getImage(s.getFile()).scaled(this.getWidth(), res.getImage(s.getFile()).getHeight() * 3));
            desc.add(image2);


            this.addAll(all, applies, desc);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    public Container addSeriesHolder(Demande_Recrutement s, Resources res) {
        try {
            Container holder = new Container(BoxLayout.x());
            Container details = new Container(BoxLayout.y());
            Container titleDuree = new Container(BoxLayout.x());

            ImageViewer image = new ImageViewer(Main.theme.getImage("jobapp.png").scaled(250, 350));

            Label lbTitle = new Label(s.getOfftit());
            Label lDescription = new Label(s.getUsername());
            Label lDuree;
            if (s.getStatus() == "true")
                lDuree = new Label("Treated !");
            else
                lDuree = new Label("Loading ..");
            image.addPointerReleasedListener((evnt) -> {
                Form f = new AppInfos(s, this, res);
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
