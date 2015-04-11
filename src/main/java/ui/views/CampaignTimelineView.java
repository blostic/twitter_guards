package ui.views;

import ui.timeline.TimelineComponent;

public class CampaignTimelineView extends TwitterGuardsView{

	private static final long serialVersionUID = 1L;

	public CampaignTimelineView() {
		super();
		addComponent(new TimelineComponent());
	}
}
