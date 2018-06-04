package Testcodes;

import java.util.Map;

import static Testcodes.DNBCore.readConfig;

public class ConfigContainer {
    private String baseDir;
    private String inputFileName;
    private String outputFileName;
    private String inputComparisonFile;
    private int exerciseLen;
    private int nback;
    private boolean interference;
    private double correctnessThreshold;
    private String outputHistoryFileName;
    private String stopWordsFile;
    private boolean displayFullSentencesInOutput;
    private int interferenceLevel;
    private Map<String, String> inputConfig;

    private int startIndex = 0;
    private int endIndex = -1;
    private boolean skimThroughMode = false;
    private boolean printOutputAfterEverySentence = false;
    private boolean dualNBack;
    private String dualNBackMode;

    public String getDualNBackNumbersRange() {
        return dualNBackNumbersRange;
    }

    private String dualNBackNumbersRange;

    private String inputSentenceType = "<NOT SET>";

    private String getLocation(String property) {
        return baseDir + "/" + inputConfig.get(property).trim();
    }

    public String getDualNBackMode() {
        return dualNBackMode;
    }

    public void setDualNBackMode(String dualNBackMode) {
        this.dualNBackMode = dualNBackMode;
    }

    public ConfigContainer(String configFileName) {
        inputConfig = readConfig(configFileName);
        baseDir = inputConfig.get("baseDir").trim();
        if (inputConfig.get("inputFile") != null) {
            inputFileName = getLocation("inputFile");
        }

        outputFileName = getLocation("outputFile");

        inputComparisonFile = getLocation("inputComparisonFile");

        if (inputConfig.get("exerciseLength") != null) {
            exerciseLen = Integer.parseInt(inputConfig.get("exerciseLength").trim());
        }

        nback = Integer.parseInt(inputConfig.get("n-back").trim());

        if (inputConfig.get("dualNBack") != null) {
            dualNBack = Boolean.parseBoolean(inputConfig.get("dualNBack").trim());
        }

        interference = Boolean.parseBoolean(inputConfig.get("interference").trim());

        if (inputConfig.get("correctnessThreshold") != null) {
            correctnessThreshold = Double.parseDouble(inputConfig.get("correctnessThreshold").trim());
        }
        outputHistoryFileName = getLocation("outputHistoryFile");

        if (inputConfig.get("stopWordsFile") != null) {
            stopWordsFile = getLocation("stopWordsFile");
        }

        if (inputConfig.get("displayFullSentencesInOutput") != null) {
            displayFullSentencesInOutput = Boolean.parseBoolean(inputConfig.get("displayFullSentencesInOutput").trim());
        }

        if (inputConfig.get("interference-level") != null) {
            interferenceLevel = Integer.parseInt(inputConfig.get("interference-level").trim());
        }

        if (inputConfig.get("skimThroughMode") != null) {
            skimThroughMode = Boolean.parseBoolean(inputConfig.get("skimThroughMode").trim());
        }

        if (inputConfig.get("printOutputAfterEverySentence") != null) {
            printOutputAfterEverySentence = Boolean.parseBoolean(inputConfig.get("printOutputAfterEverySentence").trim());
        }

        if (inputConfig.get("dualNBackMode") != null) {
            dualNBackMode = inputConfig.get("dualNBackMode").trim();
        }

        if (inputConfig.get("dualNBackNumbersRange") != null) {
            dualNBackNumbersRange = inputConfig.get("dualNBackMode").trim();
        }
        else {
            dualNBackNumbersRange = "1,9";
        }
    }

    public boolean isPrintOutputAfterEverySentence() {
        return printOutputAfterEverySentence;
    }

    public String getInputSentenceType() {
        return inputSentenceType;
    }

    public void setInputSentenceType(String inputSentenceType) {
        this.inputSentenceType = inputSentenceType;
    }

    public boolean isSkimThroughMode() {
        return skimThroughMode;
    }

    public boolean isDualNBack() {
        return dualNBack;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public Map<String, String> getInputConfig() {
        return inputConfig;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public boolean getInterference() {
        return interference;
    }

    public boolean getDisplayFullSentencesInOutput() {
        return displayFullSentencesInOutput;
    }

    public int getInterferenceLevel() {
        return interferenceLevel;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public String getInputComparisonFile() {
        return inputComparisonFile;
    }

    public void setInputComparisonFile(String inputComparisonFile) {
        this.inputComparisonFile = inputComparisonFile;
    }

    public int getExerciseLen() {
        return exerciseLen;
    }

    public void setExerciseLen(int exerciseLen) {
        this.exerciseLen = exerciseLen;
    }

    public int getNback() {
        return nback;
    }

    public void setNback(int nback) {
        this.nback = nback;
    }

    public boolean isInterference() {
        return interference;
    }

    public void setInterference(boolean interference) {
        this.interference = interference;
    }

    public double getCorrectnessThreshold() {
        return correctnessThreshold;
    }

    public void setCorrectnessThreshold(double correctnessThreshold) {
        this.correctnessThreshold = correctnessThreshold;
    }

    public String getOutputHistoryFileName() {
        return outputHistoryFileName;
    }

    public void setOutputHistoryFileName(String outputHistoryFileName) {
        this.outputHistoryFileName = outputHistoryFileName;
    }

    public String getStopWordsFile() {
        return stopWordsFile;
    }

    public void setStopWordsFile(String stopWordsFile) {
        this.stopWordsFile = stopWordsFile;
    }

    public boolean isDisplayFullSentencesInOutput() {
        return displayFullSentencesInOutput;
    }

    public void setDisplayFullSentencesInOutput(boolean displayFullSentencesInOutput) {
        this.displayFullSentencesInOutput = displayFullSentencesInOutput;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
