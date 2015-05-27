package pl.edu.agh.iosr.twitter.ranker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-config.xml"})
public class Ranker1Test {

    @Autowired
    IRanker ranker;

    String superNegativeText;
    String negativeText;
    String neutralText;
    String positiveText;
    String superPositiveText;


    @Before
    public void prepare() {
        superNegativeText = "I feel very terrible and depressed";
        negativeText = "I feel bad";
        neutralText = "I feel normal";
        positiveText = "I feel great";
        superPositiveText = "I fill like it's the most wonderful day of my life and I'm very happy.";
    }

    @Test
    public void test() {

        //test super negative sentence
        assertEquals(0, ranker.rank(superNegativeText));

        //test negative sentence
        assertEquals(1, ranker.rank(negativeText));

        //test neutral sentence
        assertEquals(2, ranker.rank(neutralText));

        //test positive sentence
        assertEquals(3, ranker.rank(positiveText));

        //test super positive sentence
        assertEquals(4, ranker.rank(superPositiveText));

    }
}