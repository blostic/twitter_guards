package ui.login;

import persistance.user.dao.UserDao;
import persistance.user.entity.User;
import ui.TwitterGuardUI;
import ui.utils.CustomePasswordField;
import ui.utils.CustomeTextField;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LoginWindow extends Window {
	
	private static final long serialVersionUID = 1L;

	private Button btnLogin = new Button("Login");
    private CustomeTextField login = new CustomeTextField("Username", "");
    private CustomePasswordField password = new CustomePasswordField("Password", "");

	private TwitterGuardUI twitterGuardUI;

    public LoginWindow(TwitterGuardUI twitterGuardUI){
        super("Authentication Required !");
		this.twitterGuardUI = twitterGuardUI;
        setContent(addLoginForm());
        setClosable(false);
        setDraggable(false);
        setResizable(false);
        setWidth("400px");
        setHeight("250px");
        center();
        
        setStyleName("login-form-window");
    }

    private VerticalLayout addLoginForm ()
    {
    	VerticalLayout loginForm = new VerticalLayout();
    	Label loginLabel = new Label ("Please login in order to use the application");
		loginForm.addComponent( loginLabel );
     	loginForm.addComponent( login );
    	loginForm.addComponent( password );
    	
    	login.getTextField().setId("login-window-login");
    	password.getTextField().setId("login-window-password");
    	
    	loginForm.setComponentAlignment(loginLabel, Alignment.MIDDLE_CENTER);
    	loginForm.setComponentAlignment(login, Alignment.MIDDLE_CENTER);
    	loginForm.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
    	
    	loginForm.setSpacing(true);
    	loginForm.setMargin(true);
    	
    	HorizontalLayout buttonsWrapper = new HorizontalLayout();
    	
    	Button registerButton = new Button("Register");
    	
    	buttonsWrapper.addComponents(btnLogin, new Label("<div style='padding-top:10px;'> or </div>", ContentMode.HTML), registerButton);
    	buttonsWrapper.setComponentAlignment(btnLogin, Alignment.MIDDLE_CENTER);
    	
    	
    	btnLogin.addStyleName("no-default-style");
    	btnLogin.addStyleName("good-looking-button");
    	btnLogin.addStyleName("editor-next-button");

    	btnLogin.setId("login-window-login-button");
    	
    	registerButton.addStyleName("no-default-style");
    	registerButton.addStyleName("good-looking-button");
    	registerButton.addStyleName("editor-finish-button");
    	registerButton.setId("user-register-button");
    	btnLogin.addClickListener(c -> {
    			try {
    				User user = authenticate(login.getValue(), password.getValue());
    				twitterGuardUI.setCurrentUser(user);
    				twitterGuardUI.loadProtectedResources();
    				Notification.show("Welcome " + user.getName() + " " + user.getSurname() + "!", Notification.TYPE_HUMANIZED_MESSAGE);
    				this.close();
    			} catch (Exception e) {
    				login.getTextField().setValue("");
    				password.getTextField().setValue("");
    				Notification.show("Login faild, Please try again", Notification.TYPE_HUMANIZED_MESSAGE);
    			}
    		}
    	);
    	
    	registerButton.addClickListener(click -> {
    		this.close();
    		twitterGuardUI.addWindow(new RegisterWindow(twitterGuardUI));
    	});
    	loginForm.addComponent(buttonsWrapper);
    	loginForm.setComponentAlignment(buttonsWrapper, Alignment.MIDDLE_CENTER);
    	buttonsWrapper.setSpacing(true);
    	return loginForm;
    }
    
    
    public User authenticate( String login, String password) throws Exception
    {
    	for (User user : UserDao.get().getAll()) {
    		String generatedHash = UserUtils.getPasswordHash(password, user.getSalt());
    		if (user.getName().equals(login) && user.getPasswordHash().equals(generatedHash)) {
    			return user;
    		}
    	}
       throw new Exception("Login failed!");
    }
    
}
