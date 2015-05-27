package pl.edu.agh.iosr.twitter.ranker;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import twitter4j.Status;

/**
 * Created on 3 Apr, 2015 by Jakub Sloniec.
 * Modified on 21 May, 2015 by Jakub Sloniec.
 */


public class Ranker implements IRanker, InitializingBean {

    @Deprecated
    public int oldRank(Status tweet) {
        if (tweet == null) {
            throw new IllegalArgumentException();
        }
        String[] words = RankerUtils.getWordListFromText(tweet.getText());

        RankerCore core = new RankerCore();

        int positiveRank = core.getPositiveRank(words);
        int negativeRank = core.getNegativeRank(words);

        return positiveRank - negativeRank;
    }

    private StanfordCoreNLP pipeline;

    public void init() {
        pipeline = new StanfordCoreNLP("stanfordnlp.properties");
    }

    /**
     * Takes twitter status object as argument and returns a sentiment value for its content text.
     *
     * @param text String to be emotionaly evaluated
     * @return sentimental Integer value, being: 0 - super negative, 1 - negative, 2 - neutral, 3 - positive, 4 - super positive
     */
    public int rank(String text) {
        if(StringUtils.isEmpty(text)){
            throw new IllegalArgumentException("Text cannot be empty or null.");
        }
        int mainSentiment = 0;
        if (text != null && text.length() > 0) {
            int longest = 0;
            Annotation annotation = pipeline.process(text);
            for (CoreMap sentence : annotation
                    .get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence
                        .get(SentimentAnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }

            }
        }
        return mainSentiment;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
