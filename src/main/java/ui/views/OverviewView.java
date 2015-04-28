package ui.views;

import java.util.Map;

import com.vaadin.ui.Label;

public class OverviewView extends TwitterGuardsView {

	private static final long serialVersionUID = 1L;
	
	public OverviewView() {
		addComponent(new Label("overview"));
	}
}
