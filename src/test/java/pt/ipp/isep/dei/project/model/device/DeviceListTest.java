package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DeviceList tests class.
 */

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
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Fridge(new FridgeSpec());
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
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
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Fridge(new FridgeSpec());
        d1.setName("fdhgfhs");
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
        Device device2 = new Fridge(new FridgeSpec());
        device2.setName("fdhggfh");
        device2.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 5D);
        device2.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 6D);
        device2.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
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
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Fridge(new FridgeSpec());
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
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
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Fridge(new FridgeSpec());
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
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
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Fridge(new FridgeSpec());
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
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
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Fridge(new FridgeSpec());
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
        deviceL1.addDevice(d1);
        //Act ----------------------------
        boolean result = deviceL1.containsDevice(d1);
        //Assert -------------------------
        assertTrue(result);
    }

    @Test
    void seeIfListIsNotEmpty() {
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
        //device List
        DeviceList deviceL1 = new DeviceList();
        Device d1 = new Fridge(new FridgeSpec());
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
        deviceL1.addDevice(d1);
        //Act ----------------------------------
        boolean result = deviceL1.isEmpty();
        //Assert -------------------------------
        assertFalse(result);
    }

    @Test
    void checkIfListIsEmpty() {
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
        //device List
        DeviceList deviceL1 = new DeviceList();
        //Act ----------------------------------
        boolean result = deviceL1.isEmpty();
        //Assert -------------------------------
        assertTrue(result);
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
        Device d1 = new Fridge(new FridgeSpec());
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
        dList1.addDevice(d1);
        DeviceList dList2 = new DeviceList();
        Device device2 = new Fridge(new FridgeSpec());
        device2.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        device2.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        device2.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
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
        Device device1 = new Fridge(new FridgeSpec());
        device1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        device1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        device1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
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
        Device device1 = new Fridge(new FridgeSpec());
        device1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        device1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        device1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
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
        Device device1 = new Fridge(new FridgeSpec());
        device1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        device1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        device1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
        Device device2 = new Fridge(new FridgeSpec());
        device2.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        device2.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        device2.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
        dList1.addDevice(device1);
        dList2.addDevice(device2);
        //Act
        boolean actualResult = dList1.equals(dList2);
        assertTrue(actualResult);
    }

    @Test
    void seeIfPrintDevicesWorks() {
        Device d1 = new Fridge(new FridgeSpec());
        d1.setName("Fridge");
        d1.setNominalPower(21.0);
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 6D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 35D);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        String expectedResult = "---------------\n" +
                "0) device Name: Fridge, device Type: Fridge, device Nominal Power: 21.0\n" +
                "---------------\n";

        String actualResult = deviceList.buildString();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintDevicesWorks2() {
        Device d1 = new Fridge(new FridgeSpec());
        d1.setName("Fridge");
        d1.setNominalPower(21.0);
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 6D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 5D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
        Device d2 = new Fridge(new FridgeSpec());
        d2.setName("Fridge2");
        d2.setNominalPower(21.0);
        d2.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d2.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 7D);
        d2.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 435D);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d2);
        deviceList.addDevice(d1);
        String expectedResult = "---------------\n" +
                "0) device Name: Fridge2, device Type: Fridge, device Nominal Power: 21.0\n" +
                "1) device Name: Fridge, device Type: Fridge, device Nominal Power: 21.0\n" +
                "---------------\n";

        String actualResult = deviceList.buildString();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetterElementsAsArray(){
        //Arrange
        Device d1 = new Fridge(new FridgeSpec());
        d1.setName("Fridge1");
        d1.setNominalPower(21.0);
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 6D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 5D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
        Device d2 = new Fridge(new FridgeSpec());
        d2.setName("Fridge2");
        d2.setNominalPower(21.0);
        d2.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d2.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 7D);
        d2.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 435D);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        deviceList.addDevice(d2);
        Device[] expectedResult = new Device[2];
        expectedResult[0] = d1;
        expectedResult[1] = d2;

        //Act
        Device[] actualResult = deviceList.getElementsAsArray();

        //Assert
        assertArrayEquals(expectedResult,actualResult);
    }
}
