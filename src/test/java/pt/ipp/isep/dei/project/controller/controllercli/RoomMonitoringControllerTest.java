package pt.ipp.isep.dei.project.controller.controllercli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.bridgeservices.GeographicAreaHouseService;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.room.RoomSensor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RoomMonitoringController tests class.
 */
@ExtendWith(MockitoExtension.class)
class RoomMonitoringControllerTest {

    // Common artifacts for testing in this class.

    private House validHouse;

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private GeographicAreaHouseService geographicAreaHouseService;
    private Room validRoom1;
    private RoomDTO validRoomDTO;

    private Date validDate1;
    private Date validDate2;
    private Date validDate3;
    private Date validStartDate;
    private Date validEndingDate;
    private Date roomReadingDate1;
    private Date roomReadingDate2;
    private Date roomReadingDate3;
    private RoomSensor firstValidRoomSensor;

    @InjectMocks
    private RoomMonitoringController controller;

    @BeforeEach
    void arrangeArtifacts() {
        // SimpleDateFormat dd/MM/yyyy HH:mm:ss
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy");
        validSdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat validSdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        validSdf2.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat readingSD = new SimpleDateFormat("yyyy-MM-dd");
        readingSD.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat readingSD2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        readingSD2.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            validDate1 = validSdf.parse("01/02/2018");
            validDate2 = validSdf.parse("10/02/2018");
            validDate3 = validSdf.parse("20/02/2018");
            roomReadingDate1 = validSdf.parse("01/12/2018");
            roomReadingDate2 = validSdf.parse("10/12/2018");
            roomReadingDate3 = validSdf.parse("20/12/2018");
            validStartDate = readingSD.parse("2017-10-03");
            validEndingDate = readingSD.parse("2019-10-03");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        List<String> deviceTypeString = new ArrayList<>();
        this.validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        this.validHouse.setMotherAreaID(111L);
        validRoom1 = new Room("Bedroom", "Double Bedroom", 2, 15, 15, 10, "Room1");
        validRoomDTO = RoomMapper.objectToDTO(validRoom1);
        validRoomDTO.setHouseId(validHouse.getId());
        firstValidRoomSensor = new RoomSensor("T32875", "SensorOne", "temperature", validDate1);
        firstValidRoomSensor.setActive(true);
    }

