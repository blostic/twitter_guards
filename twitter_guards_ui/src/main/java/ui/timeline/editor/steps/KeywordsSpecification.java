package ui.timeline.editor.steps;

import java.util.ArrayList;
import java.util.List;

import persistance.campaign.entity.Campaign;
import ui.utils.CustomeTextField;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class KeywordsSpecification extends EditorStep {

	private static final long serialVersionUID = 1L;
	private CustomeTextField keywordSearch;
	private Button addButton;
	
	private VerticalLayout keywordContent = new VerticalLayout();
	private List<String> keywords = new ArrayList<String>();

	public KeywordsSpecification() {
		HorizontalLayout wrapper = new HorizontalLayout();
		keywordSearch = new CustomeTextField("New keyword to search:", "What keyword you are looking for?");
		keywordSearch.getTextField().setId("keyword-specification-text-field");
		addButton = new Button("Add");
		addButton.setId("add-keyword-to-list-button");
		addButton.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				String typedKeyword = keywordSearch.getTextField().getValue();
				if (!"".equals(typedKeyword)) {
					keywordContent.addComponent(new KeywordComponent(typedKeyword, KeywordsSpecification.this));
					keywordSearch.getTextField().setValue("");
					keywords.add(typedKeyword);
				}
			}
		});
		
		addButton.addStyleName("no-default-style");
		addButton.addStyleName("good-looking-button");
		addButton.addStyleName("add-keyword-button");
		
		wrapper.addComponents(new VerticalLayout(keywordSearch), addButton);
		
		wrapper.setComponentAlignment(addButton, Alignment.MIDDLE_RIGHT);
		wrapper.setSpacing(true);
		
		addComponents(wrapper, keywordContent);
		setComponentAlignment(wrapper, Alignment.MIDDLE_CENTER);
		setComponentAlignment(keywordContent, Alignment.MIDDLE_CENTER);
		keywordContent.setWidth("500px");
		keywordContent.setSpacing(true);
	}

	@Override
	public void updateCampaign(Campaign campaign) {
		campaign.setKeywords(keywords);
	}

	public void removeKeyword(KeywordComponent keywordComponent) {
		keywordContent.removeComponent(keywordComponent);
		keywords.remove(keywordComponent.getValue());
	}
	
}