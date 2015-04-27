package pl.edu.agh.iosr.twitter.route;

import org.apache.camel.component.twitter.TwitterComponent;
import org.apache.camel.spring.SpringCamelContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by radek on 30.03.15.
 *
 * This component allows to add routes dynamically
 */

public class CamelRoutesManager implements InitializingBean {

    private static final Logger log = Logger.getLogger(CamelRoutesManager.class);

    @Autowired
    @Qualifier("camelContext")
    private SpringCamelContext camelContext;


    @Value("${twitter.consumerKey}")
    private String consumerKey;

    @Value("${twitter.consumerSecret}")
    private String consumerSecret;

    @Value("${twitter.accessToken}")
    private String accessToken;

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    public void addRoute(String from, List<String> to){
        DynamicRouteBuilder rtbuilder = new DynamicRouteBuilder(from, to);
        log.info("Adding new route " + from);
        try {
            camelContext.addRoutes(rtbuilder);

        } catch (Exception e) {
            log.warn("Route build failed", e);
            log.info("Avail routes:" + camelContext.getRouteDefinitions());
        }
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
