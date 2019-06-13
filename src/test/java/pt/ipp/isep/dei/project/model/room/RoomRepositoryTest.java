package pt.ipp.isep.dei.project.model.room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.dto.RoomSensorDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMinimalMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomSensorMapper;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.model.repository.RoomCrudRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RoomList tests class.
 */
@ExtendWith(MockitoExtension.class)
class RoomRepositoryTest {

    private Room validRoom;
    private Device validDevice;
    private Reading validReading1;
    private Reading validReading2;

    private RoomSensor firstValidRoomSensor;
    private RoomSensor secondValidRoomSensor;
    private RoomSensor thirdValidRoomSensor;
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018
    private Date validDate3; // Date 30/12/2018
    private Date validDate4; // Date 10/01/2019
    private Date validDate5; // Date 03/09/2018
    @Mock
    private RoomCrudRepo roomCrudRepo;
    @InjectMocks
    private RoomRepository validRoomRepository;
    private List<Room> roomList;

    @BeforeEach
    void arrangeArtifacts() {
        //    MockitoAnnotations.initMocks(this);
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
            validDate3 = validSdf.parse("30/12/2018 00:00:00");
            validDate4 = validSdf.parse("10/01/2019 00:00:00");
            validDate5 = validSdf.parse("30/12/2018 01:10:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        validReading1 = new Reading(20D, validDate1, "C", "SensorOne");
        validReading2 = new Reading(26D, validDate3, "C", "SensorOne");
        firstValidRoomSensor = new RoomSensor("T32875", "SensorOne", "temperature", validDate1);
        firstValidRoomSensor.setActive(true);
        secondValidRoomSensor = new RoomSensor("T32876", "SensorTwo", "temperature", validDate1);
        secondValidRoomSensor.setActive(true);
        thirdValidRoomSensor = new RoomSensor("T32877", "SensorThree", "Rainfall", new Date());
    }

    @Test
    void seeIfGetRoomMaxTempByIDWorks() {
        // Arrange

        Reading validReading3 = new Reading(34D, validDate5, "C", "SensorOne");

        firstValidRoomSensor.addReading(validReading1);
        firstValidRoomSensor.addReading(validReading2);
        firstValidRoomSensor.addReading(validReading3);
        validRoom.addSensor(firstValidRoomSensor);

        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.of(validRoom));

        // Act

        double actualResult = validRoomRepository.getRoomMaxTempById("Kitchen", validDate3);

        // Assert

        assertEquals(34D, actualResult, 0.01);
    }

    @Test
    void seeIfGetRoomMaxTempByIDThrowsException() {
        // Arrange

        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.empty());

        // Act && Assert

