package Testcodes;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static Testcodes.DNBCore.readStudyInputFile;

public class DNBStudy {
    private String dnbDir = DNBCore.getDNBBaseDir();
    private String configFileName = DNBCore.getDNBConfigDir() + "/study_config.txt";
    private String areaOfFocusFileName = dnbDir + "/study/areaOfFocus.txt";
    private String inputSentenceType = "sentence";
    private ConfigContainer configContainer;

    public DNBStudy() {
        configContainer = new ConfigContainer(configFileName);
        configContainer.setInputSentenceType(inputSentenceType);
    }

    private static void serializeThePreferences(Map<String, String> inputMap, String cfgFileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cfgFileName)));
        for (Map.Entry<String, String> entry: inputMap.entrySet()) {
            bw.write(entry.getKey() + " = " + entry.getValue() + "\r\n" );
        }
        bw.flush();
        bw.close();
    }

    public static Map<String, String> showSimpleDialog(String areaOfFocusFileName) throws IOException {
        JPanel jPanel = new JPanel(new GridLayout(4, 10));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(areaOfFocusFileName));
        Map<String, String > map = new HashMap<String, String>();
        try {
            String line = "";
            while(null != (line = bufferedReader.readLine())) {
                String[] lineArr = line.split("=");
                map.put(lineArr[0].trim(), lineArr[1].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            bufferedReader.close();
        }
        JTextField fileName = new JTextField(map.get("fileName"));
        JTextField startIndex = new JTextField(map.get("startIndex"));
        JTextField endIndex = new JTextField(map.get("endIndex"));

        JLabel fileNameLabel = new JLabel("File Name: ");
        JLabel startIndexLabel = new JLabel("Start Index: ");
        JLabel endIndexLabel = new JLabel("End Index: ");

        jPanel.add(fileNameLabel);
        jPanel.add(fileName);
        jPanel.add(startIndexLabel);
        jPanel.add(startIndex);
        jPanel.add(endIndexLabel);
        jPanel.add(endIndex);

        int result = JOptionPane.showConfirmDialog(null, jPanel, "DNBStudy ",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        Map<String, String> inputMap = new LinkedHashMap<String, String>();
        if (result == JOptionPane.OK_OPTION) {
            inputMap.put("fileName", fileName.getText());
            inputMap.put("startIndex", startIndex.getText());
            inputMap.put("endIndex", endIndex.getText());
            serializeThePreferences(inputMap, areaOfFocusFileName);

            return inputMap;

        } else {
            System.exit(0);
        }

        return null;
    }

    public void runGame() throws Exception {

        Map<String, String> inputMap = showSimpleDialog(areaOfFocusFileName);
        String inputFileName = configContainer.getBaseDir() + "/" + inputMap.get("fileName").trim();
        int startIndex = Integer.parseInt(inputMap.get("startIndex").trim());
        int endIndex = Integer.parseInt(inputMap.get("endIndex").trim());

        List<String> allSentences = readStudyInputFile(inputFileName);
        if ((endIndex > allSentences.size() - 1) || (endIndex == -1)) {
            endIndex = allSentences.size();
        }

        configContainer.setStartIndex(startIndex);
        configContainer.setEndIndex(endIndex);

        List<String> exerciseWords = allSentences.subList(startIndex, endIndex);
        DNBCore.startGameExecution(exerciseWords, configContainer);
        if (!configContainer.isSkimThroughMode() && (configContainer.getDisplayFullSentencesInOutput())) {
            DNBSentencesCore.displayResults(configContainer);
        }
    }

    public static void main(String[] args) throws Exception {
        DNBStudy game = new DNBStudy();
        game.runGame();
    }
}
