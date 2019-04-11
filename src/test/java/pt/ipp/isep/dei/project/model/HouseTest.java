package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherType;
import pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterType;
import pt.ipp.isep.dei.project.model.sensor.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * House tests class.
 */
@ExtendWith(MockitoExtension.class)
class HouseTest {

    // Common artifacts for testing in this class.

    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private House validHouse;
    private GeographicArea validArea;
    private AreaSensor firstValidAreaSensor;



    @BeforeEach
    void arrangeArtifacts() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        validArea = new GeographicArea("Europe", new AreaType("Continent"), 3500, 3000,
                new Local(20, 12, 33));
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        validHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));
        firstValidAreaSensor = new AreaSensor("RF12345", "tempOne", new SensorType("Temperature", "Celsius"), new Local(
                30, 20, 10), new Date(), 6008L);
        AreaSensor secondValidAreaSensor = new AreaSensor("RF17745", "rainOne", new SensorType("Rainfall", "l/m2"), new Local(21,
                40, 15), new Date(), 6008L);
        validArea.addSensor(firstValidAreaSensor);
        validArea.addSensor(secondValidAreaSensor);
        validHouse.setMotherArea(validArea);
    }

    @Test
    void seeDistanceToSensor() {
        // Act

        double actualResult = validHouse.calculateDistanceToSensor(firstValidAreaSensor);

        // Assert

        assertEquals(1111.9492664455872, actualResult, 0.01);
    }


    @Test
    void seeIfGetClosestSensorOfGivenTypeWorks() {
        // Act

        AreaSensor result = validHouse.getClosestSensorOfGivenType("Temperature");

        // Assert

        assertEquals(firstValidAreaSensor, result);
    }

    @Test
    void getMinDistanceToSensorOfGivenType() {
        // Arrange

        double expectedResult = 1111.9492664455872;
        AreaSensorService validAreaSensorService = new AreaSensorService();
        validAreaSensorService.add(firstValidAreaSensor);
        // Act

        double actualResult = validHouse.getMinDistanceToSensorOfGivenType(validAreaSensorService);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getMinDistanceToSensorOfGivenTypeSamePosition() {
        // Arrange

        AreaSensor testAreaSensor = new AreaSensor("RF12666", "tempTwo", new SensorType("Temperature", "Celsius"), new Local(20,
                20, 20), new Date(), 6008L);
        validArea.addSensor(testAreaSensor);
        double expectedResult = 0;

        // Act

        double actualResult = validHouse.getMinDistanceToSensorOfGivenType(validArea.getSensorList());

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualWorksWithNull() {
        // Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House testHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        testHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));

        // Act

        boolean actualResult = validHouse.equals(null);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualWorksEqualObject() {
        // Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House testHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        testHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));

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
        House testHouse = new House("ISEP1", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        testHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));

        // Act

        boolean actualResult = validHouse.equals(testHouse);

        // Assert

        assertTrue(actualResult);
    }


    @Test
    void seeIfEqualsWorksNotInstanceOf() {
        // Arrange

        Room testRoom = new Room("Bedroom", "Single Bedroom", 2, 30, 30, 10,"Room1","Grid1");

        // Act

        boolean actualResult = validHouse.equals(testRoom); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddRoomWorks() {
        // Arrange

        Room testRoom = new Room("Bedroom", "Single Bedroom", 2, 30, 30, 10,"Room1","Grid1");

        // Act

        boolean actualResult = validHouse.addRoom(testRoom);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddRoomWorksDuplicate() {
        // Arrange

        Room testRoom = new Room("Bedroom", "Double Bedroom", 2, 30, 30, 10,"Room1","Grid1");
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
                "34) Designation: Home | Max Power: 440.0\n" +
                "---------------\n";
        EnergyGrid testGrid = new EnergyGrid("Home", 440, "34576");
        testGrid.setId(34L);
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
    void seeIfGetSetHouseLocationWorksAltitude() {
        // Arrange

        double expectedResult = 70;

        // Act

        validHouse.setLocation(7, 78, 70);
        double actualResult = validHouse.getAltitude();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        //Arrange

        Room testRoom = new Room("Kitchen", "Ground Floor Kitchen", 0, 12, 30, 10,"Room1","Grid1");
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

        EnergyGridService gridList = new EnergyGridService();
        EnergyGrid testGrid = new EnergyGrid("Garden", 300, "34576");
        testGrid.setId(23L);
        gridList.addGrid(testGrid);
        validHouse.setGridList(gridList);

        // Act

        EnergyGridService actualResult = validHouse.getGridList();

        // Assert

        assertEquals(gridList, actualResult);
    }


    @Test
    void getDailyHouseConsumptionPerTypeTest() {
        // Arrange

        Device waterHeater = new WaterHeater(new WaterHeaterSpec());
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        waterHeater.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        waterHeater.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        waterHeater.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER_HEAT, 15D);
        Room testRoom = new Room("Office", "2nd Floor Office", 2, 30, 30, 10,"Room1","Grid1");
        testRoom.addDevice(waterHeater);
        validHouse.addRoom(testRoom);
        double expectedResult = 0.4;

        // Act

        double result = validHouse.getDailyConsumptionByDeviceType("WaterHeater", 1440);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfIsMotherAreaNullBothConditions() {
        // Act

        boolean actualResult1 = validHouse.isMotherAreaNull();
        validHouse.setMotherArea(null);
        boolean actualResult2 = validHouse.isMotherAreaNull();

        // Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
    }

    @Test
    void getHouseDevicesOfGivenType() {
        // Arrange

        Device waterHeater = new WaterHeater(new WaterHeaterSpec());
        waterHeater.setName("WaterHeaterOne");
        Room testRoom = new Room("Kitchen", "Ground Floor Kitchen", 0, 15, 15, 10,"Room1","Grid1");
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
        ReadingService readingService = new ReadingService();
        Reading firstReading = new Reading(15, laterDate, "C", firstValidAreaSensor.getId());
        Reading secondReading = new Reading(12, earlierDate, "C", firstValidAreaSensor.getId());
        readingService.addReading(firstReading);
        readingService.addReading(secondReading);
        firstValidAreaSensor.setReadingService(readingService);

        // Act

        AreaSensor actualResult = validHouse.getClosestSensorOfGivenType("Temperature");

        // Assert

        assertEquals(firstValidAreaSensor, actualResult);
    }

    @Test
    void seeIfGetClosestSensorOfTypeWorksWithTwoSensors() {
        // Arrange
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date earlierDate = new Date();
        Date laterDate = new Date();

        try {
            earlierDate = validSdf.parse("21/02/2018 10:02:00");
            laterDate = validSdf.parse("21/03/2018 10:02:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ReadingService readingService = new ReadingService();
        Reading firstReading = new Reading(15, laterDate, "C", firstValidAreaSensor.getId());
        Reading secondReading = new Reading(12, earlierDate, "C", firstValidAreaSensor.getId());
        readingService.addReading(firstReading);
        readingService.addReading(secondReading);
        firstValidAreaSensor.setReadingService(readingService);

        AreaSensor secondAreaSensor = new AreaSensor("RF4321", "tempTwo", new SensorType("Temperature", "Celsius"), new Local(
                30, 20, 10), new Date(), 6008L);
        secondAreaSensor.addReading(new Reading(15, earlierDate, "C", firstValidAreaSensor.getId()));
        validArea.addSensor(secondAreaSensor);

        // Act

        AreaSensor actualResult = validHouse.getClosestSensorOfGivenType("Temperature");

        // Assert

        assertEquals(firstValidAreaSensor, actualResult);
    }

    @Test
    void seeIfGetClosestSensorOfTypeWorksByDistance() {
        // Arrange

        AreaSensor testAreaSensor = new AreaSensor("RF12345", "rainOne", new SensorType("Rainfall", "l/m2"), new Local(20,
                21, 20), new Date(), 6008L);
        validArea.addSensor(testAreaSensor);

        // Act

        AreaSensor actualResult = validHouse.getClosestSensorOfGivenType("Rainfall");

        // Assert

        assertEquals(testAreaSensor, actualResult);
    }


    @Test
    void seeIfGetClosestSensorOfTypeWorksNoSensor() {
        // Arrange

        AreaSensor expectedResult = new AreaSensor("RF12345", "EmptyList", new SensorType("temperature", ""),
                new Local(0, 0, 0), new Date(), 6008L);

        // Act

        AreaSensor actualResult = validHouse.getClosestSensorOfGivenType("Movement");

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

        House testHouse = new House("Mock", new Address("Mock", "Mock", "Mock", "Mock", "Mock"),
                new Local(4, 5, 50), 20,
                5, new ArrayList<>());
        testHouse.setMotherArea(new GeographicArea("Mock", new AreaType("Mock"),
                60, 180, new Local(30, 40, 30)));
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
        deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherType");
        deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterType");

        // Act

        validHouse.buildDeviceTypeList(deviceTypePaths);
        List<DeviceType> deviceTypeList = validHouse.getDeviceTypeList();

        // Assert

        assertEquals(deviceTypeList.size(), 2);
        assertTrue(deviceTypeList.get(0) instanceof DishwasherType);
        assertTrue(deviceTypeList.get(1) instanceof WaterHeaterType);
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

        EnergyGrid firstGrid = new EnergyGrid("GridHome", 25, "34576");
        EnergyGrid secondGrid = new EnergyGrid("GridGarden", 55, "34576");
        EnergyGrid repeatedFirstGrid = new EnergyGrid("GridHome", 25, "34576");

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

//    @Test
//    void seeIfCreateRoomWorks() {
//        // Arrange
//
//        Room expectedResult = new Room("Kitchen", "1st Floor Kitchen", 1, 1, 1, 1,"Room1","Grid1");
//
//        // Act
//
//        Room actualResult = validHouse.createRoom("Kitchen", "1st Floor Kitchen", 1, 1, 1,
//                1,"Room1","Grid1");
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

    @Test
    void seeIfGetRoomListWorks() {
        // Arrange

        Room testRoom = new Room("Office", "1st Floor Office", 1, 20, 15, 10,"Room1","Grid1");
        RoomService roomService = new RoomService();
        roomService.add(testRoom);
        RoomService expectedResult = new RoomService();
        expectedResult.add(testRoom);
        validHouse.setRoomService(roomService);

        // Act

        RoomService actualResult = validHouse.getRoomService();


        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomListWorksNull() {
        // Arrange

        RoomService expectedResult = new RoomService();
        validHouse.setRoomService(null);

        // Act

        RoomService actualResult = validHouse.getRoomService();


        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetRoomListWorksEmpty() {
        // Arrange

        RoomService roomService = new RoomService();
        RoomService expectedResult = new RoomService();
        validHouse.setRoomService(roomService);

        // Act

        RoomService actualResult = validHouse.getRoomService();


        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetRoomListWorksNull() {
        // Arrange

        RoomService expectedResult = new RoomService();
        validHouse.setRoomService(null);

        // Act

        RoomService actualResult = validHouse.getRoomService();


        // Assert

        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfGetRoomByIndexWorks() {
//        //Arrange
//
//        Room room1 = new Room("room1", "Single Bedroom", 1, 20, 15, 10,"Room1","Grid1");
//        Room room2 = new Room("room2", "Double Bedroom", 2, 20, 15, 10,"Room1","Grid1");
//        validHouse.addRoom(room1);
//        validHouse.addRoom(room2);
//
//        //Act
//
//        Room actualResult1 = validHouse.getRoomByIndex(0);
//        Room actualResult2 = validHouse.getRoomByIndex(1);
//
//        //Assert
//
//        assertEquals(room1, actualResult1);
//        assertEquals(room2, actualResult2);
//    }


    @Test
    void seeIfGetEnergyGridByIndexWorks() {
        //Arrange

        EnergyGrid energyGrid1 = new EnergyGrid("energyGrid1", 200, "34576");
        EnergyGrid energyGrid2 = new EnergyGrid("energyGrid2", 200, "34576");
        validHouse.addGrid(energyGrid1);
        validHouse.addGrid(energyGrid2);

        //Act

        EnergyGrid actualResult1 = validHouse.getEnergyGridByIndex(0);
        EnergyGrid actualResult2 = validHouse.getEnergyGridByIndex(1);

        //Assert

        assertEquals(energyGrid1, actualResult1);
        assertEquals(energyGrid2, actualResult2);
    }

    @Test
    void getByIndexEmptyGridList() {
        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> validHouse.getEnergyGridByIndex(0));

        //Assert

        Assertions.assertEquals("The energy grid list is empty.", exception.getMessage());
    }

//    @Test
//    void getByIndexEmptyRoomList() {
//        //Act
//
//        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> validHouse.getId(0));
//
//        //Assert
//
//        Assertions.assertEquals("The room list is empty.", exception.getMessage());
//    }

    @Test
    void seeIfGetEnergyConsumptionWorksZero() {
        // Arrange

        double expectedResult = 0.0;

        // Act

        double result = validHouse.getEnergyConsumption(10);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetEnergyConsumptionWorks() {
        // Arrange

        double expectedResult = 310;
        Device device = new Fridge(new FridgeSpec());
        device.setNominalPower(31);
        Room tempRoom = new Room("tempRoom", "Sensor's Room", 1, 20, 20, 10,"Room1","Grid1");
        validHouse.addRoom(tempRoom);
        tempRoom.addDevice(device);

        // Act

        double result = validHouse.getEnergyConsumption(10);

        // Assert

        assertEquals(expectedResult, result);
    }

//    @Test
//    void energyGridListSize() {
//        //Act
//
//        int actualResult1 = validHouse.energyGridListSize();
//
//        //Assert Empty List
//
//        Assertions.assertEquals(0, actualResult1);
//
//        //Arrange
//        EnergyGrid energyGrid = new EnergyGrid("grid", 200,"34576");
//        energyGrid.setId(12);
//        validHouse.addGrid(energyGrid);
//
//        //Act
//
//        int actualResult2 = validHouse.energyGridListSize();
//
//        //Assert One Grid
//
//        Assertions.assertEquals(1, actualResult2);
//    }

//    @Test
//    void roomListSize() {
//        //Act
//
//        int actualResult1 = validHouse.roomListSize();
//
//        //Assert Empty List
//
//        Assertions.assertEquals(0, actualResult1);
//
//        //Arrange
//
//        validHouse.addRoom(new Room("room", "Single Bedroom", 2, 20, 20, 3,"Room1","Grid1"));
//
//        //Act
//
//        int actualResult2 = validHouse.roomListSize();
//
//        //Assert One Room
//
//        Assertions.assertEquals(1, actualResult2);
//    }

//    @Test
//    void seeIfIsRoomListEmptyWorks() {
//        //Act
//
//        boolean actualResult1 = validHouse.isRoomListEmpty();
//
//        //Assert Empty List
//
//        assertTrue(actualResult1);
//
//        //Arrange
//
//        validHouse.addRoom(new Room("room", "Single Bedroom", 2, 20, 20, 3,"Room1","Grid1"));
//
//        //Act
//
//        boolean actualResult2 = validHouse.isRoomListEmpty();
//
//        //Assert One Room
//
//        assertFalse(actualResult2);
//    }

    @Test
    void seeIfIsEnergyGridListEmptyWorks() {
        //Act

        boolean actualResult1 = validHouse.isEnergyGridListEmpty();

        //Assert Empty List

        assertTrue(actualResult1);

        //Arrange

        validHouse.addGrid(new EnergyGrid("energyGrid", 230, "34576"));

        //Act

        boolean actualResult2 = validHouse.isEnergyGridListEmpty();

        //Assert One Room

        assertFalse(actualResult2);
    }

    @Test
    void seeIfDeviceTypeListSizeWorks() {
        //Act

        int actualResult1 = validHouse.deviceTypeListSize();

        //Assert

        assertEquals(1, actualResult1);
    }

    @Test
    void seeIfSetMotherAreaWorks() {
        //Act
        GeographicArea geoArea = new GeographicArea("Porto", new AreaType("City"), 50, 13, new Local(5, 5, 5));
        validHouse.setMotherArea(geoArea);

        //Assert
        assertEquals(validHouse.getMotherArea(), geoArea);
    }

    @Test
    void seeIfSetGetAddressWorks() {
        //Act
        Address address = new Address("Rua do ISEP", "431", "4400", "Campus", "Portugal");
        validHouse.setAddress(address);

        //Assert
        assertEquals(validHouse.getAddress(), new Address("Rua do ISEP", "431", "4400", "Campus", "Portugal"));
    }

    @Test
    void seeIfIsMotherAreaNullWorks() {
        //Act

        boolean actualResult1 = validHouse.isMotherAreaNull();

        //Assert

        assertFalse(actualResult1);

        //Arrange As Null

        validHouse.setMotherArea(null);

        //Act

        boolean actualResult2 = validHouse.isMotherAreaNull();

        //Assert

        assertTrue(actualResult2);
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