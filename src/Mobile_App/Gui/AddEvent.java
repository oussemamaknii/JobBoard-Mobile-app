package Mobile_App.Gui;

import Mobile_App.Entities.event;
import Mobile_App.Service.Event_Service;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import java.time.LocalDate;

public class AddEvent extends Form {
    public AddEvent(Form previous) {
        setTitle("Add a new Event");
        setLayout(BoxLayout.y());

        TextField tfNom = new TextField("", "Event name");
        TextField tfdescription = new TextField("", "Description");
        TextField tfprix = new TextField("", "Price");
        TextField tfadresse = new TextField("", "Adress");
        TextField tfimage = new TextField("", "image");
        TextField tfNbre_place = new TextField("", "Places available");
        Picker dateTimePicker = new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);


        Button btnValider = new Button("Add Event");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    event event = new event(1, tfNom.getText(),(LocalDate) dateTimePicker.getValue(), tfdescription.getText(),
                            Integer.parseInt(tfprix.getText()),
                            tfadresse.getText(),tfimage.getText(), Integer.parseInt(tfNbre_place.getText()));
                    if (Event_Service.getInstance().addEvent(event))
                        Dialog.show("Success", "Connection accepted", new Command("OK"));
                    else
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }
            }
        });

        addAll(tfNom,dateTimePicker, tfdescription, tfprix, tfadresse, tfimage,tfNbre_place, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> previous.showBack());

    }
}
