package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Kettler;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.KettlerSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.sensor.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Room tests class.
 */

class RoomTest {
    // Common testing artifacts for this class.


    private static Room validRoom; // Room with a valid temperature sensor with valid readings, and a valid device.
    private AreaSensor validAreaSensor; // Valid temperature sensor with valid readings.
    private Device validDevice; // Valid device, namely of WaterHeater type.
    private AreaReading validAreaReading; // Valid temperature reading at February 2, 2018, 00:00:00.

    @BeforeEach
    void arrangeArtifacts() {
        validRoom = new Room("Bedroom","Double Bedroom", 2, 30, 40, 10);
        validAreaSensor = new AreaSensor("tempOne", new SensorType("temperature", "Celsius"), new Date());
        validAreaSensor.setActive(true);
        validRoom.addSensor(validAreaSensor);
        validDevice = new WaterHeater(new WaterHeaterSpec());
        validDevice.setName("WaterHeater");
        validDevice.setNominalPower(21.0);
        validDevice.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        validDevice.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 40D);
        validDevice.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        validDevice.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 30D);
        validRoom.addDevice(validDevice);
        validAreaReading = new AreaReading(21, new GregorianCalendar(2018, Calendar.FEBRUARY, 2).
                getTime(), "C");
        validAreaSensor.addReading(validAreaReading);
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

        validAreaReading.setValue(-12);
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

        AreaSensorList emptyList = new AreaSensorList();
        validRoom.setSensorList(emptyList);

        // Assert

        assertThrows(IllegalArgumentException.class, validRoom::getCurrentRoomTemperature);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksNoReadings() {
        // Arrange

        validAreaSensor.setAreaReadingList(new AreaReadingList());

        // Assert

        assertThrows(IllegalArgumentException.class, validRoom::getCurrentRoomTemperature);
    }

    @Test
    void seeIfAddSensorWorks() {
        // Arrange

        AreaSensor testAreaSensor = new AreaSensor("RF12345", "testSensor", new SensorType("Temperature", "Celsius"),
                new Local(1, 1, 50), new Date());

        // Act

        boolean actualResult = validRoom.addSensor(testAreaSensor);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddSensorWorksFalse() {
        // Act

        boolean actualResult = validRoom.addSensor(validAreaSensor);

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

        SensorType testType = new SensorType("Rainfall", "l/m2");

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

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSensorsByTypeWorks() {
        // Arrange

        AreaSensorList expectedResult = new AreaSensorList();
        expectedResult.add(validAreaSensor);

        // Act

        AreaSensorList actualResult = validRoom.getSensorsOfGivenType("temperature");

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

        AreaReading secondAreaReading = new AreaReading(18, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                12, 2, 0).getTime(), "C");
        AreaReading thirdAreaReading = new AreaReading(28, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                11, 2, 0).getTime(), "C");
        validAreaSensor.addReading(secondAreaReading);
        validAreaSensor.addReading(thirdAreaReading);
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
        Room noSensorRoom = new Room("Mock","Mock", 1, 2, 3, 4);
        validAreaSensor.setAreaReadingList(new AreaReadingList()); // validSensor has proper sensors, but they have no readings.


        // Act and Assert

        assertThrows(IllegalArgumentException.class, () -> noSensorRoom.getMaxTemperatureOnGivenDay(dayToTest));
        assertThrows(NoSuchElementException.class, () -> validRoom.getMaxTemperatureOnGivenDay(dayToTest));
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksSameMinuteReadings() {
        // Arrange

        AreaReading secondAreaReading = new AreaReading(18, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                12, 2, 2).getTime(), "C");
        AreaReading thirdAreaReading = new AreaReading(21, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                12, 2, 13).getTime(), "C");
        validAreaSensor.addReading(secondAreaReading);
        validAreaSensor.addReading(thirdAreaReading);
        double expectedResult = 21;

        // Act

        double actualResult = validRoom.getCurrentRoomTemperature();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksMultipleSensors() {
        // Arrange

        AreaSensor firstAreaSensor = new AreaSensor("firstSensor", new SensorType("temperature", "Celsius"), new Date()); // Has one reading, not the most recent.
        AreaSensor secondAreaSensor = new AreaSensor("secondSensor", new SensorType("temperature", "Celsius"), new Date()); // Has the most recent reading and another reading.
        AreaSensor thirdAreaSensor = new AreaSensor("secondSensor", new SensorType("temperature", "Celsius"), new Date()); // Has no readings.
        firstAreaSensor.setActive(true);
        secondAreaSensor.setActive(true);
        thirdAreaSensor.setActive(true);
        validRoom.addSensor(firstAreaSensor);
        validRoom.addSensor(secondAreaSensor);
        validRoom.addSensor(thirdAreaSensor);
        AreaReading secondAreaReading = new AreaReading(18, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                12, 2, 2).getTime(), "C");
        AreaReading thirdAreaReading = new AreaReading(21, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                12, 2, 13).getTime(), "C");
        AreaReading mostRecentAreaReading = new AreaReading(30, new GregorianCalendar(2018, Calendar.FEBRUARY, 2,
                15, 2, 13).getTime(), "C");
        firstAreaSensor.addReading(secondAreaReading);
        secondAreaSensor.addReading(thirdAreaReading);
        secondAreaSensor.addReading(mostRecentAreaReading);
        double expectedResult = 30;

        // Act

        double actualResult = validRoom.getCurrentRoomTemperature();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorksIllegalArguments() {
        //Arrange

        Room noSensorsRoom = new Room("Mock","Mock", 1, 2, 3, 4);
        validAreaSensor.setAreaReadingList(new AreaReadingList()); // Valid Sensor now has sensors, but no readings.

        //Act and Assert

        assertThrows(IllegalArgumentException.class, noSensorsRoom::getCurrentRoomTemperature);
        assertThrows(IllegalArgumentException.class, validRoom::getCurrentRoomTemperature);
    }


    @Test
    void seeIfGetSensorListWorks() {
        // Arrange

        AreaSensorList expectedResult = new AreaSensorList();
        expectedResult.add(validAreaSensor);

        // Act

        AreaSensorList actualResult = validRoom.getSensorList();

        // Assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfGetSensorListWorksNoSensors() {
        // Arrange

        AreaSensorList expectedResult = new AreaSensorList();
        validRoom.setSensorList(new AreaSensorList());

        // Act

        AreaSensorList actualResult = validRoom.getSensorList();

        // Assert

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void seeIfGetSensorListWorksMultipleSensors() {
        // Arrange

        AreaSensor testAreaSensor = new AreaSensor("Mock", new SensorType("Temperature", "Celsius"), new Date());
        validRoom.addSensor(testAreaSensor);
        AreaSensorList expectedResult = new AreaSensorList();
        expectedResult.add(validAreaSensor);
        expectedResult.add(testAreaSensor);

        // Act

        AreaSensorList actualResult = validRoom.getSensorList();

        // Assert
        assertEquals(actualResult, expectedResult);
    }


    @Test
    void getByIndexWithEmptyDeviceList() {
        //Arrange

        Room noDevicesRoom = new Room("noDevices","noDevices", 3, 24, 25, 3);

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> noDevicesRoom.getDeviceByIndex(0));

        //Assert

        assertEquals("The device list is empty.", exception.getMessage());
    }

    @Test
    void deviceListSize() {
        //Arrange

        Room emptyDeviceList = new Room("emptyDeviceList","emptyDeviceList", 2, 20, 20, 3);

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

        Room emptyDeviceList = new Room("emptyDeviceList","emptyDeviceList", 2, 20, 20, 3);

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

        Room emptyDeviceList = new Room("emptyDeviceList","emptyDeviceList", 2, 20, 20, 3);

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

        Room emptyDeviceList = new Room("emptyDeviceList","emptyDeviceList", 2, 20, 20, 3);

        //Assert

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyDeviceList.getDeviceByIndex(0);
        });
    }

    @Test
    void seeIfBuildDevicesStringByTypeWorks() {

        //Arrange

        Room emptyDeviceList = new Room("emptyDeviceList","emptyDeviceList", 2, 20, 20, 3);
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