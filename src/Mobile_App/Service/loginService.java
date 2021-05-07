package Mobile_App.Service;

import Mobile_App.Entities.User;
import Mobile_App.Utils.Session;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class loginService {
    public void login(String email, String password) {
        ConnectionRequest con = new ConnectionRequest();
        con.addRequestHeader("Content-Type", "application/json");
        con.setPost(false);
        con.addArgument("email", email);
        con.addArgument("password", password);
        con.setUrl("http://127.0.0.1/jobBoard/public/api/loginApiTest");
        User user = new User();
        con.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser jsonp = new JSONParser();
                Map<String, Object> mapUser = (Map<String, Object>) jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println("mapUser : " + mapUser.get("id").toString());

                float id = (int) Float.parseFloat(mapUser.get("id").toString());
                System.out.println("id : " + mapUser);
                user.setId((int) id);

                Session.ConnectedUser.setPassword(password);
                try {
                    Session.ConnectedUser.setId((int) Float.parseFloat(mapUser.get("id").toString()));
                    Session.ConnectedUser.setEmail(mapUser.get("email").toString());
                    System.out.println("connected User : " + Session.ConnectedUser);
                } catch (NullPointerException n) {

                }
                //}
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);


    }

}
