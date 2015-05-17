package ui.resources;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;

public enum TwitterGuardsIcons {
	MAIN_VIEW_ICON("twitter-icon.png");
	
	private String path;

	private TwitterGuardsIcons(String path) {
		this.path = path;
	}
	
	public FileResource getFileResource() {
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		FileResource resource = new FileResource(new File(basepath + "/WEB-INF/icons/" + path));
		return resource;
	}
	
}
