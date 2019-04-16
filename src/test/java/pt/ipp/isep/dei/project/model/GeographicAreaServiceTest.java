package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.repository.AreaTypeRepository;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Mock
    AreaTypeRepository areaTypeRepository;


    @BeforeEach
    void arrangeArtifacts() {
        firstValidArea = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 50, 10));
        secondValidArea = new GeographicArea("Europe", new AreaType("Continent"), 3000, 2000,
                new Local(90, 100, 10));
        validService = new GeographicAreaService(geographicAreaRepository, areaTypeRepository);
        validList = new ArrayList<>();
        validList.add(firstValidArea);
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

        GeographicArea actualResult = validService.createGA(iD, areaType.getName(), 12, 12, local);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetsGeoAreasByType() {

        // Act

        List<GeographicArea> actualResult = validService.getGeoAreasByType(validList, "Country");

        // Assert

        assertEquals(actualResult.size(), 1);
    }
}