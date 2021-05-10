package Mobile_App.Service;

import Mobile_App.Entities.User;
import Mobile_App.Utils.Session;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Session.ConnectedUser.setProfessionalTitle(mapUser.get("professionalTitle").toString());
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
    public void SingUp(String username , String password , String nom, String email , String prenom ,String phonenumber , String imgPath ) {
//
//        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(13));
        System.err.println(username);
//
//        ConnectionRequest con=new ConnectionRequest();
//
//        con.setUrl(Statics.BASE_URL_RYAAN+"api/register" + username+"&email="+email+"&password=" +hashed.substring(0, 2)+"y"+hashed.substring(3)+"&firstname=" + username +"&prenom="+prenom+"&roles=a:0:{}"+"&pass="+password+"&image="+imgPath+"&phone="+phonenumber);
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                byte[] data = (byte[]) evt.getMetaData();
//                String s = new String(data);
//                if (s.equals("success")) {
//                    Dialog.show("Succés", "ajout effectué", "Ok", null);
//
//
//                }
//                else {
//
//                    Dialog.show("Echec", "Utilisateur existe deja", "Ok", null);
//                }
//            }
//        });
//        NetworkManager.getInstance().addToQueue(con);
    }

}
