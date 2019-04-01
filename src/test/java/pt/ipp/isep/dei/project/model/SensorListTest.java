package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SensorList tests class.
 */

class SensorListTest {

    // Common artifacts for testing in this class.

    private SensorList validSensorList; // Contains the first valid sensor by default.
    private Sensor firstValidSensor;
    private Sensor secondValidSensor;
    private Sensor thirdValidSensor;

    @BeforeEach
    void arrangeArtifacts() {
        validSensorList = new SensorList();
        firstValidSensor = new Sensor("SensorOne", "SensorOne", new TypeSensor("Temperature", "Celsius"), new Local(
                31, 1, 2), new Date());
        firstValidSensor.setActive(true);
        secondValidSensor = new Sensor("SensorTwo", new TypeSensor("Temperature", "Celsius"),
                new Date());
        secondValidSensor.setActive(true);
        thirdValidSensor = new Sensor("SensorThree", new TypeSensor("Rainfall", "l/m2"),
                new Date());
        validSensorList.add(firstValidSensor);
    }


    @Test
    void seeIfEqualsWorksOnSameObject() {
        //Act

        boolean actualResult = validSensorList.equals(validSensorList); // Required for Sonarqube testing purposes.

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnDiffObject() {
        //Act

        boolean actualResult = validSensorList.equals(20D); // Required for Sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithSameContent() {
        // Arrange

        SensorList expectedResult = new SensorList();
        expectedResult.add(firstValidSensor);

        // Act

        boolean actualResult = validSensorList.equals(expectedResult);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithDifferentContent() {
        // Arrange

        SensorList expectedResult = new SensorList();
        expectedResult.add(secondValidSensor);

        // Act

        boolean actualResult = validSensorList.equals(expectedResult);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validSensorList.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeWorks() {
        // Arrange

        SensorList expectedResult = new SensorList();
        expectedResult.add(firstValidSensor);
        expectedResult.add(secondValidSensor);
        validSensorList.add(secondValidSensor);
        validSensorList.add(thirdValidSensor);

        // Act

        SensorList actualResult = validSensorList.getSensorListByType("Temperature");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeWorksFalse() {
        // Arrange

        SensorList expectedResult = new SensorList();

        // Act

        SensorList actualResult = validSensorList.getSensorListByType("Pressure");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorks() {
        // Arrange

        validSensorList.add(secondValidSensor);
        validSensorList.add(thirdValidSensor);
        String expectedResult = "---------------\n" +
                "0) Name: SensorOne | Type: Temperature | Active\n" +
                "1) Name: SensorTwo | Type: Temperature | Active\n" +
                "2) Name: SensorThree | Type: Rainfall | Active\n" +
                "---------------\n";

        // Act

        String actualResult = validSensorList.toString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorksEmpty() {
        // Arrange

        validSensorList = new SensorList();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = validSensorList.toString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksNoReadings() {
        // Arrange

        ReadingList expectedResult = new ReadingList();

        // Act

        ReadingList actualResult = validSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksReadingsAtBeginning() {
        // Arrange

        Reading readingOne = new Reading(31, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime());
        validSensorList.add(secondValidSensor);
        firstValidSensor.addReading(readingOne);
        ReadingList expectedResult = new ReadingList();
        expectedResult.addReading(readingOne);

        // Act

        ReadingList actualResult = validSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksReadingsAtEnd() {
        // Arrange

        Reading readingOne = new Reading(31, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime());
        validSensorList.add(secondValidSensor);
        secondValidSensor.addReading(readingOne);
        ReadingList expectedResult = new ReadingList();
        expectedResult.addReading(readingOne);

        // Act

        ReadingList actualResult = validSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksAllSensorsHaveReadings() {
        // Arrange

        Reading readingOne = new Reading(31, new GregorianCalendar(2018, Calendar.JANUARY, 1).getTime());
        Reading readingTwo = new Reading(20, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime());
        validSensorList.add(secondValidSensor);
        firstValidSensor.addReading(readingOne);
        secondValidSensor.addReading(readingTwo);
        ReadingList expectedResult = new ReadingList();
        expectedResult.addReading(readingOne);
        expectedResult.addReading(readingTwo);

        // Act

        ReadingList actualResult = validSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksNoSensors() {
        // Arrange

        validSensorList = new SensorList();

        // Assert

        assertThrows(IllegalArgumentException.class, validSensorList::getMostRecentlyUsedSensor);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksThreeSensors() {
        // Assign readings to sensors.

        Reading mostRecentReading = new Reading(3, new GregorianCalendar(2019, Calendar.JANUARY, 1)
                .getTime());
        firstValidSensor.addReading(mostRecentReading);
        Reading secondReading = new Reading(3, new GregorianCalendar(2018, Calendar.JANUARY, 2)
                .getTime());
        secondValidSensor.addReading(secondReading);
        Reading thirdReading = new Reading(3, new GregorianCalendar(2017, Calendar.JANUARY, 1)
                .getTime());
        thirdValidSensor.addReading(thirdReading);

        // Test for when most recent reading is in the first sensor.

        // Arrange

        validSensorList.add(secondValidSensor);
        validSensorList.add(thirdValidSensor);

        // Act

        Sensor actualResult1 = validSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidSensor, actualResult1);

        // Test for when most recent reading is in the middle sensor.

        // Arrange

        validSensorList = new SensorList();
        validSensorList.add(secondValidSensor);
        validSensorList.add(firstValidSensor);
        validSensorList.add(thirdValidSensor);

        // Act

        Sensor actualResult2 = validSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidSensor, actualResult2);

        // Test for when most recent reading is in the last sensor.

        // Arrange

        validSensorList = new SensorList();
        validSensorList.add(secondValidSensor);
        validSensorList.add(thirdValidSensor);
        validSensorList.add(firstValidSensor);

        // Act

        Sensor actualResult3 = validSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidSensor, actualResult3);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksOneSensorNoReadings() {
        // Assert

        assertThrows(IllegalArgumentException.class, validSensorList::getMostRecentlyUsedSensor);
    }

    @Test
    void seeIfIsEmptyWorks() {
        // Arrange

        SensorList emptyList = new SensorList();
        SensorList twoSensorsList = new SensorList();
        twoSensorsList.add(firstValidSensor);
        twoSensorsList.add(secondValidSensor);

        // Act

        boolean actualResult1 = emptyList.isEmpty();
        boolean actualResult2 = validSensorList.isEmpty();
        boolean actualResult3 = twoSensorsList.isEmpty();

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void getSensorsWithReadings() {
        // Arrange

        SensorList emptyList = new SensorList();
        SensorList twoSensorsList = new SensorList();

        Reading readingOne = new Reading(31, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime());
        secondValidSensor.addReading(readingOne);

        twoSensorsList.add(firstValidSensor);
        twoSensorsList.add(secondValidSensor);

        SensorList expectedResult1 = new SensorList();
        expectedResult1.add(secondValidSensor);

        // Act

        SensorList actualResult1 = twoSensorsList.getSensorsWithReadings();

        // Assert

        assertThrows(IllegalArgumentException.class, emptyList::getSensorsWithReadings);
        assertEquals(expectedResult1, actualResult1);
    }

    @Test
    void seeIfGetByIndexWorks() {
        //Arrange

        validSensorList.add(secondValidSensor);

        //Act

        Sensor actualResult1 = validSensorList.get(0);
        Sensor actualResult2 = validSensorList.get(1);

        //Assert

        assertEquals(firstValidSensor, actualResult1);
        assertEquals(secondValidSensor, actualResult2);
    }

    @Test
    void getByIndexEmptySensorList() {
        //Arrange

        SensorList emptyList = new SensorList();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));

        //Assert

        assertEquals("The sensor list is empty.", exception.getMessage());
    }

    @Test
    void getElementsAsArray() {
        //Arrange

        Sensor[] expectedResult1 = new Sensor[0];
        Sensor[] expectedResult2 = new Sensor[1];
        Sensor[] expectedResult3 = new Sensor[2];

        SensorList emptySensorList = new SensorList();
        SensorList validSensorList2 = new SensorList();
        validSensorList2.add(firstValidSensor);
        validSensorList2.add(secondValidSensor);

        expectedResult2[0] = firstValidSensor;
        expectedResult3[0] = firstValidSensor;
        expectedResult3[1] = secondValidSensor;

        //Act

        Sensor[] actualResult1 = emptySensorList.getElementsAsArray();
        Sensor[] actualResult2 = validSensorList.getElementsAsArray();
        Sensor[] actualResult3 = validSensorList2.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfGetSensorsByDistanceToHouseWorks() {
        //Arrange

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            date = validSdf.parse("10/02/2017 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Sensors

        Sensor sensorSameLocalHouse = new Sensor("123", "sameLocalAsHouse", new TypeSensor("Temperature", "K"), new Local(20, 20, 20), date);
        Sensor sensorDiffLocalHouse = new Sensor("125", "DiffLocalAsHouse", new TypeSensor("Temperature", "K"), new Local(20, 25, 20), date);

        SensorList validSensorList = new SensorList();
        validSensorList.add(sensorDiffLocalHouse);
        validSensorList.add(sensorSameLocalHouse);

        //House

        List<String> deviceTypeString = new ArrayList<>();
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "4200-072", "Porto");
        House house = new House("ISEP", address, new Local(20, 20, 20), 60,
                180, deviceTypeString);
        house.setMotherArea(new GeographicArea("Porto", new TypeArea
                ("Cidade"), 2, 3, new Local(4, 4, 100)));


        SensorList expectedResult = new SensorList();
        expectedResult.add(sensorSameLocalHouse);

        //Act

        SensorList actualResult = validSensorList.getSensorsByDistanceToHouse(house, 0);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSizeWorks() {

        //Arrange

        SensorList emptyList = new SensorList();
        SensorList twoSensors = new SensorList();
        twoSensors.add(firstValidSensor);
        twoSensors.add(secondValidSensor);

        //Act

        int actualResult1 = emptyList.size();
        int actualResult2 = validSensorList.size();
        int actualResult3 = twoSensors.size();

        //Assert

        assertEquals(0, actualResult1);
        assertEquals(1, actualResult2);
        assertEquals(2, actualResult3);
    }

    @Test
    void seeIfContainsWorks() {

        //Arrange

        SensorList emptyList = new SensorList();

        //Act

        boolean actualResult1 = emptyList.contains(firstValidSensor);
        boolean actualResult2 = validSensorList.contains(firstValidSensor);
        boolean actualResult3 = validSensorList.contains(secondValidSensor);

        //Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
    }

//    @Test
//    void seeIfAddReadingToMatchingSensorWorks() {
//        // Arrange
//
//        Date dateSensorStartedFunctioning = new GregorianCalendar(2017, Calendar.FEBRUARY, 3).getTime();
//        firstValidSensor.setDateStartedFunctioning(dateSensorStartedFunctioning);
//
//        // Act for reading within bounds.
//
//        boolean result = validSensorList.addReadingToMatchingSensor("SensorOne", 31D, new GregorianCalendar(
//                2017, Calendar.FEBRUARY, 5).getTime());
//
//        // Act for reading outside bounds.
//
//        boolean failedResult = validSensorList.addReadingToMatchingSensor("SensorOne", 31D, new GregorianCalendar(
//                2015, Calendar.FEBRUARY, 1).getTime());
//
//        // Act for not existing sensor
//
//        boolean failedResult2 = validSensorList.addReadingToMatchingSensor("xxxxxxx", 32D, new GregorianCalendar(
//                2018, Calendar.FEBRUARY, 1).getTime());
//
//
//        // Assert
//
//        assertTrue(result);
//        assertFalse(failedResult);
//        assertFalse(failedResult2);
//    }
}
