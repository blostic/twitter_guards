package pl.edu.agh.iosr.twitter.model;

import twitter4j.Status;

import java.io.Serializable;

/**
 * Created by radek on 25.04.15.
 */
public class TweetDTO implements Serializable{

    private Status tweet;
    private Integer rank;

    public TweetDTO(Status tweet, Integer rank) {
        this.tweet = tweet;
        this.rank = rank;
    }

    public Status getTweet() {
        return tweet;
    }

    public void setTweet(Status tweet) {
        this.tweet = tweet;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
