package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.Mapper;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * House Monitoring - controller Tests
 */

class HouseMonitoringControllerTest {

    // Common artifacts for testing in this class.

    private HouseMonitoringController controller = new HouseMonitoringController();
    private GeographicArea validHouseArea;
    private House validHouse;
    private RoomDTO validRoom;
    private Sensor validTemperatureSensor; // Is a temperature sensor with valid readings.
    private SensorList validSensorList; // Contains the mock sensors mentioned above.
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

    @BeforeEach
    void arrangeArtifacts() {
        // Sets Up Geographic Area, House, Room and Lists.
        Mapper mapper = new Mapper();

        validHouseArea = new GeographicArea("Portugal", new TypeArea("Country"), 300,
                200, new Local(45, 30, 30));
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)), 60,
                180, new ArrayList<>());
        validHouse.setMotherArea(validHouseArea);
        Room validRoom1 = new Room("Bedroom", 2, 15, 15, 10);
        RoomList validRoomList = new RoomList();
        validRoomList.add(validRoom1);
        validSensorList = new SensorList();
        validRoom1.setSensorList(validSensorList);
        validHouse.setRoomList(validRoomList);
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

        // Sets up a valid temperature sensor with valid Readings.

        validTemperatureSensor = new Sensor("RF12345", "TempOne", new TypeSensor("Temperature", "Celsius"),
                new Local(21, 10, 15),
                new Date());
        Reading firstTempReading = new Reading(15, validDate1);
        Reading secondTempReading = new Reading(20, validDate2);
        Reading thirdTempReading = new Reading(30, validDate3);
        Reading fourthTempReading = new Reading(30, validDate4);
        Reading fifthTempReading = new Reading(-5, validDate5);
        validTemperatureSensor.addReading(firstTempReading);
        validTemperatureSensor.addReading(secondTempReading);
        validTemperatureSensor.addReading(thirdTempReading);
        validTemperatureSensor.addReading(fourthTempReading);
        validTemperatureSensor.addReading(fifthTempReading);
        validSensorList.add(validTemperatureSensor);

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

        Sensor validRainfallSensor = new Sensor("RF12366", "RainOne", new TypeSensor("rainfall", "l/m2 "), new Local
                (21, 41, 11), new Date());
        Reading firstRainReading = new Reading(40, validDate4);
        Reading secondRainReading = new Reading(10, validDate5);
        Reading thirdRainReading = new Reading(10, validDate6);
        validRainfallSensor.addReading(firstRainReading);
        validRainfallSensor.addReading(secondRainReading);
        validRainfallSensor.addReading(thirdRainReading);
        validSensorList.add(validRainfallSensor);
        validRoom = mapper.roomToDTO(validRoom1);

    }


    @Test
    void seeIfGetCurrentRoomTemperatureWorks() {
        // Arrange

        double expectedResult = 20.0;

        // Act

        double actualResult = controller.getCurrentRoomTemperature(validRoom, validHouse);

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void SeeIfGetCurrentTemperatureInTheHouseAreaWorks() {
        // Arrange

        validHouseArea.setSensorList(validSensorList);
        validHouse.setMotherArea(validHouseArea);
        double expectedResult = 20.0;

        // Act

        double actualResult = controller.getHouseAreaTemperature(validHouse);


        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAverageOfReadingsBetweenTwoGivenDates() {
        // Arrange
//        Date intervalStart = new GregorianCalendar(2017, Calendar.DECEMBER, 2).getTime();
//        Date intervalEnd = new GregorianCalendar(2017, Calendar.DECEMBER, 20).getTime();
        validHouseArea.setSensorList(validSensorList);
        double expectedResult = 25;

        // Act
        double actualResult = controller.getAverageRainfallInterval(validHouse, validDate4, validDate5);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAverageRainfallIntervalThrowsExceptionReadingListEmpty() {
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Date day = new Date();
            try {
                day = validSdf.parse("07/11/2019 10:02:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            controller.getAverageRainfallInterval(validHouse, day, day);
        });
        //Assert
        assertEquals("Warning: Average value not calculated - No readings available.", exception.getMessage());
    }

    @Test
    void getAverageRainfallIntervalThrowsExceptionReadingListNull() {
        //Arrange
        validSensorList = null;
        //Act
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
            controller.getAverageRainfallInterval(validHouse, date, date2);
        });
        //Assert
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
        validHouseArea.setSensorList(validSensorList);
        double expectedResult = 40;

        // Act
        double actualResult = controller.getTotalRainfallOnGivenDay(validHouse, date);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDayNoRainfall() {
        // Arrange
        validHouseArea.setSensorList(validSensorList);

        // Act
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            Date date = new Date();
            try {
                date = validSdf.parse("03/12/2018 10:02:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            controller.getTotalRainfallOnGivenDay(validHouse, date);
        });

        // Assert
        assertEquals("Warning: Total value was not calculated - No readings were available.", exception.getMessage());
    }

    @Test
    void ensureThatWeGetTotalReadingsWithoutSensors() {
        // Arrange

        SensorList emptyList = new SensorList();
        validHouseArea.setSensorList(emptyList);

        // Act

        Throwable exception = assertThrows(IllegalStateException.class, () -> controller.getTotalRainfallOnGivenDay(validHouse, validDate4));

        // Assert

        assertEquals("Warning: Total value could not be calculated - No readings were available.", exception.getMessage());
    }

    @Test
    void ensureThatWeGetTotalReadingsWithoutRainFallSensorsAndWithoutReadings() {
        // Arrange
        Date date = new Date();
        try {
            date = validSdf.parse("02/02/2015 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SensorList temperatureList = new SensorList();
        Sensor temperatureSensor = new Sensor("RF12345", "temperature sensor", new TypeSensor("temperature", "celsius"), new Local(21, 20, 20), date);
        temperatureList.add(temperatureSensor);
        validHouseArea.setSensorList(temperatureList);

        // Act

        Throwable exception = assertThrows(IllegalStateException.class, () -> controller.getTotalRainfallOnGivenDay(validHouse, validDate4));

        // Assert

        assertEquals("Warning: Total value could not be calculated - No readings were available.", exception.getMessage());
    }

    @Test
    void ensureThatWeGetTotalReadingsWithoutWithoutReadings() {
        // Arrange
        Date date = new Date();
        try {
            date = validSdf.parse("02/02/2015 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SensorList rainFallSensorList = new SensorList();
        Sensor rainfallSensor = new Sensor("RF12345", "rainfall sensor", new TypeSensor("rainfall", "L"), new Local(21, 20, 20), date);
        rainFallSensorList.add(rainfallSensor);
        validHouseArea.setSensorList(rainFallSensorList);

        // Act

        Throwable exception = assertThrows(IllegalStateException.class, () -> controller.getTotalRainfallOnGivenDay(validHouse, validDate4));

        // Assert

        assertEquals("Warning: Total value could not be calculated - No readings were available.", exception.getMessage());
    }

    @Test
    void roomMaxTemperatureInGivenDay() {
        // Arrange

        Reading secondReading = new Reading(30, validDate4);
        Reading thirdReading = new Reading(3, validDate5);
        validTemperatureSensor.addReading(secondReading);
        validTemperatureSensor.addReading(thirdReading);
        double expectedResult = 30;

        // Act

        double actualResult = controller.getDayMaxTemperature(validRoom, validDate4, validHouse);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getRoomName() {
        // Arrange

        String expectedResult = "Bedroom";

        // Act

        String actualResult = controller.getRoomName(validRoom, validHouse);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetHighestTempAmplitudeDateSuccess() {
        validHouseArea.setSensorList(validSensorList);

        Date expectedResult = validDate1;

        Date actualResult = controller.getHighestTempAmplitudeDate(validHouse, validDate4, validDate1);

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetHighestTempAmplitudeDateThrowsException() {
        //Test if it throws exception when there is no readings available for the period requested
        SensorList invalidSensorList = new SensorList();
        validHouseArea.setSensorList(invalidSensorList);
        GregorianCalendar startDate = new GregorianCalendar(2013, Calendar.JANUARY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2014, Calendar.JANUARY, 1);

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                controller.getHighestTempAmplitudeDate(validHouse, startDate.getTime(), endDate.getTime()));

        assertEquals("Warning: Temperature amplitude value not calculated - No readings available.",
                exception.getMessage());
    }

    @Test
    void seeIfGetHighestTempAmplitudeValueSuccess() {
        validHouseArea.setSensorList(validSensorList);

        double expectedResult = 15.0;

        double actualResult = controller.getHighestTempAmplitudeValue(validHouse, validDate1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetHighestTempAmplitudeValueThrowsException() {
        //Test if it throws exception when there is no readings available for the period requested
        SensorList invalidSensorList = new SensorList();
        validHouseArea.setSensorList(invalidSensorList);
        GregorianCalendar startDate = new GregorianCalendar(2013, Calendar.JANUARY, 1);

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                controller.getHighestTempAmplitudeValue(validHouse, startDate.getTime()));

        assertEquals("Warning: Temperature amplitude value not calculated - No readings available.",
                exception.getMessage());
    }

    @Test
    void seeIfWeGetLastColdestDayInIntervalDateAndValueThrowsException() {
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                controller.getLastColdestDayInInterval(validHouse, validDate6, validDate1));

        assertEquals("No readings available.",
                exception.getMessage());
    }

    /**
     * Tests for getFirstHottestDayInPeriod
     *
     * Given a valid set of readings in tested period:
     * Given a valid set of readings in tested period, multiple days have the highest temperature:
     * -Should return the first day with highest temperature
     */

    @Test
    void testGetFirstHottestDayInPeriod() {
        // Arrange
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
        validHouseArea.setSensorList(validSensorList);
        Date expectedResult = validDate06;
        // Act
        Date actualResult = controller.getFirstHottestDayInPeriod(validHouse, validDate01, validDate10);
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    /**
     *  Given a valid set of unorganized readings in tested period:
     *  -Should return the first day with highest temperature
     */

    @Test
    void testGetFirstHottestDayInPeriod2() {
        // Arrange
        Reading r02 = new Reading(1, validDate02); // MaxAmplitude First Date
        Reading r03 = new Reading(22, validDate03);
        Reading r04 = new Reading(10, validDate04); // Cold First Date
        Reading r05 = new Reading(40, validDate05);
        Reading r07 = new Reading(10.2, validDate07);
        Reading r08 = new Reading(12, validDate08);
        Reading r06 = new Reading(40.2, validDate06); // Hottest First Date
        Reading r09 = new Reading(40.2, validDate09); // Hottest Final Date ALSO MaxAmplitude Final Date
        Reading r10 = new Reading(21.2, validDate10); // Cold Final Date ALSO MaxAmplitude Final Date
        Reading r01 = new Reading(40, validDate01); // MaxAmplitude First Date

        validTemperatureSensor.addReading(r02);
        validTemperatureSensor.addReading(r03);
        validTemperatureSensor.addReading(r04);
        validTemperatureSensor.addReading(r05);
        validTemperatureSensor.addReading(r07);
        validTemperatureSensor.addReading(r08);
        validTemperatureSensor.addReading(r06);
        validTemperatureSensor.addReading(r09);
        validTemperatureSensor.addReading(r10);
        validTemperatureSensor.addReading(r01);
        validHouseArea.setSensorList(validSensorList);
        Date expectedResult = validDate06;
        // Act
        Date actualResult = controller.getFirstHottestDayInPeriod(validHouse, validDate01, validDate10);
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetFirstHottestDayInPeriodIfUnorganizedReadings() {
        // Arrange
        validHouseArea.setSensorList(validSensorList);
        Date expectedResult = validDate4;
        // Act
        Date actualResult = controller.getFirstHottestDayInPeriod(validHouse, validDate4, validDate2);
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Given a valid set of readings in tested period, a given day has multiple readings and one of them is the highest
     * temperature:
     * -Should return the day with highest temperature
     */

    @Test
    void testGetFirstHottestDayMultipleReadingsSameDay() {
        // Arrange
        Reading r20 = new Reading(20, validDate20);
        Reading r21 = new Reading(22, validDate21);
        Reading r22 = new Reading(30, validDate22);
        Reading r23 = new Reading(25, validDate23);
        Reading r24 = new Reading(21, validDate24);
        Reading r25 = new Reading(20, validDate25);
        validTemperatureSensor.addReading(r20);
        validTemperatureSensor.addReading(r21);
        validTemperatureSensor.addReading(r22);
        validTemperatureSensor.addReading(r23);
        validTemperatureSensor.addReading(r24);
        validTemperatureSensor.addReading(r25);
        validHouseArea.setSensorList(validSensorList);
        // Act
        Date expectedResult = validDate22;
        Date actualResult = controller.getFirstHottestDayInPeriod(validHouse, validDate20, validDate25);
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Given a valid house without sensors on list:
     * -Should return message to User
     */

    @Test
    void testGetFirstHottestDayHouseWithoutSensors() {
        // Arrange
        House house = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)), 60,
                180, new ArrayList<>());
        SensorList sList = new SensorList();
        RoomList roomL = new RoomList();
        house.setRoomList(roomL);
        Room roomD = new Room("Bedroom", 2, 15, 15, 10);
        roomL.add(roomD);
        roomD.setSensorList(sList);
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                controller.getFirstHottestDayInPeriod(house, validDate01, validDate02));
        // Assert
        assertEquals("Warning: No temperature readings available in given period.",
                exception.getMessage());
    }

    /**
     * Given a valid house with sensor without readings:
     * -Should return message to User
     */

    @Test
    void testGetFirstHottestDayInPeriodThrowsExceptionMessage() {
        // Arrange
        validHouseArea.setSensorList(validSensorList);
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                controller.getFirstHottestDayInPeriod(validHouse, validDate01, validDate02));
        // Assert
        assertEquals("Warning: No temperature readings available in given period.",
                exception.getMessage());
    }

    /**
     * Given a valid set of readings not contained in period:
     * -Should return message to User
     */

    @Test
    void testGetFirstHottestDayValidReadingsNotContained() {
        // Arrange
        Reading r20 = new Reading(20, validDate20);
        Reading r21 = new Reading(22, validDate21);
        Reading r25 = new Reading(20, validDate25);
        validTemperatureSensor.addReading(r20);
        validTemperatureSensor.addReading(r21);
        validTemperatureSensor.addReading(r25);
        validHouseArea.setSensorList(validSensorList);
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                controller.getFirstHottestDayInPeriod(validHouse, validDate22, validDate24));
        // Assert
        assertEquals("Warning: No temperature readings available in given period.",
                exception.getMessage());
    }
}