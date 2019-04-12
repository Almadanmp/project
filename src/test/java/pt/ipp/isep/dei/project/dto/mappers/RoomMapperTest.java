package pt.ipp.isep.dei.project.dto.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.HouseSensorDTO;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.WashingMachine;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.sensor.HouseSensor;
import pt.ipp.isep.dei.project.model.sensor.HouseSensorService;
import pt.ipp.isep.dei.project.model.sensor.ReadingService;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;

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

    @Mock
    private AreaSensorRepository areaSensorRepository;

    @BeforeEach
    void arrangeArtifacts() {

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            date = validSdf.parse("11/01/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        validRoom = new Room("Kitchen", "2nd Floor Kitchen", 2, 30, 20, 10, "Room1", "Grid1");
        validDTO = new RoomDTO();
        validDTO.setName("Kitchen");
        validDTO.setFloor(2);
        validDTO.setWidth(30);
        validDTO.setLength(20);
        validDTO.setHeight(10);
        List<HouseSensorDTO> list = new ArrayList<>();
        HouseSensorDTO dto = new HouseSensorDTO();
        dto.setName("Test");
        dto.setDateStartedFunctioning("11/01/2018 10:00:00");
        list.add(dto);
        validDTO.setSensorList(list);
        DeviceList deviceList = new DeviceList();
        Device d1 = new WashingMachine(new WashingMachineSpec());
        deviceList.add(d1);
        validDTO.setDeviceList(deviceList);

        HouseSensor houseSensor = new HouseSensor();
        ReadingService readingService = new ReadingService();
        HouseSensorService houseSensorList = new HouseSensorService();
        houseSensorList.add(houseSensor);

        houseSensor.setName("Test");
        houseSensor.setActive(true);
        houseSensor.setDateStartedFunctioning(date);
        houseSensor.setId("Id");
        houseSensor.setRoomId("RoomId");
        houseSensor.setReadingService(readingService);
        houseSensor.setSensorType(new SensorType("Temperature", "Celsius"));

        validRoom.setSensorList(houseSensorList);
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
        assertEquals(validRoom.getName(), actualResult.getName());
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

    @Test
    void seeIfUpdateHouseRoom() {
        //Assert
        Room validRoom2 = new Room("Kitchen", "3nd Floor Kitchen", 2, 30, 20, 10, "Room1", "Grid1");

        House house = new House("House", new Address("Rua das Flores", "431", "4512", "Porto", "Portugal"), new Local(
                4, 6, 6), 60, 180,
                new ArrayList<>());
        house.addRoom(validRoom2);

        // Act

        Room actualResult = RoomMapper.updateHouseRoom(validDTO, house);

        // Assert

        assertEquals(validRoom, actualResult);

        // We also need to test the elements that aren't present in Room's .equals.

        assertEquals(validRoom.getDeviceList(), actualResult.getDeviceList());
        assertEquals(validRoom.getName(), actualResult.getName());
    }
}
