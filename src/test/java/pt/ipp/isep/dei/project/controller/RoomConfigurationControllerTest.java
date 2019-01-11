package pt.ipp.isep.dei.project.controller;

import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomConfigurationControllerTest {

    @Test
    public void seeIfSensorIsContainedInGA() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();
        slist1.addSensor(s1);
        slist1.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(slist1);


        //Act

        RoomConfigurationController crl = new RoomConfigurationController();
        Sensor actualResult = crl.getSensorFromGAByName("Vento", ga1);
        Sensor expectedResult = s1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfRoomIsContainedInRoomList() {
        //Arrange

        Room room1 = new Room("Quarto",1,5, 1, 21);
        Room room2 = new Room("Cozinha",1,9, 3, 5);
        GeographicArea ga1 = new GeographicArea();
        RoomList rlist1 = new RoomList();
        House house1 = new House();

        house1.setRoomList(rlist1);
        rlist1.addRoom(room1);
        rlist1.addRoom(room2);
        //Act

        RoomConfigurationController crl = new RoomConfigurationController();
        Room actualResult = crl.getRoomFromHouseByName("Quarto", house1);
        Room expectedResult = room1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSensorListIsContainedInGAList() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();
        slist1.addSensor(s1);
        slist1.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(slist1);
        GeographicAreaList glist1 = new GeographicAreaList();
        glist1.addGeographicAreaToGeographicAreaList(ga1);
        //Act

        RoomConfigurationController crl = new RoomConfigurationController();
        boolean actualResult = crl.checkIfGAContainsSensorByString("Vento", ga1);
        //Assert
        assertTrue(actualResult);
    }

    @Test
    public void seeIfSensorListIsNotContainedInGAList() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();
        slist1.addSensor(s1);
        slist1.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(slist1);
        GeographicAreaList glist1 = new GeographicAreaList();
        glist1.addGeographicAreaToGeographicAreaList(ga1);
        //Act

        RoomConfigurationController crl = new RoomConfigurationController();
        boolean actualResult = crl.checkIfGAContainsSensorByString("Chuva", ga1);
        //Assert
        assertFalse(actualResult);
    }

    @Test
    public void seeIfPrintsRoomList() {
        RoomConfigurationController crtl= new RoomConfigurationController();
        GeographicArea ga = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, roomList);
        String expectedResult = "---------------\n" +
                "0) Designation: kitchen | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "1) Designation: sala | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        String result = crtl.printRoomList(house);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintRoomWorks() {
        RoomConfigurationController crtl= new RoomConfigurationController();
        Room room = new Room("room1", 1, 1,2,2);
        String result = crtl.printRoom(room);
        assertEquals("room1, 1, 1.0, 2.0, 2.0.\n", result);
    }

    @Test
    void seeIfPrintRoomElementsByIndex() {
        //Arrange
        RoomConfigurationController crtl= new RoomConfigurationController();
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicArea ga = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, roomList);

        //Act
        String result = crtl.printRoomElementsByIndex(list,house);
        String expectedResult = "1) sala, 1, 1.0, 2.0, 2.0.\n";

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfRoomAreaIndexMatchByString() {
        RoomConfigurationController crtl= new RoomConfigurationController();
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        GeographicArea ga = new GeographicArea();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        roomList.addRoom(room1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, roomList);

        //Act
        List<Integer> result = crtl.matchRoomIndexByString("sala",house);
        List<Integer> expectedResult = Collections.singletonList(roomList.getRoomList().indexOf(room1));
        //Assert
        Assert.assertEquals(expectedResult, result);

    }

}