package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseWithoutGridsDTOTest {

    @Test
    void seeIfHashcodeWorks() {
        //Arrange
        HouseWithoutGridsDTO dto1 = new HouseWithoutGridsDTO();
        //Assert
        assertEquals(1, dto1.hashCode());
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("Rua da Constituição");
        addressDTO.setTown("Porto");
        addressDTO.setZip("4200-191");
        HouseWithoutGridsDTO dto1 = new HouseWithoutGridsDTO();
        dto1.setAddress(addressDTO);

        HouseWithoutGridsDTO sameDTO = new HouseWithoutGridsDTO();
        sameDTO.setAddress(addressDTO);

        AddressDTO addressDTO2 = new AddressDTO();
        addressDTO2.setStreet("Rua Marechal Saldanha");
        addressDTO2.setTown("Porto");
        addressDTO2.setZip("4150-657");
        HouseWithoutGridsDTO diffDTO = new HouseWithoutGridsDTO();
        diffDTO.setAddress(addressDTO2);

        //Act

        boolean actualResult1 = sameDTO.equals(sameDTO);
        boolean actualResult3 = sameDTO.equals(dto1);
        boolean actualResult2 = sameDTO.equals(diffDTO);
        boolean actualResult4 = sameDTO.equals(2);
        boolean actualResult5 = sameDTO.equals(null);

        //Assert

        assertTrue(actualResult1);
        assertTrue(actualResult3);
        assertFalse(actualResult2);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }
}