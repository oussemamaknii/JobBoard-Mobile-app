package Mobile_App.Gui.Formation;




import Mobile_App.Entities.Category;
import Mobile_App.Entities.Formation;
import Mobile_App.Gui.HomeForm;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Service.CategoryService;
import Mobile_App.Service.FormationService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.util.List;

public class ListForForm  extends SideMenu {
    public ListForForm(Form previous, Resources res) {

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);

        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        tb.addCommandToRightBar("", icon, (e) -> new AddForForm(this, null, res).show());

        this.setTitle("list Formation");
        this.setLayout(BoxLayout.y());

        List<Formation> formation = FormationService.getInstance().getAllFor();
        for (int i = 0; i < formation.size(); i++) {
            this.add(addSeriesHolder(formation.get(i), res));
        }
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public Container addSeriesHolder(Formation s, Resources res) {
        try {
            Container holder = new Container(BoxLayout.y());
            Container details = new Container(BoxLayout.x());
            Container titleDuree = new Container(BoxLayout.y());



            Label lbTitle = new Label("*titre: "+ s.getNom());
            Label lb1 = new Label("Formateur: "+ s.getFormateur());
            Label lDescription = new Label("Description: "+s.getDescription());
            Label lb2 = new Label("Email: "+ s.getMail());
            Label lb3 = new Label("Tel: "+ s.getTel());
            Label lb4 = new Label("Category: "+ s.getCategory_id());

            Container buttons = new Container(BoxLayout.x());
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
                FormationService.getInstance().deletefor(s.getId());
                Dialog.show("Success", "Deleted Successfully !", new Command("OK"));
                Form f2 = new ListForForm(new HomeForm(res), res);
                f2.show();
            });
            lModifier.addPointerPressedListener(e -> {
                Form f = new AddForForm(this, s, res);
                f.show();
            });


            titleDuree.addAll(lbTitle,lDescription,lb1,lb2,lb3,lb4);
            details.addAll(titleDuree,lSupprimer,lModifier );
            holder.addAll( details,buttons);

            return holder;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return new Container(BoxLayout.x());
    }
}