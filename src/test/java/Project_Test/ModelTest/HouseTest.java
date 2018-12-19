package Project_Test.ModelTest;


import PT.IPP.ISEP.DEI.Project.Model.*;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HouseTest {

    @Test
    public void seeIfCalculateDistanceToSensorWorks(){
        House house = new House("rua coise e tal",new Local(4,5),"447-56");
        Sensor s = new Sensor("sensr1",new TypeSensor("temperatura"),new Local(4,6),new GregorianCalendar(2018,10,1).getTime());
        double result = house.calculateDistanceToSensor(s);
        assertEquals(110.91871788829754,result,0.01);
    }

    @Test
    public void seeIfgetTheMinorDistanceFromTheHouseToTheSensorWorks(){
        Sensor s1 = new Sensor("sensr1",new TypeSensor("temperatura"),new Local(4,6),new GregorianCalendar(2018,10,1).getTime());
        Sensor s2 = new Sensor("sensr2",new TypeSensor("temperatura"),new Local(4,8),new GregorianCalendar(2018,10,1).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        House house = new House("casa de praia","rua coise e tal",new Local(4,5),"447-56",new GeographicArea(new TypeArea("cidade"),new Local(6,7),sensorList));
        double result = house.getTheMinorDistanceFromTheHouseToTheSensor();
        assertEquals(110.91871788829754,result,0.01);
    }

    @Test
    public void seeIfgetTheMinorDistanceFromTheHouseToTheSensorWorks2(){
        Sensor s1 = new Sensor("sensr1",new TypeSensor("temperatura"),new Local(4,8),new GregorianCalendar(2018,10,1).getTime());
        Sensor s2 = new Sensor("sensr5",new TypeSensor("temperatura"),new Local(4,6),new GregorianCalendar(2018,10,1).getTime());
        SensorList sensorList = new SensorList(s1);
        sensorList.addSensor(s2);
        House house = new House("casa de rua","rua coise e tal",new Local(4,5),"447-56",new GeographicArea(new TypeArea("cidade"),new Local(6,7),sensorList));
        double result = house.getTheMinorDistanceFromTheHouseToTheSensor();
        assertEquals(110.91871788829754,result,0.01);
    }

}

