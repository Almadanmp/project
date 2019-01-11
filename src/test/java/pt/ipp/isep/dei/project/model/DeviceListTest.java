package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class DeviceListTest {

    @Test
    void seeIfAddDeviceWorksWithSameDevice() {
        //Arrange
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
        //Act
        Boolean actualResult = deviceL1.addDevices(d1);
        //Assert
        assertEquals(false, actualResult);
    }

    @Test
    void seeIfAddDeviceWorksWithDifferentDevice() {
        //Arrange
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
        //Act
        Boolean actualResult = deviceL1.addDevices(device2);
        //Assert
        assertEquals(true, actualResult);
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

    //This test can only work after Devices is re-worked.

    @Test
    void ensureThatAObjectIsAInstanceOf() {
        //Arrange
        DeviceList dList1 = new DeviceList();
        Device device1 = new Device();
        dList1.addDevices(device1);
        DeviceList dList2 = new DeviceList();
        Device device2 = new Device();
        dList2.addDevices(device2);
        //Act
        Boolean actualResult = dList1.equals(dList1);
        //Assert
        assertTrue(actualResult);
    }


    @Test
    void ensureThatAObjectIsAInstanceOf2() {
        //Arrange
        DeviceList dList1 = new DeviceList();
        Device device1 = new Device();
        dList1.addDevices(device1);
        DeviceList dList2 = new DeviceList();
        dList2.addDevices(device1);
        //Act
        boolean actualResult = dList1.equals(dList2);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf3() {
        //Arrange
        DeviceList dList1 = new DeviceList();
        Device device1 = new Device();
        dList1.addDevices(device1);
        //Act
        boolean actualResult = dList1.equals(device1);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOf() {
        //Arrange
        DeviceList dList1 = new DeviceList();
        DeviceList dList2 = new DeviceList();
        Device device1 = new Device();
        Device device2 = new Device();
        dList1.addDevices(device1);
        dList2.addDevices(device2);
        //Act
        boolean actualResult = dList1.equals(dList2);
        assertFalse(actualResult);
    }
}
