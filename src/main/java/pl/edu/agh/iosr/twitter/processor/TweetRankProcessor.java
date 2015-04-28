package pl.edu.agh.iosr.twitter.processor;

import org.apache.camel.Message;
import pl.edu.agh.iosr.twitter.model.TweetDTO;
import pl.edu.agh.iosr.twitter.ranker.IRanker;
import twitter4j.Status;

/**
 * Created by radek on 25.04.15.
 */
public class TweetRankProcessor extends AbstractTwitterProcessor {

    private IRanker ranker;

    @Override
    public Object doWithTweet(Status tweet) {
        Integer rank = ranker.rank(tweet);
        TweetDTO dto = new TweetDTO(tweet, rank);

        return dto;
    }

    public void setRanker(IRanker ranker) {
        this.ranker = ranker;
    }
}
