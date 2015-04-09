package ui;

import ui.resources.TwitterGuardsIcons;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

@Theme("mytheme")
//@PreserveOnRefresh
public class TwitterGuardUI extends UI {
	private final class WindowExtension extends Window {
		private static final long serialVersionUID = 1L;
		
		public WindowExtension() {
			super();
			VerticalLayout vl = new VerticalLayout();
			Label label = new Label("test222wee");
			vl.addComponent(label);
			setContent(vl);
		}
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout content = new VerticalLayout();
//		content.setSizeFull();
		GoogleMap googleMap = new GoogleMap("");
		googleMap.setWidth("800px");
		googleMap.setHeight("400px");
		Button b = new Button("Show window");
		b.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().addWindow(new WindowExtension());
			}
		});
		content.addComponents(googleMap, new Label("Piotr"),new Label("Piotr2"), b);
		Image image = new Image("image", TwitterGuardsIcons.MAIN_VIEW_ICON.getFileResource());
		content.addComponent(image);
		setContent(content);
	}
}
