package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.testng.Assert.*;

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
        Room room = new Room("Master Room", 3, 9, sensorList);
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
    public void seeIfprintGridWorks(){
        Room room = new Room("room1",1,1);
        Reading r1 = new Reading(20);
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        Device device = new Device("frigo","frio",room,readingList,0);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevices(device);
        EnergyGrid energyGrid = new EnergyGrid("grid",0,deviceList);
        String result = energyGrid.printGrid();
        assertEquals("Energy Grid: grid, Max Power: 0.0",result);
    }
    @Test
    public void seeIfgetListOfRoomsWorks(){
        Room room = new Room("room1",1,1);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        Reading r1 = new Reading(20);
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        Device device = new Device("frigo","frio",room,readingList,0);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevices(device);
        EnergyGrid energyGrid = new EnergyGrid("grid",0,deviceList,roomList);
        String result = energyGrid.getmListOfRooms().printRooms();
        assertEquals("---------------\n" +
                "0) Designation: room1 | House Floor: 1 | Dimensions: 1.0\n" +
                "---------------\n",result);
    }

}

