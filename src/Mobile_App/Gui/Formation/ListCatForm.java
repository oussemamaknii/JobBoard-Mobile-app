package Mobile_App.Gui.Formation;
import Mobile_App.Entities.Category;
import Mobile_App.Service.CategoryService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;

import java.util.List;


public class ListCatForm  extends Form {
    private CategoryService sv;


    public ListCatForm(Form previous) {

        sv = new CategoryService();

        setTitle("Liste de categories");
        this.setLayout(BoxLayout.y());

        List<Category> Category = new CategoryService().getAllCat();
        for (int i = 0; i < Category.size(); i++) {
            add(addcategoryItem(Category.get(i)));
        }

        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            previous.showBack();
        });
    }
    public Container  addcategoryItem(Category book){
        //faire un bloc try catch pour eviter une exception genre une image inexistant ie null
        try {
            //creer les container
            Container holder =new Container(BoxLayout.y());
            Container details = new Container(BoxLayout.x());
            Container titleDuree = new Container(BoxLayout.y());

            //les composants de chaq container en recuperant les infos
            Label lbTitle = new Label("*titre: "+ book.getTitre());
            Label lDescription = new Label("Description: "+ book.getDescriptionc());

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
            titleDuree.addAll(lbTitle,lDescription);
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
