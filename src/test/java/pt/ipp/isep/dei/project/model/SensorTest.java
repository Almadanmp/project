package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertTrue;

/**
 * Sensor tests class.
 */

class SensorTest {

    // Common artifacts for testing in this class.

    private Sensor validSensor;

    @BeforeEach
    void arrangeArtifacts() {
        validSensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"), new Date());
        validSensor.setActive(true);
    }

    @Test
    void seeIfEmptyConstructorWorks() {
        // Arrange
        Sensor sensorABC = new Sensor();
        sensorABC.setId("ABC");
        String expectedResult = "ABC";
        // Act
        String actualResult = sensorABC.getId();
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetSensorList() {
        // Arrange
        SensorList listA = new SensorList();
        SensorList expectedResult = listA;
        // Act
        validSensor.setSensorList(listA);
        SensorList actualResult = validSensor.getSensorList();
        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfConstructorSetsDate() {
        // Arrange

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date startDate = new Date();

        try {
            startDate = validSdf.parse("11/01/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Sensor sensor = new Sensor("Sensor", new TypeSensor("Temperature", "Celsius"), startDate);

        // Act

        Date actualResult = sensor.getDateStartedFunctioning();

        // Assert

        assertEquals(startDate, actualResult);
    }


    @Test
    void seeIfConstructorSetsTypeArea() {
        // Arrange

        TypeSensor expectedResult = new TypeSensor("Temperature", "Celsius");

        // Act

        TypeSensor actualResult = validSensor.getTypeSensor();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsTypeArea() {
        // Arrange

        TypeSensor expectedResult = new TypeSensor("Temperature", "Celsius");
        validSensor = new Sensor("RF12345", "SensOne", new TypeSensor("Temperature", "Celsius"),
                new Local(31, 15, 3), new Date());

        // Act

        TypeSensor actualResult = validSensor.getTypeSensor();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsLocal() {
        // Arrange

        validSensor = new Sensor("RF12345", "SensOne", new TypeSensor("Temperature", "Celsius"),
                new Local(31, 15, 3), new Date());
        Local expectedResult = new Local(31, 15, 3);

        //Act

        Local actualResult = validSensor.getLocal();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetNameWorks() {
        // Arrange

        String expectedResult = "XXB6";

        // Act

        validSensor.setName("XXB6");
        String actualResult = validSensor.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfNullSensorNameThrowsStringMessage() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            validSensor.setName(null);
        });

        // Assert

        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfEmptySensorNameThrowsException() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            validSensor.setName("");
        });

        // Assert

        assertEquals("Please Insert Valid Name", exception.getMessage());
    }


    @Test
    void seeIfSetGetLocalWorks() {
        // Arrange

        Local expectedResult = new Local(31, 11, 11);

        // Act

        validSensor.setLocal(new Local(31, 11, 11));
        Local actualResult = validSensor.getLocal();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetTypeSensorWorks() {
        // Arrange

        TypeSensor expectedResult = new TypeSensor("Rain", "l/m2");
        TypeSensor actualResult;

        // Act

        validSensor.setTypeSensor(new TypeSensor("Rain", "l/m2"));
        actualResult = validSensor.getTypeSensor();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSeTAndGetReadingList() {
        // Arrange
        ReadingList expectedResult1 = new ReadingList();
        ReadingList expectedResult2 = new ReadingList();
        ReadingList emptyList = new ReadingList();
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(15, new Date());

        Sensor sensor1 = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"), new Date());
        Sensor sensor2 = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"), new Date());

        readingList.addReading(reading1);
        expectedResult2.addReading(reading1);


        // Act

        validSensor.setReadingList(readingList);
        sensor1.setReadingList(null);
        sensor2.setReadingList(emptyList);


        ReadingList actualResult = validSensor.getReadingList();
        ReadingList actualResultNull = sensor1.getReadingList();
        ReadingList actualResultEmpty = sensor2.getReadingList();

        // Assert

        assertEquals(readingList, actualResult);
        assertEquals(expectedResult1, actualResultNull);
        assertEquals(expectedResult1, actualResultEmpty);
    }


    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Arrange

        Local testLocal = new Local(21, 23, 1);

        // Act

        boolean actualResult = validSensor.equals(testLocal); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentSensor() {
        // Arrange

        Sensor s2 = new Sensor("RF12345", "Temperature Sensor XX56", new TypeSensor("Temperature", "Fahrenheit"),
                new Local(21, 1, 12), new Date());

        // Act

        boolean actualResult = validSensor.equals(s2);


        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksTrueSameSensor() {
        // Arrange

        Sensor testSensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"), new Date());

        // Act

        boolean actualResult = validSensor.equals(testSensor);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validSensor.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsContainedInArea() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 20, 30,
                new Local(31, 15, 3));
        validSensor = new Sensor("RF12345", "SensOne", new TypeSensor("Temperature", "Celsius"),
                new Local(31, 15, 3), new Date());

        // Act

        boolean result = validSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedEdgeOfArea() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 20,
                30, testLocal);
        Local testLocal2 = new Local(10, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new TypeSensor("Rainfall", "l/m2"), testLocal2,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedInAreaWorksUpperRightVertex() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 20, 30,
                testLocal);
        Local upperRightVertex = new Local(30, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new TypeSensor("Pressure", "mm"),
                upperRightVertex, new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnUpperLeftVertex() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 21,
                30, testLocal);
        Local upperLeftVertex = new Local(10, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new TypeSensor("Movement", "cm"), upperLeftVertex,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnBottomLeftVertex() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 25,
                35, testLocal);
        Local bottomLeftVertex = new Local(10, 10, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new TypeSensor("Temperature", "Kelvin"), bottomLeftVertex,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnBottomRightVertex() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 32,
                23, testLocal);
        Local bottomRightVertex = new Local(30, 10, 5);
        Sensor s1 = new Sensor("RF12345", "XV56-LD1", new TypeSensor("Rainfall", "dm/m2"), bottomRightVertex,
                new Date());

        // Act

        boolean result = s1.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLatitude() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 32,
                21, testLocal);
        Local wrongLatitude = new Local(35, 20, 5);
        Sensor s1 = new Sensor("RF12345", "XV56-LD1", new TypeSensor("Temperature", "Celsius"), wrongLatitude,
                new Date());

        // Act

        boolean result = s1.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLatitudeSecondCondition() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 25,
                20, testLocal);
        Local wrongLatitude = new Local(-35, 20, 5);
        Sensor testSensor = new Sensor("RF12345", "XV56-LD1", new TypeSensor("Rainfall", "l/m2"),
                wrongLatitude, new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLatitudeThirdCondition() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 32,
                21, testLocal);
        Local wrongLatitude = new Local(35, 20, 5);
        Sensor testSensor = new Sensor("RF123451", "XV56-LD1", new TypeSensor("Temperature", "Celsius"),
                wrongLatitude, new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLatitudeFourthCondition() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 25,
                20, testLocal);
        Local wrongLatitude = new Local(-35, 20, 5);
        Sensor s1 = new Sensor("RF12345", "XV56-LD1", new TypeSensor("Temperature", "Kelvin"),
                wrongLatitude, new Date());

        // Act

        boolean result = s1.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLongitude() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 10,
                10, testLocal);
        Local wrongLongitude = new Local(100, 100, 5);
        Sensor testSensor = new Sensor("RF123452", "Sensor", new TypeSensor("Temperature", "Fahrenheit"),
                wrongLongitude, new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLongitudeSecondCondition() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 100,
                100, testLocal);
        Local wrongLongitude = new Local(20, -35, 5);
        Sensor testSensor = new Sensor("RF123453", "Sensor", new TypeSensor("Rainfall", "l/m2"), wrongLongitude,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLongitudeThirdCondition() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 10,
                10, testLocal);
        Local wrongLongitude = new Local(100, 100, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new TypeSensor("Rainfall", "l/m2"), wrongLongitude,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLongitudeFourthCondition() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 100,
                100, testLocal);
        Local wrongLongitude = new Local(20, -35, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new TypeSensor("Rainfall", "l/m2"), wrongLongitude,
                new Date());
        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsNotContainedInAreaWrongLongitudeNegative() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 10,
                10, testLocal);
        Local wrongLongitude = new Local(100, 20, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new TypeSensor("Rainfall", "l/m2"), wrongLongitude,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsContainedInAreaNegativeCoords() {
        // Arrange

        Local testLocal = new Local(20, 20, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 50,
                50, testLocal);
        Local negativeCoords = new Local(-5, -5, -5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new TypeSensor("Rainfall", "l/m2"), negativeCoords,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnTheEdge2() {
        // Arrange

        Local testLocal = new Local(0, 40, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 20,
                30, testLocal);
        Local edge = new Local(10, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new TypeSensor("Rainfall", "l/m2"), edge,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnTheEdge2Negative() {
        // Arrange

        Local testLocal = new Local(0, 40, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 20,
                30, testLocal);
        Local edge = new Local(10, 30, 5);
        Sensor validSensor = new Sensor("RF12345", "Sensor", new TypeSensor("Temperature", "Celsius"), edge, new Date());

        // Act

        boolean result = validSensor.isContainedInArea(testArea);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnUpperRightVertex2() {
        // Arrange

        Local testLocal = new Local(40, 40, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 20,
                30, testLocal);
        Local upperRightVertex = new Local(30, 30, 5);
        Sensor validSensor = new Sensor("RF12345", "Sensor", new TypeSensor("Temperature", "Celsius"),
                upperRightVertex, new Date());

        // Act

        boolean result = validSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnUpperLeftVertex2() {
        // Arrange

        Local testLocal = new Local(0, 40, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 21,
                30, testLocal);
        Local upperLeftVertex = new Local(10, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new TypeSensor("Temperature", "Celsius"),
                upperLeftVertex, new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnBottomLeftVertex2() {
        // Arrange

        Local testLocal = new Local(0, 0, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 35,
                25, testLocal);
        Local bottomLeftVertex = new Local(10, 10, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new TypeSensor("Temperature", "Celsius"),
                bottomLeftVertex, new Date());
        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnBottomRightVertex2() {
        // Arrange

        Local testLocal = new Local(40, 0, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 32,
                23, testLocal);
        Local bottomRightVertex = new Local(30, 10, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new TypeSensor("Temperature", "Celsius"),
                bottomRightVertex, new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedInAreaNegativeCoords2() {
        // Arrange

        Local testLocal = new Local(-30, -30, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 50,
                50, testLocal);
        Local testLocal2 = new Local(-5, -5, -5);
        Sensor s1 = new Sensor("RF12345", "Sensor", new TypeSensor("Temperature", "Celsius"),
                testLocal2, new Date());

        // Act

        boolean result = s1.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    // End of isContainedInArea tests.

    @Test
    void seeIfPrintSensorWorksNoLocal() {
        // Arrange

        String expectedResult = "SensOne, Temperature. ";

        // Act

        String result = validSensor.buildString();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintSensorWorksWithLocal() {
        // Arrange

        validSensor = new Sensor("RF12345", "SensOne", new TypeSensor("Temperature", "Kelvin"), new Local(21,
                31, 15), new Date());
        String expectedResult = "SensOne, Temperature, 21.0º lat, 31.0º long \n";

        // Act

        String result = validSensor.buildString();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintDeactivated() {
        // Arrange

        String expectedResult = "Active";
        // Act

        String result = validSensor.printActive();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintActive() {
        // Arrange

        String expectedResult = "Deactivated";
        validSensor.deactivateSensor();

        // Act

        String result = validSensor.printActive();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfActive() {

        // Act
        validSensor.deactivateSensor();
        // Act
       boolean result = validSensor.deactivateSensor();

        // Assert

        Assertions.assertFalse(result);
    }

    @Test
    void seeIfDeactivates() {

        // Act
        boolean result = validSensor.deactivateSensor();

        // Assert

        Assertions.assertTrue(result);
    }


    @Test
    void seeIfSecondConstructorSetsTypeSensorCorrectly() {
        // Arrange

        validSensor = new Sensor("SensOne", new TypeSensor("Temperature", "Kelvin"), new Date());
        TypeSensor expectedResult = new TypeSensor("Temperature", "Kelvin");

        // Act

        TypeSensor actualResult = validSensor.getTypeSensor();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDistanceToHouseWorks() {
        // Arrange

        House house = new House("House", new Address("Rua das Flores", "4512", "Porto"), new Local(
                4, 6, 6), 60, 180,
                new ArrayList<>());
        house.setMotherArea(new GeographicArea("Porto", new TypeArea("City"),
                2, 3, new Local(4, 4, 100)));
        Local testLocal = new Local(-5, -5, -5);
        validSensor.setLocal(testLocal);
        double expectedResult = 1579.3659688476016;

        //Act
        double actualResult = validSensor.getDistanceToHouse(house);

        //Assert
        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void seeIfAddReadingsWorks() {
        // Arrange

        Reading reading1 = new Reading(20, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());

        // Act

        boolean actualResult1 = validSensor.addReading(reading1);
        boolean actualResult3 = validSensor.addReading(reading1);

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfAddReadingsWorksNotActiveResult() {
        // Arrange

        Reading reading1 = new Reading(20, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        validSensor.deactivateSensor();
        // Act

        boolean actualResult1 = validSensor.addReading(reading1);
        boolean actualResult3 = validSensor.addReading(reading1);

        // Assert

        assertFalse(actualResult1);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfGetDateStartedFunctioningWorks() {
        // Arrange

        Date expectedResult = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        validSensor.setDateStartedFunctioning(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());

        // Act

        Date actualResult = validSensor.getDateStartedFunctioning();


        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetIdWorks() {
        // Arrange

        String expectedResult = "XXB6";

        // Act

        validSensor.setId("XXB6");
        String actualResult = validSensor.getId();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLastColdestDayInIntervalWorks() {
        //Arrange
        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(23, new GregorianCalendar(2018, Calendar.JULY, 1, 10, 30).getTime());
        Reading reading2 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 1, 14, 30).getTime());
        Reading reading3 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 2, 11, 30).getTime());
        Reading reading4 = new Reading(29, new GregorianCalendar(2018, Calendar.JULY, 2, 16, 30).getTime());
        Reading reading5 = new Reading(34, new GregorianCalendar(2018, Calendar.JULY, 3, 9, 30).getTime());
        Reading reading6 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 3, 10, 30).getTime());
        Reading reading7 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 4, 10, 30).getTime());
        Reading reading8 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 4, 15, 30).getTime());
        Reading reading9 = new Reading(12, new GregorianCalendar(2018, Calendar.JULY, 5, 11, 30).getTime());
        Reading reading10 = new Reading(15, new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime());
        Reading reading11 = new Reading(17, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 30).getTime());
        Reading reading12 = new Reading(19, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 35).getTime());
        Reading reading13 = new Reading(20, new GregorianCalendar(2018, Calendar.JULY, 7, 10, 30).getTime());
        Reading reading14 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 7, 14, 30).getTime());
        Reading reading15 = new Reading(26, new GregorianCalendar(2018, Calendar.JULY, 8, 9, 30).getTime());
        Reading reading16 = new Reading(22, new GregorianCalendar(2018, Calendar.JULY, 8, 10, 30).getTime());
        Reading reading17 = new Reading(21, new GregorianCalendar(2018, Calendar.JULY, 9, 13, 30).getTime());
        Reading reading18 = new Reading(25, new GregorianCalendar(2018, Calendar.JULY, 9, 15, 30).getTime());
        Reading reading19 = new Reading(32, new GregorianCalendar(2018, Calendar.JULY, 10, 10, 30).getTime());
        Reading reading20 = new Reading(31, new GregorianCalendar(2018, Calendar.JULY, 10, 15, 30).getTime());
        readingList.addReading(reading1);
        readingList.addReading(reading2);
        readingList.addReading(reading3);
        readingList.addReading(reading4);
        readingList.addReading(reading5);
        readingList.addReading(reading6);
        readingList.addReading(reading7);
        readingList.addReading(reading8);
        readingList.addReading(reading9);
        readingList.addReading(reading10);
        readingList.addReading(reading11);
        readingList.addReading(reading12);
        readingList.addReading(reading13);
        readingList.addReading(reading14);
        readingList.addReading(reading15);
        readingList.addReading(reading16);
        readingList.addReading(reading17);
        readingList.addReading(reading18);
        readingList.addReading(reading19);
        readingList.addReading(reading20);
        validSensor.setReadingList(readingList);
        //Act
        Date actualResult = validSensor.getLastColdestDayInGivenInterval(new GregorianCalendar(2018, Calendar.JULY, 1, 5, 0).getTime(), new GregorianCalendar(2018, Calendar.JULY, 10, 23, 0).getTime());
        //Assert
        assertEquals(new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime(), actualResult);
    }

    @Test
    void seeIfSensorIsContainedConditionBotVertex() {
        // Arrange

        Local testLocal = new Local(20, 10, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 2,
                10, testLocal);
        Local upperLeftVertex = new Local(25, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new TypeSensor("Movement", "cm"), upperLeftVertex,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsContainedLatBotVertex() {
        // Arrange

        Local testLocal = new Local(20, 29, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 2,
                3, testLocal);
        Local upperLeftVertex = new Local(18, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new TypeSensor("Movement", "cm"), upperLeftVertex,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsContainedLatBotVertexLong() {
        // Arrange

        Local testLocal = new Local(20, 29, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 6,
                3, testLocal);
        Local upperLeftVertex = new Local(18, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new TypeSensor("Movement", "cm"), upperLeftVertex,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfSensorIsContainedLatBotVertexBotLong() {
        // Arrange

        Local testLocal = new Local(10, 29, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 10,
                20, testLocal);
        Local upperVertex = new Local(20, 24, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new TypeSensor("Movement", "cm"), upperVertex,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedLatBotVertexBotLongPlusMutation() {
        // Arrange

        Local testLocal = new Local(20, 30, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 1,
                20, testLocal);
        Local upperVertex = new Local(20, 31, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new TypeSensor("Movement", "cm"), upperVertex,
                new Date());

        // Act

        boolean result = testSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    @Test
    void seeIfAddReadingWorksValueDate() {
        // Arrange

        Date validDate1 = new GregorianCalendar(2016, Calendar.NOVEMBER, 15).getTime();
        Date outOfBoundsDate = new GregorianCalendar(2014, Calendar.NOVEMBER, 15).getTime();
        Date dateSensorStartedFunctioning = new GregorianCalendar(2015, Calendar.NOVEMBER, 15).getTime();
        validSensor.setDateStartedFunctioning(dateSensorStartedFunctioning);
        ReadingList expectedResultList = new ReadingList();
        expectedResultList.addReading(new Reading(23.3, validDate1));

        // Act

        boolean actualResult = validSensor.addReading(validDate1, 23.3);
        boolean actualResultFailed = validSensor.addReading(outOfBoundsDate, 31D);
        ReadingList actualResultList = validSensor.getReadingList().get(0).getReadingList();


        //Assert

        Assertions.assertTrue(actualResult);
        assertFalse(actualResultFailed);
        assertEquals(expectedResultList, actualResultList);
    }

    @Test
    void seeIfAddReadingsWorksValueDate() {
        // Arrange

        validSensor.setDateStartedFunctioning(new GregorianCalendar(2018, Calendar.JANUARY, 2).getTime());

        // Act

        boolean addValidReading = validSensor.addReading(new GregorianCalendar(2019, Calendar.JANUARY,
                1).getTime(), 20D);
        boolean addOutOfBoundsReading = validSensor.addReading(new GregorianCalendar(2017, Calendar.FEBRUARY,
                3).getTime(), 12D);
        boolean addOnBoundsReading = validSensor.addReading(new GregorianCalendar(2018, Calendar.JANUARY,
                2).getTime(), 15D);


        // Assert

        assertTrue(addValidReading);
        assertFalse(addOutOfBoundsReading);
        assertTrue(addOnBoundsReading);
    }

    @Test
    void seeIfAddReadingsWorksDeactivatedDevice() {
        // Arrange

        validSensor.setDateStartedFunctioning(new GregorianCalendar(2018, Calendar.JANUARY, 2).getTime());
        validSensor.deactivateSensor();
        // Act

        boolean addValidReading = validSensor.addReading(new GregorianCalendar(2019, Calendar.JANUARY,
                1).getTime(), 20D);


        // Assert

        assertFalse(addValidReading);
    }
    @Test
    void seeIfGetAverageReadingsBetweenDates() {
        // Arrange
        ReadingList validReadingList1;
        validReadingList1 = new ReadingList();
        Date validDate3 = new Date(); // 31/09/2018 23:59:59
        Date validDate4 = new Date(); // 07/10/2018 00:00:00
        Date validDate5 = new Date(); // 08/10/2018 23:26:21
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate3 = validSdf.parse("31/09/2018 23:59:59");
            validDate4 = validSdf.parse("07/10/2018 00:00:00");
            validDate5 = validSdf.parse("08/10/2018 23:26:21");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading r0 = new Reading(20, validDate3);
        Reading r1 = new Reading(25, validDate4);
        Reading r2 = new Reading(30, validDate5);
        validReadingList1.addReading(r0);
        validReadingList1.addReading(r1);
        validReadingList1.addReading(r2);
        double expectedResult = 25;

        // Act

        double actualResult = validReadingList1.getAverageReadingsBetweenDates(validDate3, validDate5);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDates() {
        // Arrange
        ReadingList validReadingList1;
        validReadingList1 = new ReadingList();
        Date validDate3 = new Date(); // 31/09/2018 23:59:59
        Date validDate4 = new Date(); // 07/10/2018 00:00:00
        Date validDate5 = new Date(); // 08/10/2018 23:26:21
        Date validDate6 = new Date(); // 31/09/2018 23:59:59
        Date validDate7 = new Date(); // 07/10/2018 00:00:00
        Date validDate8 = new Date(); // 08/10/2018 23:26:21
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate3 = validSdf.parse("31/09/2018 23:59:59");
            validDate4 = validSdf.parse("07/10/2018 20:00:00");
            validDate5 = validSdf.parse("08/10/2018 23:26:21");
            validDate6 = validSdf.parse("31/09/2018 10:59:59");
            validDate7 = validSdf.parse("07/10/2018 00:00:00");
            validDate8 = validSdf.parse("08/10/2018 13:26:21");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading r0 = new Reading(20, validDate3);
        Reading r1 = new Reading(25, validDate4);
        Reading r2 = new Reading(30, validDate5);
        Reading r3 = new Reading(10, validDate6);
        Reading r4 = new Reading(10, validDate7);
        Reading r5 = new Reading(10, validDate8);
        validReadingList1.addReading(r0);
        validReadingList1.addReading(r1);
        validReadingList1.addReading(r2);
        validReadingList1.addReading(r3);
        validReadingList1.addReading(r4);
        validReadingList1.addReading(r5);
        Date expectedResult = validDate5;

        // Act

        Date actualResult = validReadingList1.getDateHighestAmplitudeBetweenDates(validDate6, validDate5);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetTotalValueReadingsOnGivenDay() {
        // Arrange
        ReadingList validReadingList = new ReadingList();
        Date validDate15 = new Date();
        Date validDate3 = new Date();
        Date validDate7 = new Date();
        Date validDate14 = new Date();
        Date validDate13 = new Date();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat validSdfDay = new SimpleDateFormat("dd/MM/yyyy");
        try {
            validDate3 = validSdf.parse("31/09/2018 23:59:59");
            validDate14 = validSdf.parse("02/10/2018 23:59:00");
            validDate15 = validSdf.parse("03/10/2018 00:00:00");
            validDate7 = validSdf.parse("10/10/2018 18:14:03");
            validDate13 = validSdfDay.parse("03/10/2018");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Reading reading = new Reading(20, validDate15);
        Reading reading2 = new Reading(20, validDate3);
        Reading reading3 = new Reading(20, validDate7);
        Reading reading4 = new Reading(20, validDate14);
        validReadingList.addReading(reading);
        validReadingList.addReading(reading2);
        validReadingList.addReading(reading3);
        validReadingList.addReading(reading4);
        double expectedResult = 20;
        // Act

        double actualResult = validReadingList.getValueReadingsInDay(validDate13);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMostRecentValueReading() {
        // Arrange
        ReadingList validReadingList = new ReadingList();
        Date validDate12 = new Date();
        validReadingList = new ReadingList();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate12 = validSdf.parse("02/11/2015 20:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date testDate = new GregorianCalendar(2018, Calendar.NOVEMBER, 3).getTime();
        Reading earlierReading = new Reading(15, validDate12);
        Reading laterReading = new Reading(30, testDate);
        validReadingList.addReading(earlierReading);
        validReadingList.addReading(laterReading);
        double expectedResult = 30.0;

        // Act
        double result = validReadingList.getMostRecentValue();

        // Assert
        assertEquals(expectedResult, result, 0.01);
    }
}