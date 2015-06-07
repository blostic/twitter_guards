package ui.timeline.results;

import java.util.List;

import persistance.campaign.entity.Campaign;
import persistance.tweets.dao.TweetDao;
import persistance.tweets.entity.Emotion;

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
	private Campaign campaign;
    private Configuration conf;
    private Chart chart;

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
        chart = new Chart(ChartType.PIE);

        conf = chart.getConfiguration();


        if (campaign.isTwitterCampaign()) {
        	conf.setTitle("Context of collected tweets");
		} else {
        	conf.setTitle("Context of collected Facebook comments");
		}


        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setCursor(Cursor.POINTER);
        Labels dataLabels = new Labels(true);
        dataLabels
                .setFormatter("''+ this.point.name +': '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);
        conf.setPlotOptions(plotOptions);

        drawChart(campaign.getKeywords());

        return chart;

    }

    public void drawChart(List<String> keywords){
        DataSeries series = new DataSeries();

        TweetDao dao = TweetDao.get();

        series.add(new DataSeriesItem("Super Positive", dao.getTweets(campaign.getTitle(), keywords, Emotion.SUPER_POSITIVE).size()));
        DataSeriesItem distinct = new DataSeriesItem("Positive", dao.getTweets(campaign.getTitle(), keywords, Emotion.POSITIVE).size());
        distinct.setSliced(true);
        distinct.setSelected(true);
        series.add(distinct);
        conf.setSeries(series);

        series.add(new DataSeriesItem("Neutral", dao.getTweets(campaign.getTitle(), keywords, Emotion.NEUTRAL).size()));
        series.add(new DataSeriesItem("Negative", dao.getTweets(campaign.getTitle(), keywords, Emotion.NEGATIVE).size()));
        series.add(new DataSeriesItem("Super Negative", dao.getTweets(campaign.getTitle(), keywords, Emotion.SUPER_NEGATIVE).size()));
        series.add(new DataSeriesItem("Unrecognized", dao.getTweets(campaign.getTitle(), keywords, Emotion.UNRECOGNIZED).size()));

        chart.drawChart(conf);
    }

}
