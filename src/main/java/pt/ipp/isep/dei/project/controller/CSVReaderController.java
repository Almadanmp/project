package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.io.ui.UtilsUI;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.Sensor;
import pt.ipp.isep.dei.project.model.SensorList;
import pt.ipp.isep.dei.project.reader.CSVReader;
import pt.ipp.isep.dei.project.reader.CustomFormatter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVReaderController {

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
        CSVReader csvRead = new CSVReader();
        List<String[]> list = csvRead.readCSV(path);
        SensorList fullSensorList = geographicAreaList.getAreaListSensors();
        if (!fullSensorList.isEmpty()) { //TODO Remove check on geographicAreaList. Not Necessary
            try {
                Logger logger = Logger.getLogger(CSVReaderController.class.getName());
                CustomFormatter myFormat = new CustomFormatter();
                FileHandler fileHandler = new FileHandler(logPath);
                logger.addHandler(fileHandler);
                fileHandler.setFormatter(myFormat);
                for (String[] readings : list) {
                    parseAndLog(readings, logger, fullSensorList);
                }
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reads a CSV file from any path the User chooses from. Adds readings that were made withing the active period of
     * a sensor to that same sensor ReadingList. Readings that are not possible to be added are displayed in a log file.
     * This method is separated from the above so we can test with different logPaths (wrong etc).
     * @param geographicAreaList is the Geographic Area List of the application.
     * @param csvPath is the path to the CSV File.
     * @param logPath is the path to the log File.
     * @return true if was able to read and set, false otherwise
     */
    public boolean readAndSet(GeographicAreaList geographicAreaList, String csvPath, String logPath) {
        return readAndSetInternal(geographicAreaList, csvPath, logPath);
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
                }
            } catch (NumberFormatException nfe) {
                UtilsUI.printMessage("The reading values are not numeric.");
                logger.warning("The reading values are not numeric.");
            } catch (ParseException ignored) {
                ignored.getErrorOffset();
            }
        }
    }
}
