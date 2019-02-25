package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
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

    @Test
    void seeAddReadingIfListIsEmpty() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar calendar = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(17, calendar.getTime());

        //Act
        boolean actualResult = readingList.addReading(reading1);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetTotalFromGivenList() {
        //Arrange
        ReadingList readingList = new ReadingList();
        List<Double> list = new ArrayList<>();
        list.add(1.0);
        list.add(2.0);

        //Act
        double actualResult = readingList.getListSum(list);

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

        //Act
        readingList.addReading(reading1);
        boolean actualResult = readingList.addReading(reading2);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeAddReadingIfListHasSameReading() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar calendar = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(17, calendar.getTime());
        Reading reading2 = new Reading(17, calendar.getTime());

        //Act
        readingList.addReading(reading1);
        boolean actualResult = readingList.addReading(reading2);

        //Assert
        assertFalse(actualResult);
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
        double actualResult = readingList.get(0).getValue();

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
        double actualResult = readingList.get(1).getValue();

        //Assert
        assertEquals(expectedResult, actualResult, 0.1);
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
        List<Date> expectedResult = new ArrayList<>();
        expectedResult.add(new GregorianCalendar(2018, 10, 7, 0, 0, 0).getTime());
        expectedResult.add(new GregorianCalendar(2018, 10, 8, 23, 26, 21).getTime());
        expectedResult.add(new GregorianCalendar(2018, 10, 9, 8, 21, 22).getTime());
        expectedResult.add(new GregorianCalendar(2018, 10, 10, 18, 14, 3).getTime());
        expectedResult.add(new GregorianCalendar(2018, 10, 13, 12, 12, 12).getTime());
        //Act
        GregorianCalendar dateMin = new GregorianCalendar(2018, 10, 7);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 10, 13);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        List<Date> actualResult = rList.getDaysWithReadingsBetweenDates(dateToTest1, dateToTest2);
        //Assert
        assertEquals(expectedResult, actualResult);
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
        actualResult = rList.getAvgFromList(doubleList);
        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfAverageIsMinusOneWhenListIsEmpty() {
        //Arrange
        List<Double> doubleList = new ArrayList<>();
        ReadingList rList = new ReadingList();
        double actualResult;
        //Act
        actualResult = rList.getAvgFromList(doubleList);
        //Assert
        assertEquals(0, actualResult, 0.001);
    }

    @Test
    void seeIfListNullThrowsException() {
        ReadingList rList = new ReadingList();
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> rList.checkIfListValid(null));
        //Assert
        assertEquals("List is not valid", exception.getMessage());
    }

    @Test
    void seeIfListEmptyThrowsException() {
        //Arrange
        ReadingList rList = new ReadingList();
        List<Double> valuesOfDay = new ArrayList<>();

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> rList.checkIfListValid(valuesOfDay));
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
    void seeIfGetMostRecentValueOfReadingWorks() {
        ReadingList list = new ReadingList();
        Date d1 = new GregorianCalendar(2015, 11, 2).getTime();
        Date d2 = new GregorianCalendar(2015, 11, 3).getTime();
        Reading r1 = new Reading(15, d1);
        Reading r2 = new Reading(30, d2);
        list.addReading(r1);
        list.addReading(r2);
        double result = list.getMostRecentValue();
        double expectedResult = 30.0;
        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    void seeMostRecentValueWithEmptyList() {
        ReadingList list = new ReadingList();
        //ACT

        assertThrows(IllegalArgumentException.class, list::getMostRecentValue);
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
        double result = list.getMostRecentValue();
        double expectedResult = 15.0;
        assertEquals(expectedResult, result, 0.01);

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
        double expectedResult = 24.0;
        //Act
        GregorianCalendar dateMin = new GregorianCalendar(2018, 10, 7);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 10, 13);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        double actualResult = rList.getAverageReadingsBetweenDates(dateToTest1, dateToTest2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAverageReadingsBetweenDatesExceptionTest() {

        //Arrange
        ReadingList rList = new ReadingList();
        //Act
        GregorianCalendar dateMin = new GregorianCalendar(2018, 10, 7);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 10, 13);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            rList.getAverageReadingsBetweenDates(dateToTest1, dateToTest2);
        });
    }

    @Test
    void ensureReadingListIsEmpty() {
        //Arrange
        ReadingList rl = new ReadingList();
        //Act
        boolean actualResult = rl.isEmpty();
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void ensureReadingListIsNotEmpty() {
        //Arrange
        ReadingList rl = new ReadingList();
        Reading reading = new Reading(20, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        rl.addReading(reading);
        //Act
        boolean actualResult = rl.isEmpty();
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDay() {
        //Arrange
        ReadingList rl = new ReadingList();
        Reading reading = new Reading(20, new GregorianCalendar(2018, 10, 3, 0, 0, 0).getTime());
        Reading reading2 = new Reading(20, new GregorianCalendar(2018, 10, 3, 12, 0, 0).getTime());
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
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            rl.getTotalValueOfReadingOnGivenDay(new GregorianCalendar(2018, 10, 3).getTime());
        });
        //Assert
        assertEquals("Warning: Total value was not calculated - no readings were available.", exception.getMessage());
    }

    @Test
    void getMostRecentReading() {
        ReadingList readingList1 = new ReadingList(); //MOST RECENT FIRST
        ReadingList readingList2 = new ReadingList(); //MOST RECENT SECOND
        ReadingList readingList3 = new ReadingList(); //MOST RECENT THIRD
        ReadingList readingList4 = new ReadingList(); //TWO READINGS WITH SAME DATE
        ReadingList readingList5 = new ReadingList(); //NO READINGS

        Reading reading1 = new Reading(22, new GregorianCalendar(2018, 10, 2, 23, 59).getTime());
        Reading reading2 = new Reading(25, new GregorianCalendar(2018, 10, 3, 0, 0).getTime());
        Reading reading3 = new Reading(27, new GregorianCalendar(2018, 10, 3, 0, 1).getTime());

        readingList1.addReading(reading3);
        readingList1.addReading(reading1);
        readingList1.addReading(reading2);

        readingList2.addReading(reading2);
        readingList2.addReading(reading3);
        readingList2.addReading(reading1);

        readingList3.addReading(reading2);
        readingList3.addReading(reading1);
        readingList3.addReading(reading3);

        readingList4.addReading(reading3);
        readingList4.addReading(reading1);
        readingList4.addReading(reading3);

        Reading expectedResult2 = new Reading(NaN, new GregorianCalendar(1900, 0, 1).getTime());

        //Act
        Reading actualResult1 = readingList1.getMostRecentReading();
        Reading actualResult2 = readingList2.getMostRecentReading();
        Reading actualResult3 = readingList3.getMostRecentReading();
        Reading actualResult4 = readingList4.getMostRecentReading();
        Reading actualResult5 = readingList5.getMostRecentReading();
        //Assert
        assertEquals(reading3, actualResult1);
        assertEquals(reading3, actualResult2);
        assertEquals(reading3, actualResult3);
        assertEquals(reading3, actualResult4);
        assertEquals(expectedResult2, actualResult5);
    }

    @Test
    void equals() {
        ReadingList readingList1 = new ReadingList();
        ReadingList readingList2 = new ReadingList();
        ReadingList readingList3 = new ReadingList();

        Reading reading1 = new Reading(22, new GregorianCalendar(2018, 10, 2, 23, 59).getTime());
        Reading reading2 = new Reading(25, new GregorianCalendar(2018, 10, 3, 0, 0).getTime());

        readingList1.addReading(reading1);
        readingList1.addReading(reading2);

        readingList2.addReading(reading1);
        readingList2.addReading(reading2);

        readingList3.addReading(reading2);
        readingList3.addReading(reading1);
        //Act
        boolean actualResult1 = readingList1.equals(readingList2);
        boolean actualResult2 = readingList1.equals(readingList1);
        boolean actualResult3 = readingList1.equals(readingList3);
        boolean actualResult4 = readingList1.equals(2D);
        //Assert
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void hashcode() {
        ReadingList readingList1 = new ReadingList();
        Reading reading1 = new Reading(22, new GregorianCalendar(2018, 10, 2, 23, 59).getTime());
        readingList1.addReading(reading1);
        //Act
        int actualResult1 = readingList1.hashCode();
        //Assert
        assertEquals(actualResult1, 1);
    }

    @Test
    void matchByDate() {
        //Assert

        ReadingList readingList1 = new ReadingList(); //EMPTY LIST
        ReadingList readingList2 = new ReadingList(); //LIST WITH READINGS IN BOUNDARY LIMITS
        ReadingList readingList3 = new ReadingList(); //LIST WITH READINGS INSIDE LIMITS
        ReadingList readingList4 = new ReadingList(); //LIST WITH READINGS OUTSIDE LIMITS

        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();
        Date date4 = new Date();
        Date date5 = new Date();
        Date date6 = new Date();

        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            date1 = sd.parse("05/10/2000 23:59");
            date2 = sd.parse("06/10/2000 00:00");
            date3 = sd.parse("06/10/2000 12:30");
            date4 = sd.parse("06/10/2000 23:59");
            date5 = sd.parse("07/10/2000 00:00");
            date6 = sd.parse("19/11/2001 11:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Reading reading1 = new Reading(20, date1);
        Reading reading2 = new Reading(25, date2);
        Reading reading3 = new Reading(2, date3);
        Reading reading4 = new Reading(12, date4);
        Reading reading5 = new Reading(1, date5);
        Reading reading6 = new Reading(-1, date6);

        readingList2.addReading(reading1);
        readingList2.addReading(reading2);
        readingList2.addReading(reading4);
        readingList2.addReading(reading5);

        readingList3.addReading(reading3);

        readingList4.addReading(reading6);

        ReadingList expectedResult1 = new ReadingList(); //EMPTY
        ReadingList expectedResult2 = new ReadingList();
        ReadingList expectedResult3 = new ReadingList();

        expectedResult2.addReading(reading2);
        expectedResult2.addReading(reading4);
        expectedResult3.addReading(reading3);

        //Act

        ReadingList actualResult1 = readingList1.matchByDate(date1, date5);
        ReadingList actualResult2 = readingList2.matchByDate(date1, date5);
        ReadingList actualResult3 = readingList3.matchByDate(date1, date5);
        ReadingList actualResult4 = readingList4.matchByDate(date1, date5);

        //Assert

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
        assertEquals(expectedResult1, actualResult4);
    }

    @Test
    void getFirstSecondOfDaySuccess() {
        GregorianCalendar cal = new GregorianCalendar(2018, Calendar.OCTOBER, 2, 23, 59);
        Date expectedResult = new GregorianCalendar(2018, Calendar.OCTOBER, 2, 00, 00).getTime();
        Date date = cal.getTime();
        ReadingList readingList1 = new ReadingList();
        assertEquals(expectedResult, readingList1.getFirstSecondOfDay(date));
    }

    @Test
    void isReadingDateBetweenTwoDatesTrue() {
        ReadingList readingList1 = new ReadingList();
        Reading reading1 = new Reading(22, new GregorianCalendar(2018, Calendar.OCTOBER, 2, 23, 59).getTime());
        Date startDate = new GregorianCalendar(2018, Calendar.OCTOBER, 1, 23, 59).getTime();
        Date endDate = new GregorianCalendar(2019, Calendar.OCTOBER, 1, 23, 59).getTime();
        readingList1.addReading(reading1);
        assertTrue(readingList1.isReadingDateBetweenTwoDates(reading1.getDate(), startDate, endDate));
    }

    @Test
    void isReadingDateBetweenTwoDatesFalse() {
        ReadingList readingList1 = new ReadingList();
        Reading reading1 = new Reading(22, new GregorianCalendar(2018, Calendar.OCTOBER, 2, 23, 59).getTime());
        Date startDate = new GregorianCalendar(2016, Calendar.OCTOBER, 1, 23, 59).getTime();
        Date endDate = new GregorianCalendar(2017, Calendar.OCTOBER, 1, 23, 59).getTime();
        readingList1.addReading(reading1);
        assertFalse(readingList1.isReadingDateBetweenTwoDates(reading1.getDate(), startDate, endDate));
    }
}