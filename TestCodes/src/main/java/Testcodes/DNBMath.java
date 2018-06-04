package Testcodes;

import java.util.Map;

import static Testcodes.DNBCore.displayMathsQuestion;
import static Testcodes.DNBCore.readConfig;

public class DNBMath {
    private String configFileName;

    public DNBMath() {
        this.configFileName = DNBCore.getDNBConfigDir() + "/" + "math_config.txt";
    }

    public void runGame() throws Exception {
        Map<String, String> inputConfig = readConfig(configFileName);
        int difficulty = Integer.parseInt(inputConfig.get("difficultyLevel").trim());
        // 1 -> CommonWords, 2 -> phrases

        int exerciseLen = Integer.parseInt(inputConfig.get("exerciseLength").trim());
        // Start the game.
        int i = 1;
        double sumTimeTaken = 0.0;
        for (; i <= exerciseLen; i++) {
            sumTimeTaken += displayMathsQuestion(difficulty);
        }
        String avgTime = String.format("%.2f", (sumTimeTaken/exerciseLen));
        System.out.println("Average time taken: " + avgTime + " secs.");
    }

    public static void main(String[] args) throws Exception {
        DNBMath game = new DNBMath();
        game.runGame();
    }
}
