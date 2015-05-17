package ui.utils;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;

public class CustomePasswordField extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	
	private PasswordField passwordField = new PasswordField();
	
	private String DEFAULT_WIDTH = "300px";
	private String DEFAULT_HEIGHT = "40px";
	
	public CustomePasswordField(String descripiton, String initialValue){ 
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

	public PasswordField getTextField() {
		return passwordField;
	}

	public void setTextField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}
	
	public String getValue() {
		return passwordField.getValue();
	}
	
}
