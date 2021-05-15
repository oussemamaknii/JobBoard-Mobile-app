package Mobile_App.Service;

import Mobile_App.Entities.User;
import Mobile_App.Utils.BCrypt;
import Mobile_App.Utils.Session;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class LoginService {

    public void login(String email, String password) {
        ConnectionRequest con = new ConnectionRequest();
        con.addRequestHeader("Content-Type", "application/json");
        con.setPost(false);
        con.addArgument("email", email);
        con.addArgument("password", password);
        con.setUrl("http://127.0.0.1/jobBoard/public/api/loginApiTest");
        User user = new User();
        con.addResponseListener((NetworkEvent evt) -> {
            if (con.getResponseCode() == 200) {
                try {
                    JSONParser jsonp = new JSONParser();
                    Map<String, Object> mapUser = (Map<String, Object>) jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    float id = (int) Float.parseFloat(mapUser.get("id").toString());
                    user.setId((int) id);
                    Session.ConnectedUser.setPassword(password);
                    Session.ConnectedUser.setId((int) Float.parseFloat(mapUser.get("id").toString()));
                    Session.ConnectedUser.setEmail(mapUser.get("email").toString());
                    Session.ConnectedUser.setFirstName(mapUser.get("firstName").toString());
                    Session.ConnectedUser.setLastName(mapUser.get("lastName").toString());
                    Session.ConnectedUser.setPhone((int) Float.parseFloat(mapUser.get("phone").toString()));
                    if (mapUser.get("imageName").toString().equals("")) {
                        Session.ConnectedUser.setImageName("image-blank.jpg");
                    } else Session.ConnectedUser.setImageName(mapUser.get("imageName").toString());
                    Session.ConnectedUser.setRoles(mapUser.get("roles").toString());
                    Session.ConnectedUser.setAdresse(mapUser.get("adresse").toString());
                    Session.ConnectedUser.setProfessionalTitle(mapUser.get("professionalTitle").toString());
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Session.ConnectedUser.setDateOfBirth(format.parse(mapUser.get("createdAt").toString()));

                    } catch (ParseException ex) {
                    }
                    System.out.println("connected User : " + Session.ConnectedUser);
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);


    }

    public void SingUp(String firstName, String lastName, Date dateOfBirth, int phone, String adresse, String professionalTitle, String password, String email) {

     String hashed = BCrypt.hashpw(password, BCrypt.gensalt(13));

        System.err.println(hashed);

        MultipartRequest con = new MultipartRequest();
        con.setUrl(Statics.BASE_URL_RYAAN + "api/register" + "?firstName=" + firstName + "&lastName=" + lastName + "&dateOfBirth=" + dateOfBirth + "&phone=" + phone + "&adresse=" + adresse + "&professionalTitle=" + professionalTitle + "&password=" + password + "&email=" + email);
        con.setPost(true);
        String filePath = FileSystemStorage.getInstance().getAppHomePath() ;
        String mime = "image/jpeg";
        String fichernom = System.currentTimeMillis() + ".jpeg";
        con.setFilename("file", fichernom);
        System.out.println(filePath);
        try {
            con.addData("file", filePath, mime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (con.getResponseCode() == 200) {
                    Dialog.show("Success", "Added Successfully", "Ok", null);
                } else {
                    Dialog.show("Failed", "Existing User", "Ok", null);
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

}
