package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.log.Log;
import pt.ipp.isep.dei.project.model.device.log.LogList;
import pt.ipp.isep.dei.project.repository.RoomRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RoomList tests class.
 */
@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    private Room validRoom;
    private Device validDevice;

    @Mock
    private RoomRepository roomRepository;

    private RoomService validRoomService;



    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        validRoomService = new RoomService(this.roomRepository);
        validRoom = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");
        validRoomService.add(validRoom);
        validDevice = new WaterHeater(new WaterHeaterSpec());
        validDevice.setName("WaterHeater");
        validDevice.setNominalPower(21.0);
        validDevice.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        validDevice.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 40D);
        validDevice.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        validDevice.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 30D);
    }


    @Test
    void seeIfAddRoomWorks() {
       RoomService emptyRoomService = new RoomService(this.roomRepository);
        // Assert
        assertTrue(emptyRoomService.add(validRoom));
        assertFalse(validRoomService.add(validRoom));
    }

    @Test
    void seeIfRemoveRoom() {

        Mockito.when(roomRepository.findById(validRoom.getName())).thenReturn((Optional.of(validRoom)));


        //Assert
        assertTrue(validRoomService.removeRoom(validRoom));
    }

    @Test
    void seeIfDoNotRemoveRoom(){
        validRoomService.addWithPersistence(validRoom);
        //Assert
        assertFalse(validRoomService.removeRoom(validRoom));
    }
//
//    @Test
//    void seeIfAddRoom() {
//        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");
//
//
//        Mockito.when(roomRepository.findByName(room.getName())).thenReturn(room);
//
//        assertTrue(validRoomService.addPersistence(room));
//    }

    @Test
    void seeIfAddRoomCreate() {


        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");

        validRoomService.addPersistence(room);



        assertTrue(validRoomService.contains(room));
    }

    @Test
    void seeIfgetlistOfRooms() {

List<Room> roomList = new ArrayList<>();

        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");
roomList.add(room);
        validRoomService.addPersistence(room);



        assertEquals(roomList, validRoomService.getRooms());
    }

    @Test
    void seeIfGetDB (){
        String mockId = "SensorOne";

        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");


        Mockito.when(roomRepository.findById(mockId)).thenReturn(Optional.of(room));

        Room result = validRoomService.getDB(mockId);

        assertEquals(result.getId(), room.getId());
        assertEquals(result.getName(), room.getName());
    }

    @Test
    void seeIfGetDBdNoSensor() {
        String mockId = "SensorOne";

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
           validRoomService.getDB(mockId);
        });

    }

    @Test
    void seeIfCreateRoom() {

    }

    @Test
    void seeIfIdExists() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);
        //Assert
        assertTrue(validRoomService.idExists(validRoom.getName()));
    }

    @Test
    void seeIfIdNotExists() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);
        //Assert
        assertFalse(validRoomService.idExists("Hall"));
    }

    @Test
    void seeIfBuildRoomListStringWorksEmptyListDB() {

        RoomService emptyRoomService = new RoomService(this.roomRepository);

        // Act

        String expectedResult = "Invalid List - List is Empty\n";

        // Assert

        assertEquals(expectedResult, emptyRoomService.buildStringDB());
    }

    @Test
    void seeIfBuildRoomListStringWorksEmptyList() {

        RoomService emptyRoomService = new RoomService(this.roomRepository);

        // Act

        String expectedResult = "Invalid List - List is Empty\n";

        // Assert

        assertEquals(expectedResult, emptyRoomService.buildString());
    }

    @Test
    void seeIfBuildRoomListStringWorksList() {
        // Act
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);
        String expectedResult = "---------------\n" +
                "0) Designation: Kitchen | Description: 1st Floor Kitchen | House Floor: 1 | Width: 4.0 | Length: 5.0 | Height: 3.0\n" +
                "---------------\n";

        // Assert

        assertEquals(expectedResult, validRoomService.buildString());
    }

    @Test
    void seeIfBuildRoomListStringWorksListDB() {
        // Act
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);
        String expectedResult = "---------------\n" +
                "Kitchen) Description: 1st Floor Kitchen | House Floor: 1 | Width: 4.0 | Length: 5.0 | Height: 3.0\n" +
                "---------------\n";

        // Assert

        assertEquals(expectedResult, validRoomService.buildStringDB());
    }


