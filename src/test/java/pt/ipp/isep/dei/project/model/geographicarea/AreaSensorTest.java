package pt.ipp.isep.dei.project.model.geographicarea;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.JANUARY;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Sensor tests class.
 */

class AreaSensorTest {

    // Common artifacts for testing in this class.

    private AreaSensor validAreaSensor;
    private Date validDate1;
    private Date validDate2;
    private Date validDate3;

    @BeforeEach
    void arrangeArtifacts() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("01/04/2018 00:00:00");
            validDate2 = validSdf.parse("01/04/2017 00:00:00");
            validDate3 = validSdf.parse("01/04/2016 00:00:00");

        } catch (
                ParseException c) {
            c.printStackTrace();
        }
        validAreaSensor = new AreaSensor("SensOne", "SensOne", "Temperature", new Local(10, 10, 10), validDate2);
        validAreaSensor.setActive(true);
    }

    @Test
    void seeIfAddReadingWorksWhenDateSameAsActivationDate() {
        //Arrange

        Reading reading = new Reading(21D, validDate2, "C", "sensorID");

        // Act

        boolean actualResult = validAreaSensor.addReading(reading);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddReadingWorksWhenDateBeforeActivationDate() {
        //Arrange

        Reading reading = new Reading(21D, validDate3, "C", "sensorID");

        // Act

        boolean actualResult = validAreaSensor.addReading(reading);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddReadingWorksWhenDateAfterActivationDate() {
        //Arrange

        Reading reading = new Reading(21D, validDate1, "C", "sensorID");

        // Act

        boolean actualResult = validAreaSensor.addReading(reading);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddReadingWorksWhenReadingExists() {
        //Arrange

        Reading reading = new Reading(21D, validDate2, "C", "sensorID");
        validAreaSensor.addReading(reading);

        // Act

        boolean actualResult = validAreaSensor.addReading(reading);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfActiveDuringDateWorks() {
        // Act

        boolean actualResult = validAreaSensor.activeDuringDate(validDate1);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfActiveDuringDateWorksWhenDateGivenIsOlder() {
        // Act

        boolean actualResult = validAreaSensor.activeDuringDate(validDate3);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfReadingWithGivenDateExistsWorks() {
        // Arrange

        Reading reading = new Reading(21D, validDate1, "C", "sensorID");
        validAreaSensor.addReading(reading);

        // Act

        boolean actualResult = validAreaSensor.readingWithGivenDateExists(validDate1);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfReadingWithGivenDateExistsWorksWhenItDoesNot() {
        // Arrange

        Reading reading = new Reading(21D, validDate2, "C", "sensorID");
        validAreaSensor.addReading(reading);

        // Act

        boolean actualResult = validAreaSensor.readingWithGivenDateExists(validDate1);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEmptyConstructorWorks() {
        // Arrange
        AreaSensor areaSensorABC = new AreaSensor();
        areaSensorABC.setId("ABC");
        String expectedResult = "ABC";
        // Act
        String actualResult = areaSensorABC.getId();
        // Assert
        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfSetSensorList() {
//        // Arrange
//        AreaSensorList listA = new AreaSensorList();
//        // Act
//        validAreaSensor.setAreaSensorList(listA);
//        AreaSensorList actualResult = validAreaSensor.getAreaSensorList();
//        // Assert
//        assertEquals(listA, actualResult);
//    }

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

        AreaSensor areaSensor = new AreaSensor("Sensor", "Sensor", "Temperature", new Local(12, 12, 12), startDate);

        // Act

        Object actualResult = areaSensor.getDateStartedFunctioning();

        // Assert

        assertEquals(startDate, actualResult);
    }


    @Test
    void seeIfConstructorSetsTypeArea() {
        // Arrange

        String expectedResult = "Temperature";

        // Act

        String actualResult = validAreaSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsTypeArea() {
        // Arrange

        String expectedResult = "Temperature";
        validAreaSensor = new AreaSensor("RF12345", "SensOne", "Temperature",
                new Local(31, 15, 3), new Date());

        // Act

        String actualResult = validAreaSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsLocal() {
        // Arrange

        validAreaSensor = new AreaSensor("RF12345", "SensOne", "Temperature",
                new Local(31, 15, 3), new Date());
        Local expectedResult = new Local(31, 15, 3);

        //Act

        Local actualResult = validAreaSensor.getLocal();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetNameWorks() {
        // Arrange

        String expectedResult = "XXB6";

        // Act

        validAreaSensor.setName("XXB6");
        String actualResult = validAreaSensor.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAreaReadings() {
        //Arrange

        Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();
        Reading firstValidReading = new Reading(31, date, "C", "Test");
        //Act

        validAreaSensor.addReading(firstValidReading);

        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(firstValidReading);

        //Assert

        assertEquals(expectedResult, validAreaSensor.getReadings());
    }

    @Test
    void seeIfReadingExists() {
        //Arrange

        Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();
        Reading firstValidReading = new Reading(31, date, "C", "SensOne");

        //Act
        validAreaSensor.addReading(firstValidReading);

        //Assert

        assertTrue(validAreaSensor.readingWithGivenDateExists(date));
    }

    @Test
    void seeIfReadingExistsSameDate() {
        //Arrange

        Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();
        Reading firstValidReading = new Reading(31, date, "C", "SensOne");

        //Act
        validAreaSensor.addReading(firstValidReading);

        //Assert

        assertTrue(validAreaSensor.readingWithGivenDateExists(new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime()));
    }

    @Test
    void seeIfReadingDoNotExists() {
        //Arrange

        Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();

        //Assert

        assertFalse(validAreaSensor.readingWithGivenDateExists(date));
    }

    @Test
    void seeIfNullSensorNameThrowsStringMessage() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validAreaSensor.setName(null));

        // Assert

        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfEmptySensorNameThrowsException() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validAreaSensor.setName(""));

        // Assert

        assertEquals("Please Insert Valid Name", exception.getMessage());
    }


    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Arrange

        Local testLocal = new Local(21, 23, 1);

        // Act

        boolean actualResult = validAreaSensor.equals(testLocal); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentSensor() {
        // Arrange

        AreaSensor s2 = new AreaSensor("RF12345", "Temperature Sensor XX56", "Temperature",
                new Local(21, 1, 12), new Date());

        // Act

        boolean actualResult = validAreaSensor.equals(s2);


        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksTrueSameSensor() {
        // Arrange

        AreaSensor testAreaSensor = new AreaSensor("SensOne", "SensOne", "Temperature", new Local(12, 12, 12), new Date());

        // Act

        boolean actualResult = validAreaSensor.equals(testAreaSensor);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksTrueSameObject() {
        // Act

        boolean actualResult = validAreaSensor.equals(validAreaSensor);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validAreaSensor.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintSensorWorksNoLocal() {
        // Arrange

        String expectedResult = "SensOne, Temperature, 10.0º lat, 10.0º long \n";

        // Act

        String result = validAreaSensor.buildString();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintSensorWorksWithLocal() {
        // Arrange

        validAreaSensor = new AreaSensor("RF12345", "SensOne", "Temperature", new Local(21,
                31, 15), new Date());
        String expectedResult = "SensOne, Temperature, 21.0º lat, 31.0º long \n";

        // Act

        String result = validAreaSensor.buildString();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintDeactivated() {
        // Arrange

        String expectedResult = "Active";
        // Act

        String result = validAreaSensor.printActive();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintActive() {
        // Arrange

        String expectedResult = "Deactivated";
        validAreaSensor.deactivateSensor();

        // Act

        String result = validAreaSensor.printActive();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfActive() {

        // Act
        validAreaSensor.deactivateSensor();
        // Act
        boolean result = validAreaSensor.deactivateSensor();

        // Assert

        Assertions.assertFalse(result);
    }

    @Test
    void seeIfDeactivates() {

        // Act
        boolean result = validAreaSensor.deactivateSensor();

        // Assert

        Assertions.assertTrue(result);
    }


    @Test
    void seeIfSecondConstructorSetsTypeSensorCorrectly() {
        // Arrange

        validAreaSensor = new AreaSensor("SensOne", "SensOne", "Temperature", new Local(12, 12, 12), new Date());
        String expectedResult = "Temperature";

        // Act

        String actualResult = validAreaSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfSetGetIdWorks() {
        // Arrange

        String expectedResult = "XXB6";

        // Act

        validAreaSensor.setId("XXB6");
        String actualResult = validAreaSensor.getId();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDates() {
        // Arrange
        Date validDate3 = new Date(); // 31/09/2018 23:59:59
        Date validDate4 = new Date(); // 07/10/2018 00:00:00
        Date validDate5 = new Date(); // 08/10/2018 23:26:21
        Date validDate6 = new Date(); // 31/09/2018 23:59:59
        Date validDate7 = new Date(); // 07/10/2018 00:00:00
        Date validDate8 = new Date(); // 08/10/2018 23:26:21
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate3 = validSdf.parse("31/09/2018 23:59:59");
            validDate4 = validSdf.parse("07/10/2018 20:00:00");
            validDate5 = validSdf.parse("08/10/2018 23:26:21");
            validDate6 = validSdf.parse("31/09/2018 10:59:59");
            validDate7 = validSdf.parse("07/10/2018 00:00:00");
            validDate8 = validSdf.parse("08/10/2018 13:26:21");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading r0 = new Reading(20, validDate3, "C", "SensOne");
        Reading r1 = new Reading(25, validDate4, "C", "SensOne");
        Reading r2 = new Reading(30, validDate5, "C", "SensOne");
        Reading r3 = new Reading(10, validDate6, "C", "SensOne");
        Reading r4 = new Reading(10, validDate7, "C", "SensOne");
        Reading r5 = new Reading(10, validDate8, "C", "SensOne");
        validAreaSensor.addReading(r0);
        validAreaSensor.addReading(r1);
        validAreaSensor.addReading(r2);
        validAreaSensor.addReading(r3);
        validAreaSensor.addReading(r4);
        validAreaSensor.addReading(r5);
        Date expectedResult = validDate5;

        // Act

        Date actualResult = validAreaSensor.getDateHighestAmplitudeBetweenDates(validDate6, validDate5);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDatesWhenTwoDatesHaveSameAmplitude() {
        // Arrange
        Date validDate1 = new Date();
        Date validDate2 = new Date();
        Date validDate3 = new Date();
        Date validDate4 = new Date();
        Date validDate5 = new Date();
        Date validDate6 = new Date();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("31/09/2018 10:59:59");
            validDate2 = validSdf.parse("31/09/2018 23:59:59");
            validDate3 = validSdf.parse("07/10/2018 20:00:00");
            validDate4 = validSdf.parse("07/10/2018 00:00:00");
            validDate5 = validSdf.parse("08/10/2018 13:26:21");
            validDate6 = validSdf.parse("08/10/2018 23:26:21");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading r0 = new Reading(20, validDate2, "C", "SensOne");
        Reading r1 = new Reading(25, validDate3, "C", "SensOne");
        Reading r2 = new Reading(30, validDate6, "C", "SensOne");
        Reading r3 = new Reading(10, validDate1, "C", "SensOne");
        Reading r4 = new Reading(30, validDate4, "C", "SensOne");
        Reading r5 = new Reading(10, validDate5, "C", "SensOne");
        validAreaSensor.addReading(r0);
        validAreaSensor.addReading(r1);
        validAreaSensor.addReading(r2);
        validAreaSensor.addReading(r3);
        validAreaSensor.addReading(r4);
        validAreaSensor.addReading(r5);
        Date expectedResult = validDate6;

        // Act

        Date actualResult = validAreaSensor.getDateHighestAmplitudeBetweenDates(validDate1, validDate6);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDatesWhenTwoDatesHaveSameAmplitudeMostRecentFirst() {
        // Arrange
        Date validDate1 = new Date();
        Date validDate2 = new Date();
        Date validDate3 = new Date();
        Date validDate4 = new Date();
        Date validDate5 = new Date();
        Date validDate6 = new Date();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("31/09/2018 10:59:59");
            validDate2 = validSdf.parse("31/09/2018 23:59:59");
            validDate3 = validSdf.parse("07/10/2018 20:00:00");
            validDate4 = validSdf.parse("07/10/2018 00:00:00");
            validDate5 = validSdf.parse("08/10/2018 13:26:21");
            validDate6 = validSdf.parse("08/10/2018 23:26:21");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading r0 = new Reading(20, validDate2, "C", "SensOne");
        Reading r1 = new Reading(25, validDate3, "C", "SensOne");
        Reading r2 = new Reading(30, validDate6, "C", "SensOne");
        Reading r3 = new Reading(10, validDate1, "C", "SensOne");
        Reading r4 = new Reading(30, validDate4, "C", "SensOne");
        Reading r5 = new Reading(10, validDate5, "C", "SensOne");
        validAreaSensor.addReading(r0);
        validAreaSensor.addReading(r1);
        validAreaSensor.addReading(r5);
        validAreaSensor.addReading(r2);
        validAreaSensor.addReading(r3);
        validAreaSensor.addReading(r4);
        Date expectedResult = validDate6;

        // Act

        Date actualResult = validAreaSensor.getDateHighestAmplitudeBetweenDates(validDate1, validDate6);

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetMostRecentValueReading() {
        // Arrange

        Date validDate12 = new Date();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate12 = validSdf.parse("02/11/2015 20:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date testDate = new GregorianCalendar(2018, Calendar.NOVEMBER, 3).getTime();
        Reading earlierReading = new Reading(15, validDate12, "C", "Test");
        Reading laterReading = new Reading(30, testDate, "C", "Test");
        List<Reading> readings = new ArrayList<>();
        readings.add(earlierReading);
        readings.add(laterReading);
        double expectedResult = 30.0;

        // Act

        double result = ReadingUtils.getMostRecentValue(readings);

        // Assert

        assertEquals(expectedResult, result, 0.01);
    }


    @Test
    void getDateHighestAmplitudeBetweenDatesThrowsException() {
        final Date validDate3 = new GregorianCalendar(2018, JANUARY, 1).getTime();
        final Date validDate4 = new GregorianCalendar(2019, JANUARY, 1).getTime();
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validAreaSensor.getDateHighestAmplitudeBetweenDates(validDate3, validDate4));

        // Assert

        assertEquals("Warning: Temperature amplitude value not calculated - No readings available.", exception.getMessage());
    }


    @Test
    void seeIfGetAverageReadingsBetweenDates() {
        final Date date1 = new GregorianCalendar(2018, JANUARY, 1).getTime();
        final Date date2 = new GregorianCalendar(2019, JANUARY, 1).getTime();
        Reading reading1 = new Reading(15, date1, "C", "Test");
        Reading reading2 = new Reading(30, date2, "C", "Test");
        Reading reading3 = new Reading(16, date1, "C", "Test");
        Reading reading4 = new Reading(30, date2, "C", "Test");
        validAreaSensor.addReading(reading2);
        validAreaSensor.addReading(reading1);
        validAreaSensor.addReading(reading3);
        validAreaSensor.addReading(reading4);

        double expectedResult = 22.5;
        double result = validAreaSensor.getAverageReadingsBetweenDates(date1, date2);

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfBuildString() {

        String expectedResult = "SensOne, Temperature, 10.0º lat, 10.0º long \n";

        String result = validAreaSensor.buildString();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetReadingValueOnGivenDay() {
        // Arrange

        Date validDate12 = new Date();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate12 = validSdf.parse("02/11/2015 20:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date testDate = new GregorianCalendar(2018, Calendar.NOVEMBER, 3).getTime();
        Reading earlierReading = new Reading(15, validDate12, "C", "Test");
        Reading laterReading = new Reading(30, testDate, "C", "Test");

        validAreaSensor.addReading(earlierReading);
        validAreaSensor.addReading(laterReading);
        double expectedResult = 30.0;

        // Act

        double result = validAreaSensor.getReadingValueOnGivenDay(testDate);

        // Assert

        assertEquals(expectedResult, result, 0.01);

    }


    @Test
    void seeIfGetLastColdestDayInGivenInterval() {
        // Arrange

        Date validDate12 = new Date();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate12 = validSdf.parse("02/11/2017 20:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date testDate = new GregorianCalendar(2018, Calendar.NOVEMBER, 3).getTime();
        Reading earlierReading = new Reading(15, validDate12, "C", "SensOne");
        Reading earlierReading2 = new Reading(14, validDate12, "C", "SensOne");
        Reading laterReading = new Reading(30, testDate, "C", "SensOne");
        Reading laterReading2 = new Reading(30, testDate, "C", "SensOne");

        validAreaSensor.addReading(earlierReading);
        validAreaSensor.addReading(laterReading);
        validAreaSensor.addReading(earlierReading2);
        validAreaSensor.addReading(laterReading2);
        Date expectedResult = validDate12;

        // Act

        Date result = validAreaSensor.getLastColdestDayInGivenInterval(validDate12, testDate);

        // Assert

        assertEquals(expectedResult, result);

    }

    @Test
    void getLastColdestDayInGivenIntervalThrowsException() {
        final Date validDate3 = new GregorianCalendar(2015, JANUARY, 1).getTime();
        final Date validDate4 = new GregorianCalendar(2015, JANUARY, 1).getTime();
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validAreaSensor.getLastColdestDayInGivenInterval(validDate3, validDate4));

        // Assert

        assertEquals("No readings available in the chosen interval.", exception.getMessage());
    }

    @Test
    void getFirstHottestDayInGivenPeriodThrowsException() {
        final Date validDate3 = new GregorianCalendar(2015, JANUARY, 1).getTime();
        final Date validDate4 = new GregorianCalendar(2015, JANUARY, 1).getTime();
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validAreaSensor.getFirstHottestDayInGivenPeriod(validDate3, validDate4));

        // Assert

        assertEquals("No readings available.", exception.getMessage());
    }


    @Test
    void getFirstHottestDayInGivenPeriod() {
        // Arrange

        Date validDate12 = new Date();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate12 = validSdf.parse("02/11/2017 20:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date testDate = new GregorianCalendar(2018, Calendar.NOVEMBER, 3).getTime();
        Reading earlierReading = new Reading(15, validDate12, "C", "SensOne");
        Reading earlierReading2 = new Reading(14, validDate12, "C", "SensOne");
        Reading laterReading = new Reading(30, testDate, "C", "SensOne");
        Reading laterReading2 = new Reading(30, testDate, "C", "SensOne");

        validAreaSensor.addReading(earlierReading);
        validAreaSensor.addReading(laterReading);
        validAreaSensor.addReading(earlierReading2);
        validAreaSensor.addReading(laterReading2);

        // Act

        Date result = validAreaSensor.getFirstHottestDayInGivenPeriod(validDate12, testDate);

        // Assert

        assertEquals(testDate, result);

    }

    @Test
    void getFirstHottestDayInGivenPeriodThrowsSecondException() {

        Date validDate12 = new Date();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate12 = validSdf.parse("02/11/2017 20:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date testDate = new GregorianCalendar(2018, Calendar.NOVEMBER, 3).getTime();
        Reading earlierReading = new Reading(15, validDate12, "C", "SensOne");
        Reading earlierReading2 = new Reading(14, validDate12, "C", "SensOne");
        Reading laterReading = new Reading(30, testDate, "C", "SensOne");
        Reading laterReading2 = new Reading(30, testDate, "C", "SensOne");

        validAreaSensor.addReading(earlierReading);
        validAreaSensor.addReading(laterReading);
        validAreaSensor.addReading(earlierReading2);
        validAreaSensor.addReading(laterReading2);

        final Date validDate3 = new GregorianCalendar(2015, JANUARY, 1).getTime();
        final Date validDate4 = new GregorianCalendar(2015, JANUARY, 1).getTime();
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validAreaSensor.getFirstHottestDayInGivenPeriod(validDate3, validDate4));

        // Assert

        assertEquals("Warning: No temperature readings available in given period.", exception.getMessage());
    }


}
