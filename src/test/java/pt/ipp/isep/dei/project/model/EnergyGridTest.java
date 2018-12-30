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
        double actualResult = eg.getMaxPower();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

}

