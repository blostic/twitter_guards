package pl.edu.agh.iosr.twitter.model;

import org.springframework.data.mongodb.core.mapping.Document;
import twitter4j.Status;

import java.io.Serializable;

/**
 * Created by radek on 25.04.15.
 */

@Document(collection = "tweets")
public class TweetDTO implements Serializable{

    private Status tweet;
    private Integer rank;
    private String campaignName;

    public TweetDTO(Status tweet, Integer rank, String campaignName) {
        this.tweet = tweet;
        this.rank = rank;
        this.campaignName = campaignName;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
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