//    @Test
//    void seeIfEqualsWorksSameContent() {
//        // Arrange
//
//        validRoomService.add(validRoom);
//        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3);
//
//
//        // Act
//
//        boolean actualResult = validRoom.equals(room);
//
//        // Assert
//
//        assertTrue(actualResult);
//    }

    @Test
    void seeIfEqualsWorksSameObject() {
        // Arrange

        validRoomService.add(validRoom);

        // Act

        boolean actualResult = validRoom.equals(validRoom); // Needed for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetDeviceList() {
        DeviceList deviceList = new DeviceList();
        validRoom.setDeviceList(deviceList);

        assertEquals(deviceList, validRoom.getDeviceList());
    }

    @Test
    void seeIfEqualsDifferentListContents() {

        RoomService emptyRoomService = new RoomService(this.roomRepository);

        // Arrange

        Room testRoom = new Room("Balcony", "4th Floor Balcony", 4, 2, 4, 3, "Room1", "Grid1");
        validRoomService.add(testRoom);
        emptyRoomService.add(validRoom);

        // Act

        boolean actualResult = validRoom.equals(emptyRoomService);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsDifferentObjectTypes() {
        // Arrange

        Room room2 = new Room("Balcony", "3rd Floor Balcony", 3, 2, 4, 3, "Room1", "Grid1");
        validRoomService.add(validRoom);

        // Act

        boolean actualResult = validRoom.equals(room2); // Necessary for Sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

//    @Test
//    void seeIfIsEmptyWorks() {
//        //Arrange
//
//        RoomService roomService3 = new RoomService(roomRepository); //Has two rooms.
//
//        Room room2 = new Room("Balcony", "2nd Floor Balcony", 2, 21, 21, 4, "Room1", "Grid1");
//        roomService3.add(validRoom);
//        roomService3.add(room2);
//
//        // Act
//
//        boolean actualResult1 = validRoomService.isEmpty();
//        boolean actualResult2 = emptyRoomService.isEmptyDB();
//        boolean actualResult3 = roomService3.isEmptyDB();
//
//        // Assert
//
//        assertFalse(actualResult1);
//        assertTrue(actualResult2);
//        assertFalse(actualResult3);
//    }

    @Test
    void seeIfGetByIndexWorks() {
        //Arrange
        Room room = new Room("room", "Double Bedroom", 2, 20, 20, 4, "Room1", "Grid1");
        validRoomService.add(room);

        //Act

        Room actualResult1 = validRoomService.get(0);
        Room actualResult2 = validRoomService.get(1);

        //Assert

        assertEquals(validRoom, actualResult1);
        assertEquals(room, actualResult2);
    }

    @Test
    void getByIndexEmptyRoomList() {
        //Arrange

        RoomService emptyRoomService = new RoomService();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyRoomService.get(0));

        //Assert

        assertEquals("The room list is empty.", exception.getMessage());
    }

//    @Test
//    void ListSize() {
//
//        RoomService validRoomService2 = new RoomService();
//        validRoomService2.add(validRoom);
//        validRoomService2.add(new Room("room", "Single Bedroom", 2, 20, 20, 3, "Room1", "Grid1"));
//
//
//        //Act
//
//        int actualResult1 = emptyRoomService.sizeDB();
//        int actualResult2 = validRoomService.sizeDB();
//        int actualResult3 =validRoomService2.sizeDB();
//
//        //Assert
//
//        assertEquals(0, actualResult1);
//        assertEquals(1, actualResult2);
//        assertEquals(2, actualResult3);
//    }

    @Test
    void seeItGetDeviceListByTypeWorks() {
        // Arrange

        DeviceList expectedResult = new DeviceList();

        // Act

        DeviceList actualResult = validRoomService.getDeviceList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetDailyConsumptionByDevice() {
        validRoom.addDevice(validDevice);
        double expectedResult = 6.0;

        // Act

        double actualResult = validRoomService.getDailyConsumptionByDeviceType("WaterHeater", 89);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNominalPower() {
        validRoom.addDevice(validDevice);
        //Assert
        assertEquals(21.0, validRoomService.getNominalPower());
    }

    @Test
    void seeIfGetConsumptionInInterval() {
        //Logs
        Log firstLog = new Log(21, new GregorianCalendar(2019, Calendar.JANUARY, 21).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validDevice.addLog(firstLog);
        validRoom.addDevice(validDevice);

        //Interval
        Date initialTime = new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2019, Calendar.FEBRUARY, 20, 11, 0,
                0).getTime();
        //Assert
        assertEquals(21.0, validRoomService.getConsumptionInInterval(initialTime, finalTime));
    }

    @Test
    void seeIfGetLogsInInterval() {
        //Logs
        Log firstLog = new Log(21, new GregorianCalendar(2019, Calendar.JANUARY, 21).getTime(),
                new GregorianCalendar(2018, Calendar.SEPTEMBER, 21).getTime());
        validDevice.addLog(firstLog);
        validRoom.addDevice(validDevice);

        //Interval
        Date initialTime = new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 0,
                0).getTime();
        Date finalTime = new GregorianCalendar(2019, Calendar.FEBRUARY, 20, 11, 0,
                0).getTime();

        //Act
        LogList loglist = new LogList();
        loglist.addLog(firstLog);
        //Assert
        assertEquals(loglist, validRoomService.getLogsInInterval(initialTime, finalTime));
    }

    @Test
    void seeIfIsDeviceListEmptyFalse() {
        // Arrange
        validRoom.addDevice(validDevice);

        // Assert

        assertFalse(validRoomService.isDeviceListEmpty());
    }

    @Test
    void seeIfIsDeviceListEmptyTrue() {
        assertTrue(validRoomService.isDeviceListEmpty());
    }

    @Test
    void seeIfGetNumberOfDevices() {
        validDevice.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 30D);
        validRoom.addDevice(validDevice);

        // Assert

        assertEquals(1, validRoomService.getNumberOfDevices());
    }

//    @Test
//    void seeIfBuildDeviceListByType() {
//        //Arrange
//        validRoom.addDevice(validDevice);
//
//        //Act
//        StringBuilder expectedResult = new StringBuilder();
//        expectedResult.append("Device type: WaterHeater | Device name: WaterHeater | Nominal power: 21.0 | Room: Kitchen | \n");
//        // Assert
//
//        assertEquals(expectedResult, validRoomService.buildDeviceListByType("WaterHeater"));
//    }

    @Test
    void getElementsAsArray() {

        RoomService emptyRoomService = new RoomService(this.roomRepository);

        //Arrange

        Room[] expectedResult1 = new Room[0];
        Room[] expectedResult2 = new Room[1];
        Room[] expectedResult3 = new Room[2];

        RoomService validRoomService2 = new RoomService();
        validRoomService2.add(validRoom);
        validRoomService2.add(new Room("room", "Single Bedroom", 2, 20, 20, 3, "Room1", "Grid1"));

        expectedResult2[0] = validRoom;
        expectedResult3[0] = validRoom;
        expectedResult3[1] = new Room("room", "Single Bedroom", 2, 20, 20, 3, "Room1", "Grid1");

        //Act

        Room[] actualResult1 = emptyRoomService.getElementsAsArray();
        Room[] actualResult2 = validRoomService.getElementsAsArray();
        Room[] actualResult3 = validRoomService2.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfCreateRoomWorks() {
        //Arrange

        Room room = new Room("kitchen", "Ground Floor Kitchen", 0, 15, 10, 2, "Room1", "Grid1");
        Room roomExpected = new Room("kitchen", "Ground Floor Kitchen", 0, 15, 10, 2, "Room1", "Grid1");

        //Act

        Room roomActual1 = validRoomService.createRoom("kitchen", "Ground Floor Kitchen", 0, 15, 10, 2, "Room1", "Grid1");

        //Assert

        assertEquals(roomExpected, roomActual1);

        //Arrange to check if room is created when it already exists in list

        validRoomService.add(room);

        //Act

        Room roomActual2 = validRoomService.createRoom("kitchen", "Ground Floor Kitchen", 0, 15, 10, 2, "Room1", "Grid1");

        //Assert
        assertEquals(roomExpected, roomActual2);
    }


    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validRoom.hashCode();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorks() {
        // Arrange

        RoomService testList = new RoomService(roomRepository);
        testList.add(validRoom);

        //Act

        boolean actualResult =validRoomService.equals(testList);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        RoomService testList = new RoomService(roomRepository);


        //Act

        boolean actualResult =validRoomService.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentObject() {
        // Arrange

        EnergyGridService testList = new EnergyGridService();

        //Act

        boolean actualResult = validRoomService.equals(testList); // Needed for SonarQube testing purposes.

        // Assert

        assertFalse(actualResult);
    }
}