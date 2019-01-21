package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.devicetypes.Fridge;
import pt.ipp.isep.dei.project.model.device.devicetypes.WashingMachine;
import pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeater;

import java.util.GregorianCalendar;

import static org.testng.Assert.assertEquals;

/**
 * EnergyGrid tests class.
 */

class EnergyGridTest {

    @Test
    void seeIfPrintGridWorks() {
        Reading r1 = new Reading(20, new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        Device device = new Device();
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(device);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0);
        String result = energyGrid.buildGridString();
        assertEquals("Energy Grid: grid, Max Power: 0.0", result);
    }

    @Test
    void seeIfGetListOfRoomsWorks() {
        Room room = new Room("room1", 1, 1,2,2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        Reading r1 = new Reading(20,new GregorianCalendar(2018, GregorianCalendar.DECEMBER, 30).getTime());
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        Device device = new Device();
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(device);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0);
        energyGrid.setRoomList(roomList);
        String result = energyGrid.getListOfRooms().buildRoomsString();
        assertEquals("---------------\n" +
                "0) Designation: room1 | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n", result);
    }

    @Test
    void seeIfAddPowerToListSourceWorks() {
        PowerSourceList pWL1 = new PowerSourceList();
        PowerSource pS1 = new PowerSource("topfloor", 22, 15);
        PowerSource pS2 = new PowerSource("downfloor", 22, 15);
        EnergyGrid energyGrid = new EnergyGrid();
        energyGrid.setListPowerSources(pWL1);
        pWL1.addPowerSource(pS1);
        boolean expectedResult = true;
        boolean result = energyGrid.addPowerSource(pS2);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddPowerToListSourceFails() {
        PowerSourceList pWL1 = new PowerSourceList();
        PowerSource pS1 = new PowerSource("downfloor", 22, 15);
        PowerSource pS2 = new PowerSource("downfloor", 22, 15);
        EnergyGrid energyGrid = new EnergyGrid();
        energyGrid.setListPowerSources(pWL1);
        pWL1.addPowerSource(pS1);
        boolean expectedResult = false;
        boolean result = energyGrid.addPowerSource(pS2);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRemovesRoom() {
        Room room = new Room("room1", 1, 1,2,2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        PowerSourceList pWL1 = new PowerSourceList();
        PowerSource pS1 = new PowerSource("downfloor", 22, 15);
        EnergyGrid energyGrid = new EnergyGrid();
        energyGrid.setRoomList(roomList);
        energyGrid.setListPowerSources(pWL1);
        pWL1.addPowerSource(pS1);
        boolean expectedResult = true;
        boolean result = energyGrid.removeRoom(room);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRemovesRoomFails() {
        Room room = new Room("room1", 1, 1,2,2);
        Room room2 = new Room("room2", 1, 1,2,2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        PowerSourceList pWL1 = new PowerSourceList();
        PowerSource pS1 = new PowerSource("downfloor", 22, 15);
        EnergyGrid energyGrid = new EnergyGrid();
        energyGrid.setRoomList(roomList);
        energyGrid.setListPowerSources(pWL1);
        pWL1.addPowerSource(pS1);
        boolean expectedResult = false;
        boolean result = energyGrid.removeRoom(room2);
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf() {
        EnergyGrid energyGrid = new EnergyGrid();
        energyGrid.setName("topFloor");
        EnergyGrid energyGrid2 = new EnergyGrid();
        energyGrid2.setName("topFloor");
        Boolean expectedResult = true;
        Boolean actualResult = energyGrid.equals(energyGrid2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatAnObjectIsNotAInstanceOf() {
        EnergyGrid energyGrid = new EnergyGrid();
        energyGrid.setName("topFloor");
        EnergyGrid energyGrid2 = new EnergyGrid();
        energyGrid2.setName("downFloor");
        Boolean expectedResult = false;
        Boolean actualResult = energyGrid.equals(energyGrid2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetListPowerSourcesIsSuccessful(){
        EnergyGrid energyGrid = new EnergyGrid();
        PowerSource powerSource = new PowerSource("PS1",400,400);
        PowerSourceList powerSourceList = new PowerSourceList();
        energyGrid.setListPowerSources(powerSourceList);
        energyGrid.addPowerSource(powerSource);
        PowerSourceList expectedResult = new PowerSourceList();
        expectedResult.addPowerSource(powerSource);
        PowerSourceList result = energyGrid.getListPowerSources();
        assertEquals(expectedResult, result);
    }

    @Test
    void testAddRoomToAnEnergyGrid(){
        EnergyGrid energyGrid = new EnergyGrid();
        PowerSource powerSource = new PowerSource("PS1",400,400);
        PowerSourceList powerSourceList = new PowerSourceList();
        RoomList roomList = new RoomList();
        Room room = new Room("Quarto", 2, 10, 20, 3);
        energyGrid.setListPowerSources(powerSourceList);
        energyGrid.addPowerSource(powerSource);
        energyGrid.setRoomList(roomList);
        boolean expectedResult = true;
        boolean result = energyGrid.addRoomToAnEnergyGrid(room);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualsEnergyGridToSameObject() {
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG1", 400);
        boolean expectedResult = true;
        boolean actualResult = energyGrid1.equals(energyGrid2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsEnergyGridToObject() {
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        boolean expectedResult = true;
        boolean actualResult = energyGrid1.equals(energyGrid1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfFalseWhenObjectsAreDifferentWithDifferentContent() {
        Room room = new Room("Quarto", 2, 10, 20, 2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        boolean expectedResult = false;
        boolean actualResult = energyGrid1.equals(room);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetNominalPowerWorksMultipleRooms(){

        //Arrange

        Room r1 = new Room("Kitchen", 0,12,30,10);
        Room r2 = new Room("Sótão", 3, 30,40,12);
        Device d1 = new Device("WaterHeater1", 30, new WaterHeater(new Double(12), new Double(40), new Double (234)));
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
        EnergyGrid grid = new EnergyGrid();
        grid.setRoomList(roomList);
        double expectedResult = 80;

        //Act
        double actualResult = grid.getNominalPower();

        //Assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void seeIfPrintDevicesWorks(){
        Device d1 = new Device("Fridge", 21, new Fridge());
        Device d2 = new Device("WashingMachine", 30, new WashingMachine(23));
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        deviceList.addDevice(d2);
        EnergyGrid energyGrid = new EnergyGrid();
        Room r1 = new Room("Kitchen", 0, 21,31,10);
        r1.setDeviceList(deviceList);
        RoomList roomList = new RoomList();
        roomList.addRoom(r1);
        energyGrid.setRoomList(roomList);
        String expectedResult = "0) The device Name is Fridge, and its NominalPower is 21.0 kW.\n" +
                "1) The device Name is WashingMachine, and its NominalPower is 30.0 kW.\n";

        String actualResult = energyGrid.buildDeviceListString();

        Assertions.assertEquals(expectedResult,actualResult);
    }

    @Test
    void seeIfPrintRoomListWorks() {
        EnergyGrid grid = new EnergyGrid();
        String result = grid.buildRoomListString();
        Assert.assertEquals("Invalid List - List is Empty\n", result);
    }

    @Test
    void seeIfFalseWhenObjectsAreNull() {
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        boolean expectedResult = false;
        boolean actualResult = energyGrid1.equals(null);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest(){
        EnergyGrid energyGrid = new EnergyGrid();
        int expectedResult = 1;
        int result = energyGrid.hashCode();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWithNullList() {
        Room room1EdC = new Room("B107", 1, 7, 11, 3.5);
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        RoomList rl = new RoomList();
        DeviceList deviceList = new DeviceList();
        room1EdC.setDeviceList(deviceList);
        eg.setRoomList(rl);
        rl.addRoom(room1EdC);
        deviceList = null;
        String expectedResult = "---------------\n"+"---------------\n";
        String result = eg.buildListOfDeviceByTypeString(eg);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWithNullList2() {
        Room m = null;
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        RoomList rl = new RoomList();
        rl.addRoom(m);
        eg.setRoomList(rl);
        String expectedResult = "---------------\n"+"---------------\n";
        String result = eg.buildListOfDeviceByTypeString(eg);
        Assertions.assertEquals(expectedResult, result);
    }
}

