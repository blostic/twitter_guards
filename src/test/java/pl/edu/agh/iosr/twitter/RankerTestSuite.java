package pl.edu.agh.iosr.twitter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.edu.agh.iosr.twitter.ranker.Ranker1Test;
import pl.edu.agh.iosr.twitter.ranker.Ranker2Test;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Ranker1Test.class,
        Ranker2Test.class
})
public class RankerTestSuite {
}