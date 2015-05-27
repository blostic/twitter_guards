package ui.user;

import persistance.user.dao.UserDao;
import persistance.user.entity.User;
import ui.TwitterGuardUI;
import ui.login.UserUtils;
import ui.utils.CustomePasswordField;
import ui.utils.CustomeTextField;
import ui.views.TwitterGuardsView;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class UserManagementView extends TwitterGuardsView{

	private static final long serialVersionUID = 1L;
	private CustomeTextField nameTextField;
	private CustomeTextField surnameTextField;
	private CustomeTextField emailTextField;
	private CustomePasswordField password;
	private CustomePasswordField retypePassword;
	private Button saveButton;
	
	public UserManagementView() {
		User user = ((TwitterGuardUI)UI.getCurrent()).getCurrentUser();
		VerticalLayout contentWrapper = new VerticalLayout();
		
		nameTextField = new CustomeTextField("Name", "Type your name...");
		nameTextField.getTextField().setId("user-name-text-field");
		nameTextField.setValue(user.getName());
		
		surnameTextField = new CustomeTextField("Surname", "Type your surname...");		
		surnameTextField.getTextField().setId("user-surname-text-field");
		surnameTextField.setValue(user.getSurname());
		
		emailTextField = new CustomeTextField("Email", "Type your email...");
		emailTextField.getTextField().setId("user-email-text-field");
		emailTextField.setValue(user.getEmail());
		
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
					&& password.getValue().equals(retypePassword.getValue())
					&& ! UserDao.get().checkIfEmailInDbs(emailTextField.getValue())) {
				user.setName(nameTextField.getValue());
				user.setSurname(surnameTextField.getValue());
				user.setEmail(emailTextField.getValue());
				String salt = UserUtils.getRandomSalt();
				user.setPasswordHash(UserUtils.getPasswordHash(password.getValue(), salt));
				user.setSalt(salt);
				UserDao.get().save(user);
				Notification.show("Data changed", Notification.TYPE_HUMANIZED_MESSAGE);
			} else if (password.getValue() == null || password.getValue() == "") {
				user.setName(nameTextField.getValue());
				user.setSurname(surnameTextField.getValue());
				user.setEmail(emailTextField.getValue());
				UserDao.get().save(user);
				Notification.show("Data changed", Notification.TYPE_HUMANIZED_MESSAGE);
			}
		
		});
		
		contentWrapper.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
		contentWrapper.setWidth("400px");
		
		contentWrapper.addComponent(saveButton);
		
		addComponent(contentWrapper);
		
		setComponentAlignment(contentWrapper, Alignment.MIDDLE_CENTER);
		
		setMargin(true);
		
		contentWrapper.setSpacing(true);
		setSpacing(true);
		setSizeFull();
	}

	private void alignToRight(VerticalLayout wrapper, Component... components) {
		for (Component component : components) {
			wrapper.setComponentAlignment(component, Alignment.MIDDLE_CENTER);
		}
	}
}
