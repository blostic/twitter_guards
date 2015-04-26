package ui;

import ui.menu.TwitterHeader;
import ui.timeline.editor.steps.KeywordsSpecification;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@JavaScript({ "http://code.jquery.com/jquery-2.1.3.min.js" })
@Theme("mytheme")
//@PreserveOnRefresh
public class TwitterGuardUI extends UI {
	private static final long serialVersionUID = 1L;
	private ContentWrapper contentWrapper;

	@Override
	protected void init(VaadinRequest request) {
		
		TwitterHeader header = new TwitterHeader();
		setContentWrapper(new ContentWrapper(header, new KeywordsSpecification()));
		
		setContent(getContentWrapper());
	}

	public ContentWrapper getContentWrapper() {
		return contentWrapper;
	}

	public void setContentWrapper(ContentWrapper contentWrapper) {
		this.contentWrapper = contentWrapper;
	}

}
