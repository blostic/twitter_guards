package pl.edu.agh.iosr.twitter.model;


import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by radoslawdyrda on 28.04.2015.
 */
@Document(collection = "campaigns")
public class Campaign implements Serializable{
    private String name;

    //TODO query to auto add routes on server start
    private String query;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
