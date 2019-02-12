package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.*;

import java.util.*;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

/**
 * House Monitoring - controller Tests
 */

class HouseMonitoringControllerTest {

    @Test
    void seeIfPrintsGeoAList() {
        HouseMonitoringController US623 = new HouseMonitoringController();
        GeographicArea gA1 = new GeographicArea("Portugal", new TypeArea("Country"), 10, 20, new Local(21, 33, 18));
        GeographicArea gA2 = new GeographicArea("Oporto", new TypeArea("City"), 10, 20, new Local(14, 14, 18));
        GeographicArea gA3 = new GeographicArea("Lisbon", new TypeArea("Village"), 10, 20, new Local(3, 3, 18));

        GeographicAreaList gAL1 = new GeographicAreaList();
        gAL1.addGeographicAreaToGeographicAreaList(gA1);
        gAL1.addGeographicAreaToGeographicAreaList(gA2);
        gAL1.addGeographicAreaToGeographicAreaList(gA3);
        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 21.0 | Longitude: 33.0\n" +
                "1) Name: Oporto | Type: City | Latitude: 14.0 | Longitude: 14.0\n" +
                "2) Name: Lisbon | Type: Village | Latitude: 3.0 | Longitude: 3.0\n" +
                "---------------\n";
        String result = US623.buildGeoAreaListString(gAL1);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsGeoAListIfEmpty() {
        HouseMonitoringController US623 = new HouseMonitoringController();
        GeographicAreaList gAL1 = new GeographicAreaList();
        String expectedResult = "Invalid List - List is Empty\n";
        String result = US623.buildGeoAreaListString(gAL1);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeCreateDate() {
        HouseMonitoringController US623 = new HouseMonitoringController();
        int year = 2018;
        int month = 1;
        int day = 13;
        Date expectedResult = new GregorianCalendar(year, month, day).getTime();
        Date result = US623.createDate(year, month, day);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfListContainRoomByName() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        //Room
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        Room room2 = new Room("kitchen", 8, 2, 2, 2);
        roomList1.addRoom(room2);
        roomList1.addRoom(room1);

        //Act
        boolean result = ctrl.doesListContainRoomByName("room1", roomList1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfListContainRoomByNameFalse() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        //Room
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        Room room2 = new Room("kitchen", 8, 2, 2, 2);
        roomList1.addRoom(room2);
        roomList1.addRoom(room1);
        boolean result = ctrl.doesListContainRoomByName("room2", roomList1);
        assertFalse(result);
    }

    @Test
    void seeIfSensorListInARoomContainASensorByName() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange

        //Sensor
        SensorList sensorList2 = new SensorList();
        Sensor sensor1 = new Sensor("sensor", new TypeSensor("temperature", "Celsius"), new Local(4, 4, 100), new GregorianCalendar(8, 8, 8).getTime());
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("Rain", "l/m2"), new Local(4, 4, 100), new GregorianCalendar(8, 8, 8).getTime());
        sensorList2.addSensor(sensor1);
        sensorList2.addSensor(sensor2);

        //Room
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        room1.setRoomSensorList(sensorList2);
        Room room2 = new Room("kitchen", 8, 2, 2, 2);
        room2.setRoomSensorList(sensorList2);
        roomList1.addRoom(room2);
        roomList1.addRoom(room1);

        //Act
        boolean result = ctrl.doesSensorListInARoomContainASensorByName("sensor2", roomList1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorListInARoomContainASensorByNameFalse() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange

        //Sensor
        SensorList sensorList2 = new SensorList();
        Sensor sensor1 = new Sensor("sensor", new TypeSensor("temperature", "Celsius"), new Local(4, 4, 100), new GregorianCalendar(8, 8, 8).getTime());
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("Rain", "l/m2"), new Local(4, 4, 100), new GregorianCalendar(8, 8, 8).getTime());
        sensorList2.addSensor(sensor1);
        sensorList2.addSensor(sensor2);

        //Room
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789, 2, 2);
        room1.setRoomSensorList(sensorList2);
        Room room2 = new Room("kitchen", 8, 2, 2, 2);
        room2.setRoomSensorList(sensorList2);
        roomList1.addRoom(room2);
        roomList1.addRoom(room1);

        //Act
        boolean result = ctrl.doesSensorListInARoomContainASensorByName("sensor3", roomList1);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorks() {
        //Arrange -------------------------------------
        //RoomList
        RoomList roomList = new RoomList();
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d1 = new GregorianCalendar(2018, 3, 1, 15, 0, 0).getTime();
        Date d2 = new GregorianCalendar(2018, 3, 1, 17, 0, 0).getTime();
        Date d3 = new GregorianCalendar(2018, 3, 1, 16, 0, 0).getTime();
        Reading r1;
        Reading r2;
        Reading r3;
        r1 = new Reading(15, d1);
        r2 = new Reading(20, d2);
        r3 = new Reading(30, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("Temp Sensor 1", tipo, new Local(1, 1, 100), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 2, 2);
        room.setRoomSensorList(list);
        roomList.addRoom(room);
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Act -----------------------------------------
        double result = ctrl.getCurrentRoomTemperature(room);
        double expectedResult = 20;
        //Assert --------------------------------------
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void SeeIfGetCurrentTemperatureInTheHouseAreaWorks() {
        //Arrange -----------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Reading List
        Reading r1 = new Reading(30, new GregorianCalendar(2018, 8, 9, 17, 0).getTime());
        Reading r2 = new Reading(40, new GregorianCalendar(2018, 8, 9, 22, 0).getTime());
        ReadingList readingList = new ReadingList();
        readingList.addReading(r1);
        readingList.addReading(r2);
        //Sensor List
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(4, 8, 100), new GregorianCalendar(4, 4, 4).getTime());
        s1.setReadingList(readingList);
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 100), new GregorianCalendar(4, 4, 4).getTime());
        s2.setReadingList(readingList);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        //Geo Area List
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        //House List
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 100), ga, 60, 180, deviceTypeString);

        //Act ---------------------------------------------------
        double result = ctrl.getCurrentTemperatureInTheHouseArea(house, ga);
        double expectedResult = 40;
        //Assert ------------------------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    void SeeIfPrintGAWorks() {
        //Arrange -------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        //Act -----------------------
        String result = ctrl.buildGeoAreaString(ga);
        String expectedResult = "porto, cidade, 16.0º lat, 17.0º long\n";
        //Assert --------------------
        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfDoesListOfRoomsContainRoomByName() {
        //Arrange ---------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Room List
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 100), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Jardim", 1, 123, 2, 2);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);
        //Act -------------------------------------------
        boolean result = ctrl.doesListContainRoomByName("Jardim", roomList);
        //Assert ----------------------------------------
        assertTrue(result);
    }

    @Test
    void seeIfDoesListOfRoomsContainRoomByNameFalse() {
        //Arrange --------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Room List
        RoomList roomList = new RoomList();
        SensorList sensorList = new SensorList(new Sensor("s1", new TypeSensor("Temperatura", "Celsius"), new Local(21, 23, 100), new Date(21 / 11 / 2018)));
        Room r1 = new Room("Cozinha", 1, 123, 2, 2);
        r1.setRoomSensorList(sensorList);
        Room r2 = new Room("Jardim", 1, 123, 2, 2);
        r2.setRoomSensorList(sensorList);
        Room r3 = new Room("Quarto", 1, 123, 2, 2);
        r3.setRoomSensorList(sensorList);
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        roomList.addRoom(r3);
        //Act ------------------------------------------------
        boolean result = ctrl.doesListContainRoomByName("Sala", roomList);
        //Assert ---------------------------------------------
        assertFalse(result);
    }

    @Test
    void seeIfDoesSensorListInARoomContainASensorByName() {
        //Arrange -----------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Sensor List
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 100), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 100), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 100), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        //Room List
        Room room = new Room("cozinha", 1, 2, 2, 2);
        room.setRoomSensorList(sensorList1);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        //Act ---------------------------------------------
        boolean result = ctrl.doesSensorListInARoomContainASensorByName("s1", roomList);
        //Assert ------------------------------------------
        assertTrue(result);
    }

    @Test
    void seeIfDoesSensorListInARoomContainASensorByNameFalse() {
        //Arrange ----------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Sensor List
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 100), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 100), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 100), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        //Room List
        Room room = new Room("cozinha", 1, 2, 2, 2);
        room.setRoomSensorList(sensorList1);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        //Act --------------------------------------------------
        boolean result = ctrl.doesSensorListInARoomContainASensorByName("miau", roomList);
        //Assert -----------------------------------------------
        assertFalse(result);
    }

    @Test
    void seeIfMatchGeographicAreaIndexByStringWorks() {
        //Arrange -------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        GeographicArea geoa2 = new GeographicArea("Porto", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));

        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act -----------------------------------------
        List<Integer> result = ctrl.matchGeographicAreaIndexByString("Porto", mGeographicAreaList);
        List<Integer> expectedResult = Collections.singletonList(mGeographicAreaList.getGeographicAreaList().indexOf(geoa2));
        //Assert --------------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintGeoGraphicAreaElementsByIndex() {
        //Arrange -----------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        GeographicArea geoa1 = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        GeographicArea geoa2 = new GeographicArea("Porto", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa1);
        mGeographicAreaList.addGeographicAreaToGeographicAreaList(geoa2);
        //Act ---------------------------------------------
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        String result = ctrl.buildGeographicAreaElementsByIndexString(list, mGeographicAreaList);
        String expectedResult = "---------------\n" +
                "1) Porto, cidade, 16.0º lat, 17.0º long\n" +
                "---------------\n";
        //Assert ------------------------------------------
        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfMatcSensorIndexByStringWorks() {
        //Arrange -------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Geo Area List
        SensorList sensorList = new SensorList();
        Sensor s1 = new Sensor("sensor", new TypeSensor("temperatura", "Celsius"), new Local(4, 4, 100), new GregorianCalendar(8, 8, 8, 8, 8).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperatura", "Celsius"), new Local(4, 4, 100), new GregorianCalendar(8, 8, 8, 8, 8).getTime());
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        Room room = new Room("cozinha", 1, 1, 2, 2);
        room.setRoomSensorList(sensorList);
        //Act -----------------------------------------
        List<Integer> result = ctrl.matchSensorIndexByString("sensor", room);
        List<Integer> expectedResult = Collections.singletonList(sensorList.getSensorList().indexOf(s1));
        //Assert --------------------------------------
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfprintSensorWorks() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Sensor s1 = new Sensor("sensor", new TypeSensor("temperatura", "Celsius"), new Local(4, 4, 100), new GregorianCalendar(7, 7, 7).getTime());
        String result = ctrl.buildSensorString(s1);
        String expected = "sensor, temperatura, 4.0º lat, 4.0º long\n";
        assertEquals(expected, result);
    }

    @Test
    void seeIfRoomAreaIndexMatchByString() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);

        //Act
        List<Integer> result = ctrl.matchRoomIndexByString("sala", house);
        List<Integer> expectedResult = Collections.singletonList(house.getRoomList().indexOf(room1));
        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintRoomElementsByIndex() {
        //Arrange
        HouseMonitoringController ctrl = new HouseMonitoringController();
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        List<Integer> list = new ArrayList<>();
        Integer i = 1;
        list.add(i);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);

        //Act
        String result = ctrl.buildRoomElementsByIndexString(list, house);
        String expectedResult = "1) sala, 1, 1.0, 2.0, 2.0.\n";

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintSensorElementsByIndex() {
        //Arrange
        HouseMonitoringController ctrl = new HouseMonitoringController();
        List<Integer> list = new ArrayList<>();
        Integer i = 2;
        list.add(i);
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setRoomSensorList(sensorList1);
        //Act
        String result = ctrl.buildSensorElementsByIndexString(list, room);
        String expectedResult = "2) s3 which is a Rain sensor.\n";

        //Assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    void ensureThatSensorListIsPrintCorrectly() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Room room = new Room("Quarto Miki", 1, 3, 3, 3);
        TypeSensor t1 = new TypeSensor("Rain", "l/m2");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        Sensor s1 = new Sensor("s1", t1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", t2, new Local(16, 17, 50), new GregorianCalendar(2000, 11, 2).getTime());
        Sensor s3 = new Sensor("s3", t1, new Local(0, 0, 50), new GregorianCalendar(2000, 11, 1).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        sensorList1.addSensor(s3);
        room.setRoomSensorList(sensorList1);
        String expectedResult = "---------------\n" +
                "0) Designation: s1 | Sensor Type: Rain\n" +
                "1) Designation: s2 | Sensor Type: Vento\n" +
                "2) Designation: s3 | Sensor Type: Rain\n" +
                "---------------\n";
        String actualResult = ctrl.buildRoomSensorListString(room);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintsRoom() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Room room = new Room("kitchen", 1, 1, 2, 2);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        String expectedResult = "kitchen, 1, 1.0, 2.0, 2.0.\n";
        String result = ctrl.buildRoomString(room);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsRoomList() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);

        String expectedResult = "---------------\n" +
                "0) Designation: kitchen | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "1) Designation: sala | House Floor: 1 | Width: 1.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";
        String result = ctrl.buildHouseRoomListString(house);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAverageOfReadingsBetweenTwoGivenDates() {
        //Arrange
        HouseMonitoringController ctrl = new HouseMonitoringController();
        ReadingList rList = new ReadingList();
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 7, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 8, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 9, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 10, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 13, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 1, 0, 0, 0);
        Reading r0 = new Reading(23, g0.getTime());
        Reading r1 = new Reading(23, g1.getTime());
        Reading r2 = new Reading(24, g2.getTime());
        Reading r3 = new Reading(25, g3.getTime());
        Reading r4 = new Reading(26, g4.getTime());
        Reading r5 = new Reading(23, g5.getTime());
        Reading r6 = new Reading(22, g6.getTime());
        Reading r7 = new Reading(23, g7.getTime());
        Reading r8 = new Reading(22, g8.getTime());
        rList.addReading(r0);
        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);
        rList.addReading(r4);
        rList.addReading(r5);
        rList.addReading(r6);
        rList.addReading(r7);
        rList.addReading(r8);
        TypeSensor ti1 = new TypeSensor("rainfall", "l/m2");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        s1.setReadingList(rList);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);


        //Act
        GregorianCalendar dateMin = new GregorianCalendar(2018, 10, 7);
        GregorianCalendar dateMax = new GregorianCalendar(2018, 10, 13);
        Date dateToTest1 = dateMin.getTime();
        Date dateToTest2 = dateMax.getTime();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);

        house.setMotherArea(ga);
        double expectedResult = 24;
        double actualResult = ctrl.getAVGDailyRainfallOnGivenPeriod(house, dateToTest1, dateToTest2);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetHouseInfoOutputMessage() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        String expectedResult = "The Average Rainfall on the house area of casa de praia";
        String result = ctrl.getHouseInfoForOutputMessage(house);
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAVGDailyRainfallOnGivenPeriodWorksEmptyReadingList() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        Date d2 = new GregorianCalendar(2019, 1, 2, 2, 1, 1).getTime();

        ReadingList rList = new ReadingList();
        TypeSensor ti1 = new TypeSensor("rainfall", "l/m2");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        s1.setReadingList(rList);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double result = ctrl.getAVGDailyRainfallOnGivenPeriod(house, d1, d2);
        double expectedResult = NaN;
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAVGDailyRainfallOnGivenPeriodWorksDoubleNan() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        Date d2 = new GregorianCalendar(2019, 1, 2, 2, 1, 1).getTime();
        Reading r1 = new Reading(NaN, d1);
        ReadingList rList = new ReadingList();
        rList.addReading(r1);
        TypeSensor ti1 = new TypeSensor("rainfall", "l/m2");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        s1.setReadingList(rList);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double result = ctrl.getAVGDailyRainfallOnGivenPeriod(house, d1, d2);
        double expectedResult = NaN;
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAVGDailyRainfallOnGivenPeriodWorksNull() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        Date d2 = new GregorianCalendar(2019, 1, 2, 2, 1, 1).getTime();
        TypeSensor ti1 = new TypeSensor("rainfall", "l/m2");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double result = ctrl.getAVGDailyRainfallOnGivenPeriod(house, d1, d2);
        double expectedResult = NaN;
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAVGDailyRainfallOnGivenPeriodWorksNulo() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        Date d2 = new GregorianCalendar(2019, 1, 2, 2, 1, 1).getTime();
        ReadingList rList = null;
        TypeSensor ti1 = new TypeSensor("rainfall", "l/m2");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        s1.setReadingList(rList);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double result = ctrl.getAVGDailyRainfallOnGivenPeriod(house, d1, d2);
        double expectedResult = NaN;
        assertEquals(expectedResult, result);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDay() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        ReadingList rl = new ReadingList();
        Reading reading = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading2 = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading3 = new Reading(20, new GregorianCalendar(2018, 10, 4).getTime());
        Reading reading4 = new Reading(20, new GregorianCalendar(2018, 10, 2).getTime());
        rl.addReading(reading);
        rl.addReading(reading2);
        rl.addReading(reading3);
        rl.addReading(reading4);
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        TypeSensor ti1 = new TypeSensor("rainfall", "l/m2");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        s1.setReadingList(rl);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double expectedResult = s1.getReadingList().getTotalValueOfReadingOnGivenDay(d1);
        //Act
        double actualResult = ctrl.getTotalRainfallOnGivenDay(house, d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDayNotRainfall() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        ReadingList rl = new ReadingList();
        Reading reading = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading2 = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading3 = new Reading(20, new GregorianCalendar(2018, 10, 4).getTime());
        Reading reading4 = new Reading(20, new GregorianCalendar(2018, 10, 2).getTime());
        rl.addReading(reading);
        rl.addReading(reading2);
        rl.addReading(reading3);
        rl.addReading(reading4);
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        TypeSensor ti1 = new TypeSensor("temperature", "l/m2");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        s1.setReadingList(rl);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double expectedResult = NaN;
        //Act
        double actualResult = ctrl.getTotalRainfallOnGivenDay(house, d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDayNull() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        TypeSensor ti1 = new TypeSensor("rainfall", "l/m2");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double expectedResult = NaN;
        //Act
        double actualResult = ctrl.getTotalRainfallOnGivenDay(house, d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDay2() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        ReadingList rl = new ReadingList();
        Reading reading1 = new Reading(30, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 2, 1).getTime());
        rl.addReading(reading1);
        Date d1 = new GregorianCalendar(2018, Calendar.DECEMBER, 31, 2, 1, 1).getTime();
        TypeSensor ti1 = new TypeSensor("rainfall", "l/m2");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        s1.setReadingList(rl);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double expectedResult = 30;
        //Act
        double actualResult = ctrl.getTotalRainfallOnGivenDay(house, d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDay3() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        ReadingList readingList = null;
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        TypeSensor ti1 = new TypeSensor("rainfall", "l/m2");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        s1.setReadingList(readingList);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double expectedResult = NaN;
        //Act
        double actualResult = ctrl.getTotalRainfallOnGivenDay(house, d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDay4() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        ReadingList readingList = new ReadingList();
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        TypeSensor ti1 = new TypeSensor("rainfall", "l/m2");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        s1.setReadingList(readingList);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double expectedResult = NaN;
        //Act
        double actualResult = ctrl.getTotalRainfallOnGivenDay(house, d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsOnGivenDay5() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        ReadingList rl = new ReadingList();
        Reading reading = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading2 = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading3 = new Reading(20, new GregorianCalendar(2018, 10, 4).getTime());
        Reading reading4 = new Reading(20, new GregorianCalendar(2018, 10, 2).getTime());
        rl.addReading(reading);
        rl.addReading(reading2);
        rl.addReading(reading3);
        rl.addReading(reading4);
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        TypeSensor ti1 = new TypeSensor("adsjhgfkhjsgdk", "asdfhg");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        s1.setReadingList(rl);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double expectedResult = NaN;
        //Act
        double actualResult = ctrl.getTotalRainfallOnGivenDay(house, d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsWithTwoSensors() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        ReadingList rl = new ReadingList();
        Reading reading = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading2 = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading3 = new Reading(20, new GregorianCalendar(2018, 10, 4).getTime());
        Reading reading4 = new Reading(20, new GregorianCalendar(2018, 10, 2).getTime());
        rl.addReading(reading);
        rl.addReading(reading2);
        rl.addReading(reading3);
        rl.addReading(reading4);

        ReadingList r2 = new ReadingList();
        Reading reading5 = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading6 = new Reading(20, new GregorianCalendar(2018, 10, 3).getTime());
        Reading reading7 = new Reading(20, new GregorianCalendar(2018, 10, 4).getTime());
        Reading reading8 = new Reading(20, new GregorianCalendar(2018, 10, 2).getTime());
        r2.addReading(reading5);
        r2.addReading(reading6);
        r2.addReading(reading7);
        r2.addReading(reading8);

        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        TypeSensor ti1 = new TypeSensor("rainfall", "asdfhg");
        Sensor s1 = new Sensor("s1", ti1, new Local(15, 16, 50), new GregorianCalendar(2000, 10, 8).getTime());
        Sensor s2 = new Sensor("s2", ti1, new Local(10,10,20),new GregorianCalendar(2000, 10, 7).getTime());
        s1.setReadingList(rl);
        s2.setReadingList(r2);
        SensorList sensorList1 = new SensorList(s1);
        sensorList1.addSensor(s1);
        sensorList1.addSensor(s2);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        ga.setSensorList(sensorList1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double expectedResult = NaN;
        //Act
        double actualResult = ctrl.getTotalRainfallOnGivenDay(house, d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetTotalReadingsWithoutSensors() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea ga = new GeographicArea("Porto", t1, 2, 3, l1);
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("sala", 1, 1, 2, 2);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "street", "zip", "town", new Local(1, 1, 1), ga, 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        house.addRoomToRoomList(room1);
        house.setMotherArea(ga);
        double expectedResult = NaN;
        //Act
        double actualResult = ctrl.getTotalRainfallOnGivenDay(house, d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void roomMaxTemperatureInGivenDay() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Room room1 = new Room("room1", 1, 2, 3, 4); //NO SENSORS
        Room room2 = new Room("room2", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITHOUT READINGS + ONE HUMIDITY
        Room room3 = new Room("room3", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH READINGS SAME VALUE ON DAY TESTED + ONE HUMIDITY
        Room room4 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH MAX IN FIRST SENSOR + ONE HUMIDITY
        Room room5 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH MAX IN SECOND SENSOR + ONE HUMIDITY
        Room room6 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH 3 READINGS ON SAME SENSOR (MAX IN FIRST READING) + ONE HUMIDITY
        Room room7 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH 3 READINGS ON SAME SENSOR (MAX IN SECOND READING) + ONE HUMIDITY
        Room room8 = new Room("room4", 1, 2, 3, 4); //TWO TEMPERATURE SENSORS WITH 3 READINGS ON SAME SENSOR (MAX IN THIRD READING) + ONE HUMIDITY

        GregorianCalendar gregorianCalendar1 = new GregorianCalendar(2018,1,1,23,59);
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar(2018,1,2,0,0);
        GregorianCalendar gregorianCalendar3 = new GregorianCalendar(2018,1,2,1,1);
        GregorianCalendar gregorianCalendar4 = new GregorianCalendar(2018,1,2,12,12);

        Sensor sensor1 = new Sensor("sensor1", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor2 = new Sensor("sensor2", new TypeSensor("humidity", "%"), gregorianCalendar1.getTime());
        Sensor sensor3 = new Sensor("sensor3", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());

        room2.addSensor(sensor1);
        room2.addSensor(sensor2);
        room2.addSensor(sensor3);

        Sensor sensor4 = new Sensor("sensor4", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor5 = new Sensor("sensor5", new TypeSensor("humidity", "%"), gregorianCalendar1.getTime());
        Sensor sensor6 = new Sensor("sensor6", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading1 = new Reading(20,gregorianCalendar2.getTime());
        sensor4.addReading(reading1);
        sensor5.addReading(reading1);
        sensor6.addReading(reading1);

        room3.addSensor(sensor4);
        room3.addSensor(sensor5);
        room3.addSensor(sensor6);

        Sensor sensor7 = new Sensor("sensor7", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor8 = new Sensor("sensor8", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading2 = new Reading(24,gregorianCalendar2.getTime());
        Reading reading3 = new Reading(20,gregorianCalendar3.getTime());
        sensor7.addReading(reading2);
        sensor8.addReading(reading3);

        room4.addSensor(sensor5);
        room4.addSensor(sensor7);
        room4.addSensor(sensor8);

        Sensor sensor9 = new Sensor("sensor9", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor10 = new Sensor("sensor10", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading4 = new Reading(20,gregorianCalendar2.getTime());
        Reading reading5 = new Reading(25,gregorianCalendar3.getTime());
        sensor9.addReading(reading4);
        sensor10.addReading(reading5);

        room5.addSensor(sensor5);
        room5.addSensor(sensor9);
        room5.addSensor(sensor10);

        Sensor sensor11 = new Sensor("sensor11", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor12 = new Sensor("sensor12", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading6 = new Reading(26,gregorianCalendar2.getTime());
        Reading reading7 = new Reading(21,gregorianCalendar3.getTime());
        Reading reading8 = new Reading(20,gregorianCalendar4.getTime());
        sensor11.addReading(reading6);
        sensor11.addReading(reading7);
        sensor11.addReading(reading8);
        sensor12.addReading(reading8);

        room6.addSensor(sensor5);
        room6.addSensor(sensor11);
        room6.addSensor(sensor12);

        Sensor sensor13 = new Sensor("sensor13", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor14 = new Sensor("sensor14", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading9 = new Reading(21,gregorianCalendar2.getTime());
        Reading reading10 = new Reading(27,gregorianCalendar3.getTime());
        Reading reading11 = new Reading(20,gregorianCalendar4.getTime());
        sensor13.addReading(reading9);
        sensor13.addReading(reading10);
        sensor13.addReading(reading11);
        sensor14.addReading(reading11);

        room7.addSensor(sensor5);
        room7.addSensor(sensor13);
        room7.addSensor(sensor14);


        Sensor sensor15 = new Sensor("sensor15", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Sensor sensor16 = new Sensor("sensor16", new TypeSensor("temperature", "ºC"), gregorianCalendar1.getTime());
        Reading reading12 = new Reading(21,gregorianCalendar2.getTime());
        Reading reading13 = new Reading(20,gregorianCalendar3.getTime());
        Reading reading14 = new Reading(28,gregorianCalendar4.getTime());
        sensor15.addReading(reading12);
        sensor15.addReading(reading13);
        sensor15.addReading(reading14);
        sensor16.addReading(reading12);

        room8.addSensor(sensor5);
        room8.addSensor(sensor15);
        room8.addSensor(sensor16);

        //ACT
        double actualResult1 = ctrl.getMaxTemperatureInARoomOnAGivenDay(room1, gregorianCalendar2.getTime());
        double actualResult2 = ctrl.getMaxTemperatureInARoomOnAGivenDay(room2, gregorianCalendar3.getTime());
        double actualResult3 = ctrl.getMaxTemperatureInARoomOnAGivenDay(room3, gregorianCalendar4.getTime());
        double actualResult4 = ctrl.getMaxTemperatureInARoomOnAGivenDay(room4, gregorianCalendar2.getTime());
        double actualResult5 = ctrl.getMaxTemperatureInARoomOnAGivenDay(room5, gregorianCalendar3.getTime());
        double actualResult6 = ctrl.getMaxTemperatureInARoomOnAGivenDay(room6, gregorianCalendar4.getTime());
        double actualResult7 = ctrl.getMaxTemperatureInARoomOnAGivenDay(room7, gregorianCalendar2.getTime());
        double actualResult8 = ctrl.getMaxTemperatureInARoomOnAGivenDay(room8, gregorianCalendar3.getTime());

        //ASSERT
        assertEquals(actualResult1,NaN,0.01);
        assertEquals(actualResult2,NaN,0.01);
        assertEquals(actualResult3,20,0.01);
        assertEquals(actualResult4,24,0.01);
        assertEquals(actualResult5,25,0.01);
        assertEquals(actualResult6,26,0.01);
        assertEquals(actualResult7,27,0.01);
        assertEquals(actualResult8,28,0.01);
    }

    @Test
    void getRoomName() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        //Arrange
        Room room = new Room("kitchen", 1, 1, 2, 2);
        Room room1 = new Room("living room", 1, 1, 2, 2);
        //Act
        String actualResult1 = ctrl.getRoomName(room);
        String actualResult2 = ctrl.getRoomName(room1);

        //Assert
        assertEquals("kitchen", actualResult1);
        assertEquals("living room", actualResult2);
    }






}
