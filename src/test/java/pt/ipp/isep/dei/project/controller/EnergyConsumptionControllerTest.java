package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.Log;
import pt.ipp.isep.dei.project.model.device.devicetypes.Fridge;
import pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeater;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * EnergyConsumptionController tests class.
 */

class EnergyConsumptionControllerTest {

    //US705 TESTS

    @Test
    void seeIfPrintRoomsAndDevices() {

        //Arrange

        EnergyGrid grid = new EnergyGrid();
        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        Device d1 = new Device("WaterHeater", 21, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d2 = new Device("WaterHeaterTwo", 55, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d3 = new Device("Fridge", 10, new Fridge(5,3,456));
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        RoomList roomList = new RoomList();
        roomList.addRoom(r1);
        grid.setRoomList(roomList);
        String expectedResult = "0) Kitchen.\n" +
                "1) WaterHeater, Type: WATER_HEATER, Power: 21.0.\n" +
                "2) WaterHeaterTwo, Type: WATER_HEATER, Power: 55.0.\n" +
                "3) Fridge, Type: FRIDGE, Power: 10.0.\n";
        EnergyConsumptionController controller = new EnergyConsumptionController();

        //Act
        String actualResult = controller.buildRoomsAndDevicesString(grid);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfAddRoomDevicesToDeviceList() {

        //Arrange

        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        Device d1 = new Device("WaterHeater", 21, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d2 = new Device("WaterHeaterTwo", 55, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d3 = new Device("Fridge", 10, new Fridge(5,7,67));
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        DeviceList deviceList1 = new DeviceList();
        EnergyConsumptionController controller = new EnergyConsumptionController();
        boolean expectedResult = true;

        //Act
        boolean actualResult = controller.addRoomDevicesToDeviceList(r1, deviceList1);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfRemoveRoomDevicesToDeviceListTrue() {

        //Arrange

        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        Device d1 = new Device("WaterHeater", 21, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d2 = new Device("WaterHeaterTwo", 55, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d3 = new Device("Fridge", 10, new Fridge(5,7,56));
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        DeviceList deviceList1 = new DeviceList();
        deviceList1.addDevice(d1);
        deviceList1.addDevice(d2);
        deviceList1.addDevice(d3);
        EnergyConsumptionController controller = new EnergyConsumptionController();
        boolean expectedResult = true;

        //Act
        boolean actualResult = controller.removeRoomDevicesFromDeviceList(r1, deviceList1);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfRemoveRoomDevicesToDeviceListFalse() {

        //Arrange

        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        Device d1 = new Device("WaterHeater", 21, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d2 = new Device("WaterHeaterTwo", 55, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d3 = new Device("Fridge", 10, new Fridge(5,4,34));
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        DeviceList deviceList1 = new DeviceList();
        EnergyConsumptionController controller = new EnergyConsumptionController();
        boolean expectedResult = true;

        //Act
        boolean actualResult = controller.removeRoomDevicesFromDeviceList(r1, deviceList1);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfAddRoomDevicesToDeviceListFalse() {

        //Arrange

        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        Device d1 = new Device("WaterHeater", 21, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d2 = new Device("WaterHeaterTwo", 55, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d3 = new Device("Fridge", 10, new Fridge(4,5,56));
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        DeviceList deviceList1 = null;
        EnergyConsumptionController controller = new EnergyConsumptionController();
        boolean expectedResult = false;

        //Act
        boolean actualResult = controller.removeRoomDevicesFromDeviceList(r1, deviceList1);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfAddRoomToListWorksTrue() {

        //Arrange

        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        RoomList roomList = new RoomList();
        boolean expectedResult = true;
        EnergyConsumptionController controller = new EnergyConsumptionController();

        //Act
        boolean actualResult = controller.addRoomToList(r1, roomList);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfRemoveRoomFromListWorksTrue() {

        //Arrange

        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        RoomList roomList = new RoomList();
        roomList.addRoom(r1);
        boolean expectedResult = true;
        EnergyConsumptionController controller = new EnergyConsumptionController();

        //Act
        boolean actualResult = controller.removeRoomFromList(r1, roomList);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfRemoveRoomFromListWorkFalse() {

        //Arrange

        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        RoomList roomList = new RoomList();
        boolean expectedResult = false;
        EnergyConsumptionController controller = new EnergyConsumptionController();

        //Act
        boolean actualResult = controller.removeRoomFromList(r1, roomList);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfAddRoomToListWorksFalse() {

        //Arrange

        Room r1 = new Room("Kitchen", 0, 30, 50, 10);
        RoomList roomList = new RoomList();
        roomList.addRoom(r1);
        boolean expectedResult = false;
        EnergyConsumptionController controller = new EnergyConsumptionController();

        //Act
        boolean actualResult = controller.addRoomToList(r1, roomList);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfAddDeviceToListWorksTrue() {

        //Arrange

        Device d1 = new Device("Fridge", 20, new Fridge(5,7,56));
        DeviceList deviceList = new DeviceList();
        boolean expectedResult = true;
        EnergyConsumptionController controller = new EnergyConsumptionController();

        //Act
        boolean actualResult = controller.addDeviceToList(d1, deviceList);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfAddDeviceToListWorksFalse() {

        //Arrange

        Device d1 = new Device("Fridge", 20, new Fridge(3,6,45));
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        boolean expectedResult = false;
        EnergyConsumptionController controller = new EnergyConsumptionController();

        //Act
        boolean actualResult = controller.addDeviceToList(d1, deviceList);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfRemoveDeviceFromListWorksFalse() {

        //Arrange

        Device d1 = new Device("Fridge", 20, new Fridge(3,7,564));
        DeviceList deviceList = new DeviceList();
        boolean expectedResult = false;
        EnergyConsumptionController controller = new EnergyConsumptionController();

        //Act
        boolean actualResult = controller.removeDeviceFromList(d1, deviceList);

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfRemoveDeviceFromListWorksTrue() {

        //Arrange

        Device d1 = new Device("Fridge", 20, new Fridge(4,7,56));
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        boolean expectedResult = true;
        EnergyConsumptionController controller = new EnergyConsumptionController();

        //Act
        boolean actualResult = controller.removeDeviceFromList(d1, deviceList);

        //Assert
        assertEquals(expectedResult, actualResult);

    }


    @Test
    void seeIfGetSumNominalPowerFromListWorks() {
        //Arrange

        Device d1 = new Device("WaterHeater", 21, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d2 = new Device("WaterHeaterTwo", 55, new WaterHeater(new Double(12), new Double(40), new Double (234)));
        Device d3 = new Device("Fridge", 10, new Fridge(5,7,45));
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        deviceList.addDevice(d2);
        deviceList.addDevice(d3);
        EnergyConsumptionController controller = new EnergyConsumptionController();
        double expectedResult = 86;

        //Act
        double actualResult = controller.getSelectionNominalPower(deviceList);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSumNominalPowerFromListWorksZero() {
        //Arrange

        DeviceList deviceList = new DeviceList();
        EnergyConsumptionController controller = new EnergyConsumptionController();
        double expectedResult = 0;

        //Act
        double actualResult = controller.getSelectionNominalPower(deviceList);

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    //US752 TESTS

    @Test
    void getDailyHouseConsumptionTest() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        House h1 = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20,20,20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)));
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Device("fridgeOne", 12, new Fridge(3,7,45));
        Device d2 = new Device("wHeater1", 12, new WaterHeater(200.0, 30.0, 0.9));
        Device d3 = new Device("wHeater2", 11, new WaterHeater(500.0, 20.0, 0.9));
        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        Device d4 = new Device("fridgeTwo", 12, new Fridge(3,6,45));
        Device d5 = new Device("wHeater3", 12, new WaterHeater(300.0, 15.0, 0.9));
        Device d6 = new Device("wHeater4", 11, new WaterHeater(400.0, 20.0, 0.9));
        r2.addDevice(d4);
        r2.addDevice(d5);
        r2.addDevice(d6);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0.0;
        double result = controller.getDailyWaterHeaterConsumption(h1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyHouseConsumptionNoRoomsTest() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        House h1 = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20,20,20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)));
        double expectedResult = 0;
        double result = controller.getDailyWaterHeaterConsumption(h1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyHouseConsumptionNoDevicesTest() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        House h1 = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20,20,20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)));
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0;
        double result = controller.getDailyWaterHeaterConsumption(h1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getDailyHouseConsumptionNoHeaterDevicesTest() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        House h1 = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20,20,20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)));
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Device("fridgeOne", 12, new Fridge(3,6,45));
        r1.addDevice(d1);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        Device d4 = new Device("fridgeTwo", 12, new Fridge(3,7,45));
        r2.addDevice(d4);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0;
        double result = controller.getDailyWaterHeaterConsumption(h1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getTotalNominalPowerFromGridTest() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        House h1 = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20,20,20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)));
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d1 = new Device("fridgeOne", 12, new Fridge(4,7,45));
        r1.addDevice(d1);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        Device d4 = new Device("fridgeTwo", 12, new Fridge(3,7,435));
        EnergyGrid grid1 = new EnergyGrid();
        RoomList rlist1 = new RoomList();
        grid1.setRoomList(rlist1);
        rlist1.addRoom(r2);
        rlist1.addRoom(r1);
        r2.addDevice(d4);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 24;
        double result = controller.getTotalPowerFromGrid(grid1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getTotalNominalPowerFromGridNoDevicesTest() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        House h1 = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20,20,20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)));
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Room r2 = new Room("kitchen", 2, 12, 12, 12);
        EnergyGrid grid1 = new EnergyGrid();
        RoomList rlist1 = new RoomList();
        grid1.setRoomList(rlist1);
        rlist1.addRoom(r2);
        rlist1.addRoom(r1);
        h1.addRoomToRoomList(r1);
        h1.addRoomToRoomList(r2);
        double expectedResult = 0;
        double result = controller.getTotalPowerFromGrid(grid1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getWaterHeaterDeviceListTest() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        House h1 = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20,20,20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)));
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        h1.addRoomToRoomList(r1);
        Device d6 = new Device("wHeater4", 11, new WaterHeater(400.0, 20.0, 0.9));
        DeviceList d1 = new DeviceList();
        d1.addDevice(d6);
        r1.setDeviceList(d1);
        List<Device> expecteResult = new ArrayList<>();
        expecteResult.add(d6);
        List<Device> result = controller.getWHDeviceList(h1);
        Assertions.assertEquals(expecteResult, result);
    }

    @Test
    void getWaterHeaterDeviceListTest2() {
        EnergyConsumptionController controller = new EnergyConsumptionController();
        House house = new House("house1", "Rua Carlos Peixoto", "4535", "Santa Maria de Lamas", new Local(20,20,20), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)));
        Room r1 = new Room("quarto", 1, 12, 12, 12);
        Device d2 = new Device("wHeater1", 12, new WaterHeater(200.0,20.0,10.0));
        Device d3 = new Device("wHeater2", 11, new WaterHeater(500.0,30.0,1.0));
        r1.addDevice(d2);
        r1.addDevice(d3);
        house.addRoomToRoomList(r1);
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(d2);
        expectedResult.add(d3);
        List<Device> result = controller.getWHDeviceList(house);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void getHouseDevicesOfGivenType(){
        EnergyConsumptionController controller = new EnergyConsumptionController();
        Device d = new Device("wHeater2", 11, new WaterHeater(500.0,30.0,1.0));
        String result = controller.getWHName(d);
        Assertions.assertEquals("wHeater2", result);
    }

    @Test
    void configureOneHeaterTestFalse() {
        Device d6 = new Device("wHeater4", 11, new WaterHeater(400.0, 20.0, 0.9));
        d6.setAttributeValue("blowUp", 12);
        d6.setAttributeValue("blabla", 60);
        Double attributeValue = null;
        Double attributeValue2 = 30.0;
        EnergyConsumptionController controller = new EnergyConsumptionController();
        controller.configureWH(d6, attributeValue, attributeValue2);
        boolean result = controller.configureWH(d6, attributeValue, attributeValue2);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void configureOneHeaterTestFalseSecondElement() {
        Device d6 = new Device("wHeater4", 11, new WaterHeater(400.0, 20.0, 0.9));
        d6.setAttributeValue("blowUp", 12);
        d6.setAttributeValue("blabla", 60);
        Double attributeValue = 2.0;
        Double attributeValue2 = null;
        EnergyConsumptionController controller = new EnergyConsumptionController();
        controller.configureWH(d6, attributeValue, attributeValue2);
        boolean result = controller.configureWH(d6, attributeValue, attributeValue2);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void configureOneHeaterTestTrue() {
        Device d6 = new Device("wHeater4", 11, new WaterHeater(400.0, 20.0, 0.9));
        d6.setAttributeValue("Porto", 12);
        d6.setAttributeValue("Lisboa", 60);
        Double attributeValue = 2.0;
        Double attributeValue2 = 30.0;
        EnergyConsumptionController controller = new EnergyConsumptionController();
        boolean result = controller.configureWH(d6, attributeValue, attributeValue2);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void getWaterHeaterNameTest() {
        Device d6 = new Device("wHeater4", 11, new WaterHeater(400.0, 20.0, 0.9));
        String expectedResult = "wHeater4";
        String result = d6.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForTrue(){
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        Date initialTime = new GregorianCalendar(2018,10,20,10,2).getTime();
        Date finalTime = new GregorianCalendar(2018,10,20,10,60).getTime();
        Date periodBeginning = new GregorianCalendar(2018,10,20,10,10).getTime();
        Date periodEnding = new GregorianCalendar(2018,10,20,10,50).getTime();
        Device device = new Device("Washing machine",200, new WaterHeater(400.0,400.0,0.9));
        Log log = new Log(56,periodBeginning, periodEnding);
        device.addLogToLogList(log);
        boolean result = ctrl.getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeInterval(device, initialTime, finalTime);
        assertTrue(result);
    }

    @Test
    void getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeIntervalTestForEmptyList(){
        EnergyConsumptionController ctrl = new EnergyConsumptionController();
        Date initialTime = new GregorianCalendar(2018,10,20,9,2).getTime();
        Date finalTime = new GregorianCalendar(2018,10,20,9,60).getTime();
        Date periodBeginning = new GregorianCalendar(2018,10,20,10,10).getTime();
        Date periodEnding = new GregorianCalendar(2018,10,20,10,50).getTime();
        Device device = new Device("Washing machine",200, new WaterHeater(400.0,400.0,0.9));
        Log log = new Log(56,periodBeginning, periodEnding);
        device.addLogToLogList(log);
        boolean result = ctrl.getTotalMeteredEnergyConsumptionInDeviceWithinGivenTimeInterval(device, initialTime, finalTime);
        assertTrue(!result);
    }
}
