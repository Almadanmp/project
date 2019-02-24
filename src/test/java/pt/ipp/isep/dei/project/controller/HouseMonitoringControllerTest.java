package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

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
    private Room validRoom;
    private Sensor validTemperatureSensor; // Is a temperature sensor with valid readings.
    private SensorList validSensorList; // Contains the mock sensors mentioned above.

    @BeforeEach
    void arrangeArtifacts() {
        // Sets Up Geographic Area, House, Room and Lists.

        validHouseArea = new GeographicArea("Portugal", new TypeArea("Country"), 300,
                200, new Local(45, 30, 30));
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)), 60,
                180, new ArrayList<>());
        validHouse.setMotherArea(validHouseArea);
        validRoom = new Room("Bedroom", 2, 15, 15, 10);
        RoomList validRoomList = new RoomList();
        validRoomList.addRoom(validRoom);
        validSensorList = new SensorList();
        validRoom.setSensorList(validSensorList);

        // Sets up a valid temperature sensor with valid Readings.

        validTemperatureSensor = new Sensor("TempOne", new TypeSensor("Temperature", "Celsius"),
                new Local(21, 10, 15),
                new Date());
        Reading firstTempReading = new Reading(15, new GregorianCalendar(2018,
                Calendar.APRIL, 1, 15, 0,
                0).getTime());
        Reading secondTempReading = new Reading(20, new GregorianCalendar(2018,
                Calendar.APRIL, 1, 17, 0,
                0).getTime());
        Reading thirdTempReading = new Reading(30, new GregorianCalendar(2018,
                Calendar.APRIL, 1, 16, 0,
                0).getTime());
        validTemperatureSensor.addReading(firstTempReading);
        validTemperatureSensor.addReading(secondTempReading);
        validTemperatureSensor.addReading(thirdTempReading);
        validSensorList.addSensor(validTemperatureSensor);

        // Sets up a valid rainfall sensor with valid readings.

        Sensor validRainfallSensor = new Sensor("RainOne", new TypeSensor("rainfall", "l/m2 "), new Local
                (21, 41, 11), new Date());
        Reading firstRainReading = new Reading(40, new GregorianCalendar(2017,
                Calendar.DECEMBER, 3, 15, 0,
                0).getTime());
        Reading secondRainReading = new Reading(10, new GregorianCalendar(2017,
                Calendar.DECEMBER, 8, 15, 0,
                0).getTime());
        Reading thirdRainReading = new Reading(10, new GregorianCalendar(2017,
                Calendar.DECEMBER, 19, 15, 0,
                0).getTime());
        validRainfallSensor.addReading(firstRainReading);
        validRainfallSensor.addReading(secondRainReading);
        validRainfallSensor.addReading(thirdRainReading);
        validSensorList.addSensor(validRainfallSensor);
    }


    @Test
    void seeIfGetCurrentRoomTemperatureWorks() {
        // Arrange

        double expectedResult = 20;

        // Act

        double actualResult = controller.getCurrentRoomTemperature(validRoom);

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void SeeIfGetCurrentTemperatureInTheHouseAreaWorks() {
        // Arrange

        validHouseArea.setSensorList(validSensorList);
        validHouse.setMotherArea(validHouseArea);
        double expectedResult = 20;

        // Act

        double actualResult = controller.getHouseAreaTemperature(validHouse);


        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAverageOfReadingsBetweenTwoGivenDates() {
        // Arrange
        Date intervalStart = new GregorianCalendar(2017, Calendar.DECEMBER, 2).getTime();
        Date intervalEnd = new GregorianCalendar(2017, Calendar.DECEMBER, 20).getTime();
        validHouseArea.setSensorList(validSensorList);
        double expectedResult = 20;

        // Act
        double actualResult = controller.getAverageRainfallInterval(validHouse, intervalStart, intervalEnd);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAverageRainfallIntervalThrowsExceptionReadingListEmpty() {
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.getAverageRainfallInterval(validHouse, (new GregorianCalendar(2019, Calendar.NOVEMBER, 7).getTime()), (new GregorianCalendar(2019, Calendar.NOVEMBER, 7).getTime()));
        });
        //Assert
        assertEquals("Warning: average value not calculated - no readings available.", exception.getMessage());
    }

    @Test
    void getAverageRainfallIntervalThrowsExceptionReadingListNull() {
        //Arrange
        validSensorList = null;
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.getAverageRainfallInterval(validHouse, (new GregorianCalendar(2019, Calendar.NOVEMBER, 7).getTime()), (new GregorianCalendar(2019, Calendar.NOVEMBER, 7).getTime()));
        });
        //Assert
        assertEquals("Warning: average value not calculated - no readings available.", exception.getMessage());
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDay() {
        // Arrange
        Date day = new GregorianCalendar(2017, Calendar.DECEMBER, 3).getTime();
        validHouseArea.setSensorList(validSensorList);
        double expectedResult = 40;

        // Act
        double actualResult = controller.getTotalRainfallOnGivenDay(validHouse, day);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDayNoRainfall() {
        // Arrange
        Date day = new GregorianCalendar(2018, Calendar.DECEMBER, 3).getTime();
        validHouseArea.setSensorList(validSensorList);

        // Act
        double actualResult = controller.getTotalRainfallOnGivenDay(validHouse, day);

        // Assert
        assertEquals(0, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsWithoutSensors() {
        // Arrange

        SensorList emptyList = new SensorList();
        Date day = new GregorianCalendar(2017, Calendar.DECEMBER, 3).getTime();
        validHouseArea.setSensorList(emptyList);

        // Act

        double actualResult = controller.getTotalRainfallOnGivenDay(validHouse, day);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsWithoutRainFallSensorsAndWithoutReadings() {
        // Arrange

        SensorList temperatureList = new SensorList();
        Sensor temperatureSensor = new Sensor("temperature sensor", new TypeSensor("temperature", "celsius"), new Local(21, 20, 20), new GregorianCalendar(2015, 2, 2).getTime());
        temperatureList.addSensor(temperatureSensor);
        Date day = new GregorianCalendar(2017, Calendar.DECEMBER, 3).getTime();
        validHouseArea.setSensorList(temperatureList);

        // Act

        double actualResult = controller.getTotalRainfallOnGivenDay(validHouse, day);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsWithoutWithoutReadings() {
        // Arrange

        SensorList rainFallSensorList = new SensorList();
        Sensor rainfallSensor = new Sensor("rainfall sensor", new TypeSensor("rainfall", "L"), new Local(21, 20, 20), new GregorianCalendar(2015, 2, 2).getTime());
        rainFallSensorList.addSensor(rainfallSensor);
        Date day = new GregorianCalendar(2017, Calendar.DECEMBER, 3).getTime();
        validHouseArea.setSensorList(rainFallSensorList);

        // Act

        double actualResult = controller.getTotalRainfallOnGivenDay(validHouse, day);

        // Assert

        assertEquals(0, actualResult);
    }

    @Test
    void roomMaxTemperatureInGivenDay() {
        // Arrange

        Reading secondReading = new Reading(30, new GregorianCalendar(2018,
                Calendar.APRIL, 1, 1, 0,
                0).getTime());
        Reading thirdReading = new Reading(3, new GregorianCalendar(2018,
                Calendar.APRIL, 1, 20, 0,
                0).getTime());
        validTemperatureSensor.addReading(secondReading);
        validTemperatureSensor.addReading(thirdReading);
        double expectedResult = 30;

        // Act

        double actualResult = controller.getDayMaxTemperature(validRoom, new GregorianCalendar(2018,
                Calendar.APRIL, 1).getTime());

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getRoomName() {
        // Arrange

        String expectedResult = "Bedroom";

        // Act

        String actualResult = controller.getRoomName(validRoom);

        // Assert

        assertEquals(expectedResult, actualResult);

    }
}