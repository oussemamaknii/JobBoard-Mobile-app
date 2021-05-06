package Mobile_App.Gui.Offre_Emploi;

import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Main;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import java.io.IOException;
import java.util.List;

public class ListViewOffer extends SideMenu {

    public ListViewOffer(Form previous, Resources res) {
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);

        this.setTitle("liste des series");
        this.setLayout(BoxLayout.y());

        List<Offre_Emploi> offers = Offer_Service.getInstance().getAllOffers();
        for (int i = 0; i < offers.size(); i++) {
            this.add(addSeriesHolder(offers.get(i),res));
        }
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }

    public Container addSeriesHolder(Offre_Emploi s,Resources res) {
        try{
            Container holder = new Container(BoxLayout.x());
            Container details = new Container(BoxLayout.y());
            Container titleDuree = new Container(BoxLayout.x());;

            ImageViewer image = new ImageViewer(Main.theme.getImage("job.png").scaled(250, 350));

            Label lbTitle = new Label(s.getTitre());
            Label lDescription = new Label(s.getDescription());
            Label lDuree = new Label(String.valueOf(s.getEmail()));
            image.addPointerReleasedListener((evnt)->{
                Form f = new OfferInfos(s,this,res);
                f.show();
            });
            titleDuree.addAll(lbTitle, lDuree);
            details.addAll(titleDuree, lDescription);
            holder.addAll(image, details);
            holder.setLeadComponent(image);
            return holder;
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        return new Container(BoxLayout.x());
    }
}
