package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.logger.CustomFormatter;
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

public class CSVReader {

    private String csvFileLocation;


    // TEST METHOD MOCK

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
        testRead.startAndPromptPath();
        testRead.getCSVReadings(testHouse);

        ReadingList test = testSensor1.getReadingList();
        ReadingList test2 = testSensor2.getReadingList();
        int cenas = test.size();
        int cenas2 = test2.size();
        System.out.println(cenas + cenas2);
    }


    public void startAndPromptPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the location of the CSV file");
        this.csvFileLocation = scanner.next();
        while (!csvFileLocation.endsWith(".csv") || !new File(csvFileLocation).exists()) {
            System.out.println("Please enter a valid location");
            this.csvFileLocation = scanner.next();
        }
    }

    public void getCSVReadings(House house) {
        BufferedReader buffReader = null;
        FileReader fileReader = null;
        String line = "";
        String cvsSplit = ",";
        String[] readings;
        Date readDate;
        Double readValue;
        String readName;
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
                try {
                    readValue = Double.parseDouble(readings[3]);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
                    readDate = sdf.parse(readings[1]);
                    readName = readings[0];
                    for (Sensor sensor : sensorList.getElementsAsArray()) {
                        if (sensor.getName().equals(readName)) {
                            // System.out.println(sensor.getName());
                            if (!setCSVReadings(sensor, readDate, readValue)) {
                                if (logger.isLoggable(Level.WARNING)) {
                                    logger.warning("The reading with value " + readValue + " and date " + readDate + " could not be added to the sensor.");
                                }
                            }
                        }
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("The reading values are not numeric.");
                    logger.warning("The reading values are not numeric.");
                } catch (ParseException c) {
                    c.printStackTrace();
                }
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

    private boolean setCSVReadings(Sensor sensor, Date date, Double value) {
        Date startingDate = sensor.getDateStartedFunctioning();
        if (date.after(startingDate) || date == startingDate) {
            Reading reading = new Reading(value, date);
            sensor.addReading(reading);
            return true;
        }
        return false;
    }

    private SensorList getSensorData(House house) {
        GeographicArea geoArea = house.getMotherArea();
        SensorList sensorList = geoArea.getSensorList();
        return sensorList;
    }
}
