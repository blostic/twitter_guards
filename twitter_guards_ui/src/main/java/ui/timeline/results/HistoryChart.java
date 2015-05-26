package ui.timeline.results;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import persistance.campaign.entity.Campaign;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.addon.charts.model.TickmarkPlacement;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class HistoryChart extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public HistoryChart(Campaign campaign) {
		addComponent(getChart());
	}
	
		public String getDateInCorrectFormat(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}
	
	protected Component getChart() {
		  Chart chart = new Chart(ChartType.AREA);

	        Configuration conf = chart.getConfiguration();

	        conf.setTitle(new Title("Observed sentences occurrence in Tweets by context"));

	        XAxis xAxis = new XAxis();
	        xAxis.setTickmarkPlacement(TickmarkPlacement.ON);
	        
	        ArrayList<String> list = new ArrayList<String>();
	        for (int i = 7; i > 0; i--) {				
	        	Calendar c = Calendar.getInstance();
	        	c.setTime(new Date());
	        	c.add(Calendar.DATE, - i);
	        	list.add(getDateInCorrectFormat(c.getTime()));
			}
	        xAxis.setCategories(list.toArray(new String[list.size()]));
	        conf.addxAxis(xAxis);

	        YAxis yAxis = new YAxis();
	        yAxis.setTitle(new Title("Percent"));
	        conf.addyAxis(yAxis);

	        Tooltip tooltip = new Tooltip();
	        tooltip.setFormatter("this.series.name + ': ' +this.x +': '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+ Highcharts.numberFormat(this.y, 0, ',') +' tweets)'");
	        conf.setTooltip(tooltip);

	        PlotOptionsArea plotOptions = new PlotOptionsArea();
	        plotOptions.setStacking(Stacking.PERCENT);
	        plotOptions.setLineWidth(1);
	        Marker marker = new Marker();
	        plotOptions.setMarker(marker);
	        conf.setPlotOptions(plotOptions);

	        conf.addSeries(new ListSeries("Super Positive", 502, 235, 309, 247, 402, 1634, 5268));
	        conf.addSeries(new ListSeries("Positive", 106, 107, 111, 133, 221, 767, 1766));
	        conf.addSeries(new ListSeries("Neutral", 163, 203, 276, 408, 547, 729, 628));
	        conf.addSeries(new ListSeries("Negative", 18, 31, 54, 156, 339, 818, 1201));
	        conf.addSeries(new ListSeries("Super negative", 21, 23, 22, 63, 33, 43, 76));

	        chart.drawChart(conf);

	        return chart;
    }
}