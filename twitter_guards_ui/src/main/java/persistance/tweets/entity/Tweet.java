package persistance.tweets.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.geo.Point;

@Entity(noClassnameStored = true)
public class Tweet {

	@Id
	private ObjectId id;

	private String text;
	private Point position;
	private Date creationTime;
	private String userId; // user.id_str
	private String tweeterTweetId; // id_str
	private Emotion emotion; 
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

}
