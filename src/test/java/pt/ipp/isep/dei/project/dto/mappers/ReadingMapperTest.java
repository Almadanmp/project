package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.model.Reading;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadingMapperTest {

    private Date validDate1 = new Date();

    @BeforeEach
    void arrangeArtifacts() {
        SimpleDateFormat validSdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            validDate1 = validSdf.parse("2016-11-15");
        } catch (ParseException c) {
            c.printStackTrace();
        }
    }

    @Test
    void seeIfReadingDTOsToReadingsWorks() {
        // Arrange

        List<ReadingDTO> readingDTOS = new ArrayList<>();
        List<Reading> expectedResult = new ArrayList<>();

        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setSensorId("SensorID");
        readingDTO.setValue(20D);
        readingDTO.setUnit("C");
        readingDTO.setDate(validDate1);
        readingDTOS.add(readingDTO);

        Reading reading = new Reading(20D, validDate1, "C", "SensorID");
        expectedResult.add(reading);

        // Act

        List<Reading> actualResult = ReadingMapper.readingDTOsToReadings(readingDTOS);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfReadingDTOsToReadingsWorksWhenListIsEmpty() {
        // Arrange

        List<ReadingDTO> readingDTOS = new ArrayList<>();
        List<Reading> expectedResult = new ArrayList<>();

        // Act

        List<Reading> actualResult = ReadingMapper.readingDTOsToReadings(readingDTOS);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

}