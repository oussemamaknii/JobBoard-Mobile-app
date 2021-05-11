package Mobile_App.Gui.event;

import Mobile_App.Entities.Comment;
import Mobile_App.Entities.Events;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.HomeForm;
import Mobile_App.Gui.Offre_Emploi.ListViewOffer;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Main;
import Mobile_App.Service.CommentsService;
import Mobile_App.Service.DemandeService;
import Mobile_App.Service.EventService;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

import java.util.Date;
import java.util.List;

public class EventsInfod extends SideMenu {


    public EventsInfod(Events s, Form previous, Resources res) {
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);
        this.setTitle("Event Infos");
        Container all = new Container();
        all.setLayout(new FlowLayout(CENTER, CENTER));
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> previous.showBack());
        try {
            Container details = new Container(BoxLayout.y());
            Container titleDuree = new Container(BoxLayout.x());

            ImageViewer image = new ImageViewer(Main.theme.getImage("job.png").scaled(250, 350));

            Label lbTitle = new Label(s.getNom());
            Label lDescription = new Label(s.getDescription());
            Label lDuree = new Label(String.valueOf(s.getAdresse()));

            Container buttons = new Container(BoxLayout.x());
            Button delete = new Button("Delete");
            Button Update = new Button("Update");
            delete.addActionListener(e -> {
                EventService.getInstance().deleteoffer(s.getId());
                Dialog.show("Success", "Deleted Successfully !", new Command("OK"));
                Form f2 = new ListViewEvent(new HomeForm(res), res);
                f2.show();
            });
            Update.addActionListener(e -> {
                Form f = new AddEvent(this, s, res);
                f.show();
            });

            buttons.addAll(delete, Update);

            titleDuree.addAll(lbTitle, lDuree);
            details.addAll(titleDuree, lDescription);

            Container comments = new Container(BoxLayout.y());
            comments.setScrollableY(true);


            all.addAll(image, details, buttons);
            this.add(all);
            this.setLayout(BoxLayout.y());
            Container titre = new Container(new FlowLayout(CENTER, CENTER));
            comments.add(titre.add(new Label("All Comments")));
            List<Comment> comm = CommentsService.getInstance().getAllComments(s.getId());
            for (int i = 0; i < comm.size(); i++) {
                comments.add(addComm(comm.get(i), res));
            }
            Container ajout = new Container(BoxLayout.x());
            Button btnaj = new Button("COMMENT");
            btnaj.setWidth(1);
            TextField newc = new TextField("", "New Comment");
            newc.setWidth(2);
            btnaj.addActionListener(e -> {
                Comment newa = new Comment(0, "user name", "user email",
                        9693, newc.getText(), (Date) new Date(System.currentTimeMillis()), s.getId());
                comments.add(addComm(newa, res));
                CommentsService.getInstance().addOffer(newa);
                newc.clear();
            });
            ajout.addAll(newc, btnaj);
            this.addAll(comments, ajout);

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private Container addComm(Comment comment, Resources res) {
        Container c = new Container();
        Label time = new Label(comment.getCreated_at().toString().substring(0, 7) + " / " + comment.getName() + " : " + comment.getMessage());
        c.addAll(time);
        return c;
    }
}
