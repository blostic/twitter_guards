package pl.edu.agh.iosr.twitter.processor.facebook;

import facebook4j.Comment;
import pl.edu.agh.iosr.twitter.dto.assembler.FacebookCommentDTOAssembler;
import pl.edu.agh.iosr.twitter.filter.FacebookDuplicateCommentsFilter;
import pl.edu.agh.iosr.twitter.model.Emotion;
import pl.edu.agh.iosr.twitter.model.facebook.FacebookComment;
import pl.edu.agh.iosr.twitter.ranker.IRanker;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
public class FacebookCommentRankProcessor extends AbstractFacebookCommentProcessor {

    private IRanker ranker;

    private FacebookDuplicateCommentsFilter filter;

    @Override
    protected Object doWithComment(Comment comment, String campaignName) {
        Integer rank = ranker.rank(comment.getMessage());

        String postId = comment.getId().split("_")[0];

        if(filter.isCommentDuplicated(comment)){
            return null;
        }

        FacebookComment persistenceComment = new FacebookComment(
                Emotion.getValue(rank),
                postId,
                campaignName,
                comment.getId(),
                comment.getLikeCount(),
                comment.getMessage(),
                comment.getCreatedTime());

        return persistenceComment;
    }

    public void setRanker(IRanker ranker) {
        this.ranker = ranker;
    }

    public void setFilter(FacebookDuplicateCommentsFilter filter) {
        this.filter = filter;
    }
}
