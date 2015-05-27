package ui.timeline.results;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.Days;
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
import persistance.tweets.dao.TweetDao;
import persistance.tweets.entity.Emotion;

public class HistoryChart extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private Campaign campaign;
	private Chart chart;
	private Configuration conf;
	private List<Date> dateList;

	public HistoryChart(Campaign campaign) {
		this.campaign = campaign;
		addComponent(getChart());
	}
	
	public String getDateInCorrectFormat(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}
	
	protected Component getChart() {
		  chart = new Chart(ChartType.AREA);

	        conf = chart.getConfiguration();

	        conf.setTitle(new Title("Observed sentences occurrence in Tweets by context"));

	        XAxis xAxis = new XAxis();
	        xAxis.setTickmarkPlacement(TickmarkPlacement.ON);

			int daysNumber = Days.daysBetween(new DateTime(campaign.getStartDate()), new DateTime(new Date())).getDays();

			ArrayList<String> list = new ArrayList<String>();
			dateList = new ArrayList<>();
	        for (int i = daysNumber; i >= 0; i--) {
	        	Calendar c = Calendar.getInstance();
	        	c.setTime(new Date());
	        	c.add(Calendar.DATE, -i);
				dateList.add(c.getTime());
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

			drawChart(campaign.getKeywords());

	        return chart;
    }

	public void drawChart(List<String> keywords){
		TweetDao dao = TweetDao.get();
		
		ListSeries superPositiveData = new ListSeries("Super Positive");
		superPositiveData.setData(dateList.stream().map(date -> dao.getTweets(campaign.getTitle(), keywords, date, Emotion.SUPER_POSITIVE).size()).collect(Collectors.toList()));
		
		ListSeries positiveData = new ListSeries("Positive");
		positiveData.setData(dateList.stream().map(date -> dao.getTweets(campaign.getTitle(), keywords, date, Emotion.POSITIVE).size()).collect(Collectors.toList()));
		
		ListSeries neutralData = new ListSeries("Neutral");
		neutralData.setData(dateList.stream().map(date -> dao.getTweets(campaign.getTitle(), keywords, date, Emotion.NEUTRAL).size()).collect(Collectors.toList()));
		
		ListSeries negativeData = new ListSeries("Negative");
		negativeData.setData(dateList.stream().map(date -> dao.getTweets(campaign.getTitle(), keywords, date, Emotion.NEGATIVE).size()).collect(Collectors.toList()));
//	        conf.addSeries(new ListSeries("Super Positive", 502, 235, 309, 247, 402, 1634, 5268));
		
		ListSeries superNegativeData = new ListSeries("Super Negative");
		superNegativeData.setData(dateList.stream().map(date -> dao.getTweets(campaign.getTitle(), keywords, date, Emotion.SUPER_NEGATIVE).size()).collect(Collectors.toList()));
		
		
		conf.setSeries(superPositiveData, positiveData, neutralData, negativeData, superNegativeData);
//	        conf.addSeries(new ListSeries("Super negative", 21, 23, 22, 63, 33, 43, 76));

		chart.drawChart(conf);
	}
}