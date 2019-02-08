package pt.ipp.isep.dei.project.io.ui;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FileInputUtilsTest {

    @Test
    public void validGridMeteringPeriod() {
        int mp1 = 1;
        int mp2 = 60;
        int mp3 = 1440;
        FileInputUtils fileInputUtils = new FileInputUtils();
        //ACT
        boolean result1 = fileInputUtils.gridMeteringPeriodValidation(mp1);
        boolean result2 = fileInputUtils.gridMeteringPeriodValidation(mp2);
        boolean result3 = fileInputUtils.gridMeteringPeriodValidation(mp3);
        //ASSERT
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
    }

    @Test
    public void invalidGridMeteringPeriod() {
        int mp1 = 0;
        int mp2 = 14;
        int mp3 = 1441;
        FileInputUtils fileInputUtils = new FileInputUtils();
        //ACT
        boolean result1 = fileInputUtils.gridMeteringPeriodValidation(mp1);
        boolean result2 = fileInputUtils.gridMeteringPeriodValidation(mp2);
        boolean result3 = fileInputUtils.gridMeteringPeriodValidation(mp3);
        //ASSERT
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
    }
}