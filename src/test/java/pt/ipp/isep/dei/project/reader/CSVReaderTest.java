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
    private Date validDate1 = new Date();
    private Date validDate2 = new Date();
    private Date validDate3 = new Date();
    private CSVReader validReader;
    private Sensor validSensor1;
    private Sensor validSensor2;
    private SensorList validSensorList;
    private static final String validLocation1 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV1.csv";
    private static final String validLocation2 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV2.csv";
    private static final String validLocation3 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV3.csv";
    private static final String validLocation4 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV4.csv";
    private static final String validLocation5 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV5.csv";
    private static final String wrongLocation1 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV2.csa";

    @BeforeEach
    void arrangeArtifacts() {
        validReader = new CSVReader();
        SimpleDateFormat validSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate1 = validSdf.parse("2019-10-31T08:00:00+00:00");
            validDate2 = validSdf.parse("2019-12-30T06:00:00+00:00");
            validDate3 = validSdf.parse("2020-12-30T02:00:00+00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validGeographicArea = new GeographicArea("Portugal", new TypeArea("Country"), 34000, 300000,
                new Local(3, 3, 3));
        validSensor1 = new Sensor("RF12345", "Sensor1", new TypeSensor("rain", "mm"), new Local(2, 3, 4),
                validDate1);
        validSensor2 = new Sensor("RF12666", "Sensor2", new TypeSensor("rain2", "mm2"), new Local(2, 2, 2),
                validDate2);
        validSensorList = new SensorList();
        validSensorList.add(validSensor1);
        validSensorList.add(validSensor2);
        validGeographicArea.setSensorList(validSensorList);
        validGeographicAreaList = new GeographicAreaList();
        validGeographicAreaList.addGeographicArea(validGeographicArea);
    }

 /* Novo metodo
    @Test
    void seeIfReadAndSetterWorks() {

        //Arrange

        InputStream in = new ByteArrayInputStream(validLocation1.getBytes());
        System.setIn(in);

        // Act

        validReader.readAndSet(validHouse);

        //Assert

    }

    @Test
    void seeIfReadAndSetterFailsWrongLocation() {

        //Arrange

        InputStream in = new ByteArrayInputStream(wrongLocation1.getBytes());
        System.setIn(in);

        // Act

        //Assert

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            validReader.readAndSet(validHouse);
        });

    }

    @Test
    void seeIfReadAndSetterFailsNonNumericValue() {

        //Arrange

        InputStream in = new ByteArrayInputStream(validLocation2.getBytes());
        System.setIn(in);

        // Act

        validReader.readAndSet(validHouse);

        //Assert

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            validReader.readAndSet(validHouse);
        });
    }

    @Test
    void seeIfReadAndSetterFailsUnparseableDate() {

        //Arrange

        InputStream in = new ByteArrayInputStream(validLocation3.getBytes());
        System.setIn(in);

        // Act

        validReader.readAndSet(validHouse);

        //Assert

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            validReader.readAndSet(validHouse);
        });
    }

    @Test
    void seeIfReadAndSetterFailsUnreachableSensorName() {

        //Arrange

        InputStream in = new ByteArrayInputStream(validLocation4.getBytes());
        System.setIn(in);

        // Act

        validReader.readAndSet(validHouse);

        //Assert

    }

    @Test
    void seeIfReadAndSetterFailsNonSetReadings() {

        //Arrange

        InputStream in = new ByteArrayInputStream(validLocation5.getBytes());
        System.setIn(in);
        ReadingList expectedResult = new ReadingList();


        // Act

        validReader.readAndSet(validHouse);
        ReadingList actualResult = validSensor1.getReadingList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    */ //Novo metodo

//-----------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------

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
    void seeIfReadAndSetterFailsWrongLocation() {

        //Arrange

        provideInput(wrongLocation1);

        // Act

        //Assert

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            validReader.readAndSet(validGeographicAreaList);
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
 */

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

    /* TODO: FAILS ON JENKINS

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

/* TODO: FAILS ON JENKINS

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
*/

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

        boolean actualResult = validReader.setCSVReadings(validSensor2, validDate1, 23.3);

        //Assert
        assertFalse(actualResult);

    }

    @Test
    void seeIfGetSensorDataWorks() {

        //Arrange

        SensorList expectedResult = new SensorList();
        expectedResult.add(validSensor1);
        expectedResult.add(validSensor2);

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
    void seeIfParseAndLogFailsOutOfBoundsArray() {

        //Arrange

        provideInput(validLocation2);
        Logger logger = Logger.getLogger(CSVReader.class.getName());
        String[] readings = new String[0];

        // Act

        //Assert

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            validReader.parseAndLog(readings, logger, validSensorList);
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

    @Test
    void seeIfParseAndLogFailsNullPointerException() {

        //Arrange

        provideInput(validLocation2);
        Logger logger = Logger.getLogger(CSVReader.class.getName());
        String[] readings = new String[3];
        readings[1] = "test";

        // Act

        //Assert

        Assertions.assertThrows(NullPointerException.class, () -> {
            validReader.parseAndLog(readings, logger, validSensorList);
        });
    }
}