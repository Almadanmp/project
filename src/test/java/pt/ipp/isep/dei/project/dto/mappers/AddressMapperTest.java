package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.AddressDTO;
import pt.ipp.isep.dei.project.model.Address;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    private Address validAddress;
    private AddressDTO validAddressDTO;

    @BeforeEach
    void arrangeArtifacts() {
        validAddress = new Address("Street", "4", "3000", "Porto", "Portugal");
        validAddressDTO = new AddressDTO();
        validAddressDTO.setStreet("Street");
        validAddressDTO.setNumber("4");
        validAddressDTO.setZip("3000");
        validAddressDTO.setTown("Porto");
        validAddressDTO.setCountry("Portugal");
    }


    @Test
    void dtoToObject() {
        //Act

        Address actualResult = AddressMapper.dtoToObject(validAddressDTO);

        //Assert

        assertEquals(validAddress, actualResult);
    }

    @Test
    void objectToDTO() {
        //Act

        AddressDTO actualResult = AddressMapper.objectToDTO(validAddress);

        //Assert

        assertEquals(validAddressDTO, actualResult);
    }
}