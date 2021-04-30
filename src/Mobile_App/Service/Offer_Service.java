package Mobile_App.Service;

import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Offer_Service {
    public ArrayList<Offre_Emploi> Offers;

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
        String url = Statics.BASE_URL + "/offre_emploi/" + t.getTitre() + "/" + t.getPoste() + "/" + t.getDescription() + "/" + t.getLocation()
                + "/" + t.getFile() + "/" + t.getEmail() + "/" + t.getDate_debut() + "/" + t.getDate_expiration()
                + "/" + t.getMax_salary() + "/" + t.getMin_salary() + "/" + t.getCategory_id(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Offre_Emploi> parseTasks(String jsonText) {
        try {
            Offers = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Offre_Emploi t = new Offre_Emploi();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setTitre(obj.get("titre").toString());
                t.setPoste(obj.get("poste").toString());
                t.setDescription(obj.get("description").toString());
                t.setDate_debut((LocalDate) obj.get("date_debut"));
                t.setTitre(obj.get("date_expiration").toString());
                t.setFile(obj.get("file").toString());
                t.setLocation(obj.get("location").toString());
                t.setMin_salary(Integer.parseInt(obj.get("min_salary").toString()));
                t.setMax_salary(Integer.parseInt(obj.get("email").toString()));
                t.setCategory_id(Integer.parseInt(obj.get("categorie_id").toString()));
                Offers.add(t);
            }
        } catch (IOException ex) {
        }
        return Offers;
    }

    public ArrayList<Offre_Emploi> getAllOffers() {
        String url = Statics.BASE_URL + "/offre_emploi/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Offers = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Offers;
    }
}
