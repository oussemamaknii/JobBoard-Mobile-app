package Mobile_App.Gui.Offre_Emploi;

import Mobile_App.Gui.SideMenu;
import Mobile_App.Service.Offer_Service;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

import java.awt.*;
import java.util.ArrayList;

public class Stat extends SideMenu {
    public Stat(Form previous, Resources res) {
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        setupSideMenu(res);
        this.setTitle("Offer Statistics");
        this.setLayout(new BorderLayout());
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> previous.showBack());
    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);

        ArrayList<ArrayList<String>> arr = Offer_Service.getInstance().getdata();
        for (int i = 0; i < arr.size(); i++) {
            String tit = arr.get(i).get(1);
            series.add("Category " + tit, values[i]);
        }

        return series;
    }

    public Form createPieChartForm() {
        // Generate the values
        ArrayList<ArrayList<String>> arr = Offer_Service.getInstance().getdata();
        double[] values = new double[arr.size()];
        int[] colors = new int[arr.size()];
        String[] titres = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            double val = Double.parseDouble(arr.get(i).get(0));
            values[i] = val;
            Color hex = new Color(Integer.parseInt(arr.get(i).get(2),16));
            colors[i] = hex.getRGB();
            String tit = arr.get(i).get(1);
            titres[i] = tit;
        }

        // Set up the renderer
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setChartTitleTextSize(50);
        renderer.setLabelsTextSize(60);
        renderer.setLegendTextFont(Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, 12));
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Offer Statistics", values), renderer);


        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        this.add(BorderLayout.CENTER, c);
        return this;

    }
}
