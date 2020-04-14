import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String NEWLINE_DELIMITER = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String STRING_DELIMITER = " ";

    public String getResult(String inputStr) {


        if (inputStr.split(SPACE_PATTERN).length==1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] arr = inputStr.split(SPACE_PATTERN);

                List<WordInfo> wordInfoList = new ArrayList<>();
                for (String s : arr) {
                    WordInfo wordInfo = new WordInfo(s, 1);
                    wordInfoList.add(wordInfo);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordInfo>> map =getListMap(wordInfoList);

                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordInfo);
                }
                wordInfoList = list;

                wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(NEWLINE_DELIMITER);
                for (WordInfo w : wordInfoList) {
                    String s = w.getWord() + STRING_DELIMITER +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
            if (!map.containsKey(wordInfo.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getWord(), arr);
            }
            else {
                map.get(wordInfo.getWord()).add(wordInfo);
            }
        }
        return map;
    }
}
