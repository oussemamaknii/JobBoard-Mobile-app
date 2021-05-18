package Mobile_App.Gui.event;

import Mobile_App.Entities.Events;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Service.EventService;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class AddEvent extends SideMenu {

    private static final String HTML_API_KEY = "AIzaSyAM6hBJIe9C4K0UcegqEjf6O0psKXNGcQU";
    private static final String apiKey = "AIzaSyBr4DKSW58r6tZXyZDYnPTBc7IRAQS2R1U";


    private File selectedFile2;
    private File getImageFile()
    {
        return this.selectedFile2=selectedFile2;
    }

    private void setImageFile(File file2)
    {
        this.selectedFile2=file2;
    }

    public AddEvent(Form previous, Events o, Resources res) {

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);
        setTitle("Add a new Event");
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
                        Date date = new Date(System.currentTimeMillis());

                        setImageFile(new File(tffile.getText()));
                        File f=new File(getImageFile().getAbsolutePath());
                        Events offer = new Events(0, tfTitle.getText(), dateTimePicker.getDate(), tfdescription.getText(), Integer.parseInt(tflocation.getText()),
                                tfPost.getText(), "event2.jpg", Integer.parseInt(nbr.getText()));
                        Files.move(Paths.get(tffile.getText().substring(7)),
                                Paths.get("C:\\Users\\souso\\Desktop\\Mobile App\\res\\events\\"+f.getName()));

                        if (EventService.getInstance().addOffer(offer)) {
                            Dialog.show("Success", "Added Successfully !", new Command("OK"));
                            Form f1 = new ListViewEvent(null, res);
                            f1.show();
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch ( IOException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            });
            Container a = new Container(BoxLayout.y());
            Container b = new Container(BoxLayout.y());
            a.addAll(tfTitle, tfPost, tfdescription, tflocation,nbr);
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

                        setImageFile(new File(tffile.getText()));
                        File f=new File(getImageFile().getAbsolutePath());
                        Events offer = new Events(o.getId(), tfTitle.getText(), dateTimePicker.getDate(), tfdescription.getText(), Integer.parseInt(tflocation.getText()),
                                tfPost.getText(), "event2.jpg", Integer.parseInt(nbr.getText()));
                        Files.move(Paths.get(tffile.getText().substring(7)),
                                Paths.get("C:\\Users\\souso\\Desktop\\Mobile App\\res\\events\\"+f.getName()));
                        Files.move(Paths.get(tffile.getText().substring(7)),
                                Paths.get("C:\\wamp64\\www\\jobBoard-web\\public\\uploads\\Image\\"+f.getName()));

                        if (EventService.getInstance().modOffer(offer)) {
                            Dialog.show("Success", "UPDATED Successfully !", new Command("OK"));
                            Form f1 = new ListViewEvent(null, res);
                            f1.show();
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException | IOException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            });
            Container a = new Container(BoxLayout.y());
            Container b = new Container(BoxLayout.y());
            a.addAll(tfTitle, tfPost, tfdescription, tflocation,nbr);
            b.addAll(tffile, fc, dateTimePicker, btnValider);

            addAll(a, b);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                    , e -> previous.showBack());
        }

    }
}
