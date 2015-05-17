package ui.utils;

import java.util.Date;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class CustomeDataSelector extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	
	private DateField dateField = new DateField();
	
	private String DEFAULT_WIDTH = "300px";
	private String DEFAULT_HEIGHT = "40px";
	
	public CustomeDataSelector(String descripiton, Date initialDate){ 
		customizeTextField();
		Label descripitonLabel = new Label(descripiton);
		dateField.setData(initialDate);
		
		addComponents(descripitonLabel, getDateField());
		
		setComponentAlignment(descripitonLabel, Alignment.MIDDLE_RIGHT);
		setComponentAlignment(getDateField(), Alignment.MIDDLE_RIGHT);
		
		setSpacing(true);
	}

	private void customizeTextField() {
		dateField.setWidth(DEFAULT_WIDTH);
		dateField.setHeight(DEFAULT_HEIGHT);
		dateField.setDateFormat("yyyy MMM dd");
		setStyleName("custome-date-field");
	}

	public DateField getDateField() {
		return dateField;
	}

	public void setDateField(DateField dateField) {
		this.dateField = dateField;
	}
	
	public void addValueChangeListener(ValueChangeListener listener) {
		dateField.addValueChangeListener(listener);
	}
	
	public Date getValue() {
		return dateField.getValue();
	}
}
