package Sprint_0;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SensorTest {
    @Test
    public void seeIfSetGetNameWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        c.setName("Temperatura");
        String expectedResult = "Temperatura";
        String actualResult;

        //Act
        actualResult = c.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void seeIfSetGetNameWorksSecondName() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        c.setName("Temperatura");
        c.setName("Chuva");
        String expectedResult = "Chuva";
        String actualResult;

        //Act
        actualResult = c.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetGetNameWorksNoSet() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        String expectedResult = "Vento";
        String actualResult;

        //Act
        actualResult = c.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetNameWorksNull() {
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            c.setName(null);
        });
    }

    @Test
    public void seeIfSetNameWorksEmpty() {
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            c.setName("");
        });
    }

    @Test
    public void seeIfSetGetLocalWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
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
    public void seeIfSetGetLocalWorksSecondLocal() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        Local testLocal1 = new Local(34, 2, 110);
        Local testLocal2 = new Local(30, 20, 110);
        Local expectedResult = new Local(30, 20, 110);
        Local actualResult;

        //Act
        c.setLocal(testLocal1);
        c.setLocal(testLocal2);
        actualResult = c.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfSetGetLocalWorksNoSet() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        Local expectedResult = new Local(12, 31, 21);
        Local actualResult;

        //Act
        actualResult = c.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfGetSetTypeSensorWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
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
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
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
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        ReadingList rl1 = new ReadingList();
        Reading reading1 = new Reading(15, new GregorianCalendar(118, 11, 25).getTime());
        rl1.addReading(reading1);

        //Act
        c.setReadingList(rl1);
        ReadingList expectedResult = rl1;
        ReadingList result = c.getReadingList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void seeIfEqualsWorksNotAnInstanceFalse() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        Local l1 = new Local(21, 23, 1);
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = s1.equals(l1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsWorksFalseDifferentSensor() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Chuva"),
                new Local(10, 30, 20), new Date());
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = s1.equals(s2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsWorksFalseDifferentName() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = s1.equals(s2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void seeIfEqualsWorksTrueSameSensor() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        Sensor s2 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        boolean expectedResult = true;
        boolean actualResult;

        //Act
        actualResult = s1.equals(s2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsWorksTrueSameName() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        Sensor s2 = new Sensor("Vento", new TypeSensor("Chuva"),
                new Local(10, 30, 20), new Date());
        boolean expectedResult = true;
        boolean actualResult;

        //Act
        actualResult = s1.equals(s2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void hashCodeDummyTest() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere"),
                new Local(12, 31, 21), new Date());
        int expectedResult = 1;
        int actualResult = s1.hashCode();
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void seeIfcalculateDistanceToSensorWorks() {
        Local l1 = new Local(23, 46);
        Local l2 = new Local(25, 47);
        TypeSensor t1 = new TypeSensor("Termometro");
        TypeSensor t2 = new TypeSensor("Lololometro");
        Sensor s1 = new Sensor("Sensor1", t1, l1, new Date());
        Sensor s2 = new Sensor("Sensor2", t2, l2, new Date());
        double result = s1.calculateDistanceToSensor(s2);
        double expectedresult = 244;
        assertEquals(expectedresult, result, 1);
    }

    @Test
    public void seeGetMeanOfMonthThroughSensor() {
        ReadingList rList = new ReadingList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature"), new Local(15, 23), new Date(), rList);
        GregorianCalendar g0 = new GregorianCalendar(2018, 9, 31, 23, 59, 59);
        GregorianCalendar g1 = new GregorianCalendar(2018, 10, 1, 0, 0, 0);
        GregorianCalendar g2 = new GregorianCalendar(2018, 10, 23, 23, 26, 21);
        GregorianCalendar g3 = new GregorianCalendar(2018, 10, 27, 8, 21, 22);
        GregorianCalendar g4 = new GregorianCalendar(2018, 10, 23, 18, 14, 3);
        GregorianCalendar g5 = new GregorianCalendar(2018, 10, 23, 12, 14, 23);
        GregorianCalendar g6 = new GregorianCalendar(2018, 10, 28, 12, 12, 12);
        GregorianCalendar g7 = new GregorianCalendar(2018, 10, 30, 23, 59, 59);
        GregorianCalendar g8 = new GregorianCalendar(2018, 11, 01, 00, 00, 00);
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

        double expectedResult = 23.5;
        double result = s1.calculateMonthMeanOnSensor(s1, 2018, 10);
        assertEquals(expectedResult, result, 0.1);
    }
}

