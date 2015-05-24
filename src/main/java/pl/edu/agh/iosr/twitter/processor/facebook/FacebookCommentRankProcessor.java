package pl.edu.agh.iosr.twitter.processor.facebook;

import facebook4j.Comment;
import pl.edu.agh.iosr.twitter.dto.assembler.FacebookCommentDTOAssembler;
import pl.edu.agh.iosr.twitter.model.facebook.FacebookComment;
import pl.edu.agh.iosr.twitter.ranker.IRanker;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
public class FacebookCommentRankProcessor extends AbstractFacebookCommentProcessor {

    private IRanker ranker;

    @Override
    protected Object doWithComment(Comment comment, String campaignName) {
        Integer rank = ranker.rank(comment.getMessage());

        String postId = comment.getId().split("_")[0];

        FacebookComment persistenceComment = new FacebookComment(FacebookCommentDTOAssembler.convert(comment), rank, postId, campaignName);

        return persistenceComment;
    }

    public void setRanker(IRanker ranker) {
        this.ranker = ranker;
    }
}
