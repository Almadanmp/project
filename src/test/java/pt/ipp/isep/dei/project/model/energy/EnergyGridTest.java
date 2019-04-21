package pt.ipp.isep.dei.project.model.energy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.PowerSource;
import pt.ipp.isep.dei.project.model.energy.PowerSourceList;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.repository.RoomRepository;
import pt.ipp.isep.dei.project.repository.RoomSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EnergyGrid tests class.
 */

class EnergyGridTest {

    // Common artifacts for testing in this class.
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private House validHouse;
    private EnergyGrid validGrid;
    private EnergyGrid validGrid2;
    private Device validFridge;
    private Room validRoom;
    private Room validRoom2;

    @Mock
    RoomRepository roomRepository;
    @Mock
    RoomSensorRepository roomSensorRepository;
    @Mock
    SensorTypeRepository sensorTypeRepository;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        validHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));
        validGrid = new EnergyGrid("FirstGrid", 400, "34576");
        validFridge = new Fridge(new FridgeSpec());
        validFridge.setNominalPower(20);
        validFridge.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 200D);
        validFridge.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 200D);
        validFridge.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 200D);
        validRoom = new Room("Office", "2nd Floor Office", 2, 30, 30, 10, "Room1", "Grid1");
        validRoom.addDevice(validFridge);
        validGrid.addRoom(validRoom);
        validGrid2 = new EnergyGrid("FirstGrid", 400, "34576");
        validRoom2 = new Room("Office", "2nd Floor Office", 2, 30, 30, 10, "Room1", "Grid1");
        validGrid2.addRoom(validRoom2);
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

//    @Test
//    void seeIfGetListOfRoomsWorks() {
//        // Arrange
//
//        String expectedResult = "---------------\n" +
//                "Office) Designation: Office | Description: 2nd Floor Office | House Floor: 2 | Width: 30.0 | Length: 30.0 | Height: 10.0\n" +
//                "---------------\n";
//
//        // Act
//
//        String actualResult = validGrid.getRoomList().buildString();
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

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

//    @Test
//    void seeIfRemovesRoom() {
//        // Act
//
//        boolean actualResult = validGrid.removeRoom(validRoom);
//
//        // Assert
//
//        assertTrue(actualResult);
//    }

