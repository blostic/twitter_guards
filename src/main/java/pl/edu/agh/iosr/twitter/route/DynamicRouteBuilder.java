package pl.edu.agh.iosr.twitter.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

import java.util.List;

/**
 * Created by radek on 30.03.15.
 */
public class DynamicRouteBuilder extends RouteBuilder {

    private final List<String> to;
    private final String from;
    private final String routeName;
    private RouteDefinition routeDefinition;

    public DynamicRouteBuilder(String from, List<String> to, String routeName){
        this.from = from;
        this.to = to;
        this.routeName = routeName;
    }

    @Override
    public void configure() throws Exception {
        routeDefinition = from(from).setHeader("routeName", constant(routeName)).to((String[])to.toArray());
    }

    public RouteDefinition getRouteDefinition() {
        return routeDefinition;
    }
}
