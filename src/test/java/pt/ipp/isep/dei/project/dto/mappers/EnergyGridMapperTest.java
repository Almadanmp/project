package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.EnergyGridDTO;
import pt.ipp.isep.dei.project.dto.PowerSourceDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

class EnergyGridMapperTest {
    // Common testing artifacts for testing in this class

    private EnergyGrid validGrid;

    @BeforeEach
    void arrangeArtifacts() {
        validGrid = new EnergyGrid("GridOne", 21);
        RoomList roomList = new RoomList();
        Room roomOne = new Room("Kitchen", 1, 20, 30, 10);
        roomList.add(roomOne);
        PowerSourceList powerSourceList = new PowerSourceList();
        PowerSource powerSourceOne = new PowerSource("firstSource", 10, 30);
        powerSourceList.add(powerSourceOne);
        validGrid.setRoomList(roomList);
        validGrid.setPowerSourceList(powerSourceList);
    }

    @Test
    void seeIfObjectToDTOWorks() {
        // Arrange

        EnergyGridDTO expectedResult = new EnergyGridDTO();
        expectedResult.setName("GridOne");
        expectedResult.setMaxContractedPower(21);
        List<RoomDTO> roomList = new ArrayList<>();
        Room roomOne = new Room("Kitchen", 1, 20, 30, 10);
        roomList.add(RoomMapper.objectToDTO(roomOne));
        expectedResult.setRoomDTOS(roomList);
        List<PowerSourceDTO> powerSources = new ArrayList<>();
        PowerSource powerSourceOne = new PowerSource("firstSource", 10, 30);
        powerSources.add(PowerSourceMapper.objectToDTO(powerSourceOne));
        expectedResult.setPowerSourceDTOS(powerSources);

        // Act

        EnergyGridDTO actualResult = EnergyGridMapper.objectToDTO(validGrid);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDTOToObjectWorks(){
        // Arrange

        EnergyGridDTO dtoToConvert = new EnergyGridDTO();
        dtoToConvert.setName("GridOne");
        dtoToConvert.setMaxContractedPower(21);
        List<RoomDTO> roomList = new ArrayList<>();
        Room roomOne = new Room("Kitchen", 1, 20, 30, 10);
        roomList.add(RoomMapper.objectToDTO(roomOne));
        dtoToConvert.setRoomDTOS(roomList);
        List<PowerSourceDTO> powerSources = new ArrayList<>();
        PowerSource powerSourceOne = new PowerSource("firstSource", 10, 30);
        powerSources.add(PowerSourceMapper.objectToDTO(powerSourceOne));
        dtoToConvert.setPowerSourceDTOS(powerSources);

        // Act

        EnergyGrid actualResult = EnergyGridMapper.dtoToObject(dtoToConvert);

        // Assert

        assertEquals(validGrid, actualResult);

    }
}
