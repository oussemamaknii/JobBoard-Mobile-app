package Mobile_App.Gui.User;

import Mobile_App.Entities.candidateResume;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Service.show;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;

public class showResume extends SideMenu {
    public showResume(Form previous, Resources res) {
        ArrayList<candidateResume>u = show.getInstance().getResume();
        for (candidateResume uu : u) {
            Container test = new Container();
            test.add(new Label(uu.getExperience()));
            test.add(new Label(uu.getResumeHeadline()));
            test.add(new Label(uu.getSkills()));
            addAll(test);
            show();
        }
    }
}
