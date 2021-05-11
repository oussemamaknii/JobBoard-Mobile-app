/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_App.Gui;

import Mobile_App.Gui.Demande.ListApps;
import Mobile_App.Gui.Offre_Emploi.AddOffer;
import Mobile_App.Gui.Offre_Emploi.ListViewOffer;
import Mobile_App.Gui.Offre_Emploi.Stat;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

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

        btnAddTask.addActionListener(e -> new AddOffer(current,null, res).show());
        btnListTasks.addActionListener(e -> new ListViewOffer(current, res).show());
        btnListapps.addActionListener(e -> new ListApps(current, res).show());
        addAll(btnAddTask, btnListTasks,btnListapps);

    }

}
