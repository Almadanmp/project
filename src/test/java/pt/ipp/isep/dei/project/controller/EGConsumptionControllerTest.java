package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.devicetypes.Fridge;
import pt.ipp.isep.dei.project.model.devicetypes.WaterHeater;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EGConsumptionControllerTest {

    //US705 TESTS

    @Test
    void seeIfPrintRoomsAndDevices() {

        //Arrange

        EnergyGrid grid = new EnergyGrid();
        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        Device d1 = new Device("WaterHeater", 21, new WaterHeater());
        Device d2 = new Device("WaterHeaterTwo", 55, new WaterHeater());
        Device d3 = new Device("Fridge", 10, new Fridge());
        DeviceList deviceList = new DeviceList();
        deviceList.addDevices(d1);
        deviceList.addDevices(d2);
        deviceList.addDevices(d3);
        r1.setDeviceList(deviceList);
        RoomList roomList = new RoomList();
        roomList.addRoom(r1);
        grid.setRoomList(roomList);
        String expectedResult = "0) Kitchen.\n" +
                "1) WaterHeater, Type: WATER_HEATER, Power: 21.0.\n" +
                "2) WaterHeaterTwo, Type: WATER_HEATER, Power: 55.0.\n" +
                "3) Fridge, Type: FRIDGE, Power: 10.0.\n";
        EGConsumptionController controller = new EGConsumptionController();

        //Act
        String actualResult = controller.printRoomsAndDevices(grid);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfGetSumNominalPowerByIndexWorks() {
        //Arrange

        EnergyGrid grid = new EnergyGrid();
        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        Device d1 = new Device("WaterHeater", 21, new WaterHeater());
        Device d2 = new Device("WaterHeaterTwo", 55, new WaterHeater());
        Device d3 = new Device("Fridge", 10, new Fridge());
        DeviceList deviceList = new DeviceList();
        deviceList.addDevices(d1);
        deviceList.addDevices(d2);
        deviceList.addDevices(d3);
        r1.setDeviceList(deviceList);
        RoomList roomList = new RoomList();
        roomList.addRoom(r1);
        grid.setRoomList(roomList);
        List<Integer> indexes = new ArrayList<>();
        indexes.add(3);
        indexes.add(0);
        EGConsumptionController controller = new EGConsumptionController();
        double expectedResult = 96;

        //Act
        double actualResult = controller.getSumNominalPowerByIndex(indexes,grid);

        //Assert
        assertEquals(expectedResult,actualResult);
    }


    //US752 TESTS

    @Test
    void getDailyHouseConsumptionTest() {
        EGConsumptionController controller = new EGConsumptionController();
        House h1 = new House();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Device("fridgeOne", 12, new Fridge());
        Device d2 = new Device("wHeater1", 12, new WaterHeater(200, 30, 1,0.9));
        Device d3 = new Device("wHeater2", 11, new WaterHeater(500, 20, 10,0.9));
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        Device d4 = new Device("fridgeTwo", 12, new Fridge());
        Device d5 = new Device("wHeater3", 12, new WaterHeater(300, 15, 1,0.9));
        Device d6 = new Device("wHeater4", 11, new WaterHeater(400, 20, 12,0.9));
        r2.addDevice(d4);
        r2.addDevice(d5);
        r2.addDevice(d6);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 457198.56000000006;
        double result = controller.getDailyHouseConsumptionWaterHeater(h1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyHouseConsumptionNoRoomsTest() {
        EGConsumptionController controller = new EGConsumptionController();
        House h1 = new House();
        double expectedResult = 0;
        double result = controller.getDailyHouseConsumptionWaterHeater(h1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyHouseConsumptionNoDevicesTest() {
        EGConsumptionController controller = new EGConsumptionController();
        House h1 = new House();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0;
        double result = controller.getDailyHouseConsumptionWaterHeater(h1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyHouseConsumptionNoHeaterDevicesTest() {
        EGConsumptionController controller = new EGConsumptionController();
        House h1 = new House();
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Device("fridgeOne", 12, new Fridge());
        r1.addDevice(d1);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        Device d4 = new Device("fridgeTwo", 12, new Fridge());
        r2.addDevice(d4);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0;
        double result = controller.getDailyHouseConsumptionWaterHeater(h1);
        assertEquals(expectedResult, result);
    }
}
