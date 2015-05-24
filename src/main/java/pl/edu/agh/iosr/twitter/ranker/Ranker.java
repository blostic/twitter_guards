package pl.edu.agh.iosr.twitter.ranker;

import twitter4j.Status;

/**
 * Created on 3 Apr, 2015 by Jakub Sloniec.
 */


public class Ranker implements IRanker{
    public int rank(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        String[] words = RankerUtils.getWordListFromText(text);

        RankerCore core = new RankerCore();

        int positiveRank = core.getPositiveRank(words);
        int negativeRank = core.getNegativeRank(words);

        return positiveRank - negativeRank;
    }



}
