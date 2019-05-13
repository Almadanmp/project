package pt.ipp.isep.dei.project.io.ui.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.repository.RoomRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class UtilsUITest {

    private RoomService roomService;

    @Mock
    RoomRepository roomRepository;

    @Mock
    SensorTypeRepo sensorTypeRepo;

    @BeforeEach
    void arrangeArtifacts() {
        roomService = new RoomService(roomRepository, sensorTypeRepo);
    }

//    @Test
//    public void roomListsAreInvalid() {
//        Room room1 = new Room("room1","Single Bedroom", 19, 23456789, 5, 3,"Room1","Grid1");
//        UtilsUI utilsUI = new UtilsUI();
//        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida","431",
//                "4455-125", "Porto","Portugal"),
//                new Local(20, 20, 20), 60,
//                180, new ArrayList<>());
//        validHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
//                2, 3, new Local(4, 4, 100)));
//        validHouse.saveSensor(room1);
//        RoomDTO roomDTO = RoomMapper.objectToDTO(room1);
//
//        boolean result2 = utilsUI.roomDTODeviceListIsValid(roomDTO, roomService);
//
//        //ASSERT
//         assertFalse(result2);
//    }

//    @Test
//    public void seeIfRoomDTOSensorListIsValidWorks() {
//        // Arrange
//
//        Room room1 = new Room("room1","Single Bedroom", 19, 23456789, 5, 3,"Room1","Grid1");
//        GregorianCalendar date = new GregorianCalendar(2010, Calendar.DECEMBER, 2, 12, 12);
//        RoomSensor areaSensor1 = new RoomSensor("XV45","sensor", new SensorType("sensor", "celsius"), date.getTime(),"RoomTest");
//        room1.addSensor(areaSensor1);
//        Device device = new Dishwasher(new DishwasherSpec());
//        room1.addDevice(device);
//        House validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida","431",
//                "4455-125", "Porto","Portugal"),
//                new Local(20, 20, 20), 60,
//                180, new ArrayList<>());
//        validHouse.setMotherArea(new GeographicArea("Porto", new AreaType("Cidade"),
//                2, 3, new Local(4, 4, 100)));
//        validHouse.saveSensor(room1);
//        RoomDTO roomDTO = RoomMapper.objectToDTO(room1);
//        UtilsUI utilsUI = new UtilsUI();
//
//        // Act
//
//        boolean result2 = utilsUI.roomDTODeviceListIsValid(roomDTO, roomService);
//
//        // Assert
//
//        assertTrue(result2);
//    }

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