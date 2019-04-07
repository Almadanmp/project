//package pt.ipp.isep.dei.project.controller;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import pt.ipp.isep.dei.project.model.GeographicArea;
//import pt.ipp.isep.dei.project.model.GeographicAreaList;
//import pt.ipp.isep.dei.project.model.Local;
//import pt.ipp.isep.dei.project.model.AreaType;
//import pt.ipp.isep.dei.project.model.sensor.Sensor;
//import pt.ipp.isep.dei.project.model.sensor.AreaSensorList;
//import pt.ipp.isep.dei.project.model.sensor.SensorType;
//import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;
//import pt.ipp.isep.dei.project.repository.SensorRepository;
//import pt.ipp.isep.dei.project.services.SensorService;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.PrintStream;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import static org.testng.Assert.assertEquals;
//
///**
// * ReaderController test class.
// */
//@ExtendWith(MockitoExtension.class)
//class ReaderControllerIntegrationTest {
//
//    // Common artifacts for testing in this class.
//
//    private GeographicAreaList validGeographicAreaList;
//    private GeographicAreaList validGeographicAreaList2;
//    private GeographicAreaList emptyGeographicAreaList;
//    private GeographicAreaList validGeographicAreaListNoSensors;
//    private GeographicArea validGeographicArea;
//    private Date validDate1 = new Date();
//    private Date validDate2 = new Date();
//    private Date validDate3 = new Date();
//    private Date validDate4 = new Date();
//    private ReaderController validReader;
//    private Sensor validSensor1;
//    private AreaSensorList validSensorList;
//    private static final String validCSVLocation1 = "src/test/resources/test1CSVReadings.csv";
//    private static final String validCSVLocation2 = "src/test/resources/test2CSVReadings.csv";
//    private static final String validCSVLocation3 = "src/test/resources/test3CSVReadings.csv";
//    private static final String validCSVLocation4 = "src/test/resources/test4CSVReadings.csv";
//    private static final String validCSVLocation5 = "src/test/resources/test5CSVReadings.csv";
//    private static final String validJSONLocation1 = "src/test/resources/test1JSONReadings.json";
//    private static final String validJSONLocation2 = "src/test/resources/test2JSONReadings.json";
//    private static final String validJSONLocation3 = "src/test/resources/test3JSONReadings.json";
//    private static final String validJSONLocation4 = "src/test/resources/test4JSONReadings.json";
//    private static final String validXMLocation1 = "src/test/resources/test1XMLReadings.xml";
//    private static final String validXMLocation2 = "src/test/resources/test2XMLReadings.xml";
//    private static final String validXMLocation3 = "src/test/resources/test3XMLReadings.xml";
//    private static final String validXMLocation4 = "src/test/resources/test4XMLReadings.xml";
//    private static final String validXMLocation5 = "src/test/resources/test5XMLReadings.xml";
//
//    private static final String validLogPath = "resources/logs/logOut.log";
//    private static final String invalidLogPath = "./resoursagfdgs/logs/logOut.log";
//
//    private static final Logger logger = Logger.getLogger(ReaderController.class.getName());
//
//
//    @Mock
//    SensorRepository sensorRepository;
//
//    @Mock
//    GeographicAreaRepository geographicAreaRepository;
//
//    SensorService sensorService;
//
//
//    @BeforeEach
//    void arrangeArtifacts() {
//        sensorService = new SensorService(sensorRepository);
//        validReader = new ReaderController(sensorService);
//        SimpleDateFormat validSdf = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat validSdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
//        try {
//            validDate1 = validSdf.parse("2016-11-15");
//            validDate2 = validSdf.parse("2016-11-15");
//            validDate3 = validSdf.parse("2017-11-15");
//            validDate4 = validSdf.parse("2017-11-16");
//        } catch (ParseException c) {
//            c.printStackTrace();
//        }
//        validGeographicArea = new GeographicArea("ISEP", new AreaType("urban area"), 0.249, 0.261,
//                new Local(41.178553, -8.608035, 111));
//        GeographicArea validGeographicArea2 = new GeographicArea("Porto", new AreaType("city"), 3.30, 10.09,
//                new Local(41.149935, -8.610857, 118));
//        GeographicArea emptyGeographicArea = new GeographicArea("Lisbon", new AreaType("city"), 0.299, 0.291,
//                new Local(41.178553, 8.608035, 117));
//        validSensor1 = new Sensor("RF12345", "Meteo station ISEP - rainfall", new SensorType("rain", "mm"),
//                new Local(41.179230, -8.606409, 125),
//                validDate1);
//        Sensor validSensor2 = new Sensor("TT12346", "Meteo station ISEP - temperature", new SensorType("rain2", "mm2"),
//                new Local(41.179230, -8.606409, 125),
//                validDate2);
//        Sensor validSensor3 = new Sensor("RF12334", "Meteo station CMP - rainfall", new SensorType("rain2", "mm2"),
//                new Local(41.179230, -8.606409, 139),
//                validDate3);
//        Sensor validSensor4 = new Sensor("TT1236A", "Meteo station CMP - temperature", new SensorType("rain2", "mm2"),
//                new Local(41.179230, -8.606409, 139),
//                validDate4);
//        validSensorList = new AreaSensorList();
//        AreaSensorList validSensorList2 = new AreaSensorList();
//        validSensorList.add(validSensor1);
//        validSensorList.add(validSensor2);
//        validSensorList2.add(validSensor3);
//        validSensorList2.add(validSensor4);
//        validGeographicArea.setAreaSensorList(validSensorList);
//        validGeographicArea2.setAreaSensorList(validSensorList2);
//        validGeographicAreaList = new GeographicAreaList(geographicAreaRepository);
//        validGeographicAreaList2 = new GeographicAreaList(geographicAreaRepository);
//        emptyGeographicAreaList = new GeographicAreaList(geographicAreaRepository);
//        validGeographicAreaListNoSensors = new GeographicAreaList(geographicAreaRepository);
//        validGeographicAreaListNoSensors.addGeographicArea(emptyGeographicArea);
//        validGeographicAreaList.addGeographicArea(validGeographicArea);
//        validGeographicAreaList.addGeographicArea(validGeographicArea2);
//        validGeographicAreaList2.addGeographicArea(validGeographicArea);
//    }
//
//    private final InputStream systemIn = System.in;
//    private final PrintStream systemOut = System.out;
//
//    @BeforeEach
//    void setUpOutput() {
//        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(testOut));
//        logger.setLevel(Level.WARNING);
//    }
//
//    @AfterEach
//    void restoreSystemInputOutput() {
//        System.setIn(systemIn);
//        System.setOut(systemOut);
//    }
//
//
//    @Test
//    void seeIfReadReadingsFromXMLThrowsExceptionWithInvalidLogPath() {
//        // Assert
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> validReader.readReadingsFromXML(validGeographicAreaList, validXMLocation2, invalidLogPath));
//    }
//
//    @Test
//    void seeIfReadReadingsFromXMLThrowsExceptionWithInvalidXMLPath() {
//        // Assert
//        Assertions.assertThrows(IllegalArgumentException.class, () -> validReader.readReadingsFromXML(validGeographicAreaList, "src/test/resources/wrongPath.xml", validLogPath));
//    }
//
//    @Test
//    void seeIfAddReadingToMatchingSensorWorksWhenLoggerAndReadingAreValid() {
//        //Act
//
//
//        int actualResult = validReader.addReadingToMatchingSensor(logger, "TT12346", 20D, validDate1, "C");
//
//        // Assert
//
//        assertEquals(actualResult, 1);
//    }
//
//    @Test
//    void seeIfReadReadingsFromXMLWorksWhenOneReadingHasWrongSensorId() {
//        // Act
//
//        int actualResult = validReader.readReadingsFromXML(validGeographicAreaList, validXMLocation2, validLogPath);
//
//        // Assert
//
//        assertEquals(4, actualResult);
//    }
//
//    @Test
//    void seeIfReadReadingsFromXMLWorksWhenReadingsHaveSameDate() {
//        // Act
//
//        int actualResult = validReader.readReadingsFromXML(validGeographicAreaList, validXMLocation3, validLogPath);
//
//        // Assert
//
//        assertEquals(1, actualResult);
//    }
//
//
//
//
//    @Test
//    void seeIfReadReadingsFromJSONThrowsExceptionWithInvalidLogPath() {
//        // Assert
//
//        Assertions.assertThrows(NullPointerException.class, () -> validReader.readReadingsFromJSON(validGeographicAreaList, validJSONLocation3, invalidLogPath));
//    }
//    @Test
//    void seeIfReadReadingsFromJSONWorksWhenOneReadingHasWrongSensorId() {
//
//        // Act
//
//        int actualResult = validReader.readReadingsFromJSON(validGeographicAreaList, validJSONLocation2, validLogPath);
//
//        // Assert
//
//        assertEquals(4, actualResult);
//    }
//
//    @Test
//    void seeIfReadReadingsFromJSONWorksWhenReadingsHaveSameDate() {
//
//        // Act
//        int actualResult = validReader.readReadingsFromJSON(validGeographicAreaList, validJSONLocation3, validLogPath);
//
//        // Assert
//
//        assertEquals(1, actualResult);
//    }
//    @Test
//    void seeIfParseAndLogJSONReadingsWorks() {
//
//        //Arrange
//
//        JSONObject validJSONObj1 = new JSONObject();
//        validJSONObj1.put("id", "TT12346");
//        validJSONObj1.put("timestamp/date", "2018-12-30T02:00:00+00:00");
//        validJSONObj1.put("value", "23.4");
//        validJSONObj1.put("unit", "F");
//
//        JSONObject validJSONObj2 = new JSONObject();
//        validJSONObj2.put("id", "TT12346");
//        validJSONObj2.put("timestamp/date", "2018-12-30T03:00:00+00:00");
//        validJSONObj2.put("value", "25.6");
//        validJSONObj2.put("unit", "F");
//
//        JSONArray validJSONArray = new JSONArray();
//        validJSONArray.put(validJSONObj1);
//        validJSONArray.put(validJSONObj2);
//
//        // Act
//
//        int actualResult = validReader.parseAndLogJSONReadings(validJSONArray, logger);
//
//        // Assert
//
//        assertEquals(2, actualResult);
//    }
//
//    @Test
//    void seeIfParseAndLogJSONReadingWorks() {
//        //Arrange
//
//        JSONObject validJSONObj = new JSONObject();
//        validJSONObj.put("id", "TT12346");
//        validJSONObj.put("timestamp/date", "2018-12-30T02:00:00+00:00");
//        validJSONObj.put("value", "57.2");
//        validJSONObj.put("unit", "F");
//
//
//        // Act
//
//        int actualResult = validReader.parseAndLogJSONReading(validJSONObj, logger);
//
//        // Assert
//
//        assertEquals(1, actualResult);
//    }
//
//
//@Test
//    void seeIfReadReadingsFromCSVWorks() {
//            //Arrange
//
//
//            // Act
//
//            int actualResult = validReader.readReadingsFromCSV(validGeographicAreaList, validCSVLocation1, validLogPath);
//
//            // Assert
//
//            assertEquals(9, actualResult);
//            }
//}