package ui.timeline.results;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import persistance.campaign.entity.Campaign;

public class KeywordsLabelWrapper extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public KeywordsLabelWrapper(Campaign campaign) {
		Label selectKeyword = new Label("Selected keywords");
		
		addComponents(selectKeyword);
		String keywords = "";
		for (String keyword : campaign.getKeywords()) {
			keywords += keyword + ", ";
		}
		Label keywordsLabel = new Label(keywords.subSequence(0, keywords.length() -2).toString());
		addComponents(selectKeyword, keywordsLabel);
		
		setStyleName("keywords-wrapper-component");
	}

}
