package Mobile_App.Service;

import Mobile_App.Entities.event;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Event_Service {
    public ArrayList<event> events;

    public static Event_Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private Event_Service() {
        req = new ConnectionRequest();
    }

    public static Event_Service getInstance() {
        if (instance == null) {
            instance = new Event_Service();
        }
        return instance;
    }

    public boolean addEvent(event t) {
        String url = Statics.BASE_URL + "/event/" + t.getNom() + "/" + t.getDate() + "/" + t.getDescription() + "/" + t.getPrix()
                + "/" + t.getAdresse() + "/" + t.getImage() + "/" + t.getNbre_place(); //création de l'URL
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

    public ArrayList<event> parseTasks(String jsonText) {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient
            l'utilité de new CharArrayReader(json.toCharArray())

            La méthode parse json retourne une MAP<String,Object> ou String est
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

              /* Ici on récupère l'objet contenant notre liste dans une liste
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.

            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.

            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                event t = new event();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setNom(obj.get("Name").toString());
                t.setDate((LocalDate) obj.get("Event date"));
                t.setDescription(obj.get("description").toString());
                t.setPrix(Integer.parseInt(obj.get("Price").toString()));
                t.setAdresse(obj.get("adress").toString());
                t.setImage(obj.get("image").toString());
                t.setNbre_place(Integer.parseInt(obj.get("places left").toString()));
                events.add(t);
            }


        } catch (IOException ex) {

        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web

        */
        return events;
    }

    public ArrayList<event> getAllEvents() {
        String url = Statics.BASE_URL + "/event/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
}
