package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.WaterHeater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;

/**
 * House tests class.
 */

class HouseTest {

    @Test
    void seeIfGetListDevice() {
        Device device = new Device("fridge", 5, TestUtils.PATH_TO_FRIDGE);
        device.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 34D);
        device.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 34D);
        device.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 65D);
        Device device2 = new Device("washing", 5, TestUtils.PATH_TO_WASHINGMACHINE);
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
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga,60,180);
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
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
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
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        double result = house.getMinDistanceFromHouseToSensor(ga);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void ensureThatWeGetAltitude() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga,60,180);
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
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga, house, "temperature");
        assertEquals(s1, result);
    }



    @Test
    void seeEqualToEqualObject() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        House house2 = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);

        boolean actualResult = house.equals(house2);

        assertTrue(actualResult);
    }

    @Test
    void seeEqualToSameObject() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);

        boolean actualResult = house.equals(house);

        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        House house2 = new House("casa de campo", "Rua do Sol", "4516", "Porto", new Local(4, 5, 50), ga,60,180);

        boolean actualResult = house.equals(house2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = house.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);

        boolean actualResult = house.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void seeAddRoomToEmptyRoomList() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        Room room = new Room("quarto", 1, 80, 2, 2);
        boolean result = house.addRoomToRoomList(room);

        assertTrue(result);
    }

    @Test
    void seeAddRoomToRoomList() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        Room room = new Room("quarto", 1, 80, 2, 2);
        Room room2 = new Room("sala", 1, 80, 2, 2);
        house.addRoomToRoomList(room2);
        boolean result = house.addRoomToRoomList(room);

        assertTrue(result);
    }

    @Test
    void seeAddRoomSameName() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
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
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        EnergyGridList energyGridList = new EnergyGridList();
        house.setEGList(energyGridList);
        String expectedResult = "Invalid List - List is Empty\n";
        String actualResult = house.buildGridListString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seePrintedGridList() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        EnergyGrid eg1 = new EnergyGrid();
        EnergyGrid eg2 = new EnergyGrid();
        eg1.setName("Rede");
        eg1.setMaxContractedPower(444);
        eg2.setName("Rede 2");
        eg2.setMaxContractedPower(555);
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
    void seeSetId() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        //Arrange
        String expectedResult = "Casa do Porto";
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);

        //Act
        house.setId("Casa do Porto");
        String actualResult = house.getHouseId();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetStreet() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        //Arrange
        String expectedResult = "Rua da Praia";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);

        //Act
        house.setStreet("Rua da Praia");
        String actualResult = house.getStreet();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetZipCode() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        //Arrange
        String expectedResult = "6327-09";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);

        //Act
        house.setZip("6327-09");
        String actualResult = house.getZip();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetTown() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        //Arrange
        String expectedResult = "Lisboa";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);

        //Act
        house.setTown("Lisboa");
        String actualResult = house.getTown();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetHouseLocation() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        //Arrange
        Local expectedResult = new Local(7, 78, 50);

        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);

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
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", localHouse, ga,60,180);

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
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga,60,180);

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
        Device d1 = new Device("WaterHeater1", 30, TestUtils.PATH_TO_WATERHEATER);
        d1.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 12D);
        d1.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 40D);
        d1.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 234D);
        Device d2 = new Device("Fridge", 50, TestUtils.PATH_TO_FRIDGE);
        d2.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 45D);
        d2.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 45D);
        d2.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 56D);
        DeviceList deviceList1 = new DeviceList();
        deviceList1.addDevice(d1);
        r1.setDeviceList(deviceList1);
        DeviceList deviceList2 = new DeviceList();
        deviceList2.addDevice(d2);
        r2.setDeviceList(deviceList2);
        House testHouse = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
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
        EnergyGrid eg = new EnergyGrid();
        eg.setMaxContractedPower(25);
        eg.setName("main grid");
        energyGridList.addGrid(eg);

        //Arrange
        EnergyGridList expectedResult = new EnergyGridList();
        expectedResult.addGrid(eg);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga,60,180);
        //Act
        house.setEGList(energyGridList);
        EnergyGridList actualResult = house.getEGListObject();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seePrintHouse() {
        //ARRANGE
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga,60,180);
        String expectedResult = "casa de praia, Rua das Flores, 4512, Porto.\n";
        //ACT
        String actualResult = house.buildHouseString();
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga,60,180);
        int expectedResult = 1;
        int actualResult = house.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDailyHouseConsumptionPerTypeTest() {
        House h1 = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Device("fridgeOne", 12, TestUtils.PATH_TO_FRIDGE);
        d1.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 45D);
        d1.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 45D);
        d1.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 65D);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(WaterHeater.ATTRIBUTE_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        Device d4 = new Device("fridgeTwo", 12, TestUtils.PATH_TO_FRIDGE);
        d4.setAttributeValue(TestUtils.F_FREEZER_CAPACITY, 4D);
        d4.setAttributeValue(TestUtils.F_REFRIGERATOR_CAPACITY, 5D);
        d4.setAttributeValue(TestUtils.F_ANNUAL_CONSUMPTION, 45D);
        Device d5 = new Device("wHeater3", 12, TestUtils.PATH_TO_WATERHEATER);
        d5.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 300D);
        d5.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 15D);
        d5.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Device d6 = new Device("wHeater4", 11, TestUtils.PATH_TO_WATERHEATER);
        d6.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 300D);
        d6.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 15D);
        d6.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        r2.addDevice(d4);
        r2.addDevice(d5);
        r2.addDevice(d6);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0.0;
        double result = h1.getDailyConsumptionByDeviceType("WaterHeater");
        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyHouseConsumptionPerTypeTest2() {
        House h1 = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 0.9D);
        r1.addDevice(d2);
        r1.addDevice(d3);
        h1.addRoomToRoomList(r1);
        d2.setAttributeValue("coldWaterTemperature", 5.0);
        d2.setAttributeValue("volumeOfWaterToHeat", 100.0);
        d3.setAttributeValue("volumeOfWaterToHeat", 100.0);
        d3.setAttributeValue("coldWaterTemperature", 1.0);
        double expectedResult = 4.6;
        double result = h1.getDailyConsumptionByDeviceType("WaterHeater");
        assertEquals(expectedResult, result);
    }

    @Test
    void getHouseDevicesOfGivenType() {
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 10D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
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
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, TestUtils.PATH_TO_WATERHEATER);
        d2.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 200D);
        d2.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 20D);
        d2.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 10D);
        Device d3 = new Device("wHeater2", 11, TestUtils.PATH_TO_WATERHEATER);
        d3.setAttributeValue(TestUtils.WH_VOLUME_OF_WATER, 500D);
        d3.setAttributeValue(TestUtils.WH_HOT_WATER_TEMP, 30D);
        d3.setAttributeValue(TestUtils.WH_PERFORMANCE_RATIO, 1D);
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
    void getDeviceListFromConfigurationFile() throws IOException {
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Fridge");
        expectedResult.add("Dishwasher");
        expectedResult.add("WashingMachine");
        expectedResult.add("WaterHeater");
        expectedResult.add("Lamp");
        List<String> result = house.getDeviceTypes();
        System.out.println(result);
        assertEquals(expectedResult, result);
    }

    @Test
    void getDeviceClassPathFromConfigFile() throws IOException {
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
        String expectedResult = "pt.ipp.isep.dei.project.model.device.deviceSpecs.WaterHeater";
        String result = house.getDeviceTypePathToClassById("WaterHeater");
        System.out.println(result);
        assertEquals(expectedResult, result);
    }

    @Test
    void getPropertyValueByKey() throws IOException {
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
        String expectedResult = "pt.ipp.isep.dei.project.model.device.deviceSpecs.WaterHeater";
        String result = house.getPropertyValueByKey("resources/devices.properties", "WaterHeater");
        System.out.println(result);
        assertEquals(expectedResult, result);
    }

    @Test
    void getPropertyValueByKeyThrowsIOEx() {
        assertThrows(IOException.class,
                () -> {
                    House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
                    house.getPropertyValueByKey("resources/dev.properties", "WaterHeater");
                });
    }


    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsed2(){
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018,11,12).getTime());
        Reading r2 = new Reading(12, new GregorianCalendar(2018,10,1).getTime());
        Reading r3 = new Reading(12, new GregorianCalendar(2018,12,25).getTime());
        Reading r4 = new Reading(12, new GregorianCalendar(2018,10,1).getTime());
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
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga,house,"temperature");
        assertEquals(s2,result);
    }
    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsed3(){
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018,1,24).getTime());
        Reading r2 = new Reading(12, new GregorianCalendar(2018,1,1).getTime());
        Reading r3 = new Reading(12, new GregorianCalendar(2018,1,25).getTime());
        Reading r4 = new Reading(12, new GregorianCalendar(2018,1,1).getTime());
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
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga,house,"temperature");
        assertEquals(s1,result);
    }

    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsed4(){
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018,1,24).getTime());
        Reading r2 = new Reading(12, new GregorianCalendar(2018,1,1).getTime());
        Reading r3 = new Reading(12, new GregorianCalendar(2018,1,25).getTime());
        Reading r4 = new Reading(12, new GregorianCalendar(2018,1,1).getTime());
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
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga,house,"temperature");
        assertEquals(s1,result);
    }
    @Test
    void seeIfgetSensorWithMinDistanceAndMostRecentlyUsedWithDifDistance(){
        ReadingList readingList = new ReadingList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018,1,24).getTime());
        Reading r2 = new Reading(12, new GregorianCalendar(2018,1,1).getTime());
        Reading r3 = new Reading(12, new GregorianCalendar(2018,1,25).getTime());
        Reading r4 = new Reading(12, new GregorianCalendar(2018,1,1).getTime());
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
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga,60,180);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga,house,"temperature");
        assertEquals(s2,result);
    }
}


