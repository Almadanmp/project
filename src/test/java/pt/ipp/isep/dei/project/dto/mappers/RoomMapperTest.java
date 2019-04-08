package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.HouseSensorDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.WashingMachine;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.sensor.HouseSensorList;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class RoomMapperTest {
    // Common testing artifacts for testing in this class
    private Room validRoom;
    private RoomDTO validDTO;

    @Mock
    private AreaSensorRepository areaSensorRepository;

    @BeforeEach
    void arrangeArtifacts() {
        validRoom = new Room("Kitchen", "2nd Floor Kitchen", 2, 30, 20, 10);
        validDTO = new RoomDTO();
    }

    @Test
    void seeIfDTOToObjectWorks() {
        // Arrange

        validDTO.setName("Kitchen");
        validDTO.setFloor(2);
        validDTO.setWidth(30);
        validDTO.setLength(20);
        validDTO.setHeight(10);
        List<HouseSensorDTO> list = new ArrayList<>();
        HouseSensorDTO dto = new HouseSensorDTO();
        dto.setName("Test");
        dto.setDateStartedFunctioning("12-12-1995");
        list.add(dto);
        validDTO.setSensorList(list);
        DeviceList deviceList = new DeviceList();
        Device d1 = new WashingMachine(new WashingMachineSpec());
        deviceList.add(d1);
        validDTO.setDeviceList(deviceList);
        HouseSensorList testList = new HouseSensorList();
        testList.add(HouseSensorMapper.dtoToObject(dto));
        validRoom.setSensorList(testList);
        validRoom.setDeviceList(deviceList);

        // Act

        Room result = RoomMapper.dtoToObject(validDTO);

        // Assert

        assertEquals(validRoom, result);

        // We also need to test the elements that aren't present in Room's .equals.

        assertEquals(validRoom.getSensorList(), (result.getSensorList()));
        assertEquals(validRoom.getDeviceList(), result.getDeviceList());
        assertEquals(validRoom.getName(), result.getName());
    }
}
