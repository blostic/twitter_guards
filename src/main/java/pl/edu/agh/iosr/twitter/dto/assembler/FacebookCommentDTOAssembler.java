package pl.edu.agh.iosr.twitter.dto.assembler;

import facebook4j.Comment;
import pl.edu.agh.iosr.twitter.dto.FacebookCommentDTO;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
public class FacebookCommentDTOAssembler {

    public static FacebookCommentDTO convert(Comment comment){
        FacebookCommentDTO dto = new FacebookCommentDTO();

        dto.setId(comment.getId());
        dto.setCreationDate(comment.getCreatedTime().getTime());
        dto.setMessage(comment.getMessage());
        dto.setLikeCount(comment.getLikeCount());

        return dto;
    }
}
