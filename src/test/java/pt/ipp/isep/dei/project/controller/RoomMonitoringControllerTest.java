package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.RoomRepository;
import pt.ipp.isep.dei.project.repository.RoomSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * RoomMonitoringController tests class.
 */
@ExtendWith(MockitoExtension.class)
class RoomMonitoringControllerTest {

    // Common artifacts for testing in this class.

    private RoomMonitoringController controller = new RoomMonitoringController();
    private RoomService roomService;
    private Room validRoom1;
    private RoomDTO validRoomDTO;
    private List<Room> rooms;


    @Mock
    RoomSensorRepository roomSensorRepository;

    @Mock
    SensorTypeRepository sensorTypeRepository;

    @Mock
    RoomRepository roomRepository;

    @BeforeEach
    void arrangeArtifacts() {
        this.roomService = new RoomService(roomRepository, roomSensorRepository, sensorTypeRepository);
        validRoom1 = new Room("Bedroom", "Double Bedroom", 2, 15, 15, 10, "Room1", "Grid1");
        RoomService validRoomService = new RoomService(roomRepository, roomSensorRepository, sensorTypeRepository);
        this.rooms = new ArrayList<>();
        rooms.add(validRoom1);
        roomService = new RoomService(roomRepository, roomSensorRepository, sensorTypeRepository);
        validRoomDTO = RoomMapper.objectToDTO(validRoom1);

    }


    @Test
    void seeIfGetCurrentRoomTemperatureThrowsException() {
        // Act

        List<Room> mockList = new ArrayList<>();
        mockList.add(validRoom1);
        Mockito.when(roomRepository.findAll()).thenReturn(mockList);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.getCurrentRoomTemperature(validRoomDTO, roomService));

        // Assert

        assertEquals("There aren't any temperature readings available.", exception.getMessage());

    }


    @Test
    void getRoomName() {
        // Arrange

        String expectedResult = "Bedroom";
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom1);

        // Act

        Mockito.when(roomRepository.findAll()).thenReturn(rooms);
        String actualResult = controller.getRoomName(validRoomDTO, roomService);

        // Assert

        assertEquals(expectedResult, actualResult);

    }
    @Test
    void seeIfGetDayMaxTemperatureWorks() {
        // Arrange

        Room testRoom = new Room("Kitchen", "Where we cook", 0, 15, 15, 15,
                "ISEP", "G001");
        RoomSensor testSensor = new RoomSensor("S001", "TempOne", new SensorType("temperature", "Celsius"),
                new GregorianCalendar(2018, Calendar.JULY, 3).getTime(), "Kitchen");
        Reading testReading = new Reading(11, new GregorianCalendar(2018, Calendar.JULY, 3).getTime(),
                "C", "S001");
        Reading secondTestReading = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 3).getTime(),
                "C", "S001");
        Reading thirdTestReading = new Reading(11, new GregorianCalendar(2018, Calendar.JULY, 3).getTime(),
                "C", "S001");
        List<Room> mockRepositoryRooms = new ArrayList<>();
        mockRepositoryRooms.add(testRoom);
        List<Reading> testReadingList = new ArrayList<>();
        testReadingList.add(testReading);
        testReadingList.add(secondTestReading);
        testReadingList.add(thirdTestReading);
        testSensor.setReadings(testReadingList);
        testRoom.addSensor(testSensor);
        double expectedResult = 17.0;

        RoomDTO testDTO = RoomMapper.objectToDTO(testRoom);
        Mockito.when(roomService.getAllRooms()).thenReturn(mockRepositoryRooms);

        // Act

        double actualResult = controller.getDayMaxTemperature(testDTO, new GregorianCalendar(2018, Calendar.JULY,
                3).getTime(), roomService);

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);


    }
}