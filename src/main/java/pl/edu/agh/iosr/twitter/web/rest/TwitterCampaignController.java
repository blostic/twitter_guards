package pl.edu.agh.iosr.twitter.web.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.iosr.twitter.model.TwitterCampaign;
import pl.edu.agh.iosr.twitter.route.spec.TwitterRoutesManager;
import pl.edu.agh.iosr.twitter.route.spec.conf.TwitterRouteConfiguration;

import java.util.Collection;

/**
 * Created by radoslawdyrda on 12.05.2015.
 */

@RestController
@RequestMapping("/twitterCampaigns")
public class TwitterCampaignController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TwitterRoutesManager manager;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    Collection<TwitterCampaign> getAll() {
        return mongoTemplate.findAll(TwitterCampaign.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{campaignName}")
    TwitterCampaign get(@PathVariable String campaignName){
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("name").is(campaignName));

        return mongoTemplate.findOne(findQuery, TwitterCampaign.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    ResponseEntity<?> addCampaign(@RequestBody TwitterRouteConfigurationCommand configuration){
        if(StringUtils.isEmpty(configuration.getCamapignName())){
            return new ResponseEntity<>("Campaign name not provided", HttpStatus.BAD_REQUEST);
        }
        if(configuration.getPollingText().isEmpty()){
            return new ResponseEntity<>("Pooling text not provided", HttpStatus.BAD_REQUEST);
        }

        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("name").is(configuration.getCamapignName()));

        if(mongoTemplate.exists(findQuery, TwitterCampaign.class)){
            return new ResponseEntity<>("Campaign with given name already exists", HttpStatus.BAD_REQUEST);
        }

        for(String pollingText : configuration.getPollingText()) {

            TwitterRouteConfiguration routeConfiguration = new TwitterRouteConfiguration(
                    configuration.getCamapignName(),
                    pollingText,
                    "tweetRankProcessor", null, "Tweet"
            );

            manager.addPollingTwitterRouteWithRank(routeConfiguration);
        }

        return ResponseEntity.ok().build();

    }
}
