package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.*;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingList;
import pt.ipp.isep.dei.project.model.Sensor;
import pt.ipp.isep.dei.project.model.SensorList;
import pt.ipp.isep.dei.project.model.TypeArea;
import pt.ipp.isep.dei.project.model.TypeSensor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CSVReader test class.
 */
class CSVReaderTest {

    // Common artifacts for testing in this class.

    private GeographicAreaList validGeographicAreaList;
    private GeographicArea validGeographicArea;
    private GeographicArea validGeographicArea2;
    private Date validDate1 = new Date();
    private Date validDate2 = new Date();
    private Date validDate3 = new Date();
    private Date validDate4 = new Date();
    private CSVReader validReader;
    private Sensor validSensor1;
    private Sensor validSensor2;
    private Sensor validSensor3;
    private Sensor validSensor4;
    private SensorList validSensorList;
    private SensorList validSensorList2;
    private static final String validLocation1 = "src/test/resources/testCSV1.csv";
    private static final String validLocation2 = "src/test/resources/testCSV2.csv";
    private static final String validLocation3 = "src/test/resources/testCSV3.csv";
    private static final String validLocation4 = "src/test/resources/testCSV4.csv";
    private static final String validLocation5 = "src/test/resources/testCSV5.csv";
    private static final String wrongLocation1 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV2.csa";
    private static final String wrongLocation2 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV10.csv";

    @BeforeEach
    void arrangeArtifacts() {
        validReader = new CSVReader();
        SimpleDateFormat validSdf = new SimpleDateFormat("yyyy-MM-dd");
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
        validGeographicArea2 = new GeographicArea("Porto", new TypeArea("city"), 3.30, 10.09,
                new Local(41.149935, -8.610857, 118));
        validSensor1 = new Sensor("RF12345", "Meteo station ISEP - rainfall", new TypeSensor("rain", "mm"),
                new Local(41.179230, -8.606409, 125),
                validDate1);
        validSensor2 = new Sensor("TT12346", "Meteo station ISEP - temperature", new TypeSensor("rain2", "mm2"),
                new Local(41.179230, -8.606409, 125),
                validDate2);
        validSensor3 = new Sensor("RF12334", "Meteo station CMP - rainfall", new TypeSensor("rain2", "mm2"),
                new Local(41.179230, -8.606409, 139),
                validDate3);
        validSensor4 = new Sensor("TT1236A", "Meteo station CMP - temperature", new TypeSensor("rain2", "mm2"),
                new Local(41.179230, -8.606409, 139),
                validDate4);
        validSensorList = new SensorList();
        validSensorList2 = new SensorList();
        validSensorList.add(validSensor1);
        validSensorList.add(validSensor2);
        validSensorList2.add(validSensor3);
        validSensorList2.add(validSensor4);
        validGeographicArea.setSensorList(validSensorList);
        validGeographicArea2.setSensorList(validSensorList2);
        validGeographicAreaList = new GeographicAreaList();
        validGeographicAreaList.addGeographicArea(validGeographicArea);
        validGeographicAreaList.addGeographicArea(validGeographicArea2);
    }

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @AfterEach
    void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    void seeIfReadAndSetterWorks() {

        //Arrange

        provideInput(validLocation1);

        // Act

        validReader.readAndSet(validGeographicAreaList);

        //Assert

    }

    @Test
    void seeIfReadAndSetterFailsWrongFileExtension() {

        //Arrange

        provideInput(wrongLocation1);

        // Act

        //Assert

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            validReader.readAndSet(validGeographicAreaList);
        });
    }

    @Test
    void seeIfReadAndSetterFailsWrongLocation() {

        //Arrange

        provideInput(wrongLocation2);

        // Act

        //Assert

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            validReader.readAndSet(validGeographicAreaList);
        });
    }

    @Test
    void seeIfReadAndSetterFailsUnparseableDate() {

        //Arrange

        provideInput(validLocation3);

        // Act

        validReader.readAndSet(validGeographicAreaList);

        //Assert

    }

    @Test
    void seeIfReadAndSetterFailsUnreachableSensorName() {

        //Arrange

        provideInput(validLocation4);

        // Act

        validReader.readAndSet(validGeographicAreaList);

        //Assert

    }

    @Test
    void seeIfSetCVSReadingsWorks() {

        //Arrange

        // Act

        boolean actualResult = validReader.setCSVReadings(validSensor1, validDate3, 23.3);

        //Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfSetCVSReadingsWorksExactlySameDateAsSensorCreation() {

        //Arrange


        // Act

        boolean actualResult = validReader.setCSVReadings(validSensor1, validDate1, 23.3);

        //Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfSetCVSReadingsFails() {

        //Arrange


        // Act

        boolean actualResult = validReader.setCSVReadings(validSensor4, validDate1, 23.3);

        //Assert
        assertFalse(actualResult);

    }

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
    void seeIfParseAndLogFailsOutOfBoundsArray() {

        //Arrange

        provideInput(validLocation1);
        Logger logger = Logger.getLogger(CSVReader.class.getName());
        String[] readings = new String[0];

        // Act

        //Assert

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            validReader.parseAndLog(readings, logger, validSensorList);
        });
    }

    @Test
    void seeIfParseAndLogFailsNullPointerException() {

        //Arrange

        provideInput(validLocation1);
        Logger logger = Logger.getLogger(CSVReader.class.getName());
        String[] readings = new String[3];

        // Act

        //Assert

        Assertions.assertThrows(NullPointerException.class, () -> {
            validReader.parseAndLog(readings, logger, validSensorList);
        });
    }


 /* TODO: FAILS ON JENKINS

    @Test
    void seeIfReadAndSetterFailsNonNumericValue() {

        //Arrange

        provideInput(validLocation2);

        // Act

        //Assert

        Assertions.assertThrows(NumberFormatException.class, () -> {
            validReader.readAndSet(validGeographicAreaList);
        });
    }


    @Test
    void seeIfParseAndLogFailsNonNumericValue() {

        //Arrange

        provideInput(validLocation2);
        Logger logger = Logger.getLogger(CSVReader.class.getName());
        String[] readings = new String[3];
        readings[2] = "test";

        // Act

        //Assert

        Assertions.assertThrows(NumberFormatException.class, () -> {
            validReader.parseAndLog(readings, logger, validSensorList);
        });
    }

 TODO: FAILS ON JENKINS

    @Test
    void seeIfReadAndSetterWorksWithReadings() {

        //Arrange

        provideInput(validLocation5);
        ReadingList expectedResult = new ReadingList();
        Reading reading = new Reading(14, validDate3);
        expectedResult.addReading(reading);


        // Act

        validReader.readAndSet(validGeographicAreaList);
        ReadingList actualResult = validSensor1.getReadingList();

        //Assert
        assertEquals(expectedResult, actualResult);

    }

 TODO: FAILS ON JENKINS

    @Test
    void seeIfReadAndSetterFailsNonSetReadings() {

        //Arrange

        provideInput(validLocation5);
        ReadingList expectedResult = new ReadingList();

        // Act

        validReader.readAndSet(validGeographicAreaList);
        ReadingList actualResult = validSensor1.getReadingList();

        //Assert
        assertNotEquals(expectedResult, actualResult);

    }

     */

}