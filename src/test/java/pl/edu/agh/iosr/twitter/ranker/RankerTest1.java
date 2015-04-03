package pl.edu.agh.iosr.twitter.ranker;

import org.junit.Before;
import org.junit.Test;
import twitter4j.*;

import static org.junit.Assert.assertTrue;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */

public class RankerTest1 {

    Status tweet;
    Integer rank;

    @Before
    public void prepare() throws TwitterException {
        Twitter twitter = RankerUtils.getTwitterInstance();
        Query query = new Query("IET");

        QueryResult result = twitter.search(query);
        tweet = result.getTweets().get(0);
    }


    @Test
    public void test() {
        System.out.println("RankerTest1");
        rank = Ranker.rank(tweet);
        assertTrue(rank instanceof Integer);

    }
}