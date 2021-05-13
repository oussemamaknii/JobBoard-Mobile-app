package Mobile_App.Service.ServiceGPC;

import Mobile_App.Entities.GestionProduit_Commande.Commande;
import Mobile_App.Entities.GestionProduit_Commande.Produit;
import Mobile_App.Utils.DataSource;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandeService {

    private ConnectionRequest request;

    public ArrayList<Commande> lastOrder;
    private boolean responseResult;

    public CommandeService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addCommande(Commande commande) {
        String url = Statics.BASE_URL_MINTOUA + "/addOrderMobile/" + commande.getTotal_payment() + "/" + commande.isState() + "/"+ commande.getDate() + "/" + commande.getId_user();
        System.out.println(url);
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // verifie le statu de la reponse Code HTTP 200 OK
                request.removeResponseListener(this);
                System.out.println(new String("Données:" + request.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public ArrayList<Commande> parseCommandes(String jsonText){
        try {
            lastOrder = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                Commande lastCommande = new Commande();
                int id = (int) Float.parseFloat(obj.get("id").toString());
                int idUser = (int) Float.parseFloat(obj.get("idUser").toString());
                int totalPayment = (int) Float.parseFloat(obj.get("totalPayment").toString());
                String dateOrder = obj.get("date").toString();

                boolean etat = (boolean) Boolean.parseBoolean(obj.get("state").toString());

                lastOrder.add(new Commande(id,idUser,dateOrder,totalPayment,etat));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return lastOrder;
    }

    public ArrayList<Commande> getLastOrder(){
        String url = Statics.BASE_URL_MINTOUA + "/getLastOrderMobile";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lastOrder = parseCommandes(new String(request.getResponseData()));
                // System.out.println("Books: "+products);
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return lastOrder;
    }
}
