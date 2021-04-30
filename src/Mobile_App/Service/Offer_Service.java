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
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web

        */
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
