package words;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Words {
    /*
    sentence.toLowerCase() converts the entire sentence to lowercase to ensure case-insensitivity.
    .split("\\W+") splits the sentence into words using a regular expression that matches any sequence of non-word characters. This effectively removes punctuation.
    Arrays.stream(...) creates a stream from the array of words.
    .distinct() filters the stream to only include unique elements, removing duplicates.
    .collect(Collectors.toList()) collects the elements of the stream into a List.
     */
    public static List<String> getUniqueWordsFromSentence(String sentence) {
        return Arrays.stream(sentence.toLowerCase().split("\\W+"))
                .distinct()
                .collect(Collectors.toList());
    }
}
