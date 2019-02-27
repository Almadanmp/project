package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.*;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherDT;
import pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterDT;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;

/**
 * House tests class.
 */

class HouseTest {

    // Common artifacts for testing in this class.

    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";
    private House validHouse;
    private GeographicArea validArea;
    private Sensor firstValidSensor;

    @BeforeEach
    void arrangeArtifacts() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        validArea = new GeographicArea("Europe", new TypeArea("Continent"), 3500, 3000,
                new Local(20, 12, 33));
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)), 60,
                180, deviceTypeString);
        firstValidSensor = new Sensor("tempOne", new TypeSensor("Temperature", "Celsius"), new Local(
                30, 20, 10), new Date());
        Sensor secondValidSensor = new Sensor("rainOne", new TypeSensor("Rainfall", "l/m2"), new Local(21,
                40, 15), new Date());
        validArea.addSensor(firstValidSensor);
        validArea.addSensor(secondValidSensor);
        validHouse.setMotherArea(validArea);
    }

    @Test
    void seeDistanceToSensor() {
        // Act

        double actualResult = validHouse.calculateDistanceToSensor(firstValidSensor);

        // Assert

        assertEquals(1111.9492664455872, actualResult, 0.01);
    }


    @Test
    void seeIfGetClosestSensorOfGivenTypeWorks() {
        // Act

        Sensor result = validHouse.getClosestSensorOfGivenType("Temperature");

        // Assert

        assertEquals(firstValidSensor, result);
    }

    @Test
    void getMinDistanceToSensorOfGivenType() {
        // Arrange

        double expectedResult = 1111.9492664455872;

        // Act

        double actualResult = validHouse.getMinDistanceToSensorOfGivenType("Temperature");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getMinDistanceToSensorOfGivenTypeSamePosition() {
        // Arrange

        Sensor testSensor = new Sensor("tempTwo", new TypeSensor("Temperature", "Celsius"), new Local(20,
                20, 20), new Date());
        validArea.addSensor(testSensor);
        double expectedResult = 0;

        // Act

        double actualResult = validHouse.getMinDistanceToSensorOfGivenType("Temperature");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualWorksEqualObject() {
        // Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House testHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)), 60,
                180, deviceTypeString);

        // Act

        boolean actualResult = validHouse.equals(testHouse);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeEqualToSameObject() {
        // Act

        boolean actualResult = validHouse.equals(validHouse); // Needed for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        // Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House testHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida",
                "4455-125", "Porto"),
                new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)), 60,
                180, deviceTypeString);

        // Act

        boolean actualResult = validHouse.equals(testHouse);

        // Assert

        assertTrue(actualResult);
    }


    @Test
    void seeIfEqualsWorksNotInstanceOf() {
        // Arrange

        Room testRoom = new Room("Bedroom", 2, 30, 30, 10);

        // Act

        boolean actualResult = validHouse.equals(testRoom); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddRoomWorks() {
        // Arrange

        Room testRoom = new Room("Bedroom", 2, 30, 30, 10);

        // Act

        boolean actualResult = validHouse.addRoom(testRoom);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddRoomWorksDuplicate() {
        // Arrange

        Room testRoom = new Room("Bedroom", 2, 30, 30, 10);
        validHouse.addRoom(testRoom);

        // Act

        boolean actualResult = validHouse.addRoom(testRoom);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintGridListWorksEmpty() {
        // Arrange

        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = validHouse.buildGridListString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintGridListWorks() {
        // Arrange

        String expectedResult = "---------------\n" +
                "0) Designation: Home | Max Power: 440.0\n" +
                "---------------\n";
        EnergyGrid testGrid = new EnergyGrid("Home", 440);
        validHouse.addGrid(testGrid);

        // Act

        String actualResult = validHouse.buildGridListString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetHouseLocationWorks() {
        // Arrange

        Local expectedResult = new Local(7, 78, 50);

        // Act

        validHouse.setLocation(7, 78, 50);
        Local actualResult = validHouse.getLocation();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        //Arrange

        Room testRoom = new Room("Kitchen", 0, 12, 30, 10);
        Device testDevice = new WaterHeater(new WaterHeaterSpec());
        testDevice.setNominalPower(30.0);
        testRoom.addDevice(testDevice);
        validHouse.addRoom(testRoom);
        double expectedResult = 30;

        // Act

        double actualResult = validHouse.getNominalPower();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetEnergyGridList() {
        // Arrange

        EnergyGridList gridList = new EnergyGridList();
        EnergyGrid testGrid = new EnergyGrid("Garden", 300);
        gridList.addGrid(testGrid);
        validHouse.setGridList(gridList);
        EnergyGridList expectedResult = new EnergyGridList();
        expectedResult.addGrid(testGrid);

        // Act

        EnergyGridList actualResult = validHouse.getGridList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getDailyHouseConsumptionPerTypeTest() {
        // Arrange

        Device waterHeater = new WaterHeater(new WaterHeaterSpec());
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 15D);
        Room testRoom = new Room("Office", 2, 30, 30, 10);
        testRoom.addDevice(waterHeater);
        validHouse.addRoom(testRoom);
        double expectedResult = 0.4;

        // Act

        double result = validHouse.getDailyConsumptionByDeviceType("WaterHeater", 1440);

        // Assert

        assertEquals(expectedResult, result);
    }


    @Test
    void getHouseDevicesOfGivenType() {
        // Arrange

        Device waterHeater = new WaterHeater(new WaterHeaterSpec());
        waterHeater.setName("WaterHeaterOne");
        Room testRoom = new Room("Kitchen", 0, 15, 15, 10);
        testRoom.addDevice(waterHeater);
        validHouse.addRoom(testRoom);
        DeviceList expectedResult = new DeviceList();
        expectedResult.add(waterHeater);

        // Act

        DeviceList actualResult = validHouse.getDevicesOfGivenType("WaterHeater");

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetClosestSensorOfTypeWorks() {
        // Arrange

        Date laterDate = new GregorianCalendar(21, Calendar.MARCH, 2018).getTime();
        Date earlierDate = new GregorianCalendar(21, Calendar.FEBRUARY, 2018).getTime();
        ReadingList readingList = new ReadingList();
        Reading firstReading = new Reading(15, laterDate);
        Reading secondReading = new Reading(12, earlierDate);
        readingList.addReading(firstReading);
        readingList.addReading(secondReading);
        firstValidSensor.setReadingList(readingList);

        // Act

        Sensor actualResult = validHouse.getClosestSensorOfGivenType("Temperature");

        // Assert

        assertEquals(firstValidSensor, actualResult);
    }

    @Test
    void seeIfGetClosestSensorOfTypeWorksByDistance() {
        // Arrange

        Sensor testSensor = new Sensor("rainOne", new TypeSensor("Rainfall", "l/m2"), new Local(20,
                21, 20), new Date());
        validArea.addSensor(testSensor);

        // Act

        Sensor actualResult = validHouse.getClosestSensorOfGivenType("Rainfall");

        // Assert

        assertEquals(testSensor, actualResult);
    }


    @Test
    void seeIfGetClosestSensorOfTypeWorksNoSensor() {
        // Arrange

        Sensor expectedResult = new Sensor("EmptyList", new TypeSensor("temperature", ""),
                new Local(0, 0, 0), new Date());

        // Act

        Sensor actualResult = validHouse.getClosestSensorOfGivenType("Movement");

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetDeviceListWorksEmptyList() {
        // Arrange

        List<Device> expectedResult = new ArrayList<>();

        // Act

        List<Device> actualResult = validHouse.getDeviceList().getList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfBuildDeviceTypeStringWorks() {
        // Arrange

        String expectedResult = "0) DeviceType: Fridge\n";

        // Act

        String actualResult = validHouse.buildDeviceTypeString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfBuildDeviceTypeStringWorksEmptyList() {
        // Arrange

        House testHouse = new House("Mock", new Address("Mock", "Mock", "Mock"),
                new Local(4, 5, 50), new GeographicArea("Mock", new TypeArea("Mock"),
                60, 180, new Local(30, 40, 30)), 20,
                5, new ArrayList<>());
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String result = testHouse.buildDeviceTypeString();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetDeviceTypeListWorks() {
        // Arrange

        List<String> deviceTypePaths = new ArrayList<>();
        deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherDT");
        deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterDT");

        // Act

        validHouse.buildDeviceTypeList(deviceTypePaths);
        List<DeviceType> deviceTypeList = validHouse.getDeviceTypeList();

        // Assert

        assertEquals(deviceTypeList.size(), 2);
        assertTrue(deviceTypeList.get(0) instanceof DishwasherDT);
        assertTrue(deviceTypeList.get(1) instanceof WaterHeaterDT);
    }

    @Test
    void seeIfGetDeviceTypeListWorksIllegalArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    List<String> deviceTypePaths = new ArrayList<>();
                    deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.Dish");

                    validHouse.buildDeviceTypeList(deviceTypePaths);
                });
    }

    @Test
    void setGridMeteringPeriod() {
        // Act

        validHouse.setGridMeteringPeriod(8);
        validHouse.setDeviceMeteringPeriod(10);
        double actualResultGrid = validHouse.getGridMeteringPeriod();
        double actualResultDevice = validHouse.getDeviceMeteringPeriod();

        // Assert

        assertEquals(8, actualResultGrid);
        assertEquals(10, actualResultDevice);
    }

    @Test
    void seeIfAddGridToHouseWorks() {
        // Arrange

        EnergyGrid firstGrid = new EnergyGrid("GridHome", 25);
        EnergyGrid secondGrid = new EnergyGrid("GridGarden", 55);
        EnergyGrid repeatedFirstGrid = new EnergyGrid("GridHome", 25);

        // Act

        boolean actualResult1 = validHouse.addGrid(firstGrid);
        boolean actualResult2 = validHouse.addGrid(secondGrid);
        boolean actualResult3 = validHouse.addGrid(repeatedFirstGrid);
        boolean actualResult4 = validHouse.addGrid(firstGrid);

        // Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void seeIfCreateRoomWorks() {
        // Arrange

        Room expectedResult = new Room("Kitchen", 1, 1, 1, 1);

        // Act

        Room actualResult = validHouse.createRoom("Kitchen", 1, 1, 1,
                1);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomListWorks() {
        // Arrange

        Room testRoom = new Room("Office", 1, 20, 15, 10);
        RoomList roomList = new RoomList();
        roomList.add(testRoom);
        RoomList expectedResult = new RoomList();
        expectedResult.add(testRoom);
        validHouse.setRoomList(roomList);

        // Act

        RoomList actualResult = validHouse.getRoomList();


        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomListWorksEmpty() {
        // Arrange

        RoomList roomList = new RoomList();
        RoomList expectedResult = new RoomList();
        validHouse.setRoomList(roomList);

        // Act

        RoomList actualResult = validHouse.getRoomList();


        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validHouse.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}