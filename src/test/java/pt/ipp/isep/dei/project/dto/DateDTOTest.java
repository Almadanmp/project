package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

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
    void getInitialDate() {

        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(initialDate);
        Date expectedResult = initialDate;
        Date actualResult = dateDTO.getInitialDate();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getEndDate() {
        DateDTO dateDTO = new DateDTO();
        dateDTO.setInitialDate(endDate);
        Date expectedResult = endDate;
        Date actualResult = dateDTO.getEndDate();
        assertEquals(expectedResult, actualResult);
    }
}
