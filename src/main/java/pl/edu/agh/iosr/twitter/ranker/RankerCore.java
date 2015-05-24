package pl.edu.agh.iosr.twitter.ranker;

import twitter4j.Status;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */
@Deprecated
public class RankerCore {
    private List<String> positiveWords;
    private List<String> negativeWords;
    private List<String> emphasisWords;

    public RankerCore() {
        declareWordLists();
    }

    private void declareWordLists() {
        positiveWords = RankerUtils.declarePositiveWords();
        negativeWords = RankerUtils.declareNegativeWords();
        emphasisWords = RankerUtils.declareEmphasisWords();
    }

    protected int getPositiveRank(String[] words) {
        int rank = 0;
        int index=0;

        for (String w : words) {
            if (positiveWords.contains(w)) {
                rank++;
                if(RankerUtils.checkEmphasisWords(words,emphasisWords,index)){
                    rank+=2;
                }
            }
            index++;
        }
        return rank;
    }

    protected int getNegativeRank(String[] words) {
        int rank = 0;
        int index=0;

        for (String w : words) {
            if (negativeWords.contains(w)) {
                rank++;
                if(RankerUtils.checkEmphasisWords(words,emphasisWords,index)){
                    rank+=2;
                }
            }
        }
        return rank;
    }
}


