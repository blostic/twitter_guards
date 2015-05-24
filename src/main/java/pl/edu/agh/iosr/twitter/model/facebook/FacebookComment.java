package pl.edu.agh.iosr.twitter.model.facebook;

import org.springframework.data.mongodb.core.mapping.Document;
import pl.edu.agh.iosr.twitter.dto.FacebookCommentDTO;

import java.io.Serializable;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
@Document(collection = "facebookComments")
public class FacebookComment implements Serializable {
    private FacebookCommentDTO comment;
    private Integer rank;
    private String postId;
    private String capaignName;

    public FacebookComment(FacebookCommentDTO comment, Integer rank, String postId, String capaignName) {
        this.comment = comment;
        this.rank = rank;
        this.postId = postId;
        this.capaignName = capaignName;
    }

    public FacebookComment() {
    }

    public FacebookCommentDTO getComment() {
        return comment;
    }

    public void setComment(FacebookCommentDTO comment) {
        this.comment = comment;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCapaignName() {
        return capaignName;
    }

    public void setCapaignName(String capaignName) {
        this.capaignName = capaignName;
    }
}
