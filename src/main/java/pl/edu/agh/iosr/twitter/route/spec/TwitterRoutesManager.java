package pl.edu.agh.iosr.twitter.route.spec;

import org.apache.camel.CamelException;
import org.apache.camel.component.twitter.TwitterComponent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import pl.edu.agh.iosr.twitter.model.TwitterCampaign;
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

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${mongodb.dbname}")
    private String dbName;

    public String addPollingTwitterRoute(String pollText, List<String> to, String routeName){
        String from = "twitter://search?type=polling&delay=2&keywords=" + pollText;
        return addRoute(from, to, routeName);
    }

    public String addPollingTwitterRouteWithRank(TwitterRouteConfiguration configuration){
        String ranker = "bean:" + configuration.getRankerBean();
        String dbEndpoint = "mongodb:mongoBean?database=" + dbName + "&collection=" + configuration.getDbCollection() + "&operation=insert";
        mongoTemplate.insert(new TwitterCampaign(configuration.getRouteName(), configuration));
        return addPollingTwitterRoute(configuration.getPollingText(), Arrays.asList(ranker, dbEndpoint), configuration.getRouteName() + "_" + configuration.getPollingText());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        TwitterComponent tc = camelContext.getComponent("twitter", TwitterComponent.class);
        tc.setAccessToken(accessToken);
        tc.setAccessTokenSecret(accessTokenSecret);
        tc.setConsumerKey(consumerKey);
        tc.setConsumerSecret(consumerSecret);
    }

    @Override
    public void deleteRoute(String routeId) throws CamelException {
        super.deleteRoute(routeId);

        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("name").is(routeId));

        mongoTemplate.remove(findQuery, TwitterCampaign.class);
    }

    @Override
    public void stopRoute(String routeId) throws CamelException {
        super.stopRoute(routeId);
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("name").is(routeId));

        Update update = new Update();
        update.set("status", "stopped");

        mongoTemplate.updateFirst(findQuery, update, TwitterCampaign.class);
    }

    @Override
    public void startRoute(String routeId) throws CamelException {
        super.startRoute(routeId);

        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("name").is(routeId));

        Update update = new Update();
        update.set("status", "working");

        mongoTemplate.updateFirst(findQuery, update, TwitterCampaign.class);
    }
}
