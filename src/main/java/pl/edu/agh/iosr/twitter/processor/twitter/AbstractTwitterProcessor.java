package pl.edu.agh.iosr.twitter.processor.twitter;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import twitter4j.Status;

/**
 * Created by radek on 10.04.15.
 */
public abstract class AbstractTwitterProcessor implements Processor {

    public abstract Object doWithTweet(Status tweet, String campaignName);

    @Override
    public void process(Exchange exchange) throws Exception {
        Status tweet = exchange.getIn().getBody(Status.class);
        String campaignName = exchange.getIn().getHeader("routeName", String.class);

        exchange.getOut().setBody(doWithTweet(tweet, campaignName));
    }
}
