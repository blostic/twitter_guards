package ui.views;

import com.vaadin.ui.Label;

public class CampaignTimelineView extends TwitterGuardsView{

	private static final long serialVersionUID = 1L;

	public CampaignTimelineView() {
		addComponent(new Label("timeline"));
	}
}
