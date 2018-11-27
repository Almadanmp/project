package Sprint_0;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class ReadingListTest {

    @Test
    public void ensureThatWeAddAReading1ToAList() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(15, new GregorianCalendar(118, 11, 25));
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
        Reading reading2 = new Reading(29, new GregorianCalendar(118, 9, 3));
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

}