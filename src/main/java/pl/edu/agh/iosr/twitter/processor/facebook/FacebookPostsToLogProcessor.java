package pl.edu.agh.iosr.twitter.processor.facebook;

import facebook4j.Post;
import facebook4j.ResponseList;
import org.apache.log4j.Logger;

/**
 * Created by radoslawdyrda on 11.05.2015.
 */
public class FacebookPostsToLogProcessor extends AbstractFacebookProcessor {

    private static final Logger log = Logger.getLogger(FacebookPostsToLogProcessor.class);

    @Override
    public Object doWithPosts(ResponseList<Post> posts, String campaignName) {
        for(Post p : posts){
            log.info("FB POST: " + p.getMessage());
        }
        return null;
    }
}
