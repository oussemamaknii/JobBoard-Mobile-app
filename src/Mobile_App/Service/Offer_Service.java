package Mobile_App.Service;

import Mobile_App.Entities.Category;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Offer_Service {
    public ArrayList<Offre_Emploi> Offers;
    public ArrayList<Category> categ;
    public ArrayList<ArrayList<String>> data;

    public static Offer_Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private Offer_Service() {
        req = new ConnectionRequest();
    }

    public static Offer_Service getInstance() {
        if (instance == null) {
            instance = new Offer_Service();
        }
        return instance;
    }
    public boolean addOffer(Offre_Emploi t) {
        String url = Statics.BASE_URL + "/addofferjson?titre=" + t.getTitre() + "&poste=" + t.getPoste() +
                "&description=" + t.getDescription() + "&location=" + t.getLocation()
                + "&file=" + t.getFile() + "&email=" + t.getEmail() + "&maxSalary=" + t.getMax_salary() +
                "&minSalary=" + t.getMin_salary() + "&categ=" + t.getCategory_id();
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

    public boolean modOffer(Offre_Emploi t) {
        String url = Statics.BASE_URL + "/updateofferjson?id=" + t.getId() + "&titre=" + t.getTitre() + "&poste=" + t.getPoste() +
                "&description=" + t.getDescription() + "&location=" + t.getLocation()
                + "&file=" + t.getFile() + "&email=" + t.getEmail() + "&maxSalary=" + t.getMax_salary() +
                "&minSalary=" + t.getMin_salary() + "&categ=" + t.getCategory_id() + "&date-debut=" + t.getDate_debut() +
                "&date_expiration=" + t.getDate_expiration();
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

    public ArrayList<Offre_Emploi> parseOffers(String jsonText) {
        try {
            Offers = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Offre_Emploi t = new Offre_Emploi();
                float id = Float.parseFloat(obj.get("id").toString());
                float min_salary = Float.parseFloat(obj.get("minSalary").toString());
                float max_salary = Float.parseFloat(obj.get("maxSalary").toString());
                t.setId((int) id);
                t.setTitre(obj.get("titre").toString());
                t.setPoste(obj.get("poste").toString());
                t.setDescription(obj.get("description").toString());
                /*Date date2 = Date.from(Instant.parse(obj.get("date_debut").toString()));
                t.setDate_debut(date2);
                Date date1 = Date.from(Instant.parse(obj.get("date_expiration").toString()));
                t.setDate_expiration(date1);*/
                t.setFile(obj.get("file").toString());
                t.setLocation(obj.get("location").toString());
                t.setMin_salary((int) min_salary);
                t.setMax_salary((int) max_salary);
                t.setEmail(obj.get("email").toString());
                Map<String, Object> categ = (Map<String, Object>) obj.get("categorie");
                t.setCategory_id((int) Float.parseFloat(categ.get("id").toString()));
                t.setCatname(categ.get("titre").toString());
                Offers.add(t);
            }
        } catch (IOException ex) {
        }
        return Offers;
    }

    public ArrayList<Category> parseCategs(String jsonText) {
        try {
            categ = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Category t = new Category();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setTitre(obj.get("titre").toString());
                categ.add(t);
            }
        } catch (IOException ex) {
        }
        return categ;
    }

    public ArrayList<Offre_Emploi> getAllOffers() {
        String url = Statics.BASE_URL + "/listofferjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Offers = parseOffers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Offers;
    }

    public ArrayList<ArrayList<String>> getdata() {
        String url = Statics.BASE_URL + "/data";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                data = parseData(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return data;
    }

    private ArrayList<ArrayList<String>> parseData(String jsonText) {
        try {
            data = new ArrayList<ArrayList<String>>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                ArrayList<String> t = new ArrayList<>();
                t.add(obj.get("nbr").toString());
                t.add(obj.get("titre").toString());
                t.add(obj.get("color").toString().substring(1));
                data.add(t);
            }
        } catch (IOException ex) {
        }
        return data;
    }

    public void deleteoffer(int o) {
        String url = Statics.BASE_URL + "/deleteofferjson?id=" + o;
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Category> getcategnames() {
        String url = Statics.BASE_URL + "/listcategjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categ = parseCategs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categ;
    }

    public void treat(int id) {
        String url = Statics.BASE_URL + "/treatappjson?id=" + id;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
