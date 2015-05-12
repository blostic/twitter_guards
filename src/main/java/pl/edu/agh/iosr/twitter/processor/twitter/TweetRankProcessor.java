package pl.edu.agh.iosr.twitter.processor.twitter;

import pl.edu.agh.iosr.twitter.dto.assembler.TweetDTOAssembler;
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
        Integer rank = ranker.rank(tweet);
        Tweet dto = new Tweet(TweetDTOAssembler.convert(tweet), rank, campaignName);

        return dto;
    }

    public void setRanker(IRanker ranker) {
        this.ranker = ranker;
    }
}
