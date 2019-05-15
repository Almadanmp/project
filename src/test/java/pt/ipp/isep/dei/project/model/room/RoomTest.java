package pt.ipp.isep.dei.project.model.room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.device.*;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.KettlerSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Room tests class.
 */
@ExtendWith(MockitoExtension.class)
class RoomTest {
    // Common testing artifacts for this class.

    private House validHouse;
    private GeographicArea validArea;
    private Room validRoom; // Room with a valid temperature sensor with valid readings, and a valid device.
    private RoomSensor firstValidSensor; // Valid temperature sensor with valid readings.
    private RoomSensor secondValidSensor; // Valid temperature sensor without readings.
    private Device validDevice; // Valid device, namely of WaterHeater type.
    private Reading validReading; // Valid temperature reading at February 2, 2018, 00:00:00.
    private SimpleDateFormat validSdf; // SimpleDateFormat dd/MM/yyyy HH:mm:ss
    private Date validDate1;
    private Date validDate2;
    private Date validDate3;

    @BeforeEach
    void arrangeArtifacts() {
        validRoom = new Room("Bedroom", "Double Bedroom", 2, 30, 40, 10, "Room1");
        firstValidSensor = new RoomSensor("T23875", "tempOne", "temperature", new Date());
        firstValidSensor.setActive(true);
        secondValidSensor = new RoomSensor("T1234", "tempTwo", "temperature", new Date());
        secondValidSensor.setActive(true);
        validDevice = new WaterHeater(new WaterHeaterSpec());
        validDevice.setName("WaterHeater");
        validDevice.setNominalPower(21.0);
        validDevice.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 12D);
        validDevice.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 40D);
        validDevice.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        validDevice.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 30D);
        validRoom.addDevice(validDevice);
        validReading = new Reading(21, new GregorianCalendar(2018, Calendar.FEBRUARY, 2).
                getTime(), "C", "Test");
        validSdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Datas desorganizadas, para testar noção de first/last
            validDate1 = validSdf.parse("01/03/2018");
            validDate2 = validSdf.parse("10/03/2018");
            validDate3 = validSdf.parse("20/02/2018");

        } catch (ParseException c) {
            c.printStackTrace();
        }
        validArea = new GeographicArea("Europe", "Continent", 3500, 3000,
                new Local(20, 12, 33));
        this.validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        this.validHouse.setMotherAreaID(validArea.getId());

    }

    @Test
    void seeIfSetHouseIDWorks() {
        //Arrange

        validRoom.setHouseID("newHouseID");
        String expectedResult = "newHouseID";

        //Act

        String actualResult = validRoom.getHouseID();

        //Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetRoomSensorByIDWorks() {
        //Arrange

        validRoom.addSensor(firstValidSensor);
        validRoom.addSensor(secondValidSensor);

        //Act

        RoomSensor actualResult = validRoom.getRoomSensorByID("T1234");

        //Assert

        assertEquals(secondValidSensor, actualResult);
    }

    @Test
    void seeIfGetRoomSensorByIDWorksWhenSensorDoesNotExist() {
        //Arrange

        validRoom.addSensor(firstValidSensor);

        // Assert

        assertThrows(IllegalArgumentException.class,
                () -> validRoom.getRoomSensorByID("invalidSensorID"));
    }


    @Test
    void seeIfConstructorIsAccessed() {
        // Assert

        Room room = new Room();

        // Act

        String actualResult = room.getId();

        // Assert

        assertNull(actualResult);
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

        Room room = new Room("Room", "Description", 2, 2, 2, 2, "House");
        Device fridge = new Fridge(new FridgeSpec());
        fridge.setName("FridgeOne");
        fridge.setNominalPower(20);

        room.addDevice(fridge);
        room.addDevice(validDevice);

        double expectedResult = 97.97112;

        // Act

        double actualResult = room.getEstimateConsumptionOverTimeByDeviceType("WaterHeater", 1440);

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
    void getByIndexWithEmptyDeviceList() {
        //Arrange

        Room noDevicesRoom = new Room("noDevices", "noDevices", 3, 24, 25, 3, "Room1");

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> noDevicesRoom.getDeviceByIndex(0));

        //Assert

        assertEquals("The device list is empty.", exception.getMessage());
    }

    @Test
    void deviceListSize() {
        //Arrange

        Room emptyDeviceList = new Room("emptyDeviceList", "emptyDeviceList", 2, 20, 20, 3, "Room1");

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
    void seeIfIsDeviceListEmptyWorks() {
        //Arrange

        Room emptyDeviceList = new Room("emptyDeviceList", "emptyDeviceList", 2, 20, 20, 3, "Room1");

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

        Room emptyDeviceList = new Room("emptyDeviceList", "emptyDeviceList", 2, 20, 20, 3, "Room1");

        //Assert

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> emptyDeviceList.getDeviceByIndex(0));
    }

    @Test
    void seeIfBuildDevicesStringByTypeWorks() {

        //Arrange

        Room emptyDeviceList = new Room("emptyDeviceList", "emptyDeviceList", 2, 20, 20, 3, "Room1");
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

    @Test
    void seeIfGetRoomSensorsOfGivenTypeWorks() {
        //Arrange
        List<RoomSensor> roomSensorList = new ArrayList<>();
        RoomSensor humiditySensor = new RoomSensor("H1", "HumidityOne", "humidity", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        roomSensorList.add(firstValidSensor);
        roomSensorList.add(humiditySensor);
        List<RoomSensor> expectedResult = new ArrayList<>();
        expectedResult.add(firstValidSensor);
        validRoom.setRoomSensors(roomSensorList);
        //Act
        List<RoomSensor> actualResult = validRoom.getRoomSensorsOfGivenType("temperature");
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorks() {
        //Arrange
        List<Reading> readingList = new ArrayList<>();
        readingList.add(validReading);
        List<RoomSensor> roomSensorList = new ArrayList<>();
        firstValidSensor.setReadings(readingList);
        roomSensorList.add(firstValidSensor);
        readingList.add(validReading);
        roomSensorList.add(firstValidSensor);
        validRoom.setRoomSensors(roomSensorList);
        double expectedResult = 21;
        //Act
        double actualResult = validRoom.getCurrentRoomTemperature();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> validRoom.getCurrentRoomTemperature());
    }

    @Test
    void seeIfGetMaxTemperatureOnGivenDayWorks() {
        //Arrange
        List<Reading> readingList = new ArrayList<>();
        readingList.add(validReading);
        Reading reading = new Reading(30, new GregorianCalendar(2018, Calendar.FEBRUARY, 2).
                getTime(), "C", "Test");
        readingList.add(reading);
        List<RoomSensor> roomSensorList = new ArrayList<>();
        firstValidSensor.setReadings(readingList);
        roomSensorList.add(firstValidSensor);
        readingList.add(validReading);
        roomSensorList.add(firstValidSensor);
        validRoom.setRoomSensors(roomSensorList);
        //Act
        double actualResult = validRoom.getMaxTemperatureOnGivenDay(new GregorianCalendar(2018, Calendar.FEBRUARY, 2).getTime());
        //Assert
        assertEquals(30, actualResult);
    }

    @Test
    void seeIfGetMaxTemperatureOnGivenDayThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> validRoom.getMaxTemperatureOnGivenDay(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime()));
    }

    @Test
    void seeIfGetMaxTemperatureOnGivenDayThrowsNoSuchElementException() {
        //Arrange
        List<Reading> readingList = new ArrayList<>();
        readingList.add(validReading);
        List<RoomSensor> roomSensorList = new ArrayList<>();
        firstValidSensor.setReadings(readingList);
        roomSensorList.add(firstValidSensor);
        readingList.add(validReading);
        roomSensorList.add(firstValidSensor);
        validRoom.setRoomSensors(roomSensorList);
        //Assert
        assertThrows(NoSuchElementException.class, () -> validRoom.getMaxTemperatureOnGivenDay(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime()));
    }

    @Test
    void seeIfAddSensorWorks() {
        //Act
        boolean actualResult = validRoom.addSensor(firstValidSensor);
        boolean actualResult1 = validRoom.addSensor(firstValidSensor);
        //Assert
        assertTrue(actualResult);
        assertFalse(actualResult1);
    }

    @Test
    void seeIfGetSensorWorks() {
        //Arrange
        validRoom.addSensor(firstValidSensor);
        RoomSensor expectedResult = firstValidSensor;
        //Act
        RoomSensor actualResult = validRoom.getSensor(0);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomSensorsWorks() {
        //Arrange
        List<RoomSensor> expectedResult = new ArrayList<>();
        expectedResult.add(firstValidSensor);
        validRoom.setRoomSensors(expectedResult);
        //Act
        List<RoomSensor> actualResult = validRoom.getRoomSensors();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetHouseIdWorks() {
        //Arrange
        String expectedResult = "Room1";
        //Act
        String actualResult = validRoom.getHouseID();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeifGetReadingsAboveILimit() {
//
//        //ListReadings
//        List<Reading> readings = new ArrayList<>();
//        Date firstDate = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();
//        Date secondDate = new GregorianCalendar(2018, Calendar.APRIL, 25).getTime();
//        Reading reading1 = new Reading(31, firstDate, "C", "Test");
//        Reading reading2 = new Reading(31, secondDate, "C", "Test");
//        readings.add(reading1);
//        readings.add(reading2);
//
//        //House
//        List<String> deviceTypeString = new ArrayList<>();
//        deviceTypeString.add("pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType");
//        House house = new House("house", new Address("Rua Dr. António Bernardino de Almeida", "431",
//                "4455-125", "Porto", "Portugal"),
//                new Local(20, 20, 20), 60,
//                180, deviceTypeString);
//
//        //Room
//        Room room = new Room("Bedroom", "Double Bedroom", 2, 30, 40, 10, house.getId(), "Grid1");
//
//        //MotherArea
//        GeographicArea geoArea = new GeographicArea("Europe", new AreaType("Continent"), 3500, 3000,
//                new Local(20, 12, 33));
//        house.setMotherAreaID(geoArea);
//
//
//
//        //RoomSensor
//        RoomSensor roomSensor = new RoomSensor("T23875", "tempOne", new SensorType("temperature", "Celsius"), new Date(), room.getId());
//        roomSensor.setActive(true);
//        roomSensor.setReadings(readings);
//
//        List<RoomSensor> roomSensors = new ArrayList<>();
//        roomSensors.add(roomSensor);
//        room.setRoomSensors(roomSensors);
//
//
//        assertEquals(readings, room.getReadingsAboveCategoryILimit(readings, house));
//
//    }

//    @Test
//    void seeifGetReadingsAboveLimit() {
//
//        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("temperature", "Celsius"), new Local(10, 10, 10), validDate1, 6008L);
//        validAreaSensor.setActive(true);
//        Reading areaReading1 = new Reading(20D, validDate1, "C", "sensorID");
//        Reading areaReading2 = new Reading(21D, validDate2, "C", "sensorID");
//        Reading areaReading3 = new Reading(18D, validDate3, "C", "sensorID");
//        List<Reading> areaReadings = new ArrayList<>();
//        areaReadings.add(areaReading1);
//        areaReadings.add(areaReading2);
//        areaReadings.add(areaReading3);
//        validAreaSensor.setReadings(areaReadings);
//        validArea.addSensor(validAreaSensor);
//
//        Reading reading1 = new Reading(31, validDate1, "C", "Test");
//        Reading reading2 = new Reading(20, validDate2, "C", "Test1");
//        Reading reading3 = new Reading(29, validDate3, "C", "Test");
//
//        List<Reading> readings = new ArrayList<>();
//
//        readings.add(reading1);
//        readings.add(reading2);
//        readings.add(reading3);
//
//        RoomSensor roomSensor = new RoomSensor("T32875", "SensOne", new SensorType("Temperature", "Celsius"), validDate1, "RoomAD");
//        SensorType sensorType = new SensorType("temperature", "Celsius");
//        roomSensor.setSensorType(sensorType);
//        roomSensor.setReadings(readings);
//        roomSensor.setRoomId(validRoom.getId());
//
//        List<RoomSensor> roomSensors = new ArrayList<>();
//        roomSensors.add(roomSensor);
//        validRoom.setRoomSensors(roomSensors);
//
//        reading1.setSensorID(roomSensor.getId());
//        reading2.setSensorID(roomSensor.getId());
//        reading3.setSensorID(roomSensor.getId());
//
//
//        List<Reading> expectedResult = new ArrayList<>();
//        expectedResult.add(reading1);
//        expectedResult.add(reading3);
//
//        // Act
//
//        List<Reading> actualResult = validRoom.getReadingsAboveCategoryILimit(readings, validHouse);
//        List<Reading> actualResult2 = validRoom.getReadingsAboveCategoryIILimit(readings, validHouse);
//        List<Reading> actualResult3 = validRoom.getReadingsAboveCategoryIIILimit(readings, validHouse);
//
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//        assertEquals(expectedResult,actualResult2);
//        assertEquals(expectedResult,actualResult3);
//    }

//    @Test
//    void seeifGetReadingsAboveLimitLowTemperatures() {
//
//        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("temperature", "Celsius"), new Local(10, 10, 10), validDate1, 6008L);
//        validAreaSensor.setActive(true);
//        Reading areaReading1 = new Reading(20D, validDate1, "C", "sensorID");
//        Reading areaReading2 = new Reading(21D, validDate2, "C", "sensorID");
//        Reading areaReading3 = new Reading(18D, validDate3, "C", "sensorID");
//        List<Reading> areaReadings = new ArrayList<>();
//        areaReadings.add(areaReading1);
//        areaReadings.add(areaReading2);
//        areaReadings.add(areaReading3);
//        validAreaSensor.setReadings(areaReadings);
//        validArea.addSensor(validAreaSensor);
//
//        Reading reading1 = new Reading(10, validDate1, "C", "Test");
//        Reading reading2 = new Reading(10, validDate2, "C", "Test1");
//        Reading reading3 = new Reading(10, validDate3, "C", "Test");
//
//        List<Reading> readings = new ArrayList<>();
//
//        readings.add(reading1);
//        readings.add(reading2);
//        readings.add(reading3);
//
//        RoomSensor roomSensor = new RoomSensor("T32875", "SensOne", new SensorType("Temperature", "Celsius"), validDate1, "RoomAD");
//        SensorType sensorType = new SensorType("temperature", "Celsius");
//        roomSensor.setSensorType(sensorType);
//        roomSensor.setReadings(readings);
//        roomSensor.setRoomId(validRoom.getId());
//
//        List<RoomSensor> roomSensors = new ArrayList<>();
//        roomSensors.add(roomSensor);
//        validRoom.setRoomSensors(roomSensors);
//
//        reading1.setSensorID(roomSensor.getId());
//        reading2.setSensorID(roomSensor.getId());
//        reading3.setSensorID(roomSensor.getId());
//
//
//        List<Reading> expectedResult = new ArrayList<>();
//
//
//        // Act
//
//        List<Reading> actualResult = validRoom.getReadingsAboveCategoryILimit(readings, validHouse);
//        List<Reading> actualResult2 = validRoom.getReadingsAboveCategoryIILimit(readings, validHouse);
//        List<Reading> actualResult3 = validRoom.getReadingsAboveCategoryIIILimit(readings, validHouse);
//
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//        assertEquals(expectedResult,actualResult2);
//        assertEquals(expectedResult,actualResult3);
//    }

//    @Test
//    void seeifGetReadingsAboveLimitHighTemperatures() {
//
//        AreaSensor validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("temperature", "Celsius"), new Local(10, 10, 10), validDate1, 6008L);
//        validAreaSensor.setActive(true);
//        Reading areaReading1 = new Reading(20D, validDate1, "C", "sensorID");
//        Reading areaReading2 = new Reading(21D, validDate2, "C", "sensorID");
//        Reading areaReading3 = new Reading(18D, validDate3, "C", "sensorID");
//        List<Reading> areaReadings = new ArrayList<>();
//        areaReadings.add(areaReading1);
//        areaReadings.add(areaReading2);
//        areaReadings.add(areaReading3);
//        validAreaSensor.setReadings(areaReadings);
//        validArea.addSensor(validAreaSensor);
//
//        Reading reading1 = new Reading(100, validDate1, "C", "Test");
//        Reading reading2 = new Reading(100, validDate2, "C", "Test1");
//        Reading reading3 = new Reading(100, validDate3, "C", "Test");
//
//        List<Reading> readings = new ArrayList<>();
//
//        readings.add(reading1);
//        readings.add(reading2);
//        readings.add(reading3);
//
//        RoomSensor roomSensor = new RoomSensor("T32875", "SensOne", new SensorType("Temperature", "Celsius"), validDate1, "RoomAD");
//        SensorType sensorType = new SensorType("temperature", "Celsius");
//        roomSensor.setSensorType(sensorType);
//        roomSensor.setReadings(readings);
//        roomSensor.setRoomId(validRoom.getId());
//
//        List<RoomSensor> roomSensors = new ArrayList<>();
//        roomSensors.add(roomSensor);
//        validRoom.setRoomSensors(roomSensors);
//
//        reading1.setSensorID(roomSensor.getId());
//        reading2.setSensorID(roomSensor.getId());
//        reading3.setSensorID(roomSensor.getId());
//
//
//        List<Reading> expectedResult = new ArrayList<>();
//        expectedResult.add(reading1);
//        expectedResult.add(reading2);
//        expectedResult.add(reading3);
//
//
//        // Act
//
//        List<Reading> actualResult = validRoom.getReadingsAboveCategoryILimit(readings, validHouse);
//        List<Reading> actualResult2 = validRoom.getReadingsAboveCategoryIILimit(readings, validHouse);
//        List<Reading> actualResult3 = validRoom.getReadingsAboveCategoryIIILimit(readings, validHouse);
//
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//        assertEquals(expectedResult,actualResult2);
//        assertEquals(expectedResult,actualResult3);
//    }


}