package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Kettler;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.KettlerSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertTrue;

/**
 * Room tests class.
 */

class RoomTest {
    // Common testing artifacts for this class.


    private static Room validRoom; // Room with a valid temperature sensor with valid readings, and a valid device.
    private Sensor validSensor; // Valid temperature sensor with valid readings.
    private Device validDevice; // Valid device, namely of WaterHeater type.
    private Reading validReading; // Valid temperature reading at February 2, 2018, 00:00:00.

    @BeforeEach
    void arrangeArtifacts() {
        validRoom = new Room("Bedroom", 2, 30, 40, 10);
        validSensor = new Sensor("tempOne", new TypeSensor("temperature", "Celsius"), new Date());
        validSensor.setActive(true);
        validRoom.addSensor(validSensor);
        validDevice = new WaterHeater(new WaterHeaterSpec());
        validDevice.setName("WaterHeater");
        validDevice.setNominalPower(21.0);
        validDevice.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        validDevice.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 40D);
        validDevice.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        validDevice.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 30D);
        validRoom.addDevice(validDevice);
        validReading = new Reading(21, new GregorianCalendar(2018, Calendar.FEBRUARY, 2).
                getTime());
        validSensor.addReading(validReading);
    }

    @Test
    void seeIfRemoveDeviceWorks() {
        // Act

        boolean actualResult = validRoom.removeDevice(validDevice);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfRemoveDeviceWorksNoDevice() {
        // Arrange

        validRoom.removeDevice(validDevice);

        // Act

        boolean actualResult = validRoom.removeDevice(validDevice);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfBuildDeviceListStringWorks() {
        // Arrange

        String expectedResult = "---------------\n" +
                "0) device Name: WaterHeater, device Type: WaterHeater, device Nominal Power: 21.0\n" +
                "---------------\n";

        // Act

        String actualResult = validRoom.buildDeviceListString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksNegative() {
        // Arrange

        validReading.setValue(-12);
        double expectedResult = -12;

        // Act

        double actualResult = validRoom.getCurrentRoomTemperature();

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorks() {
        // Arrange

        double expectedResult = 21;

        // Act

        double actualResult = validRoom.getCurrentRoomTemperature();

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksNoSensors() {
        // Arrange

        SensorList emptyList = new SensorList();
        validRoom.setSensorList(emptyList);

        // Assert

        assertThrows(IllegalArgumentException.class, validRoom::getCurrentRoomTemperature);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksNoReadings() {
        // Arrange

        validSensor.setReadingList(new ReadingList());

        // Assert

        assertThrows(IllegalArgumentException.class, validRoom::getCurrentRoomTemperature);
    }

    @Test
    void seeIfAddSensorWorks() {
        // Arrange

        Sensor testSensor = new Sensor("RF12345", "testSensor", new TypeSensor("Temperature", "Celsius"),
                new Local(1, 1, 50), new Date());

        // Act

        boolean actualResult = validRoom.addSensor(testSensor);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddSensorWorksFalse() {
        // Act

        boolean actualResult = validRoom.addSensor(validSensor);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNull() {
        // Act

        boolean result = validRoom.equals(null); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfEqualsWorkDifClass() {
        // Arrange

        TypeSensor testType = new TypeSensor("Rainfall", "l/m2");

        // Act

        boolean result = validRoom.equals(testType); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfPrintRoomWorks() {
        // Arrange
        String expectedResult = "Bedroom, 2, 30.0, 40.0, 10.0.\n";

        // Act

        String actualResult = validRoom.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetNominalPowerWorksNoDevices() {
        // Arrange

        validRoom.removeDevice(validDevice);

        // Act

        double actualResult = validRoom.getNominalPower();

        // Assert

        Assertions.assertEquals(0, actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        // Arrange

        double expectedResult = 21;

        // Act

        double actualResult = validRoom.getNominalPower();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetEstimateConsumptionByDeviceTypeWorks() {
        // Arrange

        double expectedResult = 97.97112;

        // Act

        double actualResult = validRoom.getEstimateConsumptionOverTimeByDeviceType("WaterHeater", 1440);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddDeviceWorksDuplicate() {
        // Act

        boolean actualResult = validRoom.addDevice(validDevice);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddDeviceWorksTrue() {
        // Arrange

        validRoom.removeDevice(validDevice);

        // Act

        boolean actualResult = validRoom.addDevice(validDevice);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void getRoomDevicesOfGivenTypeSuccess() {
        // Arrange

        DeviceList expectedResult = new DeviceList();
        expectedResult.add(validDevice);

        // Act

        DeviceList actualResult = validRoom.getDevicesOfGivenType("WaterHeater");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getRoomDevicesOfGivenTypeFails() {
        // Arrange

        DeviceList expectedResult = new DeviceList();

        // Act

        DeviceList actualResult = validRoom.getDevicesOfGivenType("MassageChair");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDeviceListWorks() {
        // Arrange

        DeviceList expectedResult = new DeviceList();
        expectedResult.add(validDevice);

        // Act

        DeviceList actualResult = validRoom.getDeviceList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDeviceListWorksNoDevices() {
        // Arrange

        validRoom.removeDevice(validDevice);
        DeviceList expectedResult = new DeviceList();

        // Act

        DeviceList actualResult = validRoom.getDeviceList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRooMDevicesToDeviceListWorks() {
        // Arrange

        DeviceList expectedResult = new DeviceList();
        expectedResult.add(validDevice);
        DeviceList actualResult = new DeviceList();

        // Act

        validRoom.addRoomDevicesToDeviceList(actualResult);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRoomDevicesToDeviceListWorksAlreadyContained() {
        // Arrange

        DeviceList expectedResult = new DeviceList();
        expectedResult.add(validDevice);
        DeviceList actualResult = new DeviceList();
        actualResult.add(validDevice);

        // Act

        validRoom.addRoomDevicesToDeviceList(actualResult);

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 1.4287455;

        // Act

        double actualResult = validRoom.getEnergyConsumption(21);

        // Assert

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSensorsByTypeWorks() {
        // Arrange

        SensorList expectedResult = new SensorList();
        expectedResult.add(validSensor);

        // Act

        SensorList actualResult = validRoom.getSensorsOfGivenType("temperature");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMaxTemperatureGivenDayWorks() {
        // Arrange

        Date dayToTest = new GregorianCalendar(2018, Calendar.FEBRUARY, 2).
                getTime();
        double expectedResult = 21;

        // Act

        double actualResult = validRoom.getMaxTemperatureOnGivenDay(dayToTest);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMaxTemperatureGivenDayWorksMultipleReadings() {
        // Arrange

        Reading secondReading = new Reading(18, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                12, 2, 0).getTime());
        Reading thirdReading = new Reading(28, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                11, 2, 0).getTime());
        validSensor.addReading(secondReading);
        validSensor.addReading(thirdReading);
        Date dayToTest = new GregorianCalendar(2018, Calendar.FEBRUARY, 2).
                getTime();
        double expectedResult = 28;

        // Act

        double actualResult = validRoom.getMaxTemperatureOnGivenDay(dayToTest);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMaxTemperatureGivenDayWorksNoReadings() {
        // Arrange

        Date dayToTest = new GregorianCalendar(2018, Calendar.FEBRUARY, 2).
                getTime();
        Room noSensorRoom = new Room("Mock", 1, 2, 3, 4);
        validSensor.setReadingList(new ReadingList()); // validSensor has proper sensors, but they have no readings.


        // Act and Assert

        assertThrows(IllegalArgumentException.class, () -> noSensorRoom.getMaxTemperatureOnGivenDay(dayToTest));
        assertThrows(NoSuchElementException.class, () -> validRoom.getMaxTemperatureOnGivenDay(dayToTest));
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksSameMinuteReadings() {
        // Arrange

        Reading secondReading = new Reading(18, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                12, 2, 2).getTime());
        Reading thirdReading = new Reading(21, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                12, 2, 13).getTime());
        validSensor.addReading(secondReading);
        validSensor.addReading(thirdReading);
        double expectedResult = 21;

        // Act

        double actualResult = validRoom.getCurrentRoomTemperature();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksMultipleSensors() {
        // Arrange

        Sensor firstSensor = new Sensor("firstSensor", new TypeSensor("temperature", "Celsius"), new Date()); // Has one reading, not the most recent.
        Sensor secondSensor = new Sensor("secondSensor", new TypeSensor("temperature", "Celsius"), new Date()); // Has the most recent reading and another reading.
        Sensor thirdSensor = new Sensor("secondSensor", new TypeSensor("temperature", "Celsius"), new Date()); // Has no readings.
        firstSensor.setActive(true);
        secondSensor.setActive(true);
        thirdSensor.setActive(true);
        validRoom.addSensor(firstSensor);
        validRoom.addSensor(secondSensor);
        validRoom.addSensor(thirdSensor);
        Reading secondReading = new Reading(18, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                12, 2, 2).getTime());
        Reading thirdReading = new Reading(21, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                12, 2, 13).getTime());
        Reading mostRecentReading = new Reading(30, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                15, 2, 13).getTime());
        firstSensor.addReading(secondReading);
        secondSensor.addReading(thirdReading);
        secondSensor.addReading(mostRecentReading);
        double expectedResult = 30;

        // Act

        double actualResult = validRoom.getCurrentRoomTemperature();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksIllegalArguments() {
        //Arrange

        Room noSensorsRoom = new Room("Mock", 1, 2, 3, 4);
        validSensor.setReadingList(new ReadingList()); // Valid Sensor now has sensors, but no readings.

        //Act and Assert

        assertThrows(IllegalArgumentException.class, noSensorsRoom::getCurrentRoomTemperature);
        assertThrows(IllegalArgumentException.class, validRoom::getCurrentRoomTemperature);
    }


    @Test
    void seeIfGetSensorListWorks() {
        // Arrange

        SensorList expectedResult = new SensorList();
        expectedResult.add(validSensor);

        // Act

        SensorList actualResult = validRoom.getSensorList();

        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfGetSensorListWorksNoSensors() {
        // Arrange

        SensorList expectedResult = new SensorList();
        validRoom.setSensorList(new SensorList());

        // Act

        SensorList actualResult = validRoom.getSensorList();

        // Assert

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfGetSensorListWorksMultipleSensors() {
        // Arrange

        Sensor testSensor = new Sensor("Mock", new TypeSensor("Temperature", "Celsius"), new Date());
        validRoom.addSensor(testSensor);
        SensorList expectedResult = new SensorList();
        expectedResult.add(validSensor);
        expectedResult.add(testSensor);

        // Act

        SensorList actualResult = validRoom.getSensorList();

        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfGetId() {
        // Arrange

        validRoom.setUniqueID(UUID.randomUUID());

        // Act

        UUID uuid = validRoom.getUniqueID();

        // Assert

        assertTrue(uuid instanceof UUID); // Needed for Sonarqube testing purposes.
    }

    @Test
    void getByIndexWithEmptyDeviceList() {
        //Arrange

        Room noDevicesRoom = new Room("noDevices", 3, 24, 25, 3);

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> noDevicesRoom.getDeviceByIndex(0));

        //Assert

        assertEquals("The device list is empty.", exception.getMessage());
    }

    @Test
    void deviceListSize() {
        //Arrange

        Room emptyDeviceList = new Room("emptyDeviceList", 2, 20, 20, 3);

        //Act

        int actualResult1 = emptyDeviceList.getDeviceListSize();

        //Assert Empty List

        Assertions.assertEquals(0, actualResult1);

        //Act

        int actualResult2 = validRoom.getDeviceListSize();

        //Assert One Grid

        Assertions.assertEquals(1, actualResult2);
    }

    @Test
    void seeIfIsSensorListEmptyWorks() {
        //Arrange

        Room emptyDeviceList = new Room("emptyDeviceList", 2, 20, 20, 3);

        //Act

        boolean actualResult1 = emptyDeviceList.isSensorListEmpty();
        boolean actualResult2 = validRoom.isSensorListEmpty();

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    void seeIfIsDeviceListEmptyWorks() {
        //Arrange

        Room emptyDeviceList = new Room("emptyDeviceList", 2, 20, 20, 3);

        //Act

        boolean actualResult1 = emptyDeviceList.isDeviceListEmpty();
        boolean actualResult2 = validRoom.isDeviceListEmpty();

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    void seeIfGetDeviceByIndexWorks() {

        //Act

        Device actualResult = validRoom.getDeviceByIndex(0);

        //Assert

        assertEquals(validDevice, actualResult);
    }

    @Test
    void seeIfGetDeviceByIndexThrowsException() {

        //Arrange

        Room emptyDeviceList = new Room("emptyDeviceList", 2, 20, 20, 3);

        //Assert

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyDeviceList.getDeviceByIndex(0);
        });
    }

    @Test
    void seeIfBuildDevicesStringByTypeWorks() {

        //Arrange

        Room emptyDeviceList = new Room("emptyDeviceList", 2, 20, 20, 3);
        Device kettler = new Kettler(new KettlerSpec());
        validRoom.addDevice(kettler);
        //Act

        String actualResult1 = emptyDeviceList.buildDevicesStringByType("WaterHeater");
        String actualResult2 = validRoom.buildDevicesStringByType("WaterHeater");


        //Assert

        assertEquals("", actualResult1);
        assertEquals("Device type: WaterHeater | Device name: WaterHeater | Nominal power: 21.0 | Room: Bedroom | \n", actualResult2);
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
}