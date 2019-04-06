package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.ReadingDTOWithUnit;
import pt.ipp.isep.dei.project.services.units.Celsius;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class readingsReaderJSONTest {

    @Test
    void seeIfReadFileWorks() {
        //Arrange

        Date validDate1 = new Date();
        Date validDate2 = new Date();
        Date validDate3 = new Date();
        Date validDate4 = new Date();
        Date validDate5 = new Date();

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate1 = simpleDate.parse("2018-12-30T02:00:00+00:00");
            validDate2 = simpleDate.parse("2018-12-30T08:00:00+00:00");
            validDate3 = simpleDate.parse("2018-12-30T14:00:00+00:00");
            validDate4 = simpleDate.parse("2018-12-30T20:00:00+00:00");
            validDate5 = simpleDate.parse("2018-12-31T02:00:00+00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }

        Map<ReadingDTOWithUnit, String> expectedResult = new HashMap<>();

        ReadingsReaderJSON readingsReaderJSON = new ReadingsReaderJSON();
        ReadingDTOWithUnit readingDTO1 = new ReadingDTOWithUnit();
        readingDTO1.setDate(validDate1);
        readingDTO1.setValue(14D);
        readingDTO1.setUnit(new Celsius());
        String sensorID1 = "xxxx";

        ReadingDTOWithUnit readingDTO2 = new ReadingDTOWithUnit();
        readingDTO2.setDate(validDate2);
        readingDTO2.setValue(13.66D);
        readingDTO2.setUnit(new Celsius());
        String sensorID2 = "TT12346";

        ReadingDTOWithUnit readingDTO3 = new ReadingDTOWithUnit();
        readingDTO3.setDate(validDate3);
        readingDTO3.setValue(16.27D);
        readingDTO3.setUnit(new Celsius());
        String sensorID3 = "TT1236A";


        ReadingDTOWithUnit readingDTO4 = new ReadingDTOWithUnit();
        readingDTO4.setDate(validDate4);
        readingDTO4.setValue(15.41D);
        readingDTO4.setUnit(new Celsius());
        String sensorID4 = "RF12334";


        ReadingDTOWithUnit readingDTO5 = new ReadingDTOWithUnit();
        readingDTO5.setDate(validDate5);
        readingDTO5.setValue(13.8D);
        readingDTO5.setUnit(new Celsius());
        String sensorID5 = "RF12345";

        expectedResult.put(readingDTO1, sensorID1);
        expectedResult.put(readingDTO2, sensorID2);
        expectedResult.put(readingDTO3, sensorID3);
        expectedResult.put(readingDTO4, sensorID4);
        expectedResult.put(readingDTO5, sensorID5);

        //Act

        Map<ReadingDTOWithUnit, String> actualResult = readingsReaderJSON.readFile("src/test/resources/readerReadings/test2JSONReadings.json");

        //Assert

        assertEquals(expectedResult, actualResult);
    }
}