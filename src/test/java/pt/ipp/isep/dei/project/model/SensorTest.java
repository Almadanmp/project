package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.sensor.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Sensor tests class.
 */

class SensorTest {

    // Common artifacts for testing in this class.

    private Sensor validSensor;

    @BeforeEach
    void arrangeArtifacts() {
        validSensor = new Sensor("SensOne", new SensorType("Temperature", "Celsius"), new Date());
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
        AreaSensorList listA = new AreaSensorList();
        // Act
        validSensor.setAreaSensorList(listA);
        AreaSensorList actualResult = validSensor.getAreaSensorList();
        // Assert
        assertEquals(listA, actualResult);
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

        Sensor sensor = new Sensor("Sensor", new SensorType("Temperature", "Celsius"), startDate);

        // Act

        Date actualResult = sensor.getDateStartedFunctioning();

        // Assert

        assertEquals(startDate, actualResult);
    }


    @Test
    void seeIfConstructorSetsTypeArea() {
        // Arrange

        SensorType expectedResult = new SensorType("Temperature", "Celsius");

        // Act

        SensorType actualResult = validSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsTypeArea() {
        // Arrange

        SensorType expectedResult = new SensorType("Temperature", "Celsius");
        validSensor = new Sensor("RF12345", "SensOne", new SensorType("Temperature", "Celsius"),
                new Local(31, 15, 3), new Date());

        // Act

        SensorType actualResult = validSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsLocal() {
        // Arrange

        validSensor = new Sensor("RF12345", "SensOne", new SensorType("Temperature", "Celsius"),
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

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validSensor.setName(null));

        // Assert

        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfEmptySensorNameThrowsException() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validSensor.setName(""));

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

        SensorType expectedResult = new SensorType("Rain", "l/m2");
        SensorType actualResult;

        // Act

        validSensor.setSensorType(new SensorType("Rain", "l/m2"));
        actualResult = validSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSeTAndGetReadingList() {
        // Arrange
        AreaReadingList expectedResult1 = new AreaReadingList();
        AreaReadingList expectedResult2 = new AreaReadingList();
        AreaReadingList emptyList = new AreaReadingList();
        AreaReadingList areaReadingList = new AreaReadingList();
        AreaReading areaReading1 = new AreaReading(15, new Date(), "C");

        Sensor sensor1 = new Sensor("SensOne", new SensorType("Temperature", "Celsius"), new Date());
        Sensor sensor2 = new Sensor("SensOne", new SensorType("Temperature", "Celsius"), new Date());

        areaReadingList.addReading(areaReading1);
        expectedResult2.addReading(areaReading1);


        // Act

        validSensor.setAreaReadingList(areaReadingList);
        sensor1.setAreaReadingList(null);
        sensor2.setAreaReadingList(emptyList);


        AreaReadingList actualResult = validSensor.getAreaReadingList();
        AreaReadingList actualResultNull = sensor1.getAreaReadingList();
        AreaReadingList actualResultEmpty = sensor2.getAreaReadingList();

        // Assert

        assertEquals(areaReadingList, actualResult);
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

        Sensor s2 = new Sensor("RF12345", "Temperature Sensor XX56", new SensorType("Temperature", "Fahrenheit"),
                new Local(21, 1, 12), new Date());

        // Act

        boolean actualResult = validSensor.equals(s2);


        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksTrueSameSensor() {
        // Arrange

        Sensor testSensor = new Sensor("SensOne", new SensorType("Temperature", "Celsius"), new Date());

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

        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 20, 30,
                new Local(31, 15, 3));
        validSensor = new Sensor("RF12345", "SensOne", new SensorType("Temperature", "Celsius"),
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 20,
                30, testLocal);
        Local testLocal2 = new Local(10, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), testLocal2,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 20, 30,
                testLocal);
        Local upperRightVertex = new Local(30, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new SensorType("Pressure", "mm"),
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 21,
                30, testLocal);
        Local upperLeftVertex = new Local(10, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperLeftVertex,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 25,
                35, testLocal);
        Local bottomLeftVertex = new Local(10, 10, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new SensorType("Temperature", "Kelvin"), bottomLeftVertex,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 32,
                23, testLocal);
        Local bottomRightVertex = new Local(30, 10, 5);
        Sensor s1 = new Sensor("RF12345", "XV56-LD1", new SensorType("Rainfall", "dm/m2"), bottomRightVertex,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 32,
                21, testLocal);
        Local wrongLatitude = new Local(35, 20, 5);
        Sensor s1 = new Sensor("RF12345", "XV56-LD1", new SensorType("Temperature", "Celsius"), wrongLatitude,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 25,
                20, testLocal);
        Local wrongLatitude = new Local(-35, 20, 5);
        Sensor testSensor = new Sensor("RF12345", "XV56-LD1", new SensorType("Rainfall", "l/m2"),
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 32,
                21, testLocal);
        Local wrongLatitude = new Local(35, 20, 5);
        Sensor testSensor = new Sensor("RF123451", "XV56-LD1", new SensorType("Temperature", "Celsius"),
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 25,
                20, testLocal);
        Local wrongLatitude = new Local(-35, 20, 5);
        Sensor s1 = new Sensor("RF12345", "XV56-LD1", new SensorType("Temperature", "Kelvin"),
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 10,
                10, testLocal);
        Local wrongLongitude = new Local(100, 100, 5);
        Sensor testSensor = new Sensor("RF123452", "Sensor", new SensorType("Temperature", "Fahrenheit"),
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 100,
                100, testLocal);
        Local wrongLongitude = new Local(20, -35, 5);
        Sensor testSensor = new Sensor("RF123453", "Sensor", new SensorType("Rainfall", "l/m2"), wrongLongitude,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 10,
                10, testLocal);
        Local wrongLongitude = new Local(100, 100, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), wrongLongitude,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 100,
                100, testLocal);
        Local wrongLongitude = new Local(20, -35, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), wrongLongitude,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 10,
                10, testLocal);
        Local wrongLongitude = new Local(100, 20, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), wrongLongitude,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 50,
                50, testLocal);
        Local negativeCoords = new Local(-5, -5, -5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), negativeCoords,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 20,
                30, testLocal);
        Local edge = new Local(10, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), edge,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 20,
                30, testLocal);
        Local edge = new Local(10, 30, 5);
        Sensor validSensor = new Sensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"), edge, new Date());

        // Act

        boolean result = validSensor.isContainedInArea(testArea);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfSensorIsContainedOnUpperRightVertex2() {
        // Arrange

        Local testLocal = new Local(40, 40, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 20,
                30, testLocal);
        Local upperRightVertex = new Local(30, 30, 5);
        Sensor validSensor = new Sensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"),
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 21,
                30, testLocal);
        Local upperLeftVertex = new Local(10, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"),
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 35,
                25, testLocal);
        Local bottomLeftVertex = new Local(10, 10, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"),
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 32,
                23, testLocal);
        Local bottomRightVertex = new Local(30, 10, 5);
        Sensor testSensor = new Sensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"),
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 50,
                50, testLocal);
        Local testLocal2 = new Local(-5, -5, -5);
        Sensor s1 = new Sensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"),
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

        validSensor = new Sensor("RF12345", "SensOne", new SensorType("Temperature", "Kelvin"), new Local(21,
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

        validSensor = new Sensor("SensOne", new SensorType("Temperature", "Kelvin"), new Date());
        SensorType expectedResult = new SensorType("Temperature", "Kelvin");

        // Act

        SensorType actualResult = validSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDistanceToHouseWorks() {
        // Arrange

        House house = new House("House", new Address("Rua das Flores", "4512", "Porto","Portugal"), new Local(
                4, 6, 6), 60, 180,
                new ArrayList<>());
        house.setMotherArea(new GeographicArea("Porto", new AreaType("City"),
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

        AreaReading areaReading1 = new AreaReading(20, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "C");

        // Act

        boolean actualResult1 = validSensor.addReading(areaReading1);
        boolean actualResult3 = validSensor.addReading(areaReading1);

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfAddReadingsWorksNotActiveResult() {
        // Arrange

        AreaReading areaReading1 = new AreaReading(20, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "C");
        validSensor.deactivateSensor();
        // Act

        boolean actualResult1 = validSensor.addReading(areaReading1);
        boolean actualResult3 = validSensor.addReading(areaReading1);

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
        AreaReadingList areaReadingList = new AreaReadingList();
        AreaReading areaReading1 = new AreaReading(23, new GregorianCalendar(2018, Calendar.JULY, 1, 10, 30).getTime(), "C");
        AreaReading areaReading2 = new AreaReading(19, new GregorianCalendar(2018, Calendar.JULY, 1, 14, 30).getTime(), "C");
        AreaReading areaReading3 = new AreaReading(19, new GregorianCalendar(2018, Calendar.JULY, 2, 11, 30).getTime(), "C");
        AreaReading areaReading4 = new AreaReading(29, new GregorianCalendar(2018, Calendar.JULY, 2, 16, 30).getTime(), "C");
        AreaReading areaReading5 = new AreaReading(34, new GregorianCalendar(2018, Calendar.JULY, 3, 9, 30).getTime(), "C");
        AreaReading areaReading6 = new AreaReading(32, new GregorianCalendar(2018, Calendar.JULY, 3, 10, 30).getTime(), "C");
        AreaReading areaReading7 = new AreaReading(15, new GregorianCalendar(2018, Calendar.JULY, 4, 10, 30).getTime(), "C");
        AreaReading areaReading8 = new AreaReading(17, new GregorianCalendar(2018, Calendar.JULY, 4, 15, 30).getTime(), "C");
        AreaReading areaReading9 = new AreaReading(12, new GregorianCalendar(2018, Calendar.JULY, 5, 11, 30).getTime(), "C");
        AreaReading areaReading10 = new AreaReading(15, new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime(), "C");
        AreaReading areaReading11 = new AreaReading(17, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 30).getTime(), "C");
        AreaReading areaReading12 = new AreaReading(19, new GregorianCalendar(2018, Calendar.JULY, 6, 23, 35).getTime(), "C");
        AreaReading areaReading13 = new AreaReading(20, new GregorianCalendar(2018, Calendar.JULY, 7, 10, 30).getTime(), "C");
        AreaReading areaReading14 = new AreaReading(25, new GregorianCalendar(2018, Calendar.JULY, 7, 14, 30).getTime(), "C");
        AreaReading areaReading15 = new AreaReading(26, new GregorianCalendar(2018, Calendar.JULY, 8, 9, 30).getTime(), "C");
        AreaReading areaReading16 = new AreaReading(22, new GregorianCalendar(2018, Calendar.JULY, 8, 10, 30).getTime(), "C");
        AreaReading areaReading17 = new AreaReading(21, new GregorianCalendar(2018, Calendar.JULY, 9, 13, 30).getTime(), "C");
        AreaReading areaReading18 = new AreaReading(25, new GregorianCalendar(2018, Calendar.JULY, 9, 15, 30).getTime(), "C");
        AreaReading areaReading19 = new AreaReading(32, new GregorianCalendar(2018, Calendar.JULY, 10, 10, 30).getTime(), "C");
        AreaReading areaReading20 = new AreaReading(31, new GregorianCalendar(2018, Calendar.JULY, 10, 15, 30).getTime(), "C");
        areaReadingList.addReading(areaReading1);
        areaReadingList.addReading(areaReading2);
        areaReadingList.addReading(areaReading3);
        areaReadingList.addReading(areaReading4);
        areaReadingList.addReading(areaReading5);
        areaReadingList.addReading(areaReading6);
        areaReadingList.addReading(areaReading7);
        areaReadingList.addReading(areaReading8);
        areaReadingList.addReading(areaReading9);
        areaReadingList.addReading(areaReading10);
        areaReadingList.addReading(areaReading11);
        areaReadingList.addReading(areaReading12);
        areaReadingList.addReading(areaReading13);
        areaReadingList.addReading(areaReading14);
        areaReadingList.addReading(areaReading15);
        areaReadingList.addReading(areaReading16);
        areaReadingList.addReading(areaReading17);
        areaReadingList.addReading(areaReading18);
        areaReadingList.addReading(areaReading19);
        areaReadingList.addReading(areaReading20);
        validSensor.setAreaReadingList(areaReadingList);
        //Act
        Date actualResult = validSensor.getLastColdestDayInGivenInterval(new GregorianCalendar(2018, Calendar.JULY, 1, 5, 0).getTime(), new GregorianCalendar(2018, Calendar.JULY, 10, 23, 0).getTime());
        //Assert
        assertEquals(new GregorianCalendar(2018, Calendar.JULY, 5, 19, 30).getTime(), actualResult);
    }

    @Test
    void seeIfSensorIsContainedConditionBotVertex() {
        // Arrange

        Local testLocal = new Local(20, 10, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 2,
                10, testLocal);
        Local upperLeftVertex = new Local(25, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperLeftVertex,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 2,
                3, testLocal);
        Local upperLeftVertex = new Local(18, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperLeftVertex,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 6,
                3, testLocal);
        Local upperLeftVertex = new Local(18, 30, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperLeftVertex,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 10,
                20, testLocal);
        Local upperVertex = new Local(20, 24, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperVertex,
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
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 1,
                20, testLocal);
        Local upperVertex = new Local(20, 31, 5);
        Sensor testSensor = new Sensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperVertex,
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
        AreaReadingList expectedResultList = new AreaReadingList();
        expectedResultList.addReading(new AreaReading(23.3, validDate1, "C"));

        // Act

        boolean actualResult = validSensor.addReading(validDate1, 23.3, "C");
        boolean actualResultFailed = validSensor.addReading(outOfBoundsDate, 31D, "C");

        //Assert

        Assertions.assertTrue(actualResult);
        assertFalse(actualResultFailed);
    }

    @Test
    void seeIfAddReadingsWorksValueDate() {
        // Arrange

        validSensor.setDateStartedFunctioning(new GregorianCalendar(2018, Calendar.JANUARY, 2).getTime());

        // Act

        boolean addValidReading = validSensor.addReading(new GregorianCalendar(2019, Calendar.JANUARY,
                1).getTime(), 20D, "C");
        boolean addOutOfBoundsReading = validSensor.addReading(new GregorianCalendar(2017, Calendar.FEBRUARY,
                3).getTime(), 12D, "C");
        boolean addOnBoundsReading = validSensor.addReading(new GregorianCalendar(2018, Calendar.JANUARY,
                2).getTime(), 15D, "C");


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
                1).getTime(), 20D, "C");


        // Assert

        assertFalse(addValidReading);
    }

    @Test
    void seeIfGetAverageReadingsBetweenDates() {
        // Arrange
        AreaReadingList validAreaReadingList1;
        validAreaReadingList1 = new AreaReadingList();
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
        AreaReading r0 = new AreaReading(20, validDate3, "C");
        AreaReading r1 = new AreaReading(25, validDate4, "C");
        AreaReading r2 = new AreaReading(30, validDate5, "C");
        validAreaReadingList1.addReading(r0);
        validAreaReadingList1.addReading(r1);
        validAreaReadingList1.addReading(r2);
        double expectedResult = 25;

        // Act

        double actualResult = validAreaReadingList1.getAverageReadingsBetweenDates(validDate3, validDate5);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDateHighestAmplitudeBetweenDates() {
        // Arrange
        AreaReadingList validAreaReadingList1;
        validAreaReadingList1 = new AreaReadingList();
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
        AreaReading r0 = new AreaReading(20, validDate3, "C");
        AreaReading r1 = new AreaReading(25, validDate4, "C");
        AreaReading r2 = new AreaReading(30, validDate5, "C");
        AreaReading r3 = new AreaReading(10, validDate6, "C");
        AreaReading r4 = new AreaReading(10, validDate7, "C");
        AreaReading r5 = new AreaReading(10, validDate8, "C");
        validAreaReadingList1.addReading(r0);
        validAreaReadingList1.addReading(r1);
        validAreaReadingList1.addReading(r2);
        validAreaReadingList1.addReading(r3);
        validAreaReadingList1.addReading(r4);
        validAreaReadingList1.addReading(r5);
        Date expectedResult = validDate5;

        // Act

        Date actualResult = validAreaReadingList1.getDateHighestAmplitudeBetweenDates(validDate6, validDate5);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetTotalValueReadingsOnGivenDay() {
        // Arrange
        AreaReadingList validAreaReadingList = new AreaReadingList();
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
        AreaReading areaReading = new AreaReading(20, validDate15, "C");
        AreaReading areaReading2 = new AreaReading(20, validDate3, "C");
        AreaReading areaReading3 = new AreaReading(20, validDate7, "C");
        AreaReading areaReading4 = new AreaReading(20, validDate14, "C");
        validAreaReadingList.addReading(areaReading);
        validAreaReadingList.addReading(areaReading2);
        validAreaReadingList.addReading(areaReading3);
        validAreaReadingList.addReading(areaReading4);
        double expectedResult = 20;
        // Act

        double actualResult = validAreaReadingList.getValueReadingsInDay(validDate13);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMostRecentValueReading() {
        // Arrange
        AreaReadingList validAreaReadingList;
        Date validDate12 = new Date();
        validAreaReadingList = new AreaReadingList();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate12 = validSdf.parse("02/11/2015 20:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date testDate = new GregorianCalendar(2018, Calendar.NOVEMBER, 3).getTime();
        AreaReading earlierAreaReading = new AreaReading(15, validDate12, "C");
        AreaReading laterAreaReading = new AreaReading(30, testDate, "C");
        validAreaReadingList.addReading(earlierAreaReading);
        validAreaReadingList.addReading(laterAreaReading);
        double expectedResult = 30.0;

        // Act
        double result = validAreaReadingList.getMostRecentValue();

        // Assert
        assertEquals(expectedResult, result, 0.01);
    }
}