package pl.edu.agh.iosr.twitter.ranker;

import org.junit.Before;
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
public class RankerTest1 {

    @Autowired
    Ranker ranker;

    String negativeText;
    String neutralText;
    String positiveText;


    @Before
    public void prepare() {
        negativeText = "I feel bad";
        neutralText = "I feel normal";
        positiveText = "I feel great";
    }


    @Test
    public void test() {
        ranker = new Ranker();
        ranker.init();

        int negativeRank = ranker.rank(negativeText);
        assertTrue(negativeRank == 1);

        int neutralRank = ranker.rank(neutralText);
        assertTrue(neutralRank == 2);

        int positiveRank = ranker.rank(positiveText);
        assertTrue(positiveRank == 3);

    }
}