        assertThrows(IllegalArgumentException.class,
                () -> validRoomRepository.getRoomMaxTempById("Kitchen", validDate3));

    }

    @Test
    void seeIfGetCurrentRoomTempByRoomIDWorks() {
        // Arrange

        firstValidRoomSensor.addReading(validReading1);
        firstValidRoomSensor.addReading(validReading2);

        validRoom.addSensor(firstValidRoomSensor);

        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.of(validRoom));

        // Act

        double actualResult = validRoomRepository.getCurrentRoomTempByRoomId("Kitchen");

        // Assert

        assertEquals(26D, actualResult, 0.01);
    }

    @Test
    void seeIfGetCurrentRoomTempByRoomIDThrowsException() {
        // Arrange

        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.empty());

        // Act && Assert

        assertThrows(IllegalArgumentException.class,
                () -> validRoomRepository.getCurrentRoomTempByRoomId("Kitchen"));

    }

    @Test
    void seeIfConfigureRoom() {
        // Arrange

        RoomDTOMinimal roomDTO = new RoomDTOMinimal();
        roomDTO.setName("Kitchen");
        roomDTO.setLength(222D);
        roomDTO.setHeight(111D);
        roomDTO.setHeight(666D);
        roomDTO.setFloor(22);

        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.of(validRoom));

        // Act

        boolean actualResult = validRoomRepository.configureRoom(roomDTO);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfConfigureRoomChangesParameters() {
        // Arrange

        RoomDTOMinimal roomDTO = new RoomDTOMinimal();
        roomDTO.setName("Kitchen");
        roomDTO.setLength(222D);
        roomDTO.setWidth(111D);
        roomDTO.setHeight(666D);
        roomDTO.setFloor(22);

        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.of(validRoom));

        // Act

        boolean actualResult = validRoomRepository.configureRoom(roomDTO);

        // Assert

        assertTrue(actualResult);
        assertEquals(222D, validRoom.getLength(), 0.01);
        assertEquals(111D, validRoom.getWidth(), 0.01);
        assertEquals(666D, validRoom.getHeight(), 0.01);
        assertEquals(22, validRoom.getFloor(), 0.01);
    }

    @Test
    void seeIfConfigureRoomWorksWhenRoomIsNotInRepository() {
        // Arrange

        RoomDTOMinimal roomDTO = new RoomDTOMinimal();
        roomDTO.setName("Kitchen");
        roomDTO.setLength(222D);
        roomDTO.setHeight(111D);
        roomDTO.setHeight(666D);
        roomDTO.setFloor(22);

        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.empty());

        // Act

        boolean actualResult = validRoomRepository.configureRoom(roomDTO);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfDeleteRoomWorks() {
        // Arrange

        RoomDTOMinimal roomDTO = RoomMinimalMapper.objectToDtoWeb(validRoom);

        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.of(validRoom));

        // Act

        boolean actualResult = validRoomRepository.deleteRoom(roomDTO);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfDeleteRoomWorksWhenRoomExistsInRepository() {
        // Arrange

        RoomDTOMinimal roomDTO = RoomMinimalMapper.objectToDtoWeb(validRoom);


        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.empty());

        // Act

        boolean actualResult = validRoomRepository.deleteRoom(roomDTO);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddRoomDTOToCrudRepositoryWorks() {
        // Arrange

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom);

        Room validRoom2 = new Room("Living Room", "1st Floor Living Room", 1, 56, 55, 3, "Room1");

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom2);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        // Act

        boolean actualResult = validRoomRepository.addRoomDTOWithoutSensorsAndDevicesToCrudRepository(roomDTO);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddRoomDTOToCrudRepositoryWorksWhenRoomExistsInRepository() {
        // Arrange

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom);

        Room validRoom2 = new Room("Living Room", "1st Floor Living Room", 1, 56, 55, 3, "Room1");

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        rooms.add(validRoom2);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        // Act

        boolean actualResult = validRoomRepository.addRoomDTOWithoutSensorsAndDevicesToCrudRepository(roomDTO);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void seeIfGetAllRoomWebDTOsWorks() {
        // Arrange

        Room validRoom2 = new Room("Living Room", "1st Floor Living Room", 1, 56, 55, 3, "Room1");

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        rooms.add(validRoom2);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        List<RoomDTOMinimal> expectedResult = new ArrayList<>();

        RoomDTOMinimal validDTO1 = new RoomDTOMinimal();
        validDTO1.setName("Kitchen");
        validDTO1.setFloor(1);
        validDTO1.setWidth(4D);
        validDTO1.setLength(5D);
        validDTO1.setHeight(3D);

        RoomDTOMinimal validDTO2 = new RoomDTOMinimal();
        validDTO2.setName("Living Room");
        validDTO2.setFloor(1);
        validDTO2.setWidth(56D);
        validDTO2.setLength(55D);
        validDTO2.setHeight(3D);

        expectedResult.add(validDTO1);
        expectedResult.add(validDTO2);

        // Act

        List<RoomDTOMinimal> actualResult = validRoomRepository.getAllRoomDTOMinimal();

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetAllRoomWebDTOsWorksWhenFindAllReturnsNull() {
        // Arrange

        Mockito.when(roomCrudRepo.findAll()).thenReturn(null);

        List<RoomDTOMinimal> expectedResult = new ArrayList<>();

        // Act

        List<RoomDTOMinimal> actualResult = validRoomRepository.getAllRoomDTOMinimal();

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

        // Act

        boolean actualResult = validRoomRepository.addRoomToCrudRepository(validRoom);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddRoomToCrudRepositoryWorksWhenRoomExistsInRepository() {
        // Arrange

        Room validRoom2 = new Room("Living Room", "1st Floor Living Room", 1, 56, 55, 3, "Room1");

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        rooms.add(validRoom2);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        // Act

        boolean actualResult = validRoomRepository.addRoomToCrudRepository(validRoom);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddRoomDTOWithoutSensorsAndDevicesToCrudRepositoryWorks() {
        // Arrange

        Room validRoom2 = new Room("Living Room", "1st Floor Living Room", 1, 56, 55, 3, "Room1");
        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom);
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom2);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        // Act

        boolean actualResult = validRoomRepository.addRoomDTOWithoutSensorsAndDevicesToCrudRepository(roomDTO);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddRoomDTOWithoutSensorsAndDevicesToCrudRepositoryWorksWhenRoomExistsInRepository() {
        // Arrange

        Room validRoom2 = new Room("Living Room", "1st Floor Living Room", 1, 56, 55, 3, "Room1");
        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom2);

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        rooms.add(validRoom2);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        // Act

        boolean actualResult = validRoomRepository.addRoomDTOWithoutSensorsAndDevicesToCrudRepository(roomDTO);

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

        //
        // Act

        int actualResult = validRoomRepository.addRoomReadings("invalidSensor", readings);

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

        // Act

        int actualResult = validRoomRepository.addRoomReadings("T32875", readings);

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

        // Act

        int actualResult = validRoomRepository.addReadingsToRoomSensor(firstValidRoomSensor, readings);

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

        // Act

        int actualResult = validRoomRepository.addReadingsToRoomSensor(firstValidRoomSensor, readings);

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

        // Act

        int actualResult = validRoomRepository.addReadingsToRoomSensor(firstValidRoomSensor, readings);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfAddReadingsToRoomSensorWorksWhenListIsEmpty() {
        // Arrange

        List<Reading> readings = new ArrayList<>();

        int expectedResult = 0;

        // Act

        int actualResult = validRoomRepository.addReadingsToRoomSensor(firstValidRoomSensor, readings);

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

        // Act

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
        // Arrange

        Mockito.when(roomCrudRepo.findById(validRoom.getId())).thenReturn((Optional.of(validRoom)));

        // Assert

        assertTrue(validRoomRepository.removeRoom(validRoom));
    }

    @Test
    void seeIfDoNotRemoveRoom() {
        // Arrange

        validRoomRepository.updateRoom(validRoom);

        // Assert

        assertFalse(validRoomRepository.removeRoom(validRoom));

    }

    @Test
    void seeIfAddRoomCreate() {
        // Arrange

        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");

        roomList.add(room);

        // Assert

        assertTrue(roomList.contains(room));

    }

    @Test
    void seeIfGetListOfRooms() {
        // Arrange

        List<Room> roomList = new ArrayList<>();

        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");
        roomList.add(room);
        validRoomRepository.saveRoom(room);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(roomList);

        // Assert

        assertEquals(roomList, validRoomRepository.getAllRooms());

    }

    @Test
    void seeIfGetDB() {
        // Arrange

        String mockId = "SensorOne";

        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");

        Mockito.when(roomCrudRepo.findById(mockId)).thenReturn(Optional.of(room));

        // Act

        Room result = validRoomRepository.getRoomByName(mockId);

        // Assert

        assertEquals(result.getId(), room.getId());
        assertEquals(result.getId(), room.getId());

    }

    @Test
    void seeIfGetDBdNoSensor() {
        // Arrange

        String mockId = "SensorOne";

        // Assert

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> validRoomRepository.getRoomByName(mockId));

    }

    @Test
    void seeIfIdExists() {
        // Arrange

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        // Assert

        assertTrue(validRoomRepository.idExists(validRoom.getId()));

    }

    @Test
    void seeIfIdNotExists() {
        // Arrange

        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);
        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        //Assert

        assertFalse(validRoomRepository.idExists("Hall"));

    }

    @Test
    void seeIfBuildRoomListStringWorksEmptyListDB() {
        // Arrange

        List<Room> rooms = new ArrayList<>();

        // Act

        String expectedResult = "Invalid List - List is Empty\n";

        // Assert

        assertEquals(expectedResult, validRoomRepository.buildRoomsAsString(rooms));

    }

    @Test
    void seeIfBuildRoomListStringWorksEmptyList() {
        // Arrange

        List<Room> emptylist = new ArrayList<>();

        // Act

        String expectedResult = "Invalid List - List is Empty\n";

        // Assert

        assertEquals(expectedResult, validRoomRepository.buildSelectRoomsAsString(emptylist));

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

        assertEquals(expectedResult, validRoomRepository.buildSelectRoomsAsString(rooms));

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
        // Act

        boolean actualResult = validRoom.equals(validRoom); // Needed for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetDeviceList() {
        // Arrange

        DeviceList deviceList = new DeviceList();
        validRoom.setDeviceList(deviceList);

        // Assert

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

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void seeIfGetByIndexWorks() {
        // Arrange

        List<Room> rooms = new ArrayList<>();

        Room room = new Room("room", "Double Bedroom", 2, 20, 20, 4, "Room1");
        rooms.add(validRoom);
        rooms.add(room);

        Mockito.when(roomCrudRepo.findAll()).thenReturn((rooms));


        // Act

        Room actualResult1 = validRoomRepository.getRoom(0);
        Room actualResult2 = validRoomRepository.getRoom(1);

        // Assert

        assertEquals(validRoom, actualResult1);
        assertEquals(room, actualResult2);

    }

    @Test
    void getByIndexEmptyRoomList() {
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
        // Arrange

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
        Room roomActual2 = validRoomRepository.createRoom("kitchen", "Ground Floor Kitchen", 0, dimensions, "Room1");

        //Assert

        assertEquals(roomExpected, roomActual1);
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
        // Act

        boolean actualResult = validRoomRepository.equals(validRoomRepository);

        // Assert

        assertTrue(actualResult);

    }


    @Test
    void seeIfEqualsWorksFalseDifferentObject() {
        // Arrange

        EnergyGridRepository testList = new EnergyGridRepository();

        // Act

        boolean actualResult = validRoomRepository.equals(testList); // Needed for SonarQube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSameObject() {
        // Act

        boolean actualResult = validRoomRepository.equals(validRoomRepository); // Required for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);

    }

    @Test
    void seeIfEqualsWorksOnDiffObject() {
        // Act

        boolean actualResult = validRoomRepository.equals(20D); // Required for Sonarqube testing purposes.

        // Assert

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
                "ID: T32876 | SensorTwo | Type: temperature | Active\n" +
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
        // Arrange

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

        // Act

        Room actualResult = validRoomRepository.createRoom("Kitchen", "1st Floor Kitchen", 1, dimensions, "Room1");
        Room actualResult1 = validRoomRepository.createRoom("Kitchen3", "1st Floor Kitchen", 1, dimensions, "Room1");

        // Assert

        assertEquals(validRoom, actualResult);
        assertEquals(room2, actualResult1);

    }

    @Test
    void seeIfEmptyRoomsWorks() {
        // Arrange

        List<Room> roomList = new ArrayList<>();

        Mockito.when(roomCrudRepo.findAll()).thenReturn(roomList);

        // Act

        boolean actualResult = validRoomRepository.isEmptyRooms();

        assertTrue(actualResult);

    }

    @Test
    void seeIfGetDeviceListAppendNoDuplicatesWorks() {
        // Arrange

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

        // Act

        DeviceList actualResult = validRoomRepository.getDeviceList();

        // Assert

        assertEquals(deviceList, actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        // Arrange

        List<Room> roomList = new ArrayList<>();
        DeviceList deviceList = new DeviceList();
        deviceList.add(validDevice);
        roomList.add(validRoom);
        validRoom.setDeviceList(deviceList);
        Mockito.when(roomCrudRepo.findAll()).thenReturn(roomList);

        // Act

        double actualResult = validRoomRepository.getNominalPower();

        // Assert

        assertEquals(21, actualResult, 0.1);
    }

    @Test
    void seeIfSaveRoomReturnsFalse() {

        // Arrange

        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.of(validRoom));

        // Act

        boolean actualResult = validRoomRepository.saveRoom(validRoom);
        // Assert

        assertFalse(actualResult);

    }

    @Test
    void seeIfSaveRoomReturnsWorks() {

        // Arrange

        Mockito.when(roomCrudRepo.findByRoomName("Kitchen")).thenReturn(Optional.empty());

        // Act

        boolean actualResult = validRoomRepository.saveRoom(validRoom);
        // Assert

        assertTrue(actualResult);

    }

    @Test
    void seeIfFindRoomByIdWorks() {
        // Arrange

        Mockito.when(roomCrudRepo.findById("Kitchen")).thenReturn(Optional.of(validRoom));

        // Act

        Optional<Room> actualResult = validRoomRepository.findRoomByID("Kitchen");

        // Assert

        assertEquals(Optional.of(validRoom), actualResult);

    }


    @Test
    void seeIfCreateRoomSensorWorks() {
        //Arrange

        SensorType sensorType = new SensorType("temperature", "C");

        //Act

        RoomSensor roomSensor = validRoom.createRoomSensor("T32875", "SensorOne", sensorType.getName(), validDate1);

        // Assert

        assertEquals(firstValidRoomSensor, roomSensor);

    }

    @Test
    void seeIfUpdateHouseRoomWorks() {
        // Arrange

        List<Room> rooms = new ArrayList<>();

        validRoom = new Room("Room1", "1st Floor Room", 1, 3, 4, 4, "House 01");
        Room room1 = new Room("Room2", "1st Floor Room", 1, 3, 4, 4, "House 01");
        Room room = new Room("Room1", "1st Floor Room", 1, 3, 4, 4, "House 01");
        rooms.add(room1);
        rooms.add(room);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        // Act

        Room actualResult = validRoomRepository.updateHouseRoom(RoomMapper.objectToDTO(validRoom));

        // Assert

        assertEquals(room, actualResult);

    }

    @Test
    void seeIfUpdateHouseRoomThrowsException() {
        // Arrange

        List<Room> rooms = new ArrayList<>();

        validRoom = new Room("Room3", "1st Floor Room", 1, 3, 4, 4, "House 01");
        Room room1 = new Room("Room2", "1st Floor Room", 1, 3, 4, 4, "House 01");
        Room room = new Room("Room1", "1st Floor Room", 1, 3, 4, 4, "House 01");
        rooms.add(room1);
        rooms.add(room);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        // Assert

        assertThrows(RuntimeException.class,
                () -> validRoomRepository.updateHouseRoom(RoomMapper.objectToDTO(validRoom)));
    }

    @Test
    void seeIfAddReadingsToRoomSensorsWork() {
        // Arrange

        int expectedResult = 2;

        String logPath = "dumpFiles/dumpLogFile.html";

        ReadingDTO readingDTO1 = new ReadingDTO();
        readingDTO1.setDate(validDate1);
        readingDTO1.setUnit("C");
        readingDTO1.setValue(45);
        readingDTO1.setSensorId("T32875");

        ReadingDTO readingDTO2 = new ReadingDTO();
        readingDTO2.setDate(validDate3);
        readingDTO2.setUnit("C");
        readingDTO2.setValue(32);
        readingDTO2.setSensorId("T32875");

        List<ReadingDTO> list = new ArrayList<>();
        list.add(readingDTO1);
        list.add(readingDTO2);

        validRoom.addSensor(firstValidRoomSensor);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(roomList);

        // Act

        int actualResult = validRoomRepository.addReadingsToRoomSensors(list, logPath);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfAddReadingsToRoomSensorsWithSameDateReadingsWork() {
        // Arrange

        int expectedResult = 1;

        String logPath = "dumpFiles/dumpLogFile.html";

        ReadingDTO readingDTO1 = new ReadingDTO();
        readingDTO1.setDate(validDate3);
        readingDTO1.setUnit("C");
        readingDTO1.setValue(45);
        readingDTO1.setSensorId("T32875");

        ReadingDTO readingDTO2 = new ReadingDTO();
        readingDTO2.setDate(validDate3);
        readingDTO2.setUnit("C");
        readingDTO2.setValue(32);
        readingDTO2.setSensorId("T32875");

        List<ReadingDTO> list = new ArrayList<>();
        list.add(readingDTO1);
        list.add(readingDTO2);

        validRoom.addSensor(firstValidRoomSensor);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(roomList);

        // Act

        int actualResult = validRoomRepository.addReadingsToRoomSensors(list, logPath);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfAddReadingsToRoomSensorsBeforeStartDateWork() {
        // Arrange

        int expectedResult = 0;

        String logPath = "dumpFiles/dumpLogFile.html";

        firstValidRoomSensor.setDateStartedFunctioning(validDate3);

        ReadingDTO readingDTO1 = new ReadingDTO();
        readingDTO1.setDate(validDate1);
        readingDTO1.setUnit("C");
        readingDTO1.setValue(45);
        readingDTO1.setSensorId("T32875");

        ReadingDTO readingDTO2 = new ReadingDTO();
        readingDTO2.setDate(validDate1);
        readingDTO2.setUnit("C");
        readingDTO2.setValue(32);
        readingDTO2.setSensorId("T32875");

        List<ReadingDTO> list = new ArrayList<>();
        list.add(readingDTO1);
        list.add(readingDTO2);

        validRoom.addSensor(firstValidRoomSensor);

        Mockito.when(roomCrudRepo.findAll()).thenReturn(roomList);

        // Act

        int actualResult = validRoomRepository.addReadingsToRoomSensors(list, logPath);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfAddReadingsToRoomSensorsWithoutReadingsWork() {
        // Arrange

        int expectedResult = 0;

        String logPath = "dumpFiles/dumpLogFile.html";

        firstValidRoomSensor.setDateStartedFunctioning(validDate3);

        List<ReadingDTO> list = new ArrayList<>();

        validRoom.addSensor(firstValidRoomSensor);

        // Act

        int actualResult = validRoomRepository.addReadingsToRoomSensors(list, logPath);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetTemperatureReadingsBetweenDatesWork() {
        // Arrange

        Reading reading1 = new Reading(45, validDate1, "C", "T32875");
        Reading reading2 = new Reading(33, validDate1, "C", "T32875");

        firstValidRoomSensor.addReading(reading1);
        secondValidRoomSensor.addReading(reading2);

        validRoom.addSensor(firstValidRoomSensor);
        validRoom.addSensor(secondValidRoomSensor);

        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading1);
        expectedResult.add(reading2);
        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom);

        // Act

        List<Reading> actualResult = validRoomRepository.getTemperatureReadingsBetweenDates(validDate1, validDate4, roomDTO);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetTemperatureReadingsBetweenDatesNotBetweenDates() {
        // Arrange

        Reading reading1 = new Reading(45, validDate1, "C", "T32875");
        Reading reading2 = new Reading(33, validDate1, "C", "T32875");

        firstValidRoomSensor.addReading(reading1);
        secondValidRoomSensor.addReading(reading2);

        validRoom.addSensor(firstValidRoomSensor);
        validRoom.addSensor(secondValidRoomSensor);

        List<Reading> expectedResult = new ArrayList<>();
        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom);

        // Act

        List<Reading> actualResult = validRoomRepository.getTemperatureReadingsBetweenDates(validDate4, validDate4, roomDTO);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetAllDTOWebInformationWorksEmptyList() {
        // Arrange

        List<Room> rooms = new ArrayList<>();
        List<RoomDTOMinimal> roomDTOMinimals = new ArrayList<>();

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        // Act

        List<RoomDTOMinimal> result = validRoomRepository.getAllDTOWebInformation();

        // Assert

        assertEquals(result, roomDTOMinimals);
    }

    @Test
    void seeIfGetAllDTOWebInformationWorks() {
        // Arrange

        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        List<RoomDTOMinimal> roomDTOMinimals = new ArrayList<>();
        roomDTOMinimals.add(RoomMinimalMapper.objectToDtoWeb(room));

        Mockito.when(roomCrudRepo.findAll()).thenReturn(rooms);

        // Act

        List<RoomDTOMinimal> result = validRoomRepository.getAllDTOWebInformation();

        // Assert

        assertEquals(result, roomDTOMinimals);

    }

    @Test
    void seeIfDoNotRemoveRoomDTO() {
        // Arrange

        validRoomRepository.updateDTORoom(RoomMapper.objectToDTO(validRoom));

        // Assert

        assertFalse(validRoomRepository.removeRoom(validRoom));

    }

    @Test
    void seeIfRemoveSensorDTOWorks() {
        // Arrange

        RoomSensor roomSensor = new RoomSensor("test", "test", "test", new Date());
        validRoom.addSensor(roomSensor);

        // Assert

        assertTrue(validRoomRepository.removeSensorDTO(RoomMapper.objectToDTO(validRoom), "test"));

    }

    @Test
    void seeIfRemoveSensorDTOFails() {
        // Arrange


        // Assert

        assertFalse(validRoomRepository.removeSensorDTO(RoomMapper.objectToDTO(validRoom), "test"));

    }

    @Test
    void seeIfGetRoomDTOByNameWorks() {
        // Arrange

        String mockId = "SensorOne";

        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");

        Mockito.when(roomCrudRepo.findById(mockId)).thenReturn(Optional.of(room));

        // Act

        RoomDTO result = validRoomRepository.getRoomDTOByName(mockId);

        // Assert

        assertEquals(result.getName(), room.getId());
    }

    @Test
    void seeIfGetRoomDTOByNameFails() {
        // Arrange

        String mockId = "SensorOne";

        // Assert

        Assertions.assertThrows(IllegalArgumentException.class, () -> validRoomRepository.getRoomDTOByName(mockId));

    }

    @Test
    void seeIfAddSensorDTOWorks() {
        // Arrange

        RoomSensor roomSensor = new RoomSensor("test", "test", "test", new Date());

        // Assert

        assertTrue(validRoomRepository.addSensorDTO(RoomMapper.objectToDTO(validRoom), RoomSensorMapper.objectToDTO(roomSensor)));

    }

    @Test
    void seeIfAddSensorDTOFails() {
        // Arrange

        RoomSensor roomSensor = new RoomSensor("test", "test", "test", new Date());
        validRoom.addSensor(roomSensor);

        // Assert

        assertFalse(validRoomRepository.addSensorDTO(RoomMapper.objectToDTO(validRoom), RoomSensorMapper.objectToDTO(roomSensor)));

    }

    @Test
    void seeIfIsRoomSensorDTOValidWorks() {
        // Arrange

        RoomSensor roomSensor = new RoomSensor("test", "test", "test", new Date());

        // Assert

        assertTrue(validRoomRepository.isRoomSensorDTOValid(RoomSensorMapper.objectToDTO(roomSensor)));

    }

    @Test
    void seeIfIsRoomSensorDTOValidFailsNullName() {
        // Arrange

        RoomSensor roomSensor = new RoomSensor("test", "test", "test", new Date());
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(roomSensor);
        roomSensorDTO.setName(null);

        // Assert

        assertFalse(validRoomRepository.isRoomSensorDTOValid(roomSensorDTO));

    }

    @Test
    void seeIfIsRoomSensorDTOValidFailsNullSensorId() {
        // Arrange

        RoomSensor roomSensor = new RoomSensor("test", "test", "test", new Date());
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(roomSensor);
        roomSensorDTO.setSensorId(null);

        // Assert

        assertFalse(validRoomRepository.isRoomSensorDTOValid(roomSensorDTO));

    }

    @Test
    void seeIfIsRoomSensorDTOValidFailsNullDate() {
        // Arrange

        RoomSensor roomSensor = new RoomSensor("test", "test", "test", new Date());
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(roomSensor);
        roomSensorDTO.setDateStartedFunctioning(null);

        // Assert

        assertFalse(validRoomRepository.isRoomSensorDTOValid(roomSensorDTO));

    }

    @Test
    void seeIfIsRoomSensorDTOValidFailsNullSensorType() {
        // Arrange

        RoomSensor roomSensor = new RoomSensor("test", "test", "test", new Date());
        RoomSensorDTO roomSensorDTO = RoomSensorMapper.objectToDTO(roomSensor);
        roomSensorDTO.setTypeSensor(null);

        // Assert

        assertFalse(validRoomRepository.isRoomSensorDTOValid(roomSensorDTO));

    }

}