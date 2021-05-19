package Mobile_App.Gui.Formation;



import Mobile_App.Entities.Category;
import Mobile_App.Service.CategoryService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;



public class  AddCatForm extends Form {

    public  AddCatForm(Form previous) {

        setTitle("Add a new Category");
        setLayout(BoxLayout.y());

        TextField lb1 = new TextField("", "titre");


        TextField lb2 = new TextField("", "description");



        Button btnValidercat = new Button("Add category");

        btnValidercat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((lb1.getText().length()==0)||(lb2.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Category t = new Category(lb1.getText(), lb2.getText());
                        if( CategoryService.getInstance().Addcat(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });

        addAll(lb1,lb2,btnValidercat);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack());
    }
}