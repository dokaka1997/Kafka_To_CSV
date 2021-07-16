import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVWriter {


    public void insertCSV(String fileName, String json) {
        fileName += ".csv";
        File file = new File(fileName);
        try {
            boolean check = file.exists();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            CSVPrinter csvPrinter;
            String[] key = getKeys(json);
            if (check) {
                csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT
                        .withDelimiter(' '));
            } else {

                csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT
                        .withHeader(key).withDelimiter(' '));
            }
            getValue(json);

            csvPrinter.printRecord(getValue(json));

            csvPrinter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] getKeys(String json) {
        JSONObject output;
        output = new JSONObject(json);
        String[] key = new String[output.keySet().size()];
        Iterator<String> keys = output.keys();

        while (keys.hasNext()) {
            for (int i = 0; i < key.length; i++) {
                key[i] = keys.next();
            }
        }
        return key;
    }

    public static String[] getValue(String jsonArrayString) {
        JSONObject output;
        List<String> stringList = new ArrayList<>();
        try {
            output = new JSONObject(jsonArrayString);
            Iterator<String> keys = output.keys();

            while (keys.hasNext()) {
                stringList.add(output.get(keys.next()).toString());
            }
            stringList.toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] array = new String[stringList.size()];
        return stringList.toArray(array);
    }
}