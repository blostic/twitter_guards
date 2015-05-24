package ui.timeline.results;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class UserContentmentChart extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public UserContentmentChart() {
		Chart chart = createChart();
		addComponent(chart);
	}
	
    protected Component getChart() {
        Component ret = createChart();
        ret.setWidth("100%");
        ret.setHeight("650px");
        return ret;
    }

    public static Chart createChart() {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Context of collected tweets");


        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setCursor(Cursor.POINTER);
        Labels dataLabels = new Labels(true);
        dataLabels
                .setFormatter("''+ this.point.name +': '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);
        conf.setPlotOptions(plotOptions);

        DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Super positive", 1.5));
        
        DataSeriesItem distinct = new DataSeriesItem("Positive", 3.5);
        distinct.setSliced(true);
        distinct.setSelected(true);
        series.add(distinct);
        conf.setSeries(series);

        series.add(new DataSeriesItem("Neutral", 2.0));
        series.add(new DataSeriesItem("Negative", 1.5));
        series.add(new DataSeriesItem("Super negative", 0.5));
        series.add(new DataSeriesItem("Unrecognized", 1.0));

        chart.drawChart(conf);

        return chart;
    }

}
