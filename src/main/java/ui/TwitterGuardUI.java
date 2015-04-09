package ui;

import ui.menu.TwitterHeader;
import ui.views.CampaignTimelineView;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

@JavaScript({ "http://code.jquery.com/jquery-2.1.3.min.js" })
@Theme("mytheme")
//@PreserveOnRefresh
public class TwitterGuardUI extends UI {
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		
		TwitterHeader header = new TwitterHeader();
		ContentWrapper contentWrapper = new ContentWrapper(header, new CampaignTimelineView());
		
		setContent(contentWrapper);
	}

}
