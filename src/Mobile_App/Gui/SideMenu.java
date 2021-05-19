package Mobile_App.Gui;

import Mobile_App.Gui.GestionProduit_Commande.ShopForm;
import Mobile_App.Gui.Offre_Emploi.ListViewOffer;
import Mobile_App.Gui.Offre_Emploi.Stat;
import Mobile_App.Gui.User.AddEditCompany;
import Mobile_App.Gui.User.addEditResume;
import Mobile_App.Gui.User.login;
import Mobile_App.Gui.event.*;
import Mobile_App.Utils.Session;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;

public class SideMenu extends Form {

    Form current;

    public SideMenu(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenu(String title) {
        super(title);
    }

    public SideMenu() {
    }

    public SideMenu(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(/*User u,*/ Resources res) {
        Image profilePic = res.getImage("20187112601561032514-512.png");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(Session.ConnectedUser.getFirstName(), profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());


        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");

        getToolbar().addComponentToSideMenu(sidemenuTop);

        getToolbar().addMaterialCommandToSideMenu("  Home", FontImage.MATERIAL_HOME, e -> new HomeForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Job Offers", FontImage.MATERIAL_LOCAL_OFFER, e ->
                new ListViewOffer(this, res).show());
        getToolbar().addMaterialCommandToSideMenu("  Events", FontImage.MATERIAL_LOCAL_OFFER, e ->
                new ListViewEvent(this, res).show());
        getToolbar().addMaterialCommandToSideMenu("  Statistics", FontImage.MATERIAL_ANALYTICS, e ->
                new Stat(this, res).createPieChartForm().show());
        getToolbar().addMaterialCommandToSideMenu("  Resume", FontImage.MATERIAL_TRENDING_UP,  e ->
                new addEditResume(this,null,res).show());
        getToolbar().addMaterialCommandToSideMenu("  Company", FontImage.MATERIAL_TRENDING_UP,  e ->
                new AddEditCompany(this,null,res).show());
        getToolbar().addMaterialCommandToSideMenu("  Shop", FontImage.MATERIAL_STORE, e ->
                new ShopForm(this, res).show());
        getToolbar().addMaterialCommandToSideMenu("  Meetings", FontImage.MATERIAL_TRENDING_UP, null);
        getToolbar().addMaterialCommandToSideMenu("  Projects", FontImage.MATERIAL_ACCESS_TIME, null);
        getToolbar().addMaterialCommandToSideMenu("  Releases", FontImage.MATERIAL_ACCESS_TIME, null);
        getToolbar().addMaterialCommandToSideMenu("  Documents", FontImage.MATERIAL_TRENDING_UP, null);
        getToolbar().addMaterialCommandToSideMenu("  Issues", FontImage.MATERIAL_ACCESS_TIME, null);
        getToolbar().addMaterialCommandToSideMenu("  Meeting Claims", FontImage.MATERIAL_ACCESS_TIME, null);
        getToolbar().addMaterialCommandToSideMenu(" Update Profile", FontImage.MATERIAL_SETTINGS, null);
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, 0,e -> new login(res).show());

    }
}
