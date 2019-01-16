package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.devicetypes.Dishwasher;
import pt.ipp.isep.dei.project.model.devicetypes.Fridge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * EnergyGridSettingsController tests class.
 */

class EnergyGridSettingsControllerTest {


    //USER STORY 145

    @Test
    void seeIfIndexIsMatchedByString() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        Room room = new Room("Quarto", 1, 20, 2, 2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addGrid(energyGrid1);
        energyGridList.addGrid(energyGrid2);
        roomList.addRoom(room);
        house.setRoomList(roomList);
        house.setEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        List<Integer> result = ctrlUS145.matchGridIndexByString("EG2", house);
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(1);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEnergyGridIsPrintedByIndex() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        Room room = new Room("Quarto", 1, 20, 2, 2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addGrid(energyGrid1);
        energyGridList.addGrid(energyGrid2);
        roomList.addRoom(room);
        house.setRoomList(roomList);
        house.setEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        List<Integer> list = ctrlUS145.matchGridIndexByString("EG1", house);
        String actualResult = ctrlUS145.buildEnergyGridByIndexString(list, energyGridList);
        String expectedResult = "0) EG1, 400.0, pt.ipp.isep.dei.project.model.PowerSourceList@1.\n";
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfRoomsPrint() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        Room room = new Room("Quarto", 1, 20, 2, 2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addGrid(energyGrid1);
        roomList.addRoom(room);
        house.setRoomList(roomList);
        house.setEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        String result = ctrlUS145.buildRoomsString(roomList);
        String expectedResult = "---------------\n" +
                "0) Designation: Quarto | House Floor: 1 | Width: 20.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        Assert.assertEquals(expectedResult, result);
    }

    //USER STORY 149

    @Test
    void seeIfRoomIsRemovedFromGrid() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        Room room = new Room("Quarto", 1, 20, 2, 2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addGrid(energyGrid1);
        energyGridList.addGrid(energyGrid2);
        roomList.addRoom(room);
        energyGrid1.setRoomList(roomList);
        house.setEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        ctrlUS145.removeRoomFromGrid(energyGrid1, room);
        Room expectedResult = null;
        Room result = energyGrid1.getListOfRooms().getRoomByName("Quarto");
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRoomIsRemovedFromGridBreaks() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        Room room = new Room("Quarto", 1, 20, 2, 2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGrid energyGrid2 = new EnergyGrid("EG2", 200);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addGrid(energyGrid1);
        energyGridList.addGrid(energyGrid2);
        roomList.addRoom(room);
        energyGrid1.setRoomList(roomList);
        house.setEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        boolean result = ctrlUS145.removeRoomFromGrid(energyGrid1, room);
        assertTrue(result);
    }

    @Test
    void seeIfEnergyGridPrints() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        Room room = new Room("Quarto", 1, 20, 2, 2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addGrid(energyGrid1);
        roomList.addRoom(room);
        house.setRoomList(roomList);
        house.setEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        String result = ctrlUS145.buildEnergyGridString(energyGrid1);
        String expectedResult = "Energy Grid: EG1, Max Power: 400.0";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGridListPrints() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), ga, roomList);
        Room room = new Room("Quarto", 1, 20, 2, 2);
        EnergyGrid energyGrid1 = new EnergyGrid("EG1", 400);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addGrid(energyGrid1);
        roomList.addRoom(room);
        house.setRoomList(roomList);
        house.setEGList(energyGridList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        String result = ctrlUS145.buildGridListString(house);
        String expectedResult = "---------------\n" +
                "0) Designation: EG1 | Max Power: 400.0\n" +
                "---------------\n";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void ensureThatWeRemoveRoomFromGrid() {
        EnergyGridSettingsController egsc = new EnergyGridSettingsController();
        Room room1EdC = new Room("B107", 1, 7, 11, 3.5);
        Room room2EdC = new Room("B109", 1, 7, 11, 3.5);
        Room room3EdC = new Room("B106", 1, 7, 13, 3.5);
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        EnergyGridList egl = new EnergyGridList();
        egl.addGrid(eg);
        RoomList rl = new RoomList();
        eg.setRoomList(rl);
        rl.addRoom(room1EdC);
        rl.addRoom(room2EdC);
        rl.addRoom(room3EdC);
        boolean expectedResult = true;
        boolean actualResult = egsc.removeRoomFromGrid(eg, room1EdC);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeDoNotRemoveRoomFromGrid() {
        EnergyGridSettingsController egsc = new EnergyGridSettingsController();
        Room room1EdC = new Room("B107", 1, 7, 11, 3.5);
        Room room2EdC = new Room("B109", 1, 7, 11, 3.5);
        Room room3EdC = new Room("B106", 1, 7, 13, 3.5);
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        EnergyGridList egl = new EnergyGridList();
        egl.addGrid(eg);
        RoomList rl = new RoomList();
        eg.setRoomList(rl);
        rl.addRoom(room2EdC);
        rl.addRoom(room3EdC);
        boolean expectedResult = false;
        boolean actualResult = egsc.removeRoomFromGrid(eg, room1EdC);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeAddRoomToTheGrid() {
        EnergyGridSettingsController egsc = new EnergyGridSettingsController();
        Room room1EdC = new Room("B107", 1, 7, 11, 3.5);
        Room room2EdC = new Room("B109", 1, 7, 11, 3.5);
        Room room3EdC = new Room("B106", 1, 7, 13, 3.5);
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        EnergyGridList egl = new EnergyGridList();
        egl.addGrid(eg);
        RoomList rl = new RoomList();
        eg.setRoomList(rl);
        boolean expectedResult = true;
        boolean actualResult = egsc.addRoomToGrid(eg, room1EdC);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeDoNotAddRoomToTheGrid() {
        EnergyGridSettingsController egsc = new EnergyGridSettingsController();
        Room room1EdC = new Room("B107", 1, 7, 11, 3.5);
        Room room2EdC = new Room("B109", 1, 7, 11, 3.5);
        Room room3EdC = new Room("B106", 1, 7, 13, 3.5);
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        EnergyGridList egl = new EnergyGridList();
        egl.addGrid(eg);
        RoomList rl = new RoomList();
        eg.setRoomList(rl);
        rl.addRoom(room1EdC);
        rl.addRoom(room2EdC);
        rl.addRoom(room3EdC);
        boolean expectedResult = false;
        boolean actualResult = egsc.addRoomToGrid(eg, room1EdC);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRoomListIsPrintedByHouse() {
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), new GeographicArea(), new RoomList());
        RoomList roomList = new RoomList();
        Room room = new Room("Quarto", 1, 20, 2, 2);
        roomList.addRoom(room);
        house.setRoomList(roomList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        String result = ctrlUS145.buildHouseRoomListString(house);
        String expectedResult = "---------------\n" +
                "0) Designation: Quarto | House Floor: 1 | Width: 20.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRoomIndexIsMatchedByString() {
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50), new GeographicArea(), new RoomList());
        RoomList roomList = new RoomList();
        Room room = new Room("Quarto", 1, 20, 2, 2);
        roomList.addRoom(room);
        house.setRoomList(roomList);
        EnergyGridSettingsController ctrlUS145 = new EnergyGridSettingsController();
        List<Integer> result = ctrlUS145.getIndexHouseRoomsByString("Quarto", house);
        List<Integer> expectedResult = Collections.singletonList(0);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintRoomElementsByIndex() {
        //Arrange
        EnergyGridSettingsController ctrl = new EnergyGridSettingsController();
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicArea ga = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, roomList);

        //Act
        String result = ctrl.buildHouseRoomsByIndexString(list, house);
        String expectedResult = "1) sala, 1, 1.0, 2.0, 2.0.\n";

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddPowerSourceToEnergyGridWorks() {
        EnergyGridSettingsController ctrl = new EnergyGridSettingsController();
        EnergyGrid grid = new EnergyGrid("grid", 400);
        ctrl.createPowerSource("pw", 10, 10);
        boolean result = ctrl.addPowerSourceToGrid(grid);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddPowerSourceToEnergyGridWorksFalse() {
        EnergyGridSettingsController ctrl = new EnergyGridSettingsController();
        EnergyGrid grid = new EnergyGrid("grid", 400);
        boolean result = ctrl.addPowerSourceToGrid(grid);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddEnergyGridToHouseWorks() {
        EnergyGridSettingsController ctrl = new EnergyGridSettingsController();
        RoomList roomList = new RoomList();
        House house = new House("casa", "as", "as", "s", new Local(1, 1, 1), new GeographicArea("porto", new TypeArea("cidade"), 12, 12, new Local(1, 1, 1)), roomList);
        ctrl.createEnergyGrid("grid", 400);
        boolean result = ctrl.addEnergyGridToHouse(house);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddEnergyGridToHouseWorksFalse() {
        EnergyGridSettingsController ctrl = new EnergyGridSettingsController();
        RoomList roomList = new RoomList();
        House house = new House("casa", "as", "as", "s", new Local(1, 1, 1), new GeographicArea("porto", new TypeArea("cidade"), 12, 12, new Local(1, 1, 1)), roomList);
        boolean result = ctrl.addEnergyGridToHouse(house);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintRoomWorks() {
        EnergyGridSettingsController ctrl = new EnergyGridSettingsController();
        Room room = new Room("quarto1", 1, 2, 2, 2);
        String result = ctrl.buildRoomString(room);
        String expectedResult = "quarto1, 1, 2.0, 2.0, 2.0.\n";
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsInvalidList() {
        EnergyGridSettingsController ctrl = new EnergyGridSettingsController();
        EnergyGrid grid = new EnergyGrid("grid", 400);
        String expectedResult = "Invalid List - List is Empty\n";
        String result = ctrl.buildGridRoomsString(grid);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsRoomList() {
        EnergyGridSettingsController ctrl = new EnergyGridSettingsController();
        Room room1EdC = new Room("B107", 1, 7, 11, 3.5);
        Room room2EdC = new Room("B109", 1, 7, 11, 3.5);
        Room room3EdC = new Room("B106", 1, 7, 13, 3.5);
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        EnergyGridList egl = new EnergyGridList();
        egl.addGrid(eg);
        RoomList rl = new RoomList();
        eg.setRoomList(rl);
        rl.addRoom(room1EdC);
        rl.addRoom(room2EdC);
        rl.addRoom(room3EdC);
        String expectedResult = "---------------\n" +
                "0) Designation: B107 | House Floor: 1 | \n" +
                "1) Designation: B109 | House Floor: 1 | \n" +
                "2) Designation: B106 | House Floor: 1 | \n" +
                "---------------\n";
        String result = ctrl.buildGridRoomsString(eg);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeviceListPrintsByType() {
        EnergyGridSettingsController ctrl = new EnergyGridSettingsController();
        Room room1EdC = new Room("B107", 1, 7, 11, 3.5);
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        RoomList rl = new RoomList();
        Device d1 = new Device("fridgeOne", 12, new Fridge());
        Device d2 = new Device("DWOne", 13, new Dishwasher());
        Device d3 = new Device("FridgeTwo", 14, new Fridge());
        d1.setName("uno");
        d2.setName("dos");
        d3.setName("tres");
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(d1);
        deviceList.addDevice(d2);
        deviceList.addDevice(d3);
        room1EdC.setDeviceList(deviceList);
        eg.setRoomList(rl);
        rl.addRoom(room1EdC);
        String expectedResult = "---------------\n" +
                "Device type: DISHWASHER | dos | Room: B107 | \n" +
                "Device type: FRIDGE | uno | Room: B107 | \n" +
                "Device type: FRIDGE | tres | Room: B107 | \n" +
                "---------------\n";
        String result = ctrl.buildListOfDevicesByTypeString(eg);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWithEmptyRoomList() {
        EnergyGridSettingsController ctrl = new EnergyGridSettingsController();
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        RoomList rl = new RoomList();
        eg.setRoomList(rl);
        String expectedResult = "This energy grid has no rooms attached\n";
        String result = ctrl.buildListOfDevicesByTypeString(eg);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfDeviceListPrintsByTypeWithNoDevices() {
        EnergyGridSettingsController ctrl = new EnergyGridSettingsController();
        Room room1EdC = new Room("B107", 1, 7, 11, 3.5);
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333);
        RoomList rl = new RoomList();
        DeviceList deviceList = new DeviceList();
        room1EdC.setDeviceList(deviceList);
        eg.setRoomList(rl);
        rl.addRoom(room1EdC);
        String expectedResult = "This energy grid has no devices on it\n";
        String result = ctrl.buildListOfDevicesByTypeString(eg);
        assertEquals(expectedResult, result);
    }
}
