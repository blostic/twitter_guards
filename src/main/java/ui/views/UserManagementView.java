package ui.views;

import com.vaadin.ui.Label;

public class UserManagementView extends TwitterGuardsView{

	private static final long serialVersionUID = 1L;

	public UserManagementView() {
		addComponent(new Label("management"));
	}
}
