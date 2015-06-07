package ui.timeline.editor.steps;

import ui.TwitterGuardUI;
import ui.timeline.editor.EditorView;
import ui.timeline.editor.FacebookEditorView;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class CampaignType extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public CampaignType() {
		Button facebookCampaignButton = new Button();
		Button twitterCampaignButton = new Button();
		facebookCampaignButton.setWidth("400px");
		facebookCampaignButton.setHeight("500px");
		
		twitterCampaignButton.setWidth("400px");
		twitterCampaignButton.setHeight("500px");
		
		facebookCampaignButton.addStyleName("no-default-style");
		twitterCampaignButton.addStyleName("no-default-style");
		
		facebookCampaignButton.addStyleName("facebook-selection-button");
		twitterCampaignButton.addStyleName("twitter-selection-button");
		
		HorizontalLayout campaignTypeWrapper = new HorizontalLayout(facebookCampaignButton, twitterCampaignButton);
		campaignTypeWrapper.setComponentAlignment(twitterCampaignButton, Alignment.MIDDLE_CENTER);
		campaignTypeWrapper.setComponentAlignment(facebookCampaignButton, Alignment.MIDDLE_CENTER);
		
		campaignTypeWrapper.setWidth("100%");
		campaignTypeWrapper.setSpacing(true);
		campaignTypeWrapper.setMargin(true);
		
		addComponent(campaignTypeWrapper);
		
		twitterCampaignButton.addClickListener((event)-> {
			((TwitterGuardUI)UI.getCurrent()).getContentWrapper().setContent(new EditorView());			
		});
		facebookCampaignButton.addClickListener((event)-> {
			((TwitterGuardUI)UI.getCurrent()).getContentWrapper().setContent(new FacebookEditorView());			
		});
	}
	
}
