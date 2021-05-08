package Mobile_App.Gui.User;

import Mobile_App.Entities.User;
import Mobile_App.Utils.Session;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;



public class login extends Form {
    Form current;
    private static User User;
    public login(Resources res) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Sign In | ", "WelcomeWhite"),
                new Label("TITAN", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

        TextField login = new TextField(null, "Enter your username ! Exp: Admin_Admin", 20, TextField.ANY);
        TextField password = new TextField(null, "Enter tour password", 20, TextField.PASSWORD);
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);

        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        loginButton.addActionListener(e -> {
            Toolbar.setGlobalToolbar(false);
            User = Session.getInstance().getUser();
            System.out.println(User);


            Toolbar.setGlobalToolbar(true);
        });

        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.setUIID("CreateNewAccountButton");

        createNewAccount.addActionListener(new ActionListener() {

          @Override
            public void actionPerformed(ActionEvent evt) {
                new Register(current, res).show();

            }
        });
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

//        Container logoC = BoxLayout.encloseY(
//                new ImageViewer(res.getImage("blancTitan.png").scaled(500, 100))
//
//
//        );
//        add(BorderLayout.OVERLAY, logoC);
        Container by = BoxLayout.encloseY(
                welcome,

                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount
        );
        add(BorderLayout.CENTER, by);
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}