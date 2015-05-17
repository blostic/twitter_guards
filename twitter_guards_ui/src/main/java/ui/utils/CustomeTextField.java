package ui.utils;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class CustomeTextField extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	
	private TextField textField = new TextField();
	
	private String DEFAULT_WIDTH = "300px";
	private String DEFAULT_HEIGHT = "40px";
	
	public CustomeTextField(String descripiton, String initialValue){ 
		customizeTextField();
		Label descripitonLabel = new Label(descripiton);
		getTextField().setInputPrompt(initialValue);

		addComponents(descripitonLabel, getTextField());
		
		setComponentAlignment(descripitonLabel, Alignment.MIDDLE_RIGHT);
		setComponentAlignment(getTextField(), Alignment.MIDDLE_RIGHT);
		
		setSpacing(true);
	}

	private void customizeTextField() {
		getTextField().setWidth(DEFAULT_WIDTH);
		getTextField().setHeight(DEFAULT_HEIGHT);
		setStyleName("custome-text-field");
	}

	public TextField getTextField() {
		return textField;
	}

	public void setTextField(TextField textField) {
		this.textField = textField;
	}
	
	public void addValueChangeListener(ValueChangeListener listener) {
		textField.addValueChangeListener(listener);
	}
	
	public String getValue(){
		return textField.getValue();
	}
}
