package pl.edu.agh.iosr.twitter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.edu.agh.iosr.twitter.model.Tweet;
import pl.edu.agh.iosr.twitter.route.spec.FacebookRoutesManager;
import pl.edu.agh.iosr.twitter.route.spec.TwitterRoutesManager;
import pl.edu.agh.iosr.twitter.route.spec.conf.TwitterRouteConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by radek on 30.03.15.
 *
 * Dummy controller for test purposes only
 */

@Controller
@RequestMapping("/twitter")
public class DummyController {

    @Autowired
    private TwitterRoutesManager manager;

    @Autowired
    private FacebookRoutesManager fbManager;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "view")
    public String printWelcome(ModelMap model) {

        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("campaignName").is("agh"));
        List<Tweet> tweets = mongoTemplate.find(findQuery, Tweet.class);

        model.addAttribute("tweets", tweets);

        return "dummy";
    }

	@RequestMapping(method = RequestMethod.GET, value = "addTwitterRoute")
	public String addRoute(ModelMap model) {

        manager.addPollingTwitterRouteWithRank(new TwitterRouteConfiguration("agh", "agh", "tweetRankProcessor", null, "tweets"));

		return printWelcome(model);
	}

    @RequestMapping(method = RequestMethod.GET, value = "addFBRoute")
    public String addFBRoute(ModelMap model) {

        String fb = "facebook://searchPosts?query=agh&reading.limit=10&consumer.delay=5000";
        fbManager.addRoute(fb, Arrays.asList("bean:fbToLogProcessor"), "agh");
        return printWelcome(model);

    }
}