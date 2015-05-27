package ui.user;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import persistance.user.dao.UserDao;
import persistance.user.entity.User;
import ui.utils.CustomePasswordField;
import ui.utils.CustomeTextField;
import ui.views.TwitterGuardsView;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
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
					&& password.getValue().equals(retypePassword.getValue())
					&& ! UserDao.get().checkIfEmailInDbs(emailTextField.getValue())) {
				User user = new User();
				user.setName(nameTextField.getValue());
				user.setSurname(surnameTextField.getValue());
				user.setEmail(emailTextField.getValue());
				String salt = getRandomSalt();
				user.setPasswordHash(getPasswordHash(password.getValue(), salt));
				user.setSalt(salt);
				UserDao.get().save(user);
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
	
	private String getPasswordHash(String value, String salt) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest((value + salt).getBytes("UTF-8"));
			return hash.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	private String getRandomSalt() {
		return new BigInteger(130, new SecureRandom()).toString(32);
	}

	private void alignToRight(VerticalLayout wrapper, Component... components) {
		for (Component component : components) {
			wrapper.setComponentAlignment(component, Alignment.MIDDLE_CENTER);
		}
	}
}
