package pl.edu.agh.iosr.twitter.web.rest;

/**
 * Created by radoslawdyrda on 12.05.2015.
 */
public class TwitterRouteConfigurationCommand {

    private String camapignName;

    private String pollingText;

    public String getCamapignName() {
        return camapignName;
    }

    public void setCamapignName(String camapignName) {
        this.camapignName = camapignName;
    }

    public String getPollingText() {
        return pollingText;
    }

    public void setPollingText(String pollingText) {
        this.pollingText = pollingText;
    }
}
