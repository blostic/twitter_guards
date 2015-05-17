package ui.timeline.editor.steps;

import java.util.Date;

import persistance.campaign.entity.Campaign;
import ui.utils.CustomeDataSelector;
import ui.utils.CustomeTextField;

import com.vaadin.ui.Alignment;

public class BasicConfig extends EditorStep {

	private static final long serialVersionUID = 1L;
	
	private CustomeTextField title;
	private CustomeTextField description;
	private CustomeDataSelector startDate;
	private CustomeDataSelector endDate;
	
	public BasicConfig(){
		title = new CustomeTextField("Title:", "Type campaing title...");
		description = new CustomeTextField("Description:", "Type campaing description...");
		startDate = new CustomeDataSelector("Start date:", new Date());
		endDate = new CustomeDataSelector("End date:", new Date());
		
		startDate.getDateField().setRangeStart(new Date());
		endDate.getDateField().setRangeStart(new Date());
		
		setSpacing(true);
		setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
		addComponents(title, description, startDate, endDate);
		setWidthUndefined();
	}

	@Override
	public void updateCampaign(Campaign campaign) {
		campaign.setTitle(title.getValue());
		campaign.setDescription(description.getValue());
		campaign.setStartDate(startDate.getValue());
		campaign.setEndDate(endDate.getValue());
	}

}
