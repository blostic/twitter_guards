package pl.edu.agh.iosr.twitter.model;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.geo.Point;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by radek on 25.04.15.
 */

@Document(collection = "Tweet")
public class Tweet implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    private ObjectId id;

    private String text;
    private Point position;
    private Date creationTime;
    private String userId; // user.id_str
    private String tweeterTweetId; // id_str
    private Emotion emotion;
    private String routeName;
	private String url;

    public Tweet(String text, String url, Point position, Date creationTime, String userId, String tweeterTweetId, Emotion emotion, String routeName) {
        this.text = text;
        this.url = url;
        this.position = position;
        this.creationTime = creationTime;
        this.userId = userId;
        this.tweeterTweetId = tweeterTweetId;
        this.emotion = emotion;
        this.routeName = routeName;
    }

    public Tweet() {}

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTweeterTweetId() {
        return tweeterTweetId;
    }

    public void setTweeterTweetId(String tweeterTweetId) {
        this.tweeterTweetId = tweeterTweetId;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
