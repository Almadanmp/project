package pt.ipp.isep.dei.project.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.io.ui.MainUI;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;
import pt.ipp.isep.dei.project.services.SensorService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * ReaderController test class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {MainUI.class},
        loader = AnnotationConfigContextLoader.class)
class ReaderControllerTest {

    // Common artifacts for testing in this class.

    private GeographicAreaList validGeographicAreaList;
    private GeographicAreaList validGeographicAreaList2;
    private GeographicAreaList emptyGeographicAreaList;
    private GeographicAreaList validGeographicAreaListNoSensors;
    private GeographicArea validGeographicArea;
    private Date validDate1 = new Date();
    private Date validDate2 = new Date();
    private Date validDate3 = new Date();
    private Date validDate4 = new Date();
    private ReaderController validReader;
    private Sensor validSensor1;
    private SensorList validSensorList;
    private static final String validCSVLocation1 = "src/test/resources/test1CSVReadings.csv";
    private static final String validCSVLocation2 = "src/test/resources/test2CSVReadings.csv";
    private static final String validCSVLocation3 = "src/test/resources/test3CSVReadings.csv";
    private static final String validCSVLocation4 = "src/test/resources/test4CSVReadings.csv";
    private static final String validCSVLocation5 = "src/test/resources/test5CSVReadings.csv";
    private static final String validJSONLocation1 = "src/test/resources/test1JSONReadings.json";
    private static final String validJSONLocation2 = "src/test/resources/test2JSONReadings.json";
    private static final String validJSONLocation3 = "src/test/resources/test3JSONReadings.json";
    private static final String validJSONLocation4 = "src/test/resources/test4JSONReadings.json";
    private static final String validXMLocation1 = "src/test/resources/test1XMLReadings.xml";
    private static final String validXMLocation2 = "src/test/resources/test2XMLReadings.xml";
    private static final String validXMLocation3 = "src/test/resources/test3XMLReadings.xml";
    private static final String validXMLocation4 = "src/test/resources/test4XMLReadings.xml";
    private static final String validXMLocation5 = "src/test/resources/test5XMLReadings.xml";

    private static final String validLogPath = "resources/logs/logOut.log";
    private static final String invalidLogPath = "./resoursagfdgs/logs/logOut.log";

    private static final Logger logger = Logger.getLogger(ReaderController.class.getName());

    @Autowired
    SensorService sensorService;

    @Autowired
    GeographicAreaRepository geographicAreaRepository;

    @BeforeEach
    void arrangeArtifacts() {
        validReader = new ReaderController(sensorService);
        SimpleDateFormat validSdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat validSdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate1 = validSdf.parse("2016-11-15");
            validDate2 = validSdf.parse("2016-11-15");
            validDate3 = validSdf.parse("2017-11-15");
            validDate4 = validSdf.parse("2017-11-16");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validGeographicArea = new GeographicArea("ISEP", new TypeArea("urban area"), 0.249, 0.261,
                new Local(41.178553, -8.608035, 111));
        GeographicArea validGeographicArea2 = new GeographicArea("Porto", new TypeArea("city"), 3.30, 10.09,
                new Local(41.149935, -8.610857, 118));
        GeographicArea emptyGeographicArea = new GeographicArea("Lisbon", new TypeArea("city"), 0.299, 0.291,
                new Local(41.178553, 8.608035, 117));
        validSensor1 = new Sensor("RF12345", "Meteo station ISEP - rainfall", new TypeSensor("rain", "mm"),
                new Local(41.179230, -8.606409, 125),
                validDate1);
        Sensor validSensor2 = new Sensor("TT12346", "Meteo station ISEP - temperature", new TypeSensor("rain2", "mm2"),
                new Local(41.179230, -8.606409, 125),
                validDate2);
        Sensor validSensor3 = new Sensor("RF12334", "Meteo station CMP - rainfall", new TypeSensor("rain2", "mm2"),
                new Local(41.179230, -8.606409, 139),
                validDate3);
        Sensor validSensor4 = new Sensor("TT1236A", "Meteo station CMP - temperature", new TypeSensor("rain2", "mm2"),
                new Local(41.179230, -8.606409, 139),
                validDate4);
        validSensorList = new SensorList();
        SensorList validSensorList2 = new SensorList();
        validSensorList.add(validSensor1);
        validSensorList.add(validSensor2);
        validSensorList2.add(validSensor3);
        validSensorList2.add(validSensor4);
        validGeographicArea.setSensorList(validSensorList);
        validGeographicArea2.setSensorList(validSensorList2);
        validGeographicAreaList = new GeographicAreaList();
        validGeographicAreaList2 = new GeographicAreaList();
        emptyGeographicAreaList = new GeographicAreaList();
        validGeographicAreaListNoSensors = new GeographicAreaList();
        validGeographicAreaListNoSensors.addGeographicArea(emptyGeographicArea);
        validGeographicAreaList.addGeographicArea(validGeographicArea);
        validGeographicAreaList.addGeographicArea(validGeographicArea2);
        validGeographicAreaList2.addGeographicArea(validGeographicArea);
        validGeographicAreaList.setGeographicAreaRepository(geographicAreaRepository);
    }

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    @BeforeEach
    void setUpOutput() {
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        logger.setLevel(Level.WARNING);
    }

