package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Local tests class.
 */

class LocalTest {
    @Test
    void seeIfGetSetLatitudeWorks() {
        //Arrange
        double expectedResult = 21;
        double actualResult;
        Local c = new Local(21, 3, 50);

        //Act
        actualResult = c.getLatitude();

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetSetLongitudeWorks() {
        //Arrange
        double expectedResult = 3;
        double actualResult;
        Local c = new Local(21, 3, 50);

        //Act
        actualResult = c.getLongitude();

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetSetAltitudeWorks() {
        //Arrange
        double expectedResult = 57;
        double actualResult;
        Local c = new Local(21, 3, 210);

        //Act
        c.setAltitude(57);
        actualResult = c.getAltitude();

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetAltitudeBuilderWorks() {
        //Arrange
        double expectedResult = 210;
        double actualResult;
        Local c = new Local(21, 3, 210);

        //Act
        actualResult = c.getAltitude();

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfEqualsWorksSameObjects() {
        //Arrange
        Local l1 = new Local(23, 42, 2);
        Local l2 = new Local(23, 42, 2);
        boolean actualResult;

        //Act
        actualResult = l1.equals(l2);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotInstance() {
        //Arrange
        Local l1 = new Local(23, 42, 2);
        TypeSensor t1 = new TypeSensor("temperature", "celsius");
        boolean actualResult;

        //Act
        actualResult = l1.equals(t1);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        //Arrange
        Local l1 = new Local(23, 42, 2);
        Local l2 = new Local(21, 21, 5);
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = l1.equals(l2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentLatitude() {
        //Arrange
        Local l1 = new Local(23, 21, 5);
        Local l2 = new Local(21, 21, 5);
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = l1.equals(l2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentLongitude() {
        //Arrange
        Local l1 = new Local(21, 23, 5);
        Local l2 = new Local(21, 21, 5);
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = l1.equals(l2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentAltitude() {
        //Arrange
        Local l1 = new Local(21, 21, 6);
        Local l2 = new Local(21, 21, 5);
        boolean expectedResult = true;
        boolean actualResult;

        //Act
        actualResult = l1.equals(l2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        Local l1 = new Local(21, 21, 5);
        int expectedResult = 1;
        int actualResult = l1.hashCode();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfWeGetLinearDistanceInKmBetweenTwoLocations() {
        //Arrange
        Local porto = new Local(41.1496100,-8.6109900, 50);
        Local sidney = new Local(-33.865143,151.209900, 50);
        double expectedResult = 18064.77;

        //Act
        double result = porto.getLinearDistanceBetweenLocalsInKm(sidney);

        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfWeDoNotGetLinearDistanceInKmBetweenTwoLocations() {
        //Arrange
        Local porto = new Local(41.1496100,-8.6109900, 45);
        Local lisboa = new Local(38.7166700,-9.1333300,45);
        double expectedResult = 300;

        //Act
        double result = porto.getLinearDistanceBetweenLocalsInKm(lisboa);

        //Assert
        assertNotSame(expectedResult, result);
    }

    @Test
    void seeIfWeGetLinearDistanceInKmBetweenTwoLocationsWithConstructorWith3Parameters() {
        //Arrange
        Local porto = new Local(41.1496100, -8.6109900, 97);
        Local lisboa = new Local(38.7166700, -9.1333300, 45);
        double expectedResult = 274.15;

        //Act
        double result = lisboa.getLinearDistanceBetweenLocalsInKm(porto);

        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfWeGetLinearDistanceInKmBetweenTwoLocationsChangingTheLatitude() {
        //Arrange
        Local porto = new Local(30, -8.6109900, 97);
        Local lisboa = new Local(38.7166700, -9.1333300, 45);
        porto.setLatitude(41.1496100);
        double expectedResult = 274.15;

        //Act
        double result = lisboa.getLinearDistanceBetweenLocalsInKm(porto);

        //Assert
        assertEquals(expectedResult, result, 0.01);
    }

}
