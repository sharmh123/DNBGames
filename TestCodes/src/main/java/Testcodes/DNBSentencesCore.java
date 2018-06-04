package Testcodes;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Testcodes.DNBCore.checkHistory;
import static Testcodes.DNBCore.readFile;

public class DNBSentencesCore {

    public static boolean scoreOutput(String input, String output, double correctnessThreshold, String stopWordsFile) {
        String[] replacementChars = new String[] {",", "-", "\\.", "!", "\\?", "'"};
        List<String> stopWords = readFile(stopWordsFile);
        input = input.toLowerCase();
        output = output.toLowerCase();
        for (String stopWord: stopWords) {
            input = input.replaceAll(stopWord, "");
            output = output.replaceAll(stopWord, "");
        }

        for (String replacementChar: replacementChars) {
            input = input.replaceAll(replacementChar, " ");
            output = output.replaceAll(replacementChar, " ");
        }

        Set<String> inputSet = new HashSet<String>(Arrays.asList(input.split("\\s+")));
        Set<String> outputSet = new HashSet<String>(Arrays.asList(output.split("\\s+")));
        long wordsInInput = inputSet.size();
        long wordsOfOutputFoundInInput = 0;
        for (String word: outputSet) {
            if (inputSet.contains(word)) {
                wordsOfOutputFoundInInput++;
            }
        }

        return ((double)wordsOfOutputFoundInInput / wordsInInput) >= correctnessThreshold;
    }

    public static void displayResults(ConfigContainer configContainer) throws IOException {
        List<String> input = readFile(configContainer.getInputComparisonFile());
        List<String> output = readFile(configContainer.getOutputFileName());
        int correct = 0;
        for(int i = 0; i < input.size(); i++) {
            if(scoreOutput(input.get(i), output.get(i), configContainer.getCorrectnessThreshold(),
                    configContainer.getStopWordsFile())) {
                correct++;
                if (configContainer.getDisplayFullSentencesInOutput()) {
                    System.out.println(input.get(i) + " :: " + output.get(i) + " --> Correct");
                }
            }
            else {
                if (configContainer.getDisplayFullSentencesInOutput()) {
                    System.out.println(input.get(i) + " :: " + output.get(i) + " --> Wrong");
                }
            }
        }

        String percentage = String.format("%.2f", (double)correct/input.size() * 100);
        System.out.println(correct + " out of " + input.size() + " correct. " + percentage + " percent");
        checkHistory(configContainer.getOutputHistoryFileName(), percentage);
    }

    public static void printOutput(String input, String response, double correctnessThreshold,
                                   String stopWordFile, int messageIndex) {
        boolean output = DNBSentencesCore.scoreOutput(input, response, correctnessThreshold, stopWordFile);
        DNBCore.printResults(output, input, response, messageIndex);
    }
}
