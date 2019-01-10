package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class DeviceListTest {

    @Test
    void seeIfAddDeviceWorksWithSameDevice() {
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 9, 23456789,2,2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2008, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Total Power
        double tP1 = 50;
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device();
        deviceL1.addDevices(d1);

        Boolean actualResult = deviceL1.addDevices(d1);

        Assertions.assertEquals(false, actualResult);
    }

    @Test
    void seeIfAddDeviceWorksWithDifferentDevice() {
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 9, 23456789,2,2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2008, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device();
        Device device2 = new Device();
        deviceL1.addDevices(d1);

        Boolean actualResult = deviceL1.addDevices(device2);

        Assertions.assertEquals(true, actualResult);
    }

    @Test
    void seeIfContainsDeviceWorks() {
        //Arrange --------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789,2,2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device();
        deviceL1.addDevices(d1);
        //Act ------------------------------
        boolean result = deviceL1.containsDevice(d1);
        //Assert ---------------------------
        assertTrue(result);
    }

    @Test
    void seeIfContainsDeviceWorksForFalse() {
        //Arrange --------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789,2,2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device();
        //Act ------------------------------
        boolean result = deviceL1.containsDevice(d1);
        //Assert ---------------------------
        assertFalse(result);
    }

    @Test
    void seeIfRemoveDeviceWorks() {
        //Arrange ------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789,2,2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device();
        deviceL1.addDevices(d1);
        //Act ----------------------------
        deviceL1.removeDevice(d1);
        boolean result = deviceL1.containsDevice(d1);
        //Assert -------------------------
        assertFalse(result);
    }

    @Test
    void seeIfRemoveDeviceWorksForFalse() {
        //Arrange ------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789,2,2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device();
        deviceL1.addDevices(d1);
        //Act ----------------------------
        boolean result = deviceL1.containsDevice(d1);
        //Assert -------------------------
        assertTrue(result);
    }

    @Test
    void seeIfCheckIfListIsValidWorks() {
        //Arrange ------------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789,2,2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device();
        deviceL1.addDevices(d1);
        //Act ----------------------------------
        boolean result = deviceL1.checkIfListIsValid();
        //Assert -------------------------------
        assertTrue(result);
    }

    @Test
    void seeIfCheckIfListIsValidWorksForFalse() {
        //Arrange ------------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789,2,2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Device List
        DeviceList deviceL1 = new DeviceList();
        //Act ----------------------------------
        boolean result = deviceL1.checkIfListIsValid();
        //Assert -------------------------------
        assertFalse(result);
    }


    @Test
    void hashCodeDummyTest() {
        //Arrange -------------------
        DeviceList deviceL1 = new DeviceList();
        //Act -----------------------
        int expectedResult = 1;
        int actualResult = deviceL1.hashCode();
        //Assert --------------------
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureTha1tAObjectIsAInstanceOf() {
        //Arrange --------------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789,2,2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        DeviceList deviceL1 = new DeviceList();
        DeviceList deviceL2 = new DeviceList();
        Device d1 = new Device();
        deviceL2.addDevices(d1);
        deviceL1.addDevices(d1);
        //Act ------------------------------------
        boolean actualResult = deviceL1.equals(deviceL2);
        //Assert ---------------------------------
        assertTrue(actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOf() {
        //Arrange -----------------------------------
        DeviceList deviceL1 = new DeviceList();
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789,2,2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Device List
        DeviceList deviceL2 = new DeviceList();
        Device d1 = new Device();
        deviceL2.addDevices(d1);
        //Act ---------------------------------------
        boolean actualResult = deviceL1.equals(deviceL2);
        //Assert ------------------------------------
        assertFalse(actualResult);
    }

    @Test
    void ensureThatAObject() {
        //Arrange -----------------------------------
        DeviceList deviceL1 = new DeviceList();
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789,2,2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Device List
        DeviceList deviceL2 = new DeviceList();
        Device d1 = new Device();
        deviceL2.addDevices(d1);
        deviceL1.addDevices(d1);
        //Act ---------------------------------------
        boolean actualResult = deviceL1.equals(deviceL2);
        //Assert ------------------------------------
        assertTrue(actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOfNull() {
        //Arrange ---------------------------------------
        DeviceList deviceL1 = new DeviceList();
        //Act -------------------------------------------
        boolean actualResult = deviceL1.equals(null);
        //Assert ----------------------------------------
        assertFalse(actualResult);
    }

}
