package pl.edu.agh.iosr.twitter.model;


import org.springframework.data.mongodb.core.mapping.Document;
import pl.edu.agh.iosr.twitter.route.spec.conf.TwitterRouteConfiguration;

import java.io.Serializable;

/**
 * Created by radoslawdyrda on 28.04.2015.
 */
@Document(collection = "twitterCampaigns")
public class TwitterCampaign implements Serializable{
    private String name;

    //TODO query to auto add routes on server start
    private TwitterRouteConfiguration configuration;

    private String status;

    public String getName() {
        return name;
    }

    public TwitterCampaign(String name, TwitterRouteConfiguration configuration) {
        this.name = name;
        this.configuration = configuration;
        this.status = "initial";
    }

    public TwitterCampaign() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public TwitterRouteConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(TwitterRouteConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
