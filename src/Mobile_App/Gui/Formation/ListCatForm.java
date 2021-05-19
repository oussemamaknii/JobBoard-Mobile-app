package Mobile_App.Gui.Formation;
import Mobile_App.Entities.Category;
import Mobile_App.Entities.Events;
import Mobile_App.Gui.HomeForm;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Gui.event.AddEvent;
import Mobile_App.Gui.event.EventsInfod;
import Mobile_App.Gui.event.ListViewEvent;
import Mobile_App.Service.CategoryService;
import Mobile_App.Service.EventService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.util.List;


public class ListCatForm  extends SideMenu {
    public ListCatForm(Form previous, Resources res) {

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);

        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        tb.addCommandToRightBar("", icon, (e) -> new AddCatForm(this, null, res).show());

        this.setTitle("list Catgories");
        this.setLayout(BoxLayout.y());

        List<Category> offers = CategoryService.getInstance().getAllCat();
        for (int i = 0; i < offers.size(); i++) {
            this.add(addSeriesHolder(offers.get(i), res));
        }
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public Container addSeriesHolder(Category s, Resources res) {
        try {
            Container holder = new Container(BoxLayout.y());
            Container details = new Container(BoxLayout.x());
            Container titleDuree = new Container(BoxLayout.y());



            Label lbTitle = new Label("*titre: "+ s.getTitre());
            Label lDescription = new Label("Description: "+ s.getDescriptionc());
            Label lSupprimer = new Label(" ");
            lSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            lSupprimer.setIcon(suprrimerImage);
            lSupprimer.setTextPosition(RIGHT);
            Button Update = new Button("Update");

            Label lModifier = new Label(" ");
            lModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(lModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);
            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            lModifier.setIcon(mFontImage);
            lModifier.setTextPosition(LEFT);
            lSupprimer.addPointerPressedListener(e -> {
                CategoryService.getInstance().deletecat(s.getId());
                Dialog.show("Success", "Deleted Successfully !", new Command("OK"));
                Form f2 = new ListCatForm(new HomeForm(res), res);
                f2.show();
            });
            lModifier.addPointerPressedListener(e -> {
              //  Form f = new AddCatForm(this, s, res);
             //   f.show();

            });


            titleDuree.addAll(lbTitle,lDescription);
            details.addAll(titleDuree,lSupprimer,lModifier );
            holder.addAll( details);

            return holder;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return new Container(BoxLayout.x());
    }
}