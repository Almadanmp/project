package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.controllercli.ReaderController;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.SensorTypeCrudeRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AreaReadingList tests class.
 */
@ExtendWith(MockitoExtension.class)
class ReadingUtilsTest {

    private AreaSensor firstValidAreaSensor;
    private RoomSensor firstValidRoomSensor;
    private Date validDate1; // Date 21/11/2018
    private Date validDate2; // Date 03/09/2018
    private Date validDate3; // 31/09/2018 23:59:59
    private Date validDate4; // 07/10/2018 00:00:00
    private Date validDate5; // 08/10/2018 23:26:21
    private Date validDate6; // 09/10/2018 08:21:22
    private Date validDate7; // 10/10/2018 18:14:03
    private Date validDate8; // 23/10/2018 12:14:23
    private Date validDate9; // 13/10/2018 12:12:12
    private Date validDate10; // 30/10/2018 23:59:59
    private Date validDate11; // 01/11/2018 00:00:00
    private Date validDate12; // 02/11/2015
    private Date validDate16; // 13/10/2018 23:59:59
    private Date validDate13;
    private Date validDate14; // 02/10/2018 23:59:00
    private Date validDate15;
    private Date validDate18; // same day and month as 9 ans 16 but different year
    private Date validDate19; // same day and month as 9 ans 16 but different year, different hour
    private static final Logger logger = Logger.getLogger(ReaderController.class.getName());
    private SensorType validSensorTypeTemp;

    @Mock
    SensorTypeCrudeRepo sensorTypeCrudeRepo;

    @Autowired
    GeographicAreaRepository geographicAreaRepository;

