package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import pt.ipp.isep.dei.project.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomConfigurationControllerTest {

    @Test
    public void seeIfSensorIsAddedToRoom() {
        //Arrange

        Sensor s1 = new Sensor("vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("pluviosidade", new TypeSensor("Pluviosidade"), new Local(10, 30, 20), new GregorianCalendar(118, 12, 4).getTime());

        SensorList sl1 = new SensorList();
        sl1.addSensor(s1);
        sl1.addSensor(s2);
        Room Bathroom1 = new Room("Bathroom",1,3);
        GeographicArea ga1 = new GeographicArea();
        HouseList hlist1 = new HouseList();
        House house1 = new House();
        RoomList rlist1 = new RoomList();

        ga1.setSensorList(sl1);
        ga1.setHouseList(hlist1);
        hlist1.addHouseToHouseList(house1);
        house1.setmRoomList(rlist1);
        rlist1.addRoom(Bathroom1);

        SensorList sl2 = new SensorList();
        Bathroom1.setRoomSensorList(sl2);
        //Act

        RoomConfigurationController crl = new RoomConfigurationController();
        crl.addSensorToRoom(Bathroom1,"vento",ga1);
        crl.addSensorToRoom(Bathroom1,"pluviosidade",ga1);
        SensorList actualResult = crl.getSensorsFromRoom();
        SensorList expectedResult = sl1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSensorIsContainedInGA() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();
        slist1.addSensor(s1);
        slist1.addSensor(s2);
        GeographicArea ga1 = new GeographicArea();
        ga1.setSensorList(slist1);


        //Act

        RoomConfigurationController crl = new RoomConfigurationController();
        Sensor actualResult = crl.getSensorFromName("Vento",ga1);
        Sensor expectedResult = s1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfRoomIsContainedInRoomList() {
        //Arrange

        Room room1 = new Room("Quarto",1,5);
        Room room2 = new Room("Cozinha",1,9);
        GeographicArea ga1 = new GeographicArea();
        RoomList rlist1 = new RoomList();
        House house1 = new House();

        house1.setmRoomList(rlist1);
        rlist1.addRoom(room1);
        rlist1.addRoom(room2);
        //Act

        RoomConfigurationController crl = new RoomConfigurationController();
        Room actualResult = crl.getRoomFromName("Quarto",house1);
        Room expectedResult = room1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void seeIfSensorListIsContainedInGAList() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade"),
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
        boolean actualResult = crl.doesSensorListInAGeoAreaContainASensorByName("Vento",glist1);
        //Assert
        assertTrue(actualResult);
    }
    @Test
    public void seeIfPrintRoomElementsByIndex() {
        //Arrange --------------------------------
        RoomConfigurationController ctrl = new RoomConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("house2", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1;
        roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.printRoomElementsByIndex(list, house1);
        String expectedResult = "1) kitchen, 8, 2.0.\n";
        //Assert ---------------------------------
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfMatchRoomIndexByString() {
        //Arrange -----------------------------
        RoomConfigurationController ctrl = new RoomConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("house2", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ---------------------------------
        List<Integer> result = ctrl.matchRoomIndexByString("kitchen", house1);
        List<Integer> expectedResult = new ArrayList<>();
        Integer i = 1;
        expectedResult.add(i);
        //Assert ------------------------------
        Assertions.assertEquals(expectedResult, result);
    }
    @Test
    public void seeIfPrintRoomWorks() {
        //Arrange ---------------------
        RoomConfigurationController ctrl = new RoomConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act -------------------------
        String result = ctrl.printRoom(room2);
        String expectedResult = "kitchen, 8, 2.0.\n";
        //Assert ----------------------
        Assertions.assertEquals(expectedResult, result);
    }
    @Test
    public void seeIfPrintRoomListWork() {
        //Arrange ------------------------
        RoomConfigurationController ctrl = new RoomConfigurationController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("porto", new TypeArea("cidade"), new Local(4, 4));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        //House List
        HouseList houseList1 = new HouseList();
        House house1 = new House("a minha rica casinha", "Address2", new Local(4, 4), "3456-123");
        geoa1.setHouseList(houseList1);
        houseList1.addHouseToHouseList(house1);
        //Room List
        RoomList roomList1 = new RoomList();
        house1.setmRoomList(roomList1);
        Room room1 = new Room("room1", 19, 23456789);
        Room room2 = new Room("kitchen", 8, 2);
        roomList1.addRoom(room1);
        roomList1.addRoom(room2);
        //Act ----------------------------
        String result = ctrl.printRoomList(house1);
        String expectedResult = "---------------\n" +
                "0) Designation: room1 | House Floor: 19 | Dimensions: 2.3456789E7\n" +
                "1) Designation: kitchen | House Floor: 8 | Dimensions: 2.0\n" +
                "---------------\n";
        //Assert -------------------------
        Assertions.assertEquals(expectedResult, result);
    }
}