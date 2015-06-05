package pl.edu.agh.iosr.twitter.route.spec;

import org.apache.camel.component.facebook.FacebookComponent;
import org.apache.camel.component.facebook.config.FacebookConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import pl.edu.agh.iosr.twitter.route.CamelRoutesManager;
import pl.edu.agh.iosr.twitter.route.spec.conf.FacebookRouteConfiguration;
import pl.edu.agh.iosr.twitter.route.spec.conf.TwitterRouteConfiguration;

import java.util.Arrays;
import java.util.List;

public class FacebookRoutesManager extends CamelRoutesManager implements InitializingBean{


    @Value("${facebook.oAuthAppId}")
    private String oAuthAppId;

    @Value("${facebook.oAuthAppSecret}")
    private String oAuthAppSecret;

//    @Value("${facebook.oAuthAccessToken}")
//    private String oAuthAccessToken;

    @Value("${mongodb.dbname}")
    private String dbName;

    @Override
    public void afterPropertiesSet() throws Exception {
        FacebookComponent fb = camelContext.getComponent("facebook", FacebookComponent.class);
        FacebookConfiguration conf = new FacebookConfiguration();
        conf.setOAuthAppId(oAuthAppId);
        conf.setOAuthAppSecret(oAuthAppSecret);
//        conf.setOAuthAccessToken(oAuthAccessToken);

        fb.setConfiguration(conf);
    }

    public String addFbPostsRoute(String pageId, List<String> to, String routeName, String accessToken) {
        String from = "facebook://getPromotablePosts?pageId=" + pageId
                + "&oAuthAppId=" + oAuthAppId
                + "&oAuthAppSecret=" + oAuthAppSecret
                //+ "&oAuthAccessToken=" + (accessToken != null ? accessToken : oAuthAccessToken)
                + "&consumer.delay=5000";
        return super.addRoute(from, to, routeName);
    }

    public String addFbCommentsRoute(String postId, List<String> to, String routeName, String accessToken) {
        String from = "facebook://getPostComments?postId=" + postId
                + "&oAuthAppId=" + oAuthAppId
                + "&oAuthAppSecret=" + oAuthAppSecret
                //+ "&oAuthAccessToken=" + (accessToken != null ? accessToken : oAuthAccessToken)
                + "&consumer.delay=30000";
        return super.addRoute(from, to, routeName);
    }

    public String addFbPostsRouteWithRank(FacebookRouteConfiguration configuration){
        String ranker = "bean:" + configuration.getRankerBean();
        String dbEndpoint = "mongodb:mongoBean?database=" + dbName + "&collection=" + configuration.getDbCollection() + "&operation=insert";

        return addFbPostsRoute(configuration.getPageId(), Arrays.asList(ranker, dbEndpoint), configuration.getRouteName(), null);
    }
}
