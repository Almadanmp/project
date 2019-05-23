package pt.ipp.isep.dei.project.model.energy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;

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
    private EnergyGrid validGrid;
    private Device validFridge;
    private Room validRoom;

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
        validGrid.addRoom(validRoom);
        EnergyGrid validGrid2 = new EnergyGrid("FirstGrid", 400D, "34576");
        Room validRoom2 = new Room("Office", "2nd Floor Office", 2, 30, 30, 10, "Room1");
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
    void seeIfAddRoomToGridWorks() {
        // Arrange

        Room testRoom = new Room("Kitchen", "Equipped Kitchen", 1, 20, 20, 10, "Room1");

        // Act

        boolean actualResult = validGrid.addRoom(testRoom);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddRoomToGridDoesNotWork() {
        // Arrange

        Room testRoom = new Room("Kitchen", "Equipped Kitchen", 1, 20, 20, 10, "Room1");

        // Act

        validGrid.addRoom(testRoom);
        boolean actualResult = validGrid.addRoom(testRoom);

        // Assert

        assertFalse(actualResult);
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
    void seeIfGetNominalPowerWorksMultipleRooms() {
        //Arrange

        double expectedResult = 40;
        Room extraRoom = new Room("Kitchen", "Equipped Kitchen", 0, 12, 30, 10, "Room1");
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

        assertEquals(expectedResult, actualResult);
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

        String actualResult = validGrid.buildDeviceListWithTypeString();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWorksEmpty() {
        // Arrange

        EnergyGrid testGrid = new EnergyGrid("EmptyGrid", 100D, "34576");
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
        int expectedResult = 0;

        // Assert

        assertEquals(expectedResult, result);
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
    void getByIndexWithEmptyDeviceList() {
        //Arrange

        EnergyGrid emptyGrid = new EnergyGrid("emptyGrid", 330D, "34576");

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
    void seeIfGetRoomWorks() {
        Room actualResult = validGrid.getRoom(0);
        assertEquals(validRoom, actualResult);
    }

    @Test
    void seeIfGetRoomThrowsException() {
        //Arrange

        EnergyGrid emptyGrid = new EnergyGrid("noRooms", 330D, "34576");

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyGrid.getRoom(0));

        //Assert

        assertEquals("The room list is empty.", exception.getMessage());
    }

    @Test
    void seeIfGetNumberOfDevicesWorks() {
        //Arrange

        EnergyGrid emptyList = new EnergyGrid("noDevices", 200D, "34576");

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

        EnergyGrid nullList = new EnergyGrid("noDevices", 200D, "34576");
        EnergyGrid emptyList = new EnergyGrid("noDevices", 200D, "34576");
        Room emptyRoom = new Room("Office", "2nd Floor Office", 2, 30, 30, 10, "Room1");
        emptyList.addRoom(emptyRoom);
        //Act

        boolean actualResult1 = nullList.isDeviceListEmpty();
        boolean actualResult2 = validGrid.isDeviceListEmpty();
        boolean actualResult3 = emptyList.isDeviceListEmpty();


        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);
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
    void seeIfGetGridConsumptionInIntervalWorks() {
        //Act With Consumption
        double actualResult = validGrid.getGridConsumptionInInterval(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 3).getTime());
        //Act Without Consumption
        double actualResult1 = validGrid.getGridConsumptionInInterval(new GregorianCalendar(2019, Calendar.JANUARY, 3).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 4).getTime());
        //Assert With Consumption
        assertEquals(200, actualResult);
        //Assert Without Consumption
        assertEquals(0, actualResult1);
    }

    @Test
    void seeIfBuildEnergyGridRoomsAsStringWorks() {
        //Arrange
        EnergyGrid energyGrid = new EnergyGrid();
        String expectedResult = "---------------\n" +
                "0) ID: Office | Description: 2nd Floor Office |\n" +
                "---------------\n";
        String expectedResult1 = "Invalid List - List is Empty\n";
        //Act
        String actualResult = validGrid.buildEnergyGridRoomsAsString();
        String actualResult1 = energyGrid.buildEnergyGridRoomsAsString();
        //Assert
        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResult1, actualResult1);
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
    void seeIfGetRoomListWorks() {
        //Arrange
        List<Room> roomList = new ArrayList<>();
        roomList.add(validRoom);
        //Act
        List<Room> actualResult = validGrid.getRoomList();
        //Assert
        assertEquals(roomList, actualResult);
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
        List<Room> roomList = new ArrayList<>();
        roomList.add(validRoom);
        validGrid.setRooms(roomList);
        //Act
        Room actualResult = validGrid.getRoom(0);
        //Assert
        assertEquals(validRoom, actualResult);
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
    void seeIfRemoveRoomWorksWithoutRooms(){
        // Arrange

        validGrid.removeRoom(validRoom);

        // Act

        boolean actualResult = validGrid.removeRoom(validRoom);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfRemoveRoomByIdWorksWithManyRooms(){
        // Arrange

        Room room = new Room("Toilet", "bucket", 2, 2, 2, 2, "7");
        validGrid.addRoom(room);

        // Act

        boolean actualResult = validGrid.removeRoomById(room.getId());

        // Assert

        assertTrue(actualResult);
    }

}