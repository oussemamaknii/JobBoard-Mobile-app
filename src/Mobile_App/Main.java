package Mobile_App;


import static com.codename1.ui.CN.*;

import Mobile_App.Gui.BaseForm;
import Mobile_App.Gui.HomeForm;
import Mobile_App.Gui.User.AddEditCompany;
import Mobile_App.Gui.User.Register;
import Mobile_App.Gui.User.login;
import Mobile_App.Utils.Session;
import com.codename1.components.FloatingHint;
import com.codename1.social.Login;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;


import java.io.IOException;
import java.util.Random;

import Mobile_App.Service.LoginService;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose
 * of building native mobile applications using Java.
 */
public class Main {

    private Form current;
    public static Resources theme;
    public static Resources theme1;
    //    UtilService utilService = UtilService.getInstance();
    public static String Codex;
    private Form f;
    private TextField username;
    private TextField password;
    private Button connecter;
    private Button SignUp;
    private Button Forget_Password;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");


        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if (err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }


    public void start() {
        if (current != null) {
            current.show();
            return;
        }
        new login(theme).show();
    }

}
