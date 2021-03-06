package Mobile_App.Utils;


import Mobile_App.Entities.User;
import com.codename1.io.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Session {
     private int id;
    private String email ;
    private String password;
    private User user;
    private static Session session;
    public static String saltToken;
    public static String forgetPassMail;
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

    public static String getSaltToken() {
        return saltToken;
    }

    public static void setSaltToken(String saltToken) {
        Session.saltToken = saltToken;
    }

    public static String getForgetPassMail() {
        return forgetPassMail;
    }

    public static void setForgetPassMail(String forgetPassMail) {
        Session.forgetPassMail = forgetPassMail;
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
    public ArrayList<User> getListUsers(String json) {

        ArrayList<User> listUsers = new ArrayList<>();

        try {
            // System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) Users.get("root");
            for (Map<String, Object> mapUser : list) {
                User user = new User();
                user.setId((int) Float.parseFloat(mapUser.get("id").toString()));
                user.setActive(Boolean.parseBoolean(mapUser.get("isActive").toString()));
                user.setAdresse(mapUser.get("username").toString());
                user.setRoles(mapUser.get("roles").toString());
                user.setFirstName(mapUser.get("firstName").toString());
                user.setLastName(mapUser.get("lastName").toString());
                user.setImageName(mapUser.get("imageName").toString());
                user.setEmail(mapUser.get("email").toString());
                user.setPhone(Integer.parseInt(mapUser.get("phone").toString()));
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                user.setDateOfBirth(format.parse(mapUser.get("dateOfBirth").toString()));
                listUsers.add(user);

            }

        } catch (IOException | ParseException ex) {
        }
        System.out.println(listUsers);
        return listUsers;

    }


    }


