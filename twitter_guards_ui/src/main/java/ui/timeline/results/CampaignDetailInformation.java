package ui.timeline.results;

import persistance.campaign.entity.Campaign;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class CampaignDetailInformation extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	public CampaignDetailInformation(Campaign campaign){
		KeywordsLabelWrapper keywordSelect = new KeywordsLabelWrapper(campaign);
		
		UserContentmentChart userContentmentChart = new UserContentmentChart();
		HorizontalLayout firstLine = new HorizontalLayout(keywordSelect, userContentmentChart);
		firstLine.setSizeFull();
		firstLine.setComponentAlignment(keywordSelect, Alignment.MIDDLE_CENTER);
		HistoryChart historyChart = new HistoryChart();
		addComponents(firstLine, historyChart);

		setSpacing(true);
		setHeight("700px");
		setSizeFull();
	}
}
