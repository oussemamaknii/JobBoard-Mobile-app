package Mobile_App.Service;

import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Entities.candidateResume;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class addEditResumeService {
    public static addEditResumeService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private addEditResumeService() {
        req = new ConnectionRequest();
    }

    public static addEditResumeService getInstance() {
        if (instance == null) {
            instance = new addEditResumeService();
        }
        return instance;
    }
    public boolean addResume(candidateResume t) {
        String url = Statics.BASE_URL_RYAAN + "/api/candidateresumeApi?ResumeHeadline=" + t.getResumeHeadline() + "&skills=" + t.getSkills() +
                "&experience=" + t.getExperience();
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<candidateResume> resumes;

    public ArrayList<candidateResume> parseTasks(String jsonText) {
        try {
            resumes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                candidateResume resume = new candidateResume();
                float id = Float.parseFloat(obj.get("id").toString());
                float user_id = Float.parseFloat(obj.get("userId").toString());
                resume.setId((int) id);
                resume.setUser_id((int)user_id);
                resume.setResumeHeadline(obj.get("resumeHeadline").toString());
                resume.setSkills(obj.get("skills").toString());
                resume.setExperience(obj.get("experience").toString());
                resumes.add(resume);
            }
        } catch (IOException ex) {
        }
        return resumes;
    }
}
