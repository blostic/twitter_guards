package ui.views;

import com.vaadin.ui.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pl.edu.agh.iosr.twitter.model.Tweet;

import java.util.List;

/**
 * Created by radoslawdyrda on 28.04.2015.
 */
public class CampaignView extends TwitterGuardsView {

    @Autowired
    MongoTemplate mongoTemplate;

    public CampaignView(String campaignName) {
        Table table = new Table("Tweets");

        table.addContainerProperty("Tweet body", String.class, null);
        table.addContainerProperty("ranking", Integer.class, null);

        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("campaignName").is(campaignName));

        List<Tweet> tweets = mongoTemplate.find(findQuery, Tweet.class);

        int tweetCount = 0;
        for(Tweet tweet:tweets){
            table.addItem(new Object[]{tweet.getTweet().getText(), tweet.getRank()}, tweetCount++);
        }
    }
}
