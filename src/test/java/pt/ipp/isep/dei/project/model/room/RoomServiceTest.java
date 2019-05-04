package pt.ipp.isep.dei.project.model.room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.energy.EnergyGridService;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.RoomRepository;
import pt.ipp.isep.dei.project.repository.RoomSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RoomList tests class.
 */
@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    private Room validRoom;
    private Device validDevice;
    private RoomSensor firstValidRoomSensor;
    private RoomSensor secondValidRoomSensor;
    private RoomSensor thirdValidRoomSensor;
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018


    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomSensorRepository roomSensorRepository;
    @Mock
    private SensorTypeRepository sensorTypeRepository;

    private RoomService validRoomService;

    private List<Room> roomList;

    private static final Logger logger = Logger.getLogger(ReaderController.class.getName());

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        validRoomService = new RoomService(this.roomRepository, this.roomSensorRepository, this.sensorTypeRepository);
        validRoom = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");
        this.roomList = new ArrayList<>();
        roomList.add(validRoom);
        validDevice = new WaterHeater(new WaterHeaterSpec());
        validDevice.setName("WaterHeater");
        validDevice.setNominalPower(21.0);
        validDevice.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        validDevice.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 40D);
        validDevice.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        validDevice.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 30D);
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("21/11/2018 00:00:00");
            validDate2 = validSdf.parse("03/09/2018 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        firstValidRoomSensor = new RoomSensor("T32875", "SensorOne", new SensorType("Temperature", "Celsius"), validDate1, "RoomDFS");
        firstValidRoomSensor.setActive(true);
        secondValidRoomSensor = new RoomSensor("T32876", "SensorTwo", new SensorType("Temperature", "Celsius"), new Date(), "RoomDFS");
        secondValidRoomSensor.setActive(true);
        thirdValidRoomSensor = new RoomSensor("T32877", "SensorThree", new SensorType("Rainfall", "l/m2"), new Date(), "RoomDFS");
    }

    @Test
    void seeIfAddAreaReadingsWorksWhenSensorIDIsInvalid() {
        // Arrange

        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading(21D, validDate1, "C", "sensorID");
        readings.add(reading);

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        validRoom.addSensor(firstValidRoomSensor);

        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        int expectedResult = 0;

        //Act

        int actualResult = validRoomService.addRoomReadings("invalidSensor", readings, logger);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddAreaReadingsWorks() {
        // Arrange

        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading(21D, validDate1, "C", "sensorID");
        readings.add(reading);

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        validRoom.addSensor(firstValidRoomSensor);

        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        int expectedResult = 1;

        //Act

        int actualResult = validRoomService.addRoomReadings("T32875", readings, logger);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddReadingsToRoomSensorWorks() {
        // Arrange

        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading(21D, validDate1, "C", "T32875");
        readings.add(reading);

        int expectedResult = 1;

        //Act

        int actualResult = validRoomService.addReadingsToRoomSensor(firstValidRoomSensor, readings, logger);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddReadingsToRoomSensorWorksWhenReadingIsFromBeforeSensorActivatingDate() {
        // Arrange

        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading(21D, validDate2, "C", "sensorID");
        readings.add(reading);

        int expectedResult = 0;

        //Act

        int actualResult = validRoomService.addReadingsToRoomSensor(firstValidRoomSensor, readings, logger);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddReadingsToRoomSensorWorksWhenReadingAlreadyExists() {
        // Arrange

        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading(21D, validDate1, "C", "sensorID");
        readings.add(reading);

        firstValidRoomSensor.addReading(reading);

        int expectedResult = 0;

        //Act

        int actualResult = validRoomService.addReadingsToRoomSensor(firstValidRoomSensor, readings, logger);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddReadingsToRoomSensorWorksWhenListIsEmpty() {
        // Arrange

        List<Reading> readings = new ArrayList<>();

        int expectedResult = 0;

        //Act

        int actualResult = validRoomService.addReadingsToRoomSensor(firstValidRoomSensor, readings, logger);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomContainingSensorWithGivenIdWorks() {
        // Arrange

        List<Room> validList = new ArrayList<>();
        validRoom.addSensor(firstValidRoomSensor);
        validRoom.addSensor(secondValidRoomSensor);
        validList.add(validRoom);

        Mockito.when(roomRepository.findAll()).thenReturn(validList);

        //Act

        Room actualResult = validRoomService.getRoomContainingSensorWithGivenId("T32876");

        // Assert

        assertEquals(validRoom, actualResult);
    }

    @Test
    void seeIfGetRoomContainingSensorWithGivenIdWorksWhenSensorIdDoesNotExist() {
        // Arrange

        List<Room> emptyList = new ArrayList<>();

        Mockito.when(roomRepository.findAll()).thenReturn(emptyList);

        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> validRoomService.getRoomContainingSensorWithGivenId("invalidSensorID"));
    }

    @Test
    void seeIfGetAllRoomsWorksNull() {
        // Arrange
        Mockito.when(roomRepository.findAll()).thenReturn(null);
        List<Room> expectedResult = new ArrayList<>();

        // Act
        List<Room> actualResult = validRoomService.getAllRooms();

        // Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfRemoveRoom() {

        Mockito.when(roomRepository.findById(validRoom.getId())).thenReturn((Optional.of(validRoom)));


        //Assert
        assertTrue(validRoomService.removeRoom(validRoom));
    }

    @Test
    void seeIfDoNotRemoveRoom() {
        validRoomService.updateRoom(validRoom);
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
//        assertTrue(validRoomService.saveSensor(room));
//    }

    @Test
    void seeIfAddRoomCreate() {


        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");

        roomList.add(room);


        assertTrue(roomList.contains(room));
    }

    @Test
    void seeIfGetListOfRooms() {

        List<Room> roomList = new ArrayList<>();

        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");
        roomList.add(room);
        validRoomService.saveRoom(room);


        Mockito.when(roomRepository.findAll()).thenReturn(roomList);

        assertEquals(roomList, validRoomService.getAllRooms());
    }

    @Test
    void seeIfGetDB() {
        String mockId = "SensorOne";

        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");


        Mockito.when(roomRepository.findById(mockId)).thenReturn(Optional.of(room));

        Room result = validRoomService.getRoomByName(mockId);

        assertEquals(result.getId(), room.getId());
        assertEquals(result.getId(), room.getId());
    }

    @Test
    void seeIfGetDBdNoSensor() {
        String mockId = "SensorOne";

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> validRoomService.getRoomByName(mockId));

    }

    @Test
    void seeIfIdExists() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);
        //Assert
        assertTrue(validRoomService.idExists(validRoom.getId()));
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

        List<Room> rooms = new ArrayList<>();

        // Act

        String expectedResult = "Invalid List - List is Empty\n";

        // Assert

        assertEquals(expectedResult, validRoomService.buildRoomsAsString(rooms));
    }

    @Test
    void seeIfBuildRoomListStringWorksEmptyList() {
        List<Room> emptylist = new ArrayList<>();

        // Act

        String expectedResult = "Invalid List - List is Empty\n";

        // Assert

        assertEquals(expectedResult, validRoomService.buildselectRoomsAsString(emptylist));
    }

    @Test
    void seeIfBuildRoomListStringWorksList() {
        // Act
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        String expectedResult = "---------------\n" +
                "0) Designation: Kitchen | Description: 1st Floor Kitchen | House Floor: 1 | Width: 4.0 | Length: 5.0 | Height: 3.0\n" +
                "---------------\n";

        // Assert

        assertEquals(expectedResult, validRoomService.buildselectRoomsAsString(rooms));
    }

    @Test
    void seeIfBuildRoomListStringWorksListDB() {
        // Act
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        String expectedResult = "---------------\n" +
                "Kitchen) Description: 1st Floor Kitchen | House Floor: 1 | Width: 4.0 | Length: 5.0 | Height: 3.0\n" +
                "---------------\n";

        // Assert

        assertEquals(expectedResult, validRoomService.buildRoomsAsString(rooms));
    }


    @Test
    void seeIfEqualsWorksSameObject() {
        // Arrange

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
        // Act
        boolean actualResult = validRoom.equals(validRoomService);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsDifferentObjectTypes() {
        // Arrange

        Room room2 = new Room("Balcony", "3rd Floor Balcony", 3, 2, 4, 3, "Room1", "Grid1");

        // Act

        boolean actualResult = validRoom.equals(room2); // Necessary for Sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetByIndexWorks() {
        //Arrange
        List<Room> rooms = new ArrayList<>();

        Room room = new Room("room", "Double Bedroom", 2, 20, 20, 4, "Room1", "Grid1");
        rooms.add(validRoom);
        rooms.add(room);

        Mockito.when(roomRepository.findAll()).thenReturn((rooms));


        //Act

        Room actualResult1 = validRoomService.getRoom(0);
        Room actualResult2 = validRoomService.getRoom(1);

        //Assert

        assertEquals(validRoom, actualResult1);
        assertEquals(room, actualResult2);
    }

    @Test
    void getByIndexEmptyRoomList() {
        //Arrange

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> validRoomService.getRoom(0));

        //Assert

        assertEquals("The room list is empty.", exception.getMessage());
    }

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
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        validRoom.addDevice(validDevice);
        double expectedResult = 6.0;

        // Act


        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        double actualResult = validRoomService.getDailyConsumptionByDeviceType("WaterHeater", 89);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateRoomWorks() {
        //Arrange

        Room roomExpected = new Room("kitchen", "Ground Floor Kitchen", 0, 15, 10, 2, "Room1", "Grid1");

        //Act

        Room roomActual1 = validRoomService.createRoom("kitchen", "Ground Floor Kitchen", 0, 15, 10, 2, "Room1", "Grid1");

        //Assert

        assertEquals(roomExpected, roomActual1);

        //Arrange to check if room is created when it already exists in list


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

        //Act

        boolean actualResult = validRoomService.equals(validRoomService);

        // Assert

        assertTrue(actualResult);
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

    @Test
    void seeIfGetAllSensor() {

        List<RoomSensor> roomSensors = new ArrayList<>();
        validRoomService.saveSensor(secondValidRoomSensor);
        Mockito.when(sensorTypeRepository.findByName("Temperature")).thenReturn(Optional.of(new SensorType("Temperature", "C")));

        Mockito.when(roomSensorRepository.findAll()).thenReturn(roomSensors);
        validRoomService.saveSensor(firstValidRoomSensor);

        assertEquals(roomSensors, validRoomService.getAllSensor());
    }

    @Test
    void seeIfEqualsWorksOnSameObject() {
        //Act

        boolean actualResult = validRoomService.equals(validRoomService); // Required for Sonarqube testing purposes.

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnDiffObject() {
        //Act

        boolean actualResult = validRoomService.equals(20D); // Required for Sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfToStringWorks() {
        // Arrange

        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(secondValidRoomSensor);
        roomSensors.add(thirdValidRoomSensor);
        validRoom.setRoomSensors(roomSensors);
        String expectedResult = "---------------\n" +
                "ID: T32876 | SensorTwo | Type: Temperature | Active\n" +
                "ID: T32877 | SensorThree | Type: Rainfall | Active\n" +
                "---------------\n";

        // Act

        String actualResult = validRoom.buildRoomSensorsAsString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorksEmpty() {
        // Arrange
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = validRoom.buildRoomSensorsAsString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAllByEnergyGridNameWorks() {
        //Arrange
        List<Room> roomListExpected = new ArrayList<>();
        roomListExpected.add(validRoom);
        Mockito.when(roomRepository.findAllByEnergyGridId("Grid1")).thenReturn(roomListExpected);
        //Act
        List<Room> actualResult = validRoomService.getAllByEnergyGridName("Grid1");
        //Assert
        assertEquals(roomListExpected, actualResult);
    }

    @Test
    void seeIfCreateRoomReturnsExistingRoom() {
        //Arrange
        Room room2 = new Room("Kitchen3", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");
        Room room = new Room("Kitchen2", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");
        List<Room> roomList = new ArrayList<>();
        roomList.add(validRoom);
        roomList.add(room);
        Mockito.when(roomRepository.findAll()).thenReturn(roomList);
        //Act
        Room actualResult = validRoomService.createRoom("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");
        Room actualResult1 = validRoomService.createRoom("Kitchen3", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");
        //Assert
        assertEquals(validRoom, actualResult);
        assertEquals(room2, actualResult1);
    }

    @Test
    void seeIfEmptyRoomsWorks() {
        List<Room> roomList = new ArrayList<>();
        Mockito.when(roomRepository.findAll()).thenReturn(roomList);
        boolean actualResult = validRoomService.isEmptyRooms();
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetDeviceListAppendNoDuplicatesWorks() {
        //Arrange
        DeviceList deviceList = new DeviceList();
        deviceList.add(validDevice);
        DeviceList deviceList1 = new DeviceList();
        deviceList1.add(validDevice);
        List<Room> roomList = new ArrayList<>();
        Room room = new Room();
        room.setDeviceList(deviceList);
        Room room1 = new Room();
        room1.setDeviceList(deviceList1);
        roomList.add(room);
        roomList.add(room1);
        Mockito.when(roomRepository.findAll()).thenReturn(roomList);
        //Act
        DeviceList actualResult = validRoomService.getDeviceList();
        //Assert
        assertEquals(deviceList, actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        //Arrange
        List<Room> roomList = new ArrayList<>();
        DeviceList deviceList = new DeviceList();
        deviceList.add(validDevice);
        roomList.add(validRoom);
        validRoom.setDeviceList(deviceList);
        Mockito.when(roomRepository.findAll()).thenReturn(roomList);
        //Act
        double actualResult = validRoomService.getNominalPower();
        //Assert
        assertEquals(21, actualResult);
    }

    @Test
    void seeIfSaveRoomReturnsFalse() {
        //Arrange
        Mockito.when(roomRepository.findByRoomName("Kitchen")).thenReturn(Optional.of(validRoom));
        //Act
        boolean actualResult = validRoomService.saveRoom(validRoom);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfFindRoomByIdWorks() {
        //Arrange
        Mockito.when(roomRepository.findById("Kitchen")).thenReturn(Optional.of(validRoom));
        //Act
        Optional<Room> actualResult = validRoomService.findRoomByID("Kitchen");
        //Assert
        assertEquals(Optional.of(validRoom), actualResult);
    }

    @Test
    void seeIfGetTypeSensorByNameWorks() {
        //Arrange
        SensorType sensorType = new SensorType("Temperature", "C");
        Mockito.when(sensorTypeRepository.findByName("Temperature")).thenReturn(Optional.of(sensorType));
        //Act
        SensorType actualResult = validRoomService.getTypeSensorByName("Temperature");
        SensorType actualResult1 = validRoomService.getTypeSensorByName("Rainfall");
        //Assert
        assertEquals(sensorType, actualResult);
        assertNull(actualResult1);
    }

    @Test
    void seeIfCreateRoomSensorWorks() {
        //Arrange
        SensorType sensorType = new SensorType("Temperature", "C");
        Mockito.when(sensorTypeRepository.findByName("Temperature")).thenReturn(Optional.of(sensorType));
        //Act
        RoomSensor roomSensor = validRoomService.createRoomSensor("T32875", "SensorOne", new SensorType("Temperature", "Celsius"), validDate1, "RoomDFS");
        //Assert
        assertEquals(firstValidRoomSensor, roomSensor);
    }
}