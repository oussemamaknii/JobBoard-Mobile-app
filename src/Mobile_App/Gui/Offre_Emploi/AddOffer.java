package Mobile_App.Gui.Offre_Emploi;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import Mobile_App.Entities.Category;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.maps.Coord;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ext.filechooser.FileChooser;

import java.util.ArrayList;
import java.util.Date;

public class AddOffer extends SideMenu {

    private static final String HTML_API_KEY = "AIzaSyAM6hBJIe9C4K0UcegqEjf6O0psKXNGcQU";
    private static final String apiKey = "AIzaSyDeLKairWJQj4083je1h-J6J23aggAgBo4";

    public AddOffer(Form previous, Offre_Emploi o, Resources res) {

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);
        setTitle("Add a new Offer");
        setLayout(BoxLayout.y());
        if (o == null) {
            TextField tfTitle = new TextField("", "Offer Title");
            TextField tfPost = new TextField("", "Post");
            TextField tfdescription = new TextField("", "Description");
            //TextField tflocation = new TextField("", "Location");

            final DefaultListModel<String> options = new DefaultListModel<>();
            AutoCompleteTextField tflocation = new AutoCompleteTextField(options) {
                @Override
                protected boolean filter(String text) {
                    if(text.length() == 0) {
                        return false;
                    }
                    String[] l = searchLocations(text);
                    if(l == null || l.length == 0) {
                        return false;
                    }

                    options.removeAll();
                    for(String s : l) {
                        options.addItem(s);
                    }
                    return true;
                }
            };
            tflocation.setMinimumElementsShownInPopup(5);


            Container map = new Container();
            final MapContainer cnt = new MapContainer(HTML_API_KEY);

            Button btnMoveCamera = new Button("Move Camera");
            btnMoveCamera.addActionListener(e->{
                cnt.setCameraPosition(new Coord(-33.867, 151.206));
            });
            Style s = new Style();
            s.setFgColor(0xff0000);
            s.setBgTransparency(0);
            FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));

            Button btnAddMarker = new Button("Add Marker");
            btnAddMarker.addActionListener(e->{

                cnt.setCameraPosition(new Coord(41.889, -87.622));
                cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        cnt.getCameraPosition(),
                        "Hi marker",
                        "Optional long description",
                        evt -> {
                            ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                        }
                );

            });

            Button btnAddPath = new Button("Add Path");
            btnAddPath.addActionListener(e->{

                cnt.addPath(
                        cnt.getCameraPosition(),
                        new Coord(-33.866, 151.195), // Sydney
                        new Coord(-18.142, 178.431),  // Fiji
                        new Coord(21.291, -157.821),  // Hawaii
                        new Coord(37.423, -122.091)  // Mountain View
                );
            });

            Button btnClearAll = new Button("Clear All");
            btnClearAll.addActionListener(e->{
                cnt.clearMapLayers();
            });

            cnt.addTapListener(e->{
                TextField enterName = new TextField();
                Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
                InteractionDialog dlg = new InteractionDialog("Add Marker");
                dlg.getContentPane().add(wrapper);
                enterName.setDoneListener(e2->{
                    String txt = enterName.getText();
                    cnt.addMarker(
                            EncodedImage.createFromImage(markerImg, false),
                            cnt.getCoordAtPosition(e.getX(), e.getY()),
                            enterName.getText(),
                            "",
                            e3->{
                                ToastBar.showMessage("You clicked "+txt, FontImage.MATERIAL_PLACE);
                            }
                    );
                    dlg.dispose();
                });
                dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
                enterName.startEditingAsync();
            });

            Container root = LayeredLayout.encloseIn(
                    BorderLayout.center(cnt),
                    BorderLayout.south(
                            FlowLayout.encloseBottom(btnMoveCamera, btnAddMarker, btnAddPath, btnClearAll)
                    )
            );
            map.add( root);
            map.setHeight(1);

            TextField tffile = new TextField("", "File");
            tffile.setEditable(false);
            Button fc = new Button("file chooser");
            TextField tfemail = new TextField("", "Email");
            TextField tfmax = new TextField("", "Max salary");
            TextField tfmin = new TextField("", "Min salary");

            ComboBox<Category> categ = new ComboBox<>();
            ArrayList<Category> list = Offer_Service.getInstance().getcategnames();
            for (Category l : list) {
                categ.addItem(l);
            }
            Picker dateTimePicker = new Picker();
            dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);


            Button btnValider = new Button("Add Offer");

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
                        Offre_Emploi offer = new Offre_Emploi(0, categ.getSelectedItem().getId(), tfTitle.getText(), tfPost.getText(), tfdescription.getText(), tflocation.getText(),
                                tffile.getText(), tfemail.getText(), date, (Date) dateTimePicker.getValue(), Integer.parseInt(tfmax.getText()),
                                Integer.parseInt(tfmin.getText()));
                        if (Offer_Service.getInstance().addOffer(offer)) {
                            Dialog.show("Success", "Added Successfully !", new Command("OK"));
                            Form f1 = new ListViewOffer(null, res);
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
            a.addAll(tfTitle,tfPost,tfdescription,tflocation);
            b.addAll(tffile,fc,tfemail,dateTimePicker,tfmax,tfmin,categ,btnValider);

            addAll(a,map,b);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                    , e -> previous.showBack());
        } else {

            TextField tfTitle = new TextField(o.getTitre(), "Offer Title");
            TextField tfPost = new TextField(o.getPoste(), "Post");
            TextField tfdescription = new TextField(o.getDescription(), "Description");
            TextField tflocation = new TextField(o.getLocation(), "Location");
            TextField tffile = new TextField(o.getFile(), "File");
            tffile.setEditable(false);
            Button fc = new Button("file chooser");
            TextField tfemail = new TextField(o.getEmail(), "Email");
            TextField tfmax = new TextField(String.valueOf(o.getMax_salary()), "Max salary");
            TextField tfmin = new TextField(String.valueOf(o.getMin_salary()), "Min salary");

            ComboBox<Category> categ = new ComboBox<>();
            ArrayList<Category> list = Offer_Service.getInstance().getcategnames();
            for (Category l : list) {
                if (l.getId() == o.getCategory_id())
                    categ.addItem(l);
            }
            for (Category l : list) {
                if (l.getId() != o.getCategory_id())
                    categ.addItem(l);
            }
            Picker dateTimePicker = new Picker();
            dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);


            Button btnValider = new Button("Update Offer");

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
                        Offre_Emploi offer = new Offre_Emploi(o.getId(), categ.getSelectedItem().getId(), tfTitle.getText(), tfPost.getText(), tfdescription.getText(), tflocation.getText(),
                                tffile.getText(), tfemail.getText(), date, (Date) dateTimePicker.getValue(), Integer.parseInt(tfmax.getText()),
                                Integer.parseInt(tfmin.getText()));
                        if (Offer_Service.getInstance().modOffer(offer)) {
                            Dialog.show("Success", "Updated Successfully !", new Command("OK"));
                            Form f2 = new ListViewOffer(null, res);
                            f2.show();
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            });

            addAll(tfTitle, tfPost, tfdescription, tflocation, tffile, fc, tfemail, dateTimePicker, tfmax, tfmin, categ, btnValider);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                    , e -> previous.showBack());
        }

    }
    String[] searchLocations(String text) {
        try {
            if(text.length() > 0) {
                ConnectionRequest r = new ConnectionRequest();
                r.setPost(false);
                r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
                r.addArgument("key", apiKey);
                r.addArgument("input", text);
                NetworkManager.getInstance().addToQueueAndWait(r);
                Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
                String[] res = Result.fromContent(result).getAsStringArray("//description");
                return res;
            }
        } catch(Exception err) {
            Log.e(err);
        }
        return null;
    }
}
