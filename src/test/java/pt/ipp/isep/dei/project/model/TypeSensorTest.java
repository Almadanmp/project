package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TypeSensor tests class.
 */

class TypeSensorTest {
    // Common testing artifacts for this class.

    private TypeSensor validType;

    @BeforeEach
    void arrangeArtifacts() {
        validType = new TypeSensor("Temperature", "Celsius");
    }

    @Test
    void seeIfGetNameWorks() {
        // Arrange

        String expectedResult = "Temperature";

        // Act

        String actualResult = validType.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksSameContent() {
        // Arrange

        TypeSensor testType = new TypeSensor("Temperature", "Celsius");

        // Act

        boolean result = validType.equals(testType);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        TypeSensor testType = new TypeSensor("Rainfall", "l/m2");

        // Act

        boolean actualResult = validType.equals(testType);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentUnits() {
        // Arrange

        TypeSensor testType = new TypeSensor("Temperature", "Kelvin");

        // Act

        boolean actualResult = testType.equals(validType);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentName() {
        // Arrange

        TypeSensor testType = new TypeSensor("Movement", "Celsius");

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

        int expectedResult = 1;

        // Act

        int actualResult = validType.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetIdWorks() {
        TypeSensor typeSensor = new TypeSensor();
        Long expectedResult = 0L;
        Long actualResult = typeSensor.getId();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfToStringWorks() {
        TypeSensor typeSensor = new TypeSensor("temperature","C");
        String expectedResult = "TypeSensor[id=0, name='temperature', units='C']";
        String actualResult = typeSensor.toString();
        assertEquals(expectedResult,actualResult);
    }
}
