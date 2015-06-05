package pl.edu.agh.iosr.twitter.model.facebook;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.edu.agh.iosr.twitter.dto.FacebookCommentDTO;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
@Document(collection = "facebookComments")
public class FacebookComment implements Serializable {

    @Id
    private ObjectId id;

    private Integer rank;
    private String postId;
    private String capaignName;
    private String commentId;
    private Integer likeCount;
    private String message;
    private Date creationDate;

    public FacebookComment(Integer rank, String postId, String capaignName, String id, Integer likeCount, String message, Date creationDate) {
        this.rank = rank;
        this.postId = postId;
        this.capaignName = capaignName;
        this.commentId = id;
        this.likeCount = likeCount;
        this.message = message;
        this.creationDate = creationDate;
    }

    public FacebookComment() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
