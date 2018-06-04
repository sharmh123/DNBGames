package Testcodes;

//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by nad on 6/19/2015.
 */
public class FlashCardGenerator {

//    public static void main(String[] args) throws IOException {
//        List<String> options = showSimpleDialog();
//        String fileName = options.get(0);
//        // Excel sheet visible row indexes start with 1
//        int startIndex = Integer.parseInt(options.get(1)) - 1;
//        int endIndex = Integer.parseInt(options.get(2)) - 1;
//
//        String sheetName = options.get(3);
//        FileInputStream file = new FileInputStream(new File(fileName));
//
//
//        XSSFWorkbook wb = new XSSFWorkbook(file);
//        XSSFSheet sheet = null;
//        if (sheetName.equals("default"))
//            sheet = wb.getSheetAt(0);
//        else
//            sheet = wb.getSheet(sheetName);
//
//        int i = 0;
//        for(Row row: sheet) {
//            if (row.getRowNum() < startIndex)
//                continue;
//            if (row.getRowNum() > endIndex)
//                break;
//            JOptionPane.showMessageDialog(null, row.getCell(0), "Qn : " + i,
//                    JOptionPane.PLAIN_MESSAGE);
//            JOptionPane.showMessageDialog(null, row.getCell(1), "Ans: " + i,
//                    JOptionPane.PLAIN_MESSAGE);
//            i++;
//        }
//    }
//
//    public static List<String> showSimpleDialog() throws IOException {
//        JPanel jPanel = new JPanel(new GridLayout(4, 3));
//        String cfgFileName = "c:\\reader\\documents\\FlashCardsCfg.txt";
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(cfgFileName));
//        Map<String, String > map = new HashMap<String, String>();
//        try {
//            String line = "";
//            while(null != (line = bufferedReader.readLine())) {
//                String[] lineArr = line.split("=");
//                map.put(lineArr[0].trim(), lineArr[1].trim());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            bufferedReader.close();
//        }
//        JTextField fileName = new JTextField(map.get("fileName"));
//        JTextField startIndex = new JTextField(map.get("startIndex"));
//        JTextField endIndex = new JTextField(map.get("endIndex"));
//        JTextField sheetName = new JTextField(map.get("sheetName"));
//
//        JLabel fileNameLabel = new JLabel("File Name: ");
//        JLabel startIndexLabel = new JLabel("Start Index: ");
//        JLabel endIndexLabel = new JLabel("End Index: ");
//        JLabel sheetNameLabel = new JLabel("Sheet Name");
//
//        jPanel.add(fileNameLabel);
//        jPanel.add(fileName);
//        jPanel.add(startIndexLabel);
//        jPanel.add(startIndex);
//        jPanel.add(endIndexLabel);
//        jPanel.add(endIndex);
//        jPanel.add(sheetNameLabel);
//        jPanel.add(sheetName);
//
//        int result = JOptionPane.showConfirmDialog(null, jPanel, "FlashCards",
//                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//        Map<String, String> inputMap = new LinkedHashMap<String, String>();
//        if (result == JOptionPane.OK_OPTION) {
//            List<String> stringList = new ArrayList<String>();
//            stringList.add(fileName.getText());
//            inputMap.put("fileName", fileName.getText());
//
//            stringList.add(startIndex.getText());
//            inputMap.put("startIndex", startIndex.getText());
//
//            stringList.add(endIndex.getText());
//            inputMap.put("endIndex", endIndex.getText());
//
//            stringList.add(sheetName.getText());
//            inputMap.put("sheetName", sheetName.getText());
//
//            serializeThePreferences(inputMap, cfgFileName);
//            return stringList;
//        } else {
//            System.exit(0);
//        }
//
//        return null;
//    }
//
//    private static void serializeThePreferences(Map<String, String> inputMap, String cfgFileName) throws IOException {
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cfgFileName)));
//        for (Map.Entry<String, String> entry: inputMap.entrySet()) {
//            bw.write(entry.getKey() + " = " + entry.getValue() + "\r\n" );
//        }
//        bw.flush();
//        bw.close();
//    }
}
