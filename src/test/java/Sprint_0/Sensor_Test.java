package Sprint_0;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertEquals;

public class Sensor_Test {
    @Test
    public void seeIfSetGetNameWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12,31,21), new Date());
        c.setName("Temperatura");
        String expectedResult = "Temperatura";
        String actualResult;

        //Act
        actualResult = c.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test (expected = IllegalArgumentException.class)
    public void seeIfSetGetNameWorksWithEmptyName() {
        //TODO finish exception test
        Sensor c = new Sensor("", new TypeSensor("Atmosphere"), new Local(12,31,21), new Date());
        String actualResult = c.getName();

    }

    @Test
    public void seeIfSetGetLocalWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12,31,21), new Date());
        Local testLocal = new Local(34, 2, 110);
        Local expectedResult = new Local(34, 2, 110);
        Local actualResult;

        //Act
        c.setLocal(testLocal);
        actualResult = c.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetSetTypeSensorWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12,31,21), new Date());
        TypeSensor testSensor = new TypeSensor("Atmosphere");
        TypeSensor expectedResult = new TypeSensor("Atmosphere");
        TypeSensor actualResult;

        //Act
        c.setTypeSensor(testSensor);
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetSetDateStartedFunctioningWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12,31,21), new Date());
        Calendar myCalendar = new GregorianCalendar(2014, Calendar.FEBRUARY, 11);
        Date expectedResult = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date actualResult;

        //Act
        c.setDateStartedFunctioning(myCalendar.getTime());
        actualResult=c.getDateStartedFunctioning();

        //Assert
        assertEquals(expectedResult,actualResult);
    }
}

