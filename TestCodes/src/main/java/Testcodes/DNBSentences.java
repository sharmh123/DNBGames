package Testcodes;

import java.util.Collections;
import java.util.List;

import static Testcodes.DNBCore.readInputFile;


public class DNBSentences {
    private String configFileName;
    private String inputSentenceType = "sentence";
    private ConfigContainer configContainer;

    public DNBSentences() {
        this.configFileName = DNBCore.getDNBConfigDir() + "/sentences_config.txt";
        configContainer = new ConfigContainer(configFileName);
        configContainer.setInputSentenceType(inputSentenceType);
    }

    public void runGame() throws Exception {
        List<String> allWords = readInputFile(configContainer.getInputFileName());
        Collections.shuffle(allWords);
        List<String> exerciseWords = allWords.subList(0, configContainer.getExerciseLen());
        DNBCore.startGameExecution(exerciseWords, configContainer);
        DNBSentencesCore.displayResults(configContainer);
    }

    public static void main(String[] args) throws Exception {
        DNBSentences game = new DNBSentences();
        game.runGame();
    }
}


