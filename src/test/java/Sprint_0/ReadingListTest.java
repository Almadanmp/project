package Sprint_0;


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
        boolean result = readingList.addReading(reading1);

        //Assert
        assertEquals(expectedResult, result);
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
        boolean result = readingList.addReading(reading2);

        //Assert
        assertEquals(expectedResult, result);
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
        boolean result = readingList.addReading(reading2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureThatWeAddAReading1ToAList() {
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
        boolean result = readingList.getListOfReadings().contains(reading1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureThatWeAddAReading2ToAList() {
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
        boolean result = readingList.getListOfReadings().contains(reading2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureThatWeGetAValueFromAReading1InsideAList() {
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
        double result = readingList.getListOfReadings().get(0).getmValue();

        //Assert
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    public void ensureThatWeGetAValueFromAReading2InsideAList() {
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
        double result = readingList.getListOfReadings().get(1).getmValue();

        //Assert
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    public void ensureThatAReadingListDoesNotContainAReading() {
        //Arrange
        ReadingList readingList = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(118, 11, 25);
        Reading reading1 = new Reading(15, g1.getTime());
        GregorianCalendar g2 = new GregorianCalendar(118, 9, 3);
        Reading reading2 = new Reading(29, g2.getTime());
        readingList.addReading(reading1);
        boolean expectedResult = false;

        //Act
        boolean result = readingList.getListOfReadings().contains(reading2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureThatAReadingDoesNotAddBecauseItIsAlreadyContained() {
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
        boolean result = readingList.getListOfReadings().contains(reading2);

        //Assert
        assertEquals(expectedResult, result);
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
    public void seeIfMeanOfTheDay() {
        ReadingList rl = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(2018, 11, 23, 21, 25, 8);
        GregorianCalendar g2 = new GregorianCalendar(2018, 11, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 11, 23, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 11, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 11, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 11, 23, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 11, 23, 13, 12, 33);
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        rl.addReading(r1);
        rl.addReading(r2);
        rl.addReading(r3);
        rl.addReading(r4);
        rl.addReading(r5);
        rl.addReading(r6);
        rl.addReading(r7);
        double expectedResult = 23.71;
        double result = rl.getMeanOftheDay(2018, 11, 23);
        assertEquals(expectedResult, result, 0.1);

    }

    @Test
    public void seeIfMeanOfTheDayDifferentDays() {
        ReadingList rl = new ReadingList();
        GregorianCalendar g1 = new GregorianCalendar(2018, 11, 23, 21, 25, 8);
        GregorianCalendar g2 = new GregorianCalendar(2018, 11, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 11, 23, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 11, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 11, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 11, 23, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 11, 23, 13, 12, 33);
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        rl.addReading(r1);
        rl.addReading(r2);
        rl.addReading(r3);
        rl.addReading(r4);
        rl.addReading(r5);
        rl.addReading(r6);
        rl.addReading(r7);
        double expectedResult = 23.8;
        double result = rl.getMeanOftheDay(2018, 11, 23);
        assertEquals(expectedResult, result, 0.1);
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
        List<Integer> result = rList.getDaysOfMonthWithReadings(2018, 10);
        assertEquals(expectedResult, result);
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

        double expectedResult = 23.5;
        double result = rList.getMeanOfRecordedValuesMonth(2018, 10);
        assertEquals(expectedResult, result, 0.1);
    }

    //Funçao ainda nao existente, nome é PLACEHOLDER.
    //1- Haverá à partida uma função que cria arraylist com todos os maximos de cada dia
    //2- Outra que cria com todos os minimos
    //3- Uma ultima que faz a média (seria a maneira ideal).
    //4- Será depois preciso implementar para cada tipo de sensor

    //Testa média de temperatura minima por dia ao longo do mês.
    //Ou seja, pega na minima de cada dia e faz a média para o mês. É suposto dar sempre 10.


    //Teste igual mas para máximo, é suposto dar sempre 20.
    @Test
    public void seeIfGetMinimumValuesMonth() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        Reading reading1 = new Reading(10, new GregorianCalendar(118, 11, 1, 0, 0, 0).getTime());
        Reading reading2 = new Reading(15, new GregorianCalendar(118, 11, 1, 1, 0, 0).getTime());
        Reading reading3 = new Reading(20, new GregorianCalendar(118, 11, 1, 2, 0, 0).getTime());
        Reading reading4 = new Reading(10, new GregorianCalendar(118, 11, 5, 1, 0, 0).getTime());
        Reading reading5 = new Reading(20, new GregorianCalendar(118, 11, 5, 2, 0, 0).getTime());
        Reading reading6 = new Reading(10, new GregorianCalendar(118, 11, 10, 1, 0, 0).getTime());
        Reading reading7 = new Reading(20, new GregorianCalendar(118, 11, 10, 2, 0, 0).getTime());
        Reading reading8 = new Reading(10, new GregorianCalendar(118, 11, 15, 1, 0, 0).getTime());
        Reading reading9 = new Reading(15, new GregorianCalendar(118, 11, 15, 5, 0, 0).getTime());
        Reading reading10 = new Reading(20, new GregorianCalendar(118, 11, 15, 20, 0, 0).getTime());
        Reading reading11 = new Reading(10, new GregorianCalendar(118, 11, 20, 0, 0, 0).getTime());
        Reading reading12 = new Reading(15, new GregorianCalendar(118, 11, 20, 1, 0, 0).getTime());
        Reading reading13 = new Reading(20, new GregorianCalendar(118, 11, 20, 2, 0, 0).getTime());
        rl1.addReading(reading1);
        rl1.addReading(reading2);
        rl1.addReading(reading3);
        rl1.addReading(reading4);
        rl1.addReading(reading5);
        rl1.addReading(reading6);
        rl1.addReading(reading7);
        rl1.addReading(reading8);
        rl1.addReading(reading9);
        rl1.addReading(reading10);
        rl1.addReading(reading11);
        rl1.addReading(reading12);
        rl1.addReading(reading13);

        //Act
        double expectedResult = 10;
        double result = rl1.getAverageOfMinimumValuesInTheReadingsOfMonth(118, 11);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }


    @Test
    public void seeIfGetMinimumValuesMonth2() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        Reading reading1 = new Reading(20, new GregorianCalendar(118, 11, 1, 0, 0, 0).getTime());
        Reading reading2 = new Reading(10, new GregorianCalendar(118, 11, 1, 1, 0, 0).getTime());
        Reading reading3 = new Reading(20, new GregorianCalendar(118, 11, 1, 2, 0, 0).getTime());
        Reading reading4 = new Reading(12, new GregorianCalendar(118, 11, 5, 1, 0, 0).getTime());
        Reading reading5 = new Reading(7, new GregorianCalendar(118, 11, 5, 2, 0, 0).getTime());
        Reading reading6 = new Reading(10, new GregorianCalendar(118, 11, 10, 1, 0, 0).getTime());
        Reading reading7 = new Reading(20, new GregorianCalendar(118, 11, 10, 2, 0, 0).getTime());
        Reading reading8 = new Reading(-2, new GregorianCalendar(118, 11, 15, 1, 0, 0).getTime());
        Reading reading9 = new Reading(15, new GregorianCalendar(118, 11, 15, 5, 0, 0).getTime());
        Reading reading10 = new Reading(20, new GregorianCalendar(118, 11, 15, 20, 0, 0).getTime());
        Reading reading11 = new Reading(10, new GregorianCalendar(118, 11, 20, 0, 0, 0).getTime());
        Reading reading12 = new Reading(15, new GregorianCalendar(118, 11, 20, 1, 0, 0).getTime());
        Reading reading13 = new Reading(20, new GregorianCalendar(118, 11, 20, 2, 0, 0).getTime());
        rl1.addReading(reading1);
        rl1.addReading(reading2);
        rl1.addReading(reading3);
        rl1.addReading(reading4);
        rl1.addReading(reading5);
        rl1.addReading(reading6);
        rl1.addReading(reading7);
        rl1.addReading(reading8);
        rl1.addReading(reading9);
        rl1.addReading(reading10);
        rl1.addReading(reading11);
        rl1.addReading(reading12);
        rl1.addReading(reading13);

        //Act
        double expectedResult = 7.0;
        double result = rl1.getAverageOfMinimumValuesInTheReadingsOfMonth(118, 11);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void seeIfGetMaximumValuesMonth() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        Reading reading1 = new Reading(7, new GregorianCalendar(118, 11, 1, 0, 0, 0).getTime());
        Reading reading2 = new Reading(10, new GregorianCalendar(118, 11, 1, 1, 0, 0).getTime());
        Reading reading3 = new Reading(18, new GregorianCalendar(118, 11, 1, 2, 0, 0).getTime());
        Reading reading4 = new Reading(12, new GregorianCalendar(118, 11, 5, 1, 0, 0).getTime());
        Reading reading5 = new Reading(20, new GregorianCalendar(118, 11, 5, 2, 0, 0).getTime());
        Reading reading6 = new Reading(10, new GregorianCalendar(118, 11, 10, 1, 0, 0).getTime());
        Reading reading7 = new Reading(20, new GregorianCalendar(118, 11, 10, 2, 0, 0).getTime());
        Reading reading8 = new Reading(-2, new GregorianCalendar(118, 11, 15, 1, 0, 0).getTime());
        Reading reading9 = new Reading(-1, new GregorianCalendar(118, 11, 15, 5, 0, 0).getTime());
        Reading reading10 = new Reading(-5, new GregorianCalendar(118, 11, 15, 20, 0, 0).getTime());
        Reading reading11 = new Reading(10, new GregorianCalendar(118, 11, 20, 0, 0, 0).getTime());
        Reading reading12 = new Reading(15, new GregorianCalendar(118, 11, 20, 1, 0, 0).getTime());
        Reading reading13 = new Reading(20, new GregorianCalendar(118, 11, 20, 2, 0, 0).getTime());
        rl1.addReading(reading1);
        rl1.addReading(reading2);
        rl1.addReading(reading3);
        rl1.addReading(reading4);
        rl1.addReading(reading5);
        rl1.addReading(reading6);
        rl1.addReading(reading7);
        rl1.addReading(reading8);
        rl1.addReading(reading9);
        rl1.addReading(reading10);
        rl1.addReading(reading11);
        rl1.addReading(reading12);
        rl1.addReading(reading13);

        //Act
        double expectedResult = 15.4;
        double result = rl1.getAverageOfMaximumValuesInTheReadingsOfMonth(118, 11);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void seeIfMeanOfMonthWithAnEmptyArrayReturnsZero() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        //Act
        double expectedResult = 0;
        double result = rl1.getMeanOfRecordedValuesMonth(118, 11);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void seeIfMeanOfTheDayWithAnEmptyArrayReturnsZero() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        //Act
        double expectedResult = 0;
        double result = rl1.getMeanOftheDay(118, 11, 1);

        //Assert
        assertEquals(expectedResult, result, 0.001);
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
    public void seeIfGet1stDayOfWeek() {
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 7);
        Date d1 = cal.getTime();
        GregorianCalendar expectedResult = new GregorianCalendar(2018, 10, 4);
        GregorianCalendar result = rl1.getFirstDayOfWeekFromGivenDay(d1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfGet1stDayOfWeekIfSunday() {
        ReadingList rl1 = new ReadingList();
        GregorianCalendar cal = new GregorianCalendar(2018, 10, 4);
        Date d1 = cal.getTime();
        GregorianCalendar expectedResult = new GregorianCalendar(2018, 10, 4);
        GregorianCalendar result = rl1.getFirstDayOfWeekFromGivenDay(d1);
        assertEquals(expectedResult, result);
    }
}