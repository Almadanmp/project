package pt.ipp.isep.dei.project.io.ui.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.ReadingDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReadingsReaderCSVTest {

    private ReadingsReaderCSV readingsReaderCSV;

    @BeforeEach
    void arrangeArtifacts() {
        readingsReaderCSV = new ReadingsReaderCSV();
    }

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

        List<ReadingDTO> expectedResult = new ArrayList<>();

        ReadingDTO readingDTO1 = new ReadingDTO();
        readingDTO1.setDate(validDate1);
        readingDTO1.setValue(14D);
        readingDTO1.setUnit("C");
        readingDTO1.setSensorId("xxxx");

        ReadingDTO readingDTO2 = new ReadingDTO();
        readingDTO2.setDate(validDate2);
        readingDTO2.setValue(13.66D);
        readingDTO2.setUnit("C");
        String sensorID2 = "TT12346";
        readingDTO2.setSensorId(sensorID2);

        ReadingDTO readingDTO3 = new ReadingDTO();
        readingDTO3.setDate(validDate3);
        readingDTO3.setValue(16.27D);
        readingDTO3.setUnit("C");
        String sensorID3 = "TT1236A";
        readingDTO3.setSensorId(sensorID3);


        ReadingDTO readingDTO4 = new ReadingDTO();
        readingDTO4.setDate(validDate4);
        readingDTO4.setValue(15.41D);
        readingDTO4.setUnit("C");
        String sensorID4 = "RF12334";
        readingDTO4.setSensorId(sensorID4);


        ReadingDTO readingDTO5 = new ReadingDTO();
        readingDTO5.setDate(validDate5);
        readingDTO5.setValue(13.8D);
        readingDTO5.setUnit("C");
        String sensorID5 = "RF12345";
        readingDTO5.setSensorId(sensorID5);

        expectedResult.add(readingDTO1);
        expectedResult.add(readingDTO2);
        expectedResult.add(readingDTO3);
        expectedResult.add(readingDTO4);
        expectedResult.add(readingDTO5);

        //Act

        List<ReadingDTO> actualResult = readingsReaderCSV.readFile("src/test/resources/readingsFiles/test2CSVReadings.csv");

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfReadFileWorksWhenFileHasNoReadings() {
        //Assert

        assertThrows(IllegalArgumentException.class,
                () -> readingsReaderCSV.readFile("src/test/resources/readerReadings/test3CSVReadings.csv"));
    }

    @Test
    void seeIfReadFileWorksWhenFileHasWrongDate() {
        //Arrange

        Date validDate1 = new Date();

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate1 = simpleDate.parse("2020-12-30T14:00:00+00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }

        List<ReadingDTO> expectedResult = new ArrayList<>();

        ReadingDTO readingDTO1 = new ReadingDTO();
        readingDTO1.setDate(validDate1);
        readingDTO1.setValue(-8.61);
        readingDTO1.setUnit("Celsius");
        readingDTO1.setSensorId("Sensor1");

        expectedResult.add(readingDTO1);

        //Act

        List<ReadingDTO> actualResult = readingsReaderCSV.readFile("src/test/resources/readingsFiles/test4CSVReadings.csv");

        //Assert

        assertEquals(expectedResult, actualResult);
    }
}