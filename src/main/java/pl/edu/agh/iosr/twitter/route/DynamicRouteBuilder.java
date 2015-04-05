package pl.edu.agh.iosr.twitter.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by radek on 30.03.15.
 */
public class DynamicRouteBuilder extends RouteBuilder {

    private final String to;
    private final String from;

    public DynamicRouteBuilder(String from, String to){
        this.from = from;
        this.to = to;
    }

    @Override
    public void configure() throws Exception {
        from(from).to(to);
    }
}
