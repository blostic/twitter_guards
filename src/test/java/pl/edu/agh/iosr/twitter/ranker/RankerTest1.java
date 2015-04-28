package pl.edu.agh.iosr.twitter.ranker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import twitter4j.*;

import static org.junit.Assert.assertTrue;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-config.xml"})
public class RankerTest1 {

    Status tweet;
    Integer rank;

    @Autowired
    IRanker ranker;

    @Before
    public void prepare() throws TwitterException {
        Twitter twitter = RankerTestUtils.getTwitterInstance();
        Query query = new Query("IET");

        QueryResult result = twitter.search(query);
        tweet = result.getTweets().get(0);
    }


    @Test
    public void test() {
        System.out.println("RankerTest1");
        rank = ranker.rank(tweet);
        assertTrue(rank instanceof Integer);

    }
}