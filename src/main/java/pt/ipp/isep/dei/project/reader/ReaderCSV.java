package pt.ipp.isep.dei.project.reader;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CVSReader Class. Reads .cvs files
 */

public class ReaderCSV implements Reader {

    public List<String[]> readFile(String filePath) {
        String line;
        String cvsSplit = ",";
        String[] readings;
        List<String[]> listArrayString = new ArrayList<>();
        int iteration = 0;
        try (BufferedReader buffReader = new BufferedReader(new FileReader(filePath))){
            while ((line = buffReader.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                readings = line.split(cvsSplit);
                listArrayString.add(readings);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return listArrayString;
    }
}
