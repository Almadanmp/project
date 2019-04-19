package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.areaType.AreaType;
import pt.ipp.isep.dei.project.model.geographicArea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicArea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.model.sensorType.SensorType;
import pt.ipp.isep.dei.project.repository.HouseSensorRepository;
import pt.ipp.isep.dei.project.repository.RoomRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * House Monitoring - controller Tests
 */
@ExtendWith(MockitoExtension.class)
class HouseMonitoringControllerTest {

    // Common artifacts for testing in this class.

    private HouseMonitoringController controller = new HouseMonitoringController();
    private GeographicArea validHouseArea;
    private House validHouse;
    private RoomDTO validRoom;
    private AreaSensor validTemperatureAreaSensor; // Is a temperature sensor with valid readings.
    private RoomSensor validTemperatureRoomSensor; // Is a temperature sensor with valid readings.
    private ReadingUtils readingUtils;
    private RoomService roomService;
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
    HouseSensorRepository houseSensorRepository;
    @Mock
    SensorTypeRepository sensorTypeRepository;

    @BeforeEach
    void arrangeArtifacts() {
        // Sets Up Geographic Area, House, Room and Lists.

        validHouseArea = new GeographicArea("Portugal", new AreaType("Country"), 300,
                200, new Local(45, 30, 30));
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        validHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));
        validHouse.setMotherArea(validHouseArea);
        Room validRoom1 = new Room("Bedroom", "Double Bedroom", 2, 15, 15, 10, "Room1", "Grid1");
        RoomService validRoomService = new RoomService();
        validRoomService.add(validRoom1);
        readingUtils = new ReadingUtils();
        roomService = new RoomService(roomRepository, houseSensorRepository, sensorTypeRepository);
        validHouse.setRoomService(validRoomService);
        validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


        try {
            // Datas desorganizadas, para testar noção de first/last
            validDate1 = validSdf.parse("01/04/2018 15:00:00");
            validDate2 = validSdf.parse("01/04/2018 17:00:00");
            validDate3 = validSdf.parse("01/04/2018 16:00:00");
            validDate4 = validSdf.parse("03/12/2017 15:00:00");
            validDate5 = validSdf.parse("08/12/2017 15:00:00");
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
        Reading secondTempReading = new Reading(20, validDate2, "C", "Test");
        Reading thirdTempReading = new Reading(30, validDate3, "C", "Test");
        Reading fourthTempReading = new Reading(30, validDate4, "C", "Test");
        Reading fifthTempReading = new Reading(-5, validDate5, "C", "Test");

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
        validRoom = RoomMapper.objectToDTO(validRoom1);
    }


//    @Test
//    void seeIfGetCurrentRoomTemperatureThrowsException() {
//        // Act
//
//        Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.getCurrentRoomTemperature(validRoom, validHouse, validHouseSensorService, readingService));
//
//        // Assert
//
//        assertEquals("There aren't any temperature readings available.", exception.getMessage());
//
//    }

