package ui.timeline;

import java.util.ArrayList;
import java.util.List;

import persistance.campaign.dao.CampaignDao;
import persistance.campaign.entity.Campaign;

import com.vaadin.ui.VerticalLayout;

public class TimelineComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private List<TimelineNote> notes = new ArrayList<TimelineNote>();

    public TimelineComponent(){
        setId("cd-timeline");
        setStyleName("cd-container");
        notes.add(new TimelineNote());
        List<Campaign> campaigns = CampaignDao.get().getAll();
        for (Campaign campaign : campaigns) {
        	notes.add(new TimelineNote(campaign));
        }
        notes.forEach(note->addComponent(note));
        
        setSizeFull();
    }

}