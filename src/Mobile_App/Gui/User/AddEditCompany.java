package Mobile_App.Gui.User;

import Mobile_App.Entities.company;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Service.addEditCompanyService;
import Mobile_App.Utils.Session;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

public class AddEditCompany extends SideMenu {
    public AddEditCompany(Form previous, company comp, Resources res) {
        setTitle("Update your Company");
        setLayout(BoxLayout.y());


        System.out.println("hi");
        //  resume.getUser_id() != Session.ConnectedUser.getId()
        TextField tfCompanyName = new TextField("", "Ex: Jobhub");
        //   assert comp != null;
        TextField tfContactEmail = new TextField("", "Ex: jobhub@business.tn");
        TextField tfWebsite = new TextField("", "Ex: www.jobhub.tn");
        Picker date = new Picker();
        TextField tfCategory = new TextField("", "Ex: Software Developpment company");
        TextField tfDescription = new TextField("", "Ex: Company description ");
        TextField tfCompanyAdress = new TextField("", "Ex: Tunisia, Gafsa");
        TextField tfContactPhone = new TextField("", "Ex: +21620202020");
        TextField tfFacebookLink = new TextField("", "Ex: www.facebook.com/jobhub");
        Button companyButton = new Button("Update your Company");
        companyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    company comp = new company(0, Session.ConnectedUser.getId(), 1, Integer.parseInt(tfContactPhone.getText()), tfWebsite.getText(), tfCategory.getText(),
                            tfDescription.getText(), tfCompanyName.getText(), tfContactEmail.getText(), tfCompanyAdress.getText(), tfFacebookLink.getText(), date.getDate());
                    if (addEditCompanyService.getInstance().addEditCompany(comp)) {
                        Dialog.show("Success", "Added Successfully !", new Command("OK"));
                    } else
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }
            }
        });
        addAll(tfCompanyName,tfCompanyAdress,tfCategory,tfDescription,tfWebsite,tfContactEmail,tfFacebookLink,tfContactPhone,companyButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> previous.showBack());

    }
}