    @BeforeEach
    void arrangeArtifacts() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat validSdfDay = new SimpleDateFormat("dd/MM/yyyy");
        try {
            validDate12 = validSdf.parse("02/11/2015 20:00:00");
            validDate2 = validSdf.parse("03/09/2018 00:00:00");
            validDate3 = validSdf.parse("31/09/2018 23:59:59");
            validDate13 = validSdfDay.parse("03/10/2018");
            validDate14 = validSdf.parse("02/10/2018 23:59:00");
            validDate15 = validSdf.parse("03/10/2018 00:00:00");
            validDate4 = validSdf.parse("07/10/2018 00:00:00");
            validDate5 = validSdf.parse("08/10/2018 23:26:21");
            validDate6 = validSdf.parse("09/10/2018 08:21:22");
            validDate7 = validSdf.parse("10/10/2018 18:14:03");
            validDate9 = validSdf.parse("13/10/2018 12:12:12");
            validDate16 = validSdf.parse("13/10/2018 23:59:59");
            validDate8 = validSdf.parse("23/10/2018 12:14:23");
            validDate10 = validSdf.parse("30/10/2018 23:59:59");
            validDate1 = validSdf.parse("21/11/2018 00:00:00");
            validDate11 = validSdf.parse("01/11/2018 00:00:00");
            validDate18 = validSdf.parse("13/10/2019 12:12:12");
            validDate19 = validSdf.parse("13/10/2019 23:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        validSensorTypeTemp = new SensorType("Temperature", "Celsius");

        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", validSensorTypeTemp.getName(), new Local(
                31, 1, 2), validDate1);
        firstValidAreaSensor.setActive(true);
        firstValidRoomSensor = new RoomSensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), validDate1, "RoomID");
    }

    @Test
    void seeIfGetReadingsBySensorIDWorks() {
        // Arrange

        List<Reading> expectedResult = new ArrayList<>();
        List<Reading> readings = new ArrayList<>();

        Reading reading1 = new Reading(20D, validDate1, "C", "SensorID1");
        Reading reading2 = new Reading(20D, validDate2, "C", "SensorID2");
        readings.add(reading1);
        readings.add(reading2);
        expectedResult.add(reading2);

        // Act

        List<Reading> actualResult = ReadingUtils.getReadingsBySensorID("SensorID2", readings);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsBySensorIDWorksWhenSensorIDIsInvalid() {
        // Arrange

        List<Reading> expectedResult = new ArrayList<>();
        List<Reading> readings = new ArrayList<>();

        Reading reading1 = new Reading(20D, validDate1, "C", "SensorID1");
        Reading reading2 = new Reading(20D, validDate2, "C", "SensorID2");
        readings.add(reading1);
        readings.add(reading2);

        // Act

        List<Reading> actualResult = ReadingUtils.getReadingsBySensorID("InvalidSensorID", readings);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSensorIDsWorks() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        List<Reading> readings = new ArrayList<>();
        expectedResult.add("SensorID1");
        expectedResult.add("SensorID2");

        Reading reading1 = new Reading(20D, validDate1, "C", "SensorID1");
        Reading reading2 = new Reading(20D, validDate2, "C", "SensorID2");
        readings.add(reading1);
        readings.add(reading2);

        // Act

        List<String> actualResult = ReadingUtils.getSensorIDs(readings);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSensorIDsWorksWhenListIsEmpty() {
        // Arrange

        List<String> expectedResult = new ArrayList<>();
        List<Reading> readings = new ArrayList<>();

        // Act

        List<String> actualResult = ReadingUtils.getSensorIDs(readings);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetTotalFromList() {
        // Arrange

        List<Double> list = new ArrayList<>();
        list.add(1.0);
        list.add(2.0);

        // Act

        double actualResult = ReadingUtils.getListSum(list);

        // Assert

        assertEquals(3.0, actualResult);
    }

    @Test
    void seeTotalFromEmptyList() {
        // Arrange

        List<Double> list = new ArrayList<>();

        // Act

        double actualResult = ReadingUtils.getListSum(list);

        // Assert

        assertEquals(0.0, actualResult);
    }

    @Test
    void seeIfGetsAverage() {
        // Arrange

        List<Double> doubleList = new ArrayList<>();
        double expectedResult = 22.40;
        doubleList.add(1D);
        doubleList.add(23D);
        doubleList.add(43.2D);

        // Act

        double actualResult = ReadingUtils.getAvgFromList(doubleList);

        // Assert

        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfAverageIsZeroInEmptyList() {
        // Arrange

        List<Double> doubleList = new ArrayList<>();

        // Act

        double actualResult = ReadingUtils.getAvgFromList(doubleList);

        // Assert

        assertEquals(0, actualResult, 0.001);
    }

    @Test
    void seeIfGetsMostRecentValueWorks() {
        // Arrange

        Date testDate = new GregorianCalendar(2018, Calendar.NOVEMBER, 3).getTime();
        Reading earlierReading = new Reading(15, validDate12, "C", "Test");
        Reading laterReading = new Reading(30, testDate, "C", "Test");
        List<Reading> readings = new ArrayList<>();
        readings.add(earlierReading);
        readings.add(laterReading);
        double expectedResult = 30.0;

        // Act

        double result = ReadingUtils.getMostRecentValue(readings);

        // Assert

        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    void seeIfGetMostRecentValueWorksInSameDay() {
        // Arrange

        Date testDate = new GregorianCalendar(2015, Calendar.NOVEMBER, 2, 5, 0,
                0).getTime();
        Reading earlierReading = new Reading(15, validDate12, "C", "Test");
        Reading laterReading = new Reading(30, testDate, "C", "Test");
        List<Reading> readings = new ArrayList<>();
        readings.add(earlierReading);
        readings.add(laterReading);
        double expectedResult = 15.0;

        // Act

        double result = ReadingUtils.getMostRecentValue(readings);

        // Assert

        assertEquals(expectedResult, result, 0.01);

    }

    @Test
    void seeIfGetsTotalReadings() {
        // Arrange

        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading(20, validDate15, "C", "Test");
        Reading reading2 = new Reading(20, validDate3, "C", "Test");
        Reading reading3 = new Reading(20, validDate7, "C", "Test");
        Reading reading4 = new Reading(20, validDate14, "C", "Test");
        readings.add(reading);
        readings.add(reading2);
        readings.add(reading3);
        readings.add(reading4);
        double expectedResult = 20;

        // Act

        double actualResult = ReadingUtils.getValueReadingsInDay(validDate13, readings);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetsTotalReadingsInDayWorksNoReadings() {

        // Act
        List<Reading> readings = new ArrayList<>();
        Throwable exception = assertThrows(IllegalStateException.class,
                () -> ReadingUtils.getValueReadingsInDay(validDate13, readings));

        // Assert

        assertEquals("Warning: Total value was not calculated - No readings were available.", exception.getMessage());
    }

    @Test
    void seeIfGetsMostRecentReading() {
        // This test is particularly complex, but it tests several failure cases. The particular failure scenarios
        // are expanded on next to each assert.

        // Arrange

        List<Reading> readingService2 = new ArrayList<>();
        List<Reading> readingService3 = new ArrayList<>();
        List<Reading> readingService4 = new ArrayList<>();
        List<Reading> readingService5 = new ArrayList<>();
        Reading secondMostRecentReading = new Reading(22, validDate14, "C", "Test");
        Reading mostRecentReading = new Reading(25, validDate15, "C", "Test");
        Reading oldestReading = new Reading(27, validDate3, "C", "Test");
        readingService2.add(mostRecentReading);
        readingService2.add(oldestReading);
        readingService2.add(secondMostRecentReading);
        readingService3.add(mostRecentReading);
        readingService3.add(secondMostRecentReading);
        readingService3.add(oldestReading);
        readingService4.add(oldestReading);
        readingService4.add(secondMostRecentReading);
        readingService4.add(oldestReading);
        // Reading error = new Reading(NaN, new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime(), "C", "Test");

        // Act
        Reading actualResult2 = ReadingUtils.getMostRecentReading(readingService2);
        Reading actualResult3 = ReadingUtils.getMostRecentReading(readingService3);
        Reading actualResult4 = ReadingUtils.getMostRecentReading(readingService4);
        // Reading actualResult5 = validReadingService.getMostRecentReading(readingService5);

        // Assert
        assertEquals(mostRecentReading, actualResult2); // Tests if method works when most recent reading is in the middle of the list.
        assertEquals(mostRecentReading, actualResult3); // Tests if method works when most recent reading is the last on the list.
        assertEquals(secondMostRecentReading, actualResult4); // Tests if method works when most recent reading happens more than once.
        //   assertEquals(error, actualResult5); // Tests if method works when there are no readings.
    }

    @Test
    void seeIfGetsFirstSecondOfDay() {
        // Arrange

        Date expectedResult = new GregorianCalendar(2018, Calendar.OCTOBER, 2).getTime();

        // Assert

        assertEquals(expectedResult, ReadingUtils.getFirstSecondOfDay(validDate14));
    }


    @Test
    void seeIfGetsLastSecondOfDay() {
        // Arrange

        Date expectedResult = new GregorianCalendar(2018, Calendar.OCTOBER, 2, 23, 59, 59).getTime();

        // Assert

        assertEquals(expectedResult, ReadingUtils.getLastSecondOfDay(validDate14));
    }

    @Test
    void seeIfWeGetMaxValueOfTheDayWorks() {
        //Arrange
        List<Reading> readingService = new ArrayList<>();
        List<Reading> readingService2 = new ArrayList<>();
        List<Reading> readingService3 = new ArrayList<>();
        Reading reading1 = new Reading(22, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 10, 0).getTime(), "C", "Test");
        Reading reading2 = new Reading(22, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 9, 0).getTime(), "C", "Test");
        Reading reading3 = new Reading(25, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 11, 0).getTime(), "C", "Test");
        Reading reading4 = new Reading(19, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 21, 30).getTime(), "C", "Test");
        readingService.add(reading1);
        readingService.add(reading2);
        readingService2.add(reading2);
        readingService2.add(reading3);
        readingService3.add(reading3);
        readingService3.add(reading4);
        //Act
        Reading actualResult = ReadingUtils.getMaxValueOfTheDay(readingService, (new GregorianCalendar(2018, Calendar.OCTOBER, 8).getTime()));
        Reading actualResult2 = ReadingUtils.getMaxValueOfTheDay(readingService2, new GregorianCalendar(2018, Calendar.OCTOBER, 8).getTime());
        Reading actualResult3 = ReadingUtils.getMaxValueOfTheDay(readingService3, new GregorianCalendar(2018, Calendar.OCTOBER, 8).getTime());

        //Assert
        assertEquals(reading1, actualResult);
        assertEquals(reading3, actualResult2);
        assertEquals(reading3, actualResult3);
    }

    @Test
    void seeIfWeGetReadingListWithSpecificValueWorks() {
        //Arrange
        List<Reading> readingService = new ArrayList<>();
        List<Reading> expectedResult = new ArrayList<>();
        Reading r1 = new Reading(22, validDate2, "C", "Test");
        Reading r2 = new Reading(24, validDate14, "C", "Test");
        Reading r3 = new Reading(22, validDate2, "C", "Test");
        Reading r4 = new Reading(21, validDate15, "C", "Test");
        Reading r5 = new Reading(22, validDate12, "C", "Test");
        Reading r6 = new Reading(29, validDate2, "C", "Test");
        readingService.add(r1);
        readingService.add(r2);
        readingService.add(r3);
        readingService.add(r4);
        readingService.add(r5);
        readingService.add(r6);
        expectedResult.add(r1);
        expectedResult.add(r3);
        expectedResult.add(r5);
        //Act
        List<Reading> actualResult = ReadingUtils.getReadingListOfReadingsWithSpecificValue(readingService, 22.0);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfWeGetReadingWithSpecificDateWorks() {
        //Arrange
        List<Reading> readingService = new ArrayList<>();
        Reading r1 = new Reading(22, validDate5, "C", "Test");
        Reading r2 = new Reading(24, validDate14, "C", "Test");
        Reading r3 = new Reading(22, validDate2, "C", "Test");
        Reading r4 = new Reading(21, validDate15, "C", "Test");
        Reading r5 = new Reading(22, validDate12, "C", "Test");
        Reading r6 = new Reading(29, validDate2, "C", "Test");
        readingService.add(r1);
        readingService.add(r2);
        readingService.add(r3);
        readingService.add(r4);
        readingService.add(r5);
        readingService.add(r6);
        //Act
        Reading actualResult2 = ReadingUtils.getAReadingWithSpecificDay(readingService, validDate7);
        Reading actualResult = ReadingUtils.getAReadingWithSpecificDay(readingService, validDate2);
        //Assert
        assertNull(actualResult2);
        assertEquals(r3, actualResult);
    }

    @Test
    void seeIfWeGetListOfMaxValuesForEachDayWorks() {
        //Arrange
        List<Reading> readingService = new ArrayList<>();
        Reading r1 = new Reading(22, validDate5, "C", "Test");
        Reading r2 = new Reading(24, validDate14, "C", "Test");
        Reading r3 = new Reading(22, validDate2, "C", "Test");
        Reading r4 = new Reading(21, validDate15, "C", "Test");
        Reading r5 = new Reading(22, validDate12, "C", "Test");
        Reading r6 = new Reading(23, new GregorianCalendar(2018, Calendar.OCTOBER, 8, 21, 0).getTime(), "C", "Test");
        Reading r7 = new Reading(26, new GregorianCalendar(2018, Calendar.OCTOBER, 2, 10, 0).getTime(), "C", "Test");
        Reading r8 = new Reading(20, new GregorianCalendar(2018, Calendar.SEPTEMBER, 3, 23, 30).getTime(), "C", "Test");
        Reading r10 = new Reading(20, validDate12, "C", "Test");
        readingService.add(r1);
        readingService.add(r2);
        readingService.add(r3);
        readingService.add(r4);
        readingService.add(r5);
        readingService.add(r6);
        readingService.add(r7);
        readingService.add(r8);
        readingService.add(r10);
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(r6);
        expectedResult.add(r7);
        expectedResult.add(r3);
        expectedResult.add(r4);
        expectedResult.add(r10);
        //Act
        List<Reading> actualResult = ReadingUtils.getListOfMaxValuesForEachDay(readingService);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfGetHottestDayInGivenPeriodWorksNoReadings() {
//        // Arrange
//
//        Reading outOfBoundsReading = new Reading(1, validDate1, "C", "SensorOne");
//        validReadingService.addReading(outOfBoundsReading);
//
//        // Assert
//
//        assertThrows(IllegalArgumentException.class, () -> validReadingService.getFirstHottestDayInGivenPeriod(firstValidAreaSensor, validDate12, validDate2));
//    }

//    @Test
//    void seeReadingExistsInRepositoryWorks() {
//        // Arrange
//
//        String sensorId = "TT12";
//        Reading reading = new Reading(2D, validDate1, "C", sensorId);
//        Mockito.when(readingRepository.findReadingByDateEqualsAndSensorId(validDate1, sensorId)).thenReturn((reading));
//
//        // Act
//
//        boolean actualResult = validReadingService.readingExistsInRepository(sensorId, validDate1);
//
//        // Assert
//
//        assertTrue(actualResult);
//    }

//    @Test
//    void seeReadingExistsInRepositoryWorksWhenReadingIsNotPresent() {
//        // Arrange
//
//        String sensorId = "TT12";
//        Mockito.when(readingRepository.findReadingByDateEqualsAndSensorId(validDate1, sensorId)).thenReturn((null));
//
//        // Act
//
//        boolean actualResult = validReadingService.readingExistsInRepository(sensorId, validDate1);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }

//    @Test
//    void seeIfAddAreaReadingToRepositoryWorksWhenSensorDoesNotExist() {
//        // Arrange
//
//        String sensorId = firstValidAreaSensor.getId();
//        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn((Optional.empty()));
//
//        // Act
//
//        boolean actualResult = geographicAreaService.addAreaReadingToRepository(firstValidAreaSensor, 20D, validDate1, "C", logger, areaSensorService);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }

//    @Test
//    void seeIfAddAreaReadingToRepositoryWorksWhenSensorWasNotActiveDuringRead() {
//        // Arrange
//
//        String sensorId = firstValidAreaSensor.getId();
//        Mockito.when(areaSensorRepository.findById(sensorId)).thenReturn(Optional.of(firstValidAreaSensor));
//
//        // Act
//
//        boolean actualResult = geographicAreaService.addAreaReadingToRepository(firstValidAreaSensor, 20D, validDate2, "C", logger, areaSensorService);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }

//    @Test
//    void seeIfAddAreaReadingToRepositoryWorksWhenReadingAlreadyExists() {
//        // Arrange
//
//        Reading reading = new Reading(2D, validDate1, "C", firstValidAreaSensor.getId());
//        Mockito.when(areaSensorRepository.findById(firstValidAreaSensor.getId())).thenReturn(Optional.of(firstValidAreaSensor));
//        Mockito.when(readingRepository.findReadingByDateEqualsAndSensorId(validDate1, firstValidAreaSensor.getId())).thenReturn((reading));
//
//        // Act
//
//        boolean actualResult = validReadingService.addAreaReadingToRepository(firstValidAreaSensor, 2D, validDate1, "C", logger, areaSensorService);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }
//
//    @Test
//    void seeIfAddAreaReadingToRepositoryWorks() {
//        // Arrange
//
//        Mockito.when(areaSensorRepository.findById(firstValidAreaSensor.getId())).thenReturn(Optional.of(firstValidAreaSensor));
//        Mockito.when(readingRepository.findReadingByDateEqualsAndSensorId(validDate1, firstValidAreaSensor.getId())).thenReturn((null));
//
//        // Act
//
//        boolean actualResult = validReadingService.addAreaReadingToRepository(firstValidAreaSensor, 2D, validDate1, "C", logger, areaSensorService);
//
//        // Assert
//
//        assertTrue(actualResult);
//    }

//    @Test
//    void seeIfAddHouseReadingToRepositoryWorksWhenSensorDoesNotExist() {
//        // Arrange
//
//        String sensorId = "SensorID";
//        Mockito.when(houseSensorRepository.findById(sensorId)).thenReturn((Optional.empty()));
//
//        // Act
//
//        boolean actualResult = validReadingService.addHouseReadingToRepository(sensorId, 20D, validDate1, "C", logger, houseSensorService);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }

//    @Test
//    void seeIfAddHouseReadingToRepositoryWorksWhenSensorWasNotActiveDuringRead() {
//        // Arrange
//
//        String sensorId = "SensorID";
//        Mockito.when(houseSensorRepository.findById(sensorId)).thenReturn(Optional.of(firstValidRoomSensor));
//
//        // Act
//
//        boolean actualResult = validReadingService.addHouseReadingToRepository(sensorId, 20D, validDate2, "C", logger, houseSensorService);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }

//    @Test
//    void seeIfAddHouseReadingToRepositoryWorksWhenReadingAlreadyExists() {
//        // Arrange
//
//        String sensorId = "SensorID";
//        Reading reading = new Reading(2D, validDate1, "C", sensorId);
//        Mockito.when(houseSensorRepository.findById(sensorId)).thenReturn(Optional.of(firstValidRoomSensor));
//        Mockito.when(readingRepository.findReadingByDateEqualsAndSensorId(validDate1, sensorId)).thenReturn((reading));
//
//        // Act
//
//        boolean actualResult = validReadingService.addHouseReadingToRepository(sensorId, 2D, validDate1, "C", logger, houseSensorService);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }

//    @Test
//    void seeIfAddHouseReadingToRepositoryWorks() {
//        // Arrange
//
//        String sensorId = "SensorID";
//        Mockito.when(houseSensorRepository.findById(sensorId)).thenReturn(Optional.of(firstValidRoomSensor));
//        Mockito.when(readingRepository.findReadingByDateEqualsAndSensorId(validDate1, sensorId)).thenReturn((null));
//
//        // Act
//
//        boolean actualResult = validReadingService.addHouseReadingToRepository(sensorId, 2D, validDate1, "C", logger, houseSensorService);
//
//        // Assert
//
//        assertTrue(actualResult);
//    }

    @Test
    void seeIfGetValueReadingThrowsException() {
        //Arrange
        List<Reading> readingList = new ArrayList<>();
        //Assert
        assertThrows(IndexOutOfBoundsException.class,
                () -> ReadingUtils.getValueReading(readingList, 0));
    }

    @Test
    void seeIfGetValueDateThrowsException() {
        //Arrange
        List<Reading> readingList = new ArrayList<>();
        //Assert
        assertThrows(IndexOutOfBoundsException.class,
                () -> ReadingUtils.getValueDate(readingList, 0));
    }

    @Test
    void seeIfGetMostRecentValueThrowsException() {
        //Arrange
        List<Reading> readingList = new ArrayList<>();
        //Assert
        assertThrows(IllegalArgumentException.class,
                () -> ReadingUtils.getMostRecentValue(readingList));
    }
}