package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceListTest {

    @Test
    public void seeIfContainsDeviceWorks() {
        //Arrange --------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Total Power
        double tP1 = 50;
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device("Television", "House Apliance", room1, rL1, tP1);
        deviceL1.addDevices(d1);
        //Act ------------------------------
        boolean result = deviceL1.containsDevice(d1);
        //Assert ---------------------------
        assertTrue(result);
    }

    @Test
    public void seeIfContainsDeviceWorksForFalse() {
        //Arrange --------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Total Power
        double tP1 = 50;
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device("Television", "House Apliance", room1, rL1, tP1);
        //Act ------------------------------
        boolean result = deviceL1.containsDevice(d1);
        //Assert ---------------------------
        assertFalse(result);
    }

    @Test
    public void seeIfRemoveDeviceWorks() {
        //Arrange ------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Total Power
        double tP1 = 50;
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device("Television", "House Apliance", room1, rL1, tP1);
        deviceL1.addDevices(d1);
        //Act ----------------------------
        deviceL1.removeDevice(d1);
        boolean result = deviceL1.containsDevice(d1);
        //Assert -------------------------
        assertFalse(result);
    }

    @Test
    public void seeIfRemoveDeviceWorksForFalse() {
        //Arrange ------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Total Power
        double tP1 = 50;
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device("Television", "House Apliance", room1, rL1, tP1);
        deviceL1.addDevices(d1);
        //Act ----------------------------
        boolean result = deviceL1.containsDevice(d1);
        //Assert -------------------------
        assertTrue(result);
    }



    @Test
    public void hashCodeDummyTest() {
        //Arrange -------------------
        DeviceList deviceL1 = new DeviceList();
        //Act -----------------------
        int expectedResult = 1;
        int actualResult = deviceL1.hashCode();
        //Assert --------------------
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureTha1tAObjectIsAInstanceOf() {
        //Arrange --------------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Total Power
        double tP1 = 50;
        DeviceList deviceL1 = new DeviceList();
        DeviceList deviceL2 = new DeviceList();
        Device d1 = new Device("Television", "House Apliance", room1, rL1, tP1);
        deviceL2.addDevices(d1);
        deviceL1.addDevices(d1);
        //Act ------------------------------------
        Boolean actualResult = deviceL1.equals(deviceL2);
        //Assert ---------------------------------
        assertTrue(actualResult);
    }

    @Test
    public void ensureThatAObjectIsNotAInstanceOf() {
        //Arrange -----------------------------------
        DeviceList deviceL1 = new DeviceList();
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Total Power
        double tP1 = 50;
        //Device List
        DeviceList deviceL2 = new DeviceList();
        Device d1 = new Device("Television", "House Apliance", room1, rL1, tP1);
        deviceL2.addDevices(d1);
        //Act ---------------------------------------
        Boolean actualResult = deviceL1.equals(deviceL2);
        //Assert ------------------------------------
        assertFalse(actualResult);
    }

    @Test
    public void ensureThatAObjectIsNotAInstanceOfNull() {
        //Arrange ---------------------------------------
        DeviceList deviceL1 = new DeviceList();
        //Act -------------------------------------------
        Boolean actualResult = deviceL1.equals(null);
        //Assert ----------------------------------------
        assertFalse(actualResult);
    }

}
