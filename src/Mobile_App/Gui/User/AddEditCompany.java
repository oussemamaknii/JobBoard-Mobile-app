package Mobile_App.Gui.User;

import Mobile_App.Entities.company;
import Mobile_App.Gui.SideMenu;
import Mobile_App.Service.addEditCompanyService;
import Mobile_App.Service.addEditResumeService;
import Mobile_App.Utils.Session;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import java.util.Date;

public class AddEditCompany extends SideMenu {
    public AddEditCompany(Form previous, company comp) {
        setTitle("Update Company");
        setLayout(BoxLayout.y());
        if (comp == null) {
            //  resume.getUser_id() != Session.ConnectedUser.getId()
            TextField tfCompanyName = new TextField("", "Ex: Jobhub");
         //   assert comp != null;
            TextField tfContactEmail = new TextField("", "Ex: jobhub@business.tn");
            TextField tfWebsite = new TextField("", "Ex: www.jobhub.tn");
            Picker date = new Picker();
            TextField tfCategory = new TextField("", "Ex: Software Developpment company");
            TextField tfDescription = new TextField("", "Ex: ");
            TextField tfCompanyAdress = new TextField("", "Ex: Tunisia, Gafsa");
            TextField tfContactPhone = new TextField("", "Ex: +21620202020");
            TextField tfFacebookLink = new TextField("", "Ex: www.facebook.com/jobhub");




            Button companyButton = new Button("Add Company");
            companyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                        try {
                        company comp = new company(0,Session.ConnectedUser.getId(),1,Integer.parseInt(tfContactPhone.getText()),tfWebsite.getText(),tfCategory.getText(),
                                tfDescription.getText(),tfCompanyName.getText(),tfContactEmail.getText(),tfCompanyAdress.getText(),tfFacebookLink.getText(),date.getDate());
                        if (addEditCompanyService.getInstance().addEditCompany(comp)) {
                            Dialog.show("Success", "Added Successfully !", new Command("OK"));
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            });
            addAll(tfCompanyName,tfCompanyAdress,tfCategory,tfContactPhone,tfFacebookLink,date,tfWebsite,tfDescription,date,companyButton);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                    , e -> previous.showBack());
        } else {
            //  resume.getUser_id() != Session.ConnectedUser.getId()
            TextField tfCompanyName = new TextField("", comp.getCompanyName());
            assert comp != null;
            TextField tfContactEmail = new TextField(comp.getContactEmail(), "Ex: jobhub@business.tn");
            TextField tfWebsite = new TextField(comp.getWebsite(), "Ex: www.jobhub.tn");
            Picker date = new Picker();
            TextField tfCategory = new TextField(comp.getCategory(), "Ex: Software Developpment company");
            TextField tfDescription = new TextField(comp.getDescription(), "Ex: ");
            TextField tfCompanyAdress = new TextField(comp.getCompanyAddress(), "Ex: Tunisia, Gafsa");
            TextField tfContactPhone = new TextField(String.valueOf(comp.getContactPhone()), "Ex: +21620202020");
            TextField tfFacebookLink = new TextField(comp.getFacebookLink(), "Ex: www.facebook.com/jobhub");

            Button btnValider = new Button("Update Company");

            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        company comp = new company(0,Session.ConnectedUser.getId(),1,Integer.parseInt(tfContactPhone.getText()),tfWebsite.getText(),tfCategory.getText(),
                                tfDescription.getText(),tfCompanyName.getText(),tfContactEmail.getText(),tfCompanyAdress.getText(),tfFacebookLink.getText(),date.getDate());
                        if (addEditCompanyService.getInstance().addEditCompany(comp)) {
                            Dialog.show("Success", "Updated Successfully !", new Command("OK"));
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            });

            addAll(tfCompanyName,tfCompanyAdress,tfCategory,tfContactPhone,tfFacebookLink,date,tfWebsite,tfDescription,date,btnValider);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                    , e -> previous.showBack());
        }
    }
}


