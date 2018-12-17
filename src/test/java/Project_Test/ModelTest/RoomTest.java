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
        Reading r1 = new Reading(30,d2);
        listR.addReading(r1);
        Sensor s1  = new Sensor("sensor1",tipo,new Local(1,1),new Date(),listR);
        list.addSensor(s1);
        Room room = new Room("quarto",1,80, list);
        double result = room.getMaxTemperatureInARoomOnAGivenDay(d2);
        double expectedResult = 30.0;
        assertEquals(expectedResult,result,0.01);


    }
}
