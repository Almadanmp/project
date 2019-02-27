package pt.ipp.isep.dei.project.io.ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void main(String[] args) {
        CSVReader teest = new CSVReader();
        teest.getReadings();
    }

    public String[] getReadings() {
        String csvFile = "resources/testCSV.csv";
        BufferedReader buffReader = null;
        String line = "";
        String cvsSplit = ",";
        String[] readings = new String[0];

        try {
            buffReader = new BufferedReader(new FileReader(csvFile));
            while ((line = buffReader.readLine()) != null) {
                readings = line.split(cvsSplit);
                System.out.println("Readings [Date= " + readings[1] + " , Value=" + readings[3] + "]");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffReader != null) {
                try {
                    buffReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return readings;
    }

    /* temperatureIsepSensor.addReading(new Reading(8, new GregorianCalendar(2018, Calendar.DECEMBER, 30, 2, 0).getTime()));
     */
}



