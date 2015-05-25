package ui.timeline.results;

import java.util.Date;
import java.util.Map.Entry;

import persistance.campaign.entity.Campaign;
import persistance.tweets.entity.Emotion;
import persistance.tweets.entity.Tweet;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class TweetsDetailedInformation extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private Table tweetsTable;

	public TweetsDetailedInformation(Campaign campaign) {
		tweetsTable = new Table();
		tweetsTable.addContainerProperty("Keyword", String.class, null);
		tweetsTable.addContainerProperty("Tweet Text",  TextArea.class, null);
		tweetsTable.addContainerProperty("Date",  Date.class, null);
		tweetsTable.addContainerProperty("Location",  String.class, null);
		tweetsTable.addContainerProperty("Link to post",  Label.class, null);
		tweetsTable.addContainerProperty("Score",  Emotion.class, null);
		
//		addFakeData();
		getDataForCampaign(campaign);
		
		tweetsTable.setPageLength(8);
		tweetsTable.addStyleName("result-summary-table");
		addComponent(tweetsTable);
		tweetsTable.setSizeFull();
	}
	
	public void getDataForCampaign(Campaign campaign) {
		int i = 0;
		for(Entry<Long, Tweet> tweetEntry: campaign.getTimeToTweetMap().entrySet()) {
			Tweet tweet = tweetEntry.getValue(); 
			TextArea commentsField = new TextArea();
			 commentsField.setRows(5);
			 commentsField.setWidth("100%");
			 commentsField.setValue(tweet.getText());
			 Label linkLabel = new Label("<a href='" + tweet.getUrl() + "'>" + tweet.getUrl() + "</a>", ContentMode.HTML);
			 tweetsTable.addItem(new Object[]{tweet.getRouteName(), commentsField, 
					 new Date(tweetEntry.getKey()), tweet.getPosition().toString(), 
					 linkLabel, tweet.getEmotion()}, i);
			 i++;
		}
	}
	
	private void addFakeData() {
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
			 tweetsTable.addItem(new Object[]{"Kaczor", commentsField, new Date(), "(123.23 lat, 123 lot)", linkLabel, Emotion.SUPER_NEGATIVE}, i);
		}
	}

}
