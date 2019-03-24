package pt.ipp.isep.dei.project.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.SensorList;
import pt.ipp.isep.dei.project.reader.*;

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
     * a string path to a logger. The CSV file contains readings that will be added to the corresponding
     * geographic area sensors. The readings that fail to be added will be added to log.
     *
     * @return the number of readings added to the geographic area sensors
     **/
    public int readReadingsFromCSV(GeographicAreaList geographicAreaList, String path, String logPath) {
        SensorList sensorList = geographicAreaList.getAreaListSensors();
        int addedReadings = 0;
        if (sensorList.isEmpty()) {
            return addedReadings;
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
                addedReadings += parseAndLogCSVReading(readings, logger, sensorList);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return addedReadings;
    }

    /**
     * This method receives a logger, a sensor list and an array of strings, tries to add a reading
     * to a sensor in list and returns the number of readings added to sensor. The array of strings
     * contains the reading's attributes.
     *
     * @return 0 in case the reading was not added, 1 in case of success.
     ***/
    int parseAndLogCSVReading(String[] readings, Logger logger, SensorList sensorList) {
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'"));
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                String sensorID = readings[0];
                Date readingDate = pattern.parse(readings[1]);
                Double readingValue = Double.parseDouble(readings[2]);
                return addReadingToMatchingSensor(logger, sensorList, sensorID, readingValue, readingDate);
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

    /**
     * This method receives a geographic area list, a file path to JSON file and a file path to a log.
     * The method will read the JSON file, try to parse every reading and try to add them to the
     * corresponding sensor from its corresponding geographic area. The readings that fail to be added
     * will be added to log.
     *
     * @return the total number of readings added
     ***/
    public int readReadingsFromJSON(GeographicAreaList geographicAreaList, String path, String logPath) {
        int addedReadings = 0;
        SensorList sensorList = geographicAreaList.getAreaListSensors();
        if (sensorList.isEmpty()) {
            return addedReadings;
        }
        ReaderJSONReadings reader = new ReaderJSONReadings();
        JSONArray readings = reader.readFile(path);
        try {
            Logger logger = Logger.getLogger(ReaderController.class.getName());
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler(logPath);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            addedReadings = parseAndLogJSONReadings(sensorList, readings, logger);

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        return addedReadings;
    }

    /**
     * This method receives a logger, a sensor list and a JSONArray, tries to parse every JSON Object
     * into a reading and adding it to its corresponding sensor from geographic area.
     *
     * @return the number of readings added to geographic area sensors
     ***/
    int parseAndLogJSONReadings(SensorList sensorList, JSONArray readings, Logger logger) {
        int added = 0;
        for (int i = 0; i < readings.length(); i++) {
            JSONObject readingObject = readings.getJSONObject(i);
            added += parseAndLogJSONReading(sensorList, readingObject, logger);
        }
        return added;
    }

    /**
     * This method receives a logger, a sensor list and a JSON Object, tries to add the corresponding
     * reading to the corresponding sensor.
     *
     * @return returns 1 in case the reading is added, 0 otherwise
     ***/
    int parseAndLogJSONReading(SensorList sensorList, JSONObject reading, Logger logger) {
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'"));
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                String sensorID = reading.getString("id");
                Date readingDate = pattern.parse(reading.getString("timestamp/date"));
                Double readingValue = Double.parseDouble(reading.getString("value"));
                return addReadingToMatchingSensor(logger, sensorList, sensorID, readingValue, readingDate);
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

    /**
     * This method receives a logger, a sensor list, and reading features (sensor Id, reading value, reading date)
     * and tries to add the corresponding reading to the sensor list.
     *
     * @return 1 in case the reading is added, 0 in case the reading isn't added.
     **/
    int addReadingToMatchingSensor(Logger logger, SensorList sensorList, String sensorID, Double readingValue, Date readingDate) {
        if (logger.isLoggable(Level.WARNING) && sensorList.addReadingToMatchingSensor(sensorID, readingValue, readingDate)) {
            return 1;
        }
        logger.warning("The reading with value " + readingValue + " and date " + readingDate + " could not be added to the sensor.");
        return 0;
    }

    /**
     * This method receives a geographic area list, a file path to XML file and a file path to a log.
     * The method will read the XML file, try to parse every reading and try to add them to the
     * corresponding sensor from its corresponding geographic area. The readings that fail to be added
     * will be added to log.
     *
     * @return the total number of readings added
     ***/
    public int readReadingsFromXML(GeographicAreaList geographicAreaList, String path, String logPath) {
        int addedReadings = 0;
        SensorList sensorList = geographicAreaList.getAreaListSensors();
        if (sensorList.isEmpty()) {
            return addedReadings;
        }
        ReaderXMLReadings reader = new ReaderXMLReadings();
        Document doc = reader.readFile(path);
        doc.getDocumentElement().normalize();
        try {
            Logger logger = Logger.getLogger(ReaderController.class.getName());
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler(logPath);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            addedReadings = parseAndLogXMLReadings(sensorList, doc, logger);

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        return addedReadings;
    }

    /**
     * This method receives a logger, a sensor list and a Document, tries to parse every node
     * into a reading and adding it to its corresponding sensor from geographic area.
     *
     * @return the number of readings added to geographic area sensors
     ***/
    int parseAndLogXMLReadings(SensorList sensorList, Document doc, Logger logger) {
        int added = 0;
        NodeList nodeReadings = doc.getElementsByTagName("reading");
        for(int i = 0; i < nodeReadings.getLength(); i++) {
            Node node = nodeReadings.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;
                added += parseAndLogXMLReading(sensorList, element, logger);
            }
        }
        return added;
    }

    /**
     * This method receives a logger, a sensor list, and an Element containing
     * reading features (sensor Id, reading value, reading date)
     * and tries to add the resulting reading into the corresponding sensor.
     *
     * @return 1 in case the reading is added, 0 in case the reading isn't added.
     **/
    int parseAndLogXMLReading(SensorList sensorList, Element element, Logger logger) {
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'"));
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                String sensorID = element.getElementsByTagName("id").item(0).getTextContent();
                Date readingDate = pattern.parse(element.getElementsByTagName("timestamp_date").item(0).getTextContent());
                Double readingValue = Double.parseDouble(element.getElementsByTagName("value").item(0).getTextContent());
                return addReadingToMatchingSensor(logger, sensorList, sensorID, readingValue, readingDate);
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
}
