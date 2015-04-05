package pl.edu.agh.iosr.twitter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.edu.agh.iosr.twitter.route.CamelRoutesManager;

/**
 * Created by radek on 30.03.15.
 *
 * Dummy controller for test purposes only
 */

@Controller
@RequestMapping("/")
public class DummyController {

    @Autowired
    private CamelRoutesManager manager;


	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

        String from = "twitter://search?type=polling&delay=2&keywords=korwin";
        String to = "log:tweet";

        manager.addRoute(from, to);

		model.addAttribute("message", "Hello world!");
		return "hello";
	}
}