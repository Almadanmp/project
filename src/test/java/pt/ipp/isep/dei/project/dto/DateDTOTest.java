package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateDTOTest {
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
    void seeIfGetDateWorksEmptyConstructor() {
        //Act

        DateDTO dateDTO = new DateDTO();
        Date expectedResult = initialDate;

        //Assert

        dateDTO.setDate(initialDate);
        Date actualResult = dateDTO.getDate();

        assertEquals(actualResult,expectedResult);
    }

    @Test
    void seeIfGetDateWorks() {
        //Act

        DateDTO dateDTO = new DateDTO(initialDate);
        Date expectedResult = initialDate;

        //Assert

        Date actualResult = dateDTO.getDate();

        assertEquals(actualResult,expectedResult);
    }

    @Test
    void seeIfGetDateNullWorks() {
        //Act

        DateDTO dateDTO = new DateDTO();
        Date expectedResult = null;

        //Assert

        dateDTO.setDate(null);
        Date actualResult = dateDTO.getDate();

        assertEquals(actualResult,expectedResult);
    }

}