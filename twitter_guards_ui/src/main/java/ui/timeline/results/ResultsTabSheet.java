package ui.timeline.results;

import persistance.campaign.entity.Campaign;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class 	ResultsTabSheet extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public ResultsTabSheet(Campaign campaign){
		TabSheet tabSheet = new TabSheet();
		CampaignDetailInformation campaingSummmaryInformation = new CampaignDetailInformation(campaign);
		CampaignDetailInformationByKeyword campaingSummaryByKeyword = new CampaignDetailInformationByKeyword(campaign);
		TweetsDetailedInformation tweetsDetaileInfo = new TweetsDetailedInformation(campaign);
		
		tabSheet.addTab(campaingSummmaryInformation, "Campaign summary");
		tabSheet.addTab(campaingSummaryByKeyword, "Keywords summary");
		
        if (campaign.isTwitterCampaign()) {
        	tabSheet.addTab(tweetsDetaileInfo, "Tweets details");
		} else {
			tabSheet.addTab(tweetsDetaileInfo, "Facebook details");
		}
		
		addComponent(tabSheet);
		setMargin(true);
	}
}
