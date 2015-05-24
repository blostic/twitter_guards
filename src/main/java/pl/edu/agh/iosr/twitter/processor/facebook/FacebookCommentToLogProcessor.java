package pl.edu.agh.iosr.twitter.processor.facebook;


import facebook4j.Comment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.iosr.twitter.route.spec.FacebookRoutesManager;

public class FacebookCommentToLogProcessor extends AbstractFacebookCommentProcessor{

    private static final Logger log = Logger.getLogger(FacebookCommentToLogProcessor.class);

    @Override
    protected Object doWithComment(Comment comment, String campaignName) {
        log.info(comment.getMessage());
        return null;
    }
}
