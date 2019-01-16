package pt.ipp.isep.dei.project.controller;

import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.devicetypes.Fridge;
import pt.ipp.isep.dei.project.model.devicetypes.WashingMachine;
import pt.ipp.isep.dei.project.model.devicetypes.WaterHeater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RoomConfigurationController tests class.
 */

class RoomConfigurationControllerTest {

    //  SHARED METHODS
    @Test
    void seeIfRoomIsContainedInRoomList() {
        //Arrange
        Room room1 = new Room("Quarto", 1, 5, 1, 21);
        Room room2 = new Room("Cozinha", 1, 9, 3, 5);
        RoomList rList = new RoomList();
        House house1 = new House();
        house1.setRoomList(rList);
        rList.addRoom(room1);
        rList.addRoom(room2);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Room actualResult = ctrl.getRoomFromHouseByName("Quarto", house1);
        //Assert
        assertEquals(room1, actualResult);
    }

    @Test
    void seeIfPrintsRoomList() {
        //Arrange
        GeographicArea gA = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), gA, roomList);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String expectedactualResult = "---------------\n" +
                "0) Designation: kitchen | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "1) Designation: sala | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        String actualResult = ctrl.buildRoomListString(house);
        //Assert
        assertEquals(expectedactualResult, actualResult);
    }

    @Test
    void seeIfPrintRoomWorks() {
        //Arrange
        Room room = new Room("room1", 1, 1, 2, 2);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String actualResult = ctrl.buildRoomString(room);
        String expectedResult = "room1, 1, 1.0, 2.0, 2.0.\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintRoomElementsByIndex() {
        //Arrange
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicArea gA = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), gA, roomList);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String actualResult = ctrl.buildRoomElementsByIndexString(list, house);
        String expectedResult = "1) sala, 1, 1.0, 2.0, 2.0.\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRoomAreaIndexMatchByString() {
        //Arrange
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicArea gA = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), gA, roomList);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        List<Integer> actualResult = ctrl.matchRoomIndexByString("sala", house);
        List<Integer> expectedResult = Collections.singletonList(roomList.getRoomList().indexOf(room1));
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    /*USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
    nominal power of a room, i.e. the sum of the nominal power of all devices in the
    room. - TERESA VARELA */

    @Test
    void seeRoomWithoutDevicesNominalPower() {
        //ARRANGE
        Room room1 = new Room("room1", 19, 5, 3, 3);
        double expectedResult = 0;
        RoomConfigurationController ctrl = new RoomConfigurationController();
        //ACT
        double actualResult = ctrl.getRoomNominalPower(room1);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeGetRoomNominalPower() {
        //ARRANGE
        Fridge f1 = new Fridge();
        Device d1 = new Device("d1", 12, f1);
        Device d2 = new Device("d2", 10, f1);
        Device d3 = new Device("d3", 1, f1);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        deviceList.addDevice(d2);
        deviceList.addDevice(d3);
        Room room1 = new Room("room1", 19, 5, 3, 3);
        room1.setDeviceList(deviceList);
        double expectedResult = 23;
        RoomConfigurationController ctrl = new RoomConfigurationController();
        //ACT
        double actualResult = ctrl.getRoomNominalPower(room1);
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    /*
    USER STORY 210 -
     */

    @Test
    void seeIfPrintDeviceTypeList() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        List<DeviceType> listD = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.buildDeviceTypeListString(listD);
        String expectedResult =
                "0) Device Type: WATER_HEATER;\n" +
                        "1) Device Type: WASHING_MACHINE;\n" +
                        "2) Device Type: DISHWASHER;\n" +
                        "3) Device Type: FRIDGE;\n" +
                        "4) Device Type: LAMP;\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintDeviceTypeList2() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        DeviceType dishwasher= DeviceType.DISHWASHER;
        DeviceType fridge = DeviceType.FRIDGE;
        List<DeviceType> listD = new ArrayList<>();
        listD.add(fridge);
        listD.add(dishwasher);
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.buildDeviceTypeListString(listD);
        String expectedResult =
                "0) Device Type: WATER_HEATER;\n" +
                        "1) Device Type: WASHING_MACHINE;\n" +
                        "2) Device Type: DISHWASHER;\n" +
                        "3) Device Type: FRIDGE;\n" +
                        "4) Device Type: LAMP;\n";
        assertEquals(expectedResult, result);
    }


    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    @Test
    void seeIfSensorIsContainedInGA() {
        //Arrange
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        GeographicArea gA1 = new GeographicArea();
        gA1.setSensorList(sList);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Sensor actualResult = ctrl.getSensorFromGAByName("Vento1", gA1);
        Sensor expectedResult = s1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorListIsContainedInGAList() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(sList);
        GeographicAreaList gList = new GeographicAreaList();
        gList.addGeographicAreaToGeographicAreaList(ga1);
        //Act
        RoomConfigurationController crl = new RoomConfigurationController();
        boolean actualResult = crl.checkIfGAContainsSensorByString("Vento", ga1);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfSensorListIsNotContainedInGAList() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(sList);
        GeographicAreaList gAList = new GeographicAreaList();
        gAList.addGeographicAreaToGeographicAreaList(ga1);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        boolean actualResult = ctrl.checkIfGAContainsSensorByString("Chuva", ga1);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfMatchSensorIndexByStringWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sL1 = new SensorList();
        sL1.addSensor(s1);
        sL1.addSensor(s2);
        GeographicArea gA1 = new GeographicArea();
        gA1.setSensorList(sL1);
        GeographicAreaList gList1 = new GeographicAreaList();
        gList1.addGeographicAreaToGeographicAreaList(gA1);
        //Act
        String string = "Pluviosidade";
        RoomConfigurationController ctrl = new RoomConfigurationController();
        List<Integer> actualResult = ctrl.matchSensorIndexByString(string, sL1);
        List<Integer> expectedResult = Collections.singletonList(sL1.getSensorList().indexOf(s2));
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintSensorListWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String actualResult = ctrl.buildSensorListString(sList);
        String expectedResult = "---------------\n" +
                "0) Name: Vento1 | Type: Atmosphere\n" +
                "1) Name: Pluviosidade1 | Type: Pluviosidade\n" +
                "---------------\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintSensorWorks() {
        //String buildSensorString(Sensor sensor) {
        //Assert
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String actualResult = ctrl.buildSensorString(s2);
        String expectedResult = "Pluviosidade1, Pluviosidade, 10.0º lat, 30.0º long\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintSensorElementsByIndexWorks() {
        //public String buildSensorElementsByIndexString(List<Integer> listOfIndexesOfSensor, SensorList sensorList) {
        //Assert
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        //Act
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String actualResult = ctrl.buildSensorElementsByIndexString(list, sList);
        String expectedResult = "1) Pluviosidade1 which is a Pluviosidade sensor.\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintDeviceElementsByIndex() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        Device d1 = new Device("frigorifico", 200, new Fridge());
        Device d2 = new Device("maquina de lavar", 150, new WashingMachine(1));
        Room room = new Room("kitchen", 1, 1, 2, 2);
        d1.setmParentRoom(room);
        d2.setmParentRoom(room);
        DeviceList dlist = new DeviceList();
        dlist.addDevice(d1);
        dlist.addDevice(d2);
        room.setDeviceList(dlist);

        //Act
        String result = ctrl.buildDeviceElementsByIndexString(list, room);
        String expectedResult = "1) maquina de lavar, kitchen, 150.0.\n";

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void SeeIfMatchDeviceIndexByStringWorks() {
        //Arrange
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device d1 = new Device("frigorifico", 200, new Fridge());
        Room room = new Room("kitchen", 1, 1, 2, 2);
        d1.setmParentRoom(room);
        DeviceList dlist = new DeviceList();
        dlist.addDevice(d1);
        room.setDeviceList(dlist);
        //Act
        List<Integer> result = ctrl.matchDeviceIndexByString("frigorifico", room);
        List<Integer> expectedResult = Collections.singletonList(dlist.getDeviceList().indexOf(d1));
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintDevice() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device d1 = new Device("frigorifico", 200, new Fridge());
        Room room = new Room("kitchen", 1, 1, 2, 2);
        d1.setmParentRoom(room);
        DeviceList dlist = new DeviceList();
        dlist.addDevice(d1);
        room.setDeviceList(dlist);
        String result = ctrl.buildDeviceString(d1);
        String expectedResult = "The Device Name is frigorifico, and its NominalPower is 200.0 kW.\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintDeviceList() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device d1 = new Device("frigorifico", 200, new Fridge());
        Room room = new Room("kitchen", 1, 1, 2, 2);
        d1.setmParentRoom(room);
        DeviceList dlist = new DeviceList();
        dlist.addDevice(d1);
        room.setDeviceList(dlist);
        String result = ctrl.buildDeviceListString(room);
        String expectedResult = "---------------\n" +
                "\n" +
                "0) Device Name: frigorifico, Device Type: FRIDGE, Device Nominal Power: 200.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSensorIsAddedToRoom() {
        //Arrange
        Room room1 = new Room("Quarto", 1, 5, 1, 21);
        Sensor sensor1 = new Sensor("coiso", new TypeSensor("rain", "mm"), new GregorianCalendar(2, 3, 4).getTime());
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        boolean actualResult = ctrl.addSensorToRoom(room1, sensor1);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfTypeListIsPrintedEmptyList() {
        //Arrange
        List<TypeSensor> list1 = new ArrayList<>();
        TypeSensor t1 = new TypeSensor("rain", "mm");
        TypeSensor t2 = new TypeSensor("wind", "km/h");
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String result = "Invalid List - List is Empty\n";
        String actualResult = ctrl.buildTypeListString(list1);
        //Assert
        assertEquals(result, actualResult);
    }

    @Test
    void seeIfTypeListIsPrinted() {
        //Arrange
        List<TypeSensor> list1 = new ArrayList<>();
        TypeSensor t1 = new TypeSensor("rain", "mm");
        TypeSensor t2 = new TypeSensor("wind", "km/h");
        list1.add(t1);
        list1.add(t2);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String result = "---------------\n" +
                "0) Name: rain | Units: mm\n" +
                "1) Name: wind | Units: km/h\n" +
                "---------------\n";
        String actualResult = ctrl.buildTypeListString(list1);
        //Assert
        assertEquals(result, actualResult);
    }

    @Test
    void seeIfItSetsNominalPower() {
        //Arrange
        List<TypeSensor> list1 = new ArrayList<>();
        TypeSensor t1 = new TypeSensor("rain", "mm");
        TypeSensor t2 = new TypeSensor("wind", "km/h");
        list1.add(t1);
        list1.add(t2);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String result = "---------------\n" +
                "0) Name: rain | Units: mm\n" +
                "1) Name: wind | Units: km/h\n" +
                "---------------\n";
        String actualResult = ctrl.buildTypeListString(list1);
        //Assert
        assertEquals(result, actualResult);
    }


    @Test
    void seeIfAddDeviceToRoom() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device device = new Device("waterheater", 150, new WaterHeater());
        Room room = new Room("cozinha", 1, 1, 1, 1);
        ctrl.setDeviceName("daniel", device);
        ctrl.addDeviceToRoom(room, device);
        ctrl.setNominalPower(123.0, device);
        String result = ctrl.buildDeviceListString(room);
        String expectedResult = "---------------\n" +
                "\n" +
                "0) Device Name: daniel, Device Type: WATER_HEATER, Device Nominal Power: 123.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRemoveDeviceFromRoomWorks() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device device = new Device("waterheater", 150, new WaterHeater());
        Device device1 = new Device("skjsjk", 123, new WaterHeater());
        Room room = new Room("cozinha", 1, 1, 1, 1);
        room.addDevice(device);
        room.addDevice(device1);
        ctrl.removeDeviceFromRoom(room, device1);
        String result = ctrl.buildDeviceListString(room);
        String expectedResult = "---------------\n" +
                "\n" +
                "0) Device Name: waterheater, Device Type: WATER_HEATER, Device Nominal Power: 150.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetParentRoomWorks() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Device device = new Device("waterheater", 150, new WaterHeater());
        Room room = new Room("cozinha", 1, 1, 1, 1);
        ctrl.addDeviceToRoom(room, device);
        String result = ctrl.buildDeviceListString(room);
        String expectedResult = "---------------\n" +
                "\n" +
                "0) Device Name: waterheater, Device Type: WATER_HEATER, Device Nominal Power: 150.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }
}