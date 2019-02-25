package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * RoomList tests class.
 */

class RoomListTest {

    // Common artifacts for testing in this class.
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";


    @Test
    void seeIfAddRoomFails() {
        RoomList roomList = new RoomList();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = validSdf.parse("21/11/2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50), date));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2);
        r1.setSensorList(sensorList);
        Room r2 = new Room("Cozinha", 1, 123, 2, 2);
        r2.setSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2);
        r3.setSensorList(sensorList);
        roomList.addRoom(r1);
        boolean expectedResult = false;
        boolean actualResult = roomList.addRoom(r2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddRoomPasses() {
        RoomList roomList = new RoomList();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = validSdf.parse("21/11/2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 50),date));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2);
        r1.setSensorList(sensorList);
        Room r2 = new Room("Jardim", 1, 123, 2, 2);
        r2.setSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2);
        r3.setSensorList(sensorList);
        roomList.addRoom(r1);
        boolean actualResult = roomList.addRoom(r2);
        Assertions.assertTrue(actualResult);
    }

    @Test
    void seeIfPrintsRoomList() {
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 6, 5), ga, 60, 180, deviceTypeString);
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
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("casa de praia", address, new Local(4, 6, 5), ga, 60, 180, deviceTypeString);
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
    void isEmpty() {
        //Arrange
        RoomList roomList1 = new RoomList(); //EMPTY LIST
        RoomList roomList2 = new RoomList(); //ONE ROOM
        RoomList roomList3 = new RoomList(); //TWO ROOMS

        Room room1 = new Room("room1", 1, 22, 22, 4);
        Room room2 = new Room("room2", 2, 21, 21, 4);
        roomList2.addRoom(room1);
        roomList3.addRoom(room1);
        roomList3.addRoom(room2);
        //Act
        boolean actualResult1 = roomList1.isEmpty();
        boolean actualResult2 = roomList2.isEmpty();
        boolean actualResult3 = roomList3.isEmpty();
        //Assert
        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
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
