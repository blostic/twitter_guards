package ui.timeline.results;

import java.util.Collection;
import java.util.Date;

import persistance.campaign.entity.Campaign;
import persistance.tweets.dao.TweetDao;
import persistance.tweets.entity.Emotion;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import persistance.tweets.entity.Tweet;

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
		
		//addFakeData(tweetsTable);
		addTweets(tweetsTable, campaign);

		tweetsTable.setPageLength(8);
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

	private void addTweets(Table table, Campaign campaign){
		Collection<Tweet> tweets = TweetDao.get().getTweets(campaign.getTitle(), campaign.getKeywords());
		System.out.println(tweets.size());
		int i = 0;
		for(Tweet tweet : tweets){
			TextArea commentsField = new TextArea();
			commentsField.setWidth("100%");
			commentsField.setValue(tweet.getText());
			Label linkLabel = new Label("<a href='https://twitter.com/statuses/" + tweet.getTweeterTweetId() + "'>Go to tweet</a>", ContentMode.HTML);
			table.addItem(new Object[]{
				tweet.getRouteName().split("_")[1],
					commentsField,
					tweet.getCreationTime(),
					tweet.getPosition().toString(),
					linkLabel,
					tweet.getEmotion()
			}, i);
			i++;
		}
	}

}
