package pt.ipp.isep.dei.project.reader;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.ReadingDTOWithUnitAndSensorID;
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

        List<ReadingDTOWithUnitAndSensorID> expectedResult = new ArrayList<>();

        ReadingsReaderJSON readingsReaderJSON = new ReadingsReaderJSON();
        ReadingDTOWithUnitAndSensorID readingDTO1 = new ReadingDTOWithUnitAndSensorID();
        readingDTO1.setDate(validDate1);
        readingDTO1.setValue(14D);
        readingDTO1.setUnit(new Celsius());
        readingDTO1.setSensorID("xxxx");

        ReadingDTOWithUnitAndSensorID readingDTO2 = new ReadingDTOWithUnitAndSensorID();
        readingDTO2.setDate(validDate2);
        readingDTO2.setValue(13.66D);
        readingDTO2.setUnit(new Celsius());
        String sensorID2 = "TT12346";
        readingDTO2.setSensorID(sensorID2);

        ReadingDTOWithUnitAndSensorID readingDTO3 = new ReadingDTOWithUnitAndSensorID();
        readingDTO3.setDate(validDate3);
        readingDTO3.setValue(16.27D);
        readingDTO3.setUnit(new Celsius());
        String sensorID3 = "TT1236A";
        readingDTO3.setSensorID(sensorID3);


        ReadingDTOWithUnitAndSensorID readingDTO4 = new ReadingDTOWithUnitAndSensorID();
        readingDTO4.setDate(validDate4);
        readingDTO4.setValue(15.41D);
        readingDTO4.setUnit(new Celsius());
        String sensorID4 = "RF12334";
        readingDTO4.setSensorID(sensorID4);


        ReadingDTOWithUnitAndSensorID readingDTO5 = new ReadingDTOWithUnitAndSensorID();
        readingDTO5.setDate(validDate5);
        readingDTO5.setValue(13.8D);
        readingDTO5.setUnit(new Celsius());
        String sensorID5 = "RF12345";
        readingDTO5.setSensorID(sensorID5);

        expectedResult.add(readingDTO1);
        expectedResult.add(readingDTO2);
        expectedResult.add(readingDTO3);
        expectedResult.add(readingDTO4);
        expectedResult.add(readingDTO5);

        //Act

        List<ReadingDTOWithUnitAndSensorID> actualResult = readingsReaderJSON.readFile("src/test/resources/readerReadings/test2JSONReadings.json");

        //Assert

        assertEquals(expectedResult, actualResult);
    }
}