package ui.timeline;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class TimelineNote extends HorizontalLayout{
    
	private static final long serialVersionUID = 1L;

	private Image image = new Image();

    public TimelineNote(String title, String description){
        this.setStyleName("cd-timeline-block");

        Resource res = new ThemeResource("graphics/picture.svg" );
        this.image = new Image(null, res);
        this.image.setStyleName("cd-timeline-img");
        this.image.addStyleName("cd-movie");

        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label("<font size=5>"+title +"</font>",ContentMode.HTML));
        content.setStyleName("cd-timeline-content");
        Label label = new Label(description, ContentMode.HTML);
        content.addComponent(label);
        label.setSizeFull();
        image.setSizeUndefined();
        addComponents(image, content);
//        image.setSizeFull();
//        content.setSizeFull();
//        setSizeFull();

    }

}