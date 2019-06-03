package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateWithRoomIdDTOTest {

    private Date initialDate;

    @BeforeEach
    void arrangeArtifacts() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            initialDate = validSdf.parse("10/01/2017 09:59:59");

        } catch (
                ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void seeIfGetDateWorks() {
        //Act

        DateWithRoomIdDTO dateDTO = new DateWithRoomIdDTO(initialDate,"test");
        Date expectedResult = initialDate;

        //Assert

        Date actualResult = dateDTO.getDate();

        assertEquals(actualResult,expectedResult);
    }

    @Test
    void seeIfGetDateWorksEmptyConstructor() {
        //Act

        DateWithRoomIdDTO dateDTO = new DateWithRoomIdDTO();
        Date expectedResult = initialDate;

        //Assert

        dateDTO.setDate(initialDate);
        Date actualResult = dateDTO.getDate();

        assertEquals(actualResult,expectedResult);
    }

    @Test
    void seeIfGetRoomIdWorksEmptyConstructor() {
        //Act

        DateWithRoomIdDTO dateDTO = new DateWithRoomIdDTO();
        String expectedResult = "test";

        //Assert

        dateDTO.setRoomId("test");
        String actualResult = dateDTO.getRoomId();

        assertEquals(actualResult,expectedResult);
    }

    @Test
    void seeIfIsDateValidFailsNoDate() {
        //Act

        DateWithRoomIdDTO dateDTO = new DateWithRoomIdDTO();

        //Assert

        boolean actualResult = dateDTO.isDateValid();

        assertFalse(actualResult);
    }

    @Test
    void seeIfIsDateValidWorks() {
        //Act

        DateWithRoomIdDTO dateDTO = new DateWithRoomIdDTO();
        dateDTO.setDate(initialDate);

        //Assert

        boolean actualResult = dateDTO.isDateValid();

        assertTrue(actualResult);
    }

    @Test
    void seeIfIsDateValidFailsNullDate() {
        //Act

        DateWithRoomIdDTO dateDTO = new DateWithRoomIdDTO();
        dateDTO.setDate(null);

        //Assert

        boolean actualResult = dateDTO.isDateValid();

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetDateWorksNull() {
        //Act

        DateWithRoomIdDTO dateDTO = new DateWithRoomIdDTO(null,"test");
        Date expectedResult = null;

        //Assert

        Date actualResult = dateDTO.getDate();

        assertEquals(actualResult,expectedResult);
    }

    @Test
    void seeIfGetNullRoomIdWorks() {
        //Act

        DateWithRoomIdDTO dateDTO = new DateWithRoomIdDTO();
        String expectedResult = null;

        //Assert

        dateDTO.setRoomId(null);
        String actualResult = dateDTO.getRoomId();

        assertEquals(actualResult,expectedResult);
    }

}