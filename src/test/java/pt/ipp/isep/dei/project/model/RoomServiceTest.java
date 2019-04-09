package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * RoomList tests class.
 */

class RoomServiceTest {

    private RoomService validRoomService;
    private RoomService emptyRoomService;
    private Room validRoomKitchen;

    @BeforeEach
    void arrangeArtifacts() {
        validRoomService = new RoomService();
        emptyRoomService = new RoomService();
        validRoomKitchen = new Room("Kitchen","1st Floor Kitchen", 1, 4, 5, 3);
        validRoomService.add(validRoomKitchen);
    }

    @Test
    void seeIfAddRoomFailsDuplicateRoom() {
        // Arrange

        validRoomService.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomService.add(validRoomKitchen);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddRoomWorks() {
        // Act

        boolean actualResult = emptyRoomService.add(validRoomKitchen);

        // Assert

        Assertions.assertTrue(actualResult);
    }

    @Test
    void seeIfBuildRoomListStringWorksEmptyList() {
        // Act

        String result = emptyRoomService.buildString();

        // Assert

        assertEquals("Invalid List - List is Empty\n", result);
    }


    @Test
    void seeIfEqualsWorksSameContent() {
        // Arrange

        emptyRoomService.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomService.equals(emptyRoomService);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksSameObject() {
        // Arrange

        validRoomService.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomService.equals(validRoomService); // Needed for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsDifferentListContents() {
        // Arrange

        Room testRoom = new Room("Balcony","4th Floor Balcony", 4, 2, 4, 3);
        validRoomService.add(testRoom);
        emptyRoomService.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomService.equals(emptyRoomService);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsDifferentObjectTypes() {
        // Arrange

        Room room2 = new Room("Balcony","3rd Floor Balcony", 3, 2, 4, 3);
        validRoomService.add(validRoomKitchen);

        // Act

        boolean actualResult = validRoomService.equals(room2); // Necessary for Sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfIsEmptyWorks() {
        //Arrange

        RoomService roomService3 = new RoomService(); //Has two rooms.

        Room room2 = new Room("Balcony","2nd Floor Balcony", 2, 21, 21, 4);
        roomService3.add(validRoomKitchen);
        roomService3.add(room2);

        // Act

        boolean actualResult1 = validRoomService.isEmpty();
        boolean actualResult2 = emptyRoomService.isEmpty();
        boolean actualResult3 = roomService3.isEmpty();

        // Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfGetByIndexWorks() {
        //Arrange
        Room room = new Room("room","Double Bedroom", 2, 20, 20, 4);
        validRoomService.add(room);

        //Act

        Room actualResult1 = validRoomService.get(0);
        Room actualResult2 = validRoomService.get(1);

        //Assert

        assertEquals(validRoomKitchen, actualResult1);
        assertEquals(room, actualResult2);
    }

    @Test
    void getByIndexEmptyRoomList() {
        //Arrange

        RoomService emptyRoomService = new RoomService();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyRoomService.get(0));

        //Assert

        assertEquals("The room list is empty.", exception.getMessage());
    }

    @Test
    void ListSize() {
        //Arrange

        RoomService emptyRoomService = new RoomService();

        //Act

        int actualResult1 = emptyRoomService.size();

        //Assert Empty List

        Assertions.assertEquals(0, actualResult1);

        //Act

        int actualResult2 = validRoomService.size();

        //Assert One Grid

        Assertions.assertEquals(1, actualResult2);
    }

    @Test
    void getElementsAsArray() {
        //Arrange

        Room[] expectedResult1 = new Room[0];
        Room[] expectedResult2 = new Room[1];
        Room[] expectedResult3 = new Room[2];

        RoomService validRoomService2 = new RoomService();
        validRoomService2.add(validRoomKitchen);
        validRoomService2.add(new Room("room","Single Bedroom", 2, 20, 20, 3));

        expectedResult2[0] = validRoomKitchen;
        expectedResult3[0] = validRoomKitchen;
        expectedResult3[1] = new Room("room","Single Bedroom", 2, 20, 20, 3);

        //Act

        Room[] actualResult1 = emptyRoomService.getElementsAsArray();
        Room[] actualResult2 = validRoomService.getElementsAsArray();
        Room[] actualResult3 = validRoomService2.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfCreateRoomWorks() {
        //Arrange

        Room room = new Room("kitchen","Ground Floor Kitchen", 0, 15, 10, 2);
        Room roomExpected = new Room("kitchen","Ground Floor Kitchen", 0, 15, 10, 2);

        //Act

        Room roomActual1 = validRoomService.createRoom("kitchen", "Ground Floor Kitchen",0, 15, 10, 2);

        //Assert

        assertEquals(roomExpected, roomActual1);

        //Arrange to check if room is created when it already exists in list

        validRoomService.add(room);

        //Act

        Room roomActual2 = validRoomService.createRoom("kitchen","Ground Floor Kitchen", 0, 15, 10, 2);

        //Assert
        assertEquals(roomExpected, roomActual2);
    }




    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validRoomService.hashCode();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }
}