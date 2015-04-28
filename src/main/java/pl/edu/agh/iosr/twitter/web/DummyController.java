package pl.edu.agh.iosr.twitter.web;

import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.edu.agh.iosr.twitter.route.CamelRoutesManager;

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
    private CamelRoutesManager manager;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        String from = "twitter://search?type=polling&delay=2&keywords=korwin";

        List<String> to = Arrays.asList("bean:tweetRankProcessor", "mongodb:mongoBean?database=twitter-guard&collection=tweets&operation=insert");

        manager.addRoute(from, to, "testJKM");

        model.addAttribute("message", "Hello world!");
		return "hello";
	}
}