package pt.ipp.isep.dei.project.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CVSReader Class. Reads .cvs files
 */

public class CSVRead {

    public List<String[]> readCSV(String csvFileLocation) {
        BufferedReader buffReader = null;
        FileReader fileReader = null;
        String line = "";
        String cvsSplit = ",";
        String[] readings;
        List<String[]> listReads = new ArrayList<>();
        int iteration = 0;
        try {
            fileReader = new FileReader(csvFileLocation);
            buffReader = new BufferedReader(fileReader);
            while ((line = buffReader.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                readings = line.split(cvsSplit);
                listReads.add(readings);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffReader != null)
                    buffReader.close();
                if (fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return listReads;
    }
}
