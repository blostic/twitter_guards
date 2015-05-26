package pl.edu.agh.iosr.twitter.web.rest;

import java.util.List;

/**
 * Created by radoslawdyrda on 12.05.2015.
 */
public class TwitterRouteConfigurationCommand {

    private String camapignName;

    private List<String> pollingText;

    public String getCamapignName() {
        return camapignName;
    }

    public void setCamapignName(String camapignName) {
        this.camapignName = camapignName;
    }

    public List<String> getPollingText() {
        return pollingText;
    }

    public void setPollingText(List<String> pollingText) {
        this.pollingText = pollingText;
    }
}
