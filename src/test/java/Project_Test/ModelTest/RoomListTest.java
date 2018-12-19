package Project_Test.ModelTest;

import PT.IPP.ISEP.DEI.Project.Model.*;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomListTest {

    @Test
    public void seeIfGetRoomByNameFromList() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("Cozinha", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Temperatura"), new Local(21, 23), new Date(21 / 11 / 2018))));
        Room r2 = new Room("Jardim", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Rainfall"), new Local(21, 25), new Date(21 / 11 / 2018))));
        Room r3 = new Room("Quarto", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Temperatura"), new Local(21, 23), new Date(21 / 11 / 2018))));
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);

        Room expectedResult = r1;
        Room actualResult = roomList.getRoomByName("Cozinha");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfDoesListOfRoomsContainRoomByName() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("Cozinha", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Temperatura"), new Local(21, 23), new Date(21 / 11 / 2018))));
        Room r2 = new Room("Jardim", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Rainfall"), new Local(21, 25), new Date(21 / 11 / 2018))));
        Room r3 = new Room("Quarto", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Temperatura"), new Local(21, 23), new Date(21 / 11 / 2018))));
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);

        boolean expectedResult = true;
        boolean actualResult = roomList.doesListOfRoomsContainRoomByName("Jardim");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfDoesListOfRoomsContainRoomByNameFalse() {
        RoomList roomList = new RoomList();
        Room r1 = new Room("Cozinha", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Temperatura"), new Local(21, 23), new Date(21 / 11 / 2018))));
        Room r2 = new Room("Jardim", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Rainfall"), new Local(21, 25), new Date(21 / 11 / 2018))));
        Room r3 = new Room("Quarto", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Temperatura"), new Local(21, 23), new Date(21 / 11 / 2018))));
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);

        boolean expectedResult = false;
        boolean actualResult = roomList.doesListOfRoomsContainRoomByName("Sala");

        assertEquals(expectedResult, actualResult);
    }
}
