package Mobile_App.Gui.event;

import Mobile_App.Entities.Events;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.HomeForm;
import Mobile_App.Gui.Offre_Emploi.ListViewOffer;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Main;
import Mobile_App.Service.DemandeService;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

public class EventsInfod extends SideMenu {

    public EventsInfod(Events s, Form previous, Resources res) {
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);
        this.setTitle("Event Infos");
        this.setLayout(new FlowLayout(CENTER, CENTER));
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> previous.showBack());
        try {
            Container details = new Container(BoxLayout.y());
            Container titleDuree = new Container(BoxLayout.x());

            ImageViewer image = new ImageViewer(Main.theme.getImage("job.png").scaled(250, 350));

            Label lbTitle = new Label(s.getNom());
            Label lDescription = new Label(s.getDescription());
            Label lDuree = new Label(String.valueOf(s.getAdresse()));

            Container buttons = new Container(BoxLayout.x());
            Button delete = new Button("Delete");
            Button Update = new Button("Update");
            delete.addActionListener(e -> {
                Offer_Service.getInstance().deleteoffer(s.getId());
                Dialog.show("Success", "Deleted Successfully !", new Command("OK"));
                Form f2 = new ListViewEvent(new HomeForm(res),res);
                f2.show();
            });
            Update.addActionListener(e -> {
                Form f = new AddEvent(this, s,res);
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
