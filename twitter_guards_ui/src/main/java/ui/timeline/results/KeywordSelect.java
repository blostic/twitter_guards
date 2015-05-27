package ui.timeline.results;

import persistance.campaign.entity.Campaign;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.Arrays;

public class KeywordSelect extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;

	private ComboBox keywordSelection = new ComboBox();
	
	public KeywordSelect(Campaign campaign, CampaignDetailInformationByKeyword campaignDetailInformationByKeyword, UserContentmentChart userContentmentChart, HistoryChart historyChart) {
		Label selectKeyword = new Label("Select keyword");
		keywordSelection.setNullSelectionAllowed(false);
		
		for (Object keyword : campaign.getKeywords()) {
			keywordSelection.addItem(keyword);
		}
		keywordSelection.setValue(campaign.getKeywords().get(0));
		keywordSelection.addValueChangeListener(
				v->{
					campaignDetailInformationByKeyword.setSelectedKeyword(v.toString());
					userContentmentChart.drawChart(Arrays.asList(v.getProperty().getValue().toString()));
					historyChart.drawChart(Arrays.asList(v.getProperty().getValue().toString()));
				});
		addComponents(selectKeyword, keywordSelection);
		setComponentAlignment(selectKeyword, Alignment.MIDDLE_CENTER);
		setComponentAlignment(keywordSelection, Alignment.MIDDLE_CENTER);
		
		addStyleName("selection-combo-box");
		
		setSpacing(true);
		setSizeFull();
	}
	
}
