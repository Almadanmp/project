package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridService;
import pt.ipp.isep.dei.project.model.energy.PowerSource;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.repository.EnergyGridRepository;
import pt.ipp.isep.dei.project.repository.RoomRepository;
import pt.ipp.isep.dei.project.repository.RoomSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EnergyGridSettingsController tests class.
 */
@ExtendWith(MockitoExtension.class)
class EnergyGridSettingsControllerTest {

    // Common artifacts for testing in this class.

    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private House validHouse;
    private EnergyGrid validGrid;
    private Room validRoom;
    private EnergyGridSettingsController controller = new EnergyGridSettingsController();
    @Mock
    private EnergyGridRepository energyGridRepository;
    private EnergyGridService energyGridService;

    @Mock
    private RoomRepository roomRepository;
    @Mock
    RoomSensorRepository roomSensorRepository;
    @Mock
    SensorTypeRepository sensorTypeRepository;
    private RoomService roomService;

    @BeforeEach
    void arrangeArtifacts() {
        this.roomService = new RoomService(roomRepository, roomSensorRepository, sensorTypeRepository);
        this.energyGridService = new EnergyGridService(energyGridRepository);
        Address address = new Address("Rua Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal");
        validHouse = new House("ISEP", address, new Local(20, 20, 20),
                60, 180, new ArrayList<>());
        validHouse.setMotherArea(new GeographicArea("Porto",
                new AreaType("Cidade"), 2, 3, new Local(4, 4, 100)));
        validGrid = new EnergyGrid("validGrid", 300, "34576");
        validRoom = new Room("Room", "Double Bedroom", 1, 20, 2, 2, "Room1", "Grid1");
        roomService.add(validRoom);
    }


    //US145


    @Test
    void seeIfRoomsPrint() {

        // Arrange
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);

        String expectedResult = "---------------\n" +
                "Room) Description: Double Bedroom | House Floor: 1 | Width: 20.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";

        String actualResult = controller.buildRoomsString(roomService, rooms);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 149

//    @Test
//    void seeIfRoomIsRemovedFromGrid() {
//
//        //Arrange
//
//        Room room = new Room("Room", "Double Bedroom", 1, 20, 2, 2, "Room1", "Grid1");
//        validGrid.saveSensor(room);
//
//        //Act
//
//        boolean actualresult = controller.removeRoomFromGrid(validGrid, room);
//
//        //Assert
//
//        assertTrue(actualresult);
//    }

//    @Test
//    void seeIfGridListPrints() {
//
//        // Arrange
//
//        validGrid.setId(123L);
//        energyGridService.addGrid(validGrid);
//        validHouse.setGridList(energyGridService);
//        String expectedResult = "---------------\n" +
//                "123) Designation: validGrid | Max Power: 300.0\n" +
//                "---------------\n";
//
//        // Act
//
//        String actualResult = controller.buildGridListString(energyGridService);
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }

    @Test
    void seeIfRoomIsRemovedFromGridFalse() {

        //Arrange

        Room room = new Room("Room", "Double Bedroom", 1, 20, 2, 2, "Room1", "Grid1");

        //Act

        boolean actualresult = controller.removeRoomFromGrid(validGrid, room);

        //Assert

        assertFalse(actualresult);
    }

//    @Test
//    void ensureThatWeDoNotAddRoomToTheGrid() {
//        // Arrange
//
//        EnergyGridService gridList = new EnergyGridService();
//        gridList.addGrid(validGrid);
//        RoomService roomService = new RoomService();
//        Room room = new Room("Room", "Double Bedroom", 1, 20, 2, 2, "Room1", "Grid1");
//        roomService.add(room);
//        validGrid.setRoomService(roomService);
//        validHouse.setRoomService(roomService);
//        RoomDTO roomDTO = RoomMapper.objectToDTO(room);
//
//        // Act
//
//        boolean actualResult = controller.addRoomToGrid(validGrid, roomDTO, roomService);
//
//        // Assert
//
//        assertFalse(actualResult);
//    }

    @Test
    void seeIfAddPowerSourceToEnergyGridWorks() {
        // Arrange

        PowerSource powerSource = new PowerSource("PowerSourceOne", 10, 10, "12345L");

        // Act

        boolean result = controller.addPowerSourceToGrid(validGrid, powerSource);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfAddPowerSourceToEnergyGridWorksFalse() {

        // Arrange

        PowerSource powerSource = new PowerSource("PowerSource", 20, 20, "12345L");
        validGrid.addPowerSource(powerSource);

        // Act

        boolean result = controller.addPowerSourceToGrid(validGrid, powerSource);

        //Assert

        assertFalse(result);
    }

    @Test
    void seeIfCreateGridTrue() {
        // Arrange

        EnergyGrid expectedResult1 = new EnergyGrid("EG1", 400, "34576");
        EnergyGrid expectedResult2 = new EnergyGrid("EG2", 400, "34576");
        // Act

        EnergyGrid actualResult1 = controller.createEnergyGrid("EG1", 400, "34576", energyGridService);
        EnergyGrid actualResult2 = controller.createEnergyGrid("EG2", 400, "34576", energyGridService);
        // Assert

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
    }

    @Test
    void seeCreatePowerSource() {
        // Arrange

        PowerSource powerSource1 = new PowerSource("powersource1", 10, 10, "12345L");
        PowerSource powerSource2 = new PowerSource("powersource2", 123, 76, "12345L");

        // Act

        PowerSource actualResult1 = controller.createPowerSource(validGrid, "powersource1", 10, 10, energyGridService);
        PowerSource actualResult2 = controller.createPowerSource(validGrid, "powersource2", 123, 76, energyGridService);

        // Assert

        assertEquals(actualResult1, powerSource1);
        assertEquals(actualResult2, powerSource2);
    }

    @Test
    void testBuildListOfDevicesOrderedByTypeStringEmptyString() {
        //Arrange
        //Act
        String expectedResult = "---------------\n" +
                "---------------\n";
        String actualResult = controller.buildListOfDevicesOrderedByTypeString(validGrid);
        //Arrange
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testBuildListOfDevicesOrderedByTypeStringWithDevices() {
        //Arrange
        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        Address address = new Address("Rua das Flores", "431", "4512", "Porto", "Portugal");
        House house = new House("casa de praia", address, new Local(4, 5, 4), 60, 180, deviceTypeString);
        house.setMotherArea(new GeographicArea("porto", new AreaType("cidade"), 2, 3, new Local(4, 4, 100)));
        Room room1EdC = new Room("B107", "Classroom", 1, 7, 11, 3.5, "Room1", "Grid1");
        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333, "34576");
        List<Room> rl = new ArrayList<>();
        Device fridge = new Fridge(new FridgeSpec());
        room1EdC.addDevice(fridge);
        rl.add(room1EdC);
        eg.setRooms(rl);
        //Act
        String expectedResult = "---------------\n" +
                "Device type: Fridge | Device name: null | Nominal power: 0.0 | Room: B107 | \n" +
                "---------------\n";
        String actualResult = controller.buildListOfDevicesOrderedByTypeString(eg);
        //Arrange
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfBuildGridListStringWorks() {
        //Arrange

        List<EnergyGrid> returnList = new ArrayList<>();
        returnList.add(validGrid);

        Mockito.when(energyGridRepository.findAll()).thenReturn(returnList);

        String expectedResult = "---------------\n" +
                "Designation: validGrid | Max Power: 300.0\n" +
                "---------------\n";

        //Act

        String actualResult = controller.buildGridListString(energyGridService);

        //Arrange

        assertEquals(expectedResult, actualResult);
    }
}