package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateValueDTOTest {

    @Test
    void seeIfHashCodeWorks() {
        // Arrange

        Date validDate = new Date();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate = validSdf.parse("01/04/2018 00:00:00");

        } catch (
                ParseException c) {
            c.printStackTrace();
        }

        DateValueDTO dateValueDTO = new DateValueDTO(validDate, 30);

        // Act

        int actualResult = dateValueDTO.hashCode();

        // Assert

        assertEquals(1, actualResult);
    }

    @Test
    void seeIfSetValueWorks() {
        // Arrange

        Date validDate = new Date();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate = validSdf.parse("01/04/2018 00:00:00");

        } catch (
                ParseException c) {
            c.printStackTrace();
        }

        DateValueDTO dateValueDTO = new DateValueDTO(validDate, 30);

        // Act

        dateValueDTO.setValue(40);

        // Assert

        assertEquals(40, dateValueDTO.getValue());
    }

    @Test
    void seeIfEqualsWorks() {
        // Arrange

        Date validDate1 = new Date();
        Date validDate2 = new Date();

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("01/04/2018 00:00:00");
            validDate1 = validSdf.parse("21/04/2018 00:00:00");

        } catch (
                ParseException c) {
            c.printStackTrace();
        }

        DateValueDTO dateValueDTO1 = new DateValueDTO(validDate1, 30);
        DateValueDTO dateValueDTO2 = new DateValueDTO(validDate2, 30);
        DateValueDTO dateValueDTO3 = new DateValueDTO(validDate1, 30);


        // Act

        boolean actualResult1 = dateValueDTO1.equals(dateValueDTO1);
        boolean actualResult2 = dateValueDTO1.equals(dateValueDTO2);
        boolean actualResult3 = dateValueDTO1.equals(dateValueDTO3);
        boolean actualResult4 = dateValueDTO1.equals(3D);
        boolean actualResult5 = dateValueDTO1.equals(null);


        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }
}