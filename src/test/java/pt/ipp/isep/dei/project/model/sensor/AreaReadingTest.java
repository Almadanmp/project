package pt.ipp.isep.dei.project.model.sensor;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.services.units.Celsius;
import pt.ipp.isep.dei.project.services.units.Fahrenheit;
import pt.ipp.isep.dei.project.services.units.Unit;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Reading tests class.
 */

class AreaReadingTest {
    private Date earlyDate;
    private Date lateDate;
    private AreaReading firstValidAreaReading;

    @BeforeEach
    void arrangeArtifacts() {
        earlyDate = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();
        lateDate = new GregorianCalendar(2018, Calendar.APRIL, 25).getTime();
        firstValidAreaReading = new AreaReading(31, earlyDate, new Celsius());
    }

    @Test
    void seeIfGetDateWorks() {
        // Arrange

        Date expectedResult = earlyDate;

        // Act

        Date actualResult = firstValidAreaReading.getDate();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetDateWorks() {
        // Arrange

        Date expectedResult = lateDate;
        firstValidAreaReading.setDate(lateDate);

        //Act

        Date actualResult = firstValidAreaReading.getDate();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetValueWorks() {
        // Act

        double actualResult = firstValidAreaReading.getValue();

        // Assert

        assertEquals(31, actualResult, 0.01);
    }

    @Test
    void seeIfSetGetValueWorks() {
        // Arrange

        firstValidAreaReading.setValue(51);

        // Act

        double actualResult = firstValidAreaReading.getValue();

        // Assert

        assertEquals(51, actualResult, 0.01);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentObject() {
        //Arrange

        int testInt = 0;

        // Act

        boolean actualResult = firstValidAreaReading.equals(testInt); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksTrue() {
        // Arrange

        AreaReading testAreaReading = new AreaReading(31, earlyDate, new Celsius());

        // Act

        boolean actualResult = testAreaReading.equals(firstValidAreaReading);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = firstValidAreaReading.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAndGetUnitWorks() {
        //Arrange

        Unit expectedResult = new Fahrenheit();

        // Act

        firstValidAreaReading.setUnit(new Fahrenheit());
        Unit actualResult = firstValidAreaReading.getUnit();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}