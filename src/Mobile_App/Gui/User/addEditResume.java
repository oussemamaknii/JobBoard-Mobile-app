package Mobile_App.Gui.User;


import Mobile_App.Entities.Category;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Entities.candidateResume;
import Mobile_App.Gui.Offre_Emploi.ListViewOffer;
import Mobile_App.Service.Offer_Service;
import Mobile_App.Utils.Session;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.Date;

public class addEditResume extends Form {
    public addEditResume(Form previous, candidateResume resume) {
        setTitle("Add your Resume");
        setLayout(BoxLayout.y());
        if (resume.getUser_id() != Session.ConnectedUser.getId()) {
            TextField tfResumeHeadline = new TextField("", "Ex: Web Developper");
            TextField tfSkills = new TextField("", "Ex: php, symfony, js");
            TextField tfExperience = new TextField("", "Ex: worked 3years @ Facebook");
            Button resumeButton = new Button("Add Resume");
            resumeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }
}
