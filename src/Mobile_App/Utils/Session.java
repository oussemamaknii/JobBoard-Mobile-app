package Mobile_App.Utils;

import com.codename1.facebook.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Session {
     private int id;
    private String email ;
    private String password;
    private User user;
    private static Session session;

    public Session() {
    }

    public static Session getInstance() {
        if (session == null) session = new Session();
        return session;
    }

    public static User ConnectedUser = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setParameters(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
