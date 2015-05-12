package pl.edu.agh.iosr.twitter.processor.facebook;

import facebook4j.Post;
import facebook4j.ResponseList;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by radoslawdyrda on 11.05.2015.
 */
public abstract class AbstractFacebookProcessor implements Processor {

    public abstract Object doWithPosts(ResponseList<Post> posts, String campaignName);

    @Override
    public void process(Exchange exchange) throws Exception {
        ResponseList<Post> posts = exchange.getIn().getBody(ResponseList.class);
        String campaignName = exchange.getIn().getHeader("routeName", String.class);

        exchange.getOut().setBody(doWithPosts(posts, campaignName));

    }
}
