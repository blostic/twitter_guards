package ui.timeline.editor.steps;

import persistance.campaign.entity.Campaign;

import com.vaadin.ui.VerticalLayout;

public abstract class EditorStep extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	
	public abstract void updateCampaign(Campaign campaign);
}
