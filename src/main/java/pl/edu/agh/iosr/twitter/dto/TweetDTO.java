package pl.edu.agh.iosr.twitter.dto;

import twitter4j.GeoLocation;
import twitter4j.User;

import java.util.Date;

/**
 * Created by radoslawdyrda on 11.05.2015.
 */
public class TweetDTO {
    private String text;

    private GeoLocation location;

    private Long id;

    private Long createdAt;

    public TweetDTO(String text, GeoLocation location, Long id, Long createdAt, User user) {
        this.text = text;
        this.location = location;
        this.id = id;
        this.createdAt = createdAt;
    }

    public TweetDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

}
