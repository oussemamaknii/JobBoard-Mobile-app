package Mobile_App.Gui.User;


import Mobile_App.Entities.Category;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Entities.candidateResume;
import Mobile_App.Gui.Offre_Emploi.ListViewOffer;
import Mobile_App.Service.Offer_Service;
import Mobile_App.Service.addEditResumeService;
import Mobile_App.Utils.Session;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import java.util.ArrayList;
import java.util.Date;

public class addEditResume extends Form {
    public addEditResume(Form previous, candidateResume resume) {
        setTitle("Add your Resume");
        setLayout(BoxLayout.y());
        if (resume == null) {
          //  resume.getUser_id() != Session.ConnectedUser.getId()
            TextField tfResumeHeadline = new TextField("", "Ex: Web Developper");
            TextField tfSkills = new TextField("", "Ex: php, symfony, js");
            TextField tfExperience = new TextField("", "Ex: worked 3years @ Facebook");
            Button resumeButton = new Button("Add Resume");
            resumeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        candidateResume resume1 = new candidateResume(0,Session.ConnectedUser.getId(), tfResumeHeadline.getText(),tfSkills.getText(),
                                tfExperience.getText());
                        if (addEditResumeService.getInstance().addResume(resume1)) {
                            Dialog.show("Success", "Added Successfully !", new Command("OK"));
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            });
            addAll(tfResumeHeadline,tfSkills,tfExperience);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                    , e -> previous.showBack());
        } else {
            TextField tfResumeHeadline = new TextField("", "Ex: Web Developper");
            TextField tfSkills = new TextField("", "Ex: php, symfony, js");
            TextField tfExperience = new TextField("", "Ex: worked 3years @ Facebook");
            Button resumeButton = new Button("Add Resume");

            Button btnValider = new Button("Update Resume");

            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        candidateResume resume1 = new candidateResume(resume.getId(),Session.ConnectedUser.getId(), tfResumeHeadline.getText(),tfSkills.getText(),
                                tfExperience.getText());
                        if (addEditResumeService.getInstance().addResume(resume1)) {
                            Dialog.show("Success", "Updated Successfully !", new Command("OK"));
                            Form f2 = new ListViewOffer(null);
                            f2.show();
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            });

            addAll(tfResumeHeadline,tfSkills,tfExperience);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                    , e -> previous.showBack());
        }
    }
    }
