package Mobile_App.Gui.GestionProduit_Commande;

import Mobile_App.Gui.BaseForm;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InboxForm extends BaseForm {

    public InboxForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public InboxForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        List<Button> list_button = new ArrayList<Button>();
        List<Form> list_form = new ArrayList<Form>();
        ChartDemosForm s= (ChartDemosForm) Storage.getInstance().readObject("Statistiques");
        list_button.add(new Button("Statistiques"));
        list_form.add(s.showChart(s.options[0]));
        installSidemenu(resourceObjectInstance, list_button, list_form);

        TextField searchField = new TextField("", ""); // <1>
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        Map<String, String> notifications = (Map<String, String>) Storage.getInstance().readObject("Notifications");

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label(Integer.toString(notifications.size()), "InboxNumber"),
                        searchField
                )
        );
        Style st = UIManager.getInstance().getComponentStyle("Title");
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, st);
        Container contenant = BoxLayout.encloseY();
        display_notification("", notifications, resourceObjectInstance, contenant);
        searchField.addDataChangeListener((i1, i2) -> {
            String t = searchField.getText();
            if (t.length() < 1) {
                contenant.removeAll();
                display_notification("", notifications, resourceObjectInstance, contenant);
                contenant.refreshTheme();
            } else {
                contenant.removeAll();
                String search_word = t;
                display_notification(t, notifications, resourceObjectInstance, contenant);
                contenant.refreshTheme();

            }
            getContentPane().animateLayout(250);
        });
        getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
            searchField.startEditingAsync();
        });
        this.add(contenant);
    }

    public void display_notification(String search_word, Map<String, String> notifications, com.codename1.ui.util.Resources resource, Container co) {
        if (search_word.equals("")) {
            for (Map.Entry<String, String> entry : notifications.entrySet()) {
                Container C = BoxLayout.encloseX();
                Container C1 = BoxLayout.encloseY();
                Label titre = new Label(entry.getKey().toString());
                Style titre_style = titre.getAllStyles();
                titre_style.setFgColor(0xFE7B37);
                Label body = new Label(entry.getValue().toString());
                body.setUIID("RedLabel");
                C1.addAll(titre, body);
                Label icon_notification = new Label(resource.getImage("Group 282.png"));
                Style icon_notification_style = icon_notification.getAllStyles();
                icon_notification_style.setMargin(17, 0, 0, 0);
                Container C2 = BoxLayout.encloseY();
                C2.addAll(icon_notification);
                Style C2_style = C2.getAllStyles();
                C2_style.setMargin(0, 0, 0, 10);
                Container C3 = BoxLayout.encloseY();
                Label now = new Label(new Date().toString());
                now.setUIID("SmallFontLabel");
                C3.add(now);
                C.addAll(C2, C1, C3);
                Container separator = BoxLayout.encloseY(new Label("----------------------------------------------------------------------"));
                co.addAll(C, separator);
            }
        } else {
            for (Map.Entry<String, String> entry : notifications.entrySet()) {
                if (entry.getKey().contains(search_word) || entry.getValue().contains(search_word)) {
                    Container C = BoxLayout.encloseX();
                    Container C1 = BoxLayout.encloseY();
                    Label titre = new Label(entry.getKey().toString());
                    Style titre_style = titre.getAllStyles();
                    titre_style.setFgColor(0xFE7B37);
                    Label body = new Label(entry.getValue().toString());
                    body.setUIID("RedLabel");
                    C1.addAll(titre, body);
                    Label icon_notification = new Label(resource.getImage("Group 282.png"));
                    Style icon_notification_style = icon_notification.getAllStyles();
                    icon_notification_style.setMargin(17, 0, 0, 0);
                    Container C2 = BoxLayout.encloseY();
                    C2.addAll(icon_notification);
                    Style C2_style = C2.getAllStyles();
                    C2_style.setMargin(0, 0, 0, 10);
                    Container C3 = BoxLayout.encloseY();
                    Label now = new Label(new Date().toString());
                    now.setUIID("SmallFontLabel");
                    C3.add(now);
                    C.addAll(C2, C1, C3);
                    Container separator = BoxLayout.encloseY(new Label("----------------------------------------------------------------------"));
                    co.addAll(C, separator);
                }
            }
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
        setInlineStylesTheme(resourceObjectInstance);
        setInlineAllStyles("bgColor:fbfcfe;");
        setTitle("InBoxForm");
        setName("InBoxForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