    @Test
    void seeIfGetDayMaxTemperatureWorks() {
        // Arrange

        Date validDate1 = new Date();
        Date validDate2 = new Date();
        Date validDate3 = new Date();

        SimpleDateFormat validSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'", Locale.ENGLISH);
        try {
            validDate1 = validSdf.parse("2018-07-03T10:50:00+00:00");
            validDate2 = validSdf.parse("2018-07-03T13:15:00+00:00");
            validDate3 = validSdf.parse("2018-07-03T05:35:00+00:00");

        } catch (ParseException c) {
            c.printStackTrace();
        }

        Room testRoom = new Room("Kitchen", "Where we cook", 0, 15, 15, 15,
                "ISEP");
        RoomSensor testSensor = new RoomSensor("S001", "TempOne", "temperature", validDate1);
        Reading testReading = new Reading(11, validDate1,
                "C", "S001");
        Reading secondTestReading = new Reading(17, validDate2,
                "C", "S001");
        Reading thirdTestReading = new Reading(11, validDate3,
                "C", "S001");
        List<Reading> testReadingList = new ArrayList<>();
        testReadingList.add(testReading);
        testReadingList.add(secondTestReading);
        testReadingList.add(thirdTestReading);
        testSensor.setReadings(testReadingList);
        testRoom.addSensor(testSensor);
        double expectedResult = 17.0;

        RoomDTO testDTO = RoomMapper.objectToDTO(testRoom);

        // Act
        Mockito.when(roomRepository.updateHouseRoom(testDTO)).thenReturn(RoomMapper.dtoToObject(testDTO));
        double actualResult = controller.getDayMaxTemperature(testDTO, validDate1);

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);

    }


    @Test
    void seeIfGetCurrentRoomTemperatureThrowsException() {
        // Arrange

        // Act
        Mockito.when(roomRepository.updateHouseRoom(validRoomDTO)).thenReturn(RoomMapper.dtoToObject(validRoomDTO));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.getCurrentRoomTemperature(validRoomDTO));

        // Assert

        assertEquals("There aren't any temperature readings available.", exception.getMessage());

    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorks() {
        //Arrange

        RoomSensor roomSensor = new RoomSensor("S1", "Room Temperature Sensor", "temperature", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        validRoom1.addSensor(roomSensor);
        Reading reading = new Reading(21, Calendar.getInstance().getTime(), "C", roomSensor.getId());
        List<Reading> list = new ArrayList<>();
        list.add(reading);
        roomSensor.setReadings(list);

        //Act
        Mockito.when(roomRepository.updateHouseRoom(RoomMapper.objectToDTO(validRoom1))).thenReturn(validRoom1);
        double actualResult = controller.getCurrentRoomTemperature(RoomMapper.objectToDTO(validRoom1));

        //Assert

        assertEquals(21.0, actualResult, 0.1);
    }


    @Test
    void getRoomName() {
        // Arrange

        String expectedResult = "Bedroom";

        // Act
        Mockito.when(roomRepository.updateHouseRoom(validRoomDTO)).thenReturn(RoomMapper.dtoToObject(validRoomDTO));
        String actualResult = controller.getRoomName(validRoomDTO);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfBuildReadingsOutputWorks() {
        // Arrange

        List<Reading> list = new ArrayList<>();

        Reading reading1 = new Reading(11, validDate1,
                "C", "S001");
        Reading reading2 = new Reading(17, validDate2,
                "C", "S001");
        Reading reading3 = new Reading(11, validDate3,
                "C", "S001");


        list.add(reading1);
        list.add(reading2);
        list.add(reading3);

        String expectedResult = "Instants in which the readings are above comfort temperature:\n" +
                "0) Instant: 2018-02-01 12.00.00   Temperature value: 11.0\n" +
                "1) Instant: 2018-02-10 12.00.00   Temperature value: 17.0\n" +
                "2) Instant: 2018-02-20 12.00.00   Temperature value: 11.0\n" +
                "--------------------------------------\n";

        // Act

        String actualResult = controller.buildReadingsOutput(list, "Instants in which the readings are above comfort temperature:\n");

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfBuildReadingsOutputEmptyListWorks() {
        // Arrange

        List<Reading> list = new ArrayList<>();

        String expectedResult = "Instants in which the readings are above comfort temperature:\n" +
                "--------------------------------------\n";

        // Act

        String actualResult = controller.buildReadingsOutput(list, "Instants in which the readings are above comfort temperature:\n");

        // Assert

        assertEquals(expectedResult, actualResult);

    }


    @Test
    void seeIfGetInstantsAboveComfortIntervalCategoryI_II_IIIWithoutOutOfIntervalReadingsWorks() {
        // Arrange

        String expectedResult = "For the given category, in the given interval, there were no temperature readings above the max comfort temperature.";

        int category1 = 0;
        int category2 = 1;
        int category3 = 2;

        Reading reading1 = new Reading(15, validDate1, "C", "Test");
        Reading reading2 = new Reading(20, validDate2, "C", "Test1");
        Reading reading3 = new Reading(17, validDate3, "C", "Test");

        List<Reading> expectedReadings = new ArrayList<>();
        List<Reading> readings = new ArrayList<>();

        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading3);

        firstValidRoomSensor.setReadings(readings);

        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(firstValidRoomSensor);
        validRoom1.setRoomSensors(roomSensors);

        reading1.setSensorID(firstValidRoomSensor.getId());
        reading2.setSensorID(firstValidRoomSensor.getId());
        reading3.setSensorID(firstValidRoomSensor.getId());

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        // Act
        Mockito.when(roomRepository.getTemperatureReadingsBetweenDates(validStartDate, validEndingDate, validRoom1)).thenReturn(readings);
        Mockito.when(geographicAreaHouseService.getReadingsAboveCategoryIILimit(readings, validHouse)).thenReturn(expectedReadings);
        Mockito.when(roomRepository.getTemperatureReadingsBetweenDates(validStartDate, validEndingDate, validRoom1)).thenReturn(readings);
        Mockito.when(geographicAreaHouseService.getReadingsAboveCategoryILimit(readings, validHouse)).thenReturn(expectedReadings);
        Mockito.when(roomRepository.getTemperatureReadingsBetweenDates(validStartDate, validEndingDate, validRoom1)).thenReturn(readings);
        Mockito.when(geographicAreaHouseService.getReadingsAboveCategoryIIILimit(readings, validHouse)).thenReturn(expectedReadings);
        String actualResult1 = controller.getInstantsAboveComfortInterval(validHouse, category1, roomDTO, validStartDate, validEndingDate);
        String actualResult2 = controller.getInstantsAboveComfortInterval(validHouse, category2, roomDTO, validStartDate, validEndingDate);
        String actualResult3 = controller.getInstantsAboveComfortInterval(validHouse, category3, roomDTO, validStartDate, validEndingDate);

        // Assert

        assertEquals(expectedResult, actualResult1);
        assertEquals(expectedResult, actualResult2);
        assertEquals(expectedResult, actualResult3);

    }

    @Test
    void seeIfGetInstantsAboveComfortIntervalCategoryIWorks() {
        // Arrange

        String expectedResult = "Instants in which the readings are above comfort temperature:\n" +
                "0) Instant: 2018-02-01 12.00.00   Temperature value: 31.0\n" +
                "1) Instant: 2018-02-10 12.00.00   Temperature value: 80.0\n" +
                "2) Instant: 2018-02-20 12.00.00   Temperature value: 31.0\n" +
                "--------------------------------------\n";

        int category = 0;

        Reading reading1 = new Reading(31, validDate1, "C", "Test");
        Reading reading2 = new Reading(80, validDate2, "C", "Test1");
        Reading reading3 = new Reading(31, validDate3, "C", "Test");

        List<Reading> readings = new ArrayList<>();

        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading3);

        firstValidRoomSensor.setReadings(readings);
        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(firstValidRoomSensor);
        validRoom1.setRoomSensors(roomSensors);

        reading1.setSensorID(firstValidRoomSensor.getId());
        reading2.setSensorID(firstValidRoomSensor.getId());
        reading3.setSensorID(firstValidRoomSensor.getId());

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        // Act
        Mockito.when(roomRepository.getTemperatureReadingsBetweenDates(validStartDate, validEndingDate, validRoom1)).thenReturn(readings);
        Mockito.when(geographicAreaHouseService.getReadingsAboveCategoryILimit(readings, validHouse)).thenReturn(readings);
        String actualResult = controller.getInstantsAboveComfortInterval(validHouse, category, roomDTO, validStartDate, validEndingDate);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetInstantsAboveComfortIntervalCategoryIIWorks() {
        // Arrange

        String expectedResult = "Instants in which the readings are above comfort temperature:\n" +
                "0) Instant: 2018-02-10 12.00.00   Temperature value: 80.0\n" +
                "1) Instant: 2018-02-20 12.00.00   Temperature value: 30.0\n" +
                "--------------------------------------\n";

        int category = 1;

        Reading reading1 = new Reading(15, validDate1, "C", "Test");
        Reading reading2 = new Reading(80, validDate2, "C", "Test1");
        Reading reading3 = new Reading(30, validDate3, "C", "Test");

        List<Reading> readings = new ArrayList<>();

        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading3);

        List<Reading> expectedReadings = new ArrayList<>();
        expectedReadings.add(reading2);
        expectedReadings.add(reading3);

        firstValidRoomSensor.setReadings(readings);

        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(firstValidRoomSensor);
        validRoom1.setRoomSensors(roomSensors);

        reading1.setSensorID(firstValidRoomSensor.getId());
        reading2.setSensorID(firstValidRoomSensor.getId());
        reading3.setSensorID(firstValidRoomSensor.getId());

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        // Act
        Mockito.when(roomRepository.getTemperatureReadingsBetweenDates(validStartDate, validEndingDate, validRoom1)).thenReturn(readings);
        Mockito.when(geographicAreaHouseService.getReadingsAboveCategoryIILimit(readings, validHouse)).thenReturn(expectedReadings);
        String actualResult = controller.getInstantsAboveComfortInterval(validHouse, category, roomDTO, validStartDate, validEndingDate);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetInstantsAboveComfortIntervalCategoryIIIWorks() {
        // Arrange

        String expectedResult = "Instants in which the readings are above comfort temperature:\n" +
                "0) Instant: 2018-02-10 12.00.00   Temperature value: 80.0\n" +
                "--------------------------------------\n";

        int category = 2;

        Reading reading1 = new Reading(15, validDate1, "C", "Test");
        Reading reading2 = new Reading(80, validDate2, "C", "Test1");
        Reading reading3 = new Reading(28, validDate3, "C", "Test");

        List<Reading> readings = new ArrayList<>();

        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading3);

        List<Reading> expectedReadings = new ArrayList<>();
        expectedReadings.add(reading2);

        firstValidRoomSensor.setReadings(readings);

        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(firstValidRoomSensor);
        validRoom1.setRoomSensors(roomSensors);

        reading1.setSensorID(firstValidRoomSensor.getId());
        reading2.setSensorID(firstValidRoomSensor.getId());
        reading3.setSensorID(firstValidRoomSensor.getId());

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        // Act
        Mockito.when(roomRepository.getTemperatureReadingsBetweenDates(validStartDate, validEndingDate, validRoom1)).thenReturn(readings);
        Mockito.when(geographicAreaHouseService.getReadingsAboveCategoryIIILimit(readings, validHouse)).thenReturn(expectedReadings);
        String actualResult = controller.getInstantsAboveComfortInterval(validHouse, category, roomDTO, validStartDate, validEndingDate);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetInstantsBelowComfortIntervalCategoryIWorksNoReadings() {
        // Arrange

        String expectedResult = "For the given category, in the given interval, there were no temperature readings below the min comfort temperature.";

        int category = 0;

        List<Reading> readings = new ArrayList<>();
        firstValidRoomSensor.setReadings(readings);

        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(firstValidRoomSensor);
        validRoom1.setRoomSensors(roomSensors);

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        // Act

        String actualResult = controller.getInstantsBelowComfortInterval(validHouse, category, roomDTO, validStartDate, validEndingDate);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetInstantsBelowComfortIntervalCategoryIIWorks() {
        // Arrange

        String expectedResult = "Instants in which the readings are below the comfort temperature:\n" +
                "0) Instant: 2018-02-01 12.00.00   Temperature value: 0.0\n" +
                "1) Instant: 2018-02-10 12.00.00   Temperature value: 0.0\n" +
                "2) Instant: 2018-02-20 12.00.00   Temperature value: 0.0\n" +
                "--------------------------------------\n";

        int category = 1;

        Reading reading1 = new Reading(0, validDate1, "C", "Test");
        Reading reading2 = new Reading(0, validDate2, "C", "Test1");
        Reading reading3 = new Reading(0, validDate3, "C", "Test");

        List<Reading> readings = new ArrayList<>();

        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading3);

        firstValidRoomSensor.setReadings(readings);

        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(firstValidRoomSensor);
        validRoom1.setRoomSensors(roomSensors);

        reading1.setSensorID(firstValidRoomSensor.getId());
        reading2.setSensorID(firstValidRoomSensor.getId());
        reading3.setSensorID(firstValidRoomSensor.getId());

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        // Act
        Mockito.when(roomRepository.getTemperatureReadingsBetweenDates(validStartDate, validEndingDate, validRoom1)).thenReturn(readings);
        Mockito.when(geographicAreaHouseService.getReadingsBelowCategoryIILimit(readings, validHouse)).thenReturn(readings);
        String actualResult = controller.getInstantsBelowComfortInterval(validHouse, category, roomDTO, validStartDate, validEndingDate);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetInstantsBelowComfortIntervalCategoryIIIWorks() {
        // Arrange

        String expectedResult = "Instants in which the readings are below the comfort temperature:\n" +
                "0) Instant: 2018-02-01 12.00.00   Temperature value: 0.0\n" +
                "1) Instant: 2018-02-10 12.00.00   Temperature value: 0.0\n" +
                "2) Instant: 2018-02-20 12.00.00   Temperature value: 0.0\n" +
                "--------------------------------------\n";

        int category = 2;

        Reading reading1 = new Reading(0, validDate1, "C", "Test");
        Reading reading2 = new Reading(0, validDate2, "C", "Test1");
        Reading reading3 = new Reading(0, validDate3, "C", "Test");

        List<Reading> readings = new ArrayList<>();

        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading3);

        firstValidRoomSensor.setReadings(readings);

        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(firstValidRoomSensor);
        validRoom1.setRoomSensors(roomSensors);

        reading1.setSensorID(firstValidRoomSensor.getId());
        reading2.setSensorID(firstValidRoomSensor.getId());
        reading3.setSensorID(firstValidRoomSensor.getId());

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        // Act
        Mockito.when(roomRepository.getTemperatureReadingsBetweenDates(validStartDate, validEndingDate, validRoom1)).thenReturn(readings);
        Mockito.when(geographicAreaHouseService.getReadingsBelowCategoryIIILimit(readings, validHouse)).thenReturn(readings);
        String actualResult = controller.getInstantsBelowComfortInterval(validHouse, category, roomDTO, validStartDate, validEndingDate);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetInstantsBelowComfortIntervalCategoryIWorks() {
        // Arrange

        String expectedResult = "Instants in which the readings are below the comfort temperature:\n" +
                "0) Instant: 2018-12-01 12.00.00   Temperature value: 0.0\n" +
                "1) Instant: 2018-12-10 12.00.00   Temperature value: 0.0\n" +
                "2) Instant: 2018-12-20 12.00.00   Temperature value: 0.0\n" +
                "--------------------------------------\n";

        int category = 0;

        Reading reading1 = new Reading(0, roomReadingDate1, "C", "T32875");
        Reading reading2 = new Reading(0, roomReadingDate2, "C", "T32875");
        Reading reading3 = new Reading(0, roomReadingDate3, "C", "T32875");

        List<Reading> readings = new ArrayList<>();
        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading3);

        firstValidRoomSensor.setReadings(readings);

        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(firstValidRoomSensor);
        validRoom1.setRoomSensors(roomSensors);

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        // Act
        Mockito.when(roomRepository.getTemperatureReadingsBetweenDates(validStartDate, validEndingDate, validRoom1)).thenReturn(readings);
        Mockito.when(geographicAreaHouseService.getReadingsBelowCategoryILimit(readings, validHouse)).thenReturn(readings);
        String actualResult = controller.getInstantsBelowComfortInterval(validHouse, category, roomDTO, validStartDate, validEndingDate);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetInstantsBelowComfortIntervalCategoryIIWorksNoReadings() {
        // Arrange

        String expectedResult = "For the given category, in the given interval, there were no temperature readings below the min comfort temperature.";

        int category = 1;

        List<Reading> readings = new ArrayList<>();
        firstValidRoomSensor.setReadings(readings);

        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(firstValidRoomSensor);
        validRoom1.setRoomSensors(roomSensors);

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        // Act

        String actualResult = controller.getInstantsBelowComfortInterval(validHouse, category, roomDTO, validStartDate, validEndingDate);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetInstantsBelowComfortIntervalCategoryIIIWorksNoReadings() {
        // Arrange

        String expectedResult = "For the given category, in the given interval, there were no temperature readings below the min comfort temperature.";

        int category = 2;

        List<Reading> readings = new ArrayList<>();
        firstValidRoomSensor.setReadings(readings);

        List<RoomSensor> roomSensors = new ArrayList<>();
        roomSensors.add(firstValidRoomSensor);
        validRoom1.setRoomSensors(roomSensors);

        RoomDTO roomDTO = RoomMapper.objectToDTO(validRoom1);

        // Act

        String actualResult = controller.getInstantsBelowComfortInterval(validHouse, category, roomDTO, validStartDate, validEndingDate);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfMotherAreaIsValidWorks() {
        // Arrange

        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        validHouse.setMotherAreaID(12L);

        // Act

        boolean actualResult = controller.isMotherAreaValid(validHouse);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfMotherAreaIsValidWorksWhenHouseIsNull() {
        // Arrange

        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        validHouse.setMotherAreaID(null);

        // Act

        boolean actualResult = controller.isMotherAreaValid(validHouse);

        // Assert

        assertFalse(actualResult);

    }
}