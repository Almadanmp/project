package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.devicetypes.Fridge;
import pt.ipp.isep.dei.project.model.device.devicetypes.WashingMachine;
import pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeater;

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
        Device device = new Device("fridge", 5, new Fridge());
        Device device2 = new Device("washing", 5, new WashingMachine());
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
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, roomList);
        Sensor s = new Sensor("sensor1", new TypeSensor("temperatura", "Celsius"), new Local(4, 5, 5), new GregorianCalendar(2018, 10, 1).getTime());
        double result = house.calculateDistanceToSensor(s);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void seeMinDistanceToSensor() {
        RoomList roomList = new RoomList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperatura", "Celsius"), new Local(4, 6, 5), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperatura", "Celsius"), new Local(4, 8, 5), new GregorianCalendar(2018, 10, 1).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 2, 3, new Local(4, 5, 50));
        ga.setSensorList(sensorList);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        double result = house.getMinDistanceFromHouseToSensor(ga);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void seeMinDistanceToSensorInsideIf() {
        RoomList roomList = new RoomList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperatura", "Celsius"), new Local(4, 6, 5), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperatura", "Celsius"), new Local(4, 8, 5), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s3 = new Sensor("sensor2", new TypeSensor("temperatura", "Celsius"), new Local(4, 5, 5), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s4 = new Sensor("sensor2", new TypeSensor("temperatura", "Celsius"), new Local(4, 9, 5), new GregorianCalendar(2018, 10, 1).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);
        sensorList.addSensor(s4);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 2, 3, new Local(4, 5, 50));
        ga.setSensorList(sensorList);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        double result = house.getMinDistanceFromHouseToSensor(ga);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void ensureThatWeGetAltitude() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 2, 3, new Local(4, 5, 50));
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, roomList);
        house.setLocation(23, 43, 4);
        double expectedResult = 4;
        double actualResult = house.getLocation().getAltitude();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSensorWithMinDistance() {
        RoomList roomList = new RoomList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"), new Local(4, 8, 50), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 2, 3, new Local(4, 5, 50));
        ga.setSensorList(sensorList);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga, house, "temperature");
        assertEquals(s1, result);
    }

    @Test
    void seeSensorMinDistanceIfGAHasNoSensors() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 2, 3, new Local(4, 5, 50));
        RoomList roomList = new RoomList();
        SensorList sensorList1 = new SensorList();
        ga.setSensorList(sensorList1);
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(4, 8, 50), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga, house, "temperature");
        assertNull(result);
    }

    @Test
    void seeEqualToEqualObject() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        House house2 = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);

        boolean actualResult = house.equals(house2);

        assertTrue(actualResult);
    }

    @Test
    void seeEqualToSameObject() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);

        boolean actualResult = house.equals(house);

        assertTrue(actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        House house2 = new House("casa de campo", "Rua do Sol", "4516", "Porto", new Local(4, 5, 50), ga, roomList);

        boolean actualResult = house.equals(house2);
        assertFalse(actualResult);
    }


    @Test
    void seeEqualsToDifTypeObject() {
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), new GeographicArea(), roomList);
        Room room = new Room("quarto", 1, 80, 2, 2);

        boolean actualResult = house.equals(room);
        assertFalse(actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);

        boolean actualResult = house.equals(null);

        assertFalse(actualResult);
    }

    @Test
    void seeAddRoomToEmptyRoomList() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        Room room = new Room("quarto", 1, 80, 2, 2);
        boolean result = house.addRoomToRoomList(room);

        assertTrue(result);
    }

    @Test
    void seeAddRoomToRoomList() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        Room room = new Room("quarto", 1, 80, 2, 2);
        Room room2 = new Room("sala", 1, 80, 2, 2);
        house.addRoomToRoomList(room2);
        boolean result = house.addRoomToRoomList(room);

        assertTrue(result);
    }

    @Test
    void seeAddRoomSameName() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
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
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        EnergyGridList energyGridList = new EnergyGridList();
        house.setEGList(energyGridList);
        String expectedResult = "Invalid List - List is Empty\n";
        String actualResult = house.buildGridListString();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seePrintedGridList() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
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
    void seeSetId() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        String expectedResult = "Casa do Porto";
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);

        //Act
        house.setId("Casa do Porto");
        String actualResult = house.getHouseId();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetStreet() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        String expectedResult = "Rua da Praia";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);

        //Act
        house.setStreet("Rua da Praia");
        String actualResult = house.getStreet();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetZipCode() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        String expectedResult = "6327-09";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);

        //Act
        house.setZip("6327-09");
        String actualResult = house.getZip();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetTown() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        String expectedResult = "Lisboa";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);

        //Act
        house.setTown("Lisboa");
        String actualResult = house.getTown();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetHouseLocation() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        Local expectedResult = new Local(7, 78, 50);

        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);

        //Act
        house.setLocation(7, 78, 50);
        Local actualResult = house.getLocation();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetHouseLocation2() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        Local expectedResult = new Local(5, 35, 34);
        Local localHouse = new Local(8, 9, 1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", localHouse, ga, roomList);

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

        GeographicArea expectedResult = new GeographicArea();
        expectedResult.setId("Porto");
        expectedResult.setLocal(new Local(2, 3, 4));
        expectedResult.setTypeArea(new TypeArea("Cidade"));
        GeographicArea ga1 = new GeographicArea();
        ga1.setId("Porto");
        ga1.setLocal(new Local(2, 3, 4));
        ga1.setTypeArea(new TypeArea("Cidade"));

        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga, roomList);

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
        Device d1 = new Device("WaterHeater1", 30, new WaterHeater());
        Device d2 = new Device("Fridge", 50, new Fridge());
        DeviceList deviceList1 = new DeviceList();
        deviceList1.addDevice(d1);
        r1.setDeviceList(deviceList1);
        DeviceList deviceList2 = new DeviceList();
        deviceList2.addDevice(d2);
        r2.setDeviceList(deviceList2);
        RoomList roomList = new RoomList();
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        House testHouse = new House();
        testHouse.setRoomList(roomList);
        double expectedResult = 80;

        //Act
        double actualResult = testHouse.getNominalPower();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetEnergyGridList() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        EnergyGridList energyGridList = new EnergyGridList();
        EnergyGrid eg = new EnergyGrid("main grid", 25);
        energyGridList.addGrid(eg);

        //Arrange
        EnergyGridList expectedResult = new EnergyGridList();
        expectedResult.addGrid(eg);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga, roomList);
        //Act
        house.setEGList(energyGridList);
        EnergyGridList actualResult = house.getEGList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seePrintHouse() {
        //ARRANGE
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga, roomList);
        String expectedResult = "casa de praia, Rua das Flores, 4512, Porto.\n";
        //ACT
        String actualResult = house.buildHouseString();
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void hashCodeDummyTest() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga, roomList);
        int expectedResult = 1;
        int actualResult = house.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getDailyHouseConsumptionPerTypeTest() {
        House h1 = new House();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Device("fridgeOne", 12, new Fridge());
        Device d2 = new Device("wHeater1", 12, new WaterHeater(200.0, 30.0, 0.9));
        Device d3 = new Device("wHeater2", 11, new WaterHeater(500.0, 20.0,  0.9));
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        Device d4 = new Device("fridgeTwo", 12, new Fridge());
        Device d5 = new Device("wHeater3", 12, new WaterHeater(300.0, 15.0, 0.9));
        Device d6 = new Device("wHeater4", 11, new WaterHeater(400.0, 20.0,  0.9));
        r2.addDevice(d4);
        r2.addDevice(d5);
        r2.addDevice(d6);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0.0;
        double result = h1.getDailyConsumptionByDeviceType(DeviceType.WATER_HEATER);
        assertEquals(expectedResult, result);
    }
    @Test
    public void getDailyHouseConsumptionPerTypeTest2() {
        House h1 = new House();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, new WaterHeater(200.0, 30.0, 0.9));
        Device d3 = new Device("wHeater2", 11, new WaterHeater(500.0, 20.0,  0.9));
        r1.addDevice(d2);
        d2.setmParentRoom(r1);
        r1.addDevice(d3);
        d3.setmParentRoom(r1);
        h1.addRoomToRoomList(r1);
        d2.setAttributeValue("coldWaterTemperature",5.0);
        d2.setAttributeValue("volumeOfWaterToHeat",100.0);
        d3.setAttributeValue("volumeOfWaterToHeat",100.0);
        d3.setAttributeValue("coldWaterTemperature",1.0);
        double expectedResult = 4605.479;
        double result = h1.getDailyConsumptionByDeviceType(DeviceType.WATER_HEATER);
        assertEquals(expectedResult, result);
    }
    @Test
    void getHouseDevicesOfGivenType(){
        House house = new House();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, new WaterHeater(200.0,20.0,10.0));
        Device d3 = new Device("wHeater2", 11, new WaterHeater(500.0,30.0,1.0));
        r1.addDevice(d2);
        r1.addDevice(d3);
        house.addRoomToRoomList(r1);
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(d2);
        expectedResult.add(d3);
        List<Device> result = house.getDevicesOfGivenType(DeviceType.WATER_HEATER);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void getDeviceListTest(){
        House house = new House();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, new WaterHeater(200.0,20.0,10.0));
        Device d3 = new Device("wHeater2", 11, new WaterHeater(500.0,30.0,1.0));
        r1.addDevice(d2);
        r1.addDevice(d3);
        house.addRoomToRoomList(r1);
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(d2);
        expectedResult.add(d3);
        List<Device> result = house.getDeviceList();
        assertEquals(expectedResult, result);
    }
}


