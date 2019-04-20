package pt.ipp.isep.dei.project.io.ui.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;


class DateUtilsTest {
    private Date validDate1;

    @BeforeEach
    void arrangeArtifacts() {


        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("01/04/2018 00:00:00");

        } catch (
                ParseException c) {
            c.printStackTrace();
        }
    }

    @Test
    void formatDateNoTime(){
        GregorianCalendar cal = new GregorianCalendar(2018, Calendar.JANUARY,12);
        Date date = cal.getTime();
        String expectedResult = "12/01/2018";

        String result = DateUtils.formatDateNoTime(date);

        assertEquals(expectedResult,result);
    }

    @Test
    void isJanuaryMarchMay() {
        assertTrue(DateUtils.isJanuaryMarchMay(0));
        assertTrue(DateUtils.isJanuaryMarchMay(2));
        assertTrue(DateUtils.isJanuaryMarchMay(4));

        assertFalse(DateUtils.isJanuaryMarchMay(11));
    }

    @Test
    void isJulyAugust() {
        assertTrue(DateUtils.isJulyAugust(6));
        assertTrue(DateUtils.isJulyAugust(7));

        assertFalse(DateUtils.isJulyAugust(11));
    }

    @Test
    void isOctoberDecemberSuccess() {
        assertTrue(DateUtils.isOctoberDecember(9));
        assertTrue(DateUtils.isOctoberDecember(11));

        assertFalse(DateUtils.isOctoberDecember(2));
    }

    @Test
    void seeIfDateIsCreated() {
        // Arrange

        int year = 2018;
        int month = 3;
        int day = 01;
        Date expectedResult = validDate1;

        // Act

        Date actualResult = DateUtils.createDate(year, month, day);

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}
