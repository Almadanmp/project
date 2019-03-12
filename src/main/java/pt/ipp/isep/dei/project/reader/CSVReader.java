package pt.ipp.isep.dei.project.reader;

import pt.ipp.isep.dei.project.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CVSReader Class. Reads .cvs files
 */
public class CSVReader implements Reader {

    private String csvFileLocation;


    // TEST METHOD MOCK - Take out of comment to test
//
//    public static void main(String[] args) {
//
//        GeographicAreaList testGeoList = new GeographicAreaList();
//        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("pais"), 34000, 300000,
//                new Local(3, 3, 3));
//        GeographicArea testArea2 = new GeographicArea("Espanha", new TypeArea("pais"), 340000, 3000000,
//                new Local(30, 30, 30));
//        testGeoList.addGeographicArea(testArea);
//        testGeoList.addGeographicArea(testArea2);
//        Date validDate1 = new Date();
//        Date validDate2 = new Date();
//        SimpleDateFormat validSdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
//        try {
//            validDate1 = validSdf2.parse("2018-10-31T08:00:00+00:00");
//            validDate2 = validSdf2.parse("2017-12-30T06:00:00+00:00");
//        } catch (ParseException c) {
//            c.printStackTrace();
//        }
//        Sensor testSensor1 = new Sensor("Sensor1", "Sensor1", new TypeSensor("rain", "mm"), new Local(2, 3, 4),
//                validDate1);
//        Sensor testSensor2 = new Sensor("Sensor2", "Sensor2", new TypeSensor("rain2", "mm2"), new Local(2, 2, 2),
//                validDate2);
//        SensorList testListPortugal = new SensorList();
//        testListPortugal.add(testSensor1);
//        testArea.setSensorList(testListPortugal);
//        SensorList testListEspanha = new SensorList();
//        testListEspanha.add(testSensor2);
//        testArea2.setSensorList(testListEspanha);  //Portugal com sensor1 e Espanha com sensor2
//
//        CSVReader testRead = new CSVReader();
//        testRead.readAndSet(testGeoList);
//
//        ReadingList test = testSensor1.getReadingList();
//        ReadingList test2 = testSensor2.getReadingList();
//        int cenas = test.size();
//        int cenas2 = test2.size();
//        System.out.println(cenas + cenas2);  // = 29
//        //System.out.println(test.getMostRecentValue());
//        //System.out.println(test2.getMostRecentValue());
//    }
//

    /**
     * Reads a CSV file from any path the User chooses from. Adds readings that were made withing the active period of
     * a sensor to that same sensor ReadingList. Readings that are not possible to be added are displayed in a log file.
     *
     * @param geographicAreaList is the Geographic Area List of the application.
     * @author Andre
     */
    public void readAndSet(GeographicAreaList geographicAreaList) {
        startAndPromptPath();
        BufferedReader buffReader = null;
        FileReader fileReader = null;
        String line = "";
        String cvsSplit = ",";
        String[] readings;
        SensorList fullSensorList = getSensorData(geographicAreaList);
        try {
            fileReader = new FileReader(csvFileLocation);  //csvFileLocation : resources/csv/readings.csv (Or resources/csv/fakeCSV.csv)
            buffReader = new BufferedReader(fileReader);
            Logger logger = Logger.getLogger(CSVReader.class.getName());
            CustomFormatter myFormat = new CustomFormatter();

            FileHandler fileHandler = new FileHandler("./resources/logs/logOut.log");
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            while ((line = buffReader.readLine()) != null) {
                readings = line.split(cvsSplit);
                parseAndLog(readings, logger, fullSensorList);
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
    }

    // ACCESSORY METHODS

    /**
     * Adds a new Reading to a sensor with the date and value read from the CSV file, but only if that date is posterior
     * to the date when the sensor was activated.
     *
     * @param sensor is the sensor we want to add the reading to.
     * @param value  is the value read on the reading.
     * @param date   is the read date of the reading.
     * @return returns true if the reading was successfully added.
     * @author Andre
     */
    boolean setCSVReadings(Sensor sensor, Date date, Double value) {
        Date startingDate = sensor.getDateStartedFunctioning();
        if (date.after(startingDate) || date == startingDate) {
            Reading reading = new Reading(value, date);
            sensor.addReading(reading);
            return true;
        }
        return false;
    }


    /**
     * Gets the list of sensors that exist in the Geographic Area where the house is contained
     *
     * @param geographicAreaList is the application Geographic Area List.
     * @return returns a SensorList of the geographical area of the house.
     * @author Andre
     */
    SensorList getSensorData(GeographicAreaList geographicAreaList) {
        SensorList fullSensorList = new SensorList();
        for (GeographicArea ga : geographicAreaList.getElementsAsArray()) {
            for (Sensor sensor : ga.getSensorList().getElementsAsArray()) {
                fullSensorList.add(sensor);
            }
        }
        return fullSensorList;
    }

    /**
     * After reading a single line of the file, tries to parse the value, the date and the name to double, Date and
     * string respectively, and sees if the name of the sensor matches, and the reading is possible to add, else the
     * adding process fails and a log of the type 'warning' is generated.
     *
     * @param logger     is an instance of a logger.
     * @param readings   is an array of strings of all the parameters (each parameter separated by a comma),
     *                   in each line of the CSV file.
     * @param sensorList is the Sensor List containing all sensors from all the geographic areas in the Geographic Area List.
     * @author Andre
     */
    void parseAndLog(String[] readings, Logger logger, SensorList sensorList) {
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'"));

        for (SimpleDateFormat pattern : knownPatterns) { //TODO:  Runs both parses for all the lines, yet to find different approach.

            try {
                Double readValue = Double.parseDouble(readings[2]);
                String readID = readings[0];
                Date readDate = pattern.parse(readings[1]);
                for (Sensor sensor : sensorList.getElementsAsArray()) {
                    if (sensor.getId().equals(readID) && !setCSVReadings(sensor, readDate, readValue) && logger.isLoggable(Level.WARNING)) {
                        logger.warning("The reading with value " + readValue + " and date " + readDate + " could not be added to the sensor.");
                    }
                }
            } catch (NumberFormatException nfe) {
                System.out.println("The reading values are not numeric.");
                logger.warning("The reading values are not numeric.");
            } catch (ParseException c) {
                // System.out.println("The date format is not valid.");
            }
        }

    }

    /**
     * Method to get the path to the file from user input, only works if the file is a .csv file and it actually exists.
     * @author Andre
     */
    void startAndPromptPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the location of the CSV file");
        this.csvFileLocation = scanner.next();
        while (!csvFileLocation.endsWith(".csv") || !new File(csvFileLocation).exists()) {
            System.out.println("Please enter a valid location");
            this.csvFileLocation = scanner.next();
        }
    }
}
