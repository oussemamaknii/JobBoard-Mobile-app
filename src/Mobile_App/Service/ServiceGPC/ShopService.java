package Mobile_App.Service.ServiceGPC;

import Mobile_App.Entities.GestionProduit_Commande.Produit;
import Mobile_App.Utils.DataSource;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopService {

    private ConnectionRequest request;

    public static ShopService instance=null;
    private boolean responseResult;

    public ArrayList<Produit> products;

    public ShopService() {
        request = new ConnectionRequest();
    }

    public static ShopService getInstance() {
        if (instance == null) {
            instance = new ShopService();
        }
        return instance;
    }

    public ArrayList<Produit> parseProduits(String jsonText){
        try {
            products = new ArrayList<>();
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                Produit book = new Produit();
                int id = (int) Float.parseFloat(obj.get("id").toString());
                String name = obj.get("name").toString();
                String ref = obj.get("ref").toString();
                String description = obj.get("description").toString();
                String image = obj.get("image").toString();
                int quantity = (int) Float.parseFloat(obj.get("quantity").toString());
                double price = (double) Float.parseFloat(obj.get("price").toString());

                products.add(new Produit(id,quantity,name,ref,description,image,price));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return products;
    }

    public ArrayList<Produit> getAllProducts(){
        String url = Statics.BASE_URL_MINTOUA + "/list_products";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProduits(new String(request.getResponseData()));
               // System.out.println("Books: "+products);
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return products;
    }

}
