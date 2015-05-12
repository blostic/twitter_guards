package pl.edu.agh.iosr.twitter.model;

import org.springframework.data.mongodb.core.mapping.Document;
import pl.edu.agh.iosr.twitter.dto.TweetDTO;
import twitter4j.Status;

import java.io.Serializable;

/**
 * Created by radek on 25.04.15.
 */

@Document(collection = "tweets")
public class Tweet implements Serializable{

    private TweetDTO tweet;
    private Integer rank;
    private String campaignName;

    public Tweet(TweetDTO tweet, Integer rank, String campaignName) {
        this.tweet = tweet;
        this.rank = rank;
        this.campaignName = campaignName;
    }

    public Tweet() {}

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public TweetDTO getTweet() {
        return tweet;
    }

    public void setTweet(TweetDTO tweet) {
        this.tweet = tweet;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
