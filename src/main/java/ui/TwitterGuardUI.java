package ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@PreserveOnRefresh
public class TwitterGuardUI extends UI {
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout content = new VerticalLayout();
		content.addStyleName("valo");
		content.setSizeFull();
		GoogleMap googleMap = new GoogleMap("");
		googleMap.setWidth("800px");
		googleMap.setHeight("400px");
		content.addComponents(googleMap);
		setContent(content);
	}
}
