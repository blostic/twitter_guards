package ui.timeline.results;

import persistance.campaign.entity.Campaign;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class KeywordSelect extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;

	private ComboBox keywordSelection = new ComboBox();
	
	public KeywordSelect(Campaign campaign, CampaignDetailInformationByKeyword campaignDetailInformationByKeyword) {
		Label selectKeyword = new Label("Select keyword");
		
		keywordSelection.setNullSelectionAllowed(false);
		
		for (Object keyword : campaign.getKeywords()) {
			keywordSelection.addItem(keyword);
		}
		keywordSelection.setValue(campaign.getKeywords().get(0));
		keywordSelection.addValueChangeListener(
				v->{campaignDetailInformationByKeyword.setSelectedKeyword(v.toString());});
		addComponents(selectKeyword, keywordSelection);
		setComponentAlignment(selectKeyword, Alignment.MIDDLE_CENTER);
		setComponentAlignment(keywordSelection, Alignment.MIDDLE_CENTER);
		
		addStyleName("selection-combo-box");
		
		setSpacing(true);
		setSizeFull();
	}
	
}
