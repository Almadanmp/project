package Sprint_0;


import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Sensor_Test {
    @Test
    public void seeIfSetGetNameWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new Date());
        c.setName("Temperatura");
        String expectedResult = "Temperatura";
        String actualResult;

        //Act
        actualResult = c.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetGetLocalWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new Date());
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
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new Date());
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
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new Date());
        Calendar myCalendar = new GregorianCalendar(2014, Calendar.FEBRUARY, 11);
        Date expectedResult = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date actualResult;

        //Act
        c.setDateStartedFunctioning(myCalendar.getTime());
        actualResult = c.getDateStartedFunctioning();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSeTAndGetReadingList() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new Date());
        ReadingList rl1 = new ReadingList();
        Reading reading1 = new Reading(15, new GregorianCalendar(118, 11, 25));
        rl1.addReading(reading1);

        //Act
        c.setReadingList(rl1);
        ReadingList expectedResult = rl1;
        ReadingList result = c.getReadingList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfEqualsWorksNotAnInstance() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new Date());
        Local l1 = new Local(21, 23, 1);
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = s1.equals(l1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void hashCodeDummyTest() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"), new Local(12, 31, 21), new Date());
        int expectedResult = 1;
        int actualResult = s1.hashCode();
        assertEquals(expectedResult, actualResult);
    }
}

