package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertTrue;

/**
 * GeographicArea tests class.
 */

class GeographicAreaTest {
    private GeographicArea validArea;

    @BeforeEach
    void arrangeArtifacts() {
        validArea = new GeographicArea("Portugal", new TypeArea("Country"), 300, 200,
                new Local(50, 50, 10));
    }

    @Test
    void seeIfGetTypeAreaWorks() {
        // Arrange

        TypeArea expectedResult = new TypeArea("Country");

        // Act

        TypeArea actualResult = validArea.getTypeArea();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorksSameObject() {
        // Act

        boolean actualResult = validArea.equals(validArea); // Needed for SonarQube coverage purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new TypeArea("Country"), 300, 200,
                new Local(50, 50, 10));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        // Arrange

        RoomList testList = new RoomList();

        // Act

        boolean actualResult = validArea.equals(testList); // Necessary for Sonarqube coverage purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetSetSensorListWork() {
        // Arrange

        Sensor testSensor = new Sensor("RF12345", "Vento", new TypeSensor("Atmosférico", "km/h"),
                new Local(12, 31, 21), new Date());
        validArea.addSensor(testSensor);
        SensorList expectedResult = new SensorList();
        expectedResult.add(testSensor);

        // Act

        SensorList actualResult = validArea.getSensorList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetSetMotherAreaWorks() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new TypeArea("City"), 2, 5,
                new Local(22, 23, 100));
        validArea.setMotherArea(testArea);

        // Act

        GeographicArea actualResult = validArea.getMotherArea();

        // Assert

        assertEquals(testArea, actualResult);
    }

    @Test
    void seeIfGetSetDescription() {
        // Arrange

        validArea.setDescription("Country of Portugal.");

        // Act

        String actualResult = validArea.getDescription();

        // Assert

        assertEquals("Country of Portugal.", actualResult);
    }

    @Test
    void seeIfCheckIfAreaIsContainedWorksTrue() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new TypeArea("City"), 2, 5,
                new Local(22, 23, 100));
        validArea.setMotherArea(testArea);

        // Act

        boolean actualResult = validArea.isContainedInArea(testArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfCheckIfAreaIsContainedWorksFalse() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new TypeArea("City"), 2, 5,
                new Local(22, 23, 100));


        // Act

        boolean actualResult = validArea.isContainedInArea(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfCheckIfAreaIsContainedWorksTransitive() {
        // Arrange

        GeographicArea firstTestArea = new GeographicArea("Porto", new TypeArea("City"),
                2, 4, new Local(22, 22, 100));
        GeographicArea secondTestArea = new GeographicArea("Europe", new TypeArea("Continent"),
                200, 400, new Local(22, 22, 100));
        firstTestArea.setMotherArea(validArea);
        validArea.setMotherArea(secondTestArea);

        // Act

        boolean actualResult = firstTestArea.isContainedInArea(secondTestArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeAddSensorToGA() {
        // Arrange

        Sensor firstTestSensor = new Sensor("Sensor 1", new TypeSensor("Temperature", "Celsius"), new Date());
        Sensor secondTestSensor = new Sensor("Sensor 1", new TypeSensor("Temperature", "Celsius"), new Date());
        Sensor thirdTestSensor = new Sensor("Sensor 3", new TypeSensor("Temperature", "Celsius"), new Date());

        // Act

        boolean result1 = validArea.addSensor(firstTestSensor);
        boolean result2 = validArea.addSensor(secondTestSensor);
        boolean result3 = validArea.addSensor(thirdTestSensor);

        // Assert

        assertTrue(result1);
        assertFalse(result2);
        assertTrue(result3);
    }

    @Test
    void seeIfIsSensorListEmptyWorks() {
        // Act With No Sensors

        boolean actualResult1 = validArea.isSensorListEmpty();

        // Assert With No Sensors

        assertTrue(actualResult1);

        // Arrange

        Sensor sensor = new Sensor("Sensor 1", new TypeSensor("Temperature", "Celsius"), new Date());
        validArea.addSensor(sensor);

        // Act

        boolean actualResult2 = validArea.isSensorListEmpty();

        // Assert

        assertFalse(actualResult2);
    }

    @Test
    void seeIfToStringWorks() {
        // Arrange

        String expectedResult = "Portugal, Country, 50.0º lat, 50.0º long\n";

        // Act

        String actualResult = validArea.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validArea.hashCode();

        // Assert

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetId() {
        // Arrange

        validArea.setUniqueId(UUID.randomUUID());

        // Act

        UUID uuid = validArea.getUniqueID();

        // Assert

        assertTrue(uuid instanceof UUID); // Needed for Sonarqube testing purposes.
    }
}

