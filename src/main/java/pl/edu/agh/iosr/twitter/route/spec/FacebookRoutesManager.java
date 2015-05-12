package pl.edu.agh.iosr.twitter.route.spec;

import org.apache.camel.component.facebook.FacebookComponent;
import org.apache.camel.component.facebook.config.FacebookConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import pl.edu.agh.iosr.twitter.route.CamelRoutesManager;

public class FacebookRoutesManager extends CamelRoutesManager implements InitializingBean{


    @Value("${facebook.oAuthAppId}")
    private String oAuthAppId;

    @Value("${facebook.oAuthAppSecret}")
    private String oAuthAppSecret;

    @Value("${facebook.oAuthAccessToken}")
    private String oAuthAccessToken;

    @Override
    public void afterPropertiesSet() throws Exception {
        FacebookComponent fb = camelContext.getComponent("facebook", FacebookComponent.class);
        FacebookConfiguration conf = new FacebookConfiguration();
        conf.setOAuthAppId(oAuthAppId);
        conf.setOAuthAppSecret(oAuthAppSecret);
        conf.setOAuthAccessToken(oAuthAccessToken);

        fb.setConfiguration(conf);
    }
}
