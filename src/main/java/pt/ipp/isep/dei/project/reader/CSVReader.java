package pt.ipp.isep.dei.project.reader;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.config.DeviceTypeConfig;

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
public class CSVReader implements Reader{

    private String csvFileLocation;


    // TEST METHOD MOCK - Take out of comment to test
    /*
    public static void main(String[] args) {
        List<String> deviceTypeConfig;
        try {
            DeviceTypeConfig devTConfig = new DeviceTypeConfig();
            deviceTypeConfig = devTConfig.getDeviceTypeConfig();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        GeographicArea testArea = new GeographicArea("portugal", new TypeArea("pais"), 34000, 300000,
                new Local(3, 3, 3));
        House testHouse = new House("casa", new Address("sta", "540", "Porto"),
                new Local(3, 3, 3), testArea, 15, 15, deviceTypeConfig);
        Date validDate1 = new Date();
        Date validDate2 = new Date();
        SimpleDateFormat validSdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate1 = validSdf2.parse("2018-10-31T08:00:00+00:00");
            validDate2 = validSdf2.parse("2017-12-30T06:00:00+00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        Sensor testSensor1 = new Sensor("Sensor1", new TypeSensor("rain", "mm"), new Local(2, 3, 4),
                validDate1);
        Sensor testSensor2 = new Sensor("Sensor2", new TypeSensor("rain2", "mm2"), new Local(2, 2, 2),
                validDate2);
        SensorList testList = new SensorList();
        testList.add(testSensor1);
        testList.add(testSensor2);
        testArea.setSensorList(testList);

        CSVReader testRead = new CSVReader();
        testRead.readAndSet(testHouse);

        ReadingList test = testSensor1.getReadingList();
        ReadingList test2 = testSensor2.getReadingList();
        int cenas = test.size();
        int cenas2 = test2.size();
        System.out.println(cenas + cenas2);
        System.out.println(test.getMostRecentValue());
        System.out.println(test2.getMostRecentValue());
    }
    */

    /**
     * Reads a CSV file from any path the User chooses from. Adds readings that were made withing the active period of
     * a sensor to that same sensor ReadingList. Readings that are not possible to be added are displayed in a log file.
     *
     * @param house is the House of the application.
     */
    public void readAndSet(House house) {
        startAndPromptPath();
        BufferedReader buffReader = null;
        FileReader fileReader = null;
        String line = "";
        String cvsSplit = ",";
        String[] readings;
        SensorList sensorList = getSensorData(house);
        try {
            fileReader = new FileReader(csvFileLocation);  //csvFileLocation : resources/csv/readings.csv (Or resources/csv/fakeCSV.csv)
            buffReader = new BufferedReader(fileReader);
            Logger logger = Logger.getLogger(CSVReader.class.getName());
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler("./resources/logs/LogOutput.log");
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            while ((line = buffReader.readLine()) != null) {
                readings = line.split(cvsSplit);
                parseAndLog(readings, logger, sensorList);
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
     */
    private boolean setCSVReadings(Sensor sensor, Date date, Double value) {
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
     * @param house is the application house.
     * @return returns a SensorList of the geographical area of the house.
     */
    private SensorList getSensorData(House house) {
        GeographicArea geoArea = house.getMotherArea();
        SensorList sensorList = geoArea.getSensorList();
        return sensorList;
    }

    /**
     * After reading a single line of the file, tries to parse the value, the date and the name to double, Date and
     * string respectively, and sees if the name of the sensor matches, and the reading is possible to add, else the
     * adding process fails and a log of the type 'warning' is generated.
     *
     * @param logger     is an instance of a logger.
     * @param readings   is an array of strings of all the parameters (each parameter separated by a comma),
     *                   in each line of the CSV file.
     * @param sensorList is the sensorList of the geographic area of the house.
     */
    private void parseAndLog(String[] readings, Logger logger, SensorList sensorList) {
        try {
            Double readValue = Double.parseDouble(readings[3]);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
            Date readDate = sdf.parse(readings[1]);
            String readName = readings[0];
            for (Sensor sensor : sensorList.getElementsAsArray()) {
                if (sensor.getName().equals(readName) && !setCSVReadings(sensor, readDate, readValue) && logger.isLoggable(Level.WARNING)) {
                    logger.warning("The reading with value " + readValue + " and date " + readDate + " could not be added to the sensor.");
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("The reading values are not numeric.");
            logger.warning("The reading values are not numeric.");
        } catch (ParseException c) {
            c.printStackTrace();
        }
    }

    /**
     * Method to get the path to the file from user input, only works if the file is a .csv file and it actually exists.
     */
    private void startAndPromptPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the location of the CSV file");
        this.csvFileLocation = scanner.next();
        while (!csvFileLocation.endsWith(".csv") || !new File(csvFileLocation).exists()) {
            System.out.println("Please enter a valid location");
            this.csvFileLocation = scanner.next();
        }
    }
}
