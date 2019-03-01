package pt.ipp.isep.dei.project.model.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        roomList1.add(room1);
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
        deviceL1.add(d1);
        //Act
        Boolean actualResult = deviceL1.add(d1);
        //Assert
        assertEquals(false, actualResult);
    }

    @Test
    void seeIfAddDeviceWorksWithDifferentDevice() {
        //Arrange
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 9, 23456789, 2, 2);
        roomList1.add(room1);
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
        deviceL1.add(d1);
        //Act
        Boolean actualResult = deviceL1.add(device2);
        //Assert
        assertEquals(true, actualResult);
    }

    @Test
    void seeIfContainsDeviceWorks() {
        //Arrange --------------------------
        //Room List
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.add(room1);
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
        deviceL1.add(d1);
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
        roomList1.add(room1);
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
        roomList1.add(room1);
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
        deviceL1.add(d1);
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
        roomList1.add(room1);
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
        deviceL1.add(d1);
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
        roomList1.add(room1);
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
        deviceL1.add(d1);
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
        roomList1.add(room1);
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
        dList1.add(d1);
        DeviceList dList2 = new DeviceList();
        Device device2 = new Fridge(new FridgeSpec());
        device2.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        device2.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 56D);
        device2.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);
        dList2.add(device2);
        //Act
        boolean actualResult = dList1.equals(dList1);
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
        dList1.add(device1);
        DeviceList dList2 = new DeviceList();
        dList2.add(device1);
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
        dList1.add(device1);
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
        dList1.add(device1);
        dList2.add(device2);
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
        deviceList.add(d1);
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
        deviceList.add(d2);
        deviceList.add(d1);
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
        deviceList.add(d1);
        deviceList.add(d2);
        Device[] expectedResult = new Device[2];
        expectedResult[0] = d1;
        expectedResult[1] = d2;

        //Act
        Device[] actualResult = deviceList.getElementsAsArray();

        //Assert
        assertArrayEquals(expectedResult,actualResult);
    }

    @Test
    void appendDuplicatedList(){
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

        DeviceList mainDeviceList = new DeviceList();
        DeviceList addedDeviceList = new DeviceList();
        mainDeviceList.add(d1);
        mainDeviceList.add(d2);

        addedDeviceList.add(d1);
        addedDeviceList.add(d2);


        DeviceList expectedResult = new DeviceList();
        expectedResult.add(d1);
        expectedResult.add(d2);

        //Act

        DeviceList actualResult = mainDeviceList.appendListNoDuplicates(addedDeviceList);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void appendListOneDuplicatedDevice(){
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

        Device d3 = new Fridge(new FridgeSpec());
        d2.setName("Fridge3");
        d2.setNominalPower(21.0);
        d2.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 10D);
        d2.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 70D);
        d2.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 45D);

        DeviceList mainDeviceList = new DeviceList();
        DeviceList addedDeviceList = new DeviceList();
        mainDeviceList.add(d1);
        mainDeviceList.add(d2);

        addedDeviceList.add(d1);
        addedDeviceList.add(d3);

        DeviceList expectedResult = new DeviceList();
        expectedResult.add(d1);
        expectedResult.add(d2);
        expectedResult.add(d3);

        //Act

        DeviceList actualResult = mainDeviceList.appendListNoDuplicates(addedDeviceList);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void appendEmptyList(){
        //Arrange

        Device d1 = new Fridge(new FridgeSpec());
        d1.setName("Fridge1");
        d1.setNominalPower(21.0);
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 6D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 5D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);

        DeviceList mainDeviceList = new DeviceList();
        DeviceList addedDeviceList = new DeviceList();
        mainDeviceList.add(d1);


        DeviceList expectedResult = new DeviceList();
        expectedResult.add(d1);

        //Act

        DeviceList actualResult = mainDeviceList.appendListNoDuplicates(addedDeviceList);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDeviceByIndex(){
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

        Device d3 = new Fridge(new FridgeSpec());
        d2.setName("Fridge3");
        d2.setNominalPower(21.0);
        d2.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 10D);
        d2.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 70D);
        d2.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 45D);

        DeviceList mainDeviceList = new DeviceList();
        mainDeviceList.add(d1);
        mainDeviceList.add(d2);
        mainDeviceList.add(d3);

        //Act

        Device actualResult1 = mainDeviceList.get(0);
        Device actualResult2 = mainDeviceList.get(1);
        Device actualResult3 = mainDeviceList.get(2);

        //Assert

        assertEquals(d1, actualResult1);
        assertEquals(d2, actualResult2);
        assertEquals(d3, actualResult3);
    }

    @Test
    void getDeviceListSize(){
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

        DeviceList emptyList = new DeviceList();

        DeviceList listWithOneDevice = new DeviceList();
        listWithOneDevice.add(d1);

        DeviceList listWithTwoDevices = new DeviceList();
        listWithTwoDevices.add(d1);
        listWithTwoDevices.add(d2);

        //Act

        int actualResult1 = emptyList.size();
        int actualResult2 = listWithOneDevice.size();
        int actualResult3 = listWithTwoDevices.size();

        //Assert

        assertEquals(0, actualResult1);
        assertEquals(1, actualResult2);
        assertEquals(2, actualResult3);
    }

    @Test
    void getTypeByIndex(){
        //Arrange

        Device d1 = new Fridge(new FridgeSpec());
        d1.setName("Fridge");
        d1.setNominalPower(21.0);
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 6D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 5D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);

        Device d2 = new WashingMachine(new WashingMachineSpec());
        d2.setName("WashingMachine");
        d2.setNominalPower(21.0);
        d2.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 4D);

        DeviceList deviceList = new DeviceList();
        deviceList.add(d1);
        deviceList.add(d2);

        //Act

        String actualResult1 = deviceList.getTypeByIndex(0);
        String actualResult2 = deviceList.getTypeByIndex(1);

        //Assert

        assertEquals("Fridge", actualResult1);
        assertEquals("Washing Machine", actualResult2);
    }

    @Test
    void getDeviceListConsumptionInInterval(){
        //Arrange

        Date startInterval = new Date();
        Date endInterval = new Date();
        Date log01Start = new Date();
        Date log01End = new Date();
        Date log02Start = new Date();
        Date log02End = new Date();
        Date log03Start = new Date();
        Date log03End = new Date();

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            startInterval = validSdf.parse("20/11/2018 9:00:00");
            endInterval = validSdf.parse("20/11/2018 11:00:00");
            log01Start = validSdf.parse("20/11/2018 9:00:00");
            log01End = validSdf.parse("20/11/2018 9:30:00");
            log02Start = validSdf.parse("20/11/2018 10:00:00");
            log02End = validSdf.parse("20/11/2018 10:30:00");
            log03Start = validSdf.parse("20/11/2018 11:00:00");
            log03End = validSdf.parse("20/11/2018 11:30:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log logInsideInterval1 = new Log(10, log01Start, log01End); // Log inside interval 1
        Log logInsideInterval2 = new Log(10, log02Start, log02End); // Log inside interval 2
        Log logOutsideInterval = new Log(10, log03Start, log03End); // Log outside interval

        Device d1 = new Fridge(new FridgeSpec());
        d1.setName("Fridge");
        d1.setNominalPower(21.0);
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 6D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 5D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);

        d1.addLog(logInsideInterval1);
        d1.addLog(logInsideInterval2);
        d1.addLog(logOutsideInterval);


        Device d2 = new WashingMachine(new WashingMachineSpec());
        d2.setName("WashingMachine");
        d2.setNominalPower(21.0);
        d2.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 4D);

        d2.addLog(logInsideInterval1);
        d2.addLog(logInsideInterval2);
        d2.addLog(logOutsideInterval);

        DeviceList emptyList = new DeviceList();
        DeviceList listTwoDevices = new DeviceList();
        listTwoDevices.add(d1);
        listTwoDevices.add(d2);

        //Act

        double sumOfTwoDevices = listTwoDevices.getConsumptionInInterval(startInterval, endInterval);
        double sumEmptyList = emptyList.getConsumptionInInterval(startInterval, endInterval);

        //Assert

        assertEquals(40, sumOfTwoDevices);
        assertEquals(0, sumEmptyList);
    }

    @Test
    void getDeviceListLogList(){
        //Arrange

        Date log01Start = new Date();
        Date log01End = new Date();
        Date log02Start = new Date();
        Date log02End = new Date();

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            log01Start = validSdf.parse("20/11/2018 9:00:00");
            log01End = validSdf.parse("20/11/2018 9:30:00");
            log02Start = validSdf.parse("20/11/2018 10:00:00");
            log02End = validSdf.parse("20/11/2018 10:30:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log log1 = new Log(10, log01Start, log01End);
        Log log2 = new Log(10, log02Start, log02End);

        Device deviceEmptyLogList = new Fridge(new FridgeSpec());
        deviceEmptyLogList.setName("Fridge");
        deviceEmptyLogList.setNominalPower(21.0);
        deviceEmptyLogList.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 6D);
        deviceEmptyLogList.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 5D);
        deviceEmptyLogList.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);


        Device deviceWithLogs = new WashingMachine(new WashingMachineSpec());
        deviceWithLogs.setName("WashingMachine");
        deviceWithLogs.setNominalPower(21.0);
        deviceWithLogs.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 4D);

        deviceWithLogs.addLog(log1);
        deviceWithLogs.addLog(log2);

        LogList emptyLogList = new LogList();
        LogList listWithLogs = new LogList();
        listWithLogs.addLog(log1);
        listWithLogs.addLog(log2);


        //Act

        LogList actualResult1 = deviceEmptyLogList.getLogList();
        LogList actualResult2 = deviceWithLogs.getLogList();

        //Assert

        assertEquals(emptyLogList, actualResult1);
        assertEquals(listWithLogs, actualResult2);
    }

    @Test
    void getLogListInInterval(){
        //Arrange

        Date startInterval = new Date();
        Date endInterval = new Date();
        Date log01Start = new Date();
        Date log01End = new Date();
        Date log02Start = new Date();
        Date log02End = new Date();

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            startInterval = validSdf.parse("20/11/2018 9:00:00");
            endInterval = validSdf.parse("20/11/2018 11:00:00");
            log01Start = validSdf.parse("20/11/2018 9:00:00");
            log01End = validSdf.parse("20/11/2018 9:30:00");
            log02Start = validSdf.parse("20/11/2018 11:00:00");
            log02End = validSdf.parse("20/11/2018 11:30:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log logInsideInterval1 = new Log(10, log01Start, log01End); // Log inside interval
        Log logOutsideInterval = new Log(10, log02Start, log02End); // Log outside interval

        Device deviceOneLogInInterval = new Fridge(new FridgeSpec());
        deviceOneLogInInterval.setName("Fridge");
        deviceOneLogInInterval.setNominalPower(21.0);
        deviceOneLogInInterval.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 6D);
        deviceOneLogInInterval.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 5D);
        deviceOneLogInInterval.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 345D);

        deviceOneLogInInterval.addLog(logInsideInterval1);
        deviceOneLogInInterval.addLog(logOutsideInterval);


        Device deviceNoLogsFromInterval = new WashingMachine(new WashingMachineSpec());
        deviceNoLogsFromInterval.setName("WashingMachine");
        deviceNoLogsFromInterval.setNominalPower(21.0);
        deviceNoLogsFromInterval.setAttributeValue(WashingMachineSpec.WM_CAPACITY, 4D);

        deviceNoLogsFromInterval.addLog(logOutsideInterval);

        DeviceList emptyDeviceList = new DeviceList();
        DeviceList listDeviceLogInside = new DeviceList();
        DeviceList listDeviceLogOutside = new DeviceList();

        listDeviceLogInside.add(deviceOneLogInInterval);
        listDeviceLogOutside.add(deviceNoLogsFromInterval);

        LogList emptyList = new LogList();
        LogList listOneLog = new LogList();
        listOneLog.addLog(logInsideInterval1);

        //Act

        LogList actualResult1 = emptyDeviceList.getLogsInInterval(startInterval, endInterval);
        LogList actualResult2 = listDeviceLogInside.getLogsInInterval(startInterval, endInterval);
        LogList actualResult3 = listDeviceLogOutside.getLogsInInterval(startInterval, endInterval);


        //Assert

        assertEquals(emptyList, actualResult1);
        assertEquals(listOneLog, actualResult2);
        assertEquals(emptyList, actualResult3);
    }
}

