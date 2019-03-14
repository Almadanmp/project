package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.io.ui.UtilsUI;
import pt.ipp.isep.dei.project.model.*;
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
     * @param  path is the path to the CSV File.
     * @author Andre
     */
    public void readAndSet(GeographicAreaList geographicAreaList, String path) {
        CSVReader csvRead = new CSVReader();
        List<String[]> list = csvRead.readCSV(path);
        SensorList fullSensorList = getSensorData(geographicAreaList);
        if (fullSensorList.isEmpty() || geographicAreaList.isEmpty()) {
            UtilsUI.printMessage("Please add a sensor first.");
            return;
        }
        try {
            Logger logger = Logger.getLogger(CSVReaderController.class.getName());
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler("./resources/logs/logOut.log");
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            for (String[] readings : list) {
                parseAndLog(readings,logger,fullSensorList);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    // ACCESSORY METHODS

    /**
     * Gets the list of sensors that exist in the Geographic Area where the house is contained
     *
     * @param geographicAreaList is the application Geographic Area List.
     * @return returns a SensorList of the geographical area of the house.
     * @author Andre
     */
    SensorList getSensorData(GeographicAreaList geographicAreaList) {
        SensorList fullSensorList = new SensorList();
        if (geographicAreaList.isEmpty()) {
            return fullSensorList;
        }
        for (GeographicArea ga : geographicAreaList.getElementsAsArray()) {
            if (ga.getSensorList().isEmpty()) {
                return fullSensorList;
            }
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
        for (SimpleDateFormat pattern : knownPatterns) {
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
                UtilsUI.printMessage("The reading values are not numeric.");
                logger.warning("The reading values are not numeric.");
            } catch (ParseException c) {
                // System.out.println("The date format is not valid.");
            }
        }
    }

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

}