//    @Test
//    void SeeIfGetCurrentTemperatureInTheHouseAreaWorks() {
//        // Arrange
//
//        validHouseArea.setSensorList(validAreaSensorService);
//        validHouse.setMotherArea(validHouseArea);
//        double expectedResult = 20.0;
//
//        // Act
//
//        double actualResult = controller.getHouseAreaTemperature(validHouse, validAreaSensorService, readingService);
//
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    void seeIfGetAverageOfReadingsBetweenTwoGivenDates() {
//        // Arrange
//
//        validHouseArea.setSensorList(validAreaSensorService);
//        double expectedResult = 25;
//
//        // Act
//
//        double actualResult = controller.getAverageRainfallInterval(validHouse, validDate4, validDate5, validAreaSensorService, readingService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    void getAverageRainfallIntervalThrowsExceptionReadingListEmpty() {
//        // Act
//
//        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
//            Date day = new Date();
//            try {
//                day = validSdf.parse("07/11/2019 10:02:00");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            controller.getAverageRainfallInterval(validHouse, day, day, validAreaSensorService, readingService);
//        });
//
//        // Assert
//
//        assertEquals("Warning: Average value not calculated - No readings available.", exception.getMessage());
//    }

//    @Test
//    void getAverageRainfallIntervalThrowsExceptionReadingListNull() {
//        // Arrange
//
//        validAreaSensorService = null;
//
//        // Act
//
//        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
//            Date date = new Date();
//            try {
//                date = validSdf.parse("07/11/2019 10:02:00");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            Date date2 = new Date();
//            try {
//                date2 = validSdf.parse("07/11/2019 10:02:00");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            controller.getAverageRainfallInterval(validHouse, date, date2, validAreaSensorService, readingService);
//        });
//
//        // Assert
//
//        assertEquals("Warning: Average value not calculated - No readings available.", exception.getMessage());
//    }

//    @Test
//    void ensureThatWeGetTotalReadingsOnGivenDay() {
//        // Arrange
//
//        Date date = new Date();
//        try {
//            date = validSdf.parse("03/12/2017 10:02:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        validHouseArea.setSensorList(validAreaSensorService);
//        double expectedResult = 40;
//
//        // Act
//
//        double actualResult = controller.getTotalRainfallOnGivenDay(validHouse, date, validAreaSensorService, readingService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    void ensureThatWeGetTotalReadingsOnGivenDayNoRainfall() {
//        // Arrange
//
//        validHouseArea.setSensorList(validAreaSensorService);
//
//        // Act
//
//        Throwable exception = assertThrows(IllegalStateException.class, () -> {
//            Date date = new Date();
//            try {
//                date = validSdf.parse("03/12/2018 10:02:00");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            controller.getTotalRainfallOnGivenDay(validHouse, date, validAreaSensorService, readingService);
//        });
//
//        // Assert
//
//        assertEquals("Warning: Total value could not be calculated - No readings were available.", exception.getMessage());
//    }

//    @Test
//    void ensureThatWeGetTotalReadingsWithoutSensors() {
//        // Arrange
//
//        AreaSensorService emptyList = new AreaSensorService();
//        validHouseArea.setSensorList(emptyList);
//
//        // Act
//
//        Throwable exception = assertThrows(IllegalStateException.class, () -> controller.getTotalRainfallOnGivenDay(validHouse, validDate4, validAreaSensorService, readingService));
//
//        // Assert
//
//        assertEquals("Warning: Total value could not be calculated - No readings were available.", exception.getMessage());
//    }

//    @Test
//    void ensureThatWeGetTotalReadingsWithoutRainFallSensorsAndWithoutReadings() {
//        // Arrange
//
//        Date date = new Date();
//        try {
//            date = validSdf.parse("02/02/2015 10:02:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        AreaSensorService temperatureList = new AreaSensorService();
//        AreaSensor temperatureAreaSensor = new AreaSensor("RF12345", "temperature sensor", new SensorType("temperature", "celsius"), new Local(21, 20, 20), date, 6008L);
//        temperatureList.add(temperatureAreaSensor);
//        validHouseArea.setSensorList(temperatureList);
//
//        // Act
//
//        Throwable exception = assertThrows(IllegalStateException.class, () -> controller.getTotalRainfallOnGivenDay(validHouse, validDate4, validAreaSensorService, readingService));
//
//        // Assert
//
//        assertEquals("Warning: Total value could not be calculated - No readings were available.", exception.getMessage());
//    }

//    @Test
//    void ensureThatWeGetTotalReadingsWithoutWithoutReadings() {
//        // Arrange
//        Date date = new Date();
//        try {
//            date = validSdf.parse("02/02/2015 10:02:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        AreaSensorService rainFallAreaSensorService = new AreaSensorService();
//        AreaSensor rainfallAreaSensor = new AreaSensor("RF12345", "rainfall sensor", new SensorType("rainfall", "L"), new Local(21, 20, 20), date, 6008L);
//        rainFallAreaSensorService.add(rainfallAreaSensor);
//        validHouseArea.setSensorList(rainFallAreaSensorService);
//
//        // Act
//
//        Throwable exception = assertThrows(IllegalStateException.class, () -> controller.getTotalRainfallOnGivenDay(validHouse, validDate4, validAreaSensorService, readingService));
//
//        // Assert
//
//        assertEquals("Warning: Total value could not be calculated - No readings were available.", exception.getMessage());
//    }

//    @Test
//    void getRoomName() {
//        // Arrange
//
//        String expectedResult = "Bedroom";
//
//        // Act
//
//        String actualResult = controller.getRoomName(validRoom, roomService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//
//    }

//    @Test
//    void seeIfGetHighestTempAmplitudeDateSuccess() {
//        validHouseArea.setSensorList(validAreaSensorService);
//
//        Date expectedResult = validDate1;
//
//        Date actualResult = controller.getHighestTempAmplitudeDate(validHouse, validDate4, validDate1, validAreaSensorService, readingService);
//
//        assertEquals(expectedResult, actualResult);
//    }


    //    @Test
//    void seeIfGetHighestTempAmplitudeValueSuccess() {
//        // Arrange
//
//        validHouseArea.setSensorList(validAreaSensorService);
//        double expectedResult = 15.0;
//
//        // Act
//
//        double actualResult = controller.getTempAmplitudeValueByDate(validHouse, validDate1, validAreaSensorService, readingService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    void seeIfGetHighestTempAmplitudeValueThrowsException() {
//        // Arrange
//
//        AreaSensorService invalidAreaSensorService = new AreaSensorService();
//        validHouseArea.setSensorList(invalidAreaSensorService);
//        GregorianCalendar startDate = new GregorianCalendar(2013, Calendar.JANUARY, 1);
//
//        // Act
//
//        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
//                controller.getTempAmplitudeValueByDate(validTemperatureAreaSensor, startDate.getTime(), readingService));
//
//        // Assert
//
//        assertEquals("Warning: Temperature amplitude value not calculated - No readings available.",
//                exception.getMessage());
//    }

//    @Test
//    void seeIfWeGetLastColdestDayInIntervalDateAndValueThrowsException2() {
//        // Arrange
//
//        validHouse.setMotherArea(validHouseArea);
//        validHouseArea.setSensorList(validAreaSensorService);
//        ReadingService readingService = new ReadingService();
//        Reading reading1 = new Reading(23, new GregorianCalendar(2018, Calendar.JULY, 1, 10, 30).getTime(), "C", "TEST");
//        readingService.addReading(reading1);
//        validTemperatureAreaSensor.setReadingService(readingService);
//
//        // Act
//
//        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
//                controller.getLastColdestDayInInterval(validHouse, validDate6, validDate1, validAreaSensorService, readingService));
//
//        // Assert
//
//        assertEquals("No readings available in the chosen interval.",
//                exception.getMessage());
//    }


//    @Test
//    void seeIfWeGetLastColdestDayInIntervalDateAndValueThrowsException1() {
//        // Act
//
//        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
//                controller.getLastColdestDayInInterval(validHouse, validDate6, validDate1, validAreaSensorService, readingService));
//
//        // Assert
//
//        assertEquals("No readings available.",
//                exception.getMessage());
//    }

//    @Test
//    void seeIfGetLastColdestDayInIntervalWorks() {
//        // Arrange
//
//        validHouse.setMotherArea(validHouseArea);
//        validHouseArea.setSensorList(validAreaSensorService);
//        ReadingService readingService = new ReadingService();
//        Reading reading1 = new Reading(23, new GregorianCalendar(2018, Calendar.JULY, 1, 10, 30).getTime(), "C", "TEST");
//        Reading reading2 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 1, 14, 30).getTime(), "C", "TEST");
//        Reading reading3 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 2, 11, 30).getTime(), "C", "TEST");
//        Reading reading4 = new Reading(29, new GregorianCalendar(2018, Calendar.JULY, 2, 16, 30).getTime(), "C", "TEST");
//        Reading reading5 = new Reading(34, new GregorianCalendar(2018, Calendar.JULY, 3, 9, 30).getTime(), "C", "TEST");
//        Reading reading6 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 3, 10, 30).getTime(), "C", "TEST");
//        Reading reading7 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 4, 10, 30).getTime(), "C", "TEST");
//        Reading reading8 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 4, 15, 30).getTime(), "C", "TEST");
//        Reading reading9 = new Reading(12, new GregorianCalendar(2018, Calendar.JULY, 5, 11, 30).getTime(), "C", "TEST");
//        Reading reading10 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime(), "C", "TEST");
//        Reading reading11 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 30).getTime(), "C", "TEST");
//        Reading reading12 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 35).getTime(), "C", "TEST");
//        Reading reading13 = new Reading(20, new GregorianCalendar(2018, Calendar.JULY, 7, 10, 30).getTime(), "C", "TEST");
//        Reading reading14 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 7, 14, 30).getTime(), "C", "TEST");
//        Reading reading15 = new Reading(26, new GregorianCalendar(2018, Calendar.JULY, 8, 9, 30).getTime(), "C", "TEST");
//        Reading reading16 = new Reading(22, new GregorianCalendar(2018, Calendar.JULY, 8, 10, 30).getTime(), "C", "TEST");
//        Reading reading17 = new Reading(21, new GregorianCalendar(2018, Calendar.JULY, 9, 13, 30).getTime(), "C", "TEST");
//        Reading reading18 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 9, 15, 30).getTime(), "C", "TEST");
//        Reading reading19 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 10, 10, 30).getTime(), "C", "TEST");
//        Reading reading20 = new Reading(31, new GregorianCalendar(2018, Calendar.JULY, 10, 15, 30).getTime(), "C", "TEST");
//        readingService.addReading(reading1);
//        readingService.addReading(reading2);
//        readingService.addReading(reading3);
//        readingService.addReading(reading4);
//        readingService.addReading(reading5);
//        readingService.addReading(reading6);
//        readingService.addReading(reading7);
//        readingService.addReading(reading8);
//        readingService.addReading(reading9);
//        readingService.addReading(reading10);
//        readingService.addReading(reading11);
//        readingService.addReading(reading12);
//        readingService.addReading(reading13);
//        readingService.addReading(reading14);
//        readingService.addReading(reading15);
//        readingService.addReading(reading16);
//        readingService.addReading(reading17);
//        readingService.addReading(reading18);
//        readingService.addReading(reading19);
//        readingService.addReading(reading20);
//        validTemperatureAreaSensor.setReadingService(readingService);
//
//        // Act
//
//        Date actualResult = controller.getLastColdestDayInInterval(validHouse, (new GregorianCalendar(2018, Calendar.JULY, 1, 5, 0).getTime()), new GregorianCalendar(2018, Calendar.JULY, 10, 23, 0).getTime(), validAreaSensorService, readingService);
//
//        // Assert
//
//        assertEquals(new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime(), actualResult);
//    }

    /**
     * Tests for getFirstHottestDayInPeriod
     * <p>
     * Given a valid set of readings in tested period:
     * Given a valid set of readings in tested period, multiple days have the highest temperature:
     * -Should return the first day with highest temperature
     */

//    @Test
//    void testGetFirstHottestDayInPeriod() {
//        // Arrange
//
//        Reading r01 = new Reading(20, validDate01, "C", "TEST"); // MaxAmplitude First Date
//        Reading r02 = new Reading(1, validDate02, "C", "TEST"); // MaxAmplitude First Date
//        Reading r03 = new Reading(22, validDate03, "C", "TEST");
//        Reading r04 = new Reading(10, validDate04, "C", "TEST"); // Cold First Date
//        Reading r05 = new Reading(40, validDate05, "C", "TEST");
//        Reading r06 = new Reading(40.2, validDate06, "C", "TEST"); // Hottest First Date
//        Reading r07 = new Reading(10.2, validDate07, "C", "TEST");
//        Reading r08 = new Reading(12, validDate08, "C", "TEST");
//        Reading r09 = new Reading(40.2, validDate09, "C", "TEST"); // Hottest Final Date ALSO MaxAmplitude Final Date
//        Reading r10 = new Reading(21.2, validDate10, "C", "TEST"); // Cold Final Date ALSO MaxAmplitude Final Date
//
//        validTemperatureAreaSensor.addReading(r01);
//        validTemperatureAreaSensor.addReading(r02);
//        validTemperatureAreaSensor.addReading(r03);
//        validTemperatureAreaSensor.addReading(r04);
//        validTemperatureAreaSensor.addReading(r05);
//        validTemperatureAreaSensor.addReading(r06);
//        validTemperatureAreaSensor.addReading(r07);
//        validTemperatureAreaSensor.addReading(r08);
//        validTemperatureAreaSensor.addReading(r09);
//        validTemperatureAreaSensor.addReading(r10);
//        validHouseArea.setSensorList(validAreaSensorService);
//        Date expectedResult = validDate06;
//
//        // Act
//
//        Date actualResult = controller.getFirstHottestDayInPeriod(validHouse, validDate01, validDate10, validAreaSensorService, readingService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

    /**
     * Given a valid set of unorganized readings in tested period:
     * -Should return the first day with highest temperature
     */

//    @Test
//    void testGetFirstHottestDayInPeriod2() {
//        // Arrange
//
//        Reading r02 = new Reading(1, validDate02, "C", "TEST"); // MaxAmplitude First Date
//        Reading r03 = new Reading(22, validDate03, "C", "TEST");
//        Reading r04 = new Reading(10, validDate04, "C", "TEST"); // Cold First Date
//        Reading r05 = new Reading(40, validDate05, "C", "TEST");
//        Reading r07 = new Reading(10.2, validDate07, "C", "TEST");
//        Reading r08 = new Reading(12, validDate08, "C", "TEST");
//        Reading r06 = new Reading(40.2, validDate06, "C", "TEST"); // Hottest First Date
//        Reading r09 = new Reading(40.2, validDate09, "C", "TEST"); // Hottest Final Date ALSO MaxAmplitude Final Date
//        Reading r10 = new Reading(21.2, validDate10, "C", "TEST"); // Cold Final Date ALSO MaxAmplitude Final Date
//        Reading r01 = new Reading(40, validDate01, "C", "TEST"); // MaxAmplitude First Date
//
//        validTemperatureAreaSensor.addReading(r02);
//        validTemperatureAreaSensor.addReading(r03);
//        validTemperatureAreaSensor.addReading(r04);
//        validTemperatureAreaSensor.addReading(r05);
//        validTemperatureAreaSensor.addReading(r07);
//        validTemperatureAreaSensor.addReading(r08);
//        validTemperatureAreaSensor.addReading(r06);
//        validTemperatureAreaSensor.addReading(r09);
//        validTemperatureAreaSensor.addReading(r10);
//        validTemperatureAreaSensor.addReading(r01);
//        validHouseArea.setSensorList(validAreaSensorService);
//        Date expectedResult = validDate06;
//
//        // Act
//
//        Date actualResult = controller.getFirstHottestDayInPeriod(validHouse, validDate01, validDate10, validAreaSensorService, readingService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    void testGetFirstHottestDayInPeriodIfUnorganizedReadings() {
//        // Arrange
//
//        validHouseArea.setSensorList(validAreaSensorService);
//        Date expectedResult = validDate4;
//
//        // Act
//
//        Date actualResult = controller.getFirstHottestDayInPeriod(validHouse, validDate4, validDate2, validAreaSensorService, readingService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

    /**
     * Given a valid set of readings in tested period, a given day has multiple readings and one of them is the highest
     * temperature:
     * -Should return the day with highest temperature
     */

//    @Test
//    void testGetFirstHottestDayMultipleReadingsSameDay() {
//        // Arrange
//
//        Reading r20 = new Reading(20, validDate20, "C", "TEST");
//        Reading r21 = new Reading(22, validDate21, "C", "TEST");
//        Reading r22 = new Reading(30, validDate22, "C", "TEST");
//        Reading r23 = new Reading(25, validDate23, "C", "TEST");
//        Reading r24 = new Reading(21, validDate24, "C", "TEST");
//        Reading r25 = new Reading(20, validDate25, "C", "TEST");
//        validTemperatureAreaSensor.addReading(r20);
//        validTemperatureAreaSensor.addReading(r21);
//        validTemperatureAreaSensor.addReading(r22);
//        validTemperatureAreaSensor.addReading(r23);
//        validTemperatureAreaSensor.addReading(r24);
//        validTemperatureAreaSensor.addReading(r25);
//        validHouseArea.setSensorList(validAreaSensorService);
//
//        // Act
//
//        Date expectedResult = validDate22;
//        Date actualResult = controller.getFirstHottestDayInPeriod(validHouse, validDate20, validDate25, validAreaSensorService, readingService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }


    /**
     * Given a valid house with sensor without readings:
     * -Should return message to User
     */

//    @Test
//    void testGetFirstHottestDayInPeriodThrowsExceptionMessage() {
//        // Arrange
//
//        validHouseArea.setSensorList(validAreaSensorService);
//
//        // Act
//
//        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
//                controller.getFirstHottestDayInPeriod(validHouse, validDate01, validDate02, validAreaSensorService, readingService));
//
//        // Assert
//
//        assertEquals("Warning: No temperature readings available in given period.",
//                exception.getMessage());
//    }

    /**
     * Given a valid set of readings not contained in period:
     * -Should return message to User
     */

//    @Test
//    void testGetFirstHottestDayValidReadingsNotContained() {
//        // Arrange
//
//        Reading r20 = new Reading(20, validDate20, "C", "TEST");
//        Reading r21 = new Reading(22, validDate21, "C", "TEST");
//        Reading r25 = new Reading(20, validDate25, "C", "TEST");
//        validTemperatureAreaSensor.addReading(r20);
//        validTemperatureAreaSensor.addReading(r21);
//        validTemperatureAreaSensor.addReading(r25);
//        validHouseArea.setSensorList(validAreaSensorService);
//
//        // Act
//
//        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
//                controller.getFirstHottestDayInPeriod(validHouse, validDate22, validDate24, validAreaSensorService, readingService));
//
//        // Assert
//
//        assertEquals("Warning: No temperature readings available in given period.",
//                exception.getMessage());
//    }
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

//    @Test
//    void seeIfGetAverageRainfallIntervalWorks() {
//        // Arrange
//
//        double expectedResult = 20.0;
//
//        // Actual
//
//        double actualResult = controller.getAverageRainfallInterval(validHouse, validDate01, validDate10, validAreaSensorService, readingService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
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

//    @Test
//    void seeIfGetDayMaxTemperature() {
//        // Arrange
//
//        double expectedResult = 15;
//        Room room = RoomMapper.dtoToObject(validRoom);
//        validRoom.setName("Bedroom");
//        validHouse.addRoom(room);
//        ReadingDTO reading = new ReadingDTO();
//        reading.setDate(validDate4);
//        reading.setSensorId("Bedroom");
//        reading.setValue(15);
//        reading.setUnit("C");
//        List<ReadingDTO> readingList = new ArrayList<>();
//        readingList.add(reading);
//        HouseSensorDTO houseSensorDTO = new HouseSensorDTO();
//        houseSensorDTO.setName("Name");
//        houseSensorDTO.setDateStartedFunctioning("12-10-2000");
//        houseSensorDTO.setRoomID(validRoom.getHouseId());
//        houseSensorDTO.setTypeSensor("temperature");
//        houseSensorDTO.setUnits("C");
//        houseSensorDTO.setId("10");
//        houseSensorDTO.setReadingList(readingList);
//        List<HouseSensorDTO> sensorList = new ArrayList<>();
//        sensorList.add(houseSensorDTO);
//        validRoom.setSensorList(sensorList);
//
//        // Act
//
//        double actualResult = controller.getDayMaxTemperature(validRoom, validDate4, validHouseSensorService, readingService, roomService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
}