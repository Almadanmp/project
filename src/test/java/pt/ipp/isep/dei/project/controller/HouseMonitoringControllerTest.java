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
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaService;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * House Monitoring - controller Tests
 */
@ExtendWith(MockitoExtension.class)
class HouseMonitoringControllerTest {

    // Common artifacts for testing in this class.

    private HouseMonitoringController controller = new HouseMonitoringController();
    private GeographicArea validHouseArea;
    private House validHouse;
    private RoomDTO validRoomDTO;
    private AreaSensor validTemperatureAreaSensor; // Is a temperature sensor with valid readings.
    private RoomSensor validTemperatureRoomSensor; // Is a temperature sensor with valid readings.
    private ReadingUtils readingUtils;
    private RoomService roomService;
    private Room validRoom1;
    private SimpleDateFormat validSdf; // SimpleDateFormat dd/MM/yyyy HH:mm:ss
    private Date validDate1;
    private Date validDate2;
    private Date validDate3;
    private Date validDate4;
    private Date validDate5;
    private Date validDate6;

    private Date validDate01;
    private Date validDate02;
    private Date validDate03;
    private Date validDate04;
    private Date validDate05;
    private Date validDate06;
    private Date validDate07;
    private Date validDate08;
    private Date validDate09;
    private Date validDate10;
    private Date validDate20;
    private Date validDate21;
    private Date validDate22;
    private Date validDate23;
    private Date validDate24;
    private Date validDate25;

    @Mock
    RoomRepository roomRepository;
    @Mock
    RoomSensorRepository roomSensorRepository;
    @Mock
    SensorTypeRepository sensorTypeRepository;
    @Mock
    GeographicAreaRepository geographicAreaRepository;
    @Mock
    AreaTypeRepository areaTypeRepository;
    @Mock
    AreaSensorRepository areaSensorRepository;

    private GeographicAreaService geographicAreaService;

