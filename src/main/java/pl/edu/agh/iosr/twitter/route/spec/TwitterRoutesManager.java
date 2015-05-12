package pl.edu.agh.iosr.twitter.route.spec;

import org.apache.camel.component.twitter.TwitterComponent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import pl.edu.agh.iosr.twitter.route.CamelRoutesManager;
import pl.edu.agh.iosr.twitter.route.spec.conf.TwitterRouteConfiguration;

import java.util.Arrays;
import java.util.List;

public class TwitterRoutesManager extends CamelRoutesManager implements InitializingBean {

    @Value("${twitter.consumerKey}")
    private String consumerKey;

    @Value("${twitter.consumerSecret}")
    private String consumerSecret;

    @Value("${twitter.accessToken}")
    private String accessToken;

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    public String addPollingTwitterRoute(String pollText, List<String> to, String routeName){
        String from = "twitter://search?type=polling&delay=2&keywords=" + pollText;
        return addRoute(from, to, routeName);
    }

    public String addPollingTwitterRouteWithRank(TwitterRouteConfiguration configuration){
        String ranker = "bean:" + configuration.getRankerBean();
        String dbEndpoint = "mongodb:mongoBean?database=twitter-guard&collection=" + configuration.getDbCollection() + "&operation=insert";
        return addPollingTwitterRoute(configuration.getPollingText(), Arrays.asList(ranker, dbEndpoint), configuration.getRouteName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        TwitterComponent tc = camelContext.getComponent("twitter", TwitterComponent.class);
        tc.setAccessToken(accessToken);
        tc.setAccessTokenSecret(accessTokenSecret);
        tc.setConsumerKey(consumerKey);
        tc.setConsumerSecret(consumerSecret);
    }

}
