package Testcodes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class DNBCore {
    private static String baseDir = "/Users/himash/testcodes/src/Testcodes/src/resources/DNB";

    public static String getDNBBaseDir() {
        return baseDir;
    }

    public static String getDNBConfigDir() {
        return baseDir + "/config";
    }

    public static void checkHistory(String outputHistoryFileName, String percentage) throws IOException {
        PrintWriter outputHistoryFileWriter = new PrintWriter(new BufferedWriter(
                new FileWriter(outputHistoryFileName, true)));
        outputHistoryFileWriter.write(String.valueOf(percentage) + "\n");
        outputHistoryFileWriter.flush();
        outputHistoryFileWriter.close();

        // Read the last N lines of the file and check the scores.
        List<Double> scores = new ArrayList<>();
        final Scanner s = new Scanner(new File(outputHistoryFileName));
        while (s.hasNextLine()) {
            Double score = Double.parseDouble(s.nextLine().trim());
            scores.add(score);
        }
        List<Double> lastNScores = new ArrayList<>();
        int numScores = scores.size();
        int originalLookBack = 5;
        int actualLookback = originalLookBack;
        int numScoresBelowThreshold = 0;
        int numScoresAboveThreshold = 0;
        Double thresholdScore = 80.0;
        if (numScores <= originalLookBack)
            actualLookback = numScores;

        for (int i = numScores - actualLookback; i <= numScores - 1; i++) {

            Double score = scores.get(i);
            lastNScores.add(score);
            if (score >= thresholdScore) {
                numScoresAboveThreshold++;
            } else {
                numScoresBelowThreshold++;
            }
        }

        System.out.print("\nLast " + actualLookback + " scores: [");
        for (Double score : lastNScores) {
            System.out.print(String.format("%.2f", score) + ", ");
        }
        System.out.print("]. ");
        if (numScoresAboveThreshold == originalLookBack) {
            System.out.println("Last " + originalLookBack + " scores are above threshold. You should increase the level.");
        }
        if (numScoresBelowThreshold == originalLookBack) {
            System.out.println("Last " + originalLookBack + " scores are below threshold. You should decrease the level.");
        }
    }

    private static double showMathQuestion(MathQuestion mathQuestion) {
        long startTime = System.currentTimeMillis();
        String mathQuestionResponse = JOptionPane.showInputDialog(null,
                mathQuestion.getQuestion(), "Solve this:",
                JOptionPane.QUESTION_MESSAGE);
        long endTime = System.currentTimeMillis();
        double timeTaken = (double) (endTime - startTime) / 1000;
        String actualResponseStr = mathQuestionResponse.trim();

        String wrongResponse = "Wrong! Question -> [" + mathQuestion.getQuestion() +
                "] Actual: " + actualResponseStr + ", Expected: " + mathQuestion.getResult();
        String response = "";
        if ((!mathQuestionResponse.trim().equals(""))) {
            try {
                Integer actualResponse = Integer.parseInt(actualResponseStr);
                if (actualResponse == mathQuestion.getResult()) {
                    response = "Correct!";
                } else {
                    response = wrongResponse;
                }
            } catch (Exception ex) {
                System.out.println("The answer was not an integer: " + actualResponseStr);
            }
        }
        else {
            response = wrongResponse;
        }

        System.out.println(response + " Time Taken: " + timeTaken + " secs.");
        return timeTaken;
    }


    public static double displayMathsQuestion(int difficulty) {
        MathQuestion mathQuestion = new MathQuestion(difficulty).getRandomMathQuestion();
        return showMathQuestion(mathQuestion);
    }

    public static double displayMathsQuestion() {
        MathQuestion mathQuestion = new MathQuestion().getRandomMathQuestion();
        return showMathQuestion(mathQuestion);
    }

    public static Map<String, String> readConfig(String configFileName) {
        List<String> configLines = readFile(configFileName);
        Map<String, String> inputConfig = new HashMap<String, String>();
        for (String line : configLines) {
            String[] keyVal = line.split(":\\s*");
            inputConfig.put(keyVal[0], keyVal[1]);
        }

        return inputConfig;
    }

    public static List<String> readFile(String fileName) {
        List<String> list = new ArrayList<String>();
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0)
                    list.add(line.trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public static List<String> readInputFile(String inputFile) {
        List<String> lines = DNBCore.readFile(inputFile);

        List<String> allWords = new ArrayList<String>();
        for (String line : lines) {
            allWords.add(line.trim());
        }
        return allWords;
    }

    public static int getRandomNumber() {
        int randomNumber = new Random().nextInt(20);
        return randomNumber;
    }

    public static List<String> readStudyInputFile(String inputFile) {
        List<String> lines = DNBCore.readFile(inputFile);

        List<String> allWords = new ArrayList<String>();
        for (String line : lines) {
            String[] sublines = line.split("(\\.|\\?|\\!|\\;)\\s+");
            for (String subline : sublines) {
                allWords.add(subline);
            }
        }
        return allWords;
    }

    public static List<String> getListOfColors(int exerciseLen, ConfigContainer configContainer) {
        List<String> colorsList = new ArrayList<>();
        String[] colors = new String[] {"purple", "indigo", "blue", "green", "yellow", "orange", "red", "white",
                "black", "grey", "brown"};
        Random random = new Random();
        for (int i = 0; i < exerciseLen; i++) {
            int randomInt = random.nextInt(colors.length);
            colorsList.add(colors[randomInt]);
        }
        return colorsList;
    }

    public static List<String> getListOfNumbers(int exerciseLen, ConfigContainer configContainer) {
        List<String> numbers = new ArrayList<>();
        String[] range = configContainer.getDualNBackNumbersRange().split(",");
        Random random = new Random(7);
        for (int i = 0; i < exerciseLen; i++) {
            int randomInt = random.nextInt(Integer.parseInt(range[1]) - Integer.parseInt(range[0]) + 1) +
                    Integer.parseInt(range[0]);
            numbers.add(Integer.toString(randomInt));
        }

        return numbers;
    }

    public static void writeInputComparisonFile(String inputComparisonFile,
                                                List<String> exerciseWords) throws IOException {
        BufferedWriter bw = null;
        try {
            File file = new File(inputComparisonFile);
            if (file.exists()) {
                file.delete();
            }
            bw = new BufferedWriter(new FileWriter(file, true));
            for (String word : exerciseWords) {
                bw.write(word + "\r\n");
            }
        } catch (IOException e) {
            System.out.println("Exception Writing comparison file");
            throw e;
        } finally {
            bw.close();
        }
    }

    public static void displayResults(String inputFileName, String outputFileName, String outputHistoryFileName) throws IOException {
        List<String> input = readFile(inputFileName);
        List<String> output = readFile(outputFileName);
        int correct = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equalsIgnoreCase(output.get(i))) {
                correct++;
            }
        }

        String percentage = String.format("%.2f", (double) correct / input.size() * 100);

        for (int i = 0; i < input.size(); i++) {
            String correctness = "";
            if (input.get(i).equalsIgnoreCase(output.get(i))) {
                correctness = "Correct";
            } else
                correctness = "Wrong";
            System.out.println(input.get(i) + " :: " + output.get(i) + " --> " + correctness);
        }

        System.out.println("\n\n" + correct + " out of " + input.size() + " correct. "
                + percentage + " percent");
        checkHistory(outputHistoryFileName, percentage);
    }

    private static void showInputDialog(ConfigContainer configContainer, String message, int index,
                                        BufferedWriter bw, int displayIndex) {
        String inputSentenceType = configContainer.getInputSentenceType();
        int nback = configContainer.getNback();
        if (!configContainer.isSkimThroughMode()) {
            String response = JOptionPane.showInputDialog(null,
                    "Enter the " + inputSentenceType + " : " + displayIndex, "Enter the " + inputSentenceType,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == null || response.length() == 0)
                response = "Don't know";

            //handleInterference(configContainer);

            try {
                if (configContainer.isPrintOutputAfterEverySentence()) {
                    DNBSentencesCore.printOutput(message, response, configContainer.getCorrectnessThreshold(),
                            configContainer.getStopWordsFile(), index);
                }
                bw.write(response + "\r\n");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void handleInterference(ConfigContainer configContainer) {
        if (configContainer.getInterference()) {
            int interferenceLevel = configContainer.getInterferenceLevel();
            for (int count = 0; count < interferenceLevel; count++) {
                displayMathsQuestion();
            }
        }
    }

    /**
     * Start the exeuction of the game.
     * @param exerciseWords
     * @param configContainer
     * @throws IOException
     */
    public static void startGameExecution(List<String> exerciseWords, ConfigContainer configContainer) throws IOException {
        try {
            writeInputComparisonFile(configContainer.getInputComparisonFile(), exerciseWords);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File outputFile = new File(configContainer.getOutputFileName());
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Start the game.
        int i = configContainer.getStartIndex() + 1;
        int nback = configContainer.getNback();
        String message = "";
        for (String word : exerciseWords) {
            message = word;

            if (message != null && !message.equals(""))
                JOptionPane.showMessageDialog(null, message, configContainer.getInputSentenceType()
                                + ": " + i, JOptionPane.PLAIN_MESSAGE);

            handleInterference(configContainer);

            if (i >= nback) {
                showInputDialog(configContainer, message, i, bw, (i - nback + 1));
            }
            i++;
        }

        for (int j = (i - nback + 1); j < i; j++) {
            showInputDialog(configContainer, message, j, bw, j);
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printResults(boolean output, String input, String response, int messageIndex) {
        if (output) {
            System.out.println(messageIndex + ":: Correct!");
        } else {
            System.out.println(messageIndex + ":: Wrong! Expected: " + input + ", Actual: " + response);
        }
    }
}
