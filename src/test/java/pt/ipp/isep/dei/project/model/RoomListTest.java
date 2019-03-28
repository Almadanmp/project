package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
    void seeIfGetByIndexWorks() {
        //Arrange
        Room room = new Room("room", 2, 20, 20, 4);
        validRoomList.add(room);

        //Act

        Room actualResult1 = validRoomList.get(0);
        Room actualResult2 = validRoomList.get(1);

        //Assert

        assertEquals(validRoomKitchen, actualResult1);
        assertEquals(room, actualResult2);
    }

    @Test
    void getByIndexEmptyRoomList() {
        //Arrange

        RoomList emptyRoomList = new RoomList();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyRoomList.get(0));

        //Assert

        assertEquals("The room list is empty.", exception.getMessage());
    }

    @Test
    void ListSize() {
        //Arrange

        RoomList emptyRoomList = new RoomList();

        //Act

        int actualResult1 = emptyRoomList.size();

        //Assert Empty List

        Assertions.assertEquals(0, actualResult1);

        //Act

        int actualResult2 = validRoomList.size();

        //Assert One Grid

        Assertions.assertEquals(1, actualResult2);
    }

    @Test
    void getElementsAsArray() {
        //Arrange

        Room[] expectedResult1 = new Room[0];
        Room[] expectedResult2 = new Room[1];
        Room[] expectedResult3 = new Room[2];

        RoomList validRoomList2 = new RoomList();
        validRoomList2.add(validRoomKitchen);
        validRoomList2.add(new Room("room", 2, 20, 20, 3));

        expectedResult2[0] = validRoomKitchen;
        expectedResult3[0] = validRoomKitchen;
        expectedResult3[1] = new Room("room", 2, 20, 20, 3);

        //Act

        Room[] actualResult1 = emptyRoomList.getElementsAsArray();
        Room[] actualResult2 = validRoomList.getElementsAsArray();
        Room[] actualResult3 = validRoomList2.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfCreateRoomWorks() {
        //Arrange

        Room room = new Room("kitchen", 0, 15, 10, 2);
        Room roomExpected = new Room("kitchen", 0, 15, 10, 2);

        //Act

        Room roomActual1 = validRoomList.createRoom("kitchen", 0, 15, 10, 2);

        //Assert

        assertEquals(roomExpected, roomActual1);

        //Arrange to check if room is created when it already exists in list

        validRoomList.add(room);

        //Act

        Room roomActual2 = validRoomList.createRoom("kitchen", 0, 15, 10, 2);

        //Assert
        assertEquals(roomExpected, roomActual2);
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