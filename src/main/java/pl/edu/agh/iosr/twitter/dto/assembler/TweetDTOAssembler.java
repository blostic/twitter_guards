package pl.edu.agh.iosr.twitter.dto.assembler;

import pl.edu.agh.iosr.twitter.dto.TweetDTO;
import twitter4j.Status;

/**
 * Created by radoslawdyrda on 11.05.2015.
 */
public class TweetDTOAssembler {

    public static TweetDTO convert(Status status){
        TweetDTO tweetDTO = new TweetDTO();

        tweetDTO.setCreatedAt(status.getCreatedAt().getTime());
        tweetDTO.setText(status.getText());
        tweetDTO.setId(status.getId());
        tweetDTO.setLocation(status.getGeoLocation());

        return tweetDTO;
    }
}
