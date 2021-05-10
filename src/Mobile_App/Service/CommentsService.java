package Mobile_App.Service;

import Mobile_App.Entities.Comment;
import Mobile_App.Entities.Events;
import Mobile_App.Utils.Statics;
import com.codename1.io.*;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommentsService {

    public ArrayList<Comment> Events;

    public static CommentsService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private CommentsService() {
        req = new ConnectionRequest();
    }

    public static CommentsService getInstance() {
        if (instance == null) {
            instance = new CommentsService();
        }
        return instance;
    }

    public boolean addOffer(Comment t) {
        String url = Statics.BASE_URL + "/addCommentjson?name=" + t.getName() + "&date=" + t.getCreated_at() +
                "&email=" + t.getEmail() + "&phone=" + t.getPhone()
                + "&id=" + t.getIdEvent() + "&message=" + t.getMessage();
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

    public ArrayList<Comment> parseEvents(String jsonText) {
        try {
            Events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Comment t = new Comment();
                t.setMessage(obj.get("message").toString());
                Date date1 = Date.from(Instant.parse(obj.get("createdAt").toString()));
                t.setName(obj.get("name").toString());
                t.setCreated_at(date1);
                Events.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Events;
    }

    public ArrayList<Comment> getAllComments(int id) {
        String url = Statics.BASE_URL + "/listcomms?id="+id;
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
}
