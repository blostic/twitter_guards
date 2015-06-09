package ui.timeline.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import persistance.campaign.dao.CampaignDao;
import persistance.campaign.entity.Campaign;
import ui.TwitterGuardUI;
import ui.timeline.TimelineComponent;
import ui.timeline.editor.steps.BasicConfig;
import ui.timeline.editor.steps.EditorStep;
import ui.timeline.editor.steps.FacebookProfileStep;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import communicator.TwitterGuardsApiWrapper;

public class FacebookEditorView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private StepsMenu menu;
	private StepsManager stepsManager;
	
	private List<EditorStep> steps = new ArrayList<EditorStep>();
	
	private Campaign campaign = new Campaign();
	
	private int currentIndex = 0;
	
	public FacebookEditorView() {
		menu = new StepsMenu();
		stepsManager = new StepsManager();
		initiateSteps();
		initiateStepsManager();
		campaign.setTwitterCampaign(false);
		showContent();
	}
	
	private void showContent() {
		removeAllComponents();
		addComponents(menu, steps.get(currentIndex), stepsManager);
		
		setComponentAlignment(menu, Alignment.MIDDLE_CENTER);
		setComponentAlignment(steps.get(currentIndex), Alignment.MIDDLE_CENTER);
		setComponentAlignment(stepsManager, Alignment.MIDDLE_CENTER);
		
		steps.get(currentIndex).addStyleName("editor-content-style");
		
		setSpacing(true);
	}
	
	private void initiateStepsManager() {
		stepsManager.getNextButton().addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				steps.get(currentIndex).updateCampaign(campaign);
				currentIndex += 1;
				if (currentIndex < steps.size() - 1) {
					stepsManager.showCommonArrangement();
				} else {
					stepsManager.showFinalArrangement();
				}
				showContent();
			}
		});

		stepsManager.getPreviousButton().addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				currentIndex -= 1;
				if (currentIndex != 0) {
					stepsManager.showCommonArrangement();
				} else {
					stepsManager.showStartArrangement();
				}
				showContent();
			}
		});
				
		stepsManager.getFinishButton().addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				steps.get(currentIndex).updateCampaign(campaign);
				campaign.setUserId(((TwitterGuardUI)UI.getCurrent()).getCurrentUser().getId());
				CampaignDao.get().save(campaign);
				((TwitterGuardUI)UI.getCurrent()).getContentWrapper().setContent(new TimelineComponent());
	        	try {
					new TwitterGuardsApiWrapper().sendHttpRequestFacebook(campaign);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	private void initiateSteps() {
		steps.add(new BasicConfig());
		steps.add(new FacebookProfileStep());
	}
	
}
