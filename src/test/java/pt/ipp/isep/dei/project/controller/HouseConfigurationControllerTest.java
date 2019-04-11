package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * HouseConfigurationController tests class.
 */
@ExtendWith(MockitoExtension.class)
class HouseConfigurationControllerTest {

    // Common artifacts for testing in this class.

    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private HouseConfigurationController controller = new HouseConfigurationController();
    private House validHouse;

    @BeforeEach
    void arrangeArtifacts() {
        List<String> deviceTypeList = new ArrayList<>();
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal");
        validHouse = new House("ISEP", address,
                new Local(20, 20, 20), 60, 180,
                deviceTypeList);
        validHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));
        deviceTypeList.add(PATH_TO_FRIDGE);
    }


    //USER STORY 105


    @Test
    void seeIfGetHouseName() {
        //Act

        String actualResult = controller.getHouseId(validHouse);

        // Assert

        assertEquals("ISEP", actualResult);
    }


    // US108
//
//    @Test
//    void seeIfPrintsRoomList() {
//        // Arrange
//
//        Room roomOne = new Room("Kitchen", "Equipped Kitchen", 1, 15, 20, 10,"Room1","Grid1");
//        Room roomTwo = new Room("LivingRoom", "1st Floor Living Room", 1, 40, 40, 10,"Room1","Grid1");
//        RoomService roomService = new RoomService();
//        roomService.add(roomOne);
//        roomService.add(roomTwo);
//        validHouse.setRoomService(roomService);
//        String expectedResult = "---------------\n" +
//                "Kitchen) Designation: Kitchen | Description: Equipped Kitchen | House Floor: 1 | Width: 15.0 | Length: 20.0 | Height: 10.0\n" +
//                "LivingRoom) Designation: LivingRoom | Description: 1st Floor Living Room | House Floor: 1 | Width: 40.0 | Length: 40.0 | Height: 10.0\n" +
//                "---------------\n";
//
//        // Act
//
//        String result = controller.buildRoomsString(validHouse);
//
//        // Assert
//
//        assertEquals(expectedResult, result);
//    }

//    @Test
//    void createsRoom() {
//        // Act
//
//        Room actualResult1 = controller.createNewRoom(validHouse, "Kitchen", "Not equipped Kitchen", 1, 10, 15, 10,"Room1","Grid1");
//        Room actualResult2 = controller.createNewRoom(validHouse, "Room", "Double Bedroom", 1, 10, 15, 10,"Room1","Grid1");
//        Room actualResult3 = controller.createNewRoom(validHouse, "Kitchen", "Fully Equipped Kitchen", 1, 10, 15, 10,"Room1","Grid1");
//
//        // Assert
//
//        assertTrue(actualResult1 instanceof Room);
//        assertTrue(actualResult2 instanceof Room);
//        assertTrue(actualResult3 instanceof Room);
//    }

    @Test
    void addsRoom() {
        //Arrange
        Room room1 = new Room("Kitchen", "Not equipped Kitchen", 1, 10, 15, 10,"Room1","Grid1");
        Room room2 = new Room("Room", "Double Bedroom", 1, 10, 15, 10,"Room1","Grid1");
        Room room3 = new Room("Kitchen", "Fully Equipped Kitchen", 1, 10, 15, 10,"Room1","Grid1");

        // Act
        boolean actualResult1 = controller.addRoomToHouse(validHouse, room1);
        boolean actualResult2 = controller.addRoomToHouse(validHouse, room2);
        boolean actualResult3 = controller.addRoomToHouse(validHouse, room3);

        // Assert
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfSetHouseAddress() {
        //Act

        controller.setHouseAddress("Rua do ISEP", "431", "4400", "City", "Portugal", validHouse);

        // Assert

        assertEquals(validHouse.getAddress(), new Address("Rua do ISEP", "431", "4400", "City", "Portugal"));
    }

    @Test
    void seeIfSetHouseLocal() {
        //Act

        controller.setHouseLocal(10, 51, 2, validHouse);

        // Assert

        assertEquals(validHouse.getLocation(), new Local(10, 51, 2));
    }


    @Test
    void seeIfSetAndGetHouseMotherAreaWorks() {
        //Arrange
        controller.setHouseMotherArea(validHouse, new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100)));
        GeographicArea expected = new GeographicArea("Porto", new AreaType("Cidade"),
                2, 3, new Local(4, 4, 100));
        //Act
        GeographicArea actualResult = validHouse.getMotherArea();
        //Assert
        assertEquals(expected, actualResult);
    }

    @Test
    void seeIfIsMotherAreaNullBothConditions() {
        // Act
        boolean actualResult1 = validHouse.isMotherAreaNull();
        controller.setHouseMotherArea(validHouse, null);
        boolean actualResult2 = validHouse.isMotherAreaNull();
        // Assert
        assertFalse(actualResult1);
        assertTrue(actualResult2);
    }

}
