package ui;

import persistance.user.entity.User;
import ui.login.LoginWindow;
import ui.menu.TwitterHeader;
import ui.timeline.TimelineComponent;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@JavaScript({ "http://code.jquery.com/jquery-2.1.3.min.js" })
@Theme("mytheme")
@PreserveOnRefresh
public class TwitterGuardUI extends UI {
	private static final long serialVersionUID = 1L;
	private ContentWrapper contentWrapper;

	private User currentUser = null;
	
	@Override
	protected void init(VaadinRequest request) {
		autenticateUser();
	}

	private void autenticateUser() {
		addWindow(new LoginWindow(this));
	}

	public void loadProtectedResources() {
		TwitterHeader header = new TwitterHeader();
		setContentWrapper(new ContentWrapper(header, new TimelineComponent()));
		
		setContent(contentWrapper);
		setSizeFull();
		
		setId("twitter_guards_main_view");
	}
	
	public ContentWrapper getContentWrapper() {
		return contentWrapper;
	}

	public void setContentWrapper(ContentWrapper contentWrapper) {
		this.contentWrapper = contentWrapper;
	}


	public User getCurrentUser() {
		return currentUser;
	}


	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
