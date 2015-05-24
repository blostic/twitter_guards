package pl.edu.agh.iosr.twitter.dto.assembler;

import facebook4j.GeoLocation;
import facebook4j.Post;
import pl.edu.agh.iosr.twitter.dto.FacebookPostDTO;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
public class FacebookPostDTOAssembler {

    public static FacebookPostDTO convert(Post post){
        FacebookPostDTO dto = new FacebookPostDTO();

        dto.setId(post.getId());
        dto.setMessage(post.getMessage());
        dto.setName(post.getName());
        dto.setCreatedDate(post.getCreatedTime().getTime());
//        dto.setGeoLocation(new GeoLocation(post.getPlace().getLocation().getLatitude(), post.getPlace().getLocation().getLongitude()));

        return dto;
    }
}
