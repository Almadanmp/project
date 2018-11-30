package Sprint_0;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class Type_Sensor_Test {

    @Test
    public void constructorTypeSensorTest() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Humidade");
        String actualResult = "Humidade";

        //Act
        String expectedResult = t1.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void constructorTypeSensorTestSameObject() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Humidade");
        TypeSensor t2 = new TypeSensor("Vento");
        t2.setName("Humidade");

        //Act
         boolean expectedResult = t1.equals(t2);
         boolean result = true;

        //Assert
        assertEquals(result, expectedResult);
        
    }
    @Test
    public void testTypeSensorGetAndSet() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Humidade");
        t1.setName("Movimento");
        String actualResult = "Movimento";

        //Act
        String expectedResult = t1.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testTypeSensorGetAndSetPartII() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Humidade");
        t1.setName("Movimento");
        t1.setName("Luz");
        String actualResult = "Luz";

        //Act
        String expectedResult = t1.getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsWorksDifferentObject() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Temperatura");
        TypeSensor t2 = new TypeSensor("Pluviosidade");
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = t1.equals(t2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsWorksSameObject() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Movimento");
        TypeSensor t2 = t1;
        boolean expectedResult = true;
        boolean actualResult;

        //Act
        actualResult = t1.equals(t2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsWorksNotAnInstance() {
        //Arrange
        TypeSensor t1 = new TypeSensor("Movimento");
        Local l1 = new Local(21,3,55);
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = t1.equals(l1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void hashCodeDummyTest() {
        TypeSensor t1 = new TypeSensor("Temperatura");
        int expectedResult = 1;
        int actualResult = t1.hashCode();
        assertEquals(expectedResult, actualResult);
    }
}
