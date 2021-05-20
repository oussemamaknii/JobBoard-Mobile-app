package Mobile_App.Gui.User;

import Mobile_App.Entities.User;
import Mobile_App.Gui.HomeForm;
import Mobile_App.Service.LoginService;
import Mobile_App.Utils.Session;
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
    private Button Forget_Password;

    public login(Resources res) {

        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Sign In | ", "WelcomeWhite"),
                new Label("JobHub", "WelcomeBlue")
        );
        current = this;

        getTitleArea().setUIID("Container");
        TextField login = new TextField(null, "Enter your username ! Exp: Admin_Admin", 20, TextField.ANY);
        TextField password = new TextField(null, "Enter your password", 20, TextField.PASSWORD);
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
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                LoginService ser = new LoginService();
                ser.login(login.getText(), password.getText());

                if (Session.ConnectedUser.getId() > 0 && Session.ConnectedUser.isActive()) {
                    Toolbar.setGlobalToolbar(true);
                    new HomeForm(res).show();
                } else {
                    if (!Session.ConnectedUser.isActive()) {
                        Dialog.show("Disabled Account!", "Check Admin for futher informations!", "Ok", null);

                    } else {
                        Dialog.show("Error!", "Login ou mot de passe incorrect!", "Ok", null);

                    }
                }

            }
        });

        Button forgetPassword = new Button("forget Password?");
        forgetPassword.setUIID("forgetPassword");
        forgetPassword.addActionListener(e -> {
            new forgetPassword(current,res).show();
        });
        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.setUIID("CreateNewAccountButton");


        createNewAccount.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Form mainForm = new Form();
                mainForm.setLayout(new BorderLayout());
                mainForm.getToolbar().setHidden(true);
                mainForm.getContentPane().removeAll();
                Register reg = new Register(current, res);
                mainForm.addComponent(BorderLayout.CENTER, reg);
                mainForm.revalidate();
                mainForm.show();
            }
        });

        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        Container by = BoxLayout.encloseY(
                welcome,

                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton, forgetPassword,
                createNewAccount
        );
        add(BorderLayout.CENTER, by);
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}