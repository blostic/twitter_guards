package ui.timeline.results;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import persistance.campaign.entity.Campaign;

public class CampaignDetailInformationByKeyword extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private String selectedKeyword;

	private Campaign campaign;
	
	public CampaignDetailInformationByKeyword(Campaign campaign) {
		this.campaign = campaign;
		buildCharts();
		setSpacing(true);
		setSizeFull();
	}

	private void buildCharts() {
		KeywordSelect keywordSelect = new KeywordSelect(campaign, this);
		UserContentmentChart userContentmentChart = new UserContentmentChart();
		HorizontalLayout firstLine = new HorizontalLayout(keywordSelect, userContentmentChart);
		firstLine.setSizeFull();
		firstLine.setComponentAlignment(keywordSelect, Alignment.MIDDLE_CENTER);
		HistoryChart historyChart = new HistoryChart();
		addComponents(firstLine, historyChart);
	}

	public String getSelectedKeyword() {
		return selectedKeyword;
	}

	public void setSelectedKeyword(String selectedKeyword) {
		this.selectedKeyword = selectedKeyword;
	}
}
