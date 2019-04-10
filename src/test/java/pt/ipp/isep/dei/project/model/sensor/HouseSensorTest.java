package pt.ipp.isep.dei.project.model.sensor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Local;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * House Sensor tests class.
 */

class HouseSensorTest {

    // Common artifacts for testing in this class.

    private HouseSensor validHouseSensor;

    @BeforeEach
    void arrangeArtifacts() {
        validHouseSensor = new HouseSensor("T32875", "SensOne",  new SensorType("Temperature", "Celsius"), new Date(), "RoomAD");
        validHouseSensor.setActive(true);
    }

    @Test
    void seeIfEmptyConstructorWorks() {
        // Arrange
        HouseSensor houseSensorABC = new HouseSensor();
        houseSensorABC.setId("ABC");
        String expectedResult = "ABC";
        // Act
        String actualResult = houseSensorABC.getId();
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

        HouseSensor areaSensor = new HouseSensor("T32875", "Sensor", new SensorType("Temperature", "Celsius"), startDate, "RoomFD");

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

        SensorType actualResult = validHouseSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetNameWorks() {
        // Arrange

        String expectedResult = "XXB6";

        // Act

        validHouseSensor.setName("XXB6");
        String actualResult = validHouseSensor.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfNullSensorNameThrowsStringMessage() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validHouseSensor.setName(null));

        // Assert

        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfEmptySensorNameThrowsException() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validHouseSensor.setName(""));

        // Assert

        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Arrange

        Local testLocal = new Local(21, 23, 1);

        // Act

        boolean actualResult = validHouseSensor.equals(testLocal); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentSensor() {
        // Arrange

        HouseSensor s2 = new HouseSensor("T54654", "Temperature Sensor XX56", new SensorType("Temperature", "Fahrenheit"),
                 new Date(), "RoomFD");

        // Act

        boolean actualResult = validHouseSensor.equals(s2);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksTrueSameSensor() {
        // Arrange

        HouseSensor testAreaSensor = new HouseSensor("T345", "SensOne", new SensorType("Temperature", "Celsius"),new Date(), "RoomHG");

        // Act

        boolean actualResult = validHouseSensor.equals(testAreaSensor);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validHouseSensor.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintDeactivated() {
        // Arrange

        String expectedResult = "Active";
        // Act

        String result = validHouseSensor.printActive();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintActive() {
        // Arrange

        String expectedResult = "Deactivated";
        validHouseSensor.deactivateSensor();

        // Act

        String result = validHouseSensor.printActive();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfActive() {

        // Act
        validHouseSensor.deactivateSensor();
        // Act
        boolean result = validHouseSensor.deactivateSensor();

        // Assert

        Assertions.assertFalse(result);
    }

    @Test
    void seeIfDeactivates() {

        // Act
        boolean result = validHouseSensor.deactivateSensor();

        // Assert

        Assertions.assertTrue(result);
    }

    @Test
    void seeIfSecondConstructorSetsTypeSensorCorrectly() {
        // Arrange

        validHouseSensor = new HouseSensor("T45", "SensOne", new SensorType("Temperature", "Kelvin"), new Date(), "RoomGK");
        SensorType expectedResult = new SensorType("Temperature", "Kelvin");

        // Act

        SensorType actualResult = validHouseSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddReadingsWorks() {
        // Arrange

        Reading reading1 = new Reading(20, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "C", "TEST");

        // Act

        boolean actualResult1 = validHouseSensor.addReading(reading1);
        boolean actualResult3 = validHouseSensor.addReading(reading1);

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfAddReadingsWorksNotActiveResult() {
        // Arrange

        Reading reading1 = new Reading(20, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "C", "TEST");
        validHouseSensor.deactivateSensor();
        // Act

        boolean actualResult1 = validHouseSensor.addReading(reading1);
        boolean actualResult3 = validHouseSensor.addReading(reading1);

        // Assert

        assertFalse(actualResult1);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfSetGetIdWorks() {
        // Arrange

        String expectedResult = "XXB6";

        // Act

        validHouseSensor.setId("XXB6");
        String actualResult = validHouseSensor.getId();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

}