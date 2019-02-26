package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.*;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherDT;
import pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterDT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Sensor secondValidSensor;

    @BeforeEach
    void arrangeArtifacts(){
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
        secondValidSensor = new Sensor("rainOne", new TypeSensor("Rainfall", "l/m2"), new Local(21,
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
                20,20), new Date());
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
    void seeAddRoomToEmptyRoomList() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Room room = new Room("quarto", 1, 80, 2, 2);
        boolean result = house.addRoomToRoomList(room);

        assertTrue(result);
    }

    @Test
    void seeAddRoomToRoomList() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Room room = new Room("quarto", 1, 80, 2, 2);
        Room room2 = new Room("sala", 1, 80, 2, 2);
        house.addRoomToRoomList(room2);
        boolean result = house.addRoomToRoomList(room);

        assertTrue(result);
    }

    @Test
    void seeAddRoomSameName() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        RoomList rL1 = new RoomList();
        house.setRoomList(rL1);
        Room room = new Room("quarto", 1, 80, 2, 2);
        Room room2 = new Room("quarto", 1, 80, 2, 2);
        house.addRoomToRoomList(room2);
        boolean result = house.addRoomToRoomList(room);

        assertFalse(result);
    }

    @Test
    void seeIfRecognizesEmptyGridList() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        EnergyGridList energyGridList = new EnergyGridList();
        house.setEGList(energyGridList);
        String expectedResult = "Invalid List - List is Empty\n";
        String actualResult = house.buildGridListString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seePrintedGridList() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        EnergyGrid eg1 = new EnergyGrid("Rede", 444);
        EnergyGrid eg2 = new EnergyGrid("Rede 2", 555);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addGrid(eg1);
        energyGridList.addGrid(eg2);
        house.setEGList(energyGridList);
        String expectedResult = "---------------\n" +
                "0) Designation: Rede | Max Power: 444.0\n" +
                "1) Designation: Rede 2 | Max Power: 555.0\n" +
                "---------------\n";
        String actualResult = house.buildGridListString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetHouseLocation() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        //Arrange
        Local expectedResult = new Local(7, 78, 50);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);

        // Act
        house.setLocation(7, 78, 50);
        Local actualResult = house.getLocation();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetHouseLocation2() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        //Arrange
        Local expectedResult = new Local(5, 35, 34);
        Local localHouse = new Local(8, 9, 1);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, localHouse, ga, 60, 180, deviceTypeString);

        //Act
        localHouse.setAltitude(34);
        localHouse.setLatitude(5);
        localHouse.setLongitude(35);
        Local actualResult = house.getLocation();


        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorks() {
        //Arrange

        Room r1 = new Room("Kitchen", 0, 12, 30, 10);
        Room r2 = new Room("Sótão", 3, 30, 40, 12);
        Device d1 = new WaterHeater(new WaterHeaterSpec());
        d1.setNominalPower(30.0);
        d1.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 234D);
        Device d2 = new Fridge(new FridgeSpec());
        d2.setNominalPower(50.0);
        d2.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 45D);
        d2.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 45D);
        d2.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 56D);
        r1.addDevice(d1);
        r2.addDevice(d2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House testHouse = new House("Casa de praia", address, new Local(4, 5, 4),
                new GeographicArea("porto", new TypeArea("cidade"), 2, 3,
                        new Local(4, 4, 100)), 60, 180, deviceTypeString);
        testHouse.addRoomToRoomList(r1);
        testHouse.addRoomToRoomList(r2);
        double expectedResult = 80;

        //Act
        double actualResult = testHouse.getNominalPower();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetEnergyGridList() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        EnergyGridList energyGridList = new EnergyGridList();
        EnergyGrid eg = new EnergyGrid("main grid", 25);
        energyGridList.addGrid(eg);

        //Arrange
        EnergyGridList expectedResult = new EnergyGridList();
        expectedResult.addGrid(eg);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("Casa de praia", address, new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        //Act
        house.setEGList(energyGridList);
        EnergyGridList actualResult = house.getGridList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        int expectedResult = 1;
        int actualResult = house.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDailyHouseConsumptionPerTypeTest() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House h1 = new House("casa de praia", address, new Local(4, 5, 4),
                new GeographicArea("porto", new TypeArea("cidade"), 2, 3,
                        new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Fridge(new FridgeSpec());
        d1.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 45D);
        d1.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 45D);
        d1.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 65D);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        Device d4 = new Fridge(new FridgeSpec());
        d4.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 4D);
        d4.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 5D);
        d4.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 45D);
        Device d5 = new WaterHeater(new WaterHeaterSpec());
        d5.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 300D);
        d5.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 15D);
        d5.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Device d6 = new WaterHeater(new WaterHeaterSpec());
        d6.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 300D);
        d6.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 15D);
        d6.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        r2.addDevice(d4);
        r2.addDevice(d5);
        r2.addDevice(d6);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0.0;
        double result = h1.getDailyConsumptionByDeviceType("WaterHeater", 1440);
        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyHouseConsumptionPerTypeTest2() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House h1 = new House("casa de praia", address, new Local(4, 5, 4),
                new GeographicArea("porto", new TypeArea("cidade"), 2, 3,
                        new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("ghfg");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setName("jytjd");
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 0.9D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        h1.addRoomToRoomList(r1);
        d2.setAttributeValue("Cold Water Temperature", 5.0);
        d2.setAttributeValue("Volume Of Water To Heat", 100.0);
        d3.setAttributeValue("Volume Of Water To Heat", 100.0);
        d3.setAttributeValue("Cold Water Temperature", 1.0);
        double expectedResult = 4.6;
        double result = h1.getDailyConsumptionByDeviceType("WaterHeater", 1440);
        assertEquals(expectedResult, result);
    }

    @Test
    void getHouseDevicesOfGivenType() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 4),
                new GeographicArea("porto", new TypeArea("cidade"), 2, 3,
                        new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("hgfhjf");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setName("hfjf");
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        house.addRoomToRoomList(r1);
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(d2);
        expectedResult.add(d3);
        List<Device> result = house.getDevicesOfGivenType("WaterHeater");
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsed2() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = new Date();
        try {
            date1 = validSdf.parse("12/11/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        try {
            date2 = validSdf.parse("01/10/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date3 = new Date();
        try {
            date3 = validSdf.parse("25/12/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date4 = new Date();
        try {
            date4 = validSdf.parse("01/10/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, date1);
        Reading r2 = new Reading(12, date2);
        Reading r3 = new Reading(12, date3);
        Reading r4 = new Reading(12, date4);
        readingList.addReading(r1);
        readingList.addReading(r2);
        ReadingList readingList2 = new ReadingList();
        readingList2.addReading(r3);
        readingList2.addReading(r4);

        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"),
                new Local(4, 6, 50), date1);
        s1.setReadingList(readingList);
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"),
                new Local(4, 6, 50), date2);
        s2.setReadingList(readingList2);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Sensor result = house.getClosestSensorOfGivenType("temperature");
        assertEquals(s2, result);
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsed3() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = new Date();
        try {
            date1 = validSdf.parse("24/01/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        try {
            date2 = validSdf.parse("01/01/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date3 = new Date();
        try {
            date3 = validSdf.parse("25/01/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date4 = new Date();
        try {
            date4 = validSdf.parse("01/01/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, date1);
        Reading r2 = new Reading(12, date2);
        Reading r3 = new Reading(12, date3);
        Reading r4 = new Reading(12, date4);
        readingList.addReading(r1);
        readingList.addReading(r2);
        ReadingList readingList2 = new ReadingList();
        readingList2.addReading(r3);
        readingList2.addReading(r4);

        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"),
                new Local(4, 6, 50), date1);
        s1.setReadingList(readingList2);
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"),
                new Local(4, 6, 50), date2);
        s2.setReadingList(readingList);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Sensor result = house.getClosestSensorOfGivenType("temperature");
        assertEquals(s1, result);
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsednull() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        try {
            date = validSdf.parse("12/11/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SensorList sensorList = new SensorList();
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20,
                new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Sensor result = house.getClosestSensorOfGivenType("temperature");
        Sensor sensorError = new Sensor("EmptyList", new TypeSensor("temperature", " "),
                new Local(0, 0, 0), date);
        assertEquals(sensorError.getName(), result.getName());
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsed4() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = new Date();
        try {
            date1 = validSdf.parse("12/11/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        try {
            date2 = validSdf.parse("01/10/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date3 = new Date();
        try {
            date3 = validSdf.parse("25/12/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date4 = new Date();
        try {
            date4 = validSdf.parse("01/10/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, date1);
        Reading r2 = new Reading(12, date2);
        Reading r3 = new Reading(12, date3);
        Reading r4 = new Reading(12,date4);
        readingList.addReading(r1);
        readingList.addReading(r2);
        ReadingList readingList2 = new ReadingList();
        readingList2.addReading(r3);
        readingList2.addReading(r4);

        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"),
                new Local(4, 6, 50), date1);
        s1.setReadingList(readingList2);
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"),
                new Local(4, 6, 50), date2);
        s2.setReadingList(readingList);
        Sensor s3 = new Sensor("sensor3", new TypeSensor("temperature", "Celsius"),
                new Local(4, 6, 50), date3);
        s3.setReadingList(readingList);
        Sensor s4 = new Sensor("sensor4", new TypeSensor("temperature", "Celsius"),
                new Local(4, 6, 50), date4);
        s4.setReadingList(readingList);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);
        sensorList.addSensor(s4);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20,
                new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Sensor result = house.getClosestSensorOfGivenType("temperature");
        assertEquals(s1, result);
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsed5() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = new Date();
        try {
            date1 = validSdf.parse("12/11/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        try {
            date2 = validSdf.parse("01/10/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date3 = new Date();
        try {
            date3 = validSdf.parse("25/12/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date4 = new Date();
        try {
            date4 = validSdf.parse("01/10/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, date1);
        Reading r2 = new Reading(12, date2);
        Reading r3 = new Reading(12, date3);
        Reading r4 = new Reading(12, date4);
        readingList.addReading(r1);
        readingList.addReading(r2);
        ReadingList readingList2 = new ReadingList();
        readingList2.addReading(r3);
        readingList2.addReading(r4);

        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"),
                new Local(4, 6, 50), date1);
        s1.setReadingList(readingList2);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Sensor result = house.getClosestSensorOfGivenType("temperature");
        assertEquals(s1, result);
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsedWithDifDistance() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = new Date();
        try {
            date1 = validSdf.parse("12/11/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        try {
            date2 = validSdf.parse("01/10/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date3 = new Date();
        try {
            date3 = validSdf.parse("25/12/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date4 = new Date();
        try {
            date4 = validSdf.parse("01/10/2018 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, date1);
        Reading r2 = new Reading(12, date2);
        Reading r3 = new Reading(12, date3);
        Reading r4 = new Reading(12, date4);
        readingList.addReading(r1);
        readingList.addReading(r2);
        ReadingList readingList2 = new ReadingList();
        readingList2.addReading(r3);
        readingList2.addReading(r4);

        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"),
                new Local(4, 9, 50), date1);
        s1.setReadingList(readingList2);
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"),
                new Local(4, 6, 50), date2);
        s2.setReadingList(readingList);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Sensor result = house.getClosestSensorOfGivenType("temperature");
        assertEquals(s2, result);
    }


    @Test
    void getDeviceListSuccess() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> test = new ArrayList<>();
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, test);
        test.add("teste");
        List<DeviceType> expectedResult = new ArrayList<>();
        List<DeviceType> result = house.getDeviceTypeList();
        assertEquals(expectedResult, result);
    }

    @Test
    void buildTypeListStringSuccess() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> test = new ArrayList<>();
        test.add("pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterDT");
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, test);
        List<DeviceType> dtList = new ArrayList<>();
        dtList.add(new WaterHeaterDT());
        String expectedResult = "0) DeviceType: WaterHeater\n";
        String result = house.buildString();
        assertEquals(expectedResult, result);
    }

    @Test
    void buildTypeListStringEmpty() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> test = new ArrayList<>();
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, test);
        String expectedResult = "Invalid List - List is Empty\n";
        String result = house.buildString();
        assertEquals(expectedResult, result);
    }

    @Test
    void buildTypeListSuccess() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypePaths = new ArrayList<>();
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypePaths);
        deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherDT");
        deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterDT");

        house.buildDeviceTypeList(deviceTypePaths);

        List<DeviceType> dtList = house.getDeviceTypeList();
        assertEquals(dtList.size(), 2);
        assertTrue(dtList.get(0) instanceof DishwasherDT);
        assertTrue(dtList.get(1) instanceof WaterHeaterDT);
    }

    @Test
    void buildTypeListFails() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
                    List<String> deviceTypePaths = new ArrayList<>();
                    Address address = new Address("Rua das Flores", "4512", "Porto");
                    House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypePaths);
                    deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.Dish");

                    house.buildDeviceTypeList(deviceTypePaths);
                });
    }

    @Test
    void getEnergyConsumption() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> test = new ArrayList<>();
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, test);
        float expectedResult = 0;
        double result = house.getEnergyConsumption(12);
        assertEquals(expectedResult, result);
    }

    @Test
    void setGridMeteringPeriod() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        house.setGridMeteringPeriod(8);
        house.setDeviceMeteringPeriod(10);
        double result = house.getGridMeteringPeriod();
        double result1 = house.getDeviceMeteringPeriod();
        assertEquals(8, result);
        assertEquals(10, result1);
    }

    @Test
    void seeAddGridToHouse() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("Casa de praia", address, new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        EnergyGrid eg1 = new EnergyGrid("eg1", 25);
        EnergyGrid eg2 = new EnergyGrid("eg2", 55);
        EnergyGrid eg3 = new EnergyGrid("eg1", 25);
        //Act
        boolean actualResult1 = house.addGrid(eg1);
        boolean actualResult2 = house.addGrid(eg2);
        boolean actualResult3 = house.addGrid(eg3);
        boolean actualResult4 = house.addGrid(eg1);
        //Assert
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void createsRoom() {
        GeographicArea ga = new GeographicArea("Porto", new TypeArea("City"), 2, 3, new Local(4, 4, 100));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Flower Street", "4512", "Porto");
        House house = new House("Beach House", address, new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        Room expectedResult1 = new Room("kitchen", 1, 1, 1, 1);
        Room expectedResult2 = new Room("room", 1, 1, 1, 1);
        Room expectedResult3 = new Room("kitchen", 1, 1, 1, 1);

        //ACT
        Room actualResult1 = house.createRoom("kitchen", 1, 1, 1, 1);
        Room actualResult2 = house.createRoom("room", 1, 1, 1, 1);
        Room actualResult3 = house.createRoom("kitchen", 1, 1, 1, 1);
        //ASSERT
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }


    @Test
    void testSetAddress() {
        //Arrange
        GeographicArea ga = new GeographicArea("Porto", new TypeArea("City"), 2, 3, new Local(4, 4, 100));
        Address address1 = new Address("Rua das Coisas", "4440-616", "Balongo");
        Address address2 = new Address("Rua de Nada", "4444-666", "Valongo");
        List<String> deviceTypeString = new ArrayList<>();
        House house = new House("Beach House", address2, new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        //Act
        house.setAddress(address1);
        Address actualResult = house.getAddress();
        //Assert
        assertEquals(address1, actualResult);
    }

    @Test
    void getRoomList() {
        //Arrange
        GeographicArea ga = new GeographicArea("Porto", new TypeArea("City"), 2, 3, new Local(4, 4, 100));
        Address address1 = new Address("address1", "4434-666", "Porto");

        List<String> deviceTypeString = new ArrayList<>();
        House house1 = new House("house1", address1, new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        House house2 = new House("house2", address1, new Local(2, 5, 4), ga, 60, 180, deviceTypeString);
        House house3 = new House("house3", address1, new Local(3, 5, 4), ga, 60, 180, deviceTypeString);

        RoomList roomList1 = new RoomList(); //EMPTY
        RoomList roomList2 = new RoomList(); //ONE ROOM
        RoomList roomList3 = new RoomList(); //TWO ROOMS

        Room room1 = new Room("room1", 2, 22, 22, 4);
        Room room2 = new Room("room2", 2, 22, 22, 4);

        roomList2.addRoom(room1);
        roomList3.addRoom(room1);
        roomList3.addRoom(room2);
        house1.setRoomList(roomList1);
        house2.setRoomList(roomList2);
        house3.setRoomList(roomList3);

        RoomList expectedResult1 = new RoomList();
        RoomList expectedResult2 = new RoomList();
        RoomList expectedResult3 = new RoomList();

        expectedResult2.addRoom(room1);
        expectedResult3.addRoom(room1);
        expectedResult3.addRoom(room2);

        //Act
        RoomList actualResult1 = house1.getRoomList();
        RoomList actualResult2 = house2.getRoomList();
        RoomList actualResult3 = house3.getRoomList();

        //Assert
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }
}