package pl.edu.agh.iosr.twitter.dto;

import facebook4j.GeoLocation;
import facebook4j.Place;

import java.util.Date;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
public class FacebookPostDTO {
    private String message;
    private Long createdDate;
    private String id;
    private GeoLocation geoLocation;
    private String name;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
