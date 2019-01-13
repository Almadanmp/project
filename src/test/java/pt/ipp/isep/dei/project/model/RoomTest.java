package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

public class RoomTest {

    @Test
    public void seeIfGetMaxTemperatureInARoomOnAGivenDayWorks() {
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
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        RoomList roomList1 = new RoomList();
        Room room1 = new Room("room1", 19, 23456789,5,3);
        roomList1.addRoom(room1);
        room1.setRoomSensorList(list);
        ReadingList rL1 = new ReadingList();
        r1 = new Reading(30, d2);
        rL1.addReading(r1);
        DeviceList deviceL1 = new DeviceList();
        Room room = new Room("quarto", 1, 80, 5,3);
        room.setRoomSensorList(list);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        House house = new House("casa","ss","ss","sss", new Local(1, 1, 50),new GeographicArea("porto",new TypeArea("sksks"),1,1, new Local(1, 1, 50)),roomList);
        double result = room.getMaxTemperatureInARoomOnAGivenDay(house,d2);
        double expectedResult = 30.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfgetMaxTemperatureInARoomOnAGivenDayWorksNegatives() {
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        Reading r2;
        r1 = new Reading(-30, d2);
        r2 = new Reading(20, d2);
        listR.addReading(r1);
        listR.addReading(r2);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5,3);
        room.setRoomSensorList(list);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        House house = new House("casa","ss","ss","sss", new Local(1, 1, 50),new GeographicArea("porto",new TypeArea("sksks"),1,1, new Local(1, 1, 50)),roomList);
        double result = room.getMaxTemperatureInARoomOnAGivenDay(house,d2);
        double expectedResult = 20.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfgetMaxTemperatureInARoomOnAGivenDayWorksWithTwoDates() {
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Date d3 = new GregorianCalendar(2018, 2, 3).getTime();
        Reading r1 = new Reading(-30, d2);
        Reading r2 = new Reading(20, d2);
        Reading r3 = new Reading(25, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5,3);
        room.setRoomSensorList(list);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        House house = new House("casa","ss","ss","sss", new Local(1, 1, 50),new GeographicArea("porto",new TypeArea("sksks"),1,1, new Local(1, 1, 50)),roomList);
        double result = room.getMaxTemperatureInARoomOnAGivenDay(house, d3);
        double expectedResult = 25.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfgetMaxTemperatureInARoomOnAGivenDayWorksWithTwoDatesAndNeg() {
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Date d3 = new GregorianCalendar(2018, 2, 3).getTime();
        Reading r1 = new Reading(-30, d2);
        Reading r2 = new Reading(-20, d2);
        Reading r3 = new Reading(-25, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5,3);
        room.setRoomSensorList(list);
        RoomList roomList = new RoomList();
        roomList.addRoom(room);
        House house = new House("casa","ss","ss","sss", new Local(1, 1, 50),new GeographicArea("porto",new TypeArea("sksks"),1,1, new Local(1, 1, 50)),roomList);
        double result = room.getMaxTemperatureInARoomOnAGivenDay(house,d3);
        double expectedResult = -25.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfGetCurrentRoomTemperatureWorks() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d1 = new GregorianCalendar(2018, 2, 1, 16, 30).getTime();
        Date d2 = new GregorianCalendar(2018, 2, 1, 20, 30).getTime();
        Date d3 = new GregorianCalendar(2018, 1, 1, 20, 30).getTime();
        Date d4 = new GregorianCalendar(2017, 12, 1, 20, 30).getTime();
        Reading r1;
        Reading r2;
        Reading r3;
        Reading r4;
        r1 = new Reading(30, d1);
        r2 = new Reading(20, d2);
        r3 = new Reading(25, d3);
        r4 = new Reading(10, d4);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        listR.addReading(r4);
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5,3);
        room.setRoomSensorList(list);
        double result = room.getCurrentRoomTemperature();
        double expectedResult = 20.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfGetCurrentRoomTemperatureWorksNegative() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d1 = new GregorianCalendar(2018, 2, 1, 16, 30).getTime();
        Date d2 = new GregorianCalendar(2018, 2, 1, 20, 30).getTime();
        Date d3 = new GregorianCalendar(2018, 1, 1, 20, 30).getTime();
        Date d4 = new GregorianCalendar(2017, 12, 1, 20, 30).getTime();
        Reading r1;
        Reading r2;
        Reading r3;
        Reading r4;
        r1 = new Reading(30, d1);
        r2 = new Reading(-20, d2);
        r3 = new Reading(25, d3);
        r4 = new Reading(10, d4);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        listR.addReading(r4);
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5,3);
        room.setRoomSensorList(list);
        double result = room.getCurrentRoomTemperature();
        double expectedResult = -20.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfGetCurrentRoomTemperatureWorksMinute() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Date d1 = new GregorianCalendar(2018, 2, 1, 20, 31).getTime();
        Date d2 = new GregorianCalendar(2018, 2, 1, 20, 30).getTime();
        Date d3;
        d3 = new GregorianCalendar(2018, 1, 1, 20, 30).getTime();
        Date d4 = new GregorianCalendar(2017, 12, 1, 20, 30).getTime();
        Reading r1;
        Reading r2;
        Reading r3;
        Reading r4;
        r1 = new Reading(30, d1);
        r2 = new Reading(-20, d2);
        r3 = new Reading(25, d3);
        r4 = new Reading(10, d4);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        listR.addReading(r4);
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5,3);
        room.setRoomSensorList(list);
        double result = room.getCurrentRoomTemperature();
        double expectedResult = 30.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfDoesSensorListInARoomContainASensorByNameWorks() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5,3);
        room.setRoomSensorList(list);
        boolean result = room.doesSensorListInARoomContainASensorByName("sensor1");
        assertTrue(result);
    }

    @Test
    public void seeIfDoesSensorListInARoomContainASensorByNameWorksFalse() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 8, 5,2);
        room.setRoomSensorList(list);
        boolean result = room.doesSensorListInARoomContainASensorByName("sensor89");
        assertEquals(false, result);
    }

    @Test
    public void seeIfAddSensorWorks() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 8, 5,3);
        room.setRoomSensorList(list);
        Sensor s2 = new Sensor("sensor2", type, new Local(1, 1, 50), new Date());
        s2.setReadingList(listR);
        boolean result = room.addSensor(s2);
        assertTrue(result);
    }

    @Test
    public void seeIfAddSensorWorksFalse() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5,3);
        room.setRoomSensorList(list);
        boolean result = room.addSensor(s1);
        assertEquals(false, result);
    }

    @Test
    public void seeIfEqualsWork() {
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        ReadingList listR = new ReadingList();
        Sensor s1 = new Sensor("sensor1", type, new Local(1, 1, 50), new Date());
        s1.setReadingList(listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, 5,3);
        boolean result = room.equals(null);
        assertEquals(false, result);
    }

    @Test
    public void seeIfEqualsWorkDifClass() {
        TypeSensor type = new TypeSensor("temperature", "Celsius");
        Room room = new Room("quarto", 1, 80, 5,3);
        boolean result = room.equals(type);
        assertEquals(false, result);
    }

    @Test
     void hashCodeDummyTest(){
        Room room1 = new Room("room1", 19, 5,3,3);
        int expectedResult = 1;
        int actualResult = room1.hashCode();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
     void seeIfPrintRoomWorks() {
        Room room = new Room("room1", 1, 1,2,2);
        String result = room.printRoom();
        assertEquals("room1, 1, 1.0, 2.0, 2.0.\n", result);
    }


    @Test
    void seeNominalPowerOfRoomWithoutDevices(){
        //ARRANGE
        Room room1 = new Room("room1", 19, 5,3,3);
        double expectedResult = 0;
        //ACT
        double actualResult = room1.getNominalPower();
        //ASSERT
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeNominalPowerOfRoom(){
        //ARRANGE
        Room room1 = new Room("room1", 19, 5,3,3);
        Device d1 = new Device();
        d1.getNominalPower();
        double expectedResult = 0;
        //ACT
        double actualResult = room1.getNominalPower();
        //ASSERT
        Assertions.assertEquals(expectedResult, actualResult);
    }


}