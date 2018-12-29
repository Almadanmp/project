package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Sensor;
import pt.ipp.isep.dei.project.model.SensorList;
import pt.ipp.isep.dei.project.model.TypeSensor;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US05ControllerTest {
    @Test
    public void seeIfConstructorWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList(new Sensor[]{s1, s2});
        SensorList expectedResult = new SensorList();


        //Act
        expectedResult.addSensor(s1);
        expectedResult.addSensor(s2);
        US05Controller constructedList = new US05Controller(lc);
        SensorList actualResult = constructedList.getSensorList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetTypeWorksFalse() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList();
        lc.addSensor(s1);
        lc.addSensor(s2);
        boolean expectedResult = false;
        US05Controller ctrl = new US05Controller(lc);

        //Act
        boolean actualResult = ctrl.setTypeSensor("Portugal", "Movement");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetTypeWorks() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList();
        lc.addSensor(s1);
        lc.addSensor(s2);
        boolean expectedResult = true;
        US05Controller ctrl = new US05Controller(lc);

        //Act
        boolean actualResult = ctrl.setTypeSensor("Vento", "Movement");

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}
