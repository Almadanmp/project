package pt.ipp.isep.dei.project.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ReaderController test class.
 */

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
    private Date validDate5 = new Date();
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


    private static final String validLogPath = "resources/logs/logOut.log";
    private static final String invalidLogPath = "./resoursagfdgs/logs/logOut.log";

    private static final Logger logger = Logger.getLogger(ReaderController.class.getName());

    @BeforeEach
    void arrangeArtifacts() {
        validReader = new ReaderController();
        SimpleDateFormat validSdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat validSdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate1 = validSdf.parse("2016-11-15");
            validDate2 = validSdf.parse("2016-11-15");
            validDate3 = validSdf.parse("2017-11-15");
            validDate4 = validSdf.parse("2017-11-16");
            validDate5 = validSdf2.parse("2020-12-30T02:00:00+00:00");
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
    }

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    @BeforeEach
    void setUpOutput() {
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    void seeIfReadAndSetterWorks() {

        //Arrange

        provideInput(validCSVLocation1);

        // Act

        validReader.readAndSetInternal(validGeographicAreaList, validCSVLocation1, validLogPath);

        //Assert

    }

    @Test
    void seeIfReadAndSetterWorksEmptyGAList() {

        //Arrange

        provideInput(validCSVLocation1);

        // Act

        validReader.readAndSetInternal(emptyGeographicAreaList, validCSVLocation1, validLogPath);

        //Assert

    }

    @Test
    void seeIfReadAndSetterWorksNoSensors() {

        //Arrange

        provideInput(validCSVLocation1);

        // Act

        validReader.readAndSetInternal(validGeographicAreaList2, validCSVLocation1, validLogPath);

        //Assert

    }

    @Test
    void seeIfReadAndSetterWorksNoSensorList() {

        //Arrange

        provideInput(validCSVLocation1);

        // Act

        validReader.readAndSetInternal(emptyGeographicAreaList, validCSVLocation1, validLogPath);

        //Assert

    }

    @Test
    void seeIfReadAndSetterWorksNoSensorListGeoList() {

        //Arrange
        GeographicAreaList geoListEmptySensor = new GeographicAreaList();
        GeographicArea geographicArea = new GeographicArea("Porto", new TypeArea("city"), 3.30, 10.09,
                new Local(41.149935, -8.610857, 118));
        geoListEmptySensor.addGeographicArea(geographicArea);
        provideInput(validCSVLocation1);

        // Act

        validReader.readAndSetInternal(geoListEmptySensor, validCSVLocation1, validLogPath);

        //Assert

    }

    @Test
    void seeIfReadAndSetterFailsUnparseableDate() {

        //Arrange

        provideInput(validCSVLocation3);

        // Act

        validReader.readAndSetInternal(validGeographicAreaList, validCSVLocation1, validLogPath);

        //Assert

    }

    @Test
    void seeIfReadAndSetterFailsUnreachableSensorName() {

        //Arrange

        provideInput(validCSVLocation4);

        // Act

        validReader.readAndSetInternal(validGeographicAreaList, validCSVLocation1, validLogPath);

        //Assert

    }


    @Test
    void seeIfReadAndSetterFailsWrongPath() {

        //Arrange

        provideInput(validCSVLocation4);

        // Act
        assertTrue(validReader.readAndSetInternal(validGeographicAreaList, validCSVLocation1, invalidLogPath));
    }

    /*
        @Test
        void seeIfGetSensorDataWorks() {

            //Arrange

            SensorList expectedResult = new SensorList();
            expectedResult.add(validSensor1);
            expectedResult.add(validSensor2);
            expectedResult.add(validSensor3);
            expectedResult.add(validSensor4);

            // Act

            SensorList actualResult = validReader.getSensorData(validGeographicAreaList);

            //Assert
            assertEquals(expectedResult, actualResult);

        }

        @Test
        void seeIfGetSensorDataWorksEmptyList() {

            //Arrange

            SensorList expectedResult = new SensorList();

            // Act

            SensorList actualResult = validReader.getSensorData(new GeographicAreaList());

            //Assert

            assertEquals(expectedResult, actualResult);
        }

        @Test
        void seeIfGetSensorDataWorksEmptySensorList() {

            //Arrange

            SensorList expectedResult = new SensorList();
            validGeographicArea.setTypeSensorList(new SensorList());
            emptyGeographicAreaList.addGeographicArea(validGeographicArea);

            // Act

            SensorList actualResult = validReader.getSensorData(emptyGeographicAreaList);

            //Assert

            assertEquals(expectedResult, actualResult);
        }

        @Test
        void seeIfGetSensorDataFails() {

            //Arrange

            SensorList expectedResult = new SensorList();

            // Act

            SensorList actualResult = validReader.getSensorData(validGeographicAreaList);

            //Assert
            assertNotEquals(expectedResult, actualResult);

        }

        @Test
        void seeIfGetSensorDataIsEmpty() {

            //Arrange

            SensorList expectedResult = new SensorList();

            // Act

            SensorList actualResult = validReader.getSensorData(validGeographicAreaList);

            //Assert
            assertNotEquals(expectedResult, actualResult);

        }

        @Test
        void seeIfGetSensorDataEmptySensors() {

            //Arrange

            String expectedResult = "Invalid List - List is Empty\n";

            // Act

            String actualResult = validReader.getSensorData(emptyGeographicAreaList).toString();

            //Assert
            assertEquals(expectedResult, actualResult);

        }

        @Test
        void seeIfGetSensorDataEmptyGAList() {

            //Arrange

            SensorList expectedResult = new SensorList();

            // Act

            SensorList actualResult = validReader.getSensorData(emptyGeographicAreaList);

            //Assert
            assertEquals(expectedResult, actualResult);

        }

        @Test
        void seeIfGetSensorDataEmptySensorList() {

            //Arrange

            String expectedResult = "---------------\n0) Name: Meteo station ISEP - rainfall | Type: rain | Active\n" +
                    "1) Name: Meteo station ISEP - temperature | Type: rain2 | Active\n---------------\n";

            // Act

            String actualResult = validReader.getSensorData(validGeographicAreaList2).toString();

            //Assert
            assertEquals(expectedResult, actualResult);

        }
    */
    @Test
    void seeIfParseAndLogFailsOutOfBoundsArray() {

        //Arrange


        String[] readings = new String[0];

        // Act

        //Assert

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> validReader.parseAndLog(readings, logger, validSensorList));
    }

    @Test
    void seeIfParseAndLogFailsNullPointerException() {

        //Arrange


        String[] readings = new String[3];

        // Act

        //Assert

        Assertions.assertThrows(NullPointerException.class, () -> validReader.parseAndLog(readings, logger, validSensorList));
    }

    @Test
    void seeIfReadAndSetterWorksWithReadings() {

        //Arrange

        provideInput(validCSVLocation5);
        List<Reading> expectedResult = new ArrayList<>();
        Reading reading = new Reading(14.0, validDate5);
        expectedResult.add(reading);

        // Act

        validReader.readAndSet(validGeographicAreaList, validCSVLocation5, validLogPath);
        List<Reading> actualResult = validGeographicArea.getSensorList().get(0).getReadingList();

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfReadAndSetterFailsNonSetReadings() {

        //Arrange

        provideInput(validCSVLocation5);
        List<Reading> expectedResult = new ArrayList<>();

        // Act

        validReader.readAndSet(validGeographicAreaList, validCSVLocation5, validLogPath);
        List<Reading> actualResult = validGeographicArea.getSensorList().get(0).getReadingList();

        //Assert
        assertNotEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfParseAndLogCatchesNumberFormatException() {

        //Arrange

        provideInput(validCSVLocation1);

        String[] readings = new String[3];
        readings[2] = "log";

        // Act

        //Assert

        try {
            validReader.parseAndLog(readings, logger, validSensorList);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    void seeIfParseAndLogCatchesLogCondition() {

        //Arrange

        provideInput(validCSVLocation2);

        String[] readings = new String[3];
        readings[0] = "RF12345";
        readings[1] = "2008-12-30T02:00:00+00:00";
        readings[2] = "13.7";

        // Act

        //Assert

        validReader.parseAndLog(readings, logger, validSensorList);
    }

    @Test
    void seeIfParseAndLogCatchesLogCondition2() {

        //Arrange

        provideInput(validCSVLocation2);

        String[] readings = new String[3];
        readings[0] = "RF12345";
        readings[1] = "2008-12-30T02:00:00+00:00";
        readings[2] = "test";

        // Act

        //Assert

        validReader.parseAndLog(readings, logger, validSensorList);
    }

    @Test
    void seeIfParseAndLogWorksLogNotLoggable() {

        //Arrange

        provideInput(validCSVLocation2);

        logger.setLevel(Level.INFO);
        String[] readings = new String[3];
        readings[0] = "RF12345";
        readings[1] = "2008-12-30T02:00:00+00:00";
        readings[2] = "test";

        // Act

        //Assert

        validReader.parseAndLog(readings, logger, validSensorList);
    }

    @Test
    void seeIfReadAndSetWorksIOExceptionFileHandler() {
        // Act

        boolean actualResult = validReader.readAndSet(validGeographicAreaList, validCSVLocation1, invalidLogPath);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfReadAndSetWorks() {
        // Act

        boolean actualResult = validReader.readAndSet(validGeographicAreaList, validCSVLocation1, validLogPath);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfParseAndLogReadingWorks() {
        // Arrange


        String[] readings = new String[3];
        readings[0] = "RF12345";
        readings[1] = "2019-12-30T02:00:00+00:00";
        readings[2] = "23";

        // Act

        int actualResult = validReader.parseAndLogCSVReading(readings, logger, validSensorList);

        // Assert

        assertEquals(1, actualResult);
    }

    @Test
    void seeIfParseAndLogReadingWorksWithExtraParameter() {
        // Arrange


        String[] readings = new String[4];
        readings[0] = "RF12345";
        readings[1] = "2019-12-30T02:00:00+00:00";
        readings[2] = "23";
        readings[3] = "12";

        // Act

        int actualResult = validReader.parseAndLogCSVReading(readings, logger, validSensorList);

        // Assert

        assertEquals(1, actualResult);
    }

    @Test
    void seeIfParseAndLogReadingFailsWithWrongSensor() {
        // Arrange


        String[] readings = new String[3];
        readings[0] = "wrong sensor";
        readings[1] = "2019-12-30T02:00:00+00:00";
        readings[2] = "23";

        // Act

        int actualResult = validReader.parseAndLogCSVReading(readings, logger, validSensorList);

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

        int actualResult = validReader.parseAndLogCSVReading(readings, logger, validSensorList);

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

        int actualResult = validReader.parseAndLogCSVReading(readings, logger, emptyList);

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

        int actualResult = validReader.parseAndLogCSVReading(readings, logger, emptyList);

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

        int actualResult = validReader.parseAndLogCSVReading(readings, logger, validSensorList);

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

        int actualResult = validReader.parseAndLogCSVReading(readings, logger, validSensorList);

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

    @Test
    void seeIfReadReadingsFromCSVWorks() {
        //Arrange


        // Act

        int actualResult = validReader.readReadingsFromCSV(validGeographicAreaList, validCSVLocation1, validLogPath);

        // Assert

        assertEquals(9, actualResult);
    }


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

    @Test
    void seeIfParseAndLogJSONReadingWorks() {
        //Arrange

        JSONObject validJSONObj = new JSONObject();
        validJSONObj.put("id", "TT12346");
        validJSONObj.put("timestamp/date", "2018-12-30T02:00:00+00:00");
        validJSONObj.put("value", "57.2");
        validJSONObj.put("unit", "F");


        // Act

        int actualResult = validReader.parseAndLogJSONReading(validSensorList, validJSONObj, logger);

        // Assert

        assertEquals(1, actualResult);
    }

    @Test
    void seeIfParseAndLogJSONReadingWorksWithInvalidDateFormat() {
        //Arrange

        JSONObject validJSONObj = new JSONObject();
        validJSONObj.put("id", "TT12346");
        validJSONObj.put("timestamp/date", "00:00+00:00");
        validJSONObj.put("value", "57.2");
        validJSONObj.put("unit", "F");


        // Act

        int actualResult = validReader.parseAndLogJSONReading(validSensorList, validJSONObj, logger);

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

        int actualResult = validReader.parseAndLogJSONReading(validSensorList, validJSONObj, logger);

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

        int actualResult = validReader.parseAndLogJSONReading(validSensorList, validJSONObj, logger);

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

        int actualResult = validReader.parseAndLogJSONReading(validSensorList, validJSONObj, logger);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfParseAndLogJSONReadingsWorks() {

        //Arrange

        JSONObject validJSONObj1 = new JSONObject();
        validJSONObj1.put("id", "TT12346");
        validJSONObj1.put("timestamp/date", "2018-12-30T02:00:00+00:00");
        validJSONObj1.put("value", "23.4");
        validJSONObj1.put("unit", "F");

        JSONObject validJSONObj2 = new JSONObject();
        validJSONObj2.put("id", "TT12346");
        validJSONObj2.put("timestamp/date", "2018-12-30T03:00:00+00:00");
        validJSONObj2.put("value", "25.6");
        validJSONObj2.put("unit", "F");

        JSONArray validJSONArray = new JSONArray();
        validJSONArray.put(validJSONObj1);
        validJSONArray.put(validJSONObj2);

        // Act

        int actualResult = validReader.parseAndLogJSONReadings(validSensorList, validJSONArray, logger);

        // Assert

        assertEquals(2, actualResult);
    }

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

        int actualResult = validReader.parseAndLogJSONReadings(validSensorList, validJSONArray, logger);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadReadingsFromJSONWorksWithEmptySensorList() {
        // Act

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

    @Test
    void seeIfReadReadingsFromJSONWorksWhenOneReadingHasWrongSensorId() {

        // Act

        int actualResult = validReader.readReadingsFromJSON(validGeographicAreaList, validJSONLocation2, validLogPath);

        // Assert

        assertEquals(4, actualResult);
    }

    @Test
    void seeIfReadReadingsFromJSONWorksWhenReadingsHaveSameDate() {

        // Act

        int actualResult = validReader.readReadingsFromJSON(validGeographicAreaList, validJSONLocation3, validLogPath);

        // Assert

        assertEquals(1, actualResult);
    }

    @Test
    void seeIfReadReadingsFromJSONWorksWhenInputValuesAreWrong() {

        // Act

        int actualResult = validReader.readReadingsFromJSON(validGeographicAreaList, validJSONLocation4, validLogPath);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadReadingsFromJSONThrowsExceptionWithInvalidLogPath() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> validReader.readReadingsFromJSON(validGeographicAreaList, validJSONLocation3, invalidLogPath));
    }

    @Test
    void seeIfReadReadingsFromXMLWorksWhenSensorListIsEmpty() {
        // Act

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

    @Test
    void seeIfReadReadingsFromXMLWorksWhenOneReadingHasWrongSensorId() {
        // Act

        int actualResult = validReader.readReadingsFromXML(validGeographicAreaList, validXMLocation2, validLogPath);

        // Assert

        assertEquals(4, actualResult);
    }

    @Test
    void seeIfReadReadingsFromXMLWorksWhenReadingsHaveSameDate() {
        // Act

        int actualResult = validReader.readReadingsFromXML(validGeographicAreaList, validXMLocation3, validLogPath);

        // Assert

        assertEquals(1, actualResult);
    }

    @Test
    void seeIfReadReadingsFromXMLWorksWhenInputValuesAreWrong() {
        // Act

        int actualResult = validReader.readReadingsFromXML(validGeographicAreaList, validXMLocation4, validLogPath);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void seeIfReadReadingsFromXMLThrowsExceptionWithInvalidLogPath() {
        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> validReader.readReadingsFromXML(validGeographicAreaList, validXMLocation2, invalidLogPath));
    }
}
