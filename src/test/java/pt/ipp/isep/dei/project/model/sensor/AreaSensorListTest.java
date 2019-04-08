package pt.ipp.isep.dei.project.model.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.services.units.Celsius;

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
    private AreaSensor firstValidAreaSensor;
    private AreaSensor secondValidAreaSensor;
    private AreaSensor thirdValidAreaSensor;

    @BeforeEach
    void arrangeArtifacts() {
        validAreaSensorList = new AreaSensorList();
        firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(
                31, 1, 2), new Date());
        firstValidAreaSensor.setActive(true);
        secondValidAreaSensor = new AreaSensor("SensorTwo", new SensorType("Temperature", "Celsius"),
                new Date());
        secondValidAreaSensor.setActive(true);
        thirdValidAreaSensor = new AreaSensor("SensorThree", new SensorType("Rainfall", "l/m2"),
                new Date());
        validAreaSensorList.add(firstValidAreaSensor);
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
        expectedResult.add(firstValidAreaSensor);

        // Act

        boolean actualResult = validAreaSensorList.equals(expectedResult);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithDifferentContent() {
        // Arrange

        AreaSensorList expectedResult = new AreaSensorList();
        expectedResult.add(secondValidAreaSensor);

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
        expectedResult.add(firstValidAreaSensor);
        expectedResult.add(secondValidAreaSensor);
        validAreaSensorList.add(secondValidAreaSensor);
        validAreaSensorList.add(thirdValidAreaSensor);

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

        validAreaSensorList.add(secondValidAreaSensor);
        validAreaSensorList.add(thirdValidAreaSensor);
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

        AreaReading areaReadingOne = new AreaReading(31, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime(), new Celsius());
        validAreaSensorList.add(secondValidAreaSensor);
        firstValidAreaSensor.addReading(areaReadingOne);
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

        AreaReading areaReadingOne = new AreaReading(31, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime(), new Celsius());
        validAreaSensorList.add(secondValidAreaSensor);
        secondValidAreaSensor.addReading(areaReadingOne);
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

        AreaReading areaReadingOne = new AreaReading(31, new GregorianCalendar(2018, Calendar.JANUARY, 1).getTime(), new Celsius());
        AreaReading areaReadingTwo = new AreaReading(20, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime(), new Celsius());
        validAreaSensorList.add(secondValidAreaSensor);
        firstValidAreaSensor.addReading(areaReadingOne);
        secondValidAreaSensor.addReading(areaReadingTwo);
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
                .getTime(), new Celsius());
        firstValidAreaSensor.addReading(mostRecentAreaReading);
        AreaReading secondAreaReading = new AreaReading(3, new GregorianCalendar(2018, Calendar.JANUARY, 2)
                .getTime(), new Celsius());
        secondValidAreaSensor.addReading(secondAreaReading);
        AreaReading thirdAreaReading = new AreaReading(3, new GregorianCalendar(2017, Calendar.JANUARY, 1)
                .getTime(), new Celsius());
        thirdValidAreaSensor.addReading(thirdAreaReading);

        // Test for when most recent reading is in the first sensor.

        // Arrange

        validAreaSensorList.add(secondValidAreaSensor);
        validAreaSensorList.add(thirdValidAreaSensor);

        // Act

        AreaSensor actualResult1 = validAreaSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidAreaSensor, actualResult1);

        // Test for when most recent reading is in the middle sensor.

        // Arrange

        validAreaSensorList = new AreaSensorList();
        validAreaSensorList.add(secondValidAreaSensor);
        validAreaSensorList.add(firstValidAreaSensor);
        validAreaSensorList.add(thirdValidAreaSensor);

        // Act

        AreaSensor actualResult2 = validAreaSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidAreaSensor, actualResult2);

        // Test for when most recent reading is in the last sensor.

        // Arrange

        validAreaSensorList = new AreaSensorList();
        validAreaSensorList.add(secondValidAreaSensor);
        validAreaSensorList.add(thirdValidAreaSensor);
        validAreaSensorList.add(firstValidAreaSensor);

        // Act

        AreaSensor actualResult3 = validAreaSensorList.getMostRecentlyUsedSensor();

        // Assert

        assertEquals(firstValidAreaSensor, actualResult3);
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
        twoSensorsList.add(firstValidAreaSensor);
        twoSensorsList.add(secondValidAreaSensor);

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

        AreaReading areaReadingOne = new AreaReading(31, new GregorianCalendar(2018, Calendar.MARCH, 1).getTime(), new Celsius());
        secondValidAreaSensor.addReading(areaReadingOne);

        twoSensorsList.add(firstValidAreaSensor);
        twoSensorsList.add(secondValidAreaSensor);

        AreaSensorList expectedResult1 = new AreaSensorList();
        expectedResult1.add(secondValidAreaSensor);

        // Act

        AreaSensorList actualResult1 = twoSensorsList.getSensorsWithReadings();

        // Assert

        assertThrows(IllegalArgumentException.class, emptyList::getSensorsWithReadings);
        assertEquals(expectedResult1, actualResult1);
    }

    @Test
    void seeIfGetByIndexWorks() {
        //Arrange

        validAreaSensorList.add(secondValidAreaSensor);

        //Act

        AreaSensor actualResult1 = validAreaSensorList.get(0);
        AreaSensor actualResult2 = validAreaSensorList.get(1);

        //Assert

        assertEquals(firstValidAreaSensor, actualResult1);
        assertEquals(secondValidAreaSensor, actualResult2);
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

        AreaSensor[] expectedResult1 = new AreaSensor[0];
        AreaSensor[] expectedResult2 = new AreaSensor[1];
        AreaSensor[] expectedResult3 = new AreaSensor[2];

        AreaSensorList emptyAreaSensorList = new AreaSensorList();
        AreaSensorList validAreaSensorList2 = new AreaSensorList();
        validAreaSensorList2.add(firstValidAreaSensor);
        validAreaSensorList2.add(secondValidAreaSensor);

        expectedResult2[0] = firstValidAreaSensor;
        expectedResult3[0] = firstValidAreaSensor;
        expectedResult3[1] = secondValidAreaSensor;

        //Act

        AreaSensor[] actualResult1 = emptyAreaSensorList.getElementsAsArray();
        AreaSensor[] actualResult2 = validAreaSensorList.getElementsAsArray();
        AreaSensor[] actualResult3 = validAreaSensorList2.getElementsAsArray();

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

        AreaSensor areaSensorSameLocalHouse = new AreaSensor("123", "sameLocalAsHouse", new SensorType("Temperature", "K"), new Local(20, 20, 20), date);
        AreaSensor areaSensorDiffLocalHouse = new AreaSensor("125", "DiffLocalAsHouse", new SensorType("Temperature", "K"), new Local(20, 25, 20), date);

        AreaSensorList validAreaSensorList = new AreaSensorList();
        validAreaSensorList.add(areaSensorDiffLocalHouse);
        validAreaSensorList.add(areaSensorSameLocalHouse);

        //House

        List<String> deviceTypeString = new ArrayList<>();
        Address address = new Address("Rua Dr. António Bernardino de Almeida","431", "4200-072", "Porto","Portugal");
        House house = new House("ISEP", address, new Local(20, 20, 20), 60,
                180, deviceTypeString);
        house.setMotherArea(new GeographicArea("Porto", new AreaType
                ("Cidade"), 2, 3, new Local(4, 4, 100)));


        AreaSensorList expectedResult = new AreaSensorList();
        expectedResult.add(areaSensorSameLocalHouse);

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
        twoSensors.add(firstValidAreaSensor);
        twoSensors.add(secondValidAreaSensor);

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

        boolean actualResult1 = emptyList.contains(firstValidAreaSensor);
        boolean actualResult2 = validAreaSensorList.contains(firstValidAreaSensor);
        boolean actualResult3 = validAreaSensorList.contains(secondValidAreaSensor);

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
