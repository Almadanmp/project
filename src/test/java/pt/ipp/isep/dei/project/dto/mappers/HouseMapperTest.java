package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseMapperTest {
    @Test
    void seeIfDtoToObjectWorks() {

        Address address = new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4440-616", "Porto", "Portugal");
        Local local = new Local(0, 0, 0);
        List<String> deviceTypes = new ArrayList<>();
        EnergyGridService energyGridService = new EnergyGridService();
        energyGridService.addGrid(new EnergyGrid("EG1", 0, "01"));
        RoomService roomService = new RoomService();
        roomService.add(new Room("B109", "Classroom", 1, 3, 3, 3.5, "01", "EG1"));
        GeographicArea geographicArea = new GeographicArea("GA", new AreaType("City"), 100, 90, new Local(0, 0, 0));
        House expectedResult = new House("01", address, local, 15, 15, deviceTypes);
        expectedResult.setMotherArea(geographicArea);
        expectedResult.setGridList(energyGridService);
        expectedResult.setRoomService(roomService);
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setId("01");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCountry("Portugal");
        addressDTO.setTown("Porto");
        addressDTO.setZip("4440-616");
        addressDTO.setNumber("431");
        addressDTO.setStreet("Rua Dr. António Bernardino de Almeida");
        LocalDTO localDTO = new LocalDTO();
        localDTO.setAltitude(0);
        localDTO.setLatitude(0);
        localDTO.setLongitude(0);
        List<EnergyGridDTO> energyGridDTOList = new ArrayList<>();
        EnergyGridDTO energyGridDTO = new EnergyGridDTO();
        energyGridDTO.setName("EG1");
        energyGridDTO.setMaxContractedPower(0);
        energyGridDTO.setHouseID("01");
        List<PowerSourceDTO> powerSourceDTOList = new ArrayList<>();
        energyGridDTO.setPowerSourceDTOS(powerSourceDTOList);
        energyGridDTOList.add(energyGridDTO);
        List<RoomDTO> list = new ArrayList<>();
        energyGridDTO.setRoomDTOS(list);
        List<HouseSensorDTO> houseSensorDTOS = new ArrayList<>();
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("B109");
        roomDTO.setDescription("Classroom");
        roomDTO.setFloor(1);
        roomDTO.setWidth(3);
        roomDTO.setLength(3);
        roomDTO.setHeight(3.5);
        roomDTO.setHouseId("01");
        roomDTO.setEnergyGridName("EG1");
        roomDTO.setSensorList(houseSensorDTOS);
        list.add(roomDTO);
        GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO();
        geographicAreaDTO.setLocalDTO(localDTO);
        geographicAreaDTO.setName("GA");
        geographicAreaDTO.setTypeArea("City");
        geographicAreaDTO.setWidth(90);
        geographicAreaDTO.setLength(100);
        houseDTO.setMotherArea(geographicAreaDTO);
        houseDTO.setEnergyGridList(energyGridDTOList);
        houseDTO.setRoomList(list);
        houseDTO.setAddress(addressDTO);
        houseDTO.setLocation(localDTO);
        assertEquals(expectedResult, HouseMapper.dtoToObject(houseDTO));
    }

    @Test
    void seeIfDtoToObjectUS100Works() {

        Address address = new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4440-616", "Porto", "Portugal");
        Local local = new Local(0, 0, 0);
        List<String> deviceTypes = new ArrayList<>();
        EnergyGridService energyGridService = new EnergyGridService();
        energyGridService.addGrid(new EnergyGrid("EG1", 0, "01"));
        RoomService roomService = new RoomService();
        roomService.add(new Room("B109", "Classroom", 1, 3, 3, 3.5, "01", "EG1"));
        GeographicArea geographicArea = new GeographicArea("GA", new AreaType("City"), 100, 90, new Local(0, 0, 0));
        House expectedResult = new House("01", address, local, 15, 15, deviceTypes);
        expectedResult.setMotherArea(geographicArea);
        expectedResult.setGridList(energyGridService);
        expectedResult.setRoomService(roomService);
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setId("01");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCountry("Portugal");
        addressDTO.setTown("Porto");
        addressDTO.setZip("4440-616");
        addressDTO.setNumber("431");
        addressDTO.setStreet("Rua Dr. António Bernardino de Almeida");
        LocalDTO localDTO = new LocalDTO();
        localDTO.setAltitude(0);
        localDTO.setLatitude(0);
        localDTO.setLongitude(0);
        List<EnergyGridDTO> energyGridDTOList = new ArrayList<>();
        EnergyGridDTO energyGridDTO = new EnergyGridDTO();
        energyGridDTO.setName("EG1");
        energyGridDTO.setMaxContractedPower(0);
        energyGridDTO.setHouseID("01");
        List<PowerSourceDTO> powerSourceDTOList = new ArrayList<>();
        energyGridDTO.setPowerSourceDTOS(powerSourceDTOList);
        energyGridDTOList.add(energyGridDTO);
        List<RoomDTO> list = new ArrayList<>();
        energyGridDTO.setRoomDTOS(list);
        List<HouseSensorDTO> houseSensorDTOS = new ArrayList<>();
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("B109");
        roomDTO.setDescription("Classroom");
        roomDTO.setFloor(1);
        roomDTO.setWidth(3);
        roomDTO.setLength(3);
        roomDTO.setHeight(3.5);
        roomDTO.setHouseId("01");
        roomDTO.setEnergyGridName("EG1");
        roomDTO.setSensorList(houseSensorDTOS);
        list.add(roomDTO);
        GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO();
        geographicAreaDTO.setLocalDTO(localDTO);
        geographicAreaDTO.setName("GA");
        geographicAreaDTO.setTypeArea("City");
        geographicAreaDTO.setWidth(90);
        geographicAreaDTO.setLength(100);
        houseDTO.setMotherArea(geographicAreaDTO);
        houseDTO.setEnergyGridList(energyGridDTOList);
        houseDTO.setRoomList(list);
        houseDTO.setAddress(addressDTO);
        houseDTO.setLocation(localDTO);
        assertEquals(expectedResult, HouseMapper.dtoToObjectUS100(houseDTO));
    }

    @Test
    void seeIfObjectToDTOWorks() {

        Address address = new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4440-616", "Porto", "Portugal");
        Local local = new Local(0, 0, 0);
        List<String> deviceTypes = new ArrayList<>();
        EnergyGridService energyGridService = new EnergyGridService();
        energyGridService.addGrid(new EnergyGrid("EG1", 0, "01"));
        RoomService roomService = new RoomService();
        roomService.add(new Room("B109", "Classroom", 1, 3, 3, 3.5, "01", "EG1"));
        GeographicArea geographicArea = new GeographicArea("GA", new AreaType("City"), 100, 90, new Local(0, 0, 0));
        House house = new House("01", address, local, 15, 15, deviceTypes);
        house.setMotherArea(geographicArea);
        house.setGridList(energyGridService);
        house.setRoomService(roomService);
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setId("01");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCountry("Portugal");
        addressDTO.setTown("Porto");
        addressDTO.setZip("4440-616");
        addressDTO.setNumber("431");
        addressDTO.setStreet("Rua Dr. António Bernardino de Almeida");
        LocalDTO localDTO = new LocalDTO();
        localDTO.setAltitude(0);
        localDTO.setLatitude(0);
        localDTO.setLongitude(0);
        List<EnergyGridDTO> energyGridDTOList = new ArrayList<>();
        EnergyGridDTO energyGridDTO = new EnergyGridDTO();
        energyGridDTO.setName("EG1");
        energyGridDTO.setMaxContractedPower(0);
        energyGridDTO.setHouseID("01");
        List<PowerSourceDTO> powerSourceDTOList = new ArrayList<>();
        energyGridDTO.setPowerSourceDTOS(powerSourceDTOList);
        energyGridDTOList.add(energyGridDTO);
        List<RoomDTO> list = new ArrayList<>();
        energyGridDTO.setRoomDTOS(list);
        List<HouseSensorDTO> houseSensorDTOS = new ArrayList<>();
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("B109");
        roomDTO.setDescription("Classroom");
        roomDTO.setFloor(1);
        roomDTO.setWidth(3);
        roomDTO.setLength(3);
        roomDTO.setHeight(3.5);
        roomDTO.setHouseId("01");
        roomDTO.setEnergyGridName("EG1");
        roomDTO.setSensorList(houseSensorDTOS);
        list.add(roomDTO);
        GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO();
        geographicAreaDTO.setLocalDTO(localDTO);
        geographicAreaDTO.setName("GA");
        geographicAreaDTO.setTypeArea("City");
        geographicAreaDTO.setWidth(90);
        geographicAreaDTO.setLength(100);
        houseDTO.setMotherArea(geographicAreaDTO);
        houseDTO.setEnergyGridList(energyGridDTOList);
        houseDTO.setRoomList(list);
        houseDTO.setAddress(addressDTO);
        houseDTO.setLocation(localDTO);
        assertEquals(houseDTO, HouseMapper.objectToDTO(house));
    }
}
