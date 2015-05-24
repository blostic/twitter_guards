package ui.timeline.results;

import java.util.Date;

import persistance.campaign.entity.Campaign;
import persistance.tweets.entity.Emotion;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class TweetsDetailedInformation extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public TweetsDetailedInformation(Campaign campaign) {
		Table tweetsTable = new Table();
		tweetsTable.addContainerProperty("Keyword", String.class, null);
		tweetsTable.addContainerProperty("Tweet Text",  TextArea.class, null);
		tweetsTable.addContainerProperty("Date",  Date.class, null);
		tweetsTable.addContainerProperty("Location",  String.class, null);
		tweetsTable.addContainerProperty("Link to post",  Label.class, null);
		tweetsTable.addContainerProperty("Score",  Emotion.class, null);
		
		addFakeData(tweetsTable);
		
		tweetsTable.setPageLength(10);
		tweetsTable.addStyleName("result-summary-table");
		addComponent(tweetsTable);
		tweetsTable.setSizeFull();
	}
	
	private void addFakeData(Table table) {
		for (int i = 0; i < 100; i++) {
			 TextArea commentsField = new TextArea();
			 commentsField.setRows(5);
			 commentsField.setWidth("100%");
			 commentsField.setValue("Lorem ipsum dolor sit amet, "
						+ "consectetur adipiscing elit. Proin in imperdiet tellus, "
						+ "at elementum mauris. In venenatis commodo erat luctus gravida. "
						+ "Maecenas nec orci maximus tellus auctor convallis eu at"
						+ " dolor. Donec eget tincidunt felis, ac mattis sem."
						+ " Nulla ac urna at nisl laoreet");
			 Label linkLabel = new Label("<a href='http://www.google.com'>www.google.com</a>", ContentMode.HTML);
			 table.addItem(new Object[]{"Kaczor", commentsField, new Date(), "(123.23 lat, 123 lot)", linkLabel, Emotion.SUPER_NEGATIVE}, i);
		}
	}

}
