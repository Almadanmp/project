package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    private SimpleDateFormat validSdf1; // SimpleDateFormat dd/MM/yyyy
    private SimpleDateFormat validSdf2; // SimpleDateFormat dd/MM/yyyy HH:mm:ss
    private Date validDate1; // Date 09/08/2018
    private Date validDate2; // Date 11/02/2014
    private TypeSensor validTypeSensor1; // Sensor Type temperature with unit Celsius;
    private String validName1 = "Sensor XXV5"; // Sensor name "Sensor XXV5"
    private Local validLocal1; // Localization with Latitude:38, Longitude:7, Altitude: 5
    private TypeArea validTypeArea1; // Area of Type 'Country'

    @BeforeEach
    void arrangeArtifacts() {
        validTypeArea1 = new TypeArea("Country");
        validLocal1 = new Local(38,7,5);
        validTypeSensor1 = new TypeSensor("Temperature", "Celsius");
        validSdf1 = new SimpleDateFormat("dd/MM/yyyy");
        validSdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate2 = validSdf1.parse("11/02/2014");
            validDate1 = validSdf1.parse("09/08/2018");
        } catch (ParseException c) {
            c.printStackTrace();
        }
    }


    @Test
    void seeIfConstructorSetsTypeArea() {
        //Arrange
        TypeSensor actualResult;
        Sensor c = new Sensor(validName1, validTypeSensor1,validLocal1, validDate1);

        //Act
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(validTypeSensor1, actualResult);
    }

    @Test
    void seeIfConstructorSetsDate() {
        //Arrange
        Date actualResult;
        Sensor c = new Sensor(validName1, validTypeSensor1,validLocal1, validDate1);

        //Act
        actualResult = c.getDateStartedFunctioning();

        //Assert
        assertEquals(validDate1, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsTypeArea() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        Sensor c = new Sensor(validName1, validTypeSensor1,validLocal1, validDate1);
        c.setReadingList(rl1);

        //Act
        TypeSensor actualResult = c.getTypeSensor();

        //Assert
        assertEquals(validTypeSensor1, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsDate() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        Sensor c = new Sensor(validName1, validTypeSensor1,validLocal1, validDate1);
        c.setReadingList(rl1);
        //Act
        Date actualResult = c.getDateStartedFunctioning();

        //Assert
        assertEquals(validDate1, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsLocal() {
        //Arrange
        ReadingList rl1 = new ReadingList();
        Sensor c = new Sensor(validName1, validTypeSensor1,validLocal1, validDate1);
        c.setReadingList(rl1);
        //Act
        Local actualResult = c.getLocal();

        //Assert
        assertEquals(validLocal1, actualResult);
    }

    @Test
    void seeIfSetGetNameWorks() {
        //Arrange
        Sensor c = new Sensor(validName1,validTypeSensor1,validLocal1,validDate1);
        c.setName("XXB6");
        String expectedResult = "XXB6";
        String actualResult;

        //Act
        actualResult = c.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetNameWorksWithoutSet() {
        //Arrange
        Sensor c = new Sensor(validName1,validTypeSensor1,validLocal1,validDate1);
        //Act
        String actualResult = c.getName();

        //Assert
        assertEquals(validName1, actualResult);
    }

    @Test
    void seeIfNullSensorNameThrowsStringMessage() {
        //Arrange
        Sensor c = new Sensor(validName1, validTypeSensor1,validLocal1,validDate1);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            c.setName(null);
        });

        //Assert
        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfEmptySensorNameThrowsException() {
        //Arrange
        Sensor c = new Sensor(validName1,validTypeSensor1,validLocal1,validDate1);

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
        Sensor c = new Sensor(validName1,validTypeSensor1,validLocal1,validDate1);
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
    void seeIfSetGetLocalWorksWithoutSet() {
        //Arrange
        Sensor c = new Sensor(validName1,validTypeSensor1, new Local(12, 31, 21),validDate1);
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
        Sensor c = new Sensor(validName1, new TypeSensor("Wind Pressure", "km/h"),validLocal1,validDate1);
        TypeSensor expectedResult = validTypeSensor1;
        TypeSensor actualResult;

        //Act
        c.setTypeSensor(validTypeSensor1);
        actualResult = c.getTypeSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetDateStartedFunctioningWorks() {
        //Arrange
        Sensor c = new Sensor(validName1,validTypeSensor1,validLocal1,validDate1);
        Date expectedResult = validDate2;

        //Act
        c.setDateStartedFunctioning(validDate2);
        Date actualResult = c.getDateStartedFunctioning();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSeTAndGetReadingList() {
        //Arrange
        Sensor c = new Sensor(validName1,validTypeSensor1,validLocal1,validDate1);
        ReadingList rList1 = new ReadingList();
        Reading reading1 = new Reading(15,validDate2);
        rList1.addReading(reading1);

        //Act
        c.setReadingList(rList1);
        ReadingList result = c.getReadingList();

        //Assert
        assertEquals(rList1, result);
    }

    @Test
    void seeIfEqualsWorksNotAnInstanceFalse() {
        //Arrange
        Sensor s1 = new Sensor(validName1,validTypeSensor1,validLocal1,validDate1);
        Local l1 = new Local(21, 23, 1);

        //Act
        boolean actualResult = s1.equals(l1);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentSensor() {
        //Arrange
        Sensor s1 = new Sensor(validName1,validTypeSensor1,validLocal1,validDate1);
        Sensor s2 = new Sensor("Temperature Sensor XX56",validTypeSensor1,validLocal1,validDate1);

        //Act
        boolean actualResult = s1.equals(s2);
        boolean expectedResult = false;

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentName() {
        //Arrange
        Sensor s1 = new Sensor("Wind Sensor 1",validTypeSensor1,validLocal1,validDate1);
        Sensor s2 = new Sensor("Rain Sensor 1",validTypeSensor1,validLocal1,validDate1);
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
        Sensor s1 = new Sensor(validName1,validTypeSensor1,validLocal1,validDate2);
        Sensor s2 = new Sensor(validName1,validTypeSensor1,validLocal1,validDate2);
        boolean expectedResult = true;

        //Act
        boolean actualResult = s1.equals(s2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksTrueSameName() {
        //Arrange
        Sensor s1 = new Sensor(validName1, new TypeSensor("Atmosphere", "km/h"),validLocal1,validDate2);
        Sensor s2 = new Sensor(validName1, new TypeSensor("Rain", "mm"),validLocal1,validDate2);
        boolean expectedResult = true;

        //Act

        boolean actualResult = s1.equals(s2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        //Arrange
        Sensor s1 = new Sensor(validName1,validTypeSensor1,validLocal1,validDate1);
        int expectedResult = 1;

        //Act
        int actualResult = s1.hashCode();

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfcalculateDistanceToSensorWorks() {
        //Arrange
        Local l1 = new Local(23, 46, 5);
        Local l2 = new Local(25, 47, 5);
        Sensor s1 = new Sensor("Sensor1",validTypeSensor1, l1,validDate1);
        Sensor s2 = new Sensor("Sensor2",validTypeSensor1, l2,validDate1);
        double expectedResult = 244;

        //Act
        double result = s1.calculateDistanceToSensor(s2);

        //Assert
        assertEquals(expectedResult, result, 1);
    }

    @Test
    void seeGetMeanOfMonthThroughSensor() {
        //Arrange
        ReadingList rList = new ReadingList();
        Sensor sensor1 = new Sensor(validName1,validTypeSensor1,validLocal1,validDate2);
        sensor1.setReadingList(rList);
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
        try {
            d1 = validSdf2.parse("31/09/2018 23:59:59");
            d2 = validSdf2.parse("01/10/2018 00:00:00");
            d3 = validSdf2.parse("23/10/2018 23:26:21");
            d4 = validSdf2.parse("27/10/2018 08:21:22");
            d5 = validSdf2.parse("23/10/2018 18:14:03");
            d6 = validSdf2.parse("23/10/2018 12:14:23");
            d7 = validSdf2.parse("28/10/2018 12:12:12");
            d8 = validSdf2.parse("30/10/2018 23:59:59");
            d9 = validSdf2.parse("01/11/2018 00:00:00");
            dateToTest = validSdf2.parse("07/10/2018 00:00:00");
        } catch (ParseException c) {
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

        //Act
        double result = sensor1.calculateMonthMeanOnSensor(sensor1, dateToTest);

        //Assert
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    void seeIfSensorIsContainedInArea() {
        //Arrange
        Local loc1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, loc1);
        Local loc2 = new Local(20, 20, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc2,validDate1);

        //Act
        a1.setWidth(20);
        a1.setLength(30);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedInAreaNegative() {
        //Arrange
        Local loc1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, -10, -20, loc1);
        Local loc2 = new Local(20, 20, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc2,validDate1);

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
        Local loc1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, loc1);
        Local loc2 = new Local(10, 30, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1,loc2,validDate1);

        //Act
        a1.setWidth(20);
        a1.setLength(30);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnTheEdgeNegative() {
        //Arrange
        Local loc1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, -10, -20, loc1);
        Local loc2 = new Local(10, 30, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1,loc2,validDate1);

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
        Local loc1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, loc1);
        Local loc2 = new Local(30, 30, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc2,validDate1);

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
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(10, 30, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

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
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(10, 10, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

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
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(30, 10, 5);
        Sensor s1 = new Sensor("XV56-LD1",validTypeSensor1, loc1,validDate1);

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
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(35, 20, 5);
        Sensor s1 = new Sensor("XV56-LD1",validTypeSensor1, loc1,validDate1);

        //Act
        a1.setWidth(21);
        a1.setLength(32);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLatitudeNegative() {
        //Arrange
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, -10, -20, l1);
        Local loc1 = new Local(35, 20, 5);
        Sensor s1 = new Sensor("XV56-LD1",validTypeSensor1, loc1,validDate1);

        //Act
        a1.setWidth(21);
        a1.setLength(32);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLongitude() {
        //Arrange
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(100, 100, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

        //Act
        a1.setWidth(10);
        a1.setLength(10);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLongitudeNegative() {
        //Arrange
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, -10, -20, l1);
        Local loc1 = new Local(100, 20, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

        //Act
        a1.setWidth(10);
        a1.setLength(10);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfSensorIsContainedInAreaNegativeCoords() {
        //Arrange
        Local l1 = new Local(20, 20, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(-5, -5, -5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

        //Act
        a1.setWidth(50);
        a1.setLength(50);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnTheEdge2() {
        //Arrange
        Local l1 = new Local(0, 40, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(10, 30, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

        //Act
        a1.setWidth(20);
        a1.setLength(30);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnTheEdge2Negative() {
        //Arrange
        Local l1 = new Local(0, 40, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, -10, -20, l1);
        Local loc1 = new Local(10, 30, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

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
        Local l1 = new Local(40, 40, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(30, 30, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

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
        Local l1 = new Local(0, 40, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(10, 30, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

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
        Local l1 = new Local(0, 0, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(10, 10, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

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
        Local l1 = new Local(40, 0, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(30, 10, 5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

        //Act
        a1.setWidth(23);
        a1.setLength(32);
        boolean result = s1.isSensorContainedInArea(a1);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedInAreaNegativeCoords2() {
        //Arrange
        Local l1 = new Local(-30, -30, 5);
        GeographicArea a1 = new GeographicArea("Portugal",validTypeArea1, 10, 20, l1);
        Local loc1 = new Local(-5, -5, -5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);

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
        //Arrange

        Sensor s1 = new Sensor(validName1,validTypeSensor1,validLocal1,validDate1);
        String expectedResult = validName1+", Temperature, 38.0º lat, 7.0º long\n";

        //Act
        String result = s1.buildSensorString();

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSecondConstructorSetsTypeSensorCorretly() {
        //Arrange
        Sensor c = new Sensor(validName1,validTypeSensor1,validDate1);

        //Act
        TypeSensor actualResult = c.getTypeSensor();

        //Assert
        assertEquals(validTypeSensor1, actualResult);
    }

    @Test
    void SeeIfSensorIsActiveOnGivenDate() {
        //Arrange
        Sensor c = new Sensor(validName1,validTypeSensor1,validDate1);
        Date d2 = new Date();
        try {
            d2 = validSdf1.parse("10/08/2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean expectedResult = true;

        //Act
        boolean actualResult = c.isSensorActiveOnGivenDate(d2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void SeeIfSensorIsNotActive() {
        //Arrange
        Sensor c = new Sensor(validName1,validTypeSensor1,validDate1);
        Date d2 = new Date();
        try {
            d2 = validSdf1.parse("07/08/2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean expectedResult = false;

        //Act
        boolean actualResult = c.isSensorActiveOnGivenDate(d2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void ensureThatWeGetDistanceToHouse() {
        //Arrange
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "4512", "Porto");
        House house = new House("Casa 1", address, new Local(4, 6, 6),
                new GeographicArea("Porto",validTypeArea1, 2, 3,
                        new Local(4, 4, 100)), 60, 180, deviceTypeString);
        Local loc1 = new Local(-5, -5, -5);
        Sensor s1 = new Sensor(validName1,validTypeSensor1, loc1,validDate1);
        double expectedResult = 1579.28;

        //Act
        double actualResult = s1.getDistanceToHouse(house);

        //Assert
        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void addReadings() {
        Sensor sensor1 = new Sensor(validName1,validTypeSensor1, validDate1);
        Reading reading1 = new Reading(20, validDate1);
        Reading reading2 = new Reading(20, validDate2);
        Reading reading3 = new Reading(25, validDate1);
        Reading reading4 = new Reading(20, validDate1);

        //act
        boolean actualResult1 = sensor1.addReading(reading1);
        boolean actualResult2 = sensor1.addReading(reading2);
        boolean actualResult3 = sensor1.addReading(reading3);
        boolean actualResult4 = sensor1.addReading(reading4);

        //assert
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertTrue(actualResult3);
        assertFalse(actualResult4);
    }
}

