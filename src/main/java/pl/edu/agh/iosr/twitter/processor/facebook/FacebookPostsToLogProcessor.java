package pl.edu.agh.iosr.twitter.processor.facebook;

import facebook4j.Post;
import facebook4j.ResponseList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.iosr.twitter.route.spec.FacebookRoutesManager;

import java.util.Arrays;

/**
 * Created by radoslawdyrda on 11.05.2015.
 */
public class FacebookPostsToLogProcessor extends AbstractFacebookPostProcessor {

    private static final Logger log = Logger.getLogger(FacebookPostsToLogProcessor.class);

    @Autowired
    private FacebookRoutesManager fbManager;

    @Override
    public Object doWithPosts(Post post, String campaignName) {
        log.info("FB POST: " + post.getMessage());
        fbManager.addFbCommentsRoute(post.getId(), Arrays.asList("bean:fbCommentsToLogProcessor"), campaignName, null);
        return null;
    }
}
