package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;


import static org.testng.Assert.assertEquals;

class EnergyGridTest {

    @Test
    void seeIfPrintGridWorks() {
        Reading r1 = new Reading(20);
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        Device device = new Device();
        DeviceList deviceList = new DeviceList();
        deviceList.addDevices(device);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0);
        String result = energyGrid.printGrid();
        assertEquals("Energy Grid: grid, Max Power: 0.0", result);
    }

    @Test
    void seeIfGetListOfRoomsWorks() {
        Room room = new Room("room1", 1, 1,2,2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        Reading r1 = new Reading(20);
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        Device device = new Device();
        DeviceList deviceList = new DeviceList();
        deviceList.addDevices(device);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0);
        energyGrid.setListOfRooms(roomList);
        String result = energyGrid.getListOfRooms().printRooms();
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
        energyGrid.setListOfRooms(roomList);
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
        energyGrid.setListOfRooms(roomList);
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
        energyGrid.setListOfRooms(roomList);
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
}

