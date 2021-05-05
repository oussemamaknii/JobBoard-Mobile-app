/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobile_App.Gui;

import Mobile_App.Service.Event_Service;
import Mobile_App.Service.Offer_Service;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

/**
 *
 * @author bhk
 */
public class ListEventForm extends Form{

    public ListEventForm(Form previous) {
        setTitle("List events");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(Event_Service.getInstance().getAllEvents().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
