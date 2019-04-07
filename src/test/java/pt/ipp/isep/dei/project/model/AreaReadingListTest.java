package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.sensor.AreaReadingList;
import pt.ipp.isep.dei.project.model.sensor.Reading;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

/**
 * AreaReadingList tests class.
 */

class AreaReadingListTest {

    private AreaReadingList validAreaReadingList;
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
    private Date validDate18; // same day and month as 9 ans 16 but different year
    private Date validDate19; // same day and month as 9 ans 16 but different year, different hour

    @BeforeEach
    void arrangeArtifacts() {
        validAreaReadingList = new AreaReadingList();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat validSdfDay = new SimpleDateFormat("dd/MM/yyyy");
        try {
            validDate12 = validSdf.parse("02/11/2015 20:00:00");
            validDate2 = validSdf.parse("03/09/2018 00:00:00");
            validDate3 = validSdf.parse("31/09/2018 23:59:59");
            validDate13 = validSdfDay.parse("03/10/2018");
            validDate14 = validSdf.parse("02/10/2018 23:59:00");
            validDate15 = validSdf.parse("03/10/2018 00:00:00");
            validDate4 = validSdf.parse("07/10/2018 00:00:00");
            validDate5 = validSdf.parse("08/10/2018 23:26:21");
            validDate6 = validSdf.parse("09/10/2018 08:21:22");
            validDate7 = validSdf.parse("10/10/2018 18:14:03");
            validDate9 = validSdf.parse("13/10/2018 12:12:12");
            validDate16 = validSdf.parse("13/10/2018 23:59:59");
            validDate8 = validSdf.parse("23/10/2018 12:14:23");
            validDate10 = validSdf.parse("30/10/2018 23:59:59");
            validDate1 = validSdf.parse("21/11/2018 00:00:00");
            validDate11 = validSdf.parse("01/11/2018 00:00:00");
            validDate18 = validSdf.parse("13/10/2019 12:12:12");
            validDate19 = validSdf.parse("13/10/2019 23:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void seeAddReadingIfListIsEmpty() {
        // Arrange

        Reading reading1 = new Reading(17, validDate1, "C");

        // Act

        boolean actualResult = validAreaReadingList.addReading(reading1);

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

        double actualResult = validAreaReadingList.getListSum(list);

        // Assert

        assertEquals(3.0, actualResult);
    }

    @Test
    void seeTotalFromEmptyList() {
        // Arrange

        List<Double> list = new ArrayList<>();

        // Act

        double actualResult = validAreaReadingList.getListSum(list);

        // Assert

        assertEquals(0.0, actualResult);
    }

    @Test
    void seeIfAddsDifferentReadings() {
        // Arrange

        Reading reading1 = new Reading(17, validDate1, "C");
        Reading reading2 = new Reading(29, validDate2, "C");
        validAreaReadingList.addReading(reading1);

        // Act

        boolean actualResult = validAreaReadingList.addReading(reading2);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddsSameReading() {
        // Arrange

        Reading reading1 = new Reading(17, validDate1, "C");
        Reading reading2 = new Reading(17, validDate1, "C");
        validAreaReadingList.addReading(reading1);

        // Act

        boolean actualResult = validAreaReadingList.addReading(reading2);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetsValueFromList() {
        // Arrange

        Reading reading1 = new Reading(15, validDate1, "C");
        Reading reading2 = new Reading(29, validDate2, "C");
        validAreaReadingList.addReading(reading1);
        validAreaReadingList.addReading(reading2);
        double expectedResult = 15;

        // Act

        double actualResult = validAreaReadingList.get(0).getValue();

        // Assert

        assertEquals(expectedResult, actualResult, 0.1);
    }

    @Test
    void seeIfGetsSingularValue() {
        // Arrange

        Reading reading1 = new Reading(15, validDate1, "C");
        Reading reading2 = new Reading(29, validDate2, "C");
        validAreaReadingList.addReading(reading1);
        validAreaReadingList.addReading(reading2);
        double expectedResult = 29;

        // Act

        double actualResult = validAreaReadingList.get(1).getValue();

        // Assert

        assertEquals(expectedResult, actualResult, 0.1);
    }

    @Test
    void seeIfGetDatesWithReadingsBetweenTwoGivenDates() {
        // Arrange

        Reading r0 = new Reading(23, validDate3, "C"); //  01 Oct 23:59:59 (2018)
        Reading r1 = new Reading(23, validDate4, "C"); //  07 Oct 00:00:00 "
        Reading r2 = new Reading(24, validDate5, "C"); //  08 Oct 23:26:21 "
        Reading r3 = new Reading(25, validDate6, "C"); //  09 Oct 08:21:22 "
        Reading r4 = new Reading(26, validDate7, "C"); //  10 Oct 18:14:03 "
        Reading r5 = new Reading(23, validDate8, "C"); //  23 Oct 12:14:23 "
        Reading r6 = new Reading(22, validDate9, "C"); //  13 Oct 12:12:12 "
        Reading r7 = new Reading(23, validDate10, "C"); // 30 Oct 23:59:59 "
        Reading r8 = new Reading(22, validDate11, "C"); // 01 Nov 01:00:00 "
        Reading r9 = new Reading(23, validDate18, "C"); // 13 Oct 12:12:12 (2019)
        Reading r10 = new Reading(22, validDate19, "C"); // 13 Oct 23:59:59 "
        validAreaReadingList.addReading(r0);
        validAreaReadingList.addReading(r1);
        validAreaReadingList.addReading(r2);
        validAreaReadingList.addReading(r3);
        validAreaReadingList.addReading(r4);
        validAreaReadingList.addReading(r5);
        validAreaReadingList.addReading(r6);
        validAreaReadingList.addReading(r7);
        validAreaReadingList.addReading(r8);
        validAreaReadingList.addReading(r9);
        validAreaReadingList.addReading(r10);
        List<Date> expectedResult = new ArrayList<>();
        expectedResult.add(validDate4);
        expectedResult.add(validDate5);
        expectedResult.add(validDate6);
        expectedResult.add(validDate7);
        expectedResult.add(validDate8);
        expectedResult.add(validDate9);
        expectedResult.add(validDate10);
        expectedResult.add(validDate11);
        expectedResult.add(validDate18);

        // Act

        List<Date> actualResult = validAreaReadingList.getDaysWithReadingsBetweenDates(validDate4, validDate19);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDatesWithReadingsBetweenTwoGivenDatesIfMostRecentReadingsAreIntroducedFirst() {
        // Arrange

        Reading r0 = new Reading(23, validDate3, "C"); //  01 Oct 23:59:59 (2018)
        Reading r1 = new Reading(23, validDate4, "C"); //  07 Oct 00:00:00 "
        Reading r2 = new Reading(24, validDate5, "C"); //  08 Oct 23:26:21 "
        Reading r3 = new Reading(25, validDate6, "C"); //  09 Oct 08:21:22 "
        Reading r4 = new Reading(26, validDate7, "C"); //  10 Oct 18:14:03 "
        Reading r5 = new Reading(23, validDate8, "C"); //  23 Oct 12:14:23 "
        Reading r6 = new Reading(22, validDate9, "C"); //  13 Oct 12:12:12 "
        Reading r7 = new Reading(23, validDate10, "C"); // 30 Oct 23:59:59 "
        Reading r8 = new Reading(22, validDate11, "C"); // 01 Nov 01:00:00 "
        Reading r9 = new Reading(23, validDate18, "C"); // 13 Oct 12:12:12 (2019)
        Reading r10 = new Reading(22, validDate19, "C"); // 13 Oct 23:59:59 "
        validAreaReadingList.addReading(r10);
        validAreaReadingList.addReading(r9);
        validAreaReadingList.addReading(r8);
        validAreaReadingList.addReading(r7);
        validAreaReadingList.addReading(r6);
        validAreaReadingList.addReading(r5);
        validAreaReadingList.addReading(r4);
        validAreaReadingList.addReading(r3);
        validAreaReadingList.addReading(r2);
        validAreaReadingList.addReading(r1);
        validAreaReadingList.addReading(r0);
        List<Date> expectedResult = new ArrayList<>();
        expectedResult.add(validDate19);
        expectedResult.add(validDate11);
        expectedResult.add(validDate10);
        expectedResult.add(validDate9);
        expectedResult.add(validDate8);
        expectedResult.add(validDate7);
        expectedResult.add(validDate6);
        expectedResult.add(validDate5);
        expectedResult.add(validDate4);

        // Act

        List<Date> actualResult = validAreaReadingList.getDaysWithReadingsBetweenDates(validDate4, validDate19);

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

        double actualResult = validAreaReadingList.getAvgFromList(doubleList);

        // Assert

        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfAverageIsZeroInEmptyList() {
        // Arrange

        List<Double> doubleList = new ArrayList<>();

        // Act

        double actualResult = validAreaReadingList.getAvgFromList(doubleList);

        // Assert

        assertEquals(0, actualResult, 0.001);
    }

    @Test
    void seeIfGetsMostRecentValueWorks() {
        // Arrange

        Date testDate = new GregorianCalendar(2018, Calendar.NOVEMBER, 3).getTime();
        Reading earlierReading = new Reading(15, validDate12, "C");
        Reading laterReading = new Reading(30, testDate, "C");
        validAreaReadingList.addReading(earlierReading);
        validAreaReadingList.addReading(laterReading);
        double expectedResult = 30.0;

        // Act

        double result = validAreaReadingList.getMostRecentValue();

        // Assert

        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    void seeIfGetMostRecentValueWorksEmptyList() {
        // Assert

        assertThrows(IllegalArgumentException.class, validAreaReadingList::getMostRecentValue);
    }

    @Test
    void seeIfGetMostRecentValueWorksInSameDay() {
        // Arrange

        Date testDate = new GregorianCalendar(2015, Calendar.NOVEMBER, 2, 5, 0,
                0).getTime();
        Reading earlierReading = new Reading(15, validDate12, "C");
        Reading laterReading = new Reading(30, testDate, "C");
        validAreaReadingList.addReading(earlierReading);
        validAreaReadingList.addReading(laterReading);
        double expectedResult = 15.0;

        // Act

        double result = validAreaReadingList.getMostRecentValue();

        // Assert

        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    void seeIfGetsAverageBetweenTwoDates() {
        // Arrange

        Reading r0 = new Reading(23, validDate3, "C");
        Reading r1 = new Reading(23, validDate2, "C");
        Reading r2 = new Reading(24, validDate5, "C");
        Reading r3 = new Reading(25, validDate6, "C");
        Reading r4 = new Reading(26, validDate7, "C");
        Reading r5 = new Reading(23, validDate8, "C");
        Reading r6 = new Reading(22, validDate9, "C");
        Reading r7 = new Reading(23, validDate10, "C");
        Reading r8 = new Reading(22, validDate11, "C");
        validAreaReadingList.addReading(r0);
        validAreaReadingList.addReading(r1);
        validAreaReadingList.addReading(r2);
        validAreaReadingList.addReading(r3);
        validAreaReadingList.addReading(r4);
        validAreaReadingList.addReading(r5);
        validAreaReadingList.addReading(r6);
        validAreaReadingList.addReading(r7);
        validAreaReadingList.addReading(r8);
        double expectedResult = 24.25;

        // Act

        double actualResult = validAreaReadingList.getAverageReadingsBetweenDates(validDate4, validDate16);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAverageReadingsBetweenDatesExceptionTest() {
        // Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> validAreaReadingList.getAverageReadingsBetweenDates(validDate4, validDate16));
    }

    @Test
    void seeIfReadingListIsEmpty() {

        // Act

        boolean actualResult = validAreaReadingList.isEmpty();

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfReadingListIsNotEmpty() {
        // Arrange

        Reading testReading = new Reading(31, validDate3, "C");
        validAreaReadingList.addReading(testReading);

        // Act

        boolean actualResult = validAreaReadingList.isEmpty();

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetsTotalReadings() {
        // Arrange

        Reading reading = new Reading(20, validDate15, "C");
        Reading reading2 = new Reading(20, validDate3, "C");
        Reading reading3 = new Reading(20, validDate7, "C");
        Reading reading4 = new Reading(20, validDate14, "C");
        validAreaReadingList.addReading(reading);
        validAreaReadingList.addReading(reading2);
        validAreaReadingList.addReading(reading3);
        validAreaReadingList.addReading(reading4);
        double expectedResult = 20;

        // Act

        double actualResult = validAreaReadingList.getValueReadingsInDay(validDate13);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetsTotalReadingsInDayWorksNoReadings() {

        // Act

        Throwable exception = assertThrows(IllegalStateException.class,
                () -> validAreaReadingList.getValueReadingsInDay(validDate13));

        // Assert

        assertEquals("Warning: Total value was not calculated - No readings were available.", exception.getMessage());
    }

    @Test
    void seeIfGetsMostRecentReading() {
        // This test is particularly complex, but it tests several failure cases. The particular failure scenarios
        // are expanded on next to each assert.

        // Arrange

        AreaReadingList areaReadingList2 = new AreaReadingList();
        AreaReadingList areaReadingList3 = new AreaReadingList();
        AreaReadingList areaReadingList4 = new AreaReadingList();
        AreaReadingList areaReadingList5 = new AreaReadingList();
        Reading secondMostRecentReading = new Reading(22, validDate14, "C");
        Reading mostRecentReading = new Reading(25, validDate15, "C");
        Reading oldestReading = new Reading(27, validDate3, "C");
        validAreaReadingList.addReading(oldestReading);
        validAreaReadingList.addReading(secondMostRecentReading);
        validAreaReadingList.addReading(mostRecentReading);
        areaReadingList2.addReading(mostRecentReading);
        areaReadingList2.addReading(oldestReading);
        areaReadingList2.addReading(secondMostRecentReading);
        areaReadingList3.addReading(mostRecentReading);
        areaReadingList3.addReading(secondMostRecentReading);
        areaReadingList3.addReading(oldestReading);
        areaReadingList4.addReading(oldestReading);
        areaReadingList4.addReading(secondMostRecentReading);
        areaReadingList4.addReading(oldestReading);
        Reading error = new Reading(NaN, new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime(), "C");

        // Act

        Reading actualResult1 = validAreaReadingList.getMostRecentReading();
        Reading actualResult2 = areaReadingList2.getMostRecentReading();
        Reading actualResult3 = areaReadingList3.getMostRecentReading();
        Reading actualResult4 = areaReadingList4.getMostRecentReading();
        Reading actualResult5 = areaReadingList5.getMostRecentReading();

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

        AreaReadingList areaReadingList2 = new AreaReadingList();
        AreaReadingList areaReadingList3 = new AreaReadingList();
        Reading reading1 = new Reading(22, validDate14, "C");
        Reading reading2 = new Reading(25, validDate15, "C");
        validAreaReadingList.addReading(reading1);
        validAreaReadingList.addReading(reading2);
        areaReadingList2.addReading(reading1);
        areaReadingList2.addReading(reading2);
        areaReadingList3.addReading(reading2);
        areaReadingList3.addReading(reading1);

        // Act

        boolean actualResult1 = validAreaReadingList.equals(areaReadingList2);
        boolean actualResult2 = validAreaReadingList.equals(validAreaReadingList); //Necessary for Sonarqube testing purposes.
        boolean actualResult3 = validAreaReadingList.equals(areaReadingList3);
        boolean actualResult4 = validAreaReadingList.equals(2D); //Necessary for Sonarqube testing purposes.

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

        assertEquals(expectedResult, validAreaReadingList.getFirstSecondOfDay(validDate14));
    }


    @Test
    void seeIfGetsLastSecondOfDay() {
        // Arrange

        Date expectedResult = new GregorianCalendar(2018, Calendar.OCTOBER, 2, 23, 59, 59).getTime();

        // Assert

        assertEquals(expectedResult, validAreaReadingList.getLastSecondOfDay(validDate14));
    }

    @Test
    void seeIfReadingDateWithinTwoDates() {
        //Arrange

        Reading testReading = new Reading(22, validDate14, "C");
        validAreaReadingList.addReading(testReading);

        // Act

        boolean actualResult = validAreaReadingList.isReadingDateBetweenTwoDates(testReading.getDate(), validDate12,
                validDate16);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfReadingDateWithinTwoDatesFalse() {
        //Arrange

        Reading testReading = new Reading(22, validDate14, "C");
        validAreaReadingList.addReading(testReading);

        // Act

        boolean actualResult = validAreaReadingList.isReadingDateBetweenTwoDates(testReading.getDate(), validDate13,
                validDate15);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void getByIndexEmptyReadingList() {
        //Arrange

        AreaReadingList emptyList = new AreaReadingList();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));

        //Assert

        assertEquals("The reading list is empty.", exception.getMessage());
    }

    @Test
    void getElementsAsArray() {
        //Arrange

        Reading[] expectedResult1 = new Reading[0];
        Reading[] expectedResult2 = new Reading[1];
        Reading[] expectedResult3 = new Reading[2];

        AreaReadingList emptyList = new AreaReadingList();
        validAreaReadingList.addReading(new Reading(20, validDate1, "C"));
        AreaReadingList validAreaReadingList2 = new AreaReadingList();
        validAreaReadingList2.addReading(new Reading(20, validDate1, "C"));
        validAreaReadingList2.addReading(new Reading(25, validDate2, "C"));

        expectedResult2[0] = new Reading(20, validDate1, "C");
        expectedResult3[0] = new Reading(20, validDate1, "C");
        expectedResult3[1] = new Reading(25, validDate2, "C");

        //Act

        Reading[] actualResult1 = emptyList.getElementsAsArray();
        Reading[] actualResult2 = validAreaReadingList.getElementsAsArray();
        Reading[] actualResult3 = validAreaReadingList2.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void appendListNoDuplicates() {
        //Arrange

        AreaReadingList expectedResult1 = new AreaReadingList();
        AreaReadingList expectedResult2 = new AreaReadingList();

        Reading reading1 = new Reading(20, validDate1, "C");
        Reading reading2 = new Reading(22, validDate2, "C");

        AreaReadingList emptyList = new AreaReadingList();
        validAreaReadingList.addReading(reading1);
        AreaReadingList validAreaReadingList2 = new AreaReadingList();
        validAreaReadingList2.addReading(reading1);
        validAreaReadingList2.addReading(reading2);

        expectedResult1.addReading(reading1);
        expectedResult2.addReading(reading1);
        expectedResult2.addReading(reading2);

        //Act

        AreaReadingList actualResult1 = validAreaReadingList.appendListNoDuplicates(emptyList);

        //Assert

        assertEquals(expectedResult1, actualResult1);

        //Act

        AreaReadingList actualResult2 = emptyList.appendListNoDuplicates(validAreaReadingList);
        AreaReadingList actualResult3 = validAreaReadingList.appendListNoDuplicates(validAreaReadingList2);

        //Assert

        assertEquals(expectedResult1, actualResult2);
        assertEquals(expectedResult2, actualResult3);
    }

    @Test
    void hashCodeDummyTest() {
        //Arrange

        Reading reading1 = new Reading(22, validDate14, "C");
        validAreaReadingList.addReading(reading1);

        // Act

        int actualResult = validAreaReadingList.hashCode();

        // Assert

        assertEquals(actualResult, 1);
    }

    @Test
    void seeIfWeGetMaxValueOfTheDayWorks() {
        //Arrange
        AreaReadingList areaReadingList = new AreaReadingList();
        AreaReadingList areaReadingList2 = new AreaReadingList();
        AreaReadingList areaReadingList3 = new AreaReadingList();
        Reading reading1 = new Reading(22, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 10, 0).getTime(), "C");
        Reading reading2 = new Reading(22, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 9, 0).getTime(), "C");
        Reading reading3 = new Reading(25, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 11, 0).getTime(), "C");
        Reading reading4 = new Reading(19, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 21, 30).getTime(), "C");
        areaReadingList.addReading(reading1);
        areaReadingList.addReading(reading2);
        areaReadingList2.addReading(reading2);
        areaReadingList2.addReading(reading3);
        areaReadingList3.addReading(reading3);
        areaReadingList3.addReading(reading4);
        //Act
        Reading actualResult = areaReadingList.getMaxValueOfTheDay(new GregorianCalendar(2018, Calendar.OCTOBER, 8).getTime());
        Reading actualResult2 = areaReadingList2.getMaxValueOfTheDay(new GregorianCalendar(2018, Calendar.OCTOBER, 8).getTime());
        Reading actualResult3 = areaReadingList3.getMaxValueOfTheDay(new GregorianCalendar(2018, Calendar.OCTOBER, 8).getTime());

        //Assert
        assertEquals(reading1, actualResult);
        assertEquals(reading3, actualResult2);
        assertEquals(reading3, actualResult3);
    }

    @Test
    void seeIfWeGetReadingListWithSpecificValueWorks() {
        //Arrange
        validAreaReadingList = new AreaReadingList();
        AreaReadingList expectedResult = new AreaReadingList();
        Reading r1 = new Reading(22, validDate2, "C");
        Reading r2 = new Reading(24, validDate14, "C");
        Reading r3 = new Reading(22, validDate2, "C");
        Reading r4 = new Reading(21, validDate15, "C");
        Reading r5 = new Reading(22, validDate12, "C");
        Reading r6 = new Reading(29, validDate2, "C");
        validAreaReadingList.addReading(r1);
        validAreaReadingList.addReading(r2);
        validAreaReadingList.addReading(r3);
        validAreaReadingList.addReading(r4);
        validAreaReadingList.addReading(r5);
        validAreaReadingList.addReading(r6);
        expectedResult.addReading(r1);
        expectedResult.addReading(r3);
        expectedResult.addReading(r5);
        //Act
        AreaReadingList actualResult = validAreaReadingList.getReadingListOfReadingsWithSpecificValue(22.0);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfWeGetMinValueInReadingListWorks() {
        //Arrange
        AreaReadingList areaReadingList = new AreaReadingList();
        AreaReadingList areaReadingList2 = new AreaReadingList();
        AreaReadingList areaReadingList3 = new AreaReadingList();
        Reading reading1 = new Reading(20, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 2, 30).getTime(), "C");
        Reading reading2 = new Reading(20, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 11, 30).getTime(), "C");
        Reading reading3 = new Reading(19, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 21, 30).getTime(), "C");
        Reading reading4 = new Reading(21, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 3, 30).getTime(), "C");
        areaReadingList.addReading(reading1);
        areaReadingList.addReading(reading4);
        areaReadingList.addReading(reading2);
        areaReadingList2.addReading(reading2);
        areaReadingList2.addReading(reading3);
        areaReadingList3.addReading(reading3);
        areaReadingList3.addReading(reading4);
        double expectedResult1 = 20;
        double expectedResult2 = 19;
        double expectedResult3 = 19;
        //Act
        double actualResult = areaReadingList.getMinValueInReadingList();
        double actualResult2 = areaReadingList2.getMinValueInReadingList();
        double actualResult3 = areaReadingList3.getMinValueInReadingList();
        //Assert
        assertNotSame(expectedResult1, actualResult);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfWeGetReadingWithSpecificDateWorks() {
        //Arrange
        validAreaReadingList = new AreaReadingList();
        Reading r1 = new Reading(22, validDate5, "C");
        Reading r2 = new Reading(24, validDate14, "C");
        Reading r3 = new Reading(22, validDate2, "C");
        Reading r4 = new Reading(21, validDate15, "C");
        Reading r5 = new Reading(22, validDate12, "C");
        Reading r6 = new Reading(29, validDate2, "C");
        validAreaReadingList.addReading(r1);
        validAreaReadingList.addReading(r2);
        validAreaReadingList.addReading(r3);
        validAreaReadingList.addReading(r4);
        validAreaReadingList.addReading(r5);
        validAreaReadingList.addReading(r6);
        //Act
        Reading actualResult2 = validAreaReadingList.getAReadingWithSpecificDay(validDate7);
        Reading actualResult = validAreaReadingList.getAReadingWithSpecificDay(validDate2);
        //Assert
        assertNull(actualResult2);
        assertEquals(r3, actualResult);
    }

    @Test
    void seeIfWeGetListOfMaxValuesForEachDayWorks() {
        //Arrange
        validAreaReadingList = new AreaReadingList();
        Reading r1 = new Reading(22, validDate5, "C");
        Reading r2 = new Reading(24, validDate14, "C");
        Reading r3 = new Reading(22, validDate2, "C");
        Reading r4 = new Reading(21, validDate15, "C");
        Reading r5 = new Reading(22, validDate12, "C");
        Reading r6 = new Reading(23, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 21, 0).getTime(), "C");
        Reading r7 = new Reading(26, new GregorianCalendar(2018, Calendar.OCTOBER, 2, 10, 0).getTime(), "C");
        Reading r8 = new Reading(20, new GregorianCalendar(2018, Calendar.SEPTEMBER, 3, 23, 30).getTime(), "C");
        Reading r10 = new Reading(20, validDate12, "C");
        validAreaReadingList.addReading(r1);
        validAreaReadingList.addReading(r2);
        validAreaReadingList.addReading(r3);
        validAreaReadingList.addReading(r4);
        validAreaReadingList.addReading(r5);
        validAreaReadingList.addReading(r6);
        validAreaReadingList.addReading(r7);
        validAreaReadingList.addReading(r8);
        validAreaReadingList.addReading(r10);
        AreaReadingList expectedResult = new AreaReadingList();
        expectedResult.addReading(r6);
        expectedResult.addReading(r7);
        expectedResult.addReading(r3);
        expectedResult.addReading(r4);
        expectedResult.addReading(r10);
        //Act
        AreaReadingList actualResult = validAreaReadingList.getListOfMaxValuesForEachDay();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfWeGetLastColdestDayInGivenIntervalWorks() {
        // Arrange

        validAreaReadingList = new AreaReadingList();
        Reading reading1 = new Reading(23, new GregorianCalendar(2018, Calendar.JULY, 1, 10, 30).getTime(), "C");
        Reading reading2 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 1, 14, 30).getTime(), "C");
        Reading reading3 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 2, 11, 30).getTime(), "C");
        Reading reading4 = new Reading(29, new GregorianCalendar(2018, Calendar.JULY, 2, 16, 30).getTime(), "C");
        Reading reading5 = new Reading(34, new GregorianCalendar(2018, Calendar.JULY, 3, 9, 30).getTime(), "C");
        Reading reading6 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 3, 10, 30).getTime(), "C");
        Reading reading7 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 4, 10, 30).getTime(), "C");
        Reading reading8 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 4, 15, 30).getTime(), "C");
        Reading reading9 = new Reading(12, new GregorianCalendar(2018, Calendar.JULY, 5, 11, 30).getTime(), "C");
        Reading reading10 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime(), "C");
        Reading reading11 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 30).getTime(), "C");
        Reading reading12 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 35).getTime(), "C");
        Reading reading13 = new Reading(20, new GregorianCalendar(2018, Calendar.JULY, 7, 10, 30).getTime(), "C");
        Reading reading14 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 7, 14, 30).getTime(), "C");
        Reading reading15 = new Reading(26, new GregorianCalendar(2018, Calendar.JULY, 8, 9, 30).getTime(), "C");
        Reading reading16 = new Reading(22, new GregorianCalendar(2018, Calendar.JULY, 8, 10, 30).getTime(), "C");
        Reading reading17 = new Reading(21, new GregorianCalendar(2018, Calendar.JULY, 9, 13, 30).getTime(), "C");
        Reading reading18 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 9, 15, 30).getTime(), "C");
        Reading reading19 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 10, 10, 30).getTime(), "C");
        Reading reading20 = new Reading(31, new GregorianCalendar(2018, Calendar.JULY, 10, 15, 30).getTime(), "C");
        validAreaReadingList.addReading(reading1);
        validAreaReadingList.addReading(reading2);
        validAreaReadingList.addReading(reading3);
        validAreaReadingList.addReading(reading4);
        validAreaReadingList.addReading(reading5);
        validAreaReadingList.addReading(reading6);
        validAreaReadingList.addReading(reading7);
        validAreaReadingList.addReading(reading8);
        validAreaReadingList.addReading(reading9);
        validAreaReadingList.addReading(reading10);
        validAreaReadingList.addReading(reading11);
        validAreaReadingList.addReading(reading12);
        validAreaReadingList.addReading(reading13);
        validAreaReadingList.addReading(reading14);
        validAreaReadingList.addReading(reading15);
        validAreaReadingList.addReading(reading16);
        validAreaReadingList.addReading(reading17);
        validAreaReadingList.addReading(reading18);
        validAreaReadingList.addReading(reading19);
        validAreaReadingList.addReading(reading20);

        // Act

        Date actualResult = validAreaReadingList.getLastColdestDayInGivenInterval(new GregorianCalendar(2018, Calendar.JULY, 1, 5, 0).getTime(), new GregorianCalendar(2018, Calendar.JULY, 10, 23, 0).getTime());

        // Assert

        assertEquals(new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime(), actualResult);
    }

    @Test
    void seeIfWeGetReadingListBetweenDates() {
        //Arrange
        validAreaReadingList = new AreaReadingList();
        Reading reading1 = new Reading(23, new GregorianCalendar(2018, Calendar.JULY, 1, 10, 30).getTime(), "C");
        Reading reading2 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 1, 14, 30).getTime(), "C");
        Reading reading3 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 2, 11, 30).getTime(), "C");
        Reading reading4 = new Reading(29, new GregorianCalendar(2018, Calendar.JULY, 2, 16, 30).getTime(), "C");
        Reading reading5 = new Reading(34, new GregorianCalendar(2018, Calendar.JULY, 3, 9, 30).getTime(), "C");
        Reading reading6 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 3, 10, 30).getTime(), "C");
        Reading reading7 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 4, 10, 30).getTime(), "C");
        Reading reading8 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 4, 15, 30).getTime(), "C");
        Reading reading9 = new Reading(12, new GregorianCalendar(2018, Calendar.JULY, 5, 11, 30).getTime(), "C");
        Reading reading10 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime(), "C");
        Reading reading11 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 30).getTime(), "C");
        Reading reading12 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 35).getTime(), "C");
        Reading reading13 = new Reading(20, new GregorianCalendar(2018, Calendar.JULY, 7, 10, 30).getTime(), "C");
        Reading reading14 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 7, 14, 30).getTime(), "C");
        Reading reading15 = new Reading(26, new GregorianCalendar(2018, Calendar.JULY, 8, 9, 30).getTime(), "C");
        Reading reading16 = new Reading(22, new GregorianCalendar(2018, Calendar.JULY, 8, 10, 30).getTime(), "C");
        Reading reading17 = new Reading(21, new GregorianCalendar(2018, Calendar.JULY, 9, 13, 30).getTime(), "C");
        Reading reading18 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 9, 15, 30).getTime(), "C");
        Reading reading19 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 10, 10, 30).getTime(), "C");
        Reading reading20 = new Reading(31, new GregorianCalendar(2018, Calendar.JULY, 10, 15, 30).getTime(), "C");
        validAreaReadingList.addReading(reading1);
        validAreaReadingList.addReading(reading2);
        validAreaReadingList.addReading(reading3);
        validAreaReadingList.addReading(reading4);
        validAreaReadingList.addReading(reading5);
        validAreaReadingList.addReading(reading6);
        validAreaReadingList.addReading(reading7);
        validAreaReadingList.addReading(reading8);
        validAreaReadingList.addReading(reading9);
        validAreaReadingList.addReading(reading10);
        validAreaReadingList.addReading(reading11);
        validAreaReadingList.addReading(reading12);
        validAreaReadingList.addReading(reading13);
        validAreaReadingList.addReading(reading14);
        validAreaReadingList.addReading(reading15);
        validAreaReadingList.addReading(reading16);
        validAreaReadingList.addReading(reading17);
        validAreaReadingList.addReading(reading18);
        validAreaReadingList.addReading(reading19);
        validAreaReadingList.addReading(reading20);
        AreaReadingList expectedResult = new AreaReadingList();
        expectedResult.addReading(reading5);
        expectedResult.addReading(reading6);
        expectedResult.addReading(reading7);
        expectedResult.addReading(reading8);
        expectedResult.addReading(reading9);
        expectedResult.addReading(reading10);
        expectedResult.addReading(reading11);
        expectedResult.addReading(reading12);
        //Act
        AreaReadingList actualResult = validAreaReadingList.getReadingListBetweenDates(new GregorianCalendar(2018, Calendar.JULY, 3, 9, 0).getTime(), new GregorianCalendar(2018, Calendar.JULY, 7, 10, 29).getTime());
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDates() {
        validAreaReadingList.addReading(new Reading(22, validDate9, "C"));
        validAreaReadingList.addReading(new Reading(30, validDate16, "C"));
        validAreaReadingList.addReading(new Reading(5, validDate18, "C"));
        validAreaReadingList.addReading(new Reading(50, validDate19, "C"));
        Date expectedResult = validDate18;

        Date result = validAreaReadingList.getDateHighestAmplitudeBetweenDates(validDate9, validDate19);

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDatesIfReadingsDontChange() {
        validAreaReadingList.addReading(new Reading(22, validDate9, "C"));
        validAreaReadingList.addReading(new Reading(22, validDate16, "C"));
        validAreaReadingList.addReading(new Reading(22, validDate18, "C"));
        validAreaReadingList.addReading(new Reading(22, validDate19, "C"));
        Date expectedResult = validDate18;

        Date result = validAreaReadingList.getDateHighestAmplitudeBetweenDates(validDate9, validDate19);

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDatesIfReadingsDontChangeInverted() {
        validAreaReadingList.addReading(new Reading(22, validDate18, "C"));
        validAreaReadingList.addReading(new Reading(22, validDate19, "C"));
        validAreaReadingList.addReading(new Reading(22, validDate9, "C"));
        validAreaReadingList.addReading(new Reading(22, validDate16, "C"));

        Date expectedResult = validDate18;

        Date result = validAreaReadingList.getDateHighestAmplitudeBetweenDates(validDate9, validDate19);

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDatesThrowsException() {
        //Test if it throws exception when there is no readings available for the period requested
        GregorianCalendar startDate = new GregorianCalendar(2013, Calendar.JANUARY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2014, Calendar.JANUARY, 1);

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                validAreaReadingList.getDateHighestAmplitudeBetweenDates(startDate.getTime(), endDate.getTime()));

        assertEquals("Warning: Temperature amplitude value not calculated - No readings available.",
                exception.getMessage());
    }

    @Test
    void seeIfGetValueReadingThrowsException() {
        //Arrange

        AreaReadingList emptyList = new AreaReadingList();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.getValueReading(0));

        //Assert

        assertEquals("The reading list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetDateReadingThrowsException() {
        //Arrange

        AreaReadingList emptyList = new AreaReadingList();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.getValueDate(0));

        //Assert

        assertEquals("The reading list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetLastColdestDayInIntervalThrowsException() {
        //Arrange
        AreaReadingList emptyList = new AreaReadingList();
        Date date1 = new GregorianCalendar(2018, Calendar.JANUARY, 1).getTime();
        Date date2 = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> emptyList.getLastColdestDayInGivenInterval(date1, date2));

        //Assert
        assertEquals("No readings available.", exception.getMessage());
    }

    @Test
    void seeIfGetLastColdestDayInIntervalThrowsExceptionWithReadingOutOfTheInterval() {
        //Arrange
        AreaReadingList emptyList = new AreaReadingList();
        Date date1 = new GregorianCalendar(2018, Calendar.JANUARY, 1).getTime();
        Date date2 = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        Reading reading = new Reading(20, new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(), "C");
        emptyList.addReading(reading);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> emptyList.getLastColdestDayInGivenInterval(date1, date2));

        //Assert
        assertEquals("No readings available in the chosen interval.", exception.getMessage());
    }

    @Test
    void seeIfContainsWorks() {
        //Arrange

        Reading validReading1 = new Reading(20.2, validDate1, "C");
        Reading validReading2 = new Reading(20.2, validDate1, "C");

        //Act

        boolean actualResult1 = validAreaReadingList.contains(validReading1);

        //Arrange

        validAreaReadingList.addReading(validReading1);

        //Act

        boolean actualResult2 = validAreaReadingList.contains(validReading2);

        //Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
    }

    @Test
    void seeIfAddReadingWorks() {
        //Arrange

        Reading validReading1 = new Reading(20.2, validDate1, "C");
        Reading validReading2 = new Reading(20.2, validDate1, "C");

        //Act

        boolean actualResult1 = validAreaReadingList.addReading(validReading1);
        boolean actualResult2 = validAreaReadingList.addReading(validReading2);

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    void seeIfGetHottestDayInGivenPeriodWorks() {
        // Arrange

        Date expectedResult = new GregorianCalendar(2018, Calendar.SEPTEMBER, 3).getTime();
        Reading firstReading = new Reading(15, validDate3, "C");
        Reading secondReading = new Reading(29, validDate2, "C");
        validAreaReadingList.addReading(firstReading);
        validAreaReadingList.addReading(secondReading);

        // Act

        Date actualResult = validAreaReadingList.getFirstHottestDayInGivenPeriod(validDate12, validDate1);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetHottestDayInGivenPeriodWorksNoReadings(){
        // Arrange

        Reading outOfBoundsReading = new Reading(1, validDate1, "C");
        validAreaReadingList.addReading(outOfBoundsReading);

        // Assert

       assertThrows(IllegalArgumentException.class, () -> validAreaReadingList.getFirstHottestDayInGivenPeriod(validDate12,validDate2));
    }
}