package Mobile_App.Gui.User;

import Mobile_App.Entities.User;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Service.show;
import com.codename1.ui.*;
import com.codename1.ui.util.Resources;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class showCandidates extends SideMenu {
    public showCandidates(Form previous, Resources res) {
        ArrayList<User> u = show.getInstance().getAllCandidates();
        for (User uu : u) {
            Container cont = new Container();
            cont.add(new Label(uu.getFirstName()));
            cont.add(new Label(uu.getLastName()));
            cont.add(new Label(String.valueOf(uu.getPhone())));
            cont.add(new Label(uu.getEmail()));
            cont.add(new Label(uu.getProfessionalTitle()));
            cont.add(new Label(uu.getAdresse()));
            String date = (new SimpleDateFormat("yyyy-MM-dd")).format(uu.getDateOfBirth());
            Label dateOfBirth = new Label(date);
            cont.add(date);
        addAll(cont);
        show();
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                    , e -> previous.showBack());
    }
}
}
