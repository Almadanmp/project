package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ReadingList tests class.
 */

class ReadingListTest {

    private ReadingList validReadingList;
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018
    private Date validDate3; // 31/09/2018 23:59:59
    private Date validDate4; // 07/10/2018 00:00:00
    private Date validDate5; // 08/10/2018 23:26:21
    private Date validDate6; // 09/10/2018 08:21:22
    private Date validDate7; // 10/10/2018 18:14:03
    private Date validDate8; // 23/10/2018 12:14:23
    private Date validDate9; // 13/10/2018 12:12:12
    private Date validDate10; // 30/10/2018 23:59:59
    private Date validDate11; // 01/11/2018 00:00:00
    private Date validDate12; // 02/11/2015
    private Date validDate16; // 13/10/2018 23:59:59
    private Date validDate13;
    private Date validDate14; // 02/10/2018 23:59:00
    private Date validDate15;

    @BeforeEach
    void arrangeArtifacts() {
        validReadingList = new ReadingList();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat validSdfDay = new SimpleDateFormat("dd/MM/yyyy");

        try {
            validDate1 = validSdf.parse("21/11/2018 00:00:00");
            validDate2 = validSdf.parse("03/09/2018 00:00:00");
            validDate3 = validSdf.parse("31/09/2018 23:59:59");
            validDate4 = validSdf.parse("07/10/2018 00:00:00");
            validDate5 = validSdf.parse("08/10/2018 23:26:21");
            validDate6 = validSdf.parse("09/10/2018 08:21:22");
            validDate7 = validSdf.parse("10/10/2018 18:14:03");
            validDate8 = validSdf.parse("23/10/2018 12:14:23");
            validDate9 = validSdf.parse("13/10/2018 12:12:12");
            validDate10 = validSdf.parse("30/10/2018 23:59:59");
            validDate11 = validSdf.parse("01/11/2018 00:00:00");
            validDate12 = validSdf.parse("02/11/2015 20:00:00");
            validDate13 = validSdfDay.parse("03/10/2018");
            validDate14 = validSdf.parse("02/10/2018 23:59:00");
            validDate15 = validSdf.parse("03/10/2018 00:00:00");
            validDate16 = validSdf.parse("13/10/2018 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void seeAddReadingIfListIsEmpty() {
        // Arrange

        Reading reading1 = new Reading(17, validDate1);

        // Act

        boolean actualResult = validReadingList.addReading(reading1);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetTotalFromList() {
        // Arrange

        List<Double> list = new ArrayList<>();
        list.add(1.0);
        list.add(2.0);

        // Act

        double actualResult = validReadingList.getListSum(list);

        // Assert

        assertEquals(3.0, actualResult);
    }

    @Test
    void seeTotalFromEmptyList() {
        // Arrange

         List<Double> list = new ArrayList<>();

        // Act

        double actualResult = validReadingList.getListSum(list);

        // Assert

        assertEquals(0.0, actualResult);
    }

    @Test
    void seeIfAddsDifferentReadings() {
        // Arrange

        Reading reading1 = new Reading(17, validDate1);
        Reading reading2 = new Reading(29, validDate2);
        validReadingList.addReading(reading1);

        // Act

        boolean actualResult = validReadingList.addReading(reading2);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddsSameReading() {
        // Arrange

        Reading reading1 = new Reading(17, validDate1);
        Reading reading2 = new Reading(17, validDate1);
        validReadingList.addReading(reading1);

        // Act

        boolean actualResult = validReadingList.addReading(reading2);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetsValueFromList() {
        // Arrange

        Reading reading1 = new Reading(15, validDate1);
        Reading reading2 = new Reading(29, validDate2);
        validReadingList.addReading(reading1);
        validReadingList.addReading(reading2);
        double expectedResult = 15;

        // Act

        double actualResult = validReadingList.get(0).getValue();

        // Assert

        assertEquals(expectedResult, actualResult, 0.1);
    }

    @Test
    void seeIfGetsSingularValue() {
        // Arrange

        Reading reading1 = new Reading(15, validDate1);
        Reading reading2 = new Reading(29, validDate2);
        validReadingList.addReading(reading1);
        validReadingList.addReading(reading2);
        double expectedResult = 29;

        // Act

        double actualResult = validReadingList.get(1).getValue();

        // Assert

        assertEquals(expectedResult, actualResult, 0.1);
    }

    @Test
    void seeIfGetDatesWithReadingsBetweenTwoGivenDates() {
        // Arrange

        Reading r0 = new Reading(23, validDate3);
        Reading r1 = new Reading(23, validDate4);
        Reading r2 = new Reading(24, validDate5);
        Reading r3 = new Reading(25, validDate6);
        Reading r4 = new Reading(26, validDate7);
        Reading r5 = new Reading(23, validDate8);
        Reading r6 = new Reading(22, validDate9);
        Reading r7 = new Reading(23, validDate10);
        Reading r8 = new Reading(22, validDate11);
        validReadingList.addReading(r0);
        validReadingList.addReading(r1);
        validReadingList.addReading(r2);
        validReadingList.addReading(r3);
        validReadingList.addReading(r4);
        validReadingList.addReading(r5);
        validReadingList.addReading(r6);
        validReadingList.addReading(r7);
        validReadingList.addReading(r8);
        List<Date> expectedResult = new ArrayList<>();
        expectedResult.add(validDate4);
        expectedResult.add(validDate5);
        expectedResult.add(validDate6);
        expectedResult.add(validDate7);
        expectedResult.add(validDate9);

        // Act

        List<Date> actualResult = validReadingList.getDaysWithReadingsBetweenDates(validDate4, validDate16);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetsAverage() {
        // Arrange

        List<Double> doubleList = new ArrayList<>();
        double expectedResult = 22.40;
        doubleList.add(1D);
        doubleList.add(23D);
        doubleList.add(43.2D);

        // Act

        double actualResult = validReadingList.getAvgFromList(doubleList);

        // Assert

        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfAverageIsZeroInEmptyList() {
        // Arrange

        List<Double> doubleList = new ArrayList<>();

        // Act

        double actualResult = validReadingList.getAvgFromList(doubleList);

        // Assert

        assertEquals(0, actualResult, 0.001);
    }

    @Test
    void seeIfGetsMostRecentValueWorks() {
        // Arrange

        Date testDate = new GregorianCalendar(2018, Calendar.NOVEMBER, 3).getTime();
        Reading earlierReading = new Reading(15, validDate12);
        Reading laterReading = new Reading(30, testDate);
        validReadingList.addReading(earlierReading);
        validReadingList.addReading(laterReading);
        double expectedResult = 30.0;

        // Act

        double result = validReadingList.getMostRecentValue();

        // Assert

        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    void seeIfGetMostRecentValueWorksEmptyList() {
        // Assert

        assertThrows(IllegalArgumentException.class, validReadingList::getMostRecentValue);
    }

    @Test
    void seeIfGetMostRecentValueWorksInSameDay() {
        // Arrange

        Date testDate = new GregorianCalendar(2015, Calendar.NOVEMBER, 2, 5, 0,
                0).getTime();
        Reading earlierReading = new Reading(15, validDate12);
        Reading laterReading = new Reading(30, testDate);
        validReadingList.addReading(earlierReading);
        validReadingList.addReading(laterReading);
        double expectedResult = 15.0;

        // Act

        double result = validReadingList.getMostRecentValue();

        // Assert

        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    void seeIfGetsAverageBetweenTwoDates() {
        // Arrange

        Reading r0 = new Reading(23, validDate3);
        Reading r1 = new Reading(23, validDate4);
        Reading r2 = new Reading(24, validDate5);
        Reading r3 = new Reading(25, validDate6);
        Reading r4 = new Reading(26, validDate7);
        Reading r5 = new Reading(23, validDate8);
        Reading r6 = new Reading(22, validDate9);
        Reading r7 = new Reading(23, validDate10);
        Reading r8 = new Reading(22, validDate11);
        validReadingList.addReading(r0);
        validReadingList.addReading(r1);
        validReadingList.addReading(r2);
        validReadingList.addReading(r3);
        validReadingList.addReading(r4);
        validReadingList.addReading(r5);
        validReadingList.addReading(r6);
        validReadingList.addReading(r7);
        validReadingList.addReading(r8);
        double expectedResult = 24.0;

        // Act

        double actualResult = validReadingList.getAverageReadingsBetweenDates(validDate4, validDate16);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAverageReadingsBetweenDatesExceptionTest() {
        // Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> validReadingList.getAverageReadingsBetweenDates(validDate4, validDate16));
    }

    @Test
    void seeIfReadingListIsEmpty() {

        // Act

        boolean actualResult = validReadingList.isEmpty();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfReadingListIsNotEmpty() {
        // Arrange

        Reading testReading = new Reading(31, validDate3);
        validReadingList.addReading(testReading);

        // Act

        boolean actualResult = validReadingList.isEmpty();

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetsTotalReadings() {
        // Arrange

        Reading reading = new Reading(20, validDate15);
        Reading reading2 = new Reading(20, validDate3);
        Reading reading3 = new Reading(20, validDate7);
        Reading reading4 = new Reading(20, validDate14);
        validReadingList.addReading(reading);
        validReadingList.addReading(reading2);
        validReadingList.addReading(reading3);
        validReadingList.addReading(reading4);
        double expectedResult = 20;

        // Act

        double actualResult = validReadingList.getValueReadingsInDay(validDate13);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetsTotalReadingsInDayWorksNoReadings() {

        // Act

        Throwable exception = assertThrows(IllegalStateException.class,
                () -> validReadingList.getValueReadingsInDay(validDate13));

        // Assert

        assertEquals("Warning: Total value was not calculated - No readings were available.", exception.getMessage());
    }

    @Test
    void seeIfGetsMostRecentReading() {
        // This test is particularly complex, but it tests several failure cases. The particular failure scenarios
        // are expanded on next to each assert.

        // Arrange

        ReadingList readingList2 = new ReadingList();
        ReadingList readingList3 = new ReadingList();
        ReadingList readingList4 = new ReadingList();
        ReadingList readingList5 = new ReadingList();
        Reading secondMostRecentReading = new Reading(22, validDate14);
        Reading mostRecentReading = new Reading(25, validDate15);
        Reading oldestReading = new Reading(27, validDate3);
        validReadingList.addReading(oldestReading);
        validReadingList.addReading(secondMostRecentReading);
        validReadingList.addReading(mostRecentReading);
        readingList2.addReading(mostRecentReading);
        readingList2.addReading(oldestReading);
        readingList2.addReading(secondMostRecentReading);
        readingList3.addReading(mostRecentReading);
        readingList3.addReading(secondMostRecentReading);
        readingList3.addReading(oldestReading);
        readingList4.addReading(oldestReading);
        readingList4.addReading(secondMostRecentReading);
        readingList4.addReading(oldestReading);
        Reading error = new Reading(NaN, new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime());

        // Act

        Reading actualResult1 = validReadingList.getMostRecentReading();
        Reading actualResult2 = readingList2.getMostRecentReading();
        Reading actualResult3 = readingList3.getMostRecentReading();
        Reading actualResult4 = readingList4.getMostRecentReading();
        Reading actualResult5 = readingList5.getMostRecentReading();

        // Assert

        assertEquals(mostRecentReading, actualResult1); // Tests if method works when most recent reading is the first on the list.
        assertEquals(mostRecentReading, actualResult2); // Tests if method works when most recent reading is in the middle of the list.
        assertEquals(mostRecentReading, actualResult3); // Tests if method works when most recent reading is the last on the list.
        assertEquals(secondMostRecentReading, actualResult4); // Tests if method works when most recent reading happens more than once.
        assertEquals(error, actualResult5); // Tests if method works when there are no readings.
    }

    @Test
    void seeAllEqualsConditions() {
        // Arrange

        ReadingList readingList2 = new ReadingList();
        ReadingList readingList3 = new ReadingList();
        Reading reading1 = new Reading(22, validDate14);
        Reading reading2 = new Reading(25, validDate15);
        validReadingList.addReading(reading1);
        validReadingList.addReading(reading2);
        readingList2.addReading(reading1);
        readingList2.addReading(reading2);
        readingList3.addReading(reading2);
        readingList3.addReading(reading1);

        // Act

        boolean actualResult1 = validReadingList.equals(readingList2);
        boolean actualResult2 = validReadingList.equals(validReadingList); //Necessary for Sonarqube testing purposes.
        boolean actualResult3 = validReadingList.equals(readingList3);
        boolean actualResult4 = validReadingList.equals(2D); //Necessary for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void seeIfGetsFirstSecondOfDay() {
        // Arrange

        Date expectedResult = new GregorianCalendar(2018, Calendar.OCTOBER, 2).getTime();

        // Assert

        assertEquals(expectedResult, validReadingList.getFirstSecondOfDay(validDate14));
    }

    @Test
    void seeIfReadingDateWithinTwoDates() {
        //Arrange

        Date date1 = new Date();
        Date date2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            date1 = sd.parse("01/10/2018 23:59");
            date2 = sd.parse("01/10/2019 23:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading reading1 = new Reading(22, validDate14);
        validReadingList.addReading(reading1);

            //Assert

        assertTrue(validReadingList.isReadingDateBetweenTwoDates(reading1.getDate(), date1, date2));
    }

    @Test
    void seeIfReadingDateWithinTwoDatesFalse() {
        // Arrange

        Date date1 = new Date();
        Date date2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            date1 = sd.parse("01/10/2016 23:59");
            date2 = sd.parse("01/10/2017 23:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading reading1 = new Reading(22, validDate14);
        validReadingList.addReading(reading1);

        // Assert

        assertFalse(validReadingList.isReadingDateBetweenTwoDates(reading1.getDate(), date1, date2));
    }

    @Test
    void ensureThatWeGetReadingsBetweenDates() {
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(3, validDate4);
        Reading reading2 = new Reading(6, validDate5);
        Reading reading3 = new Reading(7, validDate6);
        Reading reading4 = new Reading(3, validDate7);
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        readingList.addReading(reading3);
        readingList.addReading(reading4);
        ReadingList expectedReadingList = new ReadingList();
        expectedReadingList.addReading(reading2);
        expectedReadingList.addReading(reading3);
        ReadingList expectedResult = expectedReadingList;
        ReadingList actualResult = readingList.getReadingsBetweenDates(validDate5,validDate6);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void ensureThatWeGetLastDayWithLowestValue(){
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(2, validDate4);
        Reading reading2 = new Reading(6, validDate5);
        Reading reading3 = new Reading(7, validDate6);
        Reading reading4 = new Reading(3, validDate7);
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        readingList.addReading(reading3);
        readingList.addReading(reading4);
        Date expectedResult = new GregorianCalendar(2018,9,7).getTime();
        Date actualResult = readingList.getLastDayWithLowestValue();
        assertEquals(expectedResult,actualResult);
    }
}