package pt.ipp.isep.dei.project.io.ui.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Dishwasher;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;
import pt.ipp.isep.dei.project.model.room.RoomSensor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UtilsUITest {

    @Mock
    RoomRepository roomRepository;

    @BeforeEach
    void arrangeArtifacts() {
    }

    @Test
    void roomListsAreInvalid() {
        Room room1 = new Room("room1", "Single Bedroom", 19, 23456789, 5, 3, "Room1");
        UtilsUI utilsUI = new UtilsUI();
        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        validHouse.setMotherAreaID(12L);
        RoomDTO roomDTO = RoomMapper.objectToDTO(room1);
        Mockito.when(roomRepository.updateHouseRoom(roomDTO)).thenReturn(room1);
        boolean result2 = utilsUI.roomDTODeviceListIsValid(roomDTO, roomRepository);

        //ASSERT
        assertFalse(result2);
    }

    @Test
    void seeIfRoomDTOSensorListIsValidWorks() {
        // Arrange

        Room room1 = new Room("room1", "Single Bedroom", 19, 23456789, 5, 3, "Room1");
        GregorianCalendar date = new GregorianCalendar(2010, Calendar.DECEMBER, 2, 12, 12);
        RoomSensor areaSensor1 = new RoomSensor("XV45", "sensor", "sensor", date.getTime());
        room1.addSensor(areaSensor1);
        Device device = new Dishwasher(new DishwasherSpec());
        room1.addDevice(device);
        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, new ArrayList<>());
        validHouse.setMotherAreaID(123L);
        RoomDTO roomDTO = RoomMapper.objectToDTO(room1);
        UtilsUI utilsUI = new UtilsUI();

        // Act
        Mockito.when(roomRepository.updateHouseRoom(roomDTO)).thenReturn(room1);
        boolean result2 = utilsUI.roomDTODeviceListIsValid(roomDTO, roomRepository);

        // Assert

        assertTrue(result2);
    }

    @Test
    void seeIfPrintMessageWorks() {

        //Arrange

        String expectedResult = "test";

        // Act

        String actualResult = UtilsUI.printMessage("test");

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintMessageFails() {

        //Arrange

        String expectedResult = "test";

        // Act

        String actualResult = UtilsUI.printMessage("test2");

        //Assert

        assertNotEquals(expectedResult, actualResult);
    }
}