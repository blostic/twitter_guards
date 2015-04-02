package pl.edu.agh.iosr.twitter.ranker;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */
public class RankerUtils {
    static List<String> declarePositiveWords() {
        List<String> positiveWords = new ArrayList<String>();

        positiveWords.add("good");
        positiveWords.add("excellent");
        positiveWords.add("outstanding");
        positiveWords.add("nice");
        positiveWords.add("cool");
        positiveWords.add("best");

        return positiveWords;
    }

    static List<String> declareNegativeWords() {
        List<String> negativeeWords = new ArrayList<String>();

        negativeeWords.add("bad");
        negativeeWords.add("awful");
        negativeeWords.add("ugly");
        negativeeWords.add("disgusting");
        negativeeWords.add("worst");
        negativeeWords.add("suck");
        negativeeWords.add("sux");

        return negativeeWords;

    }

    static String[] getWordListFromText(String text) {
        return text.toLowerCase().split("[[ ]*|[,]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+");
    }

    static Twitter getTwitterInstance() {
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


}
