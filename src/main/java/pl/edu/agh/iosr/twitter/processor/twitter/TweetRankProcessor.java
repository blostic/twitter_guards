package pl.edu.agh.iosr.twitter.processor.twitter;

import pl.edu.agh.iosr.twitter.dto.assembler.PointAssembler;
import pl.edu.agh.iosr.twitter.model.Emotion;
import pl.edu.agh.iosr.twitter.model.Tweet;
import pl.edu.agh.iosr.twitter.ranker.IRanker;
import twitter4j.Status;

/**
 * Created by radek on 25.04.15.
 */
public class TweetRankProcessor extends AbstractTwitterProcessor {

    private IRanker ranker;

    @Override
    public Object doWithTweet(Status tweet, String campaignName) {
        Integer rank = ranker.rank(tweet.getText());
        String urlToTweet= "https://twitter.com/" + tweet.getUser().getScreenName() 
        	    + "/status/" + tweet.getId();
        Tweet dto = new Tweet(
                tweet.getText(),
                urlToTweet,
                PointAssembler.convert(tweet.getGeoLocation()),
                tweet.getCreatedAt(),
                Long.toString(tweet.getUser().getId()),
                Long.toString(tweet.getId()),
                Emotion.getValue(rank),
                campaignName
        );

        return dto;
    }

    public void setRanker(IRanker ranker) {
        this.ranker = ranker;
    }
}
