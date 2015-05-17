package ui.timeline.editor.steps;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class KeywordComponent extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	private String value;

	public KeywordComponent(String value, KeywordsSpecification keywordsSpecification) {
		this.setValue(value);

		Button exitButton = new Button();
		addComponents(new Label(value), exitButton);

		exitButton.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				keywordsSpecification.removeKeyword(KeywordComponent.this);
			}
		});
		exitButton.addStyleName("no-default-style");
		exitButton.addStyleName("x-button");
		addStyleName("keyword-component-style");
		setSpacing(true);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
