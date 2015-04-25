package ui.timeline.editor.steps;

import java.util.Date;

import ui.utils.CustomeDataSelector;
import ui.utils.CustomeTextField;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class BasicConfig extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	private CustomeTextField title;
	private CustomeTextField description;
	private CustomeDataSelector startDate;
	private CustomeDataSelector endDate;
	
	public BasicConfig(){
		title = new CustomeTextField("Title:", "Type campaing title...");
		description = new CustomeTextField("Description:", "Type campaing description...");
		startDate = new CustomeDataSelector("Start date:", new Date());
		endDate = new CustomeDataSelector("End date:", new Date());
		
		startDate.getDateField().setRangeStart(new Date());
		endDate.getDateField().setRangeStart(new Date());
		
		setSpacing(true);
		setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
		addComponents(title, description, startDate, endDate);
		setWidthUndefined();
	}
	
}
