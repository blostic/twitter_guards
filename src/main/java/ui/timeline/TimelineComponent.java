package ui.timeline;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.VerticalLayout;

public class TimelineComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private List<TimelineNote> notes = new ArrayList<TimelineNote>();

    public TimelineComponent(){
        setId("cd-timeline");
        setStyleName("cd-container");
        for (int i = 0; i < 10; i++){
            notes.add(new TimelineNote("asd", "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto, optio, dolorum \" +\n" +
                    "                \"provident rerum aut hic quasi placeat iure tempora laudantium ipsa ad debitis unde? Iste voluptatibus \" +\n" +
                    "                \"minus veritatis qui ut</p>"));
        }
        notes.forEach(note->addComponent(note));
        
        setSizeFull();
    }

}