package Sprint_0;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingListTest {

    @Test
    public void ensureThatWeAddAReading1ToAList() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(17, new GregorianCalendar(118, 11, 25));
        Reading reading2 = new Reading(29, new GregorianCalendar(118, 9, 3));
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        boolean expectedResult = true;

        //Act
        boolean result = readingList.containsReading(reading1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureThatWeAddAReading2ToAList() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(15, new GregorianCalendar(118, 11, 25));
        Reading reading2 = new Reading(2, new GregorianCalendar(118, 9, 3));
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        boolean expectedResult = true;

        //Act
        boolean result = readingList.containsReading(reading2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureThatWeGetAValueFromAReading1InsideAList() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(15, new GregorianCalendar(118, 11, 25));
        Reading reading2 = new Reading(29, new GregorianCalendar(118, 9, 3));
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        double expectedResult = 15;

        //Act
        double result = readingList.getListOfReadings().get(0).getmValue();

        //Assert
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    public void ensureThatWeGetAValueFromAReading2InsideAList() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(15, new GregorianCalendar(118, 11, 25));
        Reading reading2 = new Reading(29, new GregorianCalendar(118, 9, 3));
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        double expectedResult = 29;

        //Act
        double result = readingList.getListOfReadings().get(1).getmValue();

        //Assert
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    public void ensureThatAReadingListDoesNotContainAReading() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(15, new GregorianCalendar(118, 11, 25));
        Reading reading2 = new Reading(29, new GregorianCalendar(118, 9, 3));
        readingList.addReading(reading1);
        boolean expectedResult = false;

        //Act
        boolean result = readingList.containsReading(reading2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureThatAReadingDoesNotAddBecauseItIsAlreadyContained() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(15, new GregorianCalendar(118, 11, 25));
        Reading reading2 = new Reading(29, new GregorianCalendar(118, 9, 3));
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        readingList.addReading(reading2);
        boolean expectedResult = true;

        //Act
        boolean result = readingList.containsReading(reading2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGetMostRecentReading() {

        //Arrange
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(15, new GregorianCalendar(118, 11, 25));
        Reading reading2 = new Reading(29, new GregorianCalendar(118, 9, 3));
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        Reading expectedResult = new Reading(15, new GregorianCalendar(118, 11, 25));
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
        Reading reading2 = new Reading(16, new GregorianCalendar(118, 11, 25));
        Reading reading1 = new Reading(29, new GregorianCalendar(118, 9, 3));
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        Reading expectedResult = new Reading(16, new GregorianCalendar(118, 11, 25));
        Reading actualResult;

        //Act
        actualResult = readingList.getMostRecentReading();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfMeanOfTheDay() {
        ReadingList rl = new ReadingList();
        Reading r1 = new Reading(23, new GregorianCalendar(2018, 11, 23, 21, 25, 8));
        Reading r2 = new Reading(24, new GregorianCalendar(2018, 11, 23, 23, 26, 21));
        Reading r3 = new Reading(25, new GregorianCalendar(2018, 11, 23, 8, 21, 22));
        Reading r4 = new Reading(26, new GregorianCalendar(2018, 11, 23, 18, 14, 3));
        Reading r5 = new Reading(23, new GregorianCalendar(2018, 11, 23, 12, 14, 23));
        Reading r6 = new Reading(22, new GregorianCalendar(2018, 11, 23, 12, 12, 12));
        Reading r7 = new Reading(23, new GregorianCalendar(2018, 11, 23, 13, 12, 33));
        rl.addReading(r1);
        rl.addReading(r2);
        rl.addReading(r3);
        rl.addReading(r4);
        rl.addReading(r5);
        rl.addReading(r6);
        rl.addReading(r7);
        double expectedResult = 23.71;
        double result = rl.meanOftheDay(2018, 11, 23);
        assertEquals(expectedResult, result, 0.1);

    }

    @Test
    public void seeIfMeanOfTheDayDifferentDays() {
        ReadingList rl = new ReadingList();
        Reading r1 = new Reading(23, new GregorianCalendar(2018, 11, 23, 21, 25, 8));
        Reading r2 = new Reading(24, new GregorianCalendar(2018, 11, 23, 23, 26, 21));
        Reading r3 = new Reading(25, new GregorianCalendar(2018, 11, 27, 8, 21, 22));
        Reading r4 = new Reading(26, new GregorianCalendar(2018, 11, 23, 18, 14, 3));
        Reading r5 = new Reading(23, new GregorianCalendar(2018, 11, 23, 12, 14, 23));
        Reading r6 = new Reading(22, new GregorianCalendar(2018, 11, 28, 12, 12, 12));
        Reading r7 = new Reading(23, new GregorianCalendar(2018, 11, 23, 13, 12, 33));
        rl.addReading(r1);
        rl.addReading(r2);
        rl.addReading(r3);
        rl.addReading(r4);
        rl.addReading(r5);
        rl.addReading(r6);
        rl.addReading(r7);
        double expectedResult = 23.8;
        double result = rl.meanOftheDay(2018, 11, 23);
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    public void seeIfGetDaysOfTheMonthWithReadings() {
        ReadingList rList = new ReadingList();
        Reading r0 = new Reading(23, new GregorianCalendar(2018, 9, 31, 23, 59, 59));
        Reading r1 = new Reading(23, new GregorianCalendar(2018, 10, 1, 0, 0, 0));
        Reading r2 = new Reading(24, new GregorianCalendar(2018, 10, 23, 23, 26, 21));
        Reading r3 = new Reading(25, new GregorianCalendar(2018, 10, 27, 8, 21, 22));
        Reading r4 = new Reading(26, new GregorianCalendar(2018, 10, 23, 18, 14, 3));
        Reading r5 = new Reading(23, new GregorianCalendar(2018, 10, 23, 12, 14, 23));
        Reading r6 = new Reading(22, new GregorianCalendar(2018, 10, 28, 12, 12, 12));
        Reading r7 = new Reading(23, new GregorianCalendar(2018, 10, 30, 23, 59, 59));
        Reading r8 = new Reading(22, new GregorianCalendar(2018, 11, 01, 00, 00, 00));
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        ArrayList<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(1);
        expectedResult.add(23);
        expectedResult.add(27);
        expectedResult.add(28);
        expectedResult.add(30);
        ArrayList<Integer> result = rList.getDaysOfMonthWithReadings(2018, 10);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeGetMeanOfMonth() {
        ReadingList rList = new ReadingList();
        Reading r0 = new Reading(23, new GregorianCalendar(2018, 9, 31, 23, 59, 59));
        Reading r1 = new Reading(23, new GregorianCalendar(2018, 10, 1, 0, 0, 0));
        Reading r2 = new Reading(24, new GregorianCalendar(2018, 10, 23, 23, 26, 21));
        Reading r3 = new Reading(25, new GregorianCalendar(2018, 10, 27, 8, 21, 22));
        Reading r4 = new Reading(26, new GregorianCalendar(2018, 10, 23, 18, 14, 3));
        Reading r5 = new Reading(23, new GregorianCalendar(2018, 10, 23, 12, 14, 23));
        Reading r6 = new Reading(22, new GregorianCalendar(2018, 10, 28, 12, 12, 12));
        Reading r7 = new Reading(23, new GregorianCalendar(2017, 10, 30, 23, 59, 59));
        Reading r8 = new Reading(22, new GregorianCalendar(2018, 11, 01, 00, 00, 00));
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        double expectedResult = 23.5;
        double result = rList.meanOfMonth(2018, 10);
        assertEquals(expectedResult, result, 0.1);
    }
}
