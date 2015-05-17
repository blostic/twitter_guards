package ui;

import ui.menu.TwitterHeader;

import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class ContentWrapper extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	private Layout content;
	private TwitterHeader header;
	
	public ContentWrapper(TwitterHeader header, Layout initialContent) {
		this.header = header;
		this.addComponent(header);
		
		this.header.setContentWrapper(this);
		this.setContent(initialContent);
		initialContent.setSizeFull();
	}

	public Layout getContent() {
		return content;
	}

	public void setContent(Layout content) {
		this.removeAllComponents();
		this.addComponent(header);
		this.addComponent(content);
		this.content = content;
	}
	
}
