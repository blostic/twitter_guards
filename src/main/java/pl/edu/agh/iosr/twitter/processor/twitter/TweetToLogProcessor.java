package pl.edu.agh.iosr.twitter.processor.twitter;

import org.apache.log4j.Logger;
import twitter4j.Status;

/**
 * Created by radek on 10.04.15.
 */
public class TweetToLogProcessor extends AbstractTwitterProcessor {

    private static final Logger log = Logger.getLogger(TweetToLogProcessor.class);

    @Override
    public Object doWithTweet(Status tweet, String campaignName) {
        log.info("Tweet: " + tweet.getText());
        return null;
    }
}
