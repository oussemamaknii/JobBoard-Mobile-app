package Mobile_App.Gui.event;

import Mobile_App.Entities.Category;
import Mobile_App.Entities.Events;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Service.EventService;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.maps.Coord;
import com.codename1.processing.Result;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class AddEvent extends SideMenu {

    private static final String HTML_API_KEY = "AIzaSyAM6hBJIe9C4K0UcegqEjf6O0psKXNGcQU";
    private static final String apiKey = "AIzaSyBr4DKSW58r6tZXyZDYnPTBc7IRAQS2R1U";

    public AddEvent(Form previous, Events o, Resources res) {

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);
        setTitle("Add a new Offer");
        setLayout(BoxLayout.y());
        if (o == null) {
            TextField tfTitle = new TextField("", "Event Name");
            TextField tfPost = new TextField("", "Address");
            TextField tfdescription = new TextField("", "Description");
            TextField tflocation = new TextField("", "Price");
            TextField nbr = new TextField("", "How many places");
            TextField tffile = new TextField("", "Image");
            tffile.setEditable(false);
            Button fc = new Button("file chooser");
            Picker dateTimePicker = new Picker();
            dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);

            Button btnValider = new Button("Add Event");

            fc.addActionListener(evt -> {
                ActionListener callback = e -> {
                    if (e != null && e.getSource() != null) {
                        String filePath = (String) e.getSource();
                        tffile.setText(filePath);
                    }
                };
                if (FileChooser.isAvailable()) {
                    FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);
                } else {
                    Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
                }
            });

            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date(System.currentTimeMillis());
                        Date date1 = new Date((String) dateTimePicker.getValue());
                        Events offer = new Events(0, tfTitle.getText(), date1, tfdescription.getText(), Integer.parseInt(tflocation.getText()),
                                tfPost.getText(), tffile.getText(), Integer.parseInt(nbr.getText()));
                        if (EventService.getInstance().addOffer(offer)) {
                            Dialog.show("Success", "Added Successfully !", new Command("OK"));
                            Form f1 = new ListViewEvent(null, res);
                            f1.show();
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            });
            Container a = new Container(BoxLayout.y());
            Container b = new Container(BoxLayout.y());
            a.addAll(tfTitle, tfPost, tfdescription, tflocation);
            b.addAll(tffile, fc, dateTimePicker, btnValider);

            addAll(a, b);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                    , e -> previous.showBack());
        } else {
            TextField tfTitle = new TextField(o.getNom(), "Event Name");
            TextField tfPost = new TextField(o.getAdresse(), "Address");
            TextField tfdescription = new TextField(o.getDescription(), "Description");
            TextField tflocation = new TextField(String.valueOf(o.getPrix()), "Price");
            TextField nbr = new TextField(String.valueOf(o.getNbre_place()), "How many places");
            TextField tffile = new TextField(o.getFile(), "Image");
            tffile.setEditable(false);
            Button fc = new Button("file chooser");
            Picker dateTimePicker = new Picker();
            dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
            dateTimePicker.setDate(o.getDate());

            Button btnValider = new Button("Update Event");

            fc.addActionListener(evt -> {
                ActionListener callback = e -> {
                    if (e != null && e.getSource() != null) {
                        String filePath = (String) e.getSource();
                        tffile.setText(filePath);
                    }
                };
                if (FileChooser.isAvailable()) {
                    FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);
                } else {
                    Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
                }
            });

            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        Date date = new Date(System.currentTimeMillis());
                        Events offer = new Events(o.getId(), tfTitle.getText(), dateTimePicker.getDate(), tfdescription.getText(), Integer.parseInt(tflocation.getText()),
                                tfPost.getText(), tffile.getText(), Integer.parseInt(nbr.getText()));
                        if (EventService.getInstance().modOffer(offer)) {
                            Dialog.show("Success", "UPDATED Successfully !", new Command("OK"));
                            Form f1 = new ListViewEvent(null, res);
                            f1.show();
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            });
            Container a = new Container(BoxLayout.y());
            Container b = new Container(BoxLayout.y());
            a.addAll(tfTitle, tfPost, tfdescription, tflocation);
            b.addAll(tffile, fc, dateTimePicker, btnValider);

            addAll(a, b);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                    , e -> previous.showBack());
        }

    }
}
