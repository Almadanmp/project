package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TypeSensor tests class.
 */

class TypeSensorTest {

    @Test
    void constructorTypeSensorTest() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        String actualResult = "Humidade";

        //Act
        String expectedResult = t1.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void constructorTypeSensorTestSameObject() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        TypeSensor t2 = new TypeSensor("Vento", "km/h");
        t2.setName("Humidade");
        t2.setUnits("kg/m³");

        //Act
        boolean expectedResult = t1.equals(t2);
        boolean result = true;

        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfSetNameWorksNull() {
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        assertThrows(IllegalArgumentException.class, () -> {
            t1.setName(null);
        });
    }

    @Test
    void seeIfSetNameWorksEmpty() {
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        assertThrows(IllegalArgumentException.class, () -> {
            t1.setName("");
        });
    }

    @Test
    void seeIfSetNameWorks() {
        TypeSensor t1 = new TypeSensor("Density", "Density");
        t1.setName("Densidade");
        String expectedResult = "Densidade";
        String result = t1.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    void testTypeSensorGetAndSet() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        t1.setName("Movimento");
        String actualResult = "Movimento";

        //Act
        String expectedResult = t1.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testTypeSensorGetAndSetPartII() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Humidade", "kg/m³");
        t1.setName("Movimento");
        t1.setName("Luz");
        String actualResult = "Luz";

        //Act
        String expectedResult = t1.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        TypeSensor t2 = new TypeSensor("Pluviosidade", "l/m2");
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = t1.equals(t2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksObject() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Movimento", "m/s");
        TypeSensor t2 = new TypeSensor("Movimento", "m/s");
        boolean actualResult;

        //Act
        actualResult = t1.equals(t2);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksSameObject() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Movimento", "m/s");
        TypeSensor t2 = new TypeSensor("Movimento", "m/s");
        boolean actualResult;

        //Act
        actualResult = t1.equals(t2);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsFalseWorksSameODifUnits() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Movimento", "m");
        TypeSensor t2 = new TypeSensor("Movimento", "m/s");
        boolean actualResult;

        //Act
        actualResult = t1.equals(t2);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsFalseWorksSameObjectDifName() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Coisa", "m/s");
        TypeSensor t2 = new TypeSensor("Movimento", "m/s");
        boolean actualResult;

        //Act
        actualResult = t1.equals(t2);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Movimento", "m/s");
        Local l1 = new Local(21, 3, 55);
        boolean actualResult;

        //Act
        actualResult = t1.equals(l1);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        TypeSensor t1 = new TypeSensor("Temperatura", "Celsius");
        int expectedResult = 1;
        int actualResult = t1.hashCode();
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfSetterForTypeSensorUnitsThrowsException(){
        TypeSensor t1 = new TypeSensor("Temperature", "Cº");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            t1.setUnits("");
        });
        assertEquals("Please Insert Valid String for Units of The Sensor",exception.getMessage());
    }
}
