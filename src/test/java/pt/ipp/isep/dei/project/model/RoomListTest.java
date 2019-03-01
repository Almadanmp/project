package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * RoomList tests class.
 */

class RoomListTest {

    private RoomList validRoomList;
    private RoomList emptyRoomList;
    private Room validRoomKitchen;

    @BeforeEach
    void arrangeArtifacts() {
        validRoomList = new RoomList();
        emptyRoomList = new RoomList();
        validRoomKitchen = new Room("Kitchen", 1, 4, 5, 3);
        validRoomList.add(validRoomKitchen);
    }

    @Test
    void seeIfAddRoomFailsDuplicateRoom() {
        // Arrange

        validRoomList.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomList.add(validRoomKitchen);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddRoomWorks() {
        // Act

        boolean actualResult = emptyRoomList.add(validRoomKitchen);

        // Assert

        Assertions.assertTrue(actualResult);
    }

    @Test
    void seeIfBuildRoomListStringWorksEmptyList() {
        // Act

        String result = emptyRoomList.buildString();

        // Assert

        Assert.assertEquals("Invalid List - List is Empty\n", result);
    }


    @Test
    void seeIfEqualsWorksSameContent() {
        // Arrange

        emptyRoomList.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomList.equals(emptyRoomList);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksSameObject() {
        // Arrange

        validRoomList.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomList.equals(validRoomList); // Needed for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsDifferentListContents() {
        // Arrange

        Room testRoom = new Room("Balcony", 4, 2, 4, 3);
        validRoomList.add(testRoom);
        emptyRoomList.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomList.equals(emptyRoomList);

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
    void seeIfIsEmptyWorks() {
        //Arrange

        RoomList roomList3 = new RoomList(); //Has two rooms.

        Room room2 = new Room("Balcony", 2, 21, 21, 4);
        roomList3.add(validRoomKitchen);
        roomList3.add(room2);

        // Act

        boolean actualResult1 = validRoomList.isEmpty();
        boolean actualResult2 = emptyRoomList.isEmpty();
        boolean actualResult3 = roomList3.isEmpty();

        // Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validRoomList.hashCode();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }
}