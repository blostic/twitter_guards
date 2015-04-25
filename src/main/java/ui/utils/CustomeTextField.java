package ui.utils;

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
		textField.setInputPrompt(initialValue);

		addComponents(descripitonLabel, textField);
		
		setComponentAlignment(descripitonLabel, Alignment.MIDDLE_RIGHT);
		setComponentAlignment(textField, Alignment.MIDDLE_RIGHT);
		
		setSpacing(true);
	}

	private void customizeTextField() {
		textField.setWidth(DEFAULT_WIDTH);
		textField.setHeight(DEFAULT_HEIGHT);
		setStyleName("custome-text-field");
	}
	
}
