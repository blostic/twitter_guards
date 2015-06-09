package ui.timeline.results;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import persistance.campaign.entity.Campaign;

import java.util.Arrays;

/**
 * Created by radoslawdyrda on 09.06.2015.
 */
public class PageIdSelect extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    private ComboBox keywordSelection = new ComboBox();

    public PageIdSelect(Campaign campaign, CampaignDetailInformationByKeyword campaignDetailInformationByKeyword, UserContentmentChart userContentmentChart, HistoryChart historyChart) {
        Label selectKeyword = new Label("Select keyword");
        keywordSelection.setNullSelectionAllowed(false);

        for (Object keyword : campaign.getFacebookProfiles()) {
            keywordSelection.addItem(keyword);
        }
        keywordSelection.setValue(campaign.getFacebookProfiles().get(0));
        keywordSelection.addValueChangeListener(
                v->{
                    campaignDetailInformationByKeyword.setSelectedKeyword(v.toString());
                    userContentmentChart.drawChartFacebook(v.getProperty().getValue().toString());
                    historyChart.drawFacebookChart(v.getProperty().getValue().toString());
                });
        addComponents(selectKeyword, keywordSelection);
        setComponentAlignment(selectKeyword, Alignment.MIDDLE_CENTER);
        setComponentAlignment(keywordSelection, Alignment.MIDDLE_CENTER);

        addStyleName("selection-combo-box");

        setSpacing(true);
        setSizeFull();
    }

}
