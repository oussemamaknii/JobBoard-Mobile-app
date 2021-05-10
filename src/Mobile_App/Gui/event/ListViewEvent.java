package Mobile_App.Gui.event;

import Mobile_App.Entities.Events;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Main;
import Mobile_App.Service.EventService;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.util.List;

public class ListViewEvent extends SideMenu {

    public ListViewEvent(Form previous, Resources res) {

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);

        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        tb.addCommandToRightBar("", icon, (e) -> new AddEvent(this, null, res).show());

        this.setTitle("list Events");
        this.setLayout(BoxLayout.y());

        List<Events> offers = EventService.getInstance().getAllEvents();
        for (int i = 0; i < offers.size(); i++) {
            this.add(addSeriesHolder(offers.get(i), res));
        }
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public Container addSeriesHolder(Events s, Resources res) {
        try {
            Container holder = new Container(BoxLayout.x());
            Container details = new Container(BoxLayout.y());
            Container titleDuree = new Container(BoxLayout.x());
            ;

            ImageViewer image = new ImageViewer(Main.theme.getImage("job.png").scaled(250, 350));

            Label lbTitle = new Label(s.getNom());
            Label lDescription = new Label(s.getDescription());
            Label lDuree = new Label(String.valueOf(s.getPrix()));
            image.addPointerReleasedListener((evnt) -> {
                Form f = new EventsInfod(s, this, res);
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
