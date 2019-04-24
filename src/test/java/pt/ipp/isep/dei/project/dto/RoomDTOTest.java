package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.WashingMachine;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomDTOTest {
    // Common testing artifacts for this class.

    private RoomDTO validDTO;

    @BeforeEach
    void arrangeArtifacts() {
        validDTO = new RoomDTO();
    }

    @Test
    void seeIfSetGetNameWorks() {
        // Arrange

        validDTO.setName("Mock");
        String expectedResult = "Mock";

        // Act

        String actualResult = validDTO.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetFloorWorks() {
        // Arrange

        validDTO.setFloor(3);

        // Act

        int result = validDTO.getFloor();

        // Assert

        assertEquals(3, result);
    }

    @Test
    void seeIfSetGetWidthWorks() {
        // Arrange

        validDTO.setWidth(12.5);

        // Act

        double result = validDTO.getWidth();

        // Assert

        assertEquals(12.5, result);
    }

    @Test
    void seeIfSetGetLengthWorks() {
        // Arrange

        validDTO.setLength(16.5);

        // Act

        double result = validDTO.getLength();

        // Assert

        assertEquals(16.5, result);
    }

    @Test
    void seeIfSetGetHeightWorks() {
        // Arrange

        validDTO.setHeight(31.1);

        // Act

        double result = validDTO.getHeight();

        // Assert

        assertEquals(31.1, result);
    }

    @Test
    void seeIfSetGetSensorListWorks() {
        // Arrange

        List<RoomSensorDTO> list = new ArrayList<>();
        RoomSensorDTO firstDTO = new RoomSensorDTO();
        firstDTO.setName("Test");
        list.add(firstDTO);
        validDTO.setSensorList(list);

        // Act

        List<RoomSensorDTO> result = validDTO.getSensorList();

        // Assert

        assertEquals(list, result);
    }

    @Test
    void seeIfSetGetDeviceListWorks() {
        // Arrange

        DeviceList list = new DeviceList();
        Device firstDevice = new WashingMachine(new WashingMachineSpec());
        list.add(firstDevice);
        validDTO.setDeviceList(list);

        // Act

        DeviceList result = validDTO.getDeviceList();

        // Assert

        assertEquals(list, result);
    }

    @Test
    void seeIfSetDescriptionWorks() {
        // Arrange

        validDTO.setDescription("Description");

        // Act

        String actualResult = validDTO.getDescription();

        // Assert

        assertEquals("Description", actualResult);
    }

    @Test
    void seeIfEqualsWorks() {
        // Arrange

        validDTO.setName("Room1");

        RoomDTO roomDTO2 = new RoomDTO();
        roomDTO2.setName("Room1");

        RoomDTO roomDTO3 = new RoomDTO();
        roomDTO3.setName("Room2");

        // Act

        boolean actualResult1 = validDTO.equals(validDTO);
        boolean actualResult2 = validDTO.equals(roomDTO2);
        boolean actualResult3 = validDTO.equals(roomDTO3);
        boolean actualResult4 = validDTO.equals(4D);


        // Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void seeIfHashcodeWorks() {
        // Assert

        assertEquals(1, validDTO.hashCode());

    }

}
