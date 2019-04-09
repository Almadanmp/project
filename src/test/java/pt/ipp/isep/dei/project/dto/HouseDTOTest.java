package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseDTOTest {
    // Common testing artifacts for testing in this class.
    private HouseDTO validHouseDTO;

    @BeforeEach
    void arrangeArtifacts() {
        validHouseDTO = new HouseDTO();
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("Rua R. Dr. António Bernardino de Almeida 431");
        addressDTO.setTown("Porto");
        addressDTO.setZip("4200-072");
        validHouseDTO.setAddress(addressDTO);
        validHouseDTO.setDeviceMeteringPeriod(10);
        validHouseDTO.setName("House");
        LocalDTO localDTO = new LocalDTO();
        localDTO.setAltitude(111);
        localDTO.setLatitude(41.178553);
        localDTO.setLongitude(-8.608035);
        validHouseDTO.setLocation(localDTO);
        validHouseDTO.setMotherArea(new GeographicAreaDTO());
        List<DeviceType> deviceTypeList = new ArrayList<>();
        validHouseDTO.setDeviceTypeList(deviceTypeList);
        validHouseDTO.setGridMeteringPeriod(8);
        List<EnergyGridDTO> energyGridDTOList = new ArrayList<>();

        List<RoomDTO> roomDTOS = new ArrayList<>();

    }

    @Test
    void seeIfGetSetName() {
        //Arrange
        validHouseDTO.setName("House 1");
        String expectedResult = "House 1";
        //Act
        String actualResult = validHouseDTO.getName();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetAddress() {
        //Arrange
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setZip("4543");
        addressDTO.setTown("Gaia");
        addressDTO.setStreet("Rua do ISEP");
        validHouseDTO.setAddress(addressDTO);
        //Act
        AddressDTO actualResult = validHouseDTO.getAddress();
        //Assert
        assertEquals(addressDTO, actualResult);
    }

    @Test
    void seeIfGetSetLocal() {
        //Arrange
        LocalDTO localDTO = new LocalDTO();
        localDTO.setLongitude(1);
        localDTO.setLatitude(2);
        localDTO.setAltitude(3);
        validHouseDTO.setLocation(localDTO);
        //Act
        LocalDTO actualResult = validHouseDTO.getLocation();
        //Assert
        assertEquals(localDTO, actualResult);
    }

    @Test
    void seeIfGetSetEnergyGridList() {
        //Arrange
        List<EnergyGridDTO> energyGridDTOList = new ArrayList<>();
        validHouseDTO.setEnergyGridList(energyGridDTOList);
        //Act
        List<EnergyGridDTO> actualResult = validHouseDTO.getEnergyGridList();
        //Assert
        assertEquals(energyGridDTOList, actualResult);
    }

    @Test
    void seeIfGetSetRoomList() {
        //Arrange
        List<RoomDTO> roomDTOS = new ArrayList<>();
        validHouseDTO.setRoomList(roomDTOS);
        //Act
        List<RoomDTO> actualResult = validHouseDTO.getRoomList();
        //Assert
        assertEquals(roomDTOS, actualResult);
    }

    @Test
    void seeIfGetSetGeographicArea() {
        //Arrange
        GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO();
        validHouseDTO.setMotherArea(geographicAreaDTO);
        //Act
        GeographicAreaDTO actualResult = validHouseDTO.getMotherArea();
        //Assert
        assertEquals(geographicAreaDTO, actualResult);
    }

    @Test
    void seeIfGetSetGridMeteringPeriod() {
        //Arrange
        validHouseDTO.setGridMeteringPeriod(4);
        int expectedResult = 4;
        //Act
        int actualResult = validHouseDTO.getGridMeteringPeriod();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetDeviceMeteringPeriod() {
        //Arrange
        validHouseDTO.setDeviceMeteringPeriod(8);
        int expectedResult = 8;
        //Act
        int actualResult = validHouseDTO.getDeviceMeteringPeriod();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetDeviceTypeList() {
        //Arrange
        List<DeviceType> deviceTypeList = new ArrayList<>();
        validHouseDTO.setDeviceTypeList(deviceTypeList);
        //Act
        List<DeviceType> actualResult = validHouseDTO.getDeviceTypeList();
        //Assert
        assertEquals(deviceTypeList, actualResult);
    }

}