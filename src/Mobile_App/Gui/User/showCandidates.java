package Mobile_App.Gui.User;

import Mobile_App.Entities.User;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Service.show;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class showCandidates extends SideMenu {
    public showCandidates(Form previous, Resources res) {


    ArrayList<User> u = show.getInstance().getAllCandidates();
        for(User uu : u)
    {

        Container Conainter = new Container();
        Conainter.add(new Label(uu.getFirstName()));
        Conainter.add(new Label(uu.getLastName()));
        Conainter.add(new Label(String.valueOf(uu.getPhone())));
        Conainter.add(new Label(uu.getEmail()));
        Conainter.add(new Label(uu.getProfessionalTitle()));
        Conainter.add(new Label(uu.getAdresse()));
        String date = (new SimpleDateFormat("yyyy-MM-dd")).format(uu.getDateOfBirth());
        Label dateOfBirth = new Label(date);
        Conainter.add(date);

        add(Conainter);
        previous.show();



    }
    }
}
