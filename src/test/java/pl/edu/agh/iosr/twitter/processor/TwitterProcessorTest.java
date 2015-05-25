package pl.edu.agh.iosr.twitter.processor;

import org.junit.Assert;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.iosr.twitter.dto.TweetDTO;
import pl.edu.agh.iosr.twitter.model.Emotion;
import pl.edu.agh.iosr.twitter.model.Tweet;
import pl.edu.agh.iosr.twitter.processor.twitter.TweetRankProcessor;
import pl.edu.agh.iosr.twitter.ranker.IRanker;
import twitter4j.GeoLocation;
import twitter4j.Status;
import twitter4j.User;

import java.util.Date;

/**
 * Created by radoslawdyrda on 18.05.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-config.xml"})
public class TwitterProcessorTest {

    @Autowired
    private TweetRankProcessor processor;

    @Mock
    private IRanker mockedRanker;

    @Mock
    private Status mockedTweet;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        Mockito.when(mockedRanker.rank(Mockito.any(String.class))).thenReturn(1);

        Mockito.when(mockedTweet.getCreatedAt()).thenReturn(new Date(12345));
        Mockito.when(mockedTweet.getText()).thenReturn("mock");
        Mockito.when(mockedTweet.getId()).thenReturn(3L);
        Mockito.when(mockedTweet.getGeoLocation()).thenReturn(Mockito.mock(GeoLocation.class));

        User user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(123L);
        Mockito.when(mockedTweet.getUser()).thenReturn(user);

        processor.setRanker(mockedRanker);

    }

    @Test
    public void testProcessor(){
        Object ret = processor.doWithTweet(mockedTweet, "camp");

        Assert.assertTrue(ret instanceof Tweet);

        Tweet tweet = (Tweet) ret;

        Assert.assertEquals(Emotion.getValue(1), tweet.getEmotion());
        Assert.assertEquals("mock", tweet.getText());


    }
}
