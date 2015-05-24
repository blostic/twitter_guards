package pl.edu.agh.iosr.twitter.dto;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
public class FacebookCommentDTO {
    private String id;
    private Integer likeCount;
    private String message;
    private Long creationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }
}
