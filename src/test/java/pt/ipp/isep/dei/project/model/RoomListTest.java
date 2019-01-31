package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

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

class RoomListTest {

    @Test
    void seeIfGetRoomByNameFromList() {
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Jardim", 1, 123, 2, 2);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);

        Room actualResult = roomList.getRoomByName("Cozinha");

        assertEquals(r1, actualResult);
    }

    @Test
    void seeIfGetRoomByNameIfNull() {
        RoomList roomList = new RoomList();
        Room r3 = new Room("Quarto", 1, 123, 2, 2);
        roomList.addRoom(r3);
        Room expectedResult = null;
        Room actualResult = roomList.getRoomByName("Cozinha");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDoesListOfRoomsContainRoomByName() {
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Jardim", 1, 123, 2, 2);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);

        boolean expectedResult = true;
        boolean actualResult = roomList.doesListOfRoomsContainRoomByName("Jardim");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDoesListOfRoomsContainRoomByNameFalse() {
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Jardim", 1, 123, 2, 2);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);

        boolean expectedResult = false;
        boolean actualResult = roomList.doesListOfRoomsContainRoomByName("Sala");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRoomFails() {
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Cozinha", 1, 123, 2, 2);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        boolean expectedResult = false;
        boolean actualResult = roomList.addRoom(r2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRoomPasses() {
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Jardim", 1, 123, 2, 2);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        boolean expectedResult = true;
        boolean actualResult = roomList.addRoom(r2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeifmatchRoomWorks() {
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        boolean result = roomList.matchRoom("kitchen");
        assertTrue(result);
    }

    @Test
    void seeifmatchRoomWorksFalse() {
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        boolean result = roomList.matchRoom("cozinha");
        assertFalse(result);
    }


    @Test
    void seeIfRoomAreaIndexMatchByString() {
        //Arrange

        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
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
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
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
    void seeIfBuildRoomListString() {
        //Arrange
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, 60, 180);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);

        //Act
        String result = roomList.buildRoomListString(house);
        String expectedResult = "---------------\n" +
                "0) Designation: kitchen | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "1) Designation: sala | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfBuildRoomListStringInvalid() {
        //Arrange
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, 60, 180);
        RoomList roomList = new RoomList();

        //Act
        String result = roomList.buildRoomListString(house);
        String expectedResult = "Invalid List - List is Empty\n";
        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsRoomList() {
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, 60, 180);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);

        String expectedResult = "---------------\n" +
                "0) Designation: kitchen | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "1) Designation: sala | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        String result = house.buildRoomListString();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsInvalidList() {
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));

        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, 60, 180);
        String expectedResult = "Invalid List - List is Empty\n";
        String result = house.buildRoomListString();
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintInvalidRoomsWorks() {
        RoomList roomList = new RoomList();
        String result = roomList.buildRoomsString();
        Assert.assertEquals("Invalid List - List is Empty\n", result);
    }


    @Test
    void ensureThatAObjectIsAInstanceOf() {
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        Room room2 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        RoomList roomList2 = new RoomList();
        roomList2.addRoom(room1);
        roomList2.addRoom(room2);

        boolean actualResult = roomList1.equals(roomList2);

        assertTrue(actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf2() {
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        Room room2 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        RoomList roomList2 = new RoomList();
        roomList2.addRoom(room2);

        boolean actualResult = roomList1.equals(roomList2);

        assertTrue(actualResult);
    }

    @Test
    void ensureThatAObjectIsAInstanceOf3() {
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        boolean actualResult = roomList1.equals(roomList1);
        assertTrue(actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOf() {
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        Room room2 = new Room("room2", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        RoomList roomList2 = new RoomList();
        roomList2.addRoom(room1);

        boolean actualResult = roomList1.equals(roomList2);

        assertFalse(actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOf2() {
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        Room room2 = new Room("room2", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        boolean actualResult = roomList1.equals(room2);
        assertFalse(actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOf3() {
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        boolean actualResult = roomList1.equals(room1);
        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        roomList1.addRoom(room1);
        int expectedResult = 1;
        int actualResult = roomList1.hashCode();
        Assertions.assertEquals(expectedResult, actualResult);
    }


}
