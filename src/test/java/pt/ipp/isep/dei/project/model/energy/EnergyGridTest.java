package pt.ipp.isep.dei.project.model.energy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.bridgeservices.EnergyGridRoomService;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EnergyGrid tests class.
 */

class EnergyGridTest {

    // Common artifacts for testing in this class.
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private EnergyGrid validGrid;
    private Device validFridge;
    private Room validRoom;
    private EnergyGridRoomService energyGridRoomService;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        GeographicArea geographicArea = new GeographicArea("Porto", "Cidade",
                2, 3, new Local(4, 4, 100));
        validHouse.setMotherAreaID(geographicArea.getId());
        validGrid = new EnergyGrid("FirstGrid", 400D, "34576");
        validFridge = new Fridge(new FridgeSpec());
        validFridge.setNominalPower(20);
        validFridge.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 200D);
        validFridge.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 200D);
        validFridge.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 200D);
        Log log = new Log(200, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 3).getTime());
        validFridge.addLog(log);
        validRoom = new Room("Office", "2nd Floor Office", 2, 30, 30, 10, "Room1");
        validRoom.addDevice(validFridge);
        validGrid.addRoomId(validRoom.getId());
        EnergyGrid validGrid2 = new EnergyGrid("FirstGrid", 400D, "34576");
        Room validRoom2 = new Room("Office", "2nd Floor Office", 2, 30, 30, 10, "Room1");
        //validGrid2.addRoom(validRoom2);
        energyGridRoomService = new EnergyGridRoomService();
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
    void seeIfEqualsWorksTrue() {
        // Arrange

        EnergyGrid testGrid = new EnergyGrid("FirstGrid", 400D, "34576");

        // Act

        boolean actualResult = validGrid.equals(testGrid);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        EnergyGrid testGrid = new EnergyGrid("SecondGrid", 400D, "34576");

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
        List<PowerSource> expectedResult = new ArrayList<>();
        expectedResult.add(powerSource);

        // Act

        List<PowerSource> actualResult = validGrid.getPowerSourceList();

        // Assert

        assertEquals(expectedResult, actualResult);
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

        EnergyGrid testGrid = new EnergyGrid("FirstGrid", 400D, "34576");

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
    void ListRoomSize() {
        //Arrange

        EnergyGrid emptyList = new EnergyGrid("noRooms", 200D, "34576");

        //Act

        int actualResult1 = emptyList.roomListSize();

        //Assert Empty List

        assertEquals(0, actualResult1);

        //Act

        int actualResult2 = validGrid.roomListSize();

        //Assert One Grid

        assertEquals(1, actualResult2);
    }

    @Test
    void seeIfRoomListEmptyWorks() {
        //Arrange

        EnergyGrid emptyList = new EnergyGrid("noRooms", 200D, "34576");

        //Act

        boolean actualResult1 = emptyList.isRoomListEmpty();
        boolean actualResult2 = validGrid.isRoomListEmpty();


        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);

    }

    @Test
    void seeIfSetAndGetHouseIdWorks() {
        //Arrange
        String expectedResult = "House 01";
        validGrid.setHouseId("House 01");
        //Act
        String actualResult = validGrid.getHouseId();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetAndGetNameWorks() {
        //Arrange
        String expectedResult = "Grid 01";
        validGrid.setName("Grid 01");
        //Act
        String actualResult = validGrid.getName();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetRoomsWorks() {
        //Arrange
        List<String> roomList = new ArrayList<>();
        roomList.add(validRoom.getId());
        validGrid.setRooms(roomList);
        //Act
        List<String> actualResult = validGrid.getRoomIdList();
        //Assert
        assertEquals(roomList, actualResult);
    }

    @Test
    void seeIfSetAndGetPowerSourceListWorks() {
        //Arrange
        List<PowerSource> powerSourceList = new ArrayList<>();
        PowerSource powerSource = new PowerSource("Source 01", 10, 30);
        powerSourceList.add(powerSource);
        validGrid.setPowerSourceList(powerSourceList);
        //Act
        List<PowerSource> actualResult = validGrid.getPowerSourceList();
        //Assert
        assertEquals(powerSourceList, actualResult);
    }

    @Test
    void seeIfRemoveRoomIdWorks() {
        //Arrange

        List<String> roomList = new ArrayList<>();
        roomList.add(validRoom.getId());
        validGrid.setRooms(roomList);

        //Act

        boolean actualResult = validGrid.removeRoomId(validRoom.getId());

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfRemoveRoomIdFails() {
        //Arrange

        List<String> roomList = new ArrayList<>();
        roomList.add(validRoom.getId());
        validGrid.setRooms(roomList);

        //Act

        validGrid.removeRoomId(validRoom.getId());
        boolean actualResult = validGrid.removeRoomId(validRoom.getId());

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddRoomIdFails() {
        //Arrange

        List<String> roomList = new ArrayList<>();
        roomList.add(validRoom.getId());
        validGrid.setRooms(roomList);

        //Act

        boolean actualResult = validGrid.addRoomId(validRoom.getId());

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddRoomIdWorks() {
        //Arrange

        List<String> roomList = new ArrayList<>();
        roomList.add(validRoom.getId());
        validGrid.setRooms(roomList);

        //Act

        validGrid.removeRoomId(validRoom.getId());
        boolean actualResult = validGrid.addRoomId(validRoom.getId());

        //Assert

        assertTrue(actualResult);
    }

}