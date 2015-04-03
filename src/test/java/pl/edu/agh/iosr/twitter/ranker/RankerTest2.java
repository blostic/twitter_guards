package pl.edu.agh.iosr.twitter.ranker;

import org.junit.Test;
import twitter4j.Status;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */

public class RankerTest2 {

    Status tweet = null;

    @Test(expected = IllegalArgumentException.class)
    public void testSalutationMessage() {
        System.out.println("RankerTest2");
        Ranker.rank(tweet);
    }
}