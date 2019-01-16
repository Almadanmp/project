package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.devicetypes.Fridge;
import pt.ipp.isep.dei.project.model.devicetypes.WashingMachine;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DeviceListTest {

    @Test
    void seeIfAddDeviceWorksWithSameDevice() {
        //Arrange
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 9, 23456789, 2, 2);
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
        deviceL1.addDevice(d1);
        //Act
        Boolean actualResult = deviceL1.addDevice(d1);
        //Assert
        assertEquals(false, actualResult);
    }

    @Test
    void seeIfAddDeviceWorksWithDifferentDevice() {
        //Arrange
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 9, 23456789, 2, 2);
        roomList1.addRoom(room1);
        //Reading List
        ReadingList rL1 = new ReadingList();
        Date d2 = new GregorianCalendar(2008, 2, 2).getTime();
        Reading r1;
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        //Device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Device("FridgeTwo", 12, new Fridge());
        Device device2 = new Device("FridgeOne", 12, new Fridge());
        deviceL1.addDevice(d1);
        //Act
        Boolean actualResult = deviceL1.addDevice(device2);
        //Assert
        assertEquals(true, actualResult);
    }

    @Test
    void seeIfContainsDeviceWorks() {
        //Arrange --------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
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
        deviceL1.addDevice(d1);
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
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
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
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
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
        deviceL1.addDevice(d1);
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
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
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
        deviceL1.addDevice(d1);
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
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
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
        deviceL1.addDevice(d1);
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
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
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
    void SeeIfMatchDeviceIndexByStringWorks(){
        //Arrange
        Device device = new Device("frigorifico", 200, new Fridge());
        DeviceList dlist = new DeviceList();
        dlist.addDevice(device);
        //Act
        List<Integer> result = dlist.matchDeviceIndexByString("frigorifico");
        List<Integer> expectedResult = Collections.singletonList(dlist.getDeviceList().indexOf(device));
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void SeeIfMatchDeviceIndexByStringFails(){
        //Arrange
        Device device = new Device("frigorifico", 200, new Fridge());
        DeviceList dlist = new DeviceList();
        dlist.addDevice(device);
        //Act
        List<Integer> result = dlist.matchDeviceIndexByString("heater");
        List<Integer> expectedResult = new ArrayList<>();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void SeeIfPrintElementsByIndexWorks(){
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        Device d1 = new Device("frigorifico", 200, new Fridge());
        Device d2 = new Device("maquina de lavar", 150, new WashingMachine());
        Room room = new Room("kitchen", 1, 1, 2, 2);
        d1.setmParentRoom(room);
        d2.setmParentRoom(room);
        DeviceList dlist = new DeviceList();
        dlist.addDevice(d1);
        dlist.addDevice(d2);

        //Act
        String result = dlist.printElementsByIndex(list);
        String expectedResult = "1) maquina de lavar, kitchen, 150.0.\n";

        //Assert
        Assert.assertEquals(expectedResult, result);
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
        dList1.addDevice(device1);
        DeviceList dList2 = new DeviceList();
        Device device2 = new Device();
        dList2.addDevice(device2);
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
        dList1.addDevice(device1);
        DeviceList dList2 = new DeviceList();
        dList2.addDevice(device1);
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
        dList1.addDevice(device1);
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
        dList1.addDevice(device1);
        dList2.addDevice(device2);
        //Act
        boolean actualResult = dList1.equals(dList2);
        assertTrue(actualResult);
    }

    @Test
    void seeIfPrintDevicesWorks(){
        Device d1 = new Device("Fridge", 21, new Fridge());
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        String expectedResult = "0) The Device Name is Fridge, and its NominalPower is 21.0 kW.\n";

        String actualResult = deviceList.printDevices();

        assertEquals(expectedResult,actualResult);
    }
}
