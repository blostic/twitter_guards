package pl.edu.agh.iosr.twitter.route.spec.conf;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
public class FacebookRouteConfiguration extends RouteConfiguration {
    private String pageId;


    public FacebookRouteConfiguration(String routeName, String pageId, String rankerBean, String dbName, String dbCollection) {
        this.routeName = routeName;
        this.pageId = pageId;
        this.rankerBean = rankerBean;
        this.dbName = dbName;
        this.dbCollection = dbCollection;
    }

    public FacebookRouteConfiguration() {
    }

    public String getPageId() {
        return pageId;
    }
}
