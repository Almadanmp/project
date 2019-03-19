package pt.ipp.isep.dei.project.io.ui.utils;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.testng.Assert.*;

class DateUtilsTest {

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

}
