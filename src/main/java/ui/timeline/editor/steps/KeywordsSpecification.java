package ui.timeline.editor.steps;

import ui.utils.CustomeTextField;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class KeywordsSpecification extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private CustomeTextField keywordSearch;
	private Button addButton;
	
	private VerticalLayout keywordContent = new VerticalLayout();
	
	public KeywordsSpecification() {
		HorizontalLayout wrapper = new HorizontalLayout();
		keywordSearch = new CustomeTextField("New keyword to search:", "What keyword you are looking for?");
		addButton = new Button("Add");
		
		addButton.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (!"".equals(keywordSearch.getTextField().getValue())) {
					keywordContent.addComponent(new KeywordComponent(keywordSearch.getTextField().getValue(), keywordContent));
					keywordSearch.getTextField().setValue("");
				}
			}
		});
		
		addButton.addStyleName("no-default-style");
		addButton.addStyleName("good-looking-button");
		addButton.addStyleName("add-keyword-button");
		
		wrapper.addComponents(keywordSearch, addButton);
		
		wrapper.setComponentAlignment(keywordSearch, Alignment.MIDDLE_CENTER);
		wrapper.setComponentAlignment(keywordSearch, Alignment.MIDDLE_CENTER);
		wrapper.setSpacing(true);
		
		addComponents(wrapper, keywordContent);
		setComponentAlignment(wrapper, Alignment.MIDDLE_CENTER);
		setComponentAlignment(keywordContent, Alignment.MIDDLE_CENTER);
		
		keywordContent.setWidth("500px");
		keywordContent.setSpacing(true);
	}
	
}