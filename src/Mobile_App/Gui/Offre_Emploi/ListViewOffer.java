package Mobile_App.Gui.Offre_Emploi;

import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Gui.event.AddEvent;
import Mobile_App.Main;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.io.IOException;
import java.util.List;

public class ListViewOffer extends SideMenu {

    public ListViewOffer(Form previous, Resources res) {

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);

        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        tb.addCommandToRightBar("", icon, (e) -> new AddOffer(this, null, res).show());
        tb.addSearchCommand(e -> {
            String text = (String)e.getSource();
            if(text == null || text.length() == 0) {
                // clear search
                for(Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for(Component cmp : this.getContentPane()) {
                    Container mb = (Container)cmp;
                    List<Component> a1 = mb.getChildrenAsList(false);
                    Container holder = (Container) a1.get(1);
                    List<Component> a2 = holder.getChildrenAsList(false);
                    Container details = (Container) a2.get(0);
                    List<Component> a3 = details.getChildrenAsList(false);
                    Container titcont = (Container) a3.get(0);
                    List<Component> a5 = titcont.getChildrenAsList(false);
                    Label titre =(Label) a5.get(1);
                    boolean show = titre.getText() != null && titre.getText().toLowerCase().indexOf(text) > -1 ;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);

        this.setTitle("list Offers");
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
            holder.setWidth(this.getWidth());
            Container details = new Container(BoxLayout.y());
            details.setWidth(this.getWidth()-250);
            Container titleDuree = new Container(BoxLayout.x());;

            ImageViewer image = new ImageViewer(Main.theme.getImage("job.png").scaled(250, 350));

            Container a = new Container(BoxLayout.x());
            Container b = new Container(BoxLayout.x());
            Container c = new Container(BoxLayout.xRight());

            ImageViewer imagea = new ImageViewer(Main.theme.getImage("title.png").scaled(100, 150));
            Label lbTitle = new Label(s.getTitre());

            a.addAll(imagea,lbTitle);

            Label lDescription = new Label("Description : \n"+s.getDescription());

            Label lDuree = new Label(String.valueOf(s.getEmail()));
            ImageViewer imageb = new ImageViewer(Main.theme.getImage("email.png").scaled(100, 150));

            b.addAll(imageb,lDuree);

            Label price = new Label(s.getMin_salary()+" DT - "+s.getMax_salary()+" DT");
            c.add(price);

            image.addPointerReleasedListener((evnt)->{
                Form f = new OfferInfos(s,this,res);
                f.show();
            });

            titleDuree.addAll(a, b);
            details.addAll(titleDuree, lDescription,c);
            holder.addAll(image, details);
            holder.setLeadComponent(image);

            return holder;
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        return new Container(BoxLayout.x());
    }
}
