package pl.edu.agh.iosr.twitter.processor.facebook;

import facebook4j.Post;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.iosr.twitter.dto.assembler.FacebookPostDTOAssembler;
import pl.edu.agh.iosr.twitter.model.facebook.FacebookPost;
import pl.edu.agh.iosr.twitter.ranker.IRanker;
import pl.edu.agh.iosr.twitter.route.spec.FacebookRoutesManager;

import java.util.Arrays;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
public class FacebookPostRankProcessor extends AbstractFacebookPostProcessor {

    private IRanker ranker;

    private String commentsProcessor;

    private String dbEndpoint;

    @Autowired
    private FacebookRoutesManager fbManager;

    @Override
    public Object doWithPosts(Post post, String campaignName) {
        Integer rank = ranker.rank(post.getMessage());

        FacebookPost persistencePost = new FacebookPost(FacebookPostDTOAssembler.convert(post), rank, campaignName);
        fbManager.addFbCommentsRoute(post.getId(), Arrays.asList(commentsProcessor, dbEndpoint), campaignName + "_" + post.getId(), null);

        return persistencePost;
    }

    public void setRanker(IRanker ranker) {
        this.ranker = ranker;
    }

    public void setCommentsProcessor(String commentsProcessor) {
        this.commentsProcessor = commentsProcessor;
    }

    public void setDbEndpoint(String dbEndpoint) {
        this.dbEndpoint = dbEndpoint;
    }
}
