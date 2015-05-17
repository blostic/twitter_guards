package ui.timeline.results;

import persistance.campaign.entity.Campaign;

import com.vaadin.ui.HorizontalLayout;

public class CampaignDetailInformation extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	
	public CampaignDetailInformation(Campaign campaign){
		addComponents(new HistoryChart(), new UserContentmentChart());
		setSpacing(true);
		setSizeFull();
	}
}
