package pl.edu.agh.iosr.twitter.ranker;

import twitter4j.Status;

/**
 * Created on 3 Apr, 2015 by Jakub Sloniec.
 */


public class Ranker {
    public static int rank(Status tweet) {

        if (tweet == null) {
            throw new IllegalArgumentException();
        }

        RankerCore core = new RankerCore();

        String[] words = RankerUtils.getWordListFromText(tweet.getText());

        int positiveRank = core.getPositiveRank(words);
        int negativeRank = core.getNegativeRank(words);

        return positiveRank - negativeRank;
    }



}
