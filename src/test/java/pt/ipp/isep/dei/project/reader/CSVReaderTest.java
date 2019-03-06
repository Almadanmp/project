package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CSVReader test class.
 */
class CSVReaderTest {

    // Common artifacts for testing in this class.
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";
    private House validHouse;
    private Date validDate1 = new Date();
    private Date validDate2 = new Date();
    private Date validDate3 = new Date();
    private CSVReader validReader;
    private Sensor validSensor1;
    private Sensor validSensor2;
    private SensorList validSensorList;
    private GeographicArea validGeoArea;
    private static final String validLocation1 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV1.csv";
    private static final String validLocation2 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV2.csv";
    private static final String validLocation3 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV3.csv";
    private static final String validLocation4 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV4.csv";
    private static final String validLocation5 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV5.csv";
    private static final String wrongLocation1 = "src/test/java/pt/ipp/isep/dei/project/reader/testCSV2.csa";

    @BeforeEach
    void arrangeArtifacts() {
        validReader = new CSVReader();
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        SimpleDateFormat validSdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate1 = validSdf2.parse("2018-10-31T08:00:00+00:00");
            validDate2 = validSdf2.parse("2017-12-30T06:00:00+00:00");
            validDate3 = validSdf2.parse("2020-12-30T14:00:00+00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        validGeoArea = new GeographicArea("Portugal", new TypeArea("Country"), 34000, 300000,
                new Local(3, 3, 3));
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20),validGeoArea, 60,
                180, deviceTypeString);
        validSensor1 = new Sensor("Sensor1", new TypeSensor("rain", "mm"), new Local(2, 3, 4),
                validDate1);
        validSensor2 = new Sensor("Sensor2", new TypeSensor("rain2", "mm2"), new Local(2, 2, 2),
                validDate2);
        validSensorList = new SensorList();
        validSensorList.add(validSensor1);
        validSensorList.add(validSensor2);
        validGeoArea.setSensorList(validSensorList);
    }

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
/*
    @Test
    void seeIfReadAndSetterWorksWithReadings() {

        //Arrange

        InputStream in = new ByteArrayInputStream(validLocation4.getBytes());
        System.setIn(in);
        ReadingList expectedResult = new ReadingList();
        Reading reading = new Reading(16.5,validDate3);
        expectedResult.addReading(reading);

        // Act

        validReader.readAndSet(validHouse);
        ReadingList actualResult = validSensor1.getReadingList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    */
}