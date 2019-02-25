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

    private SimpleDateFormat validSdf; // SimpleDateFormat dd/MM/yyyy
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018
    private Date validDate3 = new Date(); // 31/09/2018 23:59:59
    private Date validDate4 = new Date(); // 07/10/2018 00:00:00
    private Date validDate5 = new Date(); // 08/10/2018 23:26:21
    private Date validDate6 = new Date(); // 09/10/2018 08:21:22
    private Date validDate7 = new Date(); // 10/10/2018 18:14:03
    private Date validDate8 = new Date(); // 23/10/2018 12:14:23
    private Date validDate9 = new Date(); // 13/10/2018 12:12:12
    private Date validDate10 = new Date(); // 30/10/2018 23:59:59
    private Date validDate11 = new Date(); // 01/11/2018 00:00:00
    private Date validDate12; //02/11/2015
    private Date dateToTest2 = new Date(); // 13/10/2018 23:59:59
    private Date validDate13;
    private Date validDate14;
    private Date validDate15;

    @BeforeEach
    void arrangeDateFormat() {
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
            dateToTest2 = validSdf.parse("13/10/2018 23:59:59");
            validDate13 = validSdfDay.parse("03/10/2018");
            validDate14 = validSdf.parse("02/10/2018 23:59:00");
            validDate15 = validSdf.parse("03/10/2018 00:00:00");


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    void seeAddReadingIfListIsEmpty() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(17, validDate1);

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
        Reading reading1 = new Reading(17, validDate1);
        Reading reading2 = new Reading(29, validDate2);

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
        Reading reading1 = new Reading(17, validDate1);
        Reading reading2 = new Reading(17, validDate1);

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
        Reading reading1 = new Reading(15, validDate1);
        Reading reading2 = new Reading(29, validDate2);
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
        Reading reading1 = new Reading(15, validDate1);
        Reading reading2 = new Reading(29, validDate2);
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

        Reading r0 = new Reading(23, validDate3);
        Reading r1 = new Reading(23, validDate4);
        Reading r2 = new Reading(24, validDate5);
        Reading r3 = new Reading(25, validDate6);
        Reading r4 = new Reading(26, validDate7);
        Reading r5 = new Reading(23, validDate8);
        Reading r6 = new Reading(22, validDate9);
        Reading r7 = new Reading(23, validDate10);
        Reading r8 = new Reading(22, validDate11);
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
        expectedResult.add(validDate4);
        expectedResult.add(validDate5);
        expectedResult.add(validDate6);
        expectedResult.add(validDate7);
        expectedResult.add(validDate9);
        //Act
        List<Date> actualResult = rList.getDaysWithReadingsBetweenDates(validDate4, dateToTest2);
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
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            d2 = sd.parse("03/11/2018 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading r1 = new Reading(15, validDate12);
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
        Reading r1 = new Reading(15, validDate12);
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            d2 = sd.parse("02/11/2015 05:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

        Reading r0 = new Reading(23, validDate3);
        Reading r1 = new Reading(23, validDate4);
        Reading r2 = new Reading(24, validDate5);
        Reading r3 = new Reading(25, validDate6);
        Reading r4 = new Reading(26, validDate7);
        Reading r5 = new Reading(23, validDate8);
        Reading r6 = new Reading(22, validDate9);
        Reading r7 = new Reading(23, validDate10);
        Reading r8 = new Reading(22, validDate11);
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
        double actualResult = rList.getAverageReadingsBetweenDates(validDate4, dateToTest2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAverageReadingsBetweenDatesExceptionTest() {

        //Arrange
        ReadingList rList = new ReadingList();
        //Act
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            rList.getAverageReadingsBetweenDates(validDate4, dateToTest2);
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

        Date date1 = new Date();

        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = sd.parse("30/12/2018");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading reading = new Reading(20, date1);
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

        Date date2 = new Date();
        Date date3 = new Date();

        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            date2 = sd.parse("03/10/2018 12:00:00");
            date3 = sd.parse("04/10/2018 12:30:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading reading = new Reading(20, validDate15);
        Reading reading2 = new Reading(20, date2);
        Reading reading3 = new Reading(20, date3);
        Reading reading4 = new Reading(20, validDate14);
        rl.addReading(reading);
        rl.addReading(reading2);
        rl.addReading(reading3);
        rl.addReading(reading4);
        double expectedResult = 40;
        //Act
        double actualResult = rl.getTotalValueOfReadingOnGivenDay(validDate13);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetNaNInGetTotalValueFromGivenList() {
        //Arrange
        ReadingList rl = new ReadingList();
        //Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            rl.getTotalValueOfReadingOnGivenDay(validDate13);
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
        Date date = new Date();

        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            date = sd.parse("03/10/2018 00:01:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading reading1 = new Reading(22, validDate14);
        Reading reading2 = new Reading(25, validDate15);
        Reading reading3 = new Reading(27, date);
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

        Reading error = new Reading(NaN, new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime());

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
        assertEquals(error, actualResult5);
    }

    @Test
    void equals() {
        ReadingList readingList1 = new ReadingList();
        ReadingList readingList2 = new ReadingList();
        ReadingList readingList3 = new ReadingList();

        Reading reading1 = new Reading(22, validDate14);
        Reading reading2 = new Reading(25, validDate15);
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
        Reading reading1 = new Reading(22, validDate14);
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
        Date expectedResult = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            expectedResult = sdf.parse("02/10/2018 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
            ReadingList readingList1 = new ReadingList();
            assertEquals(expectedResult, readingList1.getFirstSecondOfDay(validDate14));
        }

        @Test
        void isReadingDateBetweenTwoDatesTrue () {
            Date date1 = new Date();
            Date date2 = new Date();

            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try {
                date1 = sd.parse("01/10/2018 23:59");
                date2 = sd.parse("01/10/2019 23:59");

            } catch (ParseException e) {
                e.printStackTrace();
            }
            ReadingList readingList1 = new ReadingList();
            Reading reading1 = new Reading(22, validDate14);
            readingList1.addReading(reading1);
            assertTrue(readingList1.isReadingDateBetweenTwoDates(reading1.getDate(), date1, date2));
        }

        @Test
        void isReadingDateBetweenTwoDatesFalse () {
            Date date1 = new Date();
            Date date2 = new Date();

            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try {
                date1 = sd.parse("01/10/2016 23:59");
                date2 = sd.parse("01/10/2017 23:59");

            } catch (ParseException e) {
                e.printStackTrace();
            }
            ReadingList readingList1 = new ReadingList();
            Reading reading1 = new Reading(22,validDate14);
            readingList1.addReading(reading1);
            assertFalse(readingList1.isReadingDateBetweenTwoDates(reading1.getDate(), date1, date2));
        }
    }