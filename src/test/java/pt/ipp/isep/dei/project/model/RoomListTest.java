package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * RoomList tests class.
 */

class RoomListTest {

    private RoomList validRoomList;
    private RoomList validSecondaryRoomList;
    private Room validRoomKitchen;

    @BeforeEach
    void arrangeArtifacts() {
        validRoomList = new RoomList();
        validSecondaryRoomList = new RoomList();
        validRoomKitchen = new Room("Kitchen", 1, 4, 5, 3);
    }

    @Test
    void seeIfAddRoomFails() {
        // Arrange

        Date date = new GregorianCalendar(2018, Calendar.NOVEMBER, 21).getTime();
        SensorList sensorList = new SensorList(new Sensor("sensor", new TypeSensor("Temperature", "Celsius"), new Local(21, 23, 50), date));
        validRoomKitchen.setSensorList(sensorList);
        Room r2 = validRoomKitchen;
        r2.setSensorList(sensorList);
        Room r3 = new Room("Bedroom", 1, 2, 5, 3);
        r3.setSensorList(sensorList);
        validRoomList.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomList.add(r2);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddRoom() {
        // Arrange

        Date date = new GregorianCalendar(2018, Calendar.NOVEMBER, 21).getTime();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperature", "Celsius"), new Local(21, 23, 50),date));
        validRoomKitchen.setSensorList(sensorList);
        Room r2 = new Room("Garden", 1, 2, 6, 3);
        r2.setSensorList(sensorList);
        Room r3 = new Room("Bedroom", 1, 3, 4, 2);
        r3.setSensorList(sensorList);
        validRoomList.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomList.add(r2);

        // Assert

        Assertions.assertTrue(actualResult);
    }

    @Test
    void seeIfInvalidRoomsPrints() {
        // Act

        String result = validRoomList.buildString();

        // Assert

        Assert.assertEquals("Invalid List - List is Empty\n", result);
    }


    @Test
    void seeIfEqualsSameContentLists() {
        // Arrange

        Room room2 = new Room("Dinning room", 3, 2, 4, 3);
        validRoomList.add(validRoomKitchen);
        validRoomList.add(room2);
        validSecondaryRoomList.add(validRoomKitchen);
        validSecondaryRoomList.add(room2);

        // Act

        boolean actualResult = validRoomList.equals(validSecondaryRoomList);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsSameObject() {
        // Arrange

        validRoomList.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomList.equals(validRoomList); // Necessary for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsDifferentListContents() {
        // Arrange

        Room room2 = new Room("Balcony", 4, 2, 4, 3);
        validRoomList.add(validRoomKitchen);
        validRoomList.add(room2);
        validSecondaryRoomList.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomList.equals(validSecondaryRoomList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsDifferentObjectTypes() {
        // Arrange

        Room room2 = new Room("Balcony", 3, 2, 4, 3);
        validRoomList.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomList.equals(room2); // Necessary for Sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfIsEmptyBothConditions() {
        //Arrange

        RoomList roomList3 = new RoomList(); //Has two rooms.

        Room room2 = new Room("Balcony", 2, 21, 21, 4);
        validSecondaryRoomList.add(validRoomKitchen);
        roomList3.add(validRoomKitchen);
        roomList3.add(room2);

        // Act

        boolean actualResult1 = validRoomList.isEmpty();
        boolean actualResult2 = validSecondaryRoomList.isEmpty();
        boolean actualResult3 = roomList3.isEmpty();

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        validRoomList.add(validRoomKitchen);
        int expectedResult = 1;

        // Act

        int actualResult = validRoomList.hashCode();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }
}