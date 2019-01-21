package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * HouseConfigurationController tests class.
 */

class HouseConfigurationControllerTest {

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
    void seeIfGeographicAreaIndexMatchByString() {
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea gA1 = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        GeographicArea gA2 = new GeographicArea("lisboa", new TypeArea("aldeia"), 2, 3, new Local(4, 4, 100));

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(gA1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(gA2);
        //Act
        List<Integer> result = ctrl.matchGeographicAreaIndexByString("lisboa", mGeographicAreaList);
        List<Integer> expectedResult = Collections.singletonList(mGeographicAreaList.getGeographicAreaList().indexOf(gA2));
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintGeoGraphicAreaElementsByIndex() {
        //Arrange
        HouseConfigurationController ctrl = new HouseConfigurationController();
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), 2, 3, new Local(4, 4, 100));

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);

        //Act
        String result = ctrl.buildGeoAreasByIndexString(list, mGeographicAreaList);
        String expectedResult = "---------------\n" +
                "1) lisboa, aldeia, 4.0º lat, 4.0º long\n" +
                "---------------\n";

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfSetHouseAddress() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4),ga);
        //Act
        ctrl.setHouseAddress("rua da rua", house);
        String result = house.getStreet();
        //Assert
        assertEquals("rua da rua", result);
    }

    @Test
    public void seeIfSetHouseZipCode() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4),ga);
        //Act
        ctrl.setHouseZIPCode("3432", house);
        String result = house.getZip();
        //Assert
        assertEquals("3432", result);
    }

    @Test
    public void seeIfSetHouseLocal() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4),ga);
        //Act
        Local local = new Local (34,56,5);
        ctrl.setHouseLocal(34,56,5, house);
        Local result = house.getLocation();
        //Assert
        assertEquals(local, result);
    }

    //USER STORY 105


    @Test
    public void seeIfGetHouseName() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4),ga);
        //Act

        String result = ctrl.getHouseName(house);
        //Assert
        assertEquals("Casa de praia", result);
    }

    @Test
    void seeIfAddRoomToHouseWorks() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        Room room1 = new Room("quarto", 1, 1, 2, 2);
        Room room2 = new Room("sala", 1, 1, 2, 2);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4),ga);
        house.addRoomToRoomList(room1);
        house.addRoomToRoomList(room2);
        boolean expectedResult = true;
        //ACT
        ctrl.createNewRoom("kitchen", 1, 1, 2, 2);
        boolean result = ctrl.addRoomToHouse(house);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddPowerToListSourceFails() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        Room room1 = new Room("kitchen", 1, 1, 2, 2);
        Room room2 = new Room("kitchen", 1, 1, 2, 2);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4),ga);
        house.addRoomToRoomList(room1);
        house.addRoomToRoomList(room2);
        ctrl.createNewRoom("kitchen", 1, 1, 2, 2);
        boolean expectedResult = false;
        boolean result = ctrl.addRoomToHouse(house);
        assertEquals(expectedResult, result);
    }

    // USER STORY 108

    @Test
    void seeIfMatchGeographicAreaIndexByStringWorks() {
        //Arrange -------------------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), 2, 3, new Local(4, 4, 100));

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act -----------------------------------------
        List<Integer> result = ctrl.matchGeographicAreaIndexByString("lisboa", mGeographicAreaList);
        List<Integer> expectedResult = Collections.singletonList(mGeographicAreaList.getGeographicAreaList().indexOf(geoa2));
        //Assert --------------------------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfPrintGeoGraphicAreaElementsByIndex2() {
        //Arrange -----------------------------------------
        HouseConfigurationController ctrl = new HouseConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        GeographicArea geoa2 = new GeographicArea("lisboa", new TypeArea("aldeia"), 2, 3, new Local(4, 4, 100));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act ---------------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.buildGeoAreasByIndexString(list, mGeographicAreaList);
        String expectedResult = "---------------\n" +
                "1) lisboa, aldeia, 4.0º lat, 4.0º long\n" +
                "---------------\n";
        //Assert ------------------------------------------
        Assertions.assertEquals(expectedResult, result);
    }

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
    public void seeIfPrintsRoomList() {
        HouseConfigurationController ctrl = new HouseConfigurationController();
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4),ga);
        house.setRoomList(roomList);
        String expectedResult = "---------------\n" +
                "0) Designation: kitchen | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "1) Designation: sala | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        String result = ctrl.buildRoomsString(house);
        Assertions.assertEquals(expectedResult, result);
    }


}
