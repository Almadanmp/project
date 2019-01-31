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

class LogListTest {

    @Test
    void testForEmptyLogList() {
        LogList list = new LogList();
        boolean result = list.isEmpty();
        assertTrue(result);
    }

    @Test
    void seeEqualToEqualObject() {
        LogList list1 = new LogList();
        LogList list2 = new LogList();
        boolean actualResult = list1.equals(list2);
        assertTrue(actualResult);
    }

    @Test
    void seeEqualToSameObject() {
        LogList list1 = new LogList();
        boolean actualResult = list1.equals(list1);
        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        LogList list1 = new LogList();
        Log log = new Log(300, new Date(), new Date());
        Device d = new Device(TestUtils.PATH_TO_WASHINGMACHINE);
        d.setNominalPower(12.0);
        d.addLog(log);
        boolean actualResult = list1.equals(d.getLogList());
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        LogList list1 = new LogList();
        Room room = new Room("quarto", 1, 80, 2, 2);
        boolean actualResult = list1.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        LogList list1 = new LogList();
        int result = list1.hashCode();
        assertEquals(result, 1);
    }
}