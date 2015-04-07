package pl.edu.agh.iosr.twitter.ranker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created on 2 Apr, 2015 by Jakub Sloniec.
 */
public class RankerUtils {
    static List<String> declarePositiveWords() {
        return getWordList("positive");
    }

    static List<String> declareNegativeWords() {
        return getWordList("negative");
    }

    static List<String> declareEmphasisWords() {
        return getWordList("emphasis");
    }

    static String[] getWordListFromText(String text) {
        return text.toLowerCase().split("[[ ]*|[,]*|[/.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+");
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

    static List<String> getWordList(String name) {
        List<String> result = null;

        Properties prop = new Properties();

        String fileName = "src/main/java/pl/edu/agh/iosr/twitter/ranker/words.properties";

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(fileName);

            if (fileInputStream != null) {
                prop.load(fileInputStream);

                Set<Object> keys = prop.keySet();
                if (keys.contains(name)) {
                    result = new ArrayList<>();
                    result.add((String) prop.get(name));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
