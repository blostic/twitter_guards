package ui.timeline.results;

import persistance.campaign.entity.Campaign;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class 	ResultsTabSheet extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public ResultsTabSheet(Campaign campaign){
		TabSheet tabSheet = new TabSheet();
		CampaignDetailInformation campaingSummmaryInformation = new CampaignDetailInformation(campaign);
		tabSheet.addTab(campaingSummmaryInformation, "Campaign summary");
		CampaignDetailInformationByKeyword campaingSummaryByKeyword = new CampaignDetailInformationByKeyword(campaign);

		tabSheet.addTab(campaingSummaryByKeyword, campaign.isTwitterCampaign()?"Keywords summary":"Pages summary");

		TweetsDetailedInformation tweetsDetaileInfo = new TweetsDetailedInformation(campaign);
		

        if (campaign.isTwitterCampaign()) {
        	tabSheet.addTab(tweetsDetaileInfo, "Tweets details");
		} else {
			tabSheet.addTab(tweetsDetaileInfo, "Facebook details");
		}
		
		addComponent(tabSheet);
		setMargin(true);
	}
}
