package pt.ipp.isep.dei.project.controller.controllercli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


/**
 * HouseConfigurationController tests class.
 */
@ExtendWith(MockitoExtension.class)
class HouseConfigurationControllerTest {

    // Common artifacts for testing in this class.
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";

    private House validHouse;
    private AreaType validAreaType;
    private GeographicArea validGeographicArea;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private SensorTypeRepository sensorTypeRepository;
    @InjectMocks
    private HouseConfigurationController controller;

    @BeforeEach
    void arrangeArtifacts() {
        List<String> deviceTypeList = new ArrayList<>();
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal");
        validHouse = new House("ISEP", address,
                new Local(20, 20, 20), 60, 180,
                deviceTypeList);
        validAreaType = new AreaType("Cidade");
        validGeographicArea = new GeographicArea("Porto", validAreaType.getName(),
                2, 3, new Local(4, 4, 100));
        validGeographicArea.setId(666L);
        validHouse.setMotherAreaID(validGeographicArea.getId());
        deviceTypeList.add(PATH_TO_FRIDGE);
    }


    //USER STORY 105


    @Test
    void seeIfGetHouseName() {
        //Act

        String actualResult = controller.getHouseId(validHouse);

        // Assert

        assertEquals("ISEP", actualResult);
    }


    // US108

