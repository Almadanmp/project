package Project_Test.ControllerTest;

import PT.IPP.ISEP.DEI.Project.Controller.US253Controller;
import PT.IPP.ISEP.DEI.Project.Controller.US620Controller;
import PT.IPP.ISEP.DEI.Project.Model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.*;

public class US253ControllerTest {

    @Test
    public void seeIfSensorIsAddedToRoom() {
        //Arrange

        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Pluviosidade", new TypeSensor("Pluviosidade"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 12, 4).getTime());

        SensorList slist1 = new SensorList();

        Room Bathroom1 = new Room("Bathroom",1,3);
        Bathroom1.setRoomSensorList(slist1);

        //Act

        US253Controller crl = new US253Controller();
        crl.addSensorToRoom(Bathroom1,s2);
        crl.addSensorToRoom(Bathroom1,s1);
        SensorList actualResult = crl.getSensorsFromRoom();
        SensorList expectedResult = slist1;
        //Assert
        assertEquals(expectedResult, actualResult);
    }

}