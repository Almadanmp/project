package pt.ipp.isep.dei.project.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;
import pt.ipp.isep.dei.project.reader.*;
import pt.ipp.isep.dei.project.services.SensorService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ReaderController {

    @Autowired
    SensorService sensorService;

    private static final String INVALID_DATE = "The reading date format is invalid.";
    private static final String INVALID_READING_VALUE = "The reading values are not numeric.";
    private static final String VALID_DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss'+00:00'";
    private static final String VALID_DATE_FORMAT2 = "dd/MM/yyyy";
    private static final String VALID_DATE_FORMAT3 = "yyyy-MM-dd";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ALTITUDE = "altitude";

    public ReaderController(SensorService service) {
        this.sensorService = service;
    }


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
            areasRead = this.readJSONFileAndAddGeoAreas(filePath, list);
            return areasRead;
        }
        if (input.endsWith(".xml")) {
            areasRead = this.readFileXMLAndAddAreas(filePath, list);
            return areasRead;
        }
        return -1;
    }

    /**
     * This method reads a .json file from its absolute filepath and returns an array of DTO objects formed
     * from the data in the file.
     *
     * @param filePath is the absolute filepath of the .json file in the system.
     * @param list     is the list of Geographic areas that comes from Main, since we still don't have a database, to which
     *                 we want to addWithoutPersisting the imported geographic areas.
     * @return is an array of data transfer geographic area objects created with the data in the .json file.
     */

    public int readJSONFileAndAddGeoAreas(String filePath, GeographicAreaList list) {
        ReaderJSONGeographicAreas reader = new ReaderJSONGeographicAreas();
        JSONArray geoAreas = reader.readFile(filePath);
        GeographicArea[] geographicAreasArray;
        geographicAreasArray = readGeoAreasJSON(geoAreas);
        return addGeoAreasToList(geographicAreasArray, list);
    }


    /**
     * The given method receives a list of geographic areas and calls mapper to convert every DTO created upon reading
     * the json file, before adding the newly created Geographic Areas (and their sensors) to the list.
     *
     * @param fileAreas is the list of Geographic Area DTOs created by reading a given .json file.
     * @param list      comes from mainUI because there is no database yet. Is the program's static list of geographic areas.
     */
    int addGeoAreasToList(GeographicArea[] fileAreas, GeographicAreaList list) {
        int result = 0;
        for (GeographicArea area : fileAreas) {
            if (list.addAndPersistGA(area)) {
                result++;
            }
        }
        return result;
    }

    /**
     * Is the method that loads geographic areas from the .json file.
     *
     * @param geoAreas is the JSONArray corresponding to the list of geographic areas in the .json file.
     * @return is an array of data transfer geographic area objects created with the data in the JSON Array provided.
     */

    private GeographicArea[] readGeoAreasJSON(JSONArray geoAreas) {
        GeographicArea[] geographicAreasArray = new GeographicArea[geoAreas.length()];
        for (int i = 0; i < geoAreas.length(); i++) {
            JSONObject area = geoAreas.getJSONObject(i);
            JSONObject local = geoAreas.getJSONObject(i).getJSONObject("location");
            String areaID = area.getString("id");
            String areaDescription = area.getString("description");
            TypeArea areaType = new TypeArea(area.getString("type"));
            double areaWidth = area.getDouble("width");
            double areaLength = (area.getDouble("length"));
            double areaLatitude = local.getDouble(LATITUDE);
            double areaLongitude = local.getDouble(LONGITUDE);
            double areaAltitude = local.getDouble(ALTITUDE);
            Local location = new Local(areaLatitude, areaLongitude, areaAltitude);
            GeographicArea areaObject = new GeographicArea(areaID, areaType, areaWidth, areaLength, location);
            areaObject.setDescription(areaDescription);
            geographicAreasArray[i] = areaObject;
            JSONArray areaSensors = area.getJSONArray("area_sensor");
            SensorList areaSensorsList = readAreaSensorsJSON(areaSensors);
            areaObject.setSensorList(areaSensorsList);
        }
        return geographicAreasArray;
    }

    /**
     * Is the method that loads sensors from a list of sensors in the .json file.
     *
     * @param areaSensors is the array of sensors contained in the .json file, usually the list of sensors
     *                    that belong to an area.
     * @return is an array of data transfer sensor objects created with the data in the given JSON Array.
     */

    private SensorList readAreaSensorsJSON(JSONArray areaSensors) {
        List<Sensor> result = new ArrayList<>();
        SensorList sensorListObject = new SensorList();
        int entriesChecked = 0;
        while (entriesChecked < areaSensors.length()) {
            JSONObject areaSensor = areaSensors.getJSONObject(entriesChecked);
            JSONObject sensor = areaSensor.getJSONObject("sensor");
            String sensorId = sensor.getString("id");
            String sensorName = sensor.getString("name");
            String sensorDate = sensor.getString("start_date");
            SimpleDateFormat validDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                date = validDateFormat.parse(sensorDate);
            } catch (ParseException c) {
                entriesChecked++;
                continue;
            }
            String sensorType = sensor.getString("type");
            String sensorUnits = sensor.getString("units");
            TypeSensor type = new TypeSensor(sensorType, sensorUnits);
            JSONObject sensorLocal = areaSensor.getJSONObject("location");
            double sensorLatitude = sensorLocal.getDouble(LATITUDE);
            double sensorLongitude = sensorLocal.getDouble(LONGITUDE);
            double sensorAltitude = sensorLocal.getDouble(ALTITUDE);
            Local local = new Local(sensorLatitude,
                    sensorLongitude, sensorAltitude);
            Sensor sensorObject = new Sensor(sensorId, sensorName, type, local, date);
            sensorObject.setSensorList(sensorListObject);
            result.add(sensorObject);
            entriesChecked++;
        }
        sensorListObject.setSensors(result);
        return sensorListObject;
    }

    /**
     * reads a XML file from a certain path and imports geographic areas and sensors from the file
     *
     * @param filePath path to the xml file
     * @param list     geographic area list to addWithoutPersisting the imported geographic areas
     */
    public int readFileXMLAndAddAreas(String filePath, GeographicAreaList list) {
        ReaderXML reader = new ReaderXML();
        int result = 0;
        Document doc = reader.readFile(filePath);
        doc.getDocumentElement().normalize();
        NodeList nListGeoArea = doc.getElementsByTagName("geographical_area");
        for (int i = 0; i < nListGeoArea.getLength(); i++) {
            if (list.addAndPersistGA(readGeographicAreasXML(nListGeoArea.item(i)))) {
                result++;
            }
        }
        return result;
    }

    /**
     * Method to import a Geographic Area from a certain node
     *
     * @param node - node of the XML file
     * @return - Geographic Area that exists in the node
     */
    private GeographicArea readGeographicAreasXML(Node node) {
        GeographicArea geoArea = new GeographicArea();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String description = getTagValue("description", element);
            String id = getTagValue("id", element);
            double length = Double.parseDouble(getTagValue("length", element));
            double width = Double.parseDouble(getTagValue("width", element));
            Local local = new Local(Double.parseDouble(getTagValue(LATITUDE, element)),
                    Double.parseDouble(getTagValue(LONGITUDE, element)),
                    Double.parseDouble(getTagValue(ALTITUDE, element)));
            TypeArea typeArea = new TypeArea(getTagValue("type", element));
            geoArea = new GeographicArea(id, typeArea, length, width, local);
            geoArea.setDescription(description);
            NodeList nListSensor = element.getElementsByTagName("sensor");
            SensorList sensorList = new SensorList();
            for (int j = 0; j < nListSensor.getLength(); j++) {
                sensorList.add(readSensorsXML(nListSensor.item(j), sensorList));
            }
            geoArea.setSensorList(sensorList);


        }
        return geoArea;
    }

    /**
     * Method to import a Sensor from a certain node
     *
     * @param node - node of the XML file.
     * @return - Sensor that exists in the node
     */
    private Sensor readSensorsXML(Node node, SensorList sensorList) {
        Sensor sensor = new Sensor();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String id = getTagValue("id", element);
            String name = getTagValue("name", element);
            String sensorDate = getTagValue("start_date", element);
            TypeSensor typeSensor = new TypeSensor(getTagValue("type", element), getTagValue("units", element));
            SimpleDateFormat validDateFormat = new SimpleDateFormat(VALID_DATE_FORMAT3);
            Local local = new Local(Double.parseDouble(getTagValue(LATITUDE, element)),
                    Double.parseDouble(getTagValue(LONGITUDE, element)),
                    Double.parseDouble(getTagValue(ALTITUDE, element)));
            Date date = new Date();
            try {
                date = validDateFormat.parse(sensorDate);
            } catch (ParseException expected) {
                expected.getErrorOffset();
            }
            sensor = new Sensor(id, name, typeSensor, local, date);
            sensor.setSensorList(sensorList);
        }
        return sensor;
    }

    /**
     * Gets the value of the tag correspondent to the String and the Element from the same Node
     *
     * @param tag     - String of the tag correspondent to the node
     * @param element - element correspondent to the nod
     * @return - returns the value in string
     */
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
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
                return addReadingToMatchingSensor(logger, sensorID, readingValue, readingDate);
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
     * The method will read the JSON file, try to parse every reading and try to addWithoutPersisting them to the
     * corresponding sensor from its corresponding geographic area. The readings that fail to be added
     * will be added to log.
     *
     * @return the total number of readings added
     ***/
    public int readReadingsFromJSON(GeographicAreaList geographicAreaList, String path, String logPath) {
        int addedReadings = 0;
        SensorList sensorList = geographicAreaList.getAll().getAreaListSensors();
        if (sensorList.isEmpty()) {
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
                return addReadingToMatchingSensor(logger, sensorID, readingValue, readingDate);
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
    int addReadingToMatchingSensor(Logger logger, String sensorID, Double readingValue, Date readingDate) {
        if (sensorService.addReadingToMatchingSensor(sensorID, readingValue, readingDate)) {
            return 1;
        }
        String message = "The reading with value " + readingValue + " from " + readingDate + " could not be added to the sensor.";
        logger.warning(message);
        return 0;
    }

    /**
     * This method receives a geographic area list, a file path to XML file and a file path to a log.
     * The method will read the XML file, try to parse every reading and try to addWithoutPersisting them to the
     * corresponding sensor from its corresponding geographic area. The readings that fail to be added
     * will be added to log.
     *
     * @return the total number of readings added
     ***/
    public int readReadingsFromXML(GeographicAreaList geographicAreaList, String path, String logPath) {
        int addedReadings = 0;
        SensorList sensorList = geographicAreaList.getAll().getAreaListSensors();
        if (sensorList.isEmpty()) {
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
                return addReadingToMatchingSensor(logger, sensorID, readingValue, readingDate);
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
}
