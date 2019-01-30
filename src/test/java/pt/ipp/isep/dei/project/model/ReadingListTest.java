package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Test;

import java.util.*;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ReadingList tests class.
 */

class ReadingListTest {

    @Test
    void seeAddReadingIfListIsEmpty() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar calendar = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(17, calendar.getTime());
        boolean expectedResult = true;

        //Act
        boolean actualResult = readingList.addReading(reading1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfGetTotalFromGivenList() {
        //Arrange
        ReadingList readingList = new ReadingList();
        List<Double> list =new ArrayList<>();
        list.add(1.0);
        list.add(2.0);

        //Act
        double actualResult = readingList.getTotalFromGivenList(list);

        //Assert
        assertEquals(3.0, actualResult);
    }

    @Test
    void seeAddReadingIfListHasDifferentReading() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar calendar = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(17, calendar.getTime());
        GregorianCalendar calendar2 = new GregorianCalendar(118, 9, 3);
        Reading reading2 = new Reading(29, calendar2.getTime());
        boolean expectedResult = true;

        //Act
        readingList.addReading(reading1);
        boolean actualResult = readingList.addReading(reading2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeAddReadingIfListHasSameReading() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar calendar = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(17, calendar.getTime());
        Reading reading2 = new Reading(17, calendar.getTime());
        boolean expectedResult = false;

        //Act
        readingList.addReading(reading1);
        boolean actualResult = readingList.addReading(reading2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeThatWeAddAReading1ToAList() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar calendar = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(17, calendar.getTime());
        GregorianCalendar calendar2 = new GregorianCalendar(118, 9, 3);
        Reading reading2 = new Reading(29, calendar.getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        boolean expectedResult = true;

        //Act
        boolean actualResult = readingList.getListOfReadings().contains(reading1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeThatWeAddAReading2ToAList() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(15, g1.getTime());
        GregorianCalendar g2 = new GregorianCalendar(118, 9, 3);
        Reading reading2 = new Reading(2, g2.getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        boolean expectedResult = true;

        //Act
        boolean actualResult = readingList.getListOfReadings().contains(reading2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeThatWeGetAValueFromAReading1InsideAList() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(15, g1.getTime());
        GregorianCalendar g2 = new GregorianCalendar(118, 9, 3);
        Reading reading2 = new Reading(29, g2.getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        double expectedResult = 15;

        //Act
        double actualResult = readingList.getListOfReadings().get(0).getmValue();

        //Assert
        assertEquals(expectedResult, actualResult, 0.1);
    }

    @Test
    void seeThatWeGetAValueFromAReading2InsideAList() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(15, g1.getTime());
        GregorianCalendar g2 = new GregorianCalendar(118, 9, 3);
        Reading reading2 = new Reading(29, g2.getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        double expectedResult = 29;

        //Act
        double actualResult = readingList.getListOfReadings().get(1).getmValue();

        //Assert
        assertEquals(expectedResult, actualResult, 0.1);
    }

    @Test
    void seeThatAReadingListDoesNotContainAReading() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(15, g1.getTime());
        GregorianCalendar g2 = new GregorianCalendar(118, 9, 3);
        Reading reading2 = new Reading(29, g2.getTime());
        readingList.addReading(reading1);
        boolean expectedResult = false;

        //Act
        boolean actualResult = readingList.getListOfReadings().contains(reading2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeThatAReadingDoesNotAddBecauseItIsAlreadyContained() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(15, g1.getTime());
        GregorianCalendar g2 = new GregorianCalendar(118, 9, 3);
        Reading reading2 = new Reading(29, g2.getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        readingList.addReading(reading2);
        boolean expectedResult = true;

        //Act
        boolean actualResult = readingList.getListOfReadings().contains(reading2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMostRecentReading() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(15, g1.getTime());
        GregorianCalendar g2 = new GregorianCalendar(118, 9, 3);
        Reading reading2 = new Reading(29, g2.getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        Reading expectedResult = new Reading(15, g1.getTime());
        Reading actualResult;

        //Act
        actualResult = readingList.getMostRecentReading();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMostRecentReadingSwitchedDates() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(118, 11, 25);
        Reading reading2 = new Reading(16, g1.getTime());
        GregorianCalendar g2 = new GregorianCalendar(118, 9, 3);
        Reading reading1 = new Reading(29, g2.getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        Reading expectedResult = new Reading(16, g1.getTime());
        Reading actualResult;

        //Act
        actualResult = readingList.getMostRecentReading();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAverageOfMonth() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        double expectedResult = 23.71;

        //Act
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfAllRecordedValueReadingsFromGivenMonth(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.1);
    }

    @Test
    void seeIfAverageReturnsZeroWhenEmptyList() {
        //Arrange
        ReadingList rList = new ReadingList();
        //Act
        double expectedResult = 0;
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfAllRecordedValueReadingsFromGivenMonth(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfAverageReturnsZeroWhenReadingListHasValuesFromDifferentMonthThanGiven() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        double expectedResult = 0;
        //Act
        GregorianCalendar cal = new GregorianCalendar(2018, 1, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfAllRecordedValueReadingsFromGivenMonth(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.1);
    }

    @Test
    void seeIfGetDatesOfTheMonthWithReadingsInMonthLimits() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        List<Date> expectedResult = new ArrayList<>();
        //Act
        expectedResult.add(g1.getTime());
        expectedResult.add(g2.getTime());
        expectedResult.add(g3.getTime());
        expectedResult.add(g4.getTime());
        expectedResult.add(g5.getTime());
        expectedResult.add(g6.getTime());
        expectedResult.add(g7.getTime());
        GregorianCalendar dateWithMonthToTest = new GregorianCalendar(2018, 10, 11, 9, 0, 4);
        List<Date> actualResult = rList.getListOfDatesOfMonthWithReadings(dateWithMonthToTest.getTime());
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDatesOfTheMonthWithReadingsWithLimitsOfFebruary() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 0, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 1, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 1, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 1, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 1, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 1, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 1, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 1, 28, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 2, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        List<Date> expectedResult = new ArrayList<>();
        //Act
        expectedResult.add(g1.getTime());
        expectedResult.add(g2.getTime());
        expectedResult.add(g3.getTime());
        expectedResult.add(g4.getTime());
        expectedResult.add(g5.getTime());
        expectedResult.add(g6.getTime());
        expectedResult.add(g7.getTime());
        GregorianCalendar dateWithMonthToTest = new GregorianCalendar(2018, 1, 11, 9, 0, 4);
        List<Date> actualResult = rList.getListOfDatesOfMonthWithReadings(dateWithMonthToTest.getTime());
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDatesWithReadingsBetweenTwoGivenDates() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 7, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 8, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 9, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 10, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 13, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(7);
        expectedResult.add(8);
        expectedResult.add(9);
        expectedResult.add(10);
        expectedResult.add(13);
        //Act
        GregorianCalendar dateMin = new GregorianCalendar(2018, 10, 7);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 10, 13);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        List<Integer> actualResult = rList.getListOfDaysWithReadingsBetweenTwoGivenDates(dateToTest1, dateToTest2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetEmptyListWhenReadingListHasValuesFromDifferentMonthThanMonthGiven() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 0, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 1, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 1, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 1, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 1, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 1, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 1, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 1, 28, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 2, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        List<Date> expectedResult = new ArrayList<>();
        GregorianCalendar dateWithMonthToTest = new GregorianCalendar(2018, 9, 11, 9, 0, 4);
        List<Date> actualResult = rList.getListOfDatesOfMonthWithReadings(dateWithMonthToTest.getTime());
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetFirstDateOfMonthIfDateGivenInMiddleOfMonth() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7, 4, 5, 7);
        Date d1 = cal.getTime();
        Date expectedResult = (new GregorianCalendar(2018, 10, 1, 0, 0, 0)).getTime();
        //Act
        Date actualResult = rl1.getFirstDateOfMonthFromGivenDate(d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetFirstDateOfMonthIfDateGivenIsStartOfMonth() {
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        Date d1 = cal.getTime();
        Date expectedResult = (new GregorianCalendar(2018, 10, 1, 0, 0, 0)).getTime();
        //Act
        Date actualResult = rl1.getFirstDateOfMonthFromGivenDate(d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetFirstDateOfMonthIfDateGivenIsEndOfMonth() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 11, 31, 23, 59, 59);
        Date d1 = cal.getTime();
        Date expectedResult = (new GregorianCalendar(2018, 11, 1, 0, 0, 0)).getTime();
        //Act
        Date actualResult = rl1.getFirstDateOfMonthFromGivenDate(d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetFirstDateOfMonthIfDateGivenIsEndOfFebruary() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 1, 28, 23, 59, 59);
        Date d1 = cal.getTime();
        Date expectedResult = (new GregorianCalendar(2018, 1, 1, 0, 0, 0)).getTime();
        //Act
        Date actualResult = rl1.getFirstDateOfMonthFromGivenDate(d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetLastDayOfMonthIfDateGivenInMiddleOfMonth() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 11, 7, 6, 32, 32);
        Date d1 = cal.getTime();
        Date expectedResult = (new GregorianCalendar(2018, 11, 31, 23, 59, 59)).getTime();
        //Act
        Date actualResult = rl1.getLastDateOfMonthFromGivenDate(d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLastDayOfMonthIfDateGivenIsStartOfMonth() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 2, 1, 0, 0, 0);
        Date d1 = cal.getTime();
        Date expectedResult = (new GregorianCalendar(2018, 2, 31, 23, 59, 59)).getTime();
        //Act
        Date actualResult = rl1.getLastDateOfMonthFromGivenDate(d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLastDayOfMonthIfDateGivenIsEndOfMonth() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 2, 31, 23, 59, 59);
        Date d1 = cal.getTime();
        Date expectedResult = (new GregorianCalendar(2018, 2, 31, 23, 59, 59)).getTime();
        //Act
        Date actualResult = rl1.getLastDateOfMonthFromGivenDate(d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLastDayOfMonthIfMiddleOfFebruary() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 1, 4, 23, 59, 59);
        Date d1 = cal.getTime();
        Date expectedResult = (new GregorianCalendar(2018, 1, 28, 23, 59, 59)).getTime();
        Date actualResult = rl1.getLastDateOfMonthFromGivenDate(d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetAverageMinimumOfMonthGivenValueReadings() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);

        //Act
        double expectedResult = 23.2;
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMinimumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAverageOfMinusOneWhenValuesReadingsAreFromDifferentMonth() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);

        //Act
        double expectedResult = NaN;
        GregorianCalendar cal = new GregorianCalendar(2018, 1, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMinimumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAverageOfMinusOneWhenValuesReadingsAreFromDifferentYear() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);

        //Act
        double expectedResult = NaN;
        GregorianCalendar cal = new GregorianCalendar(2017, 10, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMinimumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAverageOfMinusOneWhenValuesReadingsAreFromDifferentMonthAndYear() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);

        //Act
        double expectedResult = NaN;
        GregorianCalendar cal = new GregorianCalendar(2017, 1, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMinimumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAverageOfMinusOneWhenEmptyList() {
        //Arrange
        ReadingList rList = new ReadingList();
        //Act
        double expectedResult = NaN;
        GregorianCalendar cal = new GregorianCalendar(2015, 1, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMinimumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfRemovesReadingsFromDifferentMonthThenGivenWhenReadingsAreOnLimitsOfMonth() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        List<Reading> expectedResult = new ArrayList<>();
        List<Reading> actualResult;
        expectedResult.add(r1);
        expectedResult.add(r2);
        expectedResult.add(r3);
        expectedResult.add(r4);
        expectedResult.add(r5);
        expectedResult.add(r6);
        expectedResult.add(r7);
        //Act
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = cal.getTime();
        actualResult = rList.removeReadingsWithDifferentMonthAndYearFromDateGiven(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfWillNotRemoveReadingsWhenListHasSameMonthAndYearGiven() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        List<Reading> expectedResult = new ArrayList<>();
        List<Reading> actualResult;
        expectedResult.add(r1);
        expectedResult.add(r2);
        expectedResult.add(r3);
        expectedResult.add(r4);
        expectedResult.add(r5);
        expectedResult.add(r6);
        expectedResult.add(r7);
        //Act
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = cal.getTime();
        actualResult = rList.removeReadingsWithDifferentMonthAndYearFromDateGiven(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemovesAllReadingsWhenListHasDifferentMonthAndYearThenGiven() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        List<Reading> expectedResult = new ArrayList<>();
        List<Reading> actualResult;
        //Act
        GregorianCalendar cal = new GregorianCalendar(2017, 1, 7);
        Date dateToTest = cal.getTime();
        actualResult = rList.removeReadingsWithDifferentMonthAndYearFromDateGiven(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemovesAllReadingsWhenListHasDifferentMonthThenGiven() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        List<Reading> expectedResult = new ArrayList<>();
        List<Reading> actualResult;
        //Act
        GregorianCalendar cal = new GregorianCalendar(2018, 1, 7);
        Date dateToTest = cal.getTime();
        actualResult = rList.removeReadingsWithDifferentMonthAndYearFromDateGiven(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemovesAllReadingsWhenListHasDifferentYearSameMonthThenGiven() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        List<Reading> expectedResult = new ArrayList<>();
        List<Reading> actualResult;
        //Act
        GregorianCalendar cal = new GregorianCalendar(2017, 10, 7);
        Date dateToTest = cal.getTime();
        actualResult = rList.removeReadingsWithDifferentMonthAndYearFromDateGiven(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetDaysOfTheMonthWithReadingsWhenInLimitsOfMonth() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(1);
        expectedResult.add(23);
        expectedResult.add(27);
        expectedResult.add(28);
        expectedResult.add(30);
        //Act
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = cal.getTime();
        List<Integer> actualResult = rList.getDaysOfMonthWithReadings(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEmptyListWhenDateDifferentThenDateOfReadings() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        List<Integer> expectedResult = new ArrayList<>();
        //Act
        GregorianCalendar cal = new GregorianCalendar(2018, 1, 7);
        Date dateToTest = cal.getTime();
        List<Integer> actualResult = rList.getDaysOfMonthWithReadings(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetValueReadingsFromGivenDayFromListOfOneMonthReadingsAllFromSameDay() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 3, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 3, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 3, 8, 21, 22);
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        List<Double> expectedResult = new ArrayList<>();
        double v1 = 23;
        double v2 = 24;
        double v3 = 25;
        //Act
        expectedResult.add(v1);
        expectedResult.add(v2);
        expectedResult.add(v3);
        List<Double> actualResult = rList.getValueReadingsThatMatchGivenDayFromListOfOneMonthReadings(3);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetValueReadingsFromGivenDayFromListOfOneMonthReadingsWhenDayIsInBeginning() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 3, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 1, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 12, 8, 21, 22);
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        List<Double> expectedResult = new ArrayList<>();
        double v1 = 23;
        //Act
        expectedResult.add(v1);
        List<Double> actualResult = rList.getValueReadingsThatMatchGivenDayFromListOfOneMonthReadings(3);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetValueReadingsFromGivenDayFromListOfOneMonthReadingsWhenDayIsInEnd() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 3, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 1, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 12, 8, 21, 22);
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        List<Double> expectedResult = new ArrayList<>();
        double v1 = 25;
        //Act
        expectedResult.add(v1);
        List<Double> actualResult = rList.getValueReadingsThatMatchGivenDayFromListOfOneMonthReadings(12);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetValueReadingsFromGivenDayFromListOfOneMonthReadingsWhenDayIsMiddle() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 3, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 14, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 12, 8, 21, 22);
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        List<Double> expectedResult = new ArrayList<>();
        double v1 = 24;
        expectedResult.add(v1);
        //Act
        List<Double> actualResult = rList.getValueReadingsThatMatchGivenDayFromListOfOneMonthReadings(14);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLowestValueFromEndOfList() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        double value1 = 2;
        double expectedResult = 2;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        valuesOfDay.add(value1);
        actualResult = rl1.getLowestValueFromGivenList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetLowestValueFromEndOfListOutOfOrder() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        double value1 = 2;
        double value2 = 1;
        double expectedResult = 1;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        valuesOfDay.add(value1);
        valuesOfDay.add(value2);
        actualResult = rl1.getLowestValueFromGivenList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }


    @Test
    void seeIfGetLowestValueFromListIfValuesEqual() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        double value1 = 2;
        double value2 = 0;
        double value3 = 2;
        double value4 = -2;
        double expectedResult = -2;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        valuesOfDay.add(value1);
        valuesOfDay.add(value2);
        valuesOfDay.add(value3);
        valuesOfDay.add(value4);
        actualResult = rl1.getLowestValueFromGivenList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetLowestValueFromMiddleOfList() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        double value1 = 2;
        double value2 = -2;
        double value3 = 2;
        double value4 = 5;
        double expectedResult = -2;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        valuesOfDay.add(value1);
        valuesOfDay.add(value2);
        valuesOfDay.add(value3);
        valuesOfDay.add(value4);
        actualResult = rl1.getLowestValueFromGivenList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetLowestValueFromBeginningOfList() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        double value1 = -2;
        double value2 = 15;
        double value3 = 2;
        double value4 = 5;
        double expectedResult = -2;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        valuesOfDay.add(value1);
        valuesOfDay.add(value2);
        valuesOfDay.add(value3);
        valuesOfDay.add(value4);
        actualResult = rl1.getLowestValueFromGivenList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetLowestValueEmptyAndThrowsStringException() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        ReadingList rl1 = new ReadingList();

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            rl1.getLowestValueFromGivenList(valuesOfDay);
        });
        //Assert
        assertEquals("List is not valid", exception.getMessage());
    }

    @Test
    void seeIfGetLowestValueNullAndThrowsStringException() {
//Arrange
        ReadingList rl1 = new ReadingList();

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            rl1.getLowestValueFromGivenList(null);
        });
        //Assert
        assertEquals("List is not valid", exception.getMessage());
    }

    @Test
    void seeIfGetsAverageFromList() {
        //Arrange
        List<Double> doubleList = new ArrayList<>();
        ReadingList rList = new ReadingList();
        double d1 = 1;
        double d2 = 23;
        double d3 = 43.2;
        double expectedResult = 22.40;
        double actualResult;
        //Act
        doubleList.add(d1);
        doubleList.add(d2);
        doubleList.add(d3);
        actualResult = rList.getAverageFromGivenList(doubleList);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfAverageIsMinusOneWhenListIsEmpty() {
        //Arrange
        List<Double> doubleList = new ArrayList<>();
        ReadingList rList = new ReadingList();
        double expectedResult = NaN;
        double actualResult;
        //Act
        actualResult = rList.getAverageFromGivenList(doubleList);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAverageOfMaximumValueReadingsInMonthWhenListIsinLimitsOfMonth() {
        //Arrange
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(31, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);

        //Act
        double expectedResult = 22.2;
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMaximumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAverageOfMinusOneMaximumWhenListIsEmpty() {
        //Arrange
        ReadingList rList = new ReadingList();
        //Act
        double expectedResult = NaN;
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMaximumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfAverageOfMinusOneWhenGivenDateIsFromDifferentMonthThenList() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(31, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);

        //Act
        double expectedResult = NaN;
        GregorianCalendar cal = new GregorianCalendar(2018, 2, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMaximumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfAverageOfMinusOneWhenGivenDateIsFromDifferentYearThenList() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(31, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);

        //Act
        double expectedResult = NaN;
        GregorianCalendar cal = new GregorianCalendar(2017, 10, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMaximumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfAverageOfMinusOneWhenGivenDateIsFromDifferentMonthAndYearThenList() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(31, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);

        //Act
        double expectedResult = NaN;
        GregorianCalendar cal = new GregorianCalendar(2017, 1, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMaximumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetHighestValueFromEndOfList() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        double value1 = 2;
        double value2 = 2.5;
        double value3 = 0;
        double value4 = 16;
        double expectedResult = 16;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        valuesOfDay.add(value1);
        valuesOfDay.add(value2);
        valuesOfDay.add(value3);
        valuesOfDay.add(value4);
        actualResult = rl1.getHighestValueInList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetHighestValueFromMiddleOfList() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        double value1 = 2;
        double value2 = 16;
        double value3 = 2;
        double value4 = 5;
        double expectedResult = 16;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        valuesOfDay.add(value1);
        valuesOfDay.add(value2);
        valuesOfDay.add(value3);
        valuesOfDay.add(value4);
        actualResult = rl1.getHighestValueInList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }


    @Test
    void seeIfGetHighestValueFromBeginningOfList() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        double value1 = 16;
        double value2 = 15;
        double value3 = 2;
        double value4 = 5;
        double expectedResult = 16;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        valuesOfDay.add(value1);
        valuesOfDay.add(value2);
        valuesOfDay.add(value3);
        valuesOfDay.add(value4);
        actualResult = rl1.getHighestValueInList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetHighestValueEmptyAndThrowsStringException() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        ReadingList rl1 = new ReadingList();

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            rl1.getHighestValueInList(valuesOfDay);
        });
        //Assert
        assertEquals("List is not valid", exception.getMessage());
    }

    @Test
    void seeIfGetHighestValueNullAndThrowsStringException() {
//Arrange
        ReadingList rl1 = new ReadingList();

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            rl1.getHighestValueInList(null);
        });
        //Assert
        assertEquals("List is not valid", exception.getMessage());
    }

    @Test
    void seeIfGetAverageOfGivenDayValueReadingsWhenReadigsInLimitsOfDay() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 24, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 22, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(31, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        //Act
        double expectedResult = 26.666;
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 23);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfGivenDayValueReadings(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAverageOfGivenDayValueReadingsWhenReadigsInLimitsOfDayExtra() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 24, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 22, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(31, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        //Act
        double expectedResult = 26.666;
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 23, 1, 1, 1);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfGivenDayValueReadings(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAverageOfMinusOneWhenDateGivenIsNotInList() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 24, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 22, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(31, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        //Act
        double expectedResult = -1;
        GregorianCalendar cal = new GregorianCalendar(2018, 1, 23);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfGivenDayValueReadings(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAverageOfMinusOneWhenDateGivenIsFromSameDayDifferentMonth() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 24, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 22, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(31, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        //Act
        double expectedResult = -1;
        GregorianCalendar cal = new GregorianCalendar(2018, 1, 23, 2, 2, 2);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfGivenDayValueReadings(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAverageOfMinusOneWhenDateGivenIsFromSameDaySameMonthDifferentYear() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 24, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 22, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(31, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        //Act
        double expectedResult = -1;
        GregorianCalendar cal = new GregorianCalendar(2017, 10, 23);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfGivenDayValueReadings(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetListOfDatesFromWeekWithReadingsWhenReadingsAreInLimitsOfWeek() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 3, 9, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 17, 23, 59, 59);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 18, 0, 0, 0);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 3, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 22, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 22, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 24, 23, 59, 59);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 25, 0, 0, 0);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(18);
        expectedResult.add(22);
        expectedResult.add(24);
        //Act
        GregorianCalendar dateWithWeekToTest = new GregorianCalendar(2018, 10, 21);
        List<Integer> actualResult = rList.getListOfDatesWithReadingsFromWeekOfDateGiven(dateWithWeekToTest.getTime());
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetEmptyListWhenDateGivenIsFromDifferentMonthThenInList() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 3, 9, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 17, 23, 59, 59);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 18, 0, 0, 0);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 3, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 22, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 22, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 24, 23, 59, 59);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 25, 0, 0, 0);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        List<Date> expectedResult = new ArrayList<>();
        //Act
        GregorianCalendar dateWithWeekToTest = new GregorianCalendar(2018, 1, 2);
        List<Integer> actualResult = rList.getListOfDatesWithReadingsFromWeekOfDateGiven(dateWithWeekToTest.getTime());
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetEmptyListWhenDateGivenIsFromDifferentYear() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 3, 9, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 17, 23, 59, 59);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 18, 0, 0, 0);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 3, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 22, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 22, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 24, 23, 59, 59);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 25, 0, 0, 0);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        List<Date> expectedResult = new ArrayList<>();
        //Act
        GregorianCalendar dateWithWeekToTest = new GregorianCalendar(2017, 10, 22);
        List<Integer> actualResult = rList.getListOfDatesWithReadingsFromWeekOfDateGiven(dateWithWeekToTest.getTime());
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetFirstDateOfWeekIfMiddleOfWeek() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7, 2, 12, 6);
        Date d1 = cal.getTime();
        Date expectedResult = new GregorianCalendar(2018, 10, 4, 0, 0, 0).getTime();
        //Act
        Date actualResult = rl1.getFirstDateOfWeekFromDateGiven(d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetFirstDateOfWeekIfDateGivenIsAlreadyFirstDateOfWeek() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 4, 0, 0, 0);
        Date d1 = cal.getTime();
        Date expectedResult = new GregorianCalendar(2018, 10, 4, 0, 0, 0).getTime();
        //Act
        Date actualResult = rl1.getFirstDateOfWeekFromDateGiven(d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetFirstDateOfWeekIfDateGivenIsLastDateOfWeek() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 9, 0, 0, 0);
        Date d1 = cal.getTime();
        Date expectedResult = new GregorianCalendar(2018, 10, 4, 0, 0, 0).getTime();
        //Act
        Date actualResult = rl1.getFirstDateOfWeekFromDateGiven(d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAverageOfMinusDifferentWeek() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 24, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 22, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(31, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        //Act
        double expectedResult = 18.67;
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 23);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMinimumValuesInTheReadingsOfWeek(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.01);
    }


    @Test
    void seeIfGetAverageOfMaxDifferentWeek() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 24, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 22, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(31, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        //Act
        double expectedResult = 21.34;
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 23);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfMaximumValuesInTheReadingsOfWeek(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void seeIfListNullThrowsException() {
        ReadingList rList = new ReadingList();
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            rList.checkIfListValid(null);
        });
        //Assert
        assertEquals("List is not valid", exception.getMessage());
    }

    @Test
    void seeIfListEmptyThrowsException() {
        //Arrange
        ReadingList rList = new ReadingList();
        List<Double> valuesOfDay = new ArrayList<>();

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            rList.checkIfListValid(valuesOfDay);
        });
        //Assert
        assertEquals("List is not valid", exception.getMessage());
    }

    @Test
    void seeIfTrueIfListIsValid() {
        //Arrange
        ReadingList rList = new ReadingList();
        List<Double> valuesOfDay = new ArrayList<>();
        valuesOfDay.add(31.0);

        //Act
        boolean actualResult = rList.checkIfListValid(valuesOfDay);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetMaximumValueOnGivenDateWorks() {
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 24, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 22, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(5, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(5, g4.getTime());
        Reading r5 = new Reading(6, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        //Act
        double expectedResult = 6;
        double actualResult = rList.getMaximumOfGivenDayValueReadings(g5.getTime());
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }


    @Test
    void seeIfGetMaximumValueOnGivenDateWorksTestCalendar() {
        ReadingList list = new ReadingList();
        Date d1 = new GregorianCalendar(2015, GregorianCalendar.DECEMBER, 2, 4, 20, 1).getTime();
        Date d2 = new GregorianCalendar(2015, GregorianCalendar.DECEMBER, 2, 23, 59, 59).getTime();
        Reading r1 = new Reading(15, d1);
        Reading r2 = new Reading(30, d2);
        list.addReading(r1);
        list.addReading(r2);
        double result = list.getMaximumOfGivenDayValueReadings(d1);
        double expectedResult = 30.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetMaximumValueOnGivenDateWorksTestCalendarLowerLimit() {
        ReadingList list = new ReadingList();
        Date d1 = new GregorianCalendar(2015, GregorianCalendar.DECEMBER, 1, 23, 59, 59).getTime();
        Date d2 = new GregorianCalendar(2015, GregorianCalendar.DECEMBER, 2, 23, 59, 59).getTime();
        Reading r1 = new Reading(31, d1);
        Reading r2 = new Reading(30, d2);
        list.addReading(r1);
        list.addReading(r2);
        double result = list.getMaximumOfGivenDayValueReadings(d2);
        double expectedResult = 31.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetMaximumValueOnGivenDateWorksTestCalendarHigherLimit() {
        ReadingList list = new ReadingList();
        Date d1 = new GregorianCalendar(2015, GregorianCalendar.DECEMBER, 3, 0, 0, 0).getTime();
        Date d2 = new GregorianCalendar(2015, GregorianCalendar.DECEMBER, 2, 23, 59, 59).getTime();
        Reading r1 = new Reading(33, d1);
        Reading r2 = new Reading(30, d2);
        list.addReading(r1);
        list.addReading(r2);
        double result = list.getMaximumOfGivenDayValueReadings(d2);
        double expectedResult = 33.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetMaximumValueOnGivenDateWorksWithNegatives() {
        ReadingList list = new ReadingList();
        Date d1 = new GregorianCalendar(2015, 11, 2).getTime();
        Reading r1 = new Reading(-15, d1);
        Reading r2 = new Reading(-30, d1);
        list.addReading(r1);
        list.addReading(r2);
        double result = list.getMaximumOfGivenDayValueReadings(d1);
        double expectedResult = -15.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetMaximumValueOnGivenDateWorksWithDifDays() {
        ReadingList list = new ReadingList();
        Date d1 = new GregorianCalendar(2015, 11, 2).getTime();
        Date d2 = new GregorianCalendar(2015, 11, 3).getTime();
        Reading r1 = new Reading(15, d1);
        Reading r2 = new Reading(30, d2);
        list.addReading(r1);
        list.addReading(r2);
        double result = list.getMaximumOfGivenDayValueReadings(d1);
        double expectedResult = 15.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetMostRecentValueOfReadingWorks() {
        ReadingList list = new ReadingList();
        Date d1 = new GregorianCalendar(2015, 11, 2).getTime();
        Date d2 = new GregorianCalendar(2015, 11, 3).getTime();
        Reading r1 = new Reading(15, d1);
        Reading r2 = new Reading(30, d2);
        list.addReading(r1);
        list.addReading(r2);
        double result = list.getMostRecentValueOfReading();
        double expectedResult = 30.0;
        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    void seeIfGetMostRecentValueOfReadingWorksSameDay() {
        ReadingList list = new ReadingList();
        Date d1 = new GregorianCalendar(2015, 11, 2, 20, 0).getTime();
        Date d2 = new GregorianCalendar(2015, 11, 2, 5, 0).getTime();
        Reading r1 = new Reading(15, d1);
        Reading r2 = new Reading(30, d2);
        list.addReading(r1);
        list.addReading(r2);
        double result = list.getMostRecentValueOfReading();
        double expectedResult = 15.0;
        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    void seeIfGetSumOfGivenDayValueReadingsWhenReadigsInLimitsOfDay() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 24, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 22, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(-2, g0.getTime());
        Reading r1 = new Reading(10, g1.getTime());
        Reading r2 = new Reading(5, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(5, g4.getTime());
        Reading r5 = new Reading(5, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        //Act
        double expectedResult = 15;
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 23, 1, 1, 1);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getTotalSumOfGivenDayValueReadings(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAverageOfReadingsBetweenTwoGivenDates() {
        //Arrange
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 7, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 8, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 9, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 10, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 13, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        double expectedResult = 24;
        //Act
        GregorianCalendar dateMin = new GregorianCalendar(2018, 10, 7);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 10, 13);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        double actualResult = rList.getAverageReadingsBetweenTwoDates(dateToTest1, dateToTest2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureReadingListIsEmpty() {
        //Arrange
        ReadingList rl = new ReadingList();
        boolean expectedResult = true;
        //Act
        boolean actualResult = rl.isEmpty();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureReadingListIsNotEmpty() {
        //Arrange
        ReadingList rl = new ReadingList();
        Reading reading = new Reading(20,new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        rl.addReading(reading);
        boolean expectedResult = false;
        //Act
        boolean actualResult = rl.isEmpty();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDay() {
        //Arrange
        ReadingList rl = new ReadingList();
        Reading reading = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading2 = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading3 = new Reading(20, new GregorianCalendar(2018, 10, 4).getTime());
        Reading reading4 = new Reading(20, new GregorianCalendar(2018, 10, 2).getTime());
        rl.addReading(reading);
        rl.addReading(reading2);
        rl.addReading(reading3);
        rl.addReading(reading4);
        double expectedResult = 40;
        //Act
        double actualResult = rl.getTotalValueOfReadingOnGivenDay(new GregorianCalendar(2018, 10, 3).getTime());
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetNaNInGetTotalValueFromGivenList() {
        //Arrange
        ReadingList rl = new ReadingList();
        //Act
        double actualResult = rl.getTotalValueOfReadingOnGivenDay(new GregorianCalendar(2018, 10, 3).getTime());
        //Assert
        assertEquals(NaN, actualResult);
    }
}