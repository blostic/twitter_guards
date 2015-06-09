package pl.edu.agh.iosr.twitter.web.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.iosr.twitter.model.TwitterCampaign;
import pl.edu.agh.iosr.twitter.route.spec.FacebookRoutesManager;
import pl.edu.agh.iosr.twitter.route.spec.conf.FacebookRouteConfiguration;
import pl.edu.agh.iosr.twitter.route.spec.conf.TwitterRouteConfiguration;

/**
 * Created by radoslawdyrda on 24.05.2015.
 */
@RestController
@RequestMapping("/facebookCampaigns")
public class FacebookCampaignController {

    @Autowired
    private FacebookRoutesManager manager;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    ResponseEntity<?> addCampaign(@RequestBody FacebookRouteConfigurationCommand configuration){
        if(StringUtils.isEmpty(configuration.getCamapignName())){
            return new ResponseEntity<>("Campaign name not provided", HttpStatus.BAD_REQUEST);
        }
        if(configuration.getPageIds() == null || configuration.getPageIds().isEmpty()){
            return new ResponseEntity<>("Page Id not provided", HttpStatus.BAD_REQUEST);
        }

        for(String pageId : configuration.getPageIds()) {
            FacebookRouteConfiguration routeConfiguration = new FacebookRouteConfiguration(
                    configuration.getCamapignName() + "_" + pageId,
                    pageId,
                    "fbPostRankProcessor", null, "facebookPosts"
            );

            manager.addFbPostsRouteWithRank(routeConfiguration);
        }

        return ResponseEntity.ok().build();

    }

}
