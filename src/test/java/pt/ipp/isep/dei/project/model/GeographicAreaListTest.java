package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicAreaList tests class.
 */

class GeographicAreaListTest {
    // Common testing artifacts for this class.

    private GeographicAreaList validList;
    private GeographicArea firstValidArea;
    private GeographicArea secondValidArea;

    @Mock
    GeographicAreaRepository geographicAreaRepository;


    @BeforeEach
    void arrangeArtifacts() {
        firstValidArea = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 50, 10));
        secondValidArea = new GeographicArea("Europe", new AreaType("Continent"), 3000, 2000,
                new Local(90, 100, 10));
        validList = new GeographicAreaList(geographicAreaRepository);
        validList.addGeographicArea(firstValidArea);
    }

    @Test
    void seeIfAddGAToListWorksAlreadyContained() {
        //Act

        boolean actualResult = validList.addGeographicArea(firstValidArea);
        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddGAToListWorks() {
        //Act

        boolean actualResult = validList.addGeographicArea(secondValidArea);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfContainsWorksTrue() {
        // Act

        boolean actualResult = validList.contains(firstValidArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfFalseWhenGivenGeoAreaIsNotContainedInGeographicAreaList() {
        // Act

        boolean actualResult = validList.contains(secondValidArea);

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfGetGeoAreaByTypeWorks() {
        // Arrange

        GeographicAreaList expectedResult = new GeographicAreaList(geographicAreaRepository);
        expectedResult.addGeographicArea(firstValidArea);

        // Act

        GeographicAreaList actualResult = validList.getGeoAreasByType("Country");

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        GeographicAreaList testList = new GeographicAreaList(geographicAreaRepository);
        testList.addGeographicArea(secondValidArea);

        //Act

        boolean actualResult = validList.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentObject() {
        // Arrange

        RoomList testList = new RoomList();

        //Act

        boolean actualResult = validList.equals(testList); // Needed for SonarQube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintsGeoAList() {
        // Arrange

        String expectedResult = "---------------\n" +
                "0) Name: Portugal | Type: Country | Latitude: 50.0 | Longitude: 50.0\n" +
                "---------------\n";

        // Act

        String result = validList.buildString();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsGeoAListIfEmpty() {
        // Arrange

        GeographicAreaList testList = new GeographicAreaList(geographicAreaRepository);
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = testList.buildString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfContainsGAByParametersFalse() {
        // Act

        boolean actualResult = validList.containsObjectMatchesParameters("Lisboa", new AreaType("City"), new Local(21,
                30, 40));


        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfContainsGAByParametersTrue() {
        // Act

        boolean actualResult = validList.containsObjectMatchesParameters("Portugal", new AreaType("Country"), new Local(50,
                50, 10));


        // Assert

        assertTrue(actualResult);
    }

    @Test
    void checkIfGAExistsIfEmptyList() {
        // Arrange

        GeographicAreaList testList = new GeographicAreaList(geographicAreaRepository);

        // Act

        boolean actualResult = testList.containsObjectMatchesParameters("Lisboa", new AreaType("City"), new Local(21,
                30, 40));


        // Assert

        assertFalse(actualResult);
    }

    @Test
    void isEmpty() {
        //Arrange

        GeographicAreaList emptyTestList = new GeographicAreaList(geographicAreaRepository);


        // Act

        boolean actualResult1 = emptyTestList.isEmpty();
        boolean actualResult2 = validList.isEmpty();

        // Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    void seeIfGetByIndexWorks() {
        //Arrange

        validList.addGeographicArea(secondValidArea);

        //Act

        GeographicArea actualResult1 = validList.get(0);
        GeographicArea actualResult2 = validList.get(1);

        //Assert

        assertEquals(firstValidArea, actualResult1);
        assertEquals(secondValidArea, actualResult2);
    }


    @Test
    void getByIndexEmptyGAList() {
        //Arrange

        GeographicAreaList emptyList = new GeographicAreaList(geographicAreaRepository);

        //Act

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));

        //Assert

        assertEquals("The geographic area list is empty.", exception.getMessage());
    }


    @Test
    void createGA() {
        String iD = "Coimbra";
        AreaType areaType = new AreaType("Distrito");
        Local local = new Local(12, 12, 12);
        GeographicArea expectedResult = new GeographicArea(iD, areaType, 12, 12, local);

        GeographicArea actualResult = validList.createGA(iD, areaType, 12, 12, local);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDoesNotAddWithoutPersisting() {
        // Arrange

        GeographicAreaList expectedResult = new GeographicAreaList(geographicAreaRepository);
        expectedResult.addGeographicArea(firstValidArea);

        // Act

        boolean actualResult = expectedResult.addGeographicArea(firstValidArea);
        ;

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetsAreaListSensors() {
        // Arrange
        AreaSensorService validAreaSensorService = new AreaSensorService();
        AreaSensor firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(
                31, 1, 2), new Date());
        firstValidAreaSensor.setActive(true);
        AreaSensor secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", new SensorType("Temperature", "Celsius"), new Local(10,10,10),
                new Date());
        secondValidAreaSensor.setActive(true);

        GeographicAreaList geographicAreaList = new GeographicAreaList(geographicAreaRepository);

        // Act

        AreaSensorService actualResult = geographicAreaList.getAreaListSensors();

        // Assert

        assertEquals(validAreaSensorService, actualResult);
    }

    @Test
    void seeIfGetsGeoAreasByType() {
        // Arrange

        validList.addGeographicArea(firstValidArea);

        GeographicAreaList expectedResult = new GeographicAreaList(geographicAreaRepository);
        expectedResult.addGeographicArea(firstValidArea);


        // Act

        GeographicAreaList actualResult = validList.getGeoAreasByType("Country");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemovesGeographicAreaTrue() {
        // Act

        boolean actualResult = validList.removeGeographicArea(firstValidArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetAreaListSensorsWorks() {
        // Arrange

        AreaSensor firstValidAreaSensor = new AreaSensor("SensOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(10,10,10), new Date());
        AreaSensor secondValidAreaSensor = new AreaSensor("SensTwo", "SensTwo", new SensorType("Temperature", "Celsius"), new Local(10,10,20),new Date());
        AreaSensorService expectedResult = new AreaSensorService();
        expectedResult.add(firstValidAreaSensor);
        firstValidArea.addSensor(firstValidAreaSensor);

        // Act

        AreaSensorService actualResult = validList.getAreaListSensors();

        // Assert

        assertEquals(expectedResult, actualResult);

        // Add new Area

        validList.addGeographicArea(secondValidArea);
        secondValidArea.addSensor(secondValidAreaSensor);
        expectedResult.add(secondValidAreaSensor);

        // Act

        actualResult = validList.getAreaListSensors();

        // Assert

        assertEquals(expectedResult, actualResult);

        // Arrange to make the first area get skipped (empty sensor list)

        firstValidArea.setSensorList(new AreaSensorService());
        expectedResult.remove(firstValidAreaSensor);

        // Act

        actualResult = validList.getAreaListSensors();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemovesGeographicAreaFalse() {
        // Act

        boolean actualResult = validList.removeGeographicArea(secondValidArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfRemovesGeographicAreaEmptyList() {
        // Act

        GeographicAreaList emptyList = new GeographicAreaList(geographicAreaRepository);
        boolean actualResult = emptyList.removeGeographicArea(secondValidArea);

        // Assert

        assertFalse(actualResult);
    }
}