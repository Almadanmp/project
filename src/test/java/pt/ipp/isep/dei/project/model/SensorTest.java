package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Sensor tests class.
 */

 class SensorTest {
    @Test
    void seeIfFirstConstructorSetsTypeArea() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Local l1 = new Local(38, 7, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        TypeSensor expectedResult = t1;
        TypeSensor actualResult;
        Sensor c = new Sensor(name, t1, l1, d1);
        //Act
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfFirstConstructorSetsDate() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Local l1 = new Local(38, 7,5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Date expectedResult = d1;
        Date actualResult;
        Sensor c = new Sensor(name, t1, l1, d1);
        //Act
        actualResult = c.getDateStartedFunctioning();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsTypeArea() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Local l1 = new Local(38, 7, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        ReadingList rl1 = new ReadingList();
        TypeSensor expectedResult = t1;
        TypeSensor actualResult;
        Sensor c = new Sensor(name, t1, l1, d1);
        c.setReadingList(rl1);
        //Act
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsDate() {
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Local l1 = new Local(38, 7, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        ReadingList rl1 = new ReadingList();
        Date expectedResult = d1;
        Date actualResult;
        Sensor c = new Sensor(name, t1, l1, d1);
        c.setReadingList(rl1);
        //Act
        actualResult = c.getDateStartedFunctioning();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsLocal() {
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Local l1 = new Local(38, 7, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        ReadingList rl1 = new ReadingList();
        Local expectedResult = l1;
        Local actualResult;
        Sensor c = new Sensor(name, t1, l1, d1);
        c.setReadingList(rl1);
        //Act
        actualResult = c.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetNameWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
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
    void seeIfSetGetNameWorksSecondName() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
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
    void seeIfSetGetNameWorksNoSet() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());
        String expectedResult = "Vento";
        String actualResult;

        //Act
        actualResult = c.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetNameWorksNullAndThrowsStringMessage() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            c.setName(null);
        });

        //Assert
        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfSetNameWorksEmptyAndThrowsStringException() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            c.setName("");
        });

        //Assert
        assertEquals("Please Insert Valid Name", exception.getMessage());
    }


    @Test
    void seeIfSetGetLocalWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
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
    void seeIfSetGetLocalWorksSecondLocal() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
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
    void seeIfSetGetLocalWorksNoSet() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());
        Local expectedResult = new Local(12, 31, 21);
        Local actualResult;

        //Act
        actualResult = c.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetTypeSensorWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());
        TypeSensor testSensor = new TypeSensor("Atmosphere", "km/h");
        TypeSensor expectedResult = new TypeSensor("Atmosphere", "km/h");
        TypeSensor actualResult;

        //Act
        c.setTypeSensor(testSensor);
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetDateStartedFunctioningWorks() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
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
    void seeIfSeTAndGetReadingList() {
        //Arrange
        Sensor c = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
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
    void seeIfEqualsWorksNotAnInstanceFalse() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
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
    void seeIfEqualsWorksFalseDifferentSensor() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Chuva", "l/m2"),
                new Local(10, 30, 20), new Date());
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = s1.equals(s2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentName() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(12, 31, 21), new Date());
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = s1.equals(s2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfEqualsWorksTrueSameSensor() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());
        Sensor s2 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());
        boolean actualResult = s1.equals(s2);
        boolean expectedResult = true;


        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksTrueSameName() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());
        Sensor s2 = new Sensor("Vento", new TypeSensor("Chuva", "km/h"),
                new Local(10, 30, 20), new Date());
        boolean expectedResult = true;
        boolean actualResult = s1.equals(s2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());
        int expectedResult = 1;
        int actualResult = s1.hashCode();
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfcalculateDistanceToSensorWorks() {
        Local l1 = new Local(23, 46, 5);
        Local l2 = new Local(25, 47,5);
        TypeSensor t1 = new TypeSensor("Termometro", "Celsius");
        TypeSensor t2 = new TypeSensor("Lololometro", "looool");
        Sensor s1 = new Sensor("Sensor1", t1, l1, new Date());
        Sensor s2 = new Sensor("Sensor2", t2, l2, new Date());
        double result = s1.calculateDistanceToSensor(s2);
        double expectedresult = 244;
        assertEquals(expectedresult, result, 1);
    }

    @Test
    void seeGetMeanOfMonthThroughSensor() {
        ReadingList rList = new ReadingList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature", "Celsius"), new Local(15, 23, 5), new Date());
        s1.setReadingList(rList);
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

        double expectedResult = 23.71;
        GregorianCalendar gc = new GregorianCalendar(2018, 10, 7);
        Date dateToTest = gc.getTime();
        double result = s1.calculateMonthMeanOnSensor(s1, dateToTest);
        assertEquals(expectedResult, result, 0.1);
    }

    //TESTS for sensorIsContainedInArea
    //Testing latS, longS, latTopVert and longTopVert
    @Test
    void seeIfSensorIsContainedInArea() {
        //Arrange
        TypeArea t1 = new TypeArea("Pantano");
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(20, 20, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

        //Act
        a1.setWidth(20);
        a1.setLength(30);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnTheEdge() {
        //Arrange
        TypeArea t1 = new TypeArea("Pantano");
        Local l1 = new Local(20, 20,5);
        GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(10, 30, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

        //Act
        a1.setWidth(20);
        a1.setLength(30);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnUpperRightVertex() {
        //Arrange
        TypeArea t1 = new TypeArea("Pantano");
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(30, 30, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

        //Act
        a1.setWidth(20);
        a1.setLength(30);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnUpperLeftVertex() {
        //Arrange
        TypeArea t1 = new TypeArea("Pantano");
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(10, 30, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

        //Act
        a1.setWidth(21);
        a1.setLength(30);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnBottomLeftVertex() {
        //Arrange
        TypeArea t1 = new TypeArea("Pantano");
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(10, 10,5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

        //Act
        a1.setWidth(25);
        a1.setLength(35);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnBottomRightVertex() {
        //Arrange
        TypeArea t1 = new TypeArea("Pantano");
        Local l1 = new Local(20, 20,5);
        GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(30, 10,5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

        //Act
        a1.setWidth(23);
        a1.setLength(32);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLatitude() {
        //Arrange
        TypeArea t1 = new TypeArea("Pantano");
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(35, 20, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

        //Act
        a1.setWidth(21);
        a1.setLength(32);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLatitude2() {
        //Arrange
        TypeArea t1 = new TypeArea("Pantano");
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(-35, 20,5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

        //Act
        a1.setLength(20);
        a1.setWidth(25);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLongitude() {
        //Arrange
        TypeArea t1 = new TypeArea("Pantano");
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(100, 100, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

        //Act
        a1.setWidth(10);
        a1.setLength(10);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLongitude2() {
        //Arrange
        TypeArea t1 = new TypeArea("Pantano");
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(20, -35, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

        //Act
        a1.setWidth(100);
        a1.setLength(100);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfSensorIsContainedInAreaNegativeCoords() {
        //Arrange
        TypeArea t1 = new TypeArea("Pantano");
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(-5, -5, -5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

        //Act
        a1.setWidth(50);
        a1.setLength(50);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

     //Testing latLongVert and longBotVert
     //Estes testes são o contrário dos acima, daí o nome com a posição estará invertido
     @Test
     void seeIfSensorIsContainedOnTheEdge2() {
         //Arrange
         TypeArea t1 = new TypeArea("Pantano");
         Local l1 = new Local(0, 40,5);
         GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
         TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
         Local loc1 = new Local(10, 30, 5);
         Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
         Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

         //Act
         a1.setWidth(20);
         a1.setLength(30);
         boolean result = s1.isSensorContainedInArea(a1);

         //Assert
         assertTrue(result);
     }

     @Test
     void seeIfSensorIsContainedOnUpperRightVertex2() {
         //Arrange
         TypeArea t1 = new TypeArea("Pantano");
         Local l1 = new Local(40, 40, 5);
         GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
         TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
         Local loc1 = new Local(30, 30, 5);
         Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
         Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

         //Act
         a1.setWidth(20);
         a1.setLength(30);
         boolean result = s1.isSensorContainedInArea(a1);

         //Assert
         assertTrue(result);
     }

     @Test
     void seeIfSensorIsContainedOnUpperLeftVertex2() {
         //Arrange
         TypeArea t1 = new TypeArea("Pantano");
         Local l1 = new Local(0, 40, 5);
         GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
         TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
         Local loc1 = new Local(10, 30, 5);
         Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
         Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

         //Act
         a1.setWidth(21);
         a1.setLength(30);
         boolean result = s1.isSensorContainedInArea(a1);

         //Assert
         assertTrue(result);
     }

     @Test
     void seeIfSensorIsContainedOnBottomLeftVertex2() {
         //Arrange
         TypeArea t1 = new TypeArea("Pantano");
         Local l1 = new Local(0, 0, 5);
         GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
         TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
         Local loc1 = new Local(10, 10,5);
         Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
         Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

         //Act
         a1.setWidth(25);
         a1.setLength(35);
         boolean result = s1.isSensorContainedInArea(a1);

         //Assert
         assertTrue(result);
     }

     @Test
     void seeIfSensorIsContainedOnBottomRightVertex2() {
         //Arrange
         TypeArea t1 = new TypeArea("Pantano");
         Local l1 = new Local(40, 0,5);
         GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
         TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
         Local loc1 = new Local(30, 10,5);
         Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
         Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

         //Act
         a1.setWidth(23);
         a1.setLength(32);
         boolean result = s1.isSensorContainedInArea(a1);

         //Assert
         assertTrue(result);
     }

     @Test
     void seeIfSensorIsNotContainedInAreaWrongLatitude3() {
         //Arrange
         TypeArea t1 = new TypeArea("Pantano");
         Local l1 = new Local(50, 20, 5);
         GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
         TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
         Local loc1 = new Local(35, 20, 5);
         Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
         Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

         //Act
         a1.setWidth(21);
         a1.setLength(32);
         boolean result = s1.isSensorContainedInArea(a1);

         //Assert
         assertFalse(result);
     }

     @Test
     void seeIfSensorIsNotContainedInAreaWrongLatitude4() {
         //Arrange
         TypeArea t1 = new TypeArea("Pantano");
         Local l1 = new Local(-90, 20, 5);
         GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
         TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
         Local loc1 = new Local(-35, 20,5);
         Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
         Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

         //Act
         a1.setLength(20);
         a1.setWidth(25);
         boolean result = s1.isSensorContainedInArea(a1);

         //Assert
         assertFalse(result);
     }

     @Test
     void seeIfSensorIsNotContainedInAreaWrongLongitude3() {
         //Arrange
         TypeArea t1 = new TypeArea("Pantano");
         Local l1 = new Local(180, 180, 5);
         GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
         TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
         Local loc1 = new Local(100, 100, 5);
         Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
         Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

         //Act
         a1.setWidth(10);
         a1.setLength(10);
         boolean result = s1.isSensorContainedInArea(a1);

         //Assert
         assertFalse(result);
     }

     @Test
     void seeIfSensorIsNotContainedInAreaWrongLongitude4() {
         //Arrange
         TypeArea t1 = new TypeArea("Pantano");
         Local l1 = new Local(20, -90, 5);
         GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
         TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
         Local loc1 = new Local(20, -35, 5);
         Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
         Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

         //Act
         a1.setWidth(100);
         a1.setLength(100);
         boolean result = s1.isSensorContainedInArea(a1);

         //Assert
         assertFalse(result);
     }

     @Test
     void seeIfSensorIsContainedInAreaNegativeCoords2() {
         //Arrange
         TypeArea t1 = new TypeArea("Pantano");
         Local l1 = new Local(-30, -30, 5);
         GeographicArea a1 = new GeographicArea("Portugal", t1,10,20,l1);
         TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
         Local loc1 = new Local(-5, -5, -5);
         Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
         Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);

         //Act
         a1.setWidth(50);
         a1.setLength(50);
         boolean result = s1.isSensorContainedInArea(a1);

         //Assert
         assertTrue(result);
     }
     //FIM de testes isSensorContainedInArea

    @Test
    void seeIfPrintSensor() {
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(-5, -5, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);
        String result = s1.buildSensorString();
        Assertions.assertEquals("XV56-LD1, Temperatura, -5.0º lat, -5.0º long\n", result);
    }

    @Test
    void seeIf2ndConstructorSetsTypeArea() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        TypeSensor expectedResult = t1;
        TypeSensor actualResult;
        Sensor c = new Sensor(name, t1, d1);
        //Act
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEmptyConstructorSetsTypeArea() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        TypeSensor expectedResult = t1;
        TypeSensor actualResult;
        Sensor c = new Sensor("Sensor", new TypeSensor("tiposensor", "unidades"), new Local(5,5,5), new Date());
        c.setTypeSensor(t1);
        c.setName(name);
        c.setDateStartedFunctioning(d1);
        //Act
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatSensorIsActive() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        boolean expectedResult = true;
        Sensor c = new Sensor(name, t1, d1);
        //Act
        boolean actualResult = c.isSensorActiveOnGivenDate(new GregorianCalendar(2018, 8, 10));

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatSensorIsNotActive() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        boolean expectedResult = false;
        Sensor c = new Sensor(name, t1, d1);
        //Act
        boolean actualResult = c.isSensorActiveOnGivenDate(new GregorianCalendar(2018, 8, 7));

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetDistanceToHouse() {
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6,6),new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)),60,180);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(-5, -5, -5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);
        double expectedResult = 1579.28;

        double actualResult = s1.getDistanceToHouse(house);
        assertEquals(expectedResult, actualResult,0.01);
    }

    @Test
    void ensureThatEqualsRecognizesSameObject() {
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(100, 100, 5);
        Date d1 = new GregorianCalendar(2018, 8, 9).getTime();
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);
        boolean expectedResult = true;
        boolean actualResult = s1.equals(s1);

        assertEquals(expectedResult,actualResult);
    }

}

