package pt.ipp.isep.dei.project.model.sensortype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Local;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SensorType tests class.
 */

class SensorTypeTest {
    // Common testing artifacts for this class.

    private SensorType validType;

    @BeforeEach
    void arrangeArtifacts() {
        validType = new SensorType("Temperature", "Celsius");
    }


    @Test
    void seeIfGetNameWorks() {
        // Arrange

        validType.setName("Rain");
        String expectedResult = "Rain";

        // Act

        String actualResult = validType.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetUnitWorks() {
        // Arrange

        validType.setUnits("Kelvin");
        String expectedResult = "Kelvin";

        // Act

        String actualResult = validType.getUnits();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksSameContent() {
        // Arrange

        SensorType testType = new SensorType("Temperature", "Celsius");

        // Act

        boolean result = validType.equals(testType);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        SensorType testType = new SensorType("Rainfall", "l/m2");

        // Act

        boolean actualResult = validType.equals(testType);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentUnits() {
        // Arrange

        SensorType testType = new SensorType("Temperature", "Kelvin");

        // Act

        boolean actualResult = testType.equals(validType);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentName() {
        // Arrange

        SensorType testType = new SensorType("Movement", "Celsius");

        // Act

        boolean actualResult = testType.equals(validType);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Arrange

        Local testLocal = new Local(21, 3, 55);

        // Act

        boolean actualResult = validType.equals(testLocal); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksOnItself() {
        // Act

        boolean actualResult = validType.equals(validType); // Needed for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void buildTypeSensorString() {
        // Arrange

        String expectedResult = "The type of the sensor is Temperature, and the unit of measurement is Celsius.";

        // Act

        String actualResult = validType.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange
        SensorType sensorType = new SensorType();
        int expectedResult = 1;

        // Act

        int actualResult = sensorType.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorks() {
        SensorType sensorType = new SensorType("temperature", "C");
        String expectedResult = "SensorType[name='temperature', units='C']";
        String actualResult = sensorType.toString();
        assertEquals(expectedResult, actualResult);
    }
}
