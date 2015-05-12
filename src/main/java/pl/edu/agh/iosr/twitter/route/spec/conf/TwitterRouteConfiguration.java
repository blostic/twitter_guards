package pl.edu.agh.iosr.twitter.route.spec.conf;


public class TwitterRouteConfiguration {

    private String routeName;

    private String pollingText;

    private String rankerBean;

    private String dbName;

    private String dbCollection;

    public TwitterRouteConfiguration(String routeName, String pollingText, String rankerBean, String dbName, String dbCollection) {
        this.routeName = routeName;
        this.pollingText = pollingText;
        this.rankerBean = rankerBean;
        this.dbName = dbName;
        this.dbCollection = dbCollection;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getPollingText() {
        return pollingText;
    }

    public String getRankerBean() {
        return rankerBean;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbCollection() {
        return dbCollection;
    }
}
