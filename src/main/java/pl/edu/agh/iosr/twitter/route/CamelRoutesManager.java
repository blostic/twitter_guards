package pl.edu.agh.iosr.twitter.route;

import org.apache.camel.CamelException;
import org.apache.camel.component.twitter.TwitterComponent;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.spring.SpringCamelContext;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.SetUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

/**
 * Created by radek on 30.03.15.
 *
 * This component allows to add routes dynamically
 */

public class CamelRoutesManager {

    private static final Logger log = Logger.getLogger(CamelRoutesManager.class);

    @Autowired
    @Qualifier("camelContext")
    protected SpringCamelContext camelContext;

    private Map<String, RouteDefinition> activeRoutes = new HashMap<>();

    public String addRoute(String from, List<String> to, String routeName){
        DynamicRouteBuilder rtbuilder = new DynamicRouteBuilder(from, to, routeName);
        log.info("Adding new route " + from);
        try {
            camelContext.addRoutes(rtbuilder);

        } catch (Exception e) {
            log.warn("Route build failed", e);
            log.warn("Avail routes:" + camelContext.getRouteDefinitions());
        }

        String routeId = rtbuilder.getRouteDefinition().getId();
        activeRoutes.put(routeId, rtbuilder.getRouteDefinition());

        return routeId;
    }

    public Collection<String> getRoutes(){
        return activeRoutes.keySet();
    }

    public void deleteRoute(String routeId) throws CamelException {
        if(!getRoutes().contains(routeId)){
            throw new CamelException(String.format("Route with %s not found.", routeId));
        } else {
            try {
                camelContext.stopRoute(routeId);
                camelContext.removeRoute(routeId);
                activeRoutes.remove(routeId);
            } catch (Exception e) {
                throw new CamelException(e);
            }

        }
    }

    public void stopRoute(String routeId) throws CamelException {
        if(!getRoutes().contains(routeId)){
            throw new CamelException(String.format("Route with %s not found.", routeId));
        } else {
            try {
                camelContext.stopRoute(routeId);
            } catch (Exception e) {
                throw new CamelException(e);
            }

        }
    }

    public void startRoute(String routeId) throws CamelException {
        if(!getRoutes().contains(routeId)){
            throw new CamelException(String.format("Route with %s not found.", routeId));
        } else {
            try {
                camelContext.startRoute(routeId);
            } catch (Exception e) {
                throw new CamelException(e);
            }

        }
    }


}
