package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.model.AreaType;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.Local;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeographicAreaMapperTest {
    // Common testing artifacts for testing in this class.

    private GeographicArea validAreaObject;

    @BeforeEach
    void arrangeArtifacts() {
        validAreaObject = new GeographicArea("Portugal", new AreaType("Country"), 300, 200,
                new Local(50, 50, 10));
        validAreaObject.setId(21L);
    }

    @Test
    void seeIfObjectToDTOWorks() {
        // Arrange

        GeographicAreaDTO expectedResult = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();
        localDTO.setLatitude(50D);
        localDTO.setLongitude(50D);
        localDTO.setAltitude(10D);
        expectedResult.setName("Portugal");
        expectedResult.setTypeArea("Country");
        expectedResult.setLength(300);
        expectedResult.setWidth(200);
        expectedResult.setId(21L);
        expectedResult.setLocalDTO(localDTO);

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
        LocalDTO localDTO = new LocalDTO();
        localDTO.setLatitude(50);
        localDTO.setAltitude(10);
        localDTO.setLongitude(50);
        dtoToConvert.setName("Portugal");
        dtoToConvert.setTypeArea("Country");
        dtoToConvert.setLength(300);
        dtoToConvert.setWidth(200);
        dtoToConvert.setId(21L);
        dtoToConvert.setLocalDTO(localDTO);

        // Act

        GeographicArea actualResult = GeographicAreaMapper.dtoToObject(dtoToConvert);

        // Assert

        assertEquals(validAreaObject, actualResult);
        assertEquals(Long.compare(actualResult.getId(), 21L), 0);
    }
}
