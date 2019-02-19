package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.*;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherDT;
import pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterDT;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;

/**
 * House tests class.
 */

class HouseTest {

    // Common artifacts for testing in this class.
    public static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";

    @Test
    void seeIfGetListDevice() {
        Device device = new Fridge(new FridgeSpec());
        device.setName("sdgfsg");
        device.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 34D);
        device.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 34D);
        device.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 65D);
        Device device2 = new WashingMachine(new WashingMachineSpec());
        device2.setName("dsfgds");
        Room room1 = new Room("room1", 19, 23456789, 5, 3);
        room1.addDevice(device);
        room1.addDevice(device2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room1);
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(device);
        expectedResult.add(device2);
        List<Device> result = roomList.getDeviceList();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeDistanceToSensor() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 6, 5), ga, 60, 180, deviceTypeString);
        Sensor s = new Sensor("sensor1", new TypeSensor("temperatura", "Celsius"), new Local(4, 5, 5), new GregorianCalendar(2018, 10, 1).getTime());
        double result = house.calculateDistanceToSensor(s);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void seeMinDistanceToSensor() {
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperatura", "Celsius"), new Local(4, 6, 5), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperatura", "Celsius"), new Local(4, 8, 5), new GregorianCalendar(2018, 10, 1).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        double result = house.getMinDistanceFromHouseToSensor(ga);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void seeMinDistanceToSensorInsideIf() {
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperatura", "Celsius"), new Local(4, 6, 5), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperatura", "Celsius"), new Local(4, 8, 5), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s3 = new Sensor("sensor2", new TypeSensor("temperatura", "Celsius"), new Local(4, 5, 5), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s4 = new Sensor("sensor2", new TypeSensor("temperatura", "Celsius"), new Local(4, 9, 5), new GregorianCalendar(2018, 10, 1).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);
        sensorList.addSensor(s4);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        double result = house.getMinDistanceFromHouseToSensor(ga);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void ensureThatWeGetAltitude() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 6, 5), ga, 60, 180, deviceTypeString);
        house.setLocation(23, 43, 4);
        double expectedResult = 4;
        double actualResult = house.getLocation().getAltitude();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSensorWithMinDistance() {
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 9).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"), new Local(4, 8, 50), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Sensor result = house.getClosestSensorOfGivenType(ga, "temperature");
        assertEquals(s1, result);
    }


    @Test
    void seeEqualToEqualObject() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        House house2 = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);

        boolean actualResult = house.equals(house2);

        assertTrue(actualResult);
    }

    @Test
    void seeEqualToSameObject() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);

        boolean actualResult = house.equals(house);

        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        Address address = new Address("Rua das Flores", "4512", "Porto");
        Address address2 = new Address("Rua do Sol", "4516", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        House house2 = new House("casa de campo", address2, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);

        boolean actualResult = house.equals(house2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = house.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);

        boolean actualResult = house.equals(null);

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

        //Act
        house.setLocation(7, 78, 50);
        Local actualResult = house.getLocation();

        //Assert
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
    void seeSetMotherArea() {
        //Arrange

        GeographicArea expectedResult = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        expectedResult.setId("Porto");
        expectedResult.setLocal(new Local(2, 3, 4));
        expectedResult.setTypeArea(new TypeArea("Cidade"));
        GeographicArea ga1 = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga1.setId("Porto");
        ga1.setLocal(new Local(2, 3, 4));
        ga1.setTypeArea(new TypeArea("Cidade"));

        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("Casa de praia", address, new Local(4, 5, 4), ga, 60, 180, deviceTypeString);

        //Act
        house.setMotherArea(ga1);
        GeographicArea actualResult = house.getMotherArea();

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
        DeviceList deviceList1 = new DeviceList();
        deviceList1.addDevice(d1);
        r1.setDeviceList(deviceList1);
        DeviceList deviceList2 = new DeviceList();
        deviceList2.addDevice(d2);
        r2.setDeviceList(deviceList2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House testHouse = new House("Casa de praia", address, new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
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
        EnergyGridList actualResult = house.getEGListObject();

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
        House h1 = new House("casa de praia", address, new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
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
        House h1 = new House("casa de praia", address, new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
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
        double expectedResult = 17.7;
        double result = h1.getDailyConsumptionByDeviceType("WaterHeater", 1440);
        assertEquals(expectedResult, result);
    }

    @Test
    void getHouseDevicesOfGivenType() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
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
    void getDeviceListTest() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new WaterHeater(new WaterHeaterSpec());
        d2.setName("hfjfd");
        d2.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 10D);
        Device d3 = new WaterHeater(new WaterHeaterSpec());
        d3.setName("ghfghd");
        d3.setAttributeValue(WaterHeaterSpec.VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(WaterHeaterSpec.HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(WaterHeaterSpec.PERFORMANCE_RATIO, 1D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        house.addRoomToRoomList(r1);
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(d2);
        expectedResult.add(d3);
        List<Device> result = house.getDeviceList();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsed2() {
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 11, 12).getTime());
        Reading r2 = new Reading(12, new GregorianCalendar(2018, 10, 1).getTime());
        Reading r3 = new Reading(12, new GregorianCalendar(2018, 12, 25).getTime());
        Reading r4 = new Reading(12, new GregorianCalendar(2018, 10, 1).getTime());
        readingList.addReading(r1);
        readingList.addReading(r2);
        ReadingList readingList2 = new ReadingList();
        readingList2.addReading(r3);
        readingList2.addReading(r4);

        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 9).getTime());
        s1.setReadingList(readingList);
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 15).getTime());
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
        Sensor result = house.getClosestSensorOfGivenType(ga, "temperature");
        assertEquals(s2, result);
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsed3() {
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 1, 24).getTime());
        Reading r2 = new Reading(12, new GregorianCalendar(2018, 1, 1).getTime());
        Reading r3 = new Reading(12, new GregorianCalendar(2018, 1, 25).getTime());
        Reading r4 = new Reading(12, new GregorianCalendar(2018, 1, 1).getTime());
        readingList.addReading(r1);
        readingList.addReading(r2);
        ReadingList readingList2 = new ReadingList();
        readingList2.addReading(r3);
        readingList2.addReading(r4);

        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 9).getTime());
        s1.setReadingList(readingList2);
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 15).getTime());
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
        Sensor result = house.getClosestSensorOfGivenType(ga, "temperature");
        assertEquals(s1, result);
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsednull() {
        SensorList sensorList = new SensorList();
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Sensor result = house.getClosestSensorOfGivenType(ga, "temperature");
        Sensor sensorError = new Sensor("EmptyList", new TypeSensor("temperature", " "), new Local(0, 0, 0), new GregorianCalendar(1900, 1, 1).getTime());
        assertEquals(sensorError.getName(), result.getName());
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsed4() {
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018,
                1, 24).getTime());
        Reading r2 = new Reading(12, new GregorianCalendar(2018, 1, 1).getTime());
        Reading r3 = new Reading(12, new GregorianCalendar(2018, 1, 25).getTime());
        Reading r4 = new Reading(12, new GregorianCalendar(2018, 1, 1).getTime());
        readingList.addReading(r1);
        readingList.addReading(r2);
        ReadingList readingList2 = new ReadingList();
        readingList2.addReading(r3);
        readingList2.addReading(r4);

        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 9).getTime());
        s1.setReadingList(readingList2);
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 15).getTime());
        s2.setReadingList(readingList);
        Sensor s3 = new Sensor("sensor3", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 9).getTime());
        s3.setReadingList(readingList);
        Sensor s4 = new Sensor("sensor4", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 9).getTime());
        s4.setReadingList(readingList);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);
        sensorList.addSensor(s4);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Sensor result = house.getClosestSensorOfGivenType(ga, "temperature");
        assertEquals(s1, result);
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsed5() {
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 1, 24).getTime());
        Reading r2 = new Reading(12, new GregorianCalendar(2018, 1, 1).getTime());
        Reading r3 = new Reading(12, new GregorianCalendar(2018, 1, 25).getTime());
        Reading r4 = new Reading(12, new GregorianCalendar(2018, 1, 1).getTime());
        readingList.addReading(r1);
        readingList.addReading(r2);
        ReadingList readingList2 = new ReadingList();
        readingList2.addReading(r3);
        readingList2.addReading(r4);

        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 9).getTime());
        s1.setReadingList(readingList2);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, deviceTypeString);
        Sensor result = house.getClosestSensorOfGivenType(ga, "temperature");
        assertEquals(s1, result);
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsedWithDifDistance() {
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 1, 24).getTime());
        Reading r2 = new Reading(12, new GregorianCalendar(2018, 1, 1).getTime());
        Reading r3 = new Reading(12, new GregorianCalendar(2018, 1, 25).getTime());
        Reading r4 = new Reading(12, new GregorianCalendar(2018, 1, 1).getTime());
        readingList.addReading(r1);
        readingList.addReading(r2);
        ReadingList readingList2 = new ReadingList();
        readingList2.addReading(r3);
        readingList2.addReading(r4);

        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(4, 9, 50), new GregorianCalendar(4, 4, 9).getTime());
        s1.setReadingList(readingList2);
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 15).getTime());
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
        Sensor result = house.getClosestSensorOfGivenType(ga, "temperature");
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
        String result = house.buildTypeListString(dtList);
        assertEquals(expectedResult, result);
    }

    @Test
    void buildTypeListStringEmpty() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<String> test = new ArrayList<>();
        test.add("pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterDT");
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 5, 50), ga, 60, 180, test);
        List<DeviceType> dtList = new ArrayList<>();
        String expectedResult = "Invalid List - List is Empty\n";
        String result = house.buildTypeListString(dtList);
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
    void containsRoomByName() {
        GeographicArea ga = new GeographicArea("Porto", new TypeArea("City"), 2, 3, new Local(4, 4, 100));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Flower Street", "4512", "Porto");
        House house = new House("Beach House", address, new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        Room room = new Room("room", 1, 1, 1, 1);
        house.addRoomToRoomList(room);
        //ACT
        boolean expectedResult1 = house.containsRoomByName("kitchen");
        boolean expectedResult2 = house.containsRoomByName("room");
        //ASSERT
        assertFalse(expectedResult1);
        assertTrue(expectedResult2);
    }

}