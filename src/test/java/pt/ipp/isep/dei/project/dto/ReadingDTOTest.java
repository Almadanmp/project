package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ReadingDTOTest {
    // Common testing artifacts for testing in this class.

    private ReadingDTO validDTO;

    @BeforeEach
    void arrangeArtifacts() {
        validDTO = new ReadingDTO();
    }

    @Test
    void seeIfSetGetDateWorks() {
        // Arrange

        validDTO.setDate(new GregorianCalendar(2018, Calendar.JULY, 12).getTime());

        Date expectedResult = new GregorianCalendar(2018, Calendar.JULY, 12).getTime();

        // Act

        Date actualResult = validDTO.getDate();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetValueWorks() {
        // Arrange

        validDTO.setValue(21D);

        // Act

        double result = validDTO.getValue();

        // Assert

        assertEquals(21D, result);
    }

    @Test
    void seeIfSetGetStringWorks() {
        // Arrange

        validDTO.setUnit("C");
        String expectedResult = "C";

        // Act

        String actualResult = validDTO.getUnit();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorks() {
        // Arrange

        validDTO.setDate(new GregorianCalendar(2018, Calendar.JULY, 12).getTime());
        validDTO.setSensorId("TT");
        ReadingDTO testDTO = new ReadingDTO();
        testDTO.setDate(new GregorianCalendar(2018, Calendar.JULY, 12).getTime());
        testDTO.setSensorId("TT");
        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setDate(new GregorianCalendar(2018, Calendar.JULY, 12).getTime());
        readingDTO.setSensorId("T1");


        // Act and Assert

        assertEquals(validDTO, testDTO);
        assertNotEquals(validDTO, readingDTO);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        validDTO.setDate(new GregorianCalendar(2018, Calendar.JULY, 12).getTime());
        ReadingDTO testDTO = new ReadingDTO();
        testDTO.setDate(new GregorianCalendar(2018, Calendar.JULY, 8).getTime());

        // Act and Assert

        assertNotEquals(validDTO, testDTO);
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        // Assert

        assertEquals(validDTO, validDTO);
    }

    @Test
        //Assert

    void seeIfHashcodeWorks() {
        assertEquals(1, validDTO.hashCode());
    }


    @Test
    void seeIfEqualsWorksNotAnInstance() {
        assertNotEquals(validDTO, new WaterHeater(new WaterHeaterSpec()));
    }
}
