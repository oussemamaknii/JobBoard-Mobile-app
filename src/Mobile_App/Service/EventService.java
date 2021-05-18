package Mobile_App.Service;

import Mobile_App.Entities.Category;
import Mobile_App.Entities.Events;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EventService {

    public ArrayList<Events> Events;

    public static EventService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private EventService() {
        req = new ConnectionRequest();
    }

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public boolean addOffer(Events t) {
        String url = Statics.BASE_URL + "/ajoutereventjson?nom=" + t.getNom() + "&date=" + t.getDate() +
                "&description=" + t.getDescription() + "&prix=" + t.getPrix()
                + "&adresse=" + t.getAdresse() + "&image=" + t.getFile() + "&nbrePlace="+t.getNbre_place();
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

    public ArrayList<Events> parseEvents(String jsonText) {
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            Events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Events t = new Events();
                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                float nbrePlace = Float.parseFloat(obj.get("nbrePlace").toString());
                t.setId((int) id);
                t.setNom(obj.get("nom").toString());
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-d");
                LocalDate date1 = LocalDate.parse(obj.get("date").toString().substring(0,9),df);
                t.setDate( date1);
                t.setDescription(obj.get("description").toString());
                t.setPrix((int) prix);
                t.setAdresse(obj.get("adresse").toString());
                t.setFile(obj.get("image").toString());
                t.setNbre_place((int) nbrePlace);
                Events.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Events;
    }

    public ArrayList<Events> getAllEvents() {
        String url = Statics.BASE_URL + "/listEventjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Events;
    }

    public void deleteoffer(int o) {
        String url = Statics.BASE_URL + "/deleteeventjson?id=" + o;
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }


    public boolean modOffer(Events t) {
        String url = Statics.BASE_URL + "/updateeventjson?id=" + t.getId() + "&nom=" + t.getNom() + "&date=" + t.getDate() +
                "&description=" + t.getDescription() + "&prix=" + t.getPrix()
                + "&adresse=" + t.getAdresse() + "&image=" + t.getFile() + "&nbrePlace="+t.getNbre_place();
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
}
