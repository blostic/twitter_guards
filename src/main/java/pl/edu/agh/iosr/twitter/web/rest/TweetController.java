package pl.edu.agh.iosr.twitter.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.iosr.twitter.model.Tweet;

import java.util.Collection;

/**
 * Created by radoslawdyrda on 12.05.2015.
 */

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "/fromCampaign/{campaignName}")
    Collection<Tweet> getFromCampaign(@PathVariable String campaignName) {
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("campaignName").is(campaignName));
        return mongoTemplate.find(findQuery, Tweet.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{tweetId}")
    Tweet getTweet(@PathVariable String tweetId) {
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("tweet.id").is(tweetId));
        return mongoTemplate.findOne(findQuery, Tweet.class);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{tweetId}")
    void deleteTweet(@PathVariable String tweetId) {
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("tweet.id").is(tweetId));
        mongoTemplate.remove(findQuery, Tweet.class);
    }
}
