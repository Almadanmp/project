package pt.ipp.isep.dei.project.model.room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * House Sensor tests class.
 */

class RoomSensorTest {

    // Common artifacts for testing in this class.

    private RoomSensor validRoomSensor;
    private Date validDate1;
    private Date validDate2;
    private Date validDate3;

    @BeforeEach
    void arrangeArtifacts() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("01/04/2018 10:00:00");
            validDate2 = validSdf.parse("01/06/2017 10:00:00");
            validDate3 = validSdf.parse("01/05/2016 10:00:00");

        } catch (
                ParseException c) {
            c.printStackTrace();
        }

        validRoomSensor = new RoomSensor("T32875", "SensOne", new SensorType("Temperature", "Celsius"), validDate2, "RoomAD");
        validRoomSensor.setActive(true);
    }

    @Test
    void seeIfAddReadingWorksWhenDateSameSensorActivationDate() {
        //Arrange

        Reading reading = new Reading(21D, validDate2, "C", "sensorID");

        // Act

        boolean actualResult = validRoomSensor.addReading(reading);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddReadingWorksWhenDateBeforeSensorActivationDate() {
        //Arrange

        Reading reading = new Reading(21D, validDate3, "C", "sensorID");

        // Act

        boolean actualResult = validRoomSensor.addReading(reading);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddReadingWorksWhenDateSameAfterActivationDate() {
        //Arrange

        Reading reading = new Reading(21D, validDate1, "C", "sensorID");

        // Act

        boolean actualResult = validRoomSensor.addReading(reading);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddReadingWorksWhenReadingAlreadyExists() {
        //Arrange

        Reading reading = new Reading(21D, validDate2, "C", "sensorID");
        validRoomSensor.addReading(reading);

        // Act

        boolean actualResult = validRoomSensor.addReading(reading);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfActiveDuringDateWorks() {
        // Act

        boolean actualResult = validRoomSensor.activeDuringDate(validDate1);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfActiveDuringDateWorksWhenDateGivenIsOlder() {
        // Act

        boolean actualResult = validRoomSensor.activeDuringDate(validDate3);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfReadingWithGivenDateExistsWorks() {
        // Arrange

        Reading reading = new Reading(21D, validDate1, "C", "sensorID");
        validRoomSensor.addReading(reading);

        // Act

        boolean actualResult = validRoomSensor.readingWithGivenDateExists(validDate1);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfReadingWithGivenDateExistsWorksWhenItDoesNot() {
        // Arrange

        Reading reading = new Reading(21D, validDate2, "C", "sensorID");
        validRoomSensor.addReading(reading);

        // Act

        boolean actualResult = validRoomSensor.readingWithGivenDateExists(validDate1);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEmptyConstructorWorks() {
        // Arrange
        RoomSensor roomSensorABC = new RoomSensor();
        roomSensorABC.setId("ABC");
        String expectedResult = "ABC";
        // Act
        String actualResult = roomSensorABC.getId();
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfConstructorSetsDate() {
        // Arrange

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date startDate = new Date();

        try {
            startDate = validSdf.parse("11/01/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        RoomSensor areaSensor = new RoomSensor("T32875", "Sensor", new SensorType("Temperature", "Celsius"), startDate, "RoomFD");

        // Act

        Date actualResult = areaSensor.getDateStartedFunctioning();

        // Assert

        assertEquals(startDate, actualResult);
    }


    @Test
    void seeIfConstructorSetsTypeArea() {
        // Arrange

        SensorType expectedResult = new SensorType("Temperature", "Celsius");

        // Act

        SensorType actualResult = validRoomSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetNameWorks() {
        // Arrange

        String expectedResult = "XXB6";

        // Act

        validRoomSensor.setName("XXB6");
        String actualResult = validRoomSensor.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfNullSensorNameThrowsStringMessage() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validRoomSensor.setName(null));

        // Assert

        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfEmptySensorNameThrowsException() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validRoomSensor.setName(""));

        // Assert

        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Arrange

        Local testLocal = new Local(21, 23, 1);

        // Act

        boolean actualResult = validRoomSensor.equals(testLocal); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentSensor() {
        // Arrange

        RoomSensor s2 = new RoomSensor("T54654", "Temperature Sensor XX56", new SensorType("Temperature", "Fahrenheit"),
                new Date(), "RoomFD");

        // Act

        boolean actualResult = validRoomSensor.equals(s2);

        // Assert

        assertFalse(actualResult);
    }

//    @Test
//    void seeIfEqualsWorksTrueSameSensor() {
//        // Arrange
//
//        RoomSensor testAreaSensor = new RoomSensor("T345", "SensOne", new SensorType("Temperature", "Celsius"), new Date(), "RoomHG");
//
//        // Act
//
//        boolean actualResult = validRoomSensor.equals(testAreaSensor);
//
//        // Assert
//
//        assertTrue(actualResult);
//    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validRoomSensor.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintDeactivated() {
        // Arrange

        String expectedResult = "Active";
        // Act

        String result = validRoomSensor.printActive();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintActive() {
        // Arrange

        String expectedResult = "Deactivated";
        validRoomSensor.deactivateSensor();

        // Act

        String result = validRoomSensor.printActive();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfActive() {

        // Act
        validRoomSensor.deactivateSensor();
        // Act
        boolean result = validRoomSensor.deactivateSensor();

        // Assert

        Assertions.assertFalse(result);
    }

    @Test
    void seeIfDeactivates() {

        // Act
        boolean result = validRoomSensor.deactivateSensor();

        // Assert

        Assertions.assertTrue(result);
    }

    @Test
    void seeIfSecondConstructorSetsTypeSensorCorrectly() {
        // Arrange

        validRoomSensor = new RoomSensor("T45", "SensOne", new SensorType("Temperature", "Kelvin"), new Date(), "RoomGK");
        SensorType expectedResult = new SensorType("Temperature", "Kelvin");

        // Act

        SensorType actualResult = validRoomSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetIdWorks() {
        // Arrange

        String expectedResult = "XXB6";

        // Act

        validRoomSensor.setId("XXB6");
        String actualResult = validRoomSensor.getId();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfBuildStringWorks() {
        // Arrange

        String expectedResult = "SensOne, Temperature\n";

        // Act

        String actualResult = validRoomSensor.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintActiveFalseWorks() {
        // Arrange

        String expectedResult = "Active";
        validRoomSensor.setActive(true);

        // Act

        String actualResult = validRoomSensor.printActive();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsSameObjectWorks() {
        // Act

        boolean actualResult = validRoomSensor.equals(validRoomSensor);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetSensorTypeNameWorks() {
        // Arrange

        String expectedResult = "Temperature";

        // Act

        String actualResult = validRoomSensor.getSensorTypeName();

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfSetAndGetHouseReadingsWorks() {
        //Arrange
        List<Reading> readingList = new ArrayList<>();
        Reading reading = new Reading(21, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "C", "test");
        readingList.add(reading);
        validRoomSensor.setReadings(readingList);
        //Act
        List<Reading> actualResult = validRoomSensor.getReadings();
        //Assert
        assertEquals(readingList, actualResult);
    }

    @Test
    void seeIfReadingExistsWorks() {
        //Arrange
        List<Reading> readingList = new ArrayList<>();
        Reading reading = new Reading(21, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "C", "test");
        readingList.add(reading);
        validRoomSensor.setReadings(readingList);
        //Act
        boolean actualResult = validRoomSensor.readingWithGivenDateExists(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        boolean actualResult1 = validRoomSensor.readingWithGivenDateExists(new GregorianCalendar(2018, Calendar.JANUARY, 1).getTime());
        //Assert
        assertTrue(actualResult);
        assertFalse(actualResult1);
    }

    @Test
    void seeIfReadingExistsDoesNotWork() {
        //Act
        boolean actualResult = validRoomSensor.readingWithGivenDateExists(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        //Assert
        assertFalse(actualResult);
    }
}