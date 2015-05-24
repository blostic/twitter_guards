package pl.edu.agh.iosr.twitter.route.spec.conf;


import java.io.Serializable;

public class TwitterRouteConfiguration extends RouteConfiguration {

    private String pollingText;

    public TwitterRouteConfiguration(String routeName, String pollingText, String rankerBean, String dbName, String dbCollection) {
        this.routeName = routeName;
        this.pollingText = pollingText;
        this.rankerBean = rankerBean;
        this.dbName = dbName;
        this.dbCollection = dbCollection;
    }

    public TwitterRouteConfiguration() {
    }

    public String getPollingText() {
        return pollingText;
    }

}
