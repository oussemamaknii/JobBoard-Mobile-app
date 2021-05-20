package Mobile_App.Gui.User;

import Mobile_App.Entities.User;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Service.show;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class showCandidates extends SideMenu {
    public void showCandidatesOld(Form previous, Resources res) {

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

    public showCandidates(Form previous, Resources res) {
        setTitle("Candidate's List");
        setLayout(BoxLayout.y());
        setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        setScrollableY(true);
        setScrollableX(true);
        setScrollVisible(false);

        ArrayList<User> candidates = show.getInstance().getAllCandidates();


        Container cnty = new Container(BoxLayout.y());

        Container cnthelpers = new Container(BoxLayout.y());
        Label lbhelpers = new Label("Candidate's Name");
        cnthelpers.add(lbhelpers);

        Container speclts = new Container(BoxLayout.y());
        Label lbspec = new Label("Professional Title");
        speclts.add(lbspec);

        Container times = new Container(BoxLayout.y());
        Label lbtimes = new Label("Date of Birth");
        times.add(lbtimes);

        Container actions = new Container(BoxLayout.y());
        Label lbsel = new Label("Email");
        actions.add(lbsel);

        Container tel = new Container(BoxLayout.y());
        Label tel2 = new Label("Telephone Number");
        tel.add(tel2);

        for (User user : candidates) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            String pattern = "dd MMM yy HH:mm a";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String dd = simpleDateFormat.format(user.getDateOfBirth());
            //Label lbimg = new Label(res.getImage("user.png"));
            lbhelpers = new Label(user.getFirstName());
            lbspec = new Label(user.getLastName());
            lbtimes = new Label(dd);
            cnthelpers.add(lbhelpers);
            speclts.add(user.getProfessionalTitle());



       //     speclts.add(lbspec);
            times.add(lbtimes);
            tel.add(String.valueOf(user.getPhone()));
            actions.add(user.getEmail());

        }
        Container table = new Container(BoxLayout.x());
        table.addAll(cnthelpers, speclts, times, actions,tel);
        table.setScrollableX(true);
        add(BorderLayout.OVERLAY, table);
        show();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> previous.showBack());
    }
}
