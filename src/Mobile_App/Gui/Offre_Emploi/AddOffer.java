package Mobile_App.Gui.Offre_Emploi;

import Mobile_App.Entities.Category;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ext.filechooser.FileChooser;

import java.util.ArrayList;
import java.util.Date;

public class AddOffer extends Form {
    public AddOffer(Form previous, Offre_Emploi o) {
        setTitle("Add a new Offer");
        setLayout(BoxLayout.y());
        if (o == null) {
            TextField tfTitle = new TextField("", "Offer Title");
            TextField tfPost = new TextField("", "Post");
            TextField tfdescription = new TextField("", "Description");
            TextField tflocation = new TextField("", "Location");
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
                            Form f1 = new ListViewOffer(null);
                            f1.show();
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
                            Form f2 = new ListViewOffer(null);
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
}
