package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Local tests class.
 */

class LocalTest {
    private Local validLocal;

    @BeforeEach
    void arrangeArtifacts(){
        validLocal = new Local(30, -8.6109900, 97);
    }

    @Test
    void seeIfConstructorWorks() {
        // Arrange

        Local local = new Local(2, 2, 5);

        // Act

        double actualResult1 = local.getLatitude();
        double actualResult2 = local.getLongitude();
        double actualResult3 = local.getAltitude();

        // Assert

        assertEquals(2, actualResult1, 0.001);
        assertEquals(2, actualResult2, 0.001);
        assertEquals(5, actualResult3, 0.001);
    }

    @Test
    void seeIfGetLatitudeWorks() {
        // Arrange

        double expectedResult = 30;

        // Act

        double actualResult = validLocal.getLatitude();

        // Assert

        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfGetLongitudeWorks() {
        // Arrange

        double expectedResult = -8.6109900;

        // Act

        double actualResult = validLocal.getLongitude();

        // Assert

        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfSetGetAltitudeWorks() {
        // Arrange

        double expectedResult = 12;

        // Act

        validLocal.setAltitude(12);
        double actualResult = validLocal.getAltitude();

        // Assert

        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void seeIfEqualsWorksSameObject() {
        //Act

        boolean actualResult = validLocal.equals(validLocal); // Necessary for Sonarqube coverage purposes.

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotInstance() {
        // Arrange

        TypeSensor t1 = new TypeSensor("Temperature", "Celsius");

        // Act

        boolean actualResult = validLocal.equals(t1); // Necessary for Sonarqube coverage purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        // Arrange

        Local l2 = new Local(21, 21, 5);

        // Act

        boolean actualResult = validLocal.equals(l2);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentLatitude() {
        // Arrange

        Local l1 = new Local(23, -8.6109900, 97);

        // Act

        boolean actualResult = l1.equals(validLocal);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentLongitude() {
        // Arrange

        Local l2 = new Local(30, 21, 97);

        // Act

        boolean actualResult = validLocal.equals(l2);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentAltitude() {
        // Arrange

        Local l2 = new Local(30, -8.6109900, 5);

        // Act

        boolean actualResult = validLocal.equals(l2);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validLocal.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfWeGetLinearDistanceInKmBetweenTwoLocations() {
        // Arrange

        Local sidney = new Local(-33.865143,151.209900, 50);
        double expectedResult = 18066.402164597315;

        // Act

        double result = validLocal.getLinearDistanceBetweenLocalsInKm(sidney);

        // Assert

        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfWeDoNotGetLinearDistanceInKmBetweenTwoLocations() {
        // Arrange

        Local lisboa = new Local(38.7166700,-9.1333300,45);
        double expectedResult = 300;

        // Act

        double result = validLocal.getLinearDistanceBetweenLocalsInKm(lisboa);

        // Assert

        assertNotSame(expectedResult, result);
    }

    @Test
    void seeIfWeGetLinearDistanceInKmBetweenTwoLocationsWithConstructorWith3Parameters() {
        // Arrange

        Local lisboa = new Local(38.7166700, -9.1333300, 45);
        double expectedResult = 970.4292200999453;

        // Act

        double result = lisboa.getLinearDistanceBetweenLocalsInKm(validLocal);

        // Assert

        assertEquals(expectedResult, result, 0.01);
    }

    @Test
    void seeIfWeGetLinearDistanceInKmBetweenTwoLocationsChangingTheLatitude() {
        // Arrange

        Local lisboa = new Local(38.7166700, -9.1333300, 45);
        double expectedResult = 274.170339162403;
        validLocal.setLatitude(41.1496100);

        // Act

        double result = lisboa.getLinearDistanceBetweenLocalsInKm(validLocal);

        // Assert

        assertEquals(expectedResult, result, 0.01);
    }
}
