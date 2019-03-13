package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.model.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * HouseConfigurationController tests class.
 */

class HouseConfigurationControllerTest {

    // Common artifacts for testing in this class.

    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";
    private HouseConfigurationController controller = new HouseConfigurationController();
    private House validHouse;

    @BeforeEach
    void arrangeArtifacts() {
        List<String> deviceTypeList = new ArrayList<>();
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "4200-072", "Porto");
        validHouse = new House("ISEP", address,
                new Local(20, 20, 20),60, 180,
                deviceTypeList);
        validHouse.setMotherArea( new GeographicArea("Porto", new TypeArea("Cidade"),
                2, 3, new Local(4, 4, 100)));
        deviceTypeList.add(PATH_TO_FRIDGE);
    }


    //USER STORY 105


    @Test
    void seeIfGetHouseName() {
        //Act

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
        roomList.add(roomOne);
        roomList.add(roomTwo);
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

        Room actualResult1 = controller.createNewRoom(validHouse, "Kitchen", 1, 10, 15, 10);
        Room actualResult2 = controller.createNewRoom(validHouse, "Room", 1, 10, 15, 10);
        Room actualResult3 = controller.createNewRoom(validHouse, "Kitchen", 1, 10, 15, 10);

        // Assert

        assertTrue(actualResult1 instanceof Room);
        assertTrue(actualResult2 instanceof Room);
        assertTrue(actualResult3 instanceof Room); // TODO Cárina make tests fit test document parameters.
    }

    @Test
    void addsRoom() {
        //Arrange
        Room room1 = new Room("Kitchen", 1, 10, 15, 10);
        Room room2 = new Room("Room", 1, 10, 15, 10);
        Room room3 = new Room("Kitchen", 1, 10, 15, 10);

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

        controller.setHouseAddress("Rua do ISEP","4400","City",validHouse);

        // Assert

        assertEquals(validHouse.getAddress(),new Address("Rua do ISEP","4400","City"));
    }

    @Test
    void seeIfSetHouseLocal() {
        //Act

        controller.setHouseLocal(10,51,2,validHouse);

        // Assert

        assertEquals(validHouse.getLocation(),new Local(10,51,2));
    }

    @Test
    void seeIfAddGeoAreasToListWorks(){
        // Arrange

        GeographicAreaDTO[] arrayToUse = new GeographicAreaDTO[2];
        GeographicAreaList result = new GeographicAreaList();

        // First Area

        GeographicAreaDTO firstArea = new GeographicAreaDTO();
        firstArea.setId("ISEP");
        firstArea.setDescription("Campus do ISEP");
        firstArea.setTypeArea("urban area");
        firstArea.setWidth(0.261);
        firstArea.setLength(0.249);
        firstArea.setLatitudeGeoAreaDTO(41.178553);
        firstArea.setLongitudeGeoAreaDTO(-8.608035);
        firstArea.setAltitudeGeoAreaDTO(111);

        // Second Area

        GeographicAreaDTO secondArea = new GeographicAreaDTO();
        secondArea.setId("Porto");
        secondArea.setDescription("City of Porto");
        secondArea.setTypeArea("city");
        secondArea.setWidth(10.09);
        secondArea.setLength(3.30);
        secondArea.setLatitudeGeoAreaDTO(41.149935);
        secondArea.setLongitudeGeoAreaDTO(-8.610857);
        secondArea.setAltitudeGeoAreaDTO(118);

        // Set up Expected Result

        GeographicArea geoArea1 = new GeographicArea("ISEP", new TypeArea("urban area"), 0.249,
                0.261, new Local(41.178553, -8.608035, 111));
        GeographicArea geoArea2 = new GeographicArea("Porto", new TypeArea("city"), 3.30, 10.09,
                new Local(41.149935, -8.610857, 118));

        GeographicAreaList expectedResult = new GeographicAreaList();
        expectedResult.addGeographicArea(geoArea1);
        expectedResult.addGeographicArea(geoArea2);

        // Populate Array to Use

        arrayToUse[0] = firstArea;
        arrayToUse[1] = secondArea;

        // Act

        controller.addGeoAreasToList(arrayToUse, result);

        // Assert

        assertEquals(expectedResult, result);
    }
}
