package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.Collections;
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

    @Test
    void seeIfPrintGAList() {
        //Arrange
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 2, 3, new Local(21, 33, 100));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 2, 3, new Local(14, 14, 100));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 2, 3, new Local(3, 3, 100));

        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);

        //Act
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Oporto | Type: City | Latitude: 14.0 | Longitude: 14.0\n" +
                "2) Name: Lisbon | Type: Village | Latitude: 3.0 | Longitude: 3.0\n" +
                "---------------\n";
        HouseConfigurationController ctrl = new HouseConfigurationController();
        String result = ctrl.buildGAListString(gAL1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSetHouseAddress() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        //Act
        ctrl.setHouseAddress("rua da rua", house);
        String result = house.getStreet();
        //Assert
        assertEquals("rua da rua", result);
    }

    @Test
    void seeIfSetHouseZipCode() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        //Act
        ctrl.setHouseZIPCode("3432", house);
        String result = house.getZip();
        //Assert
        assertEquals("3432", result);
    }

    @Test
    void seeIfSetHouseLocal() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        //Act
        Local local = new Local(34, 56, 5);
        ctrl.setHouseLocal(34, 56, 5, house);
        Local result = house.getLocation();
        //Assert
        assertEquals(local, result);
    }

    //USER STORY 105


    @Test
    void seeIfGetHouseName() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        //Act

        String result = ctrl.getHouseName(house);
        //Assert
        assertEquals("Casa de praia", result);
    }


    // USER STORY 108

    @Test
    void seeIfPrintsGeoA() {
        HouseConfigurationController US101 = new HouseConfigurationController();
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 2, 3, new Local(21, 33, 100));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 2, 3, new Local(14, 14, 100));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 2, 3, new Local(3, 3, 100));

        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        String expectedResult =
                "Portugal, Country, 21.0º lat, 33.0º long\n";
        String result = US101.buildGeoAreaString(gA1);
        assertEquals(expectedResult, result);
    }


    //US108
    @Test
    void seeIfPrintsRoomList() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("room", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        house.setRoomList(roomList);
        String expectedResult = "---------------\n" +
                "0) Designation: kitchen | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "1) Designation: room | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        String result = ctrl.buildRoomsString(house);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void createsRoom() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("Porto", new TypeArea("City"), 2, 3, new Local(4, 4, 100));
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("Beach House", "Flower Street", "4512", "Porto", new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        Room actualResult1 = new Room("kitchen",1,1,1,1);
        Room actualResult2 = new Room("room",1,1,1,1);
        Room actualResult3 = new Room("kitchen",1,1,1,1);
        //ACT
        boolean expectedResult1 = ctrl.createNewRoom(house, "kitchen", 1,1,1,1);
        boolean expectedResult2 = ctrl.createNewRoom(house, "room", 1,1,1,1);
        boolean expectedResult3 = ctrl.createNewRoom(house, "kitchen", 1,1,1,1);
        //ASSERT
        assertTrue(expectedResult1);
        assertTrue(expectedResult2);
        assertFalse(expectedResult3);
    }
}
