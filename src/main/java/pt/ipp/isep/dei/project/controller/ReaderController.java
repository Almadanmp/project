package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.SensorList;
import pt.ipp.isep.dei.project.reader.ReaderCSVReadings;
import pt.ipp.isep.dei.project.reader.CustomFormatter;
import pt.ipp.isep.dei.project.reader.ReaderXMLGeographicAreas;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderController {

    public int counter;

    /**
     * Reads a CSV file from any path the User chooses from. Adds readings that were made withing the active period of
     * a sensor to that same sensor ReadingList. Readings that are not possible to be added are displayed in a log file.
     *
     * @param geographicAreaList is the Geographic Area List of the application.
     * @param path               is the path to the CSV File.
     * @return true if was able to read and set, false otherwise
     * @author Andre
     */
    boolean readAndSetInternal(GeographicAreaList geographicAreaList, String path, String logPath) {
        ReaderCSVReadings csvRead = new ReaderCSVReadings();
        List<String[]> list = csvRead.readFile(path);
        SensorList fullSensorList = geographicAreaList.getAreaListSensors();
        this.counter = list.size();
        if (!fullSensorList.isEmpty()) {
            try {
                Logger logger = Logger.getLogger(ReaderController.class.getName());
                CustomFormatter myFormat = new CustomFormatter();
                FileHandler fileHandler = new FileHandler(logPath);
                logger.addHandler(fileHandler);
                fileHandler.setFormatter(myFormat);
                for (String[] readings : list) {
                    parseAndLog(readings, logger, fullSensorList);
                }
            } catch (IOException e) {
                return true;
            }
            return true;
        }
        return false;
    }

    /**
     * Reads a CSV file from any path the User chooses from. Adds readings that were made withing the active period of
     * a sensor to that same sensor ReadingList. Readings that are not possible to be added are displayed in a log file.
     * This method is separated from the above so we can test with different logPaths (wrong etc).
     *
     * @param geographicAreaList is the Geographic Area List of the application.
     * @param csvPath            is the path to the CSV File.
     * @param logPath            is the path to the log File.
     * @return true if was able to read and set, false otherwise
     */
    public boolean readAndSet(GeographicAreaList geographicAreaList, String csvPath, String logPath) {
        return readAndSetInternal(geographicAreaList, csvPath, logPath);
    }


    /**
     * Reads a XLM file from any path the User chooses from. Imports geographic areas and sensors from said file.
     *
     * @param list     is the Geographic Area List of the application.
     * @param filePath is the path to the XML file
     */
    public void readFileXML(String filePath, GeographicAreaList list) {
        ReaderXMLGeographicAreas reader = new ReaderXMLGeographicAreas();
        reader.readFileAndAdd(filePath, list);
    }


    // ACCESSORY METHODS

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
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                Double readValue = Double.parseDouble(readings[2]);
                String readID = readings[0];
                Date readDate = pattern.parse(readings[1]);
                if (logger.isLoggable(Level.WARNING) && !sensorList.addReadingToMatchingSensor(readID, readValue, readDate)) {
                    logger.warning("The reading with value " + readValue + " and date " + readDate + " could not be added to the sensor.");
                    this.counter--;
                }
            } catch (NumberFormatException nfe) {
                UtilsUI.printMessage("The reading values are not numeric.");
                logger.warning("The reading values are not numeric.");
            } catch (ParseException ignored) {
                ignored.getErrorOffset();
            }
        }
    }

    /**
     * This method goes receives a geographic area list, a string with a path to a CSV file and
     * a path to a logger. The CSV file contains readings that will be added to the corresponding
     * geographic area sensors. The readings that fail to be added will be added to log.
     *
     * @return the number of readings added to the geographic area sensors
     **/
    public int readReadingsFromCSV(GeographicAreaList geographicAreaList, String path, String logPath) {
        SensorList sensorList = geographicAreaList.getAreaListSensors();
        int counter = 0;
        if (sensorList.isEmpty()) {
            return counter;
        }
        ReaderCSVReadings csvRead = new ReaderCSVReadings();
        List<String[]> list = csvRead.readFile(path);
        try {
            Logger logger = Logger.getLogger(ReaderController.class.getName());
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler(logPath);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            for (String[] readings : list) {
                counter += parseAndLogReading(readings, logger, sensorList);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return counter;
    }

    /**
     * This method receives a logger, a sensor list and an array of strings, tries to add a reading
     * to a sensor in list and returns the number of readings added to sensor. The array of strings
     * contains the reading's attributes.
     *
     * @return 0 in case the reading was not added, 1 in case of success.
     ***/
    int parseAndLogReading(String[] readings, Logger logger, SensorList sensorList) {
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'"));
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                Double readValue = Double.parseDouble(readings[2]);
                String readID = readings[0];
                Date readDate = pattern.parse(readings[1]);
                if (logger.isLoggable(Level.WARNING) && sensorList.addReadingToMatchingSensor(readID, readValue, readDate)) {
                    return 1;
                }
                logger.warning("The reading with value " + readValue + " and date " + readDate + " could not be added to the sensor.");
            } catch (NumberFormatException nfe) {
                UtilsUI.printMessage("The reading values are not numeric.");
                logger.warning("The reading values are not numeric.");
                return 0;
            } catch (ParseException ignored) {
                ignored.getErrorOffset();
            }
        }
        return 0;
    }

    public int readReadingsFromJSON(GeographicAreaList geographicAreaList, String path, String logPath) {
        SensorList sensorList = geographicAreaList.getAreaListSensors();
        if (sensorList.isEmpty()) {
            return 0;
        }
        return 1;
    }

    public int readReadingsFromXML(GeographicAreaList geographicAreaList, String path, String logPath) {
        SensorList sensorList = geographicAreaList.getAreaListSensors();
        if (sensorList.isEmpty()) {
            return 0;
        }
        return 1;
    }
}
