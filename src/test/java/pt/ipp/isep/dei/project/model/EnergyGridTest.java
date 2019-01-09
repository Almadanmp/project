package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EnergyGridTest {
    @Test
    public void ensureThatWeAttachARoomToAHouseEnergyGridWithMaxPowerAndGetTheMaxPower() {
        //Arrange
        Reading r1 = new Reading(200, new GregorianCalendar(2018, 11, 25).getTime());
        ReadingList rl1 = new ReadingList();
        rl1.addReading(r1);
        SensorList sensorList = new SensorList();
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Energia"), new Local(22, 2), new GregorianCalendar(2018, 11, 25).getTime());
        sensorList.addSensor(s1);
        Room room = new Room("Master Room", 3, 9,2,2);
        room.setRoomSensorList(sensorList);
        EnergyGrid eg = new EnergyGrid();
        RoomList roomList = new RoomList();
        eg.setListOfRooms(roomList);
        eg.addRoomToAEnergyGrid(room);
        Device device = new Device("Device 1", "Power", room, rl1, 500);
        DeviceList dl1 = new DeviceList();
        dl1.addDevices(device);
        room.setRoomDeviceList(dl1);
        dl1.addDevices(device);
        eg.setListDevices(dl1);
        double expectedResult = 500;
        //Act
        double actualResult = eg.getTotalPower();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfprintGridWorks() {
        Room room = new Room("room1", 1, 1,2,2);
        Reading r1 = new Reading(20);
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        Device device = new Device("frigo", "frio", room, readingList, 0);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevices(device);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0, deviceList);
        String result = energyGrid.printGrid();
        assertEquals("Energy Grid: grid, Max Power: 0.0", result);
    }

    @Test
    public void seeIfgetListOfRoomsWorks() {
        Room room = new Room("room1", 1, 1,2,2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        Reading r1 = new Reading(20);
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        Device device = new Device("frigo", "frio", room, readingList, 0);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevices(device);
        EnergyGrid energyGrid = new EnergyGrid("grid", 0, deviceList, roomList);
        String result = energyGrid.getmListOfRooms().printRooms();
        assertEquals("---------------\n" +
                "0) Designation: room1 | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n", result);
    }

    @Test
    public void seeIfAddPowerToListSourceWorks() {
        PowerSourceList pWL1 = new PowerSourceList();
        PowerSource pS1 = new PowerSource("topfloor", 22, 15);
        PowerSource pS2 = new PowerSource("downfloor", 22, 15);
        EnergyGrid energyGrid = new EnergyGrid();
        energyGrid.setListPowerSources(pWL1);
        pWL1.addPowerSource(pS1);
        boolean expectedResult = true;
        boolean result = energyGrid.addPowerSource(pS2);
        assertEquals(expectedResult,result);
    }

    @Test
    public void seeIfAddPowerToListSourceFails() {
        PowerSourceList pWL1 = new PowerSourceList();
        PowerSource pS1 = new PowerSource("downfloor", 22, 15);
        PowerSource pS2 = new PowerSource("downfloor", 22, 15);
        EnergyGrid energyGrid = new EnergyGrid();
        energyGrid.setListPowerSources(pWL1);
        pWL1.addPowerSource(pS1);
        boolean expectedResult = false;
        boolean result = energyGrid.addPowerSource(pS2);
        assertEquals(expectedResult,result);
    }

    @Test
    public void seeIfRemovesRoom() {
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
        assertEquals(expectedResult,result);
    }

    @Test
    public void seeIfRemovesRoomFails() {
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
        assertEquals(expectedResult,result);
    }

    @Test
    public void ensureThatAObjectIsAInstanceOf() {
        EnergyGrid energyGrid = new EnergyGrid();
        energyGrid.setName("topFloor");
        EnergyGrid energyGrid2 = new EnergyGrid();
        energyGrid2.setName("topFloor");
        Boolean expectedResult = true;
        Boolean actualResult = energyGrid.equals(energyGrid2);

       assertEquals(expectedResult,actualResult);
    }

    @Test
    public void ensureThatAObjectIsNotAInstanceOf() {
        EnergyGrid energyGrid = new EnergyGrid();
        energyGrid.setName("topFloor");
        EnergyGrid energyGrid2 = new EnergyGrid();
        energyGrid2.setName("downFloor");
        Boolean expectedResult = false;
        Boolean actualResult = energyGrid.equals(energyGrid2);

        assertEquals(expectedResult,actualResult);
    }
}

