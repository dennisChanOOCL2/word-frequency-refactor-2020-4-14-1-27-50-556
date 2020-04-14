import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String NEWLINE_DELIMITER = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String STRING_DELIMITER = " ";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = calculateWordFrequency(sentence);
            return formatWordFrequencyResult(wordInfoList);
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        List<WordInfo> wordInfoList = new ArrayList<>();
        for(String word : new HashSet<>(words)){
            int count = Collections.frequency(words, word);
            WordInfo wordInfo = new WordInfo(word, count);
            wordInfoList.add(wordInfo);
        }
        wordInfoList.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
        return wordInfoList;
    }

    private String formatWordFrequencyResult(List<WordInfo> wordInfoList){
        StringJoiner joiner = new StringJoiner(NEWLINE_DELIMITER);
        for (WordInfo wordInfo : wordInfoList) {
            String wordWithCount = wordInfo.getWord() + STRING_DELIMITER + wordInfo.getWordCount();
            joiner.add(wordWithCount);
        }
        return joiner.toString();
    }

}
