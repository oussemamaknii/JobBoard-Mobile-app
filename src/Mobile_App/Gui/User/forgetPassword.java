package Mobile_App.Gui.User;

import Mobile_App.Entities.User;
import Mobile_App.Utils.BaseForm;
import Mobile_App.Utils.Session;
import com.codename1.components.FloatingHint;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.util.Random;


public class forgetPassword extends Form {
    Form current;
    private static User User;
    public static String Codex;
    private Form f;
    private TextField username;
    private TextField password;
    private Button connecter;
    private Button SignUp;
    private Button Forget_Password;
    public forgetPassword(Resources res) {

        f = new Form();

        f.getTitleArea().setUIID("Container");
        f.setUIID("SignIn");

        f.add(new Label(res.getImage("Logo.png"), "LogoLabel"));

        TextField phone = new TextField("", "Phone", 20, TextField.ANY);
        phone.setSingleLineTextArea(false);

        Button signIn = new Button("Sign In");

        Container content = BoxLayout.encloseY(
                new FloatingHint(phone),
                new BaseForm().createLineSeparator(),
                new BaseForm().createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter()
        );
        f.add(content);

        signIn.addActionListener(e -> {
            if (phone.getText().isEmpty()) {
                Dialog.show("Error", "Phone est vide ", "Ok", null);

            } else {
//                Beblio.setMail(phone.getText());
//                code();
         Form sms = new Form();
//                taz.setLayout(new BorderLayout());
//                taz.getToolbar().setHidden(true);
//                taz.getContentPane().removeAll();
//                CodeController forumController = new CodeController();
//                forumController.initialize();
//                taz.addComponent(BorderLayout.CENTER, forumController.getView());
//                taz.revalidate();
                sms.show();

            }
        });
        f.show();
    }

//    public void code() {
//
//        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        StringBuilder salt = new StringBuilder();
//        Random rnd = new Random();
//        while (salt.length() < 5) { // length of the random string.
//            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//            salt.append(SALTCHARS.charAt(index));
//        }
//        String saltStr = salt.toString();
//        Beblio.setSaltStr(saltStr);
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21658804719"),
//                new PhoneNumber("+19098940842"), "Votre Code est : " + saltStr).create();
//        Codex =saltStr;
//        theme = UIManager.initFirstTheme("/theme_1");
//        f = new Form();
//
//        f.getTitleArea().setUIID("Container");
//        f.setUIID("SignIn");
//
//        f.add(new Label(theme.getImage("Logo.png"), "LogoLabel"));
//        f.add(new Label("Nous Avons Envoyer un code a votre numero"));
//
//        TextField email1 = new TextField("", "Code", 20, TextField.ANY);
//        email1.setSingleLineTextArea(false);
//
//        Button signIn1 = new Button("Envoyer !");
//
//        Container content1 = BoxLayout.encloseY(
//                new FloatingHint(email1),
//                new Mobile_App.Gui.BaseForm().createLineSeparator(),
//                new Mobile_App.Gui.BaseForm().createLineSeparator(),
//                signIn1,
//                FlowLayout.encloseCenter()
//        );
//
//
//        f.add(content1);
//        f.show();
//    }


}