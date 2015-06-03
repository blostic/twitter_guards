package ui.timeline;

import java.text.SimpleDateFormat;
import java.util.Date;

import persistance.campaign.entity.Campaign;
import persistance.tweets.dao.TweetDao;
import ui.TwitterGuardUI;
import ui.timeline.editor.EditorView;
import ui.timeline.editor.steps.CampaignType;
import ui.timeline.results.ResultsTabSheet;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class TimelineNote extends HorizontalLayout {
    
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	
	private Image image = new Image();

	public TimelineNote() {
		VerticalLayout content = styleNote();
		addComponent(content);
		addStyleName("new-campaign");
		Label addNewProgram = new Label("Add new campaign!");
		addNewProgram.setStyleName("new-campaign-label");
		content.addComponent(addNewProgram);
		content.setComponentAlignment(addNewProgram, Alignment.MIDDLE_CENTER);
		content.setSizeFull();
		content.setId("new-campaign-component");
		content.addLayoutClickListener(new LayoutClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void layoutClick(LayoutClickEvent event) {
				((TwitterGuardUI)UI.getCurrent()).getContentWrapper().setContent(new CampaignType());
			}
		});
		setWidth("45%");
	}
	
	public VerticalLayout styleNote() {
		VerticalLayout content = new VerticalLayout();
		this.setStyleName("cd-timeline-block");
		
		Resource res = new ThemeResource("graphics/picture.svg" );
		this.image = new Image(null, res);
		this.image.setStyleName("cd-timeline-img");
		this.image.addStyleName("cd-movie");		

		content.setStyleName("cd-timeline-content");
		content.setWidth("100%");
		return content;
	}
	
	private HorizontalLayout getDates(Date startDate, Date endDate) {
		Label fromLabel = new Label("<div class='date-label'>From </div>", ContentMode.HTML);
		Label toLabel = new Label("<div class='date-label'>to </div>", ContentMode.HTML);
		Label dateStartLabel = new Label("<div class='date-value'>" + formatter.format(startDate) + "</div>", ContentMode.HTML);
		Label dateEndLabel = new Label("<div class='date-value'>" + formatter.format(endDate) + "</div>", ContentMode.HTML);
		Label fakeLabel = new Label();
		HorizontalLayout wrapper = new HorizontalLayout(fakeLabel, fromLabel, dateStartLabel, toLabel, dateEndLabel);
		wrapper.setExpandRatio(fakeLabel, 1.0f);
		wrapper.setSpacing(true);
		wrapper.setSizeFull();
		wrapper.setStyleName("timeline-date-wrapper");
		return wrapper;
	}
	
    public TimelineNote(Campaign campaign){
    	VerticalLayout content = styleNote();
		int tweetsCount = TweetDao.get().getTweets(campaign.getTitle(), campaign.getKeywords()).size();
        Label processedTweetsLabel = new Label("Processed tweets: " + tweetsCount);
        content.addComponent(new Label("<font size=5> title: "+ campaign.getTitle() +"</font>", ContentMode.HTML));
        content.addComponent(new Label("<font size=3> description: "+ campaign.getDescription() +"</font>", ContentMode.HTML));
        content.addComponents(processedTweetsLabel);
        content.addComponent(getDates(campaign.getStartDate(), campaign.getEndDate()));
        content.setSpacing(true);
        
        image.setSizeUndefined();
        addComponents(image, content);
        
        content.addLayoutClickListener(new LayoutClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void layoutClick(LayoutClickEvent event) {
				((TwitterGuardUI)UI.getCurrent()).getContentWrapper().setContent(new ResultsTabSheet(campaign));
			}
		});
    }

}