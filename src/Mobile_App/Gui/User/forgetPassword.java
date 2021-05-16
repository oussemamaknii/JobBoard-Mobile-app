package Mobile_App.Gui.User;

import Mobile_App.Entities.User;
import Mobile_App.Utils.BaseForm;
import Mobile_App.Utils.Session;
import com.codename1.components.FloatingHint;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

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
    public static final String ACCOUNT_SID = "ACf06d8ef396999213b853a5dd057af9e2";
    public static final String AUTH_TOKEN = "654c38e49aa6b0878c74d472b086e277";
    public static Resources theme;

    public forgetPassword(Resources res) {

        add(new Label(res.getImage("logo.png"), "LogoLabel"));

        TextField email = new TextField("", "Email", 20, TextField.ANY);
        email.setSingleLineTextArea(false);

        Button signIn = new Button("Sign In");

        Container content = BoxLayout.encloseY(
                new FloatingHint(email),
                new BaseForm().createLineSeparator(),
                new BaseForm().createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter()
        );
        add(content);

        signIn.addActionListener(e -> {
            if (email.getText().isEmpty()) {
                Dialog.show("Error", "Email est vide ", "Ok", null);

            } else {
                Session.setForgetPassMail(email.getText());
                code(res);

            }
        });
        show();

    }

    public void code(Resources res) {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        Session.setSaltToken(saltStr);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21629903274"),
                new PhoneNumber("+14156505681"), "Votre Code est : " + saltStr).create();
        f = new Form();

        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        add(new Label(res.getImage("Logo.png"), "LogoLabel"));
        add(new Label("Nous avons envoyer un code à votre numéro"));
        TextField email1 = new TextField("", "Code", 20, TextField.ANY);
        email1.setSingleLineTextArea(false);
        Button signIn1 = new Button("Envoyer !");
        Container content1 = BoxLayout.encloseY(
                new FloatingHint(email1),
                new BaseForm().createLineSeparator(),
                new BaseForm().createLineSeparator(),
                signIn1,
                FlowLayout.encloseCenter()
        );
        add(content1);
        show();
    }


}