package pt.ipp.isep.dei.project.reader;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOWrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class readingsReaderJSONTest {

    @Test
    void readFile() {
        //Arrange

        Date validDate3 = new Date();

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate3 = simpleDate.parse("2018-12-30T02:00:00+00:00");

        } catch (ParseException c) {
            c.printStackTrace();
        }


        ReadingsReaderJSON readingsReaderJSON = new ReadingsReaderJSON();
        List<ReadingDTOWrapper> expectedResult = new ArrayList<>();
        ReadingDTOWrapper readingDTO1 = new ReadingDTOWrapper();
        readingDTO1.setDate(validDate3);
        readingDTO1.setValue(57.2D);
        readingDTO1.setUnit("F");
        readingDTO1.setSensorId("TT12346");

        ReadingDTOWrapper readingDTO2 = new ReadingDTOWrapper();
        readingDTO2.setDate(validDate3);
        readingDTO2.setValue(56.66D);
        readingDTO2.setUnit("F");
        readingDTO2.setSensorId("TT12346");

        expectedResult.add(readingDTO1);
        expectedResult.add(readingDTO2);

        //Act

        List<ReadingDTOWrapper> actualResult = readingsReaderJSON.readFile("src/test/resources/readerReadings/test3JSONReadings.json");

        //Assert

        assertEquals(expectedResult, actualResult);
    }
}