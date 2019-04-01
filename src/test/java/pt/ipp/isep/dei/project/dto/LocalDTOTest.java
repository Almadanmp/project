package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalDTOTest {
    // Common testing artifacts for testing in this class.

    private LocalDTO validDTO;

    @BeforeEach
    void arrangeArtifacts(){
        validDTO = new LocalDTO();
    }

    @Test
    void seeIfGetSetLatitudeWorks(){
        // Arrange

        validDTO.setLatitude(12D);

        // Act

        double result = validDTO.getLatitude();

        // Assert

        assertEquals(12D, result);
    }

    @Test
    void seeIfGetSetLongitudeWorks(){
        // Arrange

        validDTO.setLongitude(5D);

        // Act

        double result = validDTO.getLongitude();

        // Assert

        assertEquals(5D, result);
    }

    @Test
    void seeIfGetSetAltitudeWorks(){
        // Arrange

        validDTO.setAltitude(31D);

        // Act

        double result = validDTO.getAltitude();

        // Assert

        assertEquals(31D, result);
    }

    @Test
    void seeIfSetGetIDWorks(){
        // Arrange

        validDTO.setId(99L);

        // Act

        long result = validDTO.getId();

        // Assert

        assertEquals(99L, result);
    }
}
