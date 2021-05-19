package Mobile_App.Gui.Formation;




import Mobile_App.Entities.Category;
import Mobile_App.Service.CategoryService;
import Mobile_App.Service.FormationService;
import Mobile_App.Service.Offer_Service;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;


public class  AddForForm extends Form {

    public  AddForForm(Form previous, Object o, Resources res) {

        setTitle("Add a new Formation");
        setLayout(BoxLayout.y());

        TextField lb1 = new TextField("", "nom");
        TextField lb2 = new TextField("", "formateur");
        TextField lb3 = new TextField("", "description");
        TextField lb4 = new TextField("", "adresse");
        TextField lb5 = new TextField("", "mail");
        TextField lb6 = new TextField("", "tel");
        TextField lb7 = new TextField("", "prix");
/**
        ComboBox<Category> category = new ComboBox<>();
        ArrayList<Category> list = Offer_Service.getInstance().getcategnames();
        for (Category l : list) {
            category.addItem(l);
        }**/
        Picker dateTimePicker = new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);

        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        Picker datePicker2 = new Picker();
        datePicker2.setType(Display.PICKER_TYPE_DATE);
        Button btnValiderfor = new Button("Add formation");
/**
 btnValiderfor.addActionListener(new ActionListener() {


@Override
public void actionPerformed(ActionEvent evt) {
if ((lb1.getText().length()==0)||(lb2.getText().length()==0))
Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
else
{
try {
SimpleDateFormat format =new SimpleDateFormat("yyyy-mm-dd");
Formation t = new Formation(lb1.getText(), lb2.getText(),lb3.getText(),datePicker.getDate(), datePicker2.getDate(),lb4.getText(),String.valueOf(lb5.getText()).toString(), String.valueOf(lb7.getText()).toString());
if( FormationService.getInstance().Addfor(t))
Dialog.show("Success","Connection accepted",new Command("OK"));
else
Dialog.show("ERROR", "Server error", new Command("OK"));
} catch (NumberFormatException e) {
Dialog.show("ERROR", "Status must be a number", new Command("OK"));
}
}
}
});**/

        addAll(lb1,lb2,lb3,datePicker,datePicker2,lb4,lb5,lb6,lb7,btnValiderfor);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack());
    }
}
