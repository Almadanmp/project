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
//TODO test is randomly failing
    @Test
    void getValueTest() {
        Log log = new Log(300,new Date(), new Date());
        double result = log.getValue();
        double expectedResult = 300;
        assertEquals(result, expectedResult);
    }

    @Test
    void getInitialDateTest() {
        Log log = new Log(200,new Date(), new Date());
        Date result = log.getInitialDate();
        Date expectedResult = new Date();
        assertEquals(result, expectedResult);
    }
}