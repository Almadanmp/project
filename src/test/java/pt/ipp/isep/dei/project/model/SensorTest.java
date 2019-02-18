package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Sensor tests class.
 */

class SensorTest {

    // Common artifacts for testing in this class.
    public static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";


    @Test
    void seeIfFirstConstructorSetsTypeArea() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Local l1 = new Local(38, 7, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        TypeSensor actualResult;
        Sensor c = new Sensor(name, t1, l1, d1);
        //Act
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(t1, actualResult);
    }

    @Test
    void seeIfFirstConstructorSetsDate() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Local l1 = new Local(38, 7, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Date actualResult;
        Sensor c = new Sensor(name, t1, l1, d1);
        //Act
        actualResult = c.getDateStartedFunctioning();

        //Assert
        assertEquals(d1, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsTypeArea() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Local l1 = new Local(38, 7, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        ReadingList rl1 = new ReadingList();
        TypeSensor actualResult;
        Sensor c = new Sensor(name, t1, l1, d1);
        c.setReadingList(rl1);
        //Act
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(t1, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsDate() {
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Local l1 = new Local(38, 7, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        ReadingList rl1 = new ReadingList();
        Date actualResult;
        Sensor c = new Sensor(name, t1, l1, d1);
        c.setReadingList(rl1);
        //Act
        actualResult = c.getDateStartedFunctioning();

        //Assert
        assertEquals(d1, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsLocal() {
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Local l1 = new Local(38, 7, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        ReadingList rl1 = new ReadingList();
        Local actualResult;
        Sensor c = new Sensor(name, t1, l1, d1);
        c.setReadingList(rl1);
        //Act
        actualResult = c.getLocal();

        //Assert
        assertEquals(l1, actualResult);
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
        Date myCalendar = new Date();
        SimpleDateFormat sd2 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            myCalendar = sd2.parse("11/02/2014");
        }
        catch (ParseException c2){
            c2.printStackTrace();
        }
        Date expectedResult = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            expectedResult = sd.parse("11/02/2014");
        }
        catch (ParseException p){
            p.printStackTrace();
        }
        Date actualResult;

        //Act
        c.setDateStartedFunctioning(myCalendar);
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
        Date d1 = new Date();
        SimpleDateFormat sd2 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd2.parse("25/11/118");
        }
        catch (ParseException c2){
            c2.printStackTrace();
        }
        Reading reading1 = new Reading(15,d1);
        rl1.addReading(reading1);

        //Act
        c.setReadingList(rl1);
        ReadingList result = c.getReadingList();

        //Assert
        assertEquals(rl1, result);
    }

    @Test
    void seeIfEqualsWorksNotAnInstanceFalse() {
        //Arrange
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "km/h"),
                new Local(12, 31, 21), new Date());
        Local l1 = new Local(21, 23, 1);
        boolean actualResult;

        //Act
        actualResult = s1.equals(l1);

        //Assert
        assertFalse(actualResult);
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
        Local l2 = new Local(25, 47, 5);
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
        Date d1 = new Date();
        Date d2 = new Date();
        Date d3 = new Date();
        Date d4 = new Date();
        Date d5 = new Date();
        Date d6 = new Date();
        Date d7 = new Date();
        Date d8 = new Date();
        Date d9 = new Date();
        Date dateToTest = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            d1 = s.parse("31/09/2018 23:59:59");
            d2 = s.parse("01/10/2018 00:00:00");
            d3 = s.parse("23/10/2018 23:26:21");
            d4 = s.parse("27/10/2018 08:21:22");
            d5 = s.parse("23/10/2018 18:14:03");
            d6 = s.parse("23/10/2018 12:14:23");
            d7 = s.parse("28/10/2018 12:12:12");
            d8 = s.parse("30/10/2018 23:59:59");
            d9 = s.parse("01/11/2018 00:00:00");
            dateToTest = s.parse("07/10/2018 00:00:00");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Reading r0 = new Reading(23, d1);
        Reading r1 = new Reading(23, d2);
        Reading r2 = new Reading(24, d3);
        Reading r3 = new Reading(25, d4);
        Reading r4 = new Reading(26, d5);
        Reading r5 = new Reading(23, d6);
        Reading r6 = new Reading(22, d7);
        Reading r7 = new Reading(23, d8);
        Reading r8 = new Reading(22, d9);
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(20, 20, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(10, 30, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(30, 30, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(10, 30, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(10, 10, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(30, 10, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(35, 20, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(-35, 20, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(100, 100, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(20, -35, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(-5, -5, -5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        Local l1 = new Local(0, 40, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(10, 30, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(30, 30, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(10, 30, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(10, 10, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        Local l1 = new Local(40, 0, 5);
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(30, 10, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(35, 20, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(-35, 20, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(100, 100, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(20, -35, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        GeographicArea a1 = new GeographicArea("Portugal", t1, 10, 20, l1);
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(-5, -5, -5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
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
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);
        String result = s1.buildSensorString();
        Assertions.assertEquals("XV56-LD1, Temperatura, -5.0º lat, -5.0º long\n", result);
    }

    @Test
    void seeIf2ndConstructorSetsTypeArea() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        TypeSensor actualResult;
        Sensor c = new Sensor(name, t1, d1);
        //Act
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(t1, actualResult);
    }

    @Test
    void seeIfEmptyConstructorSetsTypeArea() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        TypeSensor actualResult;
        Sensor c = new Sensor("Sensor", new TypeSensor("tiposensor", "unidades"), new Local(5, 5, 5), new Date());
        c.setTypeSensor(t1);
        c.setName(name);
        c.setDateStartedFunctioning(d1);
        //Act
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(t1, actualResult);
    }

    @Test
    void ensureThatSensorIsActive() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        boolean expectedResult = true;
        Sensor c = new Sensor(name, t1, d1);
        //Act
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d2 = sd.parse("10/08/2018");
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        boolean actualResult = c.isSensorActiveOnGivenDate(d2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatSensorIsNotActive() {
        //Arrange
        String name = "Chuva";
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        boolean expectedResult = false;
        Sensor c = new Sensor(name, t1, d1);
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d2 = sd.parse("07/08/2018");
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        //Act
        boolean actualResult = c.isSensorActiveOnGivenDate(d2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetDistanceToHouse() {
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 6), new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100)), 60, 180, deviceTypeString);
        List<String> test = new ArrayList<>();
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(-5, -5, -5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);
        double expectedResult = 1579.28;

        double actualResult = s1.getDistanceToHouse(house);
        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void ensureThatEqualsRecognizesSameObject() {
        TypeSensor ty1 = new TypeSensor("Temperatura", "Celsius");
        Local loc1 = new Local(100, 100, 5);
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Sensor s1 = new Sensor("XV56-LD1", ty1, loc1, d1);
        boolean actualResult = s1.equals(s1);
        assertTrue(actualResult);
    }

    @Test
    void addReadings() {
        TypeSensor ty1 = new TypeSensor("temperature", "Celsius");
        Date d1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = s.parse("09/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Date d2 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d2 = sd.parse("12/08/2018");
        }
        catch (ParseException c){
            c.printStackTrace();
        }
        Sensor sensor1 = new Sensor("sensor1", ty1, d1);

        Reading reading1 = new Reading(20, d1);
        Reading reading2 = new Reading(20, d2);
        Reading reading3 = new Reading(25, d1);
        Reading reading4 = new Reading(20, d1);
        //ACT
        boolean actualResult1 = sensor1.addReading(reading1);
        boolean actualResult2 = sensor1.addReading(reading2);
        boolean actualResult3 = sensor1.addReading(reading3);
        boolean actualResult4 = sensor1.addReading(reading4);
        //ASSERT
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertTrue(actualResult3);
        assertFalse(actualResult4);
    }


}