    @Test
    void seeIfPrintsRoomList() {
        // Arrange

        List<Room> rooms = new ArrayList<>();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act
        Mockito.when(roomRepository.buildRoomsAsString(rooms)).thenReturn(expectedResult);
        String result = controller.buildRoomsString(rooms);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void createsRoom() {
        // Arrange

        ArrayList<Double> dimensions = new ArrayList<>();
        dimensions.add(10D);
        dimensions.add(15D);
        dimensions.add(10D);
        Room room1 = new Room("Kitchen", "Not equipped Kitchen", 1, dimensions, "Room1");
        //Room room2 = new Room("Room", "Double Bedroom", 1, dimensions, "Room1");
        //Room room3 = new Room("Kitchen", "Fully Equipped Kitchen", 1, dimensions, "Room1");
        // Act
        Mockito.when(roomRepository.createRoom("Kitchen", "Not equipped Kitchen", 1, dimensions, "Room1")).thenReturn(room1);
        Room actualResult1 = controller.createNewRoom("Kitchen", "Not equipped Kitchen", 1, dimensions, "Room1");
        Mockito.when(roomRepository.createRoom("Room", "Double Bedroom", 1, dimensions, "Room1")).thenReturn(room1);
        Room actualResult2 = controller.createNewRoom("Room", "Double Bedroom", 1, dimensions, "Room1");
        Mockito.when(roomRepository.createRoom("Kitchen", "Fully Equipped Kitchen", 1, dimensions, "Room1")).thenReturn(room1);
        Room actualResult3 = controller.createNewRoom("Kitchen", "Fully Equipped Kitchen", 1, dimensions, "Room1");

        // Assert

        assertNotNull(actualResult1);
        assertNotNull(actualResult2);
        assertNotNull(actualResult3);
    }

    @Test
    void addsRoom() {
        //Arrange
        Room room1 = new Room("Kitchen", "Not equipped Kitchen", 1, 10, 15, 10, "Room1");
        Room room2 = new Room("Room", "Double Bedroom", 1, 10, 15, 10, "Room1");

        // Act
        Mockito.when(roomRepository.saveRoom(room1)).thenReturn(true);
        boolean actualResult1 = controller.addRoomToHouse(room1);
        Mockito.when(roomRepository.saveRoom(room2)).thenReturn(true);
        boolean actualResult2 = controller.addRoomToHouse(room2);

        // Assert
        assertTrue(actualResult1);
        assertTrue(actualResult2);
    }

    @Test
    void seeIfSetHouseAddress() {
        //Act

        controller.setHouseAddress("Rua do ISEP", "431", "4400", "City", "Portugal", validHouse);

        // Assert

        assertEquals(validHouse.getAddress(), new Address("Rua do ISEP", "431", "4400", "City", "Portugal"));
    }

    @Test
    void seeIfSetHouseLocal() {
        //Act

        controller.setHouseLocal(10, 51, 2, validHouse);

        // Assert

        assertEquals(validHouse.getLocation(), new Local(10, 51, 2));
    }


    @Test
    void seeIfSetAndGetHouseMotherAreaWorks() {
        //Arrange
        controller.setHouseMotherArea(validHouse, validGeographicArea);
        Long expected = 666L;
        //Act
        Long actualResult = validHouse.getMotherAreaID();
        //Assert
        assertEquals(expected, actualResult);
    }

    @Test
    void seeIfIsMotherAreaNullBothConditions() {
        // Act
        boolean actualResult1 = validHouse.isMotherAreaNull();
        controller.setHouseMotherArea(validHouse, new GeographicArea());
        boolean actualResult2 = validHouse.isMotherAreaNull();
        // Assert
        assertFalse(actualResult1);
        assertTrue(actualResult2);
    }

//    @Test
//    void seeIfReadSensorsWorks() {
//        // Arrange
//
//        String filePath = "src/test/resources/houseSensorFiles/DataSet_sprint06_HouseSensors.json";
//
//        // Mock the checking for Rooms
//
//        Room B106 = new Room("B106", "Classroom", 3, 20, 20, 20,
//                "Mock", "Mock");
//        Optional<Room> optionalRoomB106 = Optional.of(B106);
//        Mockito.when(mockRoomRepository.findRoomByID("B106")).thenReturn(optionalRoomB106);
//
//        Room B109 = new Room("B109", "Classroom", 3, 20, 20, 20,
//                "Mock", "Mock");
//        Optional<Room> optionalRoomB109 = Optional.of(B109);
//        Mockito.when(mockRoomRepository.findRoomByID("B109")).thenReturn(optionalRoomB109);
//
//        Room B107 = new Room("B107", "Classroom", 3, 20, 20, 20,
//                "Mock", "Mock");
//        Optional<Room> optionalRoomB107 = Optional.of(B107);
//        Mockito.when(mockRoomRepository.findRoomByID("B107")).thenReturn(optionalRoomB107);
//
//        Optional<Room> optionalRoomB405 = Optional.empty();
//        Mockito.when(mockRoomRepository.findRoomByID("B405")).thenReturn(optionalRoomB405);
//
//        // Ignore the .saveSensor call, which is void.
//
//        when(roomSensorRepository.save(isA(RoomSensor.class))).thenReturn(null);
//
//        // Expected result
//
//        int[] expectedResult = new int[2];
//        expectedResult[0] = 3;
//        expectedResult[1] = 1;
//
//        // Act
//
//        int[] actualResult = controllercli.readSensors(filePath, mockRoomRepository);
//
//        // Assert
//
//        assertArrayEquals(expectedResult, actualResult);
//    }

    @Test
    void seeIfReadSensorsWorksEmptyDB() {
        // Arrange

        String filePath = "src/test/resources/houseSensorFiles/DataSet_sprint06_HouseSensors.json";

        int[] expectedResult = new int[2];

        // Act
        Mockito.when(roomRepository.isEmptyRooms()).thenReturn(true);
        int[] actualResult = controller.readSensors(filePath);

        // Assert

        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfReadSensorsWorks() {
        // Arrange

        String filePath = "src/test/resources/houseSensorFiles/DataSet_sprint06_HouseSensors.json";

        int[] expectedResult = new int[2];
        expectedResult[1] = 4;

        SensorType sensorType = new SensorType("temperature", "C");

        // Act

        Mockito.when(roomRepository.isEmptyRooms()).thenReturn(false);
        Mockito.when(sensorTypeRepository.getTypeSensorByName("temperature", "C")).thenReturn(sensorType);

        int[] actualResult = controller.readSensors(filePath);

        // Assert

        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfReadSensorsWorksInvalidFile() {
        // Arrange

        String filePath = "houseFiles/DataSet_sprint06_HouseData.json";
        Mockito.when(roomRepository.isEmptyRooms()).thenReturn(false);

        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> controller.readSensors(filePath));
    }

    @Test
    void seeIfAddSensorsToModelRoomsWorksWhenRoomDoesNotExist() {
        // Arrange

        List<RoomSensorDTO> sensorDTOS = new ArrayList<>();

        RoomSensorDTO dto1 = new RoomSensorDTO();
        dto1.setName("SensorDTO1");
        dto1.setId("ID1");
        dto1.setRoomID("Room1");

        sensorDTOS.add(dto1);

        Mockito.when(roomRepository.findRoomByID("Room1")).thenReturn(Optional.empty());

        int[] expectedResult = new int[2];
        expectedResult[1] = 1;

        //Act

        int[] actualResult = controller.addSensorsToModelRooms(sensorDTOS);

        // Assert

        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddSensorsToModelRoomsWorks() {
        // Arrange
        List<RoomSensorDTO> sensorDTOS = new ArrayList<>();

        RoomSensorDTO dto2 = new RoomSensorDTO();
        dto2.setName("SensorDTO2");
        dto2.setId("ID2");
        dto2.setRoomID("Room2");
        dto2.setTypeSensor("temperature");
        dto2.setUnits("C");
        dto2.setActive(true);
        dto2.setReadingList(new ArrayList<>());
        dto2.setDateStartedFunctioning("01/04/2018 00:00:00");

        sensorDTOS.add(dto2);

        Room validRoom = new Room("Room2", "",1, 2D,3D,5D, "HouseID");


        Mockito.when(roomRepository.findRoomByID("Room2")).thenReturn(Optional.of(validRoom));

        int[] expectedResult = new int[2];
        expectedResult[0] = 1;

        //Act

        int[] actualResult = controller.addSensorsToModelRooms(sensorDTOS);

        // Assert

        assertArrayEquals(expectedResult, actualResult);
    }
}