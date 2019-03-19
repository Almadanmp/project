package pt.ipp.isep.dei.project.utils;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

class DateUtilsTest {

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
