package pl.edu.agh.iosr.twitter.route;

import org.apache.camel.*;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration(locations = "/spring-test-config.xml")
public class DynamicRoutesManagerTest {

    @Autowired
    protected CamelRoutesManager manager;

    @Autowired
    protected SpringCamelContext camelContext;

    @EndpointInject(uri = "mock:mockE1")
    protected MockEndpoint mockE1;

    @EndpointInject(uri = "mock:mockE2")
    protected MockEndpoint mockE2;

    @Produce(uri = "direct:send")
    protected ProducerTemplate template;

    @Test
    @DirtiesContext
    public void testAddRoute() throws InterruptedException {
        String testStr = "camel-test";

        mockE2.expectedBodiesReceived(testStr);
        manager.addRoute("direct:send", Arrays.asList("mock:mockE2"), "test");

        template.sendBody(testStr);

        mockE2.assertIsSatisfied();
    }

    @Test
    @DirtiesContext
    public void testMultipleRoute() throws InterruptedException {
        String testStr = "camel-test";
        mockE1.whenAnyExchangeReceived(exchange -> exchange.getOut().setBody(exchange.getIn().getBody(String.class).toUpperCase()));

        mockE2.expectedBodiesReceived(testStr.toUpperCase());

        manager.addRoute("direct:send", Arrays.asList("mock:mockE1", "mock:mockE2"), "test");

        template.sendBody(testStr);

        mockE2.assertIsSatisfied();
    }

    @Test
    @DirtiesContext
    public void testRouteGetter() {
        Assert.assertEquals(0, manager.getRoutes().size());

        manager.addRoute("direct:send", Arrays.asList("mock:mockE1", "mock:mockE2"), "test");

        Assert.assertEquals(1, manager.getRoutes().size());
    }

    @Test
    @DirtiesContext
    public void testRouteDelete() throws CamelException {
        String id = manager.addRoute("direct:send", Arrays.asList("mock:mockE1", "mock:mockE2"), "test");
        Assert.assertEquals(1, manager.getRoutes().size());
        Assert.assertFalse(camelContext.getRoutes().isEmpty());

        manager.deleteRoute(id);

        Assert.assertTrue(camelContext.getRoutes().isEmpty());
    }

    @Test(expected = CamelException.class)
    @DirtiesContext
    public void testRouteDeleteNotFound() throws CamelException {
        manager.deleteRoute("test");
    }
}
