package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.RoomRepository;
import pt.ipp.isep.dei.project.repository.RoomSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * RoomMonitoringController tests class.
 */
@ExtendWith(MockitoExtension.class)
class RoomMonitoringControllerTest {

    // Common artifacts for testing in this class.

    private House validHouse;
    private List<String> deviceTypeString;
    private RoomMonitoringController controller = new RoomMonitoringController();
    private SimpleDateFormat validSdf; // SimpleDateFormat dd/MM/yyyy HH:mm:ss
    private RoomService roomService;
    private Room validRoom1;
    private RoomDTO validRoomDTO;
    private List<Room> rooms;
    private GeographicArea validArea;
    private AreaSensor validAreaSensor;
    private Date validDate1;
    private Date validDate2;
    private Date validDate3;


    @Mock
    RoomSensorRepository roomSensorRepository;

    @Mock
    SensorTypeRepository sensorTypeRepository;

    @Mock
    RoomRepository roomRepository;

    @BeforeEach
    void arrangeArtifacts() {
        validSdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Datas desorganizadas, para testar noção de first/last
            validDate1 = validSdf.parse("01/02/2018");
            validDate2 = validSdf.parse("10/02/2018");
            validDate3 = validSdf.parse("20/02/2018");

        } catch (ParseException c) {
            c.printStackTrace();
        }
        validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("temperature", "Celsius"), new Local(10, 10, 10), validDate1, 6008L);
        validAreaSensor.setActive(true);
        Reading areaReading1 = new Reading(20D, validDate1, "C", "sensorID");
        Reading areaReading2 = new Reading(21D, validDate2, "C", "sensorID");
        Reading areaReading3 = new Reading(18D, validDate3, "C", "sensorID");
        List<Reading> areaReadings = new ArrayList<>();
        areaReadings.add(areaReading1);
        areaReadings.add(areaReading2);
        areaReadings.add(areaReading3);
        validAreaSensor.setReadings(areaReadings);
        validArea = new GeographicArea("Europe", new AreaType("Continent"), 3500, 3000,
                new Local(20, 12, 33));
        validArea.addSensor(validAreaSensor);
        deviceTypeString = new ArrayList<>();
        this.validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        this.validHouse.setMotherArea(validArea);
        this.roomService = new RoomService(roomRepository, roomSensorRepository, sensorTypeRepository);
        validRoom1 = new Room("Bedroom", "Double Bedroom", 2, 15, 15, 10, "Room1", "Grid1");
        RoomService validRoomService = new RoomService(roomRepository, roomSensorRepository, sensorTypeRepository);
        this.rooms = new ArrayList<>();
        rooms.add(validRoom1);
        roomService = new RoomService(roomRepository, roomSensorRepository, sensorTypeRepository);
        validRoomDTO = RoomMapper.objectToDTO(validRoom1);
        validRoomDTO.setHouseId(validHouse.getId());
    }


    @Test
    void seeIfGetCurrentRoomTemperatureThrowsException() {
        // Arrange

        List<Room> mockList = new ArrayList<>();
        mockList.add(validRoom1);

        // Act


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

    @Test
    void seeIfGetInstantsAboveComfortIntervalWorks(){
        // Arrange

        String expectedResult = "Instants in which the readings are above comfort temperature:\n" +
                "0) Instant: Thu Feb 01 00:00:00 GMT 2018\n" +
                "   Temperature value: 31.0\n" +
                "   Difference from outside day average: + 11.0 Cº\n" +
                "1) Instant: Tue Feb 20 00:00:00 GMT 2018\n" +
                "   Temperature value: 31.0\n" +
                "   Difference from outside day average: + 13.0 Cº\n" +
                "---\n";

        int category = 0;

        Date startDate  = validDate1;
        Date endDate = validDate3;

        Reading reading1 = new Reading(31, validDate1, "C", "Test");
        Reading reading2 = new Reading(20, validDate2, "C", "Test1");
        Reading reading3 = new Reading(31, validDate3, "C", "Test");

        List<Reading> readings = new ArrayList<>();

        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading3);

        RoomSensor roomSensor = new RoomSensor("T32875", "SensOne", new SensorType("Temperature", "Celsius"), validDate1, "RoomAD");
        SensorType sensorType = new SensorType("temperature", "Celsius");
        roomSensor.setSensorType(sensorType);
        roomSensor.setReadings(readings);
        roomSensor.setRoomId(validRoom1.getId());

        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(roomSensor);
        validRoom1.setRoomSensors(roomSensors);

        reading1.setSensorID(roomSensor.getId());
        reading2.setSensorID(roomSensor.getId());
        reading3.setSensorID(roomSensor.getId());

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        // Act

        String actualResult = controller.getInstantsAboveComfortInterval(validHouse, category, roomDTO, startDate, endDate);

        // Assert

        assertEquals(expectedResult, actualResult);

    }
}