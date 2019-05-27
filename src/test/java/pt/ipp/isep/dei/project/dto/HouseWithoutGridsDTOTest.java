package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseWithoutGridsDTOTest {

    private HouseWithoutGridsDTO validHouseDTO;

    @BeforeEach
    void arrangeArtifacts() {
        validHouseDTO = new HouseWithoutGridsDTO();
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("Rua R. Dr. António Bernardino de Almeida 431");
        addressDTO.setTown("Porto");
        addressDTO.setZip("4200-072");
        validHouseDTO.setAddress(addressDTO);
        validHouseDTO.setDeviceMeteringPeriod(10);
        validHouseDTO.setId("House");
        LocalDTO localDTO = new LocalDTO();
        localDTO.setAltitude(111);
        localDTO.setLatitude(41.178553);
        localDTO.setLongitude(-8.608035);
        validHouseDTO.setLocation(localDTO);
        List<DeviceType> deviceTypeList = new ArrayList<>();
        validHouseDTO.setGridMeteringPeriod(8);
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange
        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setStreet("Street");
        addressDTO1.setTown("Town");
        addressDTO1.setZip("Zip");

        AddressDTO addressDTO2 = new AddressDTO();
        addressDTO2.setStreet("Street2");
        addressDTO2.setTown("Town2");
        addressDTO2.setZip("Zip2");

        List<DeviceType> deviceTypeList = new ArrayList<>();
        validHouseDTO.setAddress(addressDTO1);

        HouseWithoutGridsDTO validHouseDTO2 = new HouseWithoutGridsDTO();
        validHouseDTO2.setAddress(addressDTO2);

        HouseWithoutGridsDTO validHouseDTO3 = new HouseWithoutGridsDTO();
        validHouseDTO3.setAddress(addressDTO1);

        HouseWithoutGridsDTO validHouseDTO4 = null;


        //Act

        boolean actualResult1 = validHouseDTO.equals(validHouseDTO);
        boolean actualResult2 = validHouseDTO.equals(validHouseDTO2);
        boolean actualResult3 = validHouseDTO.equals(validHouseDTO3);
        boolean actualResult4 = validHouseDTO.equals(2D);
        boolean actualResult5 = validHouseDTO.equals(validHouseDTO4);


        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Assert

        assertEquals(1, validHouseDTO.hashCode());
    }
}