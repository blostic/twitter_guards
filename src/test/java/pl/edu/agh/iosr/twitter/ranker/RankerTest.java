package pl.edu.agh.iosr.twitter.ranker;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class RankerTest {


    public static void main(String[] args) throws TwitterException {
        RankerTest ranker = new RankerTest();

        Twitter twitter = ranker.getTwitterInstance();
        ranker.searchTweets(twitter, "IET");
        System.out.println();
        ranker.getTimeLine(twitter);
    }

    Twitter getTwitterInstance() {
        String OAuthConsumerKey = "WMFmzGLQp2uO9fHBJ5GUlDe7x";
        String OAuthConsumerSecret = "A62hS0gmeheoupPSODnkdnLpDzxHzs6BF4VNaUnwLiLszmCE6Q";
        String OAuthAccessToken = "362535496-b70rw0K4n6nrOY8BTNRE09S3k99kmciUjFIgzq1R";
        String OAuthAccessTokenSecret = "uZgL9M9XjJRzCd5pik5AJHNS4lkPPPBkkjYXVTOOO1Vfu";

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(OAuthConsumerKey)
                .setOAuthConsumerSecret(OAuthConsumerSecret)
                .setOAuthAccessToken(OAuthAccessToken)
                .setOAuthAccessTokenSecret(OAuthAccessTokenSecret);

        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

    void searchTweets(Twitter twitter, String searched) throws TwitterException {
        Query query = new Query(searched);
        QueryResult result = twitter.search(query);

        for (Status status : result.getTweets()) {
            int tweetRank = new RankerCore().rank(status);

            System.out.println("rank: " + tweetRank + " | @" + status.getUser().getScreenName() + ":" + status.getText());
        }
    }

    void getTimeLine(Twitter twitter) throws TwitterException {
        List<Status> statuses = twitter.getHomeTimeline();

        for (Status status : statuses) {
            int tweetRank = new RankerCore().rank(status);
            System.out.println("rank: " + tweetRank + " | " + status.getUser().getName() + ":" + status.getText());
        }
    }

}
