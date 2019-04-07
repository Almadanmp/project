package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.sensor.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AreaSensorList tests class.
 */

class AreaSensorListTest {

    // Common artifacts for testing in this class.

    private AreaSensorList validAreaSensorList; // Contains the first valid sensor by default.
    private Sensor firstValidSensor;
    private Sensor secondValidSensor;
    private Sensor thirdValidSensor;

    @BeforeEach
    void arrangeArtifacts() {
        validAreaSensorList = new AreaSensorList();
        firstValidSensor = new Sensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(
                31, 1, 2), new Date());
        firstValidSensor.setActive(true);
        secondValidSensor = new Sensor("SensorTwo", new SensorType("Temperature", "Celsius"),
                new Date());
        secondValidSensor.setActive(true);
        thirdValidSensor = new Sensor("SensorThree", new SensorType("Rainfall", "l/m2"),
                new Date());
        validAreaSensorList.add(firstValidSensor);
    }


    @Test
    void seeIfEqualsWorksOnSameObject() {
        //Act

        boolean actualResult = validAreaSensorList.equals(validAreaSensorList); // Required for Sonarqube testing purposes.

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnDiffObject() {
        //Act

        boolean actualResult = validAreaSensorList.equals(20D); // Required for Sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithSameContent() {
        // Arrange

        AreaSensorList expectedResult = new AreaSensorList();
        expectedResult.add(firstValidSensor);

        // Act

        boolean actualResult = validAreaSensorList.equals(expectedResult);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithDifferentContent() {
        // Arrange

        AreaSensorList expectedResult = new AreaSensorList();
        expectedResult.add(secondValidSensor);

        // Act

        boolean actualResult = validAreaSensorList.equals(expectedResult);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validAreaSensorList.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeWorks() {
        // Arrange

        AreaSensorList expectedResult = new AreaSensorList();
        expectedResult.add(firstValidSensor);
        expectedResult.add(secondValidSensor);
        validAreaSensorList.add(secondValidSensor);
        validAreaSensorList.add(thirdValidSensor);

        // Act

        AreaSensorList actualResult = validAreaSensorList.getSensorListByType("Temperature");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeWorksFalse() {
        // Arrange

        AreaSensorList expectedResult = new AreaSensorList();

        // Act

        AreaSensorList actualResult = validAreaSensorList.getSensorListByType("Pressure");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorks() {
        // Arrange

        validAreaSensorList.add(secondValidSensor);
        validAreaSensorList.add(thirdValidSensor);
        String expectedResult = "---------------\n" +
                "0) Name: SensorOne | Type: Temperature | Active\n" +
                "1) Name: SensorTwo | Type: Temperature | Active\n" +
                "2) Name: SensorThree | Type: Rainfall | Active\n" +
                "---------------\n";

        // Act

        String actualResult = validAreaSensorList.toString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorksEmpty() {
        // Arrange

        validAreaSensorList = new AreaSensorList();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = validAreaSensorList.toString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksNoReadings() {
        // Arrange

        AreaReadingList expectedResult = new AreaReadingList();

        // Act

        AreaReadingList actualResult = validAreaSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksReadingsAtBeginning() {
        // Arrange

        AreaReading areaReadingOne = new AreaReading(31, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime(), "C");
        validAreaSensorList.add(secondValidSensor);
        firstValidSensor.addReading(areaReadingOne);
        AreaReadingList expectedResult = new AreaReadingList();
        expectedResult.addReading(areaReadingOne);

        // Act

        AreaReadingList actualResult = validAreaSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksReadingsAtEnd() {
        // Arrange

        AreaReading areaReadingOne = new AreaReading(31, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime(), "C");
        validAreaSensorList.add(secondValidSensor);
        secondValidSensor.addReading(areaReadingOne);
        AreaReadingList expectedResult = new AreaReadingList();
        expectedResult.addReading(areaReadingOne);

        // Act

        AreaReadingList actualResult = validAreaSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetReadingsWorksAllSensorsHaveReadings() {
        // Arrange

        AreaReading areaReadingOne = new AreaReading(31, new GregorianCalendar(2018, Calendar.JANUARY, 1).getTime(), "C");
        AreaReading areaReadingTwo = new AreaReading(20, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime(), "C");
        validAreaSensorList.add(secondValidSensor);
        firstValidSensor.addReading(areaReadingOne);
        secondValidSensor.addReading(areaReadingTwo);
        AreaReadingList expectedResult = new AreaReadingList();
        expectedResult.addReading(areaReadingOne);
        expectedResult.addReading(areaReadingTwo);

        // Act

        AreaReadingList actualResult = validAreaSensorList.getReadings();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksNoSensors() {
        // Arrange

        validAreaSensorList = new AreaSensorList();

        // Assert

        assertThrows(IllegalArgumentException.class, validAreaSensorList::getMostRecentlyUsedSensor);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksThreeSensors() {
        // Assign readings to sensors.

        AreaReading mostRecentAreaReading = new AreaReading(3, new GregorianCalendar(2019, Calendar.JANUARY, 1)
                .getTime(), "C");
        firstValidSensor.addReading(mostRecentAreaReading);
        AreaReading secondAreaReading = new AreaReading(3, new GregorianCalendar(2018, Calendar.JANUARY, 2)
                .getTime(), "C");
        secondValidSensor.addReading(secondAreaReading);
        AreaReading thirdAreaReading = new AreaReading(3, new GregorianCalendar(2017, Calendar.JANUARY, 1)
                .getTime(), "C");
        thirdValidSensor.addReading(thirdAreaReading);

        // Test for when most recent reading is in the first sensor.

        // Arrange

        validAreaSensorList.add(secondValidSensor);
        validAreaSensorList.add(thirdValidSensor);

        // Act

        Sensor actualResult1 = validAreaSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidSensor, actualResult1);

        // Test for when most recent reading is in the middle sensor.

        // Arrange

        validAreaSensorList = new AreaSensorList();
        validAreaSensorList.add(secondValidSensor);
        validAreaSensorList.add(firstValidSensor);
        validAreaSensorList.add(thirdValidSensor);

        // Act

        Sensor actualResult2 = validAreaSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidSensor, actualResult2);

        // Test for when most recent reading is in the last sensor.

        // Arrange

        validAreaSensorList = new AreaSensorList();
        validAreaSensorList.add(secondValidSensor);
        validAreaSensorList.add(thirdValidSensor);
        validAreaSensorList.add(firstValidSensor);

        // Act

        Sensor actualResult3 = validAreaSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidSensor, actualResult3);
    }

    @Test
    void seeIfGetMostRecentlyUsedSensorWorksOneSensorNoReadings() {
        // Assert

        assertThrows(IllegalArgumentException.class, validAreaSensorList::getMostRecentlyUsedSensor);
    }

    @Test
    void seeIfIsEmptyWorks() {
        // Arrange

        AreaSensorList emptyList = new AreaSensorList();
        AreaSensorList twoSensorsList = new AreaSensorList();
        twoSensorsList.add(firstValidSensor);
        twoSensorsList.add(secondValidSensor);

        // Act

        boolean actualResult1 = emptyList.isEmpty();
        boolean actualResult2 = validAreaSensorList.isEmpty();
        boolean actualResult3 = twoSensorsList.isEmpty();

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void getSensorsWithReadings() {
        // Arrange

        AreaSensorList emptyList = new AreaSensorList();
        AreaSensorList twoSensorsList = new AreaSensorList();

        AreaReading areaReadingOne = new AreaReading(31, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime(), "C");
        secondValidSensor.addReading(areaReadingOne);

        twoSensorsList.add(firstValidSensor);
        twoSensorsList.add(secondValidSensor);

        AreaSensorList expectedResult1 = new AreaSensorList();
        expectedResult1.add(secondValidSensor);

        // Act

        AreaSensorList actualResult1 = twoSensorsList.getSensorsWithReadings();

        // Assert

        assertThrows(IllegalArgumentException.class, emptyList::getSensorsWithReadings);
        assertEquals(expectedResult1, actualResult1);
    }

    @Test
    void seeIfGetByIndexWorks() {
        //Arrange

        validAreaSensorList.add(secondValidSensor);

        //Act

        Sensor actualResult1 = validAreaSensorList.get(0);
        Sensor actualResult2 = validAreaSensorList.get(1);

        //Assert

        assertEquals(firstValidSensor, actualResult1);
        assertEquals(secondValidSensor, actualResult2);
    }

    @Test
    void getByIndexEmptySensorList() {
        //Arrange

        AreaSensorList emptyList = new AreaSensorList();

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

        AreaSensorList emptyAreaSensorList = new AreaSensorList();
        AreaSensorList validAreaSensorList2 = new AreaSensorList();
        validAreaSensorList2.add(firstValidSensor);
        validAreaSensorList2.add(secondValidSensor);

        expectedResult2[0] = firstValidSensor;
        expectedResult3[0] = firstValidSensor;
        expectedResult3[1] = secondValidSensor;

        //Act

        Sensor[] actualResult1 = emptyAreaSensorList.getElementsAsArray();
        Sensor[] actualResult2 = validAreaSensorList.getElementsAsArray();
        Sensor[] actualResult3 = validAreaSensorList2.getElementsAsArray();

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

        Sensor sensorSameLocalHouse = new Sensor("123", "sameLocalAsHouse", new SensorType("Temperature", "K"), new Local(20, 20, 20), date);
        Sensor sensorDiffLocalHouse = new Sensor("125", "DiffLocalAsHouse", new SensorType("Temperature", "K"), new Local(20, 25, 20), date);

        AreaSensorList validAreaSensorList = new AreaSensorList();
        validAreaSensorList.add(sensorDiffLocalHouse);
        validAreaSensorList.add(sensorSameLocalHouse);

        //House

        List<String> deviceTypeString = new ArrayList<>();
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "4200-072", "Porto","Portugal");
        House house = new House("ISEP", address, new Local(20, 20, 20), 60,
                180, deviceTypeString);
        house.setMotherArea(new GeographicArea("Porto", new AreaType
                ("Cidade"), 2, 3, new Local(4, 4, 100)));


        AreaSensorList expectedResult = new AreaSensorList();
        expectedResult.add(sensorSameLocalHouse);

        //Act

        AreaSensorList actualResult = validAreaSensorList.getSensorsByDistanceToHouse(house, 0);

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSizeWorks() {

        //Arrange

        AreaSensorList emptyList = new AreaSensorList();
        AreaSensorList twoSensors = new AreaSensorList();
        twoSensors.add(firstValidSensor);
        twoSensors.add(secondValidSensor);

        //Act

        int actualResult1 = emptyList.size();
        int actualResult2 = validAreaSensorList.size();
        int actualResult3 = twoSensors.size();

        //Assert

        assertEquals(0, actualResult1);
        assertEquals(1, actualResult2);
        assertEquals(2, actualResult3);
    }

    @Test
    void seeIfContainsWorks() {

        //Arrange

        AreaSensorList emptyList = new AreaSensorList();

        //Act

        boolean actualResult1 = emptyList.contains(firstValidSensor);
        boolean actualResult2 = validAreaSensorList.contains(firstValidSensor);
        boolean actualResult3 = validAreaSensorList.contains(secondValidSensor);

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
//        boolean result = validAreaSensorList.addReadingToMatchingSensor("SensorOne", 31D, new GregorianCalendar(
//                2017, Calendar.FEBRUARY, 5).getTime());
//
//        // Act for reading outside bounds.
//
//        boolean failedResult = validAreaSensorList.addReadingToMatchingSensor("SensorOne", 31D, new GregorianCalendar(
//                2015, Calendar.FEBRUARY, 1).getTime());
//
//        // Act for not existing sensor
//
//        boolean failedResult2 = validAreaSensorList.addReadingToMatchingSensor("xxxxxxx", 32D, new GregorianCalendar(
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
