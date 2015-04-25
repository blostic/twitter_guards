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
        notes.add(new TimelineNote());
        for (int i = 0; i < 10; i++){
            notes.add(new TimelineNote("Przykładowy tytuł kampani", "<p>Lorem ipsum dolor sit amet, qualisque torquatos signiferumque"
            		+ " ex nec, eu ius duis clita nonumy. Doctus indoctum in cum, zril tritani malorum an qui, te mei sint docendi."
            		+ " Ea sit dicta ludus eirmod, tale facilis id vel. His timeam epicuri praesent ut. Eos id dico consequat.</p>"));
        }
        notes.forEach(note->addComponent(note));
        
        setSizeFull();
    }

}