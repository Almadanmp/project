package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoomConfigurationControllerTest {

    //  SHARED METHODS
    @Test
    void seeIfRoomIsContainedInRoomList() {
        //Arrange
        Room room1 = new Room("Quarto", 1, 5, 1, 21);
        Room room2 = new Room("Cozinha", 1, 9, 3, 5);
        RoomList rList = new RoomList();
        House house1 = new House();
        house1.setRoomList(rList);
        rList.addRoom(room1);
        rList.addRoom(room2);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Room actualResult = ctrl.getRoomFromHouseByName("Quarto", house1);
        //Assert
        assertEquals(room1, actualResult);
    }

    @Test
    void seeIfPrintsRoomList() {
        //Arrange
        GeographicArea gA = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), gA, roomList);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String expectedactualResult = "---------------\n" +
                "0) Designation: kitchen | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "1) Designation: sala | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        String actualResult = ctrl.printRoomList(house);
        //Assert
        assertEquals(expectedactualResult, actualResult);
    }

    @Test
    void seeIfPrintRoomWorks() {
        //Arrange
        Room room = new Room("room1", 1, 1, 2, 2);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String actualResult = ctrl.printRoom(room);
        String expectedResult = "room1, 1, 1.0, 2.0, 2.0.\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintRoomElementsByIndex() {
        //Arrange
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicArea gA = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), gA, roomList);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String actualResult = ctrl.printRoomElementsByIndex(list, house);
        String expectedResult = "1) sala, 1, 1.0, 2.0, 2.0.\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRoomAreaIndexMatchByString() {
        //Arrange
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicArea gA = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), gA, roomList);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        List<Integer> actualResult = ctrl.matchRoomIndexByString("sala", house);
        List<Integer> expectedResult = Collections.singletonList(roomList.getRoomList().indexOf(room1));
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    /*USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
    nominal power of a room, i.e. the sum of the nominal power of all devices in the
    room. - TERESA VARELA */

    @Test
    void seeGetRoomNominalPower() {

    }


    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    @Test
    void seeIfSensorIsContainedInGA() {
        //Arrange
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        GeographicArea gA1 = new GeographicArea();
        gA1.setSensorList(sList);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Sensor actualResult = ctrl.getSensorFromGAByName("Vento1", gA1);
        Sensor expectedResult = s1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorListIsContainedInGAList() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(sList);
        GeographicAreaList gList = new GeographicAreaList();
        gList.addGeographicAreaToGeographicAreaList(ga1);
        //Act
        RoomConfigurationController crl = new RoomConfigurationController();
        boolean actualResult = crl.checkIfGAContainsSensorByString("Vento", ga1);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfSensorListIsNotContainedInGAList() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(sList);
        GeographicAreaList gAList = new GeographicAreaList();
        gAList.addGeographicAreaToGeographicAreaList(ga1);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        boolean actualResult = ctrl.checkIfGAContainsSensorByString("Chuva", ga1);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfMatchSensorIndexByStringWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sL1 = new SensorList();
        sL1.addSensor(s1);
        sL1.addSensor(s2);
        GeographicArea gA1 = new GeographicArea();
        gA1.setSensorList(sL1);
        GeographicAreaList gList1 = new GeographicAreaList();
        gList1.addGeographicAreaToGeographicAreaList(gA1);
        //Act
        String string = "Pluviosidade";
        RoomConfigurationController ctrl = new RoomConfigurationController();
        List<Integer> actualResult = ctrl.matchSensorIndexByString(string, sL1);
        List<Integer> expectedResult = Collections.singletonList(sL1.getSensorList().indexOf(s2));
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintSensorListWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        //Act
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String actualResult = ctrl.printSensorList(sList);
        String expectedResult = "---------------\n" +
                "0) Name: Vento1 | Type: Atmosphere\n" +
                "1) Name: Pluviosidade1 | Type: Pluviosidade\n" +
                "---------------\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintSensorWorks() {
    //String printSensor(Sensor sensor) {
    //Assert
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
    //Act
    RoomConfigurationController ctrl = new RoomConfigurationController();
    String actualResult = ctrl.printSensor(s2);
    String expectedResult = "Pluviosidade1, Pluviosidade, 10.0º lat, 30.0º long\n";
    //Assert
    assertEquals(expectedResult, actualResult);
    }
    
    @Test
    void seeIfPrintSensorElementsByIndexWorks() {
        //public String printSensorElementsByIndex(List<Integer> listOfIndexesOfSensor, SensorList sensorList) {
        //Assert
        Sensor s1 = new Sensor("Vento1", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade1", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());
        SensorList sList = new SensorList();
        sList.addSensor(s1);
        sList.addSensor(s2);
        //Act
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        RoomConfigurationController ctrl = new RoomConfigurationController();
        String actualResult = ctrl.printSensorElementsByIndex(list, sList);
        String expectedResult = "1) Pluviosidade1 which is a Pluviosidade sensor.\n";
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}