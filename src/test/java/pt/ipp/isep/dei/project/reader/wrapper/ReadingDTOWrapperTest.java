package pt.ipp.isep.dei.project.reader.wrapper;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReadingDTOWrapperTest {

    @Test
    void seeIfEqualsWorks() {
        //Assert

        Date validDate = new Date();
        Date diffDate = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate = simpleDate.parse("2018-12-30T02:00:00+00:00");
            diffDate = simpleDate.parse("2018-12-30T04:55:00+00:00"); // diff than first
        } catch (ParseException c) {
            c.printStackTrace();
        }

        ReadingDTOWrapper readingDTOWrapper1 = new ReadingDTOWrapper();
        readingDTOWrapper1.setSensorId("TT");
        readingDTOWrapper1.setDate(validDate);

        ReadingDTOWrapper readingDTOWrapper2 = new ReadingDTOWrapper(); // same as first Wrapper
        readingDTOWrapper2.setSensorId("TT");
        readingDTOWrapper2.setDate(validDate);

        ReadingDTOWrapper readingDTOWrapper3 = new ReadingDTOWrapper(); //diff Date as first Wrapper
        readingDTOWrapper3.setSensorId("TT");
        readingDTOWrapper3.setDate(diffDate);

        ReadingDTOWrapper readingDTOWrapper4 = new ReadingDTOWrapper(); // diff sensorID as first Wrapper
        readingDTOWrapper4.setSensorId("GG");
        readingDTOWrapper4.setDate(validDate);


        //Act

        boolean actualResult1 = readingDTOWrapper1.equals(readingDTOWrapper1);
        boolean actualResult2 = readingDTOWrapper1.equals(readingDTOWrapper2);
        boolean actualResult3 = readingDTOWrapper1.equals(readingDTOWrapper3);
        boolean actualResult4 = readingDTOWrapper1.equals(readingDTOWrapper4);
        boolean actualResult5 = readingDTOWrapper1.equals(2D);

        //Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Arrange

        ReadingDTOWrapper readingDTOWrapper1 = new ReadingDTOWrapper();
        int expectedResult =1;

        //Assert

        assertEquals(expectedResult, readingDTOWrapper1.hashCode());
    }
}