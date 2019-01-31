package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Log;
import pt.ipp.isep.dei.project.model.device.LogList;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;

import java.util.Date;
import java.util.GregorianCalendar;

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
        Log log = new Log(300, new GregorianCalendar(2018, 10, 20, 10, 2).getTime(), new GregorianCalendar(2018, 10, 20, 10, 10).getTime());
        Device d = new Device(new WashingMachineSpec());
        d.setNominalPower(12.0);
        d.addLog(log);
        boolean actualResult = list1.equals(d.getLogList());
        assertFalse(actualResult);
    }
    @Test
    void addLog() {
        LogList list1 = new LogList();
        Log log = new Log(300, new Date(), new Date());
        Device d = new Device(new WashingMachineSpec());
        d.setNominalPower(12.0);
        boolean actualResult = list1.addLog(log);
        assertTrue(actualResult);
    }
    @Test
    void addLogList() {
        LogList list1 = new LogList();
        LogList list2 = new LogList();
        Log log = new Log(300, new Date(), new Date());
        list1.addLog(log);
        Device d = new Device(new WashingMachineSpec());
        d.setNominalPower(12.0);
        boolean actualResult = list1.addLogList(list2);
        assertFalse(actualResult);
    }

    @Test
    void addLogList2() {
        LogList list1 = new LogList();
        LogList list2 = new LogList();
        Log log = new Log(300, new Date(), new Date());
        list1.addLog(log);
        Device d = new Device(new WashingMachineSpec());
        d.setNominalPower(12.0);
        boolean actualResult = list2.addLogList(list1);
        assertTrue(actualResult);
    }

    @Test
    void seeIfAddsLogTrue() {
        LogList list1 = new LogList();
        Log log = new Log(300, new GregorianCalendar(2018, 10, 20, 10, 2).getTime(), new GregorianCalendar(2018, 10, 20, 10, 10).getTime());
        boolean actualResult =list1.addLog(log);
        assertTrue(actualResult);
    }
    @Test
    void seeIfAddsLogFalse() {
        LogList list1 = new LogList();
        Log log = new Log(300, new GregorianCalendar(2018, 10, 20, 10, 2).getTime(), new GregorianCalendar(2018, 10, 20, 10, 10).getTime());
        list1.addLog(log);
        boolean actualResult =list1.addLog(log);
        assertFalse(actualResult);
    }

    @Test
    void seeIfAddsLogListFalse() {
        LogList list1 = new LogList();
        Log log = new Log(300, new GregorianCalendar(2018, 10, 20, 10, 2).getTime(), new GregorianCalendar(2018, 10, 20, 10, 10).getTime());
        list1.addLog(log);
        boolean actualResult =list1.addLogList(list1);
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

    @Test
    void testToString() {
        //Arrange
        LogList list1 = new LogList();
        Log log = new Log(300, new GregorianCalendar(2018, 10, 20, 10, 2).getTime(), new GregorianCalendar(2018, 10, 20, 10, 10).getTime());
        list1.addLog(log);
        //Act
        String expectedResult = "\n" +
                "0) Start Date: Tue Nov 20 10:02:00 GMT 2018 | End Date: Tue Nov 20 10:10:00 GMT 2018 | Value: 300.0";
        String actualResult = list1.toString();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testToStringEmpty() {
        //Arrange
        LogList list1 = new LogList();
        //Act
        String expectedResult = "There's no valid logs within that interval.";
        String actualResult = list1.toString();
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}