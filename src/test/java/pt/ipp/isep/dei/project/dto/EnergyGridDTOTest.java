package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.mappers.PowerSourceMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.energy.PowerSource;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class EnergyGridDTOTest {
    // Common testing artifacts for testing in this class.

    private EnergyGridDTO validDTO;

    @BeforeEach
    void arrangeArtifacts() {
        validDTO = new EnergyGridDTO();
        validDTO.setName("GridOne");
        validDTO.setMaxContractedPower(21);
        List<RoomDTO> roomList = new ArrayList<>();
        Room roomOne = new Room("Kitchen", "1st Floor Kitchen", 1, 20, 30, 10, "Room1", "Grid1");
        roomList.add(RoomMapper.objectToDTO(roomOne));
        validDTO.setRoomDTOS(roomList);
        List<PowerSourceDTO> powerSources = new ArrayList<>();
        PowerSource powerSourceOne = new PowerSource("firstSource", 10, 30, "12345L");
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
    void seeIfGetSetMaxContractedPowerWorks() {
        // Arrange

        validDTO.setMaxContractedPower(10);

        // Act

        double result = validDTO.getMaxContractedPower();

        // Assert

        assertEquals(10, result);
    }

    @Test
    void seeIfGetSetPowerSourceListWorks() {
        // Arrange

        List<PowerSourceDTO> powerSources = new ArrayList<>();
        validDTO.setPowerSourceDTOS(powerSources);

        // Act

        List<PowerSourceDTO> result = validDTO.getPowerSourceDTOS();

        // Assert

        assertEquals(powerSources, result);
    }

    @Test
    void seeIfGetSetRoomListWorks() {
        // Arrange

        List<RoomDTO> rooms = new ArrayList<>();
        validDTO.setRoomDTOS(rooms);

        // Act

        List<RoomDTO> result = validDTO.getRoomDTOS();

        // Assert

        assertEquals(rooms, result);
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange

        EnergyGridDTO energyGridDTO = new EnergyGridDTO();
        energyGridDTO.setName("Name1");
        energyGridDTO.setMaxContractedPower(1000);

        EnergyGridDTO energyGridDTO1 = new EnergyGridDTO();
        energyGridDTO1.setName("Name1");
        energyGridDTO1.setMaxContractedPower(900);


        EnergyGridDTO energyGridDTO2 = new EnergyGridDTO();
        energyGridDTO2.setName("Name2");
        energyGridDTO2.setMaxContractedPower(1000);

        //Act

        boolean actualResult1 = energyGridDTO.equals(energyGridDTO);
        boolean actualResult2 = energyGridDTO.equals(energyGridDTO1);
        boolean actualResult3 = energyGridDTO.equals(energyGridDTO2);
        boolean actualResult4 = energyGridDTO.equals(4D);

        //Assert
        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void seeIfHashCodeWorks() {
        assertEquals(1, validDTO.hashCode());
    }
}
