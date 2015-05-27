package pl.edu.agh.iosr.twitter.ranker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-config.xml"})
public class RankerTest3 {

    @Autowired
    IRanker ranker;

    String emptyText;


    @Test(expected = IllegalArgumentException.class)
    public void test() {
        emptyText = "";
        ranker.rank(emptyText);
    }
}