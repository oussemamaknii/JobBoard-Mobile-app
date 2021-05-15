package Mobile_App.Service;


import Mobile_App.Entities.company;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class addEditCompanyService {
    public static addEditCompanyService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private addEditCompanyService() {
        req = new ConnectionRequest();
    }

    public static addEditCompanyService getInstance() {
        if (instance == null) {
            instance = new addEditCompanyService();
        }
        return instance;
    }
    public boolean addEditCompany(company comp) {
        String url = Statics.BASE_URL_RYAAN + "apiCompany/AddEditCompanyApi?companyName=" + comp.getCompanyName() + "&contactEmail="+comp.getContactEmail()+"&website="+comp.getWebsite()+"&foundedDate="+comp.getFoundedDate()+"&category="+comp.getCategory()+"&country="+comp.getCountry()+"&description="+comp.getDescription()+"&contactPhone="+comp.getContactPhone()+"&companyAdress="+comp.getCompanyAddress()+"&facebookLink="+comp.getFacebookLink();
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

    public ArrayList<company> companies;

    public ArrayList<company> parseTasks(String jsonText) {
        try {
            companies = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                company comp = new company();
                float id = Float.parseFloat(obj.get("id").toString());
                float user_id = Float.parseFloat(obj.get("userId").toString());
                resume.setId((int) id);
                resume.setUser_id((int)user_id);
                resume.setResumeHeadline(obj.get("resumeHeadline").toString());
                resume.setSkills(obj.get("skills").toString());
                resume.setExperience(obj.get("experience").toString());
                companies.add(comp);
            }
        } catch (IOException ex) {
        }
        return companies;
    }
}
