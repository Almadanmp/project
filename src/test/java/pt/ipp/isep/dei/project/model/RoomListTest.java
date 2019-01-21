package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.device.DeviceList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * RoomList tests class.
 */

public class RoomListTest {

    @Test
    public void seeIfGetRoomByNameFromList() {
        DeviceList deviceList = new DeviceList();
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2, sensorList, deviceList);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Jardim", 1, 123, 2, 2, sensorList, deviceList);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2, sensorList, deviceList);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);

        Room expectedResult = r1;
        Room actualResult = roomList.getRoomByName("Cozinha");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetRoomByNameIfNull() {
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        RoomList roomList = new RoomList();
        Room r3 = new Room("Quarto", 1, 123, 2, 2, sensorList, deviceList);
        roomList.addRoom(r3);
        Room expectedResult = null;
        Room actualResult = roomList.getRoomByName("Cozinha");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfDoesListOfRoomsContainRoomByName() {
        DeviceList deviceList = new DeviceList();
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2, sensorList, deviceList);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Jardim", 1, 123, 2, 2, sensorList, deviceList);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2, sensorList, deviceList);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);

        boolean expectedResult = true;
        boolean actualResult = roomList.doesListOfRoomsContainRoomByName("Jardim");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfDoesListOfRoomsContainRoomByNameFalse() {
        DeviceList deviceList = new DeviceList();
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2, sensorList, deviceList);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Jardim", 1, 123, 2, 2, sensorList, deviceList);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2, sensorList, deviceList);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);

        boolean expectedResult = false;
        boolean actualResult = roomList.doesListOfRoomsContainRoomByName("Sala");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddRoomFails() {
        DeviceList deviceList = new DeviceList();
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2, sensorList, deviceList);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Cozinha", 1, 123, 2, 2, sensorList, deviceList);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2, sensorList, deviceList);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        boolean expectedResult = false;
        boolean actualResult = roomList.addRoom(r2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddRoomPasses() {
        DeviceList deviceList = new DeviceList();
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2, sensorList, deviceList);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Jardim", 1, 123, 2, 2, sensorList, deviceList);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2, sensorList, deviceList);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        boolean expectedResult = true;
        boolean actualResult = roomList.addRoom(r2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeifmatchRoomWorks() {
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        Room room = new Room("kitchen", 1, 1, 2, 2, sensorList, deviceList);
        Room room1 = new Room("sala", 1, 1, 2, 2, sensorList, deviceList);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        boolean result = roomList.matchRoom("kitchen");
        assertTrue(result);
    }

    @Test
    public void seeifmatchRoomWorksFalse() {
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        Room room = new Room("kitchen", 1, 1, 2, 2, sensorList, deviceList);
        Room room1 = new Room("sala", 1, 1, 2, 2, sensorList, deviceList);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        boolean result = roomList.matchRoom("cozinha");
        assertFalse(result);
    }


    @Test
    void seeIfRoomAreaIndexMatchByString() {
        //Arrange
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        Room room = new Room("kitchen", 1, 1, 2, 2, sensorList, deviceList);
        Room room1 = new Room("sala", 1, 1, 2, 2, sensorList, deviceList);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        //Act
        List<Integer> result = roomList.matchRoomIndexByString("sala");
        List<Integer> expectedResult = Collections.singletonList(roomList.getList().indexOf(room1));
        //Assert
        Assert.assertEquals(expectedResult, result);

    }

    @Test
    void seeIfPrintRoomElementsByIndex() {
        //Arrange
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        Room room = new Room("kitchen", 1, 1, 2, 2, sensorList, deviceList);
        Room room1 = new Room("sala", 1, 1, 2, 2, sensorList, deviceList);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);

        //Act
        String result = roomList.buildElementsByIndexString(list);
        String expectedResult = "1) sala, 1, 1.0, 2.0, 2.0.\n";

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsRoomList() {
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100), new SensorList());
        Room room = new Room("kitchen", 1, 1, 2, 2, sensorList, deviceList);
        Room room1 = new Room("sala", 1, 1, 2, 2, sensorList, deviceList);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, roomList);
        String expectedResult = "---------------\n" +
                "0) Designation: kitchen | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "1) Designation: sala | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        String result = roomList.buildRoomListString(house);
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintsInvalidList() {
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100), new SensorList());
        RoomList roomList = new RoomList();

        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, roomList);
        String expectedResult = "Invalid List - List is Empty\n";
        String result = roomList.buildRoomListString(house);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintInvalidRoomsWorks() {
        RoomList roomList = new RoomList();
        String result = roomList.buildRoomsString();
        Assert.assertEquals("Invalid List - List is Empty\n", result);
    }


    @Test
    public void ensureThatAObjectIsAInstanceOf() {
        RoomList roomList1 = new RoomList();
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2, sensorList, deviceList);
        Room room2 = new Room("room1", 19, 23456789, 2, 2, sensorList, deviceList);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        RoomList roomList2 = new RoomList();
        roomList2.addRoom(room1);
        roomList2.addRoom(room2);

        Boolean actualResult = roomList1.equals(roomList2);

        assertTrue(actualResult);
    }

    @Test
    public void ensureThatAObjectIsAInstanceOf2() {
        RoomList roomList1 = new RoomList();
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2, sensorList, deviceList);
        Room room2 = new Room("room1", 19, 23456789, 2, 2, sensorList, deviceList);
        roomList1.addRoom(room1);
        ;
        RoomList roomList2 = new RoomList();
        roomList2.addRoom(room2);

        Boolean actualResult = roomList1.equals(roomList2);

        assertTrue(actualResult);
    }

    @Test
    public void ensureThatAObjectIsAInstanceOf3() {
        RoomList roomList1 = new RoomList();
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2, sensorList, deviceList);
        roomList1.addRoom(room1);
        Boolean actualResult = roomList1.equals(roomList1);
        assertTrue(actualResult);
    }

    @Test
    public void ensureThatAObjectIsNotAInstanceOf() {
        RoomList roomList1 = new RoomList();
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2, sensorList, deviceList);
        Room room2 = new Room("room2", 19, 23456789, 2, 2, sensorList, deviceList);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        RoomList roomList2 = new RoomList();
        roomList2.addRoom(room1);

        Boolean actualResult = roomList1.equals(roomList2);

        assertFalse(actualResult);
    }

    @Test
    public void ensureThatAObjectIsNotAInstanceOf2() {
        RoomList roomList1 = new RoomList();
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2, sensorList, deviceList);
        Room room2 = new Room("room2", 19, 23456789, 2, 2, sensorList, deviceList);
        roomList1.addRoom(room1);
        Boolean actualResult = roomList1.equals(room2);
        assertFalse(actualResult);
    }

    @Test
    public void ensureThatAObjectIsNotAInstanceOf3() {
        RoomList roomList1 = new RoomList();
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2, sensorList, deviceList);
        roomList1.addRoom(room1);
        RoomList roomList2 = new RoomList();
        Boolean actualResult = roomList1.equals(room1);
        assertFalse(actualResult);
    }

    @Test
    public void hashCodeDummyTest() {
        RoomList roomList1 = new RoomList();
        SensorList sensorList = new SensorList();
        DeviceList deviceList = new DeviceList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2, sensorList, deviceList);
        roomList1.addRoom(room1);
        int expectedResult = 1;
        int actualResult = roomList1.hashCode();
        Assertions.assertEquals(expectedResult, actualResult);
    }


}
