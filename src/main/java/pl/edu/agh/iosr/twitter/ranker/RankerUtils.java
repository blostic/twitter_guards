package pl.edu.agh.iosr.twitter.ranker;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Arrays;
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
        positiveWords.add("like");
        positiveWords.add("love");
        positiveWords.add("beautiful");
        positiveWords.add("cute");
        positiveWords.add("happy");

        return positiveWords;
    }

    static List<String> declareNegativeWords() {
        List<String> negativeWords = new ArrayList<String>();

        negativeWords.add("bad");
        negativeWords.add("awful");
        negativeWords.add("ugly");
        negativeWords.add("disgusting");
        negativeWords.add("worst");
        negativeWords.add("suck");
        negativeWords.add("sux");
        negativeWords.add("hate");
        negativeWords.add("kill");
        negativeWords.add("upset");

        return negativeWords;
    }

    static List<String> declareEmphasisWords() {
        List<String> emphasisWords = new ArrayList<String>();

        emphasisWords.add("very");
        emphasisWords.add("much");
        emphasisWords.add("lot");
        emphasisWords.add("many");
        emphasisWords.add("real");
        emphasisWords.add("really");

        return emphasisWords;
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

    static boolean checkEmphasisWords(String[] words, List<String> emphasisWords, int index) {
        String previousWord = "";
        String nextWord = "";
        if (index > 0) {
            previousWord = words[index - 1];
        }
        if (words.length > index + 1) {
            nextWord = words[index + 1];
        }
        return emphasisWords.contains(previousWord) || emphasisWords.contains(nextWord);
    }


}
