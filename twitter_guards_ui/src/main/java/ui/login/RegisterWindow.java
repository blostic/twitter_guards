package ui.login;

import persistance.user.dao.UserDao;
import persistance.user.entity.User;
import ui.TwitterGuardUI;
import ui.utils.CustomePasswordField;
import ui.utils.CustomeTextField;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;


public class RegisterWindow extends Window {
	
	private static final long serialVersionUID = 1L;

	private TwitterGuardUI twitterGuardUI;
	private CustomeTextField nameTextField;
	private CustomeTextField surnameTextField;
	private CustomeTextField emailTextField;
	private CustomePasswordField password;
	private CustomePasswordField retypePassword;
	private Button saveButton;
	
    public RegisterWindow(TwitterGuardUI twitterGuardUI){
        super("Register");
		this.twitterGuardUI = twitterGuardUI;
        center();
        Label resultLabel = new Label("Please provide your personal information");
        Component registrationForm = getUserManagementView(resultLabel);
        VerticalLayout content = new VerticalLayout(resultLabel, registrationForm);
        content.setMargin(true);
        content.setSpacing(true);
		setContent(content);
        setStyleName("login-form-window");

        setClosable(false);
        setDraggable(false);
        setResizable(false);
        setWidth("500px");
        setHeight("500px");
    }
    
	@SuppressWarnings("deprecation")
	public VerticalLayout getUserManagementView(Label resultLabel) {
		
		VerticalLayout contentWrapper = new VerticalLayout();
		
		nameTextField = new CustomeTextField("Name", "Type your name...");
		nameTextField.getTextField().setId("user-name-text-field");
		
		surnameTextField = new CustomeTextField("Surname", "Type your surname...");		
		surnameTextField.getTextField().setId("user-surname-text-field");
		
		emailTextField = new CustomeTextField("Email", "Type your email...");
		emailTextField.getTextField().setId("user-email-text-field");
		
		password = new CustomePasswordField("Password", "Type your password...");
		password.getTextField().setId("user-password-text-field");
		
		retypePassword = new CustomePasswordField("Retype passoword", "Retype you password...");
		retypePassword.getTextField().setId("user-retyped-text-field");
		
		contentWrapper.addComponents(nameTextField, surnameTextField, emailTextField, password, retypePassword);
		alignToRight(contentWrapper, nameTextField, surnameTextField, emailTextField, password, retypePassword);
		
		saveButton = new Button("Save");
		saveButton.setId("user-save-button");
		saveButton.addStyleName("no-default-style");
		saveButton.addStyleName("good-looking-button");
		saveButton.addStyleName("add-keyword-button");
		
		saveButton .addClickListener((event) -> {
			if (password.getValue() != null 
					&& password.getValue().equals(retypePassword.getValue())) {
				if (!UserDao.get().checkIfEmailInDbs(emailTextField.getValue())) {
					User user = new User();
					user.setName(nameTextField.getValue());
					user.setSurname(surnameTextField.getValue());
					user.setEmail(emailTextField.getValue());
					String salt = UserUtils.getRandomSalt();
					user.setPasswordHash(UserUtils.getPasswordHash(password.getValue(), salt));
					user.setSalt(salt);
					UserDao.get().save(user);
					Notification.show("User added", Notification.TYPE_HUMANIZED_MESSAGE);
					this.close();
					twitterGuardUI.addWindow(new LoginWindow(twitterGuardUI));
				} else {
					Notification.show("Email already registered. Change email.", Notification.TYPE_HUMANIZED_MESSAGE);
				}
			} else {
				Notification.show("Please provide correct data.", Notification.TYPE_HUMANIZED_MESSAGE);
			}
		});
		
		contentWrapper.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
		
		HorizontalLayout wrapper = new HorizontalLayout();
		Button loginButton = new Button("Go Back");
		
		loginButton.addStyleName("no-default-style");
		loginButton.addStyleName("good-looking-button");
		loginButton.addStyleName("editor-finish-button");
		
		wrapper.setSizeFull();
		wrapper.setMargin(true);
		
		loginButton.addClickListener( e -> {
			this.close();
			twitterGuardUI.addWindow(new LoginWindow(twitterGuardUI));
		});
		
		wrapper.addComponents(loginButton, saveButton);
		
		contentWrapper.addComponent(wrapper);
		contentWrapper.setMargin(true);
		
		contentWrapper.setSpacing(true);
		return contentWrapper;
	}

	private void alignToRight(VerticalLayout wrapper, Component... components) {
		for (Component component : components) {
			wrapper.setComponentAlignment(component, Alignment.MIDDLE_CENTER);
		}
	}
    
}