    @AfterEach
    void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

//    @Test
//    void seeIfParseAndLogReadingWorks() {
//        // Arrange
//
//
//        String[] readings = new String[3];
//        readings[0] = "RF12345";
//        readings[1] = "2019-12-30T02:00:00+00:00";
//        readings[2] = "23";
//
//        // Act
//
//        int actualResult = validReader.parseAndLogCSVReading(readings, logger);
//
//        // Assert
//
//        assertEquals(1, actualResult);
//    }

//    @Test
//    void seeIfParseAndLogReadingWorksWithExtraParameter() {
//        // Arrange
//
//
//        String[] readings = new String[4];
//        readings[0] = "RF12345";
//        readings[1] = "2019-12-30T02:00:00+00:00";
//        readings[2] = "23";
//        readings[3] = "12";
//
//        // Act
//
//        int actualResult = validReader.parseAndLogCSVReading(readings, logger);
//
//        // Assert
//
//        assertEquals(0, actualResult);
//    }

    @Test
    void seeIfParseAndLogReadingFailsWithWrongSensor() {
        // Arrange


        String[] readings = new String[3];
        readings[0] = "wrong sensor";
        readings[1] = "2019-12-30T02:00:00+00:00";
        readings[2] = "23";

        // Act

        int actualResult = validReader.parseAndLogCSVReading(readings, logger);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfParseAndLogReadingFailsWithRepeatedReading() {
        // Arrange
        Date validDate = new Date();
        SimpleDateFormat validSdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate = validSdf2.parse("2019-12-30T02:00:00+00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }


        String[] readings = new String[3];
        readings[0] = "RF12345";
        readings[1] = "2019-12-30T02:00:00+00:00";
        readings[2] = "23";

        validSensor1.addReading(new Reading(32, validDate));

        // Act

        int actualResult = validReader.parseAndLogCSVReading(readings, logger);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfParseAndLogReadingFailsWithEmptySensorList() {
        // Arrange

        SensorList emptyList = new SensorList();


        String[] readings = new String[3];
        readings[0] = "RF12345";
        readings[1] = "2019-12-30T02:00:00+00:00";
        readings[2] = "23";

        // Act

        int actualResult = validReader.parseAndLogCSVReading(readings, logger);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfParseAndLogReadingFailsWithInvalidDateFormat() {
        // Arrange

        SensorList emptyList = new SensorList();


        String[] readings = new String[3];
        readings[0] = "RF12345";
        readings[1] = "invalid date";
        readings[2] = "23";

        // Act

        int actualResult = validReader.parseAndLogCSVReading(readings, logger);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfParseAndLogReadingsWorksLogNotLoggable() {

        //Arrange


        logger.setLevel(Level.INFO);
        String[] readings = new String[3];
        readings[0] = "RF12345";
        readings[1] = "2008-12-30T02:00:00+00:00";
        readings[2] = "test";

        // Act

        int actualResult = validReader.parseAndLogCSVReading(readings, logger);

        //Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfParseAndLogReadingFailsWithInvalidValue() {
        // Arrange


        String[] readings = new String[3];
        readings[0] = "RF12345";
        readings[1] = "2008-12-30T02:00:00+00:00";
        readings[2] = "invalid value";

        // Act

        int actualResult = validReader.parseAndLogCSVReading(readings, logger);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadReadingsFromCSVWorksWithEmptySensorList() {
        // Act

        int actualResult = validReader.readReadingsFromCSV(validGeographicAreaListNoSensors, " ", " ");

        // Assert

        assertEquals(0, actualResult);
    }

//    @Test
//    void seeIfReadReadingsFromCSVWorks() {
//        //Arrange
//
//
//        // Act
//
//        int actualResult = validReader.readReadingsFromCSV(validGeographicAreaList, validCSVLocation1, validLogPath);
//
//        // Assert
//
//        assertEquals(9, actualResult);
//    }

    @Test
    void seeIfReadReadingsFromCSVWorksWhenFileIsEmpty() {

        // Act

        int actualResult = validReader.readReadingsFromCSV(validGeographicAreaList, validCSVLocation3, validLogPath);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadReadingsFromCSVThrowsExceptionWithInvalidLogPath() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> validReader.readReadingsFromCSV(validGeographicAreaList, validCSVLocation1, invalidLogPath));
    }

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

    @Test
    void seeIfParseAndLogJSONReadingWorksWithInvalidDateFormat() {
        //Arrange

        JSONObject validJSONObj = new JSONObject();
        validJSONObj.put("id", "TT12346");
        validJSONObj.put("timestamp/date", "00:00+00:00");
        validJSONObj.put("value", "57.2");
        validJSONObj.put("unit", "F");


        // Act

        int actualResult = validReader.parseAndLogJSONReading(validJSONObj, logger);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfParseAndLogJSONReadingWorksWithInvalidValue() {
        //Arrange

        JSONObject validJSONObj = new JSONObject();
        validJSONObj.put("id", "TT12346");
        validJSONObj.put("timestamp/date", "2018-12-30T02:00:00+00:00");
        validJSONObj.put("value", "string");
        validJSONObj.put("unit", "F");


        // Act

        int actualResult = validReader.parseAndLogJSONReading(validJSONObj, logger);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfParseAndLogJSONReadingWorksWithInvalidSensorID() {
        //Arrange

        JSONObject validJSONObj = new JSONObject();
        validJSONObj.put("id", "xxxx");
        validJSONObj.put("timestamp/date", "2018-12-30T02:00:00+00:00");
        validJSONObj.put("value", "23.3");
        validJSONObj.put("unit", "F");


        // Act

        int actualResult = validReader.parseAndLogJSONReading(validJSONObj, logger);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfParseAndLogJSONReadingWorksWithInvalidLogger() {

        //Arrange

        JSONObject validJSONObj = new JSONObject();
        validJSONObj.put("id", "xxxx");
        validJSONObj.put("timestamp/date", "2018-12-30T02:00:00+00:00");
        validJSONObj.put("value", "23.3");
        validJSONObj.put("unit", "F");

        logger.setLevel(Level.INFO);

        // Act

        int actualResult = validReader.parseAndLogJSONReading(validJSONObj, logger);

        // Assert

        assertEquals(0, actualResult);
    }

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

    @Test
    void seeIfParseAndLogJSONReadingsWorksWithInvalidField() {

        //Arrange

        JSONObject validJSONObj1 = new JSONObject();
        validJSONObj1.put("id", "TT12346");
        validJSONObj1.put("timestamp/date", "2018-12-30T02:00:00+00:00");
        validJSONObj1.put("value", "invalidField");
        validJSONObj1.put("unit", "F");

        JSONArray validJSONArray = new JSONArray();
        validJSONArray.put(validJSONObj1);

        // Act

        int actualResult = validReader.parseAndLogJSONReadings(validJSONArray, logger);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadReadingsFromJSONWorksWithEmptySensorList() {
        // Act
        geographicAreaRepository.deleteAll();
        emptyGeographicAreaList.setGeographicAreaRepository(geographicAreaRepository);
        int actualResult = validReader.readReadingsFromJSON(emptyGeographicAreaList, validJSONLocation1, validLogPath);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadReadingsFromJSONWorksWhenFileHasNoReadings() {

        // Act

        int actualResult = validReader.readReadingsFromJSON(validGeographicAreaList, validJSONLocation1, validLogPath);

        // Assert

        assertEquals(0, actualResult);
    }

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
//        validGeographicAreaList.setGeographicAreaRepository(geographicAreaRepository);
//        int actualResult = validReader.readReadingsFromJSON(validGeographicAreaList, validJSONLocation3, validLogPath);
//
//        // Assert
//
//        assertEquals(1, actualResult);
//    }

    @Test
    void seeIfReadReadingsFromJSONWorksWhenInputValuesAreWrong() {

        // Act

        int actualResult = validReader.readReadingsFromJSON(validGeographicAreaList, validJSONLocation4, validLogPath);

        // Assert

        assertEquals(0, actualResult);
    }

//    @Test
//    void seeIfReadReadingsFromJSONThrowsExceptionWithInvalidLogPath() {
//        // Assert
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> validReader.readReadingsFromJSON(validGeographicAreaList, validJSONLocation3, invalidLogPath));
//    }

    @Test
    void seeIfReadReadingsFromXMLWorksWhenSensorListIsEmpty() {
        // Act
        geographicAreaRepository.deleteAll();
        emptyGeographicAreaList.setGeographicAreaRepository(geographicAreaRepository);
        int actualResult = validReader.readReadingsFromXML(emptyGeographicAreaList, validXMLocation1, validLogPath);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadReadingsFromXMLWorksWhenFileHasNoReadings() {
        // Act

        int actualResult = validReader.readReadingsFromXML(validGeographicAreaList, validXMLocation1, validLogPath);

        // Assert

        assertEquals(0, actualResult);
    }

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

    @Test
    void seeIfReadReadingsFromXMLWorksWhenInputValuesAreWrong() {
        // Act

        int actualResult = validReader.readReadingsFromXML(validGeographicAreaList, validXMLocation4, validLogPath);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadReadingsFromXMLWorksWhenFileHasNoElements() {
        // Act

        int actualResult = validReader.readReadingsFromXML(validGeographicAreaList, validXMLocation5, validLogPath);

        // Assert

        assertEquals(0, actualResult);
    }

//    @Test
//    void seeIfReadReadingsFromXMLThrowsExceptionWithInvalidLogPath() {
//        // Assert
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> validReader.readReadingsFromXML(validGeographicAreaList, validXMLocation2, invalidLogPath));
//    }

//    @Test
//    void seeIfReadReadingsFromXMLThrowsExceptionWithInvalidXMLPath() {
//        // Assert
//        Assertions.assertThrows(IllegalArgumentException.class, () -> validReader.readReadingsFromXML(validGeographicAreaList, "src/test/resources/wrongPath.xml", validLogPath));
//    }

    @Test
    void seeIfAddReadingToMatchingSensorWorksWhenLoggerAndReadingAreInvalid() {
        //Arrange

        logger.setLevel(Level.SEVERE);

        //Act

        int actualResult = validReader.addReadingToMatchingSensor(logger, "xxxx", 20D, validDate1);

        // Assert

        assertEquals(actualResult, 0);
    }

//    @Test
//    void seeIfAddReadingToMatchingSensorWorksWhenLoggerAndReadingAreValid() {
//        //Act
//
//
//        int actualResult = validReader.addReadingToMatchingSensor(logger, "TT12346", 20D, validDate1);
//
//        // Assert
//
//        assertEquals(actualResult, 1);
//    }

    @Test
    void seeIfAddReadingToMatchingSensorWorksWhenLoggerIsInvalidAndReadingAreValid() {
        //Arrange

        logger.setLevel(Level.SEVERE);

        //Act

        int actualResult = validReader.addReadingToMatchingSensor(logger, "TT12346", 20D, validDate1);

        // Assert

        assertEquals(actualResult, 0);
    }

    @Test
    void seeIfAddReadingToMatchingSensorWorksWhenLoggerIsValidAndReadingAreInvalid() {
        //Act

        int actualResult = validReader.addReadingToMatchingSensor(logger, "xxxx", 20D, validDate1);

        // Assert

        assertEquals(actualResult, 0);
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorks() {
        // Arrange

        GeographicAreaList actualResult = new GeographicAreaList();

        actualResult.setGeographicAreaRepository(geographicAreaRepository);

        // Act

        File fileToRead = new File("src/test/resources/DataSet_sprint05_GA.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = validReader.readFileXMLAndAddAreas(absolutePath, actualResult);

        // Assert

        assertEquals(2, areasAdded);

        // Get one of the areas to  check its contents.

        GeographicArea actualArea = actualResult.get(0);
        SensorList firstAreaSensors = actualArea.getSensorList();

        // Declare expected area / sensors.

        SensorList expectedSensors = new SensorList();
        expectedSensors.add(actualArea.getSensorList().get(0));
        expectedSensors.add(actualArea.getSensorList().get(1));

        GeographicArea expectedArea = new GeographicArea("ISEP", new TypeArea("urban area"), 0.249,
                0.261, new Local(41.178553, -8.608035, 139));

        // Assert

        assertEquals(expectedArea, actualArea);
        assertEquals(expectedSensors, firstAreaSensors);
    }


    @Test
    void seeIfReadFileXMLGeoAreaWorksZeroAreas() {
        // Arrange

        GeographicAreaList actualResult = new GeographicAreaList();

        // Act

        File fileToRead = new File("src/test/resources/test1XMLReadings.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = validReader.readFileXMLAndAddAreas(absolutePath, actualResult);

        // Assert

        assertEquals(0, areasAdded);
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWithAnotherDateFormat() {
        // Arrange

        GeographicAreaList geographicAreaList3 = new GeographicAreaList();
        geographicAreaList3.setGeographicAreaRepository(geographicAreaRepository);

        // Act

        File fileToRead = new File("src/test/resources/DataSet_sprint05_GA_test_wrong_date.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = validReader.readFileXMLAndAddAreas(absolutePath, geographicAreaList3);

        // Assert

        assertEquals(2, areasAdded);

        // Get one of the areas to  check its contents.

        GeographicArea actualArea = geographicAreaList3.get(0);
        SensorList firstAreaSensors = actualArea.getSensorList();

        // Declare expected area / sensors.

        SensorList expectedSensors = new SensorList();
        expectedSensors.add(actualArea.getSensorList().get(0));
        expectedSensors.add(actualArea.getSensorList().get(1));

        GeographicArea expectedArea = new GeographicArea("ISEP", new TypeArea("urban area"), 0.249,
                0.261, new Local(41.178553, -8.608035, 139));

        // Assert

        assertEquals(expectedArea, actualArea);
        assertEquals(expectedSensors, firstAreaSensors);
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWithNormalDateAndOtherDate() {
        // Arrange

        validGeographicAreaList2.setGeographicAreaRepository(geographicAreaRepository);

        // Act

        File fileToRead = new File("src/test/resources/DataSet_sprint05_GA_test_wrong_and_correct_date.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = validReader.readFileXMLAndAddAreas(absolutePath, validGeographicAreaList2);

        // Assert

        assertEquals(1, areasAdded);

    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWithoutGeoAreas() {
        // Arrange
        GeographicAreaList actualResult = new GeographicAreaList();

        // Act

        File fileToRead = new File("src/test/resources/DataSet_sprint05_GA_test_no_GAs.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = validReader.readFileXMLAndAddAreas(absolutePath, actualResult);

        // Assert

        assertEquals(0, areasAdded);
    }

    @Test
    void seeIfReadFileXMLGeoAreaWorksWithOneGeoArea() {
        // Arrange

        GeographicAreaList actualResult = new GeographicAreaList();
        actualResult.setGeographicAreaRepository(geographicAreaRepository);

        // Act

        File fileToRead = new File("src/test/resources/DataSet_sprint05_GA_test_one_GA.xml");
        String absolutePath = fileToRead.getAbsolutePath();
        double areasAdded = validReader.readFileXMLAndAddAreas(absolutePath, actualResult);

        // Assert

        assertEquals(1, areasAdded);
    }

    @Test
    void seeIfAddGeoAreasToListWorks() {
        // Arrange

        GeographicAreaList result = new GeographicAreaList();
        result.setGeographicAreaRepository(geographicAreaRepository);
        GeographicArea[] arrayToUse = new GeographicArea[2];

        // Set up Expected Result

        GeographicArea geoArea1 = new GeographicArea("ISEP", new TypeArea("urban area"), 0.249,
                0.261, new Local(41.178553, -8.608035, 111));
        GeographicArea geoArea2 = new GeographicArea("Porto", new TypeArea("city"), 3.30, 10.09,
                new Local(41.149935, -8.610857, 118));

        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.setGeographicAreaRepository(geographicAreaRepository);
        expectedResult.addAndPersistGA(geoArea1);
        expectedResult.addAndPersistGA(geoArea2);

        // Populate Array to Use

        arrayToUse[0] = geoArea1;
        arrayToUse[1] = geoArea2;

        // Act

        double addedAreas = validReader.addGeoAreasToList(arrayToUse, result);

        // Assert

        assertEquals(2, addedAreas);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAcceptPathWorksXML() {
        String input = "src/test/resources/DataSet_sprint05_GA_test_no_GAs.xml";
        File fileToRead = new File(input);
        String absolutePath = fileToRead.getAbsolutePath();
        int result = validReader.acceptPath(input, absolutePath, validGeographicAreaList);
        assertEquals(result, 0);
    }

    @Test
    void seeIfAcceptPathWorksWrongPath() {
        String input = "src/test/resources/wrong_path";
        File fileToRead = new File(input);
        String absolutePath = fileToRead.getAbsolutePath();
        int result = validReader.acceptPath(input, absolutePath, validGeographicAreaList);
        assertEquals(result, -1);
    }

    @Test
    void seeIfAcceptPathWorksJSON() {
        // Arrange

        String input = "src/test/resources/DataSet_sprint04_GA.json";
        File fileToRead = new File(input);
        String absolutePath = fileToRead.getAbsolutePath();
        GeographicAreaList geographicAreaList1 = new GeographicAreaList();
        geographicAreaList1.setGeographicAreaRepository(geographicAreaRepository);
        ReaderController readerController = new ReaderController(sensorService);

        // Act

        int result = readerController.acceptPath(input, absolutePath, geographicAreaList1);

        // Assert

        assertEquals(result, 2);
    }
}