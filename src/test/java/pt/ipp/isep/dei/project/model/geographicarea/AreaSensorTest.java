package pt.ipp.isep.dei.project.model.geographicarea;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Sensor tests class.
 */

class AreaSensorTest {

    // Common artifacts for testing in this class.

    private AreaSensor validAreaSensor;
    private Date validDate1;
    private Date validDate2;

    @BeforeEach
    void arrangeArtifacts() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate1 = validSdf.parse("01/04/2018 00:00:00");
            validDate2 = validSdf.parse("01/04/2017 00:00:00");

        } catch (
                ParseException c) {
            c.printStackTrace();
        }

        validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(10, 10, 10), validDate1, 6008L);
        validAreaSensor.setActive(true);
    }

    @Test
    void seeIfAddReadingWorks() {
        //Arrange

        Reading reading = new Reading(21D, validDate1, "C", "sensorID");

        // Act

        boolean actualResult = validAreaSensor.addReading(reading);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddReadingWorksWhenReadingExists() {
        //Arrange

        Reading reading = new Reading(21D, validDate1, "C", "sensorID");
        validAreaSensor.addReading(reading);

        // Act

        boolean actualResult = validAreaSensor.addReading(reading);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfActiveDuringDateWorks() {
        // Act

        boolean actualResult = validAreaSensor.activeDuringDate(validDate1);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfActiveDuringDateWorksWhenDateGivenIsOlder() {
        // Act

        boolean actualResult = validAreaSensor.activeDuringDate(validDate2);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfReadingWithGivenDateExistsWorks() {
        // Arrange

        Reading reading = new Reading(21D, validDate1, "C", "sensorID");
        validAreaSensor.addReading(reading);

        // Act

        boolean actualResult = validAreaSensor.readingWithGivenDateExists(validDate1);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfReadingWithGivenDateExistsWorksWhenItDoesNot() {
        // Arrange

        Reading reading = new Reading(21D, validDate2, "C", "sensorID");
        validAreaSensor.addReading(reading);

        // Act

        boolean actualResult = validAreaSensor.readingWithGivenDateExists(validDate1);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEmptyConstructorWorks() {
        // Arrange
        AreaSensor areaSensorABC = new AreaSensor();
        areaSensorABC.setId("ABC");
        String expectedResult = "ABC";
        // Act
        String actualResult = areaSensorABC.getId();
        // Assert
        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfSetSensorList() {
//        // Arrange
//        AreaSensorList listA = new AreaSensorList();
//        // Act
//        validAreaSensor.setAreaSensorList(listA);
//        AreaSensorList actualResult = validAreaSensor.getAreaSensorList();
//        // Assert
//        assertEquals(listA, actualResult);
//    }

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

        AreaSensor areaSensor = new AreaSensor("Sensor", "Sensor", new SensorType("Temperature", "Celsius"), new Local(12, 12, 12), startDate, 6008L);

        // Act

        Object actualResult = areaSensor.getDateStartedFunctioning();

        // Assert

        assertEquals(startDate, actualResult);
    }


    @Test
    void seeIfConstructorSetsTypeArea() {
        // Arrange

        SensorType expectedResult = new SensorType("Temperature", "Celsius");

        // Act

        SensorType actualResult = validAreaSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsTypeArea() {
        // Arrange

        SensorType expectedResult = new SensorType("Temperature", "Celsius");
        validAreaSensor = new AreaSensor("RF12345", "SensOne", new SensorType("Temperature", "Celsius"),
                new Local(31, 15, 3), new Date(), 6008L);

        // Act

        SensorType actualResult = validAreaSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSecondConstructorSetsLocal() {
        // Arrange

        validAreaSensor = new AreaSensor("RF12345", "SensOne", new SensorType("Temperature", "Celsius"),
                new Local(31, 15, 3), new Date(), 6008L);
        Local expectedResult = new Local(31, 15, 3);

        //Act

        Local actualResult = validAreaSensor.getLocal();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetNameWorks() {
        // Arrange

        String expectedResult = "XXB6";

        // Act

        validAreaSensor.setName("XXB6");
        String actualResult = validAreaSensor.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetAreaReadings() {
        //Arrange

        Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();
        Reading firstValidReading = new Reading(31, date, "C", "Test");
        List<Reading> readingList = new ArrayList<>();

        //Act

        validAreaSensor.getAreaReadings().add(firstValidReading);
        readingList.add(firstValidReading);

        //Assert

        assertEquals(readingList, validAreaSensor.getAreaReadings());
    }

    @Test
    void seeIfReadingExists() {
        //Arrange

        Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();
        Reading firstValidReading = new Reading(31, date, "C", "Test");

        //Act
        validAreaSensor.getAreaReadings().add(firstValidReading);

        //Assert

        assertTrue(validAreaSensor.readingWithGivenDateExists(date));
    }

    @Test
    void seeIfReadingExistsSameDate() {
        //Arrange

        Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();
        Reading firstValidReading = new Reading(31, date, "C", "Test");

        //Act
        validAreaSensor.getAreaReadings().add(firstValidReading);

        //Assert

        assertTrue(validAreaSensor.readingWithGivenDateExists(new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime()));
    }

    @Test
    void seeIfReadingDoNotExists() {
        //Arrange

        Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 13).getTime();

        //Assert

        assertFalse(validAreaSensor.readingWithGivenDateExists(date));
    }

    @Test
    void seeIfNullSensorNameThrowsStringMessage() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validAreaSensor.setName(null));

        // Assert

        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfEmptySensorNameThrowsException() {
        // Act

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validAreaSensor.setName(""));

        // Assert

        assertEquals("Please Insert Valid Name", exception.getMessage());
    }


    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Arrange

        Local testLocal = new Local(21, 23, 1);

        // Act

        boolean actualResult = validAreaSensor.equals(testLocal); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentSensor() {
        // Arrange

        AreaSensor s2 = new AreaSensor("RF12345", "Temperature Sensor XX56", new SensorType("Temperature", "Fahrenheit"),
                new Local(21, 1, 12), new Date(), 6008L);

        // Act

        boolean actualResult = validAreaSensor.equals(s2);


        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksTrueSameSensor() {
        // Arrange

        AreaSensor testAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), new Local(12, 12, 12), new Date(), 6008L);

        // Act

        boolean actualResult = validAreaSensor.equals(testAreaSensor);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksTrueSameObject() {
        // Act

        boolean actualResult = validAreaSensor.equals(validAreaSensor);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validAreaSensor.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsContainedInArea() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 20, 30,
                new Local(31, 15, 3));
        validAreaSensor = new AreaSensor("RF12345", "SensOne", new SensorType("Temperature", "Celsius"),
                new Local(31, 15, 3), new Date(), 6008L);

        // Act

        boolean result = validAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), testLocal2,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "SensorOne", new SensorType("Pressure", "mm"),
                upperRightVertex, new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperLeftVertex,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "SensorOne", new SensorType("Temperature", "Kelvin"), bottomLeftVertex,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor s1 = new AreaSensor("RF12345", "XV56-LD1", new SensorType("Rainfall", "dm/m2"), bottomRightVertex,
                new Date(), 6008L);

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
        AreaSensor s1 = new AreaSensor("RF12345", "XV56-LD1", new SensorType("Temperature", "Celsius"), wrongLatitude,
                new Date(), 6008L);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "XV56-LD1", new SensorType("Rainfall", "l/m2"),
                wrongLatitude, new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF123451", "XV56-LD1", new SensorType("Temperature", "Celsius"),
                wrongLatitude, new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor s1 = new AreaSensor("RF12345", "XV56-LD1", new SensorType("Temperature", "Kelvin"),
                wrongLatitude, new Date(), 6008L);

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
        AreaSensor testAreaSensor = new AreaSensor("RF123452", "Sensor", new SensorType("Temperature", "Fahrenheit"),
                wrongLongitude, new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF123453", "Sensor", new SensorType("Rainfall", "l/m2"), wrongLongitude,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), wrongLongitude,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), wrongLongitude,
                new Date(), 6008L);
        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), wrongLongitude,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), negativeCoords,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("Rainfall", "l/m2"), edge,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor validAreaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"), edge, new Date(), 6008L);

        // Act

        boolean result = validAreaSensor.isContainedInArea(testArea);

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
        AreaSensor validAreaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"),
                upperRightVertex, new Date(), 6008L);

        // Act

        boolean result = validAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"),
                upperLeftVertex, new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"),
                bottomLeftVertex, new Date(), 6008L);
        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"),
                bottomRightVertex, new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor s1 = new AreaSensor("RF12345", "Sensor", new SensorType("Temperature", "Celsius"),
                testLocal2, new Date(), 6008L);

        // Act

        boolean result = s1.isContainedInArea(testArea);

        // Assert

        assertTrue(result);
    }

    // End of isContainedInArea tests.

    @Test
    void seeIfPrintSensorWorksNoLocal() {
        // Arrange

        String expectedResult = "SensOne, Temperature, 10.0º lat, 10.0º long \n";

        // Act

        String result = validAreaSensor.buildString();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintSensorWorksWithLocal() {
        // Arrange

        validAreaSensor = new AreaSensor("RF12345", "SensOne", new SensorType("Temperature", "Kelvin"), new Local(21,
                31, 15), new Date(), 6008L);
        String expectedResult = "SensOne, Temperature, 21.0º lat, 31.0º long \n";

        // Act

        String result = validAreaSensor.buildString();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintDeactivated() {
        // Arrange

        String expectedResult = "Active";
        // Act

        String result = validAreaSensor.printActive();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintActive() {
        // Arrange

        String expectedResult = "Deactivated";
        validAreaSensor.deactivateSensor();

        // Act

        String result = validAreaSensor.printActive();

        // Assert

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void seeIfActive() {

        // Act
        validAreaSensor.deactivateSensor();
        // Act
        boolean result = validAreaSensor.deactivateSensor();

        // Assert

        Assertions.assertFalse(result);
    }

    @Test
    void seeIfDeactivates() {

        // Act
        boolean result = validAreaSensor.deactivateSensor();

        // Assert

        Assertions.assertTrue(result);
    }


    @Test
    void seeIfSecondConstructorSetsTypeSensorCorrectly() {
        // Arrange

        validAreaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Kelvin"), new Local(12, 12, 12), new Date(), 6008L);
        SensorType expectedResult = new SensorType("Temperature", "Kelvin");

        // Act

        SensorType actualResult = validAreaSensor.getSensorType();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetDistanceToHouseWorks() {
        // Arrange

        House house = new House("House", new Address("Rua das Flores", "431", "4512", "Porto", "Portugal"), new Local(
                4, 6, 6), 60, 180,
                new ArrayList<>());
        house.setMotherArea(new GeographicArea("Porto", new AreaType("City"),
                2, 3, new Local(4, 4, 100)));
        Local testLocal = new Local(-5, -5, -5);
        double expectedResult = 799.8866399214708;

        //Act
        double actualResult = validAreaSensor.getDistanceToHouse(house);

        //Assert
        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void seeIfSetGetIdWorks() {
        // Arrange

        String expectedResult = "XXB6";

        // Act

        validAreaSensor.setId("XXB6");
        String actualResult = validAreaSensor.getId();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsContainedConditionBotVertex() {
        // Arrange

        Local testLocal = new Local(20, 10, 5);
        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 2,
                10, testLocal);
        Local upperLeftVertex = new Local(25, 30, 5);
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperLeftVertex,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperLeftVertex,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperLeftVertex,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperVertex,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

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
        AreaSensor testAreaSensor = new AreaSensor("RF12345", "SensorOne", new SensorType("Movement", "cm"), upperVertex,
                new Date(), 6008L);

        // Act

        boolean result = testAreaSensor.isContainedInArea(testArea);

        // Assert

        assertFalse(result);
    }

    //TODO Review commented tests


//
//    @Test
//    void seeIfAddReadingsWorksValueDate() {
//        // Arrange
//
//        validAreaSensor.setDateStartedFunctioning(new GregorianCalendar(2018, Calendar.JANUARY, 2).getTime());
//
//        // Act
//
//        boolean addValidReading = validAreaSensor.addReading(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), 20D, "C","TEST");
//        boolean addOutOfBoundsReading = validAreaSensor.addReading(new GregorianCalendar(2017, Calendar.FEBRUARY,
//                3).getTime(), 12D, "C","TEST");
//        boolean addOnBoundsReading = validAreaSensor.addReading(new GregorianCalendar(2018, Calendar.JANUARY,
//                2).getTime(), 15D, "C","TEST");
//
//
//        // Assert
//
//        assertTrue(addValidReading);
//        assertFalse(addOutOfBoundsReading);
//        assertTrue(addOnBoundsReading);
//    }
//
//    @Test
//    void seeIfAddReadingsWorksDeactivatedDevice() {
//        // Arrange
//
//        validAreaSensor.setDateStartedFunctioning(new GregorianCalendar(2018, Calendar.JANUARY, 2).getTime());
//        validAreaSensor.deactivateSensor();
//        // Act
//
//        boolean addValidReading = validAreaSensor.addReading(new GregorianCalendar(2019, Calendar.JANUARY,
//                1).getTime(), 20D, "C", validAreaSensor.getId());
//
//
//        // Assert
//
//        assertFalse(addValidReading);
//    }

//    @Test
//    void seeIfGetDateHighestAmplitudeBetweenDates() {
//        // Arrange
//        ReadingService validReadingService1;
//        validReadingService1 = new ReadingService();
//        Date validDate3 = new Date(); // 31/09/2018 23:59:59
//        Date validDate4 = new Date(); // 07/10/2018 00:00:00
//        Date validDate5 = new Date(); // 08/10/2018 23:26:21
//        Date validDate6 = new Date(); // 31/09/2018 23:59:59
//        Date validDate7 = new Date(); // 07/10/2018 00:00:00
//        Date validDate8 = new Date(); // 08/10/2018 23:26:21
//        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        try {
//            validDate3 = validSdf.parse("31/09/2018 23:59:59");
//            validDate4 = validSdf.parse("07/10/2018 20:00:00");
//            validDate5 = validSdf.parse("08/10/2018 23:26:21");
//            validDate6 = validSdf.parse("31/09/2018 10:59:59");
//            validDate7 = validSdf.parse("07/10/2018 00:00:00");
//            validDate8 = validSdf.parse("08/10/2018 13:26:21");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Reading r0 = new Reading(20, validDate3, "C", "SensOne");
//        Reading r1 = new Reading(25, validDate4, "C", "SensOne");
//        Reading r2 = new Reading(30, validDate5, "C", "SensOne");
//        Reading r3 = new Reading(10, validDate6, "C", "SensOne");
//        Reading r4 = new Reading(10, validDate7, "C", "SensOne");
//        Reading r5 = new Reading(10, validDate8, "C", "SensOne");
//        validReadingService1.addReading(r0);
//        validReadingService1.addReading(r1);
//        validReadingService1.addReading(r2);
//        validReadingService1.addReading(r3);
//        validReadingService1.addReading(r4);
//        validReadingService1.addReading(r5);
//        Date expectedResult = validDate5;
//
//        // Act
//
//        Date actualResult = validReadingService1.getDateHighestAmplitudeBetweenDates(validAreaSensor, validDate6, validDate5);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }


    @Test
    void seeIfGetMostRecentValueReading() {
        // Arrange

        Date validDate12 = new Date();
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            validDate12 = validSdf.parse("02/11/2015 20:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date testDate = new GregorianCalendar(2018, Calendar.NOVEMBER, 3).getTime();
        Reading earlierReading = new Reading(15, validDate12, "C", "Test");
        Reading laterReading = new Reading(30, testDate, "C", "Test");
        List<Reading> readings = new ArrayList<>();
        readings.add(earlierReading);
        readings.add(laterReading);
        double expectedResult = 30.0;

        // Act

        double result = ReadingUtils.getMostRecentValue(readings);

        // Assert

        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfSetGeographicAreaIdWorks() {
        // Arrange

        validAreaSensor.setGeographicAreaId(20L);
        long expectedResult = 20;

        // Act

        long actualResult = validAreaSensor.getGeographicAreaId();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfBuildStringWorksWithNullLocal() {
        // Arrange

        AreaSensor areaSensor = new AreaSensor("SensOne", "SensOne", new SensorType("Temperature", "Celsius"), null, new Date(), 6008L);
        String expectedResult = "SensOne, Temperature. ";

        // Act

        String actualResult = areaSensor.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);

    }

}