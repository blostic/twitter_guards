package ui.timeline.results;

import persistance.campaign.entity.Campaign;

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
import persistance.tweets.dao.TweetDao;
import persistance.tweets.entity.Emotion;

public class UserContentmentChart extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private Campaign campaign;

	public UserContentmentChart(Campaign campaign) {
		this.campaign = campaign;
		Chart chart = createChart();
		addComponent(chart);
	}
	
    protected Component getChart() {
        Component ret = createChart();
        ret.setWidth("100%");
        ret.setHeight("650px");
        return ret;
    }

    public Chart createChart() {
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

        TweetDao dao = TweetDao.get();

        DataSeriesItem distinct = new DataSeriesItem("Positive", dao.getTweets(campaign.getTitle(), campaign.getKeywords(), Emotion.POSITIVE).size());
        distinct.setSliced(true);
        distinct.setSelected(true);
        series.add(distinct);
        conf.setSeries(series);

        series.add(new DataSeriesItem("Neutral", dao.getTweets(campaign.getTitle(), campaign.getKeywords(), Emotion.NEUTRAL).size()));
        series.add(new DataSeriesItem("Negative", dao.getTweets(campaign.getTitle(), campaign.getKeywords(), Emotion.NEGATIVE).size()));
        series.add(new DataSeriesItem("Unrecognized", dao.getTweets(campaign.getTitle(), campaign.getKeywords(), Emotion.UNRECOGNIZED).size()));

        chart.drawChart(conf);

        return chart;
    }

}
