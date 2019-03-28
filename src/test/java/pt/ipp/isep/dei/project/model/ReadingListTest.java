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
    private Date validDate18; // same day and month as 9 ans 16 but different year
    private Date validDate19; // same day and month as 9 ans 16 but different year, different hour

    @BeforeEach
    void arrangeArtifacts() {
        validReadingList = new ReadingList();
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

        Reading r0 = new Reading(23, validDate3); //  01 Oct 23:59:59 (2018)
        Reading r1 = new Reading(23, validDate4); //  07 Oct 00:00:00 "
        Reading r2 = new Reading(24, validDate5); //  08 Oct 23:26:21 "
        Reading r3 = new Reading(25, validDate6); //  09 Oct 08:21:22 "
        Reading r4 = new Reading(26, validDate7); //  10 Oct 18:14:03 "
        Reading r5 = new Reading(23, validDate8); //  23 Oct 12:14:23 "
        Reading r6 = new Reading(22, validDate9); //  13 Oct 12:12:12 "
        Reading r7 = new Reading(23, validDate10); // 30 Oct 23:59:59 "
        Reading r8 = new Reading(22, validDate11); // 01 Nov 01:00:00 "
        Reading r9 = new Reading(23, validDate18); // 13 Oct 12:12:12 (2019)
        Reading r10 = new Reading(22, validDate19); // 13 Oct 23:59:59 "
        validReadingList.addReading(r0);
        validReadingList.addReading(r1);
        validReadingList.addReading(r2);
        validReadingList.addReading(r3);
        validReadingList.addReading(r4);
        validReadingList.addReading(r5);
        validReadingList.addReading(r6);
        validReadingList.addReading(r7);
        validReadingList.addReading(r8);
        validReadingList.addReading(r9);
        validReadingList.addReading(r10);
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

        List<Date> actualResult = validReadingList.getDaysWithReadingsBetweenDates(validDate4, validDate19);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDatesWithReadingsBetweenTwoGivenDatesIfMostRecentReadingsAreIntroducedFirst() {
        // Arrange

        Reading r0 = new Reading(23, validDate3); //  01 Oct 23:59:59 (2018)
        Reading r1 = new Reading(23, validDate4); //  07 Oct 00:00:00 "
        Reading r2 = new Reading(24, validDate5); //  08 Oct 23:26:21 "
        Reading r3 = new Reading(25, validDate6); //  09 Oct 08:21:22 "
        Reading r4 = new Reading(26, validDate7); //  10 Oct 18:14:03 "
        Reading r5 = new Reading(23, validDate8); //  23 Oct 12:14:23 "
        Reading r6 = new Reading(22, validDate9); //  13 Oct 12:12:12 "
        Reading r7 = new Reading(23, validDate10); // 30 Oct 23:59:59 "
        Reading r8 = new Reading(22, validDate11); // 01 Nov 01:00:00 "
        Reading r9 = new Reading(23, validDate18); // 13 Oct 12:12:12 (2019)
        Reading r10 = new Reading(22, validDate19); // 13 Oct 23:59:59 "
        validReadingList.addReading(r10);
        validReadingList.addReading(r9);
        validReadingList.addReading(r8);
        validReadingList.addReading(r7);
        validReadingList.addReading(r6);
        validReadingList.addReading(r5);
        validReadingList.addReading(r4);
        validReadingList.addReading(r3);
        validReadingList.addReading(r2);
        validReadingList.addReading(r1);
        validReadingList.addReading(r0);
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

        List<Date> actualResult = validReadingList.getDaysWithReadingsBetweenDates(validDate4, validDate19);

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
    void seeIfGetsLastSecondOfDay() {
        // Arrange

        Date expectedResult = new GregorianCalendar(2018, Calendar.OCTOBER, 2, 23, 59, 59).getTime();

        // Assert

        assertEquals(expectedResult, validReadingList.getLastSecondOfDay(validDate14));
    }

    @Test
    void seeIfReadingDateWithinTwoDates() {
        //Arrange

        Reading testReading = new Reading(22, validDate14);
        validReadingList.addReading(testReading);

        // Act

        boolean actualResult = validReadingList.isReadingDateBetweenTwoDates(testReading.getDate(), validDate12,
                validDate16);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfReadingDateWithinTwoDatesFalse() {
        //Arrange

        Reading testReading = new Reading(22, validDate14);
        validReadingList.addReading(testReading);

        // Act

        boolean actualResult = validReadingList.isReadingDateBetweenTwoDates(testReading.getDate(), validDate13,
                validDate15);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void getByIndexEmptyReadingList() {
        //Arrange

        ReadingList emptyList = new ReadingList();

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

        ReadingList emptyList = new ReadingList();
        validReadingList.addReading(new Reading(20, validDate1));
        ReadingList validReadingList2 = new ReadingList();
        validReadingList2.addReading(new Reading(20, validDate1));
        validReadingList2.addReading(new Reading(25, validDate2));

        expectedResult2[0] = new Reading(20, validDate1);
        expectedResult3[0] = new Reading(20, validDate1);
        expectedResult3[1] = new Reading(25, validDate2);

        //Act

        Reading[] actualResult1 = emptyList.getElementsAsArray();
        Reading[] actualResult2 = validReadingList.getElementsAsArray();
        Reading[] actualResult3 = validReadingList2.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void appendListNoDuplicates() {
        //Arrange

        ReadingList expectedResult1 = new ReadingList();
        ReadingList expectedResult2 = new ReadingList();

        Reading reading1 = new Reading(20, validDate1);
        Reading reading2 = new Reading(22, validDate2);

        ReadingList emptyList = new ReadingList();
        validReadingList.addReading(reading1);
        ReadingList validReadingList2 = new ReadingList();
        validReadingList2.addReading(reading1);
        validReadingList2.addReading(reading2);

        expectedResult1.addReading(reading1);
        expectedResult2.addReading(reading1);
        expectedResult2.addReading(reading2);

        //Act

        ReadingList actualResult1 = validReadingList.appendListNoDuplicates(emptyList);

        //Assert

        assertEquals(expectedResult1, actualResult1);

        //Act

        ReadingList actualResult2 = emptyList.appendListNoDuplicates(validReadingList);
        ReadingList actualResult3 = validReadingList.appendListNoDuplicates(validReadingList2);

        //Assert

        assertEquals(expectedResult1, actualResult2);
        assertEquals(expectedResult2, actualResult3);
    }

    @Test
    void hashCodeDummyTest() {
        //Arrange

        Reading reading1 = new Reading(22, validDate14);
        validReadingList.addReading(reading1);

        // Act

        int actualResult = validReadingList.hashCode();

        // Assert

        assertEquals(actualResult, 1);
    }

    @Test
    void seeIfWeGetMaxValueOfTheDayWorks() {
        //Arrange
        ReadingList readingList = new ReadingList();
        ReadingList readingList2 = new ReadingList();
        ReadingList readingList3 = new ReadingList();
        Reading reading1 = new Reading(22, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 10, 0).getTime());
        Reading reading2 = new Reading(22, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 9, 0).getTime());
        Reading reading3 = new Reading(25, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 11, 0).getTime());
        Reading reading4 = new Reading(19, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 21, 30).getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        readingList2.addReading(reading2);
        readingList2.addReading(reading3);
        readingList3.addReading(reading3);
        readingList3.addReading(reading4);
        //Act
        Reading actualResult = readingList.getMaxValueOfTheDay(new GregorianCalendar(2018, Calendar.OCTOBER, 8).getTime());
        Reading actualResult2 = readingList2.getMaxValueOfTheDay(new GregorianCalendar(2018, Calendar.OCTOBER, 8).getTime());
        Reading actualResult3 = readingList3.getMaxValueOfTheDay(new GregorianCalendar(2018, Calendar.OCTOBER, 8).getTime());

        //Assert
        assertEquals(reading1, actualResult);
        assertEquals(reading3, actualResult2);
        assertEquals(reading3, actualResult3);
    }

    @Test
    void seeIfWeGetReadingListWithSpecificValueWorks() {
        //Arrange
        validReadingList = new ReadingList();
        ReadingList expectedResult = new ReadingList();
        Reading r1 = new Reading(22, validDate5);
        Reading r2 = new Reading(24, validDate14);
        Reading r3 = new Reading(22, validDate2);
        Reading r4 = new Reading(21, validDate15);
        Reading r5 = new Reading(22, validDate12);
        Reading r6 = new Reading(29, validDate2);
        validReadingList.addReading(r1);
        validReadingList.addReading(r2);
        validReadingList.addReading(r3);
        validReadingList.addReading(r4);
        validReadingList.addReading(r5);
        validReadingList.addReading(r6);
        expectedResult.addReading(r1);
        expectedResult.addReading(r3);
        expectedResult.addReading(r5);
        //Act
        ReadingList actualResult = validReadingList.getReadingListOfReadingsWithSpecificValue(22.0);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfWeGetMinValueInReadingListWorks() {
        //Arrange
        ReadingList readingList = new ReadingList();
        ReadingList readingList2 = new ReadingList();
        ReadingList readingList3 = new ReadingList();
        Reading reading1 = new Reading(20, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 2, 30).getTime());
        Reading reading2 = new Reading(20, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 11, 30).getTime());
        Reading reading3 = new Reading(19, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 21, 30).getTime());
        Reading reading4 = new Reading(21, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 3, 30).getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading4);
        readingList.addReading(reading2);
        readingList2.addReading(reading2);
        readingList2.addReading(reading3);
        readingList3.addReading(reading3);
        readingList3.addReading(reading4);
        double expectedResult1 = 20;
        double expectedResult2 = 19;
        double expectedResult3 = 19;
        //Act
        double actualResult = readingList.getMinValueInReadingList();
        double actualResult2 = readingList2.getMinValueInReadingList();
        double actualResult3 = readingList3.getMinValueInReadingList();
        //Assert
        assertNotSame(expectedResult1, actualResult);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfWeGetReadingWithSpecificDateWorks() {
        //Arrange
        validReadingList = new ReadingList();
        Reading r1 = new Reading(22, validDate5);
        Reading r2 = new Reading(24, validDate14);
        Reading r3 = new Reading(22, validDate2);
        Reading r4 = new Reading(21, validDate15);
        Reading r5 = new Reading(22, validDate12);
        Reading r6 = new Reading(29, validDate2);
        validReadingList.addReading(r1);
        validReadingList.addReading(r2);
        validReadingList.addReading(r3);
        validReadingList.addReading(r4);
        validReadingList.addReading(r5);
        validReadingList.addReading(r6);
        //Act
        Reading actualResult2 = validReadingList.getAReadingWithSpecificDay(validDate7);
        Reading actualResult = validReadingList.getAReadingWithSpecificDay(validDate2);
        //Assert
        assertNull(actualResult2);
        assertEquals(r3, actualResult);
    }

    @Test
    void seeIfWeGetListOfMaxValuesForEachDayWorks() {
        //Arrange
        validReadingList = new ReadingList();
        Reading r1 = new Reading(22, validDate5);
        Reading r2 = new Reading(24, validDate14);
        Reading r3 = new Reading(22, validDate2);
        Reading r4 = new Reading(21, validDate15);
        Reading r5 = new Reading(22, validDate12);
        Reading r6 = new Reading(23, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 21, 0).getTime());
        Reading r7 = new Reading(26, new GregorianCalendar(2018, Calendar.OCTOBER, 2, 10, 0).getTime());
        Reading r8 = new Reading(20, new GregorianCalendar(2018, Calendar.SEPTEMBER, 3, 23, 30).getTime());
        Reading r10 = new Reading(20, validDate12);
        validReadingList.addReading(r1);
        validReadingList.addReading(r2);
        validReadingList.addReading(r3);
        validReadingList.addReading(r4);
        validReadingList.addReading(r5);
        validReadingList.addReading(r6);
        validReadingList.addReading(r7);
        validReadingList.addReading(r8);
        validReadingList.addReading(r10);
        ReadingList expectedResult = new ReadingList();
        expectedResult.addReading(r6);
        expectedResult.addReading(r7);
        expectedResult.addReading(r3);
        expectedResult.addReading(r4);
        expectedResult.addReading(r10);
        //Act
        ReadingList actualResult = validReadingList.getListOfMaxValuesForEachDay();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfWeGetLastColdestDayInGivenIntervalWorks() {
        // Arrange

        validReadingList = new ReadingList();
        Reading reading1 = new Reading(23, new GregorianCalendar(2018, Calendar.JULY, 1, 10, 30).getTime());
        Reading reading2 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 1, 14, 30).getTime());
        Reading reading3 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 2, 11, 30).getTime());
        Reading reading4 = new Reading(29, new GregorianCalendar(2018, Calendar.JULY, 2, 16, 30).getTime());
        Reading reading5 = new Reading(34, new GregorianCalendar(2018, Calendar.JULY, 3, 9, 30).getTime());
        Reading reading6 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 3, 10, 30).getTime());
        Reading reading7 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 4, 10, 30).getTime());
        Reading reading8 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 4, 15, 30).getTime());
        Reading reading9 = new Reading(12, new GregorianCalendar(2018, Calendar.JULY, 5, 11, 30).getTime());
        Reading reading10 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime());
        Reading reading11 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 30).getTime());
        Reading reading12 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 35).getTime());
        Reading reading13 = new Reading(20, new GregorianCalendar(2018, Calendar.JULY, 7, 10, 30).getTime());
        Reading reading14 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 7, 14, 30).getTime());
        Reading reading15 = new Reading(26, new GregorianCalendar(2018, Calendar.JULY, 8, 9, 30).getTime());
        Reading reading16 = new Reading(22, new GregorianCalendar(2018, Calendar.JULY, 8, 10, 30).getTime());
        Reading reading17 = new Reading(21, new GregorianCalendar(2018, Calendar.JULY, 9, 13, 30).getTime());
        Reading reading18 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 9, 15, 30).getTime());
        Reading reading19 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 10, 10, 30).getTime());
        Reading reading20 = new Reading(31, new GregorianCalendar(2018, Calendar.JULY, 10, 15, 30).getTime());
        validReadingList.addReading(reading1);
        validReadingList.addReading(reading2);
        validReadingList.addReading(reading3);
        validReadingList.addReading(reading4);
        validReadingList.addReading(reading5);
        validReadingList.addReading(reading6);
        validReadingList.addReading(reading7);
        validReadingList.addReading(reading8);
        validReadingList.addReading(reading9);
        validReadingList.addReading(reading10);
        validReadingList.addReading(reading11);
        validReadingList.addReading(reading12);
        validReadingList.addReading(reading13);
        validReadingList.addReading(reading14);
        validReadingList.addReading(reading15);
        validReadingList.addReading(reading16);
        validReadingList.addReading(reading17);
        validReadingList.addReading(reading18);
        validReadingList.addReading(reading19);
        validReadingList.addReading(reading20);

        // Act

        Date actualResult = validReadingList.getLastColdestDayInGivenInterval(new GregorianCalendar(2018, Calendar.JULY, 1, 5, 0).getTime(), new GregorianCalendar(2018, Calendar.JULY, 10, 23, 0).getTime());

        // Assert

        assertEquals(new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime(), actualResult);
    }

    @Test
    void seeIfWeGetReadingListBetweenDates() {
        //Arrange
        validReadingList = new ReadingList();
        Reading reading1 = new Reading(23, new GregorianCalendar(2018, Calendar.JULY, 1, 10, 30).getTime());
        Reading reading2 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 1, 14, 30).getTime());
        Reading reading3 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 2, 11, 30).getTime());
        Reading reading4 = new Reading(29, new GregorianCalendar(2018, Calendar.JULY, 2, 16, 30).getTime());
        Reading reading5 = new Reading(34, new GregorianCalendar(2018, Calendar.JULY, 3, 9, 30).getTime());
        Reading reading6 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 3, 10, 30).getTime());
        Reading reading7 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 4, 10, 30).getTime());
        Reading reading8 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 4, 15, 30).getTime());
        Reading reading9 = new Reading(12, new GregorianCalendar(2018, Calendar.JULY, 5, 11, 30).getTime());
        Reading reading10 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime());
        Reading reading11 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 30).getTime());
        Reading reading12 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 35).getTime());
        Reading reading13 = new Reading(20, new GregorianCalendar(2018, Calendar.JULY, 7, 10, 30).getTime());
        Reading reading14 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 7, 14, 30).getTime());
        Reading reading15 = new Reading(26, new GregorianCalendar(2018, Calendar.JULY, 8, 9, 30).getTime());
        Reading reading16 = new Reading(22, new GregorianCalendar(2018, Calendar.JULY, 8, 10, 30).getTime());
        Reading reading17 = new Reading(21, new GregorianCalendar(2018, Calendar.JULY, 9, 13, 30).getTime());
        Reading reading18 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 9, 15, 30).getTime());
        Reading reading19 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 10, 10, 30).getTime());
        Reading reading20 = new Reading(31, new GregorianCalendar(2018, Calendar.JULY, 10, 15, 30).getTime());
        validReadingList.addReading(reading1);
        validReadingList.addReading(reading2);
        validReadingList.addReading(reading3);
        validReadingList.addReading(reading4);
        validReadingList.addReading(reading5);
        validReadingList.addReading(reading6);
        validReadingList.addReading(reading7);
        validReadingList.addReading(reading8);
        validReadingList.addReading(reading9);
        validReadingList.addReading(reading10);
        validReadingList.addReading(reading11);
        validReadingList.addReading(reading12);
        validReadingList.addReading(reading13);
        validReadingList.addReading(reading14);
        validReadingList.addReading(reading15);
        validReadingList.addReading(reading16);
        validReadingList.addReading(reading17);
        validReadingList.addReading(reading18);
        validReadingList.addReading(reading19);
        validReadingList.addReading(reading20);
        ReadingList expectedResult = new ReadingList();
        expectedResult.addReading(reading5);
        expectedResult.addReading(reading6);
        expectedResult.addReading(reading7);
        expectedResult.addReading(reading8);
        expectedResult.addReading(reading9);
        expectedResult.addReading(reading10);
        expectedResult.addReading(reading11);
        expectedResult.addReading(reading12);
        //Act
        ReadingList actualResult = validReadingList.getReadingListBetweenDates(new GregorianCalendar(2018, Calendar.JULY, 3, 9, 0).getTime(), new GregorianCalendar(2018, Calendar.JULY, 7, 10, 29).getTime());
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDates() {
        validReadingList.addReading(new Reading(22, validDate9));
        validReadingList.addReading(new Reading(30, validDate16));
        validReadingList.addReading(new Reading(5, validDate18));
        validReadingList.addReading(new Reading(50, validDate19));
        Date expectedResult = validDate18;

        Date result = validReadingList.getDateHighestAmplitudeBetweenDates(validDate9, validDate19);

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDatesIfReadingsDontChange() {
        validReadingList.addReading(new Reading(22, validDate9));
        validReadingList.addReading(new Reading(22, validDate16));
        validReadingList.addReading(new Reading(22, validDate18));
        validReadingList.addReading(new Reading(22, validDate19));
        Date expectedResult = validDate18;

        Date result = validReadingList.getDateHighestAmplitudeBetweenDates(validDate9, validDate19);

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDatesIfReadingsDontChangeInverted() {
        validReadingList.addReading(new Reading(22, validDate18));
        validReadingList.addReading(new Reading(22, validDate19));
        validReadingList.addReading(new Reading(22, validDate9));
        validReadingList.addReading(new Reading(22, validDate16));

        Date expectedResult = validDate18;

        Date result = validReadingList.getDateHighestAmplitudeBetweenDates(validDate9, validDate19);

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDatesThrowsException() {
        //Test if it throws exception when there is no readings available for the period requested
        GregorianCalendar startDate = new GregorianCalendar(2013, Calendar.JANUARY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2014, Calendar.JANUARY, 1);

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                validReadingList.getDateHighestAmplitudeBetweenDates(startDate.getTime(), endDate.getTime()));

        assertEquals("Warning: Temperature amplitude value not calculated - No readings available.",
                exception.getMessage());
    }

    @Test
    void seeIfGetValueReadingThrowsException() {
        //Arrange

        ReadingList emptyList = new ReadingList();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.getValueReading(0));

        //Assert

        assertEquals("The reading list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetDateReadingThrowsException() {
        //Arrange

        ReadingList emptyList = new ReadingList();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.getValueDate(0));

        //Assert

        assertEquals("The reading list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetLastColdestDayInIntervalThrowsException() {
        //Arrange
        ReadingList emptyList = new ReadingList();
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
        ReadingList emptyList = new ReadingList();
        Date date1 = new GregorianCalendar(2018, Calendar.JANUARY, 1).getTime();
        Date date2 = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        Reading reading = new Reading(20, new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime());
        emptyList.addReading(reading);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> emptyList.getLastColdestDayInGivenInterval(date1, date2));

        //Assert
        assertEquals("No readings available in the chosen interval.", exception.getMessage());
    }

    @Test
    void seeIfContainsWorks() {
        //Arrange

        Reading validReading1 = new Reading(20.2, validDate1);
        Reading validReading2 = new Reading(20.2, validDate1);

        //Act

        boolean actualResult1 = validReadingList.contains(validReading1);

        //Arrange

        validReadingList.addReading(validReading1);

        //Act

        boolean actualResult2 = validReadingList.contains(validReading2);

        //Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
    }

    @Test
    void seeIfAddReadingWorks() {
        //Arrange

        Reading validReading1 = new Reading(20.2, validDate1);
        Reading validReading2 = new Reading(20.2, validDate1);

        //Act

        boolean actualResult1 = validReadingList.addReading(validReading1);
        boolean actualResult2 = validReadingList.addReading(validReading2);

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    void seeIfGetHottestDayInGivenPeriodWorks() {
        // Arrange

        Date expectedResult = new GregorianCalendar(2018, Calendar.SEPTEMBER, 3).getTime();
        Reading firstReading = new Reading(15, validDate3);
        Reading secondReading = new Reading(29, validDate2);
        validReadingList.addReading(firstReading);
        validReadingList.addReading(secondReading);

        // Act

        Date actualResult = validReadingList.getFirstHottestDayInGivenPeriod(validDate12, validDate1);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetHottestDayInGivenPeriodWorksNoReadings(){
        // Arrange

        Reading outOfBoundsReading = new Reading(1, validDate1);
        validReadingList.addReading(outOfBoundsReading);

        // Assert

       assertThrows(IllegalArgumentException.class, () -> validReadingList.getFirstHottestDayInGivenPeriod(validDate12,validDate2));
    }
}