    @BeforeEach
    void arrangeArtifacts() {
        // Sets Up Geographic Area, House, Room and Lists.

        geographicAreaService = new GeographicAreaService(geographicAreaRepository, areaTypeRepository, sensorTypeRepository);
        validHouseArea = new GeographicArea("Portugal", new AreaType("Country"), 300,
                200, new Local(45, 30, 30));
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        validHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));
        validHouse.setMotherArea(validHouseArea);
        validRoom1 = new Room("Bedroom", "Double Bedroom", 2, 15, 15, 10, "Room1", "Grid1");
        RoomService validRoomService = new RoomService();
        validRoomService.add(validRoom1);
        roomService = new RoomService(roomRepository, roomSensorRepository, sensorTypeRepository);
        validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


        try {
            // Datas desorganizadas, para testar noção de first/last
            validDate1 = validSdf.parse("01/04/2018 15:00:00");
            validDate2 = validSdf.parse("01/04/2018 17:00:00");
            validDate3 = validSdf.parse("01/04/2018 16:00:00");
            validDate4 = validSdf.parse("03/12/2017 15:00:00");//
            validDate5 = validSdf.parse("08/12/2017 15:00:00");//
            validDate6 = validSdf.parse("19/12/2017 15:00:00");

            // Datas ordenadas, exemplo mais real

            validDate01 = validSdf.parse("02/02/2017 01:00:00");
            validDate02 = validSdf.parse("02/02/2017 22:30:00");
            validDate03 = validSdf.parse("25/06/2017 10:00:00");
            validDate04 = validSdf.parse("02/10/2017 15:30:00");
            validDate05 = validSdf.parse("13/12/2017 01:02:00");
            validDate06 = validSdf.parse("02/01/2018 22:00:00");
            validDate07 = validSdf.parse("09/07/2018 12:20:00");
            validDate08 = validSdf.parse("02/08/2018 08:00:00");
            validDate09 = validSdf.parse("05/02/2019 01:15:00");
            validDate10 = validSdf.parse("05/02/2019 20:00:00");

            validDate20 = validSdf.parse("05/02/2020 18:00:00");
            validDate21 = validSdf.parse("05/02/2020 20:00:00");
            validDate22 = validSdf.parse("06/02/2020 16:00:00");
            validDate23 = validSdf.parse("06/02/2020 18:00:00");
            validDate24 = validSdf.parse("06/02/2020 20:00:00");
            validDate25 = validSdf.parse("07/02/2020 20:00:00");

        } catch (ParseException c) {
            c.printStackTrace();
        }

        // Sets up a valid temperature sensor with valid readings.

        validTemperatureAreaSensor = new AreaSensor("RF12345", "TempOne", new SensorType("temperature", "Celsius"),
                new Local(21, 10, 15),
                new Date(), 6008L);
        validTemperatureRoomSensor = new RoomSensor("T123", "TempOne", new SensorType("temperature", "Celsius"),
                new Date(), "RoomAB");
        Reading firstTempReading = new Reading(15, validDate1, "C", "Test");
        validTemperatureAreaSensor.getReadings().add(firstTempReading);
        Reading secondTempReading = new Reading(20, validDate2, "C", "Test");
        validTemperatureAreaSensor.getReadings().add(secondTempReading);
        Reading thirdTempReading = new Reading(30, validDate3, "C", "Test");
        validTemperatureAreaSensor.getReadings().add(thirdTempReading);
        Reading fourthTempReading = new Reading(30, validDate4, "C", "Test");
        validTemperatureAreaSensor.getReadings().add(fourthTempReading);
        Reading fifthTempReading = new Reading(-5, validDate5, "C", "Test");
        validTemperatureAreaSensor.getReadings().add(fifthTempReading);

        // Copy past to TEST for using the organized dates and readings
        /*
        Reading r01 = new Reading(20, validDate01); // MaxAmplitude First Date
        Reading r02 = new Reading(1, validDate02); // MaxAmplitude First Date
        Reading r03 = new Reading(22, validDate03);
        Reading r04 = new Reading(10, validDate04); // Cold First Date
        Reading r05 = new Reading(40, validDate05);
        Reading r06 = new Reading(40.2, validDate06); // Hottest First Date
        Reading r07 = new Reading(10.2, validDate07);
        Reading r08 = new Reading(12, validDate08);
        Reading r09 = new Reading(40.2, validDate09); // Hottest Final Date ALSO MaxAmplitude Final Date
        Reading r10 = new Reading(21.2, validDate10); // Cold Final Date ALSO MaxAmplitude Final Date
        Reading r20 = new Reading(20, validDate20);
        Reading r21 = new Reading(22, validDate21);
        Reading r22 = new Reading(20, validDate22);
        Reading r23 = new Reading(25, validDate23);
        Reading r24 = new Reading(21, validDate24);
        Reading r25 = new Reading(20, validDate25);

        validTemperatureSensor.addReading(r01);
        validTemperatureSensor.addReading(r02);
        validTemperatureSensor.addReading(r03);
        validTemperatureSensor.addReading(r04);
        validTemperatureSensor.addReading(r05);
        validTemperatureSensor.addReading(r06);
        validTemperatureSensor.addReading(r07);
        validTemperatureSensor.addReading(r08);
        validTemperatureSensor.addReading(r09);
        validTemperatureSensor.addReading(r10);
        validTemperatureSensor.addReading(r20);
        validTemperatureSensor.addReading(r21);
        validTemperatureSensor.addReading(r22);
        validTemperatureSensor.addReading(r23);
        validTemperatureSensor.addReading(r24);
        validTemperatureSensor.addReading(r25);
        */

        // Sets up a valid rainfall sensor with valid readings.

        AreaSensor validRainfallAreaSensor = new AreaSensor("RF12366", "RainOne", new SensorType("rainfall", "l/m2 "), new Local
                (21, 41, 11), new Date(), 6008L);
        Reading firstRainReading = new Reading(40, validDate4, "C", "Test");
        Reading secondRainReading = new Reading(10, validDate5, "C", "Test");
        Reading thirdRainReading = new Reading(10, validDate6, "C", "Test");
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
    void SeeIfGetCurrentTemperatureInTheHouseAreaWorks() {
        // Arrange

        double expectedResult = 20.0;

        // Act

        double actualResult = controller.getHouseAreaTemperature(validTemperatureAreaSensor);


        // Assert

        assertEquals(expectedResult, actualResult, 0.01);

    }

    @Test
    void seeIfGetAverageOfReadingsBetweenTwoGivenDates() {
        // Arrange

        double expectedResult = 12.5;

        // Act

        double actualResult = controller.getAverageRainfallInterval(validTemperatureAreaSensor, validDate4, validDate5);

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void getAverageRainfallIntervalThrowsExceptionReadingListEmpty() {
        // Act

        Date day = new Date();
        try {
            day = validSdf.parse("07/11/2023 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date initialDay = day;
        Date day1 = new Date();
        try {
            day1 = validSdf.parse("07/12/2023 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date finalDay = day1;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.getAverageRainfallInterval(validTemperatureAreaSensor, initialDay, finalDay));

        // Assert

        assertEquals("Warning: Average value not calculated - No readings available.", exception.getMessage());
    }

    @Test
    void getAverageRainfallIntervalThrowsExceptionReadingListNull() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Date date = new Date();
            try {
                date = validSdf.parse("07/11/2019 10:02:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date2 = new Date();
            try {
                date2 = validSdf.parse("07/11/2019 10:02:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            controller.getAverageRainfallInterval(validTemperatureAreaSensor, date, date2);
        });

        // Assert

        assertEquals("Warning: Average value not calculated - No readings available.", exception.getMessage());
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDay() {
        // Arrange

        Date date = new Date();
        try {
            date = validSdf.parse("03/12/2017 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double expectedResult = 30;

        // Act

        double actualResult = controller.getTotalRainfallOnGivenDay(date, validTemperatureAreaSensor);

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDayNoRainfall() {
        // Act

        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            Date date = new Date();
            try {
                date = validSdf.parse("03/12/2018 10:02:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            controller.getTotalRainfallOnGivenDay(date, validTemperatureAreaSensor);
        });

        // Assert

        assertEquals("Warning: Total value was not calculated - No readings were available.", exception.getMessage());
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
    void seeIfGetHighestTempAmplitudeDateSuccess() {
        // Arrange


        Date expectedResult = validDate1;

        // Act

        Date actualResult = controller.getHighestTempAmplitudeDate
                (validTemperatureAreaSensor, validDate4, validDate1);

        // Assert

        assertEquals(expectedResult, actualResult);

    }


    @Test
    void seeIfGetHighestTempAmplitudeValueSuccess() {
        // Arrange

        double expectedResult = 15.0;

        // Act

        double actualResult = controller.getTempAmplitudeValueByDate(validTemperatureAreaSensor, validDate1);

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);

    }

    @Test
    void seeIfWeGetLastColdestDayInIntervalDateAndValueThrowsException() {
        // Arrange

        Date day = new Date();
        try {
            day = validSdf.parse("07/11/2023 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date initialDay = day;
        Date day1 = new Date();
        try {
            day1 = validSdf.parse("07/12/2023 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date finalDay = day1;

        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                controller.getLastColdestDayInInterval(validTemperatureAreaSensor, initialDay, finalDay));

        // Assert

        assertEquals("No readings available in the chosen interval.",
                exception.getMessage());

    }

    @Test
    void seeIfGetLastColdestDayInIntervalWorks() {
        // Arrange

        validHouse.setMotherArea(validHouseArea);

        // Act

        Date actualResult = controller.getLastColdestDayInInterval(validTemperatureAreaSensor, validDate01, validDate06);

        // Assert

        assertEquals(validDate5, actualResult);
    }

    @Test
    void testGetFirstHottestDayInPeriod() {
        // Arrange

        Date expectedResult = validDate4;

        // Act

        Date actualResult = controller.getFirstHottestDayInPeriod(validTemperatureAreaSensor, validDate01, validDate06);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetFirstHottestDayInPeriodWorks() {
        // Arrange

        AreaSensor testSensorNoReadings = new AreaSensor("S001", "TempOne", new SensorType("temperature", "C"),
                new Local(21, 3, 13), validDate01, 213L);

        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                controller.getFirstHottestDayInPeriod(testSensorNoReadings, validDate01, validDate02));

        // Assert

        assertEquals("No readings available.",
                exception.getMessage());
    }

    /**
     * Given a valid set of readings not contained in period:
     * -Should return message to User
     */

    @Test
    void seeIfGetFirstHottestDayInPeriodWorksNoReadings() {
        // Arrange

        Reading r20 = new Reading(20, validDate20, "C", "TEST");
        Reading r21 = new Reading(22, validDate21, "C", "TEST");
        Reading r25 = new Reading(20, validDate25, "C", "TEST");
        validTemperatureAreaSensor.addReading(r20);
        validTemperatureAreaSensor.addReading(r21);
        validTemperatureAreaSensor.addReading(r25);
        validHouseArea.addSensor(validTemperatureAreaSensor);

        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                controller.getFirstHottestDayInPeriod(validTemperatureAreaSensor, validDate22, validDate24));

        // Assert

        assertEquals("Warning: No temperature readings available in given period.",
                exception.getMessage());
    }

    @Test
    void seeIfIsMotherAreaValidWorks() {
        // Act

        boolean actualResult = controller.isMotherAreaValid(validHouse);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfIsMotherAreaValidNoMotherArea() {
        // Arrange

        House invalidHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"), new Local(20, 20, 20), 60,
                180, new ArrayList<>());

        // Act

        boolean actualResult = controller.isMotherAreaValid(invalidHouse);

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetAverageRainfallIntervalWorks() {
        // Arrange

        AreaSensor rainFallSensor = new AreaSensor("S001", "Rainfall2", new SensorType("rainfall", "l/m2"),
                new Local(21, 31, 12), new GregorianCalendar(2017, Calendar.JANUARY,
                21).getTime(), 213L);
        Reading mockReading = new Reading(15, new GregorianCalendar(2017, Calendar.FEBRUARY,3).getTime(),
                "l/m2", "S001");
        List<Reading> mockReadingList = new ArrayList<>();
        mockReadingList.add(mockReading);
        rainFallSensor.setReadings(mockReadingList);
        double expectedResult = 15.0;

        // Actual

        double actualResult = controller.getAverageRainfallInterval(rainFallSensor, validDate01, validDate10);

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);
    }
//
//    @Test
//    void seeIfGetTotalRainfallOnGivenDayWorks() {
//        // Arrange
//
//        double expectedResult = 40.0;
//        validHouse.getMotherArea().setSensorList(validAreaSensorService);
//
//        // Actual
//
//        double actualResult = controller.getTotalRainfallOnGivenDay(validHouse, validDate4, validAreaSensorService, readingService);
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    void seeIfGetHighestTempAmplitudeDateWorks() {
//        // Arrange
//
//        Date expectedResult = validDate1;
//        validHouse.getMotherArea().setSensorList(validAreaSensorService);
//
//        // Actual
//
//        Date actualResult = controller.getHighestTempAmplitudeDate(validHouse, validDate01, validDate10, validAreaSensorService, readingService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    void seeIfGetHouseAreaTemperatureWorks() {
//        // Arrange
//
//        double expectedResult = 20.0;
//        validHouse.getMotherArea().setSensorList(validAreaSensorService);
//
//        // Actual
//
//        double actualResult = controller.getHouseAreaTemperature(validHouse, validAreaSensorService, readingService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

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