package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.WashingMachine;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.repository.RoomCrudRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class RoomMapperTest {
    // Common testing artifacts for testing in this class
    private Room validRoom;
    private RoomDTO validDTO;

    @BeforeEach
    void arrangeArtifacts() {
        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            date = validSdf.parse("11/01/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        validRoom = new Room("Kitchen", "2nd Floor Kitchen", 2, 30, 20, 10, "Room1");
        validDTO = new RoomDTO();
        validDTO.setName("Kitchen");
        validDTO.setFloor(2);
        validDTO.setWidth(30);
        validDTO.setLength(20);
        validDTO.setHeight(10);
        List<RoomSensorDTO> list = new ArrayList<>();
        RoomSensorDTO dto = new RoomSensorDTO();
        dto.setName("Test");
        dto.setDateStartedFunctioning("11/01/2018 10:00:00");
        List<RoomSensor> objectList = new ArrayList<>();
        objectList.add(RoomSensorMapper.dtoToObject(dto));
        validRoom.setRoomSensors(objectList);
        list.add(dto);
        validDTO.setSensorList(list);
        DeviceList deviceList = new DeviceList();
        Device d1 = new WashingMachine(new WashingMachineSpec());
        deviceList.add(d1);
        validDTO.setDeviceList(deviceList);

        RoomSensor roomSensor = new RoomSensor();

        roomSensor.setName("Test");
        roomSensor.setActive(true);
        roomSensor.setDateStartedFunctioning(date);
        roomSensor.setId("Id");
        roomSensor.setSensorType("Temperature");
        validRoom.setDeviceList(deviceList);
    }

    @Test
    void seeIfDTOToObjectWorks() {
        // Act

        Room actualResult = RoomMapper.dtoToObject(validDTO);

        // Assert

        assertEquals(validRoom, actualResult);

        // We also need to test the elements that aren't present in Room's .equals.

        assertEquals(validRoom.getDeviceList(), actualResult.getDeviceList());
        assertEquals(validRoom.getId(), actualResult.getId());
    }

    @Test
    void seeIfDTOToObjectWithoutSensorsAndDevicesWorks() {
        // Act

        Room actualResult = RoomMapper.dtoToObjectWithoutSensorsAndDevices(validDTO);

        // Assert

        assertEquals(validRoom, actualResult);

        // We also need to test the elements that aren't present in Room's .equals.

        assertEquals(validRoom.getId(), actualResult.getId());
    }

    @Test
    void seeIfObjectToDTOWorks() {
        // Act

        RoomDTO actualResult = RoomMapper.objectToDTO(validRoom);

        // Assert

        assertEquals(validDTO, actualResult);

        // We also need to test the elements that aren't present in Room's .equals.

        assertEquals(validDTO.getDeviceList(), actualResult.getDeviceList());
        assertEquals(validDTO.getName(), actualResult.getName());
    }


}
