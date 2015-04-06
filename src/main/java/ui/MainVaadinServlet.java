package ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = true, ui = TwitterGuardUI.class, widgetset = "AppWidgetSet")
public class MainVaadinServlet extends VaadinServlet {
	private static final long serialVersionUID = 1L;
}
