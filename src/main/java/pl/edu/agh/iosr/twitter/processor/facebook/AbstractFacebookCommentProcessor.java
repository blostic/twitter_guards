package pl.edu.agh.iosr.twitter.processor.facebook;

import facebook4j.Comment;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
public abstract class AbstractFacebookCommentProcessor implements Processor {

    protected abstract Object doWithComment(Comment comment, String campaignName);

    @Override
    public void process(Exchange exchange) throws Exception {
        Comment comment = exchange.getIn().getBody(Comment.class);
        String campaignName = exchange.getIn().getHeader("routeName", String.class);

        exchange.getOut().setBody(doWithComment(comment, campaignName));

    }
}
