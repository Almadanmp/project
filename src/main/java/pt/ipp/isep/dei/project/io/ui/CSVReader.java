package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.Sensor;
import pt.ipp.isep.dei.project.model.TypeSensor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVReader {

    private List<Date> dates = new ArrayList<>();
    private List<Double> values = new ArrayList<>();


    //* TEST METHOD

 /*  public static void main(String[] args) {
        CSVReader testRead = new CSVReader();
        testRead.getCSVReadings();
        Date validDate1 = new Date();
        SimpleDateFormat validSdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate1 = validSdf2.parse("2017-12-31T08:00:00+00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        Sensor sensorTest = new Sensor("Sensor1",new TypeSensor("Rain","mm"),
                new Local(2,2,2),validDate1);
        testRead.setCSVReadings(sensorTest);
        Date test = sensorTest.getReadingList().getMostRecentReadingDate();
        System.out.println(test);
    }
*/

    public String[] getCSVReadings() {
        String csvFile = "resources/testCSV.csv";
        BufferedReader buffReader = null;
        FileReader fileReader = null;
        String line = "";
        String cvsSplit = ",";
        String[] readings = new String[0];
        Date readDate;
        Double readValue = 0.0;
        try {
            fileReader = new FileReader(csvFile);
            buffReader = new BufferedReader(fileReader);
            while ((line = buffReader.readLine()) != null) {
                readings = line.split(cvsSplit);
                //* System.out.println("Readings [Date= " + readings[1] + " , Value=" + readings[3] + "]");
                try {
                    readValue = Double.parseDouble(readings[3]);
                } catch (NumberFormatException nfe) {
                    System.out.println("CSV readings values are not numeric.");
                }
                values.add(readValue);
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
                    readDate = sdf.parse(readings[1]);
                    dates.add(readDate);
                } catch (ParseException c) {
                    c.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        return readings;
    }

    public void setCSVReadings(Sensor sensor) {
        Date startDate = sensor.getDateStartedFunctioning();
        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).after(startDate) || dates.get(i) == startDate) {
                Reading reading = new Reading(values.get(i), dates.get(i));
                sensor.addReading(reading);
            }
        }
    }

}



