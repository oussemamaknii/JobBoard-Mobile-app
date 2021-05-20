package Mobile_App.Utils;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer.Orientation;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.ui.Form;

import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import Mobile_App.Service.show;

public class CompaniesChart extends AbstractDemoChart {
    @Override
    public String getName() {
        return "Sales horizontal bar chart";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    @Override
    public String getDesc() {
        return "The monthly sales for the last 2 years (horizontal bar chart)";
    }

    @Override
    public Component getChartModelEditor() {
        return null;
    }

    @Override
    public String getChartTitle() {
        return "";
    }

    @Override
    public Component execute() {
        String[] titles = new String[]{"Company per month", "Offer per month"};


        List<double[]> values = new ArrayList<>();
            values.add(new double[]{14230,  22030, 21200, 19500, 15500,
                    12600, 14000});
        values.add(new double[]{5230, 7300, 9240, 10540, 7900,  9500, 10500,
                11600, 13500});
        int[] colors = new int[]{ColorUtil.CYAN, ColorUtil.BLUE};
        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
        renderer.setOrientation(Orientation.HORIZONTAL);
        setChartSettings(renderer, "Nombre des offre par mois", "Month", "Nombre d'Offre ", 0.5,
                12.5, 0, 24000, ColorUtil.BLUE, ColorUtil.MAGENTA);
        renderer.setXLabels(1);
        renderer.setYLabels(10);
        renderer.addXTextLabel(1, "Jan");
        renderer.addXTextLabel(2, "Feb");
        renderer.addXTextLabel(3, "Mar");
        renderer.addXTextLabel(4, "Apr");
        renderer.addXTextLabel(5, "May");
        renderer.addXTextLabel(6, "Jui");
        renderer.addXTextLabel(7, "Jul");
        renderer.addXTextLabel(8, "Aug");
        renderer.addXTextLabel(9, "Sep");
        renderer.addXTextLabel(10, "Oct");
        renderer.addXTextLabel(11, "Nov");
        renderer.addXTextLabel(12, "Dec");
        initRendererer(renderer);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
            seriesRenderer.setDisplayChartValues(false);
        }

        BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
                Type.DEFAULT);
        return newChart(chart);

    }


}
