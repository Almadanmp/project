package Sprint0_Test.ModelTest;


import Sprint0.Model.Reading;
import Sprint0.Model.ReadingList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingListTest {

    @Test
    public void seeAddReadingIfListIsEmpty() {
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
    public void seeAddReadingIfListHasDifferentReading() {
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
    public void seeAddReadingIfListHasSameReading() {
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
    public void seeThatWeAddAReading1ToAList() {
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
    public void seeThatWeAddAReading2ToAList() {
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
    public void seeThatWeGetAValueFromAReading1InsideAList() {
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
    public void seeThatWeGetAValueFromAReading2InsideAList() {
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
    public void seeThatAReadingListDoesNotContainAReading() {
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
    public void seeThatAReadingDoesNotAddBecauseItIsAlreadyContained() {
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
    public void seeIfGetMostRecentReading() {

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
    public void seeIfGetMostRecentReadingSwitchedDates() {
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
    public void seeGetMeanOfMonth() {
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 01, 0, 0, 0);
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
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfAllRecordedValuesFromAGivenMonth(dateToTest);
        assertEquals(expectedResult, actualResult, 0.1);
    }

    @Test
    public void seeIfMeanOfMonthWithAnEmptyArrayReturnsZero() {
        //Arrange
        ReadingList rList = new ReadingList();
        //Act
        double expectedResult = 0;
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = cal.getTime();
        double actualResult = rList.getAverageOfAllRecordedValuesFromAGivenMonth(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetDatesOfTheMonthWithReadings1() {
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 01, 00, 00, 00);
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
        expectedResult.add(g1.getTime());
        expectedResult.add(g2.getTime());
        expectedResult.add(g3.getTime());
        expectedResult.add(g4.getTime());
        expectedResult.add(g5.getTime());
        expectedResult.add(g6.getTime());
        expectedResult.add(g7.getTime());
        GregorianCalendar dateWithMonthToTest = new GregorianCalendar(2018,10,11);
        List<Date> actualResult = rList.getDatesOfMonthWithReadings(dateWithMonthToTest.getTime());
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetFirstDateOfMonth() {
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date d1 = cal.getTime();
        Date expectedResult = (new GregorianCalendar(2018, 10, 1)).getTime();
        Date actualResult = rl1.getFirstDateOfMonthFromGivenDate(d1);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfGetLastDayOfMonth() {
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 11, 7);
        Date d1 = cal.getTime();
        Date expectedResult = (new GregorianCalendar(2018, 11, 31)).getTime();
        Date actualResult = rl1.getLastDateOfMonthFromGivenDate(d1);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void seeIfGetMinimumValuesInReadingsOfMonth() {
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
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 01, 00, 00, 00);
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
        double result = rList.getAverageOfMinimumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void seeIfRemovesReadingsFromDifferentMonthAndYear() {
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
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 01, 00, 00, 00);
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
    public void seeIfGetDaysOfTheMonthWithReadings() {
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 01, 00, 00, 00);
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
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = cal.getTime();
        List<Integer> result = rList.getDaysOfMonthWithReadings(dateToTest);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetValuesFromDayOfMonthWithReadings() {
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 3, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 3, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 3, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 11, 01, 00, 00, 00);
        Reading r0 = new Reading(21, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(22, g4.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        List<Double> expectedResult = new ArrayList<>();
        double v1 = 23;
        double v2 = 24;
        double v3 = 25;
        expectedResult.add(v1);
        expectedResult.add(v2);
        expectedResult.add(v3);
        List<Double> result = rList.getValueReadingsThatMatchDayWithinMonth(3);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetLowestValueFromEndOfList() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        double value1 = 2;
        double value2 = 2.5;
        double value3 = 5;
        double value4 = -2;
        double expectedResult = -2;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        valuesOfDay.add(value1);
        valuesOfDay.add(value2);
        valuesOfDay.add(value3);
        valuesOfDay.add(value4);
        actualResult = rl1.getLowestValueInList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetLowestValueFromListIfValuesEqual() {
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
        actualResult = rl1.getLowestValueInList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetLowestValueFromMiddleOfList() {
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
        actualResult = rl1.getLowestValueInList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetLowestValueFromBeginningOfList() {
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
        actualResult = rl1.getLowestValueInList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetLowestValueEmpty() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        double expectedResult = 0;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        actualResult = rl1.getLowestValueInList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetLowestValueNull() {
        //Arrange
        double expectedResult = 0;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        actualResult = rl1.getLowestValueInList(null);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetsAverageFromList() {
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
    public void seeIfGetMaximumValuesMonth() {
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
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 01, 00, 00, 00);
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
        double result = rList.getAverageOfMaximumValuesInTheReadingsOfMonth(dateToTest);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void seeIfGetHighestValueFromEndOfList() {
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
    public void seeIfGetHighestValueFromMiddleOfList() {
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
    public void seeIfGetHighestValueEmpty() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        double expectedResult = 0;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        actualResult = rl1.getHighestValueInList(valuesOfDay);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetHighestValueNull() {
        //Arrange
        double expectedResult = 0;
        double actualResult;
        ReadingList rl1 = new ReadingList();

        //Act
        actualResult = rl1.getHighestValueInList(null);

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetHighestValueFromBeginningOfList() {
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
    public void seeIfGetMeanOfTheDay() {
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 1, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 01, 00, 00, 00);
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
        double actualResult = rList.getMeanOfGivenDay(dateToTest);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetDaysOfTheWeekWithReadings() {
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);//
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);//
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);//
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 01, 00, 00, 00);
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
        expectedResult.add(g3.getTime());
        expectedResult.add(g6.getTime());
        expectedResult.add(g7.getTime());
        expectedResult.add(g8.getTime());
        GregorianCalendar dateWithMonthToTest = new GregorianCalendar(2018,10,27);
        List<Date> result = rList.getDaysOfWeekWithReadings(dateWithMonthToTest.getTime());
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGet1stDayOfWeek() {
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date d1 = cal.getTime();
        Date expectedResult = new GregorianCalendar(2018, 10, 4).getTime();
        Date result = rl1.getFirstDayOfWeekFromGivenDay(d1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGet1stDayOfWeekIfSunday() {
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 4);
        Date d1 = cal.getTime();
        Date expectedResult = new GregorianCalendar(2018, 10, 4).getTime();
        Date result = rl1.getFirstDayOfWeekFromGivenDay(d1);
        assertEquals(expectedResult, result);
    }
}