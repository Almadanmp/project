package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertEquals;

/**
 * HouseConfigurationController tests class.
 */

class HouseConfigurationControllerTest {

    // Common artifacts for testing in this class.

    public static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";
    private HouseConfigurationController controller = new HouseConfigurationController();
    private House validHouse;

    @BeforeEach
    void arrangeArtifacts() {
        List<String> deviceTypeList = new ArrayList<>();
        validHouse = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072", "Porto",
                new Local(20, 20, 20), new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)), 60, 180,
                deviceTypeList);
        deviceTypeList.add(PATH_TO_FRIDGE);
    }


    @Test
    void seeIfSetHouseAddress() {
        // Arrange

        controller.setHouseAddress("Rua de Teste", validHouse);

        // Act

        String actualResult = validHouse.getStreet();

        // Assert

        assertEquals("Rua de Teste", actualResult);
    }

    @Test
    void seeIfSetHouseZipCode() {
        // Arrange

        controller.setHouseZIPCode("4400", validHouse);

        // Act

        String actualResult = validHouse.getZip();

        // Assert

        assertEquals("4400", actualResult);
    }

    @Test
    void seeIfSetHouseLocal() {
        // Arrange

        Local local = new Local(34, 56, 5);
        controller.setHouseLocal(34, 56, 5, validHouse);

        // Act

        Local actualResult = validHouse.getLocation();

        // Assert

        assertEquals(local, actualResult);
    }

    // US105


    @Test
    void seeIfGetHouseName() {
        // Act

        String actualResult = controller.getHouseName(validHouse);

        // Assert

        assertEquals("ISEP", actualResult);
    }


    // US108

    @Test
    void seeIfPrintsRoomList() {
        // Arrange

        Room roomOne = new Room("Kitchen", 1, 15, 20, 10);
        Room roomTwo = new Room("LivingRoom", 1, 40, 40, 10);
        RoomList roomList = new RoomList();
        roomList.addRoom(roomOne);
        roomList.addRoom(roomTwo);
        validHouse.setRoomList(roomList);
        String expectedResult = "---------------\n" +
                "0) Designation: Kitchen | House Floor: 1 | Width: 15.0 | Length: 20.0 | Height: 10.0\n" +
                "1) Designation: LivingRoom | House Floor: 1 | Width: 40.0 | Length: 40.0 | Height: 10.0\n" +
                "---------------\n";

        // Act

        String result = controller.buildRoomsString(validHouse);

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void createsRoom() {
        // Act

        boolean expectedResult1 = controller.createNewRoom(validHouse, "Kitchen", 1, 10, 15, 10);
        boolean expectedResult2 = controller.createNewRoom(validHouse, "Room", 1, 10, 15, 10);
        boolean expectedResult3 = controller.createNewRoom(validHouse, "Kitchen", 1, 10, 15, 10);

        // Assert

        assertTrue(expectedResult1);
        assertTrue(expectedResult2);
        assertFalse(expectedResult3);
    }
}
