package Mobile_App.Gui.Offre_Emploi;

import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.HomeForm;
import Mobile_App.Main;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import java.io.IOException;
import java.util.Date;

import static com.codename1.ui.layouts.GroupLayout.CENTER;

public class OfferInfos extends Form {

    public OfferInfos(Offre_Emploi s, Form previous) {
        this.setTitle("Offer Infos");
        this.setLayout(new FlowLayout(CENTER, CENTER));
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
            delete.addActionListener(e -> {
                Offre_Emploi offer = new Offre_Emploi(s.getId(), 0, "e", "e", "e", "e",
                        "e", "e", null, null, 2, 2);
                Offer_Service.getInstance().deleteoffer(offer)
                Dialog.show("Success", "Deleted Successfully !", new Command("OK"));
                Form f2 = new ListViewOffer(new HomeForm());
                f2.show();
            });
            Update.addActionListener(e -> {
                Form f = new AddOffer(this, s);
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
