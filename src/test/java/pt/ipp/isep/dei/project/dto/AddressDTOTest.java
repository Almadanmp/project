package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AddressDTOTest {
    // Common testing artifacts for testing in this class.

    private AddressDTO validAddressDTO;

    @BeforeEach
    void arrangeArtifacts() {
        validAddressDTO = new AddressDTO();
        validAddressDTO.setStreet("Rua R. Dr. António Bernardino de Almeida 431");
        validAddressDTO.setTown("Porto");
        validAddressDTO.setZip("4200-072");
    }

    @Test
    void seeIfGetSetStreetWorks() {
        //Arrange
        validAddressDTO.setStreet("Rua do ISEP");
        String expectedResult = "Rua do ISEP";
        //Act
        String actualResult = validAddressDTO.getStreet();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetZipWorks() {
        //Arrange
        validAddressDTO.setZip("4019-222");
        String expectedResult = "4019-222";
        //Act
        String actualResult = validAddressDTO.getZip();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetTownWorks() {
        //Arrange
        validAddressDTO.setTown("Gaia");
        String expectedResult = "Gaia";
        //Act
        String actualResult = validAddressDTO.getTown();
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}