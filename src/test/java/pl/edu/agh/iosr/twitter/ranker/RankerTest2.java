package pl.edu.agh.iosr.twitter.ranker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import twitter4j.Status;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-config.xml"})
public class RankerTest2 {

    Status tweet = null;

    @Autowired
    IRanker ranker;

    @Test(expected = IllegalArgumentException.class)
    public void testSalutationMessage() {
        System.out.println("RankerTest2");
        ranker.rank(tweet);
    }
}