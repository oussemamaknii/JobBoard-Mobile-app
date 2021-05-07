package Mobile_App.Gui;

import Mobile_App.Entities.Category;
import Mobile_App.Entities.Offre_Emploi;
import Mobile_App.Service.Offer_Service;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import java.time.LocalDate;

public class AddOffer extends Form {
    public AddOffer(Form previous) {
        setTitle("Add a new Offer");
        setLayout(BoxLayout.y());
        TextField tfTitle = new TextField("", "Offer Title");
        TextField tfPost = new TextField("", "Post");
        TextField tfdescription = new TextField("", "Description");
        TextField tflocation = new TextField("", "Location");
        TextField tffile = new TextField("", "File");
        TextField tfemail = new TextField("", "Email");
        TextField tfmax = new TextField("", "Max salary");
        TextField tfmin = new TextField("", "Min salary");
        ComboBox<Category> categ = new ComboBox<>();
        Picker dateTimePicker = new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);

        Button btnValider = new Button("Add Offer");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Offre_Emploi offer = new Offre_Emploi(1, tfTitle.getText(), tfPost.getText(), tfdescription.getText(), tflocation.getText(),
                            tffile.getText(), tfemail.getText(), LocalDate.now(), (LocalDate) dateTimePicker.getValue(), Integer.parseInt(tfmax.getText()),
                            Integer.parseInt(tfmin.getText()));
                    if (Offer_Service.getInstance().addOffer(offer))
                        Dialog.show("Success", "Connection accepted", new Command("OK"));
                    else
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }
            }
        });

        addAll(tfTitle, tfPost, tfdescription, tflocation, tffile, tfemail, dateTimePicker, tfmax, tfmin, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> previous.showBack());

    }
}
