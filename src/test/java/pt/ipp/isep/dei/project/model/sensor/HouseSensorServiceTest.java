package pt.ipp.isep.dei.project.model.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * House Sensor Service tests class.
 */
class HouseSensorServiceTest {

    // Common artifacts for testing in this class.

    private HouseSensorService validHouseSensorService; // Contains the first valid sensor by default.
    private HouseSensor firstValidHouseSensor;
    private HouseSensor secondValidHouseSensor;
    private HouseSensor thirdValidHouseSensor;

    @BeforeEach
    void arrangeArtifacts() {
        validHouseSensorService = new HouseSensorService();
        firstValidHouseSensor = new HouseSensor("SensorOne", new SensorType("Temperature", "Celsius"),new Date());
        firstValidHouseSensor.setActive(true);
        secondValidHouseSensor = new HouseSensor("SensorTwo", new SensorType("Temperature", "Celsius"), new Date());
        secondValidHouseSensor.setActive(true);
        thirdValidHouseSensor = new HouseSensor("SensorThree", new SensorType("Rainfall", "l/m2"), new Date());
        validHouseSensorService.add(firstValidHouseSensor);
    }

    @Test
    void seeIfEqualsWorksOnSameObject() {
        //Act

        boolean actualResult = validHouseSensorService.equals(validHouseSensorService); // Required for Sonarqube testing purposes.

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnDiffObject() {
        //Act

        boolean actualResult = validHouseSensorService.equals(20D); // Required for Sonarqube testing purposes.

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithSameContent() {
        // Arrange

        HouseSensorService expectedResult = new HouseSensorService();
        expectedResult.add(firstValidHouseSensor);

        // Act

        boolean actualResult = validHouseSensorService.equals(expectedResult);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnSensorListWithDifferentContent() {
        // Arrange

        HouseSensorService expectedResult = new HouseSensorService();
        expectedResult.add(secondValidHouseSensor);

        // Act

        boolean actualResult = validHouseSensorService.equals(expectedResult);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validHouseSensorService.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeWorks() {
        // Arrange

        HouseSensorService expectedResult = new HouseSensorService();
        expectedResult.add(firstValidHouseSensor);
        expectedResult.add(secondValidHouseSensor);
        validHouseSensorService.add(secondValidHouseSensor);
        validHouseSensorService.add(thirdValidHouseSensor);

        // Act

        HouseSensorService actualResult = validHouseSensorService.getSensorListByType("Temperature");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeItGetSensorListByTypeWorksFalse() {
        // Arrange

        HouseSensorService expectedResult = new HouseSensorService();

        // Act

        HouseSensorService actualResult = validHouseSensorService.getSensorListByType("Pressure");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorks() {
        // Arrange

        validHouseSensorService.add(secondValidHouseSensor);
        validHouseSensorService.add(thirdValidHouseSensor);
        String expectedResult = "---------------\n" +
                "0) Name: SensorOne | Type: Temperature | Active\n" +
                "1) Name: SensorTwo | Type: Temperature | Active\n" +
                "2) Name: SensorThree | Type: Rainfall | Active\n" +
                "---------------\n";

        // Act

        String actualResult = validHouseSensorService.toString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorksEmpty() {
        // Arrange

        validHouseSensorService = new HouseSensorService();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = validHouseSensorService.toString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsEmptyWorks() {
        // Arrange

        HouseSensorService emptyList = new HouseSensorService();
        HouseSensorService twoSensorsList = new HouseSensorService();
        twoSensorsList.add(firstValidHouseSensor);
        twoSensorsList.add(secondValidHouseSensor);

        // Act

        boolean actualResult1 = emptyList.isEmpty();
        boolean actualResult2 = validHouseSensorService.isEmpty();
        boolean actualResult3 = twoSensorsList.isEmpty();

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfGetByIndexWorks() {
        //Arrange

        validHouseSensorService.add(secondValidHouseSensor);

        //Act

        HouseSensor actualResult1 = validHouseSensorService.get(0);
        HouseSensor actualResult2 = validHouseSensorService.get(1);

        //Assert

        assertEquals(firstValidHouseSensor, actualResult1);
        assertEquals(secondValidHouseSensor, actualResult2);
    }

    @Test
    void getByIndexEmptySensorList() {
        //Arrange

        HouseSensorService emptyList = new HouseSensorService();

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));

        //Assert

        assertEquals("The sensor list is empty.", exception.getMessage());
    }

    @Test
    void getElementsAsArray() {
        //Arrange

        HouseSensor[] expectedResult1 = new HouseSensor[0];
        HouseSensor[] expectedResult2 = new HouseSensor[1];
        HouseSensor[] expectedResult3 = new HouseSensor[2];

        HouseSensorService emptyAreaSensorService = new HouseSensorService();
        HouseSensorService validAreaSensorService2 = new HouseSensorService();
        validAreaSensorService2.add(firstValidHouseSensor);
        validAreaSensorService2.add(secondValidHouseSensor);

        expectedResult2[0] = firstValidHouseSensor;
        expectedResult3[0] = firstValidHouseSensor;
        expectedResult3[1] = secondValidHouseSensor;

        //Act

        HouseSensor[] actualResult1 = emptyAreaSensorService.getElementsAsArray();
        HouseSensor[] actualResult2 = validHouseSensorService.getElementsAsArray();
        HouseSensor[] actualResult3 = validAreaSensorService2.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult1);
        assertArrayEquals(expectedResult2, actualResult2);
        assertArrayEquals(expectedResult3, actualResult3);
    }

    @Test
    void seeIfSizeWorks() {

        //Arrange

        HouseSensorService emptyList = new HouseSensorService();
        HouseSensorService twoSensors = new HouseSensorService();
        twoSensors.add(firstValidHouseSensor);
        twoSensors.add(secondValidHouseSensor);

        //Act

        int actualResult1 = emptyList.size();
        int actualResult2 = validHouseSensorService.size();
        int actualResult3 = twoSensors.size();

        //Assert

        assertEquals(0, actualResult1);
        assertEquals(1, actualResult2);
        assertEquals(2, actualResult3);
    }

    @Test
    void seeIfContainsWorks() {

        //Arrange

        HouseSensorService emptyList = new HouseSensorService();

        //Act

        boolean actualResult1 = emptyList.contains(firstValidHouseSensor);
        boolean actualResult2 = validHouseSensorService.contains(firstValidHouseSensor);
        boolean actualResult3 = validHouseSensorService.contains(secondValidHouseSensor);

        //Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
    }
}