package pt.ipp.isep.dei.project.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.HouseDTO;
import pt.ipp.isep.dei.project.dto.ReadingDTOWithUnitAndSensorID;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.reader.*;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.units.Unit;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ReaderController {


    private AreaSensorService areaSensorService;
    private HouseService houseService;

    private static final String INVALID_DATE = "The reading date format is invalid.";
    private static final String INVALID_READING_VALUE = "The reading values are not numeric.";
    private static final String VALID_DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss'+00:00'";
    private static final String VALID_DATE_FORMAT2 = "dd/MM/yyyy";
    private static final String VALID_DATE_FORMAT3 = "yyyy-MM-dd";

    public ReaderController(AreaSensorService areaSensorService) {

        this.areaSensorService = areaSensorService;
    }

    //
    // USER STORY 15v2 - As an Administrator, I want to import geographical areas and sensors from a JSON or XML file.

    /**
     * This method only accepts a path that ends with .json or .xml
     *
     * @param input    - the user input
     * @param filePath - the path to the file if it exists
     * @param list     - the geographic area list
     * @return - number of geoareas imported
     */
    public int acceptPath(String input, String filePath, GeographicAreaList list) {
        int areasRead;
        if (input.endsWith(".json")) {
            ReaderJSONGeographicAreas readerJSON = new ReaderJSONGeographicAreas();
            areasRead = readerJSON.readJSONFileAndAddGeoAreas(filePath, list, areaSensorService);
            return areasRead;
        }
        if (input.endsWith(".xml")) {
            ReaderXMLGeoArea readerXML = new ReaderXMLGeoArea();
            areasRead = readerXML.readFileXMLAndAddAreas(filePath, list, areaSensorService);
            return areasRead;
        }
        return -1;
    }

    /**
     * This method reads a JSON file that represents the class House() and sets House attributes from the file and
     * saves it into the repository.
     *
     * @param filePath is the file path.
     * @return true if the House was successfully saved in the repository, false otherwise.
     */
    public boolean readJSONAndDefineHouse(String filePath) {
        ReaderJSONHouse readerJSONHouse = new ReaderJSONHouse();
        HouseDTO houseDTO = readerJSONHouse.readFile(filePath);
        House house = HouseMapper.dtoToObject(houseDTO);
        return houseService.saveHouse(house);
    }

    /**
     * This method receives a list of Geographic Areas to add the given NodeList correspondent to the Geographic Areas
     * imported from the XML File.
     *
     * @param nListGeoArea - NodeList imported from the XML.
     * @param list         - list to which we want to add and persist the Geographic areas.
     * @return - the number of geographic areas imported.
     */
    public int addGeoAreaNodeListToList(NodeList nListGeoArea, GeographicAreaList list, AreaSensorService areaSensorService) {
        ReaderXMLGeoArea readerXML = new ReaderXMLGeoArea();
        int result = 0;
        for (int i = 0; i < nListGeoArea.getLength(); i++) {
            if (readerXML.readGeographicAreasXML(nListGeoArea.item(i), list, areaSensorService)) {
                result++;
            }
        }
        return result;
    }

    public List<GeographicAreaDTO> readFileJSONGeoAreas(String filePath) {
        GeographicAreaReaderJSON readerJSON = new GeographicAreaReaderJSON();
        return readerJSON.readFile(filePath);
    }

    public int addGeoAreasDTOToList(List<GeographicAreaDTO> geographicAreaDTOS, GeographicAreaList list) {
        int counter = 0;
        for (GeographicAreaDTO dto : geographicAreaDTOS) {
            list.addAndPersistGA(GeographicAreaMapper.dtoToObject(dto));
            System.out.println(GeographicAreaMapper.dtoToObject(dto).toString());
            counter++;
        }
        return counter;
    }

    /**
     * This method goes receives a geographic area list, a string with a path to a CSV file and
     * a string path to a logger. The CSV file contains readings that will be added to the corresponding
     * geographic area sensors. The readings that fail to be added will be added to log.
     *
     * @return the number of readings added to the geographic area sensors
     **/
    public int readReadingsFromCSV(GeographicAreaList geographicAreaList, String path, String logPath) {
        AreaSensorService areaSensorService2 = geographicAreaList.getAreaListSensors();
        int addedReadings = 0;
        if (areaSensorService2.isEmpty()) {
            return addedReadings;
        }
        ReaderCSV csvRead = new ReaderCSV();
        List<String[]> list = csvRead.readFile(path);
        try {
            Logger logger = Logger.getLogger(ReaderController.class.getName());
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler(logPath);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            for (String[] reading : list) {
                addedReadings += parseAndLogCSVReading(reading, logger);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return addedReadings;
    }

    /**
     * This method receives a logger, a sensor list and an array of strings, tries to addWithoutPersisting a reading
     * to a sensor in list and returns the number of readings added toM sensor. The array of strings
     * contains the reading's attributes.
     *
     * @return 0 in case the reading was not added, 1 in case of success.
     ***/
    int parseAndLogCSVReading(String[] readings, Logger logger) {
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        knownPatterns.add(new SimpleDateFormat(VALID_DATE_FORMAT1));
        knownPatterns.add(new SimpleDateFormat(VALID_DATE_FORMAT2));
        knownPatterns.add(new SimpleDateFormat(VALID_DATE_FORMAT3));
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                String sensorID = readings[0];
                Date readingDate = pattern.parse(readings[1]);
                Double readingValue = Double.parseDouble(readings[2]);
                String readingUnit = readings[3];
                return addReadingToMatchingSensor(logger, sensorID, readingValue, readingDate, readingUnit);
            } catch (NumberFormatException nfe) {
                logger.warning(INVALID_READING_VALUE);
                return 0;
            } catch (ParseException expected) {
                expected.getErrorOffset();
            }
        }
        logger.warning(INVALID_DATE);
        return 0;
    }

    /**
     * This method receives a geographic area list, a file path to JSON file and a file path to a log.
     * The method will readSensors the JSON file, try to parse every reading and try to addWithoutPersisting them to the
     * corresponding sensor from its corresponding geographic area. The readings that fail to be added
     * will be added to log.
     *
     * @return the total number of readings added
     ***/
    public int readReadingsFromJSON(GeographicAreaList geographicAreaList, String path, String logPath) {
        int addedReadings = 0;
        AreaSensorService areaSensorService = geographicAreaList.getAll().getAreaListSensors();
        if (areaSensorService.isEmpty()) {
            return addedReadings;
        }
        ReaderJSONReadings reader = new ReaderJSONReadings();
        JSONArray array = reader.readFile(path);
        try {
            Logger logger = Logger.getLogger(ReaderController.class.getName());
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler(logPath);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            addedReadings = parseAndLogJSONReadings(array, logger);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return addedReadings;
    }

    /**
     * This method receives a logger, a sensor list and a JSONArray, tries to parse every JSON Object
     * into a reading and adding it to its corresponding sensor from geographic area.
     *
     * @return the number of readings added to geographic area sensors
     ***/
    int parseAndLogJSONReadings(JSONArray array, Logger logger) {
        int added = 0;
        for (int i = 0; i < array.length(); i++) {
            JSONObject readingObject = array.getJSONObject(i);
            added += parseAndLogJSONReading(readingObject, logger);
        }
        return added;
    }

    /**
     * This method receives a logger, a sensor list and a JSON Object, tries to addWithoutPersisting the corresponding
     * reading to the corresponding sensor.
     *
     * @return returns 1 in case the reading is added, 0 otherwise
     ***/
    int parseAndLogJSONReading(JSONObject reading, Logger logger) {
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        knownPatterns.add(new SimpleDateFormat(VALID_DATE_FORMAT1));
        knownPatterns.add(new SimpleDateFormat(VALID_DATE_FORMAT2));
        knownPatterns.add(new SimpleDateFormat(VALID_DATE_FORMAT3));
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                String sensorID = reading.getString("id");
                Date readingDate = pattern.parse(reading.getString("timestamp/date"));
                Double readingValue = Double.parseDouble(reading.getString("value"));
                String readingUnit = reading.getString("unit");
                return addReadingToMatchingSensor(logger, sensorID, readingValue, readingDate, readingUnit);
            } catch (NumberFormatException nfe) {
                logger.warning(INVALID_READING_VALUE);
                return 0;
            } catch (ParseException ok) {
                ok.getErrorOffset();
            }
        }
        logger.warning(INVALID_DATE);
        return 0;
    }

    /**
     * This method receives a logger, a sensor list, and reading features (sensor Id, reading value, reading date)
     * and tries to addWithoutPersisting the corresponding reading to the sensor list.
     *
     * @return 1 in case the reading is added, 0 in case the reading isn't added.
     **/
    int addReadingToMatchingSensor(Logger logger, String sensorID, Double readingValue, Date readingDate, String readingUnit) {
        if (areaSensorService.addReadingToMatchingSensor(sensorID, readingValue, readingDate, readingUnit)) {
            return 1;
        }
        String message = "The reading with value " + readingValue + " from " + readingDate + " could not be added to the sensor.";
        logger.warning(message);
        return 0;
    }

    /**
     * This method receives a geographic area list, a file path to XML file and a file path to a log.
     * The method will readSensors the XML file, try to parse every reading and try to addWithoutPersisting them to the
     * corresponding sensor from its corresponding geographic area. The readings that fail to be added
     * will be added to log.
     *
     * @return the total number of readings added
     ***/
    public int readReadingsFromXML(GeographicAreaList geographicAreaList, String path, String logPath) {
        int addedReadings = 0;
        AreaSensorService areaSensorService = geographicAreaList.getAll().getAreaListSensors();
        if (areaSensorService.isEmpty()) {
            return addedReadings;
        }
        ReaderXML reader = new ReaderXML();
        Document doc = reader.readFile(path);
        doc.getDocumentElement().normalize();
        try {
            Logger logger = Logger.getLogger(ReaderController.class.getName());
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler(logPath);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            addedReadings = parseAndLogXMLReadings(doc, logger);

        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return addedReadings;
    }

    /**
     * This method receives a logger, a sensor list and a Document, tries to parse every node
     * into a reading and adding it to its corresponding sensor from geographic area.
     *
     * @return the number of readings added to geographic area sensors
     ***/
    private int parseAndLogXMLReadings(Document doc, Logger logger) {
        int added = 0;
        NodeList nodeReadings = doc.getElementsByTagName("reading");
        for (int i = 0; i < nodeReadings.getLength(); i++) {
            Node node = nodeReadings.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                added += parseAndLogXMLReading(element, logger);
            }
        }
        return added;
    }

    /**
     * This method receives a logger, a sensor list, and an Element containing
     * reading features (sensor Id, reading value, reading date)
     * and tries to addWithoutPersisting the resulting reading into the corresponding sensor.
     *
     * @return 1 in case the reading is added, 0 in case the reading isn't added.
     **/
    private int parseAndLogXMLReading(Element element, Logger logger) {
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        knownPatterns.add(new SimpleDateFormat(VALID_DATE_FORMAT1));
        knownPatterns.add(new SimpleDateFormat(VALID_DATE_FORMAT2));
        knownPatterns.add(new SimpleDateFormat(VALID_DATE_FORMAT3));
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                String sensorID = element.getElementsByTagName("id").item(0).getTextContent();
                Date readingDate = pattern.parse(element.getElementsByTagName("timestamp_date").item(0).getTextContent());
                Double readingValue = Double.parseDouble(element.getElementsByTagName("value").item(0).getTextContent());
                String readingUnit = element.getElementsByTagName("unit").item(0).getTextContent();
                return addReadingToMatchingSensor(logger, sensorID, readingValue, readingDate, readingUnit);
            } catch (NumberFormatException nfe) {
                logger.warning(INVALID_READING_VALUE);
                return 0;
            } catch (ParseException ok) {
                ok.getErrorOffset();
            }
        }
        logger.warning(INVALID_DATE);
        return 0;
    }

    /**
     * This method will receive a List of AreaReadingDTOs and a log file path and will try to add
     * every reading in the list to its corresponding area sensor.
     * The method returns the number of readings that were correctly added and logs every readings
     * that wasn't added.
     *
     * @param readings List of Area Reading DTOs
     * @param logPath  log file path
     * @return number of Area Readings added to corresponding Area Sensor
     **/
    public int addReadingsToGeographicAreaSensors(List<ReadingDTOWithUnitAndSensorID> readings, String logPath) {
        Logger logger = getLogger(logPath);
        int addedReadings = 0;
        for (ReadingDTOWithUnitAndSensorID r : readings) {
            String sensorID = r.getSensorID();
            double value = r.getValue();
            Date date = r.getDate();
            Unit unit = r.getUnit();
            if (areaSensorService.addAreaReadingToAreaSensor(sensorID, value, date, unit, logger)) {
                addedReadings++;
            }
        }
        return addedReadings;
    }

    /**
     * This method creates a Logger.
     *
     * @param logPath log file path.
     * @return object of class Logger.
     **/
    private Logger getLogger(String logPath) {
        Logger logger = Logger.getLogger(ReaderController.class.getName());
        try {
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler(logPath);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
        } catch (IOException io) {
            io.getMessage();
        }
        return logger;
    }
}
