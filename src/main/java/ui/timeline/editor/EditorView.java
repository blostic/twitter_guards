package ui.timeline.editor;

import ui.timeline.editor.steps.BasicConfig;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class EditorView extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	private Layout currentEditorContent;

	private StepsMenu menu;
	
	public EditorView() {
		menu = new StepsMenu();
		currentEditorContent = new BasicConfig();
		addComponents(menu, currentEditorContent);
		setComponentAlignment(currentEditorContent, Alignment.MIDDLE_CENTER);
	}
	
}
