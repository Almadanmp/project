package pt.ipp.isep.dei.project.model.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        firstValidHouseSensor = new HouseSensor("T32875", "SensorOne", new SensorType("Temperature", "Celsius"), new Date(), "RoomDFS");
        firstValidHouseSensor.setActive(true);
        secondValidHouseSensor = new HouseSensor("T32876", "SensorTwo", new SensorType("Temperature", "Celsius"), new Date(), "RoomDFS");
        secondValidHouseSensor.setActive(true);
        thirdValidHouseSensor = new HouseSensor("T32877", "SensorThree", new SensorType("Rainfall", "l/m2"), new Date(), "RoomDFS");
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
    void seeIfToStringWorks() {
        // Arrange

        List<HouseSensor> houseSensors = new ArrayList<>();
        houseSensors.add(secondValidHouseSensor);
        houseSensors.add(thirdValidHouseSensor);
        String expectedResult = "---------------\n" +
                "T32876SensorTwo | Type: Temperature | Active\n" +
                "T32877SensorThree | Type: Rainfall | Active\n" +
                "---------------\n";

        // Act

        String actualResult = validHouseSensorService.buildString(houseSensors);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorksEmpty() {
        // Arrange

        List<HouseSensor> houseSensors = new ArrayList<>();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = validHouseSensorService.buildString(houseSensors);

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

}