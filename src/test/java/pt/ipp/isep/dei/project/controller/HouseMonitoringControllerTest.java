package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.TestUtils;
import pt.ipp.isep.dei.project.model.*;

import java.util.*;

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
    void SeeIfGetMaxTemperatureInARoomOnAGivenDay() {
        //Arrange
        HouseMonitoringController ctrl = new HouseMonitoringController();
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        Reading r2;
        r1 = new Reading(30, d2);
        r2 = new Reading(20, d2);
        listR.addReading(r1);
        listR.addReading(r2);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1, 100), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 2, 2);
        room.setRoomSensorList(list);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "rua", "123", "coisa", new Local(1, 1, 1), new GeographicArea("porto", new TypeArea("cidade"), 12, 12, new Local(1, 1, 1)), 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);

        //Act
        double result = ctrl.getMaxTemperatureInARoomOnAGivenDay(d2, house, room);
        double expectedResult = 30.0;
        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetMaxTemperatureInARoomOnAGivenDayWorksNegatives() {
        //Arrange
        HouseMonitoringController ctrl = new HouseMonitoringController();
        SensorList list = new SensorList();
        TypeSensor type1 = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        Reading r2;
        r1 = new Reading(-30, d2);
        r2 = new Reading(20, d2);
        listR.addReading(r1);
        listR.addReading(r2);
        Sensor s1 = new Sensor("sensor1", type1, new Local(1, 1, 100), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 2, 2);
        room.setRoomSensorList(list);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "rua", "123", "coisa", new Local(1, 1, 1), new GeographicArea("porto", new TypeArea("cidade"), 12, 12, new Local(1, 1, 1)), 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        //Act
        double result = ctrl.getMaxTemperatureInARoomOnAGivenDay(d2, house, room);
        double expectedResult = 20.0;
        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetMaxTemperatureInARoomOnAGivenDayWorksWithTwoDates() {
        //Arrange -----------------------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        SensorList list = new SensorList();
        TypeSensor type1 = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Date d3 = new GregorianCalendar(2018, 2, 3).getTime();
        Reading r1 = new Reading(-30, d2);
        Reading r2 = new Reading(20, d2);
        Reading r3 = new Reading(25, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("sensor1", type1, new Local(1, 1, 100), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 2, 2);
        room.setRoomSensorList(list);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "rua", "123", "coisa", new Local(1, 1, 1), new GeographicArea("porto", new TypeArea("cidade"), 12, 12, new Local(1, 1, 1)), 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        //Act ---------------------------------------------------------------
        double result = ctrl.getMaxTemperatureInARoomOnAGivenDay(d3, house, room);
        double expectedResult = 25.0;
        //Assert ------------------------------------------------------------
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfGetMaxTemperatureInARoomOnAGivenDayWorksWithTwoDatesAndNeg() {
        //Arrange -----------------------------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        SensorList list = new SensorList();
        TypeSensor type1 = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Date d3 = new GregorianCalendar(2018, 2, 3).getTime();
        Reading r1 = new Reading(-30, d2);
        Reading r2 = new Reading(-20, d2);
        Reading r3 = new Reading(-25, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("sensor1", type1, new Local(1, 1, 100), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 2, 2);
        room.setRoomSensorList(list);
        List<String> test = new ArrayList<>();
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa", "rua", "123", "coisa", new Local(1, 1, 1), new GeographicArea("porto", new TypeArea("cidade"), 12, 12, new Local(1, 1, 1)), 60, 180, deviceTypeString);
        house.addRoomToRoomList(room);
        //Act ---------------------------------------------------------------------
        double result = ctrl.getMaxTemperatureInARoomOnAGivenDay(d3, house, room);
        double expectedResult = -25.0;
        //Assert ------------------------------------------------------------------
        assertEquals(expectedResult, result, 0.01);
    }


    @Test
    void seeIfGetSensorWithTheMinimumDistanceToHouseWorks() {
        //Arrange --------------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 100), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"), new Local(4, 8, 100), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 100), ga, 60, 180, deviceTypeString);

        //Act ------------------------------------------------------
        Sensor result = ctrl.getSensorWithTheMinimumDistanceToHouse(house, ga, "temperature");
        //Assert ---------------------------------------------------
        assertEquals(s1, result);
    }

    @Test
    void seeIfGetSensorWithTheMinimumDistanceToHouseWorks2() {
        //Arrange ---------------------------------------------------
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(4, 8, 100), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature", "Celsius"), new Local(4, 6, 100), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"), 10, 20, new Local(16, 17, 18));
        ga.setSensorList(sensorList);
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(TestUtils.PATH_TO_FRIDGE);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 100), ga, 60, 180, deviceTypeString);
        //Act -------------------------------------------------------
        Sensor result = ctrl.getSensorWithTheMinimumDistanceToHouse(house, ga, "temperature");
        //Assert ----------------------------------------------------
        assertEquals(s2, result);
    }

    @Test
    void seeIfGetCurrentRoomTemperatureWorks() {
        //Arrange -------------------------------------
        //RoomList
        RoomList roomList = new RoomList();
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("Temperature", "Celsius");
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
        double expectedResult = Double.NaN;
        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetAVGDailyRainfallOnGivenPeriodWorksDoubleNan() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Date d1 = new GregorianCalendar(2018, 12, 31, 2, 1, 1).getTime();
        Date d2 = new GregorianCalendar(2019, 1, 2, 2, 1, 1).getTime();
        Reading r1 = new Reading(Double.NaN, d1);
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
        double expectedResult = Double.NaN;
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
        double expectedResult = Double.NaN;
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
        double expectedResult = Double.NaN;
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
        double expectedResult = s1.getReadingList().getTotalValueOfReadingOnGivenDay(d1);
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
        double expectedResult = Double.NaN;
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
        double expectedResult = Double.NaN;
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
        double expectedResult = Double.NaN;
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
        double expectedResult = Double.NaN;
        //Act
        double actualResult = ctrl.getTotalRainfallOnGivenDay(house, d1);
        //Assert
        assertEquals(expectedResult, actualResult);
    }


}
