package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.TypeArea;

import static org.testng.Assert.assertEquals;

class GeographicAreaMapperTest {
    // Common testing artifacts for testing in this class.

    private GeographicArea validAreaObject;

    @BeforeEach
    void arrangeArtifacts() {
        validAreaObject = new GeographicArea("Portugal", new TypeArea("Country"), 300, 200,
                new Local(50, 50, 10));
        validAreaObject.setId(21L);
    }

    @Test
    void seeIfObjectToDTOWorks() {
        // Arrange

        GeographicAreaDTO expectedResult = new GeographicAreaDTO();
        expectedResult.setLatitude(50);
        expectedResult.setAltitude(10);
        expectedResult.setLongitude(50);
        expectedResult.setName("Portugal");
        expectedResult.setTypeArea("Country");
        expectedResult.setLength(300);
        expectedResult.setWidth(200);

        // Act

        GeographicAreaDTO actualResult = GeographicAreaMapper.objectToDTO(validAreaObject);

        // Assert

        assertEquals(expectedResult, actualResult);
        assertEquals(actualResult.getId(), validAreaObject.getId());
    }

    @Test
    void seeIfDTOToObjectWorks(){
        // Arrange

        GeographicAreaDTO dtoToConvert = new GeographicAreaDTO();
        dtoToConvert.setLatitude(50);
        dtoToConvert.setAltitude(10);
        dtoToConvert.setLongitude(50);
        dtoToConvert.setName("Portugal");
        dtoToConvert.setTypeArea("Country");
        dtoToConvert.setLength(300);
        dtoToConvert.setWidth(200);
        dtoToConvert.setId(21L);

        // Act

        GeographicArea actualResult = GeographicAreaMapper.dtoToObject(dtoToConvert);

        // Assert

        assertEquals(validAreaObject, actualResult);
        assertEquals(Long.compare(actualResult.getId(), 21L), 0);
    }
}
