package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

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
    public void seeIfGetRoomByNameIfNull() {
        RoomList roomList = new RoomList();
        Room expectedResult = null;
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

    @Test
    public void seeIfAddRoomFails(){
            RoomList roomList = new RoomList();
        Room r1 = new Room("Cozinha", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Temperatura"), new Local(21, 23), new Date(21 / 11 / 2018))));
        Room r2 = new Room("Cozinha", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Temperatura"), new Local(21, 23), new Date(21 / 11 / 2018))));
        roomList.addRoom(r1);
        boolean expectedResult = false;
        boolean actualResult = roomList.addRoom(r2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfAddRoomPasses(){
        RoomList roomList = new RoomList();
        Room r1 = new Room("Cozinha", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Temperatura"), new Local(21, 23), new Date(21 / 11 / 2018))));
        Room r2 = new Room("Varanda", 1, 123, new SensorList(new Sensor("s1", new TypeSensor("Temperatura"), new Local(21, 23), new Date(21 / 11 / 2018))));
        roomList.addRoom(r1);
        boolean expectedResult = true;
        boolean actualResult = roomList.addRoom(r2);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void seeIfPrintRoomListWork() {
        //Arrange ------------------------
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ----------------------------
        String result = roomList1.printRoomList(house1);
        String expectedResult = "---------------\n" +
                "0) Designation: room1 | House Floor: 19 | Dimensions: 2.3456789E7\n" +
                "1) Designation: kitchen | House Floor: 8 | Dimensions: 2.0\n" +
                "---------------\n";
        //Assert -------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintRoomListWorksForInvalid() {
        //Arrange -----------------------------------
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("kitchen", 8, 2);
        //Act ---------------------------------------
        String result = roomList1.printRoomList(house1);
        String expectedResult = "Invalid List - List is Empty\n";
        //Assert ------------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeifmatchRoomWorks(){
        Room room = new Room("kitchen",1,1);
        Room room1 = new Room("sala",1,1);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        boolean result = roomList.matchRoom("kitchen");
        assertTrue(result);
    }

    @Test
    public void seeifmatchRoomWorksFalse(){
        Room room = new Room("kitchen",1,1);
        Room room1 = new Room("sala",1,1);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        boolean result = roomList.matchRoom("cozinha");
        assertFalse(result);
    }
    @Test
    public void seeIfPrintRoomstWork() {
        //Arrange ------------------------
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ----------------------------
        String result = roomList1.printRooms();
        String expectedResult = "---------------\n" +
                "0) Designation: room1 | House Floor: 19 | Dimensions: 2.3456789E7\n" +
                "1) Designation: kitchen | House Floor: 8 | Dimensions: 2.0\n" +
                "---------------\n";
        //Assert -------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintRoomsPrintsInvalidList() {
        //Arrange ------------------------
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
             //Act ----------------------------
        String result = roomList1.printRooms();
        String expectedResult = "Invalid List - List is Empty\n";
        //Assert -------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureThatAObjectIsAInstanceOf() {
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("room1", 19, 23456789);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        RoomList roomList2 = new RoomList();
        roomList2.addRoom(room1);
        roomList2.addRoom(room2);

        Boolean actualResult = roomList1.equals(roomList2);

        assertTrue(actualResult);
    }

    @Test
    public void ensureThatAObjectIsNotAInstanceOf() {
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("room2", 19, 23456789);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        RoomList roomList2 = new RoomList();
        roomList2.addRoom(room1);

        Boolean actualResult = roomList1.equals(roomList2);

        assertFalse(actualResult);
    }

    @Test
    public void hashCodeDummyTest(){
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789);
        roomList1.addRoom(room1);
        int expectedResult = 1;
        int actualResult = roomList1.hashCode();
        Assertions.assertEquals(expectedResult,actualResult);
    }


}
