package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.RoomService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

        // Act and Assert

        assertEquals(validDTO, testDTO);
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
    void seeIfEqualsWorksNotAnInstance(){
        assertNotEquals(validDTO, new RoomService());
    }
}
