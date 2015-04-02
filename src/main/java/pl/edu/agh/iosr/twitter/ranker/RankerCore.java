package pl.edu.agh.iosr.twitter.ranker;

import twitter4j.Status;

import java.util.List;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */
public class RankerCore {
    public List<String> positiveWords;
    public List<String> negativeeWords;

    public RankerCore() {
        declareWordLists();
    }

    void declareWordLists() {
        positiveWords = RankerUtils.declarePositiveWords();
        negativeeWords = RankerUtils.declareNegativeWords();
    }

    int rank(Status tweet) {

        if (tweet == null) {
            throw new IllegalArgumentException();
        }

        String[] words = RankerUtils.getWordListFromText(tweet.getText());

        int positiveRank = getPositiveRank(words);
        int negativeRank = getNegativeRank(words);

        return positiveRank - negativeRank;
    }

    int getPositiveRank(String[] words) {
        int rank = 0;

        for (String w : words) {
            if (positiveWords.contains(w)) {
                rank++;
            }
        }
        return rank;
    }


    int getNegativeRank(String[] words) {
        int rank = 0;

        for (String w : words) {
            if (negativeeWords.contains(w)) {
                rank++;
            }
        }
        return rank;
    }


}


