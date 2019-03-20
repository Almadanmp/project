package pt.ipp.isep.dei.project.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CVSReader Class. Reads .cvs files
 */

public class ReaderCSVReadings {

    public List<String[]> readCSV(String csvFileLocation) {
        String line = "";
        String cvsSplit = ",";
        String[] readings;
        List<String[]> listReads = new ArrayList<>();
        int iteration = 0;
        try (BufferedReader buffReader = new BufferedReader(new FileReader(csvFileLocation))){
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
        }
        return listReads;
    }
}
