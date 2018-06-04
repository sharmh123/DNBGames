package Testcodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static Testcodes.DNBCore.readInputFile;

public class DNBWords {
    private String configFileName;
    private String inputSentenceType = "word";
    private ConfigContainer configContainer;

    public DNBWords() {
        this.configFileName = DNBCore.getDNBConfigDir() + "/" + "words_config.txt";
        configContainer = new ConfigContainer(configFileName);
        configContainer.setInputSentenceType(inputSentenceType);
    }

    public List<String> getSecondListOfWords(int exerciseLen, ConfigContainer configContainer, List<String> allWords) {
        Random random = new Random(exerciseLen);
        int randomNumber = random.nextInt(200);
        List<String> secondListOfExerciseWords = null;
        switch (configContainer.getDualNBackMode()) {
            case "numbers":
                secondListOfExerciseWords = DNBCore.getListOfNumbers(exerciseLen, configContainer);
                break;
            case "colors":
                secondListOfExerciseWords = DNBCore.getListOfColors(exerciseLen, configContainer);
                break;
            default:
                secondListOfExerciseWords = allWords.subList(0 + randomNumber, exerciseLen + randomNumber);
        }

        return secondListOfExerciseWords;
    }

    public List<String> getExerciseWords(String inputFileName, int exerciseLen, ConfigContainer configContainer) {
        boolean dualNBack = configContainer.isDualNBack();
        List<String> allWords = readInputFile(inputFileName);

        Collections.shuffle(allWords);
        List<String> exerciseWords = allWords.subList(0, exerciseLen);

        if (dualNBack) {
            List<String> secondListOfExerciseWords = getSecondListOfWords(exerciseLen, configContainer, allWords);
            for (int i = 0; i < secondListOfExerciseWords.size(); i++) {
                String combinedWord = exerciseWords.get(i) + ", " + secondListOfExerciseWords.get(i);
                exerciseWords.set(i, combinedWord);
            }
        }

        return exerciseWords;
    }

    public void runGame() throws Exception {
        String inputFileName = configContainer.getInputFileName();
        int exerciseLen = configContainer.getExerciseLen();
        List<String> exerciseWords = getExerciseWords(inputFileName, exerciseLen, configContainer);
        DNBCore.startGameExecution(exerciseWords, configContainer);
        DNBCore.displayResults(configContainer.getInputComparisonFile(), configContainer.getOutputFileName(),
                configContainer.getOutputHistoryFileName());
    }

    public static void main(String[] args) throws Exception {
        DNBWords game = new DNBWords();
        game.runGame();
    }
}


