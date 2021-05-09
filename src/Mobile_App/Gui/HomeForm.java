/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_App.Gui;

import Mobile_App.Gui.Demande.ListApps;
import Mobile_App.Gui.Offre_Emploi.AddOffer;
import Mobile_App.Gui.Offre_Emploi.ListViewOffer;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import Mobile_App.Gui.User.addEditResume;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    public HomeForm() {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Offer");
        Button btnListTasks = new Button("List Offers");
        Button btnListapps = new Button("List Applications");
        Button btnResume = new Button("Resume");
        btnAddTask.addActionListener(e -> new AddOffer(current,null).show());
        btnListTasks.addActionListener(e -> new ListViewOffer(current).show());
        btnResume.addActionListener(e -> new addEditResume(current,null).show());
        btnListapps.addActionListener(e -> new ListApps(current).show());
        addAll(btnAddTask, btnListTasks,btnListapps,btnResume);

    }

}
