package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Sensor tests class.
 */

class SensorTest {

    // Common artifacts for testing in this class.

    private Sensor validSensor;

    @BeforeEach
    void arrangeArtifacts() {
        validSensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"), new Date());
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
        validSensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"),
                new Local(31, 15, 3), new Date());

        // Act

        TypeSensor actualResult = validSensor.getTypeSensor();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsLocal() {
        // Arrange

        validSensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"),
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

        ReadingList readingList = new ReadingList();
        Reading reading1 = new Reading(15, new Date());
        readingList.addReading(reading1);

        // Act

        validSensor.setReadingList(readingList);
        ReadingList actualResult = validSensor.getReadingList();

        // Assert

        assertEquals(readingList, actualResult);
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

        Sensor s2 = new Sensor("Temperature Sensor XX56", new TypeSensor("Temperature", "Fahrenheit"),
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
        validSensor = new Sensor("SensOne", new TypeSensor("Temperature", "Celsius"),
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
        Sensor testSensor = new Sensor("Sensor", new TypeSensor("Rainfall", "l/m2"), testLocal2,
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
        Sensor testSensor = new Sensor("SensorOne", new TypeSensor("Pressure", "mm"),
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
        Sensor testSensor = new Sensor("SensorOne", new TypeSensor("Movement", "cm"), upperLeftVertex,
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
        Sensor testSensor = new Sensor("SensorOne", new TypeSensor("Temperature", "Kelvin"), bottomLeftVertex,
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
        Sensor s1 = new Sensor("XV56-LD1", new TypeSensor("Rainfall", "dm/m2"), bottomRightVertex,
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
        Sensor s1 = new Sensor("XV56-LD1", new TypeSensor("Temperature", "Celsius"), wrongLatitude,
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
        Sensor testSensor = new Sensor("XV56-LD1", new TypeSensor("Rainfall", "l/m2"),
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
        Sensor testSensor = new Sensor("XV56-LD1", new TypeSensor("Temperature", "Celsius"),
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
        Sensor s1 = new Sensor("XV56-LD1", new TypeSensor("Temperature", "Kelvin"),
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
        Sensor testSensor = new Sensor("Sensor", new TypeSensor("Temperature", "Fahrenheit"),
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
        Sensor testSensor = new Sensor("Sensor", new TypeSensor("Rainfall", "l/m2"), wrongLongitude,
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
        Sensor testSensor = new Sensor("Sensor", new TypeSensor("Rainfall", "l/m2"), wrongLongitude,
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
        Sensor testSensor = new Sensor("Sensor", new TypeSensor("Rainfall", "l/m2"), wrongLongitude,
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
        Sensor testSensor = new Sensor("Sensor", new TypeSensor("Rainfall", "l/m2"), wrongLongitude,
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
        Sensor testSensor = new Sensor("Sensor", new TypeSensor("Rainfall", "l/m2"), negativeCoords,
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
        Sensor testSensor = new Sensor("Sensor", new TypeSensor("Rainfall", "l/m2"), edge,
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
        Sensor validSensor = new Sensor("Sensor", new TypeSensor("Temperature", "Celsius"), edge, new Date());

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
        Sensor validSensor = new Sensor("Sensor", new TypeSensor("Temperature", "Celsius"),
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
        Sensor testSensor = new Sensor("Sensor", new TypeSensor("Temperature", "Celsius"),
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
        Sensor testSensor = new Sensor("Sensor", new TypeSensor("Temperature", "Celsius"),
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
        Sensor testSensor = new Sensor("Sensor", new TypeSensor("Temperature", "Celsius"),
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
        Sensor s1 = new Sensor("Sensor", new TypeSensor("Temperature", "Celsius"),
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

        validSensor = new Sensor("SensOne", new TypeSensor("Temperature", "Kelvin"), new Local(21,
                31, 15), new Date());
        String expectedResult = "SensOne, Temperature, 21.0º lat, 31.0º long\n";

        // Act

        String result = validSensor.buildString();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSecondConstructorSetsTypeSensorCorretly() {
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
                4, 6, 6), new GeographicArea("Porto", new TypeArea("City"),
                2, 3, new Local(4, 4, 100)), 60, 180,
                new ArrayList<>());
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
    void seeIfGetDateStartedFunctioningWorks() {
        // Arrange

        Date expectedResult = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        validSensor.setDateStartedFunctioning(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());

        // Act

        Date actualResult = validSensor.getDateStartedFunctioning();


        // Assert

        assertEquals(expectedResult, actualResult);
    }
}

