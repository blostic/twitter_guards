package persistance.campaign.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.geo.Polygon;

import persistance.tweets.entity.Tweet;

@Entity(noClassnameStored = true)
public class Campaign {

	@Id
	private ObjectId id;

	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	
	private ObjectId userId;
	
	private List<String> keywords;
	private Polygon polygon;
	private Map<Long, Tweet> timeToTweetMap = new HashMap<Long, Tweet>();
	
	private boolean isTwitterCampaign;
	
	private List<String> facebookProfiles;
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	public Map<Long, Tweet> getTimeToTweetMap() {
		return timeToTweetMap;
	}

	public void setTimeToTweetMap(Map<Long, Tweet> timeToTweetMap) {
		this.timeToTweetMap = timeToTweetMap;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", title=" + title + ", description="
				+ description + ", startDate=" + startDate + ", endDate="
				+ endDate + ", keywords=" + keywords + ", polygon=" + polygon
				+ ", timeToTweetMap=" + timeToTweetMap + "]";
	}

	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	public List<String> getFacebookProfiles() {
		return facebookProfiles;
	}

	public void setFacebookProfiles(List<String> facebookProfiles) {
		this.facebookProfiles = facebookProfiles;
	}

	public boolean isTwitterCampaign() {
		return isTwitterCampaign;
	}

	public void setTwitterCampaign(boolean isTwitterCampaign) {
		this.isTwitterCampaign = isTwitterCampaign;
	}

}
