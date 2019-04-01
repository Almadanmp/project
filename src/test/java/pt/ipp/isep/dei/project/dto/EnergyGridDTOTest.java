package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.mappers.PowerSourceMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.PowerSource;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class EnergyGridDTOTest {
    // Common testing artifacts for testing in this class.

    private EnergyGridDTO validDTO;

    @BeforeEach
    void arrangeArtifacts() {
        validDTO = new EnergyGridDTO();
        validDTO.setName("GridOne");
        validDTO.setMaxContractedPower(21);
        List<RoomDTO> roomList = new ArrayList<>();
        Room roomOne = new Room("Kitchen", 1, 20, 30, 10);
        roomList.add(RoomMapper.objectToDTO(roomOne));
        validDTO.setRoomDTOS(roomList);
        List<PowerSourceDTO> powerSources = new ArrayList<>();
        PowerSource powerSourceOne = new PowerSource("firstSource", 10, 30);
        powerSources.add(PowerSourceMapper.objectToDTO(powerSourceOne));
        validDTO.setPowerSourceDTOS(powerSources);
    }

    @Test
    void seeIfGetSetNameWorks() {
        // Arrange

        String expectedResult = "Test";
        validDTO.setName("Test");

        // Act

        String actualResult = validDTO.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetMaxContractedPowerWorks(){
        // Arrange

        validDTO.setMaxContractedPower(10);

        // Act

        double result = validDTO.getMaxContractedPower();

        // Assert

        assertEquals(10, result);
    }

    @Test
    void seeIfGetSetPowerSourceListWorks(){
        // Arrange

        List<PowerSourceDTO> powerSources = new ArrayList<>();
        validDTO.setPowerSourceDTOS(powerSources);

        // Act

        List<PowerSourceDTO> result = validDTO.getPowerSourceDTOS();

        // Assert

        assertEquals(powerSources, result);
    }

    @Test
    void seeIfGetSetRoomListWorks(){
        // Arrange

        List<RoomDTO> rooms = new ArrayList<>();
        validDTO.setRoomDTOS(rooms);

        // Act

        List<RoomDTO> result = validDTO.getRoomDTOS();

        // Assert

        assertEquals(rooms, result);
    }

    @Test
    void seeIfEqualsWorksSameInstance() {
        assertEquals(validDTO, validDTO);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        assertNotEquals(validDTO, new RoomList()); // Needed for sonarqube testing purposes.
    }

    @Test
    void seeIfHashCodeWorks() {
        assertEquals(1, validDTO.hashCode());
    }
}
