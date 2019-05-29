package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateIntervalDTOTest {
    private Date initialDate;
    private Date endDate;

    @BeforeEach
    void arrangeArtifacts() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            initialDate = validSdf.parse("10/01/2017 09:59:59");
            endDate = validSdf.parse("10/01/2019 09:59:59");

        } catch (
                ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void seeIfEmptyConstructorWorks() {
        //Act

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO();

        //Assert

        assertNull(dateIntervalDTO.getInitialDate());
        assertNull(dateIntervalDTO.getEndDate());
    }

    @Test
    void seeIfSetInitalDateWorks() {
        //Arrange

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(null, endDate);

        //Act

        dateIntervalDTO.setInitialDate(initialDate);

        //Assert

        assertEquals(initialDate, dateIntervalDTO.getInitialDate());
    }

    @Test
    void seeIfSetInitalDateWorksWhenNull() {
        //Arrange

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(initialDate, endDate);

        //Act

        dateIntervalDTO.setInitialDate(null);

        //Assert

        assertNull(dateIntervalDTO.getInitialDate());
    }

    @Test
    void seeIfSetEndDateWorks() {
        //Arrange

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(initialDate, null);

        //Act

        dateIntervalDTO.setEndDate(endDate);

        //Assert

        assertEquals(endDate, dateIntervalDTO.getEndDate());
    }

    @Test
    void seeIfSetEndDateWorksWhenNull() {
        //Arrange

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(initialDate, endDate);

        //Act

        dateIntervalDTO.setEndDate(null);

        //Assert

        assertNull(dateIntervalDTO.getEndDate());
    }


    @Test
    void seeIfConstructorWorks() {
        //Act

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(initialDate, endDate);

        //Assert

        assertEquals(initialDate, dateIntervalDTO.getInitialDate());
        assertEquals(endDate, dateIntervalDTO.getEndDate());
    }

    @Test
    void seeIfConstructorWorksWhenNull() {
        //Act

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(null, null);

        //Assert

        assertNull(dateIntervalDTO.getInitialDate());
        assertNull(dateIntervalDTO.getEndDate());
    }


    @Test
    void getInitialDate() {

        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(initialDate, endDate);
        Date expectedResult = initialDate;
        Date actualResult = dateIntervalDTO.getInitialDate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getEndDate() {
        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(initialDate, endDate);
        Date expectedResult = endDate;
        Date actualResult = dateIntervalDTO.getEndDate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void idDateDTOValidSuccess() {
        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(initialDate, endDate);
        assertTrue(dateIntervalDTO.isValid());
    }

    @Test
    void idDateDTOValidInvalidNoEndDate() {
        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(initialDate, null);
        assertFalse(dateIntervalDTO.isValid());
    }

    @Test
    void idDateDTOValidInvalidNoDates() {
        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(null, null);
        assertFalse(dateIntervalDTO.isValid());
    }

    @Test
    void idDateDTOValidInvalidNoInitialDate() {
        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(null, endDate);
        assertFalse(dateIntervalDTO.isValid());
    }

    @Test
    void idDateDTOValidInvalidInvertedDates() {
        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(endDate, initialDate);
        assertFalse(dateIntervalDTO.isValid());
    }

    @Test
    void idDateDTOValidValidSameDate() {
        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(initialDate, initialDate);
        assertFalse(dateIntervalDTO.isValid());
    }
}

