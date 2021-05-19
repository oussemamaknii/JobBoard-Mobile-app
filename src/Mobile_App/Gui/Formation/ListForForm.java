package Mobile_App.Gui.Formation;




import Mobile_App.Entities.Formation;
import Mobile_App.Service.FormationService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;

import java.util.List;

public class ListForForm extends Form {
    private FormationService sv;


    public ListForForm(Form previous) {

        sv = new FormationService();

        setTitle("Liste de categories");
        this.setLayout(BoxLayout.y());

        List<Formation> Formation = new FormationService().getAllFor();
        for (int i = 0; i < Formation.size(); i++) {
            add(addforItem(Formation.get(i)));
        }

        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            previous.showBack();
        });
    }
    public Container addforItem(Formation book){
        //faire un bloc try catch pour eviter une exception genre une image inexistant ie null
        try {
            //creer les container
            Container holder =new Container(BoxLayout.y());
            Container details = new Container(BoxLayout.x());
            Container titleDuree = new Container(BoxLayout.y());

            //les composants de chaq container en recuperant les infos
            Label lbTitle = new Label("*titre: "+ book.getNom());
            Label lb1 = new Label("Formateur: "+ book.getFormateur());
            Label lDescription = new Label("Description: "+ book.getDescription());
            Label lb2 = new Label("Email: "+ book.getMail());
            Label lb3 = new Label("Tel: "+ book.getTel());
            Label lb4 = new Label("Category: "+ book.getCategory_id());

            Label lSupprimer = new Label(" ");
            lSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            lSupprimer.setIcon(suprrimerImage);
            lSupprimer.setTextPosition(RIGHT);


            //

            //click delete icon
            lSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");



                if(dig.show("Suppression","Vous voulez supprimer ce reclamation ?","Annuler","Oui")) {
                    dig.dispose();
                }
                else {
                    dig.dispose();
                }
                //n3ayto l suuprimer men service Reclamation
                //if(CategoryService.getInstance().deletecat(rec.getId())) {
                //     new ListCatForm(res).show();




            });


            //Update icon
            Label lModifier = new Label(" ");
            lModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(lModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);
            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            lModifier.setIcon(mFontImage);
            lModifier.setTextPosition(LEFT);


            lModifier.addPointerPressedListener(l -> {

                //System.out.println("hello update");
                // new ModifierReclamationForm(res,rec).show();
            });

            //cree l'action qui utilise un lambda expression
            //ajouter a chque container les elements respectifs
            titleDuree.addAll(lbTitle,lb1,lDescription,lb2,lb3,lb4);
            details.addAll(titleDuree,lSupprimer,lModifier);
            holder.addAll(details);
            //pour rendre l'action sur tout le container

            //retourner le container holder
            return holder;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return new Container(BoxLayout.x());
    }


}

