package Project_Test.ControllerTest;


import PT.IPP.ISEP.DEI.Project.Controller.US610Controller;
import PT.IPP.ISEP.DEI.Project.Model.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
class US610ControllerTest {

    @Test
    public void seeIfdoesListContainRoomByNameWorks(){
        RoomList roomList = new RoomList();
        Room room = new Room("cozinha",8,8);
        roomList.addRoom(room);
        US610Controller ctrl = new US610Controller(roomList);
        boolean result = ctrl.doesListContainRoomByName("cozinha");
        boolean expectedResult = true;
        assertEquals(expectedResult,result);
    }

    @Test
    public void seeIfdoesListContainRoomByNameWorks2(){
        RoomList roomList = new RoomList();
        Room room = new Room("cozinha",8,8);
        Room room1 = new Room("cozinha2",8,8);
        roomList.addRoom(room);
        roomList.addRoom(room1);
        US610Controller ctrl = new US610Controller(roomList);
        boolean result = ctrl.doesListContainRoomByName("cozinha2");
        boolean expectedResult = true;
        assertEquals(expectedResult,result);
    }

    @Test
    public void seeIfdoesListContainRoomByNameWorksFalse(){
        RoomList roomList = new RoomList();
        Room room = new Room("cozinha",8,8);
        Room room1 = new Room("cozinha2",8,8);
        roomList.addRoom(room);
        roomList.addRoom(room1);
        US610Controller ctrl = new US610Controller(roomList);
        boolean result = ctrl.doesListContainRoomByName("sala");
        boolean expectedResult = false;
        assertEquals(expectedResult,result);
    }
    @Test
    public void seeIfdoesListContainSensorByNameWorks(){
        RoomList mRoomList = new RoomList();
        ReadingList readingList = new ReadingList();
        Reading reading = new Reading(30,new GregorianCalendar(2018,8,5).getTime());
        Reading reading1 = new Reading(40, new GregorianCalendar(2018,8,5).getTime());
        readingList.addReading(reading);
        readingList.addReading(reading1);
        Sensor sensor1 = new Sensor("sensor",new TypeSensor("temperature"),new Local(4,4),new GregorianCalendar(8,8,8).getTime(),readingList);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(sensor1);
        Room room = new Room("cozinha",8,2,sensorList);
        mRoomList.addRoom(room);
        US610Controller ctrl = new US610Controller(mRoomList);
        boolean result = ctrl.doesSensorListInARoomContainASensorByName("sensor");
        boolean expectedResult = true;
        assertEquals(expectedResult,result);
    }

    @Test
    public void seeIfGetMaxTemperatureInARoomInAGivenDayWorks(){
        RoomList roomList = new RoomList();
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Reading r1;
        Reading r2;
        r1 = new Reading(30, d2);
        r2 = new Reading(20, d2);
        listR.addReading(r1);
        listR.addReading(r2);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1), new Date(), listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, list);
        roomList.addRoom(room);
        US610Controller ctrl = new US610Controller(roomList);
        double result = ctrl.getMaxTemperatureInARoomOnAGivenDay(d2);
        double expectedResult = 30.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void seeIfGetMaxTemperatureInARoomInAGivenDayWorksTwoDays(){
        RoomList roomList = new RoomList();
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Date d3 = new GregorianCalendar(2018, 2, 3).getTime();
        Reading r1 = new Reading(-30, d2);
        Reading r2 = new Reading(20, d2);
        Reading r3 = new Reading(25,d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1), new Date(), listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, list);
        roomList.addRoom(room);
        US610Controller ctrl = new US610Controller(roomList);
        double result = ctrl.getMaxTemperatureInARoomOnAGivenDay(d3);
        double expectedResult = 25.0;
        assertEquals(expectedResult, result, 0.01);
    }
    @Test
    public void seeIfGetMaxTemperatureInARoomInAGivenDayWorksTwoDaysNeg(){
        RoomList roomList = new RoomList();
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018, 2, 2).getTime();
        Date d3 = new GregorianCalendar(2018, 2, 3).getTime();
        Reading r1 = new Reading(-30, d2);
        Reading r2 = new Reading(-20, d2);
        Reading r3 = new Reading(-25, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        listR.addReading(r3);
        Sensor s1 = new Sensor("sensor1", tipo, new Local(1, 1), new Date(), listR);
        list.addSensor(s1);
        Room room = new Room("quarto", 1, 80, list);
        roomList.addRoom(room);
        US610Controller ctrl = new US610Controller(roomList);
        double result = ctrl.getMaxTemperatureInARoomOnAGivenDay(d2);
        double expectedResult = -20.0;
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    public void SeeIfCreateDateWorks(){
        RoomList roomList = new RoomList();
        US610Controller ctrl = new US610Controller(roomList);
        Date result = ctrl.createDate(2018,6,11);
        Date expectedResult = new GregorianCalendar(2018,6,11).getTime();
        assertEquals(expectedResult,result);
    }
}