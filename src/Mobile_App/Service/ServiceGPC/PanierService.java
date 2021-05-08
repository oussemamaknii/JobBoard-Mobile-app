package Mobile_App.Service.ServiceGPC;

import Mobile_App.Entities.GestionProduit_Commande.Commande;
import Mobile_App.Entities.GestionProduit_Commande.Panier;
import Mobile_App.Utils.DataSource;
import Mobile_App.Utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

public class PanierService {

    private ConnectionRequest request;

    private boolean responseResult;
    public PanierService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addPanier(Panier panier) {
        String url = Statics.BASE_URL_MINTOUA + "/addCartMobile/" + panier.getQuantity() + "/" + panier.getIdOrder() + "/" + panier.getIdProduct();
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
}
