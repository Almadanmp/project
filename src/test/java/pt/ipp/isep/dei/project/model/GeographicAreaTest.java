package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicArea tests class.
 */

class GeographicAreaTest {
    private GeographicArea validArea;

    @BeforeEach
    void arrangeArtifacts() {
        validArea = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 50, 10));
    }

    @Test
    void seeIfGetTypeAreaWorks() {
        // Arrange

        AreaType expectedResult = new AreaType("Country");

        // Act

        AreaType actualResult = validArea.getAreaType();

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
    void seeIfEqualsWorks() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 50, 10));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalDiffTypeDiffLocal() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("City"), 300, 200,
                new Local(21, 31, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalSameNameDiffType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("City"), 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalDiffNameSameType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("Country"), 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseSameLocalDiffNameDiffType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("City"), 300, 200,
                new Local(50, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalSameNameSameType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 30, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalDiffNameSameType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("Country"), 300, 200,
                new Local(50, 21, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDiffLocalSameNameDiffType() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Portugal", new AreaType("City"), 300, 200,
                new Local(21, 50, 1));

        // Act

        boolean actualResult = validArea.equals(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        // Arrange

        RoomService testList = new RoomService();

        // Act

        boolean actualResult = validArea.equals(testList); // Necessary for Sonarqube coverage purposes.

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfGetSetSensorListWork() {
        // Arrange

        AreaSensor testAreaSensor = new AreaSensor("RF12345", "Vento", new SensorType("Atmosférico", "km/h"),
                new Local(12, 31, 21), new Date(), 6008L);
        validArea.addSensor(testAreaSensor);
        AreaSensorService expectedResult = new AreaSensorService();
        expectedResult.add(testAreaSensor);

        // Act

        AreaSensorService actualResult = validArea.getSensorList();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfGetSetMotherAreaWorks() {
        // Arrange

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("City"), 2, 5,
                new Local(22, 23, 100));
        validArea.setMotherArea(testArea);

        // Act

        GeographicArea actualResult = validArea.getMotherArea();

        // Assert

        assertEquals(testArea, actualResult);
    }

    @Test
    void seeIfGetSetMotherAreaWorksFalse() {
        // Act

        boolean actualResult = validArea.setMotherArea(null);

        // Assert

        assertFalse(actualResult);
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

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("City"), 2, 5,
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

        GeographicArea testArea = new GeographicArea("Porto", new AreaType("City"), 2, 5,
                new Local(22, 23, 100));


        // Act

        boolean actualResult = validArea.isContainedInArea(testArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfCheckIfAreaIsContainedWorksTransitive() {
        // Arrange

        GeographicArea firstTestArea = new GeographicArea("Porto", new AreaType("City"),
                2, 4, new Local(22, 22, 100));
        GeographicArea secondTestArea = new GeographicArea("Europe", new AreaType("Continent"),
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

        AreaSensor firstTestAreaSensor = new AreaSensor("Sensor 1", "Sensor 1", new SensorType("Temperature", "Celsius"), new Local(12, 12, 12), new Date(), 6008L);
        AreaSensor secondTestAreaSensor = new AreaSensor("Sensor 1", "Sensor 1", new SensorType("Temperature", "Celsius"), new Local(12, 12, 12), new Date(), 6008L);
        AreaSensor thirdTestAreaSensor = new AreaSensor("Sensor 3", "Sensor 3", new SensorType("Temperature", "Celsius"), new Local(12, 12, 12), new Date(), 6008L);

        // Act

        boolean result1 = validArea.addSensor(firstTestAreaSensor);
        boolean result2 = validArea.addSensor(secondTestAreaSensor);
        boolean result3 = validArea.addSensor(thirdTestAreaSensor);

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

        AreaSensor areaSensor = new AreaSensor("Sensor 1", "Sensor 1", new SensorType("Temperature", "Celsius"), new Local(12, 12, 12), new Date(), 6008L);
        validArea.addSensor(areaSensor);

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

        validArea.setName("Malta");

        // Act

        String id = validArea.getName();

        // Assert

        assertEquals("Malta", id);
    }

    @Test
    void seeIfGetTypeArea() {
        // Arrange

        AreaType type = new AreaType("Island");
        validArea.setAreaType(type);

        // Act

        AreaType actualType = validArea.getAreaType();

        // Assert

        assertEquals(type, actualType);
    }

    @Test
    void seeIfEqualsTypeAreaWorks() {
        // Arrange

        AreaType areaType1 = new AreaType("City");
        validArea.setAreaType(areaType1);

        // Act

        boolean actualResult = validArea.equalsTypeArea(areaType1);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsTypeAreaWorksFalse() {
        // Arrange

        AreaType areaType1 = new AreaType("City");
        validArea.setAreaType(areaType1);
        AreaType areaType2 = new AreaType("Street");

        // Act

        boolean actualResult = validArea.equalsTypeArea(areaType2);

        // Assert

        assertFalse(actualResult);

    }

    @Test
    void seeIfGetLocation() {
        // Arrange

        Local local = new Local(51, 24, 36);
        validArea.setLocation(local);

        // Act

        Local actualLocal = validArea.getLocal();

        // Assert

        assertEquals(local, actualLocal);

    }


    @Test
    void seeIfGetSensorList() {
        // Arrange

        AreaSensorService areaSensorService = new AreaSensorService();
        validArea.setSensorList(areaSensorService);

        // Act

        AreaSensorService actualAreaSensorService = validArea.getSensorList();

        // Assert

        assertEquals(areaSensorService, actualAreaSensorService);
    }

    @Test
    void seeIfGetLengthWidth() {
        // Arrange

        validArea.setWidth(5);
        validArea.setLength(10);

        // Act

        double actualWidth = validArea.getWidth();
        double actualLength = validArea.getLength();

        // Assert

        assertEquals(10, actualLength);
        assertEquals(5, actualWidth);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Arrange
        Local local = new Local(21, 1, 12);

        // Act
        boolean actualResult = validArea.equals(local);

        // Assert

        assertFalse(actualResult);


    }

    @Test
    void seeIfEqualsParametersWorks() {
        // Act
        boolean actualResult1 = validArea.equalsParameters("Portugal", new AreaType("Country"), new Local(50, 50, 10));
        boolean actualResult2 = validArea.equalsParameters("Porto", new AreaType("City"), new Local(20, 20, 20));
        boolean actualResult3 = validArea.equalsParameters("Porto", new AreaType("Country"), new Local(50, 50, 10));
        boolean actualResult4 = validArea.equalsParameters("Portugal", new AreaType("City"), new Local(50, 50, 10));
        boolean actualResult5 = validArea.equalsParameters("Portugal", new AreaType("Country"), new Local(20, 50, 10));

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void seeIfGetIdWorks() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        geographicArea.setId(6008L);
        Long expectedResult = 6008L;
        //Actual
        Long actualResult = geographicArea.getId();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSensorsOfGivenTypeWorks() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        AreaSensorService areaSensorService = new AreaSensorService();
        AreaSensor areaSensor1 = new AreaSensor("Sensor 1", "Sensor 1", new SensorType("temperature", "C"), new Local(12, 12, 12), new GregorianCalendar(2018, Calendar.JANUARY, 1).getTime(), 6008L);
        AreaSensor areaSensor2 = new AreaSensor("Sensor 2", "Sensor 2", new SensorType("rainfall", "mm"), new Local(12, 12, 12), new GregorianCalendar(2018, Calendar.JANUARY, 2).getTime(), 6008L);
        AreaSensor areaSensor3 = new AreaSensor("Sensor 3", "Sensor 3", new SensorType("temperature", "C"), new Local(12, 12, 12), new GregorianCalendar(2018, Calendar.JANUARY, 3).getTime(), 6008L);
        areaSensorService.add(areaSensor1);
        areaSensorService.add(areaSensor2);
        areaSensorService.add(areaSensor3);
        geographicArea.setSensorList(areaSensorService);
        AreaSensorService expectedResult = new AreaSensorService();
        expectedResult.add(areaSensor1);
        expectedResult.add(areaSensor3);
        //Act
        AreaSensorService actualResult = geographicArea.getSensorsOfGivenType("temperature");
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetLocationWorks() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea();
        geographicArea.setLocation(new Local(2, 1, 4));
        Local expectedResult = new Local(2, 1, 4);
        //Act
        Local actualResult = geographicArea.getLocation();
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}


