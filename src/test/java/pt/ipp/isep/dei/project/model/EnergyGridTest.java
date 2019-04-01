package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.*;

/**
 * EnergyGrid tests class.
 */

class EnergyGridTest {

    // Common artifacts for testing in this class.
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private House validHouse;
    private EnergyGrid validGrid;
    private Device validFridge;
    private Room validRoom;


    @BeforeEach
    void arrangeArtifacts() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        validHouse.setMotherArea( new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)));
        validHouse.addGrid(validGrid);
        validGrid = new EnergyGrid("FirstGrid", 400);
        validFridge = new Fridge(new FridgeSpec());
        validFridge.setNominalPower(20);
        validFridge.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 200D);
        validFridge.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 200D);
        validFridge.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 200D);
        validRoom = new Room("Office", 2, 30, 30, 10);
        validRoom.addDevice(validFridge);
        validGrid.addRoom(validRoom);
    }

    @Test
    void seeIfPrintGridWorks() {
        // Arrange

        String expectedResult = "Energy Grid: FirstGrid, Max Power: 400.0";

        // Act

        String actualResult = validGrid.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetListOfRoomsWorks() {
        // Arrange

        String expectedResult = "---------------\n" +
                "0) Designation: Office | House Floor: 2 | Width: 30.0 | Length: 30.0 | Height: 10.0\n" +
                "---------------\n";

        // Act

        String actualResult = validGrid.getRoomList().buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddPowerSourceToGridWorks() {
        // Arrange

        PowerSource firstPowerSource = new PowerSource("Top Floor", 25,
                15);

        // Act

        boolean result = validGrid.addPowerSource(firstPowerSource);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfAddPowerSourceToGridFailsDuplicate() {
        // Arrange

        PowerSource firstPowerSource = new PowerSource("Top Floor", 25,
                15);
        validGrid.addPowerSource(firstPowerSource);

        // Act

        boolean result = validGrid.addPowerSource(firstPowerSource);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfRemovesRoom() {
        // Act

        boolean actualResult = validGrid.removeRoom(validRoom);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfRemovesRoomFails() {
        // Arrange

        validGrid.removeRoom(validRoom);

        // Act

        boolean actualResult = validGrid.removeRoom(validRoom);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksTrue() {
        // Arrange

        EnergyGrid testGrid = new EnergyGrid("FirstGrid", 400);

        // Act

        boolean actualResult = validGrid.equals(testGrid);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        EnergyGrid testGrid = new EnergyGrid("SecondGrid", 400);

        // Act

        boolean actualResult = validGrid.equals(testGrid);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetListPowerSourcesIsSuccessful() {
        // Arrange

        PowerSource powerSource = new PowerSource("SourceOne", 400, 400);
        validGrid.addPowerSource(powerSource);
        PowerSourceList expectedResult = new PowerSourceList();
        expectedResult.add(powerSource);

        // Act

        PowerSourceList actualResult = validGrid.getPowerSourceList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRoomToGridWorks() {
        // Arrange

        Room testRoom = new Room("Kitchen", 1, 20, 20, 10);

        // Act

        boolean actualResult = validGrid.addRoom(testRoom);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksSameObject() {
        // Act

        boolean actualResult = validGrid.equals(validGrid); // For sonarqube coverage purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsSameContentWorks() {
        // Arrange

        EnergyGrid testGrid = new EnergyGrid("FirstGrid", 400);

        // Act

        boolean actualResult = validGrid.equals(testGrid);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksWhenObjectsAreDifferentWithDifferentContent() {
        // Act

        boolean actualResult = validGrid.equals(validRoom); // For sonarqube coverage purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorksMultipleRooms() {
        //Arrange

        double expectedResult = 40;
        Room extraRoom = new Room("Kitchen", 0, 12, 30, 10);
        extraRoom.addDevice(validFridge);
        validGrid.addRoom(extraRoom);

        //Act

        double actualResult = validGrid.getNominalPower();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintDevicesWorks() {
        // Arrange

        String expectedResult = "---------------\n" +
                "0) device Name: null, device Type: Fridge, device Nominal Power: 20.0\n" +
                "---------------\n";

        // Act

        String actualResult = validGrid.buildDeviceListString();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintRoomListWorksEmptyList() {
        // Arrange

        EnergyGrid emptyGrid = new EnergyGrid("Main Energy Grid Edificio C", 330);

        // Act

        String actualResult = emptyGrid.buildRoomListString();

        // Assert

        Assert.assertEquals("Invalid List - List is Empty\n", actualResult);
    }

    @Test
    void seeIfEqualsWorksWhenObjectsAreNull() {
        // Act

        boolean actualResult = validGrid.equals(null); // For sonarqube coverage purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validGrid.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWorks() {
        // Arrange

        String expectedResult = "---------------\n" +
                "Device type: Fridge | Device name: null | Nominal power: 20.0 | Room: Office | \n" +
                "---------------\n";

        // Act

        String actualResult = validGrid.buildDeviceListWithTypeString(validHouse);

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWorksEmpty() {
        // Arrange

        EnergyGrid testGrid = new EnergyGrid("EmptyGrid", 100);
        String expectedResult = "---------------\n" +
                "---------------\n";

        // Act

        String actualResult = testGrid.buildDeviceListWithTypeString(validHouse);

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWorksNullRoom() throws IOException {
        // Arrange

        EnergyGrid testGrid = new EnergyGrid("EmptyGrid", 100);
        Room nullRoom = null;
        testGrid.addRoom(nullRoom);
        String expectedResult = "---------------\n" +
                "---------------\n";

        // Act

        String actualResult = testGrid.buildDeviceListWithTypeString(validHouse);

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void energyConsumptionDummyTest() {
        // Act

        double result = validGrid.getEnergyConsumption(10);

        // Assert

        assertEquals(result, 0);
    }

    @Test
    void seeIfGetsLogInInterval() {
        // Arrange
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date initialTime = new Date();
        try {
            initialTime = validSdf.parse("11/01/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date finalTime = new Date();
        try {
            finalTime = validSdf.parse("11/03/2018 10:30:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date logDate = new Date();
        try {
            logDate = validSdf.parse("20/02/2018 10:30:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log validLog = new Log(300, logDate, new GregorianCalendar
                (2018, Calendar.FEBRUARY, 20, 10, 30).getTime());
        validFridge.addLog(validLog);
        LogList expectedResult = new LogList();
        expectedResult.addLog(validLog);

        // Act

        LogList actualResult = validGrid.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDoesNotGetLogInInterval() {
        // Arrange
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date initialTime = new Date();
        try {
            initialTime = validSdf.parse("11/01/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date finalTime = new Date();
        try {
            finalTime = validSdf.parse("11/03/2018 10:30:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogList expectedResult = new LogList();

        // Act

        LogList actualResult = validGrid.getLogsInInterval(initialTime, finalTime);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetsNegativeMaxContractedPower() {
        // Act

        boolean actualResult = validGrid.setMaxContractedPower(-1);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfSetsMaxContractedPowerZero() {
        // Arrange

        boolean actualResult = validGrid.setMaxContractedPower(0);

        // Act

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetEnergyConsumption() {
        // Arrange
        double expectedResult = 0;

        // Act

        double value = validGrid.getEnergyConsumption(21);

        // Assert

        assertEquals(expectedResult, value);
    }

    @Test
    void setRoomList() {
        // Arrange
        RoomList expectedResult1 = new RoomList();
        RoomList expectedResult2 = new RoomList();
        RoomList emptyList = new RoomList();
        RoomList oneRoomList = new RoomList();

        oneRoomList.add(validRoom);
        expectedResult2.add(validRoom);

        EnergyGrid gridNoRooms1 = new EnergyGrid("noRooms1", 200);
        EnergyGrid gridNoRooms2 = new EnergyGrid("noRooms2", 200);
        EnergyGrid gridNoRooms3 = new EnergyGrid("noRooms3", 200);

        // Act

        gridNoRooms1.setRoomList(emptyList);
        gridNoRooms2.setRoomList(null);
        gridNoRooms3.setRoomList(oneRoomList);

        // Assert

        assertEquals(expectedResult1, gridNoRooms1.getRoomList());
        assertEquals(expectedResult1, gridNoRooms2.getRoomList());
        assertEquals(expectedResult2, gridNoRooms3.getRoomList());
    }
    @Test
    void getByIndexWithEmptyDeviceList() {
        //Arrange

        EnergyGrid emptyGrid = new EnergyGrid("emptyGrid", 330);

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyGrid.getDeviceByIndex(0));

        //Assert

        assertEquals("The device list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetDeviceByIndexWorks() {
        //Act

        Device actualResult = validGrid.getDeviceByIndex(0);

        //Assert

        assertEquals(validFridge, actualResult);
    }

    @Test
    void ListRoomSize() {
        //Arrange

        EnergyGrid emptyList = new EnergyGrid("noRooms", 200);

        //Act

        int actualResult1 = emptyList.roomListSize();

        //Assert Empty List

        Assertions.assertEquals(0, actualResult1);

        //Act

        int actualResult2 = validGrid.roomListSize();

        //Assert One Grid

        Assertions.assertEquals(1, actualResult2);
    }

    @Test
    void seeIfGetRoomWorks() {
        //Act

        Room actualResult1 = validGrid.getRoom(0);

        //Assert

        assertEquals(validRoom, actualResult1);
    }

    @Test
    void seeIfGetRoomThrowsException() {
        //Arrange

        EnergyGrid emptyGrid = new EnergyGrid("noRooms", 330);

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyGrid.getRoom(0));

        //Assert

        assertEquals("The room list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetNumberOfDevicesWorks() {
        //Arrange

        EnergyGrid emptyList = new EnergyGrid("noDevices", 200);

        //Act

        int actualResult1 = emptyList.getNumberOfDevices();
        int actualResult2 = validGrid.getNumberOfDevices();


        //Assert

        assertEquals(0, actualResult1);
        assertEquals(1, actualResult2);
    }

    @Test
    void seeIfIsDeviceListEmptyWorks() {
        //Arrange

        EnergyGrid emptyList = new EnergyGrid("noDevices", 200);

        //Act

        boolean actualResult1 = emptyList.isDeviceListEmpty();
        boolean actualResult2 = validGrid.isDeviceListEmpty();


        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    void seeIfRoomListEmptyWorks() {
        //Arrange

        EnergyGrid emptyList = new EnergyGrid("noRooms", 200);

        //Act

        boolean actualResult1 = emptyList.isRoomListEmpty();
        boolean actualResult2 = validGrid.isRoomListEmpty();


        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);

    }

}