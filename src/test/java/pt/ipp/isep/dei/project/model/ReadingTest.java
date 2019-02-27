package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Reading secondValidReading;

    @BeforeEach
    void arrangeArtifacts() {
        earlyDate = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();
        lateDate = new GregorianCalendar(2018, Calendar.APRIL, 25).getTime();
        firstValidReading = new Reading(31, earlyDate);
        secondValidReading = new Reading(-5, lateDate);
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

        firstValidReading.setValue(31);

        // Act

        double actualResult = firstValidReading.getValue();

        // Assert

        assertEquals(31, actualResult, 0.01);
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

        Reading testReading = new Reading(31, earlyDate);

        // Act

        boolean actualResult = testReading.equals(firstValidReading);

        // Assert

        assertTrue(actualResult);
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
}