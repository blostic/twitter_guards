package pl.edu.agh.iosr.twitter.model.facebook;

import org.springframework.data.mongodb.core.mapping.Document;
import pl.edu.agh.iosr.twitter.dto.FacebookPostDTO;

import java.io.Serializable;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
@Document(collection = "facebookPosts")
public class FacebookPost implements Serializable {

    private FacebookPostDTO post;

    private Integer rank;

    private String campaignName;

    public FacebookPost(FacebookPostDTO post, Integer rank, String campaignName) {
        this.post = post;
        this.rank = rank;
        this.campaignName = campaignName;
    }

    public FacebookPost() {
    }

    public FacebookPostDTO getPost() {
        return post;
    }

    public void setPost(FacebookPostDTO post) {
        this.post = post;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }
}
