package Mobile_App.Service;

import Mobile_App.Entities.Formation;

import Mobile_App.Service.FormationService;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FormationService {
    public ArrayList<Formation> Formation;

    public static FormationService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public FormationService() {
        req = new ConnectionRequest();
    }

    public static FormationService getInstance() {
        if (instance == null) {
            instance = new FormationService();
        }
        return instance;
    }

    public boolean Addfor(Formation t) {
        String url = Statics.BASE_URL_OUMA+"/formation/newformation*?nom=" + t.getNom() + "&formateur=" + t.getFormateur()+"&description=" + t.getDescription()+"&date_debut=" + t.getDate_debut()+"&date_fin=" + t.getDate_fin()+"&adresse=" + t.getAdresse()+"&mail=" + t.getMail()+"&tel=" + t.getTel()+"&prix=" + t.getPrix(); //création de l'URL

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
    public ArrayList<Formation> parseTasks(String jsonText) {
        try {
            Formation = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Formation t = new Formation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);

                //t.setCategory_id(Integer.parseInt(obj.get("category_id").toString()));
                t.setNom(obj.get("nom").toString());
                t.setFormateur(obj.get("formateur").toString());
                t.setDescription(obj.get("description").toString());
            //   Map<String, Object> categ = (Map<String, Object>) obj.get("category");
             //  t.setCategory_id((int) Float.parseFloat(category.get("id").toString()));
             //   t.setCatname(category.get("titre").toString());
                // t.setDate_debut(Date.valueOf(obj.get("date_debut").toString()));
                //  Date date_debut = Date.from(Instant.parse(obj.get("date_debut").toString()));
                //  t.setDate_debut(date_debut);
                //  Date date_fin = Date.from(Instant.parse(obj.get("date_fin").toString()));
                //  t.setDate_fin(date_fin);

                t.setAdresse(obj.get("adresse").toString());
                t.setMail(obj.get("mail").toString());
                //**prix wtel**/

                Formation.add(t);


            }
        } catch (IOException ex) {
        }
        return Formation;
    }


    public ArrayList<Formation> getAllFor() {
        String url = Statics.BASE_URL_OUMA+ "/formation*";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Formation = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Formation;
    }
    public void deletefor(int o) {
        String url = Statics.BASE_URL_OUMA + "/deletjsonefor?id=" + o;
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }


}

