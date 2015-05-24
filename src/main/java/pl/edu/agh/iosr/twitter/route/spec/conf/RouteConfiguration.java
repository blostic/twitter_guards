package pl.edu.agh.iosr.twitter.route.spec.conf;

import java.io.Serializable;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
public class RouteConfiguration implements Serializable {
    protected String routeName;
    protected String rankerBean;
    protected String dbName;
    protected String dbCollection;

    public String getRouteName() {
        return routeName;
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
