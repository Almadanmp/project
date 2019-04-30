package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Reading tests class.
 */

class ReadingTest {
    private Date earlyDate;
    private Date lateDate;
    private Reading firstValidReading;

    @BeforeEach
    void arrangeArtifacts() {
        earlyDate = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();
        lateDate = new GregorianCalendar(2018, Calendar.APRIL, 25).getTime();
        firstValidReading = new Reading(31, earlyDate, "C", "Test");
    }

    @Test
    void seeIfGetDateWorks() {
        // Arrange

        Date expectedResult = earlyDate;

        // Act

        Date actualResult = firstValidReading.getDate();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetDateWorks() {
        // Arrange

        Date expectedResult = lateDate;
        firstValidReading.setDate(lateDate);

        //Act

        Date actualResult = firstValidReading.getDate();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetValueWorks() {
        // Act

        double actualResult = firstValidReading.getValue();

        // Assert

        assertEquals(31, actualResult, 0.01);
    }

    @Test
    void seeIfSetGetValueWorks() {
        // Arrange

        firstValidReading.setValue(51);

        // Act

        double actualResult = firstValidReading.getValue();

        // Assert

        assertEquals(51, actualResult, 0.01);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentObject() {
        //Arrange

        int testInt = 0;

        // Act

        boolean actualResult = firstValidReading.equals(testInt); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksTrue() {
        // Arrange

        Reading testReading = new Reading(31, earlyDate, "C", "Test");
        Reading reading2 = new Reading(31, earlyDate, "C", "Test1");
        Reading reading3 = new Reading(31, lateDate, "C", "Test");

        // Act

        boolean actualResult = testReading.equals(firstValidReading);
        boolean actualResult1 = testReading.equals(reading2);
        boolean actualResult2 = testReading.equals(reading3);
        boolean actualResult3 = testReading.equals(testReading);

        // Assert

        assertTrue(actualResult);
        assertFalse(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = firstValidReading.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAndGetUnitWorks() {
        //Arrange

        String expectedResult = "C";

        // Act

        String actualResult = firstValidReading.getUnit();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAndGetSensorIdWorks() {
        //Arrange
        Reading reading = new Reading();
        reading.setSensorID("Sensor 01");
        String expectedResult = "Sensor 01";
        //Act
        String actualResult = reading.getSensorID();
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}