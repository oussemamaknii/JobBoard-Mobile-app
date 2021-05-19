/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_App.Gui;

import Mobile_App.Gui.Demande.ListApps;
import Mobile_App.Gui.Formation.AddCatForm;
import Mobile_App.Gui.Formation.AddForForm;
import Mobile_App.Gui.Formation.ListCatForm;
import Mobile_App.Gui.Formation.ListForForm;
import Mobile_App.Gui.GestionProduit_Commande.ShopForm;
import Mobile_App.Gui.Offre_Emploi.AddOffer;
import Mobile_App.Gui.Offre_Emploi.ListViewOffer;
import Mobile_App.Gui.Offre_Emploi.Stat;
import Mobile_App.Gui.User.AddEditCompany;
import Mobile_App.Gui.User.showCandidates;
import Mobile_App.Gui.User.showResume;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import Mobile_App.Gui.User.addEditResume;

/**
 *
 * @author bhk
 */
public class HomeForm extends SideMenu {

    Form current;
    public HomeForm(Resources res) {
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Offer");
        Button btnListTasks = new Button("List Offers");
        Button btnListapps = new Button("List Applications");

        Button btnResume = new Button("Update Resume");
        Button btnCompany = new Button("Update Company");
        Button btnCandidates = new Button("Candidates List");
        Button btnShowResume = new Button("show Resume");
        /**oumaima**/

        Button btnListfor = new Button("List formations");
        Button btnAddcat = new Button("Add categorie");
        Button btnListcat = new Button("List categories");
        /** **/
        btnShowResume.addActionListener(e -> new showResume(current, res));


        btnCandidates.addActionListener(e -> new showCandidates(current, res));

        btnAddTask.addActionListener(e -> new AddOffer(current,null,res).show());
        btnListTasks.addActionListener(e -> new ListViewOffer(current,res).show());
        btnResume.addActionListener(e -> new addEditResume(current,null,res).show());
        btnCompany.addActionListener(e -> new AddEditCompany(current,null,res).show());

        btnListapps.addActionListener(e -> new ListApps(current,res).show());

        Button btnShop = new Button("Shop");

        btnAddTask.addActionListener(e -> new AddOffer(current,null, res).show());
        btnListTasks.addActionListener(e -> new ListViewOffer(current, res).show());
        btnListapps.addActionListener(e -> new ListApps(current, res).show());
        btnShop.addActionListener(e->new ShopForm(current,res).show());

        /**ouma **/

        btnListfor.addActionListener(e -> new ListForForm(current,res).show());
        btnAddcat.addActionListener(e -> new AddCatForm(current, null, res).show());
        btnListcat.addActionListener(e -> new ListCatForm(current,res).show());;
        /** **/
        addAll(btnAddTask, btnListTasks,btnListapps,btnResume,btnCompany,btnCandidates,btnShowResume,btnShop,btnListfor,btnAddcat,btnListcat);

    }

}
