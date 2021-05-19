package Mobile_App.Service;


import Mobile_App.Entities.User;
import Mobile_App.Entities.candidateResume;
import com.codename1.io.ConnectionRequest;
import Mobile_App.Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class show {
    public ArrayList<User> users;
    public ArrayList<candidateResume> resumes;

    public static show instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private show() {
        req = new ConnectionRequest() {

        };
    }

    public static show getInstance() {
        if (instance == null) {
            instance = new show();
        }
        return instance;
    }
    public ArrayList<User> parseCandidates(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();
              //  int id = Integer.parseInt((obj.get("id").toString()));
                float phone = Float.parseFloat(obj.get("phone").toString());
                //        u.setId((int) id);
                u.setPhone((int) phone);
                if(obj.get("firstName") != null) {
                    u.setFirstName(obj.get("firstName").toString());
                }
                if(obj.get("lastName") != null) {
                    u.setLastName(obj.get("lastName").toString());
                }
                if(obj.get("professionalTitle") != null){
                    u.setProfessionalTitle(obj.get("professionalTitle").toString());
                }
                if(obj.get("adresse") != null){
                    u.setAdresse(obj.get("adresse").toString());
                }
                if(obj.get("email") != null){
                u.setEmail( obj.get("email").toString());
                }
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    if(obj.get("dateOfBirth") != null){
                    u.setDateOfBirth(format.parse(obj.get("dateOfBirth").toString()));
                    }

                } catch (ParseException ex) {
                }
                users.add(u);
            }
        } catch (IOException ex) {
        }
        return users;
    }

    public ArrayList<User> getAllCandidates() {
        String url = Statics.BASE_URL_RYAAN + "/api/candidatesApi";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseCandidates(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

    /// get all resume
    // problem list null
    public ArrayList<candidateResume> getResume() {
        String url = Statics.BASE_URL_RYAAN+"api/showResumeApi";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resumes = parseResumes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resumes;
    }
    public ArrayList<candidateResume> parseResumes(String jsonText) {
        try {
            resumes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                candidateResume res = new candidateResume();
                if(obj.get("experience") != null){
                    res.setExperience( obj.get("experience").toString());
                }
                if(obj.get("skills") != null){
                    res.setExperience( obj.get("skills").toString());
                }

                if(obj.get("ResumeHeadline") != null){
                    res.setExperience( obj.get("ResumeHeadline").toString());
                }

                resumes.add(res);
            }
        } catch (IOException ex) {
        }
        return resumes;
    }



}
