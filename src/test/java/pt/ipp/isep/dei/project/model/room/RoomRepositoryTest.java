package pt.ipp.isep.dei.project.model.room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.controller.controllercli.ReaderController;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.RoomCrudRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * RoomList tests class.
 */
@ExtendWith(MockitoExtension.class)
class RoomRepositoryTest {

    private Room validRoom;
    private Device validDevice;
    private RoomSensor firstValidRoomSensor;
    private RoomSensor secondValidRoomSensor;
    private RoomSensor thirdValidRoomSensor;
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018


    @Mock
    private RoomCrudRepo roomCrudRepo;

    @InjectMocks
    private RoomRepository validRoomRepository;

    private List<Room> roomList;

    private static final Logger logger = Logger.getLogger(ReaderController.class.getName());

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        validRoom = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");
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
        firstValidRoomSensor = new RoomSensor("T32875", "SensorOne", "Temperature", validDate1);
        firstValidRoomSensor.setActive(true);
        secondValidRoomSensor = new RoomSensor("T32876", "SensorTwo", "Temperature", new Date());
        secondValidRoomSensor.setActive(true);
        thirdValidRoomSensor = new RoomSensor("T32877", "SensorThree", "Rainfall", new Date());
    }

    @Test
    void seeIfGetAllRoomWebDTOsWorks() {
        // Arrange

        Room validRoom2 = new Room("Living Room", "1st Floor Living Room", 1, 56, 55, 3, "Room1");

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        rooms.add(validRoom2);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        List<RoomDTOWeb> expectedResult = new ArrayList<>();

        RoomDTOWeb validDTO1 = new RoomDTOWeb();
        validDTO1.setName("Kitchen");
        validDTO1.setFloor(1);
        validDTO1.setWidth(4D);
        validDTO1.setLength(5D);
        validDTO1.setHeight(3D);

        RoomDTOWeb validDTO2 = new RoomDTOWeb();
        validDTO2.setName("Living Room");
        validDTO2.setFloor(1);
        validDTO2.setWidth(56D);
        validDTO2.setLength(55D);
        validDTO2.setHeight(3D);

        expectedResult.add(validDTO1);
        expectedResult.add(validDTO2);

        //Act

        List<RoomDTOWeb> actualResult = validRoomRepository.getAllRoomWebDTOs();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAllRoomWebDTOsWorksWhenFindAllReturnsNull() {
        // Arrange

        Mockito.when(roomCrudRepo.findAll()).thenReturn(null);

        List<RoomDTOWeb> expectedResult = new ArrayList<>();

        //Act

        List<RoomDTOWeb> actualResult = validRoomRepository.getAllRoomWebDTOs();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRoomToCrudRepositoryWorks() {
        // Arrange

        Room validRoom2 = new Room("Living Room", "1st Floor Living Room", 1, 56, 55, 3, "Room1");

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom2);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        //Act

        boolean actualResult = validRoomRepository.addRoomToCrudRepository(validRoom);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddRoomToCrudRepositoryWorksWhenRoomExistsInRepository() {
        // Arrange

        Room validRoom2 = new Room("Living Room", "1st Floor Living Room", 1, 56, 55, 3, "Room1");

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom2);
        rooms.add(validRoom);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        //Act

        boolean actualResult = validRoomRepository.addRoomToCrudRepository(validRoom);

        // Assert

        assertFalse(actualResult);
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

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        int expectedResult = 0;

        //Act

        int actualResult = validRoomRepository.addRoomReadings("invalidSensor", readings, logger);

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

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        int expectedResult = 1;

        //Act

        int actualResult = validRoomRepository.addRoomReadings("T32875", readings, logger);

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

        int actualResult = validRoomRepository.addReadingsToRoomSensor(firstValidRoomSensor, readings, logger);

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

        int actualResult = validRoomRepository.addReadingsToRoomSensor(firstValidRoomSensor, readings, logger);

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

        int actualResult = validRoomRepository.addReadingsToRoomSensor(firstValidRoomSensor, readings, logger);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddReadingsToRoomSensorWorksWhenListIsEmpty() {
        // Arrange

        List<Reading> readings = new ArrayList<>();

        int expectedResult = 0;

        //Act

        int actualResult = validRoomRepository.addReadingsToRoomSensor(firstValidRoomSensor, readings, logger);

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

        Mockito.when(roomCrudRepo.findAll()).thenReturn(validList);

        //Act

        Room actualResult = validRoomRepository.getRoomContainingSensorWithGivenId("T32876");

        // Assert

        assertEquals(validRoom, actualResult);
    }

    @Test
    void seeIfGetRoomContainingSensorWithGivenIdWorksWhenSensorIdDoesNotExist() {
        // Arrange

        List<Room> emptyList = new ArrayList<>();

        Mockito.when(roomCrudRepo.findAll()).thenReturn(emptyList);

        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> validRoomRepository.getRoomContainingSensorWithGivenId("invalidSensorID"));
    }

    @Test
    void seeIfGetAllRoomsWorksNull() {
        // Arrange
        Mockito.when(roomCrudRepo.findAll()).thenReturn(null);
        List<Room> expectedResult = new ArrayList<>();

        // Act
        List<Room> actualResult = validRoomRepository.getAllRooms();

        // Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfRemoveRoom() {

        Mockito.when(roomCrudRepo.findById(validRoom.getId())).thenReturn((Optional.of(validRoom)));


        //Assert
        assertTrue(validRoomRepository.removeRoom(validRoom));
    }

    @Test
    void seeIfDoNotRemoveRoom() {
        validRoomRepository.updateRoom(validRoom);
        //Assert
        assertFalse(validRoomRepository.removeRoom(validRoom));
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


        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");

        roomList.add(room);


        assertTrue(roomList.contains(room));
    }

    @Test
    void seeIfGetListOfRooms() {

        List<Room> roomList = new ArrayList<>();

        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");
        roomList.add(room);
        validRoomRepository.saveRoom(room);


        Mockito.when(roomCrudRepo.findAll()).thenReturn(roomList);

        assertEquals(roomList, validRoomRepository.getAllRooms());
    }

    @Test
    void seeIfGetDB() {
        String mockId = "SensorOne";

        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");


        Mockito.when(roomCrudRepo.findById(mockId)).thenReturn(Optional.of(room));

        Room result = validRoomRepository.getRoomByName(mockId);

        assertEquals(result.getId(), room.getId());
        assertEquals(result.getId(), room.getId());
    }

    @Test
    void seeIfGetDBdNoSensor() {
        String mockId = "SensorOne";

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> validRoomRepository.getRoomByName(mockId));

    }

    @Test
    void seeIfIdExists() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);
        //Assert
        assertTrue(validRoomRepository.idExists(validRoom.getId()));
    }

    @Test
    void seeIfIdNotExists() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);
        //Assert
        assertFalse(validRoomRepository.idExists("Hall"));
    }

    @Test
    void seeIfBuildRoomListStringWorksEmptyListDB() {

        List<Room> rooms = new ArrayList<>();

        // Act

        String expectedResult = "Invalid List - List is Empty\n";

        // Assert

        assertEquals(expectedResult, validRoomRepository.buildRoomsAsString(rooms));
    }

    @Test
    void seeIfBuildRoomListStringWorksEmptyList() {
        List<Room> emptylist = new ArrayList<>();

        // Act

        String expectedResult = "Invalid List - List is Empty\n";

        // Assert

        assertEquals(expectedResult, validRoomRepository.buildselectRoomsAsString(emptylist));
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

        assertEquals(expectedResult, validRoomRepository.buildselectRoomsAsString(rooms));
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

        assertEquals(expectedResult, validRoomRepository.buildRoomsAsString(rooms));
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
        boolean actualResult = validRoom.equals(validRoomRepository);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsDifferentObjectTypes() {
        // Arrange

        Room room2 = new Room("Balcony", "3rd Floor Balcony", 3, 2, 4, 3, "Room1");

        // Act

        boolean actualResult = validRoom.equals(room2); // Necessary for Sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetByIndexWorks() {
        //Arrange
        List<Room> rooms = new ArrayList<>();

        Room room = new Room("room", "Double Bedroom", 2, 20, 20, 4, "Room1");
        rooms.add(validRoom);
        rooms.add(room);

        Mockito.when(roomCrudRepo.findAll()).thenReturn((rooms));


        //Act

        Room actualResult1 = validRoomRepository.getRoom(0);
        Room actualResult2 = validRoomRepository.getRoom(1);

        //Assert

        assertEquals(validRoom, actualResult1);
        assertEquals(room, actualResult2);
    }

    @Test
    void getByIndexEmptyRoomList() {
        //Arrange

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> validRoomRepository.getRoom(0));

        //Assert

        assertEquals("The room list is empty.", exception.getMessage());
    }

    @Test
    void seeItGetDeviceListByTypeWorks() {
        // Arrange

        DeviceList expectedResult = new DeviceList();

        // Act

        DeviceList actualResult = validRoomRepository.getDeviceList();

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


        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        double actualResult = validRoomRepository.getDailyConsumptionByDeviceType("WaterHeater", 89);

        // Assert

        assertEquals(expectedResult, actualResult, 0.1);
    }

    @Test
    void seeIfCreateRoomWorks() {
        //Arrange

        Room roomExpected = new Room("kitchen", "Ground Floor Kitchen", 0, 15, 10, 2, "Room1");
        ArrayList<Double> dimensions = new ArrayList<>();
        dimensions.add(15D);
        dimensions.add(10D);
        dimensions.add(2D);


        //Act

        Room roomActual1 = validRoomRepository.createRoom("kitchen", "Ground Floor Kitchen", 0, dimensions, "Room1");

        //Assert

        assertEquals(roomExpected, roomActual1);

        //Arrange to check if room is created when it already exists in list


        //Act

        Room roomActual2 = validRoomRepository.createRoom("kitchen", "Ground Floor Kitchen", 0, dimensions, "Room1");

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

        boolean actualResult = validRoomRepository.equals(validRoomRepository);

        // Assert

        assertTrue(actualResult);
    }


    @Test
    void seeIfEqualsWorksFalseDifferentObject() {
        // Arrange

        EnergyGridRepository testList = new EnergyGridRepository();

        //Act

        boolean actualResult = validRoomRepository.equals(testList); // Needed for SonarQube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSameObject() {
        //Act

        boolean actualResult = validRoomRepository.equals(validRoomRepository); // Required for Sonarqube testing purposes.

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnDiffObject() {
        //Act

        boolean actualResult = validRoomRepository.equals(20D); // Required for Sonarqube testing purposes.

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
    void seeIfCreateRoomReturnsExistingRoom() {
        //Arrange

        Room room2 = new Room("Kitchen3", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");
        Room room = new Room("Kitchen2", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");
        List<Room> roomList = new ArrayList<>();
        roomList.add(validRoom);
        roomList.add(room);
        Mockito.when(roomCrudRepo.findAll()).thenReturn(roomList);
        ArrayList<Double> dimensions = new ArrayList<>();
        dimensions.add(4D);
        dimensions.add(5D);
        dimensions.add(3D);

        //Act

        Room actualResult = validRoomRepository.createRoom("Kitchen", "1st Floor Kitchen", 1, dimensions, "Room1");
        Room actualResult1 = validRoomRepository.createRoom("Kitchen3", "1st Floor Kitchen", 1, dimensions, "Room1");

        //Assert

        assertEquals(validRoom, actualResult);
        assertEquals(room2, actualResult1);

    }

    @Test
    void seeIfEmptyRoomsWorks() {
        List<Room> roomList = new ArrayList<>();
        Mockito.when(roomCrudRepo.findAll()).thenReturn(roomList);
        boolean actualResult = validRoomRepository.isEmptyRooms();
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
        Mockito.when(roomCrudRepo.findAll()).thenReturn(roomList);
        //Act
        DeviceList actualResult = validRoomRepository.getDeviceList();
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
        Mockito.when(roomCrudRepo.findAll()).thenReturn(roomList);
        //Act
        double actualResult = validRoomRepository.getNominalPower();
        //Assert
        assertEquals(21, actualResult, 0.1);
    }

    @Test
    void seeIfSaveRoomReturnsFalse() {
        //Arrange
        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.of(validRoom));
        //Act
        boolean actualResult = validRoomRepository.saveRoom(validRoom);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfFindRoomByIdWorks() {
        //Arrange
        Mockito.when(roomCrudRepo.findById("Kitchen")).thenReturn(Optional.of(validRoom));
        //Act
        Optional<Room> actualResult = validRoomRepository.findRoomByID("Kitchen");
        //Assert
        assertEquals(Optional.of(validRoom), actualResult);
    }


    @Test
    void seeIfCreateRoomSensorWorks() {
        //Arrange
        SensorType sensorType = new SensorType("Temperature", "C");
        //Act
        RoomSensor roomSensor = validRoom.createRoomSensor("T32875", "SensorOne", sensorType.getName(), validDate1);
        //Assert
        assertEquals(firstValidRoomSensor, roomSensor);
    }

    @Test
    void seeIfUpdateHouseRoomWorks() {
        List<Room> rooms = new ArrayList<>();
        validRoom = new Room("Room1", "1st Floor Room", 1, 3, 4, 4, "House 01");
        Room room1 = new Room("Room2", "1st Floor Room", 1, 3, 4, 4, "House 01");
        Room room = new Room("Room1", "1st Floor Room", 1, 3, 4, 4, "House 01");
        rooms.add(room1);
        rooms.add(room);
        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);
        Room actualResult = validRoomRepository.updateHouseRoom(RoomMapper.objectToDTO(validRoom));
        assertEquals(room, actualResult);
    }
}