package pl.edu.agh.iosr.twitter.web.rest;

import java.util.List;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
public class FacebookRouteConfigurationCommand {

    private String camapignName;

    private List<String> pageIds;

    public String getCamapignName() {
        return camapignName;
    }

    public void setCamapignName(String camapignName) {
        this.camapignName = camapignName;
    }

    public List<String> getPageIds() {
        return pageIds;
    }

    public void setPageIds(List<String> pageIds) {
        this.pageIds = pageIds;
    }
}
