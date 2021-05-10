package Mobile_App.Service;

import Mobile_App.Entities.*;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import Mobile_App.Entities.Category;
import Mobile_App.Entities.Offre_Emploi;
import com.codename1.io.ConnectionRequest;

import java.util.ArrayList;

public class DemandeService {
    public ArrayList<Demande_Recrutement> apps;

    public static DemandeService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private DemandeService() {
        req = new ConnectionRequest();
    }

    public static DemandeService getInstance() {
        if (instance == null) {
            instance = new DemandeService();
        }
        return instance;
    }


    public void deleteapp(int o) {
        String url = Statics.BASE_URL + "/deleteappjson?id="+o;
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void applytojob(int idoffer, int iduser) {
        String url = Statics.BASE_URL + "/applyjson?idoffer=" + idoffer + "&iduser=" + iduser;
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Demande_Recrutement> parseApps(String jsonText) {
        try {
            apps = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Demande_Recrutement t = new Demande_Recrutement();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                Map<String, Object> offre = (Map<String, Object>) obj.get("offre");
                t.setOfftit(offre.get("titre").toString());
                t.setOffre_id((int) Float.parseFloat(offre.get("id").toString()));
                Map<String, Object> user = (Map<String, Object>) obj.get("candidat");
                t.setUsername(user.get("firstName").toString()+" "+user.get("lastName").toString());
                t.setCandidat_id((int) Float.parseFloat(offre.get("id").toString()));
                apps.add(t);

            }
        } catch (IOException ex) {

        }
        return apps;
    }

    public ArrayList<Demande_Recrutement> getAllApps() {
        String url = Statics.BASE_URL + "/listappjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                apps = parseApps(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return apps;
    }
}
