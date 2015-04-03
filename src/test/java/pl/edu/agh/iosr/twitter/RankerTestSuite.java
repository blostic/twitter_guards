package pl.edu.agh.iosr.twitter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.edu.agh.iosr.twitter.ranker.RankerTest1;
import pl.edu.agh.iosr.twitter.ranker.RankerTest2;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RankerTest1.class,
        RankerTest2.class
})
public class RankerTestSuite {
}