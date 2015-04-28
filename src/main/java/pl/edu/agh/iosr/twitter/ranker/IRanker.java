package pl.edu.agh.iosr.twitter.ranker;

import twitter4j.Status;

/**
 * Created by radek on 25.04.15.
 */
public interface IRanker {
    int rank(Status tweet);
}
