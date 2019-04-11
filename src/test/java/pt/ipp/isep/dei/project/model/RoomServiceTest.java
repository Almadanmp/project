package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.repository.RoomRepository;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RoomList tests class.
 */
@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    private Room validRoom;

    @Mock
    private RoomRepository roomRepository;
    private RoomService validRoomService;
    private RoomService emptyRoomService;


    @BeforeEach
    void arrangeArtifacts() {

        validRoomService = new RoomService(roomRepository);
        emptyRoomService = new RoomService(roomRepository);
        validRoom = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1", "Grid1");
        validRoomService.add(validRoom);
    }


    @Test
    void seeIfAddRoomWorks() {

        // Assert
        assertTrue(emptyRoomService.add(validRoom));
        assertFalse(validRoomService.add(validRoom));
    }

//    @Test
//    void seeIfAddPersistenceRoomWorks() {
//        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3);
//
//        Mockito.when(roomRepository.findByRoomName(room.getName())).thenReturn(room);
////Assert
//        assertTrue(validRoomService.addPersistence(room));
//    }

    @Test
    void seeIfBuildRoomListStringWorksEmptyList() {
        // Act

        String expectedResult = "Invalid List - List is Empty\n";

        // Assert

        assertEquals(expectedResult, emptyRoomService.buildString());
    }


//    @Test
//    void seeIfEqualsWorksSameContent() {
//        // Arrange
//
//        validRoomService.add(validRoom);
//        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3);
//
//
//        // Act
//
//        boolean actualResult = validRoom.equals(room);
//
//        // Assert
//
//        assertTrue(actualResult);
//    }

    @Test
    void seeIfEqualsWorksSameObject() {
        // Arrange

        validRoomService.add(validRoom);

        // Act

        boolean actualResult = validRoom.equals(validRoom); // Needed for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetDeviceList() {
        DeviceList deviceList = new DeviceList();
        validRoom.setDeviceList(deviceList);

        assertEquals(deviceList, validRoom.getDeviceList());
    }

    @Test
    void seeIfEqualsDifferentListContents() {
        // Arrange

        Room testRoom = new Room("Balcony", "4th Floor Balcony", 4, 2, 4, 3, "Room1", "Grid1");
        validRoomService.add(testRoom);
        emptyRoomService.add(validRoom);

        // Act

        boolean actualResult = validRoom.equals(emptyRoomService);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsDifferentObjectTypes() {
        // Arrange

        Room room2 = new Room("Balcony", "3rd Floor Balcony", 3, 2, 4, 3, "Room1", "Grid1");
        validRoomService.add(validRoom);

        // Act

        boolean actualResult = validRoom.equals(room2); // Necessary for Sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

//    @Test
//    void seeIfIsEmptyWorks() {
//        //Arrange
//
//        RoomService roomService3 = new RoomService(roomRepository); //Has two rooms.
//
//        Room room2 = new Room("Balcony", "2nd Floor Balcony", 2, 21, 21, 4, "Room1", "Grid1");
//        roomService3.add(validRoom);
//        roomService3.add(room2);
//
//        // Act
//
//        boolean actualResult1 = validRoomService.isEmpty();
//        boolean actualResult2 = emptyRoomService.isEmptyDB();
//        boolean actualResult3 = roomService3.isEmptyDB();
//
//        // Assert
//
//        assertFalse(actualResult1);
//        assertTrue(actualResult2);
//        assertFalse(actualResult3);
//    }

    @Test
    void seeIfGetByIndexWorks() {
        //Arrange
        Room room = new Room("room", "Double Bedroom", 2, 20, 20, 4, "Room1", "Grid1");
        validRoomService.add(room);

        //Act

        Room actualResult1 = validRoomService.get(0);
        Room actualResult2 = validRoomService.get(1);

        //Assert

        assertEquals(validRoom, actualResult1);
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

//    @Test
//    void ListSize() {
//        //Arrange
//
//        RoomService emptyRoomService = new RoomService();
//
//        //Act
//
//        int actualResult1 = emptyRoomService.sizeDB();
//
//        //Assert Empty List
//
//        assertEquals(0, actualResult1);
//
//        //Act
//        Room room = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3);
//        RoomService rooms = new RoomService();
//        rooms.add(room);
//
//
//        int actualResult2 = rooms.sizeDB();
//
//        //Assert One Grid
//
//        assertEquals(1, actualResult2);
//    }

    @Test
    void getElementsAsArray() {
        //Arrange

        Room[] expectedResult1 = new Room[0];
        Room[] expectedResult2 = new Room[1];
        Room[] expectedResult3 = new Room[2];

        RoomService validRoomService2 = new RoomService();
        validRoomService2.add(validRoom);
        validRoomService2.add(new Room("room", "Single Bedroom", 2, 20, 20, 3, "Room1", "Grid1"));

        expectedResult2[0] = validRoom;
        expectedResult3[0] = validRoom;
        expectedResult3[1] = new Room("room", "Single Bedroom", 2, 20, 20, 3, "Room1", "Grid1");

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

        Room room = new Room("kitchen", "Ground Floor Kitchen", 0, 15, 10, 2, "Room1", "Grid1");
        Room roomExpected = new Room("kitchen", "Ground Floor Kitchen", 0, 15, 10, 2, "Room1", "Grid1");

        //Act

        Room roomActual1 = validRoomService.createRoom("kitchen", "Ground Floor Kitchen", 0, 15, 10, 2, "Room1", "Grid1");

        //Assert

        assertEquals(roomExpected, roomActual1);

        //Arrange to check if room is created when it already exists in list

        validRoomService.add(room);

        //Act

        Room roomActual2 = validRoomService.createRoom("kitchen", "Ground Floor Kitchen", 0, 15, 10, 2, "Room1", "Grid1");

        //Assert
        assertEquals(roomExpected, roomActual2);
    }


    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validRoom.hashCode();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }
}