package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicAreaList tests class.
 */
@ExtendWith(MockitoExtension.class)
class GeographicAreaServiceTest {
    // Common testing artifacts for this class.

    private GeographicAreaService validService;
    private GeographicArea firstValidArea;
    private GeographicArea secondValidArea;
    private List<GeographicArea> validList;

    @Mock
    GeographicAreaRepository geographicAreaRepository;


    @BeforeEach
    void arrangeArtifacts() {
        firstValidArea = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 50, 10));
        secondValidArea = new GeographicArea("Europe", new AreaType("Continent"), 3000, 2000,
                new Local(90, 100, 10));
        validService = new GeographicAreaService(geographicAreaRepository);
        validService.addGeographicArea(firstValidArea);
        validList = new ArrayList<>();
        validList.add(firstValidArea);
    }

    @Test
    void seeIfAddGAToListWorksAlreadyContained() {
        //Act

        boolean actualResult = validService.addGeographicArea(firstValidArea);
        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfAddGAToListWorks() {
        //Act

        boolean actualResult = validService.addGeographicArea(secondValidArea);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfContainsWorksTrue() {
        // Act

        boolean actualResult = validService.contains(firstValidArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfFalseWhenGivenGeoAreaIsNotContainedInGeographicAreaList() {
        // Act

        boolean actualResult = validService.contains(secondValidArea);

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        GeographicAreaService testList = new GeographicAreaService(geographicAreaRepository);
        testList.addGeographicArea(secondValidArea);

        //Act

        boolean actualResult = validService.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksFalseDifferentObject() {
        // Arrange

        RoomService testList = new RoomService();

        //Act

        boolean actualResult = validService.equals(testList); // Needed for SonarQube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfPrintsGeoAList() {
        // Arrange
        List<GeographicArea> geographicAreas = new ArrayList<>();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String result = validService.buildStringRepository(geographicAreas);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfPrintsGeoAListIfEmpty() {
        // Arrange

        List<GeographicArea> geoAreas = new ArrayList<>();
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = validService.buildStringRepository(geoAreas);

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfContainsGAByParametersFalse() {
        // Act

        boolean actualResult = validService.containsObjectMatchesParameters("Lisboa", new AreaType("City"), new Local(21,
                30, 40));


        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfContainsGAByParametersTrue() {
        // Act

        boolean actualResult = validService.containsObjectMatchesParameters("Portugal", new AreaType("Country"), new Local(50,
                50, 10));


        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfIsEmptyFalse() {
        List<GeographicArea> geographicAreas = new ArrayList<>();
        geographicAreas.add(firstValidArea);

        Mockito.when(geographicAreaRepository.findAll()).thenReturn(geographicAreas);

        assertFalse(validService.isEmpty());

    }

    @Test
    void seeIfIsEmptyTrue() {

        List<GeographicArea> geographicAreas = new ArrayList<>();

        Mockito.when(geographicAreaRepository.findAll()).thenReturn(geographicAreas);

        assertTrue(validService.isEmpty());
    }

    @Test
    void seeIfGetTypeAreaByIdRepository() {
        long mockId = 1234;
        firstValidArea.setId(mockId);

        Mockito.when(geographicAreaRepository.findById(mockId)).thenReturn(Optional.of(firstValidArea));

        GeographicArea result = validService.get(mockId);

        assertEquals(result.getId(), firstValidArea.getId());

    }

    @Test
    void seeIfGetTypeAreaByIdRepositoryNull() {
        long mockId = 1234;

        Mockito.when(geographicAreaRepository.findById(mockId)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoSuchElementException.class, () -> validService.get(mockId));

        assertEquals("ERROR: There is no Geographic Area with the selected ID.", exception.getMessage());
    }

    @Test
    void createGA() {
        String iD = "Coimbra";
        AreaType areaType = new AreaType("Distrito");
        Local local = new Local(12, 12, 12);
        GeographicArea expectedResult = new GeographicArea(iD, areaType, 12, 12, local);

        GeographicArea actualResult = validService.createGA(iD, areaType, 12, 12, local);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDoesNotAddWithoutPersisting() {
        // Arrange

        GeographicAreaService expectedResult = new GeographicAreaService(geographicAreaRepository);
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
        List<AreaSensor> validAreaSensorService = new ArrayList<>();
        AreaSensor firstValidAreaSensor = new AreaSensor("SensorOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(
                31, 1, 2), new Date(), 6008L);
        firstValidAreaSensor.setActive(true);
        AreaSensor secondValidAreaSensor = new AreaSensor("SensorTwo", "SensorTwo", new SensorType("Temperature", "Celsius"), new Local(10, 10, 10),
                new Date(), 6008L);
        secondValidAreaSensor.setActive(true);


        // Act

        List<AreaSensor> actualResult = validService.getAreaListSensors(validList);

        // Assert

        assertEquals(validAreaSensorService, actualResult);
    }

    @Test
    void seeIfGetsGeoAreasByType() {

        // Act

        List<GeographicArea> actualResult = validService.getGeoAreasByType(validList, "Country");

        // Assert

        assertEquals(actualResult.size(), 1);
    }

    @Test
    void seeIfRemovesGeographicAreaTrue() {
        // Act

        boolean actualResult = validService.removeGeographicArea(firstValidArea);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetAreaListSensorsWorks() {
        // Arrange

        AreaSensor firstValidAreaSensor = new AreaSensor("SensOne", "SensorOne", new SensorType("Temperature", "Celsius"), new Local(10, 10, 10), new Date(), 6008L);
        AreaSensor secondValidAreaSensor = new AreaSensor("SensTwo", "SensTwo", new SensorType("Temperature", "Celsius"), new Local(10, 10, 20), new Date(), 6008L);
        List<AreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(firstValidAreaSensor);
        firstValidArea.addSensor(firstValidAreaSensor);

        // Act

        List<AreaSensor> actualResult = validService.getAreaListSensors(validList);

        // Assert

        assertEquals(expectedResult, actualResult);

        // Add new Area

        validList.add(secondValidArea);
        secondValidArea.addSensor(secondValidAreaSensor);
        expectedResult.add(secondValidAreaSensor);

        // Act

        actualResult = validService.getAreaListSensors(validList);

        // Assert

        assertEquals(expectedResult, actualResult);

        // Arrange to make the first area getDB skipped (empty sensor list)

        firstValidArea.setSensorList(new AreaSensorService());
        expectedResult.remove(firstValidAreaSensor);

        // Act

        actualResult = validService.getAreaListSensors(validList);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRemovesGeographicAreaFalse() {
        // Act

        boolean actualResult = validService.removeGeographicArea(secondValidArea);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfRemovesGeographicAreaEmptyList() {
        // Act

        GeographicAreaService emptyList = new GeographicAreaService(geographicAreaRepository);
        boolean actualResult = emptyList.removeGeographicArea(secondValidArea);

        // Assert

        assertFalse(actualResult);
    }
}