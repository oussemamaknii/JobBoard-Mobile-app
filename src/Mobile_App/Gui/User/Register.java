package Mobile_App.Gui.User;

import Mobile_App.Entities.User;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

public class Register extends Form {

    Form current;
    private static User User;

    public Register(Form previous, Resources res) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Sign Up | ", "WelcomeWhite")
        );

        getTitleArea().setUIID("Container");

        TextField firstName = new TextField(null, "Enter your first name", 20, TextField.ANY);
        TextField lastName = new TextField(null, "Enter your last name", 20, TextField.ANY);
        TextField mail = new TextField(null, "Enter your mail", 20, TextField.ANY);
        TextField password = new TextField(null, "Enter your password", 20, TextField.PASSWORD);
        TextField confpass = new TextField(null, "Confirm your password ", 20, TextField.ANY);
        TextField phone = new TextField(null, "Enter your phone number ", 20, TextField.NUMERIC);
        TextField address = new TextField(null, "Enter your address", 20, TextField.ANY);
        TextField professionalTitle = new TextField(null, "Enter your professional title ", 20, TextField.NUMERIC);
        Picker date = new Picker();
        firstName.getAllStyles().setMargin(LEFT, 0);
        lastName.getAllStyles().setMargin(LEFT, 0);
        mail.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        confpass.getAllStyles().setMargin(LEFT, 0);
        phone.getAllStyles().setMargin(LEFT, 0);
        address.getAllStyles().setMargin(LEFT, 0);
        professionalTitle.getAllStyles().setMargin(LEFT, 0);
        date.getAllStyles().setMargin(LEFT, 0);
        Button RegisterButton = new Button("Register");
        RegisterButton.setUIID("LoginButton");
        Button login = new Button("Sign In");
        login.setUIID("CreateNewAccountButton");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new login(res).show();
            }
        });
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        Container logoC = BoxLayout.encloseY(
                new ImageViewer(res.getImage("logo.png").scaled(500, 100))
        );
        add(BorderLayout.OVERLAY, logoC);
        Container by = BoxLayout.encloseY(
                spaceLabel,
                welcome,
                BorderLayout.center(firstName),
                BorderLayout.center(lastName),
                BorderLayout.center(mail),
                BorderLayout.center(phone),
                BorderLayout.center(password),
                BorderLayout.center(confpass),
                BorderLayout.center(address),
                BorderLayout.center(professionalTitle),
                BorderLayout.center(date),
                RegisterButton,
                login
        );
        add(BorderLayout.CENTER, by);
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }

}