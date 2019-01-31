package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Log;
import pt.ipp.isep.dei.project.model.device.LogList;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ReadingList tests class.
 */

class LogTest {
    @Test
    void getValueTest() {
        Log log = new Log(300,new Date(), new Date());
        double result = log.getValue();
        double expectedResult = 300;
        assertEquals(result, expectedResult);
    }

    @Test
    void getInitialDateTest() {
        Date testDate = new Date();

        Log log = new Log(200, testDate, new Date());
        Date result = log.getInitialDate();
        Date expectedResult = testDate;
        assertEquals(result, expectedResult);
    }
}