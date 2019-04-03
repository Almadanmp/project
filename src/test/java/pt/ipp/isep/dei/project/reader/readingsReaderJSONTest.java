package pt.ipp.isep.dei.project.reader;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.ReadingDTOWithSensorID;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class readingsReaderJSONTest {

    @Test
    void readFile() {

        Date validDate3 = new Date();

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate3 = simpleDate.parse("2018-12-30T02:00:00+00:00");

        } catch (ParseException c) {
            c.printStackTrace();
        }


        ReadingsReaderJSON readingsReaderJSON = new ReadingsReaderJSON();
        List<ReadingDTOWithSensorID> readingDTOList = new ArrayList<>();
        ReadingDTOWithSensorID readingDTO1 = new ReadingDTOWithSensorID();
        readingDTO1.setDate(validDate3);
        readingDTO1.setValue(57.2D);
        readingDTO1.setUnit("F");
        readingDTO1.setSensorId("TT12346");

        ReadingDTOWithSensorID readingDTO2 = new ReadingDTOWithSensorID();
        readingDTO2.setDate(validDate3);
        readingDTO2.setValue(56.66D);
        readingDTO2.setUnit("F");
        readingDTO2.setSensorId("TT12346");

        readingDTOList.add(readingDTO1);
        readingDTOList.add(readingDTO2);

        List<ReadingDTOWithSensorID> actual = readingsReaderJSON.readFile("/Users/teresavarela/Dropbox/SWITCHPESSOAL/desoft/project_g2/src/test/resources/readerReadings/test3JSONReadings.json");

        assertEquals(readingDTOList, actual);
    }
}