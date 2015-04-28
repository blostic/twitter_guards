package ui.timeline;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class TimelineNote extends HorizontalLayout {
    
	private static final long serialVersionUID = 1L;

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
	
    public TimelineNote(String title, String description){
    	VerticalLayout content = styleNote();
        content.addComponent(new Label("<font size=5>"+title +"</font>", ContentMode.HTML));
        
        Label label = new Label(description, ContentMode.HTML);
        content.addComponent(label);
        label.setSizeFull();
        image.setSizeUndefined();
        addComponents(image, content);
    }

}