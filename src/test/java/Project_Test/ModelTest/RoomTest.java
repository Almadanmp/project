package Project_Test.ModelTest;

import PT.IPP.ISEP.DEI.Project.Model.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomTest {

    @Test
    public void seeIfgetMaxTemperatureInARoomOnAGivenDayWorks(){
        SensorList list = new SensorList();
        TypeSensor tipo = new TypeSensor("temperature");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018,2,2).getTime();
        Reading r1;
        Reading r2;
        r1 = new Reading(30,d2);
        r2 = new Reading(20, d2);
        listR.addReading(r1);
        listR.addReading(r2);
        Sensor s1  = new Sensor("sensor1",tipo,new Local(1,1),new Date(),listR);
        list.addSensor(s1);
        Room room = new Room("quarto",1,80, list);
        double result = room.getMaxTemperatureInARoomOnAGivenDay(d2);
        double expectedResult = 30.0;
        assertEquals(expectedResult,result,0.01);


    }
    @Test
    public void seeIfGetCurrentRoomTemperatureWorks(){
        SensorList list = new SensorList();
        TypeSensor type = new TypeSensor("temperature");
        ReadingList listR = new ReadingList();
        Date d2 = new GregorianCalendar(2018,2,1,16,30).getTime();
        Date d3 = new GregorianCalendar(2018,2,1,20,30).getTime();
        Reading r1;
        Reading r2;
        r1 = new Reading(30,d2);
        r2 = new Reading(20, d3);
        listR.addReading(r1);
        listR.addReading(r2);
        Sensor s1  = new Sensor("sensor1",type,new Local(1,1),new Date(),listR);
        list.addSensor(s1);
        Room room = new Room("quarto",1,80, list);
        double result = room.getCurrentRoomTemperature(d2);
        double expectedResult = 20.0;
        assertEquals(expectedResult,result,0.01);


    }

}
