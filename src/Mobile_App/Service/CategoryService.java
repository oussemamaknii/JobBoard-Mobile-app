package Mobile_App.Service;

import Mobile_App.Entities.Category;
import Mobile_App.Entities.Events;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CategoryService {
    public ArrayList<Category> Category;

    public static CategoryService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public CategoryService() {
        req = new ConnectionRequest();
    }

    public static CategoryService getInstance() {
        if (instance == null) {
            instance = new CategoryService();
        }
        return instance;
    }

    public boolean Addcat(Category t) {
        String url = Statics.BASE_URL_OUMA+"/category/newCat*?titre=" + t.getTitre() + "&descriptionc=" + t.getDescriptionc(); //création de l'URL

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
    public ArrayList<Category> parseTasks(String jsonText) {
        try {
            Category = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Category t = new Category();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setTitre(obj.get("titre").toString());
                t.setDescriptionc(obj.get("descriptionc").toString());
                // t.setCouleur(obj.get("couleur").toString());

                Category.add(t);


            }
        } catch (IOException ex) {
        }
        return Category;
    }

    public ArrayList<Category> getAllCat() {
        String url = Statics.BASE_URL_OUMA+ "/category*";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Category = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Category;
    }
/**
    public boolean modcat(Category t) {
        String url = Statics.BASE_URL_OUMA+ "/editcatjson/?id="+ t.getId() + "&titre=" + t.getTitre() + "&desciptionc=" + t.getDescriptionc();
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
    }**/
/**
    public boolean deletecat(int  id ) {
        String url = Statics.BASE_URL_OUMA +"/deletjsone?id="+id;


        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }**/
public void deletecat(int o) {
    String url = Statics.BASE_URL_OUMA + "/deletecatjson?id=" + o;
    req.setUrl(url);
    req.setPost(false);
    NetworkManager.getInstance().addToQueueAndWait(req);
}
    public boolean modcat(Category t) {
        String url = Statics.BASE_URL_OUMA+ "/editcatjson/?id="+ t.getId() + "&titre=" + t.getTitre() + "&desciptionc=" + t.getDescriptionc();
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