//    @Test
//    void seeIfRemovesRoomFails() {
//        // Arrange
//
//        validGrid.removeRoom(validRoom);
//
//        // Act
//
//        boolean actualResult = validGrid.removeRoom(validRoom);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }

    @Test
    void seeIfEqualsWorksTrue() {
        // Arrange

        EnergyGrid testGrid = new EnergyGrid("FirstGrid", 400, "34576");

        // Act

        boolean actualResult = validGrid.equals(testGrid);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        EnergyGrid testGrid = new EnergyGrid("SecondGrid", 400, "34576");

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

        Room testRoom = new Room("Kitchen", "Equipped Kitchen", 1, 20, 20, 10, "Room1", "Grid1");

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

        EnergyGrid testGrid = new EnergyGrid("FirstGrid", 400, "34576");

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
        Room extraRoom = new Room("Kitchen", "Equipped Kitchen", 0, 12, 30, 10, "Room1", "Grid1");
        extraRoom.addDevice(validFridge);
        validGrid.addRoom(extraRoom);

        //Act

        double actualResult = validGrid.getNominalPower();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfPrintDevicesWorks() {
//        // Arrange
//
//        String expectedResult = "---------------\n" +
//                "0) device Name: null, device Type: Fridge, device Nominal Power: 20.0\n" +
//                "---------------\n";
//
//        // Act
//
//        String actualResult = validGrid.buildDeviceListString();
//
//        // Assert
//
//        Assertions.assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    void seeIfPrintRoomListWorksEmptyList() {
//        // Arrange
//
//        EnergyGrid emptyGrid = new EnergyGrid("Main Energy Grid Edificio C", 330,"34576");
//
//        // Act
//
//        String actualResult = emptyGrid.buildRoomListString();
//
//        // Assert
//
//        assertEquals("Invalid List - List is Empty\n", actualResult);
//    }

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

        String actualResult = validGrid.buildDeviceListWithTypeString();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWorksEmpty() {
        // Arrange

        EnergyGrid testGrid = new EnergyGrid("EmptyGrid", 100, "34576");
        String expectedResult = "---------------\n" +
                "---------------\n";

        // Act

        String actualResult = testGrid.buildDeviceListWithTypeString();

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

//    @Test
//    void setRoomList() {
//        // Arrange
//        RoomService expectedResult1 = new RoomService();
//        RoomService expectedResult2 = new RoomService();
//        RoomService emptyList = new RoomService();
//        RoomService oneRoomService = new RoomService();
//
//        oneRoomService.add(validRoom);
//        expectedResult2.add(validRoom);
//
//        EnergyGrid gridNoRooms1 = new EnergyGrid("noRooms1", 200, "34576");
//        EnergyGrid gridNoRooms2 = new EnergyGrid("noRooms2", 200, "34576");
//        EnergyGrid gridNoRooms3 = new EnergyGrid("noRooms3", 200, "34576");
//
//        // Act
//
//        gridNoRooms1.setRoomService(emptyList);
//        gridNoRooms2.setRoomService(null);
//        gridNoRooms3.setRoomService(oneRoomService);
//
//        // Assert
//
//        assertEquals(expectedResult1, gridNoRooms1.getRoomList());
//        assertEquals(expectedResult1, gridNoRooms2.getRoomList());
//        assertEquals(expectedResult2, gridNoRooms3.getRoomList());
//    }

    @Test
    void getByIndexWithEmptyDeviceList() {
        //Arrange

        EnergyGrid emptyGrid = new EnergyGrid("emptyGrid", 330, "34576");

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyGrid.getDeviceByIndex(0));

        //Assert

        assertEquals("The device list is empty.", exception.getMessage());
    }

//    @Test
//    void seeIfGetDeviceByIndexWorks() {
//        //Act
//
//        Device actualResult = validGrid.getDeviceByIndex(0);
//
//        //Assert
//
//        assertEquals(validFridge, actualResult);
//    }

//    @Test
//    void ListRoomSize() {
//        //Arrange
//
//        EnergyGrid emptyList = new EnergyGrid("noRooms", 200,"34576");
//
//        //Act
//
//        int actualResult1 = emptyList.roomListSize();
//
//        //Assert Empty List
//
//        Assertions.assertEquals(0, actualResult1);
//
//        //Act
//
//        int actualResult2 = validGrid.roomListSize();
//
//        //Assert One Grid
//
//        Assertions.assertEquals(1, actualResult2);
//    }

//    @Test
//    void seeIfGetRoomWorks() {
//        //Arrange
//
//        //Act
//
//        Mockito.when(validGrid.getRoom(0)).thenReturn(validRoom2);
//
//
//        Room actualResult1 = validGrid2.getRoom(0);
//        String actualResultS = actualResult1.getName();
//
//        //Assert
//
//        assertEquals(validRoom2, actualResult1);
//    }

//    @Test
//    void seeIfGetRoomThrowsException() {
//        //Arrange
//
//        EnergyGrid emptyGrid = new EnergyGrid("noRooms", 330,"34576");
//
//        //Act
//
//        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyGrid.getRoom(0));
//
//        //Assert
//
//        assertEquals("The room list is empty.", exception.getMessage());
//    }

    @Test
    void seeIfGetNumberOfDevicesWorks() {
        //Arrange

        EnergyGrid emptyList = new EnergyGrid("noDevices", 200, "34576");

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

        EnergyGrid emptyList = new EnergyGrid("noDevices", 200, "34576");

        //Act

        boolean actualResult1 = emptyList.isDeviceListEmpty();
        boolean actualResult2 = validGrid.isDeviceListEmpty();


        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

//    @Test
//    void seeIfRoomListEmptyWorks() {
//        //Arrange
//
//        EnergyGrid emptyList = new EnergyGrid("noRooms", 200,"34576");
//
//        //Act
//
//        boolean actualResult1 = emptyList.isRoomListEmpty();
//        boolean actualResult2 = validGrid.isRoomListEmpty();
//
//
//        //Assert
//
//        assertTrue(actualResult1);
//        assertFalse(actualResult2);
//
//    }

}