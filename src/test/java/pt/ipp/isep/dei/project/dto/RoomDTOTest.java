package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.WashingMachine;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomDTOTest {
    // Common testing artifacts for this class.

    private RoomDTO validDTO;

    @BeforeEach
    void arrangeArtifacts(){
        validDTO = new RoomDTO();
    }

    @Test
    void seeIfSetGetNameWorks(){
        // Arrange

        validDTO.setName("Mock");
        String expectedResult = "Mock";

        // Act

        String actualResult = validDTO.getName();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetFloorWorks(){
        // Arrange

        validDTO.setFloor(3);

        // Act

        int result = validDTO.getFloor();

        // Assert

        assertEquals(3, result);
    }

    @Test
    void seeIfSetGetWidthWorks(){
        // Arrange

        validDTO.setWidth(12.5);

        // Act

        double result = validDTO.getWidth();

        // Assert

        assertEquals(12.5, result);
    }

    @Test
    void seeIfSetGetLengthWorks(){
        // Arrange

        validDTO.setLength(16.5);

        // Act

        double result = validDTO.getLength();

        // Assert

        assertEquals(16.5, result);
    }

    @Test
    void seeIfSetGetHeightWorks(){
        // Arrange

        validDTO.setHeight(31.1);

        // Act

        double result = validDTO.getHeight();

        // Assert

        assertEquals(31.1, result);
    }

    @Test
    void seeIfSetGetIDWorks(){
        // Arrange

        validDTO.setId(131L);

        // Act

        long result = validDTO.getId();

        // Assert

        assertEquals(131L, result);
    }

    @Test
    void seeIfSetGetSensorListWorks(){
        // Arrange

        List<SensorDTO> list = new ArrayList<>();
        SensorDTO firstDTO = new SensorDTO();
        firstDTO.setId("Mock");
        firstDTO.setName("Test");
        list.add(firstDTO);
        validDTO.setSensorList(list);

        // Act

        List<SensorDTO> result = validDTO.getSensorList();

        // Assert

        assertEquals(list, result);
    }

    @Test
    void seeIfSetGetDeviceListWorks(){
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
}
